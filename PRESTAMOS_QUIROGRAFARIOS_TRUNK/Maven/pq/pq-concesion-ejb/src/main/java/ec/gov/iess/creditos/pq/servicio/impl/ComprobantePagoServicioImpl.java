/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.pq.servicio.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.enumeracion.DocumentoHabilitacionEnum;
import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoEmpleadorEnum;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.comprobante.dto.DatoComprobante;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.dao.ComprobantePagoDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.enumeracion.EstadoComprobantePago;
import ec.gov.iess.creditos.enumeracion.SituacionPrestamo;
import ec.gov.iess.creditos.enumeracion.TipoRecaudacionEnum;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoIndividualExcepcion;
import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.modelo.dto.DatosSituacionPrestamo;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosComprobante;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.PeriodoComprobante;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.ComprobantePagoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.EntidadNoEncontradaException;
import ec.gov.iess.creditos.pq.excepcion.NoTieneComprobanteVigenteException;
import ec.gov.iess.creditos.pq.excepcion.SituacionPrestamoNoExisteException;
import ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio;
import ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicioRemote;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.util.Utilities;
import ec.gov.iess.creditos.sp.AnularComprobanteJdbcSp;
import ec.gov.iess.creditos.sp.LiquidacionJdbc;
import ec.gov.iess.hl.dao.ServicioPrestadoDao;
import ec.gov.iess.hl.modelo.ServicioPrestado;
import ec.gov.iess.creditos.excepcion.AnularComprobanteExcepcion;

/**
 * Incluir aquí la descripción de la clase.
 * 
 * @version $Revision: 1.2 $ [Sep 20, 2007]
 * @author pablo
 */
@Stateless
public class ComprobantePagoServicioImpl implements ComprobantePagoServicio,
		ComprobantePagoServicioRemote {
	
	private static final LoggerBiess LOG = LoggerBiess.getLogger(ComprobantePagoServicioImpl.class);

	@EJB
	private ComprobantePagoDao comprobanteDao;

	@EJB
	private LiquidacionJdbc liquidacionJdbc;

	@EJB
	private PrestamoDao prestamoDao;

	@EJB
	private PrestamoServicio prestamoServicio;
	
	@EJB
	private ServicioPrestadoDao servicioPrestadoDao;
	
	@EJB 
	private ParametroBiessDao parametroBiessDao;
	
	@EJB 
	private AnularComprobanteJdbcSp anularComprobanteJdbcSp;
	

	private static final String TIPO_PERIODO = ConstantesCreditos.TIPO_PERIODO_DIVIDENDO;
	
	private String rucsPublicos;
	
	@PostConstruct
	private void init() {
		try {
			this.rucsPublicos = this.parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.RUC_COMPROBANTE_LIQUIDACION.getIdParametro(),
					ParametrosBiessEnum.RUC_COMPROBANTE_LIQUIDACION.getNombreParametro()).getValorCaracter();
		} catch (final ParametroBiessException e) {
			LOG.error("Error al obtener parametro de base de datos", e);
		}
	}

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio#obtenerComprobantePagoVigente(java.lang.Long)
	 */
	@Override
	public ComprobantePago obtenerComprobantePagoVigente(final Long numeroLiquidacion,final List<EstadoComprobantePago> estados)
			throws NoTieneComprobanteVigenteException {
		/*List<EstadoComprobantePago> estados = new ArrayList<EstadoComprobantePago>();
		estados.add(EstadoComprobantePago.DEP);
		estados.add(EstadoComprobantePago.GEN);*/
		final List<ComprobantePago> comprobantes = comprobanteDao
				.obtenerPorLiquidacionYEstados(numeroLiquidacion, estados);
		if (comprobantes.size() > 0) {
			final ComprobantePago comprobantePago = comprobantes.get(0);
			if (comprobantePago.getDetalle() != null) {
				// Forzar a cargar los detalles
				comprobantePago.getDetalle().size();
			}
			return comprobantePago;
		} else {
			final StringBuffer msg = new StringBuffer();
			msg.append("La liquidacion [");
			msg.append(numeroLiquidacion);
			msg.append("] no tiene un comprobante de pago vigente.");
			throw new NoTieneComprobanteVigenteException(msg.toString());
		}
	}

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio#generarComprobantePagoIndividual(ec.gov.iess.creditos.pq.modelo.persistencia.PrestamoPk,
	 *      java.util.List)
	 */
	@Override
	public ComprobantePagoPk generarComprobantePagoIndividual(
			final List<Long> dividendos, final DatosSituacionPrestamo datSituacionPrestamo)
			throws GenerarComprobantePagoIndividualExcepcion {

		final Prestamo prestamo = prestamoDao.load(datSituacionPrestamo.getPrestamoPk());

		if (prestamo == null) {
			throw new GenerarComprobantePagoIndividualExcepcion(
					"El prestamo no existe");
		}

		String politicaCorporativa = null;

		try {
			politicaCorporativa = obtenerPoliticaCorporativa(datSituacionPrestamo);
		} catch (final SituacionPrestamoNoExisteException e) {
			throw new GenerarComprobantePagoIndividualExcepcion(e);
		}

		return liquidacionJdbc.generarComprobanteConsolidado(datSituacionPrestamo.getPrestamoPk(),
				prestamo.getNumafi(),
				TIPO_PERIODO,
				dividendos,
				politicaCorporativa,
				datSituacionPrestamo.getTipoEmpleador(),
				prestamo.getEstadoPrestamo().getCodestpre(), 
				TipoRecaudacionEnum.COMPROB.name(), 
				datSituacionPrestamo.getFechaValidezComprobante().getTime());
	}
	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio#generarComprobantePagoIndividual(ec.gov.iess.creditos.pq.modelo.persistencia.PrestamoPk,
	 *      java.util.List)
	 */
	@Override
	public void anularComprobantePago(final ComprobantePago comprobantePago) 
			throws AnularComprobanteExcepcion {
		final String estado = "VEN"; //cambio ST
		final String fecha = Utilities.getCurrentDate("yyyy-MM-dd");
		final String observacion = "Anulación por funcionario";
		
		anularComprobanteJdbcSp.anularComprobante(comprobantePago,estado,fecha,observacion);
	}

	/**
	 * Obtiene la politica coporativa para la generacion de comprobante de pago
	 * individual 1. el afiliado es censante y el estado del prestamo sea VIG.
	 * 2. el afiliado se activo y el estado del prestamo sea ELC ó ELF. el caso
	 * 1 será: 'CPDIVIND', para el caso2 será: 'CPSALDEB'
	 * 
	 * @return
	 */
	private String obtenerPoliticaCorporativa(final DatosSituacionPrestamo datSituacionPrestamo)
			throws SituacionPrestamoNoExisteException {

		// TODO: Sacar este procedimiento a un solo lugar

		String returnValue = "";

		final SituacionPrestamo situacionPrestamo = prestamoServicio.obtenerSituacionPrestamo(datSituacionPrestamo);

		if (SituacionPrestamo.ACTIVO_PRESTAMO_ELC_ELF.equals(situacionPrestamo)) {
			returnValue = "CPSALDEB";
		}
		if (SituacionPrestamo.ACTIVO_PRESTAMO_VIG_MORA
				.equals(situacionPrestamo)) {
			returnValue = "CPDIVIND";
		}
		if (SituacionPrestamo.CESANTE_PRESTAMO_ELC_ELF
				.equals(situacionPrestamo)) {
			returnValue = "CPSALDEB";
		}
		if (SituacionPrestamo.CESANTE_PRESTAMO_VIG.equals(situacionPrestamo)) {
			returnValue = "CPDIVIND";
		}

		return returnValue;

	}

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio#existeComprobanteIndividualVigente(ec.gov.iess.creditos.pq.modelo.persistencia.PrestamoPk)
	 */
	@Override
	public Boolean existeComprobanteIndividualVigente(final ValidarRequisitosComprobante valReqComprobante) {

		final Long cuantos = comprobanteDao.contarPorPrestamoEstadoPTipoEstado(
				valReqComprobante.getPrestamoPk(), 
				valReqComprobante.getEstadosPrestamo(),
				valReqComprobante.getTiposComprobante(),
				valReqComprobante.getEstadosComprobante());

		if (cuantos.longValue() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio#existeComprobanteIndividualVigente(ec.gov.iess.creditos.pq.modelo.persistencia.PrestamoPk)
	 */
	@Override
	public Boolean existeComprobantesIndividualesVigentes(final ValidarRequisitosComprobante valReqComprobante) {

		final Long cuantos = comprobanteDao.contarPorAfiliadoEstadoPTipoEstado(
				valReqComprobante.getNumeroAfiliado(), 
				valReqComprobante.getEstadosPrestamo(),
				valReqComprobante.getTiposComprobante(),
				valReqComprobante.getEstadosComprobante());

		if (cuantos.longValue() > 0) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * @see ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio#obtenerComprobanteIndividualVigente(ec.gov.iess.creditos.pq.modelo.persistencia.PrestamoPk)
	 */
	@Override
	public List<ComprobantePago> obtenerComprobanteIndividualVigente(final ValidarRequisitosComprobante valReqComprobante) {
		
		return comprobanteDao.obtenerPorPrestamoEstadoPTipoEstado(valReqComprobante.getPrestamoPk(),
				valReqComprobante.getEstadosPrestamo(),
				valReqComprobante.getTiposComprobante(),
				valReqComprobante.getEstadosComprobante());
	}
	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio#obtenerComprobanteIndividualVigente(ec.gov.iess.creditos.pq.modelo.persistencia.PrestamoPk)
	 */
	@Override
	public List<ComprobantePago> obtenerComprobantesIndividualesVigentes(final ValidarRequisitosComprobante valReqComprobante) {
		
		return comprobanteDao.obtenerPorAfiliadoEstadoPTipoEstado(valReqComprobante.getNumeroAfiliado(),
				valReqComprobante.getEstadosPrestamo(),
				valReqComprobante.getTiposComprobante(),
				valReqComprobante.getEstadosComprobante());
	}

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio#obtenerPorPk(ec.gov.iess.creditos.pq.modelo.persistencia.ComprobantePagoPk)
	 */
	@Override
	public ComprobantePago obtenerPorPk(final ComprobantePagoPk comprobantePagoPk)
			throws EntidadNoEncontradaException {
		final ComprobantePago comprobantePago = comprobanteDao.load(comprobantePagoPk);
		// Forzar a que se carguen sus detalles
		if (comprobantePago != null && comprobantePago.getDetalle() != null) {
			comprobantePago.getDetalle().size();
			return comprobanteDao.load(comprobantePagoPk);
		} else {
			final StringBuffer msg = new StringBuffer();
			msg.append("El comprobante[CODCOMPAGAFI, CODTIPCOMPAG][");
			msg.append(comprobantePagoPk.getCodComPagAfi());
			msg.append(", ");
			msg.append(comprobantePagoPk.getCodTipComPag());
			msg.append("]");
			throw new EntidadNoEncontradaException(msg.toString());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio#determinaEmpleador(ec.gov.iess.creditos.modelo.
	 * persistencia.pk.PrestamoPk)
	 */
	@Override
	public String determinaEmpleador(final PrestamoPk prestamoPk) {
		String tipoEmpleador = null;
		final Prestamo prestamo = prestamoDao.load(prestamoPk);
		final String rucEmpleador = prestamo.getRucemp();
		
		final List<ServicioPrestado> listaServicioPrestado = servicioPrestadoDao.consultaActivoOCesante(prestamo.getNumafi());
		
		for (final ServicioPrestado servicioPrestamo : listaServicioPrestado) {
			if (rucEmpleador.equals(servicioPrestamo.getServicioPrestadoPk().getRucEmpleador())) {
				if ("E".equals(servicioPrestamo.getOripag())) {
					tipoEmpleador = TipoEmpleadorEnum.PUB.getCodigo();
					// Se verifica si el RUC del empleador pertenece a alguna empresa publica
					final String[] listaRucs = this.rucsPublicos.split(",");
					rucsLista : for (final String rucCodigo : listaRucs) {
						final String[] rucCodigoArray = rucCodigo.split("-");
						if (rucEmpleador.equals(rucCodigoArray[0])) {
							tipoEmpleador = rucCodigoArray[1];
							break rucsLista;
						}
					}
				} else if ("P".equals(servicioPrestamo.getOripag())) {
					tipoEmpleador = TipoEmpleadorEnum.PRI.getCodigo();
				}
				break;
			}
		}
		
		return tipoEmpleador;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio#habilitaComprobanteLiquidacion(java.util.Calendar,
	 * java.lang.String, ec.fin.biess.creditos.pq.enumeracion.DocumentoHabilitacionEnum)
	 */
	@Override
	public boolean habilitaComprobanteLiquidacion(final Calendar fechaValidacion, final String tipoEmpleador, 
			final DocumentoHabilitacionEnum documentoHabilita, final PeriodoComprobante periodoComprobante) throws PeriodoComprobanteException {
		boolean habilita = false;
			String rangos = periodoComprobante.getRangoHabilitaComprobante();
			if (DocumentoHabilitacionEnum.LIQUIDACION_ANTICIPADA.equals(documentoHabilita)) {
				rangos = periodoComprobante.getRangoHabilidaLiquidacion();
			}

			final String[] rangosHabilitados = rangos.split(":");
			for (final String rangoHabilitado : rangosHabilitados) {
				if (!rangoHabilitado.trim().isEmpty()) {
					// Solo si el dia(fechaValidacion) esta dentro del rango
					if (this.validaRango(rangoHabilitado, fechaValidacion)) {
						habilita = true;
						break;
					}
				}
			}
		return habilita;
	}
	
	@Override
	public boolean habilitaComprobantePago(final Calendar fechaValidacion, final String rangos)  {
		boolean habilita = false;	
			final String[] rangosHabilitados = rangos.trim().split("\\|");
			for (final String rangoHabilitado : rangosHabilitados) {
				if (!rangoHabilitado.trim().isEmpty()) {
					// Solo si el dia(fechaValidacion) esta dentro del rango
					if (this.validaRango(rangoHabilitado, fechaValidacion)) {
						habilita = true;
						break;
					}
				}
			}		
		return habilita;
	}
	
	/**
	 * Valida que la fecha este en rango enviado
	 * 
	 * @param rango
	 * @param fecha
	 * @return
	 */
	private boolean validaRango(final String rango, final Calendar fecha) {
		boolean valida = false;
		final String[] diasValidos = rango.split("-");
		
		if (diasValidos.length > 1) {
						
			final int diaInicial= Integer.parseInt(Utilities.obtenerdiaHoraMinuto(diasValidos[0])[0]);
			final int horaInicial= Integer.parseInt(Utilities.obtenerdiaHoraMinuto(diasValidos[0])[1]);
			final int minutoInicial= Integer.parseInt(Utilities.obtenerdiaHoraMinuto(diasValidos[0])[2]);
					
			Calendar calendarioInicial = Utilities.obtenerCalendario(diaInicial, horaInicial, minutoInicial);			
					
			final int diaFinal= Integer.parseInt(Utilities.obtenerdiaHoraMinuto(diasValidos[1])[0]);
			final int horaFinal= Integer.parseInt(Utilities.obtenerdiaHoraMinuto(diasValidos[1])[1]);
			final int minutoFinal= Integer.parseInt(Utilities.obtenerdiaHoraMinuto(diasValidos[1])[2]);
			
			Calendar calendarioFinal = Utilities.obtenerCalendario(diaFinal, horaFinal, minutoFinal);	
			
			SimpleDateFormat sdfCalenInici = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat sdfFechaActua = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat sdfCalenFinal = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			sdfCalenInici.setCalendar(calendarioInicial);
			sdfFechaActua.setCalendar(fecha);
			sdfCalenFinal.setCalendar(calendarioFinal);
			
			if((sdfCalenInici.getCalendar().getTime().before(sdfFechaActua.getCalendar().getTime())
					|sdfCalenInici.getCalendar().getTime().toString().equals(sdfFechaActua.getCalendar().getTime().toString()))
					&&(sdfFechaActua.getCalendar().getTime().before(sdfCalenFinal.getCalendar().getTime())
							|sdfFechaActua.getCalendar().getTime().toString().equals(sdfCalenFinal.getCalendar().getTime().toString()))){
				valida=true;
			}	
		
		}		
		return valida;
	}
	


	@Override
	public ComprobantePagoPk generarComprobantePagoIndividualSAC(final DatoComprobante datoComprobante) throws GenerarComprobantePagoIndividualExcepcion {
		return liquidacionJdbc.generarComprobantePagoIndividualSAC(datoComprobante);
	}
	
	
	@Override
	public List<ComprobantePago> obtenerComprobantePendPago(final String identificacion,
			final List<EstadoComprobantePago> estadosComprobante){
		return comprobanteDao.obtenerComprobantePendPago(identificacion, estadosComprobante);
	}
}