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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.consolidado.servicio.ResumenConsolidadoServicio;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.dao.ComprobantePagoDao;
import ec.gov.iess.creditos.dao.DividendoPrestamoDao;
import ec.gov.iess.creditos.dao.LiquidacionPrestamoDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.enumeracion.EstadoLiquidacion;
import ec.gov.iess.creditos.enumeracion.RolSolicitante;
import ec.gov.iess.creditos.enumeracion.SituacionPrestamo;
import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.enumeracion.TipoRecaudacionEnum;
import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.excepcion.CalculoValorRealFondosExcepcion;
import ec.gov.iess.creditos.excepcion.DebitoAutomaticoExcepcion;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionAnticipadaExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionFondosReservaExcepcion;
import ec.gov.iess.creditos.modelo.cruccta.dto.DataCrucCtaGaf;
import ec.gov.iess.creditos.modelo.dto.CalculoLiquidacion;
import ec.gov.iess.creditos.modelo.dto.DatosSituacionPrestamo;
import ec.gov.iess.creditos.modelo.dto.RequisitosCruce;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosRecaudacion;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.ComprobantePagoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.GarantiaException;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.GenerarComprobanteIndividualExcepcion;
import ec.gov.iess.creditos.pq.excepcion.NoTieneLiquidacionVigenteException;
import ec.gov.iess.creditos.pq.excepcion.SituacionPrestamoNoExisteException;
import ec.gov.iess.creditos.pq.servicio.GarantiasPorOperacionSacServicio;
import ec.gov.iess.creditos.pq.servicio.LiquidacionServicio;
import ec.gov.iess.creditos.pq.servicio.LiquidacionServicioRemote;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.util.CompararDatosCredito;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;
import ec.gov.iess.creditos.sp.LiquidacionFondosJdbc;
import ec.gov.iess.creditos.sp.LiquidacionJdbc;
import ec.gov.iess.fondosreserva.ctaindividual.servicio.CuentaIndividualServicio;

/**
 * Incluir aquÃ­ la descripciÃ³n de la clase.
 * 
 * @version $Revision: 1.7 $ [Sep 17, 2007]
 * @author pablo
 */
@Stateless
public class LiquidacionServicioImpl implements LiquidacionServicio,
		LiquidacionServicioRemote {

    /**
     * LOG
     */
	LoggerBiess log = LoggerBiess.getLogger(LiquidacionServicioImpl.class);

	@EJB
	private LiquidacionJdbc liquidacionJdbc;

	@EJB
	private PrestamoDao prestamoDao;

	@EJB
	private DividendoPrestamoDao dividendoPrestamoDao;

	@EJB
	private ComprobantePagoDao comprobantePagoAfiliadoDao;

	@EJB
	private LiquidacionPrestamoDao liquidacionPrestamoDao;

	@EJB
	private PrestamoServicio prestamoServicio;

	@EJB
	private LiquidacionFondosJdbc LiquidacionFondosJdbc;

	@EJB
	private ResumenConsolidadoServicio resumenConsolidadoServicio;
	
	@EJB(name = "CuentaIndividualServicio/local")
	private CuentaIndividualServicio cuentaIndividualServicio;

	@EJB
	private GarantiasPorOperacionSacServicio garantiasSacServicio;
	/**
	 * 
	 */
	private static final String PREREQUISITO_1 = "No Tener Mora en Pr\u00E9stamo Quirografario";
	/**
	 * 
	 */
	private static final String PREREQUISITO_2 = "No tener comprobante de pago individual generado";
	/**
	 * 
	 */
	private static final String PREREQUISITO_3 = "No tener Solicitud de Devoluci\u00F3n de Fondos de Reserva";
	/**
	 * 
	 */
	private static final String PREREQUISITO_4 = "No Tener Cargos o Bloqueos Totales en Fondos de Reserva";
	/**
	 * 
	 */
	private static final String PREREQUISITO_5 = "Tener fecha de inicio de rendimiento todos los aportes de Fondos de Reserva";
	/**
	 * 
	 */
	private static final String PREREQUISITO_6 = "Existir consistencia en los valores comprometidos de Fondos de Reserva";
	/**
	 * 
	 */
	private static final String PREREQUISITO_7 = "Tener Fondos de Reserva disponibles para cubrir la totalidad del saldo de Pr\u00E9stamo Quirografario";
	/**
	 * 
	 */
	private static final String PREREQUISITO_8 = "No tener bloqueo total en la Cta. Individual de Fondo de Reservas";
	/**
	 * 
	 */
	private static final String PREREQUISITO_9 = "Cr\u00E9ditos garantizados con fondos de reserva total o parcialmente";
	/**
	 * 
	 */
	private static final String PREREQUISITO_10 = "No tener Solicitud de Novaci\u00F3n de Cr\u00E9dito en Tr\u00E1mite";
	/**
	 
	 * Constructor vacio
	 */
	public LiquidacionServicioImpl() {
      //Constructor vacio
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public CalculoLiquidacion calcularLiquidacion(
			final ValidarRequisitosRecaudacion valReqLiqAnt,
			final TipoLiquidacion tipoLiquidacion) throws CalculoLiquidacionExcepcion {
		CalculoLiquidacion calculoLiquidacion = null;

		try {
			if (esPosibleLiquidacion(valReqLiqAnt)) {
				/*
				 * calculoLiquidacion
				 * =liquidacionGenericaServicio.calculoLiquidacion(
				 * valReqLiqAnt.getPrestamo().getPrestamoPk(),
				 * TipoLiquidacion.PRE);
				 */
				calculoLiquidacion = liquidacionJdbc.calcularLiquidacion(
						valReqLiqAnt.getPrestamo().getCreditoPk(),
						tipoLiquidacion);
			}
		} catch (final LiquidacionAnticipadaExcepcion e) {
			throw new CalculoLiquidacionExcepcion(e);
		}
		log
				.info("La liquidacion es de:"
						+ calculoLiquidacion.getCapitalTotal());

		return calculoLiquidacion;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public CalculoLiquidacion calcularLiquidacionFondos(
			final ValidarRequisitosRecaudacion valReqCruces)
			throws LiquidacionFondosReservaExcepcion {
		CalculoLiquidacion calculoLiquidacion = null;

		try {
			if (esPosibleLiquidacionFondos(valReqCruces)) {
				calculoLiquidacion = liquidacionJdbc.calcularLiquidacion(
						valReqCruces.getPrestamo().getPrestamoPk(),
						valReqCruces.getTipoLiquidacion());
			}
		} catch (final CalculoLiquidacionExcepcion e) {
			throw new LiquidacionFondosReservaExcepcion(e);
		}
		log
				.info("La liquidacion es de:"
						+ calculoLiquidacion.getCapitalTotal());

		return calculoLiquidacion;
	}

	@Override
	public BigDecimal calcularValorFondos(final PrestamoPk prestamoPk,final String cedula,final String cumpleImposiciones)
			throws CalculoValorRealFondosExcepcion {

		try {
			return LiquidacionFondosJdbc.calculoValorRealFondos(prestamoPk,cedula,cumpleImposiciones);
		} catch (final CalculoValorRealFondosExcepcion e) {
			throw new CalculoValorRealFondosExcepcion(e);
		}

	}

	@Override
	public BigDecimal generarLiquidacionAnticipada(
			final ValidarRequisitosRecaudacion valReqLiqAnt,
			final TipoLiquidacion tipoLiquidacion)
			throws LiquidacionAnticipadaExcepcion,
			GenerarComprobantePagoExcepcion {

		BigDecimal numeroLiquidacion = null;

		if (esPosibleLiquidacion(valReqLiqAnt)) {
			// Prestamo prestamo = prestamoDao.load(prestamoPk);

			// Generar la liquidacion
			final Map resultado = liquidacionJdbc.generarLiquidacionConsolidado(
					valReqLiqAnt.getPrestamo().getCreditoPk(), 
					valReqLiqAnt.getPrestamo().getNumafi(), 
					valReqLiqAnt.getPrestamo().getEstadoPrestamo().getCodestpre(), 
					tipoLiquidacion, 
					valReqLiqAnt.getTipoEmpleador(), 
					TipoRecaudacionEnum.LIQPRE.name(), 
					valReqLiqAnt.getFechaValidezLiquidacion().getTime());
			
			final Long numeroLiquidacionT = Long.valueOf(resultado.get("ao_nnumliqpre").toString());
			final LiquidacionPrestamo liquidacionPrestamo = liquidacionPrestamoDao
					.load(numeroLiquidacionT);

			numeroLiquidacion = BigDecimal.valueOf(numeroLiquidacionT.longValue());
			
			log.info("Liquidacion: "
					+ liquidacionPrestamo.getNumeroLiquidacion());

			final String codigoComprobante = (String) resultado.get("ao_ccodcompag");
			final String tipoComprobante = (String) resultado.get("ao_ccodtipcompag");

			final ComprobantePagoPk comprobantePagoAfiliadoPk = new ComprobantePagoPk();
			comprobantePagoAfiliadoPk.setCodComPagAfi(codigoComprobante);
			comprobantePagoAfiliadoPk.setCodTipComPag(tipoComprobante);

			final ComprobantePago comprobantePago = comprobantePagoAfiliadoDao
					.load(comprobantePagoAfiliadoPk);
			log.info(" estado comprobantePago: "
					+ comprobantePago.getEstadoComprobante());
			log
					.info(" comprobantePago: "
							+ comprobantePago.getNumeroAfiliado());

		}
		return numeroLiquidacion;
	}

	@Override
	public BigDecimal generarLiquidacionFondos(
			final ValidarRequisitosRecaudacion valReqCruces)
			throws LiquidacionFondosReservaExcepcion {

		BigDecimal numeroLiquidacion = null;

		if (esPosibleLiquidacionFondos(valReqCruces)) {
			// Generar la liquidacion
			numeroLiquidacion = LiquidacionFondosJdbc.generarLiquidacionFondos(
					valReqCruces.getPrestamo().getCreditoPk(), valReqCruces
							.getPrestamo().getNumafi(), valReqCruces
							.getPrestamo().getCoddivpol(), valReqCruces
							.getCumpleImposiciones(), valReqCruces
							.getEstadoAfiliadoFondos());

			log.info("Liquidacion: " + numeroLiquidacion);

		}
		return numeroLiquidacion;
	}
	
	/**
	 * Genera la liquidacion fondo reserva
	 * @param valReqCruces
	 * @return
	 * @throws LiquidacionFondosReservaExcepcion
	 */
	public void generarLiquidacionFondosGaf(
			final ValidarRequisitosRecaudacion valReqCruces)
			throws LiquidacionFondosReservaExcepcion {


		if (esPosibleLiquidacionFondos(valReqCruces)) {
			DataCrucCtaGaf dataCruCtaGaf=new DataCrucCtaGaf();
			dataCruCtaGaf.setCedula(valReqCruces
					.getPrestamo().getNumafi());
			dataCruCtaGaf.setCoddivpol(valReqCruces
					.getPrestamo().getCoddivpol());
			dataCruCtaGaf.setCumpleImposiciones( valReqCruces
					.getCumpleImposiciones());
			dataCruCtaGaf.setEstadoAfiliado(valReqCruces
					.getEstadoAfiliadoFondos());
			dataCruCtaGaf.setPrestamoPk(valReqCruces.getPrestamo().getCreditoPk());
			dataCruCtaGaf.setValorCruceCta(valReqCruces.getCalculoLiquidacion().getValorPorCancelar());
			// Generar la liquidacion
			 LiquidacionFondosJdbc.generarLiquidacionFondosGaf(
					 dataCruCtaGaf);


		}
	}

	@Override
	public Boolean esPosibleLiquidacion(
			final ValidarRequisitosRecaudacion valReqLiqAnt)
			throws LiquidacionAnticipadaExcepcion {

		// 1. El estado del prestamo debe ser VIG (Vigente).
		// Prestamo prestamo = prestamoDao.load(pk);

		if (valReqLiqAnt.getPrestamo() == null) {
			throw new LiquidacionAnticipadaExcepcion("El prestamo no existe");
		}

		if (!valReqLiqAnt.getPrestamo().getEstadoPrestamo().getCodestpre()
				.equals(valReqLiqAnt.getEstadoCredito())) { // VIG
			throw new LiquidacionAnticipadaExcepcion(
					"Su prestamo no esta vigente");
		}
		
		if (!RolSolicitante.FUN.name().equals(valReqLiqAnt.getFlujo())) {
			// 2. El prestamo no debe tener mÃ¡s de dos dividendos en MOR, si tiene
			// tres no se permitirÃ¡ la liquidaciÃ³n.
			final Long dividendosMora = dividendoPrestamoDao
					.contarDividendosPorPrestamoYEstado(valReqLiqAnt.getPrestamo()
							.getCreditoPk(), valReqLiqAnt.getDividendosMora()); // MOR
			if (dividendosMora.longValue() > 2) {
				throw new LiquidacionAnticipadaExcepcion(
						"Su prestamo tiene mas de 2 dividendos en mora, favor generar comprobante de pago individual antes de proceder a la liquidacion.");
			}
		}

		// 3. El prestamo debe tener por lo menos dos dividendos en estado REG
		// (registrado).
		final Long dividendosRegistrados = dividendoPrestamoDao
				.contarDividendosPorPrestamoYEstado(valReqLiqAnt.getPrestamo()
						.getCreditoPk(), valReqLiqAnt.getDividendosReg()); // REG
		if (dividendosRegistrados.longValue() < 2) {
			throw new LiquidacionAnticipadaExcepcion(
					"El prestamo debe tener por lo menos 2 dividendos pendientes de pago");
		}

		// 4. No deben existir dividendos en estado ECO (tramite de pago)
		final Long dividendosEco = dividendoPrestamoDao
				.contarDividendosPorPrestamoYEstado(valReqLiqAnt.getPrestamo()
						.getCreditoPk(), valReqLiqAnt.getDividendosEco()); // ECO
		if (dividendosEco.longValue() > 0) {
			String mensaje = "El prestamo no debe tener dividendos en tramite de pago";
			if (RolSolicitante.FUN.name().equals(valReqLiqAnt.getFlujo())) {
				mensaje = "El asegurado tiene dividendos en comprobante de pago. Se debe cancelar primero su comprobante o anular el mismo antes de generar la liquidación";
			} 
			throw new LiquidacionAnticipadaExcepcion(mensaje);
		}

		// 5. No deben existir comprobantes de pago del tipo PAGIND en estado
		// GEN o DEP.
		final Long comprobantes = comprobantePagoAfiliadoDao
				.contarPorPrestamoTipoYEstado(valReqLiqAnt.getPrestamo()
						.getCreditoPk(), "PAGIND", valReqLiqAnt
						.getEstadoComprobantePago());
		if (comprobantes.longValue() > 0) {
			String mensaje = "El prestamo no debe tener comprobantes de pago";
			if (RolSolicitante.FUN.name().equals(valReqLiqAnt.getFlujo())) {
				mensaje = "El asegurado tiene dividendos en comprobante de pago. Se debe cancelar primero su comprobante o anular el mismo antes de generar la liquidación";
			} 
			throw new LiquidacionAnticipadaExcepcion(mensaje);
		}

		return true;
	}

	@Override
	public Boolean esPosibleLiquidacionFondos(
			final ValidarRequisitosRecaudacion valReqCruces)
			throws LiquidacionFondosReservaExcepcion {

		// 1. El estado del prestamo debe ser VIG (Vigente).
		final Prestamo prestamo = prestamoDao.load(valReqCruces.getPrestamo()
				.getCreditoPk());

		if (prestamo == null) {
			throw new LiquidacionFondosReservaExcepcion("El prestamo no existe");
		}

		if (!prestamo.getEstadoPrestamo().getCodestpre().equals(
				valReqCruces.getEstadoCredito())) { // VIG
			throw new LiquidacionFondosReservaExcepcion(
					"Su pr\u00E9stamo no esta vigente");
		}

		// 2. No pueden hacer cruce de cuentas los de codprecla 21,23,24 y 25, a
		// menos que sean jubilados activos y tengan fondos

		final boolean existeTipo = CompararDatosCredito.CompararTipos(valReqCruces
				.getTipoCredito(), prestamo.getPrestamoPk().getCodprecla()
				.longValue());

		if (existeTipo) {
			if (prestamo.getPrestamoPk().getCodprecla().equals(new Long(23))) {
				throw new LiquidacionFondosReservaExcepcion(
						"No puede realizar cruce de cuentas.");
			}
			if (prestamo.getRucemp().equals(ConstantesCreditos.RUC_IESS)
					&& (prestamo.getPrestamoPk().getCodprecla().equals(
							new Long(21))
							|| prestamo.getPrestamoPk().getCodprecla().equals(
									new Long(24)) || prestamo.getPrestamoPk()
							.getCodprecla().equals(new Long(25)))) {

				throw new LiquidacionFondosReservaExcepcion(
						"No puede realizar cruce de cuentas.");

			}
		}

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<RequisitosCruce> cargarRequisitoCruceCuentas(final ValidarRequisitosRecaudacion valReqCruces)
			throws LiquidacionFondosReservaExcepcion, GarantiasSacException {


		final BigDecimal valorCancelarSac= valReqCruces.getCalculoLiquidacion().getValorPorCancelar();
		final List<RequisitosCruce> requisitosCruce = new ArrayList<RequisitosCruce>();
		// Prestamo prestamo = prestamoDao.load(valReqCruces.getPk());
		/*
		 * Carlos Bastidas: INC-6047 se agrega Servicios de fondos de reserva
		 * para validar si cumple imposiciones y tambien el estado del afiliado
		 * en fondos de reserva"
		 */
		HashMap imposicionesMap = new HashMap();
		imposicionesMap = cuentaIndividualServicio.cumpleReglasLiberacionFR(valReqCruces.getPrestamo().getNumafi());
		if ((String) imposicionesMap.get("codigoLiberacionFR") == "2") {
			throw new LiquidacionFondosReservaExcepcion ((String) imposicionesMap.get("mensaje"));
		} else {
			valReqCruces.setCumpleImposiciones((String) imposicionesMap
					.get("codigoLiberacionFR"));
			valReqCruces.setEstadoAfiliadoFondos((String) imposicionesMap
					.get("estadoAfiliado"));
		}
		//Carlos Bastidas: INC-6047 Se valida si es fallecido
		final ResumenConsolidado resumenConsolidado = resumenConsolidadoServicio
		.getResumenByCedula(valReqCruces.getPrestamo().getNumafi());
		
		if (resumenConsolidado == null)
		{
			throw new LiquidacionFondosReservaExcepcion ("NO EXITO REGISTRO EN LA CALIFICACION GENERAL");
		}
		final boolean esFallecido = "1".equals(resumenConsolidado.getFallecido());
		if (esFallecido) {
			throw new LiquidacionFondosReservaExcepcion ("EN LA CALIFICACION GENERAL CONSTA CON REGISTRO DE FALLECIMIENTO");
		}
		// 1. El prestamo no debe tener dias de mora, del valor devuelto por el
		// SAC
		if (valReqCruces.getPrestamo().getDiasMora() > 0) {
			requisitosCruce.add(new RequisitosCruce(1, PREREQUISITO_1, false,
					"El Pr\u00E9stamo Quirografario tiene dividendos en mora"));
		} else {
			requisitosCruce.add(new RequisitosCruce(1, PREREQUISITO_1, true,
								"El Pr\u00E9stamo Quirografario no tiene dividendos en mora"));
		}
		log.info("# de dias mora: " + valReqCruces.getPrestamo().getDiasMora());
		// 2. No deben existir comprobantes de pago en estado EMI (tabla
		// rec_transaccion_tbl)
		//boolean existeComprobante = existeComprobanteEMI(valReqCruces.getPrestamo());
		final boolean existeComprobante = existeComprobantesPorEstados(valReqCruces.getPrestamo(), Arrays.asList("EMI", "SRV", "REV"));
		if (existeComprobante) {
			requisitosCruce.add(new RequisitosCruce(2, PREREQUISITO_2, false,
					"Usted tiene generado un comprobante de pago individual"));
		} else {
			requisitosCruce.add(new RequisitosCruce(2, PREREQUISITO_2, true,
							"Usted No tiene generado un comprobante de pago individual"));
		}
		log.info("Validacion del comprobante de pago en estado EMI es: " + existeComprobante);
		// 3. Verificar que no tenga solicitud de fondos de Reserva
		// Carlos Bastidas: INC-6047 Se agrega validación de solicitud de fondos  de reserva en trámite
		final BigDecimal solicitudFondos = dividendoPrestamoDao
				.comprobarSolicitudFondos(valReqCruces.getPrestamo()
						.getNumafi(), valReqCruces.getTipoSolicitudFondos(),
						valReqCruces.getEstadoSolicitudFondos());
		final BigDecimal solicitudFondosTramite = dividendoPrestamoDao
				.comprobarSolicitudFondosTramite(valReqCruces.getPrestamo()
						.getNumafi(), valReqCruces
						.getEstadoSolicitudFondosTramite());
		if (solicitudFondos.longValue() > 0
				|| solicitudFondosTramite.longValue() > 0) {
			requisitosCruce
					.add(new RequisitosCruce(
							3,
							PREREQUISITO_3,
							false,
							"Usted tiene una solicitud de Fondos de Reserva en tr\u00E1mite"));
		} else {
			requisitosCruce
					.add(new RequisitosCruce(
							3,
							PREREQUISITO_3,
							true,
							"Usted No tiene una solicitud de Fondos de Reserva en tr\u00E1mite"));
		}
		// 4. Comprobar si el afiliado tiene cargos
		final BigDecimal cargosAfiliado = dividendoPrestamoDao.comprobarCargos(
				valReqCruces.getPrestamo().getNumafi(), valReqCruces
						.getEstadoCargoReg(), valReqCruces.getEstadoCargoPro());
		final BigDecimal bloqueosAfiliado = dividendoPrestamoDao.comprobarBloqueos(
				valReqCruces.getPrestamo().getNumafi(), valReqCruces
						.getEstadoBloqueado());
		if (cargosAfiliado.longValue() > 0 || bloqueosAfiliado.longValue() > 0) {
			requisitosCruce
					.add(new RequisitosCruce(
							4,
							PREREQUISITO_4,
							false,
							"La cuenta individual de Fondos de Reserva tiene cargos o bloqueos totales"));
		} else {
			requisitosCruce
					.add(new RequisitosCruce(
							4,
							PREREQUISITO_4,
							true,
							"La cuenta individual de Fondos de Reserva No tiene cargos o bloqueos totales"));
		}

		/**
		 * Carlos Bastidas: INC-6047 Se agrega validación para excluir aportes
		 * que no cumplen imposiciones"
		 */
		// 5. Verifica si todos los aportes tienen fecha de inicio de
		// rendimiento.
		final BigDecimal aportesFecha = dividendoPrestamoDao.comprobarAportesFecha(
				valReqCruces.getPrestamo().getNumafi(), valReqCruces
						.getTipoAporte(), valReqCruces.getCumpleImposiciones());
		if (aportesFecha.longValue() > 0) {
			requisitosCruce
					.add(new RequisitosCruce(
							5,
							PREREQUISITO_5,
							false,
							"Algunos Aportes de Fondos de Reserva no tienen fecha de inicio de rendimiento – Favor intente en 48 horas"));
		} else {
			requisitosCruce
					.add(new RequisitosCruce(
							5,
							PREREQUISITO_5,
							true,
							"Todos los aportes tienen fecha de inicio de rendimiento"));
		}

		// 6. Comprobar si el afiliado tiene errores
		final Map resultado = LiquidacionFondosJdbc
				.validarLiquidacionFondos(valReqCruces.getPrestamo().getPrestamoPk(), valReqCruces.getPrestamo()
						.getNumafi());
		final String codigoError = (String) resultado.get("AOCRESPRO");
		// String mensajeError = (String) resultado.get("AOCMENERR");
		if (!"1".equals(codigoError.trim())) {
			requisitosCruce
					.add(new RequisitosCruce(
							6,
							PREREQUISITO_6,
							false,
							"No existe consistencia en los valores comprometidos con Fondos de Reserva – Favor intente en 48 horas"));
		} else {
			requisitosCruce
					.add(new RequisitosCruce(
							6,
							PREREQUISITO_6,
							true,
							"Existe consistencia en los valores comprometidos con Fondos de Reserva"));
		}

		// 7. Verifica si el valor de fondos de reserva alcanza a cancelar la
		// totalidad del crédito, o no tiene valor por cancelar.
		BigDecimal valorRealFondos = new BigDecimal(0);
		/**
		 * Carlos Bastidas: INC-6047 Se agrega validación si cumple imposiciones
		 * de fondos de reserva"
		 */
		try {
			valorRealFondos = LiquidacionFondosJdbc
					.calculoValorRealFondos(valReqCruces.getPrestamo().getPrestamoPk(),valReqCruces.getPrestamo().getNumafi()
							                ,valReqCruces.getCumpleImposiciones());
		} catch (final CalculoValorRealFondosExcepcion e) {
			log.error("Error al calcular el valor real d fondos",e);
		}
		log.info("Valor a cancelar: "
				+ valorCancelarSac.longValue());
		log.info("Valor de fondos: " + valorRealFondos.longValue());

		if (valorCancelarSac.longValue() <= 0) {
			requisitosCruce
					.add(new RequisitosCruce(
							7,
							PREREQUISITO_7,
							false,
							"No existe saldo de Pr\u00E9stamo Quirografario para cruce de cuentas"));
		} else if (valorCancelarSac.longValue() > 0
				&& valorCancelarSac.longValue() <= valorRealFondos
						.longValue()) {
			requisitosCruce
					.add(new RequisitosCruce(
							7,
							PREREQUISITO_7,
							true,
							"El valor de Fondos de Reserva alcanza a cubrir la totalidad del saldo del Pr\u00E9stamo Quirografario"));
		} else {
			if (valorCancelarSac.longValue() > 0
					&& valorCancelarSac.longValue() > valorRealFondos
							.longValue()) {
				requisitosCruce
						.add(new RequisitosCruce(
								7,
								PREREQUISITO_7,
								false,
								"El valor de Fondos de Reserva no alcanza a cubrir la totalidad del saldo del Pr\u00E9stamo Quirografario"));
			}
		}

		// RT: 8. Si el campo crez1 de la tabla frafitactces es "1" no es
		// valido, caso contrario es valido
		if (resumenConsolidado == null) {
			requisitosCruce
					.add(new RequisitosCruce(
							8,
							PREREQUISITO_8,
							false,
							"Usted tiene bloqueo total en la cuenta de Fondo de Reservas"));
			log.info("entro al null");
		} else {
			final String creditoEspecial = resumenConsolidado.getCreditoEspecial()
					.trim();
			if ("1".equals(creditoEspecial)) {
				requisitosCruce
						.add(new RequisitosCruce(
								8,
								PREREQUISITO_8,
								false,
								"Usted tiene bloqueo total en la cuenta de Fondo de Reservas"));
			} else {
				requisitosCruce
						.add(new RequisitosCruce(
								8,
								PREREQUISITO_8,
								true,
								"Usted tiene bloqueo total en la cuenta de Fondo de Reservas"));
			}
		}
		// RQ 8.1 Validacion de garantias comprometias fondos reserva desde SAC
		final boolean existeGarantiaFR = validarGarantiasPorOperacionSAC(valReqCruces);		
		if (existeGarantiaFR) {
			requisitosCruce
					.add(new RequisitosCruce(9, PREREQUISITO_9,
							true, "El cr\u00E9dito s\u00ED tiene garant\u00EDas disponibles"));
		} else {
			requisitosCruce
					.add(new RequisitosCruce(9, PREREQUISITO_9,
							false, "El cr\u00E9dito no tiene garant\u00EDas disponibles fondos de reserva."));
		}
		
		final Prestamo prestamo=valReqCruces.getPrestamo();
		final boolean tieneSolNov=prestamoDao.tieneSolicitudNovacionTramite(prestamo.getNumCancelacionArmado(),prestamo.getNumafi());
		if (tieneSolNov) {
			requisitosCruce
					.add(new RequisitosCruce(10, PREREQUISITO_10,
							false, "Tiene solicitud de novaci\u00F3n en tr\u00E1mite"));
		} else {
			requisitosCruce
					.add(new RequisitosCruce(10, PREREQUISITO_10,
							true, "No tiene solicitud de novaci\u00F3n en tr\u00E1mite"));
		}

		return requisitosCruce;
	}

	

	@Override
	public Boolean existeComprobanteEMI(final Prestamo prestamo) {

		return comprobantePagoAfiliadoDao.validarComprobantePorEstadoEMI(prestamo.getNut(), prestamo.getNumafi());

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Boolean esPosibleGenCompIndividual(
			final DatosSituacionPrestamo datSituacionPrestamo)
			throws GenerarComprobanteIndividualExcepcion {

		try {
			prestamoServicio.obtenerSituacionPrestamo(datSituacionPrestamo);
		} catch (final SituacionPrestamoNoExisteException e) {
			throw new GenerarComprobanteIndividualExcepcion(e.getMessage());
		}

		return true;
	}

	@Override
	public Long obtenerLiquidacionVigente(final PrestamoPk prestamoPk)
			throws NoTieneLiquidacionVigenteException {
		final List<LiquidacionPrestamo> liquidaciones = liquidacionPrestamoDao
				.obtenerLiquidacionPorPrestamoYEstado(prestamoPk,
						EstadoLiquidacion.ECO);
		if (liquidaciones.size() > 0) {
			final LiquidacionPrestamo liquidacionPrestamo = liquidaciones.get(0);
			return liquidacionPrestamo.getNumeroLiquidacion();
		} else {
			throw new NoTieneLiquidacionVigenteException(
					"El credito no tiene liquidacion vigente");
		}

	}
	
	public List<LiquidacionPrestamo> obtenerLiquidacionesVigente(final String numeroAfiliado)
		throws NoTieneLiquidacionVigenteException {
		final List<LiquidacionPrestamo> liquidaciones = liquidacionPrestamoDao
				.obtenerLiquidacionPorAfiliadoYEstado(numeroAfiliado,
						EstadoLiquidacion.ECO);
		if (liquidaciones.size() > 0) {
			return liquidaciones;
		} else {
			throw new NoTieneLiquidacionVigenteException(
					"El credito no tiene liquidacion vigente");
		}
	}

	@Override
	public LiquidacionPrestamo consultar(final Long numeroLiquidacion) {
		final LiquidacionPrestamo liquidacionPrestamo = liquidacionPrestamoDao
				.load(numeroLiquidacion);
		// Obligar al entity manager a cargar los detalles
		liquidacionPrestamo.getDetalle().size();
		return liquidacionPrestamo;
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public String obtenerPoliticaCorporativa(
			final DatosSituacionPrestamo datSituacionPrestamo)
			throws SituacionPrestamoNoExisteException {

		// TODO: Sacar este procedimiento a un solo lugar

		String returnValue = "";

		final SituacionPrestamo situacionPrestamo = prestamoServicio
				.obtenerSituacionPrestamo(datSituacionPrestamo);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionServicio#buscarLiquidacionPorNumeroTipoEstado(java.lang.Long,
	 * java.lang.String, ec.gov.iess.creditos.enumeracion.EstadoLiquidacion)
	 */
	@Override
	public LiquidacionPrestamo buscarLiquidacionPorNumeroTipoEstado(final Long numeroLiquidacion, final String tipoLiquidacion,
			final EstadoLiquidacion estadoLiquidacion) {
		return liquidacionPrestamoDao.buscarLiquidacionPorNumeroTipoEstado(numeroLiquidacion, tipoLiquidacion, estadoLiquidacion);
	}

	/**
	 * Permite validar las garantias desde el core SAC
	 * 
	 * @param numeroOperacion
	 * @return
	 * @throws GarantiaException
	 * @throws GarantiasSacException
	 */
	private boolean validarGarantiasPorOperacionSAC(final ValidarRequisitosRecaudacion valReqCruces)
			throws GarantiasSacException {
		boolean validarGarantiaFR = false;
		final OperacionSacRequest operacionSacRequest = new OperacionSacRequest();
		final OperacionRequestDto operacion = new OperacionRequestDto();
		operacion.setNumero(valReqCruces.getPrestamo().getNumOperacionSAC());
		operacion.setNut(valReqCruces.getPrestamo().getNut());
		operacion.setTipoProducto(valReqCruces.getPrestamo().getDestinoComercial().getCodProdPq());
		final ClienteRequestDto cliente = new ClienteRequestDto();
		cliente.setTipoCliente(FuncionesUtilesSac.obtenerTipoPrestamista(valReqCruces.getTipoPrestamista()));
		operacionSacRequest.setCliente(cliente);
		operacionSacRequest.setOperacion(operacion);
		final InformacionGarantias informacionGarantias = garantiasSacServicio
				.obtenerGarantiasPorOperacion(operacionSacRequest);
		if (!informacionGarantias.getListaGarantiasFR().isEmpty() && informacionGarantias.getTotalGarantiasFondoReserva().signum()==1) {
			validarGarantiaFR = true;
		}
		return validarGarantiaFR;
	}

	@Override
	public Boolean existeComprobantesPorEstados(final Prestamo prestamo, final List<String> estados) {
		return comprobantePagoAfiliadoDao.validarComprobantePorEstados(prestamo.getNut(), prestamo.getNumafi(),estados);
	}

	@Override
	public boolean tieneSolicitudNovacionTramite(final Long numCanceladoNovacion, final String identificacion) {
		
		return prestamoDao.tieneSolicitudNovacionTramite(numCanceladoNovacion, identificacion);
	}
	
	
	/**
	 * REQ 444
	 */
	public Boolean existeOperacionesEnDebitoAutomatico(final String identificacion, final Long operacion, final Long nut) {
		return comprobantePagoAfiliadoDao.consultarDebitoAutomaticoPeriodo(identificacion, operacion,nut);
	}
	
	/**
	 * REQ 444 N1.
	 */
	public String validarDebitoAutomatico(String identificacion, Long operacion, Long nut) throws DebitoAutomaticoExcepcion {
		
		return	comprobantePagoAfiliadoDao.consultarDebitoAutomatico(identificacion, operacion, nut); 			
					
	}
	
}