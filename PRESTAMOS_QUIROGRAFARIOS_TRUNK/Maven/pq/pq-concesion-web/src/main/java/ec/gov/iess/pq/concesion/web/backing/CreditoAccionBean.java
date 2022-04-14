package ec.gov.iess.pq.concesion.web.backing;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.model.selection.Selection;

import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.alerts.modelo.InformacionAfiliado;
import ec.fin.biess.creditos.pq.alertas.util.AlertUtil;
import ec.fin.biess.creditos.pq.enumeracion.ConfiguracionPQEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoEmpleadorEnum;
import ec.fin.biess.creditos.pq.excepcion.ConsultaParametroException;
import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiess;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiessPK;
import ec.fin.biess.creditos.pq.servicio.ParametroCreditoServicio;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.commons.mail.MailUtil;
import ec.gov.iess.creditos.comprobante.dto.DatoComprobante;
import ec.gov.iess.creditos.dao.ComprobantePagoDao;
import ec.gov.iess.creditos.dao.ITransaccionRecaudacionDao;
import ec.gov.iess.creditos.enumeracion.EnvioMail;
import ec.gov.iess.creditos.enumeracion.EstadoComprobantePago;
import ec.gov.iess.creditos.enumeracion.EstadoLiquidacion;
import ec.gov.iess.creditos.enumeracion.EstadoPrestamoEnum;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.CalculoValorRealFondosExcepcion;
import ec.gov.iess.creditos.excepcion.DebitoAutomaticoExcepcion;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoIndividualExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionAnticipadaExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionFondosReservaExcepcion;
import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.modelo.dto.CalculoLiquidacion;
import ec.gov.iess.creditos.modelo.dto.DatosSituacionPrestamo;
import ec.gov.iess.creditos.modelo.dto.RequisitoNovacion;
import ec.gov.iess.creditos.modelo.dto.RequisitosCruce;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosComprobante;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosRecaudacion;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePagoDetalle;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.SaldoLiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.TipoComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.TransaccionRecaudacion;
import ec.gov.iess.creditos.modelo.persistencia.pk.ComprobantePagoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.TransaccionRecaudacionPk;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.DatoDeudaDto;
import ec.gov.iess.creditos.pq.dto.DividendoDto;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.EntidadNoEncontradaException;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.GenerarComprobanteIndividualExcepcion;
import ec.gov.iess.creditos.pq.excepcion.NoTieneComprobanteVigenteException;
import ec.gov.iess.creditos.pq.excepcion.NoTieneLiquidacionVigenteException;
import ec.gov.iess.creditos.pq.excepcion.SimulacionCancelacionSacException;
import ec.gov.iess.creditos.pq.excepcion.SimulacionValorExigibleException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaFechaEfectivaGafLocal;
import ec.gov.iess.creditos.pq.servicio.ConsultaParametroServicioLocal;
import ec.gov.iess.creditos.pq.servicio.DefinirRolServicioLocal;
import ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.LiquidacionServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.SimulacionCancelacionSacServicio;
import ec.gov.iess.creditos.pq.servicio.SimulacionValorExigibleSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.TablaAmortizacionOperacionSacServicioLocal;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.EstadosCredito;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;
import ec.gov.iess.creditos.pq.util.TiposCredito;
import ec.gov.iess.creditos.pq.util.Utilities;
import ec.gov.iess.hl.exception.NoTieneRelacionDeDependenciaException;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.exception.GeneracionComprobanteExcepcion;
import ec.gov.iess.pq.concesion.web.exception.PagIndException;
import ec.gov.iess.pq.concesion.web.util.BaseBean;

/**
 * Backing bean para la pagina de cosulta y adm. de creditos
 * 
 * @author Daniel Cardenas
 * 
 */
public class CreditoAccionBean extends BaseBean {   

	private transient static final LoggerBiess LOG = LoggerBiess
			.getLogger(CreditoAccionBean.class);

	@EJB(name = "LiquidacionServicioImpl/local")
	private LiquidacionServicio liquidacionServicio;

	@EJB(name = "ComprobantePagoServicioImpl/local")
	private ComprobantePagoServicio comprobantePagoServicio;

	@EJB(name = "DividendoPrestamoServicioImpl/local")
	private DividendoPrestamoServicio dividendoPrestamoServicio;

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;
	
	@EJB(name = "AlertUserHelperImpl/local")
	private AlertUserHelper alertUserHelper;
	
	@EJB(name = "DefinirRolServicioImpl/local")
	private DefinirRolServicioLocal definirRolServicio;
	
	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;
	
	@EJB(name = "SimulacionCancelacionSacServicioImpl/local")
	private SimulacionCancelacionSacServicio simulacionCancelacionSacServicio;

	@EJB(name = "TransaccionRecaudacionDaoImpl/local")
	private ITransaccionRecaudacionDao transaccionRecaudacionDao;

	@EJB(name = "SimulacionValorExigibleSacServicioImpl/local")
	private SimulacionValorExigibleSacServicioLocal simularValorExigibleSacServicio;

	@EJB(name = "ComprobantePagoDaoImpl/local")
	private ComprobantePagoDao comprobantePagoAfiliadoDao;

	@EJB(name = "TablaAmortizacionOperacionSacServicioImpl/local")
	private TablaAmortizacionOperacionSacServicioLocal tablaAmortizacionOperacionSacServicio;
	
	@EJB(name = "ConsultaFechaEfectivaGafImpl/local")
	private ConsultaFechaEfectivaGafLocal consultaFechaEfectivaGafLocal;
	
	@EJB(name = "ParametroCreditoServicioImpl/local")
	private transient ParametroCreditoServicio parametroCreditoServicio;

	@EJB(name="ConsultaParametroServicioImpl/local")
	private ConsultaParametroServicioLocal consultaParametroServicio;
	
	private boolean habilitarLiquidarFondos = false;
	private boolean habilitarRequisitosCruce = true;
	private boolean habilitarCalcularLiq = false;
	private String msjCalcularLiq = null;
	private boolean habilitarCalcularLiqFondos = false;
	private String msjCalcularLiqFondos = null;
	private boolean habilitarGenerarComp = false;
	private String msjGenerarComp = null;
	private boolean habilitarVerDetalle = false;

	private boolean habilitarConsultaLiq = false;
	private boolean habilitarConsultaGC = false;

	private Prestamo prestamoSeleccionado;
	private Selection selectedItem;
	private boolean validarMontoCancelar = false;
	
	private String msjDiaHabilCompInd = null;
	private String msjDiaHabilLiqAnt = null;
	private String msjDiaHabilCruCue = null;

	private Prestamo prestamoSeleccionadoNovacion;

	private String msjError = null;

	private BigDecimal valorRealFondos;

	// para liquidaciones
	private CalculoLiquidacion calculoLiquidacion;
	private Long numeroLiquidacion;
	private LiquidacionPrestamo liquidacionPrestamo;
	private ComprobantePago comprobantePago;
	private List<RequisitosCruce> requisitosCruceCuentas;

	// para pagind
	private List<DividendoPrestamo> dividendosPorPagar;
	private List<SaldoLiquidacionPrestamo> saldosLiq;
	private List<ComprobantePago> comprobantesDePago;
	private String msjErrorPagInd = null;
	private boolean pagindGenerado = false;
	// migracion cartera inicio
	private boolean esConsultaComprobante;
	// dato deuda dividendo
	private List<DatoDeudaDto> listaDividendosSac;
	private List<DatoDeudaDto> lstDivPersonalizadoSac;

	// manejo los estados de seleccion
	private Map<DatoDeudaDto, Boolean> dividendosSacCheck = new LinkedHashMap<DatoDeudaDto, Boolean>();
	private boolean habilitaSeleccionDividendo;
	private BigDecimal totalDividendos = BigDecimal.ZERO;
	private BigDecimal totalMoraDividendos = BigDecimal.ZERO;
	private boolean esMontoPersonalizado;
	private BigDecimal montoTotalDividendos = BigDecimal.ZERO;
	private boolean mostrarResultSimulacion;
	private BigDecimal montoMaximo;
	private boolean accionGenCompIndivActivo;
	private String tipoTicketPersonal="PAC";
	// migracion cartera fin
	private final ValidarRequisitosRecaudacion validarRecaudacion = new ValidarRequisitosRecaudacion();
	private final List<EstadoComprobantePago> estadosLiquidar = new ArrayList<EstadoComprobantePago>();
	private final ValidarRequisitosComprobante validarComprobante = new ValidarRequisitosComprobante();
	private final DatosSituacionPrestamo situacionPrestamo = new DatosSituacionPrestamo();
	private BigDecimal numeroDiasMora = BigDecimal.ZERO;

	private DatosBean datos;
	private TipoProductosBean productos;
	
	// INC-2129 Control en Generacion de Comprobantes.
	private boolean desplegarMensajeComprobanteImpago = false;
	
	private boolean todos;
		
	private SaldoLiquidacionPrestamo saldoLiquidacionPrestamoSeleccionado;
	
	private TipoPrestamista tipoPrestamista = null;
	
	private CreditoExigibleDto datosLiquidacion;
	
	private BigDecimal sumaValoresSeleccionados = BigDecimal.ZERO;

	private BigDecimal valorPersonalizado = BigDecimal.ZERO;

	List<DatoDeudaDto> listaDatoDeudaDto = new ArrayList<DatoDeudaDto>();
	private String msjConsultaGC = null;

	private boolean muestraDetalleNov;
	
	private   List<RequisitoNovacion> requisitosNova;
	
	private boolean genCompIndivSaldebActivo;
	
	private double diasMoraDebAut;
	/**
	 * CODIGO DE ERROR DE GAF nose permite APZ, ya no existen cuotas donde distribuir el Flujo
	 */
	private static final String COD_ERR_GAF_CUOT_SIN_DISTRI = "GAF0509";

	/**
	 * Mensaje generacion comprobante individual.
	 */
	private static final String MSG_VALORES_PENDIENTES = "Estimado cliente al momento no tiene valores pendientes de pago, por favor verificar la opci\u00F3n \"Ver Detalle\".";
	
	private static final String PORCENTAJE_NOVACION_CANCELADA = "PQW_CON_PORNOVAPAG";
	/**
	 * para validar si es saldeb
	 */
	private boolean esSaldeb;
	/**
	 * Porcentaje novacion
	 */
	private String porcentajeNovacion;
	
	private String periodoComprobanteCesante;
	private String periodoComprobanteVoluntario;
	private String periodoComprobanteUnipersonal;
	private String periodoComprobanteJubilado;
	private String periodoComprobanteAfiliado;
	
	private StringBuilder tipoComprobanteSB;
	
	private String vencimientoComprobante;
	//REQ 444 N1
		private boolean habilitaMensajeDebito;
		private boolean panelMensajes;
		private boolean banDeb=false;
	
	String paramsAfiliado = null;
	String paramsJubilado = null;
	
		private List<String> obtenerEstComprobante() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_GEN);
		estados.add("DEP");
		return estados;
	}
	@PostConstruct
	public void init() {
		totalDividendos = BigDecimal.ZERO;
		totalMoraDividendos = BigDecimal.ZERO;
		dividendosSacCheck.clear();
		montoTotalDividendos = BigDecimal.ZERO;
		esMontoPersonalizado = Boolean.FALSE;
		try {
			this.montoMaximo = parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, "MONTO_MAXIMO_LIQUIDAR").getValorNumerico();
		
			diasMoraDebAut=	parametroCreditoServicio.obtenerParametroPQMontosMaximos("DIASMORA").doubleValue();
		} catch (final ParametroBiessException e) {
			LOG.error("Error al obtener monto maximo de liquidacion", e);
	} catch (final ParametroCreditoException e) {
		LOG.error("Error al obtener Parametro Credito Dias Mora", e);
		}
	}
	public String seleccionarCancelado() {

		// verifica si puede liquidar
		LOG.debug("getNumpreafi:" + prestamoSeleccionado.getCreditoPk().getNumpreafi());
		LOG.debug("getCodprecla:" + prestamoSeleccionado.getCreditoPk().getCodprecla());
		LOG.debug("getCodpretip:" + prestamoSeleccionado.getCreditoPk().getCodpretip());
		LOG.debug("getOrdpreafi:" + prestamoSeleccionado.getCreditoPk().getOrdpreafi());

		// Carga datos en objeto ValidarRequisitosRecaudacion
		validarRecaudacion.setPrestamo(prestamoSeleccionado);

		return null;

	}
	/**
	 * Permite habilitar todas las opciones una vez seleccionado el credito
	 */
	public void seleccionarCredito() {
		prestamoSeleccionadoNovacion = null;
		resetear();
		habilitarOpciones();
	}

	/**
	 * Permite habilitar las opciones de recaudacion
	 */
	private void habilitarOpciones() {
		habilitarVerDetalle = true;
		habilitarCalcularLiq = true;
		habilitarCalcularLiqFondos = true;
		habilitarGenerarComp = true;
		habilitarConsultaGC = true;
	}
/**
	 * Permite buscar el credito seleccionado
	 */
	private void setearPrestamoSeleccionado() {
		final PrestamoBiess prestamo = (PrestamoBiess) selectedItem.getKeys().next();
		prestamoSeleccionado = prestamoServicio.getPrestamoByNut(prestamo.getNut());
		prestamoSeleccionado.setDiasMora(prestamo.getDiasMora());
		prestamoSeleccionado.setFecinipre(prestamo.getFecpreafi());
		prestamoSeleccionado.setFecfinpre(prestamo.getFechaFinPrestamo());
		prestamoSeleccionado.setMntpre(prestamo.getValsalcap());

		// Carga datos en objeto ValidarRequisitosRecaudacion
		validarRecaudacion.setPrestamo(prestamoSeleccionado);
		validarRecaudacion.setEstadoCredito(EstadosCredito.ESTADO_VIGENTE);
		validarRecaudacion.setEstadoComprobante(obtenerEstComprobante());
		validarRecaudacion.setTipoCredito(obtenerTiposCreditoValidacion());
		// Se determina el rol si es voluntario o cesante
		try {
//			tipoPrestamista = definirRolServicio.determinaVoluntarioCesante(datos.getSolicitante().getDatosPersonales().getCedula() , datos.getRolPrestamista());
			tipoPrestamista = definirRolServicio.determinarTipoPrestamistaCesanteVoluntarioDobleRol(datos.getSolicitante().getDatosPersonales().getCedula() , datos.getRolPrestamista());
		} catch (final ServicioPrestadoException e) {
			msjCalcularLiqFondos = e.getMessage();
		} catch (final NoTieneRelacionDeDependenciaException e) {
			msjCalcularLiqFondos = e.getMessage();
		}
	}

	/**
	 * Permite validar las condiciones para habilitar la Liquidacion de un prestamo
	 * 
	 * @throws GeneracionComprobanteExcepcion
	 */
	private void validarCondicionesPrestamo() throws TablaAmortizacionSacException, GeneracionComprobanteExcepcion {
		// debe tener por lo menos dos dividendos en estado REG.
		final OperacionSacRequest operacionSacRequest = new OperacionSacRequest();
		final ClienteRequestDto cliente = new ClienteRequestDto();
		cliente.setTipoCliente(FuncionesUtilesSac.obtenerTipoPrestamista(datos.getTipo()));
		operacionSacRequest.setCliente(cliente);
		final OperacionRequestDto operacion = new OperacionRequestDto();
		operacion.setNut(prestamoSeleccionado.getNut());
		operacion.setNumero(prestamoSeleccionado.getNumOperacionSAC());
		operacion.setTipoProducto(prestamoSeleccionado.getDestinoComercial().getCodProdPq());
		operacionSacRequest.setOperacion(operacion);
		final List<DividendoDto> listaDividendos = tablaAmortizacionOperacionSacServicio
				.obtenerTablaAmortizacionOperacionSac(operacionSacRequest);
		int dividendosReg = 0;
		for (final DividendoDto dividendoDto : listaDividendos) {
			if (EstadoPrestamoEnum.PND.name().equals(dividendoDto.getEstado())) {
				dividendosReg++;
		   }
		}
		if (dividendosReg < 2) {
			throw new GeneracionComprobanteExcepcion("El pr\u00E9stamo debe tener por lo menos 2 dividendos pendientes");
			}
			
	
		// No deben existir comprobantes de pago del tipo PAGIND en estado GEN o DEP
		final Long comprobantes = comprobantePagoAfiliadoDao.contarPorPrestamoTipoYEstado(
				validarRecaudacion.getPrestamo().getCreditoPk(), "PAGIND",
				validarRecaudacion.getEstadoComprobantePago());
		if (comprobantes.longValue() > 0) {
			throw new GeneracionComprobanteExcepcion(
					"El pr\u00E9stamo tiene comprobantes de pago generados, en caso de requerir su anulaci\u00F3n favor acercarse a las oficinas de servicio al clientes del BIESS, el cr\u00E9dito no se puede liquidar.");
				}
				
		if (!"VIG".equals(prestamoSeleccionado.getEstadoPrestamo().getCodestpre())) {
			throw new GeneracionComprobanteExcepcion(
					"El pr\u00E9stamo debe estar en estado VIGENTE");
			}
		}


	/**
	 * Permite vaidar habilitacion de componente dependiendo cliente
	 * 
	 * @param documento
	 * @param tipoComponente
	 * @return
	 * @throws PeriodoComprobanteException
	 * @throws ConsultaParametroException 
	 */
	private boolean esAfiliadoJubilado() throws  ConsultaParametroException {
		boolean continuaFlujo = true;
		final Calendar fechaGeneracion = Calendar.getInstance();
		// Se determina que tipo de empleador es
		String tipoEmpleador=null;
		tipoEmpleador = obtenerTipoEmpleador();

		if ((!TipoPrestamista.CESANTE.equals(tipoPrestamista)
				 && !TipoPrestamista.JUBILADO.equals(datos.getRolPrestamista()))) {
			if (TipoPrestamista.VOLUNTARIO.equals(tipoPrestamista)) {
				continuaFlujo = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPVO"));				
			} 	
			else if (TipoPrestamista.VOLUNTARIO_AFILIADO.equals(tipoPrestamista)) {
				continuaFlujo = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPVO"));					
				boolean continuaFlujoAux = true;
				continuaFlujoAux = this.comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPAF"));
				continuaFlujo =(continuaFlujo && continuaFlujoAux);												
			}
			else if (TipoPrestamista.VOLUNTARIO_JUBILADO.equals(tipoPrestamista)) {
				continuaFlujo = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPVO"));					
				boolean continuaFlujoAux = true;
				continuaFlujoAux = this.comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPJU"));
				continuaFlujo =(continuaFlujo && continuaFlujoAux);												
			}
			else if (TipoPrestamista.UNIPERSONAL.equals(tipoPrestamista)) {
				continuaFlujo = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPUN"));
			}
			else if (TipoPrestamista.UNIPERSONAL_AFILIADO.equals(tipoPrestamista)) {
				continuaFlujo = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPUN"));					
				boolean continuaFlujoAux = true;
				continuaFlujoAux = this.comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPAF"));
				continuaFlujo =(continuaFlujo && continuaFlujoAux);												
			}
			else if (TipoPrestamista.UNIPERSONAL_JUBILADO.equals(tipoPrestamista)) {
				continuaFlujo = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPUN"));					
				boolean continuaFlujoAux = true;
				continuaFlujoAux = this.comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPJU"));
				continuaFlujo =(continuaFlujo && continuaFlujoAux);												
			}			
			else if (TipoPrestamista.AFILIADO_JUBILADO.equals(tipoPrestamista)) {
				continuaFlujo = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPAF"));
				boolean continuaFlujoAux = true;
				continuaFlujoAux = this.comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPJU"));
				continuaFlujo =(continuaFlujo && continuaFlujoAux);
			} else if (TipoEmpleadorEnum.PRI.getCodigo().equals(tipoEmpleador)) {
				continuaFlujo = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPAF"));
			} 
			
		} else {
			if (TipoPrestamista.JUBILADO.equals(tipoPrestamista) || TipoEmpleadorEnum.JUB.getCodigo().equals(tipoEmpleador)) {
				continuaFlujo = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPJU"));
			}else if (TipoPrestamista.CESANTE.equals(tipoPrestamista)) {
				continuaFlujo = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
						this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPCE"));				
			}  
		}
		return continuaFlujo;			
	}
	

	
	private String obtenerTipoEmpleador() {
		
		if(TipoPrestamista.AFILIADO.equals(tipoPrestamista) || TipoPrestamista.ZAFRERO_TEMPORAL.equals(tipoPrestamista) ) {
			return TipoEmpleadorEnum.PRI.getCodigo();
		}else if(TipoPrestamista.AFILIADO_JUBILADO.equals(tipoPrestamista) || (TipoPrestamista.CESANTE.equals(tipoPrestamista)
				 && TipoPrestamista.JUBILADO.equals(datos.getRolPrestamista()))) {
			return TipoEmpleadorEnum.JUB.getCodigo();
			
		}else {
			return null;
		}
		
	}
	
	private List<String> obtenerDividendosMora() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_MOR);
		return estados;
	}

	private List<String> obtenerDividendosEco() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_ECO);
		return estados;
	}

	private List<String> obtenerDividendosEpl() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_EPL);
		return estados;
	}

	private List<String> obtenerDividendosReg() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_REG);
		return estados;
	}

	private List<String> obtenerDivComprobante() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_ECP);
		estados.add(EstadosCredito.ESTADO_DIV_GEN);
		estados.add(EstadosCredito.ESTADO_DIV_MOR);
		estados.add(EstadosCredito.ESTADO_DIV_TGL);
		return estados;
	}

	private List<String> obtenerTiposSolicitudAfi() {
		final List<String> tipos = new ArrayList<String>();
		tipos.add(TiposCredito.TIPO_SOLICITUD_AFI_AFL);
		tipos.add(TiposCredito.TIPO_SOLICITUD_AFI_FAL);
		return tipos;
	}

	private List<String> obtenerEstadosSolicitudAfi() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_FONDOS_AFT);
		estados.add(EstadosCredito.ESTADO_FONDOS_REG);
		estados.add(EstadosCredito.ESTADO_FONDOS_TRA);
		return estados;
	}

	/**
	 * Carlos Bastidas: INC-6047 se agrega estados de solicitud en tramite por
	 * fondos de reserva"
	 */
	private List<String> obtenerEstadosSolicitudTramite() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_FONDOS_TRA_ANU);
		estados.add(EstadosCredito.ESTADO_FONDOS_TRA_PAG);
		estados.add(EstadosCredito.ESTADO_FONDOS_TRA_NPA);
		return estados;
	}

	private List<String> obtenerEstadoCargoReg() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_CARGO_REG);
		return estados;
	}

	private List<String> obtenerEstadoCargoPro() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_CARGO_PRO);
		return estados;
	}

	private List<String> obtenerEstadoBloqueado() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_BLOQUEADO);
		return estados;
	}

	private List<String> obtenerTipoAportes() {
		final List<String> tipos = new ArrayList<String>();
		tipos.add(TiposCredito.TIPO_APORTE_BAC);
		tipos.add(TiposCredito.TIPO_APORTE_PAG);
		tipos.add(TiposCredito.TIPO_APORTE_EXC);
		tipos.add(TiposCredito.TIPO_APORTE_CAR);
		tipos.add(TiposCredito.TIPO_APORTE_AJS);
		tipos.add(TiposCredito.TIPO_APORTE_REL);
		return tipos;
	}
	
	/**
	 * Cuando selecciona un prestamo
	 * 
	 * @return
	 */
	public String seleccionar() {
		/* Validar si existe un credito seleccionado */
		if (null == selectedItem || 0 == selectedItem.size()) {
			return "";
		}
		prestamoSeleccionadoNovacion = null;
		resetear();
		// siempre se habilita ver detalle
		habilitarVerDetalle = true;

		/* Obtener prestamo seleccionado */		
		final PrestamoBiessPK pbpk = (PrestamoBiessPK)selectedItem.getKeys().next();
		final PrestamoPk pk = new PrestamoPk(pbpk.getCodprecla(), pbpk.getCodpretip(), pbpk.getNumpreafi(), pbpk.getOrdpreafi());
		prestamoSeleccionado = prestamoServicio.bucarPrestamoPk(pk);

		// verifica si puede liquidar
		LOG.debug("getNumpreafi:"
				+ prestamoSeleccionado.getCreditoPk().getNumpreafi());
		LOG.debug("getCodprecla:"
				+ prestamoSeleccionado.getCreditoPk().getCodprecla());
		LOG.debug("getCodpretip:"
				+ prestamoSeleccionado.getCreditoPk().getCodpretip());
		LOG.debug("getOrdpreafi:"
				+ prestamoSeleccionado.getCreditoPk().getOrdpreafi());

		// Carga datos en objeto ValidarRequisitosRecaudacion
		validarRecaudacion.setPrestamo(prestamoSeleccionado);
		validarRecaudacion.setEstadoCredito(EstadosCredito.ESTADO_VIGENTE);
		validarRecaudacion.setDividendosMora(obtenerDividendosMora());
		validarRecaudacion.setDividendosEco(obtenerDividendosEco());
		validarRecaudacion.setDividendosEpl(obtenerDividendosEpl());
		validarRecaudacion.setEstadoComprobante(obtenerDivComprobante());
		validarRecaudacion.setTipoCredito(obtenerTiposCreditoValidacion());
		validarRecaudacion.setDividendosReg(obtenerDividendosReg());
		validarRecaudacion
				.setEstadoSolicitudFondos(obtenerEstadosSolicitudAfi());
		validarRecaudacion.setTipoSolicitudFondos(obtenerTiposSolicitudAfi());
		validarRecaudacion.setEstadoCargoReg(obtenerEstadoCargoReg());
		validarRecaudacion.setEstadoCargoPro(obtenerEstadoCargoPro());
		validarRecaudacion.setEstadoBloqueado(obtenerEstadoBloqueado());
		validarRecaudacion.setTipoAporte(obtenerTipoAportes());
		/**
		 * Carlos Bastidas: INC-6047 se agrega estados de solicitud en tramite
		 * por fondos de reserva"
		 */
		validarRecaudacion
				.setEstadoSolicitudFondosTramite(obtenerEstadosSolicitudTramite());

		situacionPrestamo.setPrestamoPk(prestamoSeleccionado.getPrestamoPk());
		situacionPrestamo.setEstadosCreVigentes(obtenerEstadosCreditoVig());
		situacionPrestamo.setEstadosCreLiquidacion(obtenerEstadosCreditoLiq());
		situacionPrestamo.setEstadosDivPorPagar(obtenerEstadosDivPorPagar());
		situacionPrestamo.setEstadosDivMora(obtenerEstadosDivMora());
		situacionPrestamo.setEstadosDivSaldoLiq(obtenerEstadosSaldoLiq());
		situacionPrestamo.setTipoCredito(obtenerTiposSituacionCredito());
		situacionPrestamo.setTipoEmpleador(null);
		
		validarComprobante.setEstadosComprobante(obtenerEstadosComprobante());
		validarComprobante.setEstadosPrestamo(obtenerEstadosCreComprobante());
		validarComprobante.setPrestamoPk(prestamoSeleccionado.getPrestamoPk());
		validarComprobante.setTiposComprobante(obtenerTiposComprobante());
		
		// Se determina el rol si es voluntario o cesante
		try {
			tipoPrestamista = definirRolServicio.determinaVoluntarioCesante(datos.getSolicitante().getDatosPersonales().getCedula() , datos.getRolPrestamista());
		} catch (final ServicioPrestadoException e) {
			msjCalcularLiqFondos = e.getMessage();
		} catch (final NoTieneRelacionDeDependenciaException e) {
			msjCalcularLiqFondos = e.getMessage();
		}
		
		final Calendar fechaGeneracion = Calendar.getInstance();
		final Calendar fechaValidez = determinaEmpleadorFechaGeneracion(fechaGeneracion.getTime(), 
				fechaGeneracion.getTime(), fechaGeneracion);
		
		situacionPrestamo.setFechaValidezLiquidacion(fechaValidez);
		situacionPrestamo.setFechaValidezComprobante(fechaValidez);
		//Validacion de dividendos en mora con mora mayor a 90 dias
		try
		{
		   numeroDiasMora = dividendoPrestamoServicio.obtenerDiasMora(prestamoSeleccionado.getPrestamoPk());
		   if (numeroDiasMora == null)
		   {
			   numeroDiasMora = BigDecimal.ZERO;
		   }
		}catch (final Exception e) {
			msjError = "ERROR AL CONSULTAR DIAS DE MORA DEL CREDITO";
			return "consultaCreditos";
		}

		try {
			if (numeroDiasMora.doubleValue() > diasMoraDebAut) {
				if (prestamoSeleccionado.getPrestamoPk().getCodprecla() == 20 || prestamoSeleccionado.getPrestamoPk().getCodprecla() == 22) {
					throw new LiquidacionAnticipadaExcepcion(
							"EXISTEN DIVIDENDOS CON MORA MAYOR A"+ diasMoraDebAut +" D\u00CDAS SUS GARANT\u00CDAS SER\u00C1N EJECUTADAS");
				}
			}
			habilitarCalcularLiq = liquidacionServicio.esPosibleLiquidacion(validarRecaudacion);
 
		}catch (final LiquidacionAnticipadaExcepcion e) {
			msjCalcularLiq = e.getMessage();
		}

		try {
			
			habilitarCalcularLiqFondos = liquidacionServicio
					.esPosibleLiquidacionFondos(validarRecaudacion);
		} catch (final LiquidacionFondosReservaExcepcion e) {
			msjCalcularLiqFondos = e.getMessage();
		}

		// verifica si puede generar comprobante
		try {
			if (numeroDiasMora.doubleValue() > diasMoraDebAut)
			{
				if (prestamoSeleccionado.getPrestamoPk().getCodprecla() == 20 || prestamoSeleccionado.getPrestamoPk().getCodprecla() == 22)
				{
					throw new GenerarComprobanteIndividualExcepcion("EXISTEN DIVIDENDOS CON MORA MAYOR A "+diasMoraDebAut+" D\u00CDAS SUS GARANT\u00CDAS SER\u00C1N EJECUTADAS");
				}
			}
			habilitarGenerarComp = liquidacionServicio
					.esPosibleGenCompIndividual(situacionPrestamo);

		} catch (final GenerarComprobanteIndividualExcepcion e) {
			msjGenerarComp = e.getMessage();
		}

		try {
			numeroLiquidacion = liquidacionServicio
					.obtenerLiquidacionVigente(prestamoSeleccionado
							.getPrestamoPk());
			habilitarConsultaLiq = true;
		} catch (final NoTieneLiquidacionVigenteException e) {
			habilitarConsultaLiq = false;
		}

		habilitarConsultaGC = comprobantePagoServicio
				.existeComprobanteIndividualVigente(validarComprobante);

		return null;
	}
	

	/**
	 * Indica si se debe calificar al empleador (MIN, PRI, PUB, JUB) de acuerdo a la fecha de generacion de comprobante
	 * (COMP, LIQ)
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @param fechaGeneracion
	 * @return Calendar
	 */
	private Calendar determinaEmpleadorFechaGeneracion(final Date fechaInicio, final Date fechaFin, final Calendar fechaGeneracion) {
		Calendar fechaValidezComprobante = null;
		final int diasValidez = Utilities.calcurarNuemroDias(fechaFin, fechaInicio);
		fechaValidezComprobante = Utilities.agregarDiasFecha(fechaGeneracion, diasValidez);
		fechaValidezComprobante.set(Calendar.HOUR_OF_DAY, 23);
		fechaValidezComprobante.set(Calendar.MINUTE, 59);
		fechaValidezComprobante.set(Calendar.SECOND, 59);

		return fechaValidezComprobante;
	}

	public String seleccionarCreditoNovacion() {
		this.productos.setMensajeInformativo(null);
		prestamoSeleccionado = null;
		final Prestamo tmpprestamo = (Prestamo) getHttpServletRequest().getAttribute("item");
		prestamoSeleccionadoNovacion = prestamoServicio.getPrestamoByNut(tmpprestamo.getNut());
		prestamoSeleccionadoNovacion.setValsalcap(tmpprestamo.getValsalcap());
		prestamoSeleccionadoNovacion.setNumOperacionSAC(tmpprestamo.getNumOperacionSAC());
		try {
			prestamoSeleccionadoNovacion.setValliqnov(obtenerMontoPrecancelarNova(prestamoSeleccionadoNovacion));
		} catch (SimulacionCancelacionSacException e1) {
			LOG.error("Error al calcular la simulacion");
			this.productos.setMensajeInformativo(e1.getCodigo()+":"+e1.getMensaje());
			return "";
		} catch (ConsultaParametroException e) {
			LOG.error("Error al consultar la fecha efectiva del gaf");
			this.productos.setMensajeInformativo(e.getMessage());
			return "";
		}
		prestamoSeleccionadoNovacion.setDiasMora(tmpprestamo.getDiasMora());
		final LiquidacionPrestamo liquidacionPrestamo = liquidacionServicio.buscarLiquidacionPorNumeroTipoEstado(
				prestamoSeleccionadoNovacion.getNumliqprenov(), "LNV", EstadoLiquidacion.CAN);
		final Calendar fecCanPreCal = Calendar.getInstance();
		if (liquidacionPrestamo != null) {
			fecCanPreCal.setTime(liquidacionPrestamo.getFechaLiquidacion());
			fecCanPreCal.set(Calendar.HOUR, 0);
			fecCanPreCal.set(Calendar.MINUTE, 0);
			fecCanPreCal.set(Calendar.SECOND, 0);
			fecCanPreCal.set(Calendar.MILLISECOND, 0);
		}
		
		final Calendar fechaParametro = Calendar.getInstance();
		fechaParametro.setTime(this.obtenerFechaNovacionesJubilado());
		fechaParametro.set(Calendar.HOUR, 0);
		fechaParametro.set(Calendar.MINUTE, 0);
		fechaParametro.set(Calendar.SECOND, 0);
		fechaParametro.set(Calendar.MILLISECOND, 0);
		
		if ((TipoPrestamista.JUBILADO == this.datos.getTipo()) && (prestamoSeleccionadoNovacion.getPrestamoPk().getCodprecla().equals(20L))) {
			this.productos.setMensajeInformativo(super.getResource("messages", "mensaje.novar.prestamo.afiliado"));
			return "";
		} else {
			datos.setPrestamoNovacionSeleccionado(prestamoSeleccionadoNovacion);
			LOG.debug("getNumpreafi:"
					+ prestamoSeleccionadoNovacion.getCreditoPk()
							.getNumpreafi());
			LOG.debug("getCodprecla:"
					+ prestamoSeleccionadoNovacion.getCreditoPk()
							.getCodprecla());
			LOG.debug("getCodpretip:"
					+ prestamoSeleccionadoNovacion.getCreditoPk()
							.getCodpretip());
			LOG.debug("getOrdpreafi:"
					+ prestamoSeleccionadoNovacion.getCreditoPk()
							.getOrdpreafi());
			try {
				getHttpServletResponse().sendRedirect(
						getRequest().getContextPath()
								+ "/pages/novacion/datosSolicitante.jsf");
			} catch (final IOException e) {
				LOG.error("Falla de redireccion",e);
			}
		}
		return null;
	}
/**
	 * Permite mostrar el detalle de a novacion
	 */
	public void verDetalleCreditoNovacion() {
		 try {
			 porcentajeNovacion=(String) consultaParametroServicio.consultarParametro(PORCENTAJE_NOVACION_CANCELADA, "float");
		this.productos.setMensajeInformativo(null);
		prestamoSeleccionado = null;
		final Prestamo tmpprestamo = (Prestamo) getHttpServletRequest().getAttribute("item");
		prestamoSeleccionadoNovacion = tmpprestamo;
		muestraDetalleNov = Boolean.TRUE;
		 } catch (ConsultaParametroException e) {
			 LOG.error("Error al consultar el parametro de porcentaje de novacion",e);
		}
		
		
	
	}

	/**
	 * Carga los prerequisitos para realizar el Cruce de Cuentas con Fondos de
	 * Reserva
	 * 
	 * @return
	 */
	public String cargarRequisitoCruceCuentas() {
		
		if(validarRequisitosCruceCuentas()==null) {
			return "";
		}else if (!esValidoCruceCuentas()) {
			habilitarOpciones();
			return "";
		} 
		return validarRequisitosCruceCuentas();
	}

	/**
	 * Carga los prerequisitos para realizar el Cruce de Cuentas con Fondos de
	 * Reserva
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	private String validarRequisitosCruceCuentas() {

		final MailUtil mail = new MailUtil();
		int requisito5 = 0;
		int requisito6 = 0;	
		
		try {
			setearPrestamoSeleccionado();
			if (!esAfiliadoJubilado()) {
				msjCalcularLiqFondos="D\u00EDa no h\u00E1bil para generar Cruce de Cuentas. "
						+ "<br/>Nos encontramos generando las planillas para el "
						+ "descuento mensual de la(s) cuota(s) de su(s) pr\u00E9stamo(s) "
						+ "que se realiza(n) a trav\u00E9s de su empleador.";
				return null;
			}
			
			obtenerLiquidacionCredito(obtenerFechaActual(),"C+L");
			validarRecaudacion.setTipoPrestamista(datos.getTipo());
			validarRecaudacion.setCalculoLiquidacion(calculoLiquidacion);
			requisitosCruceCuentas = liquidacionServicio.cargarRequisitoCruceCuentas(validarRecaudacion);
			for (final RequisitosCruce rc : requisitosCruceCuentas) {
				if (!rc.isAprobado()) {
					habilitarRequisitosCruce = false;
					LOG.debug("id req" + rc.getId());

					if (rc.getId() == 5) {
						requisito5++;
						LOG.debug("NO cumple el requisito 5");
					}
					if (rc.getId() == 6) {
						requisito6++;
						LOG.debug("NO cumple el requisito 6");
					}
				}
			}

			if (requisito5 == 1 || requisito6 == 1) {
				final List<String> destinatarios = new ArrayList<String>();

				destinatarios.add(EnvioMail.FROM1.getValor());
				destinatarios.add(EnvioMail.FROM2.getValor());
				destinatarios.add(EnvioMail.FROM3.getValor());
				destinatarios.add(EnvioMail.FROM4.getValor());

				if (requisito5 == 1) {

					for (final String from : destinatarios) {
						mail.enviarMail(
								EnvioMail.ASUNTO.getValor(),
								EnvioMail.CUERPOFECHAINICIO.getValor()
										+ prestamoSeleccionado.getNumafi()
										+ ". "
										+ EnvioMail.COMENTARIO.getValor()
										+ ".\n\n"
										+ EnvioMail.PIEMAIL.getValor(), from,
								null);
					}

				} else if (requisito6 == 1) {

					for (final String from : destinatarios) {
						mail.enviarMail(
								EnvioMail.ASUNTO.getValor(),
								EnvioMail.CUERPOERRORCUENTA.getValor()
										+ prestamoSeleccionado.getNumafi()
										+ ". "
										+ EnvioMail.COMENTARIO.getValor()
										+ ".\n\n"
										+ EnvioMail.PIEMAIL.getValor(), from,
								null);
					}

				} else if (requisito5 == 1 && requisito6 == 1) {

					for (final String from : destinatarios) {
						mail.enviarMail(
								EnvioMail.ASUNTO.getValor(),
								EnvioMail.CUERPOFECHAYCUENTA.getValor()
										+ prestamoSeleccionado.getNumafi()
										+ ". "
										+ EnvioMail.COMENTARIO.getValor()
										+ ".\n\n"
										+ EnvioMail.PIEMAIL.getValor(), from,
								null);
					}

				}
			}

		} catch (final LiquidacionFondosReservaExcepcion e) {
			msjError = e.getMessage();
			habilitarRequisitosCruce = false;
		} catch (final GarantiasSacException e) {
			msjError = e.getCodigo() + ", " + e.getMensaje();
			habilitarRequisitosCruce = false;
		} catch (final SimulacionCancelacionSacException e) {
			msjError = e.getCodigo() + ", " + e.getMensaje();
			LOG.error("No es posible simular la cancelacion operacion GAF",e);
			habilitarRequisitosCruce = false;
		} catch (ConsultaParametroException e) {
			msjError = "Estimado cliente: Se produjo un problema a ejecutar la accion solicitada porfavor intente nuevamente";
			LOG.error("Estimado cliente: Se produjo un problema a ejecutar la accion solicitada porfavor intente nuevamente",e);			
			habilitarRequisitosCruce = false;
		}
		return "requisitosCruce";
	}

	private Double obtenerMontoPrecancelarNova(Prestamo prestamoBD) throws SimulacionCancelacionSacException, ConsultaParametroException {
		final OperacionSacRequest operacionSacRequest = new OperacionSacRequest();
		final OperacionRequestDto operacionRequestDto = new OperacionRequestDto();
		operacionRequestDto.setNumero(prestamoBD.getNumOperacionSAC());
		operacionRequestDto.setTipoTicket("C+L");
		operacionRequestDto.setFechaSimulacion(consultaFechaEfectivaGafLocal.consultarFechaEfectiva());
		operacionRequestDto.setTipoProducto(prestamoBD.getDestinoComercial().getCodProdPq());
		final ClienteRequestDto cliente = new ClienteRequestDto();
		cliente.setTipoCliente(FuncionesUtilesSac.obtenerTipoPrestamista(datos.getTipo()));
		operacionSacRequest.setOperacion(operacionRequestDto);
		operacionSacRequest.setCliente(cliente);
		CreditoExigibleDto liquidacion=simulacionCancelacionSacServicio.simularCancelacion(operacionSacRequest);
		return liquidacion.getMontoPrecancelar().doubleValue();
	}
	
	private String obtenerFechaVecimiento() {
		final Calendar calendar = Calendar.getInstance();
		Date fechaVencimiento;

		fechaVencimiento = calendar.getTime();

		String fechaFormatoSac = null;
		try {
			fechaFormatoSac = FuncionesUtilesSac.obtenerFechaSac(fechaVencimiento);
		} catch (final ParseException e) {
			LOG.error("Error al parsear la fecha vecimiento peticion SAC", e);
		}
		return fechaFormatoSac;
	}

	/**
	 * Permite validar el cruce de cuentas
	 */
	private boolean esValidoCruceCuentas() {
		boolean esValido = Boolean.FALSE;
		try {
			
			setearPrestamoSeleccionado();
				banDeb=false;
				habilitaMensajeDebito = false;
				debitoAutomatico();
			
			if (liquidacionServicio.existeComprobantesPorEstados(prestamoSeleccionado,
					Arrays.asList("EMI", "SRV", "REV","REC"))) {
				throw new LiquidacionFondosReservaExcepcion(
						"Estimado cliente: Usted no puede realizar el Cruce de Cuentas, existe un proceso de recaudaci\u00F3n del cr\u00E9dito en tr\u00E1mite");
			}
			liquidacionServicio.esPosibleLiquidacionFondos(validarRecaudacion);
			esValido = true;
		} catch (final LiquidacionFondosReservaExcepcion e) {
			msjCalcularLiqFondos = e.getMessage();
		} catch (GeneracionComprobanteExcepcion e) {
			msjCalcularLiqFondos = e.getMessage();
		}
		return esValido;
	}
	/**
	 * Calcula como quedaria la Liquidacion del credito con Fondos de Reserva
	 * 
	 * @return
	 */
	public String calcularLiquidacionFondos() {
		try {
			if(!validaDiasHabilesCruCue()) {
				return "";
			}
			if(calculoLiquidacion==null) {
				obtenerLiquidacionCredito(obtenerFechaActual(),"C+L");
			}else {
				//ya no consulto ya debe estar cargado con los prerequisitos
			}
			
		} catch (final SimulacionCancelacionSacException e) {
			msjError = e.getCodigo() + ", " + e.getMensaje();
			return "consultaCreditos";
		}
		return "calculoLiquidacionFondos";
	}

	/**
	 * Permite obtener las liquidacion del credito desde el core SAC
	 * 
	 * @param operacionRequestDto
	 * @return
	 * @throws SimulacionCancelacionSacException
	 * @throws GarantiasSacException
	 */
	private void obtenerLiquidacionCredito(final String fechaVecimiento,final String tipoTicket) throws SimulacionCancelacionSacException {
		final OperacionSacRequest operacionSacRequest = new OperacionSacRequest();
		final OperacionRequestDto operacionRequestDto = new OperacionRequestDto();
		operacionRequestDto.setNumero(validarRecaudacion.getPrestamo().getNumOperacionSAC());
		operacionRequestDto.setTipoTicket(tipoTicket);
		operacionRequestDto.setFechaSimulacion(fechaVecimiento);
		operacionRequestDto.setTipoProducto(prestamoSeleccionado.getDestinoComercial().getCodProdPq());
		final ClienteRequestDto cliente = new ClienteRequestDto();
		cliente.setTipoCliente(FuncionesUtilesSac.obtenerTipoPrestamista(datos.getTipo()));
		operacionSacRequest.setOperacion(operacionRequestDto);
		operacionSacRequest.setCliente(cliente);
		datosLiquidacion = simulacionCancelacionSacServicio.simularCancelacion(operacionSacRequest);
		calculoLiquidacion = new CalculoLiquidacion();
		calculoLiquidacion
				.setCapitalTotal(datosLiquidacion.getMontoCapital().add(datosLiquidacion.getMontoInteresVencimiento())
						.add(datosLiquidacion.getInteresVencimientoAnticipado()));
		calculoLiquidacion.setEstadoPrestamo(datosLiquidacion.getEstado());
		calculoLiquidacion.setFechaInicial(prestamoSeleccionado.getFecinipre());
		calculoLiquidacion.setFechaFinal(prestamoSeleccionado.getFecfinpre());
		calculoLiquidacion.setInteresPorMora(datosLiquidacion.getMontoMora());
		calculoLiquidacion.setMontoPrestamo(BigDecimal.valueOf(prestamoSeleccionado.getMntpre()));
		calculoLiquidacion.setSeguroSaldos(datosLiquidacion.getValorSeguroSaldos());
		calculoLiquidacion.setValorPorCancelar(datosLiquidacion.getMontoPrecancelar());
		calculoLiquidacion.setInteresGracia(datosLiquidacion.getInteresGracia());
	}



	/**
	 * Calcula como quedaria la Liquidacion del credito
	 * 
	 * @return
	 */

	public BigDecimal calcularValorFondos() {
		try {
			return liquidacionServicio.calcularValorFondos(
					prestamoSeleccionado.getPrestamoPk(),
					prestamoSeleccionado.getNumafi(),
					validarRecaudacion.getCumpleImposiciones()).setScale(2,
					BigDecimal.ROUND_DOWN);
		} catch (final CalculoValorRealFondosExcepcion e) {
			msjError = e.getMessage();
			return null;
		}
	}

	/**
	 * Calcula como quedaria la Liquidacion del credito
	 * 
	 * @return
	 */
	public String calcularLiq() {
		try {
			validarCondicionesLiq();
			obtenerLiquidacionCredito(obtenerFechaVecimiento(),"C+L");
			if (calculoLiquidacion.getValorPorCancelar().compareTo(montoMaximo) >= 0) {
				validarMontoCancelar = true;
			}
		} catch (final PeriodoComprobanteException e) {
			msjCalcularLiq = e.getMessage();
			LOG.error("Error al habilitar generacion de comprobante o liquidacion anticipada.", e);
			habilitarOpciones();
			return "";
		} catch (final LiquidacionAnticipadaExcepcion e) {
			msjCalcularLiq = e.getMessage();
			habilitarOpciones();
			return "";
		} catch (final GeneracionComprobanteExcepcion e) {
			msjCalcularLiq = e.getMessage();
			habilitarOpciones();
			return "";
		} catch (final TablaAmortizacionSacException e) {
			msjCalcularLiq = e.getCodigo() + ", " + e.getMessage();
			habilitarOpciones();
			return "";
		} catch (final SimulacionCancelacionSacException e) {
			msjError = e.getCodigo() + ", " + e.getMensaje();
			habilitarOpciones();
			return "consultaCreditos";
		}
		return "calculoLiquidacion";
	}
	/**
	 * Permite validar las condicines que debe cumplir el credito para poder
	 * liquidar
	 * 
	 * @throws LiquidacionAnticipadaExcepcion
	 * @throws TablaAmortizacionSacException
	 * @throws GeneracionComprobanteExcepcion
	 * @throws PeriodoComprobanteException
	 */
	private void validarCondicionesLiq() throws LiquidacionAnticipadaExcepcion, TablaAmortizacionSacException,
			GeneracionComprobanteExcepcion, PeriodoComprobanteException {
		
		// Se restringe la generacion de comprobantes a AFILIADOS y
		// AFILIADOS-JUBILADOS
		
		try {
			setearPrestamoSeleccionado();
//			REQ 444
			banDeb=false;
			habilitaMensajeDebito = false;
			debitoAutomatico();
			if (!esAfiliadoJubilado()) {
				throw new LiquidacionAnticipadaExcepcion(msjError != null?msjError:"D\u00EDa no h\u00E1bil para generar comprobante de liquidaci\u00F3n. "
						+ "<br/>Nos encontramos generando las planillas para el descuento mensual de la(s) cuota(s) de su(s) "
						+ "pr\u00E9stamo(s) que se realiza(n) a trav\u00E9s de su \nempleador.");
			}	
		} catch (ConsultaParametroException e) {
			throw new LiquidacionAnticipadaExcepcion("Estimado afiliado, no se pudo completar la accion intente nuevamente");
		}				
		if (prestamoSeleccionado.getDiasMora() > diasMoraDebAut) {
			if (prestamoSeleccionado.getPrestamoPk().getCodprecla() == 20
					|| prestamoSeleccionado.getPrestamoPk().getCodprecla() == 22) {
				throw new LiquidacionAnticipadaExcepcion(
						"EXISTEN DIVIDENDOS CON MORA MAYOR A "+ diasMoraDebAut +" D\u00CDAS SUS GARANT\u00CDAS SER\u00C1N EJECUTADAS");
			}
		}
		if (liquidacionServicio.existeComprobantesPorEstados(prestamoSeleccionado,
				Arrays.asList("EMI", "SRV", "REV","REC"))) {
			throw new LiquidacionAnticipadaExcepcion(
					"El pr\u00E9stamo tiene dividendos en tr\u00E1mite de pago, no se puede liquidar");
		}
		
		
		if (liquidacionServicio.tieneSolicitudNovacionTramite(prestamoSeleccionado.getNumCancelacionArmado(),
				datos.getSolicitante().getDatosPersonales().getCedula())) {
			throw new GeneracionComprobanteExcepcion(
					"Estimado cliente: Existe una solicitud de novaci\u00F3n en tr\u00E1mite ");
		}

		validarCondicionesPrestamo();
		
		
	}

	private boolean validaDiasHabilesLiqAnt() {
		try {
			if (!esAfiliadoJubilado()) {
				msjDiaHabilLiqAnt = "D\u00EDa no h\u00E1bil para generar comprobante de liquidaci\u00F3n. "
						+ "<br/>Nos encontramos generando las planillas para el descuento mensual de la(s) cuota(s) de su(s) "
						+ "pr\u00E9stamo(s) que se realiza(n) a trav\u00E9s de su \nempleador.";
				return false;
			}
		} catch (ConsultaParametroException e) {
			e.getMessage();
		}
		return true;
	}

	private boolean validaDiasHabilesCruCue() {
		try {
			if (!esAfiliadoJubilado()) {
				msjDiaHabilCruCue = "D\u00EDa no h\u00E1bil para generar Cruce de Cuentas. "
						+ "<br/>Nos encontramos generando las planillas para el descuento mensual de la(s) cuota(s) de su(s) "
						+ "pr\u00E9stamo(s) que se realiza(n) a trav\u00E9s de su \nempleador.";
				return false;
			}
		} catch (ConsultaParametroException e) {
			e.getMessage();
		}
		return true;
	}

	/**
	 * Realiza la liquidacion del credito
	 * 
	 * @return
	 * @throws GeneracionComprobanteExcepcion 
	 */
	public String liquidar() throws GeneracionComprobanteExcepcion {
			if(!validaDiasHabilesLiqAnt()) {
				return "";
			}
			estadosLiquidar.clear();
			estadosLiquidar.add(EstadoComprobantePago.DEP);
			estadosLiquidar.add(EstadoComprobantePago.GEN);
			
			validarRecaudacion.setTipoEmpleador(null);
				final Calendar fechaGeneracion = Calendar.getInstance();
			
		//Comienzo generar comprobante 
		final DatoComprobante datoComprobante = new DatoComprobante();
		datoComprobante.setTipoTransaccion(21);
		datoComprobante.setCedula(prestamoSeleccionado.getNumafi());
		datoComprobante.setNut(prestamoSeleccionado.getNut());
		datoComprobante.setIdGaf(prestamoSeleccionado.getNumOperacionSAC());
		datoComprobante.setValorCobro(datosLiquidacion.getMontoPrecancelar().doubleValue());
		datoComprobante.setIntMora(datosLiquidacion.getMontoMora().doubleValue());
		datoComprobante.setFechaVencimiento(new Date());
			
		try {		
			final ComprobantePagoPk comprobantepk = comprobantePagoServicio
					.generarComprobantePagoIndividualSAC(datoComprobante);
				
			//FIn comprobante

			numeroLiquidacion = Long.valueOf(comprobantepk.getCodComPagAfi());
			liquidacionPrestamo = new LiquidacionPrestamo();
			liquidacionPrestamo.setNumeroLiquidacion(numeroLiquidacion);
			liquidacionPrestamo.setFechaLiquidacion(fechaGeneracion.getTime());
			liquidacionPrestamo.setSumIntMor(datosLiquidacion.getMontoMora());
			liquidacionPrestamo.setSumDivTot(calculoLiquidacion.getCapitalTotal());
			liquidacionPrestamo.setSegSalNet(calculoLiquidacion.getSeguroSaldos());
			liquidacionPrestamo.setTotCanLiq(datosLiquidacion.getMontoPrecancelar());
			liquidacionPrestamo.setSumIntPerGra(datosLiquidacion.getInteresGracia());
			habilitarCalcularLiq = false;
			habilitarGenerarComp = false;
			habilitarCalcularLiqFondos = false;
			habilitarConsultaLiq = true;
			// cargo el comprobante
			fabricarComprobante(comprobantepk, fechaGeneracion.getTime());
		} catch (final GenerarComprobantePagoIndividualExcepcion e) {
			msjError = e.getMessage().concat("Datos:").concat(datoComprobante.toString());
			return "consultaCreditos";
		}
		
		
		/* INC-1817 Notificaciones asegurados */
		if (null != liquidacionPrestamo && null != comprobantePago) {
			enviarAlertaLiqCredito();
		}

		return "resultadoLiquidacion";
	}


	/**
	 * Permite construir el comprobante que se guardo en la base de datos
	 * 
	 * @param pk
	 * @param fechaGeneracion
	 */
	private void fabricarComprobante(final ComprobantePagoPk pk, final Date fechaGeneracion) {
		comprobantePago = new ComprobantePago();
		comprobantePago.setPk(pk);
		final TipoComprobantePago tipoComprobante = new TipoComprobantePago();
		tipoComprobante.setDescripcion("PAGO DE LIQUIDACION DE PRESTAMOS");
		comprobantePago.setTipoComprobante(tipoComprobante);
		comprobantePago.setFechageneracionComprobante(new Timestamp(fechaGeneracion.getTime()));
		comprobantePago.setNumeroAfiliado(prestamoSeleccionado.getNumafi());
		comprobantePago.setFechaFinComprobante(new Timestamp(fechaGeneracion.getTime()));
		comprobantePago.setNumPreAfi(new BigDecimal(prestamoSeleccionado.getPrestamoPk().getNumpreafi()));
		comprobantePago.setValComPagAfi(liquidacionPrestamo.getSumDivTot());
		comprobantePago.setInteresMora(liquidacionPrestamo.getSumIntMor());
		comprobantePago
				.setValorTotalComprobante(liquidacionPrestamo.getSumDivTot().add(liquidacionPrestamo.getSumIntMor()));
		comprobantePago.setValDedPag(BigDecimal.ZERO);
		final List<ComprobantePagoDetalle> detalles = new ArrayList<ComprobantePagoDetalle>();
		final ComprobantePagoDetalle detalle = new ComprobantePagoDetalle();
		detalle.setValIntDet(liquidacionPrestamo.getSumIntMor().floatValue());
		detalle.setValCanDet(liquidacionPrestamo.getTotCanLiq().floatValue());
		detalles.add(detalle);
		comprobantePago.setDetalle(detalles);

	}

	/**
	 * Realiza la liquidacion del credito
	 * 
	 * @return
	 */
	public String liquidarFondos() {
		
		if(!validaDiasHabilesCruCue()) {
			return "";
		}
		
			habilitarCalcularLiq = false;
			habilitarGenerarComp = false;
			habilitarConsultaLiq = false;
			habilitarLiquidarFondos = false;
			habilitarCalcularLiqFondos = false;
		liquidacionPrestamo = new LiquidacionPrestamo();
		liquidacionPrestamo.setSumIntMor(calculoLiquidacion.getInteresPorMora());
		liquidacionPrestamo.setSumDivTot(calculoLiquidacion.getCapitalTotal());
		liquidacionPrestamo.setSegSalNet(calculoLiquidacion.getSeguroSaldos());
		liquidacionPrestamo.setTotCanLiq(calculoLiquidacion.getValorPorCancelar());
		liquidacionPrestamo.setFecTerPreLiq(new Date());
		final TransaccionRecaudacion transaccion = construirTransaccion();
		transaccionRecaudacionDao.guardarTransaccion(transaccion);
		try {
			liquidacionServicio.generarLiquidacionFondosGaf(validarRecaudacion);
			actualizarTransaccion(transaccion, "REC");
		} catch (LiquidacionFondosReservaExcepcion e) {
			// Se produce un error al consumir sp iess
			LOG.error("Error al consumir sp iess cruce fondos reserva se anulara la transaccion ",e);
			msjError = super.getResource("messages", "pq.dinamico.error.generico")+" "+e.getMessage();
			actualizarTransaccion(transaccion, "ANU");
			return "consultaCreditos";
		}catch(Exception e) {
			LOG.error("Error No Controlado al cruzar cuentas se va anular la transaccion ",e);
			msjError = super.getResource("messages", "pq.dinamico.error.generico");
			actualizarTransaccion(transaccion, "ANU");
			return "consultaCreditos";
		}

		/* INC-1817 Notificaciones asegurados */
		if (null != liquidacionPrestamo) {
			enviarAlertaCruceFR();
		}

		return "resultadoLiquidacionFondos";
	}

	private void actualizarTransaccion(final TransaccionRecaudacion transaccion,String estado) {
		transaccionRecaudacionDao.actualizarEstadoAjustePlanilla(estado, transaccion.getFechaProceso(), null,
				transaccion.getPk().getTrIdtipotransaccion(), transaccion.getPk().getIdTransaccion());

	}

	/**
	 * Consulta la liquidacion vigente
	 * 
	 * @return
	 */
	public String consultarLiquidacion() {
		LOG.debug("Consultando liquidacion #:" + numeroLiquidacion);
		liquidacionPrestamo = liquidacionServicio.consultar(numeroLiquidacion);
		estadosLiquidar.clear();
		estadosLiquidar.add(EstadoComprobantePago.DEP);
		estadosLiquidar.add(EstadoComprobantePago.GEN);
		// cargo el comprobante de pago
		try {
			comprobantePago = comprobantePagoServicio
					.obtenerComprobantePagoVigente(numeroLiquidacion,
							estadosLiquidar);
		} catch (final NoTieneComprobanteVigenteException e) {
			msjError = e.getMessage();
			comprobantePago = null;
			return "consultaCreditos";
		}
		return "resultadoLiquidacion";
	}
	public void cambiarMontoPersonalizado() {
		if (esMontoPersonalizado) {
			esMontoPersonalizado = Boolean.FALSE;
		} else {
			esMontoPersonalizado = Boolean.TRUE;
		}
	}
	/**
	 * Permite construir los parametros para realizar la simulacion de valor
	 * exigible
	 * 
	 * @return retorna la entrada para servicio de simulacion valor exigible
	 */
	private OperacionSacRequest armarOperacionPeticionSimular(final double valorSimulacion, final String tipoTicket) {
		final OperacionSacRequest request = new OperacionSacRequest();
		final OperacionRequestDto operacionDto = new OperacionRequestDto();
		try {
			final String fechaSimulacion = FuncionesUtilesSac.obtenerFechaSac(new Date());
			operacionDto.setFechaSimulacion(fechaSimulacion);

		} catch (final ParseException e1) {
			LOG.error("Existe un error al parsear la fecha en forma sac", e1);
		}

		operacionDto.setTipoTicket(tipoTicket);
		operacionDto.setNumero(prestamoSeleccionado.getNumOperacionSAC());
		operacionDto.setTipoProducto(prestamoSeleccionado.getDestinoComercial().getCodProdPq());
		operacionDto.setValorTotalPago(valorSimulacion);
		final ClienteRequestDto cliente = new ClienteRequestDto();
		cliente.setTipoCliente(FuncionesUtilesSac.obtenerTipoPrestamista(datos.getTipo()));
		request.setCliente(cliente);
		request.setOperacion(operacionDto);
		return request;
	}
	/**
	 * Permite sumar los dividendos seleccionados
	 * 
	 * @param event
	 * @throws PagIndException
	 */
	public void sumarPagosSeleccionados(final ValueChangeEvent event) {

		boolean seleccionValida = true;
		final DatoDeudaDto dividendoSeleccionado = (DatoDeudaDto) ((UIInput) event.getSource()).getAttributes()
				.get("dividendoSelect");
		dividendosSacCheck.put(dividendoSeleccionado, (Boolean) (event.getNewValue()));

		final boolean primeraCuotaSeleccionada = dividendosSacCheck.entrySet().iterator().next().getValue();
		int totalFalse = 0;
		BigDecimal sumaMontoMora = BigDecimal.ZERO;
		sumaValoresSeleccionados = BigDecimal.ZERO;
		for (final Map.Entry<DatoDeudaDto, Boolean> dividendoActual : dividendosSacCheck.entrySet()) {

			if (dividendoActual.getValue() && primeraCuotaSeleccionada && totalFalse == 0) {
				sumaValoresSeleccionados = sumaValoresSeleccionados.add(dividendoActual.getKey().getValorDividendo());
				sumaMontoMora = sumaMontoMora.add(dividendoActual.getKey().getMontoMora());
			} else {
				totalFalse++;

			}

			if (totalFalse > 0 && dividendoActual.getValue()) {
				msjErrorPagInd = super.getResource("messages", "msg.seleccion.consecutiva");
				LOG.info(msjErrorPagInd);

				accionGenCompIndivActivo = Boolean.FALSE;
				seleccionValida = Boolean.FALSE;
				sumaValoresSeleccionados = BigDecimal.ZERO;
				sumaMontoMora = BigDecimal.ZERO;
				setTotalDividendos(sumaValoresSeleccionados);
				setTotalMoraDividendos(sumaMontoMora);
				break;
			} else {
		msjErrorPagInd = null;
				accionGenCompIndivActivo = Boolean.TRUE;
			}

		}
		this.totalDividendos = (totalFalse == dividendosSacCheck.entrySet().size()) ? BigDecimal.ZERO
				: this.totalDividendos;
		this.totalMoraDividendos = (totalFalse == dividendosSacCheck.entrySet().size()) ? BigDecimal.ZERO
				: this.totalMoraDividendos;

		realizarSumaRestaDividendos(seleccionValida, sumaMontoMora);

	}
	/**
	 * Suma la mora y los dividendos para los creditos seleccionados
	 * 
	 * @param seleccionValida
	 * 
	 * @param sumaMontoMora
	 */
	private void realizarSumaRestaDividendos(final boolean seleccionValida, final BigDecimal sumaMontoMora) {
		if (seleccionValida && sumaValoresSeleccionados.signum() == 1) {
			setTotalDividendos(sumaValoresSeleccionados);
			setTotalMoraDividendos(sumaMontoMora);
		}
		accionGenCompIndivActivo = totalDividendos.signum() == 1;
		}

	/**
	 * Permite iniciar todos lo valores para volver a calcular
	 */
	private void restaurarValoresIniciales() {
		dividendosSacCheck.clear();
		habilitaSeleccionDividendo = Boolean.FALSE;
		totalDividendos = BigDecimal.ZERO;
		totalMoraDividendos = BigDecimal.ZERO;
		esMontoPersonalizado = Boolean.FALSE;
		montoTotalDividendos = BigDecimal.ZERO;
		mostrarResultSimulacion = Boolean.FALSE;
		listaDividendosSac = new ArrayList<DatoDeudaDto>();
		lstDivPersonalizadoSac = new ArrayList<DatoDeudaDto>();
		valorPersonalizado = BigDecimal.ZERO;
		accionGenCompIndivActivo = Boolean.FALSE;
	}

	/**
	 * Metodo que invoca cuando se selecciona la opcion generar comprobante indivual
	 * 
	 * @return redirige a la pagina de generacion de comprobante individual o
	 *         muestra un mensaje de error si no cumplio una validacion
	 */
	public String seleccionarDividendos() {
		esConsultaComprobante=Boolean.FALSE;
		restaurarValoresIniciales();
		CreditoExigibleDto creditoExigible;
		try {
			validarCondicionesCompInd();
			if(esFlujoPrestamoSaldeb()) {
				obtenerLiquidacionCredito(obtenerFechaActual(),"PRE");
				//validar si valor liquidacion es mayor a cero
				genCompIndivSaldebActivo=Boolean.TRUE;
				return "calculoLiquidacionSaldeb";
			}
			if (prestamoSeleccionado.getDiasMora() > diasMoraDebAut && (prestamoSeleccionado.getPrestamoPk().getCodprecla() == 20
					|| prestamoSeleccionado.getPrestamoPk().getCodprecla() == 22)) {

				throw new GenerarComprobanteIndividualExcepcion(
						"EXISTEN DIVIDENDOS CON MORA MAYOR A "+ diasMoraDebAut +" D\u00CDAS SUS GARANT\u00CDAS SER\u00C1N EJECUTADAS");
			
			}
			creditoExigible = simularValorExigibleSacServicio
					.obtenerSimulacionExigibles(armarOperacionPeticionSimular(0,"PAC"));
			if(creditoExigible.getDatosDeuda().size()>0) {
			listaDividendosSac = creditoExigible.getDatosDeuda();
			}else {
				msjGenerarComp=MSG_VALORES_PENDIENTES;
				return "";
			}
			
			montoTotalDividendos = BigDecimal.ZERO;
			for (final DatoDeudaDto datoDeudaDto : listaDividendosSac) {
				dividendosSacCheck.put(datoDeudaDto, false);
				montoTotalDividendos = montoTotalDividendos.add(datoDeudaDto.getValorDividendo());
			}
		} catch (final SimulacionValorExigibleException e) {
			habilitarOpciones();
			msjGenerarComp = e.getCodigo() + ", " + e.getMensaje();
			
			return "";
		} catch (final GenerarComprobanteIndividualExcepcion e) {
			habilitarOpciones();
			msjGenerarComp = e.getMessage();
			return "";
		} catch (final GeneracionComprobanteExcepcion e) {
			habilitarOpciones();
			msjGenerarComp = e.getMessage();
			return "";
		} catch (final PeriodoComprobanteException e) {
			habilitarOpciones();
			msjGenerarComp = e.getMessage();
			return "";
		} catch (final SimulacionCancelacionSacException e) {
			habilitarOpciones();
			msjGenerarComp = e.getMessage();
			return "";
		}

			return "pagIndSeleccion";
		}
	private boolean esFlujoPrestamoSaldeb() {
		final String estadoPrestamo=prestamoSeleccionado.getEstadoPrestamo().getCodestpre();
		return "ELC".equals(estadoPrestamo) || "ELF".equals(estadoPrestamo);
	}

				
	/**
	 * Verifica si puede generar comprobante
	 * 
	 * @throws GenerarComprobanteIndividualExcepcion
	 * @throws GeneracionComprobanteExcepcion
	 * @throws PeriodoComprobanteException
	 */
	private void validarCondicionesCompInd()
			throws GenerarComprobanteIndividualExcepcion, GeneracionComprobanteExcepcion, PeriodoComprobanteException {
		setearPrestamoSeleccionado();
		if (liquidacionServicio.tieneSolicitudNovacionTramite(prestamoSeleccionado.getNumCancelacionArmado(),
				datos.getSolicitante().getDatosPersonales().getCedula())) {
			throw new GeneracionComprobanteExcepcion(
					"Estimado cliente: Existe una solicitud de novaci\u00F3n en tr\u00E1mite ");
				}

		if (liquidacionServicio.existeComprobantesPorEstados(prestamoSeleccionado,
				Arrays.asList("EMI", "SRV", "REV","REC"))) {
			throw new GeneracionComprobanteExcepcion(
					"Estimado cliente: Usted tiene un comprobante previamente generado, no puede generar un segundo comprobante");
			}
//		REQ 444
		habilitaMensajeDebito = false;
		banDeb=true;
		debitoAutomatico();
// Restrinjo la generacion de comprobantes a AFILIADOS
		try {
			if (!esAfiliadoJubilado()) {
				throw new GeneracionComprobanteExcepcion(msjError != null?msjError:
						"D\u00EDa no h\u00E1bil para generar comprobante de pago individual. "
						+ "<br/>Nos encontramos generando las planillas para el "
						+ "descuento mensual de la(s) cuota(s) de su(s) pr\u00E9stamo(s) "
						+ "que se realiza(n) a trav\u00E9s de su empleador.");
				}
		} catch (ConsultaParametroException e) {
			throw new GeneracionComprobanteExcepcion(
					"Estimado cliente: Se produjo un problema a ejecutar la accion solicitada porfavor intente nuevamente");
		}
	}
	
	private void debitoAutomatico() throws GeneracionComprobanteExcepcion {
		try {
			String estado = liquidacionServicio.validarDebitoAutomatico(datos.getSolicitante().getDatosPersonales().getCedula(),
					prestamoSeleccionado.getNumOperacionSAC(), prestamoSeleccionado.getNut());
		
			if(estado != "" && comprobantePagoAfiliadoDao.estadoBanderaDebito()) {				
				 panelMensajes=true;
				  habilitaMensajeDebito=true;
				throw new GeneracionComprobanteExcepcion(
						"");
				
			}
			
			if((estado.equals("ADA") && Boolean.FALSE.equals(banDeb)) || estado.equals("CDA")
					|| (estado.equals("ENV") && comprobantePagoAfiliadoDao.consultaDebitoENV(datos.getSolicitante().getDatosPersonales().getCedula(),
							prestamoSeleccionado.getNumOperacionSAC(), prestamoSeleccionado.getNut()))) {				
				 panelMensajes=true;
				  habilitaMensajeDebito=true;
				throw new GeneracionComprobanteExcepcion(
						"");
				
			}			
			
			
		} catch (DebitoAutomaticoExcepcion e1) {
			throw new GeneracionComprobanteExcepcion(
					"Estimado cliente: Se produjo un problema a ejecutar la accion solicitada porfavor intente nuevamente");
		}
	}

	private boolean validaDiasHabilesCompInd() {
		try {
			if (!esAfiliadoJubilado()) {
				msjDiaHabilCompInd="D\u00EDa no h\u00E1bil para generar comprobante de pago individual. "
						+ "<br/>Nos encontramos generando las planillas para el "
						+ "descuento mensual de la(s) cuota(s) de su(s) pr\u00E9stamo(s) "
						+ "que se realiza(n) a trav\u00E9s de su empleador.";
				return false;
			}			
		} catch (ConsultaParametroException e) {
			e.getMessage();
		}
		return true;
	}
	
	/**
	 * Permite quitar los pagos seleccionados de los checkbox
	 */
	public void quitarPagosSeleccionados() {

		setTotalMoraDividendos(BigDecimal.ZERO);
		setTotalDividendos(BigDecimal.ZERO);
		dividendosSacCheck.clear();

			}

	/**
	 * Valida que no se ingresa un monto mayor a el monto total de dividendos ni el
	 * minimo valor
	 * 
	 * @param monto
	 * @throws PagIndException
	 */
	private void validarQueNoSupereElMaxMin(final double monto) throws PagIndException {

		if (monto > montoTotalDividendos.doubleValue()) {
			reiniciarValorPersonali();
			throw new PagIndException("Estimado Usuario, el valor que debe ingresar debe ser menor o igual a USD "
					+ String.format("%.2f", montoTotalDividendos));
		} else if (monto < 10 && montoTotalDividendos.doubleValue()>=10) {
			reiniciarValorPersonali();
			throw new PagIndException("Estimado Usuario, el valor que debe ingresar debe ser mayor o igual a USD "
					+ String.format("%.2f", 10.0));
		}else if(monto<10 && montoTotalDividendos.doubleValue()<=10 ) {
			reiniciarValorPersonali();
			throw new PagIndException("Estimado Usuario, usted debe pagar el valor completo "
					+ String.format("%.2f", montoTotalDividendos.doubleValue()));
		}
	}
	private void reiniciarValorPersonali() {
		mostrarResultSimulacion = Boolean.FALSE;
	}

	/**
	 * Genera pagind de acuerdo a los divindendos seleccionados
	 * 
	 * @return redirige a la pagina comprobante de pago definido en el face config
	 * @throws GeneracionComprobanteExcepcion 
	 */
	public String generarPagInd() throws GeneracionComprobanteExcepcion {
		try {
			if(!validaDiasHabilesCompInd()) {
				return "";
			}	
			generarComprobanteIndividual(this.totalDividendos.doubleValue(), this.totalMoraDividendos.doubleValue(),16,"CTAXPAG A SP(PAGOS INDIV. PREST)");
		} catch (final PagIndException e) {
			msjErrorPagInd = e.getMessage();
			pagindGenerado = false;
			return null;
		}
		return "comprobantesPago";
		
		}
		
	/**
	 * Genera comprobante saldeb de acuerdo a los divindendos seleccionados
	 * 
	 * @return redirige a la pagina comprobante de pago definido en el face config
	 */
	public String generarSalDeb() {
         esSaldeb=Boolean.TRUE;
		try {
			final BigDecimal montoComprobante=calculoLiquidacion.getValorPorCancelar().subtract(calculoLiquidacion.getSeguroSaldos());
			final BigDecimal montoMora=calculoLiquidacion.getInteresPorMora();
			generarComprobantePagoIndividual(montoComprobante.doubleValue(),montoMora.doubleValue(),29,"PAGO DE SALDOS DIVIDENDOS PQ");
		} catch (final PagIndException e) {
			msjErrorPagInd = e.getMessage();
			pagindGenerado = false;
			return null;
		}
		/* INC-1817 Notificaciones asegurados */
		if (null != comprobantesDePago)

		{
			enviarAlertaComPagInd();
		}
		pagindGenerado = true;
		return "comprobantesPago";
		
	}
	/**
	 * Genera un comprobante individual con el valor ingresado por el cliente
	 * 
	 * @return redirige a la pagina comprobante de pago definido en el face config
	 * @throws GeneracionComprobanteExcepcion 
	 */
	public String generarCompIndValPersonalizado() throws GeneracionComprobanteExcepcion {
		try {
			if(!validaDiasHabilesCompInd()){
				return "";
			}		
			generarComprobanteIndividual(this.valorPersonalizado.doubleValue(),
					calcularInteresMora(this.lstDivPersonalizadoSac),16,"CTAXPAG A SP(PAGOS INDIV. PREST)");
		} catch (final PagIndException e) {
			msjErrorPagInd = e.getMessage();
			pagindGenerado = false;
			return null;
		}
		return "comprobantesPago";
	}

	/**
	 * Generar comprobante individual personalizado salbed
	 * @return
	 */
	public String generarCompIndValPersonalizadoSaldeb() {
		try {
			final BigDecimal montoComprobante=calculoLiquidacion.getValorPorCancelar();
			Integer idTrans=29;
			final String descTrans="PAGO DE SALDOS DIVIDENDOS PQ";
			if(montoComprobante.compareTo(this.valorPersonalizado)>0) {
				idTrans=17;
			}			
			generarComprobanteIndividual(this.valorPersonalizado.doubleValue(),
					calcularInteresMora(this.lstDivPersonalizadoSac),idTrans,descTrans);
			
		} catch (final PagIndException e) {
			msjErrorPagInd = e.getMessage();
			pagindGenerado = false;
			return null;
		}
		return "comprobantesPago";
	}
	

	/**
	 * Realiz las validaciones para generar un comprobante indivual y lo genera
	 * 
	 * @param montoComprobante
	 * @param montoMora
	 * @throws PagIndException retorna cuando no cumple una condicion de validacion
	 */
	private void generarComprobanteIndividual(final double montoComprobante, final double montoMora,final Integer tipoTans, final String descComp) throws PagIndException {

		validarMontoComprobante(montoComprobante);
		validarMontoMayorComp(montoComprobante);
		generarComprobantePagoIndividual(montoComprobante, montoMora,tipoTans,descComp);
		/* INC-1817 Notificaciones asegurados */
		if (null != comprobantesDePago)

		{
			enviarAlertaComPagInd();
		}
		pagindGenerado = true;

	}
	/**
	 * Permite calcular el interes de mora de los dividendos
	 * 
	 * @param listaDividendos
	 * @return retorna la suma del interes de todos los dividendos si no hay
	 *         devuelve 0
	 */
	private double calcularInteresMora(final List<DatoDeudaDto> listaDividendos) {

		BigDecimal interesMora = BigDecimal.ZERO;
		if (listaDividendos != null) {
			for (final DatoDeudaDto datoDeudaDto : listaDividendos) {
				interesMora = interesMora.add(datoDeudaDto.getMontoMora());
			}
		}
		return interesMora.doubleValue();

	}

	/**
	 * Valida el monto sea mayor a cero
	 * 
	 * @throws PagIndException si el monto no es mayor a cero lanza un mensaje
	 */
	private void validarMontoComprobante(final double monto) throws PagIndException {
		if (monto <= 0) {
			valorPersonalizado = BigDecimal.ZERO;
			mostrarResultSimulacion = Boolean.FALSE;
			throw new PagIndException("Estimado Usuario, el valor que debe ingresar debe ser mayor o igual a USD "
					+ String.format("%.2f", 10.0));
		}
	}

	/**
	 * 
	 * @param monto
	 * @throws PagIndException
	 */
	private void validarMontoMayorComp(final double monto) throws PagIndException {

		if (monto >=  montoMaximo.doubleValue()) {
			throw new PagIndException("Estimado cliente, el valor del comprobante es igual o mayor a USD $"
					+ montoMaximo
					+ ", es necesario que se dirija a las oficinas del BIESS portando el \"Formulario de Declaraci\u00F3n de Licitud de Fondos y Transacciones\" completo y los justificativos que avalen el documento, para que uno de nuestros asesores genere e imprima el comprobante requerido.");

		}
	}

	/**
	 * Permite activar o desactivar el monto personalizado
	 */
	public void personalizarPago() {
		if (esMontoPersonalizado) {
			valorPersonalizado=BigDecimal.ZERO;
			mostrarResultSimulacion=Boolean.FALSE;
			esMontoPersonalizado = false;
			msjErrorPagInd = null;
		} else {
			esMontoPersonalizado = validarRangoMntPersonalizado();
		}
		
	}
	private boolean validarRangoMntPersonalizado() {
		if(montoTotalDividendos.compareTo(BigDecimal.valueOf(0.01))>0 && montoTotalDividendos.compareTo(BigDecimal.valueOf(10))<0 ) {
			msjErrorPagInd = "Estimado cliente por favor seleccionar el dividendo a cancelar y presionar en la opci\u00F3n \"Confirmar Generaci\u00F3n de Comprobante\".";
			return Boolean.FALSE;
			
		}else {
			return Boolean.TRUE;
		}
	}

	/**
	 * Permite realizar el calculo personalizado
	 */
	public void calcularMontoPersonalizado() {
		CreditoExigibleDto creditoExigible;
		try {
			validarMontoComprobante(valorPersonalizado.doubleValue());
			validarQueNoSupereElMaxMin(valorPersonalizado.doubleValue());
			creditoExigible = simularValorExigibleSacServicio.obtenerSimulacionExigibles(
					armarOperacionPeticionSimular(valorPersonalizado.doubleValue(), tipoTicketPersonal));
			lstDivPersonalizadoSac = creditoExigible.getDatosDeuda();

			mostrarResultSimulacion = !lstDivPersonalizadoSac.isEmpty() && creditoExigible != null;
			msjErrorPagInd = null;
		} catch (final SimulacionValorExigibleException e) {
			String msg = e.getCodigo() + ", " + e.getMensaje();
			if (COD_ERR_GAF_CUOT_SIN_DISTRI.equals(e.getCodigo()) && "APZ".equals(tipoTicketPersonal)) {
				msjErrorPagInd = "Estimado afiliado/a, en este momento no puede personalizar el valor a pagar, presione \"Regresar\" y luego  \"Confirmar Generaci\u00F3n de Comprobante\" ";
			} else {
				msjErrorPagInd = msg;
			}
			LOG.error(msg);
			valorPersonalizado = BigDecimal.ZERO;
			mostrarResultSimulacion = Boolean.FALSE;
		} catch (final PagIndException e) {
			msjErrorPagInd = e.getMessage();

		}

	}
	
	/**
	 * Permite realizar el calculo personalizado
	 */
	public void calcularMontoPersonalizadoSaldeb() {
		montoTotalDividendos=calculoLiquidacion.getValorPorCancelar();
		this.tipoTicketPersonal="APZ";
		if (valorPersonalizado.doubleValue()==montoTotalDividendos.doubleValue()) {
			msjErrorPagInd = "Estimado afiliado/a, si desea cancelar el total, presione \"Regresar\" y luego  \"Confirmar Generaci\u00F3n de Comprobante\" ";
			return;
		}
		calcularMontoPersonalizado();
	}

	/**
	 * Consulta comprobantes PagInd
	 * 
	 * @return
	 */
	public String consultarPagInd() {
		esConsultaComprobante=Boolean.TRUE;
		setearPrestamoSeleccionado();
		validarComprobante.setEstadosComprobante(obtenerEstadosComprobante());
		validarComprobante.setEstadosPrestamo(obtenerEstadosCreComprobante());
		validarComprobante.setPrestamoPk(prestamoSeleccionado.getPrestamoPk());
		validarComprobante.setTiposComprobante(obtenerTiposComprobante());

		if (comprobantePagoServicio.existeComprobanteIndividualVigente(validarComprobante)) {
			comprobantesDePago = comprobantePagoServicio.obtenerComprobanteIndividualVigente(validarComprobante);
			Collections.sort(comprobantesDePago, new Comparator<ComprobantePago>() {
			    @Override
			    public int compare(final ComprobantePago o1, final ComprobantePago o2) {
			        return o1.getFechageneracionComprobante().compareTo(o2.getFechageneracionComprobante());
			    }
			});
		LOG.debug("total comprobantes:" + comprobantesDePago);
		return "comprobantesPago";
			} else {
			msjConsultaGC = "Usted no posee ningun comprobante de pago";
			return "";
		}
				}
	
	private List<String> obtenerTiposComprobante() {
		final List<String> tipos = new ArrayList<String>();
		tipos.add(TiposCredito.TIPO_COMPROBANTE_PAG);
		tipos.add(TiposCredito.TIPO_COMPROBANTE_SAL);
		tipos.add(TiposCredito.TIPO_COMPROBANTE_LIQPRE);

		// Cambios Elsa Angamarca 21/10/2011
		// Aumento para recuperar el tipo de comprobante de pago PAGINDB para el
		// BIESS
		tipos.add(TiposCredito.TIPO_COMPROBANTE_PAG_BIESS);
		tipos.add(TiposCredito.TIPO_COMPROBANTE_SAL_BIESS);
		return tipos;
			}

	private List<EstadoComprobantePago> obtenerEstadosComprobante() {
		final List<EstadoComprobantePago> estadosComprobante = new ArrayList<EstadoComprobantePago>();
		estadosComprobante.add(EstadoComprobantePago.GEN);
		return estadosComprobante;
	}

	private List<String> obtenerEstadosCreComprobante() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_VIGENTE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_FONDOS_CRE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CESANTES_CRE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_ANTICIPADA);
		return estados;
	}
	public String seleccionarComprobante() {

		comprobantePago = (ComprobantePago) getHttpServletRequest().getAttribute("comprobante");

		LOG.debug("getCodComPagAfi()" + comprobantePago.getPk().getCodComPagAfi());
		LOG.debug("getCodTipComPag()" + comprobantePago.getPk().getCodTipComPag());

		LOG.debug("selecciono comprobante num:" + comprobantePago.getNumeroDocumentoPago());

		if(esConsultaComprobante) {
			try {
				comprobantePago = comprobantePagoServicio
						.obtenerPorPk(comprobantePago.getPk());
			} catch (EntidadNoEncontradaException e) {
				LOG.warn("No se encontro el comprobante:"
						+ comprobantePago.getNumeroDocumentoPago());
				comprobantePago = null;
			}

			
		}
		return null;
		}

	/**
	 * Genera el comprobante individual
	 * 
	 * @throws PagIndException
	 */
	public void generarComprobantePagoIndividual(final double monto, final double montoInteresMora,final Integer tipoTans, final String descComp) throws PagIndException {

		try {

			final DatoComprobante datoComprobante = new DatoComprobante();
			datoComprobante.setTipoTransaccion(tipoTans);
			datoComprobante.setCedula(prestamoSeleccionado.getNumafi());
			datoComprobante.setNut(prestamoSeleccionado.getNut());
			datoComprobante.setIdGaf(prestamoSeleccionado.getNumOperacionSAC());
			datoComprobante.setValorCobro(monto);
			datoComprobante.setIntMora(montoInteresMora);
			datoComprobante.setFechaVencimiento(new Date());
			
			final ComprobantePagoPk comprobantepk = comprobantePagoServicio
					.generarComprobantePagoIndividualSAC(datoComprobante);
			comprobantesDePago = new ArrayList<ComprobantePago>();
			comprobantesDePago.add(contruirComprobanteIndividual(comprobantepk, datoComprobante,descComp));

		} catch (final GenerarComprobantePagoIndividualExcepcion e) {
			throw new PagIndException(e.getMessage());
		} catch (ParseException e) {
		    LOG.error("Error la parsear la fecha SAC",e);
			throw new PagIndException(super.getResource("messages", "pq.dinamico.error.generico"));
		}
		LOG.debug(esSaldeb?"saldeb Generado!":"pagind pagindGenerado!");
		msjErrorPagInd = null;
		saldosLiq = null;
		dividendosPorPagar = null;
		this.dividendosSacCheck.clear();
		LOG.debug("total comprobantes:" + comprobantesDePago);

	}

	/**
	 * Permie construir el comprobante individual para imprimir por parte el usuario
	 * 
	 * @param comprobantepk
	 * @param datoComprobante
	 * @return devuelve un comprobante de pago con datos del cliente y valores
	 *         cancelados
	 * @throws ParseException 
	 */
	private ComprobantePago contruirComprobanteIndividual(final ComprobantePagoPk comprobantepk,
			final DatoComprobante datoComprobante,final String desComp) throws ParseException {

		final Timestamp fechaGeneracion = new Timestamp(datoComprobante.getFechaVencimiento().getTime());
		final BigDecimal valorCobro = BigDecimal.valueOf(datoComprobante.getValorCobro());
		final BigDecimal intMora =BigDecimal.valueOf(datoComprobante.getIntMora());
		final BigDecimal valorSinMora = valorCobro.subtract(intMora);
		comprobantePago = new ComprobantePago();
		final TipoComprobantePago tcp = new TipoComprobantePago();
		tcp.setDescripcion(desComp);
		comprobantePago.setTipoComprobante(tcp);
		comprobantePago.setPk(comprobantepk);
		comprobantePago.setFechageneracionComprobante(fechaGeneracion);
		comprobantePago.setInteresMora(intMora);
		comprobantePago.setValorTotalComprobante(valorCobro);
		comprobantePago.setValComPagAfi(valorSinMora);
		comprobantePago.setNumeroAfiliado(datoComprobante.getCedula());
		comprobantePago.setFechaFinComprobante(fechaGeneracion);
		comprobantePago.setNumPreAfi(BigDecimal.valueOf(prestamoSeleccionado.getPrestamoPk().getNumpreafi()));
		if(esMontoPersonalizado && !esSaldeb) {
			comprobantePago.setDetalle(agregarDetalleCompPersona());
		}else {
		comprobantePago.setDetalle(agregarDetalleComp());
		}
	
		return comprobantePago;

		}

	private List<ComprobantePagoDetalle> agregarDetalleComp() throws ParseException{
		List<ComprobantePagoDetalle> detalles=new ArrayList<ComprobantePagoDetalle>();
		for (final Map.Entry<DatoDeudaDto, Boolean> dividendoActual : dividendosSacCheck.entrySet()) {
			if(dividendoActual.getValue()) {
				detalles.add(creaCompPagDetall(dividendoActual.getKey()))	;
			}
			
		}
		return detalles;
		
	}
	
	private List<ComprobantePagoDetalle> agregarDetalleCompPersona() throws ParseException{
		List<ComprobantePagoDetalle> detalles=new ArrayList<ComprobantePagoDetalle>();
		for (final DatoDeudaDto datoDeuda:lstDivPersonalizadoSac) {
				detalles.add(creaCompPagDetall(datoDeuda))	;
			}
			
		return detalles;
		
	}
	private ComprobantePagoDetalle creaCompPagDetall(DatoDeudaDto datoDeuda) throws ParseException {
		ComprobantePagoDetalle comp=new ComprobantePagoDetalle();
		DividendoPrestamo dividendoPrestamo= new DividendoPrestamo();
		Date fecha=FuncionesUtilesSac.obtenerFechaSac(datoDeuda.getFechaVencimiento());
		Calendar fechaDividendo = Calendar.getInstance();
		fechaDividendo.setTime(fecha);
		dividendoPrestamo.setAniper(Long.valueOf(fechaDividendo.get(Calendar.YEAR)));
		dividendoPrestamo.setMesper(Long.valueOf(fechaDividendo.get(Calendar.MONTH)+1l));
		DividendoPrestamoPk divPk=new DividendoPrestamoPk();
		divPk.setNumdiv(datoDeuda.getNumeroCuota());
		dividendoPrestamo.setDividendoPrestamoPk(divPk);
		comp.setDividendoPrestamo(dividendoPrestamo);
		comp.setValCanDet(datoDeuda.getValorDividendo().floatValue());
		comp.setValIntDet(datoDeuda.getMontoMora().floatValue());
		comp.setNumCuoAfi(datoDeuda.getNumeroCuota());
		return comp;
	}
	
	/**
	 * Genera pagind de acuerdo a los saldos seleccionados
	 * 
	 * @return
	 */
	public String generarPagIndSaldos() {
		try {
			generarComprobante("saldosSeleccionados", "SALDOS");
		} catch (final PagIndException e) {
			msjErrorPagInd = e.getMessage();
			pagindGenerado = false;
		return null;
	}
		pagindGenerado = true;
		return "comprobantesPago";
	}



	/**
	 * Genera PagInd para dividendos y saldos
	 * 
	 * @param checkBoxName
	 * @throws PagIndException
	 */
	private void generarComprobante(final String checkBoxName, final String tipo)
			throws PagIndException {

		final String[] dividendosSeleccionados = getHttpServletRequest()
				.getParameterValues(checkBoxName);
		List<Long> dividendosAPagar = new ArrayList<Long>();

		if (tipo.equals("DIVIDENDOS")) {
			dividendosAPagar = PagIndHelper.validarDividendos(
					dividendosSeleccionados, dividendosPorPagar);
		} else {// SALDOS
			// INC-2129 Control en Generacion de Comprobantes.
			final List<SaldoLiquidacionPrestamo> saldosPorPagar = new ArrayList<SaldoLiquidacionPrestamo>();
			final List<Long> valoresIds = new ArrayList<Long>();
			
			for(final SaldoLiquidacionPrestamo saldoLiquidacionPrestamo :  saldosLiq) {
				if(saldoLiquidacionPrestamo.isSeleccionado()){
					valoresIds.add(saldoLiquidacionPrestamo.getDividendoPrestamo().getDividendoPrestamoPk().getNumdiv());
					saldosPorPagar.add(saldoLiquidacionPrestamo);
				}
			}
			
			if(valoresIds.isEmpty()){
				LOG.info("no ha seleccionado nada");
				throw new PagIndException("Seleccione al menos un dividendo.");
			} else {
				final String[] valores = new String[valoresIds.size()];
				int i =0;
				for(final Long id : valoresIds){
					valores[i] = String.valueOf(id);
					i++;
				}
				dividendosAPagar = PagIndHelper.validarDividendosSaldos(valores, saldosLiq);
			}
		
		}

		try {
			comprobantePagoServicio.generarComprobantePagoIndividual(
					dividendosAPagar, situacionPrestamo);
			comprobantesDePago = comprobantePagoServicio
					.obtenerComprobanteIndividualVigente(validarComprobante);
			LOG.debug("total comprobantes:" + comprobantesDePago);
		} catch (final GenerarComprobantePagoIndividualExcepcion e) {
			throw new PagIndException(e.getMessage());
		}
		LOG.debug("pagind pagindGenerado!");
		msjErrorPagInd = null;
		saldosLiq = null;
		dividendosPorPagar = null;

	}

	/**
	 * Metodo para deshabilitar todos los botones
	 */
	public void resetear() {
		cleanOptions();
		msjError = null;
	}

	/**
	 * Consulta los peridos para la generación de comprobantes de pago, tipos de comprobante y vencimiento del comprobante 
	 * @throws ConsultaParametroException
	 */
	public void consultarDiasHabiles() throws  ConsultaParametroException{	
		periodoComprobanteCesante = obtenerRangos(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPCE")));
		periodoComprobanteVoluntario = obtenerRangos(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPVO")));
		periodoComprobanteUnipersonal = obtenerRangos(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPUN")));
		periodoComprobanteJubilado = obtenerRangos(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPJU")));
		periodoComprobanteAfiliado = obtenerRangos(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPAF")));
		
		final String[] tiposComprobante = this.consultaParametroServicio.consultarParametroString("PQW_CON_TIPOCOMPAFI").trim().split(":");		
		tipoComprobanteSB = new StringBuilder();
		for (final String tipCom : tiposComprobante) {			
			if (!tipCom.trim().isEmpty()) {	
				tipoComprobanteSB.append(tipCom.replace("Ã©", "é").replace("Ã","í").replace("í³","ó").replace("Â·", "·"));
			}
		}
		vencimientoComprobante = this.consultaParametroServicio.consultarParametroString("PQW_CON_VENCCOMPAFI").replace("Ã","í");
	}
	
	/**
	 * Permite obtener los rangos para mostrar al cliente
	 * @param rangos
	 * @return
	 */
	private static String obtenerRangos(String rangos) {
		String valor="";
		String[] primerArreglo = rangos.split(":");
		for(int i=0;i<primerArreglo.length;i++) {
			 String[] valores=primerArreglo[i].split("-"); 
			  if(primerArreglo.length>2) {
            	  valor=valor+Integer.valueOf(valores[0])+" al "+Integer.valueOf(valores[1])+(i==primerArreglo.length-2?" y del ":primerArreglo.length==i+1?" de cada mes.":",");
              }else {
            	  valor=valor+Integer.valueOf(valores[0])+" al "+Integer.valueOf(valores[1])+(primerArreglo.length==i+1?" de cada mes.":" y del ");	
			  }
		}
		return valor;
	}
	
	/* getters and setters */

	/**
	 * @return the habilitarCalcularLiq
	 */
	public boolean isHabilitarCalcularLiq() {
		return habilitarCalcularLiq;
	}

	/**
	 * @param habilitarCalcularLiq
	 *            the habilitarCalcularLiq to set
	 */
	public void setHabilitarCalcularLiq(final boolean liquidar) {
		this.habilitarCalcularLiq = liquidar;
	}

	/**
	 * @return the habilitarCalcularLiqFr
	 */
	public boolean isHabilitarCalcularLiqFondos() {
		return habilitarCalcularLiqFondos;
	}

	/**
	 * @param habilitarCalcularLiqFr
	 *            the habilitarCalcularLiqFr to set
	 */
	public void setHabilitarCalcularLiqFondos(final boolean habilitarCalcularLiqFondos) {
		this.habilitarCalcularLiqFondos = habilitarCalcularLiqFondos;
	}

	/**
	 * @return the habilitarVerDetalle
	 */
	public boolean isHabilitarVerDetalle() {
		return habilitarVerDetalle;
	}

	/**
	 * @param habilitarVerDetalle
	 *            the habilitarVerDetalle to set
	 */
	public void setHabilitarVerDetalle(final boolean verDetalle) {
		this.habilitarVerDetalle = verDetalle;
	}

	/**
	 * @return the msjCalcularLiq
	 */
	public String getMsjCalcularLiq() {
		return msjCalcularLiq;
	}

	/**
	 * @param msjCalcularLiq
	 *            the msjCalcularLiq to set
	 */
	public void setMsjCalcularLiq(final String msjLiquidar) {
		this.msjCalcularLiq = msjLiquidar;
	}

	/**
	 * @return the msjCalcularLiqFondos
	 */
	public String getMsjCalcularLiqFondos() {
		return msjCalcularLiqFondos;
	}

	/**
	 * @param msjCalcularLiqFR
	 *            the msjCalcularLiqFr to set
	 */
	public void setMsjCalcularLiqFondos(final String msjCalcularLiqFondos) {
		this.msjCalcularLiqFondos = msjCalcularLiqFondos;
	}

	/**
	 * @return the msjError
	 */
	public String getMsjError() {
		return msjError;
	}

	/**
	 * @param msjError
	 *            the msjError to set
	 */
	public void setMsjError(final String msjError) {
		this.msjError = msjError;
	}

	/**
	 * @return the habilitarGenerarComp
	 */
	public boolean isHabilitarGenerarComp() {
		return habilitarGenerarComp;
	}

	/**
	 * @param habilitarGenerarComp
	 *            the habilitarGenerarComp to set
	 */
	public void setHabilitarGenerarComp(final boolean habilitarGenerarComp) {
		this.habilitarGenerarComp = habilitarGenerarComp;
	}

	/**
	 * @return the msjGenerarComp
	 */
	public String getMsjGenerarComp() {
		return msjGenerarComp;
	}

	/**
	 * @param msjGenerarComp
	 *            the msjGenerarComp to set
	 */
	public void setMsjGenerarComp(final String msjGenerarComp) {
		this.msjGenerarComp = msjGenerarComp;
	}

	/**
	 * @return the calculoLiquidacion
	 */
	public CalculoLiquidacion getCalculoLiquidacion() {
		return calculoLiquidacion;
	}

	/**
	 * @param calculoLiquidacion
	 *            the calculoLiquidacion to set
	 */
	public void setCalculoLiquidacion(final CalculoLiquidacion calculoLiquidacion) {
		this.calculoLiquidacion = calculoLiquidacion;
	}

	/**
	 * @return the prestamoSeleccionado
	 */
	public Prestamo getPrestamoSeleccionado() {
		return prestamoSeleccionado;
	}

	/**
	 * @param prestamoSeleccionado
	 *            the prestamoSeleccionado to set
	 */
	public void setPrestamoSeleccionado(final Prestamo prestamoSeleccionado) {
		this.prestamoSeleccionado = prestamoSeleccionado;
	}

	/**
	 * @return the habilitarConsultaLiq
	 */
	public boolean isHabilitarConsultaLiq() {
		return habilitarConsultaLiq;
	}

	/**
	 * @param habilitarConsultaLiq
	 *            the habilitarConsultaLiq to set
	 */
	public void setHabilitarConsultaLiq(final boolean habilitarConsultaLiq) {
		this.habilitarConsultaLiq = habilitarConsultaLiq;
	}

	/**
	 * @return the habilitarConsultaGC
	 */
	public boolean isHabilitarConsultaGC() {
		return habilitarConsultaGC;
	}

	/**
	 * @param habilitarConsultaGC
	 *            the habilitarConsultaGC to set
	 */
	public void setHabilitarConsultaGC(final boolean habilitarConsultaGC) {
		this.habilitarConsultaGC = habilitarConsultaGC;
	}

	/**
	 * @return the liquidacionPrestamo
	 */
	public LiquidacionPrestamo getLiquidacionPrestamo() {
		return liquidacionPrestamo;
	}

	/**
	 * @param liquidacionPrestamo
	 *            the liquidacionPrestamo to set
	 */
	public void setLiquidacionPrestamo(final LiquidacionPrestamo liquidacionPrestamo) {
		this.liquidacionPrestamo = liquidacionPrestamo;
	}

	/**
	 * @return the comprobantePago
	 */
	public ComprobantePago getComprobantePago() {
		return comprobantePago;
	}

	/**
	 * @param comprobantePago
	 *            the comprobantePago to set
	 */
	public void setComprobantePago(final ComprobantePago comprobantePago) {
		this.comprobantePago = comprobantePago;
	}

	/**
	 * @return the dividendosPorPagar
	 */
	public List<DividendoPrestamo> getDividendosPorPagar() {
		return dividendosPorPagar;
	}

	/**
	 * @param dividendosPorPagar
	 *            the dividendosPorPagar to set
	 */
	public void setDividendosPorPagar(final List<DividendoPrestamo> dividendosPorPagar) {
		this.dividendosPorPagar = dividendosPorPagar;
	}

	/**
	 * @return the msjErrorPagInd
	 */
	public String getMsjErrorPagInd() {
		return msjErrorPagInd;
	}

	/**
	 * @param msjErrorPagInd
	 *            the msjErrorPagInd to set
	 */
	public void setMsjErrorPagInd(final String msjErrorPagInd) {
		this.msjErrorPagInd = msjErrorPagInd;
	}

	/**
	 * @return the saldosLiq
	 */
	public List<SaldoLiquidacionPrestamo> getSaldosLiq() {
		return saldosLiq;
	}

	/**
	 * @param saldosLiq
	 *            the saldosLiq to set
	 */
	public void setSaldosLiq(final List<SaldoLiquidacionPrestamo> saldosLiq) {
		this.saldosLiq = saldosLiq;
	}

	/**
	 * @return the comprobantesDePago
	 */
	public List<ComprobantePago> getComprobantesDePago() {
		return comprobantesDePago;
	}

	/**
	 * @param comprobantesDePago
	 *            the comprobantesDePago to set
	 */
	public void setComprobantesDePago(final List<ComprobantePago> comprobantesDePago) {
		this.comprobantesDePago = comprobantesDePago;
	}

	/**
	 * @return the pagindGenerado
	 */
	public boolean isPagindGenerado() {
		return pagindGenerado;
	}

	/**
	 * @param pagindGenerado
	 *            the pagindGenerado to set
	 */
	public void setPagindGenerado(final boolean pagindGenerado) {
		this.pagindGenerado = pagindGenerado;
	}

	public BigDecimal getValorRealFondos() {
		valorRealFondos = calcularValorFondos();
		return valorRealFondos;

	}

	public void setValorRealFondos(final BigDecimal valorRealFondos) {
		this.valorRealFondos = valorRealFondos;
	}

	public boolean isHabilitarLiquidarFondos() {
		habilitarLiquidarFondos = valorRealFondos.longValue() >= calculoLiquidacion
				.getValorPorCancelar().longValue();
		return habilitarLiquidarFondos;

	}

	public void setHabilitarLiquidarFondos(final boolean habilitarLiquidarFondos) {
		this.habilitarLiquidarFondos = habilitarLiquidarFondos;
	}

	public List<RequisitosCruce> getRequisitosCruceCuentas() {
		return requisitosCruceCuentas;
	}

	public void setRequisitosCruceCuentas(
			final List<RequisitosCruce> requisitosCruceCuentas) {
		this.requisitosCruceCuentas = requisitosCruceCuentas;
	}

	public boolean isHabilitarRequisitosCruce() {
		return habilitarRequisitosCruce;
	}

	public void setHabilitarRequisitosCruce(final boolean habilitarRequisitosCruce) {
		this.habilitarRequisitosCruce = habilitarRequisitosCruce;
	}

	/**
	 * @return the prestamoSeleccionadoNovacion
	 */
	public Prestamo getPrestamoSeleccionadoNovacion() {
		return prestamoSeleccionadoNovacion;
	}

	/**
	 * @param prestamoSeleccionadoNovacion
	 *            the prestamoSeleccionadoNovacion to set
	 */
	public void setPrestamoSeleccionadoNovacion(
			final Prestamo prestamoSeleccionadoNovacion) {
		this.prestamoSeleccionadoNovacion = prestamoSeleccionadoNovacion;
	}

	private List<Long> obtenerTiposCreditoValidacion() {
		final List<Long> tiposCredito = new ArrayList<Long>();
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HL);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_GYE);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_UIO);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_MIGRADOS_HOST);
		return tiposCredito;
	}

	private List<String> obtenerEstadosCreditoVig() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CESANTES_CRE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_FONDOS_CRE);
		estados.add(EstadosCredito.ESTADO_VIGENTE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_ANTICIPADA);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CRUSE_FONDOS);
		return estados;
	}

	private List<String> obtenerEstadosCreditoLiq() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_VIGENTE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_ANTICIPADA);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CRUSE_FONDOS);
		return estados;
	}

	private List<String> obtenerEstadosDivPorPagar() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_MORA);
		estados.add(EstadosCredito.ESTADO_REGISTRADO);
		return estados;
	}

	private List<String> obtenerEstadosDivMora() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_MORA);
		return estados;
	}

	private List<String> obtenerEstadosSaldoLiq() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CESANTIAS_DIV);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_FONDOS_DIV);
		return estados;
	}

	private List<Long> obtenerTiposSituacionCredito() {
		final List<Long> tipos = new ArrayList<Long>();
		tipos.add(TiposCredito.TIPO_CREDITO_JUBILADO_HL);
		tipos.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_UIO);
		tipos.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_GYE);
		return tipos;
	}



	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(final Selection selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * @return the selectedItem
	 */
	public Selection getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @return the datos
	 */
	public DatosBean getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(final DatosBean datos) {
		this.datos = datos;
	}

	/**
	 * Metodo que limpia las opciones de recaudacion para un nuevo request.
	 * 
	 */
	public void cleanOptions() {
		habilitarCalcularLiq = false;
		habilitarCalcularLiqFondos = false;
		habilitarRequisitosCruce = true;
		habilitarGenerarComp = false;
		habilitarVerDetalle = false;
		habilitarConsultaLiq = false;
		habilitarConsultaGC = false;
		msjCalcularLiq = null;		
		msjCalcularLiqFondos = null;
		msjGenerarComp = null;
		msjErrorPagInd = null;
		calculoLiquidacion = null;
		numeroLiquidacion = null;
		liquidacionPrestamo = null;
		comprobantePago = null;
		dividendosPorPagar = null;
		saldosLiq = null;
		comprobantesDePago = null;
		pagindGenerado = false;
		datosLiquidacion = null;
		validarMontoCancelar = false;
		msjConsultaGC = null;
		msjDiaHabilCompInd = null;
		msjDiaHabilLiqAnt = null;
		msjDiaHabilCruCue = null;
	}
	
	/**
	 * Metodo que resetea los botones con opciones de recaudacion y regresa a la pagina de seleccion.
	 * 
	 * @return String
	 */
	public String cancelar() {
		resetear();
		
		return "consultaCreditos";
	}
	
	/**
	 * Metodo que envia una alerta al usuario cuando se ha realizado un cruce de cuentas con FR.
	 * 
	 * @param dp
	 */
	private void enviarAlertaCruceFR() {
		try {
			final String templatePath = "ec/fin/biess/creditos/pq/alertas/templates/email/crucefr.ftl";                    
	        /* Parametros del mensaje */
	        final Map<String, Object> alertData = new HashMap<String, Object>();
	        alertData.put("msg_usuario", datos.getSolicitante().getDatosPersonales().getApellidosNombres());
	        alertData.put("msg_fecha", AlertUtil.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss"));
	        /* alertData.put("msg_valor", liquidacionPrestamo.getTotCanLiq()); */
	        final InformacionAfiliado informacionAfiliado = new InformacionAfiliado();
			informacionAfiliado.setApellidosNombres(datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			informacionAfiliado.setCedula(datos.getSolicitante().getDatosPersonales().getCedula());
			informacionAfiliado.setCelular(datos.getSolicitante().getDatosPersonales().getCelular());
			informacionAfiliado.setEmail(datos.getSolicitante().getDatosPersonales().getEmail());
	        AlertUtil.enviarAlerta(alertUserHelper, informacionAfiliado, templatePath, alertData, null, AlertType.EMAIL);
		} catch (final Exception e) {
			LOG.error("Error al enviar alerta de cruce de cuentas al afiliado: " + datos.getSolicitante().getDatosPersonales().getCedula(), e);
		}
	}
	
	/**
	 * Metodo que envia una alerta al usuario cuando se ha generado un comprobante de pago individual.
	 * 
	 */
	private void enviarAlertaComPagInd() {
		try {
			final String templatePath = "ec/fin/biess/creditos/pq/alertas/templates/email/compagind.ftl";                    
	        /* Parametros del mensaje */
	        final Map<String, Object> alertData = new HashMap<String, Object>();
	        alertData.put("msg_usuario", datos.getSolicitante().getDatosPersonales().getApellidosNombres());
	        alertData.put("msg_fecha", AlertUtil.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss"));
	        final InformacionAfiliado informacionAfiliado = new InformacionAfiliado();
			informacionAfiliado.setApellidosNombres(datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			informacionAfiliado.setCedula(datos.getSolicitante().getDatosPersonales().getCedula());
			informacionAfiliado.setCelular(datos.getSolicitante().getDatosPersonales().getCelular());
			informacionAfiliado.setEmail(datos.getSolicitante().getDatosPersonales().getEmail());
	        AlertUtil.enviarAlerta(alertUserHelper, informacionAfiliado, templatePath, alertData, null, AlertType.EMAIL);
		} catch (final Exception e) {
			LOG.error("Error al enviar alerta de generacion de comprobante de pago individual al afiliado: " 
					+ datos.getSolicitante().getDatosPersonales().getCedula(), e);
		}
	}

	/**
	 * Metodo que envia una alerta al usuario cuando se ha generado una liquidacion de credito.
	 * 
	 */
	private void enviarAlertaLiqCredito() {
		try {
			final String templatePath = "ec/fin/biess/creditos/pq/alertas/templates/email/liqcredito.ftl";                    
	        /* Parametros del mensaje */
	        final Map<String, Object> alertData = new HashMap<String, Object>();
	        alertData.put("msg_usuario", datos.getSolicitante().getDatosPersonales().getApellidosNombres());
	        alertData.put("msg_fecha", AlertUtil.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss"));
	        final InformacionAfiliado informacionAfiliado = new InformacionAfiliado();
			informacionAfiliado.setApellidosNombres(datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			informacionAfiliado.setCedula(datos.getSolicitante().getDatosPersonales().getCedula());
			informacionAfiliado.setCelular(datos.getSolicitante().getDatosPersonales().getCelular());
			informacionAfiliado.setEmail(datos.getSolicitante().getDatosPersonales().getEmail());
	        AlertUtil.enviarAlerta(alertUserHelper, informacionAfiliado, templatePath, alertData, null, AlertType.EMAIL);
		} catch (final Exception e) {
			LOG.error("Error al enviar alerta de generacion de liquidacion de credito al afiliado: " 
					+ datos.getSolicitante().getDatosPersonales().getCedula(), e);
		}
	}

	/**
	 * @return the desplegarMensajeComprobanteImpago
	 */
	public boolean isDesplegarMensajeComprobanteImpago() {
		return desplegarMensajeComprobanteImpago;
	}

	/**
	 * @param desplegarMensajeComprobanteImpago the desplegarMensajeComprobanteImpago to set
	 */
	public void setDesplegarMensajeComprobanteImpago(
			final boolean desplegarMensajeComprobanteImpago) {
		this.desplegarMensajeComprobanteImpago = desplegarMensajeComprobanteImpago;
	}

	/**
	 * Selecciona todos los dividendos.
	 * 
	 * @param event
	 */
	public void seleccionarTodos(final ValueChangeEvent event) {
		// INC-2129 Control en Generacion de Comprobantes.
		if(saldosLiq!=null || !saldosLiq.isEmpty()){
			for(final SaldoLiquidacionPrestamo saldoLiquidacionPrestamo: saldosLiq){
				saldoLiquidacionPrestamo.setSeleccionado((Boolean)event.getNewValue());
			}
		}
	}
	
	/**
	 * Selecciona un dividendo.
	 */
	public void seleccionarSaldo() {
		// INC-2129 Control en Generacion de Comprobantes.
		saldoLiquidacionPrestamoSeleccionado.setSeleccionado(true);
	}
	
	/**
	 * Deselecciona un dividendo.
	 */
	public void deseleccionarSaldo() {
		// INC-2129 Control en Generacion de Comprobantes.
		saldoLiquidacionPrestamoSeleccionado.setSeleccionado(false);
		this.setTodos(false);
	}
	
	/**
	 * Devuelve la fecha desde la cual se validan el numero de novaciones para jubilados
	 * 
	 * @return
	 */
	private Date obtenerFechaNovacionesJubilado() {
		Date fechaNovJub = null;
		String fechaDato = "";
		try {
			fechaDato = this.parametroBiessDao.consultarPorIdNombreParametro(ConfiguracionPQEnum.FECHA_CONTAR_NOVACION_JUB.getIdParametro(),
					ConfiguracionPQEnum.FECHA_CONTAR_NOVACION_JUB.getNombreParametro()).getValorCaracter();
		} catch (final ParametroBiessException e) {
			LOG.error("Error al consultar la fecha de validacion de novaciones de jubilados", e);
		}
		
		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			fechaNovJub = formatter.parse(fechaDato);
		} catch (final ParseException e) {
			LOG.error("Error al parsear la fecha de novaciones de jubilados", e);
		}

		return fechaNovJub;
	}

	/**
	 * @return the todos
	 */
	public boolean isTodos() {
		return todos;
	}

	/**
	 * @param todos the todos to set
	 */
	public void setTodos(final boolean todos) {
		this.todos = todos;
	}

	/**
	 * @return the saldoLiquidacionPrestamoSeleccionado
	 */
	public SaldoLiquidacionPrestamo getSaldoLiquidacionPrestamoSeleccionado() {
		return saldoLiquidacionPrestamoSeleccionado;
	}

	/**
	 * @param saldoLiquidacionPrestamoSeleccionado the saldoLiquidacionPrestamoSeleccionado to set
	 */
	public void setSaldoLiquidacionPrestamoSeleccionado(final SaldoLiquidacionPrestamo saldoLiquidacionPrestamoSeleccionado) {
		this.saldoLiquidacionPrestamoSeleccionado = saldoLiquidacionPrestamoSeleccionado;
	}

	public TipoProductosBean getProductos() {
		return productos;
	}

	public void setProductos(final TipoProductosBean productos) {
		this.productos = productos;
	}

	/**
	 * Permite obtener la fecha actual en el formato anio mes dia para la simulacion
	 * de liquidacion
	 * 
	 * @return
	 */
	private String obtenerFechaActual() {
		final Calendar cl = Calendar.getInstance();
		String fecha = null;
		try {
			fecha = FuncionesUtilesSac.obtenerFechaSac(cl.getTime());
		} catch (final ParseException e) {
			LOG.error("Error al parsear la fecha liquidacion SAC", e);
		}
		return fecha;
	}



	/**
	 * Permite fabricar la entidad de persistencia transaccion recaudacion
	 * 
	 * @param detalle
	 * @return
	 */
	private TransaccionRecaudacion construirTransaccion() {
		final Calendar calendar = Calendar.getInstance();
		final Prestamo prestamo = validarRecaudacion.getPrestamo();
		final TransaccionRecaudacion transaccionRecaudacion = new TransaccionRecaudacion();
		final TransaccionRecaudacionPk pk = new TransaccionRecaudacionPk();
		pk.setTrIdtipotransaccion(34L);
		pk.setIdTransaccion(transaccionRecaudacionDao.obtenerValorSecuencial("IESS_OWNER.REC_TRANSACCION_SEQ"));
		transaccionRecaudacion.setPk(pk);
		transaccionRecaudacion.setTrNut(BigDecimal.valueOf(prestamo.getNut()));
		transaccionRecaudacion.setCedula(prestamo.getNumafi());
		transaccionRecaudacion.setAnioPeriodo(BigDecimal.valueOf(calendar.get(Calendar.YEAR)));
		transaccionRecaudacion.setMesPeriodo(BigDecimal.valueOf(calendar.get(Calendar.MONTH)+1));
		transaccionRecaudacion.setMaximoDebitar(BigDecimal.ZERO);
		transaccionRecaudacion.setTrIdgaf(BigDecimal.valueOf(prestamo.getNumOperacionSAC()));
		transaccionRecaudacion.setValorCobrar(liquidacionPrestamo.getTotCanLiq());
		transaccionRecaudacion.setValorRecaudado(liquidacionPrestamo.getTotCanLiq());
		transaccionRecaudacion.setInteresesMora(liquidacionPrestamo.getSumIntMor());
		transaccionRecaudacion.setObservaciones("CANCELADO POR CRUCE DE CUENTAS");
		transaccionRecaudacion.setReferenciaCancelacion(null);
		transaccionRecaudacion.setValorDividendo(liquidacionPrestamo.getSumDivTot());
		transaccionRecaudacion.setEstado("EMI");
		transaccionRecaudacion.setFechaProceso(calendar.getTime());
		transaccionRecaudacion.setFechaMaximaPago(calendar.getTime());
		transaccionRecaudacion.setFechaCancelacion(calendar.getTime());
		transaccionRecaudacion.setNumeroSolicitudServida(BigDecimal.valueOf(prestamo.getNumsolser()));
		transaccionRecaudacion.setCodigoTipoSolicitudServida(BigDecimal.valueOf(prestamo.getCodtipsolser()));
		return transaccionRecaudacion;
	}


	/**
	 * Permite ver el detalle del credito
	 * 
	 * @return
	 */
	public String verDetallePrestamo() {
		datos.setPaginaOrigen("calculoLiquidacion");
		return "detallePrestamo";
	}
	
	/**
	 * Permite ver el detalle del credito
	 * @return
	 */
	public String verDetallePrestamoLiqFondos() {
		datos.setPaginaOrigen("calculoLiquidacionFondos");
		return "detallePrestamo";
	}

	public String mostrarDetalle() {
		setearPrestamoSeleccionado();
		datos.setPaginaOrigen(null);
		return "detallePrestamo";
	}

	public CreditoExigibleDto getDatosLiquidacion() {
		return datosLiquidacion;
	}

	public void setDatosLiquidacion(final CreditoExigibleDto datosLiquidacion) {
		this.datosLiquidacion = datosLiquidacion;
	}

	public BigDecimal getSumaValoresSeleccionados() {
		return sumaValoresSeleccionados;
	}

	public void setSumaValoresSeleccionados(final BigDecimal sumaValoresSeleccionados) {
		this.sumaValoresSeleccionados = sumaValoresSeleccionados;
	}

	public BigDecimal getValorPersonalizado() {
		return valorPersonalizado;
	}

	public void setValorPersonalizado(final BigDecimal valorPersonalizado) {
		this.valorPersonalizado = valorPersonalizado;
	}

	public boolean isValidarMontoCancelar() {
		return validarMontoCancelar;
	}

	public void setValidarMontoCancelar(final boolean validarMontoCancelar) {
		this.validarMontoCancelar = validarMontoCancelar;
	}

	/**
	 * @return the listaDividendosSac
	 */
	public List<DatoDeudaDto> getListaDividendosSac() {
		return listaDividendosSac;
	}

	/**
	 * @param listaDividendosSac the listaDividendosSac to set
	 */
	public void setListaDividendosSac(final List<DatoDeudaDto> listaDividendosSac) {
		this.listaDividendosSac = listaDividendosSac;
	}

	/**
	 * @return the dividendosSacCheck
	 */
	public Map<DatoDeudaDto, Boolean> getDividendosSacCheck() {
		return dividendosSacCheck;
	}

	/**
	 * @param dividendosSacCheck the dividendosSacCheck to set
	 */
	public void setDividendosSacCheck(final Map<DatoDeudaDto, Boolean> dividendosSacCheck) {
		this.dividendosSacCheck = dividendosSacCheck;
	}

	/**
	 * @return the habilitaSeleccionDividendo
	 */
	public boolean isHabilitaSeleccionDividendo() {
		return habilitaSeleccionDividendo;
	}

	/**
	 * @param habilitaSeleccionDividendo the habilitaSeleccionDividendo to set
	 */
	public void setHabilitaSeleccionDividendo(final boolean habilitaSeleccionDividendo) {
		this.habilitaSeleccionDividendo = habilitaSeleccionDividendo;
	}

	/**
	 * @return the totalDividendos
	 */
	public BigDecimal getTotalDividendos() {
		return totalDividendos;
	}

	/**
	 * @param totalDividendos the totalDividendos to set
	 */
	public void setTotalDividendos(final BigDecimal totalDividendos) {
		this.totalDividendos = totalDividendos;
	}

	/**
	 * @return the totalMoraDividendos
	 */
	public BigDecimal getTotalMoraDividendos() {
		return totalMoraDividendos;
	}

	/**
	 * @param totalMoraDividendos the totalMoraDividendos to set
	 */
	public void setTotalMoraDividendos(final BigDecimal totalMoraDividendos) {
		this.totalMoraDividendos = totalMoraDividendos;
	}

	/**
	 * @return the esMontoPersonalizado
	 */
	public boolean isEsMontoPersonalizado() {
		return esMontoPersonalizado;
	}

	/**
	 * @param esMontoPersonalizado the esMontoPersonalizado to set
	 */
	public void setEsMontoPersonalizado(final boolean esMontoPersonalizado) {
		this.esMontoPersonalizado = esMontoPersonalizado;
	}

	/**
	 * @return the lstDivPersonalizadoSac
	 */
	public List<DatoDeudaDto> getLstDivPersonalizadoSac() {
		return lstDivPersonalizadoSac;
	}

	/**
	 * @param lstDivPersonalizadoSac the lstDivPersonalizadoSac to set
	 */
	public void setLstDivPersonalizadoSac(final List<DatoDeudaDto> lstDivPersonalizadoSac) {
		this.lstDivPersonalizadoSac = lstDivPersonalizadoSac;
	}

	/**
	 * @return the montoTotalDividendos
	 */
	public BigDecimal getMontoTotalDividendos() {
		return montoTotalDividendos;
	}

	/**
	 * @param montoTotalDividendos the montoTotalDividendos to set
	 */
	public void setMontoTotalDividendos(final BigDecimal montoTotalDividendos) {
		this.montoTotalDividendos = montoTotalDividendos;
	}

	/**
	 * @return the mostrarResultSimulacion
	 */
	public boolean isMostrarResultSimulacion() {
		return mostrarResultSimulacion;
	}

	/**
	 * @param mostrarResultSimulacion the mostrarResultSimulacion to set
	 */
	public void setMostrarResultSimulacion(final boolean mostrarResultSimulacion) {
		this.mostrarResultSimulacion = mostrarResultSimulacion;
	}

	/**
	 * @return the montoMaximo
	 */
	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	/**
	 * @param montoMaximo the montoMaximo to set
	 */
	public void setMontoMaximo(final BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	/**
	 * @return the accionGenCompIndivActivo
	 */
	public boolean isAccionGenCompIndivActivo() {
		return accionGenCompIndivActivo;
	}

	/**
	 * @param accionGenCompIndivActivo the accionGenCompIndivActivo to set
	 */
	public void setAccionGenCompIndivActivo(final boolean accionGenCompIndivActivo) {
		this.accionGenCompIndivActivo = accionGenCompIndivActivo;
	}
	
	public String getMsjConsultaGC() {
		return msjConsultaGC;
	}

	public void setMsjConsultaGC(final String msjConsultaGC) {
		this.msjConsultaGC = msjConsultaGC;
	}

	public boolean isMuestraDetalleNov() {
		return muestraDetalleNov;
	}

	public void setMuestraDetalleNov(final boolean muestraDetalleNov) {
		this.muestraDetalleNov = muestraDetalleNov;
	}
	public List<RequisitoNovacion> getRequisitosNova() {
		return requisitosNova;
	}
	public void setRequisitosNova(final List<RequisitoNovacion> requisitosNova) {
		this.requisitosNova = requisitosNova;
	}
	public boolean isGenCompIndivSaldebActivo() {
		return genCompIndivSaldebActivo;
	}
	public void setGenCompIndivSaldebActivo(final boolean genCompIndivSaldebActivo) {
		this.genCompIndivSaldebActivo = genCompIndivSaldebActivo;
	}
	public String getTipoTicketPersonal() {
		return tipoTicketPersonal;
	}
	public void setTipoTicketPersonal(String tipoTicketPersonal) {
		this.tipoTicketPersonal = tipoTicketPersonal;
	}
	public boolean isEsSaldeb() {
		return esSaldeb;
	}
	public void setEsSaldeb(boolean esSaldeb) {
		this.esSaldeb = esSaldeb;
	}
	public boolean isEsConsultaComprobante() {
		return esConsultaComprobante;
	}
	public void setEsConsultaComprobante(boolean esConsultaComprobante) {
		this.esConsultaComprobante = esConsultaComprobante;
	}
	public String getPorcentajeNovacion() {
		return porcentajeNovacion;
	}
	public void setPorcentajeNovacion(String porcentajeNovacion) {
		this.porcentajeNovacion = porcentajeNovacion;
	}
	
	public String getPeriodoComprobanteCesante() {
		return periodoComprobanteCesante;
	}
	public void setPeriodoComprobanteCesante(String periodoComprobanteCesante) {
		this.periodoComprobanteCesante = periodoComprobanteCesante;
	}
	public String getPeriodoComprobanteVoluntario() {
		return periodoComprobanteVoluntario;
	}
	public void setPeriodoComprobanteVoluntario(String periodoComprobanteVoluntario) {
		this.periodoComprobanteVoluntario = periodoComprobanteVoluntario;
	}
	public String getPeriodoComprobanteUnipersonal() {
		return periodoComprobanteUnipersonal;
	}
	public void setPeriodoComprobanteUnipersonal(String periodoComprobanteUnipersonal) {
		this.periodoComprobanteUnipersonal = periodoComprobanteUnipersonal;
	}
	public String getPeriodoComprobanteJubilado() {
		return periodoComprobanteJubilado;
	}
	public void setPeriodoComprobanteJubilado(String periodoComprobanteJubilado) {
		this.periodoComprobanteJubilado = periodoComprobanteJubilado;
	}
	public String getPeriodoComprobanteAfiliado() {
		return periodoComprobanteAfiliado;
	}
	public void setPeriodoComprobanteAfiliado(String periodoComprobanteAfiliado) {
		this.periodoComprobanteAfiliado = periodoComprobanteAfiliado;
	}	
	public String getVencimientoComprobante() {
		return vencimientoComprobante;
	}
	public void setVencimientoComprobante(String vencimientoComprobante) {
		this.vencimientoComprobante = vencimientoComprobante;
	}
	public StringBuilder getTipoComprobanteSB() {
		return tipoComprobanteSB;
	}
	public void setTipoComprobanteSB(StringBuilder tipoComprobanteSB) {
		this.tipoComprobanteSB = tipoComprobanteSB;
	}
	public String getMsjDiaHabilCompInd() {
		return msjDiaHabilCompInd;
	}
	public void setMsjDiaHabilCompInd(String msjDiaHabilCompInd) {
		this.msjDiaHabilCompInd = msjDiaHabilCompInd;
	}
	public String getMsjDiaHabilLiqAnt() {
		return msjDiaHabilLiqAnt;
	}
	public void setMsjDiaHabilLiqAnt(String msjDiaHabilLiqAnt) {
		this.msjDiaHabilLiqAnt = msjDiaHabilLiqAnt;
	}
	public String getMsjDiaHabilCruCue() {
		return msjDiaHabilCruCue;
	}
	public void setMsjDiaHabilCruCue(String msjDiaHabilCruCue) {
		this.msjDiaHabilCruCue = msjDiaHabilCruCue;
	}
	public boolean isHabilitaMensajeDebito() {
		return habilitaMensajeDebito;
	}
	public void setHabilitaMensajeDebito(boolean habilitaMensajeDebito) {
		this.habilitaMensajeDebito = habilitaMensajeDebito;
	}
	public boolean isPanelMensajes() {
		return panelMensajes;
	}
	public void setPanelMensajes(boolean panelMensajes) {
		this.panelMensajes = panelMensajes;
	}
		
}