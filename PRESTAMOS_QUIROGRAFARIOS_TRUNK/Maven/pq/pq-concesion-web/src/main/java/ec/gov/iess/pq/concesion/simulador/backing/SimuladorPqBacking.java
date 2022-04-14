package ec.gov.iess.pq.concesion.simulador.backing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlTab;
import org.richfaces.component.html.HtmlTabPanel;

import ec.fin.biess.creditos.pq.enumeracion.TipoInformacionServicioIessEnum;
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
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.modelo.dto.Requisito;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigible;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.creditos.pq.servicio.CalculoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.CreditoPQEmpSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.GarantiasSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio;
import ec.gov.iess.creditos.pq.servicio.SimularCreditoServicio;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;
import ec.gov.iess.pq.concesion.simulador.util.SimuladorUtil;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.MensajesUtil;
import ec.gov.iess.pq.concesion.web.util.SimulacionCreditoUtil;

/**
 * Backing para el simulador de prestamos quirografarios
 * 
 * @author hugo.mora
 * @date 2017/05/18
 *
 */
public class SimuladorPqBacking extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final LoggerBiess LOG = LoggerBiess.getLogger(SimuladorPqBacking.class);

	private DatosBean datos;

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

	@EJB(name = "SimulacionCreditoServicioImpl/local")
	private SimularCreditoServicio simularCreditoServicio;

	@EJB(name = "PrecalificacionServicioUsaResumenImpl/local")
	private PrecalificacionServicio precalificacionServicio;

	@EJB(name = "DatosPersonalesAfiliadoBiessServicioImpl/local")
	private DatosPersonalesAfiliadoBiessServicio datosPersonalesAfiliadoBiessServicio;

	@EJB(name = "InformacionIessServicioImpl/local")
	private InformacionIessServicio informacionIessServicio;

	@EJB(name = "CalculoCreditoServicioImpl/local")
	private CalculoCreditoServicio calculoCreditoServicio;

	private SimuladorPqRequestBacking simuladorPqRequest;

	@EJB(name="GarantiasSacServicioImpl/local")
	private GarantiasSacServicioLocal garantiasSAC;
	
	@EJB(name="CreditoPQOpEmplSacServicioImpl/local")
	private CreditoPQEmpSacServicioLocal creditoPqClientesEmpl;

	/**
	 * Metodo que carga despues del constructor
	 */
	@PostConstruct
	private void init() {
		this.simuladorPqSesion.getHtmlTabAmo().setDisabled(true);
		this.cargarRequisitos();
	}

	/**
	 * Metodo que carga los requisitos bloqueantes para el simulador de prestamos quirografarios
	 * 
	 * @return
	 */
	private void cargarRequisitos() {
		this.mensajeError = null;
		TipoPrestamista tipoPrestamista = this.datos.getTipo();

		try {

			if (TipoPrestamista.JUBILADO == tipoPrestamista) {
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

			if (this.simuladorPqSesion.getPrecalificacionNuevo() == null) {
				this.simuladorPqSesion.setPrecalificacionNuevo(this.obtienePrecalificacion(false, null,datos.getInfoPqExigile(),this.datos.getInformacionGarantia()));
			}

			this.aprobadoBloqueantes = true;
			this.requisitosBloqueantes = this.precalificacionServicio.obtieneRequisitosBloqueantesSimulador(
					this.simuladorPqSesion.getPrecalificacionNuevo().getRequisitos(),
					this.datos.getSolicitante().getDatosPersonales().getFechaNacimiento(), tipoPrestamista, false);

			for (Requisito requisito : requisitosBloqueantes) {
				if (!requisito.isAprobado()) {
					this.aprobadoBloqueantes = false;
					continue;
				}
			}

			if (this.aprobadoBloqueantes && this.simuladorPqSesion.getParametrosSimulacion() == null) {
				this.cargarCalculoCreditoSimulador(false, null,datos.getInfoPqExigile());

				this.obtenerRequisitosNoBloqueantes();
			}

			this.simuladorPqSesion.setCargaInicial(true);
			this.datos.setPrecalificacion(this.simuladorPqSesion.getPrecalificacionNuevo());

		} catch (PrecalificacionExcepcion e) {
			LOG.error("1. Se presento un error al consultar los requisitos bloqueantes", e);
			this.mensajeError = "";
			String mensaje = MensajesUtil.getErrorMessage(FacesContext.getCurrentInstance(), "credito.error.aplicativo");
			addGlobalErrorMessage(mensaje, "");
			LOG.error("Error al obtener informacion de requisitos en simulador ", e);
			this.simuladorPqSesion.setMostrarPaneles(false);
		} catch (PQExigibleException e) {
			addGlobalErrorMessage(e.getCodigo() + ", " + e.getMensaje(), "");
			LOG.error("Error al obtener los exigibles del SAC ", e);
			this.simuladorPqSesion.setMostrarPaneles(false);
		} catch (GarantiasSacException e) {
			addGlobalErrorMessage(e.getCodigo() + ", " + e.getMensaje(), "");
			LOG.error("Error al obtener garantias del cliente desde el SAC ", e);
			this.simuladorPqSesion.setMostrarPaneles(false);
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
	private Precalificacion obtienePrecalificacion(boolean esNovacion, Prestamo prestamoANovar, InformacionPQExigible infoPqExigile,InformacionGarantias informacionGarantia) throws PrecalificacionExcepcion, GarantiasSacException, PQExigibleException {
		LOG.info("Ingresa en obtiene precalificacion");
		Precalificacion precalificacion = new Precalificacion();

		precalificacion = this.precalificacionServicio.obtenerRequisitos(false, esNovacion, false,
				this.datos.getSolicitante().getDatosPersonales().getCedula(), this.datos.getTipo(), this.datos.getSolicitante(),
				TipoPrecalificacionEnum.SIMULACION, this.tipoProductosPqSeleccionado,
				this.datos.getSolicitante().getDatosPersonales().getFechaNacimiento(), this.datos.getInformacionIessServicio(),
				this.datos.getDiscapacitado().booleanValue(), prestamoANovar,infoPqExigile,informacionGarantia);

		return precalificacion;
	}

	/**
	 * Procesa la informacion requerida para la simulacion y la setea en las variables requeridas.
	 * 
	 * @param esNovacion
	 * @param prestamoNovar
	 * @return
	 */
	private String cargarCalculoCreditoSimulador(boolean esNovacion, Prestamo prestamoNovar,InformacionPQExigible infoPqExigile) {
		this.mensajeError = null;
		LOG.info("Carga los parametros para la simulacion");
		DatosSimulacionDto datosSimulacion = new DatosSimulacionDto();
		datosSimulacion.setCedula(this.datos.getSolicitante().getDatosPersonales().getCedula());
		datosSimulacion.setEsNovacion(esNovacion);
		datosSimulacion.setPrestamoPkNovacion(prestamoNovar == null ? null : prestamoNovar.getCreditoPk());
		datosSimulacion.setTipoPrestamista(this.datos.getTipo());
		datosSimulacion.setRolPrestamista(this.datos.getRolPrestamista());
		datosSimulacion.setTipoProducto(TiposProductosPq.NOR);
		datosSimulacion.setSueldoPromedioAfiliado(this.simuladorPqSesion.getPrecalificacionNuevo().getSueldoPromedio());
		datosSimulacion.setValorCesantia(this.simuladorPqSesion.getPrecalificacionNuevo().getGarantia().getValorComprometidoCesantia());
		datosSimulacion.setInformacionPrestacionPensionado(null);
		datosSimulacion.setInfoGarantia(this.datos.getInformacionGarantia());
		datosSimulacion.setListaCreditos(infoPqExigile.getListaCreditosExigible());
		if (TipoPrestamista.JUBILADO == this.datos.getTipo()) {
			datosSimulacion.setInformacionPrestacionPensionado(this.datos.getInformacionIessServicio().getInformacionPrestacionPensionado());
		}

		this.simuladorPqSesion.setCargado(true);

		try {
			this.simuladorPqSesion.setParametrosSimulacion(this.simulacionCreditoPqServicio.obtenerParametrosSimulacion(datosSimulacion));
			if (!this.simuladorPqSesion.getParametrosSimulacion().isTieneCapacidadEndeudamiento()) {
				this.mensajeError = "No tiene capacidad de endeudamiento";
				this.simuladorPqSesion.setCargado(false);
			}
			
		} catch (SimuladorPqException e) {
			LOG.error("Error al obtener informacion de parametros de simulacion ", e);
			this.mensajeError = super.getResource("messages", "mensaje.simulador.error.parametros.calculo");
			this.simuladorPqSesion.setCargado(false);
		}

		return "";
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
			this.simulacion = this.simulacionCreditoPqServicio.obtenerMontoMaximo(this.simuladorPqSesion.getParametrosSimulacion(),
					this.getOpcionSimCuota(), TiposProductosPq.EMERG == this.tipoProductosPqSeleccionado, this.datos.getTipo(),this.datos.getSolicitante(),this.datos.getPrecalificacion());
			this.datos.setSimulacion(simulacion);
		} catch (SimuladorPqException e) {
			this.opcionSimCuota.setTipoTablaSeleccionado(null);
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
			
							DatosCredito datosCredito=new DatosCredito();
							datosCredito.setFechaNacimiento(datos.getSolicitante()
									.getDatosPersonales().getFechaNacimiento());
							datosCredito.setNombreAsegurado(datos.getSolicitante()
									.getDatosPersonales().getApellidosNombres());
							datos.getTipo();
							datosCredito.setCedulaAfiliado(datos.getSolicitante()
									.getDatosPersonales().getCedula());
							datosCredito.setTipoPrestamista(datos.getTipo());
							opcionSimCuota.setMontoMaxSac(this.datos.getSimulacion().getMontoMaximoCredito());
							opcionSimCuota.setTipoProductoPq(TiposProductosPq.NOR);
							opcionSimCuota.setPlazoMaximoCredito(this.datos.getSimulacion().getPlazoMaximoCredito());
						List<BigDecimal> minSBU=	 this.simulacionCreditoPqServicio.validacionMontoMinimo(this.opcionSimCuota.getValorTotalCredito(),BigDecimal.valueOf(this.opcionSimCuota.getMeses()),this.datos.getTipo(),this.simuladorPqSesion.getParametrosSimulacion().getEdadActualAnios());
	  if(minSBU!=null) {
							
			addGlobalErrorMessage( "El monto m\u00EDnimo del cr\u00E9dito debe ser al menos el " + minSBU.get(0).toString() + " del SBU: $" + minSBU.get(1).toString(), "");
			return;
	   }
		this.simulacion = this.simularCreditoServicio.simularCreditoSegunMontoIngresadoSac(this.opcionSimCuota, calculoCreditoServicio, FuncionesUtilesSac.fabricarCliente(datosCredito), null);
		opcionSimCuota.setCuotaMensual(simulacion.getCuotaCredito());	
					this.simuladorPqSesion.getParametrosSimulacion();
			
			this.simuladorPqSesion.setCalculoCredito(true);
			if (this.simulacion != null && !this.simulacion.getCalculoCredito()) {
				this.simuladorPqSesion.setCalculoCredito(false);
			}
			
		} catch (MontosMaximosException e) {
			this.simuladorPqSesion.setCalculoCredito(false);
			addGlobalErrorMessage(e.getMessage(), "");
			LOG.error("Error en obtener maximo", e);
		} catch (ParseException e) {
			this.simuladorPqSesion.setCalculoCredito(false);
			addGlobalErrorMessage(e.getMessage(), "");
			LOG.error("Error en parsear fecha GAF", e);
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
		boolean esEmergente = TiposProductosPq.EMERG == this.tipoProductosPqSeleccionado;
		String cedula = this.datos.getSolicitante().getDatosPersonales().getCedula();

		DatosSimulacionCuotaMontoDto datosSimulacion = new DatosSimulacionCuotaMontoDto();
		datosSimulacion.setCedula(cedula);
		datosSimulacion.setCuotaMensualMaxima(this.getOpcionSimCuota().getCuotaMensual());
		datosSimulacion.setEdadAsegurado(this.simuladorPqSesion.getParametrosSimulacion().getEdadActualAnios());
		datosSimulacion.setEsEmergente(esEmergente);
		datosSimulacion.setEsNovacion(false);
		datosSimulacion.setFechaNacimiento(this.simuladorPqSesion.getParametrosSimulacion().getFechaNacimiento());

		try {
			this.simulacion = this.simulacionCreditoPqServicio.calcularSimulacionCuota(this.getOpcionSimCuota(),
					TiposProductosPq.EMERG == this.tipoProductosPqSeleccionado, this.datos.getTipo(),
					this.simuladorPqSesion.getParametrosSimulacion(),null);
		} catch (SimuladorPqException e) {
			addGlobalErrorMessage(e.getMessage(), "");
			LOG.error("Error en calcularSimCuotaNew", e);
		}
		datosSimulacion.setMontoCredito(this.simulacion.getMontoCredito());
		datosSimulacion.setMontoMaximoCredito(this.simulacion.getMontoMaximoCredito());
		datosSimulacion.setPlazoCredito(this.simulacion.getPlazoCredito());
		datosSimulacion.setPrestamoNovacion(null);
		datosSimulacion.setTipoPrestamista(this.datos.getTipo());
		datosSimulacion.setTiposProductosPq(this.tipoProductosPqSeleccionado);
		datosSimulacion.setTipoTablaAmortizacion(this.getOpcionSimCuota().getTipoTablaSeleccionado());
		datosSimulacion.setNombreAsegurado(this.getDatos().getSolicitante().getDatosPersonales().getApellidosNombres());
		try {
			this.prestamoCalculo = this.simulacionCreditoPqServicio.aceptarSimulacionCuota(datosSimulacion);
			this.simuladorPqSesion.setTablaAmortizacion(this.prestamoCalculo.getDividendoCalculoList());
			this.simuladorPqSesion.getHtmlTabAmo().setDisabled(false);
			this.htmlTabPanel.setValue("amo");
			this.simuladorPqSesion.setCalculoCredito(false);
			datosSimulacion.setEsNovacion(false);
			datosSimulacion.setPrestamoNovacion(null);
		} catch (SimuladorPqException e) {
			addGlobalErrorMessage(e.getMessage(), "");
			LOG.error("Error en calcularSimCuotaNew", e);
		} catch (TablaAmortizacionSacException e) {
			addGlobalErrorMessage(e.getCodigo()+", "+e.getMensaje(), "");
			LOG.error("Error en aceptarSimulacionCuota", e);
		}

		return "";

	}

	/**
	 * Llena la lista con los requisitos no bloqueantes
	 */
	private void obtenerRequisitosNoBloqueantes() {
		this.simuladorPqSesion.setMostrarRequisitosNoBloqueantes(false);

		this.simuladorPqSesion.getListaRequisitosNoBloqueantes().clear();

		this.simuladorPqSesion.setListaRequisitosNoBloqueantes(SimuladorUtil.obtenerRequisitosNoBloqueantes(this.simuladorPqSesion.getPrecalificacionNuevo()));

		// Si la lista contiene datos muestra los requisitos no bloqueantes
		if (!this.simuladorPqSesion.getListaRequisitosNoBloqueantes().isEmpty()) {
			this.simuladorPqSesion.setMostrarRequisitosNoBloqueantes(true);
		}
	}

	/**
	 * Verifica si el asegurado tiene condicion de discapacitado
	 */
	public void confirmarDiscapacidad() {
		this.mensajeDiscapacitado = null;

		if ("SI".equals(this.simuladorPqRequest.getSeleccionTramiteComoDiscapacitado())) {
			String cedula = this.datos.getSolicitante().getDatosPersonales().getCedula();
			try {
				boolean esDiscapacitado = this.datosPersonalesAfiliadoBiessServicio.validarDiscapacitado(cedula, super.getIpUser(),
						AplicativoEnum.PQ_WEB);
				if (esDiscapacitado) {
					this.mensajeDiscapacitado = super.getResource("messages", "info.ok.discapacitado");
					this.datos.setDiscapacitado(true);
					this.estiloMensajeDiscapacitado = "okayPanel";
					this.simuladorPqSesion.setPrecalificacionNuevo(null);
					this.cargarRequisitos();
				} else {
					this.mensajeDiscapacitado = super.getResource("errores", "error.discapacitado");
					this.datos.setDiscapacitado(false);
					this.estiloMensajeDiscapacitado = "warningPanel";
				}
			} catch (RegistroCivilBiessException e) {
				String msg = getResource("errores", "error.rc.discapacitado");
				LOG.error(msg + ". CI:" + cedula, e);
				this.mensajeDiscapacitado = msg;
				this.datos.setDiscapacitado(new Boolean(false));
				this.estiloMensajeDiscapacitado = "warningPanel";
			} catch (Exception e) {
				String msg = "Error al confirmar informaci\u00F3n de discapacidad.";
				LOG.error(msg + ". CI:" + cedula, e);
				this.mensajeDiscapacitado = msg;
				this.datos.setDiscapacitado(new Boolean(false));
				this.estiloMensajeDiscapacitado = "warningPanel";
			}
		} else {
			this.mensajeDiscapacitado = null;
			this.datos.setDiscapacitado(false);
			this.simuladorPqSesion.setPrecalificacionNuevo(null);
			this.cargarRequisitos();
		}

	}

	// Getters and setters
	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	public List<Requisito> getRequisitosBloqueantes() {
		return requisitosBloqueantes;
	}

	public void setRequisitosBloqueantes(List<Requisito> requisitosBloqueantes) {
		this.requisitosBloqueantes = requisitosBloqueantes;
	}

	public boolean isAprobadoBloqueantes() {
		return aprobadoBloqueantes;
	}

	public void setAprobadoBloqueantes(boolean aprobadoBloqueantes) {
		this.aprobadoBloqueantes = aprobadoBloqueantes;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public SimulacionCreditoPqLocalService getSimulacionCreditoPqServicio() {
		return simulacionCreditoPqServicio;
	}

	public void setSimulacionCreditoPqServicio(SimulacionCreditoPqLocalService simulacionCreditoPqServicio) {
		this.simulacionCreditoPqServicio = simulacionCreditoPqServicio;
	}

	public HtmlTabPanel getHtmlTabPanel() {
		return htmlTabPanel;
	}

	public void setHtmlTabPanel(HtmlTabPanel htmlTabPanel) {
		this.htmlTabPanel = htmlTabPanel;
	}

	public HtmlTab getHtmlTabSim() {
		return htmlTabSim;
	}

	public void setHtmlTabSim(HtmlTab htmlTabSim) {
		this.htmlTabSim = htmlTabSim;
	}

	public CategoriaTipoProductoPq getCategoriaProductoSeleccionado() {
		return categoriaProductoSeleccionado;
	}

	public void setCategoriaProductoSeleccionado(CategoriaTipoProductoPq categoriaProductoSeleccionado) {
		this.categoriaProductoSeleccionado = categoriaProductoSeleccionado;
	}

	public List<SelectItem> getTipoTablaItems() {
		tipoTablaItems = SimulacionCreditoUtil.obtenerTipoTablaItems(datos.getTipo());
		return tipoTablaItems;
	}

	public void setTipoTablaItems(List<SelectItem> tipoTablaItems) {
		this.tipoTablaItems = tipoTablaItems;
	}

	public PrestamoCalculo getPrestamoCalculo() {
		return prestamoCalculo;
	}

	public void setPrestamoCalculo(PrestamoCalculo prestamoCalculo) {
		this.prestamoCalculo = prestamoCalculo;
	}

	public TiposProductosPq getTipoProductosPqSeleccionado() {
		return tipoProductosPqSeleccionado;
	}

	public void setTipoProductosPqSeleccionado(TiposProductosPq tipoProductosPqSeleccionado) {
		this.tipoProductosPqSeleccionado = tipoProductosPqSeleccionado;
	}

	public String getColorTextoEndeudamiento() {
		String color = "color:Black;";
		if (this.simuladorPqSesion.getParametrosSimulacion() != null
				&& this.simuladorPqSesion.getParametrosSimulacion().getCapacidadEndeudamiento() != null) {
			if (this.simuladorPqSesion.getParametrosSimulacion().getCapacidadEndeudamiento().compareTo(BigDecimal.ZERO) <= 0) {
				color = "color:Red;";
			}
		}

		return color;
	}

	public String getMensajeDiscapacitado() {
		return mensajeDiscapacitado;
	}

	public void setMensajeDiscapacitado(String mensajeDiscapacitado) {
		this.mensajeDiscapacitado = mensajeDiscapacitado;
	}

	public String getEstiloMensajeDiscapacitado() {
		return estiloMensajeDiscapacitado;
	}

	public void setEstiloMensajeDiscapacitado(String estiloMensajeDiscapacitado) {
		this.estiloMensajeDiscapacitado = estiloMensajeDiscapacitado;
	}

	public String getSeleccionTramiteComoDiscapacitado() {
		return seleccionTramiteComoDiscapacitado;
	}

	public void setSeleccionTramiteComoDiscapacitado(String seleccionTramiteComoDiscapacitado) {
		this.seleccionTramiteComoDiscapacitado = seleccionTramiteComoDiscapacitado;
	}

	public SimuladorPqSesionBacking getSimuladorPqSesion() {
		return simuladorPqSesion;
	}

	public void setSimuladorPqSesion(SimuladorPqSesionBacking simuladorPqSesion) {
		this.simuladorPqSesion = simuladorPqSesion;
	}

	public Simulacion getSimulacion() {
		return simulacion;
	}

	public void setSimulacion(Simulacion simulacion) {
		this.simulacion = simulacion;
	}

	public OpcionCredito getOpcionSimCuota() {
		return opcionSimCuota;
	}

	public void setOpcionSimCuota(OpcionCredito opcionSimCuota) {
		this.opcionSimCuota = opcionSimCuota;
	}

	public SimuladorPqRequestBacking getSimuladorPqRequest() {
		return simuladorPqRequest;
	}

	public void setSimuladorPqRequest(SimuladorPqRequestBacking simuladorPqRequest) {
		this.simuladorPqRequest = simuladorPqRequest;
	}

}