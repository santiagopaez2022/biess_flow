/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */

package ec.gov.iess.pq.concesion.web.backing;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.richfaces.component.html.HtmlTab;

import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.alerts.modelo.InformacionAfiliado;
import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.alertas.util.AlertUtil;
import ec.fin.biess.creditos.pq.dao.CambioClaveDao;
import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;
import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoIdentificacionSacEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoInformacionServicioIessEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoProductoEnum;
import ec.fin.biess.creditos.pq.excepcion.MontosMaximosException;
import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.excepcion.TablaAmortizacionException;
import ec.fin.biess.creditos.pq.modelo.dto.ValidarRequisitosPrecalificacionBiess;
import ec.fin.biess.creditos.pq.servicio.InformacionIessServicio;
import ec.fin.biess.creditos.pq.servicio.ParametroCreditoServicio;
import ec.fin.biess.creditos.pq.servicio.PersonalizarCreditoService;
import ec.fin.biess.creditos.pq.servicio.PrecalificacionListaObservadosServicioLocal;
import ec.fin.biess.creditos.pq.servicio.PrestamoCalculoService;
import ec.fin.biess.creditos.pq.servicio.TipoCuentaBiessServicio;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametrizacionPQException;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.listaobservados.constant.ConstantesListaObservadosWS;
import ec.fin.biess.listaobservados.exception.BloqueoListaControlException;
import ec.fin.biess.listaobservados.modelo.persistence.BloqueoListaControl;
import ec.fin.biess.listaobservados.service.BloqueoListaControlServicioLocal;
import ec.fin.biess.matriz.exception.BiessDivisionPoliticaException;
import ec.fin.biess.matriz.modelo.persistence.BiessDivisionPolitica;
import ec.fin.biess.matriz.service.BiessDivisionPoliticaServicioLocal;
import ec.fin.biess.service.ParametrizacionPQServicioLocal;
import ec.fin.biess.shop.integration.webservice.BiessShopService;
import ec.fin.biess.shop.integration.webservice.OrdenWSService;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.dinamico.dto.Campo;
import ec.gov.iess.creditos.dinamico.dto.DataNotificaProvAprRequestDto;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.OrigenJubilado;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TipoSimulacionCredito;
import ec.gov.iess.creditos.enumeracion.TipoTablaEnum;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.excepcion.ParametroNoEncontradoException;
import ec.gov.iess.creditos.focalizados.dto.ProductoDto;
import ec.gov.iess.creditos.modelo.dto.CalculoCredito;
import ec.gov.iess.creditos.modelo.dto.DatosCatalogo;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.dto.DetalleCalculoEgresos;
import ec.gov.iess.creditos.modelo.dto.DividendoCalculo;
import ec.gov.iess.creditos.modelo.dto.Empleador;
import ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera;
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.dto.PlazoCreditoSinDocumentoFiduciario;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosFondos;
import ec.gov.iess.creditos.modelo.persistencia.Catalogo;
import ec.gov.iess.creditos.modelo.persistencia.CreditoValidacion;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.EstadoDividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.EstadoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.TipoDividendo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.VariablePrestamoPK;
import ec.gov.iess.creditos.otp.dto.NotificacionOTP;
import ec.gov.iess.creditos.pq.dto.EvaluaReglaMontoMinimoDto;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.PrestamoRequestDto;
import ec.gov.iess.creditos.pq.dto.TablaAmortizacionSac;
import ec.gov.iess.creditos.pq.dto.migracion.cartera.DataPersonalizacionDto;
import ec.gov.iess.creditos.pq.excepcion.BloqueoFinSemanaException;
import ec.gov.iess.creditos.pq.excepcion.CabeceraCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.CalculoCreditoException;
import ec.gov.iess.creditos.pq.excepcion.CodigoReservaPaqueteTuristicoException;
import ec.gov.iess.creditos.pq.excepcion.CondicionCalculoException;
import ec.gov.iess.creditos.pq.excepcion.ConecSrvPqDinamicoException;
import ec.gov.iess.creditos.pq.excepcion.ConsultaDataPqDinamicoException;
import ec.gov.iess.creditos.pq.excepcion.CrearCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.DiasNoPermitidosNovacionException;
import ec.gov.iess.creditos.pq.excepcion.MontoMinimoException;
import ec.gov.iess.creditos.pq.excepcion.NovarCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.OTPException;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionListaObservadosException;
import ec.gov.iess.creditos.pq.excepcion.PrestamoException;
import ec.gov.iess.creditos.pq.excepcion.PrestamosTuristicosException;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaBloqueadaException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio;
import ec.gov.iess.creditos.pq.servicio.CalculoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.CatalogoServicio;
import ec.gov.iess.creditos.pq.servicio.CondicionCalculoServicio;
import ec.gov.iess.creditos.pq.servicio.CreditoPQEmpSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoValidacionServicioLocal;
import ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiasSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.OTPServicioLocal;
import ec.gov.iess.creditos.pq.servicio.ParametroServicio;
import ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoDinamicoLocalService;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoTuristicoLocalService;
import ec.gov.iess.creditos.pq.servicio.SimularCreditoServicio;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.pq.util.EstadosCredito;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;
import ec.gov.iess.creditos.pq.util.PrecalificacionUtil;
import ec.gov.iess.creditos.pq.util.TiposCredito;
import ec.gov.iess.creditos.turismo.dto.PaqueteTurismoInfoDto;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancaria;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancariaAfiliado;
import ec.gov.iess.cuentabancaria.modelo.enumeracion.EstadoCuentaAfiliadoEnum;
import ec.gov.iess.cuentabancaria.servicio.CuentaBancariaAfiliadoServicio;
import ec.gov.iess.cuentabancaria.servicio.InstitucionFinancieraServicio;
import ec.gov.iess.hl.exception.AfiliadoException;
import ec.gov.iess.hl.modelo.Afiliado;
import ec.gov.iess.hl.servicio.AfiliadoServicio;
import ec.gov.iess.hl.servicio.DivisionPoliticaServicio;
import ec.gov.iess.pq.concesion.focalizados.backing.OllasFocalizadosBacking;
import ec.gov.iess.pq.concesion.focalizados.backing.PqFocalizadosBacking;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.enumeration.ParametrosGeneralesEnum;
import ec.gov.iess.pq.concesion.web.enumeration.ValidacionPrestamoEnum;
import ec.gov.iess.pq.concesion.web.exception.ValidacionMontoCtaBcoException;
import ec.gov.iess.pq.concesion.web.helper.AuditoriaPqWebHelper;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.DireccionIPUtil;
import ec.gov.iess.pq.concesion.web.util.EncriptacionUtil;
import ec.gov.iess.pq.concesion.web.util.SimulacionCreditoUtil;
import ec.gov.iess.pq.concesion.web.util.Utilities;
import ec.gov.iess.pq.concesion.web.validator.EmailValidator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 *
 * <b> Bean para la pantalla de concesi�n de cr�ditos. </b>
 *
 * @author cbastidas
 * @version $Revision: 1.38.2.2 $
 *          <p>
 *          [$Author: cbastidas $, $Date: 2011/06/07 22:07:01 $]
 *          </p>
 */
public class ConcesionCreditoBean extends BaseBean implements Serializable {




	private static final java.lang.String MSG_PLAZ_MAX_CAT = "El plazo ingresado para este producto es mayor al permitido: %d meses";

	private static final String FORM_AUT_DINAMICO = "ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferenciaDinamicoCondiciones.ftl";

	private static final long serialVersionUID = 1332648752021634908L;

	private static final String STR_LABEL = "labels";

	private static final String STR_MESSAGES = "messages";

	private transient LoggerBiess log = LoggerBiess.getLogger(ConcesionCreditoBean.class);

	@EJB(name = "PrestamoDinamicoImpl/local")
	private PrestamoDinamicoLocalService prestamoDinamico;

	@EJB(name = "CalculoCreditoServicioImpl/local")
	private CalculoCreditoServicio calculoCreditoServicio;

	@EJB(name = "SimulacionCreditoServicioImpl/local")
	private SimularCreditoServicio simularCreditoServicio;

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;

	// Cambio para control de fraudes.
	@EJB(name = "AdministracionOrdenCompraProveedorServicioImpl/local")
	private AdministracionOrdenCompraProveedorServicio administracionOrdenCompraProveedorServicio;

	// cambio para control de fraudes 19/08/2010
	@EJB(name = "CuentaBancariaAfiliadoServicioImpl/local")
	private CuentaBancariaAfiliadoServicio cuentabancariaafiliadoservicio;

	@EJB(name = "ParametroServicioImpl/local")
	private ParametroServicio parametroservicio;

	// fin cambio 19/08/2010

	// cambio para destino del credito
	@EJB(name = "CatalogoServicioImpl/local")
	private CatalogoServicio catalogoServicio;

	/* Omar Villanueva. 09/01/2013. EJB para leer los parametros SBU y NUMSBU */
	@EJB(name = "ParametroCreditoServicioImpl/local")
	private ParametroCreditoServicio parametroCreditoServicio;

	@EJB(name = "AlertUserHelperImpl/local")
	private AlertUserHelper alertUserHelper;

	@EJB(name = "CambioClaveDaoEjb/local")
	private CambioClaveDao cambioClaveDao;

	@EJB(name = "CondicionCalculoServicioImpl/local")
	private CondicionCalculoServicio condicionCalculoServicio;

	@EJB(name = "ParametrizacionPQServicioImpl/local")
	private ParametrizacionPQServicioLocal parametrizacionPQServicio;

	@EJB(name = "GarantiaCreditoServicioImpl/local")
	private GarantiaCreditoServicio garantiaCreditoServicio;

	@EJB(name = "InformacionIessServicioImpl/local")
	private InformacionIessServicio informacionIessServicio;

	@EJB(name = "PrestamoTuristicoServiceImpl/local")
	private PrestamoTuristicoLocalService prestamoTuristicoServicio;

	@EJB(name = "CreditoPQOpEmplSacServicioImpl/local")
	private CreditoPQEmpSacServicioLocal creditoPqClientesEmpl;
	
	@EJB(name = "GarantiasSacServicioImpl/local")
	private GarantiasSacServicioLocal garantiasSAC;
	
	// propiedades del componente

	private boolean cargado = false;
	private boolean cumpleMontoMaximo = false;

	private PrestamoCalculo prestamoCalculo;

	private OpcionCredito opcionSimMonto = new OpcionCredito();

	private OpcionCredito opcionSimCuota = new OpcionCredito();

	private String panelActivoPrincipal; //htmlTabPanel panel principal

	private boolean deshabilitarAmortizacion; //htmlTabAmo pestania amortizacion

	private boolean deshabilitarSimulacion; //htmlTabSim pestania simulacion

	private boolean renderizarPanelPrincpal; // htmlTabPanel contiene pensanias anteriores


	private HtmlTab htmlTabRef;// pestania referencia

	private CalculoCredito calculoCredito;

	private Simulacion simulacion;

	private DatosBean datos;

	private PqFocalizadosBacking pqFocalizados;
	private OllasFocalizadosBacking ollasFocalizados;

	//OTP
	private String codigoOtpIngresado;

	private boolean prestamoOk = false;

	private boolean bloquearCreditoServicios = false;

	private String message;

	private String msgNovNegativa;

	private String menssageOrdenOK;

	private boolean opcionSimMontoCargado = false;

	private boolean opcionSimCuotaCargado = false;

	private boolean activarCalculoCredito = false;

	private String panelActivoSimulacion;

	// private DatosCredito datosCredito = new DatosCredito();
	private DatosCredito datosCredito = null;

	private boolean prestamoQuirografarioProcesado = false;

	private int procesado = 0;

	// Cambios andres cantos 18/08/2011 pq-fraudes
	private String popupvalidacionrc = "VRC";
	private boolean flagvalidacionrc = true;
	private boolean flagcambioclave = true;
	private boolean validacionmontoctabco = true;

	/* INC-1800. Control PDA para jubilados */
	private boolean flagcambioinucta = false;

	// cambios andres cantos 18/10/2011
	private boolean vermensajetienda = false;

	// INC-2292 Mejora Refugiados.
	private boolean prestamoRefugiadoFallido = false;

	// INC-2344 Pensiones Alimenticias
	@EJB(name = "InstitucionFinancieraServicioImpl/local")
	private transient InstitucionFinancieraServicio institucionFinancieraServicio;

	@EJB(name = "DivisionPoliticaServicioImpl/local")
	private DivisionPoliticaServicio divisionPoliticaServicio;

	@EJB(name = "TipoCuentaBiessServicioImpl/local")
	private TipoCuentaBiessServicio tipoCuentaBiessServicio;

	private String textoFAT;

	private boolean desplegarFAT = false;

	private String msjCreditoListaObservadosPep ;
	private String msjImprimirFormPensiones;
	private String msjPensionesAlimenticiasFinTit;
	private String msjPqInformacion3Otp;


	// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
	@EJB(name = "BloqueoListaControlServicioImpl/local")
	private BloqueoListaControlServicioLocal bloqueoListaControlServicio;

	//servicio tabla amortizacion DS
	@EJB(name="PersonalizarCreditoServiceImpl/local")
	private PersonalizarCreditoService personalizarCreditoService;

	@EJB(name="PrestamoCalculoServiceImpl/local")
	private PrestamoCalculoService prestamoCalculoService;

	// INC-2588 Implementacion automatizada de
	// verificacion en lista de control de usuarios PQ.
	// Servicio para validacion de listas de observados
	@EJB(name = "PrecalificacionListaObservadosServicioImpl/local")
	private transient PrecalificacionListaObservadosServicioLocal precalListObsServicio;

	// Servicio para obtener el codigo de la provincia en base
	// a la cedula para la impresion de notificacion del CONSEP
	@EJB(name = "BiessDivisionPoliticaServicioImpl/local")
	private transient BiessDivisionPoliticaServicioLocal biessDivPolServicio;

	// Obtiene informacion de la precalificacion para realizar
	// la verificacion de listas de observados
	@EJB(name = "PrecalificacionServicioUsaResumenImpl/local")
	private transient PrecalificacionServicio precalServicio;

	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;

	@EJB(name = "CreditoValidacionServicioImpl/local")
	private transient CreditoValidacionServicioLocal creditoValidacionServicio;

	@EJB(name = "OTPServicioImpl/local")
	private transient OTPServicioLocal otpServicio;

	/* Servicio para consultar datos del afiliado */
	@EJB(name = "AfiliadoServicioImpl/local")
	private transient AfiliadoServicio afiliadoServicio;

	// Variable para la impresion del codigo de la notificacion del CONSEP
	private String codigoNotificacion;

	// Mensaje de error al conceder el credito
	private static final String MENSAJE_ERROR_CONCESION = "SE PRODUJO UN ERROR EN LA CONCESI\u00D3N DEL CR\u00C9DITO.: ";

	// Mensaje para intentar mas tarde realizar el credito
	private static final String MENSAJE_INTENTE_TARDE = " POR FAVOR INTENTE M\u00C1S TARDE";

	// Almacena la informacion de la pre calificacion
	private Precalificacion precalificacion;


	/**
	 * Constante para almacenar el Credito especial
	 * en la tabla para producto viaja ecuador
	 */
	private static final long COD_PROD_VIAECU = 2l;

	// Cambio de servicio comun tabla amortizacion
	private BigDecimal totalCapitalAbonado = BigDecimal.ZERO;
	private List<SelectItem> tipoTablaItems = new ArrayList<SelectItem>();

	private BigDecimal capacidadPago;

	private BigDecimal sbu = BigDecimal.ZERO;

	private BigDecimal numsbu = BigDecimal.ZERO;

	private BigDecimal plazoMaximoEmergente;

	private BigDecimal montoMaximoEmergente;

	private int mesesGraciaEmergente;

	private int contadorIntentosOTP = 0;
	private int maximoIntentosOtpFocalizados;
	private int numeroDiasCaducidad;
	private String errorIngresoOtp;
	private List<ProductoDto> listaProductosFocalizados;
	private int contadorProductosFocalizados;
	private String idTransaccion;
	private String mensajeValidacionOtp;
	private String mensajeTiempoOtp;
	private String cerrarSesionOtp;
	private String tituloValidacionOtp;

	private CalculoCreditoHelperSingleton calculoCreditoHelper;
	private int edadAsegurado;

	private boolean aceptaServicio;
	private boolean validaAceptaServicio;
	private boolean presentaValidacion;
	private boolean continuaValidacionOTP;
	private int longitudOTP;
	private boolean mensajeEmail;
	private boolean seguirOtp;
	private boolean seguirInicial;

	// Ecuador tu lugar en el mundo
	private String codigoReservaEcuTur;
	private String descripcionPaqueteTuristico;
	private BigDecimal montoPaqueteTuristico;
	private String condicionesFATEcuTur;
	private int intentosReserva = 0;
	private int intentosMaximoReserva = Integer.parseInt(super.getResource(STR_LABEL, "ecuador.turistico.maximo.intentos.reserva"));

	private static final String DESTINOS_PRODUCTIVOS = "14,15,16,17,18,19";
	// Migracion cartera -pasamped
	private BigDecimal montoMaxSimulacion;

	//VARIABLES PARA PQ DINAMICO
	/**
	 * variable que se llena cuando se tiene un monto menor
	 */
	private boolean esMtnMenProv;
	/**
	 * variable que se llena con el valor ingresado por el usuario
	 */
	private String valIngCred;
	/**
	 * variable con el valor del proveedor
	 */
	private String valPacProv;
	/**
	 * diferencia entre el monto del proveedor y el valor ingresado
	 */
	private String difValPag;

    
    //MIGRACION CARTERA
    /**
     * bandera para habilitar o deshabiltar el boton para calcular la cuota
     */
    private boolean deshabilitarCalcularCuota=Boolean.TRUE;
	
	public boolean isVermensajetienda() {
		return vermensajetienda;
	}

	public void setVermensajetienda(final boolean vermensajetienda) {
		this.vermensajetienda = vermensajetienda;
	}

	/**
	 * @return the popupvalidacionrc
	 */
	public String getPopupvalidacionrc() {
		return popupvalidacionrc;
	}

	// fin cambios 18/08/2011
	public boolean isOpcionSimMontoCargado() {
		return opcionSimMontoCargado;
	}

	public void setOpcionSimMontoCargado(final boolean opcionSimMontoCargado) {
		this.opcionSimMontoCargado = opcionSimMontoCargado;
	}

	public boolean isOpcionSimCuotaCargado() {
		return opcionSimCuotaCargado;
	}

	public void setOpcionSimCuotaCargado(final boolean opcionSimCuotaCargado) {
		this.opcionSimCuotaCargado = opcionSimCuotaCargado;
	}

	/**
	 * @return the prestamoOk
	 */
	public boolean isPrestamoOk() {
		return prestamoOk;
	}

	/**
	 * @param prestamoOk
	 *            the prestamoOk to set
	 */
	public void setPrestamoOk(final boolean prestamoOk) {
		this.prestamoOk = prestamoOk;
	}

	public ConcesionCreditoBean() {
	}

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(final DatosBean datos) {
		this.datos = datos;
	}

	public boolean isCargado() {
		return cargado;
	}

	public void setCargado(final boolean cargado) {
		this.cargado = cargado;
	}

	public CalculoCredito getCalculoCredito() {
		return calculoCredito;
	}

	public void setCalculoCredito(final CalculoCredito calculoCredito) {
		this.calculoCredito = calculoCredito;
	}

	// acciones de la aplicacion

	public boolean isCumpleMontoMaximo() {
		return cumpleMontoMaximo;
	}

	public void setCumpleMontoMaximo(final boolean cumpleMontoMaximo) {
		this.cumpleMontoMaximo = cumpleMontoMaximo;
	}

	// cambios angel sarmiento
	private ArrayList<DatosCatalogo> dtocatalogo = new ArrayList<DatosCatalogo>();

	private DatosCatalogo datodto;

	private String error = null;

	public String getError() {
		return error;
	}

	public void setError(final String error) {
		this.error = error;
	}

	public ArrayList<DatosCatalogo> getDtocatalogo() {
		return dtocatalogo;
	}

	public void setDtocatalogo(final ArrayList<DatosCatalogo> dtocatalogo) {
		this.dtocatalogo = dtocatalogo;
	}

	private List<Catalogo> listCatalogo = null;

	@PostConstruct
	public void init() {
		// En caso que haya bloqueo de fin de semana y no haya seleccionado producto focalizado redirecciona a pagina de
		// bloqueo
		if (datos.isBloqueoFinesSemana()
				&& (!CategoriaTipoProductoPq.CAT_FOCALIZADOS.equals(datos.getCategoriaTipoProductoPq())
				&& TiposProductosPq.ECU_TUR != this.datos.getTiposProductosPq())
				&& (TiposProductosPq.DINAMICO != this.datos.getTiposProductosPq())) {
			try {
				getResponse().sendRedirect(getContextPath() + "/pages/concesion/bloqueoFinSemana.jsf");
			} catch (final IOException e) {
				log.error("Error al redireccionar a bloqueo de fin de semana", e);
			}
		}

		/* Obtener el NUMSBU Y SBU */
		try {
			this.sbu = this.parametroCreditoServicio.obtenerValorSBU();
			this.numsbu = this.parametroCreditoServicio.obtenerValorNUMSBU();
			this.plazoMaximoEmergente = this.parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.PLAZO_MAXIMO.getIdParametro(),
					BiessEmergenteEnum.PLAZO_MAXIMO.getNombreParametro()).getValorNumerico();
			this.mesesGraciaEmergente = this.parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MESES_GRACIA.getIdParametro(),
					BiessEmergenteEnum.MESES_GRACIA.getNombreParametro()).getValorNumerico().intValue();
			this.longitudOTP = this.parametroBiessDao
					.consultarPorIdNombreParametro(ParametrosBiessEnum.LONGITUD_OTP.getIdParametro(),
							ParametrosBiessEnum.LONGITUD_OTP.getNombreParametro()).getValorNumerico().intValue();

			this.montoMaximoEmergente=this.parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MONTO_MAX_CREDITO_EMERGENTE.getIdParametro(),
					BiessEmergenteEnum.MONTO_MAX_CREDITO_EMERGENTE.getNombreParametro()).getValorNumerico();

		} catch (final ParametroCreditoException e) {
			log.error("Error al obtener los parametros de SBU y NUMSBU en ConcesionCreditoBean " + e.getMessage());
		} catch (final ParametroBiessException e) {
			log.error("Error al obtener parametro de base de datos", e);
		}

		int tiempoVigencia = 0;
		int longitud = 0;
		try {
			tiempoVigencia = this.parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.TIEMPO_VIGENCIA_OTP.getIdParametro(),
					ParametrosBiessEnum.TIEMPO_VIGENCIA_OTP.getNombreParametro()).getValorNumerico().divide(new BigDecimal(60), RoundingMode.HALF_UP).intValue();

			this.maximoIntentosOtpFocalizados = this.parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.INTENTOS_FALLIDOS_OTP.getIdParametro(),
					ParametrosBiessEnum.INTENTOS_FALLIDOS_OTP.getNombreParametro()).getValorNumerico().intValue();

			this.numeroDiasCaducidad = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.DIAS_CADUCIDAD.getIdParametro(),
					ParametrosBiessEnum.DIAS_CADUCIDAD.getNombreParametro()).getValorNumerico().intValue();

			longitud = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.LONGITUD_OTP.getIdParametro(),
					ParametrosBiessEnum.LONGITUD_OTP.getNombreParametro()).getValorNumerico().intValue();
		} catch (final ParametroBiessException e) {
			log.error("Error al obtener parametro de base de datos para pq focalizados", e);
		}

		cargarTextosParametrizados();

		this.mensajeValidacionOtp = super.getResource(STR_LABEL, "pq.informacion1.otp") + " "
				+ longitud + " " + super.getResource(STR_LABEL, "pq.informacion2.otp") + " "
				+ tiempoVigencia + " " + msjPqInformacion3Otp + " ";

		mensajeTiempoOtp = super.getResource(STR_LABEL, "pq.tiempo.maximo.otp") + " "
				+ tiempoVigencia + " minutos.";

		this.calculoCreditoHelper = CalculoCreditoHelperSingleton.getInstancia();
		this.edadAsegurado = calculoCreditoHelper.determinarEdad(datos.getSolicitante().getDatosPersonales().getFechaNacimiento(), new Date())
				.intValue();

		// Obtiene porcentaje de capacidad de pago


		if (this.datos.getTipo() == TipoPrestamista.JUBILADO) {
			this.capacidadPago = this.parametrizacionPQServicio.obtenerCapacidadPagoMaximoProductoEdad("A", TipoProductoEnum.JUB_PEN.getDescripcion(), new BigDecimal(this.edadAsegurado));
		} else {
			this.capacidadPago = this.parametrizacionPQServicio.obtenerCapacidadPagoMaximoProducto("A", TipoProductoEnum.NORMAL.getDescripcion());
		}



		tituloValidacionOtp = super.getResource(STR_LABEL, "pq.codigo.confirmacion.otp") + " "
				+ devolverTipoProducto();

		aceptaServicio = false;
		validaAceptaServicio = false;
		presentaValidacion = false;
		codigoOtpIngresado = null;

		panelActivoPrincipal = "sim";
		panelActivoSimulacion = "uiSimM";
		deshabilitarSimulacion = false;
		activarCalculoCredito = false;
		deshabilitarAmortizacion = true;
		this.errorIngresoOtp = null;
		this.contadorIntentosOTP = 0;
		mensajeEmail = false;
	}

	/**
	 * Obtiene el monto para prestamos focalizados
	 */
	private void obtenerValorMontoFocalizados() {
		// En caso que sea un credito de PQ Focalizado obtiene el monto del credito del valor de la cocina y olla
		// seleccionadas
		if (this.datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_FOCALIZADOS) {
			BigDecimal precioCocina = BigDecimal.ZERO;
			BigDecimal precioOlla = BigDecimal.ZERO;
			this.contadorProductosFocalizados = 0;
			this.listaProductosFocalizados = new ArrayList<ProductoDto>();

			if (this.pqFocalizados.getCocinaInformacion() != null && this.pqFocalizados.getCocinaInformacion().getPrecio() != null) {
				this.contadorProductosFocalizados++;
				precioCocina = this.pqFocalizados.getCocinaInformacion().getPrecio();
				this.listaProductosFocalizados.add(this.pqFocalizados.getCocinaInformacion());
			}

			if (this.ollasFocalizados.getOllaInformacion() != null && this.ollasFocalizados.getOllaInformacion().getPrecio() != null) {
				this.contadorProductosFocalizados++;
				precioOlla = this.ollasFocalizados.getOllaInformacion().getPrecio();
				this.listaProductosFocalizados.add(this.ollasFocalizados.getOllaInformacion());
			}

			this.opcionSimCuota.setValorTotalCredito(precioCocina.add(precioOlla));
		}
	}

	/**
	 * El valor de la reserva lo pone como monto para el credito
	 */
	private void obtenerValorMontoPqTurismo(final BigDecimal valorReserva) {
		this.opcionSimCuota.setValorTotalCredito(valorReserva);
	}

	public void cargarListaC() {
		dtocatalogo.clear();
		listCatalogo = null;
		//INC-2272 Pensiones Alimenticias
		//se agrega validacion para buscar producto PQ dinamico
	    final long codigoDestino=datos.getTiposProductosPq().name().equals("DINAMICO")?Long.valueOf(datos.getTiposProductosPq()
				.getCodigoTipoProducto().toString().concat(this.datos.getCodigoProdEspecial().toString())):datos.getTiposProductosPq()
				.getCodigoTipoProducto();

		listCatalogo = catalogoServicio.getListaCatalogoDestinoPorCodigoTipoProducto(codigoDestino);


		for (final Catalogo catalogoDestino : listCatalogo) {
			final DatosCatalogo dto = new DatosCatalogo();
			final Long idCatalogo = catalogoDestino.getCodigo(); 
			dto.setDescripcion(catalogoDestino.getDescripcion());
			dto.setIdcatalogo(idCatalogo);
			if (datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_SERVICIOS
					|| datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_FOCALIZADOS) {
				dto.setSeleccion(true);
			} else {
				dto.setSeleccion(false);
			}
			// Buscar codigo destino productivo
			if (datos.isProductoProductivo()) {
				if (validarDestinoProductivo(idCatalogo.toString())) {
					dtocatalogo.add(dto);
				}
			} else {
				if (!validarDestinoProductivo(idCatalogo.toString())) {
					dtocatalogo.add(dto);
				}
			}
		}
	}

	public boolean validarDestinoProductivo(final String codigoCatalogo) {
		boolean respuesta = false;
		final String[] codigosProductivos = DESTINOS_PRODUCTIVOS.split(",");
        for (final String codigoProductivo: codigosProductivos)    {
			if (codigoCatalogo.equals(codigoProductivo)) {
				respuesta = true;
				break;
			}
		}
		return respuesta;
	}

	public void asignarValor(final ActionEvent event) {
		datodto = ((DatosCatalogo) event.getComponent().getAttributes()
				.get("valor"));
	}

	public void seleccionarOp() {
		error = null;

		for (final DatosCatalogo op : dtocatalogo) {
			op.setSeleccion(false);
		}

		for (final DatosCatalogo op : dtocatalogo) {
			if (op.getIdcatalogo() == datodto.getIdcatalogo()
					&& !op.getSeleccion()) {
				op.setSeleccion(true);

			} else {
				if (op.getIdcatalogo() == datodto.getIdcatalogo()
						&& op.getSeleccion()) {
					op.setSeleccion(false);
				}

			}
		}
	}

	/**
	 * @return Cambios Diana Suasnavas 29-04-2015
	 */
	public String cargarCalculoCreditoNew() {

		//Cuando es un PQ dinamico coloco el valor que se obtuvo del costo del proveedor
		if(TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq()){
			this.obtenerValorMontoPqTurismo(this.datos.getDataRespGenericaResponseDto().getTotalFinanciar());
		}

		if (datos.getSolicitante() == null) {
			log.warn("se ha perdido valores de sesion");
			return "errorSesionVacia";
		}
		if (!datos.getSolicitante().isAprobado()) {
			return "errorNoAproboRequisitos";
		}
		message = "";
		msgNovNegativa=null;
		/* Fijar variables para validacion PDA */
		this.flagvalidacionrc = true;
		this.popupvalidacionrc = "VRC";
		datos.setDatosCorrecto(false);
		/* Cargar lista de opciones destinos del credito */
		this.cargarListaC();
		/* Calcular opiciones del credito */
		if (calculoCredito == null) {
			deshabilitarAmortizacion = true;
			deshabilitarSimulacion = false;
			final TipoPrestamista tipoPrestamista = datos.getTipo();
			final String cedula = datos.getSolicitante().getDatosPersonales().getCedula();
			final String genero = datos.getSolicitante().getDatosPersonales().getGenero().getCodigo();
			final Date fechaNacimiento = datos.getSolicitante().getDatosPersonales().getFechaNacimiento();
			final OrigenJubilado origenJubilado = datos.getSolicitante().getOrigenJubilado();

			try {
				datosCredito.setCedulaAfiliado(cedula);
				datosCredito.setGenero(genero);
				datosCredito.setFechaNacimiento(fechaNacimiento);
				datosCredito.setFechaSolicitud(new Date());
				datosCredito.setOrigenJubilado(origenJubilado);
				datosCredito.setTipoPrestamista(tipoPrestamista);
				datosCredito.setGarantia(datos.getDatosGarantia());
				datosCredito.setGarantiaReal(datos.getGarantia());

				// INC-2272 Pensiones Alimenticias
				datosCredito.setIdTipoCredito(datos.getTiposProductosPq().getCodigoTipoProducto().intValue());

				datosCredito.setOrdenCompra(datos.getDatosOrdenCompra());
				// INC-2286 Ferrocarriles
				datosCredito.setInstitucionFinancieraProveedor(datos.getInstitucionFinancieraProveedor());
				if (datos.isCreditoNovacion()) {
					datosCredito.setEsNovacion(datos.isCreditoNovacion());
					datosCredito
							.setPrestamoAnteriorNovacion(((CreditoAccionBean) getSession()
									.getAttribute("creditoAccion"))
									.getPrestamoSeleccionadoNovacion());
				}

				datosCredito.setEsEnfermoTerminal(datos.getSolicitante()
						.isEsEnfermoTerminal());

				calculoCredito = calculoCreditoServicio
						.obtenerCalculoCreditoNew(datosCredito);

				// Validacion de capacidad de endeudamiento
				if (!calculoCredito.isTieneCapacidadEndeudamiento()) {
					deshabilitarSimulacion = true;
					deshabilitarAmortizacion = true;
					this.renderizarPanelPrincpal = false;

					// INC-2135 Pensiones Jubilados/Pensionistas: se cambia el
					// mensaje segun lo rquerido.
					if (TipoPrestamista.JUBILADO.compareTo(datos.getSolicitante().getTipo()) == 0) {
						message = "No tiene valor disponible de su pensi\u00F3n.";
					} else {
						message = "No tiene capacidad de endeudamiento.";
						if (TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq()) {
							this.message = "No posee capacidad de endeudamiento, para verificaci\u00F3n ir a la opci\u00F3n \"Simular Cr\u00E9dito Nuevo\"";
						}
					}
				}

				cargado = true;
			} catch (final CalculoCreditoException e) {
				cargado = false;
				message = e.getMessage();
			}

		}

		return null;
	}

	/**
	 * @return
	 */
	public String cargarCalculoCredito() {

		if (datos.getSolicitante() == null) {
			log.warn("se ha perdido valores de sesion");
			return "errorSesionVacia";
		}

		if (!datos.getSolicitante().isAprobado()) {
			return "errorNoAproboRequisitos";
		}
		message = "";
		/* Fijar variables para validaci�n PDA */
		this.flagvalidacionrc = true;
		this.popupvalidacionrc = "VRC";
		datos.setDatosCorrecto(false);
		/* Cargar lista de opciones destinos del credito */
		this.cargarListaC();
		/* Calcular opiciones del cr�dito */
		if (calculoCredito == null) {
			deshabilitarAmortizacion = true;
			deshabilitarSimulacion = false;
			final TipoPrestamista tipoPrestamista = datos.getTipo();
			final String cedula = datos.getSolicitante().getDatosPersonales()
					.getCedula();
			final String genero = datos.getSolicitante().getDatosPersonales()
					.getGenero().getCodigo();
			final Date fechaNacimiento = datos.getSolicitante().getDatosPersonales()
					.getFechaNacimiento();
			final OrigenJubilado origenJubilado = datos.getSolicitante()
					.getOrigenJubilado();
			// Suma de saldos vigentes
			BigDecimal saldoUsado = prestamoServicio
					.sumaSaldosPrestamosVigentes(cedula);
			if (saldoUsado == null) {
				saldoUsado = BigDecimal.ZERO;
			}
			BigDecimal totalSBU = BigDecimal.ZERO;
			totalSBU = sbu.multiply(numsbu).setScale(2, BigDecimal.ROUND_HALF_UP);
			datos.setSumaSaldoVigente(totalSBU.subtract(saldoUsado));

			try {
				datosCredito.setCedulaAfiliado(cedula);
				datosCredito.setGenero(genero);
				datosCredito.setFechaNacimiento(fechaNacimiento);
				datosCredito.setFechaSolicitud(new Date());
				datosCredito.setOrigenJubilado(origenJubilado);
				datosCredito.setTipoPrestamista(tipoPrestamista);
				datosCredito.setGarantia(datos.getDatosGarantia());
				datosCredito.setGarantiaReal(datos.getGarantia());

				//INC-2272 Pensiones Alimenticias
				datosCredito.setIdTipoCredito(datos.getTiposProductosPq().getCodigoTipoProducto().intValue());

				datosCredito.setOrdenCompra(datos.getDatosOrdenCompra());
				//INC-2286 Ferrocarriles
				datosCredito.setInstitucionFinancieraProveedor(datos.getInstitucionFinancieraProveedor());
				if (datos.isCreditoNovacion()) {
					datosCredito.setEsNovacion(datos.isCreditoNovacion());
					datosCredito
							.setPrestamoAnteriorNovacion(((CreditoAccionBean) getSession()
									.getAttribute("creditoAccion"))
									.getPrestamoSeleccionadoNovacion());
				}
				// GE: MANDAR NUEVO PARAMETRO DE CUOTA y en otros recalculos
				datosCredito.setCuotaMensualBuro(datos
						.getCuotaEstimadaMensualBuro());
				// Parametro de enfermo terminal
				// datosCredito.setEsEnfermoTerminal(((Object)
				// datos.getSolicitante()).isEsEnfermoTerminal());
				datosCredito.setEsEnfermoTerminal(datos.getSolicitante()
						.isEsEnfermoTerminal());
				if (datos.isNovacionCredito()
						&& datos.getPrestamoNovacionSeleccionado() != null) {
					calculoCredito = calculoCreditoServicio
							.obtenerCalculoCredito(
									datosCredito,
									datos.getSumaSaldoVigente().add(
											calcularMontoMaximo()), tipoPrestamista);
				} else {
					calculoCredito = calculoCreditoServicio
							.obtenerCalculoCredito(datosCredito,
									datos.getSumaSaldoVigente(), tipoPrestamista);
				}
				// Cambios Andres Cantos 18/10/2011
				final BigDecimal montoprestamo = calculoCreditoServicio
						.recuperarMontoTienda();

				List<PlazoCredito> plazoCreditoList1 = null;
				plazoCreditoList1 = calculoCredito.getTablaReferenciaCredito()
						.getPlazoCreditoSinDocumentoFiduciario();

				BigDecimal montoMaximoComparar = new BigDecimal(0);
				for (final PlazoCredito plazoCredito1 : plazoCreditoList1) {
					plazoCredito1.getValorMaximoCredito();
					if (plazoCredito1.getOpcionCredito().getMeses() == 60) {
						montoMaximoComparar = plazoCredito1
								.getValorMaximoCredito();
					}
				}

				if (montoMaximoComparar.compareTo(montoprestamo) >= 0) {
					this.vermensajetienda = true;
				}
				// fin cambios

				// Validacion de capacidad de endeudamiento
				if (!calculoCredito.isTieneCapacidadEndeudamiento()) {
					/*
					 * throw new CalculoCreditoException( "No tiene capacidad de endeudamiento.");
					 */
					htmlTabRef.setDisabled(true);
					deshabilitarSimulacion = true;
					deshabilitarAmortizacion = false;
					htmlTabRef.setDisabled(true);
					this.renderizarPanelPrincpal = false;
					// INC-2135 Pensiones Jubilados/Pensionistas: se cambia el mensaje segun lo rquerido.
					if (TipoPrestamista.JUBILADO.compareTo(datos.getSolicitante().getTipo()) == 0) {
						message = "No tiene valor disponible de su pensi\u00F3n.";
					} else{
						message = "No tiene capacidad de endeudamiento.";
					}
				}

				// Validaci�n para tomar en cuenta la cuota de hipotecarios
				if (calculoCredito.isTienePH()) {
					// htmlTabPanel.setValue("sim");
					// htmlTabRef.setDisabled(true);
				}

				//INC-2272 Pensiones Alimenticias
				if (datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_BIENES) {
					List<PlazoCredito> plazoCreditoList = null;
					plazoCreditoList = calculoCredito
							.getTablaReferenciaCredito()
							.getPlazoCreditoSinDocumentoFiduciario();
					// Validacion del monto maximo para el nuevo producto
					for (final PlazoCredito plazoCredito : plazoCreditoList) {
						cumpleMontoMaximo = false;
						if (plazoCredito.isCumpleMonto()) {
							cumpleMontoMaximo = true;
							break;
						}
					}
					if (!isCumpleMontoMaximo()) {
						ConfirmarOrdenCompra(false);
						throw new CalculoCreditoException(
								"El valor total de la orden excede al monto permitido.");
					}
				}
				cargado = true;
			} catch (final CalculoCreditoException e) {
				cargado = false;
				message = e.getMessage();
			}

		}

		return null;
	}

	/**
	 * Funcion que calcula el monto maximo
	 *
	 * @return
	 */
	private BigDecimal calcularMontoMaximo() {
		Double totalPorCancelar = 0.0;
		if (datos.getPrestamoNovacionSeleccionado().getDividendosPrestamo() != null) {
			for (final DividendoPrestamo dp : datos.getPrestamoNovacionSeleccionado()
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

	public String aceptar() {
		this.calculoCredito = null;
		this.datosCredito = new DatosCredito();
		this.cargado = false;
		//this.htmlTabRef = null;
		this.opcionSimCuota = new OpcionCredito();
		this.opcionSimMonto = new OpcionCredito();
		this.prestamoCalculo = null;
		this.simulacion = null;
		this.aceptaServicio = false;
		this.validaAceptaServicio = false;
		this.presentaValidacion = false;
		this.codigoOtpIngresado = null;
		deshabilitarSimulacion = false;
		deshabilitarAmortizacion = true;
		activarCalculoCredito = false;
		this.codigoReservaEcuTur = null;
		this.descripcionPaqueteTuristico = null;
		this.montoPaqueteTuristico = null;
		this.renderizarPanelPrincpal = true;
		return "aceptar";
	}

	private  TablaAmortizacionSac  obtenerAmortizacionFrancesa(final DatosCredito credito,final Integer mesesCalculo) throws ParseException, TablaAmortizacionSacException {
		return obtenerAmortizacionTipo(credito, mesesCalculo, TipoTablaEnum.FRANCESA);
	}
	
	
	private  TablaAmortizacionSac  obtenerAmortizacionTipo(final DatosCredito credito,final Integer mesesCalculo,final TipoTablaEnum tipo) throws ParseException, TablaAmortizacionSacException {
		return obtenerAmortizacionTipo(credito, mesesCalculo, tipo, null);
	}
	
	
	private  TablaAmortizacionSac  obtenerAmortizacionTipo(final DatosCredito credito,final Integer mesesCalculo,final TipoTablaEnum tipo,final BigDecimal monto) throws ParseException, TablaAmortizacionSacException {
		final OperacionSacRequest request = new OperacionSacRequest();
		request.setCliente(FuncionesUtilesSac.fabricarCliente(credito));
		final PrestamoRequestDto prestamo = new PrestamoRequestDto();
		if(monto!=null && monto.signum()>0) {
			prestamo.setMonto(monto);
			prestamo.setMontoCuota(BigDecimal.ZERO);
		}else {
			prestamo.setMontoCuota(calculoCredito.getGarantia().getCapacidadEndeudamiento());
			prestamo.setMonto(BigDecimal.ZERO);
		}
		prestamo.setPlazo(mesesCalculo);
		prestamo.setTipoTablaAmortizacion(FuncionesUtilesSac.obtenerTipoTablaSac(tipo.name()
				));
		final OperacionRequestDto operacion = new OperacionRequestDto();
		operacion.setTipoProducto(obtenerTipoProducto());
		request.setOperacion(operacion);
		request.setPrestamo(prestamo);
	
		return calculoCreditoServicio.obtenerInformacionTablaAmortizacionSAC(request);
	}
	
	private String obtenerTipoProducto() {
		if(TiposProductosPq.DINAMICO.name().equals(datosCredito.getOrdenCompra().getCodigoProducto())) {
			return this.datos.getCodigoProdEspecial()==2?"VEC":"EBI";
		}else {
			return this.datos.isProductoBiessEmergente()?"EMERG":datosCredito.getOrdenCompra().getCodigoProducto();
		}
	}

	public String aceptarOpcion() {
		error = null;
		final PlazoCreditoSinDocumentoFiduciario plazo = item();
		log.debug("selecciona:" + plazo.getOpcionCredito().getMeses());

		int idVariedadCredito;
		final TipoPrestamista tipoPrestamista = datos.getTipo();
		final OrigenJubilado origenJubilado = datos.getSolicitante()
				.getOrigenJubilado();
		try {
			idVariedadCredito = CalculoCreditoHelperSingleton
					.determinarIdVariedadCredito(tipoPrestamista,
							origenJubilado);
		} catch (final CalculoCreditoException e1) {
			log.error(e1.getMessage());
			message = e1.getMessage();
			return null;
		}

		datosCredito.setCedulaAfiliado(datos.getSolicitante()
				.getDatosPersonales().getCedula());
		datosCredito.setTasaInteres(calculoCredito.getCondicionCalculo()
				.getTasaInteres());
		datosCredito.setMonto(plazo.getValorMaximoCredito());
		datosCredito.setPlazo(plazo.getOpcionCredito().getMeses());
		// Setea el plazo en DatosBean para obtener porcentaje de comprometimiento
		this.datos.setPlazo(plazo.getOpcionCredito().getMeses());
		datosCredito.setFechaSolicitud(new Date());
		datosCredito
				.setValorSeguroSaldosOrden(plazo.getValorTotalSeguroSaldo());

		//INC-2272 Pensiones Alimenticias
		datosCredito.setIdTipoCredito(datos.getTiposProductosPq().getCodigoTipoProducto().intValue());

		datosCredito.setIdVariedadcredito(idVariedadCredito);
		datosCredito.setFechaNacimiento(datos.getSolicitante()
				.getDatosPersonales().getFechaNacimiento());

		if (datos.isCreditoNovacion()) {
			datosCredito.setEsNovacion(datos.isCreditoNovacion());
			datosCredito
					.setPrestamoAnteriorNovacion(((CreditoAccionBean) getSession()
							.getAttribute("creditoAccion"))
							.getPrestamoSeleccionadoNovacion());
		}

		// INC-2148 Refugiados==>> Se setea la visa/pasaporte del afiliado.
		if(datos.getSolicitante().getDatosPersonales().getRegistroCivilExtranjero()!=null){
			datosCredito.setVisaPasaporteAfiliado(datos.getSolicitante().getDatosPersonales().getRegistroCivilExtranjero()
					.getNumeroIdentificacion());
		}

		try {
			prestamoCalculo = calculoCreditoServicio
					.calculoarCredito(datosCredito);
			deshabilitarAmortizacion = false;
			deshabilitarSimulacion = true;
			log.debug("cambia a amo");
			panelActivoPrincipal = "amo";
		} catch (final CalculoCreditoException e) {
			log.error(e.getMessage());
			message = e.getMessage();
		}
		return null;
	}

	public void simular() {
		log.debug("cambia a sim");
		panelActivoPrincipal = "sim";
		simulacion = null;
		message = "";
		deshabilitarAmortizacion = true;
		deshabilitarSimulacion = false;
		//INC-2272 Pensiones Alimenticias
		if (datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_NORMALES
				|| datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_SERVICIOS) {
			opcionSimMonto.setValorTotalCredito(BigDecimal.ZERO);
			opcionSimCuota.setCuotaMensual(BigDecimal.ZERO);
		}
		opcionSimMontoCargado = false;
		opcionSimCuotaCargado = false;
	}

	public void calcularSimMonto() {
		log.debug("calculando sim monto");
		log.debug("calculando sim cuota");
		message = "";
		msgNovNegativa=null;
		final TipoSimulacionCredito tipoSimulacion = TipoSimulacionCredito.SIMULACION_MONTO;
		final int plazo = opcionSimMonto.getMeses();
		final BigDecimal monto = null;
		final BigDecimal cuota = opcionSimMonto.getCuotaMensual();
		final BigDecimal tasaInteres = calculoCredito.getCondicionCalculo()
				.getTasaInteres();
		// CB 13/12/2010 Se aumenta la cuota del hipotecario para el monto
		// m�ximo del Pq.
		List<PlazoCredito> plazoCreditoList = null;
		plazoCreditoList = calculoCredito.getTablaReferenciaCredito()
				.getPlazoCreditoSinDocumentoFiduciario();

		if (calculoCredito.isTienePH()) {
			panelActivoPrincipal = "sim";
			deshabilitarAmortizacion = true;
			deshabilitarSimulacion = false;
			htmlTabRef.setDisabled(true);

			if (calculoCredito.getMaximoComprometerHipotecario().floatValue() > 0) {
				for (final PlazoCredito plazoCredito : plazoCreditoList) {
					plazoCredito.setPorcentajeComprometido(calculoCredito
							.getPorcentajeHipotecario());
					plazoCredito.setValorMaximoComprometer(calculoCredito
							.getMaximoComprometerHipotecario());
				}
			} else {
				simulacion = new Simulacion();
				simulacion.setTipoSimulacion(tipoSimulacion.getEstado());
				simulacion.setCalculoCredito(false);
				activarCalculoCredito = false;
				simulacion
						.setObservacion("Cuota de hipotecario sobrepasa el "
								+ new BigDecimal(
								ConstantesCreditos.PORCENTAJE_COMPROMETIMIENTO_PH)
								.setScale(2, BigDecimal.ROUND_HALF_UP)
								+ "% de su ingreso.");
				return;
			}
		}

		try {
			simulacion = simularCreditoServicio.simularCredito(
					tipoSimulacion.getValor(), plazo, monto, cuota,
					plazoCreditoList, tasaInteres);
			activarCalculoCredito = simulacion.getCalculoCredito();
		} catch (final Exception e) {
			simulacion = new Simulacion();
			simulacion.setTipoSimulacion(tipoSimulacion.getEstado());
			simulacion.setCalculoCredito(false);
			activarCalculoCredito = false;
			simulacion.setObservacion("Inconsistencia.");
			return;
		}
		log.debug("simlacion:" + simulacion.getCalculoCredito());
		log.debug("simlacion:" + simulacion.getObservacion());
		opcionSimMonto.setValorTotalCredito(simulacion.getMontoCredito());
		opcionSimCuota.setCuotaMensual(new BigDecimal(0));
		opcionSimMontoCargado = true;
		opcionSimCuotaCargado = false;
	}

	public void calcularSimCuota() {
		log.debug("calculando sim cuota");
		message = "";
		msgNovNegativa=null;
		final TipoSimulacionCredito tipoSimulacion = TipoSimulacionCredito.SIMULACION_CUOTA;
		final BigDecimal cuota = null;
		final BigDecimal tasaInteres = calculoCredito.getCondicionCalculo()
				.getTasaInteres();
		// CB 13/12/2010 Se aumenta la cuota del hipotecario para el monto
		// m�ximo del Pq.
		List<PlazoCredito> plazoCreditoList = null;
		plazoCreditoList = calculoCredito.getTablaReferenciaCredito()
				.getPlazoCreditoSinDocumentoFiduciario();

		// CB 30/03/2011 Para realizar la simulacion con los nuevos productos
		int plazo = opcionSimCuota.getMeses();
		BigDecimal monto = opcionSimCuota.getValorTotalCredito();

		//INC-2272 Pensiones Alimenticias
		if (datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_BIENES) {
			plazo = calculoCredito.getPlazoMaximoProducto();
			monto = calculoCredito.getMontoMaximoProducto();

			for (final PlazoCredito plazoCredito : plazoCreditoList) {
				if (plazo <= plazoCredito.getOpcionCredito().getMeses()) {
					datosCredito.setValorSeguroSaldosOrden(plazoCredito
							.getValorTotalSeguroSaldo());

					if (!plazoCredito.isCumpleMonto()) {
						simulacion
								.setObservacion("No cumple monto,garant\u00EDas,capacidad de pago o plazo para los par\u00E1metros ingresados.");
						return;
					}
					break;
				}
			}
		}

		if (calculoCredito.isTienePH()) {
			panelActivoPrincipal = "sim";
			deshabilitarAmortizacion = true;
			deshabilitarSimulacion = false;
			htmlTabRef.setDisabled(true);

			if (calculoCredito.getMaximoComprometerHipotecario().floatValue() > 0) {
				for (final PlazoCredito plazoCredito : plazoCreditoList) {
					plazoCredito.setPorcentajeComprometido(calculoCredito
							.getPorcentajeHipotecario());
					plazoCredito.setValorMaximoComprometer(calculoCredito
							.getMaximoComprometerHipotecario());
				}
			} else {
				simulacion = new Simulacion();
				simulacion.setTipoSimulacion(tipoSimulacion.getEstado());
				simulacion.setCalculoCredito(false);
				activarCalculoCredito = false;
				simulacion
						.setObservacion("Cuota de hipotecario sobrepasa el "
								+ new BigDecimal(
								ConstantesCreditos.PORCENTAJE_COMPROMETIMIENTO_PH)
								.setScale(2, BigDecimal.ROUND_HALF_UP)
								+ "% de su ingreso.");
				return;
			}
		}
		try {

			simulacion = simularCreditoServicio.simularCredito(
					tipoSimulacion.getValor(), plazo, monto, cuota,
					plazoCreditoList, tasaInteres);
			activarCalculoCredito = simulacion.getCalculoCredito();
		} catch (final Exception e) {
			simulacion = new Simulacion();
			simulacion.setTipoSimulacion(tipoSimulacion.getEstado());
			simulacion.setCalculoCredito(false);
			activarCalculoCredito = false;
			simulacion.setObservacion("Inconsistencia.");
			return;
		}
		log.debug("simlacion:" + simulacion.getCalculoCredito());
		log.debug("simlacion:" + simulacion.getObservacion());
		opcionSimCuota.setCuotaMensual(simulacion.getCuotaCredito());
		opcionSimMonto.setValorTotalCredito(new BigDecimal(0));
		opcionSimMontoCargado = false;
		opcionSimCuotaCargado = true;
	}

	public void aceptarSimMonto() {
		log.debug("aceptar sim monto");

		int idVariedadCredito;
		final TipoPrestamista tipoPrestamista = datos.getTipo();
		final OrigenJubilado origenJubilado = datos.getSolicitante()
				.getOrigenJubilado();

		try {
			idVariedadCredito = CalculoCreditoHelperSingleton
					.determinarIdVariedadCredito(tipoPrestamista,
							origenJubilado);
		} catch (final CalculoCreditoException e1) {
			log.error(e1);
			message = e1.getMessage();
			return;
		}

		datosCredito.setCedulaAfiliado(datos.getSolicitante()
				.getDatosPersonales().getCedula());
		datosCredito.setTasaInteres(calculoCredito.getCondicionCalculo()
				.getTasaInteres());
		datosCredito.setMonto(simulacion.getMontoCredito());
		datosCredito.setPlazo(simulacion.getPlazoCredito());
		datosCredito.setFechaSolicitud(new Date());
		//INC-2272 Pensiones Alimenticias
		datosCredito.setIdTipoCredito(datos.getTiposProductosPq().getCodigoTipoProducto().intValue());
		datosCredito.setIdVariedadcredito(idVariedadCredito);
		datosCredito.setFechaNacimiento(datos.getSolicitante()
				.getDatosPersonales().getFechaNacimiento());
		if (datos.isCreditoNovacion()) {
			datosCredito.setEsNovacion(datos.isCreditoNovacion());
			datosCredito
					.setPrestamoAnteriorNovacion(((CreditoAccionBean) getSession()
							.getAttribute("creditoAccion"))
							.getPrestamoSeleccionadoNovacion());

		}
		try {
			prestamoCalculo = calculoCreditoServicio
					.calculoarCredito(datosCredito);
			deshabilitarAmortizacion = false;
			deshabilitarSimulacion = true;
			log.debug("cambia a amo");
			panelActivoPrincipal = "amo";
		} catch (final CalculoCreditoException e) {
			log.error(e);
			message = e.getMessage();
		}

	}

	public void aceptarSimCuota() {
		log.debug("aceptar sim cuota");
		log.debug("aceptar sim monto");
		error = null;
		int idVariedadCredito;
		final TipoPrestamista tipoPrestamista = datos.getTipo();
		final OrigenJubilado origenJubilado = datos.getSolicitante()
				.getOrigenJubilado();

		try {
			idVariedadCredito = CalculoCreditoHelperSingleton
					.determinarIdVariedadCredito(tipoPrestamista,
							origenJubilado);
		} catch (final CalculoCreditoException e1) {
			log.error(e1);
			message = e1.getMessage();
			return;
		}

		datosCredito.setCedulaAfiliado(datos.getSolicitante()
				.getDatosPersonales().getCedula());
		datosCredito.setTasaInteres(calculoCredito.getCondicionCalculo()
				.getTasaInteres());
		datosCredito.setMonto(simulacion.getMontoCredito());
		datosCredito.setPlazo(simulacion.getPlazoCredito());
		datosCredito.setFechaSolicitud(new Date());
		//INC-2272 Pensiones Alimenticias
		datosCredito.setIdTipoCredito(datos.getTiposProductosPq().getCodigoTipoProducto().intValue());
		datosCredito.setIdVariedadcredito(idVariedadCredito);
		datosCredito.setFechaNacimiento(datos.getSolicitante()
				.getDatosPersonales().getFechaNacimiento());
		if (datos.isCreditoNovacion()) {
			datosCredito.setEsNovacion(datos.isCreditoNovacion());
			datosCredito
					.setPrestamoAnteriorNovacion(((CreditoAccionBean) getSession()
							.getAttribute("creditoAccion"))
							.getPrestamoSeleccionadoNovacion());
		}
		try {
			prestamoCalculo = calculoCreditoServicio
					.calculoarCredito(datosCredito);

			deshabilitarAmortizacion = false;
			deshabilitarSimulacion = true;
			log.debug("cambia a amo");
			panelActivoPrincipal = "amo";

		} catch (final CalculoCreditoException e) {
			log.error(e);
			message = e.getMessage();
		}
	}

	/**
	 * Devuelve la tasa de interes dado el plazo
	 *
	 * @param plazo
	 *            Es el plazo en meses
	 * @param tipoPrestamista
	 *            Es el rol del prestamista
	 * @param edad
	 *            Edad del asegurado
	 * @return
	 * @throws CondicionCalculoException
	 */
	private BigDecimal obtenerTasaInteres(final BigDecimal plazo, final TipoPrestamista tipoPrestamista, final int edad) throws CondicionCalculoException {
		String tipoProducto = TipoProductoEnum.NORMAL.getDescripcion();
		if (TipoPrestamista.JUBILADO == tipoPrestamista) {
			tipoProducto = TipoProductoEnum.JUB_PEN.getDescripcion();
		}
		final BigDecimal resp = condicionCalculoServicio.obtenerTasaInteresPorPlazo(plazo, tipoProducto, edad);
		return resp;
	}
	private void validarMontoMenorProv(){
		if(TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq() &&
				datos.getDataRespGenericaResponseDto().getTotalFinanciar().compareTo(opcionSimCuota.getValorTotalCredito())==1 ){
			setValIngCred(currencyFormat(opcionSimCuota.getValorTotalCredito()));
			valPacProv=currencyFormat(datos.getDataRespGenericaResponseDto().getTotalFinanciar());
			difValPag=currencyFormat(datos.getDataRespGenericaResponseDto().getTotalFinanciar().subtract(opcionSimCuota.getValorTotalCredito()));
			esMtnMenProv=Boolean.TRUE;
		}else {
			esMtnMenProv=Boolean.FALSE;
		}
	}

	private  String currencyFormat(final BigDecimal n) {
		final DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		 final DecimalFormat df = new DecimalFormat("#,###.00",simbolos);
		return  df.format(n);
	}
	public void aceptarSimCuotaNew() {

		validarMontoMenorProv();
		log.info("aceptar sim cuota new");
		filtrarOTP();
		error = null;
		int idVariedadCredito;
		final TipoPrestamista tipoPrestamista = datos.getTipo();
		final OrigenJubilado origenJubilado = datos.getSolicitante()
				.getOrigenJubilado();

		try {
			idVariedadCredito = CalculoCreditoHelperSingleton
					.determinarIdVariedadCredito(tipoPrestamista,
							origenJubilado);
		} catch (final CalculoCreditoException e1) {
			log.error(e1);
			message = e1.getMessage();
			return;
		}

		try {
			EvaluaReglaMontoMinimoDto montoMinimoRegla = this.prestamoServicio.validarMontoMinimoPrestamo(this.opcionSimCuota.getValorTotalCredito(),
					new BigDecimal(opcionSimCuota.getMeses()), this.datos.getTipo(), this.edadAsegurado);
			if ( !montoMinimoRegla.isCumpleMontoMinimo()) {
				simulacion.setObservacion("El monto m\u00EDnimo del cr\u00E9dito debe ser al menos el " + montoMinimoRegla.getValorMinimoSBU()
						+ " del SBU: $" + montoMinimoRegla.getValorSBUMitad());
				simulacion.setCalculoCredito(false);
				activarCalculoCredito = false;
				return;
			}
		} catch (MontoMinimoException e) {
			simulacion.setObservacion(e.getMessage());
			simulacion.setCalculoCredito(false);
			activarCalculoCredito = false;
			return;
		}

	

		//este es para el tipo de tabla
		datosCredito.setTipoTabla(opcionSimCuota.getTipoTablaSeleccionado());
		datosCredito.setCuotaMensualMaxima(opcionSimCuota.getCuotaMensual());

		datosCredito.setCedulaAfiliado(datos.getSolicitante()
				.getDatosPersonales().getCedula());
		datosCredito.setTasaInteres(calculoCredito.getCondicionCalculo().getTasaInteres());
		datosCredito.setMonto(simulacion.getMontoCredito());
		datosCredito.setMontoMaximo(simulacion.getMontoMaximoCredito());
		datosCredito.setPlazo(simulacion.getPlazoCredito());
		datosCredito.setFechaSolicitud(new Date());
		// INC-2272 Pensiones Alimenticias
		datosCredito.setIdTipoCredito(datos.getTiposProductosPq()
				.getCodigoTipoProducto().intValue());
		datosCredito.setIdVariedadcredito(idVariedadCredito);
		datosCredito.setFechaNacimiento(datos.getSolicitante()
				.getDatosPersonales().getFechaNacimiento());
//Se agrega datos para simulacion en el GAF
		
	this.datosCredito.setNombreAsegurado(this.datos.getSolicitante().getDatosPersonales().getApellidosNombres());
	 this.datosCredito.setTipoPeticionTablaSac("V/X");
		this.datosCredito.setProductoCarga("QBI");
		this.datosCredito.setTipoIdentificacionSac("C");
		final boolean esExtranjeroRefugiado = TipoIdentificacionSacEnum.REFUGIADO.name().equals(FuncionesUtilesSac
				.obtenerTipoIdentificacionSac(this.datos.getSolicitante().getDatosPersonales().getCedula()));
		if (esExtranjeroRefugiado) {
			this.datosCredito.setTipoIdentificacionSac("N");
		}
		//FIN DE DATOS PAR SIMULACION GAF
		if (datos.isCreditoNovacion()) {
			datosCredito.setEsNovacion(datos.isCreditoNovacion());
			datosCredito
					.setPrestamoAnteriorNovacion(((CreditoAccionBean) getSession()
							.getAttribute("creditoAccion"))
							.getPrestamoSeleccionadoNovacion());
		}
		try {
			final PrestamoCalculo prestamoCalculoAux = new PrestamoCalculo();

			datosCredito.setCreditoEspecial(null);
			if (datos.isProductoBiessEmergente()) {
				datosCredito.setCreditoEspecial(CreditoEspecialEnum.EMERGENTE.getCodigoCredito());
			}
			//Agrego el numero de contrato o reserva y seteo el valor de credito especial
			if (TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq()) {
				this.datosCredito.setNumeroReserva(this.datos.getCodigoContratoProv());
				datosCredito.setCreditoEspecial(datos.getCodigoProdEspecial());
			}
			prestamoCalculo = calculoCreditoServicio.calcularCreditoNew(datosCredito, prestamoCalculoAux);
			calculoCredito.getCondicionCalculo().setTasaInteres(prestamoCalculo.getTasaInteres());
			deshabilitarAmortizacion = false;
			deshabilitarSimulacion = true;
			log.debug("cambia a amo");
			panelActivoPrincipal = "amo";
			activarCalculoCredito = false;

		} catch (final CalculoCreditoException e) {
			log.error(e);
			if(e.getMessage()!=null && e.getMessage().indexOf("negativo")!=-1) {
				msgNovNegativa=e.getMessage();
			}else {
			message = e.getMessage();
			}
			
			
			
		}	catch (final TablaAmortizacionException e) {
			log.error(e);
			message = e.getMessage();
		} catch (final TablaAmortizacionSacException e) {
			log.error(e);
			message = e.getCodigo() + ", " + e.getMensaje();
		}

	}

	public void aceptarSimMontoNew() {
		log.info("aceptar sim monto New");
		filtrarOTP();
		int idVariedadCredito;
		final TipoPrestamista tipoPrestamista = datos.getTipo();
		final OrigenJubilado origenJubilado = datos.getSolicitante()
				.getOrigenJubilado();

		try {
			idVariedadCredito = CalculoCreditoHelperSingleton
					.determinarIdVariedadCredito(tipoPrestamista,
							origenJubilado);
		} catch (final CalculoCreditoException e1) {
			log.error(e1);
			message = e1.getMessage();
			return;
		}

		try {
			EvaluaReglaMontoMinimoDto montoMinimoRegla = this.prestamoServicio.validarMontoMinimoPrestamo(this.opcionSimMonto.getValorTotalCredito(),
					new BigDecimal(this.opcionSimMonto.getMeses()), this.datos.getTipo(), this.edadAsegurado);
			if (!montoMinimoRegla.isCumpleMontoMinimo()) {
				simulacion.setObservacion("El monto m\u00EDnimo del cr\u00E9dito debe ser al menos el " + montoMinimoRegla.getValorMinimoSBU()
						+ " del SBU: $" + montoMinimoRegla.getValorSBUMitad() + ". Ingrese otro valor de cuota.");
				simulacion.setCalculoCredito(false);
				activarCalculoCredito = false;
				return;
			}
		} catch (MontoMinimoException e) {
			simulacion.setObservacion(e.getMessage());
			simulacion.setCalculoCredito(false);
			activarCalculoCredito = false;
			return;
		}

		//este es para el tipo de tabla
		datosCredito.setTipoTabla(opcionSimMonto.getTipoTablaSeleccionado());
		datosCredito.setCuotaMensualMaxima(opcionSimMonto.getCuotaMensual());

		datosCredito.setCedulaAfiliado(datos.getSolicitante()
				.getDatosPersonales().getCedula());
		datosCredito.setTasaInteres(calculoCredito.getCondicionCalculo()
				.getTasaInteres());
		datosCredito.setMonto(simulacion.getMontoCredito());
		datosCredito.setPlazo(simulacion.getPlazoCredito());
		datosCredito.setFechaSolicitud(new Date());
		// INC-2272 Pensiones Alimenticias
		datosCredito.setIdTipoCredito(datos.getTiposProductosPq()
				.getCodigoTipoProducto().intValue());
		datosCredito.setIdVariedadcredito(idVariedadCredito);
		datosCredito.setFechaNacimiento(datos.getSolicitante()
				.getDatosPersonales().getFechaNacimiento());
		 this.datosCredito.setTipoPeticionTablaSac("V/X");
		if (datos.isCreditoNovacion()) {
			datosCredito.setEsNovacion(datos.isCreditoNovacion());
			datosCredito
					.setPrestamoAnteriorNovacion(((CreditoAccionBean) getSession()
							.getAttribute("creditoAccion"))
							.getPrestamoSeleccionadoNovacion());

		}
		try {

			PrestamoCalculo prestamoCalculoAux = new PrestamoCalculo();
			prestamoCalculoAux = prestamoCalculoService.poblarPrestamoCalculoNew(datosCredito);

			datosCredito.setCreditoEspecial(null);
			if (datos.isProductoBiessEmergente()) {
				datosCredito.setCreditoEspecial(CreditoEspecialEnum.EMERGENTE.getCodigoCredito());
			}
			//Agrego el numero de contrato o reserva y seteo el valor de credito especial
			if (TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq()) {
				this.datosCredito.setNumeroReserva(this.datos.getCodigoContratoProv());
				datosCredito.setCreditoEspecial(datos.getCodigoProdEspecial());
			}
			prestamoCalculo = calculoCreditoServicio.calcularCreditoNew(datosCredito,prestamoCalculoAux);
			calculoCredito.getCondicionCalculo().setTasaInteres(prestamoCalculo.getTasaInteres());

			deshabilitarAmortizacion = false;
			deshabilitarSimulacion = true;
			log.debug("cambia a amo");
			panelActivoPrincipal = "amo";
			activarCalculoCredito = false;

		} catch (final CalculoCreditoException e) {
			log.error(e);
			message = e.getMessage();
		} catch (final TablaAmortizacionException e) {
			log.error(e);
			message = e.getMessage();
		} catch (final TablaAmortizacionSacException e) {
			log.error(e);
			message = e.getCodigo() + ", " + e.getMensaje();
		}

	}

	public String aceptarPrestamo() {

		/* INC.1800. Control PDA para jubilados */
		if (flagcambioinucta) {
			return "errorRegistroCivil";
		}
		return "resultadoPrestamo";
	}

	public String cancelarPrestamo() {
		this.continuaValidacionOTP = false;

		return "";
	}

	/**
	 * Siempre se va por OTP cambio REQ-161
	 */
	public void filtrarOTP() {
		seguirOtp = true;
		seguirInicial = false;
	}

	/**
	 * Funcion que valida que se escoja una opcion de credito
	 */
	public String validaDestinoCredito() {
		final String continuar = null;
		/* Valida monto m�nimo */
		if (!cumpleMontoMinimo()) {
			return continuar;
		}
		/* Valida que se haya seleccionado un destino de credito */
		Boolean flag = false;
		error = null;
		for (final DatosCatalogo op : dtocatalogo) {
			if (op.getSeleccion()) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			error = "Seleccione al menos un destino del cr\u00E9dito.";
			return continuar;
		}

		/* Realizar verificaci�n cuenta bancaria para ver si debe o no pasar validaci�n registro civil */
		// No realiza validacion PDA para prestamos focalizados
		if (flag && (datos.getCategoriaTipoProductoPq() != CategoriaTipoProductoPq.CAT_FOCALIZADOS
				&& TiposProductosPq.ECU_TUR != this.datos.getTiposProductosPq() && TiposProductosPq.DINAMICO != this.datos.getTiposProductosPq())  ) {
			try {
				this.verificarCondicionesAntiFraude();
			} catch (final Exception e) {
				log.error("Error al validar cuenta bancaria. " + e.getMessage(), e);
				error = "Error al validar cuenta bancaria";
				this.flagvalidacionrc = true;
				this.popupvalidacionrc = "VRC";
			}
		}

		if (datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_FOCALIZADOS
				|| TiposProductosPq.ECU_TUR == this.datos.getTiposProductosPq() || TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq()) {
			this.flagvalidacionrc = false;
			this.popupvalidacionrc = null;
			// Guarda auditoria cuando es Ecuador tu lugar en el mundo
			if (TiposProductosPq.ECU_TUR == this.datos.getTiposProductosPq()) {
				final ParametroEvento parametroEvento = AuditoriaPqWebHelper
						.obtenerParametrosAceptaCondicionesEcuadorTurismo(this.datos.getSolicitante().getDatosPersonales().getCedula(),
								this.opcionSimCuota.getMeses(), this.opcionSimCuota.getTipoTablaSeleccionado(),
								this.opcionSimCuota.getValorTotalCredito(), this.codigoReservaEcuTur);
				super.guardarAuditoria(OperacionEnum.ACEPTA_CONDICIONES_ECUADOR_TURISMO, parametroEvento,
						this.datos.getSolicitante().getDatosPersonales().getCedula());
			}
			if (TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq()) {
				final ParametroEvento parametroEvento = AuditoriaPqWebHelper
						.obtenerParametrosAceptaCondicionesEcuadorTurismo(this.datos.getSolicitante().getDatosPersonales().getCedula(),
								this.opcionSimCuota.getMeses(), this.opcionSimCuota.getTipoTablaSeleccionado(),
								this.opcionSimCuota.getValorTotalCredito(), this.datos.getCodigoContratoProv());
				super.guardarAuditoria(OperacionEnum.ACEPTA_CONDICIONES_ECUADOR_TURISMO, parametroEvento,
						this.datos.getSolicitante().getDatosPersonales().getCedula());
			}
		}

		aceptaServicio = false;
		validaAceptaServicio = false;
		presentaValidacion = false;
		continuaValidacionOTP = false;
		codigoOtpIngresado = null;
		mensajeEmail = false;
		this.errorIngresoOtp = null;
		this.contadorIntentosOTP = 0;



		//se quita validaciones para ir a otros flujos siempre debe validar OTP REQ-161

		return "validacionOTP";
	}

	public void confirmarAceptacion() {
		continuaValidacionOTP = false;
		mensajeEmail = false;
		if (aceptaServicio) {
			presentaValidacion = true;
			this.generarCodigoOTP();
		} else {
			validaAceptaServicio = false;
			presentaValidacion = false;
			errorIngresoOtp = null;
		}

	}

	/**
	 * Obtiene un id de transaccion para generacion de codigo OTP para prestamos focalizados a partir de un mapa de
	 * parametros
	 *
	 * @param parametrosId
	 * @return
	 */
	private String obtenerIdTransaccion(final Map<String, Object> parametrosId) {
		final StringBuilder idTransaccion = new StringBuilder();
		idTransaccion.append(parametrosId.get("APLICATIVO"));
		idTransaccion.append(parametrosId.get("CODPRECLA"));
		idTransaccion.append(parametrosId.get("ORDPREAFI"));
		idTransaccion.append(parametrosId.get("CODPRETIP"));
		idTransaccion.append(parametrosId.get("CEDULA"));
		idTransaccion.append(parametrosId.get("DIA"));
		idTransaccion.append(parametrosId.get("MES"));
		idTransaccion.append(parametrosId.get("ANIO"));
		idTransaccion.append(parametrosId.get("HORA"));
		idTransaccion.append(parametrosId.get("MINUTO"));
		idTransaccion.append(parametrosId.get("SEGUNDO"));

		return idTransaccion.toString();
	}

	public void generarCodigoOTP() {
		this.setCodigoOtpIngresado(null);
		this.errorIngresoOtp = null;
		this.contadorIntentosOTP = 0;
		final Afiliado afiliado = consultarDatosAfiliado(this.datos.getSolicitante().getDatosPersonales().getCedula());
		final String email = afiliado.getMaiafi();
		if (!validarMailAfiliado(email)) {
			final NotificacionOTP notificacionOTP = new NotificacionOTP();
			notificacionOTP.setCelular(this.datos.getSolicitante().getDatosPersonales().getCelular());
			notificacionOTP.setMail(email);
			notificacionOTP.setNombresApellidos(this.datos.getSolicitante().getDatosPersonales().getApellidosNombres());

			String exito = "";
			String observacion = "";
			try {
				// Crea el id de transaccion para generacion de OTP
				final Map<String, Object> parametrosId = new HashMap<String, Object>();
				parametrosId.put("APLICATIVO", "PQ");
				int codPrecla = 0;
				try {
					codPrecla = obtenerVariedadCredito(this.datos.getTipo(), datos.getSolicitante().getOrigenJubilado());
				} catch (final CalculoCreditoException e) {
					log.error("No se pudo obtener el codprecla en destino del credito", e);
				}
				parametrosId.put("CODPRECLA", codPrecla);
				parametrosId.put("ORDPREAFI", 1);
				parametrosId.put("CODPRETIP", this.datos.getTiposProductosPq().getCodigoTipoProducto().intValue());
				parametrosId.put("CEDULA", datos.getSolicitante().getDatosPersonales().getCedula());
				final Calendar calendar = Calendar.getInstance();
				parametrosId.put("DIA", calendar.get(Calendar.DATE));
				parametrosId.put("MES", calendar.get(Calendar.MONTH) + 1);
				parametrosId.put("ANIO", calendar.get(Calendar.YEAR));
				parametrosId.put("HORA", calendar.get(Calendar.HOUR_OF_DAY));
				parametrosId.put("MINUTO", calendar.get(Calendar.MINUTE));
				parametrosId.put("SEGUNDO", calendar.get(Calendar.SECOND));

				String negocio = "PQCRE";
				if (datos.isCreditoNovacion()) {
					negocio = "PQNOV";
				}

				this.idTransaccion = obtenerIdTransaccion(parametrosId);
				this.otpServicio.generaOTP(idTransaccion, this.datos.getSolicitante().getDatosPersonales().getCedula(), notificacionOTP, negocio);
				exito = "true";
				observacion = "Generacion OTP PQ Exitoso";
				validaAceptaServicio = true;
			} catch (final OTPException e) {
				exito = "false";
				observacion = "Error al generar codigo OTP PQ: " + e.getMessage();
				log.info("Se presento una excepcion al generar el codigo OTP", e);
				this.errorIngresoOtp = "Se present\u00F3 un problema al generar el c\u00F3digo de validaci\u00F3n. Favor intentar m\u00E1s tarde.";
			} finally {
				final ParametroEvento parametroEvento = AuditoriaPqWebHelper
						.obtenerParametrosCedulaExitoObservacion(this.datos.getSolicitante().getDatosPersonales().getCedula(), exito, observacion);
				super.guardarAuditoria(OperacionEnum.GENERACION_CODIGO_OTP, parametroEvento,
					this.datos.getSolicitante().getDatosPersonales().getCedula());
			}
		} else {
			presentaValidacion = false;
		}
	}

	private Afiliado consultarDatosAfiliado(final String cedula) {
		Afiliado afiliado = null;
		try {
			afiliado = afiliadoServicio.consultarDatosAfiliado(cedula);
		} catch (final AfiliadoException e) {
			log.error(e.getMessage());
		}
		return afiliado;
	}

	public boolean	validarMailAfiliado(final String email) {
		mensajeEmail = true;
		if (email != null) {
			if (EmailValidator.validar(email)) {
				mensajeEmail = false;
			}
		}
		return mensajeEmail;
	}

	public String devolverTipoProducto() {
		String tipoProducto = " cr\u00E9dito";
		if (datos.isCreditoNovacion()) {
			tipoProducto = " novaci\u00F3n";
		}
		return tipoProducto;
	}

	public String devolverTipoProductoMail() {
		String tipoProducto = " cr&#233;dito";
		if (datos.isCreditoNovacion()) {
			tipoProducto = " novaci&#243;n";
		}
		return tipoProducto;
	}

	public String cancelarAutorizacion() {
		this.cerrarSesionOtp = null;
		this.errorIngresoOtp = null;
		this.contadorIntentosOTP = 0;
		this.continuaValidacionOTP = false;
		activarCalculoCredito = false;
		validaAceptaServicio = false;
		aceptaServicio = false;
		cancelarDividendos();
		if (datos.isCreditoNovacion()) {
			return "regresarNovacion";
		} else {
			return "regresar";
		}
	}

	/**
	 * Realiza la validacion de ingreso de codigo OTP, en caso de superar el numero maximo de
	 * intentos fallidos redirecciona a una pagina informativa
	 *
	 * @return
	 */
	public String validacionCodigoOTP() {
		cerrarSesionOtp = null;
		this.errorIngresoOtp = null;
		continuaValidacionOTP = false;
		String exito = "";
		String observacion = "";
		try {
			final boolean validacionOTP = this.otpServicio.validaOTP(this.idTransaccion, this.codigoOtpIngresado);
			//boolean validacionOTP = validaTemporal();
			if (validacionOTP) {
				exito = "true";
				observacion = "Ingreso de codigo OTP PQ correcto";
				continuaValidacionOTP = true;
				return "";
			} else {
				exito = "false";
				this.contadorIntentosOTP++;
				this.errorIngresoOtp = String.format("El c\u00F3digo ingresado es incorrecto. Intento %d de %d",
						this.contadorIntentosOTP, this.maximoIntentosOtpFocalizados);
				observacion = "PQ. " + this.errorIngresoOtp;
			}
		} catch (final OTPException e) {	
			exito = "false";
			this.errorIngresoOtp = Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", "") ;
			observacion = "Se presento un error al validar codigo OTP PQ: " + this.errorIngresoOtp;
		} finally {
			final ParametroEvento parametroEvento = AuditoriaPqWebHelper
					.obtenerParametrosCedulaExitoObservacion(this.datos.getSolicitante().getDatosPersonales().getCedula(), exito, observacion);
			super.guardarAuditoria(OperacionEnum.GENERACION_CODIGO_OTP, parametroEvento,
				this.datos.getSolicitante().getDatosPersonales().getCedula());
		}

		if (errorIngresoOtp != null) {
			final int resultado = errorIngresoOtp.indexOf("tiempo de vigencia");
			if(resultado != -1) {
				cerrarSesionOtp = super.getResource(STR_MESSAGES, "mensaje.pq.error.tiempo.otp") + devolverTipoProducto() + ".";
				exito = "false";
				observacion = "Se excedido el tiempo de vigencia para ingreso del codigo OTP PQ";
	        	final ParametroEvento parametroEvento = AuditoriaPqWebHelper
						.obtenerParametrosCedulaExitoObservacion(this.datos.getSolicitante().getDatosPersonales().getCedula(), exito, observacion);
				super.guardarAuditoria(OperacionEnum.GENERACION_CODIGO_OTP, parametroEvento,
				this.datos.getSolicitante().getDatosPersonales().getCedula());
	        	final String mensajeMail = "que ha excedido el tiempo de vigencia para ingreso del c&#243;digo de validaci&#243;n, "
						+ "su cr&#233;dito no puede ser generado. Favor generar una nueva solicitud de" + devolverTipoProductoMail();
				this.enviarAlertaOTP(mensajeMail,null);
				return "errorValidacionOtp";
			}
		}

		if (this.contadorIntentosOTP == this.maximoIntentosOtpFocalizados) {
			this.contadorIntentosOTP = 0;
			this.setCodigoOtpIngresado("");
			this.errorIngresoOtp = null;
			this.cerrarSesionOtp = super.getResource(STR_MESSAGES, "mensaje.pq.error.intentos.otp") + devolverTipoProducto() + ".";
			exito = "false";
			observacion = "Se alcanzo el numero maximo de intentos fallidos OTP PQ";
        	final ParametroEvento parametroEvento = AuditoriaPqWebHelper
					.obtenerParametrosCedulaExitoObservacion(this.datos.getSolicitante().getDatosPersonales().getCedula(), exito, observacion);
			super.guardarAuditoria(OperacionEnum.GENERACION_CODIGO_OTP, parametroEvento,
				this.datos.getSolicitante().getDatosPersonales().getCedula());
			final String mensajeMail = "que ha excedido el n&#250;mero de intentos para ingreso del c&#243;digo de validaci&#243;n, "
					+ "su cr&#233;dito no puede ser generado. Favor generar una nueva solicitud de" + devolverTipoProductoMail();
			this.enviarAlertaOTP(mensajeMail,"INTENTOS");
			return "errorValidacionOtp";
		}

		return "";
	}

	/**
	 * Bloquea la cuenta del asegurado por intentos fallidos
	 *
	 * @return
	 */
	public String bloquearCuentaIntentosFallidos() {
		// Se cierra la sesion para que no pueda seguir navegando en el aplicativo
		aceptar();

		return "";
	}

	/**
	 * Valida si el monto del prestamo es mayor o igual al monto m�nimo.
	 *
	 *  @return boolean
	 */
	public boolean cumpleMontoMinimo() {
		try {
			if (!prestamoServicio.cumpleMontoMinimo(prestamoCalculo.getMontoPrestamo())) {
				error = "El monto del cr\u00E9dito debe ser mayor o igual a 1 USD.";
				return false;
			}
		} catch (final PrestamoException e) {
			log.error(e.getMessage(), e);
			error = e.getMessage();
			return false;
		}

		return true;
	}

	/**
	 * Metodo que verifica si el credito cumple las condiciones antifraude.
	 *
	 * @throws ValidacionMontoCtaBcoException
	 */
	private void verificarCondicionesAntiFraude() throws ValidacionMontoCtaBcoException {
		// INC-2272 Pensiones Alimenticias // INC-2148: Refugiados. // INC-2452 Ajuste validacion PDV
		if (datos.isPagoPensionesAlimenticias() || datos.isBeneficiarioRefugiado()) {
			this.flagcambioinucta = false;
			this.flagvalidacionrc = false;
			this.popupvalidacionrc = null;
			return;
		}

		/* INC.1800. Control PDA para jubilados. Se consulta movimientos inusuales en el registro de cuentas */
		this.flagcambioinucta = false;
		if (cambioInusualCtaBco()) {
			this.flagcambioinucta = true;
			this.flagvalidacionrc = false;
			this.popupvalidacionrc = null;

			return;
		}

		/* Bandera que indica si el cr�dito pasa por validaci�n registro civil */
		this.flagvalidacionrc = true;
		this.popupvalidacionrc = "VRC";
		/* Si no realizo cambio de clave en el nuevo sistema de claves del Iess
		 * y no cumple condiciones de monto y cambio cuenta banco sigue el curso normal del credito */
		this.flagcambioclave = verificarCambioClave();
		this.validacionmontoctabco = entraValidacionMontoCtaBco();
		if (!flagcambioclave && !validacionmontoctabco) {
			this.flagvalidacionrc = false;
			this.popupvalidacionrc = null;
		}
	}

	/**
	 * Metodo que verifica si existe mas de dos cambios (registro o anulacion) de cta bancaria
	 * en un periodo de 30 dias inmediatamente anteriores.
	 *
	 * @return boolean
	 */
	private boolean cambioInusualCtaBco() {
		return prestamoServicio.cambioInusualCtaBco(datos.getSolicitante().getDatosPersonales().getCedula());
	}

	/**
	 * M�todo que valida si el (monto del credito es mayor o igual al monto minimo de control antifraudes)
	 * y (si se realiz� una cambio de cuenta bancaria luego de la fecha Biess)
	 *
	 * @return boolean
	 * @throws ValidacionMontoCtaBcoException
	 */
	private boolean entraValidacionMontoCtaBco() throws ValidacionMontoCtaBcoException {
		/* Obtener monto de validaci�n PDA */
		BigDecimal montoPDA = null;
		try {
			montoPDA = parametroservicio.obtenermMontopqparavalidacionpda();
		} catch (final ParametroNoEncontradoException e) {
			throw new ValidacionMontoCtaBcoException("No se puedo recuperar el parametro montopq", e);
		}
		if (null == montoPDA) {
			throw new ValidacionMontoCtaBcoException("El parametro montopq es nulo");
		}
		/* Si monto prestamo es menor a montoPDA no importa si cambio de cuenta banco */
		if (prestamoCalculo != null && prestamoCalculo.getMontoPrestamo() != null) {
			if (prestamoCalculo.getMontoPrestamo().compareTo(montoPDA) < 0) {
				return false;
			}
		}
		/* Obtener fecha validaci�n PDA */
		Date fechaPDA = null;
		try {
			fechaPDA = parametroservicio.obtenerFechabiess();
		} catch (final ParametroNoEncontradoException e) {
			throw new ValidacionMontoCtaBcoException("No se puedo recuperar el parametro fecbiess", e);
		}
		if (null == fechaPDA) {
			throw new ValidacionMontoCtaBcoException("El parametro fecbiess es nulo");
		}
		/* Si se han perdido datos de session */
		if (datos.getSolicitante() == null) {
			throw new ValidacionMontoCtaBcoException("Se han perdido valores de sesion al validar cuenta bancaria.");
		}

		/* Validar cambio de cuenta afiliado */
		return afiliadoCambioCtaBco(fechaPDA);
	}

	/**
	 * M�todo que valida si la cuenta bancaria de afiliado cambio luego de la fecha Biess.
	 *
	 * @param fechaBiess
	 * @return boolean
	 */
	private boolean afiliadoCambioCtaBco(final Date fechaBiess) {
		/* Bandera que indica si hay cuentas autorizadas luego de fecha biess */
		boolean flagfechaaut = true;
		/* Bandera que indica si hay cuentas registradas luego de fecha biess */
		boolean flagfechareg = true;
		/* Obtener cuentas autorizadas */
		CuentaBancariaAfiliado ctaafidatos = null;
		ctaafidatos = cuentabancariaafiliadoservicio.findCuentaValidaAfiliado(
				datos.getSolicitante().getDatosPersonales().getCedula());
		/* Si al momento de la validaci�n no hay cuentas autorizadas */
		if (null == ctaafidatos) {
			return true;
		}
		/* Obtener cuentas registradas */
		CuentaBancariaAfiliado ctaafidatosreg = null;
		final List<CuentaBancariaAfiliado> lstctb = cuentabancariaafiliadoservicio
				.findCuentasPorAfiliadoEstado(datos.getSolicitante().getDatosPersonales().getCedula(),
						EstadoCuentaAfiliadoEnum.REG);
		if (null != lstctb && !lstctb.isEmpty()) {
			ctaafidatosreg = lstctb.get(0);
		}
		/* Validar cuentas en estado registrado */
		if (null != ctaafidatosreg) {
			/* Validar si fecha de registro o actualizacion es mayor o igual a fecha biess */
			if (ctaafidatosreg.getFechaActualizacion() == null) {
				/* Si no hay fecha actualizaci�n validar fecha de registro */
				if (ctaafidatosreg.getFechaRegistro().getTime() < fechaBiess.getTime()) {
					flagfechareg = false;
				}
				/* Caso contrario validar fecha actualizaci�n */
			} else {
				if (ctaafidatosreg.getFechaActualizacion().getTime() < fechaBiess.getTime()) {
					flagfechareg = false;
				}
			}
		} else {
			flagfechareg = false;
		}
		/* Validar cuentas en estado autorizado */
		/* Validar si fecha de registro o actualizacion es mayor o igual a fecha biess */
		if (ctaafidatos.getFechaActualizacion() == null) {
			/* Si no hay fecha actualizaci�n validar fecha de registro */
			if (ctaafidatos.getFechaRegistro().getTime() < fechaBiess.getTime()) {
				flagfechaaut = false;
			}
			/* Caso contrario validar fecha actualizaci�n */
		} else {
			if (ctaafidatos.getFechaActualizacion().getTime() < fechaBiess.getTime()) {
				flagfechaaut = false;
			}
		}
		/*
		 * Si no existen cuentas autorizadas ni registradas luego de fecha biess se asume que el afiliado no cambio cuenta banco
		 */
		if (!flagfechaaut && !flagfechareg) {
			return false;
		}
		/* Verificar lista blanca */
		final CuentaBancaria ctaAfiliado = datos.getSolicitante().getCuentaBancaria();
		if (prestamoServicio.existecuentaenlistablanca(datos.getSolicitante().getDatosPersonales().getCedula(),
				ctaAfiliado.getInstitucionFinanciera().getRuc(), ctaAfiliado.getTipoCuenta().getCodigo(),
				ctaAfiliado.getNumeroCuenta())) {
			/* Si la cuenta esta en lista blanca se asume que es una cuenta verificada */
			return false;
		}

		return true;
	}

	/**
	 * Verifica si el afiliado/jubilado se encuentra en listas de observados
	 *
	 * @return
	 */
	public String revisionListaObservados() throws CrearCreditoQuirografarioException {
		// INC-2588 Implementacion automatizada de // verificacion en lista de control de usuarios PQ.
		try {
			final String cedula = datos.getSolicitante().getDatosPersonales().getCedula();
			final TipoPrestamista tipoPrestamista = datos.getTipo();

			final ValidarRequisitosFondos validarFondos = new ValidarRequisitosFondos();
			validarFondos.setCedula(cedula);
			validarFondos.setTiposCargos(PrecalificacionUtil.obtenerTiposCargos());
			validarFondos.setEstadoBloqueado(EstadosCredito.ESTADO_BLOQUEO_CUENTA);
			validarFondos.setEstadosSolicitud(PrecalificacionUtil.obtenerEstadosSolicitud());

			final DatosGarantia datosGarantia = new DatosGarantia();
			datosGarantia.setCedula(cedula);
			datosGarantia.setFechaSolicitud(new Date());
			datosGarantia.setTipoPrestamista(tipoPrestamista);
			datosGarantia.setValReqFondos(validarFondos);
			datosGarantia.setCuotaMensualBuro(BigDecimal.ZERO);
			datosGarantia.setFechaNacimiento(this.datos.getSolicitante().getDatosPersonales().getFechaNacimiento());

			final ValidarRequisitosPrecalificacionBiess reqPrecalificacion = new ValidarRequisitosPrecalificacionBiess();
			reqPrecalificacion.setCedula(cedula);
			reqPrecalificacion.setTipoPrestamista(tipoPrestamista);
			reqPrecalificacion.setEstadoCreditoCuentaBancaria(PrecalificacionUtil.obtenerEstadosCuentaBancaria());
			reqPrecalificacion.setEstadoCreditoHl(PrecalificacionUtil.obtenerEstadosHl());
			reqPrecalificacion.setEstadoSolicitudHlPH(PrecalificacionUtil.obtenerEstadosHlPH());
			reqPrecalificacion.setCodEstSolSerList(PrecalificacionUtil.obtenerEstadosSolicitudPH());
			reqPrecalificacion.setCodTipSolSerList(PrecalificacionUtil.obtenerTiposSolicitudPH());
			reqPrecalificacion.setSolicitante(datos.getSolicitante());
			reqPrecalificacion.setGarantia(datosGarantia);
			// Datos Orden Compra
			reqPrecalificacion.setDatosOrdenCompra(datos.getDatosOrdenCompra());
			/* Fijo informacion de discapacitado */
			reqPrecalificacion.setDiscapacitado(datos.getDiscapacitado());

			if (TipoPrestamista.JUBILADO.equals(reqPrecalificacion.getTipoPrestamista())) {
				// Si es jubilado solo necesitoa prestaciones
				this.datos.setInformacionIessServicio(this.informacionIessServicio.obtenerInformacion(this.datos.getInformacionIessServicio(),
						TipoInformacionServicioIessEnum.PRESTACIONES));
			} else {
				this.datos.setInformacionIessServicio(this.informacionIessServicio.obtenerInformacion(this.datos.getInformacionIessServicio(),
						TipoInformacionServicioIessEnum.APORTES_MORA_CESANTIAS));
				
					 this.datos.setInformacionGarantia(this.datos.getInformacionGarantia()==null?garantiasSAC.obtenerGarantias(getHttpServletRequest().getRemoteUser()):this.datos.getInformacionGarantia());
			}
			if(datos.getInfoPqExigile()==null) {
				datos.setInfoPqExigile(creditoPqClientesEmpl.obtenerInfoPqOperEmp(getHttpServletRequest().getRemoteUser()));
			}
			final Precalificacion precalificacionDato = precalServicio.obtenerPrecalificacion(reqPrecalificacion, BigDecimal.ZERO,
					datos.getRolPrestamista(), this.datos.getInformacionIessServicio(), datos.isPagoPensionesAlimenticias(),
					datos.isProductoBiessEmergente(),datos.getInfoPqExigile(),this.datos.getInformacionGarantia());
			precalificacionDato.setIdUsuarioLogueado(getHttpServletRequest().getRemoteUser());
			precalificacionDato.setIpUsuarioLogueado(DireccionIPUtil.obtenerDireccionIPCliente(getHttpServletRequest()));

			precalificacion = precalListObsServicio.obtenerPrecalificacionListaObservados(precalificacionDato);

			// Seteo la bandera para Listas PEP.
			this.datos.setEnListaObservadosPEP(precalificacion.isEnListaObservadosPEP());
			this.datos.setBloqueoListaControl(precalificacion.getBloqueoListaControl());

			if (precalificacion.isEnListaObservadosCONSEP()) {
				// Generacion del Formulario para imprimir.
				String codigoProvincia = "";
				final String codigo = cedula.substring(0, 2);
				try {
					final BiessDivisionPolitica provincia = this.biessDivPolServicio.obtenerPorCodigo(codigo);
					if (provincia != null && provincia.getNomenclatura() != null) {
						codigoProvincia = provincia.getNomenclatura();
					}
				} catch (final BiessDivisionPoliticaException e) {
					throw new PrecalificacionListaObservadosException(e);
				}
				codigoNotificacion = ConstantesListaObservadosWS.UNIDAD_CUMPLIMIENTO + "-" + ConstantesListaObservadosWS.LISTA_CONTROL + "-"
						+ ConstantesListaObservadosWS.CONCEP + "-" + codigoProvincia;
			}
		} catch (final PrecalificacionListaObservadosException e) {
			throw new CrearCreditoQuirografarioException();
		} catch (final Exception e) {
			throw new CrearCreditoQuirografarioException();
		}

		return "";
	}

	/**
	 * Obtiene la variedad de credito (codprecla) dado el tipo de prestamista y el origen jubilado
	 *
	 * @param tipoPrestamista
	 * @param origenJubilado
	 * @return
	 * @throws CalculoCreditoException
	 */
	private int obtenerVariedadCredito(final TipoPrestamista tipoPrestamista, final OrigenJubilado origenJubilado) throws CalculoCreditoException {
		return CalculoCreditoHelperSingleton.determinarIdVariedadCredito(tipoPrestamista, origenJubilado);
	}

	/**
	 * Metodo que controla dos peticiones simultaneas
	 *
	 * @return
	 */
	public String prestamoEnProceso() {
		log.info("******PRESTAMO EN PROCESO*******");
		// Si existe un prestamo en proceso
		if (this.datos.isPrestamoEnProceso()) {
			// Esperamos 10 segundos a que se procese
			try {
				Thread.sleep(Long.parseLong(super.getResource(STR_MESSAGES, "tiempo.valida.credito.en.proceso")));

				// Si aun no termina le damos 10 segundos mas
				if (this.datos.isPrestamoEnProceso()) {
					Thread.sleep(Long.parseLong(super.getResource(STR_MESSAGES, "tiempo.valida.credito.en.proceso")));
				}
			} catch (final InterruptedException e1) {
				log.error("Error en tiempo de espera prestamo", e1);
				Thread.currentThread().interrupt();
			}
		}
		return "";
	}

	/**
	 * Metodo que procesa la concecion del credito.
	 *
	 * @return String
	 */
	public String procesarPrestamo() {
		if (this.datos.isPrestamoEnProceso()) {
			return "";
		}
		this.datos.setPrestamoEnProceso(true);

		try {
			revisionListaObservados();
		} catch (final CrearCreditoQuirografarioException e) {
			final String respuestaFinal = descripcionExcepcion(e);
			message = MENSAJE_ERROR_CONCESION + respuestaFinal + MENSAJE_INTENTE_TARDE;
			log.error(e.getMessage() + " " + respuestaFinal);
			prestamoOk = false;
			this.prestamoRefugiadoFallido = true;
			datos.setPrestamoConcedido(false);
			return "";
		}


		if (precalificacion.isEnListaObservados()) {
			log.error("No se pudo conceder el credito ya que el afiliado jubilado se encuentra en listas de observados para cedula: "
					+ datos.getSolicitante().getDatosPersonales().getCedula());
			message = "El cr\u00E9dito ha sido negado";
			prestamoOk = false;
			this.prestamoRefugiadoFallido = true;
			datos.setPrestamoConcedido(false);
			return "resultadoPrestamo";
		} else {

			this.prestamoRefugiadoFallido = false;
			/* Si existen creditos en estado ERC o ECC el usuario esta bloqueado para crear nuevos creditos */
			if (null != datos.getListaPrestamosERC() || null != datos.getListaPrestamosECC()) {
				return null;
			}
			/* INC-1800. Control PDA para jubilados. Se agrega bandera de bloqueo al credito que se intenta novar */
			if (datos.isCreditoNovacion() && this.flagcambioinucta) {
				prestamoServicio.actualizarPrestamoCambioInuCtaBco(datosCredito.getPrestamoAnteriorNovacion());
				return null;
			}


			// INC - 2292 Mejora Refugiados
			if (datos.isBeneficiarioRefugiado()) {
				if (datos.getSolicitante().getDatosPersonales().getRegistroCivilExtranjero() == null
						|| datos.getSolicitante().getDatosPersonales().getRegistroCivilExtranjero().getNumeroIdentificacion() == null) {
					log.error(
							"No se pudo conceder el cr\u00E9dito, No tiene registrado ning\u00FAn documento de identificaci\u00F3n en el IESS: Pasaporte, Carnet de Refugiado o Carnet laboral. Por favor ac\u00E9rquese a las oficinas del IESS para regularizar su documentaci\u00F3n.");
					message = "No se pudo conceder el cr\u00E9dito, No tiene registrado ning\u00FAn documento de identificaci\u00F3n en el IESS: Pasaporte, Carnet de Refugiado o Carnet laboral. Por favor ac\u00E9rquese a las oficinas del IESS para regularizar su documentaci\u00F3n.";
					prestamoOk = false;
					this.prestamoRefugiadoFallido = true;
					datos.setPrestamoConcedido(false);
					return "resultadoPrestamo";

				}
			}

			/* Flujo credito normal */
			prestamoQuirografarioProcesado = true;
			procesado = 1;
			log.info("------INICIO aceptarPrestamo -----------");
			log.debug("concediendo prestamo");
			if (datos.getSolicitante() == null) {
				log.warn("se ha perdido valores de sesion");
				return "errorSesionVacia";
			}

			if (datos.isPrestamoConcedido()) {
				log.warn("ya se le ha concedido el credito");
				log.warn("redireccionando a cerrarSesion.jsp");
				return "errorYaFueConcedido";
			}

			int idVariedadCredito;
			final TipoPrestamista tipoPrestamista = datos.getTipo();
			try {
				idVariedadCredito = obtenerVariedadCredito(tipoPrestamista, datos.getSolicitante().getOrigenJubilado());
			} catch (final CalculoCreditoException e1) {
				log.error("No se pudo conceder el prestamo a la cedula:" + datos.getSolicitante().getDatosPersonales().getCedula());
				log.error(e1.getMessage());
				log.error(e1);
				message = e1.getMessage();
				prestamoOk = false;
				this.prestamoRefugiadoFallido = true;
				datos.setPrestamoConcedido(false);
				return "resultadoPrestamo";
			}

			// INC-2272 Pensiones Alimenticias
			final int idTipoCredito = datos.getTiposProductosPq().getCodigoTipoProducto().intValue();

			final String cedulaAfiliado = datos.getSolicitante().getDatosPersonales().getCedula();
			String tipoSolicitante = "P";
			if (datos.getTipo().equals(TipoPrestamista.AFILIADO)) {
				tipoSolicitante = "A";
			}
			final String idDivisionPolitica = datos.getSolicitante().getDatosPersonales().getParroquiaId();

			// INC-2272 Pensiones Alimenticias
			final InstitucionFinanciera institucionFinanciera = new InstitucionFinanciera();
			if (datos.isPagoPensionesAlimenticias()) {
				institucionFinanciera.setInstitucionId(datos.getCuentaBancariaBeneficiarioCredito().getInstitucionFinanciera().getRuc());
				institucionFinanciera.setNumeroCuenta(datos.getCuentaBancariaBeneficiarioCredito().getNumeroCuenta());
				institucionFinanciera.setTipoCuentaId(datos.getCuentaBancariaBeneficiarioCredito().getTipoCuenta().getCodigo());
			} else {
				institucionFinanciera.setInstitucionId(datos.getSolicitante().getCuentaBancaria().getInstitucionFinanciera().getRuc());
				institucionFinanciera.setNumeroCuenta(datos.getSolicitante().getCuentaBancaria().getNumeroCuenta());
				institucionFinanciera.setTipoCuentaId(datos.getSolicitante().getCuentaBancaria().getTipoCuenta().getCodigo());
			}

			final Empleador empleador = datos.getSolicitante().getEmpleador();
			final BigDecimal tasaInteres = calculoCredito.getCondicionCalculo().getTasaInteres();
			// Antes de crear el cr�dito validar si se puede descontar el
			// dividendo
			// solo para jubilados
			if (idVariedadCredito != 20 && idVariedadCredito != 22) {
				if (!this.validarSiDividendoPuedeSerPagado()) {
					log.error("No se pudo conceder el prestamo a la cedula:" + cedulaAfiliado);
					message = "NO SE PUDO CONCEDER EL CREDITO POR CUANTO SU CAPACIDAD DE ENDEUDAMIENTO NO DEBE SUPERAR MENSUALMENTE" + " DEL 40% DE $"
							+ datos.getSolicitante().getRentaTotalNomina() + ".  PUEDE VOLVER A SIMULAR SU CREDITO "
							+ "CONSIDERANDO UNA CUOTA MENSUAL MENOR A $" + datos.getSolicitante().getRentaTotalNomina() * 0.4;
					prestamoOk = false;
					this.prestamoRefugiadoFallido = true;
					datos.setPrestamoConcedido(false);
					return "resultadoPrestamo";
				}
			}

			/*
			 * Antes de solicitar el guardado se va a determinar si hay que guardar las garantias
			 */
			if (TipoPrestamista.AFILIADO.equals(tipoPrestamista) || TipoPrestamista.ZAFRERO_TEMPORAL.equals(tipoPrestamista)) {
				prestamoCalculo.setGuardarGarantias(Boolean.TRUE);
			} else {
				prestamoCalculo.setGuardarGarantias(Boolean.FALSE);
			}
			try {
				datos.getDatosGarantia().setIdTipocredito(idTipoCredito);
				datos.getDatosGarantia().setIdVariedadCredito(idVariedadCredito);
				if (TiposProductosPq.TUR == this.datos.getTiposProductosPq() || TiposProductosPq.FOC == this.datos.getTiposProductosPq()
						|| TiposProductosPq.ECU_TUR == this.datos.getTiposProductosPq() || TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq()) {
					datos.getDatosGarantia().setMontocredito(
							prestamoCalculo.getMontoPrestamo().add(prestamoCalculo.getSeguroSaldo().getValor().setScale(2, RoundingMode.HALF_UP)));
				} else {
					datos.getDatosGarantia().setMontocredito(prestamoCalculo.getMontoPrestamo());
				}

				// Datos Prestamo
				datosCredito.setDividendoCalculoList(prestamoCalculo.getDividendoCalculoList());
				datosCredito.setEmpleador(empleador);
				final EstadoDividendoPrestamo estadoDividendoPrestamo = new EstadoDividendoPrestamo();
				estadoDividendoPrestamo.setCodestdiv(EstadosCredito.ESTADO_DIV_REG);
				datosCredito.setEstadoDividendoPrestamo(estadoDividendoPrestamo);
				datosCredito.setFechaSolicitud(new Date());
				datosCredito.setIdDivisionPolitica(idDivisionPolitica);
				datosCredito.setIdTipoCredito(idTipoCredito);
				datosCredito.setIdVariedadcredito(idVariedadCredito);

				datosCredito.setInstitucionFinanciera(institucionFinanciera);
				datosCredito.setPrestamoCalculo(prestamoCalculo);
				datosCredito.setTasaInteres(tasaInteres);
				datosCredito.setTipoSolicitante(tipoSolicitante);
				final TipoDividendo tipoDividendo = new TipoDividendo();
				tipoDividendo.setCodtipdiv(TiposCredito.TIPO_DIVIDENDO_NOR);
				datosCredito.setTipoDividendo(tipoDividendo);

				// Datos Adicionales
				datosCredito.setOrdenPrestamo(1L);
				datosCredito.setTipoPeriodo("M");
				datosCredito.setParametroPrestamo("87020701");

				final Calendar cl = Calendar.getInstance();
				cl.setTime(datosCredito.getFechaSolicitud());
				datosCredito.setAnio(new Long(cl.get(Calendar.YEAR)));
				datosCredito.setMes(new Long(cl.get(Calendar.MONTH) + 1));

				final EstadoPrestamo estadoPrestamo = new EstadoPrestamo();

				// Verificar el estado puede ser GEN o PDA dependiendo de una
				// validacion
				// if (flagestadopda)
				// estadoPrestamo.setCodestpre("PDA");
				// else
				estadoPrestamo.setCodestpre("GEN");

				datosCredito.setEstadoPrestamo(estadoPrestamo);

				datosCredito.setClasePrestamoReal("NUV");

				// Datos Credito

				datosCredito.setIdTipoCredito(idTipoCredito);
				datosCredito.setIdVariedadcredito(idVariedadCredito);
				datosCredito.setFechaSolicitud(new Date());
				datosCredito.setCedulaAfiliado(cedulaAfiliado);
				datosCredito.setTipoSolicitante(tipoSolicitante);
				datosCredito.setIdDivisionPolitica(idDivisionPolitica);
				datosCredito.setPrestamoCalculo(prestamoCalculo);
				datosCredito.setInstitucionFinanciera(institucionFinanciera);
				datosCredito.setEmpleador(empleador);
				datosCredito.setTasaInteres(tasaInteres);
				// datosCredito.setPrestamo(datosPrestamo);
				datosCredito.setGarantia(datos.getDatosGarantia());
				// Cambio Hecho por Angel Sarmiento
				// Se agraga El Motivo Del Prestamo
				datosCredito.setDtocatalogo(dtocatalogo);

				// Fijar tipo de beneficiario
				datosCredito.setTipoBeneficiario(datos.getTipoBeneficiario());

				// Bandera para prestamos con nuevos parametros de tasa de interes
				datosCredito.setParametrizacion(1L);

				datosCredito.setEdadAsegurado(this.edadAsegurado);

				// INC-2272 Pensiones Alimenticias
				if (datos.isPagoPensionesAlimenticias()) {
					datosCredito.setPagoPensionesAlimenticias(datos.isPagoPensionesAlimenticias());
					datosCredito.setBeneficiarioCredito(datos.getBeneficiarioCredito());
				}

				// INC-2148 Refugiados
				if (datos.isBeneficiarioRefugiado()) {
					datosCredito.setVisaPasaporteAfiliado(
							datos.getSolicitante().getDatosPersonales().getRegistroCivilExtranjero().getNumeroIdentificacion());
				}

				if (datos.getProveedor() != null) {
					datosCredito.setProveedor(datos.getProveedor());
				}

				//Quito validacion ERC ya que no se valida datos registros civil REQ-161
				if (flagvalidacionrc && !this.datos.getTiposProductosPq().equals(TiposProductosPq.DINAMICO)) {
					datosCredito.setValidacionCredito(ValidacionPrestamoEnum.TIPO_PDA.getTipo());
				}

				boolean flagupdated = false;
				if (!flagvalidacionrc && !this.datos.getTiposProductosPq().equals(TiposProductosPq.DINAMICO)) {
					// Cuando es un prestamo focalizado o Ecuador tu lugar en el mundo y una persona es PEP no se pide
					// informacion adicional ni entra en estado PDV, por lo que se setea a false
					if (datos.isEnListaObservadosPEP() && (CategoriaTipoProductoPq.CAT_FOCALIZADOS != this.datos.getCategoriaTipoProductoPq()
							&& TiposProductosPq.ECU_TUR != this.datos.getTiposProductosPq())) {
						datosCredito.setValidacionCredito(ValidacionPrestamoEnum.TIPO_PDV.getTipo());
						flagupdated = true;
					}

					if (!flagupdated) {
						if (datos.getCategoriaTipoProductoPq().equals(CategoriaTipoProductoPq.CAT_SERVICIOS)
								&& (this.datos.getTiposProductosPq().equals(TiposProductosPq.TREN)
								|| this.datos.getTiposProductosPq().equals(TiposProductosPq.TUR))) {
							datosCredito.setValidacionCredito(ValidacionPrestamoEnum.TIPO_PAP.getTipo());
						} else if ((CategoriaTipoProductoPq.CAT_FOCALIZADOS != this.datos.getCategoriaTipoProductoPq())
								&& (datos.isPagoPensionesAlimenticias() || datos.isBeneficiarioRefugiado())) {
							datosCredito.setValidacionCredito(ValidacionPrestamoEnum.TIPO_PDC.getTipo());
						}
					}
				}

				// En caso que sea credito PQ Focalizado setea la informacion requerida
				if (this.datos.getCategoriaTipoProductoPq().equals(CategoriaTipoProductoPq.CAT_FOCALIZADOS)) {
					final String codigoActivacion = this.otpServicio.obtenerCodigoActivacion(this.idTransaccion, this.datos.getSolicitante().getDatosPersonales().getCedula());
					this.datosCredito.setCodigoActivacion(codigoActivacion);
					this.datosCredito.setCodigoActivacionEncripta(EncriptacionUtil.generarMD5(codigoActivacion));
					this.datosCredito.setListsProductosFocalizados(this.listaProductosFocalizados);
					this.datosCredito.setIdProveedorMeer(this.datos.getIdProveedorMeer());
				}

				// En caso que sea credito PQ Ecuador Tu Lugar en el Mundo setea la informacion requerida
				if (TiposProductosPq.ECU_TUR == this.datos.getTiposProductosPq()) {
					this.datosCredito.setNumeroReserva(this.codigoReservaEcuTur);
				}

				// Setea valor para prestamos especiales
				datosCredito.setCreditoEspecial(null);
				if (datos.isProductoBiessEmergente()) {
					datosCredito.setCreditoEspecial(CreditoEspecialEnum.EMERGENTE.getCodigoCredito());
				}
				// Req-131 viaja ecuador
				if(datos.isProdViajaEcu()) {
					datosCredito.setCreditoEspecial(COD_PROD_VIAECU);
				}
				//Agrego el numero de contrato o reserva y seteo el valor de credito especial
				if (TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq()) {
					this.datosCredito.setNumeroReserva(this.datos.getCodigoContratoProv());
					datosCredito.setCreditoEspecial(datos.getCodigoProdEspecial());
				}

				// Para la novacion
				if (datos.isCreditoNovacion() && prestamoQuirografarioProcesado) {
					datosCredito.setEsNovacion(datos.isCreditoNovacion());
					datosCredito.setPrestamoAnteriorNovacion(
							((CreditoAccionBean) getSession().getAttribute("creditoAccion")).getPrestamoSeleccionadoNovacion());
					prestamoServicio.novarCreditoQuirografario(datosCredito);

				} else {

					// fin del cambio
					prestamoServicio.crearCreditoQuirografario(datosCredito);
				}

				// se ha concedido el prestamo con exito
				prestamoOk = true;

				/* PK nuevo credito creado */
				final PrestamoPk prestamoPK = new PrestamoPk();
				prestamoPK.setCodprecla(datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodprecla());
				prestamoPK.setCodpretip(datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodpretip());
				prestamoPK.setNumpreafi(datosCredito.getVariablePrestamo().getSecvarpre());
				prestamoPK.setOrdpreafi(datosCredito.getOrdenPrestamo());

				/* INC-1817 Notificaciones asegurados */
				final InformacionAfiliado informacionAfiliado = new InformacionAfiliado();
				informacionAfiliado.setApellidosNombres(datos.getSolicitante().getDatosPersonales().getApellidosNombres());
				informacionAfiliado.setCedula(datos.getSolicitante().getDatosPersonales().getCedula());
				informacionAfiliado.setCelular(datos.getSolicitante().getDatosPersonales().getCelular());
				informacionAfiliado.setEmail(datos.getSolicitante().getDatosPersonales().getEmail());


				/**
				 * Generar Auditoria.
				 */
				final ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedulaCredito(
						this.datos.getSolicitante().getDatosPersonales().getCedula(), prestamoPK.getCodprecla().toString(),
						prestamoPK.getCodpretip().toString(), prestamoPK.getNumpreafi().toString(), prestamoPK.getOrdpreafi().toString());
				super.guardarAuditoria(OperacionEnum.CREACION_CREDITO_PQ, parametroEvento,
						this.datos.getSolicitante().getDatosPersonales().getCedula());

				// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
					boolean esPEP=Boolean.FALSE;
	try {
					if (this.datos.isEnListaObservadosPEP()) {
						final BloqueoListaControl bloqueoListaControl = this.datos.getBloqueoListaControl();
						bloqueoListaControl.setCodprecla(datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodprecla());
						bloqueoListaControl.setCodpretip(datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodpretip());
						bloqueoListaControl.setNumpreafi(datosCredito.getVariablePrestamo().getSecvarpre());
						bloqueoListaControl.setOrdpreafi(datosCredito.getOrdenPrestamo());
	                                        prestamoServicio.actualizarcabeceraprestamoPDV(prestamoPK);
						this.bloqueoListaControlServicio.actualizar(bloqueoListaControl);
	                                         esPEP=Boolean.TRUE;
					}
				} catch (final BloqueoListaControlException e) {
					throw new PrecalificacionExcepcion(e);
				}

				// En caso que sea un credito pq focalizado o Ecuador Tu Lugar en el Mundo se pone la variable en false
				// para que no le muestre al usuario la pantalla de traer documentacion adicionales
				if (datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_FOCALIZADOS
						|| this.datos.getTiposProductosPq().equals(TiposProductosPq.ECU_TUR) || this.datos.getTiposProductosPq().equals(TiposProductosPq.DINAMICO)) {
					this.datos.setEnListaObservadosPEP(false);
				}

				boolean enviarCorreo = true;
				/* INC-1800. Control PDA para jubilados. Se cambia estado del credito a ECC */
				if (flagcambioinucta) {
					prestamoServicio.actualizarEstadoPrestamoECC(prestamoPK);
					enviarCorreo = false;
				}

				/* INC-1858 Actualizaci�n estado del credito segun resultados verificacion RC */
				if (datosCredito.getValidacionCredito() != null
						&& (datosCredito.getValidacionCredito().equals(ValidacionPrestamoEnum.TIPO_PDA.getTipo())
						|| datosCredito.getValidacionCredito().equals(ValidacionPrestamoEnum.TIPO_ERC.getTipo()))) {
					if (validacionmontoctabco && !esPEP) {
						prestamoServicio.actualizarcabeceraprestamoPDA(prestamoPK, true);
						enviarCorreo = true;
					}

				}



				if (enviarCorreo) {
					enviarAlertas(informacionAfiliado, prestamoPK);
				}

				if (datosCredito.getValidacionCredito() != null && datosCredito.getValidacionCredito().equals(ValidacionPrestamoEnum.TIPO_PDV.getTipo())) {
					prestamoServicio.actualizarcabeceraprestamoPDV(prestamoPK);
				}

				if (datosCredito.getValidacionCredito() != null && datosCredito.getValidacionCredito().equals(ValidacionPrestamoEnum.TIPO_PAP.getTipo())) {
					prestamoServicio.actualizarEstadoPrestamo(prestamoPK, "PAP");
				} else if (datosCredito.getValidacionCredito() != null && datosCredito.getValidacionCredito().equals(ValidacionPrestamoEnum.TIPO_PDC.getTipo())) {
					prestamoServicio.actualizarcabeceraprestamoPDC(prestamoPK);
				}

				if (datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_BIENES) {
					menssageOrdenOK = "Su cr\u00E9dito ha sido generado satisfactoriamente, para la orden de compra No. "
							+ datos.getDatosOrdenCompra().getNumeroOrden()
							+ ", la tabla de amortizaci\u00F3n creada es referencial, la cual se actualizar\u00E1 una vez que el proveedor haya confirmado la entrega de su producto.";
					// Informacion de la orden creada al webServices
					ConfirmarOrdenCompra(true);

				}
			// Validacion de prestamo para invocar a SAC
			final Prestamo prestamo = prestamoServicio.getPrestamoByPk(prestamoPK);
			if ("GEN".equals(prestamo.getEstadoPrestamo().getCodestpre())
					&& validarEstadosPermitidos(datosCredito.getVariablePrestamo().getVariablePrestamoPK())) {
				prestamoServicio.crearOperacionSAC(datosCredito, prestamo);
			}
				// control para saber que ya se le concedio el prestamo
				datos.setPrestamoConcedido(true);
				// message = "PRESTAMO CONCEDIDO EXITOSAMENTE";
			} catch (final BloqueoFinSemanaException e) {
				log.error("No se pudo conceder el prestamo a la cedula:" + cedulaAfiliado);
				log.error("Error: " + e.getMessage());
				final String respuestaFinal = descripcionExcepcion(e);
				this.message = e.getMessage();
				log.error(e.getMessage() + " " + respuestaFinal);
				this.prestamoOk = false;
				this.prestamoRefugiadoFallido = true;
				datos.setPrestamoConcedido(false);
			} catch (final MontoMinimoException e) {
				log.error("No se pudo conceder el prestamo a la cedula:" + cedulaAfiliado);
				log.error("Error: " + e.getMessage());
				final String respuestaFinal = descripcionExcepcion(e);
				message = e.getMessage();
				log.error(e.getMessage() + " " + respuestaFinal);
				prestamoOk = false;
				this.prestamoRefugiadoFallido = true;
				datos.setPrestamoConcedido(false);
			} catch (final DiasNoPermitidosNovacionException e) {
				log.error("No se pudo conceder el prestamo a la cedula:" + cedulaAfiliado);
				log.error("Error: " + e.getMessage());
				final String respuestaFinal = descripcionExcepcion(e);
				message = e.getMessage();
				log.error(e.getMessage() + " " + respuestaFinal);
				prestamoOk = false;
				this.prestamoRefugiadoFallido = true;
				datos.setPrestamoConcedido(false);
			} catch (final CrearCreditoQuirografarioException e) {
				log.error("No se pudo conceder el prestamo a la cedula:" + cedulaAfiliado);

				/*
				 * FacesMessage message = new FacesMessage(); message.setDetail(e.getMessage());
				 * message.setSummary(e.getMessage()); message.setSeverity(FacesMessage.SEVERITY_ERROR);
				 * context().addMessage(null, message);
				 */
				final String respuestaFinal = descripcionExcepcion(e);
				message = MENSAJE_ERROR_CONCESION + respuestaFinal + MENSAJE_INTENTE_TARDE;
				log.error(e.getMessage() + " " + respuestaFinal);
				prestamoOk = false;
				this.prestamoRefugiadoFallido = true;
				datos.setPrestamoConcedido(false);
			} catch (final OTPException e) {
				message = Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", "") ;
				prestamoOk = false;
				datos.setPrestamoConcedido(false);
			} catch (final SecuenciaBloqueadaException e) {
				log.error("No se pudo conceder el prestamo a la cedula:" + cedulaAfiliado);

				/*
				 * FacesMessage message = new FacesMessage(); message.setDetail(e.getMessage());
				 * message.setSummary(e.getMessage()); message.setSeverity(FacesMessage.SEVERITY_ERROR);
				 * context().addMessage(null, message);
				 */
				final String respuestaFinal = descripcionExcepcion(e);
				message = MENSAJE_ERROR_CONCESION + respuestaFinal + MENSAJE_INTENTE_TARDE;
				log.error(e.getMessage() + " " + respuestaFinal);
				prestamoOk = false;
				this.prestamoRefugiadoFallido = true;
				datos.setPrestamoConcedido(false);
			} catch (final NovarCreditoQuirografarioException e) {
				log.error(prestamoQuirografarioProcesado);
				final String respuestaFinal = descripcionExcepcion(e);
				log.error(e.getMessage() + " " + respuestaFinal);
				message = MENSAJE_ERROR_CONCESION + respuestaFinal + MENSAJE_INTENTE_TARDE;
				prestamoOk = false;
				this.prestamoRefugiadoFallido = true;
				datos.setPrestamoConcedido(false);

			} catch (final CabeceraCreditoQuirografarioException e) {
				log.error("No se pudo conceder el prestamo a la cedula:" + cedulaAfiliado + " ", e);
				final String mensajeExcepcion = Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", "");
				final String mensajeMuestra = Utilities.reemplazarStringHastaCaracter(mensajeExcepcion, ":", "");
				message = mensajeMuestra;
				prestamoOk = false;
				this.prestamoRefugiadoFallido = true;
				datos.setPrestamoConcedido(false);
			} catch (final Exception e) {
				datos.setPrestamoConcedido(false);
				final String respuestaFinal = descripcionExcepcion(e);
				message = MENSAJE_ERROR_CONCESION + respuestaFinal + MENSAJE_INTENTE_TARDE;
				log.error(e.getMessage() + " " + respuestaFinal);
				prestamoOk = false;
				this.prestamoRefugiadoFallido = true;

			}
			this.datos.setPrestamoEnProceso(false);
			log.info("------FIN aceptarPrestamo -----------");
		}

		notificarProveedorAprCredito();

		return "";
	}
	/**
	 * Permite validar los productos para cambiar el estado del creditos y crear nut
	 * 
	 * @param pk
	 * @return
	 */
	private boolean validarEstadosPermitidos(final VariablePrestamoPK pk) {
		//Se elimina el produto 4 que corresponde ahora a todos los dinamicos estos se aprueban de inmediato
		boolean productos = true;
		if (pk.getCodpretip().equals(TiposProductosPq.TREN.getCodigoTipoProducto())
				|| pk.getCodpretip().equals(TiposProductosPq.TUR.getCodigoTipoProducto())
				|| pk.getCodpretip().equals(TiposProductosPq.FOC.getCodigoTipoProducto())) {
			productos = false;
		}
		return productos;
	}

	private void notificarProveedorAprCredito() {
		if(prestamoOk && this.datos.getTiposProductosPq() == TiposProductosPq.DINAMICO
				&& this.datos.getCodigoProdEspecial()!=null) {
			final DataNotificaProvAprRequestDto dataNotificaProv=new DataNotificaProvAprRequestDto();
			dataNotificaProv.setCodigoContrato(this.datos.getCodigoContratoProv());
			dataNotificaProv.setCodigoEspecial( this.datos.getCodigoProdEspecial());
			dataNotificaProv.setCodigoProducto(TiposProductosPq.DINAMICO.getCodigoTipoProducto());
			dataNotificaProv.setFechaAprobacion(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			dataNotificaProv.setNumeroPrestamo(this.datosCredito.getVariablePrestamo().getSecvarpre());
			dataNotificaProv.setRucEmpresa(this.datos.getProveedor().getPrRuc());
			dataNotificaProv.setValorPaquete(this.datos.getDataRespGenericaResponseDto().getTotalFinanciar());
			dataNotificaProv.setValorDesembolso(this.getPrestamoCalculo().getLiquidoPagar().setScale(2, RoundingMode.HALF_UP));
			dataNotificaProv.setCodPreCla(this.datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodprecla());
			dataNotificaProv.setOrdPreAfi(this.datosCredito.getOrdenPrestamo());
			dataNotificaProv.setDocAceptacion(DatatypeConverter.printBase64Binary
					(obtenerTextoFAT().getBytes(Charset.forName("UTF-8"))));
			dataNotificaProv.setCodCategoria(this.datos.getDataRespGenericaResponseDto().getCodigoCategoria());

			String descError=null;
			try {
				prestamoDinamico.notificarAprobacionCredito(dataNotificaProv);
			} catch (final ConsultaDataPqDinamicoException e) {
				log.error("ConsultaDataPqDinamicoException",e);
				descError=e.getMessage();
			} catch (final ConecSrvPqDinamicoException e) {
				log.error("ConecSrvPqDinamicoException",e);
				descError=e.getMessage();
			}finally {
				final ParametroEvento parametroEvento = AuditoriaPqWebHelper
						.obtenerParametrosEnvioNotiProveedor(dataNotificaProv,descError);
			super.guardarAuditoria(OperacionEnum.ENVIO_APROB_CRED_PROV, parametroEvento,
						this.datos.getSolicitante().getDatosPersonales().getCedula());
			}
		}
	}

	/**
	 * Método para enviar alertas de correo
	 *
	 * @param informacionAfiliado
	 * @param prestamoPK
	 */
	private void enviarAlertas(final InformacionAfiliado informacionAfiliado, final PrestamoPk prestamoPK) {
		if (datos.isCreditoNovacion() && prestamoQuirografarioProcesado) {
			enviarAlertaNovacionCredito(informacionAfiliado, prestamoPK);
		} else {
			enviarAlertaNuevoCredito(informacionAfiliado, prestamoPK);
		}
	}

	/**
	 * Retorna la descripcion de la excepcion si existe
	 *
	 * @param e
	 * @return
	 */
	private String descripcionExcepcion(final Exception e) {
		//Se agrega gui si no existiera descripcion
		String guid=java.util.UUID.randomUUID().toString().replace("-", "");
		final Throwable res = devolverExcepcion(e.getCause());
		String respuestaFinal = " ";

		if (res != null
				&& res.getMessage().contains("&")
				&& res.getMessage().indexOf("&") != res.getMessage()
				.lastIndexOf("&")) {
			respuestaFinal = res.getMessage().substring(
					res.getMessage().indexOf("&") + 1,
					res.getMessage().lastIndexOf("&"));
		}
		log.error("GUID-ERROR:".concat(guid),e);
		return respuestaFinal.concat(" GUID:".concat(guid).concat(" "));
	}

	public String cancelarDividendos() {
		log.debug("no acepta dividendos");
		log.debug("cambia a sim");
		this.opcionSimCuota = new OpcionCredito();
		this.opcionSimMonto = new OpcionCredito();
		this.simulacion = null;
		panelActivoPrincipal = "sim";
		deshabilitarAmortizacion = true;
		deshabilitarSimulacion = false;
		activarCalculoCredito = false;

		return "cancelar";
	}

	private PlazoCreditoSinDocumentoFiduciario item() {
		final PlazoCreditoSinDocumentoFiduciario item = (PlazoCreditoSinDocumentoFiduciario) context()
				.getExternalContext().getRequestMap().get("item");
		return (item);
	}

	public HtmlTab getHtmlTabRef() {
		return htmlTabRef;
	}

	public void setHtmlTabRef(final HtmlTab htmlTabRef) {
		this.htmlTabRef = htmlTabRef;
	}

	/**
	 * @return the prestamoCalculo
	 */
	public PrestamoCalculo getPrestamoCalculo() {
		return prestamoCalculo;
	}

	/**
	 * @param prestamoCalculo
	 *            the prestamoCalculo to set
	 */
	public void setPrestamoCalculo(final PrestamoCalculo prestamoCalculo) {
		this.prestamoCalculo = prestamoCalculo;
	}

	/**
	 * @return the opcionSimMonto
	 */
	public OpcionCredito getOpcionSimMonto() {
		return opcionSimMonto;
	}

	/**
	 * @param opcionSimMonto
	 *            the opcionSimMonto to set
	 */
	public void setOpcionSimMonto(final OpcionCredito opcionSim) {
		this.opcionSimMonto = opcionSim;
	}

	/**
	 * @return the simulacion
	 */
	public Simulacion getSimulacion() {
		return simulacion;
	}

	/**
	 * @param simulacion
	 *            the simulacion to set
	 */
	public void setSimulacion(final Simulacion simulacion) {
		this.simulacion = simulacion;
	}

	/**
	 * @return the opcionSimCuota
	 */
	public OpcionCredito getOpcionSimCuota() {
		return opcionSimCuota;
	}

	/**
	 * @param opcionSimCuota
	 *            the opcionSimCuota to set
	 */
	public void setOpcionSimCuota(final OpcionCredito opcionSimCuota) {
		this.opcionSimCuota = opcionSimCuota;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * Retorna el interes acumulado
	 *
	 * @return
	 */
	public BigDecimal getTotalInteres() {

		BigDecimal totalInteres = new BigDecimal(0);
		BigDecimal totalInteresGracia = BigDecimal.ZERO;
		if(prestamoCalculo.getDividendoCalculoList() != null
				&& !prestamoCalculo.getDividendoCalculoList().isEmpty()){
			for (final DividendoCalculo dividendoCalculo : prestamoCalculo.getDividendoCalculoList()) {
				totalInteres = totalInteres.add(dividendoCalculo.getValorInteres());
				totalInteresGracia = totalInteresGracia.add(dividendoCalculo.getValorIntPerGra());
			}
	
		}
		//Migracion SAC se debe sumar el periodo de gracia
		totalInteres= totalInteres.add(totalInteresGracia);
		return totalInteres;
	}

	/**
	 *
	 * <b> Metodo que llena el monto maximo deacuerdo al plazo elegido. </b>
	 * <p>
	 * [Author: cbastidas, Date: 14/04/2011]
	 * </p>
	 *
	 */
	public void llenarMontoSimulacion() {
		//INC-2272 Pensiones Alimenticias
		if (datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_BIENES) {
			List<PlazoCredito> plazoCreditoList = null;
			plazoCreditoList = calculoCredito.getTablaReferenciaCredito()
					.getPlazoCreditoSinDocumentoFiduciario();
			//if (datos.getDatosOrdenCompra().getCodigoProducto() != TiposProductosPq.NOR.toString()) {
			final int plazoSim = calculoCredito.getPlazoMaximoProducto();
			for (final PlazoCredito plazoCredito : plazoCreditoList) {
				if (plazoSim <= plazoCredito.getOpcionCredito().getMeses()) {
					calculoCredito.setMontoMaximoProducto(plazoCredito
							.getValorMaximoCredito().setScale(2));
					break;
				}
			}
			//}
			calcularSimCuota();
		}
	}

	/**
	 *
	 * <b> Metodo que confirma que la orden de compra fue creada. </b>
	 * <p>
	 * [Author: cbastidas, Date: 25/04/2011]
	 * </p>
	 *
	 * @throws CalculoCreditoException
	 *
	 */
	public void ConfirmarOrdenCompra(final boolean exito)
			throws CalculoCreditoException {
		try {
			final BiessShopService service = new BiessShopService();
			final OrdenWSService port = service.getOrdenWSServicePort();
			final StringBuilder concatenadoPqOrdenCompra = new StringBuilder();
			int result = 0;
			if (exito) {
				concatenadoPqOrdenCompra.append(datosCredito
						.getPrestamoOrdenCompra().getPrestamoPk()
						.getCodpretip());
				concatenadoPqOrdenCompra.append(datosCredito
						.getPrestamoOrdenCompra().getPrestamoPk()
						.getOrdpreafi());
				concatenadoPqOrdenCompra.append(datosCredito
						.getPrestamoOrdenCompra().getPrestamoPk()
						.getCodprecla());
				concatenadoPqOrdenCompra.append(datosCredito
						.getPrestamoOrdenCompra().getPrestamoPk()
						.getNumpreafi());

				final Long numeroCredito = new Long(
						String.valueOf(concatenadoPqOrdenCompra));

				result = port.confirmarSolicitud(1, numeroCredito.intValue(),
						datos.getDatosOrdenCompra().getProveedor().getPrRuc(),
						datos.getDatosOrdenCompra().getNumeroOrden());

				if (result == 1) {
					administracionOrdenCompraProveedorServicio
							.actualizarOrdenCompraEstado(datosCredito);
				}
			}

			else {
				result = port.confirmarSolicitud(0, 0, datos
						.getDatosOrdenCompra().getProveedor().getPrRuc(), datos
						.getDatosOrdenCompra().getNumeroOrden());
			}
		} catch (final Exception e) {
			log.error("Error al consultar datos de la tienda virtual. " + e.getMessage(), e);
			throw new CalculoCreditoException("Error al consultar datos de la tienda virtual");
		}
	}

	/**
	 * Retorna el capital acumulado
	 *
	 * @return
	 */
	public BigDecimal getTotalCapital() {
		final List<DividendoCalculo> dividendoCalculoList = prestamoCalculo
				.getDividendoCalculoList();
		BigDecimal totalCapital = BigDecimal.ZERO;
		for (final DividendoCalculo dividendoCalculo : dividendoCalculoList) {
			totalCapital = totalCapital.add(dividendoCalculo
					.getValorAbonoCapital());
		}
		return totalCapital;
	}

	private boolean validarSiDividendoPuedeSerPagado() {
		final double totalPrimerDividendo = prestamoCalculo.getPeriodoGracia()
				.getValor().doubleValue()
				+ prestamoCalculo.getValorTotalDividendoMensual().doubleValue();
		final double valorMayorRenta = datos.getSolicitante().getRentaTotalNomina();

		if ((valorMayorRenta - totalPrimerDividendo) <= 0)
			return false;
		else
			return true;
	}

	public String generarOrdenCompra() {
		// String strEnlaceACtaBancaria = null;
		/*
		 * FacesContext ctx = FacesContext.getCurrentInstance(); HttpSession session = (HttpSession) ctx.getExternalContext() .getSession(false); session.invalidate();
		 */

		final ExternalContext ectx = FacesContext.getCurrentInstance()
				.getExternalContext();
		// HttpServletResponse response = (HttpServletResponse)
		// ectx.getResponse();
		final HttpSession session = (HttpSession) ectx.getSession(false);
		session.invalidate();
		// Navegar a la pagina principal
		final FacesContext ctx = FacesContext.getCurrentInstance();
		final Application app = ctx.getApplication();
		app.getNavigationHandler().handleNavigation(ctx,
				"/pages/concesion/roles.jsf", "roles");

		return null;
	}

	/**
	 * Verifica si es prestamo quirografario
	 *
	 * @retunr
	 * @author palvarez, cbastidas
	 */
	public boolean isPrestamoQuirografarioProcesado() {
		return prestamoQuirografarioProcesado;
	}

	public void setPrestamoQuirografarioProcesado(
			final boolean prestamoQuirografarioProcesado) {
		this.prestamoQuirografarioProcesado = prestamoQuirografarioProcesado;
	}

	public int getProcesado() {
		return procesado;
	}

	public void setProcesado(final int procesado) {
		this.procesado = procesado;
	}

	public String getMenssageOrdenOK() {
		return menssageOrdenOK;
	}

	public void setMenssageOrdenOK(final String menssageOrdenOK) {
		this.menssageOrdenOK = menssageOrdenOK;
	}


	/**
	 * Metodo que envia una alerta al usuario cuando se cierra sesion por OTP.
	 *
	 */
	private void enviarAlertaOTP(final String mensajeMail, final String validador) {

		/*Notificaciones Asegurados */
		final InformacionAfiliado informacionAfiliado = new InformacionAfiliado();
		informacionAfiliado.setApellidosNombres(datos.getSolicitante().getDatosPersonales().getApellidosNombres());
		informacionAfiliado.setCedula(datos.getSolicitante().getDatosPersonales().getCedula());
		informacionAfiliado.setCelular(datos.getSolicitante().getDatosPersonales().getCelular());
		informacionAfiliado.setEmail(datos.getSolicitante().getDatosPersonales().getEmail());

		String mensajeAdicional = "Si usted no realiz&#243; esta transacci&#243;n, por favor notif&#237;quelo "
				+ "llamando a nuestro Call Center al 1800 BIESS7. Opci&#243;n 6.";

		if (validador == null) {
			mensajeAdicional = "";
		}

		/*Formato Fecha*/
		final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd 'de' MMMMM 'del' yyyy 'a las' HH:mm:ss", new Locale("es"));
		final String fechaEnvio = formatoFecha.format(new Date());

		final String templatePath = "ec/fin/biess/creditos/pq/alertas/templates/email/otp.ftl";
		/* Parametros del mensaje */
		final Map<String, Object> alertData = new HashMap<String, Object>();
		alertData.put("msg_fecha",  fechaEnvio);
		alertData.put("msg_usuario",  informacionAfiliado.getApellidosNombres());
		alertData.put("msg_personalizado",  mensajeMail);
		alertData.put("msg_adicional",  mensajeAdicional);

		AlertUtil.enviarAlerta(alertUserHelper, informacionAfiliado, templatePath, alertData, null, AlertType.EMAIL);
	}

	/**
	 * Metodo que envia una alerta al usuario cuando se ha generado un nuevo credito.
	 *
	 * @param dp
	 * @param prestamoPK
	 */
	private void enviarAlertaNuevoCredito(final InformacionAfiliado dp, final PrestamoPk prestamoPK) {
		try {
			final String templatePath = "ec/fin/biess/creditos/pq/alertas/templates/email/credito.ftl";
			/* Parametros del mensaje */
			final Map<String, Object> alertData = new HashMap<String, Object>();
			alertData.put("msg_usuario",  dp.getApellidosNombres());
			AlertUtil.enviarAlerta(alertUserHelper, dp, templatePath, alertData, null, AlertType.EMAIL);
		} catch (final Exception e) {
			log.error("Error al enviar alerta de nuevo credito al afiliado: " + dp.getCedula(), e);
		}
	}

	/**
	 * Metodo que envia una alerta al usuario cuando se ha generado una novacion.
	 *
	 * @param dp
	 * @param prestamoPK
	 */
	private void enviarAlertaNovacionCredito(final InformacionAfiliado dp, final PrestamoPk prestamoPK) {
		try {
			final String templatePath = "ec/fin/biess/creditos/pq/alertas/templates/email/novacion.ftl";
			/* Parametros del mensaje */
			final Map<String, Object> alertData = new HashMap<String, Object>();
			alertData.put("msg_usuario",  dp.getApellidosNombres());
			AlertUtil.enviarAlerta(alertUserHelper, dp, templatePath, alertData, null, AlertType.EMAIL);
		} catch (final Exception e) {
			log.error("Error al enviar alerta de novacion al afiliado: " + dp.getCedula(), e);
		}
	}

	/**
	 * Metodo que verifica si el asegurado cambio la clave.
	 *
	 * @return boolean
	 */
	private boolean verificarCambioClave() {
		return cambioClaveDao.afiliadoCambioClave(datos.getSolicitante().getDatosPersonales().getCedula());
	}

	/**
	 * @return the prestamoRefugiadoFallido
	 */
	public boolean isPrestamoRefugiadoFallido() {
		return prestamoRefugiadoFallido;
	}

	/**
	 * @param prestamoRefugiadoFallido the prestamoRefugiadoFallido to set
	 */
	public void setPrestamoRefugiadoFallido(final boolean prestamoRefugiadoFallido) {
		this.prestamoRefugiadoFallido = prestamoRefugiadoFallido;
	}

	// INC-2344 Pensiones Alimenticias

	/**
	 * Setea las propiedades para imprimir el formulario de autorizacion de transferencias.
	 */
	public void imprimirFormularioAT() {
		//toma el seguimiento del guardado si existe
		if(TiposProductosPq.DINAMICO.equals(datos.getTiposProductosPq())) {
			try {
				this.textoFAT= prestamoDinamico.obtenerDocumentoContrato(this.datos.getCodigoContratoProv());
				this.desplegarFAT = true;
				//termino el proceso
				return;
			}catch(final ConsultaDataPqDinamicoException e) {
				//continuar con el proceso que existia
				log.info("Error negocio",e);
			}catch(final ConecSrvPqDinamicoException e) {
				//continuar con el proceso que existia
				log.error("Error conexion al servicio",e);
			}
		}
		// INC-2344 Pension Alimenticia.
		this.desplegarFAT = true;
		if (datos.getTiposProductosPq() == TiposProductosPq.PEN) {
			this.cargarDatosBeneficiarioCredito();
		}
		this.obtenerTextoFAT();
	}

	/**
	 * Carga los detalles del beneficiario.
	 */
	private boolean cargarDatosBeneficiarioCredito() {
		// INC-2344 Pension Alimenticia.
		if (this.validarDatosBeneficiarioCredito()) {
			try {
				// Institucion Financiera
				datos.getCuentaBancariaBeneficiarioCredito().setInstitucionFinanciera(
						this.institucionFinancieraServicio.getInstitucionFinanciera(datos
								.getCuentaBancariaBeneficiarioCredito().getInstitucionFinanciera().getRuc()));
				// Tipo Cuenta
				datos.getCuentaBancariaBeneficiarioCredito()
						.getTipoCuenta()
						.setDescripcion(
								tipoCuentaBiessServicio.obtenerPorCodigo(
										this.datos.getCuentaBancariaBeneficiarioCredito().getTipoCuenta().getCodigo())
										.getDescripcion());

				// Obtener informacion de la ciudad.
				datos.getBeneficiarioCredito().setCiudad(
						divisionPoliticaServicio.consultarDivisionPolitica(datos.getBeneficiarioCredito()
								.getCiudadJuicio()));

				// Obtener informacion de la institucion financiera.
				this.datos.getCuentaBancariaBeneficiarioCredito().setInstitucionFinanciera(
						institucionFinancieraServicio.getInstitucionFinanciera(this.datos
								.getCuentaBancariaBeneficiarioCredito().getInstitucionFinanciera().getRuc()));
				// Obtener Tipo Cuenta
				datos.getCuentaBancariaBeneficiarioCredito()
						.getTipoCuenta()
						.setDescripcion(
								tipoCuentaBiessServicio.obtenerPorCodigo(
										this.datos.getCuentaBancariaBeneficiarioCredito().getTipoCuenta().getCodigo())
										.getDescripcion());
				return true;
			} catch (final Exception e) {
				log.error("Problemas al cargar informacion del Beneficiario con Patria Potestad.", e);
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * Valida los datos del beneficiario del credito.
	 *
	 * @return true todo bien, caso contrario false.
	 */
	private boolean validarDatosBeneficiarioCredito() {
		// INC-2344 Pension Alimenticia.
		// Cedula Beneficiario
		if (datos.getBeneficiarioCredito().getNumeroBeneficiario() == null
				|| datos.getBeneficiarioCredito().getNumeroBeneficiario().trim().length() <= 0) {
			return false;
		}
		// Nombre Beneficiario
		if (datos.getBeneficiarioCredito().getBeneficiario() == null
				|| datos.getBeneficiarioCredito().getBeneficiario().trim().length() <= 0) {
			return false;
		}
		// Nombre Hijos
		if (datos.getBeneficiarioCredito().getNombreMenor() == null
				|| datos.getBeneficiarioCredito().getNombreMenor().trim().length() <= 0) {
			return false;
		}
		// Institucion Financiera
		if (datos.getCuentaBancariaBeneficiarioCredito().getInstitucionFinanciera().getRuc() == null
				|| datos.getCuentaBancariaBeneficiarioCredito().getInstitucionFinanciera().getRuc().trim().length() <= 0) {
			return false;
		}

		// Numero Cuenta
		if (datos.getCuentaBancariaBeneficiarioCredito().getNumeroCuenta() == null
				|| datos.getCuentaBancariaBeneficiarioCredito().getNumeroCuenta().trim().length() <= 0) {
			return false;
		}

		// Tipo Cuenta
		if (datos.getCuentaBancariaBeneficiarioCredito().getTipoCuenta().getCodigo() == null
				|| datos.getCuentaBancariaBeneficiarioCredito().getTipoCuenta().getCodigo().trim().length() <= 0) {
			return false;
		}

		// No. de Causa
		if (datos.getBeneficiarioCredito().getNumeroCausa() == null
				|| datos.getBeneficiarioCredito().getNumeroCausa().trim().length() <= 0) {
			return false;
		}

		// Juzgado No
		if (datos.getBeneficiarioCredito().getNumeroJuzgado() == null
				|| datos.getBeneficiarioCredito().getNumeroJuzgado().trim().length() <= 0) {
			return false;
		}

		// Provincia
		if (datos.getBeneficiarioCredito().getProvinciaJuicio() == null
				|| datos.getBeneficiarioCredito().getProvinciaJuicio().trim().length() <= 0) {
			return false;
		}

		// Ciudad
		if (datos.getBeneficiarioCredito().getCiudadJuicio() == null
				|| datos.getBeneficiarioCredito().getCiudadJuicio().trim().length() <= 0) {
			return false;
		}

		return true;
	}

	/**
	 * Obtiene el texto para el formulario de autorizacion de transferencias.
	 *
	 * @return texto formulario.
	 */
	public String obtenerTextoFAT() {
		// INC-2344 Pension Alimenticia.
		try {
			/* Obtener template */
			String templatePath = "";
			/* Parametros del mensaje */
			final Map<String, Object> parametros = new HashMap<String, Object>();
			// Comunes
			final SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
			parametros.put("param_fecha", sdf.format(new Date()));
			parametros.put("param_solicitante", this.datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			parametros.put("param_solicitante_cedula", this.datos.getSolicitante().getDatosPersonales().getCedula());
			parametros.put("param_acercarse_islas", "");

			// Pensiones Alimenticias
			if (datos.getTiposProductosPq() == TiposProductosPq.PEN) {
				templatePath = "ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferencia.ftl";
				parametros.put("param_ciudad", this.datos.getBeneficiarioCredito().getCiudad().getNomdivpol());

				parametros.put("param_tipo_cuenta", this.datos.getCuentaBancariaBeneficiarioCredito().getTipoCuenta()
						.getDescripcion());
				parametros.put("param_numero_cuenta", this.datos.getCuentaBancariaBeneficiarioCredito()
						.getNumeroCuenta());
				parametros.put("param_banco", this.datos.getCuentaBancariaBeneficiarioCredito()
						.getInstitucionFinanciera().getDescripcion());
				parametros.put("param_beneficiario", this.datos.getBeneficiarioCredito().getBeneficiario());
				parametros.put("param_nombre_menores", this.datos.getBeneficiarioCredito().getNombreMenor());
				parametros
						.put("param_beneficiario_cedula", this.datos.getBeneficiarioCredito().getNumeroBeneficiario());
				parametros.put("param_numero_juzgado", this.datos.getBeneficiarioCredito().getNumeroJuzgado());

				parametros.put("param_monto", this.getPrestamoCalculo().getLiquidoPagar().setScale(2, RoundingMode.HALF_UP));

				// INC-2286 Ferrocarriles
			} else if (datos.getTiposProductosPq() == TiposProductosPq.TREN || datos.getTiposProductosPq() == TiposProductosPq.TUR) {
				templatePath = "ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferenciaFEEP.ftl";
				parametros.put("param_operadora", getOperadora());
				parametros.put("param_proveedor_nombre", datos.getProveedor().getPrNombre());
				parametros.put("param_monto", this.getPrestamoCalculo().getLiquidoPagar().setScale(2, RoundingMode.HALF_UP));
				parametros.put("param_tipo_cuenta", this.datos.getInstitucionFinancieraProveedor().getTipoCuenta());
				parametros.put("param_numero_cuenta", this.datos.getInstitucionFinancieraProveedor().getNumeroCuenta());
				parametros.put("param_banco", this.datos.getInstitucionFinancieraProveedor().getInstitucion());
			} else if (datos.getTiposProductosPq() == TiposProductosPq.FOC) {
				templatePath = "ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferenciaFocalizados.ftl";
				final Long codprecla = this.datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodprecla();
				final Long codpretip = this.datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodpretip();
				final Long numpreafi = this.datosCredito.getVariablePrestamo().getSecvarpre();
				final Long ordpreafi = this.datosCredito.getOrdenPrestamo();

				final CreditoValidacion creditoValidacion = this.creditoValidacionServicio.consultarPorPrestamo(codprecla, codpretip, numpreafi, ordpreafi);

				final StringBuilder codigoCredito = new StringBuilder();
				codigoCredito.append(
						Utilities.agregarCerosInicio(this.datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodprecla().toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(this.datosCredito.getOrdenPrestamo().toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(this.datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodpretip().toString(), 2));
				codigoCredito.append(this.datosCredito.getVariablePrestamo().getSecvarpre().toString());

				parametros.put("param_codigo_credito", codigoCredito.toString());
				parametros.put("param_num_referencia", this.datosCredito.getCodigoActivacion());
				parametros.put("param_fecha_caducidad", sdf.format(creditoValidacion.getFechaCaducidad()));
				parametros.put("param_monto", this.getPrestamoCalculo().getLiquidoPagar().setScale(2, RoundingMode.HALF_UP));
				parametros.put("param_numero_cuenta", this.datos.getInstitucionFinancieraProveedor().getNumeroCuenta());
				parametros.put("param_banco", this.datos.getInstitucionFinancieraProveedor().getInstitucion());
				parametros.put("param_proveedor_nombre", this.datos.getProveedor().getPrNombre() + " - " + this.datos.getProveedor().getPrRuc());
				parametros.put("param_adquision_producto",
						this.contadorProductosFocalizados == 1 ? "la adquisici&#243;n de la cocina de inducci&#243;n"
								: "la adquisici&#243;n de cocina de inducci&#243;n y juego de ollas");
				parametros.put("nombre_beneficiario", this.datos.getProveedor().getPrNombre() + " - " + this.datos.getProveedor().getPrRuc());
				parametros.put("param_dias_caducidad", this.numeroDiasCaducidad);
				final StringBuilder marcaModeloCocina = new StringBuilder();
				marcaModeloCocina.append("Marca cocina ");
				marcaModeloCocina.append(this.pqFocalizados.getCocinaInformacion().getMarcaNombre());
				marcaModeloCocina.append(", Modelo ");
				marcaModeloCocina.append(this.pqFocalizados.getCocinaInformacion().getNombre());

				parametros.put("modelo_marca_cocina", marcaModeloCocina.toString());
				parametros.put("modelo_marca_ollas", "");
				if (this.contadorProductosFocalizados > 1) {
					final StringBuilder marcaModeloOllas = new StringBuilder();
					marcaModeloOllas.append(" y Marca Juego de Ollas ");
					marcaModeloOllas.append(this.ollasFocalizados.getOllaInformacion().getMarcaNombre());
					marcaModeloOllas.append(", Modelo ");
					marcaModeloOllas.append(this.ollasFocalizados.getOllaInformacion().getNombre());
					parametros.put("modelo_marca_ollas", marcaModeloOllas.toString());
				}
			} else if (this.datos.getTiposProductosPq() == TiposProductosPq.ECU_TUR) {
				templatePath = "ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferenciaEcuadorTurismo.ftl";
				final StringBuilder codigoCredito = new StringBuilder();
				codigoCredito.append(
						Utilities.agregarCerosInicio(this.datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodprecla().toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(this.datosCredito.getOrdenPrestamo().toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(this.datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodpretip().toString(), 2));
				codigoCredito.append(this.datosCredito.getVariablePrestamo().getSecvarpre().toString());

				parametros.put("param_codigo_credito", codigoCredito.toString());
				parametros.put("param_num_reserva", this.datosCredito.getNumeroReserva());
				parametros.put("param_monto", this.getPrestamoCalculo().getLiquidoPagar().setScale(2, RoundingMode.HALF_UP));
				parametros.put("param_numero_cuenta", this.datos.getInstitucionFinancieraProveedor().getNumeroCuenta());
				parametros.put("param_banco", this.datos.getInstitucionFinancieraProveedor().getInstitucion());
			}else if (this.datos.getTiposProductosPq() == TiposProductosPq.DINAMICO) {
				templatePath="ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferenciaDinamicoCondiciones.ftl";
				final StringBuilder codigoCredito = new StringBuilder();
				codigoCredito.append(
						Utilities.agregarCerosInicio(this.datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodprecla().toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(this.datosCredito.getOrdenPrestamo().toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(this.datosCredito.getVariablePrestamo().getVariablePrestamoPK().getCodpretip().toString(), 2));
				codigoCredito.append(this.datosCredito.getVariablePrestamo().getSecvarpre().toString());
				parametros.put("param_codigo_credito", codigoCredito.toString());
				parametros.put("nom_producto", this.datos.getDataProductoDinamico().getTitulo());
				parametros.put("nom_contrato", this.datos.getDataProductoDinamico().getNombreBusqueda());
				parametros.put("nom_proveedor", this.datos.getProveedor().getPrNombre());
				parametros.put("param_num_reserva", this.datos.getCodigoContratoProv());
				parametros.put("param_monto",
						this.getPrestamoCalculo().getLiquidoPagar().setScale(2, RoundingMode.HALF_UP));

				parametros.putAll(llenarParametrosDinamico());


			}

			if (datos.getTiposProductosPq() == TiposProductosPq.TUR) {
				log.info("Entra en turismo");
				parametros.put("param_acercarse_islas", getResource(STR_MESSAGES, "creditur.acercarse.islas"));
			}

			final Configuration cfg = new Configuration();
			/* Cargar el template */
			cfg.setClassForTemplateLoading(AlertUtil.class, "/");
			final Template template = cfg.getTemplate(templatePath);

			if (null == parametros || null == template) {
				throw new Exception(
						"Error al imprimir el Formulario de Autorizaci\u00f3n de Transferencias. El template y/o el map de datos son nulos.");
			}

			try {
				final Writer out = new StringWriter();
				template.process(parametros, out);
				out.flush();
				this.textoFAT = out.toString();
			} catch (final TemplateException e) {
				// log.error("Error al obtener el template para formar el Formulario de Autorizaci\u00f3n de Transferencias.",
				// e);
				throw new Exception(e);
			}
		} catch (final Exception e) {
			log.error(e.getMessage());
		}

		return textoFAT;
	}

	private String getOperadora() {
		String operadora = "EMPRESA";
		final TiposProductosPq tiposProductosPq = TiposProductosPq.getTiposProductosPq(datos.getProveedor().getTipoPrestamoProducto().getCodigo());
		if (tiposProductosPq != null && tiposProductosPq.equals(TiposProductosPq.TUR)) {
			operadora = "OPERADORA TURISTICA";
		}

		return operadora;
	}

	/**
	 * @return the textoFAT
	 */
	public String getTextoFAT() {
		return textoFAT;
	}

	/**
	 * @param textoFAT
	 *            the textoFAT to set
	 */
	public void setTextoFAT(final String textoFAT) {
		this.textoFAT = textoFAT;
	}

	/**
	 * @return the desplegarFAT
	 */
	public boolean isDesplegarFAT() {
		return desplegarFAT;
	}

	/**
	 * @param desplegarFAT
	 *            the desplegarFAT to set
	 */
	public void setDesplegarFAT(final boolean desplegarFAT) {
		this.desplegarFAT = desplegarFAT;
	}

	public BigDecimal getTotalCapitalAbonado() {
		return totalCapitalAbonado;
	}

	public void setTotalCapitalAbonado(final BigDecimal totalCapitalAbonado) {
		this.totalCapitalAbonado = totalCapitalAbonado;
	}

	public List<SelectItem> getTipoTablaItems() {
		tipoTablaItems = SimulacionCreditoUtil.obtenerTipoTablaItems(datos.getTipo());
		return tipoTablaItems;
	}


	/**
	 * Metodo que devuelve el valor tope maximo de credito
	 * dependiendo de las garantias del afiliado.
	 * El valor tope puede ser solo hasta = (SBU*80)
	 *
	 * @return valorTopeCredito
	 */
	private BigDecimal valorTopeMaximoCredito() {

		final BigDecimal valorTopeCredito= calculoCredito.getGarantia().getTotalGarantia();		

		return valorTopeCredito;
	}

	public void calcularSimCuotaNew() {
		log.debug("calculando sim cuota new");
		message = "";
		msgNovNegativa=null;
		if (tieneMaximoCategoria()) {
			simulacion.setObservacion(String.format(MSG_PLAZ_MAX_CAT, datos.getDataRespGenericaResponseDto().getProveedor().getCategoriaProveedor().getPlazoMaximo()));
			return;
		}

		if (simulacion ==null) {
			simulacion = new Simulacion();
		}

		if (this.opcionSimCuota.getMeses() == null) {
			this.simulacion.setObservacion("Debe ingresar un n\u00FAmero de plazo");
		} else {

			try {
				final EvaluaReglaMontoMinimoDto montoMinimoRegla = this.prestamoServicio.validarMontoMinimoPrestamo(
						this.opcionSimCuota.getValorTotalCredito(), new BigDecimal(this.opcionSimCuota.getMeses()), this.datos.getTipo(),
						this.edadAsegurado);
				if (!montoMinimoRegla.isCumpleMontoMinimo()) {
					this.simulacion.setObservacion("El monto m\u00EDnimo del cr\u00E9dito debe ser al menos el " + montoMinimoRegla.getValorMinimoSBU()
							+ " del SBU: $" + montoMinimoRegla.getValorSBUMitad());
					this.simulacion.setCalculoCredito(false);
					this.activarCalculoCredito = false;
					return;
				}
			} catch (final MontoMinimoException e) {
				this.simulacion.setObservacion(e.getMessage());
				this.simulacion.setCalculoCredito(false);
				this.activarCalculoCredito = false;
				return;
			}


			//valida las condiciones del monto para el pq dinamico
			if (TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq()) {
				if (this.opcionSimCuota.getValorTotalCredito() != null && this.opcionSimCuota.getValorTotalCredito()
						.compareTo(this.datos.getDataRespGenericaResponseDto().getTotalFinanciar()) > 0) {
					this.simulacion.setObservacion(getResource(STR_MESSAGES, "pq.dinamico.monto.mayor.contratado"));
					this.simulacion.setCalculoCredito(false);
					this.activarCalculoCredito = false;
					return;
				}

			}



			final String tipoTabla = opcionSimCuota.getTipoTablaSeleccionado();

			this.recalcularCapacidadEndeudamiento(opcionSimCuota.getMeses());

			this.opcionSimCuota.setCapacidadEndeudamiento(this.calculoCredito.getGarantia().getCapacidadEndeudamiento());
			this.opcionSimCuota.setTasaInteres(this.calculoCredito.getCondicionCalculo().getTasaInteres());
			this.opcionSimCuota.setTotalGarantias(valorTopeMaximoCredito());
			this.opcionSimCuota.setPlazoMaximoCredito(this.calculoCredito.getCondicionCalculo().getPlazoMaximo());
			this.opcionSimCuota.setEsEmergente(this.datos.isProductoBiessEmergente());
			this.opcionSimCuota.setTipoPrestamista(this.datos.getTipo());
			this.opcionSimCuota.setEdadAsegurado(this.edadAsegurado);
			this.opcionSimCuota.setTipoProductoPq(this.datos.getTiposProductosPq());

			try {

				if (tipoTabla != null && !tipoTabla.isEmpty()) {
	
					opcionSimCuota.setMontoMaxSac(montoMaxSimulacion);
					opcionSimCuota.setCodProdEspecial(this.datos.getCodigoProdEspecial());
					this.simulacion = this.simularCreditoServicio.simularCreditoSegunMontoIngresadoSac(this.opcionSimCuota, calculoCreditoServicio, FuncionesUtilesSac.fabricarCliente(datosCredito),datosCredito.getPrestamoAnteriorNovacion()!=null?datosCredito.getPrestamoAnteriorNovacion().getNumOperacionSAC():null);
				
					
					activarCalculoCredito = simulacion.getCalculoCredito();

				} else {
					this.simulacion.setObservacion("Debe Seleccionar un tipo de Amortizaci\u00F3n");
					this.simulacion.setCalculoCredito(false);
					activarCalculoCredito = false;
				}
				this.opcionSimCuota.setCuotaMensual(simulacion.getCuotaCredito());
				this.opcionSimMonto.setValorTotalCredito(new BigDecimal(0));
				this.opcionSimMontoCargado = false;
				this.opcionSimCuotaCargado = true;

			} catch (final MontosMaximosException e) {
				this.simulacion = new Simulacion();
				this.simulacion.setCalculoCredito(false);
				activarCalculoCredito = false;
				this.simulacion.setObservacion("Inconsistencia." + e.getMessage());
				log.error(e.getMessage());
				return;
			} catch (final ParseException e) {
				this.simulacion = new Simulacion();
				this.simulacion.setCalculoCredito(false);
				activarCalculoCredito = false;
				this.simulacion.setObservacion("Inconsistencia." + e.getMessage());
				log.error(e.getMessage());
				return;
			}

			if (this.datos.isProductoBiessEmergente()) {
				this.simulacion.setPlazoMaximoCredito(obtenerPlazoMaximo());
			} else {
				this.simulacion.setPlazoMaximoCredito(this.calculoCredito.getCondicionCalculo().getPlazoMaximo());
			}
		}

	}

	/**
	 * Obtiene el valor de endeudamiento y la capacidad de pago
	 *
	 * @param plazo
	 */
	private void recalcularCapacidadEndeudamiento(final Integer plazo) {
		try {
			this.capacidadPago =  this.garantiaCreditoServicio.obtenerPorcentajeComprometimientoPQ(datos.isProductoBiessEmergente(), datos.getTipo(),
					this.edadAsegurado, new BigDecimal(plazo));
		} catch (final ParametroBiessException e) {
			log.error("Error al obtener el porcentaje de capacidad de pago", e);
			message = e.getMessage();
			return;
		} catch (final ParametrizacionPQException e) {
			log.error("Error al obtener el porcentaje de capacidad de pago", e);
			message = e.getMessage();
			return;
		}
		final BigDecimal sueldoPromedio = this.calculoCredito.getGarantia().getSueldoPromedioOriginal();
		this.calculoCredito.getGarantia()
				.setCapacidadPago(sueldoPromedio.multiply(this.capacidadPago).divide(new BigDecimal(100), RoundingMode.HALF_UP));
		BigDecimal egresos = BigDecimal.ZERO;
		if (this.calculoCredito.getGarantia().getDetalleCalculoEgresos() != null
				&& !this.calculoCredito.getGarantia().getDetalleCalculoEgresos().isEmpty()) {
			for (final DetalleCalculoEgresos detalle : this.calculoCredito.getGarantia().getDetalleCalculoEgresos()) {
				if (detalle != null) {
					egresos = egresos.add(detalle.getValor() == null ? BigDecimal.ZERO : detalle.getValor());
				}
			}
		}
		// Se suman los ingresos porque viene con valor negativo
		this.calculoCredito.getGarantia().setCapacidadEndeudamiento(this.calculoCredito.getGarantia().getCapacidadPago().add(egresos));
	}

	public void calcularSimMontoNew() {
		log.debug("calculando sim monto New");
		message = "";
		msgNovNegativa=null;
		if (simulacion == null) {
			simulacion = new Simulacion();
		}

		if (opcionSimMonto.getMeses() == null || opcionSimMonto.getMeses() == 0) {
			simulacion.setObservacion("Debe ingresar un n\u00FAmero de plazo");
		} else {
			final String tipoTabla = opcionSimMonto.getTipoTablaSeleccionado();
			


			this.recalcularCapacidadEndeudamiento(opcionSimMonto.getMeses());

			opcionSimMonto.setCapacidadEndeudamiento(calculoCredito.getGarantia().getCapacidadEndeudamiento());
			opcionSimMonto.setTasaInteres(calculoCredito.getCondicionCalculo().getTasaInteres());
			opcionSimMonto.setTotalGarantias(valorTopeMaximoCredito());
			opcionSimMonto.setPlazoMaximoCredito(this.calculoCredito.getCondicionCalculo().getPlazoMaximo());
			opcionSimMonto.setEsEmergente(datos.isProductoBiessEmergente());
			opcionSimMonto.setTipoPrestamista(datos.getTipo());
			opcionSimMonto.setEdadAsegurado(this.edadAsegurado);

			try {
				if (tipoTabla != null && !tipoTabla.isEmpty()) {
					simulacion = simularCreditoServicio.simularCreditoSegunCuotaIngresada(opcionSimMonto);
					simulacion.setCuotaCredito(opcionSimMonto.getCuotaMensual());
					activarCalculoCredito = simulacion.getCalculoCredito();
					calcularCuotaMaximaComprometer();
				} else {
					simulacion.setObservacion("Debe Seleccionar un tipo de Amortizacion");
					simulacion.setCalculoCredito(false);
					activarCalculoCredito = false;
				}
				opcionSimMonto.setValorTotalCredito(simulacion.getMontoCredito());

				final EvaluaReglaMontoMinimoDto montoMinimoRegla = this.prestamoServicio.validarMontoMinimoPrestamo(
						opcionSimMonto.getValorTotalCredito(), new BigDecimal(this.opcionSimMonto.getMeses()), this.datos.getTipo(),
						this.edadAsegurado);
				if (!montoMinimoRegla.isCumpleMontoMinimo()) {
					simulacion.setObservacion("El monto m\u00EDnimo del cr\u00E9dito debe ser al menos el " + montoMinimoRegla.getValorMinimoSBU()
							+ " del SBU: $" + montoMinimoRegla.getValorSBUMitad() + ". Ingrese otro valor de cuota.");
					simulacion.setCalculoCredito(false);
					activarCalculoCredito = false;
					return;
				}

				opcionSimCuota.setCuotaMensual(new BigDecimal(0));
				opcionSimMontoCargado = true;
				opcionSimCuotaCargado = false;

			} catch (final MontoMinimoException e) {
				simulacion = new Simulacion();
				simulacion.setCalculoCredito(false);
				activarCalculoCredito = false;
				simulacion.setObservacion("Inconsistencia." + e.getMessage());
				log.error(e.getMessage());
				return;
			} catch (final MontosMaximosException e) {
				simulacion = new Simulacion();
				simulacion.setCalculoCredito(false);
				activarCalculoCredito = false;
				simulacion.setObservacion("Inconsistencia." + e.getMessage());
				log.error(e.getMessage());
				return;
			}
		}
	}

	/**
	 * Método para calcular la cuota máxima a comprometer
	 */
	private void calcularCuotaMaximaComprometer() {
		BigDecimal tasaInteres = null;
		try {
			tasaInteres = obtenerTasaInteres(new BigDecimal(opcionSimMonto.getMeses()), this.datos.getTipo(), this.edadAsegurado);
		} catch (final CondicionCalculoException e1) {
			log.error("Error al obtener la tasa de interes en monto maximo: ", e1);
			message = e1.getMessage();
			simulacion.setObservacion("Error al obtener la tasa de inter\u00E9s en monto m\u00E1ximo: "
					+ message.replace("ec.fin.biess.exception.ParametrizacionPQException:", ""));
			return;
		}

		final int numeroMeses = opcionSimMonto.getMeses();
		final String tipo = opcionSimMonto.getTipoTablaSeleccionado();
		if (tipo != null && !tipo.isEmpty()) {
			BigDecimal montoMaximoCredito=valorTopeMaximoCredito();
			if(datos.isProductoBiessEmergente() && UtilAjusteCalculo.ajusteCalculo(montoMaximoCredito).compareTo(montoMaximoEmergente)>=0) {
				montoMaximoCredito=montoMaximoEmergente;
			}

			simulacion.setCuotaMaximaComprometer(
					simularCreditoServicio.obtenerCuotaMaximaPorTipoTabla(montoMaximoCredito, tasaInteres, numeroMeses, tipo));
			simulacion.setPlazoMaximoCredito(this.calculoCredito.getCondicionCalculo().getPlazoMaximo());
		}
	}

	public void limpiarCampos(){

		this.opcionSimCuota = new OpcionCredito();
		this.opcionSimMonto = new OpcionCredito();
		this.simulacion = null;
		deshabilitarAmortizacion = true;
		deshabilitarSimulacion = false;
		panelActivoPrincipal = "sim";
	}

	/**
	 * Establece la lista de tipo de tabla de amortizacion a la opcion Seleccione
	 *
	 * @return
	 */
	public String reseteaListaTablaAmortizacion() {
		opcionSimCuota.setMeses(0);
		opcionSimCuota.setTipoTablaSeleccionado(null);
		opcionSimCuota.setValorTotalCredito(null);
		activarCalculoCredito = false;
		opcionSimCuota.setCuotaMensual(BigDecimal.ZERO);
		if (simulacion != null) {
			simulacion.setMontoMaximoCredito(BigDecimal.ZERO);
		}
		return "";
	}

	/**
	 * Establece la lista de tipo de tabla de amortizacion a la opcion Seleccione
	 *
	 * @return
	 */
	public String reseteaListaTablaAmortizacionMonto() {
		opcionSimMonto.setMeses(0);
		opcionSimMonto.setTipoTablaSeleccionado(null);
		if (simulacion != null) {
			simulacion.setCuotaMaximaComprometer(BigDecimal.ZERO);
		}
		return "";
	}

	/**
	 * Obtiene el plazo maximo para prestamos emergentes y normales
	 *
	 * @return
	 */
	private int obtenerPlazoMaximo() {
		int plazoMaximo =  this.calculoCredito.getCondicionCalculo().getPlazoMaximo();
		if (datos.isProductoBiessEmergente()) {
			plazoMaximo = plazoMaximoEmergente.intValue();
		}
		if (TiposProductosPq.DINAMICO.equals(datos.getTiposProductosPq())) {
			plazoMaximo = datos.getDataRespGenericaResponseDto().getProveedor().getCategoriaProveedor() == null
					? plazoMaximo
					: datos.getDataRespGenericaResponseDto().getProveedor().getCategoriaProveedor().getPlazoMaximo();
		}
		return plazoMaximo;
	}

	private boolean tieneMaximoCategoria() {
		return TiposProductosPq.DINAMICO == this.datos.getTiposProductosPq() &&
				datos.getDataRespGenericaResponseDto().getProveedor().getCategoriaProveedor()!=null
				&& opcionSimCuota.getMeses()>datos.getDataRespGenericaResponseDto().getProveedor().getCategoriaProveedor().getPlazoMaximo();
	}

	public void obtenerMontoMaximo() {
		simulacion = new Simulacion();
		simulacion.setCalculoCredito(false);
		activarCalculoCredito = false;
		opcionSimCuota.setCuotaMensual(BigDecimal.ZERO);
		if (opcionSimCuota.getMeses() == null || opcionSimCuota.getMeses()==0 ) {
			simulacion.setObservacion("Debe ingresar un n\u00FAmero de plazo");
		} else if (opcionSimCuota.getMeses() > obtenerPlazoMaximo()) {
			final String observacionMesesMaximo = "El plazo ingresado es mayor al permitido: %d meses";
			simulacion.setObservacion(String.format(observacionMesesMaximo, obtenerPlazoMaximo()));
		} else if(tieneMaximoCategoria()){

			simulacion.setObservacion(String.format(MSG_PLAZ_MAX_CAT, datos.getDataRespGenericaResponseDto().getProveedor().getCategoriaProveedor().getPlazoMaximo()));
		} else {

			this.recalcularCapacidadEndeudamiento(opcionSimCuota.getMeses());
			//Se elimina la consulta de la tasa interes MIGRACION SAC
			final String tipo = opcionSimCuota.getTipoTablaSeleccionado();
			try {
				if (tipo != null && !tipo.isEmpty()) {
					montoMaxSimulacion = obtenerMontoMaximoSimulacion(opcionSimCuota.getMeses(),
							TipoTablaEnum.valueOf(tipo));
					if (UtilAjusteCalculo.ajusteCalculo(montoMaxSimulacion).compareTo(montoMaximoEmergente) >= 0
							&& datos.isProductoBiessEmergente()) {
						montoMaxSimulacion=montoMaximoEmergente;
						simulacion.setMontoMaximoCredito(montoMaximoEmergente);
					}else {
						simulacion.setMontoMaximoCredito(montoMaxSimulacion);
					}
					simulacion.setPlazoMaximoCredito(obtenerPlazoMaximo());
				}
			} catch (final MontosMaximosException e) {
				removeMessages();
				addGlobalErrorMessage("Error al obtener monto m\u00E1ximo: " + e.getMessage(), "");
				log.error(e.getMessage());
				
				reseteaListaTablaAmortizacion();
			}
            catch (final ParseException e) {
            	removeMessages();
            	reseteaListaTablaAmortizacion();
            	addGlobalErrorMessage("Error al tranformar fecha","");
				log.error(e.getMessage());
			} catch (final TablaAmortizacionSacException e) {
				removeMessages();
				reseteaListaTablaAmortizacion();
				addGlobalErrorMessage("Error: " + e.getMessage(), "");
				log.error(e.getMessage());
			} catch (final MontoMinimoException e) {
				removeMessages();
				reseteaListaTablaAmortizacion();
				addGlobalErrorMessage("Error al obtener monto m\u00EDmimo: " + e.getMessage(), "");
				log.error(e.getMessage());
			}

			// En caso que sea prestamo focalizado el monto del credito se obtiene del precio de la cocina mas olla
			obtenerValorMontoFocalizados();
		}
	}

	private BigDecimal obtenerMontoMaximoSimulacion(final Integer mesesCalculo,final TipoTablaEnum tipo) throws MontosMaximosException, MontoMinimoException, ParseException, TablaAmortizacionSacException {
		final DataPersonalizacionDto dataPerDto=new DataPersonalizacionDto();
		this.datosCredito.setNombreAsegurado(this.datos.getSolicitante().getDatosPersonales().getApellidosNombres());
		final TablaAmortizacionSac tblAmorti = obtenerAmortizacionFrancesa(datosCredito,mesesCalculo);
		dataPerDto.setSimulacionSac(tblAmorti);
		dataPerDto.setGarantiaTotal(valorTopeMaximoCredito());
		dataPerDto.setEdad(edadAsegurado);
		dataPerDto.setPlazoMaximo(mesesCalculo);
		dataPerDto.setTipoPrestamista(datos.getTipo());
		dataPerDto.setTotalSaldosCred(this.datos.getPrecalificacion().getSumaSaldosCred());
		dataPerDto.setCupoMaxCredito(this.datos.getPrecalificacion().getGarantia().getCupoMaximoCredito());
		dataPerDto.setEmergente(datos.isProductoBiessEmergente());
		dataPerDto.setTipo(tipo.name());
		return personalizarCreditoService.obtenerMontoMaximoPorTipoAmortizacionFran(dataPerDto);
		
	}
	
	
	/**
	 * Valida que al ingresar el monto se haya seleccionado una tabla de amortizacion (alemana o francesa)
	 *
	 * @return
	 */
	public String validarSeleccionTablaAmortizacion() {
		if (opcionSimCuota.getTipoTablaSeleccionado() == null) {
			simulacion.setObservacion("Debe Seleccionar un tipo de Amortizaci\u00F3n");
			simulacion.setCalculoCredito(false);
			activarCalculoCredito = false;
		}
		return "";
	}

	/**
	 * Nomuestra el boton de ver la tabla de amortización
	 *
	 * @return
	 */
	public String ocultarBotonTablaAmortizacion() {
		if (simulacion != null) {
			activarCalculoCredito = false;
			simulacion.setCalculoCredito(false);
			simulacion.setObservacion(null);
		}
		return "";
	}

	/**
	 * oculta el boton de tabla de amortizacion para cuota
	 *
	 * @return
	 */
	public void ocultarBotonTablaAmortizacionC() {
		activarCalculoCredito = false;
		simulacion = null;
		panelActivoSimulacion = "uiSimC";
	}

	/**
	 * oculta el boton de tabla de amortizacion para monto
	 *
	 * @return
	 */
	public void ocultarBotonTablaAmortizacionM() {
		activarCalculoCredito = false;
		simulacion = null;
		panelActivoSimulacion = "uiSimM";
	}

	//Eliminar funcionalidad obtener cuota maxima

	/**
	 * Obtiene informacion de la reserva
	 */
	public void verificarNumeroReserva() {
		log.info("Aqui verifica el numero de reserva");
		PaqueteTurismoInfoDto paqueteTuristico = new PaqueteTurismoInfoDto();
		try {
			paqueteTuristico = this.prestamoTuristicoServicio
					.consultarInformacionPaqueteTuristico(this.datos.getSolicitante().getDatosPersonales().getCedula(), this.codigoReservaEcuTur);
			if (paqueteTuristico != null) {
				this.setDescripcionPaqueteTuristico(paqueteTuristico.getDescripcionPaquete());
				this.setMontoPaqueteTuristico(paqueteTuristico.getMontoPaquete());
				this.obtenerValorMontoPqTurismo(paqueteTuristico.getMontoPaquete());
			} else {
				this.setDescripcionPaqueteTuristico(null);
				this.setMontoPaqueteTuristico(null);
			}

			this.intentosReserva = 0;
		} catch (final CodigoReservaPaqueteTuristicoException e) {
			log.error("1. Se presento un error al consumir el servicio de prestamos turisticos", e);
			this.setDescripcionPaqueteTuristico(null);
			this.setMontoPaqueteTuristico(null);
			this.obtenerValorMontoPqTurismo(BigDecimal.ZERO);
			this.intentosReserva++;
			final String mensajeValida = String.format(super.getResource("messages", "ecuador.turismo.codigo.reserva.intentos"), this.intentosReserva, this.intentosMaximoReserva);
			addGlobalErrorMessage(mensajeValida, "");
		} catch (final PrestamosTuristicosException e) {
			log.error("2. Se presento un error al consumir el servicio de prestamos turisticos", e);
			this.setDescripcionPaqueteTuristico(null);
			this.setMontoPaqueteTuristico(null);
			this.obtenerValorMontoPqTurismo(BigDecimal.ZERO);
			addGlobalErrorMessage(Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", ""), "");
		}

		// Inicio guarda auditoria
		final ParametroEvento parametroEvento = AuditoriaPqWebHelper
				.obtenerParametrosReservaEcuadorTurismo(this.datos.getSolicitante().getDatosPersonales().getCedula(), this.codigoReservaEcuTur,
						this.intentosReserva, this.descripcionPaqueteTuristico, this.montoPaqueteTuristico);
		super.guardarAuditoria(OperacionEnum.VERIFICACION_CODIGO_RESERVA_ECUADOR_TURISMO, parametroEvento,
				this.datos.getSolicitante().getDatosPersonales().getCedula());
		// Fin guarda auditoria

		if (this.intentosReserva == this.intentosMaximoReserva) {
			final ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
			final HttpSession session = (HttpSession) ectx.getSession(false);
			session.invalidate();
			final FacesContext ctx = FacesContext.getCurrentInstance();
			final Application app = ctx.getApplication();
			app.getNavigationHandler().handleNavigation(ctx, "/pages/concesion/roles.jsf", "roles");
		}

	}

	/**
	 * Obtiene el texto de las condiciones para prestamos de tipo Ecuador tu lugar en el mundo
	 */
	public void obtenerTextoFATCondicionesEcuTur() {
		final String templatePath = "ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferenciaEcuadorTurismoCondiciones.ftl";

		final Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("param_num_reserva", this.codigoReservaEcuTur);
		this.condicionesFATEcuTur=generarFormularioAceptacion(parametros, templatePath);
	}

	private String generarFormularioAceptacion(final Map<String, Object> parametros,final String templatePath) {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
		parametros.put("param_fecha", sdf.format(new Date()));
		parametros.put("param_solicitante", this.datos.getSolicitante().getDatosPersonales().getApellidosNombres());
		parametros.put("param_solicitante_cedula", this.datos.getSolicitante().getDatosPersonales().getCedula());
		parametros.put("param_monto", this.getPrestamoCalculo().getLiquidoPagar().setScale(2, RoundingMode.HALF_UP));
		parametros.put("param_numero_cuenta", this.datos.getInstitucionFinancieraProveedor().getNumeroCuenta());
		parametros.put("param_banco", this.datos.getInstitucionFinancieraProveedor().getInstitucion());
		try {
			final Configuration cfg = new Configuration();
			/* Cargar el template */
			cfg.setClassForTemplateLoading(AlertUtil.class, "/");
			final Template template = cfg.getTemplate(templatePath);

			if (null == parametros || null == template) {
				log.error("Error al imprimir el Formulario de Autorizaci\u00f3n de Transferencias. El template y/o el map de datos son nulos.");
				return null;
			}

			final Writer out = new StringWriter();
			template.process(parametros, out);
			out.flush();
			return out.toString();
		} catch (final IOException e) {
			log.error("Error al obtener texto de autorizacion de transferencias de fondos para prestamos turisticos", e);
		} catch (final TemplateException e) {
			log.error("Error al obtener texto de autorizacion de transferencias de fondos para prestamos turisticos", e);
		}
		return null;

	}

	public void obtenerTextoFATDinamico() {
		this.condicionesFATEcuTur=generarFormularioAceptacion(llenarParametrosDinamico(), FORM_AUT_DINAMICO);
	}


	/**
	 * Metodo que llena los parametros de un pq dinamico
	 * @return retornar un Map con los datos para reemplazar en el formulario
	 */
	private Map<String, Object>  llenarParametrosDinamico() {
		final Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nom_producto", this.datos.getDataProductoDinamico().getTitulo());
		parametros.put("nom_contrato", this.datos.getDataProductoDinamico().getNombreBusqueda());
		parametros.put("nom_proveedor", this.datos.getProveedor().getPrNombre());
		parametros.put("param_num_reserva", this.datos.getCodigoContratoProv());

		parametros.put("detalle_proveedor_header", dataProveedor(this.datos.getDataRespGenericaResponseDto().getHeader()));
		parametros.put("detalle_proveedor_body", dataProveedor(this.datos.getDataRespGenericaResponseDto().getBody()));
		return parametros;
	}

	private String dataProveedor(final List<Campo> listaCampo) {
		final StringBuilder data=new StringBuilder();
		for (final Campo campo : listaCampo) {
			data.append("<tr class=\"proveedor\">");
			data.append("<td class=\"proveedor\">").append(campo.getItem()).append("</td>");
			data.append("<td class=\"proveedor\">").append(campo.getValor()).append("</td>");
			data.append("</tr>");
		}
		return data.toString();

	}

	private void cargarTextosParametrizados() {

		msjCreditoListaObservadosPep = obtenerTextoParametrizado(super.getResource(STR_MESSAGES, "credito.datos.lista.observados.pep.mensaje"),
				ParametrosGeneralesEnum.PQW_CORREO_CONCESION);
		msjImprimirFormPensiones = obtenerTextoParametrizado(super.getResource(STR_MESSAGES, "mensaje.imprimir.form.pensiones"),
				ParametrosGeneralesEnum.PQW_CORREO_CONCESION);
		msjPensionesAlimenticiasFinTit  = obtenerTextoParametrizado(super.getResource(STR_MESSAGES,"pensiones.alimenticias.mensaje.final.titulo"),
				ParametrosGeneralesEnum.PQW_CORREO_CONCESION);
		msjPqInformacion3Otp  = obtenerTextoParametrizado(super.getResource(STR_LABEL,"pq.informacion3.otp"),
				ParametrosGeneralesEnum.PQW_FORM_DATOS_IESS);
	}

	public String obtenerTextoParametrizado(String texto, ParametrosGeneralesEnum parametro) {
		return Utilities.reemplazarParametroEnTexto(texto, parametro, datos.getMapaParametrosGenerales());
	}

	public boolean isBloquearCreditoServicios() {
		return bloquearCreditoServicios;
	}

	public void setBloquearCreditoServicios(final boolean bloquearCreditoServicios) {
		this.bloquearCreditoServicios = bloquearCreditoServicios;
	}

	public String getCodigoNotificacion() {
		return codigoNotificacion;
	}

	public void setCodigoNotificacion(final String codigoNotificacion) {
		this.codigoNotificacion = codigoNotificacion;
	}

	public Precalificacion getPrecalificacion() {
		return precalificacion;
	}

	public void setPrecalificacion(final Precalificacion precalificacion) {
		this.precalificacion = precalificacion;
	}

	public BigDecimal getCapacidadPago() {
		return capacidadPago;
	}

	public void setCapacidadPago(final BigDecimal capacidadPago) {
		this.capacidadPago = capacidadPago;
	}

	public BigDecimal getSbu() {
		return sbu;
	}

	public void setSbu(final BigDecimal sbu) {
		this.sbu = sbu;
	}

	public BigDecimal getNumsbu() {
		return numsbu;
	}

	public void setNumsbu(final BigDecimal numsbu) {
		this.numsbu = numsbu;
	}

	public BigDecimal getPlazoMaximoEmergente() {
		return plazoMaximoEmergente;
	}

	public void setPlazoMaximoEmergente(final BigDecimal plazoMaximoEmergente) {
		this.plazoMaximoEmergente = plazoMaximoEmergente;
	}

	public int getMesesGraciaEmergente() {
		return mesesGraciaEmergente;
	}

	public void setMesesGraciaEmergente(final int mesesGraciaEmergente) {
		this.mesesGraciaEmergente = mesesGraciaEmergente;
	}

	public PqFocalizadosBacking getPqFocalizados() {
		return pqFocalizados;
	}

	public void setPqFocalizados(final PqFocalizadosBacking pqFocalizados) {
		this.pqFocalizados = pqFocalizados;
	}

	public OllasFocalizadosBacking getOllasFocalizados() {
		return ollasFocalizados;
	}

	public void setOllasFocalizados(final OllasFocalizadosBacking ollasFocalizados) {
		this.ollasFocalizados = ollasFocalizados;
	}

	public int getContadorIntentosOTP() {
		return contadorIntentosOTP;
	}

	public void setContadorIntentosOTP(final int contadorIntentosOTP) {
		this.contadorIntentosOTP = contadorIntentosOTP;
	}

	public int getMaximoIntentosOtpFocalizados() {
		return maximoIntentosOtpFocalizados;
	}

	public void setMaximoIntentosOtpFocalizados(final int maximoIntentosOtpFocalizados) {
		this.maximoIntentosOtpFocalizados = maximoIntentosOtpFocalizados;
	}

	public String getErrorIngresoOtp() {
		return errorIngresoOtp;
	}

	public void setErrorIngresoOtp(final String errorIngresoOtp) {
		this.errorIngresoOtp = errorIngresoOtp;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(final String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getMensajeValidacionOtp() {
		return mensajeValidacionOtp;
	}

	public void setMensajeValidacionOtp(final String mensajeValidacionOtp) {
		this.mensajeValidacionOtp = mensajeValidacionOtp;
	}

	public int getEdadAsegurado() {
		return edadAsegurado;
	}

	public void setEdadAsegurado(final int edadAsegurado) {
		this.edadAsegurado = edadAsegurado;
	}

	public String getColorTextoEndeudamiento() {
		String color = "color:Black;";
		if (this.calculoCredito.getGarantia().getCapacidadEndeudamiento().compareTo(BigDecimal.ZERO) <= 0) {
			color = "color:Red;";
		}

		return color;
	}

	public int getLongitudOTP() {
		return longitudOTP;
	}

	public void setLongitudOTP(final int longitudOTP) {
		this.longitudOTP = longitudOTP;
	}

	public boolean isPresentaValidacion() {
		return presentaValidacion;
	}

	public void setPresentaValidacion(final boolean presentaValidacion) {
		this.presentaValidacion = presentaValidacion;
	}

	public boolean getMensajeEmail() {
		return mensajeEmail;
	}

	public void setMensajeEmail(final boolean mensajeEmail) {
		this.mensajeEmail = mensajeEmail;
	}

	public String getCodigoOtpIngresado() {
		return codigoOtpIngresado;
	}

	public void setCodigoOtpIngresado(final String codigoOtpIngresado) {
		this.codigoOtpIngresado = codigoOtpIngresado;
	}

	public String getPanelActivoPrincipal() {
		return panelActivoPrincipal;
	}

	public void setPanelActivoPrincipal(final String panelActivoPrincipal) {
		this.panelActivoPrincipal = panelActivoPrincipal;
	}

	public boolean isDeshabilitarAmortizacion() {
		return deshabilitarAmortizacion;
	}

	public void setDeshabilitarAmortizacion(final boolean deshabilitarAmortizacion) {
		this.deshabilitarAmortizacion = deshabilitarAmortizacion;
	}

	public boolean isDeshabilitarSimulacion() {
		return deshabilitarSimulacion;
	}

	public void setDeshabilitarSimulacion(final boolean deshabilitarSimulacion) {
		this.deshabilitarSimulacion = deshabilitarSimulacion;
	}

	public boolean isActivarCalculoCredito() {
		return activarCalculoCredito;
	}

	public void setActivarCalculoCredito(final boolean activarCalculoCredito) {
		this.activarCalculoCredito = activarCalculoCredito;
	}

	public String getPanelActivoSimulacion() {
		return panelActivoSimulacion;
	}

	public void setPanelActivoSimulacion(final String panelActivoSimulacion) {
		this.panelActivoSimulacion = panelActivoSimulacion;
	}

	public String getMensajeTiempoOtp() {
		return mensajeTiempoOtp;
	}

	public void setMensajeTiempoOtp(final String mensajeTiempoOtp) {
		this.mensajeTiempoOtp = mensajeTiempoOtp;
	}

	public String getTituloValidacionOtp() {
		return tituloValidacionOtp;
	}

	public void setTituloValidacionOtp(final String tituloValidacionOtp) {
		this.tituloValidacionOtp = tituloValidacionOtp;
	}

	public String getCerrarSesionOtp() {
		return cerrarSesionOtp;
	}

	public void setCerrarSesionOtp(final String cerrarSesionOtp) {
		this.cerrarSesionOtp = cerrarSesionOtp;
	}

	public boolean isValidaAceptaServicio() {
		return validaAceptaServicio;
	}

	public void setValidaAceptaServicio(final boolean validaAceptaServicio) {
		this.validaAceptaServicio = validaAceptaServicio;
	}

	public boolean isContinuaValidacionOTP() {
		return continuaValidacionOTP;
	}

	public void setContinuaValidacionOTP(final boolean continuaValidacionOTP) {
		this.continuaValidacionOTP = continuaValidacionOTP;
	}

	public boolean isAceptaServicio() {
		return aceptaServicio;
	}

	public void setAceptaServicio(final boolean aceptaServicio) {
		this.aceptaServicio = aceptaServicio;
	}

	public boolean isSeguirOtp() {
		return seguirOtp;
	}

	public void setSeguirOtp(final boolean seguirOtp) {
		this.seguirOtp = seguirOtp;
	}

	public boolean isSeguirInicial() {
		return seguirInicial;
	}

	public void setSeguirInicial(final boolean seguirInicial) {
		this.seguirInicial = seguirInicial;
	}

	public String getCodigoReservaEcuTur() {
		return codigoReservaEcuTur;
	}

	public void setCodigoReservaEcuTur(final String codigoReservaEcuTur) {
		this.codigoReservaEcuTur = codigoReservaEcuTur;
	}

	public String getDescripcionPaqueteTuristico() {
		return descripcionPaqueteTuristico;
	}

	public void setDescripcionPaqueteTuristico(final String descripcionPaqueteTuristico) {
		this.descripcionPaqueteTuristico = descripcionPaqueteTuristico;
	}

	public BigDecimal getMontoPaqueteTuristico() {
		return montoPaqueteTuristico;
	}

	public void setMontoPaqueteTuristico(final BigDecimal montoPaqueteTuristico) {
		this.montoPaqueteTuristico = montoPaqueteTuristico;
	}

	public String getCondicionesFATEcuTur() {
		return condicionesFATEcuTur;
	}

	public void setCondicionesFATEcuTur(final String condicionesFATEcuTur) {
		this.condicionesFATEcuTur = condicionesFATEcuTur;
	}

	public int getIntentosReserva() {
		return intentosReserva;
	}

	public void setIntentosReserva(final int intentosReserva) {
		this.intentosReserva = intentosReserva;
	}

	public boolean isRenderizarPanelPrincpal() {
		return renderizarPanelPrincpal;
	}

	public void setRenderizarPanelPrincpal(final boolean renderizarPanelPrincpal) {
		this.renderizarPanelPrincpal = renderizarPanelPrincpal;
	}

	public boolean isEsMtnMenProv() {
		return esMtnMenProv;
	}

	public void setEsMtnMenProv(final boolean esMtnMenProv) {
		this.esMtnMenProv = esMtnMenProv;
	}

	public String getValIngCred() {
		return valIngCred;
	}

	public void setValIngCred(final String valIngCred) {
		this.valIngCred = valIngCred;
	}

	public String getValPacProv() {
		return valPacProv;
	}

	public void setValPacProv(final String valPacProv) {
		this.valPacProv = valPacProv;
	}

	public String getDifValPag() {
		return difValPag;
	}

	public void setDifValPag(final String difValPag) {
		this.difValPag = difValPag;
	}

	public boolean isDeshabilitarCalcularCuota() {
		return deshabilitarCalcularCuota;
	}

	public void setDeshabilitarCalcularCuota(final boolean deshabilitarCalcularCuota) {
		this.deshabilitarCalcularCuota = deshabilitarCalcularCuota;
	}
	public BigDecimal getMontoMaximoEmergente() {
		return montoMaximoEmergente;
	}

	public void setMontoMaximoEmergente(BigDecimal montoMaximoEmergente) {
		this.montoMaximoEmergente = montoMaximoEmergente;
	}

	public String getMsjCreditoListaObservadosPep() {
		return msjCreditoListaObservadosPep;
	}

	public void setMsjCreditoListaObservadosPep(String msjCreditoListaObservadosPep) {
		this.msjCreditoListaObservadosPep = msjCreditoListaObservadosPep;
	}

	public String getMsjImprimirFormPensiones() {
		return msjImprimirFormPensiones;
	}

	public void setMsjImprimirFormPensiones(String msjImprimirFormPensiones) {
		this.msjImprimirFormPensiones = msjImprimirFormPensiones;
	}

	public String getMsjPensionesAlimenticiasFinTit() {
		return msjPensionesAlimenticiasFinTit;
	}

	public void setMsjPensionesAlimenticiasFinTit(String msjPensionesAlimenticiasFinTit) {
		this.msjPensionesAlimenticiasFinTit = msjPensionesAlimenticiasFinTit;
	}

	public String getMsjPqInformacion3Otp() {
		return msjPqInformacion3Otp;
	}

	public void setMsjPqInformacion3Otp(String msjPqInformacion3Otp) {
		this.msjPqInformacion3Otp = msjPqInformacion3Otp;
	}

	public String getMsgNovNegativa() {
		return msgNovNegativa;
	}

	public void setMsgNovNegativa(String msgNovNegativa) {
		this.msgNovNegativa = msgNovNegativa;
	}
}	