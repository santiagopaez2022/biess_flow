/**
 * ConozcaClienteBacking.java
 *
 * Modulo Conozca a su Cliente.
 *
 * Copyright 2014 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 *
 * Todos los derechos reservados.
 */
package ec.gov.iess.pq.concesion.conozcacliente.backing;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.alerts.modelo.InformacionAfiliado;
import ec.fin.biess.conozcasucliente.constant.CatalogoConstante;
import ec.fin.biess.conozcasucliente.dto.ActividadEconomicaSriDto;
import ec.fin.biess.conozcasucliente.exception.ClienteExcepcion;
import ec.fin.biess.conozcasucliente.exception.ServicioPrestadoException;
import ec.fin.biess.conozcasucliente.exception.UnlockException;
import ec.fin.biess.conozcasucliente.modelo.persistence.Cliente;
import ec.fin.biess.conozcasucliente.modelo.persistence.DetalleCliente;
import ec.fin.biess.conozcasucliente.modelo.persistence.ReferenciaBancaria;
import ec.fin.biess.conozcasucliente.modelo.persistence.ReferenciaPersonal;
import ec.fin.biess.conozcasucliente.modelo.persistence.RelacionTrabajo;
import ec.fin.biess.conozcasucliente.modelo.persistence.ServicioPrestado;
import ec.fin.biess.conozcasucliente.modelo.persistence.SolicitanteTipoRol;
import ec.fin.biess.conozcasucliente.sbs.exception.SbsCatalogoExcepcion;
import ec.fin.biess.conozcasucliente.sbs.modelo.persistence.SbsCatalogo;
import ec.fin.biess.conozcasucliente.sbs.service.SbsCatalogoServicioLocal;
import ec.fin.biess.conozcasucliente.service.ActividadEconomicaSRIService;
import ec.fin.biess.conozcasucliente.service.ClienteServicioLocal;
import ec.fin.biess.conozcasucliente.service.HistoriaLaboralServicioLocal;
import ec.fin.biess.conozcasucliente.service.ReferenciaBancariaServicioLocal;
import ec.fin.biess.conozcasucliente.util.EncriptacionUtil;
import ec.fin.biess.matriz.exception.BiessNacionalidadException;
import ec.fin.biess.matriz.modelo.persistence.BiessDivisionPolitica;
import ec.fin.biess.matriz.modelo.persistence.BiessNacionalidad;
import ec.fin.biess.matriz.service.BiessDivisionPoliticaServicioLocal;
import ec.fin.biess.matriz.service.BiessNacionalidadServiceLocal;
import ec.gov.iess.creditos.modelo.dto.DatosPersonales;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;
import ec.gov.iess.cuentabancaria.servicio.InstitucionFinancieraServicio;
import ec.gov.iess.hl.exception.AfiliadoException;
import ec.gov.iess.hl.exception.ImposicionException;
import ec.gov.iess.hl.exception.RegistroCivilException;
import ec.gov.iess.hl.modelo.Afiliado;
import ec.gov.iess.hl.modelo.RegistroCivil;
import ec.gov.iess.hl.servicio.AfiliadoServicio;
import ec.gov.iess.hl.servicio.ImposicionServicio;
import ec.gov.iess.hl.servicio.RegistroCivilServicio;
import ec.gov.iess.pq.concesion.conozcacliente.constant.ConozcaClienteWebConstante;
import ec.gov.iess.pq.concesion.conozcacliente.util.MensajeUtil;
import ec.gov.iess.pq.concesion.web.backing.RolesBean;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.enumeration.ParametrosGeneralesEnum;
import ec.gov.iess.pq.concesion.web.enumeration.UnidadFechaEnum;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.CedulaUtil;
import ec.gov.iess.pq.concesion.web.util.Utilities;
import ec.gov.iess.pq.concesion.web.validator.TelefonoValidator;
import ec.gov.iess.util.EnmascararUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import ec.fin.biess.conozcasucliente.dao.ActividadesEconomicasDao;
/**
 * Clase controlador de la pagina de conozca a su cliente.
 *
 * @author diego.iza
 */

public class ConozcaClienteBacking extends BaseBean implements Serializable {

	private transient static final Logger log = Logger.getLogger(ConozcaClienteBacking.class);

	private static final long serialVersionUID = -412519230877428203L;
	private Cliente cliente;
	private DetalleCliente detalleCliente;
	private boolean aceptaTerminos;
	private boolean skip;

	@EJB(name = "ClienteServicioImpl/local")
	private ClienteServicioLocal clienteService;

	@EJB(name = "BiessDivisionPoliticaServicioImpl/local")
	private BiessDivisionPoliticaServicioLocal divisionPoliticaService;

	@EJB(name = "BiessNacionalidadServiceImpl/local")
	private BiessNacionalidadServiceLocal nacionalidadService;

	@EJB(name = "SbsCatalogoServicioImpl/local")
	private SbsCatalogoServicioLocal catalogoServicioLocal;

	@EJB(name = "AlertUserHelperImpl/local")
	private AlertUserHelper alertUserHelper;

	@EJB(name = "InstitucionFinancieraServicioImpl/local")
	private InstitucionFinancieraServicio institucionFinancieraServicio;

	@EJB(name = "RegistroCivilServicioImpl/local")
	private RegistroCivilServicio registroCivilServicio;

	@EJB(name = "BiessNacionalidadServiceImpl/local")
	private BiessNacionalidadServiceLocal biessNacionalidadService;

	@EJB(name = "ClienteServicioImpl/local")
	private ClienteServicioLocal clienteServicio;

	@EJB(name = "SbsCatalogoServicioImpl/local")
	private SbsCatalogoServicioLocal catalogoServicio;

	@EJB(name = "ImposicionServicioImpl/local")
	private ImposicionServicio imposicionServicio;

	@EJB(name = "HistoriaLaboralServicioImpl/local")
	private HistoriaLaboralServicioLocal historiaLaboralServicio;

	@EJB(name = "ReferenciaBancariaServicioImpl/local")
	private ReferenciaBancariaServicioLocal referenciaBancariaServicio;

	@EJB(name = "ActividadEconomicaSRIServiceImpl/local")
	private ActividadEconomicaSRIService actividadSriService;

	/* Servicio para consultar datos del afiliado */
	@EJB(name = "AfiliadoServicioImpl/local")
	AfiliadoServicio afiliadoServicio;

	@EJB(name = "ActividadesEconomicasDaoImpl/local")
	private ActividadesEconomicasDao actividadEconomicaConsulta;
	
	private String informativo1;
	private String informativo2;
	private String informativo3;
	private String ingresoInfoObligatorio;

	private ArrayList<SelectItem> listaTipoIdentificacion;
	private ArrayList<SelectItem> listaTipoRelacionItems;
	private ArrayList<SelectItem> listaNivelEstudiosItems;
	private ArrayList<SelectItem> listaProfesionItems;
	private ArrayList<SelectItem> listaGeneroItems;
	private ArrayList<SelectItem> listaNacionalidadItems;
	private ArrayList<SelectItem> listaEstadoCivilItems;
	private ArrayList<SelectItem> listaCargaFamiliarItems;
	private ArrayList<SelectItem> listaActividadEconomicaItems;
	private ArrayList<SelectItem> listaRelacionDependenciaLaboralItems;

	private ArrayList<SelectItem> listaTipoViviendaItems;
	private ArrayList<SelectItem> listaParroquiaItems;
	private ArrayList<SelectItem> listaHorarioContactoItems;

	private ArrayList<SelectItem> listaProvinciaItems;
	private ArrayList<SelectItem> listaProvinciaTrabajoItems;
	private ArrayList<SelectItem> listaCantonItems;
	private ArrayList<SelectItem> listaCantonTrabajoItems;
	private ArrayList<SelectItem> listaProvinciaNacimientoItems;
	private ArrayList<SelectItem> listaCantonNacimientoItems;

	private ArrayList<SelectItem> listaReferenciaTipoItems;

	private String institucionBanco;
	private String tipoCuentaSeleccionada;
	private String numeroCuenta;
	private List<ReferenciaBancaria> listaReterenciaBancaria;
	private ArrayList<SelectItem> listaTipoCuentaItems;

	private String relacionSeleccionada;

	private String nombresApellidos;
	private String telefonoRef;
	private String otroNumeroContactoRef;
	private List<ReferenciaPersonal> listaReterenciaPersonal;
	private ArrayList<SelectItem> listaTipoRelacionPersonalItems;

	private ArrayList<SelectItem> listaOrigenIngresosItems;

	private ArrayList<SelectItem> listaOrigenIngresosSecuItems;

	private ArrayList<SelectItem> listaDestinoCreditoItems;

	private String provinciaSelec;
	private String provinciaTrabSelec;
	private String cantonSelec;

	//Datos Nacimiento
	private String provinciaNacimientoSelec;
	private String cantonNacimientoSelec;

	private ArrayList<SelectItem> listaNumCargasS;

	private List<SelectItem> listaTipoPersona;
	private List<SelectItem> listaEstadoMigratorio;

	// Variables para mostrar controles
	private boolean listaNumCargasRendered = true;

	private boolean profesionRendered = true;

	private String mensajeAcceso;

	private String email;

	private String emailAfiliado;

	private String mascaraEmail;

	private String telefonoTrabajo;

	private String telefonoDomicilio;

	private String telefonoCelular;

	private String mascaraTelefonoCelular;

	private String nombreConyuge;

	private String celularConyuge;

	private String emailTrabajo;

	private String mascaraEmailTrabajo;

	private boolean informacionGuardada;

	private boolean mostrarCelularConyugue;

	private String valorAtras = "Atr\u00E1s";

	private String nacionalidadSelect;
	private String nacionalidadConyugeSelect;

	private boolean bloqueaInfoDatosAdicionales;

	private ArrayList<SelectItem> listaIfis;

	private boolean muestraMontoVivienda;

	private ReferenciaBancaria referenciaBancariaSeleccionada;

	private ReferenciaPersonal referenciaPersonalSeleccionada;

	private String tipoIdentificacionConyuge = "C";// Por defecto cedula

	private boolean bloqueaDatosConyuge;

	private int longitudIndenConyuge = 10;

	private DatosBean datos;
	private RolesBean roles;

	private String error;

	private List<ReferenciaBancaria> listaReferenciaBancariaEliminar = new ArrayList<ReferenciaBancaria>();

	private List<ReferenciaPersonal> listaReferenciaPersonalEliminar = new ArrayList<ReferenciaPersonal>();

	private String estiloPaneles = "panelErrores";

	private String etiquetaAgregarModBancaria = "Agregar";
	private String etiquetaAgregarModPersonal = "Agregar";

	// Variable para mostrar mensaje en caso que no exista información de
	// división política
	private boolean mostrarMensajeDivPol;

	private boolean mostrarObligatorio;

	// Mensaje Email
	private String mensajeEmail;

	private boolean actividadEconomicaSrd;

	private boolean habilitarTelefonoTrabajo;

	private String actividadEconomica;

	private String codActividadEconomica;
	
	private String extensionTrabajo;

	private boolean mostrarLugarNac;

	private String NACIONALIDAD_ECU = "239";

	private String msgDivpolParametrizado;

	public ConozcaClienteBacking() {

	}

	@PostConstruct
	public void init() {

		try {
			referenciaBancariaSeleccionada = null;
			habilitarTelefonoTrabajo = false;
			String identificacion = getRemoteUser();
			// Obtiene datos de la persona
			this.inicializarDatos(identificacion, "PQ");

			//Obtiene datos afiliado
			Afiliado afiliado = this.consultarDatosAfiliado(identificacion);
			validarEnmascararMailAfiliado(afiliado);
			this.obtenerMsgDivpolParametrizado();
			this.cliente.setTokenValidacion(null);

			this.listaTipoIdentificacion = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.TIPO_IDENTIFICACION));
			this.listaTipoRelacionItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.TIPO_RELACION_SUJETO));
			this.listaNivelEstudiosItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.NIVEL_ESTUDIOS));
			this.listaProfesionItems = this
					.buildCatalogoSelect(this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.PROFESION));
			this.listaGeneroItems = this
					.buildCatalogoSelect(this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.GENERO));
			this.listaNacionalidadItems = this
					.buildNacionalidadSelect(this.nacionalidadService.obtenerNacionalidades());
			this.listaEstadoCivilItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.ESTADO_CIVIL));
			this.listaCargaFamiliarItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.ESTADO_CIVIL));

			this.listaTipoViviendaItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.TIPO_VIVIENDA));
			this.listaHorarioContactoItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.HORARIO_CONTACTO));

			this.listaActividadEconomicaItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.ACTIVIDAD_SUJETO_DE_CREDITO));
			this.listaRelacionDependenciaLaboralItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.RELACION_DEPENDENCIA_LABORAL));

			this.listaReferenciaTipoItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.REFERENCIA_TIPO));

			this.listaProvinciaItems = this.buildDivPoliticaSelect(this.divisionPoliticaService.obtenerProvincias());
			this.listaProvinciaTrabajoItems = this
					.buildDivPoliticaSelect(this.divisionPoliticaService.obtenerProvincias());
			this.listaProvinciaNacimientoItems = this.
					buildDivPoliticaSelect(this.divisionPoliticaService.obtenerProvincias());

			this.listaEstadoMigratorio = buildListaEstadoMigratorio();

			//Actividad Economica
			if (this.cliente.getActividadEconomicaNombre() == null ){
				//Actividad Economica WS Dinardap SRI
				ActividadEconomicaSriDto actividadEco = actividadSriService.consultarActividadEconomica(identificacion);
				recuperaActividadEconomica(identificacion,actividadEco);
			}

			
			//Provincia de Nacimiento
			this.provinciaNacimientoSelec = this.cliente.getProvinciaNacimiento();
			if (this.provinciaNacimientoSelec == null || this.provinciaNacimientoSelec.equals("")) {
				this.provinciaNacimientoSelec = this.cliente.getCedula().substring(0, 2);
				if (!validarDivisionPolitica(this.listaProvinciaNacimientoItems,
						this.provinciaNacimientoSelec)) {
					this.provinciaNacimientoSelec = this.listaProvinciaNacimientoItems.get(0).getValue().toString();
				}
			}
			this.listaCantonNacimientoItems = this
					.buildDivPoliticaSelect(this.divisionPoliticaService.obtenerCantones(this.provinciaNacimientoSelec));
			this.cantonNacimientoSelec = this.cliente.getCantonNacimiento();
			System.out.println("1 cantonNacimientoSelec: " + cantonNacimientoSelec);
			if (this.cantonNacimientoSelec == null || this.cantonNacimientoSelec.equals("")) {
				this.cantonNacimientoSelec = this.listaCantonNacimientoItems.get(0).getValue().toString();
				System.out.println("2 cantonNacimientoSelec: " + cantonNacimientoSelec);
			}

			this.provinciaSelec = this.cliente.getProvincia();// Obtenemos
			// la
			// provincia del cliente
			if (this.provinciaSelec == null || this.provinciaSelec.equals("")) {// Si
				// no
				// tiene
				// le
				// asignamos
				// el
				// primero
				// para
				// consultar
				this.provinciaSelec = this.listaProvinciaItems.get(0).getValue().toString();
			}
			this.listaCantonItems = this
					.buildDivPoliticaSelect(this.divisionPoliticaService.obtenerCantones(this.provinciaSelec));

			this.cantonSelec = this.cliente.getCanton();
			if (this.cantonSelec == null || this.cantonSelec.equals("")) {
				this.cantonSelec = this.listaCantonItems.get(0).getValue().toString();
			}
			this.listaParroquiaItems = this
					.buildDivPoliticaSelect(this.divisionPoliticaService.obtenerParroquias(this.cantonSelec));

			if (this.cliente.getProvinciaTrabajo() != null) {
				this.provinciaTrabSelec = this.cliente.getProvinciaTrabajo();// Obtenemos
				// la
				// provincia
				// de
				// trab
				// del
				// cliente
			}

			if (this.provinciaTrabSelec == null || this.provinciaTrabSelec.equals("")) {
				this.provinciaTrabSelec = this.listaProvinciaTrabajoItems.get(0).getValue().toString();
			}

			if (this.cliente.getNacionalidad() != null) {
				this.nacionalidadSelect = this.cliente.getNacionalidad().getCodigoNacionalidad();
			}

			if (this.cliente.getNacionalidadConyugue() != null) {
				this.nacionalidadConyugeSelect = this.cliente.getNacionalidadConyugue().getCodigoNacionalidad();
			}

			List<BiessDivisionPolitica> listaDivicion = this.divisionPoliticaService
					.obtenerCantones(this.provinciaTrabSelec);
			this.listaCantonTrabajoItems = this.buildDivPoliticaSelect(listaDivicion);

			if (this.cliente.getCantonTrabajo() == null) {
				this.cliente.setCantonTrabajo(listaDivicion.get(0).getCodigo());
			}

			this.listaReterenciaBancaria = this.cliente.getListaReferenciaBancaria();
			List<SbsCatalogo> tipoCuenta = this.catalogoServicioLocal
					.buscarPorCodigoPadre(CatalogoConstante.TIPO_CUENTA);
			this.listaTipoCuentaItems = this.buildCatalogoSelect(tipoCuenta);
			this.buildListCatalogoRB(listaReterenciaBancaria, tipoCuenta);

			this.listaReterenciaPersonal = this.cliente.getListaReferenciaPersonal();
			List<SbsCatalogo> tipoRelacion = this.catalogoServicioLocal
					.buscarPorCodigoPadre(CatalogoConstante.TIPO_RELACION_PERSONAL);
			this.listaTipoRelacionPersonalItems = this.buildCatalogoTipoReferencia(tipoRelacion);
			this.buildListCatalogoRP(listaReterenciaPersonal, tipoRelacion);

			this.listaOrigenIngresosItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.ORIGEN_DE_INGRESOS));

			this.listaOrigenIngresosSecuItems = this.buildCatalogoSecundariosSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.ORIGEN_DE_INGRESOS));

			this.listaDestinoCreditoItems = this.buildCatalogoSelect(
					this.catalogoServicioLocal.buscarPorCodigoPadre(CatalogoConstante.DESTINO_CREDITO_PQ));

			this.email = null;
			this.mascaraEmail = null;
			if (afiliado != null) {
				this.emailAfiliado = afiliado.getMaiafi();
				if (mensajeEmail == null) {
					//this.email = this.cliente.getEmail();
					this.mascaraEmail = EnmascararUtil.enmascararMail(3, null, this.emailAfiliado);
					this.email = this.emailAfiliado;
				} else {
					this.mascaraEmail = this.emailAfiliado;
				}
			}
			this.telefonoTrabajo = this.cliente.getTelefonoTrabajo();
			this.telefonoDomicilio = this.cliente.getTelefonoDomicilio();
			this.extensionTrabajo = this.cliente.getTrabajoExtension();
			//this.telefonoCelular = this.cliente.getCelular();
			this.telefonoCelular = null;
			this.mascaraTelefonoCelular = null;
			if (afiliado != null) {
				if (afiliado.getCelafi() != null) {
					this.telefonoCelular = afiliado.getCelafi();
					this.mascaraTelefonoCelular = EnmascararUtil.enmascararDato(0, 7, this.telefonoCelular);
				}
			}

			this.tipoIdentificacionConyuge = this.cliente.getTipoIdentificacionCon();
			if (this.tipoIdentificacionConyuge == null || this.tipoIdentificacionConyuge.equals("C")) { // Si
				// es
				// cedula
				this.tipoIdentificacionConyuge = "C";// le seteamos cedula
				this.bloqueaDatosConyuge = true;
			}

			String cedulaConyuge = cliente.getCedulaConyugue();
			// Consulta genero del conyuge
			if(cedulaConyuge!=null) {
			RegistroCivil registroCivil = this.registroCivilServicio.consultarRegistroCivil(cedulaConyuge);
				if (registroCivil != null) {
					this.cliente.setGeneroConyuge(
							this.getCodigoCatalogoSBS(CatalogoConstante.GENERO_LEY, 
									(registroCivil.getCodGeneroLey()!=null)?registroCivil.getCodGeneroLey().toString():registroCivil.getGenper()));
					this.nombreConyuge = registroCivil.getNomper();
				}
			}
			this.mostrarCelularConyugue = true;
			if (cedulaConyuge != null) {
				Afiliado conyuge = this.consultarDatosAfiliado(cedulaConyuge);
				if (conyuge != null) {
					if (conyuge.getCelafi() != null) {
						cliente.setCelularConyugue(conyuge.getCelafi());
					}
					mostrarCelularConyugue = false;
				}
			}
			this.celularConyuge = this.cliente.getCelularConyugue();
			this.emailTrabajo = this.cliente.getEmailTrabajo();
			if (this.emailTrabajo != null) {
				this.mascaraEmailTrabajo = EnmascararUtil.enmascararMail(3, null, this.emailTrabajo);
			}

			String listaProfesion = this.cliente.getNivelEstudio();
			if (listaProfesion == null || listaProfesion.equals("N") || "P".equals(listaProfesion)
					|| "S".equals(listaProfesion)) {// Si es sin estudios,
				// primaria o secundaria
				profesionRendered = false;
			}

			this.changeOrigenSecundarios();
			this.loadNumCargas();
			this.buildListaIfis();
			String tipoVivienda = this.cliente.getTipoVivienda();
			if (tipoVivienda == null || tipoVivienda.equals("P") || tipoVivienda.equals("N")) {// (P)Propia
				// hipotecada
				// (N)Propia
				// no
				// hipotecada
				this.muestraMontoVivienda = true;
				if (this.cliente.getValorVivienda() == null) {
					this.cliente.setValorVivienda(new BigDecimal(0));
				}
			}

			// Verifica si existe información en la tabla KSPCOTAFILIADOS en el campo CODDIVPOL
			if (datos != null && datos.getSolicitante() != null && datos.getSolicitante().getDatosPersonales() != null
					&& datos.getSolicitante().getDatosPersonales().getParroquiaId() == null
					|| datos.getSolicitante().getDatosPersonales().getParroquiaId().isEmpty()) {
				mostrarMensajeDivPol = true;
				estiloPaneles = "panelErrores";
			}

			// Se ingresa informacion de tipo de persona para extranjeros (Natural, Juridica)
			if (listaTipoPersona == null) {
				listaTipoPersona = new ArrayList<SelectItem>();
				SelectItem itemNatural = new SelectItem("N", "Natural");
				SelectItem itemJuridica = new SelectItem("J", "Jur\u00EDdica");
				listaTipoPersona.add(new SelectItem(null, "Seleccione"));
				listaTipoPersona.add(itemNatural);
				listaTipoPersona.add(itemJuridica);
			}

			if (detalleCliente.getEstadoMigratorio() != null) {
				mostrarCampoObligatorio();
			}

		} catch (Exception e) {
			this.mensajeAcceso = e.getMessage();
			return;
		}
	}

	private String convertirCharacters(String input) {

		input = input.replaceAll("&#193;","\u00C1");
		input = input.replaceAll("&#201;","\u00C9");
		input = input.replaceAll("&#205;","\u00CD");
		input = input.replaceAll("&#211;","\u00D3");
		input = input.replaceAll("&#218;","\u00DA");
		input = input.replaceAll("&#209;","\u00D1");

		return input;
	}

	/**
	 * Obtiene valores de la URL
	 *
	 * @param parametro
	 * @return
	 */
	public String obtenerParametroUrl(String parametro) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return params.get(parametro);
	}

	/**
	 * Desencripta la cadena
	 *
	 * @param cadena
	 * @return
	 * @throws UnlockException
	 */
	public String desencriptarCadena(String cadena) throws UnlockException {
		if (cadena == null || cadena.trim().length() <= 0) {
			throw new UnlockException("El valor de la cadena a desencriptar es un valor requerido.");
		}
		return EncriptacionUtil.desencriptarCadena(cadena);
	}

	/**
	 * Redirecciona la pagina de informativo de conozca a su cliente
	 * @return
	 */
	public String redireccionarExtranjeros() {
		if (datos.isBeneficiarioRefugiado()) {
			return "extranjeros";
		} else {
			return "next";
		}
	}

	/**
	 * Regresa desde la pagina de datos personales a la pagina informativo o extranjeros
	 *
	 * @return
	 */
	public String regresarDatosPersonales() {
		if (datos.isBeneficiarioRefugiado()) {
			return "extranjeros";
		} else {
			return "previous";
		}
	}

	/**
	 * Inicializa los datos del cliente
	 *
	 * @param cedulaAfiliado
	 * @param aplicacion
	 */
	private void inicializarDatos(String cedulaAfiliado, String aplicacion) throws Exception {
		/**
		 * Obtener informacion registrada.
		 */
		this.cliente = this.obtenerDatosAfiliado(cedulaAfiliado);
		if (cliente != null && cliente.getDetalleCliente() != null) {
			this.detalleCliente = cliente.getDetalleCliente();
		}

		if (cliente != null) {
			if (cliente.getNacionalidad() == null) {
				RegistroCivil registroCivil = this.registroCivilServicio.consultarRegistroCivil(cedulaAfiliado);
				BiessNacionalidad nacionalidad = this.biessNacionalidadService
						.obtenerNacionalidad(registroCivil.getNacper());
				cliente.setNacionalidad(nacionalidad);
			}
		}

		DatosPersonales datosPerso = this.datos.getSolicitante().getDatosPersonales();

		if (detalleCliente == null && datos.isBeneficiarioRefugiado()) {
			detalleCliente = new DetalleCliente();
		}

		if (this.cliente == null || this.cliente.getIdCliente() == null) {
			this.cliente = new Cliente();

			this.cliente.setCedula(cedulaAfiliado);
			this.cliente.setFechaNacimiento(datosPerso.getFechaNacimiento());
			this.cliente.setNombre(datosPerso.getApellidosNombres());
			this.cliente.setTipoIdentificacion("C");// Siempre Cedula C,la otra
			// opcion pasaporte P
			RegistroCivil registroCivil = this.registroCivilServicio.consultarRegistroCivil(cedulaAfiliado);
			if (registroCivil != null) {
				BiessNacionalidad nacionalidad = this.biessNacionalidadService
						.obtenerNacionalidad(registroCivil.getNacper());
				this.cliente.setNacionalidad(nacionalidad);
				this.cliente.setNacionalidadConyugue(nacionalidad);
			}

			this.cliente.setEmail(datosPerso.getEmail());

			String telefonoTrab = datosPerso.getTelefonoTrabajo();
			if (telefonoTrab != null && telefonoTrab.length() > 12) {
				telefonoTrab = telefonoTrab.substring(0, 12);
			}
			this.cliente.setTelefonoTrabajo(telefonoTrab);

			String celular = datosPerso.getCelular();
			if (celular != null && celular.length() > 12) {
				celular = celular.substring(0, 12);
			}
			this.cliente.setCelular(celular);

			this.cliente.setCedulaConyugue(datosPerso.getCedulaConyuge());
			this.cliente.setNombreConyugue(datosPerso.getNombreConyuge());
			this.cliente.setFechaNacimientoConyugue(datosPerso.getFechaNacimientoConyuge());

			this.cliente.setProvincia(datosPerso.getProvinciaId());// usan el
			// mismo
			// catalogo
			// mat_divisionespoliticas_tbl
			this.cliente.setCanton(datosPerso.getCantonId());// usan el mismo
			// catalogo
			// mat_divisionespoliticas_tbl
			this.cliente.setParroquia(datosPerso.getParroquiaId());// usan el
			// mismo
			// catalogo
			// mat_divisionespoliticas_tbl

			String telefonoDom = datosPerso.getTelefono().trim();
			if (telefonoDom != null && telefonoDom.length() > 10) {
				telefonoDom = telefonoDom.substring(0, 10);
			}
			this.cliente.setTelefonoDomicilio(telefonoDom);

			this.cliente.setPatrimonio(new BigDecimal(0));
			this.cliente.setTotalIngreso(new BigDecimal(0));
			this.cliente.setIngresosSecundarios(new BigDecimal(0));
			this.cliente.setTotalGastosMensuales(new BigDecimal(0));
			this.cliente.setTotalActivos(new BigDecimal(0));
			this.cliente.setTotalDeuda(new BigDecimal(0));

			this.cliente.setListaReferenciaBancaria(new ArrayList<ReferenciaBancaria>());
			this.cliente.setListaReferenciaPersonal(new ArrayList<ReferenciaPersonal>());
		}

		this.actualizarIngresosRelacionDep(cedulaAfiliado);

		// Determinamos el tipo de relacion del Sujeto y su relacion de
		// dependencia
		this.actualizarRelacionSujeto(cedulaAfiliado);

		// Consultamos la informacion de su ultimo trabajo
		this.actualizaInformacionUltimoTrab(cedulaAfiliado);

		this.cliente.setEstadoCivil(
				this.getCodigoCatalogoSBS(CatalogoConstante.ESTADO_CIVIL, datosPerso.getEstadoCivilId()));
		this.cliente.setGenero(this.getCodigoCatalogoSBS(CatalogoConstante.GENERO_LEY, datosPerso.getGenero().getCodigo()));

		/**
		 * Actualizar la informacion desde que aplicativo se llama a la pagina
		 * PH o PQ.
		 */
		this.cliente.setAplicativoIngreso(aplicacion);
		this.cliente.setFechaActualizacionPatrimonio(new Date());
		this.cliente.setEstadoRegistro("0");// Le ponemos como no actualizado
		if(!datosPerso.getApellidosNombres().isEmpty()) {
			this.cliente.setNombre(datosPerso.getApellidosNombres());
		}
		
	}

	/**
	 * Consulta el codigo del catalogo carrespondiente al catalogo SBS
	 *
	 * @param codigoPadre
	 * @param codigoIess
	 * @return
	 */
	private String getCodigoCatalogoSBS(String codigoPadre, String codigoIess) {
		String codigoSBS = "";
		SbsCatalogo catalogo;
		try {
			catalogo = this.catalogoServicio.buscarPorCodigoIess(codigoPadre, codigoIess);
			if (catalogo != null) {
				codigoSBS = catalogo.getCodigo();
			}
		} catch (SbsCatalogoExcepcion e) {
			error = "Error al cargar catalogo SBS " + codigoPadre;
			log.error(error);
		}
		return codigoSBS;
	}

	/**
	 * Actualiza los ingresos en relacion de dependencia de un solicitante
	 *
	 * @param cedulaSolicitante
	 */
	private void actualizarIngresosRelacionDep(String cedulaSolicitante) {
		BigDecimal ingresoRelacionDep = new BigDecimal(0);
		try {
			ingresoRelacionDep = this.imposicionServicio.consultarPromedioUltimasImposicionPorNumero(cedulaSolicitante,
					1);
		} catch (ImposicionException e1) {
			this.error = "Error al determinar informacion de ingresos de relacion de dependencia";
			log.error(this.error + " " + e1.toString());
		}

		if (ingresoRelacionDep != null) {
			ingresoRelacionDep = ingresoRelacionDep.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.cliente.setIngresosRelacionDependencia(ingresoRelacionDep);
	}

	/**
	 * Consulta y acttualiza la realcion de dependencia de un solitiante
	 *
	 * @param cedulaSolicitante
	 */
	private void actualizarRelacionSujeto(String cedulaSolicitante) {
		try {
			// Determinamos el tipo de relacion del Sujeto y su relacion de
			// dependencia
			String codTipoPrestamista = "N";// No afiliado
			String codRelacionDep = "N";// No aplica
			SolicitanteTipoRol tipo = this.clienteServicio.determinaRolSolicitante(cedulaSolicitante);
			if (tipo != null) {
				codTipoPrestamista = tipo.getCodigoRol();
				if (codTipoPrestamista.equals("V")) {// Si es voluntario se lo
					// pone como A(Afiliado)
					// debido a que la sbs
					// no maneja voluntario
					codTipoPrestamista = "A";
				}
				codRelacionDep = tipo.getRelacionDependencia();
				System.out.println("actualizarRelacionSujeto codRelacionDep: " + codRelacionDep);
				// Consultamos informacion para determinar informacion del
				// trabajo actual
				RelacionTrabajo relacionTrabajo = tipo.getRelacionTrabajo();
				if (relacionTrabajo != null) {
					this.cliente.setCargoTrabajo(relacionTrabajo.getCargo());
					this.cliente.setProvinciaTrabajo(relacionTrabajo.getCodProvincia());

					this.cliente.setCantonTrabajo(relacionTrabajo.getCodCanton());
					this.cliente.setTelefonoTrabajo(relacionTrabajo.getTelefono());
					this.cliente.setTrabajoCallePrincipal(relacionTrabajo.getDireccion());
					Date fechaIngreso = relacionTrabajo.getFechaIngreso();
					this.cliente.setTiempoUltimoTrabajo(
							(int) Utilities.calcularDiferenciaFechas(fechaIngreso, new Date(), UnidadFechaEnum.MES));

					if (TelefonoValidator.validarTelefono(this.cliente.getTelefonoTrabajo())) {
						habilitarTelefonoTrabajo = true;
					}
				}
			}

			this.cliente.setTipoRelacion(codTipoPrestamista);
			this.cliente.setRelacionDependencia(codRelacionDep);
			validarActividadEconomicaSrd();
			validarNacionalidad();
		} catch (Exception e) {
			this.error = "Error al determinar informacion de relacion de trabajo del solicitante";
			log.error(this.error);
		}
	}

	/**
	 * Consulta y actualiza la informacion del utlimo trabajo de un solicitante
	 *
	 * @param cedulaSolicitante
	 */
	private void actualizaInformacionUltimoTrab(String cedulaSolicitante) {
		try {
			List<ServicioPrestado> serviciosPres = this.historiaLaboralServicio
					.consultaInactivoPorCedula(cedulaSolicitante);
			if (serviciosPres != null && !serviciosPres.isEmpty()) {
				for (ServicioPrestado servicioPres : serviciosPres) {// Si tiene
					// trabajos
					// inactivos
					// Cojemos solo los que tienen datos consistentes
					if (servicioPres.getFecingafi() != null && servicioPres.getFecsalafi() != null) {
						this.cliente.setFechaInicioUltimoTrabajo(servicioPres.getFecingafi());
						this.cliente.setFechaFinUltimoTrabajo(servicioPres.getFecsalafi());
						break;
					}
				}
			}
		} catch (ServicioPrestadoException e1) {
			this.error = "Error al determinar informacion ultimo trabajo solicitante";
			log.error(this.error);
		}

	}

	/**
	 * Obtiene los datos del afiliado registrado en conozca a su cliente.
	 *
	 * @param cedulaAfiliado
	 *            - cedula del afiliado.
	 *
	 * @return datos del cliente obtenidos.
	 *
	 * @throws Exception
	 *             - excepcion.
	 */
	public Cliente obtenerDatosAfiliado(String cedulaAfiliado) throws Exception {

		if (cedulaAfiliado == null || cedulaAfiliado.trim().length() <= 0) {
			return null;
		}

		Map<String, String> filtros = new HashMap<String, String>();

		filtros.put("cedula", cedulaAfiliado);

		Cliente cliente = clienteService.obtenerPorCedulaReferencias(cedulaAfiliado);

		return cliente;
	}

	/**
	 * Habilita el control de numero de cargas familiares
	 *
	 * @param cliente
	 * @return
	 */
	public boolean habilitarNumeroCargasFamiliares(Cliente cliente) {

		if (cliente.getCargasFamiliares() == null || cliente.getCargasFamiliares().trim().length() <= 0
				|| cliente.getCargasFamiliares().equalsIgnoreCase("0")) {
			cliente.setNumeroCargasFamiliares(null);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Llena el combo de numero de cargas
	 */
	public void loadNumCargas() {
		String cargasFami = this.cliente.getCargasFamiliares();
		listaNumCargasS = new ArrayList<SelectItem>();
		for (Integer i = 1; i <= 25; i++) {
			listaNumCargasS.add(new SelectItem(i.toString(), i.toString()));
		}
		if (cargasFami != null && cargasFami.equals("1")) // Si tiene cargas
		// familiares
		{
			this.listaNumCargasRendered = true;
		} else {// No tiene cargas familiares
			this.cliente.setCargasFamiliares("0");
			this.listaNumCargasRendered = false;
		}
	}

	/**
	 * Carga el combo de nivel de estudios
	 */
	public void loadNivelEstudios() {
		String nivelStufios = this.cliente.getNivelEstudio();
		this.profesionRendered = true;
		if (nivelStufios != null && (nivelStufios.equals("N") || nivelStufios.equals("P") || nivelStufios.equals("S"))) // Si
		// no
		// tiene
		// estudios
		{
			this.profesionRendered = false;// Ocultamos el combo de nivel de
			// estudio
		}
	}

	/**
	 * Cuando se modifica el combo
	 */
	public void changeOrigenSecundarios() {
		this.bloqueaInfoDatosAdicionales = true;
		String origenIngresosSecundarios = this.cliente.getOrigenIngresosSecundarios();
		if (origenIngresosSecundarios != null && !origenIngresosSecundarios.equals("0")) // Si
		// se
		// le
		// indica
		// que
		// no
		// tiene
		// ingresos
		{
			this.bloqueaInfoDatosAdicionales = false;
		} else {
			this.cliente.setIngresosSecundarios(new BigDecimal(0));
		}
	}

	/**
	 * Cuando se modifica el combo
	 */
	public void changeEstadoCivil() {
		String estadoCivil = this.cliente.getEstadoCivil();
		if (estadoCivil != null && ("C".equals(estadoCivil) || estadoCivil.equals("U"))) {// Si
			// esta
			// casado
			// o
			// en
			// union
			// libre
			this.tipoIdentificacionConyuge = "C";// Le ponemos por defecto
			// cedula
		} else {
			this.tipoIdentificacionConyuge = null;// Caso contrario este campo
			// es null
		}
	}

	/**
	 * Agrega una referencia bancaria
	 */
	public void agregarReferenciaBancaria() {
		if (this.institucionBanco != null && !this.institucionBanco.trim().equals("")
				&& !this.numeroCuenta.trim().equals("")) {
			ReferenciaBancaria referenciaB = null;
			referenciaB = new ReferenciaBancaria();
			referenciaB.setNombre(this.institucionBanco);
			this.findTipoCuenta();
			referenciaB.setTipoCuenta(this.tipoCuentaSeleccionada);
			referenciaB.setTipoCuentaNombre(this.findTipoCuenta());
			referenciaB.setNumeroCuenta(this.numeroCuenta);
			referenciaB.setCliente(this.cliente);
			referenciaB.setFechaCreacion(new Date());
			this.institucionBanco = "";
			this.numeroCuenta = "";

			if (referenciaBancariaSeleccionada != null) {
				this.listaReferenciaBancariaEliminar.add(referenciaBancariaSeleccionada);
				this.listaReterenciaBancaria.remove(referenciaBancariaSeleccionada);
			}

			this.listaReterenciaBancaria.add(referenciaB);
			referenciaBancariaSeleccionada = null;

		} else {
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.ERROR_REF_BAN_AGREGAR"));
			estiloPaneles = "panelErrores";
		}

		setEtiquetaAgregarModBancaria("Agregar");
	}

	/**
	 * Busca el tipo de cuenta de una referencia bancaria
	 *
	 * @return
	 */
	private String findTipoCuenta() {
		String descripcion = "";
		String codigoCuenta = this.tipoCuentaSeleccionada;
		for (SelectItem cuenta : this.listaTipoCuentaItems) {
			Object codigo = cuenta.getValue();
			if (codigo.equals(codigoCuenta)) {
				descripcion = cuenta.getLabel();
			}
		}
		return descripcion;
	}

	/**
	 * Agrega una referencia personal
	 */
	public void agregarReferenciaPersonal() {
		if (this.relacionSeleccionada != null && !this.relacionSeleccionada.trim().equals("")
				&& !this.nombresApellidos.trim().equals("") && !this.telefonoRef.trim().equals("")
				&& !this.otroNumeroContactoRef.trim().equals("")) {
			ReferenciaPersonal referenciaP = new ReferenciaPersonal();
			this.findRelacion();
			referenciaP.setTipoRelacion(this.relacionSeleccionada);
			referenciaP.setTipoRelacionNombre(this.findRelacion());
			referenciaP.setNombre(this.nombresApellidos);
			referenciaP.setTelefono(this.telefonoRef);
			referenciaP.setCliente(this.cliente);
			referenciaP.setFechaCreacion(new Date());
			referenciaP.setCelular(this.otroNumeroContactoRef);
			this.nombresApellidos = "";
			this.telefonoRef = "";
			this.relacionSeleccionada = "";
			this.otroNumeroContactoRef = "";

			if (referenciaPersonalSeleccionada != null) {
				this.listaReferenciaPersonalEliminar.add(referenciaPersonalSeleccionada);
				this.listaReterenciaPersonal.remove(referenciaPersonalSeleccionada);
			}

			this.listaReterenciaPersonal.add(referenciaP);
			referenciaPersonalSeleccionada = null;
		} else {
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.ERROR_REF_PER_AGREGAR"));
			estiloPaneles = "panelErrores";
		}
		setEtiquetaAgregarModPersonal("Agregar");
	}

	/**
	 * Busca la relacion de la referencia personal
	 *
	 * @return
	 */
	private String findRelacion() {
		String descripcion = "";
		String codigoRelacion = this.relacionSeleccionada;
		for (SelectItem relacion : this.listaTipoRelacionPersonalItems) {
			Object codigo = relacion.getValue();
			if (codigo.equals(codigoRelacion)) {
				descripcion = relacion.getLabel();
			}
		}
		return descripcion;
	}

	/**
	 * Valida referencia bancaria y personal
	 *
	 * @return
	 */
	public boolean validaRefBanPer() {
		boolean valida = true;
		if (this.listaReterenciaBancaria.size() == 0) {
			valida = false;
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.ERROR_REF_BAN"));
			estiloPaneles = "panelErrores";
		}
		if (this.listaReterenciaPersonal.size() == 0) {
			valida = false;
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.ERROR_REF_PER"));
			estiloPaneles = "panelErrores";
		}
		return valida;
	}

	/**
	 * Valida las referencias bancaria y peronales
	 *
	 * @return
	 */
	public String validaReferencias() {
		String validacion = null;
		if (this.validaRefBanPer()) {
			validacion = "next";
		}
		institucionBanco = "";
		numeroCuenta = "";
		relacionSeleccionada = "";
		nombresApellidos = "";
		telefonoRef = "";
		otroNumeroContactoRef = "";
		referenciaBancariaSeleccionada = null;
		referenciaPersonalSeleccionada = null;
		setEtiquetaAgregarModBancaria("Agregar");
		setEtiquetaAgregarModPersonal("Agregar");

		return validacion;
	}

	/**
	 * Valida el formulario de extranjeros
	 *
	 * @return
	 */
	public String validaExtranjeros() {
		// Da error si la fecha de expedicion del pasaporte es anterior a la fecha de nacimiento
		if (detalleCliente.getFechaExpedicionPasaporte().before(cliente.getFechaNacimiento())) {
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("conozcacliente_msg_validacion_exp_pasaporte"));
			estiloPaneles = "panelErrores";
			return null;
		}

		// Da error si la fecha de caducidad del pasaporte es anterior a la fecha de ingreso al pais
		if (detalleCliente.getFechaIngresoPais() != null
				&& detalleCliente.getFechaCaducidadPasaporte().before(detalleCliente.getFechaIngresoPais())) {
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("conozcacliente_msg_validacion_cad_pasaporte"));
			estiloPaneles = "panelErrores";
			return null;
		}

		// Da error si la fecha de ingreso al pais es anterior a la fecha de expedicion del pasaporte y
		// Si el estado migratorio es diferente de Asilo o Refugio (10)
		if (detalleCliente.getFechaIngresoPais() != null && detalleCliente.getFechaIngresoPais().before(detalleCliente.getFechaExpedicionPasaporte())) {
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("conozcacliente_msg_validacion_ingreso_pais"));
			estiloPaneles = "panelErrores";
			return null;
		}

		// Si el estado migratorio es diferente de Asilo o Refugio (10) y la fecha de ingreso al pais es null muestra
		// mensaje de obligatoriedad del campo de fecha de ingreso al pais
		if (!"10".equals(detalleCliente.getEstadoMigratorio()) && detalleCliente.getFechaIngresoPais() == null) {
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("conozcacliente_msg_validacion_ing_pais_obl"));
			estiloPaneles = "panelErrores";
			return null;
		}

		return "next";
	}

	/**
	 * Valida las referencias bancaria y peronales
	 *
	 * @return
	 */
	public String validaPaginaEconomica() {
		String validacion = null;

		if (("C".equals(cliente.getEstadoCivil()) || "U".equals(cliente.getEstadoCivil()))
				&& ("C".equals(tipoIdentificacionConyuge)) && (cliente.getCedulaConyugue() != null)) {
			if (CedulaUtil.esCedulaValida(cliente.getCedulaConyugue(), 24) != 0) {
				MensajeUtil
						.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("conozcacliente_msg_validacion_cedula_invalida"));
				estiloPaneles = "panelErrores";
				return null;
			}
		}

		String estadoCivil = this.cliente.getEstadoCivil();
		if (estadoCivil.equalsIgnoreCase("C") || estadoCivil.equalsIgnoreCase("U")) {// Se
			// valida
			// solo
			// si
			// es
			// Casado
			// o
			// Unio
			// Libre
			Date fechaNac = this.cliente.getFechaNacimientoConyugue();
			Date fechaActual = new Date();
			if (fechaNac != null && fechaActual.after(fechaNac)) {
				validacion = "next";
			} else {
				MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.ERROR_FECHA_CONYUGE"));
				estiloPaneles = "panelErrores";
				return null;
			}
		}

		if ((this.cliente.getFechaInicioUltimoTrabajo() == null && this.cliente.getFechaFinUltimoTrabajo() == null)
				|| (this.cliente.getFechaFinUltimoTrabajo() != null
				&& this.cliente.getFechaFinUltimoTrabajo().before(new Date()))) {
			validacion = "next";
		} else {
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.ERROR_FECHAS_INVALIDAS"));
			estiloPaneles = "panelErrores";
			return null;
		}

		String telefonoTrabajo = cliente.getSinRelDepTelefonoTrabajo();
		if (telefonoTrabajo != null && telefonoTrabajo.trim().length() > 0) {
			TelefonoValidator.validate(telefonoTrabajo, context(),
					"telefono.invalido",false);
			if (context().getMessages().hasNext()) {
				return null;
			}
		}

		if (this.validarRangoFechas()) {
			validacion = "next";
		} else {
			return null;
		}

		if(cliente.getSinRelDepRuc() != null) {
			if (validarRuc(cliente.getSinRelDepRuc())) {
				validacion = "next";
			} else {
				return null;
			}
		}

		return validacion;
	}

	/**
	 * Guarda la informacion del cliente
	 */
	public void guardar() {
		try {
			Long idCliente = this.cliente.getIdCliente();
			if (detalleCliente != null) {
				cliente.setDetalleCliente(detalleCliente);
				// Se establece el campo tipo persona como N (natural) ya que el campo fue retirado por un acta de cambio
				// En caso que a futuro se vuelva a poner el campo se lo deja
				detalleCliente.setTipoPersona("N");
				detalleCliente.setCliente(cliente);
			}
			if (this.aceptaTerminos) {
				if (idCliente != null) {
					this.actualizarDatosCliente();
				} else {
					this.insertarDatosCliente();
				}
				this.informacionGuardada = true;
				datos.setFechaUltimaActualizacion(cliente.getFechaActualizacion());
				roles.setCliente(this.cliente);
				MensajeUtil.addMensajeInfoWeb(MensajeUtil.getMensajeWeb("msg.guardado_exito"));
				this.enviarMailAfiliado("ec/gov/iess/pq/concesion/conozcacliente/templates/actualizacionDatos.ftl");
				estiloPaneles = "panelExitoso";
			} else {
				MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.acepta_terminos"));
				estiloPaneles = "panelErrores";
			}
		} catch (Exception e) {
			log.error("Error al guardar informacion de conozca su cliente", e);
			this.cliente.setEstadoRegistro("0");
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.guardado_error"));
			estiloPaneles = "panelErrores";
		}
	}

	/**
	 * Envia un mail al afiliado al guardar la informacion
	 */
	private void enviarMailAfiliado(String templatePath) {
		try {

			InformacionAfiliado dp = new InformacionAfiliado();
			dp.setCedula(this.cliente.getCedula());
			dp.setApellidosNombres(this.cliente.getNombre());
			dp.setEmail(this.cliente.getEmail());
			/* Obtener template */
			/* Parametros del mensaje */
			Map<String, Object> alertData = new HashMap<String, Object>();
			alertData.put("msg_usuario", dp.getApellidosNombres());
			alertData.put("msg_fecha", MensajeUtil.getFormatedDate(new Date(), "dd/MM/yyyy HH:mm:ss"));
			alertData.put("msg_aplicativo", "PR&#201;STAMOS QUIROGRAFARIOS");

			freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_NONE);

			Configuration cfg = new Configuration();
			/* Cargar el template */
			cfg.setClassForTemplateLoading(ConozcaClienteBacking.class, "/");
			Template template = cfg.getTemplate(templatePath);

			alertUserHelper.sendAlert(dp, "Notificaci\u00F3n Banco del IESS", template, alertData, null,
					AlertType.EMAIL);
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	/**
	 * Actualiza los datos del cliente
	 *
	 * @throws ClienteExcepcion
	 */
	private void actualizarDatosCliente() throws ClienteExcepcion {
		this.buildCliente();
		this.cliente = this.clienteService.actualizaEliminarRefBancariaPersonal(this.cliente, listaReferenciaBancariaEliminar,
				listaReferenciaPersonalEliminar, super.getIpUser());

		this.listaReterenciaBancaria = this.cliente.getListaReferenciaBancaria();
		this.listaReterenciaPersonal = this.cliente.getListaReferenciaPersonal();

		try {
			List<SbsCatalogo> tipoCuenta = this.catalogoServicioLocal
					.buscarPorCodigoPadre(CatalogoConstante.TIPO_CUENTA);
			this.buildListCatalogoRB(this.listaReterenciaBancaria, tipoCuenta);

			List<SbsCatalogo> tipoRelacion = this.catalogoServicioLocal
					.buscarPorCodigoPadre(CatalogoConstante.TIPO_RELACION_PERSONAL);
			this.buildListCatalogoRP(this.listaReterenciaPersonal, tipoRelacion);
		} catch (SbsCatalogoExcepcion e) {
			log.error("Error al cargar referencias en actualizar cliente", e);
		}
		// referenciaBancariaServicio.eliminarListaReferenciaBancarias(listaReferenciaBancariaEliminar);
	}

	/**
	 * Inserta los datos del cliente
	 *
	 * @throws ClienteExcepcion
	 */
	private void insertarDatosCliente() throws ClienteExcepcion {
		this.buildCliente();
		this.clienteService.registrar(this.cliente, super.getIpUser());
	}

	/**
	 * Construye la informacion del cliente
	 */
	private void buildCliente() {
		this.cliente.setListaReferenciaBancaria(this.listaReterenciaBancaria);
		this.cliente.setListaReferenciaPersonal(this.listaReterenciaPersonal);
		this.cliente.setEstadoRegistro("1");// Le ponemos como registro
		// actualizado
		try {
			this.cliente.setProvincia(this.provinciaSelec);
			this.cliente.setCanton(this.cantonSelec);
			this.cliente.setProvinciaTrabajo(this.provinciaTrabSelec);
			this.cliente.setProvinciaNacimiento(this.provinciaNacimientoSelec);
			this.cliente.setCantonNacimiento(this.cantonNacimientoSelec);
		} catch (Exception e) {
			log.error(e.toString());
		}

		try {
			this.cliente.setNacionalidad(this.nacionalidadService.obtenerNacionalidad(this.nacionalidadSelect));
		} catch (BiessNacionalidadException e) {
			log.error("Error al construir nacionalidad", e);
		}

		if (this.mensajeEmail == null) {
			this.cliente.setEmail(this.email);
		}
		this.cliente.setEmailTrabajo(this.emailTrabajo);
		this.cliente.setTelefonoTrabajo(this.telefonoTrabajo);
		this.cliente.setTrabajoExtension(this.extensionTrabajo);
		this.cliente.setTelefonoDomicilio(this.telefonoDomicilio);
		if (this.telefonoCelular != null) {
			this.cliente.setCelular(this.telefonoCelular);
		}
		this.cliente.setTipoIdentificacionCon(this.tipoIdentificacionConyuge);

		String estadoCivil = this.cliente.getEstadoCivil();
		if (estadoCivil != null && (estadoCivil.equalsIgnoreCase("C") || estadoCivil.equalsIgnoreCase("U"))) {
			this.cliente.setNombreConyugue(this.nombreConyuge);
			this.cliente.setCelularConyugue(this.celularConyuge);
			try {
				this.cliente.setNacionalidadConyugue(
						this.nacionalidadService.obtenerNacionalidad(this.nacionalidadConyugeSelect));
			} catch (BiessNacionalidadException e) {
				log.error("Error al construir nacionalidad conyuge", e);
			}
		} else {
			// Ponemos null a todos los datos del conyuge debido a que no es ni
			// casado y union libre
			this.cliente.setCedulaConyugue(null);
			this.cliente.setNombreConyugue(null);
			this.cliente.setCelularConyugue(null);
			this.cliente.setNacionalidadConyugue(null);
			this.cliente.setFechaNacimientoConyugue(null);
			this.cliente.setTipoIdentificacionCon(null);
			this.cliente.setEstadoCivilConyuge(null);
			this.cliente.setGeneroConyuge(null);
		}

		// Si tiene cargas familiares y por algun motivo no tiene escogido el
		// numero
		if (this.cliente.getCargasFamiliares().equals("1") && (this.cliente.getNumeroCargasFamiliares() == null
				|| this.cliente.getNumeroCargasFamiliares().equals(""))) {
			this.cliente.setNumeroCargasFamiliares(1);// Seteamos por defecto 1
		}

		if (this.cliente.getIngresosSecundarios() == null) {
			this.cliente.setIngresosSecundarios(new BigDecimal(0));
		}

		BigDecimal totalIngresos = this.cliente.getIngresosRelacionDependencia()
				.add(this.cliente.getIngresosSecundarios());
		this.cliente.setTotalIngreso(totalIngresos);
		BigDecimal patrimonio = this.cliente.getTotalActivos().subtract(this.cliente.getTotalDeuda());
		this.cliente.setPatrimonio(patrimonio);

		this.cliente.setFechaActualizacionPatrimonio(new Date());
		this.cliente.setFechaActualizacion(new Date());

		if (this.cliente.getNivelEstudio() != null && (this.cliente.getNivelEstudio().equals("N")
				|| this.cliente.getNivelEstudio().equals("P") || this.cliente.getNivelEstudio().equals("S"))) {// Si
			// no
			// tiene
			// nivel
			// o
			// es
			// Primaria,
			// Secundaria
			// de estudio
			this.cliente.setProfesion(null);
		}
	}

	public String eventoValidarDireccion() {
		String mensajeErrorDireccion = null;
		//validar direccion
		if (!validarDireccion(cliente.getDireccion())) {
			mensajeErrorDireccion = MensajeUtil.getMensajeWeb("conozcacliente_lbl_direccion_domicilio_calle_principal_error");
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("conozcacliente_lbl_direccion_domicilio_calle_principal_error"));
			estiloPaneles = "panelErrores";
		}

		if (mensajeErrorDireccion == null) {
			return "next";
		} else {
			return null;
		}
	}

	public boolean validarDireccion(String direccion) {
		boolean validacion = true;
		if (direccion.length() < 8 ) {
			validacion = false;
		} else {
			if (Utilities.esNumero(direccion)) {
				validacion = false;
			}
		}
		return validacion;
	}

	/**
	 * Carga combo de cantones para provincia nacimiento
	 */
	public void cargarCantonNacimiento() {
		try {
			this.listaCantonNacimientoItems = this
					.buildDivPoliticaSelect(this.divisionPoliticaService.obtenerCantones(this.provinciaNacimientoSelec));
			this.cantonNacimientoSelec = this.listaCantonNacimientoItems.get(0).getValue().toString();
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	/**
	 * Carga el combo de cantones
	 */
	public void cargarCanton() {
		try {
			this.listaCantonItems = this
					.buildDivPoliticaSelect(this.divisionPoliticaService.obtenerCantones(this.provinciaSelec));
			this.cantonSelec = this.listaCantonItems.get(0).getValue().toString();
			this.cargarParroquia();
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	/**
	 * Carga el combo de parroquias
	 */
	public void cargarParroquia() {
		try {
			this.listaParroquiaItems = this
					.buildDivPoliticaSelect(this.divisionPoliticaService.obtenerParroquias(this.cantonSelec));
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	/**
	 * Canga el combo de canto del trabajo
	 */
	public void cargarCantonTrabajo() {
		try {
			List<BiessDivisionPolitica> listaCantones = this.divisionPoliticaService
					.obtenerCantones(this.provinciaTrabSelec);
			this.listaCantonTrabajoItems = this.buildDivPoliticaSelect(listaCantones);
			if (listaCantones != null && listaCantones.size() > 0) {
				this.cliente.setCantonTrabajo(listaCantones.get(0).getCodigo());
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	/**
	 * Carga la lista de catalogos referecnia bancaria
	 *
	 * @param listaRB
	 * @param tipoCuentasCombo
	 */
	private void buildListCatalogoRB(List<ReferenciaBancaria> listaRB, List<SbsCatalogo> tipoCuentasCombo) {
		for (ReferenciaBancaria refB : listaRB) {
			String codigoCuenta = refB.getTipoCuenta();
			for (SbsCatalogo catalogo : tipoCuentasCombo) {
				if (codigoCuenta.equals(catalogo.getCodigo())) {
					refB.setTipoCuentaNombre(catalogo.getDescripcion());
				}
			}
		}
	}

	/**
	 * Carga la lista de catalogos referecnia personal
	 *
	 * @param listaRP
	 * @param tipoRelacionPersonalCombo
	 */
	private void buildListCatalogoRP(List<ReferenciaPersonal> listaRP, List<SbsCatalogo> tipoRelacionPersonalCombo) {
		for (ReferenciaPersonal refP : listaRP) {
			String codigoRelacion = refP.getTipoRelacion();
			for (SbsCatalogo catalogo : tipoRelacionPersonalCombo) {
				if (codigoRelacion.equals(catalogo.getCodigo())) {
					refP.setTipoRelacionNombre(catalogo.getDescripcion());
				}
			}
		}
	}

	/**
	 * Cuando selecciona una fecha inicial
	 */
	public void onDateSelectIni() {
		try {
			Date fechaFin = this.cliente.getFechaFinUltimoTrabajo();

			if (fechaFin.after(new Date())) {
				MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.ERROR_FECHAS_INVALIDAS"));
				estiloPaneles = "panelErrores";
			}
			this.validarRangoFechas();
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	/**
	 * Cuando selecciona una fecha final
	 */
	public void onDateSelectFin() {
		try {
			Date fechaFin = this.cliente.getFechaFinUltimoTrabajo();

			if (fechaFin.after(new Date())) {
				MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.ERROR_FECHAS_INVALIDAS"));
				estiloPaneles = "panelErrores";
			}
			this.validarRangoFechas();
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	/**
	 *
	 */
	public void onDateFechaConyuge() {
		try {
			Date fechaNac = this.cliente.getFechaNacimientoConyugue();
			if (fechaNac.after(new Date())) {
				MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.ERROR_FECHA_CONYUGE"));
				estiloPaneles = "panelErrores";
			}

		} catch (Exception e) {
			log.error("Error en el cambio de fecha", e);
		}
	}

	/**
	 * Valida el rango de fechas
	 *
	 * @return
	 */
	private boolean validarRangoFechas() {
		boolean validad = true;
		Date fechaFin = this.cliente.getFechaFinUltimoTrabajo();
		if (fechaFin == null && fechaFin == null) {
			return true;
		}

		if (fechaFin.after(new Date())) {
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("msg.ERROR_FECHAS_INVALIDAS"));
			estiloPaneles = "panelErrores";
			validad = false;
		}
		return validad;
	}

	public boolean validarRuc(String ruc) {
		boolean validacion = true;
		if (ruc.length() < 13 ) {
			MensajeUtil.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("conozcacliente_msg_validacion_ruc_invalido"));
			estiloPaneles = "panelErrores";
			validacion = false;
		}
		return validacion;
	}

	/**
	 * Construye el catalogo seleecionado
	 *
	 * @param lista
	 * @return
	 */
	private ArrayList<SelectItem> buildCatalogoSelect(List<SbsCatalogo> lista) {
		ArrayList<SelectItem> listaCatalogo = new ArrayList<SelectItem>();
		for (SbsCatalogo catalogo : lista) {
			SelectItem item = new SelectItem();
			item.setValue(catalogo.getCodigo());
			item.setLabel(catalogo.getDescripcion());
			listaCatalogo.add(item);
		}
		return listaCatalogo;
	}

	/**
	 * Construye el catalogo seleecionado
	 *
	 * @param lista
	 * @return
	 */
	private ArrayList<SelectItem> buildCatalogoTipoReferencia(List<SbsCatalogo> lista) {
		ArrayList<SelectItem> listaCatalogo = new ArrayList<SelectItem>();
		listaCatalogo.add(new SelectItem("", "Seleccione..."));
		for (SbsCatalogo catalogo : lista) {
			SelectItem item = new SelectItem();
			item.setValue(catalogo.getCodigo());
			item.setLabel(catalogo.getDescripcion());
			listaCatalogo.add(item);
		}
		return listaCatalogo;
	}

	/**
	 * @param lista
	 * @return
	 */
	private ArrayList<SelectItem> buildCatalogoSecundariosSelect(List<SbsCatalogo> lista) {
		ArrayList<SelectItem> listaCatalogo = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem();
		item.setValue("0");
		item.setLabel("Ninguno");
		listaCatalogo.add(item);
		for (SbsCatalogo catalogo : lista) {
			item = new SelectItem();
			item.setValue(catalogo.getCodigo());
			item.setLabel(catalogo.getDescripcion());
			listaCatalogo.add(item);
		}
		return listaCatalogo;
	}

	/**
	 * Construye la nacionalidad seleccionada
	 *
	 * @param lista
	 * @return
	 */
	private ArrayList<SelectItem> buildNacionalidadSelect(List<BiessNacionalidad> lista) {
		ArrayList<SelectItem> listaCatalogo = new ArrayList<SelectItem>();
		for (BiessNacionalidad nacionalidad : lista) {
			SelectItem item = new SelectItem();
			item.setValue(nacionalidad.getCodigoNacionalidad());
			item.setLabel(nacionalidad.getDescripcion());
			listaCatalogo.add(item);
		}
		return listaCatalogo;
	}

	/**
	 * Construye la division politica seleccionada
	 *
	 * @param lista
	 * @return
	 */
	private ArrayList<SelectItem> buildDivPoliticaSelect(List<BiessDivisionPolitica> lista) {
		ArrayList<SelectItem> listaCatalogo = new ArrayList<SelectItem>();
		for (BiessDivisionPolitica div : lista) {
			SelectItem item = new SelectItem();
			item.setValue(div.getCodigo());
			item.setLabel(div.getNombreDivisionPolitica());
			listaCatalogo.add(item);
		}
		return listaCatalogo;
	}

	private boolean validarDivisionPolitica(ArrayList<SelectItem> lista, String codigo) {
		boolean respuesta = false;
		for (SelectItem div : lista) {
			if (div.getValue().toString().equals(codigo)) {
				respuesta = true;
				break;
			}
		}
		return respuesta;
	}

	private ArrayList<SelectItem> buildListaIfis() {
		if (listaIfis == null) {
			log.debug("obteniendo ifis");
			List<InstitucionFinanciera> listaIfi = institucionFinancieraServicio.getIfisOrderByDescripcion();
			listaIfis = new ArrayList<SelectItem>();
			listaIfis.add(new SelectItem("", "Seleccione..."));
			for (InstitucionFinanciera ifi : listaIfi) {
				listaIfis.add(new SelectItem(ifi.getDescripcion(), ifi.getDescripcion()));
			}
		}
		return listaIfis;
	}

	/**
	 * Construye un List de SelectItem a partir del Catalogo de la SBS de estado migratorio
	 *
	 * @return
	 * @throws SbsCatalogoExcepcion
	 */
	private List<SelectItem> buildListaEstadoMigratorio() throws SbsCatalogoExcepcion {
		List<SelectItem> listaEstados = new ArrayList<SelectItem>();
		List<SbsCatalogo> listaCatalogoSbs = catalogoServicio.buscarPorCodigoPadre("ESTADOMIGRA");
		listaEstados = new ArrayList<SelectItem>();
		listaEstados.add(new SelectItem("", "Seleccione..."));
		for (SbsCatalogo catalogo : listaCatalogoSbs) {
			listaEstados.add(new SelectItem(catalogo.getCodigo(), catalogo.getDescripcion()));
		}
		return listaEstados;
	}

	/**
	 *
	 */
	public void renderedMontoVivienda() {
		this.muestraMontoVivienda = false;
		this.cliente.setValorVivienda(new BigDecimal(0));
		String tipoVivienda = this.cliente.getTipoVivienda();
		if (tipoVivienda != null && (tipoVivienda.equals("P") || tipoVivienda.equals("N"))) {// (P)Propia
			// hipotecada
			// (N)Propia
			// no
			// hipotecada
			this.muestraMontoVivienda = true;
		}
	}

	public void consultarDatosConyuge() {
		String cedulaConyuge = this.cliente.getCedulaConyugue();
		this.bloqueaDatosConyuge = false;

		if ("C".equals(this.tipoIdentificacionConyuge) && cedulaConyuge != null) {// Solo
			// si
			// cedula
			this.limpiarDatosConyuge();// Solo si es cedula limpiamos
			this.bloqueaDatosConyuge = true;
			if (CedulaUtil.esCedulaValida(cedulaConyuge, 24) == 0) {// Solo si
				// pasa el
				// digito
				// verificador
				try {
					RegistroCivil registroCivil = this.registroCivilServicio.consultarRegistroCivil(cedulaConyuge);
					if (registroCivil != null) {
						this.nombreConyuge = registroCivil.getNomper();
						this.cliente.setFechaNacimientoConyugue(registroCivil.getFecnacper());
						this.nacionalidadConyugeSelect = registroCivil.getNacper();
						this.cliente.setEstadoCivilConyuge(this.getCodigoCatalogoSBS(CatalogoConstante.ESTADO_CIVIL,
								registroCivil.getEstadoCivil().getCodigo()));
						this.cliente.setGeneroConyuge(
								this.getCodigoCatalogoSBS(CatalogoConstante.GENERO_LEY, 
										(registroCivil.getCodGeneroLey()!=null)?registroCivil.getCodGeneroLey().toString():registroCivil.getGenper()));
					}

					Afiliado conyuge = this.consultarDatosAfiliado(cedulaConyuge);
					mostrarCelularConyugue = true;
					if (conyuge != null) {
						String celularConyugue = conyuge.getCelafi();
						cliente.setCelularConyugue(celularConyugue);
						mostrarCelularConyugue = false;
					}

				} catch (RegistroCivilException e) {
					log.error("Error al consultar datos registro civil conyuge", e);
				}
			} else {
				MensajeUtil
						.addMensajeErrorWeb(MensajeUtil.getMensajeWeb("conozcacliente_msg_validacion_cedula_invalida"));
				estiloPaneles = "panelErrores";
			}
		}
	}

	public void changeTipoIdenConyuge() {
		this.cliente.setCedulaConyugue("");
		this.limpiarDatosConyuge();
		this.bloqueaDatosConyuge = false;
		this.longitudIndenConyuge = 20;
		if (tipoIdentificacionConyuge.equals("C")) {
			this.longitudIndenConyuge = 10;
		}
	}

	public void limpiarDatosConyuge() {
		this.nombreConyuge = "";
		this.cliente.setFechaNacimientoConyugue(null);
		this.nacionalidadConyugeSelect = null;
		this.celularConyuge = "";
	}

	/**
	 * Verifica si el registro esta actualizado
	 *
	 * @return
	 */
	public boolean registroActualizado() {
		boolean actualizado = true;
		String cedulaSolicitante = this.datos.getSolicitante().getDatosPersonales().getCedula();
		try {
			cliente = this.clienteServicio.obtenerPorCedulaReferencias(cedulaSolicitante);
		} catch (ClienteExcepcion e) {
			log.error("Error al obtener el cliente", e);
			error = "Error al validar si necesita actualizar info. cliente";
		}
		if (cliente == null || cliente.getEstadoRegistro() == null || cliente.getEstadoRegistro().equals("0")) {
			actualizado = false;
		}
		return actualizado;
	}

	/**
	 * Redirecciona a la página de credito de PQ
	 *
	 * @return
	 */
	public String redireccionaCredito() {
		try {
			getResponse().sendRedirect(getContextPath() + "/pages/concesion/tipoProductos.jsf");
		} catch (IOException e) {
			log.error("Error al redireccionar a credito PQ");
		}
		return "";
	}

	/**
	 * Redirecciona a la página de credito de PQ
	 *
	 * @return
	 */
	public String redireccionaNovacion() {
		try {
			getResponse().sendRedirect(getContextPath() + "/pages/novacion/informacionNovacion.jsf");
		} catch (IOException e) {
			log.error("Error al redireccionar a credito PQ");
		}
		return "";
	}

	/**
	 * INICIO SE AGREGA
	 */

	/**
	 * Obtiene la referencia bancaria seleccionada para modificarla
	 *
	 * @return
	 */
	public String obtenerReferenciaBancaria() {
		numeroCuenta = referenciaBancariaSeleccionada.getNumeroCuenta();
		institucionBanco = referenciaBancariaSeleccionada.getNombre();
		tipoCuentaSeleccionada = referenciaBancariaSeleccionada.getTipoCuenta();
		setEtiquetaAgregarModBancaria("Modificar");
		return "";
	}

	/**
	 * Obtiene la referencia personal seleccionada para modificarla
	 *
	 * @return
	 */
	public String obtenerReferenciaPersonal() {
		relacionSeleccionada = referenciaPersonalSeleccionada.getTipoRelacion();
		nombresApellidos = referenciaPersonalSeleccionada.getNombre();
		telefonoRef = referenciaPersonalSeleccionada.getTelefono();
		System.out.println("obtenerReferenciaPersonal celular: " + referenciaPersonalSeleccionada.getCelular());
		otroNumeroContactoRef = referenciaPersonalSeleccionada.getCelular();
		System.out.println("obtenerReferenciaPersonal otroNumeroContactoRef: " + otroNumeroContactoRef);
		setEtiquetaAgregarModPersonal("Modificar");
		return "";
	}

	/**
	 * FIN SE AGREGA
	 */

	/**
	 * Desbloquea boton guardar
	 *
	 * @return
	 */
	public String validaPaginaIngresosEgresos() {
		// para que siempre se active el boton guardar
		this.informacionGuardada = false;
		this.aceptaTerminos = false;
		return "next";
	}

	/**
	 * Cambia el booleano de mostrarObligatorio cuando el estado migratorio es diferente de refugiado
	 *
	 * @return
	 */
	public String mostrarCampoObligatorio() {
		mostrarObligatorio = false;
		if (!detalleCliente.getEstadoMigratorio().equals("10")) {
			mostrarObligatorio = true;
		}
		return "";
	}

	private Afiliado consultarDatosAfiliado(String cedula) {
		Afiliado afiliado = null;
		try {
			afiliado = afiliadoServicio.consultarDatosAfiliado(cedula);
		} catch (AfiliadoException e) {
			log.error(e.getMessage());
		}
		return afiliado;
	}

	public void	validarEnmascararMailAfiliado(Afiliado afiliado) {
		if (afiliado != null) {
			mensajeEmail = Utilities.reemplazarParametroEnTexto(super.getResource("labels", "conozcacliente_msg_actdat"),
					ParametrosGeneralesEnum.PQW_FORM_DATOS_IESS,
					datos.getMapaParametrosGenerales());
			emailAfiliado = afiliado.getMaiafi();
			if (emailAfiliado != null) {
				if (EnmascararUtil.verificarEmail(emailAfiliado)) {
					mensajeEmail = null;
				}
			}
		}
	}

	public void sumarActivos() {

		BigDecimal cero = BigDecimal.ZERO;
		cliente.setTotalActivos(cero);

		if (cliente.getValorBienesInmuebles() != null) {
			cliente.setTotalActivos(cliente.getTotalActivos().add(cliente.getValorBienesInmuebles()));
		}

		if (cliente.getValorTotalVehiculos() != null) {
			cliente.setTotalActivos(cliente.getTotalActivos().add(cliente.getValorTotalVehiculos()));
		}

		if (cliente.getValorOtrosActivos() != null) {
			cliente.setTotalActivos(cliente.getTotalActivos().add(cliente.getValorOtrosActivos()));
		}

	}

	public void validarActividadEconomicaSrd() {
		this.actividadEconomicaSrd = false;
		System.out.println("validarActividadEconomicaSrd getRelacionDependencia: " + this.cliente.getRelacionDependencia());
		if ("I".equals(this.cliente.getRelacionDependencia())) {
			this.actividadEconomicaSrd = true;
		}
	}

	public void validarNacionalidad() {
		this.mostrarLugarNac = false;
		if (this.cliente.getNacionalidad().getCodigoNacionalidad().equals(NACIONALIDAD_ECU)) {
			this.mostrarLugarNac = true;
		}
	}

	/**
	 * Metodo para reiniciar el estilo de paneles cuando se da clic en Atras de
	 * la pagina de confirmacion
	 *
	 * @param event
	 */
	public void regresarConfirmacion(ActionEvent event) {
		estiloPaneles = "panelErrores";
	}

	public void obtenerMsgDivpolParametrizado() {
		msgDivpolParametrizado = Utilities.reemplazarParametroEnTexto(super.getResource("labels", "conozcacliente_msg_divpol"),
				ParametrosGeneralesEnum.PQW_TRAMITE_IESS,
				datos.getMapaParametrosGenerales());
	}


	/*
	REQ-539
	Metodo para recuperar la actividad economica del WS Dinardap SRI
	y que homologa si es necesario
	Fecha: ago-2021
	RMonge  -addrmj
	*/
	private void recuperaActividadEconomica(String cedulaIdentificacion,ActividadEconomicaSriDto actividad){
	
		if (actividad != null) {
			//Valida el codigo de actividad economica que viene del SRI y homologa si es necesario
			List<String> res = actividadEconomicaConsulta.obtenerActividadEconomica(actividad.getCodigoActividad());
			if (res != null && !res.isEmpty()){				
					if ( res.get(0).equals("0") || res.get(0).equals("2")){
						this.actividadEconomica = this.convertirCharacters(res.get(3));
						this.codActividadEconomica = res.get(2);
						this.cliente.setActividadEconomica(res.get(2));						
						this.cliente.setActividadEconomicaNombre(res.get(3));
						if ( res.get(0).equals("2")){
							//Envía correo por no existir el codigo de actividad que envia la DINARDAP
							enviarMail(cedulaIdentificacion, actividad.getCodigoActividad(), actividad.getDescripcionActividad());
						}
					}
					else{
						log.info("Error en recuperar actividadEconomica: (conozca-su-cliente-ejb-SBS_ACTIVIDADECONOMICA_PRC): "+ res.get(1));
					}
			}
			else{
				log.info("Error en recuperar actividadEconomica: (conozca-su-cliente-ejb-SBS_ACTIVIDADECONOMICA_PRC): No trae datos.");
			}
		}
		else
		{
			log.info("ERROR: actividadEconomica "+  "Llamada WS Act-Economica no devuleve datos");
		}
		log.info("actividadEconomica "+codActividadEconomica+"-"+actividadEconomica );
		
	}
	
	/*
	REQ-539
	Metodo para enviar un email de notificacion cuando la activiad economica que trae de la DINARDAP no existe en nuestro catalogo (SBS_OWNER. Catalogo)
	Fecha: ago-2021
	RMonge  -addrmj
	*/
	private void enviarMail(String identificacion, String codActEconomica, String desActEconomica) {
		try {
			String emailNotificacion = actividadSriService.consultarEmailNotificacion();
			
			if (emailNotificacion != null)
			{
				String templatePath = "ec/gov/iess/pq/concesion/conozcacliente/templates/notificacionActEco.ftl";
				InformacionAfiliado dp = new InformacionAfiliado();
				dp.setCedula(identificacion);
				dp.setApellidosNombres("");
				dp.setEmail(emailNotificacion);
				/* Obtener template */
				/* Parametros del mensaje */
				Map<String, Object> alertData = new HashMap<String, Object>();
				alertData.put("msg_cedula", dp.getCedula());
				alertData.put("msg_aplicativo", "PR&#201;STAMOS QUIROGRAFARIOS");
				alertData.put("msg_fecha", MensajeUtil.getFormatedDate(new Date(), "dd/MM/yyyy HH:mm:ss"));
				alertData.put("msg_acteco",  codActEconomica +": "+ desActEconomica);
				
				freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_NONE);
				 
				Configuration cfg = new Configuration();
				/* Cargar el template */
				cfg.setClassForTemplateLoading(ConozcaClienteBacking.class, "/");
				Template template = cfg.getTemplate(templatePath);
	
				alertUserHelper.sendAlert(dp, "Notificaci\u00F3n No existe Actividad Econ\\u00F3mica - PQ-WEB", template, alertData, null, AlertType.EMAIL);
			}
			else
			{
				log.info("Actividad Economica: No se pudo enviar correo para notificar que no existe la actividad económica: "+ codActEconomica+": "+ desActEconomica);
			}
		} catch (Exception e) {
			log.error("Actividad Economica: No se pudo enviar correo para notificar que no existe la actividad económica: "+ codActEconomica +": "+ desActEconomica +" - " +e.toString());
		}
	}
	
	/**
	 * @return the mensajeAcceso
	 */
	public String getMensajeAcceso() {
		return mensajeAcceso;
	}

	/**
	 * @param mensajeAcceso
	 *            the mensajeAcceso to set
	 */
	public void setMensajeAcceso(String mensajeAcceso) {
		this.mensajeAcceso = mensajeAcceso;
	}

	/**
	 * @return
	 */
	public boolean isProfesionRendered() {
		return profesionRendered;
	}

	/**
	 * @param profesionRendered
	 */
	public void setProfesionRendered(boolean profesionRendered) {
		this.profesionRendered = profesionRendered;
	}

	/**
	 * @return the listaReferenciaTipoItems
	 */
	public ArrayList<SelectItem> getListaReferenciaTipoItems() {
		return listaReferenciaTipoItems;
	}

	/**
	 * @param listaReferenciaTipoItems
	 *            the listaReferenciaTipoItems to set
	 */
	public void setListaReferenciaTipoItems(ArrayList<SelectItem> listaReferenciaTipoItems) {
		this.listaReferenciaTipoItems = listaReferenciaTipoItems;
	}

	/**
	 * @return the informacionGuardada
	 */
	public boolean isInformacionGuardada() {
		return informacionGuardada;
	}

	/**
	 * @param informacionGuardada
	 *            the informacionGuardada to set
	 */
	public void setInformacionGuardada(boolean informacionGuardada) {
		this.informacionGuardada = informacionGuardada;
	}

	/**
	 * @return
	 */
	public boolean isSkip() {
		return skip;
	}

	/**
	 * @param skip
	 */
	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the aceptaTerminos
	 */
	public boolean isAceptaTerminos() {
		return aceptaTerminos;
	}

	/**
	 * @param aceptaTerminos
	 *            the aceptaTerminos to set
	 */
	public void setAceptaTerminos(boolean aceptaTerminos) {
		this.aceptaTerminos = aceptaTerminos;
	}

	/**
	 * @return the listaTipoIdentificacion
	 */
	public ArrayList<SelectItem> getListaTipoIdentificacion() {
		return listaTipoIdentificacion;
	}

	/**
	 * @param listaTipoIdentificacion
	 *            the listaTipoIdentificacion to set
	 */
	public void setListaTipoIdentificacion(ArrayList<SelectItem> listaTipoIdentificacion) {
		this.listaTipoIdentificacion = listaTipoIdentificacion;
	}

	/**
	 * @return the listaTipoRelacionItems
	 */
	public ArrayList<SelectItem> getListaTipoRelacionItems() {
		return listaTipoRelacionItems;
	}

	/**
	 * @param listaTipoRelacionItems
	 *            the listaTipoRelacionItems to set
	 */
	public void setListaTipoRelacionItems(ArrayList<SelectItem> listaTipoRelacionItems) {
		this.listaTipoRelacionItems = listaTipoRelacionItems;
	}

	/**
	 * @return the listaNivelEstudiosItems
	 */
	public ArrayList<SelectItem> getListaNivelEstudiosItems() {
		return listaNivelEstudiosItems;
	}

	/**
	 * @param listaNivelEstudiosItems
	 *            the listaNivelEstudiosItems to set
	 */
	public void setListaNivelEstudiosItems(ArrayList<SelectItem> listaNivelEstudiosItems) {
		this.listaNivelEstudiosItems = listaNivelEstudiosItems;
	}

	/**
	 * @return the listaProfesionItems
	 */
	public ArrayList<SelectItem> getListaProfesionItems() {
		return listaProfesionItems;
	}

	/**
	 * @param listaProfesionItems
	 *            the listaProfesionItems to set
	 */
	public void setListaProfesionItems(ArrayList<SelectItem> listaProfesionItems) {
		this.listaProfesionItems = listaProfesionItems;
	}

	/**
	 * @return the listaGeneroItems
	 */
	public ArrayList<SelectItem> getListaGeneroItems() {
		return listaGeneroItems;
	}

	/**
	 * @param listaGeneroItems
	 *            the listaGeneroItems to set
	 */
	public void setListaGeneroItems(ArrayList<SelectItem> listaGeneroItems) {
		this.listaGeneroItems = listaGeneroItems;
	}

	/**
	 * @return the listaNacionalidadItems
	 */
	public ArrayList<SelectItem> getListaNacionalidadItems() {
		return listaNacionalidadItems;
	}

	/**
	 * @param listaNacionalidadItems
	 *            the listaNacionalidadItems to set
	 */
	public void setListaNacionalidadItems(ArrayList<SelectItem> listaNacionalidadItems) {
		this.listaNacionalidadItems = listaNacionalidadItems;
	}

	/**
	 * @return the listaEstadoCivilItems
	 */
	public ArrayList<SelectItem> getListaEstadoCivilItems() {
		return listaEstadoCivilItems;
	}

	/**
	 * @param listaEstadoCivilItems
	 *            the listaEstadoCivilItems to set
	 */
	public void setListaEstadoCivilItems(ArrayList<SelectItem> listaEstadoCivilItems) {
		this.listaEstadoCivilItems = listaEstadoCivilItems;
	}

	/**
	 * @return the listaCargaFamiliarItems
	 */
	public ArrayList<SelectItem> getListaCargaFamiliarItems() {
		return listaCargaFamiliarItems;
	}

	/**
	 * @param listaCargaFamiliarItems
	 *            the listaCargaFamiliarItems to set
	 */
	public void setListaCargaFamiliarItems(ArrayList<SelectItem> listaCargaFamiliarItems) {
		this.listaCargaFamiliarItems = listaCargaFamiliarItems;
	}

	/**
	 * @return the listaCantonItems
	 */
	public ArrayList<SelectItem> getListaCantonItems() {
		return listaCantonItems;
	}

	/**
	 * @param listaCantonItems
	 *            the listaCantonItems to set
	 */
	public void setListaCantonItems(ArrayList<SelectItem> listaCantonItems) {
		this.listaCantonItems = listaCantonItems;
	}

	/**
	 * @return the listaActividadEconomicaItems
	 */
	public ArrayList<SelectItem> getListaActividadEconomicaItems() {
		return listaActividadEconomicaItems;
	}

	/**
	 * @param listaActividadEconomicaItems
	 *            the listaActividadEconomicaItems to set
	 */
	public void setListaActividadEconomicaItems(ArrayList<SelectItem> listaActividadEconomicaItems) {
		this.listaActividadEconomicaItems = listaActividadEconomicaItems;
	}

	/**
	 * @return the listaRelacionDependenciaLaboralItems
	 */
	public ArrayList<SelectItem> getListaRelacionDependenciaLaboralItems() {
		return listaRelacionDependenciaLaboralItems;
	}

	/**
	 * @param listaRelacionDependenciaLaboralItems
	 *            the listaRelacionDependenciaLaboralItems to set
	 */
	public void setListaRelacionDependenciaLaboralItems(ArrayList<SelectItem> listaRelacionDependenciaLaboralItems) {
		this.listaRelacionDependenciaLaboralItems = listaRelacionDependenciaLaboralItems;
	}

	/**
	 * @return the listaProvinciaItems
	 */
	public ArrayList<SelectItem> getListaProvinciaItems() {
		return listaProvinciaItems;
	}

	/**
	 * @param listaProvinciaItems
	 *            the listaProvinciaItems to set
	 */
	public void setListaProvinciaItems(ArrayList<SelectItem> listaProvinciaItems) {
		this.listaProvinciaItems = listaProvinciaItems;
	}

	/**
	 * @return the listaCantonTrabajoItems
	 */
	public ArrayList<SelectItem> getListaCantonTrabajoItems() {
		return listaCantonTrabajoItems;
	}

	/**
	 * @param listaCantonTrabajoItems
	 *            the listaCantonTrabajoItems to set
	 */
	public void setListaCantonTrabajoItems(ArrayList<SelectItem> listaCantonTrabajoItems) {
		this.listaCantonTrabajoItems = listaCantonTrabajoItems;
	}

	/**
	 * @return the listaProvinciaTrabajoItems
	 */
	public ArrayList<SelectItem> getListaProvinciaTrabajoItems() {
		return listaProvinciaTrabajoItems;
	}

	/**
	 * @param listaProvinciaTrabajoItems
	 *            the listaProvinciaTrabajoItems to set
	 */
	public void setListaProvinciaTrabajoItems(ArrayList<SelectItem> listaProvinciaTrabajoItems) {
		this.listaProvinciaTrabajoItems = listaProvinciaTrabajoItems;
	}

	/**
	 * @return the listaReterenciaBancaria
	 */
	public List<ReferenciaBancaria> getListaReterenciaBancaria() {
		return listaReterenciaBancaria;
	}

	/**
	 * @param listaReterenciaBancaria
	 *            the listaReterenciaBancaria to set
	 */
	public void setListaReterenciaBancaria(List<ReferenciaBancaria> listaReterenciaBancaria) {
		this.listaReterenciaBancaria = listaReterenciaBancaria;
	}

	/**
	 * @return the listaReterenciaPersonal
	 */
	public List<ReferenciaPersonal> getListaReterenciaPersonal() {
		return listaReterenciaPersonal;
	}

	/**
	 * @param listaReterenciaPersonal
	 *            the listaReterenciaPersonal to set
	 */
	public void setListaReterenciaPersonal(List<ReferenciaPersonal> listaReterenciaPersonal) {
		this.listaReterenciaPersonal = listaReterenciaPersonal;
	}

	/**
	 * @return the institucionBanco
	 */
	public String getInstitucionBanco() {
		return institucionBanco;
	}

	/**
	 * @param institucionBanco
	 *            the institucionBanco to set
	 */
	public void setInstitucionBanco(String institucionBanco) {
		this.institucionBanco = institucionBanco;
	}

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the nombresApellidos
	 */
	public String getNombresApellidos() {
		return nombresApellidos;
	}

	/**
	 * @param nombresApellidos
	 *            the nombresApellidos to set
	 */
	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefonoRef() {
		return telefonoRef;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefonoRef(String telefonoRef) {
		this.telefonoRef = telefonoRef;
	}

	/**
	 * @return the listaTipoCuentaItems
	 */
	public ArrayList<SelectItem> getListaTipoCuentaItems() {
		return listaTipoCuentaItems;
	}

	/**
	 * @param listaTipoCuentaItems
	 *            the listaTipoCuentaItems to set
	 */
	public void setListaTipoCuentaItems(ArrayList<SelectItem> listaTipoCuentaItems) {
		this.listaTipoCuentaItems = listaTipoCuentaItems;
	}

	/**
	 * @return the listaTipoRelacionPersonalItems
	 */
	public ArrayList<SelectItem> getListaTipoRelacionPersonalItems() {
		return listaTipoRelacionPersonalItems;
	}

	/**
	 * @param listaTipoRelacionPersonalItems
	 *            the listaTipoRelacionPersonalItems to set
	 */
	public void setListaTipoRelacionPersonalItems(ArrayList<SelectItem> listaTipoRelacionPersonalItems) {
		this.listaTipoRelacionPersonalItems = listaTipoRelacionPersonalItems;
	}

	/**
	 * @return the listaOrigenIngresosItems
	 */
	public ArrayList<SelectItem> getListaOrigenIngresosItems() {
		return listaOrigenIngresosItems;
	}

	/**
	 * @param listaOrigenIngresosItems
	 *            the listaOrigenIngresosItems to set
	 */
	public void setListaOrigenIngresosItems(ArrayList<SelectItem> listaOrigenIngresosItems) {
		this.listaOrigenIngresosItems = listaOrigenIngresosItems;
	}

	/**
	 * @return the listaTipoViviendaItems
	 */
	public ArrayList<SelectItem> getListaTipoViviendaItems() {
		return listaTipoViviendaItems;
	}

	/**
	 * @param listaTipoViviendaItems
	 *            the listaTipoViviendaItems to set
	 */
	public void setListaTipoViviendaItems(ArrayList<SelectItem> listaTipoViviendaItems) {
		this.listaTipoViviendaItems = listaTipoViviendaItems;
	}

	/**
	 * @return the listaParroquiaItems
	 */
	public ArrayList<SelectItem> getListaParroquiaItems() {
		return listaParroquiaItems;
	}

	/**
	 * @param listaParroquiaItems
	 *            the listaParroquiaItems to set
	 */
	public void setListaParroquiaItems(ArrayList<SelectItem> listaParroquiaItems) {
		this.listaParroquiaItems = listaParroquiaItems;
	}

	/**
	 * @return the listaHorarioContactoItems
	 */
	public ArrayList<SelectItem> getListaHorarioContactoItems() {
		return listaHorarioContactoItems;
	}

	/**
	 * @param listaHorarioContactoItems
	 *            the listaHorarioContactoItems to set
	 */
	public void setListaHorarioContactoItems(ArrayList<SelectItem> listaHorarioContactoItems) {
		this.listaHorarioContactoItems = listaHorarioContactoItems;
	}

	/**
	 * @return the provinciaSelec
	 */
	public String getProvinciaSelec() {
		return provinciaSelec;
	}

	/**
	 * @param provinciaSelec
	 *            the provinciaSelec to set
	 */
	public void setProvinciaSelec(String provinciaSelec) {
		this.provinciaSelec = provinciaSelec;
	}

	/**
	 * @return the provinciaTrabSelec
	 */
	public String getProvinciaTrabSelec() {
		return provinciaTrabSelec;
	}

	/**
	 * @param provinciaTrabSelec
	 *            the provinciaTrabSelec to set
	 */
	public void setProvinciaTrabSelec(String provinciaTrabSelec) {
		this.provinciaTrabSelec = provinciaTrabSelec;
	}

	/**
	 * @return the listaDestinoCreditoItems
	 */
	public ArrayList<SelectItem> getListaDestinoCreditoItems() {
		return listaDestinoCreditoItems;
	}

	/**
	 * @param listaDestinoCreditoItems
	 *            the listaDestinoCreditoItems to set
	 */
	public void setListaDestinoCreditoItems(ArrayList<SelectItem> listaDestinoCreditoItems) {
		this.listaDestinoCreditoItems = listaDestinoCreditoItems;
	}

	/**
	 * Obtiene la localizacion para componentes de fechas.
	 *
	 * @return localidad.
	 */
	public String getLocale() {
		return ConozcaClienteWebConstante.LOCALE;

	}

	public ArrayList<SelectItem> getListaNumCargasS() {
		this.loadNumCargas();
		return listaNumCargasS;
	}

	public void setListaNumCargasS(ArrayList<SelectItem> listaNumCargasS) {
		this.listaNumCargasS = listaNumCargasS;
	}

	/**
	 * @return the listaNumCargasRendered
	 */
	public boolean isListaNumCargasRendered() {
		return listaNumCargasRendered;
	}

	/**
	 * @param listaNumCargasRendered
	 *            the listaNumCargasRendered to set
	 */
	public void setListaNumCargasRendered(boolean listaNumCargasRendered) {
		this.listaNumCargasRendered = listaNumCargasRendered;
	}

	/**
	 * @return the cantonSelec
	 */
	public String getCantonSelec() {
		return cantonSelec;
	}

	/**
	 * @param cantonSelec
	 *            the cantonSelec to set
	 */
	public void setCantonSelec(String cantonSelec) {
		this.cantonSelec = cantonSelec;
	}

	/**
	 * @return the informativo1
	 */
	public String getInformativo1() {
		this.informativo1 = MensajeUtil.getMensajeWeb("conozcacliente_msg_datos_mensaje_informativo_1");
		return informativo1;
	}

	/**
	 * @param informativo1
	 *            the informativo1 to set
	 */
	public void setInformativo1(String informativo1) {
		this.informativo1 = informativo1;
	}

	/**
	 * @return the informativo2
	 */
	public String getInformativo2() {
		this.informativo2 = MensajeUtil.getMensajeWeb("conozcacliente_msg_datos_mensaje_informativo_2");
		return this.informativo2;
	}

	/**
	 * @param informativo2
	 *            the informativo2 to set
	 */
	public void setInformativo2(String informativo2) {
		this.informativo2 = informativo2;
	}

	/**
	 * @return the informativo3
	 */
	public String getInformativo3() {
		this.informativo3 = MensajeUtil.getMensajeWeb("conozcacliente_msg_datos_mensaje_informativo_3");
		return this.informativo3;
	}

	public String getIngresoInfoObligatorio() {
		this.ingresoInfoObligatorio = MensajeUtil.getMensajeWeb("conozcacliente_msg_datos_ingreso_informacion");
		return ingresoInfoObligatorio;
	}

	public void setIngresoInfoObligatorio(String ingresoInfoObligatorio) {
		this.ingresoInfoObligatorio = ingresoInfoObligatorio;
	}

	/**
	 * @param informativo3
	 *            the informativo3 to set
	 */
	public void setInformativo3(String informativo3) {
		this.informativo3 = informativo3;
	}

	/**
	 * @return the telefonoTrabajo
	 */
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}

	/**
	 * @param telefonoTrabajo
	 *            the telefonoTrabajo to set
	 */
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	/**
	 * @return the telefonoDomicilio
	 */
	public String getTelefonoDomicilio() {
		return telefonoDomicilio;
	}

	/**
	 * @param telefonoDomicilio
	 *            the telefonoDomicilio to set
	 */
	public void setTelefonoDomicilio(String telefonoDomicilio) {
		this.telefonoDomicilio = telefonoDomicilio;
	}

	/**
	 * @return the telefonoCelular
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	/**
	 * @param telefonoCelular
	 *            the telefonoCelular to set
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	/**
	 * @return the nombreConyuge
	 */
	public String getNombreConyuge() {
		return nombreConyuge;
	}

	/**
	 * @param nombreConyuge
	 *            the nombreConyuge to set
	 */
	public void setNombreConyuge(String nombreConyuge) {
		this.nombreConyuge = nombreConyuge;
	}

	/**
	 * @return the celularConyuge
	 */
	public String getCelularConyuge() {
		return celularConyuge;
	}

	/**
	 * @param celularConyuge
	 *            the celularConyuge to set
	 */
	public void setCelularConyuge(String celularConyuge) {
		this.celularConyuge = celularConyuge;
	}

	/**
	 * @return the tipoCuentaSeleccionada
	 */
	public String getTipoCuentaSeleccionada() {
		return tipoCuentaSeleccionada;
	}

	/**
	 * @param tipoCuentaSeleccionada
	 *            the tipoCuentaSeleccionada to set
	 */
	public void setTipoCuentaSeleccionada(String tipoCuentaSeleccionada) {
		this.tipoCuentaSeleccionada = tipoCuentaSeleccionada;
	}

	/**
	 * @return the relacionSeleccionada
	 */
	public String getRelacionSeleccionada() {
		return relacionSeleccionada;
	}

	/**
	 * @param relacionSeleccionada
	 *            the relacionSeleccionada to set
	 */
	public void setRelacionSeleccionada(String relacionSeleccionada) {
		this.relacionSeleccionada = relacionSeleccionada;
	}

	/**
	 * @return the emailTrabajo
	 */
	public String getEmailTrabajo() {
		return emailTrabajo;
	}

	/**
	 * @param emailTrabajo
	 *            the emailTrabajo to set
	 */
	public void setEmailTrabajo(String emailTrabajo) {
		this.emailTrabajo = emailTrabajo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getValorAtras() {
		return valorAtras;
	}

	/**
	 * @param valorAtras
	 */
	public void setValorAtras(String valorAtras) {
		this.valorAtras = valorAtras;
	}

	/**
	 * @return
	 */
	public String getNacionalidadSelect() {
		return nacionalidadSelect;
	}

	/**
	 * @param nacionalidadSelect
	 */
	public void setNacionalidadSelect(String nacionalidadSelect) {
		this.nacionalidadSelect = nacionalidadSelect;
	}

	/**
	 * @return
	 */
	public String getNacionalidadConyugeSelect() {
		return nacionalidadConyugeSelect;
	}

	/**
	 * @param nacionalidadConyugeSelect
	 */
	public void setNacionalidadConyugeSelect(String nacionalidadConyugeSelect) {
		this.nacionalidadConyugeSelect = nacionalidadConyugeSelect;
	}

	/**
	 * @return
	 */
	public ArrayList<SelectItem> getListaOrigenIngresosSecuItems() {
		return listaOrigenIngresosSecuItems;
	}

	/**
	 * @param listaOrigenIngresosSecuItems
	 */
	public void setListaOrigenIngresosSecuItems(ArrayList<SelectItem> listaOrigenIngresosSecuItems) {
		this.listaOrigenIngresosSecuItems = listaOrigenIngresosSecuItems;
	}

	/**
	 * @return
	 */
	public boolean isBloqueaInfoDatosAdicionales() {
		return bloqueaInfoDatosAdicionales;
	}

	/**
	 * @param bloqueaInfoDatosAdicionales
	 */
	public void setBloqueaInfoDatosAdicionales(boolean bloqueaInfoDatosAdicionales) {
		this.bloqueaInfoDatosAdicionales = bloqueaInfoDatosAdicionales;
	}

	/**
	 * @return the listaIfis
	 */
	public ArrayList<SelectItem> getListaIfis() {
		return listaIfis;
	}

	/**
	 * @param listaIfis
	 *            the listaIfis to set
	 */
	public void setListaIfis(ArrayList<SelectItem> listaIfis) {
		this.listaIfis = listaIfis;
	}

	/**
	 * @return the muestraMontoVivienda
	 */
	public boolean isMuestraMontoVivienda() {
		return muestraMontoVivienda;
	}

	/**
	 * @param muestraMontoVivienda
	 *            the muestraMontoVivienda to set
	 */
	public void setMuestraMontoVivienda(boolean muestraMontoVivienda) {
		this.muestraMontoVivienda = muestraMontoVivienda;
	}

	public ReferenciaBancaria getReferenciaBancariaSeleccionada() {
		return referenciaBancariaSeleccionada;
	}

	public void setReferenciaBancariaSeleccionada(ReferenciaBancaria referenciaBancariaSeleccionada) {
		this.referenciaBancariaSeleccionada = referenciaBancariaSeleccionada;
	}

	/**
	 * @return the tipoIdentificacionConyuge
	 */
	public String getTipoIdentificacionConyuge() {
		return tipoIdentificacionConyuge;
	}

	/**
	 * @param tipoIdentificacionConyuge
	 *            the tipoIdentificacionConyuge to set
	 */
	public void setTipoIdentificacionConyuge(String tipoIdentificacionConyuge) {
		this.tipoIdentificacionConyuge = tipoIdentificacionConyuge;
	}

	/**
	 * @return the bloqueaDatosConyuge
	 */
	public boolean isBloqueaDatosConyuge() {
		return bloqueaDatosConyuge;
	}

	/**
	 * @param bloqueaDatosConyuge
	 *            the bloqueaDatosConyuge to set
	 */
	public void setBloqueaDatosConyuge(boolean bloqueaDatosConyuge) {
		this.bloqueaDatosConyuge = bloqueaDatosConyuge;
	}

	/**
	 * @return the longitudIndenConyuge
	 */
	public int getLongitudIndenConyuge() {
		return longitudIndenConyuge;
	}

	/**
	 * @param longitudIndenConyuge
	 *            the longitudIndenConyuge to set
	 */
	public void setLongitudIndenConyuge(int longitudIndenConyuge) {
		this.longitudIndenConyuge = longitudIndenConyuge;
	}

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<ReferenciaBancaria> getListaReferenciaBancariaEliminar() {
		return listaReferenciaBancariaEliminar;
	}

	public void setListaReferenciaBancariaEliminar(List<ReferenciaBancaria> listaReferenciaBancariaEliminar) {
		this.listaReferenciaBancariaEliminar = listaReferenciaBancariaEliminar;
	}

	public ReferenciaPersonal getReferenciaPersonalSeleccionada() {
		return referenciaPersonalSeleccionada;
	}

	public void setReferenciaPersonalSeleccionada(ReferenciaPersonal referenciaPersonalSeleccionada) {
		this.referenciaPersonalSeleccionada = referenciaPersonalSeleccionada;
	}

	public List<ReferenciaPersonal> getListaReferenciaPersonalEliminar() {
		return listaReferenciaPersonalEliminar;
	}

	public void setListaReferenciaPersonalEliminar(List<ReferenciaPersonal> listaReferenciaPersonalEliminar) {
		this.listaReferenciaPersonalEliminar = listaReferenciaPersonalEliminar;
	}

	public String getEstiloPaneles() {
		return estiloPaneles;
	}

	public void setEstiloPaneles(String estiloPaneles) {
		this.estiloPaneles = estiloPaneles;
	}

	public String getEtiquetaAgregarModBancaria() {
		return etiquetaAgregarModBancaria;
	}

	public void setEtiquetaAgregarModBancaria(String etiquetaAgregarModBancaria) {
		this.etiquetaAgregarModBancaria = etiquetaAgregarModBancaria;
	}

	public String getEtiquetaAgregarModPersonal() {
		return etiquetaAgregarModPersonal;
	}

	public void setEtiquetaAgregarModPersonal(String etiquetaAgregarModPersonal) {
		this.etiquetaAgregarModPersonal = etiquetaAgregarModPersonal;
	}

	public boolean isMostrarMensajeDivPol() {
		return mostrarMensajeDivPol;
	}

	public void setMostrarMensajeDivPol(boolean mostrarMensajeDivPol) {
		this.mostrarMensajeDivPol = mostrarMensajeDivPol;
	}

	public List<SelectItem> getListaTipoPersona() {
		return listaTipoPersona;
	}

	public void setListaTipoPersona(List<SelectItem> listaTipoPersona) {
		this.listaTipoPersona = listaTipoPersona;
	}

	public DetalleCliente getDetalleCliente() {
		return detalleCliente;
	}

	public void setDetalleCliente(DetalleCliente detalleCliente) {
		this.detalleCliente = detalleCliente;
	}

	public List<SelectItem> getListaEstadoMigratorio() {
		return listaEstadoMigratorio;
	}

	public void setListaEstadoMigratorio(List<SelectItem> listaEstadoMigratorio) {
		this.listaEstadoMigratorio = listaEstadoMigratorio;
	}

	public boolean isMostrarObligatorio() {
		return mostrarObligatorio;
	}

	public void setMostrarObligatorio(boolean mostrarObligatorio) {
		this.mostrarObligatorio = mostrarObligatorio;
	}

	public RolesBean getRoles() {
		return roles;
	}

	public void setRoles(RolesBean roles) {
		this.roles = roles;
	}

	public String getMascaraEmail() {
		return mascaraEmail;
	}

	public void setMascaraEmail(String mascaraEmail) {
		this.mascaraEmail = mascaraEmail;
	}

	public String getMascaraEmailTrabajo() {
		return mascaraEmailTrabajo;
	}

	public void setMascaraEmailTrabajo(String mascaraEmailTrabajo) {
		this.mascaraEmailTrabajo = mascaraEmailTrabajo;
	}

	public String getMascaraTelefonoCelular() {
		return mascaraTelefonoCelular;
	}

	public void setMascaraTelefonoCelular(String mascaraTelefonoCelular) {
		this.mascaraTelefonoCelular = mascaraTelefonoCelular;
	}

	public String getEmailAfiliado() {
		return emailAfiliado;
	}

	public void setEmailAfiliado(String emailAfiliado) {
		this.emailAfiliado = emailAfiliado;
	}

	public String getMensajeEmail() {
		return mensajeEmail;
	}

	public void setMensajeEmail(String mensajeEmail) {
		this.mensajeEmail = mensajeEmail;
	}

	public boolean isMostrarCelularConyugue() {
		return mostrarCelularConyugue;
	}

	public void setMostrarCelularConyugue(boolean mostrarCelularConyugue) {
		this.mostrarCelularConyugue = mostrarCelularConyugue;
	}

	public String getOtroNumeroContactoRef() {
		return otroNumeroContactoRef;
	}

	public void setOtroNumeroContactoRef(String otroNumeroContactoRef) {
		this.otroNumeroContactoRef = otroNumeroContactoRef;
	}

	public String getProvinciaNacimientoSelec() {
		return provinciaNacimientoSelec;
	}

	public void setProvinciaNacimientoSelec(String provinciaNacimientoSelec) {
		this.provinciaNacimientoSelec = provinciaNacimientoSelec;
	}

	public String getCantonNacimientoSelec() {
		return cantonNacimientoSelec;
	}

	public void setCantonNacimientoSelec(String cantonNacimientoSelec) {
		this.cantonNacimientoSelec = cantonNacimientoSelec;
	}

	public boolean isActividadEconomicaSrd() {
		return actividadEconomicaSrd;
	}

	public void setActividadEconomicaSrd(boolean actividadEconomicaSrd) {
		this.actividadEconomicaSrd = actividadEconomicaSrd;
	}

	public ArrayList<SelectItem> getListaProvinciaNacimientoItems() {
		return listaProvinciaNacimientoItems;
	}

	public void setListaProvinciaNacimientoItems(
			ArrayList<SelectItem> listaProvinciaNacimientoItems) {
		this.listaProvinciaNacimientoItems = listaProvinciaNacimientoItems;
	}

	public ArrayList<SelectItem> getListaCantonNacimientoItems() {
		return listaCantonNacimientoItems;
	}

	public void setListaCantonNacimientoItems(
			ArrayList<SelectItem> listaCantonNacimientoItems) {
		this.listaCantonNacimientoItems = listaCantonNacimientoItems;
	}

	public boolean isHabilitarTelefonoTrabajo() {
		return habilitarTelefonoTrabajo;
	}

	public void setHabilitarTelefonoTrabajo(boolean habilitarTelefonoTrabajo) {
		this.habilitarTelefonoTrabajo = habilitarTelefonoTrabajo;
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public String getExtensionTrabajo() {
		return extensionTrabajo;
	}

	public void setExtensionTrabajo(String extensionTrabajo) {
		this.extensionTrabajo = extensionTrabajo;
	}

	public boolean isMostrarLugarNac() {
		return mostrarLugarNac;
	}

	public void setMostrarLugarNac(boolean mostrarLugarNac) {
		this.mostrarLugarNac = mostrarLugarNac;
	}

	public String getMsgDivpolParametrizado() {
		return msgDivpolParametrizado;
	}

	public void setMsgDivpolParametrizado(String msgDivpolParametrizado) {
		this.msgDivpolParametrizado = msgDivpolParametrizado;
	}

	public String getCodActividadEconomica() {
		return codActividadEconomica;
	}

	public void setCodActividadEconomica(String codActividadEconomica) {
		this.codActividadEconomica = codActividadEconomica;
	}
}
