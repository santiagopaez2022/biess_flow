/*
 * Copyright 2010 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;
import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.creditos.pq.excepcion.BeneficiarioCreditoExcepcion;
import ec.fin.biess.creditos.pq.servicio.BeneficiarioCreditoServicio;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametrizacionPQException;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.constante.ParametrosPQ;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.excepcion.ParametroNoEncontradoException;
import ec.gov.iess.creditos.focalizados.dto.ProductoDto;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.persistencia.CreditoValidacion;
import ec.gov.iess.creditos.modelo.persistencia.DetalleFocalizado;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.creditos.modelo.persistencia.PrestacionConcesion;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.SolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.VariablePrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.CreditoValidacionPk;
import ec.gov.iess.creditos.pq.excepcion.CabeceraCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.CrearCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.OrdenCompraException;
import ec.gov.iess.creditos.pq.excepcion.RecalculoAmortizacionException;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaBloqueadaException;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaCreditoException;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaSolicitudException;
import ec.gov.iess.creditos.pq.excepcion.SolicitudException;
import ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio;
import ec.gov.iess.creditos.pq.servicio.CreditoValidacionServicioLocal;
import ec.gov.iess.creditos.pq.servicio.DestinoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.DiasFeriadoServicio;
import ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiaPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.ParametroServicio;
import ec.gov.iess.creditos.pq.servicio.PrestacionConcesionServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoQuirografarioServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamocabeceraServicio;
import ec.gov.iess.creditos.pq.servicio.ResumenCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.ResumenFinPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.VariablePrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.VariableSolicitudServicio;
import ec.gov.iess.hl.modelo.OficinaIess;
import ec.gov.iess.hl.servicio.OficinaIessServicio;




/**
 * 
 * <b> Servicio que crea el crédito quirografario. </b>
 * 
 * @author cvillarreal,cbastidas
 * @version $Revision: 1.7 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:29:23 $]
 *          </p>
 */
@Stateless
public class PrestamoQuirografarioServicioImpl implements PrestamoQuirografarioServicio {

	LoggerBiess log = LoggerBiess.getLogger(PrestamoQuirografarioServicioImpl.class);

	@EJB
	private SolicitudCreditoServicio solicitudCreditoServicio;

	@EJB
	private PrestamocabeceraServicio prestamocabeceraServicio;

	@EJB
	private ResumenFinPrestamoServicio resumenFinPrestamoServicio;

	@EJB
	private DividendoPrestamoServicio dividendoPrestamoServicio;

	@EJB(beanName = "GarantiaPrestamoServicioImpl")
	private GarantiaPrestamoServicio garantiaPrestamoServicio;

	@EJB
	private VariableSolicitudServicio  variableSolicitudServicio;
	
	@EJB
	private VariablePrestamoServicio  variablePrestamoServicio;

	@EJB
	private OficinaIessServicio oficinaIessServicio;

	@EJB
	private AdministracionOrdenCompraProveedorServicio administracionOrdenCompra;

	@EJB
	private PrestacionConcesionServicio prestamoConcesionServicio;
	@EJB
	private DestinoCreditoServicio destinoCreditoServicio;	
	@EJB
	private ResumenCreditoServicio resumenCreditoServicio;	
	
	@EJB private ParametroServicio parametroServicio;
	
	@EJB private PrestamoDao prestamoDao;
	
	@EJB private AdministracionOrdenCompraProveedorServicio administracionOrdenCompraProveedorServicio;
	
	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	// INC-2272 Pensiones Alimenticias
	@EJB
	private BeneficiarioCreditoServicio beneficiarioCreditoServicio; 
	
	@EJB
	private transient CreditoValidacionServicioLocal creditoValidacionServicio;
	
	@EJB
	private GarantiaCreditoServicio garantiaCreditoServicio;
	
	private BigDecimal capacidadPago;

	@EJB
	private static DiasFeriadoServicio diaFeriadoServicio;

	public PrestamoQuirografarioServicioImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoQuirografarioServicio#crearCreditoQuirografario(ec.gov.iess.creditos.
	 * modelo.dto.DatosCredito)
	 */
	@Override
	public Prestamo crearCreditoQuirografario(DatosCredito credito)
			throws CrearCreditoQuirografarioException,
			SecuenciaBloqueadaException, BeneficiarioCreditoExcepcion, CabeceraCreditoQuirografarioException {
	
		log.info(" CREACION CREDITO");
		log.debug(" Parametros :");
		log.debug(" idTipoCredito :" + credito.getIdTipoCredito());
		log.debug(" idVariedadcredito :" + credito.getIdVariedadcredito());
		log.debug(" fechaSolicitud :" + credito.getFechaSolicitud());
		log.debug(" cedulaAfiliado :" + credito.getCedulaAfiliado());
		log.debug(" tipoSolicitante :" + credito.getTipoSolicitante());
		log.debug(" idDivisionPolitica :" + credito.getIdDivisionPolitica());
		log.debug(" tasaInteres :" + credito.getIdDivisionPolitica());

		log.debug(" 1. Consulta tipo solicitud");
		TipoSolicitudCredito tipoSolicitudCredito;
		try {
			tipoSolicitudCredito = variableSolicitudServicio.obtenerSecuencial(credito
					.getIdTipoCredito(), credito.getIdVariedadcredito());
		} catch (SecuenciaSolicitudException e) {
			throw new SecuenciaBloqueadaException(e.getMessage(), e);
		}

		SolicitudCredito solicitudCredito = new SolicitudCredito();

		solicitudCredito = solicitudCreditoServicio.crearSolicitudCreditoPq(
				tipoSolicitudCredito.getNumsectipsol(), tipoSolicitudCredito
						.getCodtipsolser(), credito.getFechaSolicitud(),
				credito.getCedulaAfiliado(), credito.getTipoSolicitante());
		log.debug(" 3. Creo la solicitud  credito "
				+ solicitudCredito.getCodestsolser());
		credito.setNut(solicitudCredito.getNut());
		credito.setSolicitudCredito(solicitudCredito);
		VariablePrestamo variablePrestamo;
		try {
			variablePrestamo = variablePrestamoServicio.obtenerSecuencial(credito
					.getIdTipoCredito(), credito.getIdVariedadcredito());
		} catch (SecuenciaCreditoException e) {
			throw new SecuenciaBloqueadaException(e.getMessage(), e);
		}
		log.debug(" 4. Incremento variable credito credito : "
				+ variablePrestamo.getSecvarpre());

		credito.setVariablePrestamo(variablePrestamo);
		credito.getGarantia()
				.setNumeroPrestamo(variablePrestamo.getSecvarpre());
		credito.setNumeroPrestamo(variablePrestamo.getSecvarpre());
		OficinaIess oficinaIess = null;

		try {
			oficinaIess = consultaOficinaIess(credito.getIdDivisionPolitica());
			credito.setOficinaIess(oficinaIess);
		} catch (OficinaIessException e) {
			log.error("Error al consultar oficina iess. ", e);
			throw new CrearCreditoQuirografarioException(e);
		}
		log.debug(" 5. Consulta oficina IESS : " + (oficinaIess == null ? " No tiene oficina" : oficinaIess.getCodregadm()));

		Prestamo prestamo = prestamocabeceraServicio.crearPrestamoPq(credito);
		log.debug(" 6. Creo cabecera prestamo : " + (oficinaIess == null ? " No tiene oficina" : oficinaIess.getCodregadm()));

		resumenFinPrestamoServicio
				.crearResumenFinPrestamoPq(credito.getIdTipoCredito(), credito
						.getIdVariedadcredito(), variablePrestamo
						.getSecvarpre(), credito.getPrestamoCalculo()
						.getPeriodoGracia().getValor(), credito
						.getPrestamoCalculo().getPlazoMeses(), credito
						.getPrestamoCalculo().getMontoPrestamo(), credito
						.getTasaInteres(), credito.getPrestamoCalculo()
						.getSeguroSaldo().getValor(), credito
						.getPrestamoCalculo().getSeguroSaldo().getTasaInteres());

		log.debug(" 7. Creo resumen de todo el prestamo : ");
		
		// INC-2272 Pensiones Alimenticias - Crea el beneficiario del credito.
		if (credito.isPagoPensionesAlimenticias()) {
			try {
				log.debug(" 7.1 Creo el beneficiario del prestamo : ");
				beneficiarioCreditoServicio.crearBeneficiarioCredito(prestamo,
						credito.getBeneficiarioCredito());
			} catch (Exception e) {
				StringBuffer msg = new StringBuffer();
				msg.append("Error al crear el beneficiario del credito de pensiones alimenticias");
				log.error(msg.toString(), e);
				throw new CrearCreditoQuirografarioException(msg.toString(), e);
			}
		}

		

		if (credito.getPrestamoCalculo().getGuardarGarantias()) {
			try {
				log.debug("Monto del prestamo:"
						+ credito.getPrestamoCalculo().getMontoPrestamo());
				garantiaPrestamoServicio.crearGarantiaPq(credito.getGarantia());
				log.debug(" 9. Creo las garantias prestamo : ");
			} catch (Exception e) {
				StringBuffer msg = new StringBuffer();
				msg.append("Error al crear las garantias del prestamo");
				log.error(msg.toString(), e);
				throw new CrearCreditoQuirografarioException(msg.toString(), e);
			}
		}

		// Guardo datos de la orden de compra si el tipo de préstamo es distinto
		// de normal
		//INC-2272 Pensiones Alimenticias
		CategoriaTipoProductoPq categoriaTipoProductoPq = TiposProductosPq
				.getCategoriaTipoProductoPq(TiposProductosPq.valueOf(credito.getOrdenCompra().getCodigoProducto())
						.getCodigoTipoProducto());
		if (categoriaTipoProductoPq == CategoriaTipoProductoPq.CAT_BIENES) {
			try {
				credito.setPrestamoOrdenCompra(prestamo);
				administracionOrdenCompra.crearOrdenCompra(credito);
			} catch (OrdenCompraException e) {
				StringBuffer msg = new StringBuffer();
				msg.append("Error al crear la orden de Compra");
				log.error(msg.toString(), e);
				throw new CrearCreditoQuirografarioException(msg.toString(), e);
			}
		}
		
		// En caso que sea un credito de PQ Focalizado guarda el detalle de los productos adquiridos y credito validacion
		if (categoriaTipoProductoPq == CategoriaTipoProductoPq.CAT_FOCALIZADOS) {
			CreditoValidacion creditoValidacion = new CreditoValidacion();
			CreditoValidacionPk creditoValidacionPk = new CreditoValidacionPk();
			creditoValidacionPk.setCodprecla(prestamo.getPrestamoPk().getCodprecla());
			creditoValidacionPk.setCodpretip(prestamo.getPrestamoPk().getCodpretip());
			creditoValidacionPk.setNumpreafi(prestamo.getPrestamoPk().getNumpreafi());
			creditoValidacionPk.setOrdpreafi(prestamo.getPrestamoPk().getOrdpreafi());
			creditoValidacion.setCreditoValidacionPk(creditoValidacionPk);
			creditoValidacion.setIdProveedor(prestamo.getIdProveedor());
			creditoValidacion.setCodigoActivacion(credito.getCodigoActivacionEncripta());
			creditoValidacion.setNumafi(prestamo.getNumafi());
			creditoValidacion.setFechaGeneracion(new Date());
			creditoValidacion.setEstado("SOL");
			creditoValidacion.setIdPuntoVenta(credito.getCodigoPuntoVenta());
			creditoValidacion.setNumeroIntento(0);
			creditoValidacion.setIdProveedorMeer(credito.getIdProveedorMeer());
			Calendar fechaCaducidad = Calendar.getInstance();
			fechaCaducidad.setTime(prestamo.getFecpreafi());
			
			Integer diasCaducidad = 0;
			// Obtiene el numero de dias a sumar para la caducidad del credito
			try {
				diasCaducidad = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.DIAS_CADUCIDAD.getIdParametro(),
						ParametrosBiessEnum.DIAS_CADUCIDAD.getNombreParametro()).getValorNumerico().intValue();
			} catch (ParametroBiessException e) {
				throw new CrearCreditoQuirografarioException(e.getMessage(), e);
			}
			
			fechaCaducidad.add(Calendar.DATE, diasCaducidad);
			fechaCaducidad.set(Calendar.HOUR, 0);
			fechaCaducidad.set(Calendar.MINUTE, 0);
			fechaCaducidad.set(Calendar.SECOND, 0);
			fechaCaducidad.set(Calendar.MILLISECOND, 0);
			creditoValidacion.setFechaCaducidad(fechaCaducidad.getTime());
			
			DetalleFocalizado detalleFocalizado = null;
			List<DetalleFocalizado> listaDetalleFocalizado = new ArrayList<DetalleFocalizado>();
			for (ProductoDto productoDto : credito.getListsProductosFocalizados()) {
				detalleFocalizado = new DetalleFocalizado();
				detalleFocalizado.setCodprecla(prestamo.getPrestamoPk().getCodprecla());
				detalleFocalizado.setCodpretip(prestamo.getPrestamoPk().getCodpretip());
				detalleFocalizado.setNumpreafi(prestamo.getPrestamoPk().getNumpreafi());
				detalleFocalizado.setOrdpreafi(prestamo.getPrestamoPk().getOrdpreafi());
				detalleFocalizado.setCodigoProductoMeer(productoDto.getCodigoMEER());
				detalleFocalizado.setEstado("ACT");
				detalleFocalizado.setPrecio(productoDto.getPrecio());
				detalleFocalizado.setCreditoValidacion(creditoValidacion);
				listaDetalleFocalizado.add(detalleFocalizado);
			}
			creditoValidacion.setListaDetalleFocalizado(listaDetalleFocalizado);
			this.creditoValidacionServicio.insertarCreditoValidacion(creditoValidacion);
		}
		
		log.info("Ingresando Resumen del credito");
		try {
			resumenCreditoServicio.registrarResumenCredito(credito, prestamo);
		} catch (Exception e) {
			throw new CrearCreditoQuirografarioException( e.getMessage(), e);
		}
		log.info("Finalizando Resumen del credito");

		

		// Guardo las prestaciones de Jubilado
		log.info("Inicio Insertando Prestacion");
		if (TipoPrestamista.JUBILADO.equals(credito
				.getTipoPrestamista()) || TipoPrestamista.AFILIADO_JUBILADO.equals(credito
						.getTipoPrestamista()) ) {
			try {
				for (PrestacionConcesion prestacion : credito
						.getCalculoCredito().getGarantia()
						.getListPrestamoConcesion()) {

					prestacion.setPretamo(prestamo);
					boolean esEmergente = credito.getCreditoEspecial() != null
							&& credito.getCreditoEspecial().equals(CreditoEspecialEnum.EMERGENTE.getCodigoCredito());
					prestacion.setPpPorcentajeCapendeudamiento(
							obtenerPorcentajeComprometimientoJubiladoPQ(esEmergente, credito.getEdadAsegurado(), new BigDecimal(credito.getPlazo())));
					prestamoConcesionServicio.registrar(prestacion);
				}
			} catch (Exception e) {
				e.printStackTrace();
				StringBuffer msg = new StringBuffer();
				msg
						.append("Error al registrar las prestaciones de concesion jubilados");
				log.error(msg.toString(), e);
				throw new CrearCreditoQuirografarioException(msg.toString(), e);
			}
		}
		log.info("Fin Insertando Prestacion");
		
		// Se verifica si ya tiene un prestamo en tramite
		List<String> estadosCredito = new ArrayList<String>();
		estadosCredito.add("GEN");
		estadosCredito.add("ELT");
		estadosCredito.add("PDA");
		estadosCredito.add("PDC");
		estadosCredito.add("PAP");
		estadosCredito.add("APR");
		estadosCredito.add("ERC");
		estadosCredito.add("BLQ");
		estadosCredito.add("PDV");
		
		BigDecimal tieneTramite = prestamoDao.consultarTienePqPorEstado(credito.getCedulaAfiliado(), estadosCredito);
		
		BigDecimal tieneTramitSac =prestamoDao.consultarTienePqSolCreTramite(credito.getCedulaAfiliado());
		if (tieneTramite.add(tieneTramitSac).compareTo(BigDecimal.ZERO) > 0) {
			throw new CrearCreditoQuirografarioException("Existe un credito en tramite para la cedula: " + credito.getCedulaAfiliado());
		}
		
		log.info("Inicio Insertando Prestacion");
		//Cambios Realizados Por Angel Sarmiento
		//Aqui Inserto MOtivo Prestamo
		destinoCreditoServicio.insertarDestinoCredito(prestamo.getPrestamoPk(), credito);
		log.info("Inicio fin Prestacion");

		return prestamo;

	}
	
	/**
	 * Obtiene el porcentaje de comprometimiento de PQ
	 * 
	 * @param creditoEmergente
	 * @param edad
	 * @param plazo
	 * @return
	 */
	private BigDecimal obtenerPorcentajeComprometimientoJubiladoPQ(boolean creditoEmergente, int edad, BigDecimal plazo) {
		try {
			if (capacidadPago == null) {
				this.capacidadPago = garantiaCreditoServicio.obtenerPorcentajeComprometimientoPQ(creditoEmergente, TipoPrestamista.JUBILADO, edad, plazo);
			}
		} catch (ParametrizacionPQException e) {
			log.error("1. Error al obtener el porcentaje de capacidad de pago en GarantiaCreditoServicioImpl.java", e);
		} catch (ParametroBiessException e) {
			log.error("2. Error al obtener el porcentaje de capacidad de pago en GarantiaCreditoServicioImpl.java", e);
		}
		return capacidadPago;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private OficinaIess consultaOficinaIess(String idDivisionPolitica)
			throws OficinaIessException {
		try

		{
			OficinaIess oficinaIess = new OficinaIess();
			oficinaIess = oficinaIessServicio
					.consultarOficinaIessDivPol(idDivisionPolitica);
			return oficinaIess;
		} catch (Exception e) {
			throw new OficinaIessException(
					" Error al consultar la oficina IESS DIVPOL= "
							+ idDivisionPolitica);
		}
	}
	
	
	/**
	 * 
	 * Reajusta el Listado de Préstamos
	 * @author dolaya
	 */
	public void reajustarListadoPrestamos() throws RecalculoAmortizacionException {
		 String consultaPQGenDiv = null;
		 List<Prestamo> listaPrestamos = null;
		try {
			consultaPQGenDiv = parametroServicio.obtenerParametro(ParametrosPQ.QPQGEN.getIdParametro());
			listaPrestamos = prestamoDao.consultaGenerica(consultaPQGenDiv);
			log.error("INICIO ELIMINAR DIVIDENDOS");
			dividendoPrestamoServicio.eliminarDividendosHistoricoLista(listaPrestamos);
			log.error("FIN ELIMINAR DIVIDENDOS");
			for (Prestamo prestamo : listaPrestamos) {
				 administracionOrdenCompraProveedorServicio.recalculaTablaAmortizacionparaCRON(
						 prestamo.getPrestamoPk().getNumpreafi(),
						 prestamo.getPrestamoPk().getOrdpreafi(),
						 prestamo.getPrestamoPk().getCodpretip(),
						 prestamo.getPrestamoPk().getCodprecla(),
						 new Date(System.currentTimeMillis()));
			}
		} catch (ParametroNoEncontradoException e) {
			throw new RecalculoAmortizacionException(e.getMessage(), e);
		} catch (SolicitudException e) {
			throw new RecalculoAmortizacionException(e.getMessage(), e);
		} catch (NoServicioPrestadoSucursalException e) {
			throw new RecalculoAmortizacionException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RecalculoAmortizacionException(e.getMessage(), e);
		} 
	 }
	
	public Calendar traerDiaHabil(int aumento) {

		Calendar c = aumentarUnDia(aumento);
		Long anio = Long.valueOf(c.get(Calendar.YEAR));
		Long mes = Long.valueOf(c.get(Calendar.MONTH) + 1L);
		Long diaDelMes = Long.valueOf(c.get(Calendar.DAY_OF_MONTH));
		int diaDeLaSemana = c.get(Calendar.DAY_OF_WEEK);
		if (!esFeriado(anio, mes, diaDelMes, "CRE") && !esFinSemana(diaDeLaSemana)) {
			return c;
		} else {
			return traerDiaHabil(aumento + 1);
		}
	}

	private Calendar aumentarUnDia(int aumento) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, aumento);
		return c;
	}

	private boolean esFeriado(Long anio, Long mes, Long dia, String modulo) {
		return diaFeriadoServicio.obtenerPorAnioMesDiaMod(anio, mes, dia, modulo) == null ? Boolean.FALSE : Boolean.TRUE;
	}

	private boolean esFinSemana(int diaDeLaSemana) {
		return diaDeLaSemana == 7 || diaDeLaSemana == 1 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public GarantiaCesantia consultarCesantiaCliente(String identificacion) {
		return garantiaPrestamoServicio.consultarCesantiaCliente(identificacion);
	}

	@Override
	public BigDecimal consultarFondosReservaTotales(String identificacion) {
		return garantiaPrestamoServicio.consultarTotalFondosReserva(identificacion);
	}
	
}