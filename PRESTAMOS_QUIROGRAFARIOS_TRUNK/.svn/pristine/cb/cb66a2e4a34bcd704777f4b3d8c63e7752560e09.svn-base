package ec.gov.iess.pq.concesion.simulador.backing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlTab;
import org.richfaces.component.html.HtmlTabPanel;

import ec.fin.biess.creditos.pq.enumeracion.TipoInformacionServicioIessEnum;
import ec.fin.biess.creditos.pq.excepcion.ConsultaParametroException;
import ec.fin.biess.creditos.pq.excepcion.MontosMaximosException;
import ec.fin.biess.creditos.pq.excepcion.RegistroCivilBiessException;
import ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio;
import ec.fin.biess.creditos.pq.servicio.InformacionIessServicio;
import ec.fin.biess.enumeraciones.AplicativoEnum;
import ec.fin.biess.pq.simulacion.dto.DatosSimulacionCuotaMontoDto;
import ec.fin.biess.pq.simulacion.dto.DatosSimulacionDto;
import ec.fin.biess.pq.simulacion.exception.SimuladorPqException;
import ec.fin.biess.pq.simulacion.service.SimulacionCreditoPqLocalService;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.TipoPrecalificacionEnum;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.modelo.dto.Requisito;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigible;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.excepcion.SimulacionCancelacionSacException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.creditos.pq.servicio.CalculoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaFechaEfectivaGafLocal;
import ec.gov.iess.creditos.pq.servicio.ConsultaParametroServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoPQEmpSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.GarantiasSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.SimulacionCancelacionSacServicio;
import ec.gov.iess.creditos.pq.servicio.SimularCreditoServicio;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;
import ec.gov.iess.pq.concesion.simulador.util.SimuladorUtil;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.MensajesUtil;
import ec.gov.iess.pq.concesion.web.util.SimulacionCreditoUtil;

/**
 * Bean para el simulador de novaciones
 * 
 * @author hugo.mora
 *
 */
public class SimuladorPqNovacionBacking extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final LoggerBiess LOG = LoggerBiess.getLogger(SimuladorPqNovacionBacking.class);

	private DatosBean datos;

	private SimuladorPqRequestBacking simuladorPqRequest;

	private SimuladorPqSesionBacking simuladorPqSesion;

	private List<Requisito> requisitosBloqueantes;

	private String mensajeError = null;

	// Indicador para determinar si se cumplieron todos los requisitos bloqueantes (true) o si no se cumplio algo o
	// todos (false)
	private boolean aprobadoBloqueantes;

	private HtmlTabPanel htmlTabPanel;

	private HtmlTab htmlTabSim;// pestania simulacion

	private TiposProductosPq tipoProductosPqSeleccionado = TiposProductosPq.NOR;

	private CategoriaTipoProductoPq categoriaProductoSeleccionado = this.tipoProductosPqSeleccionado.getCategoriaTipoProducto();

	private List<SelectItem> tipoTablaItems = new ArrayList<SelectItem>();

	private PrestamoCalculo prestamoCalculo;

	private String mensajeDiscapacitado;

	private String estiloMensajeDiscapacitado;

	private String seleccionTramiteComoDiscapacitado = "NO";

	private Simulacion simulacion;

	private OpcionCredito opcionSimCuota = new OpcionCredito(); // simulacion por cuota

	@EJB(name = "SimulacionCreditoPqServiceImpl/local")
	private SimulacionCreditoPqLocalService simulacionCreditoPqServicio;

	@EJB(name = "PrecalificacionServicioUsaResumenImpl/local")
	private PrecalificacionServicio precalificacionServicio;

	@EJB(name = "DatosPersonalesAfiliadoBiessServicioImpl/local")
	private DatosPersonalesAfiliadoBiessServicio datosPersonalesAfiliadoBiessServicio;

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;

	@EJB(name = "InformacionIessServicioImpl/local")
	private InformacionIessServicio informacionIessServicio;
	
	@EJB(name = "ConsultaParametroServicioImpl/local")
	private ConsultaParametroServicioLocal consultaParametroServicio;
	

	@EJB(name = "SimulacionCancelacionSacServicioImpl/local")
	private SimulacionCancelacionSacServicio simularCancelacionSrv;
	
	@EJB(name = "SimulacionCreditoServicioImpl/local")
	private SimularCreditoServicio simularCreditoServicio;
	
	@EJB(name = "CalculoCreditoServicioImpl/local")
	private CalculoCreditoServicio calculoCreditoServicio;

	@EJB(name = "ConsultaFechaEfectivaGafImpl/local")
	private ConsultaFechaEfectivaGafLocal consultaFechaEfectivaGafLocal;

	@EJB(name="CreditoPQOpEmplSacServicioImpl/local")
	private CreditoPQEmpSacServicioLocal creditoPqClientesEmpl;
	
	@EJB(name="GarantiasSacServicioImpl/local")
	private GarantiasSacServicioLocal garantiasSAC;
	
	private HtmlTab htmlTabAmoNovacion = new HtmlTab();// pestania amortizacion novacion
	
	private static final String NUMERO_NOVACIONES_PERMITIDAS_AFI ="PQW_CON_NUMNOVAPER";
	private static final String NUMERO_NOVACIONES_PERMITIDAS_JUB ="PQW_CON_NUMNOVAJUB";
	String paramsAfiliado=null;
	String paramsJubilado=null;

	@PostConstruct
	private void init() {
	   //	this.verificaNovacionesANioFiscal();
		// Si no tiene mas de dos novaciones en el ultimo anio fiscal realiza las demas consultas
		if (!this.simuladorPqSesion.isTieneNovacionesAnioFiscal()) {
			this.htmlTabAmoNovacion.setDisabled(true);
			this.cargarRequisitos();
		}
	}

	/**
	 * Cuenta las novaciones hechas en el anio fiscal para verificar si puede realizar o no novaciones
	 */
	private void verificaNovacionesANioFiscal() {
		final TipoPrestamista tipoPrestamista = this.datos.getTipo();
		final Calendar cal = Calendar.getInstance();
		final Long anio = Long.valueOf(cal.get(Calendar.YEAR));
		Long numeroNovaciones = 0l;
		int novacionesPermitidasAfi = 0;
		int novacionesPermitidasJub = 0;
		
		obtenerParametrosNovacion();
		
		if (paramsAfiliado != null) {
			final String[] parametrosNovacionesPermitidasAfi = paramsAfiliado.split(";");
			novacionesPermitidasAfi = Integer.parseInt(parametrosNovacionesPermitidasAfi[0]);
		}
		
		if (paramsJubilado != null) {
			final String[] parametrosNovacionesPermitidasJub = paramsJubilado.split(";");
			novacionesPermitidasJub = Integer.parseInt(parametrosNovacionesPermitidasJub[0]);
		}
		
		if (TipoPrestamista.AFILIADO == tipoPrestamista || TipoPrestamista.ZAFRERO_TEMPORAL == tipoPrestamista) {
			numeroNovaciones = this.prestamoServicio.contarPorEstadoAnio(getRemoteUser(), "CNV", anio);
			if (numeroNovaciones.intValue() >= novacionesPermitidasAfi) {
				this.simuladorPqSesion.setTieneNovacionesAnioFiscal(true);
				this.simuladorPqSesion.setMostrarPanelesNovacion(false);
			}
		} else if (TipoPrestamista.JUBILADO == tipoPrestamista) {
			numeroNovaciones = this.prestamoServicio.contarPorEstadoAnio(getRemoteUser(), "CNV", anio);
			if (numeroNovaciones.intValue() >= novacionesPermitidasJub) {
				this.simuladorPqSesion.setTieneNovacionesAnioFiscal(true);
				this.simuladorPqSesion.setMostrarPanelesNovacion(false);
			}
		}
	}
	
	

	/**
	 * Carga los requisitos del afiliado
	 */
	private void cargarRequisitos() {
		this.mensajeError = null;
		final TipoPrestamista tipoPrestamista = this.datos.getTipo();

		try {
			if (TipoPrestamista.JUBILADO == tipoPrestamista) {
				this.datos.setInformacionIessServicio(this.informacionIessServicio.obtenerInformacion(this.datos.getInformacionIessServicio(),
						TipoInformacionServicioIessEnum.PRESTACIONES));
			} else {
				this.datos.setInformacionIessServicio(this.informacionIessServicio.obtenerInformacion(this.datos.getInformacionIessServicio(),
						TipoInformacionServicioIessEnum.APORTES_MORA_CESANTIAS));
				
				this.datos.setInformacionGarantia(this.datos.getInformacionGarantia()==null?
						garantiasSAC.obtenerGarantias(getHttpServletRequest().getRemoteUser()):	this.datos.getInformacionGarantia());
			}

		
			if(datos.getInfoPqExigile()==null) {
				datos.setInfoPqExigile(creditoPqClientesEmpl.obtenerInfoPqOperEmp(getHttpServletRequest().getRemoteUser()));
				}
			if (this.simuladorPqSesion.getPrecalificacionNovacionNuevo() == null) {
				this.simuladorPqSesion.setPrecalificacionNovacionNuevo(this.obtienePrecalificacion(false, null,datos.getInfoPqExigile(),this.datos.getInformacionGarantia()));
			}


			this.aprobadoBloqueantes = true;
			this.requisitosBloqueantes = this.precalificacionServicio.obtieneRequisitosBloqueantesSimulador(
					this.simuladorPqSesion.getPrecalificacionNovacionNuevo().getRequisitos(),
					this.datos.getSolicitante().getDatosPersonales().getFechaNacimiento(), tipoPrestamista, true);

			for (final Requisito requisito : requisitosBloqueantes) {
				if (!requisito.isAprobado()) {
					this.aprobadoBloqueantes = false;
					continue;
				}
			}

			// En caso que haya un requisito bloqueante se encera la lista de creditos a novar para no mostrarlo en la
			// pagina web
			if (!this.aprobadoBloqueantes) {
				this.simuladorPqSesion.getPrestamosNovar().clear();
			}

		this.datos.setPrecalificacion(this.simuladorPqSesion.getPrecalificacionNovacionNuevo());
		} catch (final PrecalificacionExcepcion e) {
			LOG.error("1. Se presento un error al consultar los requisitos bloqueantes", e);
			this.mensajeError = "";
			final String mensaje = MensajesUtil.getErrorMessage(FacesContext.getCurrentInstance(), "credito.error.aplicativo");
			addGlobalErrorMessage(mensaje, "");
			LOG.error("Error al obtener informacion de requisitos en simulador ", e);
			this.simuladorPqSesion.setMostrarPanelesNovacion(false);
		} catch (final PQExigibleException e) {			
			addGlobalErrorMessage(e.getCodigo() + ", " + e.getMensaje(), "");
			LOG.error("Error al obtener informacion desde el SAC ", e);
			this.simuladorPqSesion.setMostrarPanelesNovacion(false);
		} catch (final GarantiasSacException e) {			
			addGlobalErrorMessage(e.getCodigo() + ", " + e.getMensaje(), "");
			LOG.error("Error al obtener las garantias del cliente desde el SAC ", e);
			this.simuladorPqSesion.setMostrarPanelesNovacion(false);
		}
	}

	/**
	 * Obtiene la precalificacion, dada la informacion de novacion
	 * 
	 * @param esNovacion
	 * @param prestamoANovar
	 * @return
	 * @throws PrecalificacionExcepcion
	 * @throws PQExigibleException 
	 */
	private Precalificacion obtienePrecalificacion(final boolean esNovacion, final Prestamo prestamoANovar,InformacionPQExigible infoPqExigile,InformacionGarantias infoGarantiaComp) throws PrecalificacionExcepcion, GarantiasSacException, PQExigibleException {
		Precalificacion precalificacion = new Precalificacion();

		precalificacion = this.precalificacionServicio.obtenerRequisitos(false, esNovacion, false,
				this.datos.getSolicitante().getDatosPersonales().getCedula(), this.datos.getTipo(), this.datos.getSolicitante(),
				TipoPrecalificacionEnum.SIMULACION, this.tipoProductosPqSeleccionado,
				this.datos.getSolicitante().getDatosPersonales().getFechaNacimiento(), this.datos.getInformacionIessServicio(),
				this.datos.getDiscapacitado().booleanValue(), prestamoANovar,infoPqExigile,infoGarantiaComp);

		return precalificacion;
	}

	/**
	 * Procesa la informacion requerida para la simulacion y la setea en las variables requeridas.
	 * 
	 * @param esNovacion
	 * @param prestamoNovar
	 * @return
	 */
	private String cargarCalculoCreditoSimulador(final boolean esNovacion, final Prestamo prestamoNovar,final InformacionGarantias infGarantia,DatosGarantia datGarantia,InformacionPQExigible infoExigible) {
		this.mensajeError = null;
		LOG.info("Carga los parametros para la simulacion");
		final DatosSimulacionDto datosSimulacion = new DatosSimulacionDto();
		datosSimulacion.setCedula(this.datos.getSolicitante().getDatosPersonales().getCedula());
		datosSimulacion.setEsNovacion(esNovacion);
		datosSimulacion.setPrestamoPkNovacion(prestamoNovar == null ? null : prestamoNovar.getCreditoPk());
		datosSimulacion.setTipoPrestamista(this.datos.getTipo());
		datosSimulacion.setRolPrestamista(this.datos.getRolPrestamista());
		datosSimulacion.setTipoProducto(TiposProductosPq.NOR);
		datosSimulacion.setSueldoPromedioAfiliado(this.simuladorPqSesion.getPrecalificacionNovacionNuevo().getSueldoPromedio());
		datosSimulacion.setValorCesantia(this.simuladorPqSesion.getPrecalificacionNovacionNuevo().getGarantia().getValorComprometidoCesantia());
		datosSimulacion.setInformacionPrestacionPensionado(null);
		datosSimulacion.setPrestamoNovar(prestamoNovar);
		datosSimulacion.setInfoGarantia(infGarantia);
		datosSimulacion.setDatGarantia(datGarantia);
		datosSimulacion.setListaCreditos(infoExigible.getListaCreditosExigible());
		if (TipoPrestamista.JUBILADO == this.datos.getTipo()) {
			datosSimulacion.setInformacionPrestacionPensionado(this.datos.getInformacionIessServicio().getInformacionPrestacionPensionado());
		}

		this.simuladorPqSesion.setCargadoNovacion(true);

		try {
			this.simuladorPqSesion.setParametrosSimulacionNovacion(this.simulacionCreditoPqServicio.obtenerParametrosSimulacion(datosSimulacion));
			if (!this.simuladorPqSesion.getParametrosSimulacionNovacion().isTieneCapacidadEndeudamiento()) {
				this.mensajeError = "No tiene capacidad de endeudamiento";
				this.simuladorPqSesion.setCargadoNovacion(false);
			}
		} catch (final SimuladorPqException e) {
			LOG.error("Error al obtener informacion de parametros de simulacion ", e);
			this.mensajeError = super.getResource("messages", "mensaje.simulador.error.parametros.calculo");
			this.simuladorPqSesion.setCargadoNovacion(false);
		}

		return "";
	}

	/**
	 * Selecciona el credito a novar y lo almacena en memoria
	 * 
	 * @return
	 */
	public String seleccionarCreditoNovacion() {
		Prestamo prestamoSeleccionado = (Prestamo) super.getHttpServletRequest().getAttribute("item");
		prestamoSeleccionado=prestamoServicio.getPrestamoByNut(prestamoSeleccionado.getNut());
		
		final TipoPrestamista tipoPrestamista = this.datos.getTipo();



		try {
			if (TipoPrestamista.JUBILADO == tipoPrestamista) {
			} else {
				this.datos.setInformacionGarantia(this.datos.getInformacionGarantia()==null?
						garantiasSAC.obtenerGarantias(getHttpServletRequest().getRemoteUser()):	this.datos.getInformacionGarantia());
			}
			if(datos.getInfoPqExigile()==null) {
				datos.setInfoPqExigile(creditoPqClientesEmpl.obtenerInfoPqOperEmp(getHttpServletRequest().getRemoteUser()));
			}
			prestamoSeleccionado.setValliqnov(obtenerMontoPrecancelarNova(prestamoSeleccionado.getNumOperacionSAC(), prestamoSeleccionado));
			this.simuladorPqSesion.setPrestamoNovacionSeleccionado(prestamoSeleccionado);
			this.simuladorPqSesion.setPrecalificacionNovacionNuevo(this.obtienePrecalificacion(true, prestamoSeleccionado,datos.getInfoPqExigile(),this.datos.getInformacionGarantia()));
		} catch (final PrecalificacionExcepcion e) {
			addGlobalErrorMessage("Se present\u00F3 un error al obtener informaci\u00F3n del cr\u00E9dito a novar", "");
			LOG.error("Error al obtener informacion del credito a novar ", e);
		} catch (final PQExigibleException e) {
			addGlobalErrorMessage(e.getCodigo()+", "+e.getMensaje(), "");
			LOG.error("Error al obtener informacion del credito a novar SAC", e);
		} catch (final GarantiasSacException e) {
			addGlobalErrorMessage(e.getCodigo() + ", " + e.getMensaje(), "");
			LOG.error("Error al obtener garantias del cliente por medio del SAC", e);
		} catch (SimulacionCancelacionSacException e) {
			addGlobalErrorMessage(e.getCodigo() + ", " + e.getMensaje(), "");
			LOG.error("Error al simular la cancelacion operacion", e);
		} catch (ConsultaParametroException e) {
			LOG.error("Error al consultar la fecha efectiva del gaf");
			addGlobalErrorMessage(e.getMessage(),"");		
		}

		this.cargarCalculoCreditoSimulador(true, this.simuladorPqSesion.getPrestamoNovacionSeleccionado(),
				this.datos.getInformacionGarantia(),
				this.simuladorPqSesion.getPrecalificacionNovacionNuevo().getGarantia().getDatGarantia(),datos.getInfoPqExigile());
		this.obtenerRequisitosNoBloqueantes();

		this.simuladorPqSesion.setMostrarPanelSimulacion(true);

		return "";
	}

	private Double obtenerMontoPrecancelarNova(final Long operacion,Prestamo prestamoBD) throws SimulacionCancelacionSacException, ConsultaParametroException {
		final OperacionSacRequest operacionSacRequest = new OperacionSacRequest();
		final OperacionRequestDto operacionRequestDto = new OperacionRequestDto();
		operacionRequestDto.setNumero(operacion);
		operacionRequestDto.setTipoTicket("C+L");
		operacionRequestDto.setFechaSimulacion(consultaFechaEfectivaGafLocal.consultarFechaEfectiva());
		operacionRequestDto.setTipoProducto(prestamoBD.getDestinoComercial().getCodProdPq());
		final ClienteRequestDto cliente = new ClienteRequestDto();
		cliente.setTipoCliente(FuncionesUtilesSac.obtenerTipoPrestamista(datos.getTipo()));
		operacionSacRequest.setOperacion(operacionRequestDto);
		operacionSacRequest.setCliente(cliente);
		CreditoExigibleDto liquidacion=simularCancelacionSrv.simularCancelacion(operacionSacRequest);
		return liquidacion.getMontoPrecancelar().doubleValue();
	}
	


	/**
	 * Llena la lista con los requisitos no bloqueantes
	 */
	private void obtenerRequisitosNoBloqueantes() {
		this.simuladorPqSesion.setMostrarRequisitosNoBloqueantesNovacion(false);

		this.simuladorPqSesion.getListaRequisitosNoBloqueantesNovacion().clear();

		this.simuladorPqSesion.setListaRequisitosNoBloqueantesNovacion(
				SimuladorUtil.obtenerRequisitosNoBloqueantes(this.simuladorPqSesion.getPrecalificacionNovacionNuevo()));

		// Si la lista contiene datos muestra los requisitos no bloqueantes
		if (!this.simuladorPqSesion.getListaRequisitosNoBloqueantesNovacion().isEmpty()) {
			this.simuladorPqSesion.setMostrarRequisitosNoBloqueantesNovacion(true);
		}
	}

	/**
	 * No muestra el boton de ver la tabla de amortización
	 * 
	 * @return
	 */
	public String ocultarBotonTablaAmortizacion() {
		SimulacionCreditoUtil.ocultarBotonTablaAmortizacion(this.simulacion);

		return "";
	}

	/**
	 * Establece la lista de tipo de tabla de amortizacion a la opcion Seleccione
	 * 
	 * @return
	 */
	public String reseteaListaTablaAmortizacion() {
		this.getOpcionSimCuota().setMeses(0);
		this.getOpcionSimCuota().setTipoTablaSeleccionado(null);
		if (this.simulacion != null) {
			this.simulacion.setMontoMaximoCredito(BigDecimal.ZERO);
		}
		return "";
	}

	/**
	 * Establece la lista de tipo de tabla de amortizacion a la opcion Seleccione
	 * 
	 * @return
	 */
	public String reseteaListaTablaAmortizacionMonto() {
		if (this.simulacion != null) {
			this.simulacion.setCuotaMaximaComprometer(BigDecimal.ZERO);
		}
		return "";
	}

	/**
	 * Obtiene el monto maximo del credito
	 */
	public void obtenerMontoMaximo() {
		try {
			this.simulacion = this.simulacionCreditoPqServicio.obtenerMontoMaximo(this.simuladorPqSesion.getParametrosSimulacionNovacion(),
					this.getOpcionSimCuota(), TiposProductosPq.EMERG == this.tipoProductosPqSeleccionado, this.datos.getTipo(),this.datos.getSolicitante(),this.datos.getPrecalificacion());
			this.datos.setSimulacion(simulacion);
		} catch (final SimuladorPqException e) {
			addGlobalErrorMessage(e.getMessage(), "");
			LOG.error(e.getMessage());
		}
	}

	/**
	 * Valida que al ingresar el monto se haya seleccionado una tabla de amortizacion (alemana o francesa)
	 * 
	 * @return
	 */
	public String validarSeleccionTablaAmortizacion() {
		if (this.getOpcionSimCuota().getTipoTablaSeleccionado() == null) {
			this.simulacion.setObservacion("Debe Seleccionar un tipo de Amortizaci\u00F3n");
			this.simulacion.setCalculoCredito(false);
		}
		return "";
	}

	/**
	 * Realiza la simulacion del credito por el monto ingresado
	 */
	public void calcularSimCuotaNew() {
		this.mensajeError = "";
		if (this.simulacion == null) {
			this.simulacion = new Simulacion();
		}
		try {

			DatosCredito datosCredito = new DatosCredito();
			datosCredito.setFechaNacimiento(datos.getSolicitante().getDatosPersonales().getFechaNacimiento());
			datosCredito.setNombreAsegurado(datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			datos.getTipo();
			datosCredito.setCedulaAfiliado(datos.getSolicitante().getDatosPersonales().getCedula());
			datosCredito.setTipoPrestamista(datos.getTipo());
			opcionSimCuota.setMontoMaxSac(this.datos.getSimulacion().getMontoMaximoCredito());
			opcionSimCuota.setTipoProductoPq(TiposProductosPq.NOR);
			opcionSimCuota.setPlazoMaximoCredito(this.datos.getSimulacion().getPlazoMaximoCredito());
			List<BigDecimal> minSBU=	 this.simulacionCreditoPqServicio.validacionMontoMinimo(this.opcionSimCuota.getValorTotalCredito(),BigDecimal.valueOf(this.opcionSimCuota.getMeses()),this.datos.getTipo(),this.simuladorPqSesion.getParametrosSimulacionNovacion().getEdadActualAnios());
if(minSBU!=null) {

	addGlobalErrorMessage( "El monto m\u00EDnimo del cr\u00E9dito debe ser al menos el " + minSBU.get(0).toString() + " del SBU: $" + minSBU.get(1).toString(), "");
	return;
}
			this.simulacion = this.simularCreditoServicio.simularCreditoSegunMontoIngresadoSac(this.opcionSimCuota,
					calculoCreditoServicio, FuncionesUtilesSac.fabricarCliente(datosCredito),this.simuladorPqSesion.getPrestamoNovacionSeleccionado().getNumOperacionSAC());
			opcionSimCuota.setCuotaMensual(simulacion.getCuotaCredito());

			this.simuladorPqSesion.setCalculoCreditoNovacion(true);
			if (this.simulacion != null && !this.simulacion.getCalculoCredito()) {
				this.simuladorPqSesion.setCalculoCreditoNovacion(false);
			}

		} catch (MontosMaximosException e) {
			this.simuladorPqSesion.setCalculoCredito(false);
			addGlobalErrorMessage(e.getMessage(), "");
			LOG.error("Error en calcularSimCuotaNew-Monto Maximo", e);
		} catch (ParseException e) {
			this.simuladorPqSesion.setCalculoCredito(false);
			addGlobalErrorMessage(e.getMessage(), "");
			LOG.error("Error en calcularSimCuotaNew-Parse Exception", e);
		} catch (SimuladorPqException e) {
			LOG.error("Error en validar montoMinimo", e);
			this.simuladorPqSesion.setCalculoCredito(false);
			addGlobalErrorMessage(e.getMessage(), "");
		}

	}

	/**
	 * Obtiene informacion de tabla de amortizacion y seguro de saldos para pq dado el monto
	 */
	public String aceptarSimCuotaNew() {
		final boolean esEmergente = TiposProductosPq.EMERG == this.tipoProductosPqSeleccionado;
		final String cedula = this.datos.getSolicitante().getDatosPersonales().getCedula();

		final DatosSimulacionCuotaMontoDto datosSimulacion = new DatosSimulacionCuotaMontoDto();
		datosSimulacion.setCedula(cedula);
		datosSimulacion.setCuotaMensualMaxima(this.getOpcionSimCuota().getCuotaMensual());
		datosSimulacion.setEdadAsegurado(this.simuladorPqSesion.getParametrosSimulacionNovacion().getEdadActualAnios());
		datosSimulacion.setEsEmergente(esEmergente);
		datosSimulacion.setEsNovacion(true);
		datosSimulacion.setFechaNacimiento(this.simuladorPqSesion.getParametrosSimulacionNovacion().getFechaNacimiento());

		try {
			this.simulacion = this.simulacionCreditoPqServicio.calcularSimulacionCuota(this.getOpcionSimCuota(),
					TiposProductosPq.EMERG == this.tipoProductosPqSeleccionado, this.datos.getTipo(),
					this.simuladorPqSesion.getParametrosSimulacionNovacion(),simuladorPqSesion.getPrestamoNovacionSeleccionado().getNumOperacionSAC());
		} catch (final SimuladorPqException e) {
			addGlobalErrorMessage(e.getMessage(), "");
			LOG.error("Error en calcularSimCuotaNew", e);
		}
		datosSimulacion.setMontoCredito(this.simulacion.getMontoCredito());
		datosSimulacion.setMontoMaximoCredito(this.simulacion.getMontoMaximoCredito());
		datosSimulacion.setPlazoCredito(this.simulacion.getPlazoCredito());
		datosSimulacion.setPrestamoNovacion(this.simuladorPqSesion.getPrestamoNovacionSeleccionado());
		datosSimulacion.setTipoPrestamista(this.datos.getTipo());
		datosSimulacion.setTiposProductosPq(this.tipoProductosPqSeleccionado);
		datosSimulacion.setTipoTablaAmortizacion(this.getOpcionSimCuota().getTipoTablaSeleccionado());
		datosSimulacion.setNombreAsegurado(this.getDatos().getSolicitante().getDatosPersonales().getApellidosNombres());
		try {
			this.prestamoCalculo = this.simulacionCreditoPqServicio.aceptarSimulacionCuota(datosSimulacion);
			this.simuladorPqSesion.setTablaAmortizacionNovacion(this.prestamoCalculo.getDividendoCalculoList());

			this.htmlTabAmoNovacion.setDisabled(false);
			this.htmlTabPanel.setValue("amo");
			this.simuladorPqSesion.setCalculoCreditoNovacion(false);
			datosSimulacion.setEsNovacion(false);
			datosSimulacion.setPrestamoNovacion(null);
		} catch (final SimuladorPqException e) {
			final String mensajeReemplazo = e.getMessage().replace("ec.gov.iess.creditos.pq.excepcion.CalculoCreditoException:", "");

			addGlobalErrorMessage(mensajeReemplazo, "");
			LOG.error("Error en calcularSimCuotaNew", e);
		} catch (final TablaAmortizacionSacException e) {
			addGlobalErrorMessage(e.getCodigo()+", "+e.getMensaje(), "");
			LOG.error("Error en aceptarSimulacionCuota", e);
		}

		return "";
	}

	/**
	 * Verifica si el asegurado tiene condicion de discapacitado
	 */
	public void confirmarDiscapacidad() {
		this.mensajeDiscapacitado = null;

		if ("SI".equals(this.simuladorPqRequest.getSeleccionTramiteComoDiscapacitado())) {
			final String cedula = this.datos.getSolicitante().getDatosPersonales().getCedula();
			try {
				final boolean esDiscapacitado = this.datosPersonalesAfiliadoBiessServicio.validarDiscapacitado(cedula, super.getIpUser(), AplicativoEnum.PQ_WEB);
				if (esDiscapacitado) {
					this.mensajeDiscapacitado = super.getResource("messages", "info.ok.discapacitado");
					this.datos.setDiscapacitado(true);
					this.estiloMensajeDiscapacitado = "okayPanel";
					this.simuladorPqSesion.setPrecalificacionNovacionNuevo(null);
					this.cargarRequisitos();
				} else {
					this.mensajeDiscapacitado = super.getResource("errores", "error.discapacitado");
					this.datos.setDiscapacitado(false);
					this.estiloMensajeDiscapacitado = "warningPanel";
				}
			} catch (final RegistroCivilBiessException e) {
				final String msg = getResource("errores", "error.rc.discapacitado");
				LOG.error(msg + ". CI:" + cedula, e);
				this.mensajeDiscapacitado = msg;
				this.datos.setDiscapacitado(new Boolean(false));
				this.estiloMensajeDiscapacitado = "warningPanel";
			} catch (final Exception e) {
				final String msg = "Error al confirmar informaci\u00F3n de discapacidad.";
				LOG.error(msg + ". CI:" + cedula, e);
				this.mensajeDiscapacitado = msg;
				this.datos.setDiscapacitado(new Boolean(false));
				this.estiloMensajeDiscapacitado = "warningPanel";
			}
		} else {
			this.mensajeDiscapacitado = null;
			this.datos.setDiscapacitado(false);
			this.simuladorPqSesion.setPrecalificacionNovacionNuevo(null);
			this.cargarRequisitos();
		}

	}

	// Getters and setters
	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(final DatosBean datos) {
		this.datos = datos;
	}

	public SimuladorPqSesionBacking getSimuladorPqSesion() {
		return simuladorPqSesion;
	}

	public void setSimuladorPqSesion(final SimuladorPqSesionBacking simuladorPqSesion) {
		this.simuladorPqSesion = simuladorPqSesion;
	}

	public List<Requisito> getRequisitosBloqueantes() {
		return requisitosBloqueantes;
	}

	public void setRequisitosBloqueantes(final List<Requisito> requisitosBloqueantes) {
		this.requisitosBloqueantes = requisitosBloqueantes;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(final String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public boolean isAprobadoBloqueantes() {
		return aprobadoBloqueantes;
	}

	public void setAprobadoBloqueantes(final boolean aprobadoBloqueantes) {
		this.aprobadoBloqueantes = aprobadoBloqueantes;
	}

	public HtmlTabPanel getHtmlTabPanel() {
		return htmlTabPanel;
	}

	public void setHtmlTabPanel(final HtmlTabPanel htmlTabPanel) {
		this.htmlTabPanel = htmlTabPanel;
	}

	public HtmlTab getHtmlTabSim() {
		return htmlTabSim;
	}

	public void setHtmlTabSim(final HtmlTab htmlTabSim) {
		this.htmlTabSim = htmlTabSim;
	}

	public TiposProductosPq getTipoProductosPqSeleccionado() {
		return tipoProductosPqSeleccionado;
	}

	public void setTipoProductosPqSeleccionado(final TiposProductosPq tipoProductosPqSeleccionado) {
		this.tipoProductosPqSeleccionado = tipoProductosPqSeleccionado;
	}

	public CategoriaTipoProductoPq getCategoriaProductoSeleccionado() {
		return categoriaProductoSeleccionado;
	}

	public void setCategoriaProductoSeleccionado(final CategoriaTipoProductoPq categoriaProductoSeleccionado) {
		this.categoriaProductoSeleccionado = categoriaProductoSeleccionado;
	}

	public List<SelectItem> getTipoTablaItems() {
		this.tipoTablaItems = SimulacionCreditoUtil.obtenerTipoTablaItems(datos.getTipo());
		return tipoTablaItems;
	}

	public void setTipoTablaItems(final List<SelectItem> tipoTablaItems) {
		this.tipoTablaItems = tipoTablaItems;
	}

	public PrestamoCalculo getPrestamoCalculo() {
		return prestamoCalculo;
	}

	public void setPrestamoCalculo(final PrestamoCalculo prestamoCalculo) {
		this.prestamoCalculo = prestamoCalculo;
	}

	public String getMensajeDiscapacitado() {
		return mensajeDiscapacitado;
	}

	public void setMensajeDiscapacitado(final String mensajeDiscapacitado) {
		this.mensajeDiscapacitado = mensajeDiscapacitado;
	}

	public String getEstiloMensajeDiscapacitado() {
		return estiloMensajeDiscapacitado;
	}

	public void setEstiloMensajeDiscapacitado(final String estiloMensajeDiscapacitado) {
		this.estiloMensajeDiscapacitado = estiloMensajeDiscapacitado;
	}

	public String getSeleccionTramiteComoDiscapacitado() {
		return seleccionTramiteComoDiscapacitado;
	}

	public void setSeleccionTramiteComoDiscapacitado(final String seleccionTramiteComoDiscapacitado) {
		this.seleccionTramiteComoDiscapacitado = seleccionTramiteComoDiscapacitado;
	}

	public Simulacion getSimulacion() {
		return simulacion;
	}

	public void setSimulacion(final Simulacion simulacion) {
		this.simulacion = simulacion;
	}

	public OpcionCredito getOpcionSimCuota() {
		return opcionSimCuota;
	}

	public void setOpcionSimCuota(final OpcionCredito opcionSimCuota) {
		this.opcionSimCuota = opcionSimCuota;
	}

	public String getColorTextoEndeudamiento() {
		String color = "color:Black;";
		if (this.simuladorPqSesion.getParametrosSimulacionNovacion() != null
				&& this.simuladorPqSesion.getParametrosSimulacionNovacion().getCapacidadEndeudamiento() != null) {
			if (this.simuladorPqSesion.getParametrosSimulacionNovacion().getCapacidadEndeudamiento().compareTo(BigDecimal.ZERO) <= 0) {
				color = "color:Red;";
			}
		}

		return color;
	}

	public SimuladorPqRequestBacking getSimuladorPqRequest() {
		return simuladorPqRequest;
	}

	public void setSimuladorPqRequest(final SimuladorPqRequestBacking simuladorPqRequest) {
		this.simuladorPqRequest = simuladorPqRequest;
	}

	public HtmlTab getHtmlTabAmoNovacion() {
		return htmlTabAmoNovacion;
	}

	public void setHtmlTabAmoNovacion(final HtmlTab htmlTabAmoNovacion) {
		this.htmlTabAmoNovacion = htmlTabAmoNovacion;
	}
	
	private void obtenerParametrosNovacion() {
		try{
			paramsAfiliado= (String)this.consultaParametroServicio.consultarParametro(NUMERO_NOVACIONES_PERMITIDAS_AFI,"string");
			LOG.info("PARAMETRO AFILIADO OBTENIDO: "+paramsAfiliado);
			paramsJubilado= (String)this.consultaParametroServicio.consultarParametro(NUMERO_NOVACIONES_PERMITIDAS_JUB,"string");
			LOG.info("PARAMETRO JUBILADO OBTENIDO: "+paramsJubilado);
		}catch(final ConsultaParametroException e){
			LOG.error("Erro al consultar el parametro numero novaciones permitidas afiliado/jubilado");
		}
	}

}
