package ec.gov.iess.pq.concesion.web.backing;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.alerts.modelo.InformacionAfiliado;
import ec.fin.biess.conozcasucliente.sbs.enumeration.NivelEstudioEnum;
import ec.fin.biess.conozcasucliente.sbs.enumeration.TipoViviendaEnum;
import ec.fin.biess.creditos.pq.alertas.util.AlertUtil;
import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoInformacionServicioIessEnum;
import ec.fin.biess.creditos.pq.excepcion.RegistroCivilBiessException;
import ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio;
import ec.fin.biess.creditos.pq.servicio.InformacionIessServicio;
import ec.fin.biess.creditos.pq.servicio.TipoCuentaBiessServicio;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.enumeraciones.AplicativoEnum;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.persistencia.Referencia;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;
import ec.gov.iess.cuentabancaria.servicio.InstitucionFinancieraServicio;
import ec.gov.iess.hl.exception.RegistroCivilException;
import ec.gov.iess.hl.modelo.RegistroCivil;
import ec.gov.iess.hl.servicio.DivisionPoliticaServicio;
import ec.gov.iess.hl.servicio.RegistroCivilServicio;
import ec.gov.iess.pq.concesion.conozcacliente.backing.ConozcaClienteBacking;
import ec.gov.iess.pq.concesion.focalizados.backing.OllasFocalizadosBacking;
import ec.gov.iess.pq.concesion.focalizados.backing.PqFocalizadosBacking;
import ec.gov.iess.pq.concesion.web.bean.CombosDireccionBean;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.CedulaUtil;
import ec.gov.iess.pq.concesion.web.util.MensajesUtil;
import ec.gov.iess.pq.concesion.web.util.Utilities;
import ec.gov.iess.suite.seguridad.Encriptacion;
import ec.gov.iess.suite.seguridad.EncriptacionException;
import ec.gov.iess.suite.seguridad.EncriptacionFactory;
import ec.gov.iess.suite.seguridad.EncriptacionFactoryException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Daniel Cardenas
 * 
 */
public class DatosSolicitanteBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -2137208540804949270L;

	// log4j
	private transient static final LoggerBiess log = LoggerBiess.getLogger(DatosSolicitanteBean.class);

	/* INC-1719 2013-11-11 Agregar preferencias para afiliados discapacitados */
	@EJB(name = "DatosPersonalesAfiliadoBiessServicioImpl/local")
	private DatosPersonalesAfiliadoBiessServicio datosPersonalesAfiliadoBiessServicio;

	@EJB(name = "AlertUserHelperImpl/local")
	private AlertUserHelper alertUserHelper;
	
	@EJB(name = "InformacionIessServicioImpl/local")
	private InformacionIessServicio informacionIessServicio;

	// propiedades del componente

	private String error;

	private DatosBean datos;
	private PqFocalizadosBacking pqFocalizados;
	private OllasFocalizadosBacking ollasFocalizados;

	private String requisitos;

	/* INC-1719 2013-11-11 Agregar preferencias para afiliados discapacitados */
	private String errorDiscapacitado;
	private String infoDiscapacitado;

	// INC-2272 Pensiones Alimenticias
	@EJB(name = "InstitucionFinancieraServicioImpl/local")
	private transient InstitucionFinancieraServicio institucionFinancieraServicio;

	@EJB(name = "RegistroCivilServicioImpl/local")
	private RegistroCivilServicio registroCivilServicio;

	@EJB(name = "DivisionPoliticaServicioImpl/local")
	private DivisionPoliticaServicio divisionPoliticaServicio;

	@EJB(name = "TipoCuentaBiessServicioImpl/local")
	private TipoCuentaBiessServicio tipoCuentaBiessServicio;
	
	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;

	private String mensajeErrorRegCivil;

	private String textoFAT;

	private boolean desplegarFAT;

	private String mensajePensionesAlimenticias;
	
	private String mensajeErrorFAT;
	
	// Bandera para deshabilitar boton de continuar por ifi inactiva y para mostrar mensaje de ifi inactiva 
	private boolean verificaIfiInactiva = false;
	
	private Referencia referencia;
	
	private String cargaFamiliar = "";
	
	private ConozcaClienteBacking conozcaCliente;
	
	private String mensajeCreditoFocalizado;
	
	@PostConstruct
	public void init() {
		// En caso que haya bloqueo de fin de semana y no haya seleccionado un producto focalizado redirecciona a la
		// pagina de bloqueo de fin de semana
		if ((!CategoriaTipoProductoPq.CAT_FOCALIZADOS.equals(datos.getCategoriaTipoProductoPq())
				&& TiposProductosPq.ECU_TUR != this.datos.getTiposProductosPq()) && pqFocalizados.getCodigoProductoMeer() == null
				&& (datos.isBloqueoFinesSemana()) &&  TiposProductosPq.DINAMICO != this.datos.getTiposProductosPq()) {
			try {
				getResponse().sendRedirect(getContextPath() + "/pages/concesion/bloqueoFinSemana.jsf");
			} catch (IOException e) {
				log.error("Error al redireccionar a bloqueo de fin de semana", e);
			}
		} else if (datos.getCategoriaTipoProductoPq().equals(CategoriaTipoProductoPq.CAT_NORMALES)) {
			if (!"1".equals(this.datos.getSolicitante().getCuentaBancaria().getInstitucionFinanciera().getEmpleadoEsIfi())) {
				verificaIfiInactiva = true;
			}
		} else if (datos.getCategoriaTipoProductoPq().equals(CategoriaTipoProductoPq.CAT_SERVICIOS)
				&& !datos.getTiposProductosPq().equals(TiposProductosPq.PEN)) {
			InstitucionFinanciera institucionFinanciera = institucionFinancieraServicio
					.getInstitucionFinanciera(this.datos.getInstitucionFinancieraProveedor().getInstitucionId());
			if (!institucionFinanciera.getEmpleadoEsIfi().equals("1")) {
				verificaIfiInactiva = true;
			}
		} else {
			verificaIfiInactiva = false;
		}
		
		if (datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_FOCALIZADOS) {
			int numeroDiasCaducidad = 0;
			try {
				numeroDiasCaducidad = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.DIAS_CADUCIDAD.getIdParametro(),
						ParametrosBiessEnum.DIAS_CADUCIDAD.getNombreParametro()).getValorNumerico().intValue();
			} catch (ParametroBiessException e) {
				log.error("Error al obtener parametro de base de datos para pq focalizados", e);
			}

			this.mensajeCreditoFocalizado = String.format(super.getResource("messages", "nota.ifi.dias"), this.datos.getProveedor().getPrNombre(),
					numeroDiasCaducidad);
		}
		
	}

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	public String registrarCuentaBancaria() {

		String id = datos.getSolicitante().getDatosPersonales().getCedula();
		String strEnlaceACtaBancaria = null;
		id = id == null ? "1802363711" : id;
		// Aqui se capturaria el ID del afiliado

		Encriptacion e;
		try {
			e = EncriptacionFactory.getInstance();
			// id = e.encodeWithDateToken("1802363711");
			id = e.encodeWithDateToken(id);
			/**
			 * Ricardo Tituaña: INC-5780 se actualizo el tipo de correo de "iess.gov.ec" a "iess.gob.ec"
			 */
			strEnlaceACtaBancaria = "http://www.iess.gob.ec/cuentasBancarias/cb.do" + id + "&reqCode=inicio";

			HttpServletResponse response = getHttpServletResponse();
			// datos.cleadDatos();
			response.sendRedirect(strEnlaceACtaBancaria);

		} catch (EncriptacionFactoryException e1) {
			e1.printStackTrace();
		} catch (EncriptacionException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		return null;
	}

	// acciones de la aplicacion

	private boolean datosActualizadosHoy() {
		boolean result = false;
		Date hoy = Calendar.getInstance().getTime();
		Date actualizacion = datos.getSolicitante().getDatosPersonales().getFechaUltimaActualizacion();
		if (null == actualizacion) {
			Calendar tmpdate = Calendar.getInstance();
			tmpdate.add(Calendar.DAY_OF_MONTH, -1);
			actualizacion = tmpdate.getTime();
		}
		if (Utilities.compareDatesWithoutTime(actualizacion, hoy) >= 0) {
			result = true;
		}

		return result;
	}

	public void confirmarDiscapacidad() {
		if (!datos.getDiscapacitado().booleanValue()) {
			return;
		}
		String cedula = datos.getSolicitante().getDatosPersonales().getCedula();
		try {
			if (datosPersonalesAfiliadoBiessServicio.validarDiscapacitado(cedula, super.getIpUser(), AplicativoEnum.PQ_WEB)) {
				infoDiscapacitado = getResource("messages", "info.ok.discapacitado");
			} else {
				errorDiscapacitado = getResource("errores", "error.discapacitado");
				datos.setDiscapacitado(new Boolean(false));
			}
		} catch (RegistroCivilBiessException e) {
			String msg = getResource("errores", "error.rc.discapacitado");
			log.error(msg + ". CI:" + cedula, e);
			errorDiscapacitado = msg;
			datos.setDiscapacitado(new Boolean(false));
		} catch (Exception e) {
			String msg = "Error al confirmar informaci\u00F3n de discapacidad.";
			log.error(msg + ". CI:" + cedula, e);
			errorDiscapacitado = msg;
			datos.setDiscapacitado(new Boolean(false));
		}
	}

	public String getRequisitos() {

		this.requisitos = "<p align='left'><strong>NOTA IMPORTANTE:</strong></p> "
				+ "	Una vez que haya terminado de registrar su cuenta bancaria, por"
				+ "	favor acudir a las oficinas m&aacute;s cercanas del IESS con los"
				+ "	siguientes documentos <br/>"
				+ "	<ul>"
				+ "	<li><b>Original y copia de la c&eacute;dula de ciudadan&iacute;a actualizada.</b></li>"
				+ "	<li><b>Certificaci&oacute;n a la fecha del banco que indique que"
				+ "	la cuenta esta activa</b></li>"
				+ "	<li><b>Copia de la papeleta de votaci&oacute;n de las &uacute;ltimas elecciones o documento que justifique su abstenci&oacute;n o el que acredite haber cumplido la sanci&oacute;n impuesta</b></li>"
				+ "	</ul>";

		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @param errorDiscapacitado
	 *            the errorDiscapacitado to set
	 */
	public void setErrorDiscapacitado(String errorDiscapacitado) {
		this.errorDiscapacitado = errorDiscapacitado;
	}

	/**
	 * @return the errorDiscapacitado
	 */
	public String getErrorDiscapacitado() {
		return errorDiscapacitado;
	}

	/**
	 * @param infoDiscapacitado
	 *            the infoDiscapacitado to set
	 */
	public void setInfoDiscapacitado(String infoDiscapacitado) {
		this.infoDiscapacitado = infoDiscapacitado;
	}

	/**
	 * @return the infoDiscapacitado
	 */
	public String getInfoDiscapacitado() {
		return infoDiscapacitado;
	}

	/**
	 * Metodo que envia una alerta al usuario cuando se ha generado una liquidacion de credito.
	 * 
	 */
	private void enviarAlertaActualizacionDatos() {
		try {
			String templatePath = "ec/fin/biess/creditos/pq/alertas/templates/email/actdatos.ftl";
			/* Parametros del mensaje */
			Map<String, Object> alertData = new HashMap<String, Object>();
			alertData.put("msg_usuario", datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			alertData.put("msg_fecha", AlertUtil.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss"));
			String email_new = datos.getSolicitante().getDatosPersonales().getEmail().replace(" ", "").trim();
			String email_old = datos.getEmailDb();
			/* Tambien se envia alerta al antiguo e-mail si ha sido actualizado */
			if (null != email_old && !email_old.isEmpty() && email_old.compareToIgnoreCase(email_new) != 0) {
				InformacionAfiliado dpold = new InformacionAfiliado();
				dpold.setCedula(datos.getSolicitante().getDatosPersonales().getCedula());
				dpold.setEmail(email_old);
				AlertUtil.enviarAlerta(alertUserHelper, dpold, templatePath, alertData, null, AlertType.EMAIL);
			}
			InformacionAfiliado informacionAfiliado = new InformacionAfiliado();
			informacionAfiliado.setApellidosNombres(datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			informacionAfiliado.setCedula(datos.getSolicitante().getDatosPersonales().getCedula());
			informacionAfiliado.setCelular(datos.getSolicitante().getDatosPersonales().getCelular());
			informacionAfiliado.setEmail(datos.getSolicitante().getDatosPersonales().getEmail());
			AlertUtil.enviarAlerta(alertUserHelper, informacionAfiliado, templatePath,
					alertData, null, AlertType.EMAIL);
		} catch (Exception e) {
			log.error("Error al enviar alerta de actualizacion de datos al afiliado: "
					+ datos.getSolicitante().getDatosPersonales().getCedula(), e);
		}
	}

	/**
	 * Accion aceptar continuar.
	 * 
	 * @return
	 */
	public String aceptarContinuar() {
		datos.setMostrarBotonNovacion(false);
		datos.setMostrarBotonConcesion(false);
		
		// INC-2460 Conozca su cliente
		// La variable se setea en RolesBean en cargarSolicitante()
		if (!conozcaCliente.registroActualizado()) {// Si el registro no esta actualizado
			try {
				getResponse().sendRedirect(getContextPath() + "/pages/conozcasucliente/informativo.jsf");
			} catch (IOException e) {
				log.error("Error al redireccionar a credito PQ");
			}
		}

		if (datos.isPagoPensionesAlimenticias()) {
			// Validar Datos Requeridos - Beneficiario Patria Potestad
			if (cargarDatosBeneficiarioCredito()) {
				mensajePensionesAlimenticias = null;
			} else {
				mensajePensionesAlimenticias = "Ingrese la informaci\u00F3n requerida del Ciudadana/o con Patria Potestad.";
				return "";
			}
		}

		// INC-2135 Pensiones Jubilados/Pensionistas.
		this.obtenerPrestaciones();
		
		return "aceptar";
	}
	
	/**
	 * Redirecciona prestamos focalizados
	 * 
	 * @return
	 */
	public String redireccionaFocalizado() {
		if (this.pqFocalizados.isAgregarOllas()) {
			this.ollasFocalizados.buscarOllasPorEstablecimientoYPV();
			return "ollas";
		} else {
			this.ollasFocalizados.setOllaSeleccionada(null);
			this.ollasFocalizados.setOllaInformacion(null);
			return this.aceptarContinuar();
		}
	}
	
	/**
	 * Valida los datos del beneficiario del credito.
	 * 
	 * @return true todo bien, caso contrario false.
	 */
	private boolean validarDatosBeneficiarioCredito() {

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
	 * Carga los detalles del beneficiario.
	 */
	private boolean cargarDatosBeneficiarioCredito() {

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
			} catch (Exception e) {
				this.mensajePensionesAlimenticias = "Problemas al cargar informacion del Beneficiario con Patria Potestad.";
				log.error("Problemas al cargar informacion del Beneficiario con Patria Potestad.", e);
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * Accion aceptar continuar.
	 * 
	 * @return
	 */
	public String aceptarContinuarNovacion() {
		datos.setMostrarBotonNovacion(false);
		datos.setMostrarBotonConcesion(false);
		
		// INC-2460 Conozca su cliente
		// La variable se setea en RolesBean en cargarSolicitante()
		if (!conozcaCliente.registroActualizado()) {// Si el registro no esta actualizado
			return null;
		}

		if (datos.isPagoPensionesAlimenticias()) {
			// Validar Datos Requeridos - Beneficiario Patria Potestad
			if (cargarDatosBeneficiarioCredito()) {
				mensajePensionesAlimenticias = null;
			} else {
				mensajePensionesAlimenticias = "Ingrese la informaci\u00F3n requerida del Ciudadana/o con Patria Potestad.";
				return "";
			}
		}

		// INC-2135 Pensiones Jubilados/Pensionistas
		this.obtenerPrestaciones();
		return "aceptarNovacion";
	}

	/**
	 * Obtiene las prestaciones del jubilado o prestamista: consume web service de pensiones.
	 * 
	 * @return lista de pensiones.
	 */
	private void obtenerPrestaciones() {

		// INC-2135 Pensiones Jubilados/Pensionistas: Cuando el usuario es un jubilado se carga sus ingresos y egresos
		// de un web service del IESS
		log.info("datos.getSolicitante().getTipo(): " + datos.getSolicitante().getTipo());
		if (TipoPrestamista.JUBILADO.compareTo(datos.getSolicitante().getTipo()) == 0) {

			try {
				this.datos.setMensajeValorNegativo(null);
				this.datos.setInformacionIessServicio(this.informacionIessServicio.obtenerInformacion(this.datos.getInformacionIessServicio(),
						TipoInformacionServicioIessEnum.PRESTACIONES));
				this.datos.setMensajePrestacionJubilado(null);
				if (this.datos.getInformacionIessServicio().getInformacionPrestacionPensionado().getValorAComprometer()
						.compareTo(BigDecimal.ZERO) < 0) {
					this.datos.setMensajeValorNegativo("No tiene valor disponible de su pensi\u00F3n.");
				}
				completarInformacionJubilado();
			} catch (Exception e) {
				String mensaje = MensajesUtil.getErrorMessage(FacesContext.getCurrentInstance(), "credito.error.aplicativo");
				this.datos.setMensajePrestacionJubilado(mensaje);
				log.error("Error al obtener valor de prestaciones", e);
			}
		}
	}

	private void completarInformacionJubilado() {
		datos.getSolicitante().setRentaTotalNomina(
				datos.getInformacionIessServicio().getInformacionPrestacionPensionado().getValorAComprometer().doubleValue());
	}

	/**
	 * Obtiene los datos del beneficiario, consumiendo el ws de registro civil.
	 * 
	 * @param event
	 *            - evento.
	 */
	public void obtenerDatosBeneficiarioRegistroCivil(ActionEvent event) {

		// INC-2272 Pension Alimenticia.
		mensajeErrorRegCivil = null;

		datos.getBeneficiarioCredito().setBeneficiario("");

		if (CedulaUtil.validarCedula(datos.getBeneficiarioCredito().getNumeroBeneficiario(), "inputTextPACedula")) {

			RegistroCivil registroCivil = null;
			try {
				registroCivil = registroCivilServicio.consultarRegistroCivil(datos.getBeneficiarioCredito()
						.getNumeroBeneficiario());

				// Verificar informacion obtenida del WS
				if (null == registroCivil || null == registroCivil.getNomper()
						|| "" == registroCivil.getNomper().trim()) {
					log.error("Error al obtener el nombre del beneficiario del registro civil.");
					throw new RegistroCivilException("Error al obtener el nombre del beneficiario del registro civil.");
				} else {
					datos.getBeneficiarioCredito().setBeneficiario(registroCivil.getNomper());
				}

			} catch (RegistroCivilException e) {
				mensajeErrorRegCivil = "NO EXISTEN DATOS EN REGISTRO CIVIL: ".concat(datos.getBeneficiarioCredito()
						.getNumeroBeneficiario());
			} catch (NullPointerException e) {
				mensajeErrorRegCivil = "NO EXISTEN DATOS EN REGISTRO CIVIL: ".concat(datos.getBeneficiarioCredito()
						.getNumeroBeneficiario());
			}
		}
	}

	/**
	 * Setea el combo de ciudades cuando cambia la provincia.
	 */
	public void cambiarProvinciaPA() {
		// INC-2272 Pension Alimenticia.
		CombosDireccionBean d = (CombosDireccionBean) getRequest().getAttribute("combosDireccion");
		d.setIdProvinciaPA(this.datos.getBeneficiarioCredito().getProvinciaJuicio());
		d.cargarProvincias();
		d.cargarCiudadesPensionesAlimenticias();
	}

	/**
	 * Setea las propiedades para imprimir el formulario de autorizacion de transferencias.
	 */
	public void imprimirFAT() {
		// INC-2272 Pension Alimenticia.
		this.desplegarFAT = false;
		if (datos.getTiposProductosPq() == TiposProductosPq.PEN) {
			if (this.cargarDatosBeneficiarioCredito()) {
				this.desplegarFAT = true;
				this.obtenerTextoFAT();
			}
			// INC-2286 Ferrocarriles
		} else if (datos.getCategoriaTipoProductoPq() == CategoriaTipoProductoPq.CAT_SERVICIOS) {
			this.obtenerTextoFAT();
			this.desplegarFAT = true;
		}
	}

	/**
	 * Obtiene el texto para el formulario de autorizacion de transferencias.
	 * 
	 * @return texto formulario.
	 */
	public String obtenerTextoFAT() {
		// INC-2272 Pension Alimenticia.
		try {
			/* Obtener template */
			String templatePath = "";
			/* Parametros del mensaje */
			Map<String, Object> parametros = new HashMap<String, Object>();
			// Comunes
			SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
			parametros.put("param_fecha", sdf.format(new Date()));
			parametros.put("param_solicitante", this.datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			parametros.put("param_solicitante_cedula", this.datos.getSolicitante().getDatosPersonales().getCedula());

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
				// INC-2286 Ferrocarriles
			} else if (datos.getTiposProductosPq() == TiposProductosPq.TREN) {
				templatePath = "ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferenciaFEEP.ftl";
				parametros.put("param_tipo_cuenta", this.datos.getInstitucionFinancieraProveedor().getTipoCuenta());
				parametros.put("param_numero_cuenta", this.datos.getInstitucionFinancieraProveedor().getNumeroCuenta());
				parametros.put("param_banco", this.datos.getInstitucionFinancieraProveedor().getInstitucion());
			}
			Configuration cfg = new Configuration();
			/* Cargar el template */
			cfg.setClassForTemplateLoading(AlertUtil.class, "/");
			Template template = cfg.getTemplate(templatePath);

			if (null == parametros || null == template) {
				throw new Exception(
						"Error al imprimir el Formulario de Autorizaci\u00f3n de Transferencias. El template y/o el map de datos son nulos.");
			}

			try {
				Writer out = new StringWriter();
				template.process(parametros, out);
				out.flush();
				this.textoFAT = out.toString();
			} catch (TemplateException e) {
				throw new Exception(e);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			mensajeErrorFAT = e.getMessage();
		}

		return textoFAT;
	}

	/**
	 * Deshabilita el boton aceptar.
	 * 
	 * @return
	 */
	public boolean isDisableBotonAceptar() {
		// Deshabilita el boton de continuar si es un prestamo de pensiones alimenticias y no se ha validado los datos del beneficiario
		// o si la cuenta bancaria del prestamo pertenece a una IFI que ya no se encuentra activa
		if ((this.datos.isPagoPensionesAlimenticias() && !this.validarDatosBeneficiarioCredito())
				|| isVerificaIfiInactiva()) {
			return true;
		}

		return false;
	}

	/**
	 * Habilita o deshabilita el combo de profesiones.
	 */
	public void habilitarDeshabilitarProfesiones() {
		if (!NivelEstudioEnum.FORMACION_INTERMEDIA.getId().equals(
				this.getDatos().getSolicitante().getCliente().getNivelEstudio())
				&& !NivelEstudioEnum.POSTGRADO.getId().equals(
						this.getDatos().getSolicitante().getCliente().getNivelEstudio())
				&& !NivelEstudioEnum.UNIVERSITARIA.getId().equals(
						this.getDatos().getSolicitante().getCliente().getNivelEstudio())) {
			this.getDatos().getSolicitante().getCliente().setProfesion(null);
		}
		datosPersonalesAfiliadoBiessServicio.mantenerSesionActiva();
	}

	/**
	 * Habilita o deshabilita de campos valor vivienda y tiempo de residencia.
	 */
	public void habilitarDeshabilitarValorViviendaAniosResidencia() {
		if (!TipoViviendaEnum.PROPIA_HIPOTECADA.getId().equals(
				this.getDatos().getSolicitante().getCliente().getTipoVivienda())
				&& !TipoViviendaEnum.PROPIA_NO_HIPOTECADA.getId().equals(
						this.getDatos().getSolicitante().getCliente().getTipoVivienda())) {
			this.getDatos().getSolicitante().getCliente().setValorVivienda(null);
			this.getDatos().getSolicitante().getCliente().setTiempoResidencia(null);
		}
		datosPersonalesAfiliadoBiessServicio.mantenerSesionActiva();
	}

	/**
	 * Permite tener activa la sesión mientras se esta ingresando los datos complementarios.
	 */
	public void mantenerSesionActiva() {
		datosPersonalesAfiliadoBiessServicio.mantenerSesionActiva();
	}

	/**
	 * @return the mensajeErrorRegCivil
	 */
	public String getMensajeErrorRegCivil() {
		return mensajeErrorRegCivil;
	}

	/**
	 * @param mensajeErrorRegCivil
	 *            the mensajeErrorRegCivil to set
	 */
	public void setMensajeErrorRegCivil(String mensajeErrorRegCivil) {
		this.mensajeErrorRegCivil = mensajeErrorRegCivil;
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
	public void setTextoFAT(String textoFAT) {
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
	public void setDesplegarFAT(boolean desplegarFAT) {
		this.desplegarFAT = desplegarFAT;
	}

	/**
	 * @return the mensajePensionesAlimenticias
	 */
	public String getMensajePensionesAlimenticias() {
		return mensajePensionesAlimenticias;
	}

	/**
	 * @param mensajePensionesAlimenticias
	 *            the mensajePensionesAlimenticias to set
	 */
	public void setMensajePensionesAlimenticias(String mensajePensionesAlimenticias) {
		this.mensajePensionesAlimenticias = mensajePensionesAlimenticias;
	}
	public Referencia getReferencia() {
		if (referencia == null) {
			referencia = new Referencia();
		}
		
		return referencia;
	}

	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}

	public String getCargaFamiliar() {
		return cargaFamiliar;
	}

	public void setCargaFamiliar(String cargaFamiliar) {
		this.cargaFamiliar = cargaFamiliar;
	}

	public ConozcaClienteBacking getConozcaCliente() {
		return conozcaCliente;
	}

	public void setConozcaCliente(ConozcaClienteBacking conozcaCliente) {
		this.conozcaCliente = conozcaCliente;
	}
	
	/**
	 * @return the mensajeErrorFAT
	 */
	public String getMensajeErrorFAT() {
		return mensajeErrorFAT;
	}

	/**
	 * @param mensajeErrorFAT the mensajeErrorFAT to set
	 */
	public void setMensajeErrorFAT(String mensajeErrorFAT) {
		this.mensajeErrorFAT = mensajeErrorFAT;
	}

	public boolean isVerificaIfiInactiva() {
		return verificaIfiInactiva;
	}

	public void setVerificaIfiInactiva(boolean verificaIfiInactiva) {
		this.verificaIfiInactiva = verificaIfiInactiva;
	}

	public PqFocalizadosBacking getPqFocalizados() {
		return pqFocalizados;
	}

	public void setPqFocalizados(PqFocalizadosBacking pqFocalizados) {
		this.pqFocalizados = pqFocalizados;
	}

	public OllasFocalizadosBacking getOllasFocalizados() {
		return ollasFocalizados;
	}

	public void setOllasFocalizados(OllasFocalizadosBacking ollasFocalizados) {
		this.ollasFocalizados = ollasFocalizados;
	}

	public String getMensajeCreditoFocalizado() {
		return mensajeCreditoFocalizado;
	}

	public void setMensajeCreditoFocalizado(String mensajeCreditoFocalizado) {
		this.mensajeCreditoFocalizado = mensajeCreditoFocalizado;
	}

}