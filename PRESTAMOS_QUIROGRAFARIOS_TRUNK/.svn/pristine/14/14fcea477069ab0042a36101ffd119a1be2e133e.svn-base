/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoCalculoEnum;
import ec.fin.biess.creditos.pq.excepcion.ConsultaParametroException;
import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.excepcion.PrestacionPensionadosException;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionIessServicioDto;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado;
import ec.fin.biess.creditos.pq.modelo.dto.ParamsReglasPrecalificacion;
import ec.fin.biess.creditos.pq.modelo.dto.ValidarRequisitosPrecalificacionBiess;
import ec.fin.biess.creditos.pq.servicio.ConsultaPrestacionPensionadosServicioLocal;
import ec.fin.biess.creditos.pq.servicio.ParametroCreditoServicio;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.burocredito.modelo.BuroCreditoDetalle;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.consolidado.servicio.ResumenConsolidadoServicio;
import ec.gov.iess.creditos.dao.DividendoPrestamoDao;
import ec.gov.iess.creditos.dao.GarantiaCesantiaDao;
import ec.gov.iess.creditos.dao.ITransaccionRecaudacionDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.enumeracion.EstadoComprobantePago;
import ec.gov.iess.creditos.enumeracion.TipoPrecalificacionEnum;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TipoRequisitoSimulacionEnum;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.CalculoLiquidacion;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.dto.DatosOrdenCompra;
import ec.gov.iess.creditos.modelo.dto.DetalleBuroCredito;
import ec.gov.iess.creditos.modelo.dto.Garantia;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosFondos;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosPrecalificacion;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.TransaccionRecaudacion;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.InformacionAportacionResponseDto;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigible;
import ec.gov.iess.creditos.pq.excepcion.AportacionesException;
import ec.gov.iess.creditos.pq.excepcion.GarantiaException;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.PHEnTramiteException;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.helper.reglas.ReglasJubilado;
import ec.gov.iess.creditos.pq.servicio.AportacionesServicioLocal;
import ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaParametroServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoPQEmpSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio;
import ec.gov.iess.creditos.pq.servicio.PrecalificacionServicioRemote;
import ec.gov.iess.creditos.pq.servicio.SolicitanteServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.ValidarPHEnTramiteServicioLocal;
import ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicio;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.pq.util.EstadosCredito;
import ec.gov.iess.creditos.pq.util.GeneradorListaPrestaciones;
import ec.gov.iess.creditos.pq.util.PrecalificacionUtil;
import ec.gov.iess.creditos.pq.util.ValidadorPrecalificacion;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancaria;
import ec.gov.iess.hl.dao.ServicioPrestadoDao;
import ec.gov.iess.hl.exception.ImposicionException;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.hl.exception.SucursalException;
import ec.gov.iess.hl.modelo.Imposicion;
import ec.gov.iess.hl.servicio.ImposicionServicio;
import ec.gov.iess.hl.servicio.ServicioPrestadoServicio;
import ec.gov.iess.servicio.fondoreserva.dao.CuentaFondoReservaDao;
import ec.gov.iess.servicio.fondoreserva.servicio.FondoReservaServicio;

/**
 * 
 * <b> Realiza la precalificación del préstamo quirografario </b>
 * 
 * @author pmlopez,cbastidas
 * @version $Revision: 1.12 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:29:23 $]
 *          </p>
 */
@Stateless
public class PrecalificacionServicioUsaResumenImpl implements PrecalificacionServicio, PrecalificacionServicioRemote {

	@EJB
	private ResumenConsolidadoServicio resumenConsolidadoServicio;

	@EJB
	private PrestamoDao prestamoDao;

	@EJB
	private DividendoPrestamoDao dividendoDao;

	@EJB
	ServicioPrestadoDao servicioPrestadoDao;

	@EJB
	private ImposicionServicio imposicionServicio;

	@EJB
	private ValidarPHEnTramiteServicioLocal validarPHEnTramiteServicio;

	//@EJB(beanName = "GarantiaCreditoServicioImpl")
        @EJB(name = "GarantiaCreditoServicioImpl/local")
	private GarantiaCreditoServicio garantiaCreditoServicio;

	@EJB
	private VerificacionCreditoServicio verificacionCreditoServicio;

	@EJB
	ServicioPrestadoServicio servicioPrestado;

	@EJB(beanName = "FondoReserva2ServicioImpl")
	FondoReservaServicio fondoReservaServicio;

	@EJB
	private SolicitudCreditoServicio solicitudCreditoServicio;

	@EJB
	private ParametroCreditoServicio parametroCreditoServicio;

	// INC-2135 Pensiones Jubilados/Pensionistas

	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private AportacionesServicioLocal aportacionesServicio;
	
	@EJB
	private CuentaFondoReservaDao cuentaFondoReservaDao;
	
	@EJB
	private ConsultaPrestacionPensionadosServicioLocal consultaPrestaciones;
	
	@EJB
	private SolicitanteServicio solicitanteServicio;
	
	private CalculoCreditoHelperSingleton calculoCreditoHelper;
	
	@EJB
	private ConsultaParametroServicioLocal consultaParametroServicio;
	
	@EJB
	private CreditoPQEmpSacServicioLocal creditoPqClientesEmpl;

	@EJB(name = "TransaccionRecaudacionDaoImpl/local")
	private ITransaccionRecaudacionDao transaccionRecaudacionDao;
	
	@EJB
	private GarantiaCesantiaDao cesantiaDao;
	
	@EJB(name = "ComprobantePagoServicioImpl/local")
	private ComprobantePagoServicio comprobantePagoServicio;

	private static final String NUMERO_MESES_PROMEDIO = "PQW_CON_NUMMESESPROM";
	private static final String APORTES_ACUMULADOS ="PQW_CON_APORTEACUMU";
	private static final String APORTES_CONSECUTIVOS ="PQW_CON_APORTECONSEC";
	private static final String NUMERO_NOVACIONES_PERMITIDAS_AFI ="PQW_CON_NUMNOVAPER";
	private static final String NUMERO_NOVACIONES_PERMITIDAS_JUB ="PQW_CON_NUMNOVAJUB";
	private static final String RESOURCE_MESSAGES ="messages";
	String paramsAfiliado=null;
	String paramsJubilado=null;

	private final LoggerBiess log = LoggerBiess.getLogger(PrecalificacionServicioUsaResumenImpl.class);
	
	@PostConstruct
	private void init() {
		this.calculoCreditoHelper = CalculoCreditoHelperSingleton.getInstancia();
	}
	
	
	
	private List<String> obtenerEstadosTramitePq() {
		final List<String> estadosCredito = new ArrayList<String>();
		estadosCredito.add("GEN");
		estadosCredito.add("ELT");
		// cambio 25/12/2011
		// cambio para pq-fraudes, se debe considerar 2 nuevos estados PDA
		// (pdendiente de aprobacion)
		// y APR (aprobado por el funcionario )
		// cambio 26/042012
		// Se aumenta los estados ERC,BLQ como en tramite
		estadosCredito.add("PDA");
		// INC-2272 Pensiones Alimenticias
		estadosCredito.add("PDC");
		//INC-2286 Ferrocarriles
		estadosCredito.add("PAP");
		estadosCredito.add("APR");
		estadosCredito.add("ERC");
		estadosCredito.add("BLQ");
		
		// INC-2350 Lista de Observados
		estadosCredito.add("PDV");
		return estadosCredito;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio#obtenerPrecalificacion(ec.fin.biess.creditos.pq.modelo.
	 * dto.ValidarRequisitosPrecalificacionBiess, java.math.BigDecimal,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista,
	 * ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado, boolean, boolean, boolean)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Precalificacion obtenerPrecalificacion(final ValidarRequisitosPrecalificacionBiess valReqPrecalificacion,
			final BigDecimal saldoValorNovacion, final TipoPrestamista rolPrestamista,
			final InformacionIessServicioDto informacionIessServicioDto, final boolean esPagoPensionesAlimenticias,
			final boolean productoBiessEmergente,final InformacionPQExigible infoPqExigile,InformacionGarantias infoGarantiaComp) throws PrecalificacionExcepcion, GarantiasSacException, PQExigibleException {

		final InformacionPrestacionPensionado informacionPrestacionPensionado = informacionIessServicioDto.getInformacionPrestacionPensionado();
		
		final Precalificacion precalificacion = new Precalificacion();
		precalificacion.setCalificado(true);
		
		precalificacion.setTipoPrecalificacionEnum(valReqPrecalificacion.getTipoPrecalificacionEnum());

		// INC-2135 Inicializamos la lista de requisitos
		precalificacion.setRequisitos(new ArrayList<Requisito>());

		Long inicio;
		Long fin;
		inicio = (new Date().getTime());
		final ResumenConsolidado resumenConsolidado = resumenConsolidadoServicio.getResumenByCedula(valReqPrecalificacion
				.getCedula());
		fin = (new Date().getTime());

		/**
		 * Ricardo Tituaña: INC - 5780 agregando nueva validacion
		 * 
		 */
		boolean esRealmenteSSC = false;
		final List<String> listaRt = servicioPrestadoDao
				.consultarAfiladoTrabajaEnEmpresaConRelacionTrabajoU(valReqPrecalificacion.getCedula());
		RT: for (final String relacionL : listaRt) {
			if ("01".equals(relacionL)) {
				esRealmenteSSC = true;
			} else if ("46".equals(relacionL)) {
				esRealmenteSSC = true;
			} else if ("47".equals(relacionL)) {
				esRealmenteSSC = true;
			} else if ("47".equals(relacionL)) {
				esRealmenteSSC = true;
			} else {
				esRealmenteSSC = false;
				break RT;
			}
		}
		if (resumenConsolidado != null) {
			if (esRealmenteSSC) {
				resumenConsolidado.setRelacionTrabajo("46");
			} else {
				resumenConsolidado.setRelacionTrabajo(null);
			}
		}

		log.debug("resumenConsolidado: " + (fin - inicio));
		if (resumenConsolidado == null) {
			throw new PrecalificacionExcepcion("NO SE HAN ENCONTRADO DATOS PARA LA PRECALIFICACION CON LA CEDULA: "
					+ valReqPrecalificacion.getCedula());
		}

		// Cuenta bancaria activa
		inicio = (new Date().getTime());
		/* INC-1800. Control PDA para jubilados. Se usa cta bancaria cargada al inicio de la App */
		/* Bandera que indica si el asegurado tiene una cuenta bancaria valida */
		final boolean tieneCuentaBancaria = valReqPrecalificacion.getSolicitante().getCuentaBancaria() != null;
		/* Fijar información cuenta bancaria para la precalificación */
		if (tieneCuentaBancaria) {
			final CuentaBancaria ctaBancaria = valReqPrecalificacion.getSolicitante().getCuentaBancaria();
			resumenConsolidado.setNombreIfi(ctaBancaria.getInstitucionFinanciera().getDescripcion());
			resumenConsolidado.setRucIfi(ctaBancaria.getInstitucionFinanciera().getRuc());
			resumenConsolidado.setTipoCuenta(ctaBancaria.getTipoCuenta().getCodigo());
			resumenConsolidado.setNumeroCuenta(ctaBancaria.getNumeroCuenta());
		}

		fin = new Date().getTime();

		log.debug("tieneCuentaBancaria: " + (fin - inicio));

		// Solo en el caso de pensionistas
		if (TipoPrestamista.JUBILADO.equals(valReqPrecalificacion.getTipoPrestamista())) {
			// CONSULTA SI ES PENSIONISTA
			inicio = new Date().getTime();

			/* INC-1731 2013-11-05 Se incluye el aumento 296 en los rubros para la concesión */
			// //prestacionesJubilado =
			// prestacionesBiessServicio.getListaPrestaciones(valReqPrecalificacion.getCedula(),
			// prestacionesRequeridas);

			fin = new Date().getTime();
			log.debug("getListaPrestaciones: " + (fin - inicio));

			// INC-2135 Pensiones Jubilados/Pensionistas: viene como parametro las prestaciones.
			final boolean esPensionista = (informacionPrestacionPensionado == null || informacionPrestacionPensionado
					.getListaPrestaciones() == null) ? false : informacionPrestacionPensionado.getListaPrestaciones()
					.size() > 0;

			resumenConsolidado.setEsPensionista(new Boolean(esPensionista));

			// Solo en caso de que tenga prestaciones
			// INC-2135 Pensiones Jubilados/Pensionistas: se utiliza las prestaciones, que vienen como parametro.
			if (esPensionista) {
				resumenConsolidado
						.setNombresPrestaciones(GeneradorListaPrestaciones
								.concatenarDescripcionPrestaciones((informacionPrestacionPensionado == null || informacionPrestacionPensionado
										.getListaPrestaciones() == null) ? null : informacionPrestacionPensionado
										.getListaPrestaciones()));
			}
		}

		// Para todos los casos
		resumenConsolidado.setTieneCuentaAutorizada(new Boolean(tieneCuentaBancaria));
		final boolean tienePrestamoHl = prestamoDao.tienePrestamoVigentesHl(valReqPrecalificacion.getCedula(),
				valReqPrecalificacion.getEstadoCreditoHl());

		// Consulta si tiene prestamos quirografarios vigentes
		if (tienePrestamoHl) {
			resumenConsolidado.setCreditoVigenteHl("1");
		} else {
			resumenConsolidado.setCreditoVigenteHl("0");
		}

		// Se elimina la validación de Hipotecarios
		// CB 10/12/2010
		/*
		 * boolean tienePrestamoHlPH = prestamoDao.tienePrestamoVigentesHlPH( valReqPrecalificacion.getCedula(),
		 * valReqPrecalificacion .getEstadoSolicitudHlPH());
		 * 
		 * // Consulta si tiene prestamos hipotecarios vigentes if (tienePrestamoHlPH) {
		 * resumenConsolidado.setTieneHipotecarioVigenteHl("1"); } else {
		 * resumenConsolidado.setTieneHipotecarioVigenteHl("0"); }
		 */		

		Long numeroImposicionesCon = new Long(0);
		Long numeroImposiciones = new Long(0);
		
		final InformacionAportacionResponseDto informacion = informacionIessServicioDto.getInformacionAportacionResponse();
		if (TipoPrestamista.AFILIADO.equals(valReqPrecalificacion.getTipoPrestamista())) {
			precalificacion.setSueldoPromedio(informacion.getSueldoPromedio());
			
			// Seteamos la informacion de las aportaciones consecutivas
			resumenConsolidado.setNunmeroImpoConsecutivas(new Long(informacion.getNumeroConsecutivas()));
			numeroImposicionesCon = resumenConsolidado.getNunmeroImpoConsecutivas();

			log.debug("***--- Imposiciones consecutivas: " + resumenConsolidado.getNunmeroImpoConsecutivas());
			// Seteamos la informacion de las aportaciones acumuladas
			resumenConsolidado.setNumeroImposiciones(new Long(informacion.getNumeroAcumuladas()));
			numeroImposiciones = new Long(informacion.getNumeroAcumuladas());
				
				
		} else if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(valReqPrecalificacion.getTipoPrestamista())) {
			// Consultamos número de aportes para zafreros
			try {
				this.consultarDatosImposicionesZafreros(resumenConsolidado);
				numeroImposiciones = resumenConsolidado.getNumeroImposiciones().longValue();
				numeroImposicionesCon = resumenConsolidado.getNunmeroImpoConsecutivas().longValue();
			} catch (final ImposicionException e) {
				log.error(e);
				throw new PrecalificacionExcepcion(
						"NO SE PUDIERON DETERMINAR LAS IMPOSICIONES PARA N AFILIADO ZAFRERO: ", e);
			}
		}
		// Consulta de los valores para la liquidación del préstamo
		// solamente para la novacion
		if (valReqPrecalificacion.isNovacion()) {
			final CalculoLiquidacion liquidacionPrestamo = new CalculoLiquidacion();
			liquidacionPrestamo.setValorPorCancelar(BigDecimal.valueOf(valReqPrecalificacion.getPrestamoVigenteNovacion().getValliqnov()));
			valReqPrecalificacion.setLiquidacionPrestamoVigente(liquidacionPrestamo);
			}

		final BigDecimal capitalEAM= transaccionRecaudacionDao.saldoCapitalTotal(valReqPrecalificacion.getCedula());
		infoPqExigile.setValorSaldoCreditos(infoPqExigile.getValorSaldosEmp().add(infoPqExigile.getValorSaldoCreditos()).subtract(capitalEAM));
		//Si es novacion debo quitar el saldo del credito a novar.
		if (valReqPrecalificacion.isNovacion()) {
			final CreditoExigibleDto credito=infoPqExigile.obtenerCreditoPorOperacion(valReqPrecalificacion.getPrestamoVigenteNovacion().getNumOperacionSAC());
			infoPqExigile.setValorSaldoCreditos(infoPqExigile.getValorSaldoCreditos().subtract(credito==null?BigDecimal.ZERO:credito.getSaldoOperacion()));
		}

		Garantia garantia = null;
		// Consultar el parámetro de monto máximo
		try {

			// valReqPrecalificacion.getGarantia().setValReqPrecalificacion(valReqPrecalificacion);
			consultarMontoMaximo(valReqPrecalificacion);

			// INC-2135 Pensiones Jubilados/Pensionistas - se pasa como parámetro el valor a comprometer.
			// Validacion de servicio de aportes. Se corrige validacion de PMD para evitar NullPointerException
			// No afecta la logica existente en la validacion.
			
			garantia = garantiaCreditoServicio.getGarantia(valReqPrecalificacion.getGarantia(), rolPrestamista,
					informacionIessServicioDto, informacion == null ? BigDecimal.ZERO : informacion.getSueldoPromedio(),infoPqExigile,infoGarantiaComp);

			resumenConsolidado.setTieneGarantiasAfiliadoVoluntario(garantia.getTotalGarantia().doubleValue() > 0);
			garantia.setNumImposiciones(numeroImposiciones);
			garantia.setNumImposicionesCon(numeroImposicionesCon);
			precalificacion.setGarantia(garantia);

		} catch (final GarantiaException e) {
			resumenConsolidado.setTieneGarantiasAfiliadoVoluntario(new Boolean(false));
			throw new PrecalificacionExcepcion("ERROR AL DETERMINAR LAS GARANTIAS: " + e.getMessage(), e);
		}

		if (TipoPrestamista.AFILIADO.equals(valReqPrecalificacion.getTipoPrestamista())
				|| TipoPrestamista.ZAFRERO_TEMPORAL.equals(valReqPrecalificacion.getTipoPrestamista())) {
			// Determina si se encuentra activo o cesante directamente a la
			// tabla
			// ksafitserpre. Solo para afiliados.
			try {
				this.determinarSiEsActivo(valReqPrecalificacion.getCedula(), resumenConsolidado);
			} catch (final ServicioPrestadoException e) {
				throw new PrecalificacionExcepcion("ERROR AL DETERMINAR SI EL SOLICITATES ES ACTIVO O CESANTE", e);
			}
		}

		if (valReqPrecalificacion.isNovacion()) {
		resumenConsolidado.setDividendosPlanilla(transaccionRecaudacionDao.obtenerTransaccionEpl(BigDecimal.valueOf(valReqPrecalificacion.getPrestamoVigenteNovacion().getNut()), valReqPrecalificacion.getCedula()));
		}


		// Se vuelven a aumentar validaciones de solicitud y gastos de
		// hipotecarios
		// CB 09/06/2011

		boolean tieneSolicitudPHEnTramite;
		try {
			tieneSolicitudPHEnTramite = this.validarPHEnTramiteServicio
					.tienePhEnTramite(valReqPrecalificacion.getCedula());

			log.info("<<<<<<<<<<<Tiene solicitud" + tieneSolicitudPHEnTramite);

		} catch (final PHEnTramiteException e3) {
			log.error("Se presento un error al consumir tiene PH en tramite");
			throw new PrecalificacionExcepcion(e3.getMessage(), e3);
		}

		resumenConsolidado.setTieneSolicitudPHEnTramite(tieneSolicitudPHEnTramite);

		final boolean tieneGastosPendientes = solicitudCreditoServicio
				.consultarSolicitudGastosAdministrativosPorCedulaTipoSolicitud(valReqPrecalificacion.getCedula(),
						valReqPrecalificacion.getCodTipSolSerList());
		resumenConsolidado.setTieneGastosPendientes(tieneGastosPendientes);

		// SAC: Novacion R7.1 Tiene un comprobante pendiente de pago
		final List<EstadoComprobantePago> estadosComprobante = new ArrayList<EstadoComprobantePago>();
		estadosComprobante.add(EstadoComprobantePago.GEN);
		estadosComprobante.add(EstadoComprobantePago.DEP);
		final List<ComprobantePago> lstCompPago=comprobantePagoServicio.obtenerComprobantePendPago(valReqPrecalificacion.getCedula(), estadosComprobante);
		precalificacion
				.setCumpleComprobante(BigDecimal.valueOf(lstCompPago.size()));

		// Validación de Pq en trámite
		
		// fin cambio 25/12/2011
		final BigDecimal tienePqTramite = prestamoDao.consultarTienePqPorEstado(valReqPrecalificacion.getCedula(),
				obtenerEstadosTramitePq());
		
		//Se agrega validacion para proceso concesion con GAF
		final BigDecimal tienePqTramiteSAC=prestamoDao.consultarTienePqSolCreTramite(valReqPrecalificacion.getCedula());
		
		precalificacion.setCumplePqTramite(tienePqTramite.add(tienePqTramiteSAC));


		// Validacion de enfermo terminal
		if ("1".equals(resumenConsolidado.getTieneEnfermedadTerminal())) {
			valReqPrecalificacion.getSolicitante().setEsEnfermoTerminal(true);
		} else {
			valReqPrecalificacion.getSolicitante().setEsEnfermoTerminal(false);
		}
		precalificacion.setValidarRequisitosPrecalificacion(valReqPrecalificacion);
		BigDecimal sumaSaldos = infoPqExigile.getValorSaldoCreditos();
		precalificacion.setSumaSaldosCred(sumaSaldos);
		sumaSaldos = sumaSaldos.subtract(saldoValorNovacion);
		final boolean tieneMora = infoPqExigile.isTieneMora();

		// Si tiene mora se setea el valor a 1, caso contrario en cero, lo
		// cual sera validado en el metodo
		// tieneCreditosEnMora de la clase ReglasGeneral.java
		resumenConsolidado.setCreditosHistoriaLaboral(tieneMora ? "1" : "0");
		/* Obtener el NUMSBU Y SBU */
		BigDecimal sbu = BigDecimal.ZERO;
		BigDecimal numsbu = BigDecimal.ZERO;
		try {
			sbu = parametroCreditoServicio.obtenerValorSBU();
			numsbu = parametroCreditoServicio.obtenerValorNUMSBU();
		} catch (final ParametroCreditoException e) {
			throw new PrecalificacionExcepcion(e.getMessage(), e);
		}

		/* Consular si el afiliado tiene creditos en estado cancelado por fallecimiento CFA */
		boolean tieneCFA = true;
		try {
			final List<String> estadosCFA = new ArrayList<String>();
			estadosCFA.add("CFA");
			tieneCFA = prestamoDao.consultarTienePqPorEstado(valReqPrecalificacion.getCedula(), estadosCFA).compareTo(
					BigDecimal.ZERO) > 0 ? true : false;
		} catch (final Exception e) {
			log.error("Error al consultar cr\u00E9ditos en estato CFA. " + e.getMessage(), e);
			throw new PrecalificacionExcepcion("Error al consultar cr\u00E9ditos en estato CFA. " + e.getMessage(), e);
		}

		/* Fijar parametros reglas precalificacion */
		final ParamsReglasPrecalificacion params = new ParamsReglasPrecalificacion();
		params.setSumaSaldos(sumaSaldos);
		params.setSbu(sbu);
		params.setNumsbu(numsbu);
		params.setTieneCFA(tieneCFA);
		params.setDiscapacitado(valReqPrecalificacion.getDiscapacitado().booleanValue());
		
		//Agrego consulta de cesantias validar credito subgarantizado PRY_CARTERA
		final GarantiaCesantia cuentaCesantia = cesantiaDao.load(valReqPrecalificacion.getCedula());
		params.setGarantiaCesantia(cuentaCesantia);
		try {
			params.setNumeroAportacionesAcumuladasRequeridas(this.consultaParametroServicio.consultarParametroEntero(APORTES_ACUMULADOS));
			params.setNumeroAportacionesConsecutivasRequeridas(this.consultaParametroServicio.consultarParametroEntero(APORTES_CONSECUTIVOS));
		} catch (final ConsultaParametroException e1) {
			log.error("Se presento un error al consultar el parametro de aportes", e1);
			throw new PrecalificacionExcepcion("Error al obtener parametros de n\u00FAmero de aportes acumulados", e1);
		}
		//Seteamos la mora patronal
		if (!TipoPrestamista.JUBILADO.equals(valReqPrecalificacion.getTipoPrestamista())) {
			params.setTieneMoraPatronal(informacion.isTieneMora());
		}
		
		// Validacion para mora a personas del sector público
		final boolean esBeneficiarioPublico = solicitudCreditoServicio.esBeneficiarioEmpresaPublica(valReqPrecalificacion.getCedula());
		
		if (esBeneficiarioPublico) {
			// Verifica si tiene mora individual
			final boolean tieneComprobanteMora = verificarMoraIndividualBiessFechaPorCedula(valReqPrecalificacion.getCedula());
			boolean tienePlanillasPQMora = false;
			if (!tieneComprobanteMora && !TipoPrestamista.JUBILADO.equals(valReqPrecalificacion.getTipoPrestamista())) {
				// Verifica si tiene mora de planillas de PQ
				tienePlanillasPQMora = solicitudCreditoServicio.contarPlanillaMoraSectorPublico(valReqPrecalificacion.getCedula()).compareTo(BigDecimal.ZERO) > 0;
			}
			// Parametro para validar el requisito NO TENER CREDITO EN MORA CON EL IESS O BIESS
			params.setParamMoraComprobantePublicos(tieneComprobanteMora || tienePlanillasPQMora);
			params.setValidaPublicos(esBeneficiarioPublico);
		}
		
		// INC-2135 Pensiones Jubilados/Pensionistas
		if (precalificacion.getValidarRequisitosPrecalificacion().getSolicitante()
				.getTipo() != null && TipoPrestamista.JUBILADO.compareTo(precalificacion.getValidarRequisitosPrecalificacion().getSolicitante()
				.getTipo()) == 0) {
			final Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			log.info("cal: " + cal);
			// INC-2135 Pensiones Jubilados/Pensionistas: se setea el valor de prestaciones a cero, pendiente
			// veirifacion de rubros a tomar en cuenta.
			params.setValorRetencionesJudiciales(BigDecimal.ZERO);
			// Long anio = Long.valueOf(cal.get(Calendar.YEAR));
			// Long mes = Long.valueOf(cal.get(Calendar.MONTH)+1);
			// params.setValorRetencionesJudiciales(rolPeriodoDetalleServicioLocal.sumarValorPorIdentificacionAnioMesRubro(precalificacion.getValidarRequisitosPrecalificacion().getCedula(),
			// anio, mes, 11L));
			log.info("params.getValorRetencionesJudiciales(): " + params.getValorRetencionesJudiciales());
			params.setInformacionPrestacionPensionado(informacionPrestacionPensionado);
		}

		// Ejecución de las reglas del negocio
		try {
			// ValidadorPrecalificacion.ejecutarReglasJava(precalificacion,
			// resumenConsolidado, sumaSaldos, sbu, numsbu, tieneCFA,
			// valReqPrecalificacion.getDiscapacitado().booleanValue());
			ValidadorPrecalificacion.ejecutarReglasJava(precalificacion, resumenConsolidado, params);
		} catch (final PrecalificacionExcepcion e) {
			throw new PrecalificacionExcepcion("ERROR AL DETERMINAR LAS REGLAS DE CALIFICACION", e);
		}

		return precalificacion;

	}
	
	
	/**
	 * Verifica si tiene mora individual
	 * 
	 * @param cedula
	 * @return
	 * @throws PrecalificacionExcepcion
	 */
	private boolean verificarMoraIndividualBiessFechaPorCedula(final String cedula) throws PrecalificacionExcepcion {
		BigDecimal cuentaMoraComprobantes = BigDecimal.ZERO;
		cuentaMoraComprobantes = dividendoDao.contarDividendosMoraBiessPorFecha(cedula);
		
		return cuentaMoraComprobantes.intValue() > 0;
	}



	@SuppressWarnings("unused")
	private List<DetalleBuroCredito> poblarDetalles(final List<BuroCreditoDetalle> detalles) {

		final List<DetalleBuroCredito> detallesBC = new ArrayList<DetalleBuroCredito>();

		if (detalles == null)
			return new ArrayList<DetalleBuroCredito>();

		if (detalles.size() == 0)
			return new ArrayList<DetalleBuroCredito>();

		for (final BuroCreditoDetalle buroCreditoDetalle : detalles) {
			final DetalleBuroCredito detalleBuroCredito = new DetalleBuroCredito();
			detalleBuroCredito.setInstitucion(buroCreditoDetalle.getFinancieras().getNomins());
			detalleBuroCredito.setCalificacion(buroCreditoDetalle.getTipcalbur());
			detalleBuroCredito.setObservaciones(buroCreditoDetalle.getDescalbur());
			detalleBuroCredito.setNombreContacto(buroCreditoDetalle.getFinancieras().getNompercon());
			detalleBuroCredito.setTelefonoContacto(buroCreditoDetalle.getFinancieras().getTelins());
			detalleBuroCredito.setEmailContacto(buroCreditoDetalle.getFinancieras().getMailins());
			detalleBuroCredito.setDireccionIFI(buroCreditoDetalle.getFinancieras().getDirins());
			detalleBuroCredito.setValorDeuda(buroCreditoDetalle.getValdeu());
			log.info("Deuda buro de credito: " + detalleBuroCredito.getValorDeuda());
			detalleBuroCredito.setFecha(buroCreditoDetalle.getBuroCredito().getBuroCreditoBitacora().getFecvigini());
			detallesBC.add(detalleBuroCredito);
		}
		return detallesBC;
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	private void determinarSiEsActivo(final String cedula, final ResumenConsolidado resumenConsolidado)
			throws ServicioPrestadoException {
		final boolean esActivo = servicioPrestado.consultarEsActivoPorCedula(cedula);
		if (esActivo)
			resumenConsolidado.setEstadoActivoHl("ACT");
		else
			resumenConsolidado.setEstadoActivoHl("CES");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio#consultarDatosImposicionesZafreros(ec.gov.iess.
	 * consolidado.modelo.ResumenConsolidado)
	 */
	@Override
	public void consultarDatosImposicionesZafreros(final ResumenConsolidado resumenConsolidado) throws ImposicionException {
		/*
		 * Consultamos las últimas imposiciones consecutivas de zafreros a partir del último semestre del año anterior
		 */
		List<Imposicion> imposionesUltimoSemestreAnioAnterior = new ArrayList<Imposicion>();
		final Calendar hoy = new GregorianCalendar();
		final Long anioAnterior = (long) (hoy.get(Calendar.YEAR) - 1);
		final List<Long> mesList = new ArrayList<Long>();
		mesList.add(7L);
		mesList.add(8L);
		mesList.add(9L);
		mesList.add(10L);
		mesList.add(11L);
		mesList.add(12L);
		imposionesUltimoSemestreAnioAnterior = imposicionServicio.consultarPorCedulaAnioListaMes(
				resumenConsolidado.getCedula(), anioAnterior, mesList);
		resumenConsolidado.setNunmeroImpoConsecutivas(Long.valueOf(imposionesUltimoSemestreAnioAnterior.size()));
		log.info("Imposiciones consecutivas Zafrero: " + resumenConsolidado.getNunmeroImpoConsecutivas());

		/*
		 * Consultamos todas las imposiciones de un zafrero a partir de diciembre del año anterior a la solicitud del
		 * préstamo
		 */

		final List<Imposicion> todasLasImposiciones = imposicionServicio.consultarImposicionPorCedula(resumenConsolidado
				.getCedula());

		Long totalImposicionesZafrero = Long.valueOf(0);
		for (final Imposicion imposicion : todasLasImposiciones) {
			if (imposicion.getImposicionPk().getAniper().longValue() <= anioAnterior) {
				totalImposicionesZafrero += 1;
			}
		}
		resumenConsolidado.setNumeroImposiciones(totalImposicionesZafrero);
		log.info("Total imposiciones zafrero: " + resumenConsolidado.getNumeroImposiciones());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio#consultarMontoMaximo(ec.gov.iess.creditos.modelo.dto.
	 * ValidarRequisitosPrecalificacion)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void consultarMontoMaximo(final ValidarRequisitosPrecalificacion valReqPrecalificacion) throws GarantiaException {
		// Consulta el parámetro del monto máximo del crédito
		if (valReqPrecalificacion.getDatosOrdenCompra() != null) {
			//INC-2272 Pensiones Alimenticias
			final int codigoTipoPrestamo = TiposProductosPq
					.valueOf(valReqPrecalificacion.getDatosOrdenCompra().getCodigoProducto()).getCodigoTipoProducto()
					.intValue();
			valReqPrecalificacion.getGarantia().setParametroMontoMaximo(
					prestamoDao.montoMaximoCredito(codigoTipoPrestamo, 20));

			/*
			 * if (valReqPrecalificacion.getDatosOrdenCompra().getCodigoProducto() == TiposProductosPq.NOR .toString())
			 * { valReqPrecalificacion.getGarantia().setParametroMontoMaximo( prestamoDao.montoMaximoCredito(1, 20)); }
			 * else if (valReqPrecalificacion.getDatosOrdenCompra() .getCodigoProducto() ==
			 * TiposProductosPq.COM.toString()) { valReqPrecalificacion.getGarantia().setParametroMontoMaximo(
			 * prestamoDao.montoMaximoCredito(5, 20)); } else if (valReqPrecalificacion.getDatosOrdenCompra()
			 * .getCodigoProducto() == TiposProductosPq.TREN.toString()) {
			 * valReqPrecalificacion.getGarantia().setParametroMontoMaximo( prestamoDao.montoMaximoCredito(6, 20)); }
			 * else {
			 * 
			 * throw new GarantiaException( "ERROR EN PARAMETRIZACION DEL CODIGO DE PRODUCTO"); }
			 */
		} else {
			valReqPrecalificacion.getGarantia().setParametroMontoMaximo(prestamoDao.montoMaximoCredito(1, 20));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio#obtenerValorFondoReserva(java.lang.String)
	 */
	@Override
	public BigDecimal obtenerValorFondoReserva(final String cedula) throws PrecalificacionExcepcion {
		BigDecimal fondoReservaDisponible = BigDecimal.ZERO;
		fondoReservaDisponible = this.cuentaFondoReservaDao.obtenerDisponibleFondosReserva(cedula);

		if (fondoReservaDisponible == null || fondoReservaDisponible.compareTo(BigDecimal.ZERO) < 0) {
			fondoReservaDisponible = BigDecimal.ZERO;
		}

		return fondoReservaDisponible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio#obtieneInformacionPensiones(java.lang.String)
	 */
	@Override
	public InformacionPrestacionPensionado obtieneInformacionPensiones(final String cedula) throws PrecalificacionExcepcion {
		InformacionPrestacionPensionado informacionPrestacion = null;
		try {
			informacionPrestacion = this.consultaPrestaciones.consultarPrestaciones(cedula, java.net.InetAddress.getLocalHost().getHostAddress());
		} catch (final UnknownHostException e) {
			log.error("Error al obtener la IP del servidor en simulador", e);
			throw new PrecalificacionExcepcion(e);
		} catch (final PrestacionPensionadosException e) {
			log.error("Error al consumir el servicio de prestaciones en simulador", e);
			throw new PrecalificacionExcepcion(e);
		}
		return informacionPrestacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio#obtenerRequisitos(boolean, boolean, boolean,
	 * java.lang.String, ec.gov.iess.creditos.enumeracion.TipoPrestamista, ec.gov.iess.creditos.modelo.dto.Solicitante,
	 * ec.gov.iess.creditos.enumeracion.TipoPrecalificacionEnum, ec.gov.iess.creditos.enumeracion.TiposProductosPq,
	 * java.util.Date, ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado, boolean,
	 * ec.gov.iess.creditos.modelo.persistencia.Prestamo)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Precalificacion obtenerRequisitos(final boolean esEmergente, final boolean esNovacion,
			final boolean esPagoPensionesAlimenticias, final String cedula, final TipoPrestamista tipoPrestamista,
			final Solicitante solicitante, final TipoPrecalificacionEnum tipoPrecalificacionEnum, final TiposProductosPq tiposProductosPq,
			final Date fechaNacimiento, final InformacionIessServicioDto informacionIessServicioDto, final boolean esDiscapacitado,
			final Prestamo prestamoNovar,InformacionPQExigible infoPqExigile,InformacionGarantias infoGarantiaComp) throws PrecalificacionExcepcion, GarantiasSacException, PQExigibleException {
		Precalificacion precalificacion = new Precalificacion();

		try {
			this.solicitanteServicio.determinarEmpleador(cedula, tipoPrestamista, solicitante, esEmergente);
		} catch (final SucursalException e) {
			throw new PrecalificacionExcepcion(e);
		} catch (final ServicioPrestadoException e) {
			throw new PrecalificacionExcepcion(e);
		} catch (final NoServicioPrestadoSucursalException e) {
			log.info("No tiene un empleador y sucursal: " + cedula);
		}
		
		final ValidarRequisitosFondos validarFondos = new ValidarRequisitosFondos();
		validarFondos.setCedula(cedula);
		validarFondos.setTiposCargos(PrecalificacionUtil.obtenerTiposCargos());
		validarFondos.setEstadoBloqueado(EstadosCredito.ESTADO_BLOQUEO_CUENTA);
		validarFondos.setEstadosSolicitud(PrecalificacionUtil.obtenerEstadosSolicitud());
		validarFondos.setNovacion(esNovacion);
		
		final DatosGarantia datosGarantia = new DatosGarantia();
		datosGarantia.setCedula(cedula);
		datosGarantia.setFechaSolicitud(new Date());
		datosGarantia.setTipoPrestamista(tipoPrestamista);
		datosGarantia.setValReqFondos(validarFondos);
		datosGarantia.setCuotaMensualBuro(BigDecimal.ZERO);
		datosGarantia.setPrestamoVigNovacion(prestamoNovar);
		
		final ValidarRequisitosPrecalificacionBiess reqPrecalificacion = new ValidarRequisitosPrecalificacionBiess();
		reqPrecalificacion.setCedula(cedula);
		reqPrecalificacion.setTipoPrestamista(tipoPrestamista);
		reqPrecalificacion.setEstadoCreditoCuentaBancaria(PrecalificacionUtil.obtenerEstadosCuentaBancaria());
		reqPrecalificacion.setEstadoCreditoHl(PrecalificacionUtil.obtenerEstadosHl());
		reqPrecalificacion.setEstadoSolicitudHlPH(PrecalificacionUtil.obtenerEstadosHlPH());
		reqPrecalificacion.setCodEstSolSerList(PrecalificacionUtil.obtenerEstadosSolicitudPH());
		reqPrecalificacion.setCodTipSolSerList(PrecalificacionUtil.obtenerTiposSolicitudPH());
		reqPrecalificacion.setSolicitante(solicitante);
		reqPrecalificacion.setTipoPrecalificacionEnum(TipoPrecalificacionEnum.SIMULACION);
		reqPrecalificacion.setPrestamoVigenteNovacion(prestamoNovar);
		final DatosOrdenCompra datosOrdenCompra = new DatosOrdenCompra();
		datosOrdenCompra.setCodigoProducto(tiposProductosPq.toString());
		datosOrdenCompra.setDescripcionProducto(tiposProductosPq.getDescripcion());
		reqPrecalificacion.setDatosOrdenCompra(datosOrdenCompra);
		reqPrecalificacion.setDiscapacitado(esDiscapacitado);
		reqPrecalificacion.setNovacion(esNovacion);
		
		datosGarantia.setIndicaCreditoEmergente(false);
		if (esEmergente) {
			datosGarantia.setIndicaCreditoEmergente(true);
		}

		datosGarantia.setFechaNacimiento(fechaNacimiento);
		datosGarantia.setPlazo(BigDecimal.ZERO);

		reqPrecalificacion.setGarantia(datosGarantia);

		BigDecimal saldoValorNovacion = BigDecimal.ZERO;
		if (esNovacion) {
			saldoValorNovacion = this.calcularMontoMaximo(prestamoNovar);
		}
		
		precalificacion = this.obtenerPrecalificacion(reqPrecalificacion, saldoValorNovacion,
				tipoPrestamista, informacionIessServicioDto, esPagoPensionesAlimenticias,
				esEmergente,infoPqExigile,infoGarantiaComp);
		
		return precalificacion;
	}
	
	private BigDecimal calcularMontoMaximo(final Prestamo prestamoNovar) {
		Double totalPorCancelar = 0.0;
		if (prestamoNovar.getDividendosPrestamo() != null) {
			for (final DividendoPrestamo dp : prestamoNovar
					.getDividendosPrestamo()) {
				if (dp.getEstadoDividendoPrestamo().getCodestdiv()
						.equals("REG")) {
					totalPorCancelar += dp.getValcapamo();
				}
			}
		}
		final BigDecimal saldoPrestamo = new BigDecimal(totalPorCancelar);
		return saldoPrestamo;
	}

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio#obtenerInformacionAportaciones(java.lang.String, boolean)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public InformacionAportacionResponseDto obtenerInformacionAportaciones(final String cedula, final boolean esEmergente) throws PrecalificacionExcepcion {
		InformacionAportacionResponseDto informacion = null;
		
		TipoCalculoEnum tipoCalculoAportaciones = TipoCalculoEnum.NORMAL;
		
		
		
		try {
			informacion = aportacionesServicio.obtenerInformacionAportaciones(cedula, tipoCalculoAportaciones.value(),
					this.consultaParametroServicio.consultarParametroEntero(NUMERO_MESES_PROMEDIO));
		} catch (final AportacionesException e) {
			throw new PrecalificacionExcepcion(e);
		} catch (final ConsultaParametroException e) {
			throw new PrecalificacionExcepcion(e);
		}
		
		return informacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio#obtieneRequisitosBloqueantesSimulador(java.util.List,
	 * java.util.Date, ec.gov.iess.creditos.enumeracion.TipoPrestamista, boolean)
	 */
	@Override
	public List<Requisito> obtieneRequisitosBloqueantesSimulador(final List<Requisito> requisitos, final Date fechaNacimiento, final TipoPrestamista tipoPrestamista,
			final boolean esNovacion) {
		final List<Requisito> requisitosBloqueantes = new ArrayList<Requisito>();
		if (esNovacion) {
			for (final Requisito requisito : requisitos) {
				if (TipoRequisitoSimulacionEnum.BLOQUEANTE == requisito.getTipoRequisitoSimulacion()) {
					if (!requisito.isAprobado()) {
						requisitosBloqueantes.add(requisito);
						continue;
					}
				}

			}

			if (TipoPrestamista.JUBILADO == tipoPrestamista) {
				final int edadAsegurado = calculoCreditoHelper.determinarEdad(fechaNacimiento, new Date()).intValue();
				final Requisito requisito = this.devuelveRequisitoEdadJubilado(tipoPrestamista, edadAsegurado);
				if (requisito != null && !requisito.isAprobado()) {
					requisitosBloqueantes.add(requisito);
				}
			}
		} else {
			for (final Requisito requisito : requisitos) {
				if (TipoRequisitoSimulacionEnum.BLOQUEANTE == requisito.getTipoRequisitoSimulacion()) {
					if (!requisito.isAprobado()) {
						requisitosBloqueantes.add(requisito);
						continue;
					}
				}

			}
		}

		return requisitosBloqueantes;
	}
	
	/**
	 * Devuelve el requisito de validacion de edad maxima para jubilados
	 * 
	 * @param tipoPrestamista
	 * @param edadAsegurado
	 * @return
	 */
	private Requisito devuelveRequisitoEdadJubilado(final TipoPrestamista tipoPrestamista, final int edadAsegurado) {
		Requisito requisito = null;
		if (TipoPrestamista.JUBILADO == tipoPrestamista) {
			requisito = ReglasJubilado.edadMinimaNovacionSimulacion(edadAsegurado, this.obtenerEdadPermiteNovaciones());
		}

		return requisito;
	}

	/**
	 * Obtiene la edad hasta la cual permite novaciones
	 * 
	 * @return
	 */
	private int obtenerEdadPermiteNovaciones() {
		int edadPermiteNovaciones = 0;
		try {
			paramsJubilado= (String)this.consultaParametroServicio.consultarParametro(NUMERO_NOVACIONES_PERMITIDAS_JUB,"string");
			log.info("PARAMETRO JUBILADO OBTENIDO: "+paramsJubilado);
			if (paramsJubilado != null) {
				final String[] parametrosNovacionesPermitidasJub = paramsJubilado.split(";");
				edadPermiteNovaciones =  Integer.parseInt(parametrosNovacionesPermitidasJub[2]);
			}
		} catch(final ConsultaParametroException e){
			log.error("Erro al consultar el parametro numero novaciones permitidas afiliado/jubilado");
		}

		return edadPermiteNovaciones;
	}
	
	

}