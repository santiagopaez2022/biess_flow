/**
 * 
 */

package ec.gov.iess.creditos.pq.servicio.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.conozcasucliente.sbs.exception.SbsCatalogoExcepcion;
import ec.fin.biess.creditos.pq.dao.ListaBlancaDao;
import ec.fin.biess.creditos.pq.dao.ParametroPQDao;
import ec.fin.biess.creditos.pq.dao.PrestamoBiessDao;
import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoProductoEnum;
import ec.fin.biess.creditos.pq.excepcion.BeneficiarioCreditoExcepcion;
import ec.fin.biess.creditos.pq.excepcion.ConsultaParametroException;
import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.modelo.persistencia.ParametroPQ;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiess;
import ec.fin.biess.creditos.pq.servicio.ParametroCreditoServicio;
import ec.fin.biess.creditos.pq.servicio.ProveedorServicio;
import ec.fin.biess.dao.NutTataBiessDao;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.dao.QueryGenericoBiessDao;
import ec.fin.biess.enumeraciones.AplicativoEnum;
import ec.fin.biess.exception.ParametrizacionPQException;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.modelo.persistencia.ParametrizacionPQ;
import ec.fin.biess.service.ParametrizacionPQServicioLocal;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.dao.CreditoConsolidadoDao;
import ec.gov.iess.creditos.dao.CreditoQuirografarioHostDao;
import ec.gov.iess.creditos.dao.DividendoPrestamoDao;
import ec.gov.iess.creditos.dao.HistoricoAniosPlazoCapitalEndeudamientoDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.dao.PrestamoInformacionDao;
import ec.gov.iess.creditos.dao.PrestamoInformacionDetalleDao;
import ec.gov.iess.creditos.dao.PrestamoResumenHistoricoDao;
import ec.gov.iess.creditos.dao.QueryGenericoDao;
import ec.gov.iess.creditos.dao.SolicitudCreditoDao;
import ec.gov.iess.creditos.dao.TipoSolicitudDao;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.SituacionPrestamo;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.excepcion.ListaVaciaException;
import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.excepcion.PrestamoPqCoreException;
import ec.gov.iess.creditos.excepcion.QueryGenericoException;
import ec.gov.iess.creditos.excepcion.ReversaPrestamoExcepcion;
import ec.gov.iess.creditos.modelo.dto.CuentaBancariaAnterior;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DatosSituacionPrestamo;
import ec.gov.iess.creditos.modelo.dto.SolicitudPda;
import ec.gov.iess.creditos.modelo.dto.SucursalDto;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosPrecalificacion;
import ec.gov.iess.creditos.modelo.persistencia.CreditoConsolidado;
import ec.gov.iess.creditos.modelo.persistencia.CreditoQuirografarioHost;
import ec.gov.iess.creditos.modelo.persistencia.CreditoValidacion;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCredito;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.EstadoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.creditos.modelo.persistencia.HistoricoAniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoInformacion;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoInformacionDetalle;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoResumenHistorico;
import ec.gov.iess.creditos.modelo.persistencia.SaldoLiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.SolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.DatoCaptacionDto;
import ec.gov.iess.creditos.pq.dto.DatoDemograficoDto;
import ec.gov.iess.creditos.pq.dto.DatoDesembolsoDto;
import ec.gov.iess.creditos.pq.dto.EvaluaReglaMontoMinimoDto;
import ec.gov.iess.creditos.pq.dto.InformacionPQOperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.PrestamoRequestDto;
import ec.gov.iess.creditos.pq.excepcion.ActualizarPrestamoEstadoHistoricoException;
import ec.gov.iess.creditos.pq.excepcion.BloqueoFinSemanaException;
import ec.gov.iess.creditos.pq.excepcion.CabeceraCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.CreacionOperacionSacException;
import ec.gov.iess.creditos.pq.excepcion.CrearCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.DiasNoPermitidosNovacionException;
import ec.gov.iess.creditos.pq.excepcion.MontoMinimoException;
import ec.gov.iess.creditos.pq.excepcion.NovarCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.PrestamoException;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaBloqueadaException;
import ec.gov.iess.creditos.pq.excepcion.SituacionPrestamoNoExisteException;
import ec.gov.iess.creditos.pq.helper.reglas.Messages;
import ec.gov.iess.creditos.pq.servicio.ConsultaParametroServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoPQCrearOperacionSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoValidacionServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CuentaBancariaAprobadaServicioLocal;
import ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.PeriodoComprobanteServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoEstadoHistoricoServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoQuirografarioServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicioRemote;
import ec.gov.iess.creditos.pq.servicio.SaldoLiquidacionPrestamoServicio;
import ec.gov.iess.creditos.pq.util.CompararDatosCredito;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.EstadosCredito;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;
import ec.gov.iess.creditos.pq.util.MailSend;
import ec.gov.iess.creditos.pq.util.TiposCredito;
import ec.gov.iess.creditos.pq.util.Utilities;
import ec.gov.iess.creditos.sp.ReversarPrestamoJdbc;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancariaAfiliado;
import ec.gov.iess.cuentabancaria.servicio.CuentaBancariaAfiliadoServicio;
import ec.gov.iess.hl.exception.DivisionPoliticaException;
import ec.gov.iess.hl.exception.NoTieneRelacionDeDependenciaException;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.hl.exception.SucursalException;
import ec.gov.iess.hl.modelo.DivisionPolitica;
import ec.gov.iess.hl.modelo.Sucursal;
import ec.gov.iess.hl.servicio.DivisionPoliticaServicio;
import ec.gov.iess.hl.servicio.ServicioPrestadoServicio;
import ec.gov.iess.hl.servicio.SucursalServicio;


/**
 * @author cvillarreal ,pjarrin
 * 
 */
@Stateless
public class PrestamoServicioImpl implements PrestamoServicio,
		PrestamoServicioRemote {

	private static final String PERIODO_NOVA_AFI = "NAF";

	private static final String PERIODO_NOVA_JUB = "NJU";
	
	private static final String PERIODO_NOVA_AFI_JUB = "NAJ";

	private static final LoggerBiess LOG =  LoggerBiess.getLogger(PrestamoServicioImpl.class);

	private static Long pkCreditoConsolidadoTotal = 1L;
	private static Long pkCreditoConsolidadoDiario = 2L;
	private static final String PORCENTAJE_NOVACION_CANCELADA ="PQW_CON_PORNOVAPAG";

	@Resource
	SessionContext sessionctx;

	@EJB
	private PrestamoQuirografarioServicio prestamoQuirografarioServicio;

	@EJB
	private PrestamoDao prestamoDao;

	@EJB
	private QueryGenericoDao queryGenericoDao;
	
	@EJB
	private QueryGenericoBiessDao queryGenericoBiessDao;

	@EJB
	private CreditoConsolidadoDao creditoConsolidadoDao;

	@EJB
	private ServicioPrestadoServicio servicioPrestadoServicio;

	@EJB
	private DividendoPrestamoServicio dividendoPrestamoServicio;

	@EJB
	private SaldoLiquidacionPrestamoServicio saldoLiquidacionPrestamoServicio;

	@EJB
	private DividendoPrestamoDao dividendoPrestamoDao;

	@EJB
	private CreditoQuirografarioHostDao creditoQuirografarioHostDao;

	@EJB
	private HistoricoAniosPlazoCapitalEndeudamientoDao historicoAniosPlazoCapitalEndeudamientoDao;


	@EJB
	private PrestamoResumenHistoricoDao prestamoResumenHistoricoDao;

	@EJB(name = "PrestamoEstadoHistoricoServicioImpl/local")
	private PrestamoEstadoHistoricoServicio prestadohistorico;

	@EJB
	private ReversarPrestamoJdbc reversaprestamo;

	@EJB
	private CuentaBancariaAfiliadoServicio cuentabancariaafiliadoservicio;

	@EJB
	private SucursalServicio sucursalservicio;

	@EJB
	private DivisionPoliticaServicio divisionpoliticaservicio;

	@EJB
	private PrestamoBiessDao prestamoBiessDao;

	@EJB
	private CuentaBancariaAprobadaServicioLocal ctabancariaaprservicio;

	@EJB
	private ListaBlancaDao listaBlancaDao;

	//INC-2272 Pensiones Alimenticias
	@EJB
	private SolicitudCreditoDao solicitudCreditoDao;
	
	//INC-2286 Ferrocarriles
	@EJB
	private PrestamoInformacionDao prestamoInformacionDao;
	
	@EJB
	private PrestamoInformacionDetalleDao prestamoInformacionDetalleDao;
	
	@EJB
	private CreditoValidacionServicioLocal creditoValidacionServicio;
	
	@EJB
	private DividendoPrestamoDao dividendoDao;
	
	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	
	@EJB
	private ParametrizacionPQServicioLocal parametrizacionPQServicio;
	
	@EJB
	private ParametroCreditoServicio parametroCreditoServicio;
	
	@EJB
	private ParametroPQDao parametroPQDao;
	
	@EJB
	private ConsultaParametroServicioLocal consultaParametroServicio;
	
	@EJB
	TipoSolicitudDao tipoSolicitudDao;

	@EJB(name = "ProveedorServicioImpl/local")
	private  ProveedorServicio proveedorServicio;


	@EJB
	private CreditoPQCrearOperacionSacServicioLocal creditoPQCrearOperacionSacServicio;


	
	@EJB(name = "PeriodoComprobanteServicioImpl/local")
	private PeriodoComprobanteServicio periodoComprobanteServicio;

	@EJB(name = "NutTataBiessDaoImpl/local")
	private NutTataBiessDao nutTataBiessDao;

	/**
	 * 
	 */
	public PrestamoServicioImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#crearCreditoQuirografario(ec.gov.iess.creditos.modelo.dto.
	 * DatosCredito)
	 */
	@Override
	public void crearCreditoQuirografario(final DatosCredito credito)
			throws CrearCreditoQuirografarioException,
			SecuenciaBloqueadaException, MontoMinimoException, CabeceraCreditoQuirografarioException, BloqueoFinSemanaException {

		// Evalua el valor de monto minimo del credito
		final EvaluaReglaMontoMinimoDto montoMinimoRegla = this.validarMontoMinimoPrestamo(credito.getPrestamoCalculo().getMontoPrestamo(),
				new BigDecimal(credito.getPrestamoCalculo().getPlazoMeses()), credito.getTipoPrestamista(), credito.getEdadAsegurado());
		final boolean esEmergente = credito.getCreditoEspecial() != null
				&& credito.getCreditoEspecial().equals(CreditoEspecialEnum.EMERGENTE.getCodigoCredito());

		if (!esEmergente && !montoMinimoRegla.isCumpleMontoMinimo()) {
			throw new MontoMinimoException("El monto m\u00EDnimo del cr\u00E9dito debe ser al menos el " + montoMinimoRegla.getValorMinimoSBU()
					+ " del SBU: $" + montoMinimoRegla.getValorSBUMitad());
		}
		
		if(esDiaFeriado()) {
			throw new BloqueoFinSemanaException(Messages.getString("mensaje.bloqueo.fin.semana"));
		}

		try {
			final String query = "SELECT * FROM BIE_ACCESOSAPLICATIVO_TBL WHERE AC_CEDULA = '" + credito.getCedulaAfiliado() + "' AND AC_APLICATIVO = '"
					+ AplicativoEnum.PQ_WEB.getCodigo() + "' for update nowait";
			
			LOG.info("INICIO bloqueo creditos concesion " + credito.getCedulaAfiliado());
			this.queryGenericoBiessDao.ejecutarQueryGenerico(query);
			LOG.info("FIN bloqueo creditos concesion " + credito.getCedulaAfiliado());
			
			final Prestamo prestamoNew = prestamoQuirografarioServicio
					.crearCreditoQuirografario(credito);

			LOG.debug("Estado Prestamo: " + prestamoNew.getEstadoPrestamo());
			LOG.debug("observacion Prestamo: " + prestamoNew.getObsanupre());

			prestamoSeguridad(prestamoNew.getPrestamoPk());
		} catch (final SecuenciaBloqueadaException e) {
			LOG.error("No se pudo crear el crédito, favor intente más tarde.",
					e);
			throw new SecuenciaBloqueadaException(
					"No se pudo crear el crédito, favor intente más tarde.", e);
		} catch (final javax.ejb.EJBException e) {
			LOG.error("No se pudo crear el crédito: ", e);
			throw new CrearCreditoQuirografarioException(
					"No se pudo crear el crédito: ", e);
		} catch (final RuntimeException e) {
			final Throwable errorEsperado = getErrorEsperado(e);
			if (errorEsperado != null) {
				throw new CrearCreditoQuirografarioException(
						clearMessage(errorEsperado.getMessage()));
			} else
				throw e;
		} catch (final BeneficiarioCreditoExcepcion e) {
			LOG.error("Error al crear el Beneficiario del Credito.", e);
			throw new CrearCreditoQuirografarioException("Error al crear el Beneficiario del Credito.", e);
		} catch (final CabeceraCreditoQuirografarioException e) {
			throw new CabeceraCreditoQuirografarioException(e);
		} catch (final Exception e) {
			LOG.error("Error no controlado al crear el credito.", e);
			LOG.error("idTipoCredito:" + credito.getIdTipoCredito());
			LOG.error("idVariedadcredito:" + credito.getIdVariedadcredito());
			LOG.error("fechaSolicitud:" + credito.getFechaSolicitud());
			LOG.error("cedulaAfiliado:" + credito.getCedulaAfiliado());
			LOG.error("tipoSolicitante:" + credito.getTipoSolicitante());
			throw new CrearCreditoQuirografarioException("Error no controlado al crear el credito.", e);
		}

	}

	@Override
	public void novarCreditoQuirografario(final DatosCredito credito)
			throws NovarCreditoQuirografarioException, DiasNoPermitidosNovacionException, BloqueoFinSemanaException {
		LOG.error("INICIA EL PROCESO DE NOVACION-->>>>>>>> "
				+ credito.getPrestamoAnteriorNovacion().getNumafi());
		LOG.error("EMPIEZA EL PROCESO DE liquidación-->>>>>>>>");
		
		// Verifica si se pueden realizar novaciones por dias permitidos
		if (!this.permiteRealizarNovaciones()) {
			final String mensajeObservacion = "Por favor intente efectuar la solicitud de novaci\u00F3n en las fechas establecidas: <br /><br /> <ul><li>Meses de enero a noviembre del 1 al 27 de cada mes y</li><li>Mes de Diciembre del 1 al 25</li></ul>";
			throw new DiasNoPermitidosNovacionException(mensajeObservacion);
		}
		
		if(esDiaFeriado()) {
			throw new BloqueoFinSemanaException(Messages.getString("mensaje.bloqueo.fin.semana"));
		}
		
		try {

			final String query = "select * from KSCRETCREDITOS where "
					+ " NUMPREAFI = "
					+ credito.getPrestamoAnteriorNovacion().getPrestamoPk()
							.getNumpreafi()
					+ " and ORDPREAFI = "
					+ credito.getPrestamoAnteriorNovacion().getPrestamoPk()
							.getOrdpreafi()
					+ " and CODPRECLA = "
					+ credito.getPrestamoAnteriorNovacion().getPrestamoPk()
							.getCodprecla()
					+ " and CODPRETIP = "
					+ credito.getPrestamoAnteriorNovacion().getPrestamoPk()
							.getCodpretip() + " for update nowait";
			LOG.error("INICIO bloqueo creditos");
			queryGenericoDao.ejecutarQueryGenerico(query);
			LOG.error("FIN bloqueo creditos");

			// Generación de la cancelación de la liquidación
			LOG.error("OBTIENE EL VALOR DE LIQUIDACION DEL PRESTAMO ANTERIOR------->>>>"+ credito.getPrestamoAnteriorNovacion().getNumafi());

			credito.setValorCanceladoNovacion(credito.getPrestamoAnteriorNovacion().getValliqnov());

			LOG.error("FIN DE OBTENER EL PRESTAMO ANTERIOR DE LIQUIDACION------->>>>"+ credito.getPrestamoAnteriorNovacion().getNumafi());



			// Concesión del nuevo PQ
			final StringBuilder concatenadoPqAnterior = new StringBuilder();
			concatenadoPqAnterior.append(credito.getPrestamoAnteriorNovacion()
					.getPrestamoPk().getCodpretip());
			concatenadoPqAnterior.append(credito.getPrestamoAnteriorNovacion()
					.getPrestamoPk().getOrdpreafi());
			concatenadoPqAnterior.append(credito.getPrestamoAnteriorNovacion()
					.getPrestamoPk().getCodprecla());
			concatenadoPqAnterior.append(credito.getPrestamoAnteriorNovacion()
					.getPrestamoPk().getNumpreafi());
			credito.setNumeroCanceladoNovacion(new Long(String
					.valueOf(concatenadoPqAnterior)));
			//Ya no se usa numero de liquidacion pues se lo hace luego GAF
			LOG.error("EMPIEZA LA CREACIÓN DEL NUEVO PQ"+ credito.getPrestamoAnteriorNovacion().getNumafi());
			crearCreditoQuirografario(credito);
			LOG.error("PQ CREADO>>>>"+ credito.getPrestamoAnteriorNovacion().getNumafi());
			LOG.error("ACTUALIZACION DE DATOS DE CANCELACION EN EL DIVIDENDO"+ credito.getPrestamoAnteriorNovacion().getNumafi());
			final StringBuilder concatenadoPqNuevo = new StringBuilder();
			concatenadoPqNuevo.append(credito.getVariablePrestamo()
					.getVariablePrestamoPK().getCodpretip());
			concatenadoPqNuevo.append(credito.getOrdenPrestamo());
			concatenadoPqNuevo.append(credito.getVariablePrestamo()
					.getVariablePrestamoPK().getCodprecla());
			concatenadoPqNuevo.append(credito.getVariablePrestamo()
					.getSecvarpre());

	
			LOG.error("ACTUALIZACION EXITOSA"+ credito.getPrestamoAnteriorNovacion().getNumafi());
			LOG.error("TERMINA EL PROCESO DE NOVACION-->>>>>>>>"+ credito.getPrestamoAnteriorNovacion().getNumafi());

		} catch (final CrearCreditoQuirografarioException e) {
			final StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			final StringBuilder mensajeError = new StringBuilder();
			mensajeError.append(".\n\n" + sw.toString());
			mensajeError
					.append("Se suscitó un error controlado durante la novacion al crear el nuevo prestamo. El Prestamo Vigente Nro: "
							+ credito.getPrestamoAnteriorNovacion()
									.getPrestamoPk().getNumpreafi()
							+ " CodPrecla:"
							+ credito.getPrestamoAnteriorNovacion()
									.getPrestamoPk().getCodprecla()
							+ " del afiliado con cedula:"
							+ credito.getCedulaAfiliado());
			MailSend.enviarMailErrorNovacionPQ(mensajeError.toString());
			throw new NovarCreditoQuirografarioException(
					"ERROR EN LA NOVACIÓN DEL CRÉDITO. (CC)");
		} catch (final SecuenciaBloqueadaException e) {
			final StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			final StringBuilder mensajeError = new StringBuilder();
			mensajeError.append(".\n\n" + sw.toString());
			mensajeError
					.append("Se suscitó un error controlado durante la novacion.Se presento un bloqueo al obtener el secuencial del prestamo. El Prestamo Vigente Nro: "
							+ credito.getPrestamoAnteriorNovacion()
									.getPrestamoPk().getNumpreafi()
							+ " CodPrecla:"
							+ credito.getPrestamoAnteriorNovacion()
									.getPrestamoPk().getCodprecla()
							+ " del afiliado con cedula:"
							+ credito.getCedulaAfiliado());
			MailSend.enviarMailErrorNovacionPQ(mensajeError.toString());
			throw new NovarCreditoQuirografarioException(e.getMessage()
					.toUpperCase());
		} catch (final QueryGenericoException e) {
			final StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			final StringBuilder mensajeError = new StringBuilder();
			mensajeError.append(".\n\n" + sw.toString());
			mensajeError
					.append("Se suscitó un error controlado durante la novacion.Se presento un bloqueo en la tabla de creditos. El Prestamo Vigente Nro: "
							+ credito.getPrestamoAnteriorNovacion()
									.getPrestamoPk().getNumpreafi()
							+ " CodPrecla:"
							+ credito.getPrestamoAnteriorNovacion()
									.getPrestamoPk().getCodprecla()
							+ " del afiliado con cedula:"
							+ credito.getCedulaAfiliado());
			MailSend.enviarMailErrorNovacionPQ(mensajeError.toString());
			throw new NovarCreditoQuirografarioException(
					"No se pudo crear el crédito, favor intente más tarde."
							.toUpperCase());
		} catch (final Exception e) {
			final StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			final StringBuilder mensajeError = new StringBuilder();
			mensajeError
					.append("Se suscitó un error controlado inuscitado al novar el Prestamo Nro: "
							+ credito.getPrestamoAnteriorNovacion()
									.getPrestamoPk().getNumpreafi()
							+ " CodPrecla:"
							+ credito.getPrestamoAnteriorNovacion()
									.getPrestamoPk().getCodprecla()
							+ " del afiliado con cedula:"
							+ credito.getCedulaAfiliado());
			mensajeError.append(".\n\n" + sw.toString());
			MailSend.enviarMailErrorNovacionPQ(mensajeError.toString());
			throw new NovarCreditoQuirografarioException(
					"ERROR EN LA NOVACIÓN DEL CRÉDITO. (EI)");
		}

	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	private void prestamoSeguridad(final PrestamoPk prestamoPk)
			throws CrearCreditoQuirografarioException {

		final Prestamo prestamo = prestamoDao.load(prestamoPk);
		LOG.info(" ODEST : " + prestamo.getEstadoPrestamo().getCodestpre());
		if ("REC".equals(prestamo.getEstadoPrestamo().getCodestpre())) {
			LOG.error(" ESTADO : " + prestamo.getObsanupre());
			throw new CrearCreditoQuirografarioException(" Credito rechazado. "
					+ prestamo.getObsanupre());
		}

	}

	@Override
	public Prestamo bucarPrestamoPk(final PrestamoPk pk) {
		final Prestamo prestamo = prestamoDao.load(pk);
		prestamo.getDividendosPrestamo().size();

		return prestamo;
	}

	private Throwable getErrorEsperado(final Exception e) {
		Throwable ex = null;

		ex = e.getCause();
		while (ex != null) {
			if (ex.getMessage().indexOf("RECHAZADO") > -1) {
				return ex;
			}
			ex = ex.getCause();
		}

		return null;
	}

	private String clearMessage(final String msg) {
		int inicio = msg.indexOf("ORA-");
		inicio = inicio + 11;
		final int fin = msg.indexOf("ORA-", inicio);
		return msg.substring(inicio, fin);
	}

	@Override
	public CreditoConsolidado getResumenConsolidadoTotal() {
		return creditoConsolidadoDao.load(pkCreditoConsolidadoTotal);
	}

	@Override
	public CreditoConsolidado getResumenConsolidadoDiario() {
		return creditoConsolidadoDao.load(pkCreditoConsolidadoDiario);
	}

	@Override
	public List<Prestamo> getPrestamosPorCedula(final String cedula) {
		final List<Prestamo> prestamos = prestamoDao.getPrestamosPorCedula(cedula);
		return prestamos;
	}

	@Override
	public List<Prestamo> getPrestamosVigentesPorCedula(final String cedula,
			final List<String> estadoCredito) {
		return prestamoDao.listaPrestamoVigentesHl(cedula, estadoCredito);
	}

	@Override
	public Prestamo getPrestamoByNut(final Long nut) {

		final SolicitudCredito solicitud = solicitudCreditoDao.findByNut(nut);
		final Prestamo prestamo = prestamoDao.buscarPorSolicitud(solicitud.getSolicitudCreditoPK().getCodtipsolser(),
				solicitud.getSolicitudCreditoPK().getNumsolser());
		prestamo.setNut(nut);
		// Forzar a que se carguen los dividendos
		if (prestamo.getDividendosPrestamo() != null) {
			prestamo.getDividendosPrestamo().size();
			// Se determina el valor del saldo de capital
			prestamo.setDividendosPrestamo(
					determinaSaldoCapital(prestamo.getDividendosPrestamo(), prestamo.getValsalcap()));
		}
		return prestamo;
	}
	@Override
	public Long traerNut(final Long tiposolicitud, final Long numeroSolicitud) {

		// AQUI
		final SolicitudCredito solicitudCredito = solicitudCreditoDao.traerSolicitudPorTipoPorNumero(tiposolicitud,
				numeroSolicitud);

		Long nut = null;

		if (solicitudCredito != null) {
			nut = solicitudCredito.getNut();
		}

		return nut;

	}
	@Override
	public Prestamo getPrestamoByPk(final PrestamoPk pk) {
		final Prestamo prestamo = prestamoDao.load(pk);

		// Forzar a que se carguen los dividendos
		if (prestamo.getDividendosPrestamo() != null) {
			prestamo.getDividendosPrestamo().size();
			// Se determina el valor del saldo de capital
			prestamo.setDividendosPrestamo(determinaSaldoCapital(
					prestamo.getDividendosPrestamo(), prestamo.getValsalcap()));
		}
		
		return prestamo;
	}
	
	/**
	 * Función que se encarga de calcular el saldo de capital usando una lista
	 * de dividendos.
	 * 
	 * @param listaDividendoPrestamo
	 * @param valorPrestamo
	 * @return una lista objetos {@link DividendoPrestamo} con el valor del
	 *         saldo de capital
	 */
	private List<DividendoPrestamo> determinaSaldoCapital(
			final List<DividendoPrestamo> listaDividendoPrestamo, final Double valorPrestamo) {
		Double saldoCapital = valorPrestamo;
		final List<DividendoPrestamo> listaDividendoPrestamoActualizado = new ArrayList<DividendoPrestamo>();
		for (final DividendoPrestamo dividendoPrestamo : listaDividendoPrestamo) {
			saldoCapital = saldoCapital - dividendoPrestamo.getValcapamo();
			dividendoPrestamo.setSaldoCapital(new BigDecimal(saldoCapital));
			listaDividendoPrestamoActualizado.add(dividendoPrestamo);
		}

		return listaDividendoPrestamoActualizado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#consultarQuirografariosVigentes(java.lang.String,
	 * java.util.List, java.util.List)
	 */
	@Override
	public List<Prestamo> consultarQuirografariosVigentes(final String cedula, final List<String> estadoCredito, final List<Long> tipoCredito) {
		final List<Prestamo> listaprestamos = prestamoDao.consultarQuirografariosVigentes(cedula, estadoCredito, tipoCredito);
		/*Obtener dividendos del prestamo bajo demanda */
		/*
		 * for (Prestamo pre : listaprestamos) { pre.getDividendosPrestamo().size(); }
		 */

		// En caso que sea prestamo focalizado setea una variable para permitir anular el credito
		for (final Prestamo prestamo : listaprestamos) {
			prestamo.setPermiteAnular(false);
			if (TiposProductosPq.FOC.getCodigoTipoProducto().equals(prestamo.getPrestamoPk().getCodpretip())
					&& "GEN".equals(prestamo.getEstadoPrestamo().getCodestpre())) {
				final CreditoValidacion creditoValidacion = creditoValidacionServicio.consultarPorPrestamo(prestamo.getPrestamoPk().getCodprecla(),
						prestamo.getPrestamoPk().getCodpretip(), prestamo.getPrestamoPk().getNumpreafi(), prestamo.getPrestamoPk().getOrdpreafi());
				if ("SOL".equals(creditoValidacion.getEstado()) || "VAL".equals(creditoValidacion.getEstado())) {
					prestamo.setPermiteAnular(true);
				}
			}
		}

		return listaprestamos;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SituacionPrestamo obtenerSituacionPrestamo(
			final DatosSituacionPrestamo datSituacionPrestamo)
			throws SituacionPrestamoNoExisteException {
		SituacionPrestamo situacionPrestamo = null;
		List<DividendoPrestamo> divActuales = null;
		final Calendar cl = Calendar.getInstance();
		final Date fechaActual = cl.getTime();
		final Prestamo prestamo = prestamoDao.load(datSituacionPrestamo
				.getPrestamoPk());
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date fechaDivMor = null;
		Date fechaDivSaldeb = null;

		if (prestamo == null) {
			throw new SituacionPrestamoNoExisteException(
					"El prestamo no existe");
		}

		try {
			// TODO: Este servicio no deberia lanzar exception
			final boolean activo = servicioPrestadoServicio
					.consultarEsActivoPorCedula(prestamo.getNumafi());
			final String estadoPrestamo = prestamo.getEstadoPrestamo().getCodestpre();
			final boolean esVigente = CompararDatosCredito.CompararEstados(
					datSituacionPrestamo.getEstadosCreVigentes(),
					estadoPrestamo);
			final boolean esLiquidacion = CompararDatosCredito.CompararEstados(
					datSituacionPrestamo.getEstadosCreLiquidacion(),
					estadoPrestamo);
			// ELC,ELF,VIG,ELI,LCF
			if (activo && esVigente) {
				// VIG,ELI,LCF
				if (esLiquidacion) {
					// Contar dividendos en mora y registrados CB
					final List<DividendoPrestamo> dividendos = dividendoPrestamoServicio
							.obtenerDividendosPorPagar(datSituacionPrestamo
									.getPrestamoPk(), datSituacionPrestamo
									.getEstadosDivPorPagar());
					divActuales = new ArrayList<DividendoPrestamo>();
					for (final DividendoPrestamo div : dividendos) {
						if (sdf.format(div.getFecpagdiv()).compareTo(
								sdf.format(fechaActual)) <= 0) {
							divActuales.add(div);
						}
					}

					if (divActuales.size() > 0) {
						situacionPrestamo = SituacionPrestamo.ACTIVO_PRESTAMO_VIG_MORA;
					} else {
						throw new SituacionPrestamoNoExisteException(
								"No tiene dividendos de pago a la fecha");
					}
				} else {
					// Es activo y el prestamo esta en ELC o ELF
					// Validación para generar comprobante si el credito esta en
					// ELC o ELF y tiene dividendos en mora CB
					final List<DividendoPrestamo> dividendos = dividendoPrestamoServicio
							.obtenerDividendosEnMora(
									datSituacionPrestamo.getPrestamoPk(),
									datSituacionPrestamo.getEstadosDivMora());
					final List<SaldoLiquidacionPrestamo> dividendosLiqPre = saldoLiquidacionPrestamoServicio
							.obtenerSaldosPorLiquidacion(datSituacionPrestamo
									.getPrestamoPk(), datSituacionPrestamo
									.getEstadosDivSaldoLiq());
					if (dividendos.size() > 0 && dividendosLiqPre.size() > 0) {
						// Obtengo el primer dividendo seria la menor fecha de
						// pago;
						fechaDivMor = dividendos.get(0)
								.getFecpagdiv();
						fechaDivSaldeb = dividendosLiqPre
								.get(0).getDividendoPrestamo().getFecpagdiv();
						if (sdf.format(fechaDivSaldeb).compareTo(
								sdf.format(fechaDivMor)) > 0) {
							situacionPrestamo = SituacionPrestamo.ACTIVO_PRESTAMO_VIG_MORA;
						} else {
							situacionPrestamo = SituacionPrestamo.ACTIVO_PRESTAMO_ELC_ELF;
						}
					} else {
						if (dividendos.size() > 0) {
							situacionPrestamo = SituacionPrestamo.ACTIVO_PRESTAMO_VIG_MORA;
						} else {
							situacionPrestamo = SituacionPrestamo.ACTIVO_PRESTAMO_ELC_ELF;
						}
					}
				}
			} else {
				boolean cesante = false;

				try {
					cesante = servicioPrestadoServicio
							.consultarEsCesantePorCedula(prestamo.getNumafi());
				} catch (final NoTieneRelacionDeDependenciaException e) {
					// Si codprecla esta en 21,24,25 el estado es cesante
					final long codPreCla = datSituacionPrestamo.getPrestamoPk()
							.getCodprecla().longValue();
					final boolean existeTipo = CompararDatosCredito.CompararTipos(
							datSituacionPrestamo.getTipoCredito(), codPreCla);
					if (existeTipo) {
						cesante = true;
					} else {
						throw new SituacionPrestamoNoExisteException(
								"El afiliado no consta como cesante ni como activo");
					}
				}

				// ELC,ELF
				if (cesante && !esLiquidacion) {
					// Es cesante y el prestamo en liquidacion de fondos o
					// cesantias
					// Validación para generar comprobante si el credito esta en
					// ELC o ELF y tiene dividendos en mora CB
					final List<DividendoPrestamo> dividendos = dividendoPrestamoServicio
							.obtenerDividendosEnMora(
									datSituacionPrestamo.getPrestamoPk(),
									datSituacionPrestamo.getEstadosDivMora());
					final List<SaldoLiquidacionPrestamo> dividendosLiqPre = saldoLiquidacionPrestamoServicio
							.obtenerSaldosPorLiquidacion(datSituacionPrestamo
									.getPrestamoPk(), datSituacionPrestamo
									.getEstadosDivSaldoLiq());
					if (dividendos.size() > 0 && dividendosLiqPre.size() > 0) {
						// Obtengo el primer dividendo seria la menor fecha de
						// pago;
						fechaDivMor = dividendos.get(0)
								.getFecpagdiv();
						fechaDivSaldeb = dividendosLiqPre
								.get(0).getDividendoPrestamo().getFecpagdiv();
						if (sdf.format(fechaDivSaldeb).compareTo(
								sdf.format(fechaDivMor)) > 0) {
							situacionPrestamo = SituacionPrestamo.CESANTE_PRESTAMO_VIG;
						} else {
							situacionPrestamo = SituacionPrestamo.CESANTE_PRESTAMO_ELC_ELF;
						}
					} else {
						if (dividendos.size() > 0) {
							situacionPrestamo = SituacionPrestamo.CESANTE_PRESTAMO_VIG;
						} else {
							situacionPrestamo = SituacionPrestamo.CESANTE_PRESTAMO_ELC_ELF;
						}
					}
				}
				// VIG,ELI,LCF
				if (cesante && esLiquidacion) {
					// Es cesante y el prestamo vigente

					// Contar dividendos en mora y registrados CB
					final List<DividendoPrestamo> dividendos = dividendoPrestamoServicio
							.obtenerDividendosPorPagar(datSituacionPrestamo
									.getPrestamoPk(), datSituacionPrestamo
									.getEstadosDivPorPagar());
					divActuales = new ArrayList<DividendoPrestamo>();
					for (final DividendoPrestamo div : dividendos) {
						if (sdf.format(div.getFecpagdiv()).compareTo(
								sdf.format(fechaActual)) <= 0) {
							divActuales.add(div);
						}
					}
					if (divActuales.size() > 0) {
						situacionPrestamo = SituacionPrestamo.CESANTE_PRESTAMO_VIG;
					} else {
						throw new SituacionPrestamoNoExisteException(
								"No tiene dividendos de pago a la fecha");
					}
				}
			}

		} catch (final ServicioPrestadoException e) {
			throw new SituacionPrestamoNoExisteException(e.getMessage());
		}

		if (situacionPrestamo == null) {
			throw new SituacionPrestamoNoExisteException(
					"El prestamo no cumple condiciones para generar comprobante de pago individual");
		}

		return situacionPrestamo;

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean tienePrestamoQuirografarioMora(final String cedula,
			final List<String> estadoDividendo) {
		return dividendoPrestamoDao
				.tienePrestamoMoraHl(cedula, estadoDividendo);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean tienePrestamoQuirografarioVigente(final String cedula,
			final List<String> estadoCredito) {

		return prestamoDao.tienePrestamoVigentesHl(cedula, estadoCredito);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal getTotalCreditoVigente(final String cedula,
			final List<String> estadoPrestamo) {

		final BigDecimal resultado = prestamoDao.getTotalCreditoVigente(cedula,
				estadoPrestamo);

		return resultado == null ? BigDecimal.ZERO : resultado;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal getTotalSaldoCreditoVigente(final String cedula,
			final List<String> estadoPrestamo, final List<String> estadoDividendo) {
		final BigDecimal resultado = prestamoDao.getTotalSaldoCreditoVigente(cedula,
				estadoPrestamo, estadoDividendo);

		return resultado == null ? BigDecimal.ZERO : resultado;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public HistoricoAniosPlazoCapitalEndeudamiento getAniosPlazoCapitalEndeudamiento(
			final int tipoCredito, final int idCodigoCredito, final int plazo, final Date fecha) {

		return historicoAniosPlazoCapitalEndeudamientoDao
				.consultarEndeudamientoindividual(idCodigoCredito, tipoCredito,
						plazo, fecha);

	}

	@Override
	public List<CreditoQuirografarioHost> getCreditosQuirografariosHost(
			final String cedula, final List<String> listaCreditos)
			throws ListaVaciaException {

		return creditoQuirografarioHostDao.getCreditosQuirografariosHost(
				cedula, listaCreditos);
	}

	@Override
	public List<Prestamo> consultarQuirografariosVigentesPH(final String cedula,
			final List<String> estadoCredito, final List<Long> tipoCredito) {
		return prestamoDao.consultarQuirografariosVigentesPH(cedula,
				estadoCredito, tipoCredito);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean tienePrestamoQuirografarioVigentePH(final String cedula,
			final List<String> estadoCredito, final List<Long> tipoCredito) {
		return prestamoDao.tienePrestamoQuirografarioVigentesPH(cedula,
				estadoCredito, tipoCredito);
	}

	@Override
	public DetalleCredito consultarDetalleCredito(final String cedula,
			final Long numeroPrestamo, final Long codPreTip, final Long ordPreAfi, final Long codPreCla) {
		DetalleCredito detalleCredito = null;
		detalleCredito = prestamoDao.consultarDetalleCredito(
				cedula, numeroPrestamo, codPreTip, ordPreAfi, codPreCla);
		return detalleCredito;
	}

	@Override
	public List<Prestamo> consultarprestamospendientesaprobacion(
			final Date fecha_ant, final Date fecha_post) {
		return prestamoDao.consultarprestamospendientesaprobacion(fecha_ant,
				fecha_post);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void rechazarPrestamoPda(final Prestamo p, final String observacion,
			final Long idmotivorechazo, final String cedulafuncionario, final String estadoActualPrestamo)
			throws ActualizarPrestamoEstadoHistoricoException {

		final EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		estadoPrestamo.setCodestpre("REC"); // Cambiar el estado a APROBADO
		p.setEstadoPrestamo(estadoPrestamo);
		p.setFeccanpre(new Date());
		prestamoDao.actualizarFecCancelacionYEstado(p);
		prestamoDao.flush();

		LOG.info("ACTUALIZAR ESTADO");
		prestadohistorico.actualizarprestamoPdaRec(p.getPrestamoPk()
				.getNumpreafi(), p.getPrestamoPk().getOrdpreafi(), p
				.getPrestamoPk().getCodpretip(), p.getPrestamoPk()
				.getCodprecla(), estadoActualPrestamo, "PQ rechazado por funcionario");

		if (p.getNumprecannov() != null) {
			LOG.info("VERIFICAR NOVACION");

			final String numprecannnov = p.getNumprecannov().toString();
			final Long numpreafi = new Long(numprecannnov.substring(4));
			final Long codprecla = new Long(numprecannnov.substring(2, 4));
			final Long ordpreafi = new Long(numprecannnov.substring(0, 1));
			final Long codpretip = new Long(numprecannnov.substring(1, 2));
			final PrestamoPk ppk = new PrestamoPk(codprecla, codpretip, numpreafi,
					ordpreafi);
			try {
				LOG.info("PRIMER PROCEDIMIENTO");
				reversaprestamo.reversaValorescomprometidos(p.getPrestamoPk());

				LOG.info("SEGUNDO PROCEDIMIENTO");
				reversaprestamo.ejecutarreversa(ppk);

			} catch (final ReversaPrestamoExcepcion e) {
				sessionctx.setRollbackOnly();
				LOG.error(e.getMessage());
				throw new ActualizarPrestamoEstadoHistoricoException(
						"Error Ejecutando los procedimientos de reversa");
			}
		}
		final PrestamoResumenHistorico prestamoResumenHistorico = new PrestamoResumenHistorico();
		prestamoResumenHistorico.setPrestamo(p);
		prestamoResumenHistorico.setChFecCreacion(p.getFecpreafi());
		prestamoResumenHistorico.setChFecInicio(p.getFecinipre());
		prestamoResumenHistorico.setChFecFin(p.getFecfinpre());
		prestamoResumenHistorico.setChTipoCuenta(p.getTipoCuenta());
		prestamoResumenHistorico.setChNumCtabancaria(p.getNumctaban());
		prestamoResumenHistorico.setChRucIntsfinanciera(p.getRucempinsfin());
		prestamoResumenHistorico.setChValorDividendo(p.getValtotdiv());
		prestamoResumenHistorico.setChMonto(p.getMntpre());
		prestamoResumenHistorico.setChPlazo(p.getPlzmes());
		prestamoResumenHistorico.setChTasa(p.getTasint());

		prestamoResumenHistorico.setChIntDiasgracia(p.getIntdiagrc());
		prestamoResumenHistorico.setChValorSegurosaldos(p.getValsegsal());
		prestamoResumenHistorico.setChObservacion(observacion);
		prestamoResumenHistorico.setChFecTransaccion(new Date());
		prestamoResumenHistorico.setCr_cedulafuncionario(cedulafuncionario);// Almaceno
																			// la
																			// cedula
																			// del
																			// funcionario
																			// cuando
																			// hago
																			// una
																			// aprobacion
		prestamoResumenHistorico.setCr_motivorechazo(idmotivorechazo);
		prestamoResumenHistoricoDao.insert(prestamoResumenHistorico);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#existecuentaenlistablanca
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existecuentaenlistablanca(final String numafi,
			final String rucfinanciera, final String tipocta, final String numerocta) {
		return prestamoDao.existecuentaenlistablanca(numafi, rucfinanciera,
				tipocta, numerocta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#
	 * actualizarcabeceraprestamoPDA
	 * (ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk, boolean)
	 */
	@Override
	public void actualizarcabeceraprestamoPDA(final PrestamoPk pk,
			final boolean verificacionRegistro) {

		final Prestamo pr = prestamoDao.load(pk);

		final EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		if (verificacionRegistro) {
			estadoPrestamo.setCodestpre("PDA");
		} else {
			estadoPrestamo.setCodestpre("ERC");
		}
		pr.setEstadoPrestamo(estadoPrestamo);
		// Actualizo la fecha del prestamo para que modifique el historial
		prestamoDao.update(pr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#actualizarEstadoPrestamoECC(ec.gov.iess.creditos.modelo.
	 * persistencia.pk.PrestamoPk)
	 */
	@Override
	public void actualizarEstadoPrestamoECC(final PrestamoPk pk) {
		final Prestamo prestamo = prestamoDao.load(pk);
		final EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		estadoPrestamo.setCodestpre("ECC");
		prestamo.setEstadoPrestamo(estadoPrestamo);
		prestamoDao.update(prestamo);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#poseeCreditosERC(java
	 * .lang.String)
	 */
	@Override
	public List<Prestamo> poseeCreditosERC(final String cedula) {
		return prestamoDao.poseeCreditosERC(cedula);
	}

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#poseeCreditosECC(java.lang.String)
	 */
	@Override
	public List<PrestamoBiess> poseeCreditosECC(final String cedula) {
		final List<PrestamoBiess> lprestamos = prestamoBiessDao.getPrestamosEstadoBloqueo(cedula, "ECC", "C");
		
		return null == lprestamos || lprestamos.isEmpty() ? null : lprestamos;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#
	 * obtenerCreditoERCPorParametros(java.util.Date, java.util.Date,
	 * java.lang.String)
	 */
	@Override
	public List<Prestamo> obtenerCreditoPorParametros(final Date fechaDesde,
			final Date fechaHasta, final String cedula, final String estado, final String flagBloqueo)
			throws PrestamoException {
		List<Prestamo> lresult = new ArrayList<Prestamo>();
		if (fechaDesde != null && fechaHasta != null) {
			validarFechas(fechaDesde, fechaHasta);
		}
		final List<Prestamo> lista = prestamoDao.obtenerCreditoPorParametros(
				fechaDesde, fechaHasta, cedula, estado, "VIG", flagBloqueo);
		if (lista != null) {
			lresult = lista;
			for (final Prestamo p : lista) {
				p.setCuentaBancariaAnterior(prestamoDao
						.cuentaBancariaAnterior(p.getNumafi()));
			}
		}

		return lresult;
	}

	/**
	 * Validar fechas de nombramiento.
	 * 
	 * @param armador
	 * @throws PrestamoException
	 */
	private void validarFechas(final Date fechaDesde, final Date fechaHasta)
			throws PrestamoException {
		if (fechaDesde.after(fechaHasta)) {
			throw new PrestamoException(
					"La fecha desde debe ser menor o igual a la fecha hasta");
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<SolicitudPda> consultarprestamosPda(final Date fecha_ant,
			final Date fecha_post, final String cedulaBus, final boolean conCedula) {
		final List<SolicitudPda> listaSolicitudPDA = new ArrayList<SolicitudPda>();
		// prestamoDao.cuentaBancariaAnterior("1714677877");
		List<Prestamo> listaPrestamosPDA = null;
		if (conCedula) {
			listaPrestamosPDA = this.prestamoDao
					.consultarPrestamoPDAPorCedula(cedulaBus);
		} else {
			listaPrestamosPDA = this.prestamoDao
					.consultarprestamospendientesaprobacion(fecha_ant,
							fecha_post);
		}
		if (listaPrestamosPDA != null) {
			for (final Prestamo prestamo : listaPrestamosPDA) {
				final SolicitudPda solicitudPda = new SolicitudPda();
				Sucursal sucursalfila = null;
				DivisionPolitica divipoliticapatrono = null;
				final String cedula = prestamo.getNumafi();
				final String rucemp = prestamo.getRucemp();
				final String codsucu = prestamo.getCodsuc();
				final CuentaBancariaAnterior cba = prestamoDao
						.cuentaBancariaAnterior(cedula);
				if (cba != null) {
					solicitudPda.setTipoCuentaAnterior(cba
							.getTipoCuentaAnterior());
					solicitudPda.setNumeroCuentaAnterior(cba
							.getNumeroCuentaAnterior());
					solicitudPda.setEntidadFinancieraAnterior(cba
							.getEntidadFinancieraAnterior());
					solicitudPda.setFechaRegistroAnterior(cba
							.getFechaRegistroAnterior());
				}
				final CuentaBancariaAfiliado ctaafidatos = this.cuentabancariaafiliadoservicio
						.findCuentaValidaAfiliado(cedula);
				if (ctaafidatos == null) {
					solicitudPda.setFechactabancaria(null);
					solicitudPda.setPrimeravez("");
				} else {
					solicitudPda.setFechactabancaria(ctaafidatos
							.getFechaRegistro());
					if (ctaafidatos.getFechaActualizacion() == null)
						solicitudPda.setPrimeravez("PRIMERA VEZ");
					else {
						solicitudPda.setPrimeravez("ACTUALIZACION");
					}
				}
				solicitudPda.setPrestamo(prestamo);
				try {
					sucursalfila = this.sucursalservicio.consultarSucursal(
							rucemp, codsucu);
					solicitudPda.setTelsuc(sucursalfila.getTelsuc());
					solicitudPda
							.setApenomrepleg(sucursalfila.getApenomrepleg());
					solicitudPda.setDirsuc(sucursalfila.getDirsuc());
				} catch (final Exception e) {
					solicitudPda.setTelsuc("");
					solicitudPda.setApenomrepleg("");
					solicitudPda.setDirsuc("");
					LOG.error(e.getMessage());
				}
				try {
					divipoliticapatrono = this.divisionpoliticaservicio
							.consultaDivisionPoliticaPorId(sucursalfila
									.getCoddivpol());
					solicitudPda.setNomdivpol(divipoliticapatrono
							.getDivisionPolitica().getDivisionPolitica()
							.getNomdivpol());
					solicitudPda.setNomdivpolDP(divipoliticapatrono
							.getDivisionPolitica().getNomdivpol());
				} catch (final Exception e) {
					solicitudPda.setNomdivpol(null);
					solicitudPda.setNomdivpolDP(null);
					LOG.error(e.getMessage());
				}
				listaSolicitudPDA.add(solicitudPda);
			}

		}
		return listaSolicitudPDA;
	}

	@Override
	public List<SolicitudPda> consultarPrestamoPDAPorCedula(final String cedula) {
		final List<SolicitudPda> listaSolicitudPDA = new ArrayList<SolicitudPda>();
		final List<Prestamo> listaPrestamosPDA = this.prestamoDao
				.consultarPrestamoPDAPorCedula(cedula);
		for (final Prestamo prestamo : listaPrestamosPDA) {
			final SolicitudPda solicitudPda = new SolicitudPda();
			Sucursal sucursalfila = null;
			DivisionPolitica divipoliticapatrono = null;
			final String rucemp = prestamo.getRucemp();
			final String codsucu = prestamo.getCodsuc();

			final CuentaBancariaAfiliado ctaafidatos = this.cuentabancariaafiliadoservicio
					.findCuentaValidaAfiliado(cedula);
			if (ctaafidatos == null) {
				solicitudPda.setFechactabancaria(null);
				solicitudPda.setPrimeravez("");
			} else {
				solicitudPda
						.setFechactabancaria(ctaafidatos.getFechaRegistro());
				if (ctaafidatos.getFechaActualizacion() == null)
					solicitudPda.setPrimeravez("PRIMERA VEZ");
				else {
					solicitudPda.setPrimeravez("ACTUALIZACION");
				}
			}
			solicitudPda.setPrestamo(prestamo);
			try {
				sucursalfila = this.sucursalservicio.consultarSucursal(rucemp,
						codsucu);
				solicitudPda.setTelsuc(sucursalfila.getTelsuc());
				solicitudPda.setApenomrepleg(sucursalfila.getApenomrepleg());
				solicitudPda.setDirsuc(sucursalfila.getDirsuc());
			} catch (final Exception e) {
				solicitudPda.setTelsuc("");
				solicitudPda.setApenomrepleg("");
				solicitudPda.setDirsuc("");
				LOG.error(e.getMessage());
			}
			try {
				divipoliticapatrono = this.divisionpoliticaservicio
						.consultaDivisionPoliticaPorId(sucursalfila
								.getCoddivpol());
				solicitudPda.setNomdivpol(divipoliticapatrono
						.getDivisionPolitica().getDivisionPolitica()
						.getNomdivpol());
				solicitudPda.setNomdivpolDP(divipoliticapatrono
						.getDivisionPolitica().getNomdivpol());
			} catch (final Exception e) {
				solicitudPda.setNomdivpol(null);
				solicitudPda.setNomdivpolDP(null);
				LOG.error(e.getMessage());
			}
			listaSolicitudPDA.add(solicitudPda);
		}

		return listaSolicitudPDA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#actualizarPrestamoRegCivil
	 * (ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk)
	 */
	@Override
	public void actualizarPrestamoRegCivil(final Prestamo prestamo) {
		prestamo.setValidacionRegistroCivil("S");
		prestamoDao.update(prestamo);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#actualizarPrestamoCambioInuCtaBco(ec.gov.iess.creditos.modelo
	 * .persistencia.Prestamo)
	 */
	@Override
	public void actualizarPrestamoCambioInuCtaBco(final Prestamo prestamo) {
		prestamo.setValidacionRegistroCivil("C");
		prestamoDao.update(prestamo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#desbloquearPrestamoNovacion
	 * (ec.gov.iess.creditos.modelo.persistencia.Prestamo)
	 */
	@Override
	public void desbloquearPrestamoNovacion(final Prestamo prestamo, final String cedulaFuncionario) {
		final String flagBlqECC = new String(prestamo.getValidacionRegistroCivil());
		prestamo.setValidacionRegistroCivil("N");
		prestamoDao.update(prestamo);
		/* Se agrega a lista blanca los desbloqueos de tipo ECC */
		if (flagBlqECC.compareTo("C") == 0) {
			ctabancariaaprservicio.actulizarctalistablanca(prestamo
					.getNumafi(), null, null, null, null,
					cedulaFuncionario, "ECC");
		}		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#sumaSaldosPrestamosVigentes
	 * (java.lang.String)
	 */
	@Override
	public BigDecimal sumaSaldosPrestamosVigentes(final String cedula) {
		final List<BigDecimal> lista = dividendoPrestamoDao.saldoCapitalPQ(cedula);
		BigDecimal total = BigDecimal.ZERO;
		if (lista != null) {
			for (final BigDecimal v : lista) {
				total = total.add(v);
			}
		}
		return total;
	}

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#cambioInusualCtaBco(java.lang.String)
	 */
	@Override
	public boolean cambioInusualCtaBco(final String cedula) {
		/* Consultar lista de cuentas bancarias registradas en HL */
		final List<CuentaBancariaAfiliado> lcba = cuentabancariaafiliadoservicio
				.findCuentasPorAfiliado(cedula);
		/* Consultar lista blanca */
		final Date fechaLBlanca = listaBlancaDao.consultarListaBlancaECC(cedula);
		/* Se define fecha 30 dias anteriores a fecha actual */
		final Calendar calTope = Calendar.getInstance();
		calTope.add(Calendar.DATE, -30);
		Date fechaTope = calTope.getTime(); 
		/* Se toma la fecha mayor entre fechaTope y fechaLBlanca*/
		if (null != fechaLBlanca && fechaLBlanca.after(fechaTope)) {
			fechaTope = fechaLBlanca;
		}
		int contador = 0;
		for (final CuentaBancariaAfiliado cba : lcba) {
			//cba.getEstadoCuentaBancariaAfiliado().getCodigo().compareToIgnoreCase("AUT") != 0
			//if (Utilities.compareDatesWithoutTime(cba.getFechaRegistro(), fechaTope) > 0) {
			if (cba.getFechaRegistro().after(fechaTope)) {
				contador = contador + 1;
			}
		}
		
		return contador > 2 ? true : false;	
	}

	/* 
	 * Referencia
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#cumpleMontoMinimo(BigDecimal)
	 */
	@Override
	public boolean cumpleMontoMinimo(final BigDecimal montoPrestamo) throws PrestamoException {
		try {
			if (montoPrestamo.compareTo(BigDecimal.ONE) >= 0) {
				return true;
			}
			return false;
		} catch (final Exception e) {
			throw new PrestamoException("Error al validar monto m\u00EDnimo");
		}
	}
	
	/**
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#anularPrestamo(ec.gov
	 * .iess.creditos.modelo.persistencia.Prestamo, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void anularPrestamo(final Prestamo p, final String observacion)
			throws ActualizarPrestamoEstadoHistoricoException {
		final TiposProductosPq tipoProducto = TiposProductosPq
				.getTiposProductosPq(p.getPrestamoPk().getCodpretip());
		if (tipoProducto.getCategoriaTipoProducto().equals(CategoriaTipoProductoPq.CAT_SERVICIOS)) {						
			final String estadoAnulado = "ANU";
			// Anulacion solicitud
			final SolicitudCreditoPK solicitudCreditoPK = new SolicitudCreditoPK(
					p.getCodtipsolser(), p.getNumsolser());
			final SolicitudCredito solicitudCredito = solicitudCreditoDao
					.load(solicitudCreditoPK);
			solicitudCredito.setCodestsolser(estadoAnulado);
			solicitudCreditoDao.update(solicitudCredito);			
			// Actualizacion historico credito
			prestadohistorico.actualizarPrestamoHistorico(p, estadoAnulado,
					observacion);
			// Actualizacion credito
			prestamoBiessDao.actualizarFechaCancelacionYEstado(p, estadoAnulado, new Date());
			prestamoBiessDao.flush();
			final PrestamoResumenHistorico prestamoResumenHistorico = new PrestamoResumenHistorico();
			prestamoResumenHistorico.setPrestamo(p);
			prestamoResumenHistorico.setChFecCreacion(p.getFecpreafi());
			prestamoResumenHistorico.setChFecInicio(p.getFecinipre());
			prestamoResumenHistorico.setChFecFin(p.getFecfinpre());
			prestamoResumenHistorico.setChTipoCuenta(p.getTipoCuenta());
			prestamoResumenHistorico.setChNumCtabancaria(p.getNumctaban());
			prestamoResumenHistorico
					.setChRucIntsfinanciera(p.getRucempinsfin());
			prestamoResumenHistorico.setChValorDividendo(p.getValtotdiv());
			prestamoResumenHistorico.setChMonto(p.getMntpre());
			prestamoResumenHistorico.setChPlazo(p.getPlzmes());
			prestamoResumenHistorico.setChTasa(p.getTasint());
			prestamoResumenHistorico.setChIntDiasgracia(p.getIntdiagrc());
			prestamoResumenHistorico.setChValorSegurosaldos(p.getValsegsal());
			prestamoResumenHistorico.setChObservacion(observacion);
			prestamoResumenHistorico.setChFecTransaccion(new Date());
			prestamoResumenHistorico.setCr_cedulafuncionario(null);
			prestamoResumenHistorico.setCr_motivorechazo(null);
			prestamoResumenHistoricoDao.insert(prestamoResumenHistorico);
			// Eliminar los dividendos
			dividendoPrestamoServicio.eliminarDividendosEHistoricos(p.getPrestamoPk());
		}

	}

	/** 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#actualizarEstadoPrestamo
	 * (ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk,
	 * java.lang.String)
	 */
	@Override
	public void actualizarEstadoPrestamo(final PrestamoPk pk, final String estado) {

		//INC-2272 Pensiones Alimenticias
		final Prestamo pr = prestamoDao.load(pk);
		final EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		estadoPrestamo.setCodestpre(estado);
		pr.setEstadoPrestamo(estadoPrestamo);
		// Actualizo la fecha del prestamo para que modifique el historial
		prestamoDao.update(pr);

	}

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#
	 *      actualizarcabeceraprestamoPDC
	 *      (ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk)
	 */
	@Override
	public void actualizarcabeceraprestamoPDC(final PrestamoPk pk) {

		// INC-2272 Pensiones Alimenticias
		final Prestamo pr = prestamoDao.load(pk);

		final EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		estadoPrestamo.setCodestpre("PDC");
		pr.setEstadoPrestamo(estadoPrestamo);

		prestamoDao.update(pr);
	}

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#consultarPrestamos(java.util.List, java.util.Date,
	 *      java.util.Date, java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Prestamo> consultarPrestamos(final List<String> estadosPrestamo, final Date fechaAnt, final Date fechaPost,
			final String cedulaAfiliado, final Long idTipoProducto, final String visaPasaporte) {
		// INC-2148: Refugiados.
		return this.prestamoDao.consultarPrestamos(estadosPrestamo, fechaAnt, fechaPost, cedulaAfiliado,
				idTipoProducto, visaPasaporte);
	}
	

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#consultarPrestamosPorEstadosCedulas(java.util.List, 
	 * java.util.Date, java.util.Date, java.lang.Long, java.util.List)
	 */
	@Override
	public List<Prestamo> consultarPrestamosPorEstadosCedulas(final List<String> estadosPrestamo, final Date fechaAnt, final Date fechaPost,
			final Long idTipoProducto, final List<String> cedulasPrestamo) {
		return this.prestamoDao.consultarPrestamosPorEstadosCedulas(estadosPrestamo, fechaAnt, fechaPost,
				idTipoProducto, cedulasPrestamo);
	}

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#
	 * consultaDivisionPoliticaPorId(ec.gov.iess.hl.modelo.Sucursal)
	 */
	@Override
	public DivisionPolitica consultaDivisionPoliticaPorId(final Sucursal sucursalfila)
			throws DivisionPoliticaException {
		// INC-2272 Pensiones Alimenticias
		return this.divisionpoliticaservicio
				.consultaDivisionPoliticaPorId(sucursalfila.getCoddivpol());
	}

	/**
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#obtenerSucursal(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public Sucursal obtenerSucursal(final String rucemp, final String codsucu)
			throws SucursalException {
		// INC-2272 Pensiones Alimenticias
		return this.sucursalservicio.consultarSucursal(rucemp, codsucu);
	}

	/**
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#obtenerCuentaValidaAfiliado
	 * (java.lang.String)
	 */
	@Override
	public CuentaBancariaAfiliado obtenerCuentaValidaAfiliado(final String cedula) {
		// INC-2272 Pensiones Alimenticias
		return this.cuentabancariaafiliadoservicio
				.findCuentaValidaAfiliado(cedula);
	}

	/** 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#
	 * obtenerCuentabancariaAnterior(java.lang.String)
	 */
	@Override
	public CuentaBancariaAnterior obtenerCuentabancariaAnterior(final String cedula) {
		// INC-2272 Pensiones Alimenticias
		return prestamoDao.cuentaBancariaAnterior(cedula);
	}
	
	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#obtenerPrestamoInformacion(ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk)
	 */
	@Override
	public PrestamoInformacion obtenerPrestamoInformacion(final PrestamoPk prestamoPk) {
		return prestamoInformacionDao.load(prestamoPk);
	}
	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#actualizarcabeceraprestamoPDV(ec.gov.iess.creditos.modelo.
	 *      persistencia.pk.PrestamoPk)
	 */
	 @Override
	public void actualizarcabeceraprestamoPDV(final PrestamoPk pk) {

        // INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
        final Prestamo pr = prestamoDao.load(pk);

        final EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
        estadoPrestamo.setCodestpre("PDV");
	        
        pr.setEstadoPrestamo(estadoPrestamo);
        try {
			Thread.sleep(2000);
		} catch (final InterruptedException e) {
			LOG.error("Error al actualizar estado PDV", e);
		}

        prestamoDao.update(pr);
    }
		
		/*
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#obtenerPrestamoInformacionDetalle(ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk)
	 */
	@Override
	public List<PrestamoInformacionDetalle> obtenerPrestamoInformacionDetalle(final PrestamoPk prestamoPk) {
		return prestamoInformacionDetalleDao.obtenerInformacionDetallePorPK(prestamoPk);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#contarPorEstadoAnio(java.lang.String, java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public Long contarPorEstadoAnio(final String numafi, final String estadoPrestamo, final Long aniper) {
		return prestamoDao.contarPorEstadoAnio(numafi, estadoPrestamo, aniper);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#verificarMontoMaximoSBU(java.math.BigDecimal,
	 * java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal)
	 */
	@Override
	public boolean verificarMontoMaximoSBU(final BigDecimal monto, final BigDecimal sumaSaldos, final BigDecimal sbu, final BigDecimal numsbu) {
		boolean aprobado = true;
		if (sumaSaldos != null) {
			final BigDecimal totalSBU = sbu.multiply(numsbu).setScale(2, BigDecimal.ROUND_HALF_UP);
			final BigDecimal totalValor = sumaSaldos.add(monto);
			if (new Integer(totalValor.compareTo(totalSBU)).equals(-1)) {
				aprobado = true;
			} else {
				aprobado = false;
			}
		} else {
			aprobado = true;
		}
		return aprobado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#contarPorFechaCancelacion(java.util.Date, java.util.Date,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public BigDecimal contarPorFechaCancelacion(final Date fecrecpla, final Date feccanpla, final String codtippla, final String rucemp, final String codsuc) {
		return prestamoDao.contarPorFechaCancelacion(fecrecpla, feccanpla, codtippla, rucemp, codsuc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#contarPlanillasMoraBiessEmergente(java.util.Date,
	 * java.lang.String)
	 */
	@Override
	public BigDecimal contarPlanillasMoraInvididualBiessEmergente(final Date fechaCreacionPlanilla, final String cedula) {
		return prestamoDao.contarPlanillasMoraInvididualBiessEmergente(fechaCreacionPlanilla, cedula);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#contarPlanillasMoraPatronalEmergente(java.util.Date,
	 * java.lang.String)
	 */
	@Override
	public BigDecimal contarPlanillasMoraPatronalEmergente(final Date fechaCreacionPlanilla, final String cedula) {
		return prestamoDao.contarPlanillasMoraPatronalEmergente(fechaCreacionPlanilla, cedula);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#ejecutarCambioEstadoSP(java.util.Map, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void ejecutarCambioEstadoSP(final Map<String, Long> parametrosCredito, final String estadoCredito, final String estadoActualCredito)
			throws PrestamoPqCoreException {
		prestamoDao.ejecutarCambioEstadoSP(parametrosCredito, estadoCredito, estadoActualCredito);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#cambiarEstadoCredito(java.util.Map, java.lang.String)
	 */
	@Override
	public void cambiarEstadoCredito(final Map<String, Long> parametrosCredito, final String estadoCredito) throws PrestamoPqCoreException {
		prestamoDao.cambiarEstadoCredito(parametrosCredito, estadoCredito);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#anularCreditoPQConProcedimiento(java.util.Map,
	 * java.lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void anularCreditoPQConProcedimiento(final Map<String, Long> parametrosCredito, final String estadoActualCredito) throws PrestamoPqCoreException {
		this.anularCreditoPQConProcedimiento(parametrosCredito, estadoActualCredito, "ANU");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#contarPorEstadoTipoPrestamo(java.lang.String,
	 * java.lang.String, java.lang.Long)
	 */
	@Override
	public Long contarPorEstadoTipoPrestamo(final String numafi, final String estadoPrestamo, final Long codpretip) {
		return prestamoDao.contarPorEstadoTipoPrestamo(numafi, estadoPrestamo, codpretip);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#contarPorEstadoFeccanpreAnio(java.lang.String,
	 * java.lang.String, java.lang.Long, java.util.Date)
	 */
	@Override
	public Long contarPorEstadoFeccanpreAnio(final String numafi, final String estadoPrestamo, final Long aniper, final Date feccanpre) {
		return prestamoDao.contarPorEstadoFeccanpreAnio(numafi, estadoPrestamo, aniper, feccanpre);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#obtenerPrestamosVigentesNovacion(java.lang.String,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista)
	 */
	@Override
	public List<Prestamo> obtenerPrestamosVigentesNovacion(final String cedula, final TipoPrestamista tipoPrestamista) {
		final List<Prestamo> prestamosNovar = new ArrayList<Prestamo>();
		final List<Prestamo> creditosNovacionTmp = this.consultarQuirografariosVigentes(cedula, obtenerEstadosCreditoNovacion(),
				obtenerTiposCreditoNovacion(tipoPrestamista));

		for (final Prestamo p : creditosNovacionTmp) {
			// INC-2272 Pensiones Alimenticias
			if ("VIG".equals(p.getEstadoPrestamo().getCodestpre())
					&& (p.getCreditoPk().getCodpretip().equals(TiposProductosPq.NOR.getCodigoTipoProducto())
							|| p.getCreditoPk().getCodpretip().equals(TiposProductosPq.PEN.getCodigoTipoProducto()))) {
				prestamosNovar.add(p);
			}
		}

		return prestamosNovar;
	}
	
	/**
	 * Obtiene los estados de los creditos que se pueden novar
	 * 
	 * @return
	 */
	private List<String> obtenerEstadosCreditoNovacion() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_PQ_VIGENTE);
		return estados;
	}
	
	/**
	 * Obtiene los tipos de credito para novar
	 * 
	 * @return
	 */
	private List<Long> obtenerTiposCreditoNovacion(final TipoPrestamista tipoPrestamista) {
		final List<Long> tiposCredito = new ArrayList<Long>();
		tiposCredito.add(TiposCredito.TIPO_CREDITO_AFILIADO);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_ZAFRERO);
		if (tipoPrestamista.compareTo(TipoPrestamista.AFILIADO) != 0) {
			tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HL);
			tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_UIO);
			tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_GYE);
		}

		return tiposCredito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#obtienePrestamosVigentesNovacionSimulador(java.lang.String,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista)
	 */
	@Override
	public List<Prestamo> obtienePrestamosVigentesNovacionSimulador(final String cedula, final TipoPrestamista tipoPrestamista) {
		final List<Prestamo> prestamosNovarSimulador = new ArrayList<Prestamo>();
		List<Prestamo> prestamosVigentes = new ArrayList<Prestamo>();
		prestamosVigentes = this.obtenerPrestamosVigentesNovacion(cedula, tipoPrestamista);
		
		final ValidarRequisitosPrecalificacion requiPre = new ValidarRequisitosPrecalificacion();
		ResumenConsolidado resumenConsolidado = null;
		for (final Prestamo prestamo : prestamosVigentes) {
			/*if (this.novarCreditoProductoNovacion(prestamo, tipoPrestamista)) {
				continue;
			}*/
			if (this.verificaCreditoJubiladoAfiliado(prestamo, tipoPrestamista)) {
				continue;
			}
			resumenConsolidado = new ResumenConsolidado();
			this.determinarPorcentajeCancelacionPqVigente(prestamo, resumenConsolidado, requiPre);
			if (resumenConsolidado.isCumplePlazoCancelacionCredito()) {
				prestamosNovarSimulador.add(prestamo);
			}
		}
		
		return prestamosNovarSimulador;
	}
	
	/**
	 * Verifica que un prestamo que fue creado como afiliado no se muestre en la novacion del rol de jubilado
	 * 
	 * @param prestamo
	 * @param tipoPrestamista
	 * @return Devuelve true cuando NO puede novar un credito
	 */
	private boolean verificaCreditoJubiladoAfiliado(final Prestamo prestamo, final TipoPrestamista tipoPrestamista) {
		boolean resp = false;
		
		if (TipoPrestamista.JUBILADO == tipoPrestamista && (prestamo.getPrestamoPk().getCodprecla().equals(20L))) {
			resp = true;
		}
		
		return resp;
	}
	


	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoServicio#determinarPorcentajeCancelacionPqVigente(ec.gov.iess.creditos.
	 * modelo.persistencia.Prestamo, ec.gov.iess.consolidado.modelo.ResumenConsolidado,
	 * ec.gov.iess.creditos.modelo.dto.ValidarRequisitosPrecalificacion)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void determinarPorcentajeCancelacionPqVigente(final Prestamo prestamoVigente,
			final ResumenConsolidado resumenConsolidado, final ValidarRequisitosPrecalificacion requisitosPrec) {
		LOG.debug("codPreCla" + prestamoVigente.getPrestamoPk().getCodprecla());
		LOG.debug("tipoPres" + prestamoVigente.getPrestamoPk().getCodpretip());
		LOG.debug("numpres" + prestamoVigente.getPrestamoPk().getNumpreafi());
		LOG.debug("orden" + prestamoVigente.getPrestamoPk().getOrdpreafi());
		final List<String> estadosPrestamos = new ArrayList<String>();
		estadosPrestamos.add("cancelado");
		List<DividendoPrestamo> dividendos = new ArrayList<DividendoPrestamo>();
		dividendos = dividendoDao.obtenerDividendosPorClaseEstado(prestamoVigente.getPrestamoPk(), estadosPrestamos);
		float totalDividendosCancelados = 0;
		for (final DividendoPrestamo d : dividendos) {
			totalDividendosCancelados += d.getValcapamo();
		}
		
		// INICIO CALCULO DIVIDENDOS CANCELADOS PARA NOVACIONES DE PRESTAMOS EMERGENTE
		int referenciaNumDiv = 0;
		if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamoVigente.getCreditoEspecial())) {
			Collections.sort(dividendos, new Comparator<DividendoPrestamo>() {
				@Override
				public int compare(final DividendoPrestamo o1, final DividendoPrestamo o2) {
					// Comparamos los valores de los sueldos y multiplicamos
					// por -1 para que el orden sea decendente
					return o1.getDividendoPrestamoPk().getNumdiv().compareTo(o2.getDividendoPrestamoPk().getNumdiv());
				}
			});

			int mesesGracia = 0;
			try {
				mesesGracia = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MESES_GRACIA.getIdParametro(),
						BiessEmergenteEnum.MESES_GRACIA.getNombreParametro()).getValorNumerico().intValue();
			} catch (final ParametroBiessException e) {
				LOG.info("Error al obtener el parametro de meses de gracia", e);
			}
			referenciaNumDiv = mesesGracia;
			
			int cont = 1;
			for (final Iterator<DividendoPrestamo> iterator = dividendos.iterator(); iterator.hasNext();) {
				final DividendoPrestamo dp = iterator.next();
				if (cont > mesesGracia) {
					break;
				}

				if (dp.getDividendoPrestamoPk().getNumdiv() == cont) {
					iterator.remove();
				}

				cont++;
			}
			
		}
		// INICIO CALCULO DIVIDENDOS CANCELADOS PARA NOVACIONES DE PRESTAMOS EMERGENTE
		
		LOG.info("Determinacion Porcentaje Cancelacion PQ: Total dividendos cancelados: " + totalDividendosCancelados);
		LOG.info("Determinacion Porcentaje Cancelacion PQ: Valor prestamo capital: " + prestamoVigente.getValsalcap());
		final float porcentaje = (float) ((totalDividendosCancelados / prestamoVigente.getValsalcap()) * 100);
		LOG.info("Determinacion Porcentaje Cancelacion PQ: Porcentaje final cancelado: " + porcentaje);
		resumenConsolidado.setPorcentajeCancelacionCredAnt(porcentaje);
		float porcentajeValidar = 0f;
		String parametroPorcentaje = null;
		try {
			parametroPorcentaje = (String) consultaParametroServicio.consultarParametro(PORCENTAJE_NOVACION_CANCELADA, "float");
			porcentajeValidar = (Float.parseFloat(parametroPorcentaje))/100;
			LOG.info("Determinacion Porcentaje Nocavion Cancelada PQ: " + porcentajeValidar);
		} catch (final ConsultaParametroException e) {
			LOG.error("Error al consultar parametro porcentaje de novacion cancelada", e);
		}
		resumenConsolidado.setCreditosHost(parametroPorcentaje);
		if (cumplePlazoCancelacion(dividendos, prestamoVigente.getPlzmes(),
				porcentajeValidar, referenciaNumDiv)) {
			resumenConsolidado.setCumplePlazoCancelacionCredito(true);
		} else {
			resumenConsolidado.setCumplePlazoCancelacionCredito(false);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	private boolean cumplePlazoCancelacion(final List<DividendoPrestamo> dividendos, final long plazo, final float porcentajePlazo, int referenciaNumDiv) {
		boolean flag = false;
		boolean pagosConsecutivos = true;
		final boolean plazoCancelacion = true;
		if (dividendos != null) {
			final int numMesesACumplir = Math.round((plazo * porcentajePlazo));
			final int numDividendosCancelados = dividendos.size();
			// Validación del plazo de dividendos cancelados
			if (numDividendosCancelados < numMesesACumplir) {
				return false;
			}
			
			// Validaci�n del pago consecutivo para los dividendos
			for (final DividendoPrestamo d : dividendos) {
				referenciaNumDiv++;
				if (referenciaNumDiv > numMesesACumplir) {
					break;
				}
				if (d.getDividendoPrestamoPk().getNumdiv() != referenciaNumDiv) {
					pagosConsecutivos = false;
					break;
				}
			}
			if (pagosConsecutivos && plazoCancelacion) {
				flag = true;
			}
		}
		LOG.info("Al validar plazos de cancelaci\u00F3n, pagos consecu: " + pagosConsecutivos + " plazo cancelacion: "
				+ plazoCancelacion);
		return flag;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#validarMontoMinimoPrestamo(java.math.BigDecimal,
	 * java.math.BigDecimal, ec.gov.iess.creditos.enumeracion.TipoPrestamista, int)
	 */
	@Override
	public EvaluaReglaMontoMinimoDto validarMontoMinimoPrestamo(final BigDecimal monto, final BigDecimal plazo, final TipoPrestamista tipoPrestamista, final int edadAsegurado)
			throws MontoMinimoException {
		final EvaluaReglaMontoMinimoDto resp = new EvaluaReglaMontoMinimoDto();
		resp.setCumpleMontoMinimo(true);
		
		BigDecimal valorSBU = null;
		BigDecimal valorMinimoSBU = null;
		BigDecimal valorSBUMitad = null;
		
		try {
			ParametrizacionPQ parametrizacionPQ = null;
			
			if (TipoPrestamista.JUBILADO == tipoPrestamista) {
				parametrizacionPQ = this.parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProductoEdad(plazo, "A",
						TipoProductoEnum.JUB_PEN.getDescripcion(), new BigDecimal(edadAsegurado));
			} else {
				parametrizacionPQ = this.parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProducto(plazo, "A",
						TipoProductoEnum.NORMAL.getDescripcion());
			}
			
			valorMinimoSBU = parametrizacionPQ.getMontoMinimoSBU();
			
			if (valorMinimoSBU == null) {
				throw new MontoMinimoException("No existe parametrizado el valor de monto m\u00EDnimo del cr\u00E9dito.");
			}
			
			valorSBU = parametroCreditoServicio.obtenerValorSBU();
			valorSBUMitad = valorSBU.multiply(valorMinimoSBU);
			
			if (monto.compareTo(valorSBUMitad) < 0) {
				resp.setCumpleMontoMinimo(false);
				resp.setValorMinimoSBU(valorMinimoSBU);
				resp.setValorSBUMitad(valorSBUMitad);
			}
		} catch (final ParametrizacionPQException e) {
			LOG.error("Error al obtener el parametro para monto minimo", e);
			throw new MontoMinimoException("Error al obtener el par\u00E1metro para monto m\u00EDnimo");
		} catch (final ParametroCreditoException e1) {
			LOG.error("Error al obtener el valor del SBU ", e1);
			throw new MontoMinimoException("Error al obtener el par\u00E1metro del SBU");
		}
		
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#permiteRealizarNovaciones()
	 */
	@Override
	public boolean permiteRealizarNovaciones() {
		boolean resp = false;
		final Calendar calendar = Calendar.getInstance();
		ParametroPQ parametroPQNov = null;
		ParametroPQ parametroPQDic = null;

		if (Calendar.DECEMBER == calendar.get(Calendar.MONTH)) {
			parametroPQDic = parametroPQDao.consultarPorCodigo("BLONOVDIPQ", 1);
			final int diaHastaPermitidos = Integer.parseInt(parametroPQDic.getValorCaracter());
			if (calendar.get(Calendar.DAY_OF_MONTH) <= diaHastaPermitidos) {
				resp = true;
			}
		} else {
			parametroPQNov = parametroPQDao.consultarPorCodigo("BLOQNOVPQ", 1);
			final int diaHastaPermitidos = Integer.parseInt(parametroPQNov.getValorCaracter());
			if (calendar.get(Calendar.DAY_OF_MONTH) <= diaHastaPermitidos) {
				resp = true;
			}
		}

		return resp;
	}
	
	public boolean validarHabilitaNovacionPlanillaje(TipoPrestamista tipoPrestamista,String rangoJub,String rangoAfi, String rangoAfiJub) throws PeriodoComprobanteException {
	    String tipo=obtenerTipoPeriodo(tipoPrestamista);
		String rangos="";
		if(PERIODO_NOVA_AFI.equals(tipo)){			
			rangos=rangoAfi;
		}else if(PERIODO_NOVA_JUB.equals(tipo)) {
			rangos=rangoJub;
		}else if(PERIODO_NOVA_AFI_JUB.equals(tipo)) {
			rangos=rangoAfiJub;
		}
		return habilitarNovacionPlanillaje(rangos);
	}

	
	private String obtenerTipoPeriodo(TipoPrestamista tipoPrestamista) {
		if (TipoPrestamista.AFILIADO.equals(tipoPrestamista) || TipoPrestamista.ZAFRERO_TEMPORAL.equals(tipoPrestamista)
				|| TipoPrestamista.VOLUNTARIO.equals(tipoPrestamista)
				|| TipoPrestamista.VOLUNTARIO_EXT.equals(tipoPrestamista)) {
			return PERIODO_NOVA_AFI;

		} else if (TipoPrestamista.JUBILADO.equals(tipoPrestamista)) {
           return PERIODO_NOVA_JUB;
		} else if (TipoPrestamista.AFILIADO_JUBILADO.equals(tipoPrestamista)) {
           return PERIODO_NOVA_AFI_JUB;           
		}
		return null;
		
	}
	
	private boolean habilitarNovacionPlanillaje(final String rangos) {
		
		final Calendar fechaGeneracion = Calendar.getInstance();
		final String[] rangosHabilitados = rangos.split("\\|");
		for (final String rangoHabilitado : rangosHabilitados) {
			if (!rangoHabilitado.trim().isEmpty()) {
				// Solo si el dia(fechaValidacion) esta dentro del rango
				if (this.validaRango(rangoHabilitado, fechaGeneracion)) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
	
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
			
			if((calendarioInicial.before(fecha)
					||calendarioInicial.equals(fecha))
					&&(fecha.before(calendarioFinal)
							||fecha.equals(calendarioFinal))){
				valida=true;
			}			
		}		
		return valida;
	}

	/**
	 * Valida si es dia feriado basado en requerimiento anterior
	 * @return
	 */
	private boolean esDiaFeriado() {
		boolean esDiaFeriado=false;
		 try
		    {
		
			 String fechaBloqueo = this.parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.FECHA_BIESS_PQ_BLOQUEO.getIdParametro(),BiessEmergenteEnum.FECHA_BIESS_PQ_BLOQUEO.getNombreParametro()).getValorCaracter(); 
								 
		      String[] dias = fechaBloqueo.split(";");
		      Calendar fechaActual = Calendar.getInstance();
		      fechaActual.set(Calendar.HOUR_OF_DAY, 0);
		      fechaActual.set(Calendar.MINUTE, 0);
		      fechaActual.set(Calendar.SECOND, 0);
		      fechaActual.set(Calendar.MILLISECOND, 0);
		      
		      for (String diaFeriado : dias)
		      {
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		        Date fechaFeriado = sdf.parse(diaFeriado);
		        Calendar calendarFeriado = Calendar.getInstance();
		        calendarFeriado.setTime(fechaFeriado);
		        calendarFeriado.set(Calendar.HOUR_OF_DAY, 0);
				calendarFeriado.set(Calendar.MINUTE, 0);
				calendarFeriado.set(Calendar.SECOND, 0);
				calendarFeriado.set(Calendar.MILLISECOND, 0);
			
		        if (fechaActual.compareTo(calendarFeriado) == 0) {
		        	esDiaFeriado = true;
		        }
			}
		      LOG.info("Hoy esta bloqueado: " + esDiaFeriado);
		    }catch (ParametroBiessException e){
		      LOG.error("Error al determinar dia de bloqueo", e);
		    } catch (ParseException e) {
		    	 LOG.error("Error al transformar fecha obtenida de parametro", e);
		}

		 return esDiaFeriado;
	}
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoServicio#anularCreditoPQConProcedimiento(java.util.Map,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void anularCreditoPQConProcedimiento(final Map<String, Long> parametrosCredito, final String estadoActualCredito, final String estadoAnulacion)
			throws PrestamoPqCoreException {
		LOG.info("Anulacion del credito: " + parametrosCredito.toString());
		this.ejecutarCambioEstadoSP(parametrosCredito, estadoAnulacion, estadoActualCredito);
		LOG.debug("Ejecucion cambio estado credito SP: " + parametrosCredito.toString());
		this.cambiarEstadoCredito(parametrosCredito, estadoAnulacion);

		final Long codpretip = parametrosCredito.get("codpretip");
        //Se rechaza la solicitud esto se agrega para centralizacion de cartera
		final SolicitudCreditoPK solCrePk=new SolicitudCreditoPK();
		solCrePk.setCodtipsolser(parametrosCredito.get("codtipsolser"));
		solCrePk.setNumsolser(parametrosCredito.get("numsolser"));
		solicitudCreditoDao.actualizarEstadoSolicitudCredito(solCrePk, "REC");
		// Solo cuando es PQ Focalizado anula en la tabla CRE_CREDITOVALIDACION_T
		if (TiposProductosPq.FOC.getCodigoTipoProducto().equals(codpretip)) {
			final CreditoValidacion creditoValidacion = creditoValidacionServicio.consultarPorPrestamo(parametrosCredito.get("codprecla"),
					parametrosCredito.get("codpretip"), parametrosCredito.get("numpreafi"), parametrosCredito.get("ordpreafi"));
			creditoValidacion.setEstado(estadoAnulacion);
			creditoValidacionServicio.actualizarCreditoValidacion(creditoValidacion);
		}
	}

	
	@Override
	public BigDecimal obtenerTotalCreditosConciliacion(final String cedula) {
		
		return prestamoDao.obtenerCreditoConciliacion(cedula);
	}

	
	@Override
	public List<Prestamo> listarPrestamosCancelados(final String cedula) {
		return prestamoDao.listarPrestamosCancelados(cedula);

	}

	@Override
	public void crearOperacionSAC(final DatosCredito datosCredito, final Prestamo prestamo) {
		try {
			LOG.info("Inicio de Actualizacion de NUT y estadoa APR");
			// Actualizar credito a estado APR
			actualizarEstadoPrestamo(prestamo.getPrestamoPk(), "APR");
			LOG.info("Fin de Actualizacion de NUT y estado APR");
			// Fabricar entidades para enviar al SAC
			final InformacionPQOperacionRequestDto informacionPqRequest = new InformacionPQOperacionRequestDto();
			informacionPqRequest.setNumeroIdentificacion(datosCredito.getCedulaAfiliado());
			informacionPqRequest.setTipoIdentificacion(datosCredito.getTipoIdentificacionSac());
			informacionPqRequest.setNombreAsegurado(datosCredito.getNombreAsegurado());
			final OperacionSacRequest operacionSacRequest = new OperacionSacRequest();
			operacionSacRequest.setCliente(fabricarCliente(datosCredito));
			prestamo.setNut(datosCredito.getNut());
			operacionSacRequest.setOperacion(fabricarOperacion(prestamo));
			operacionSacRequest.setPrestamo(fabricarPrestamo(datosCredito));
			operacionSacRequest.setDatoDemografico(fabricarDatoDemografico(datosCredito));
			operacionSacRequest.setDatoCaptacion(fabricarDatoCaptacion());
			operacionSacRequest.setDatoDesembolso(fabricarDatoDesembolso(datosCredito));
			if (datosCredito.isEsNovacion()) {
				operacionSacRequest.getOperacion()
						.setNumero(datosCredito.getPrestamoAnteriorNovacion().getNumOperacionSAC());
				operacionSacRequest.getDatoDesembolso().setMontoPrecancelacion(
						BigDecimal.valueOf(datosCredito.getPrestamoAnteriorNovacion().getValliqnov()).setScale(2,
								BigDecimal.ROUND_HALF_UP));
			}
			final CreditoExigibleDto operacion = creditoPQCrearOperacionSacServicio
					.obtenerNumeroOperacion(operacionSacRequest);
			// Actualizar solicitud credito a estado SOL
			solicitudCreditoDao.actualizarEstadoSolicitudCredito(
					datosCredito.getSolicitudCredito().getSolicitudCreditoPK(), "SOL");
			//Actualizar la transaccion 
			final Prestamo pr = prestamoDao.load(prestamo.getCreditoPk());
			pr.setNumOperacionSAC(operacion.getOperacionSAC());
			pr.setEstadoSAC(operacion.getEstado());
			pr.setValsegsal(operacion.getValorSeguroSaldos().doubleValue());
			pr.setPrisegsal(BigDecimal.valueOf(operacion.getTasaSegSaldos()));
		    prestamoDao.update(pr);

		
		} catch (final ParseException e) {
			LOG.error("Error en formatear fecha", e);
		} catch (final CreacionOperacionSacException e) {
			LOG.error(e.getCodigo()+":Se ha producido un error al obtener la información, por favor notificar este mensaje al siguiente correo  ayudapq@biess.fin.ec", e);
		}
	}


	/**
	 * Fabricar cliente para crear la operacion
	 * 
	 * @param credito
	 * @return
	 * @throws ParseException
	 */
	private ClienteRequestDto fabricarCliente(final DatosCredito credito) throws ParseException {
		final ClienteRequestDto clienteRequestDto = FuncionesUtilesSac.fabricarCliente(credito);
		clienteRequestDto.setRentaNetaSueldo(credito.getCalculoCredito().getGarantia().getSueldoPromedioOriginal()
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		clienteRequestDto.setCapacidadEndeudamiento(credito.getCalculoCredito().getGarantia()
				.getCapacidadEndeudamiento().setScale(2, BigDecimal.ROUND_HALF_UP));
		clienteRequestDto.setParroquia(credito.getIdDivisionPolitica().substring(4, 6));
		return clienteRequestDto;

	}

	/**
	 * Arma a operacion en base a datos del prestamo
	 * 
	 * @param prestamo
	 * @return
	 * @throws ParseException
	 */
	private OperacionRequestDto fabricarOperacion(final Prestamo prestamo) {
		final OperacionRequestDto operacionRequestDto = new OperacionRequestDto();
		operacionRequestDto.setNut(prestamo.getNut());
		operacionRequestDto.setOrigenFondos(ConstantesSAC.ORIGEN_FONDOS_GENERICO);
		operacionRequestDto.setDestinoFondos(ConstantesSAC.OTROS_GASTOS_NO_ESPECIFICOS);
		operacionRequestDto
				.setTipoProducto( prestamo.getDestinoComercial().getCodProdPq());
		return operacionRequestDto;

	}
	

	/**
	 * Arma los datos para datos del prestamo
	 * 
	 * @param credito
	 * @return
	 * @throws ParseException
	 */
	private PrestamoRequestDto fabricarPrestamo(final DatosCredito credito) {
		final PrestamoRequestDto prestamoRequestDto = new PrestamoRequestDto();
		prestamoRequestDto.setMonto(credito.getPrestamoCalculo().getMontoPrestamo());
		prestamoRequestDto.setPlazo((credito.getCreditoEspecial()!=null && credito.getCreditoEspecial()==1l)?credito.getPlazo()+3:credito.getPlazo());
		prestamoRequestDto.setTipoTablaAmortizacion(FuncionesUtilesSac.obtenerTipoTablaSac(credito.getTipoTabla()));
		prestamoRequestDto.setFechaPrimerVencimiento(ConstantesSAC.FECHA_VENCIMIENTO_FINAL_MES);
		return prestamoRequestDto;

	}

	/**
	 * Fabrica los datos demograficos, se toman los datos del empleador
	 * 
	 * @return
	 */
	private DatoDemograficoDto fabricarDatoDemografico(final DatosCredito credito) {
		final SucursalDto sucursal = proveedorServicio.obtenerDatosSucursal(credito.getEmpleador().getRucEmpleador(),
				credito.getEmpleador().getCodigoSucursal());
		final DatoDemograficoDto datoDemograficoDto = new DatoDemograficoDto();
		datoDemograficoDto.setCodProvincia(Integer.parseInt(sucursal.getCodDivisionPolitica().substring(0, 2)));
		datoDemograficoDto.setCodCanton(Integer.parseInt(sucursal.getCodDivisionPolitica().substring(2, 4)));
		return datoDemograficoDto;
	}

	/**
	 * Fabrica los datos de captacion
	 * 
	 * @return
	 */
	private DatoCaptacionDto fabricarDatoCaptacion() {
		final DatoCaptacionDto datoCaptacionDto = new DatoCaptacionDto();
		datoCaptacionDto.setNumeroAgencia(ConstantesSAC.NUMERO_AGENCIA);
		datoCaptacionDto.setNumeroZona(ConstantesSAC.NUMERO_ZONA);
		return datoCaptacionDto;
	}

	/**
	 * Fabrica los datos de desembolso
	 * 
	 * @param credito
	 * @return
	 * @throws SbsCatalogoExcepcion
	 */
	private DatoDesembolsoDto fabricarDatoDesembolso(final DatosCredito credito) {
		final DatoDesembolsoDto datoDesembolso = new DatoDesembolsoDto();
		datoDesembolso.setCodigoBanco(credito.getInstitucionFinanciera().getInstitucionId());
		datoDesembolso.setCodigoForma(ConstantesSAC.NOTA_CREDITO);
		datoDesembolso.setNumeroCuenta(credito.getInstitucionFinanciera().getNumeroCuenta());
		datoDesembolso.setTipoCuenta(
				FuncionesUtilesSac.obtenerTipoCuentaSac(credito.getInstitucionFinanciera().getTipoCuentaId()));
		return datoDesembolso;
	}

	@Override
	public boolean tieneCreditosSubgarantizados(final String identificacion) {
		final GarantiaCesantia garantiaCesantia = prestamoQuirografarioServicio.consultarCesantiaCliente(identificacion);
		boolean tieneCreditosSubgarantizados = false;
		if (garantiaCesantia != null && garantiaCesantia.getTotalCesantia() != null
				&& garantiaCesantia.getTotalCesantia().signum() == -1) {
			tieneCreditosSubgarantizados = true;
		}

		return tieneCreditosSubgarantizados;
	}

	@Override
	public BigDecimal totalGarantiaCliente(final String identificacion) {
		final GarantiaCesantia garantiaCesantia = prestamoQuirografarioServicio.consultarCesantiaCliente(identificacion);
		BigDecimal valorTotalCesantia = BigDecimal.ZERO;

		if (garantiaCesantia != null && garantiaCesantia.getValorHistoriaLaboral() != null) {
			valorTotalCesantia = garantiaCesantia.getValorHistoriaLaboral();
		}
		return prestamoQuirografarioServicio.consultarFondosReservaTotales(identificacion).add(valorTotalCesantia);
	}

	@Override
	public boolean existeUnCreditoCastigadoPh(String cedula) {
		return   nutTataBiessDao
				.existeUnCreditoCastigado(cedula);
	}
	//REQ-617 9-23-21
	@SuppressWarnings("deprecation")
	 public Prestamo buscarCreditoAnterior(Prestamo prestamoActual)  {
		if(prestamoActual.getNumprecannov()==null) {
			return null;
		}else {
		System.out.println("bucarCreditoAnterior() - numprecannov: " + prestamoActual.getNumprecannov() );
		PrestamoPk prestamoPk = null;
		String numprecannov = prestamoActual.getNumprecannov().toString();
		try {
			String ordpreafi = numprecannov.substring(1, 2);
			String codepretip = numprecannov.substring(0 , 1);		
					
			String codprecla = numprecannov.substring(2, 4);
			String numpreafi = numprecannov.substring(4, numprecannov.length());
			
			// BUSCAR EL CREDITO ANTERIOR
			prestamoPk = new PrestamoPk(); 
			try {
				prestamoPk.setCodprecla(Long.valueOf(codprecla));
				prestamoPk.setCodpretip(Long.valueOf(codepretip));
				prestamoPk.setNumpreafi(Long.valueOf(numpreafi));
				prestamoPk.setOrdpreafi(Long.valueOf(ordpreafi));
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				System.out.println(
						"Error al obtener los valores de la clave primaria del credito anterior en base al NUMPRECANNOV: "
								+ numprecannov);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			System.out.println(
					"Error al obtener los valores de la clave primaria del credito anterior en base al NUMPRECANNOV: "
							+ numprecannov);
		}
		Prestamo prestamoNovacion = this.prestamoDao.buscarPorPk(prestamoPk);
		 Calendar cal = Calendar.getInstance();
         Calendar fechaPrestamo = Calendar.getInstance();
         fechaPrestamo.setTime(prestamoNovacion.getFectracre());
         
		if(fechaPrestamo.get(Calendar.YEAR) == cal.get(Calendar.YEAR)) {
		return prestamoNovacion;
		}else {
			return null;
		
			}
		}
	}
//fin Req-617	
	

}