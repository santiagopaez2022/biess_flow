/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.conozcasucliente.exception.ClienteExcepcion;
import ec.fin.biess.conozcasucliente.modelo.persistence.Cliente;
import ec.fin.biess.conozcasucliente.service.ClienteServicioLocal;
import ec.fin.biess.creditos.pq.dao.ParametroPQDao;
import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoEmpleadorEnum;
import ec.fin.biess.creditos.pq.excepcion.ConsultaParametroException;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionIessServicioDto;
import ec.fin.biess.creditos.pq.modelo.persistencia.ParametroPQ;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.enumeraciones.AplicativoEnum;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.modelo.persistencia.AccesosAplicativo;
import ec.fin.biess.service.AccesosAplicativoLocalService;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.DatosAfiliadoDao;
import ec.gov.iess.creditos.enumeracion.TipoBeneficiarioCredito;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.ObtenerRolesExcepcion;
import ec.gov.iess.creditos.pq.excepcion.SolicitanteExcepcion;
import ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaParametroServicioLocal;
import ec.gov.iess.creditos.pq.servicio.DefinirRolServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitanteServicio;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.hl.exception.NoTieneRelacionDeDependenciaException;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.hl.exception.SucursalException;
import ec.gov.iess.hl.servicio.AfiliadoServicio;
import ec.gov.iess.hl.servicio.ServicioPrestadoServicio;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.enumeration.ParametrosGeneralesEnum;
import ec.gov.iess.pq.concesion.web.helper.AuditoriaPqWebHelper;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.Utilities;

public class RolesBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 3377801083181320397L;

	private static final LoggerBiess LOG = LoggerBiess.getLogger(RolesBean.class);

	private boolean diaFeriado;
	
	private TipoPrestamista tipoPrestamista = null;

	public boolean getDiaFeriado(){
		return diaFeriado;
	}

	@EJB(name = "SolicitanteServicioImpl/local")
	private SolicitanteServicio solicitanteServicio;

	@EJB(name = "ServicioPrestadoServicioImpl/local")
	ServicioPrestadoServicio servicioPrestado;

	@EJB(name = "ParametroPQDaoEjb/local")
	private ParametroPQDao parametroPQDao;

	@EJB(name = "AfiliadoServicioImpl/local")
	AfiliadoServicio afiliadoServicio;

	// INC-2460 Conozca a su cliente
	@EJB(name = "ClienteServicioImpl/local")
	private ClienteServicioLocal clienteServicio;

	@EJB(name = "DatosAfiliadoDaoImpl/local")
	private DatosAfiliadoDao datosAfiliadoDao;

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;

	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;

	@EJB(name = "ClienteServicioImpl/local")
	private ClienteServicioLocal clienteService;

	@EJB(name = "AccesosAplicativoServiceImpl/local")
	private AccesosAplicativoLocalService accesosAplicativoServicio;

	@EJB(name = "ConsultaParametroServicioImpl/local")
	private ConsultaParametroServicioLocal consultaParametroServicio;
	
	@EJB(name = "ComprobantePagoServicioImpl/local")
	private ComprobantePagoServicio comprobantePagoServicio;
	
	@EJB(name = "DefinirRolServicioImpl/local")
	private DefinirRolServicioLocal definirRolServicio;

	// propiedades del componente

	private List<TipoPrestamista> roles;

	private DatosBean datos;

	private boolean cargado = false;

	private String errorMsg;

	private String radioRoles;

	private boolean habilitarAfi = false;
	private boolean habilitarJub = false;
	private boolean habilitarZafrero = false;

	private boolean habilitarNovacion = false;

	private String lastSignin;

	private Cliente cliente;

	private String mensajeSueldoPromedio;
	private static final  String NUMERO_MESES_PROMEDIO = "PQW_CON_NUMMESESPROM";
	
	private Integer mostrarMensaje;
	private String diaDesdeGeneraComprobante;

	public List<TipoPrestamista> getRoles() {
		return roles;
	}

	public void setRoles(List<TipoPrestamista> roles) {
		this.roles = roles;
	}

	public boolean isCargado() {
		return cargado;
	}

	public void setCargado(boolean cargado) {
		this.cargado = cargado;
	}

	// acciones de la aplicacion

	/**
	 * @return the datos
	 */
	public DatosBean getDatos() {
		return datos;
	}

	/**
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	/**
	 *  Method executed after dependency injection. Use it to perform any initialization.
	 */
	@PostConstruct
	public void init() {		
		/* Obtener informaci?n del ?ltimo acceso al sistema */
		readLastAccess();
		tieneCreditosEnConciliacion(Utilities.getRemoteCI());
		tieneCreditosPhCastigados(Utilities.getRemoteCI());
		/* Guardar el log de ingreso al sistema */
		ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedula(Utilities.getRemoteCI());
		super.guardarAuditoria(OperacionEnum.LOGIN_PQ_WEB, parametroEvento, Utilities.getRemoteCI());
		/* Verificar si el proceso de planillas se esta ejecutando */
		procesoPlanillasRunning();
		/* Verificar si el reajuste de tasas de interes se esta ejecutando */
		reajusteTasasRunning();
		// Obtiene datos de bloqueo de novacion
		obtenerDatosBloqueoNovacion();
		// Verifica si Biess Emergente esta vigente
		estaBiessEmergenteVigente();
		// Verifica si Biess Mujer esta vigente
		estaBiessMujerVigente();
		// Verifica si Biess Mama esta vigente
		estaBiessMamaVigente();
		// Verifica si Biess Mama esta vigente
		estaBiessPapaVigente();
		// Verifica si es dia de bloqueo
		this.esDiaFeriado();
		// Verifica si es fin de semana para bloqueo de paginas
		seteaVariableBloqueoFinesSemana();
		// Obtiene informacion de conozca su cliente
		setearCliente();
		// Oculta mensaje informativo
		mostrarMensaje=0;
		// inicializa día Desde para generación de comprobante
		diaDesdeGeneraComprobante="";
		try {
			Integer numeroPromedio = this.consultaParametroServicio.consultarParametroEntero(NUMERO_MESES_PROMEDIO);
			this.mensajeSueldoPromedio = "Sueldo Promedio " + numeroPromedio + " Meses";

			//REQ-267: Consulta parametros generales
			cargarParametrosGenerales();
		} catch (ConsultaParametroException e) {
			this.errorMsg = "Error al consumir el servicio de par\u00E1metros";
		}
	}

	private void esDiaFeriado() {

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
					this.diaFeriado = true;
				}
			}
			LOG.info("Hoy esta bloqueado: " + this.diaFeriado);
		}
		catch (Exception e){
			LOG.error("Error al determinar dia de bloqueo", e);
		}
	}

	public String initRoles() {
		if (datos.getSolicitante() == null) {
			List<TipoPrestamista> roles = new ArrayList<TipoPrestamista>();
			String mensaje = "Lo sentimos, usted no consta en n\u00F3mina de pensiones. Si usted se jubil\u00F3 en el mes en curso podr\u00E1 solicitar "
					+ "su pr\u00E9stamo quirografario luego de cobrar su primera pensi\u00F3n, caso contrario acercarse a Administraci\u00F3n de Pensiones ";
			try {
				//Verifica si puede realizar novacion
				habilitarNovacion();

				// INc-2148 ==> Se comenta para permitir el acceso a afiliados/jubilados refugiados y extranjeros.
				// Validacion de no permitir acceso a c?dulas ficticias
				// String validarCedula = getCedulaUsuario().substring(0, 1);
				// if (validarCedula.equals("6")) {
				// mensaje = "CEDULA FICTICIA. FAVOR SACAR CEDULA DE IDENTIDAD EN EL REGISTRO CIVIL";
				// throw new ObtenerRolesExcepcion(
				// 		"CEDULA FICTICIA. FAVOR SACAR CEDULA DE IDENTIDAD EN EL REGISTRO CIVIL");
				// }
				LOG.debug("obteniendo roles.");
				roles = solicitanteServicio.obtenerRoles(getCedulaUsuario());
				datos.setRoles(roles);
				LOG.info("total roles:" + roles.size());
				if (roles.size() == 1) {
					LOG.debug("tiene un solo rol");
					// datos.setTipo(roles.get(0));
					LOG.info("Rol: "+ roles.get(0).getEtiqueta());
				} else {
					if (roles.size() == 0) {
						LOG.error("no tiene roles");
					} else {
						LOG.debug("tiene mas de un rol");
					}
				}
				obtenerRoles();

				// Valida el tipo de beneficiario.
				verificarTipoBeneficiario();

			} catch (ObtenerRolesExcepcion e) {
				LOG.error(e);
				setErrorMsg(mensaje);
				/*
				 * ExternalContext ectx =
				 * FacesContext.getCurrentInstance().getExternalContext();
				 * HttpSession session = (HttpSession) ectx.getSession(false);
				 * session.invalidate();
				 */
				return "error_roles";
			}
		}
		return null;
	}

	public String obtenerRoles() {
		if (!datos.isRolesCargados()) {
			String mensaje = "Lo sentimos, usted no consta en n\u00F3mina de pensiones. Si usted se jubil\u00F3 en el mes en curso podr\u00E1 solicitar "
					+ "su pr\u00E9stamo quirografario luego de cobrar su primera pensi\u00F3n, caso contrario acercarse a Administraci\u00F3n de Pensiones ";
			cargado = true;
			if (datos.getRoles().size() == 0) {
				LOG.error("no tiene roles");
				setErrorMsg(mensaje);
			} else {
				for (TipoPrestamista tipo : datos.getRoles()) {
					if (tipo.equals(TipoPrestamista.AFILIADO)) {
						habilitarAfi = true;
						datos.setRolPrestamista(TipoPrestamista.AFILIADO);
					} else if (tipo.equals(TipoPrestamista.JUBILADO)) {
						habilitarJub = true;
						datos.setRolPrestamista(TipoPrestamista.JUBILADO);
					} else if (tipo.equals(TipoPrestamista.ZAFRERO_TEMPORAL)) {
						habilitarZafrero = true;
						datos.setRolPrestamista(TipoPrestamista.ZAFRERO_TEMPORAL);
					}
				}
				// determinar si es cesante desactivar opcion JOSE SANCHEZ
				if (habilitarAfi && habilitarJub) {
					try {
						boolean esActivo = servicioPrestado
								.consultarEsActivoPorCedula(getCedulaUsuario());
						if (esActivo) {
							/* INC-2005. Se consideran creditos segun tipo de rol para asegurados afiliados-jubilados */
							datos.setRolPrestamista(TipoPrestamista.AFILIADO_JUBILADO);
							habilitarAfi = true;
						} else {
							habilitarAfi = false;
						}
					} catch (ServicioPrestadoException e) {
						habilitarAfi = false;
					}
				}

				datos.setRolesCargados(true);
				LOG.debug("tiene mas de un rol");
				roles = datos.getRoles();
			}
		}
		return null;
	}

	public String seleccionaRolAfiliado() {
		datos.setTipo(TipoPrestamista.AFILIADO);
		// Verifica si el usuario es trabajador del hogar (ama de casa)
		esTrabajadorHogar(getCedulaUsuario());
		habilitarJub = false;
		return seleccionaRol();
	}

	public String seleccionaRolJubilado() {
		datos.setTipo(TipoPrestamista.JUBILADO);
		habilitarAfi = false;
		return seleccionaRol();
	}

	public String seleccionaRolZafrero() {
		datos.setTipo(TipoPrestamista.ZAFRERO_TEMPORAL);
		// Verifica si el usuario es trabajador del hogar (ama de casa)
		esTrabajadorHogar(getCedulaUsuario());
		return seleccionaRol();
	}

	public String seleccionaRol() {
		// datos.setTipo(TipoPrestamista.valueOf(getRadioRoles()));
		LOG.debug("seleccionoENUM:" + datos.getTipo());
		try {
			cargarSolicitante();
			mostrarMensajeInformacion();
		} catch (SolicitanteExcepcion e) {
			LOG.error(e);
			LOG.debug("Ingreso el mensaje");
			setErrorMsg(e.getMessage());
			return "errorDatosSolicitante";
		}
		return "volverPrincipal";
	}
	
	/* Permite vaidar habilitacion de componente dependiendo cliente
	 * 
	 * @param documento
	 * @param tipoComponente
	 * @return
	 * @throws PeriodoComprobanteException
	 * @throws ConsultaParametroException 
	 */
	private Integer mostrarMensajeInformacion() {
		mostrarMensaje = 0;		
		try {			
			tipoPrestamista = definirRolServicio.determinaVoluntarioCesante(datos.getSolicitante().getDatosPersonales().getCedula() , datos.getRolPrestamista());
			// Determinamos el empleador del solictante segun su rol y si es emergente
			this.solicitanteServicio.determinarEmpleador(datos.getSolicitante().getDatosPersonales().getCedula(),
					datos.getTipo(), datos.getSolicitante(), this.datos.isProductoBiessEmergente());			
		} catch (final ServicioPrestadoException e) {
			LOG.error("Error", e);
		} catch (final NoTieneRelacionDeDependenciaException e) {
			LOG.error("Error", e);
		} catch (SucursalException e) {
			LOG.error("Error", e);
		} catch (NoServicioPrestadoSucursalException e) {
			LOG.error("Error", e);
		}
		final Calendar fechaGeneracion = Calendar.getInstance();
		// Se determina que tipo de empleador es
		String tipoEmpleador=null;
		tipoEmpleador = obtenerTipoEmpleador();
		try {
			if ((!TipoPrestamista.CESANTE.equals(tipoPrestamista)
					 && !TipoPrestamista.JUBILADO.equals(datos.getRolPrestamista()))) {				
				if (TipoPrestamista.VOLUNTARIO.equals(tipoPrestamista)) {
					mostrarMensaje = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
							this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPVO"))?1:2;
					diaDesdeGeneraComprobante = obtenerDiaDesde(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPVO")));
				}
				else if (datos.getSolicitante().getEmpleador()!=null && datos.getSolicitante().getEmpleador().getTipoEmpresa().equals("3")) {
					mostrarMensaje = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
							this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPUN"))?1:2;
					diaDesdeGeneraComprobante = obtenerDiaDesde(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPUN")));
				}
				else if (TipoPrestamista.AFILIADO_JUBILADO.equals(tipoPrestamista)) {	
					if(datos.getTipo().equals(TipoPrestamista.AFILIADO)) {
						mostrarMensaje = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
								this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPAF"))?1:2;
						diaDesdeGeneraComprobante = obtenerDiaDesde(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPAF")));
					}else {
						mostrarMensaje = this.comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
								this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPJU"))?1:2;
						diaDesdeGeneraComprobante = obtenerDiaDesde(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPJU")));
					}
				} else if (TipoEmpleadorEnum.PRI.getCodigo().equals(tipoEmpleador)) {
					mostrarMensaje = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
							this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPAF"))?1:2;
					diaDesdeGeneraComprobante = obtenerDiaDesde(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPAF")));
				} 
			} else {
				if (TipoPrestamista.JUBILADO.equals(tipoPrestamista) || TipoEmpleadorEnum.JUB.getCodigo().equals(tipoEmpleador)) {
					mostrarMensaje = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
							this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPJU"))?1:2;
					diaDesdeGeneraComprobante = obtenerDiaDesde(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPJU")));
				} else if (TipoPrestamista.CESANTE.equals(tipoPrestamista)) {
					mostrarMensaje = comprobantePagoServicio.habilitaComprobantePago(fechaGeneracion,
							this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPCE"))?1:2;
					diaDesdeGeneraComprobante = obtenerDiaDesde(Utilities.obtenerRangosDias(this.consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABCOMPCE")));
				} 
			}
		} catch (ConsultaParametroException e) {
			LOG.error("Exception ",e);
		}
		return mostrarMensaje;			
	}
	
	/**
	 * Permite obtener el día desde el que puede generar comprobante
	 * @param rangos
	 * @return
	 */
	private static String obtenerDiaDesde(String rangos) {
		String[] valores = rangos.split(":");
		for(int i=0;i<valores.length;i++) {
			if(valores.length==1) {
				return Integer.valueOf(valores[i].substring(0, valores[i].indexOf("-"))).toString();
			}else if(i==1) {				
				return Integer.valueOf(valores[i].substring(0, valores[i].indexOf("-"))).toString();
			}
		}
		return "";
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

	// metodos privados del componenteO

	private void cargarSolicitante() throws SolicitanteExcepcion {

		if (datos.getSolicitante() == null) {
			Solicitante solicitante = datos.getSolicitante();

			LOG.debug("instanciando Solicitante");
			try {
				solicitante = solicitanteServicio.obtenerSolicitanteMasCargas(
						getCedulaUsuario(), datos.getTipo());
			} catch (SolicitanteExcepcion e) {
				LOG.error("Exception", e);
				throw e;
			}

			solicitante.setTipo(datos.getTipo());
			LOG.debug("tipoopo:" + datos.getTipo());

			// calcular actualizado

			// TODO esta bien esto?
			// Verifica en la tabla UAF_COCLIENTES_TBL el campo cc_fecactureg para saber si
			// se ingreso informacion de conozca a su cliente alguna vez.
			// Esta bandera sirve para obligar a ingresar la informacion de conozca
			// la primera vez, y despues aparezca el dialogo de ingreso pero no obligue.
			List<Cliente> clientes = null;
			try {
				clientes = clienteServicio.obtenerPorCedula(Utilities.getRemoteCI());
			} catch (ClienteExcepcion e) {
				LOG.error("fecha ult. act:" + e);
			}
			if (clientes == null || clientes.isEmpty() || clientes.get(0).getFechaActualizacion() == null) {
				LOG.debug("no ha actualizado nunca.");
				solicitante.getDatosPersonales().setDireccionDomicilio("");
				datos.setActualizado(false);
				datos.setFechaUltimaActualizacion(null);
			} else if (clientes.get(0).getFechaActualizacion() != null) {
				// si ha actualizado alguna vez permite avanzar
				datos.setActualizado(true);
				datos.setFechaUltimaActualizacion(clientes.get(0).getFechaActualizacion());
			}

			datos.setSolicitante(solicitante);
			/* INC-1817 Notificaciones asegurados */
			datos.setEmailDb(solicitante.getDatosPersonales().getEmail());

			InformacionIessServicioDto informacionIessServicioDto = new InformacionIessServicioDto();
			informacionIessServicioDto.setCedula(Utilities.getRemoteCI());
			this.datos.setInformacionIessServicio(informacionIessServicioDto);
		}
	}

	private String getCedulaUsuario() {
		String ced = context().getExternalContext().getRemoteUser();
		LOG.debug("ced" + ced);
		datos.setSolicitante(null);
		return ced;
		// return "1802907129";
	}

	/**
	 * Valida que tipo de beneficiario es.
	 */
	private void verificarTipoBeneficiario(){

		// INc-2148 ==> Verifica el tipo de beneficiario.
		if (this.solicitanteServicio.validarCedulaRefugiado(this.getCedulaUsuario())) {
			datos.setTipoBeneficiario(TipoBeneficiarioCredito.REFUGIADO.getId());
			datos.setBeneficiarioRefugiado(true);
		} else{
			datos.setTipoBeneficiario(TipoBeneficiarioCredito.NORMAL.getId());
			datos.setBeneficiarioRefugiado(false);
		}
	}

	/**
	 * Valida que sea ama de casa y si tiene prestamos vigentes
	 */
	private void esTrabajadorHogar(String pCedula) {
		List<String> lista = new ArrayList<String>();
		lista.add("0");
		lista.add("2");
		lista.add("3");
		if (datosAfiliadoDao.esTrabajadorHogar(pCedula)) {
			datos.setEsAmaDeCasa(true);
			if (prestamoServicio.getPrestamosVigentesPorCedula(pCedula, lista).size() > 0) {
				LOG.info("Ama de casa tiene prestamos vigentes " + pCedula);
				datos.setPrestamoVigenteAmaCasa(true);
			} else {
				LOG.info("Ama de casa no tiene prestamos vigentes " + pCedula);
				datos.setPrestamoVigenteAmaCasa(false);
			}
		} else {
			datos.setEsAmaDeCasa(false);
		}

	}

	/**
 * Valida si el usuario que ingreso tiene creditos en conciliacion	
 */
private void tieneCreditosEnConciliacion(final String cedula) {
	BigDecimal totalPrestamo= prestamoServicio.obtenerTotalCreditosConciliacion(cedula);
	LOG.info("totalPrestamosEnEstadoCon:"+totalPrestamo);
	datos.setProcesoConciliacionMig(totalPrestamo.signum()>0);
}
	
	/**
 * valida si tienes creditos castigados PH
 */
private void tieneCreditosPhCastigados(final String cedula) {

	datos.setTieneCreditoCastigado(prestamoServicio.existeUnCreditoCastigadoPh(cedula));
}
	
	/**
	 * Verifica si el producto de Biess Emergente esta vigente
	 */
	private void estaBiessEmergenteVigente() {
		try {

			String fechaVigenciaEmergente = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.FECHA_BIESS_EMERGENTE_VIGENCIA.getIdParametro(),
					BiessEmergenteEnum.FECHA_BIESS_EMERGENTE_VIGENCIA.getNombreParametro()).getValorCaracter();

			BigDecimal montoMaxDesembolsos = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MONTO_MAX_DESEMBOLSO_CREDITOS_PQ.getIdParametro(),
					BiessEmergenteEnum.MONTO_MAX_DESEMBOLSO_CREDITOS_PQ.getNombreParametro()).getValorNumerico();


			BigDecimal montoAcumuladoPQ = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MONTO_ACUMULADO_PQ.getIdParametro(),
					BiessEmergenteEnum.MONTO_ACUMULADO_PQ.getNombreParametro()).getValorNumerico();

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaVigenciaEmergenteDate = null;
			try {
				fechaVigenciaEmergenteDate = formatter.parse(fechaVigenciaEmergente);
			} catch (ParseException e) {
				LOG.info("Error al convertir en Date la fecha de vigencia de Biess Emergente", e);
			}
			Calendar fechaActual = Calendar.getInstance();
			fechaActual.set(Calendar.HOUR_OF_DAY, 0);
			fechaActual.set(Calendar.MINUTE,0);
			fechaActual.set(Calendar.SECOND,0);
			fechaActual.set(Calendar.MILLISECOND,0);

			Calendar calFecVigenciaEmergente = Calendar.getInstance();
			calFecVigenciaEmergente.setTime(fechaVigenciaEmergenteDate);

			datos.setProductoBiessEmergenteVigente(true);
			if (fechaActual.after(calFecVigenciaEmergente)) {
				datos.setProductoBiessEmergenteVigente(false);
			}else {
				datos.setProductoBiessEmergenteVigente(montoAcumuladoPQ.compareTo(montoMaxDesembolsos)<=0);

			}

		} catch (ParametroBiessException e) {
			LOG.info("Error al obtener parametro de fecha de vigencia de Biess Emergente", e);
		}
	}

	/**
	 * Verifica si el producto de Biess Mujer esta vigente
	 */
	private void estaBiessMujerVigente() {
		try {

			String fechaVigenciaProductoMujer = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.FECHA_BIESS_MUJER_VIGENCIA.getIdParametro(),
					BiessEmergenteEnum.FECHA_BIESS_MUJER_VIGENCIA.getNombreParametro()).getValorCaracter();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaVigenciaProductoMujerDate = null;
			fechaVigenciaProductoMujerDate = formatter.parse(fechaVigenciaProductoMujer);
			Calendar fechaActual = Calendar.getInstance();
			fechaActual.set(Calendar.HOUR_OF_DAY, 0);
			fechaActual.set(Calendar.MINUTE,0);
			fechaActual.set(Calendar.SECOND,0);
			fechaActual.set(Calendar.MILLISECOND,0);

			Calendar calFecVigenciaProductoMujer = Calendar.getInstance();
			calFecVigenciaProductoMujer.setTime(fechaVigenciaProductoMujerDate);

			datos.setProductoBiessMujerVigente(true);
			if (fechaActual.after(calFecVigenciaProductoMujer)) {
				datos.setProductoBiessMujerVigente(false);
			}

		} catch (ParametroBiessException e) {
			LOG.info("Error al obtener parametro de fecha de vigencia de Biess Producto Mujer", e);
		} catch (ParseException e) {
			LOG.error("Error al convertir en Date la fecha de vigencia de Biess Producto Mujer", e);
		}
	}

	/**
	 * Verifica si el producto de Biess Mama esta vigente
	 */
	private void estaBiessMamaVigente() {
		try {

			String fechaVigenciaProductoMama = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.FECHA_BIESS_MAMA_VIGENCIA.getIdParametro(),
					BiessEmergenteEnum.FECHA_BIESS_MAMA_VIGENCIA.getNombreParametro()).getValorCaracter();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaVigenciaProductoMamaDate = null;
			fechaVigenciaProductoMamaDate = formatter.parse(fechaVigenciaProductoMama);
			Calendar fechaActual = Calendar.getInstance();
			fechaActual.set(Calendar.HOUR_OF_DAY, 0);
			fechaActual.set(Calendar.MINUTE,0);
			fechaActual.set(Calendar.SECOND,0);
			fechaActual.set(Calendar.MILLISECOND,0);

			Calendar calFecVigenciaProductoMama = Calendar.getInstance();
			calFecVigenciaProductoMama.setTime(fechaVigenciaProductoMamaDate);

			datos.setProductoBiessMamaVigente(true);
			if (fechaActual.after(calFecVigenciaProductoMama)) {
				datos.setProductoBiessMamaVigente(false);
			}

		} catch (ParametroBiessException e) {
			LOG.info("Error al obtener parametro de fecha de vigencia de Biess Producto Mama", e);
		} catch (ParseException e) {
			LOG.error("Error al convertir en Date la fecha de vigencia de Biess Producto Mama", e);
		}
	}

	/**
	 * Verifica si el producto de Biess Mama esta vigente
	 */
	private void estaBiessPapaVigente() {
		try {

			String fechaVigenciaProductoPapa = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.FECHA_BIESS_PAPA_VIGENCIA.getIdParametro(),
					BiessEmergenteEnum.FECHA_BIESS_PAPA_VIGENCIA.getNombreParametro()).getValorCaracter();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaVigenciaProductoPapaDate = null;
			fechaVigenciaProductoPapaDate = formatter.parse(fechaVigenciaProductoPapa);
			Calendar fechaActual = Calendar.getInstance();
			fechaActual.set(Calendar.HOUR_OF_DAY, 0);
			fechaActual.set(Calendar.MINUTE,0);
			fechaActual.set(Calendar.SECOND,0);
			fechaActual.set(Calendar.MILLISECOND,0);

			Calendar calFecVigenciaProductoPapa = Calendar.getInstance();
			calFecVigenciaProductoPapa.setTime(fechaVigenciaProductoPapaDate);

			datos.setProductoBiessPapaVigente(true);
			if (fechaActual.after(calFecVigenciaProductoPapa)) {
				datos.setProductoBiessPapaVigente(false);
			}

		} catch (ParametroBiessException e) {
			LOG.info("Error al obtener parametro de fecha de vigencia de Biess Producto Preferencial", e);
		} catch (ParseException e) {
			LOG.error("Error al convertir en Date la fecha de vigencia de Biess Producto Preferencial", e);
		}
	}

	/**
	 * Setea variable de bean de sesion a true en caso que sea fin de semana
	 * Si se cambia los dias tambien deben cambiarse en BloqueosAccesosFilter.java metodo doFilter()
	 */
	private void seteaVariableBloqueoFinesSemana() {
		this.datos.setBloqueoFinesSemana(false);
		if (this.diaFeriado) {
			this.datos.setBloqueoFinesSemana(true);
		}
	}

	/**
	 * Setea informacion de conozca su cliente
	 */
	private void setearCliente() {
		try {
			this.cliente = obtenerDatosAfiliado(getCedulaUsuario());
		} catch (Exception e) {
			LOG.error("Se presento un error al obtener cliente el roles", e);
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
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * Returns the value of radioRoles.
	 *
	 * @return radioRoles
	 */
	public String getRadioRoles() {
		return radioRoles;
	}

	/**
	 * Sets the value for radioRoles.
	 *
	 * @param radioRoles
	 */
	public void setRadioRoles(String radioRoles) {
		this.radioRoles = radioRoles;
	}

	public String verificarDatos() {
		if (datos.getSolicitante() == null) {
			return "seleccionRoles";
		}
		return null;
	}

	/**
	 * @return the habilitarAfi
	 */
	public boolean isHabilitarAfi() {
		return habilitarAfi;
	}

	/**
	 * @param habilitarAfi
	 *            the habilitarAfi to set
	 */
	public void setHabilitarAfi(boolean habilitarAfi) {
		this.habilitarAfi = habilitarAfi;
	}

	/**
	 * @return the habilitarJub
	 */
	public boolean isHabilitarJub() {
		return habilitarJub;
	}

	/**
	 * @param habilitarJub
	 *            the habilitarJub to set
	 */
	public void setHabilitarJub(boolean habilitarJub) {
		this.habilitarJub = habilitarJub;
	}

	/**
	 * @return the habilitarZafrero
	 */
	public boolean isHabilitarZafrero() {
		return habilitarZafrero;
	}

	/**
	 * @param habilitarZafrero
	 *            the habilitarZafrero to set
	 */
	public void setHabilitarZafrero(boolean habilitarZafrero) {
		this.habilitarZafrero = habilitarZafrero;
	}

	/**
	 * @return
	 */
	public String getLastSignin() {
		return lastSignin;
	}

	/**
	 * @param lastAccess
	 */
	public void setLastSignin(String lastSignin) {
		this.lastSignin = lastSignin;
	}

	/**
	 * cerrar Session
	 *
	 * @retunr
	 * @author rtituana
	 */
	public String cerrarSession() {
		ExternalContext ectx = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) ectx.getSession(false);
		session.invalidate();
		return "roles";
	}

	public boolean isHabilitarNovacion() {
		return habilitarNovacion;
	}

	public void setHabilitarNovacion(boolean habilitarNovacion) {
		this.habilitarNovacion = habilitarNovacion;
	}

	/**
	 * M?todo para habilitar la novaci?n cbastidas 16/07/2012
	 */
	public void habilitarNovacion() {
		habilitarNovacion = false;
		try {
			habilitarNovacion = CalculoCreditoHelperSingleton.getInstancia().habilitarNovacion();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}

	/**
	 * M?todo que obtiene y fija la fecha del ?ltimo acceso al sistema.
	 */
	private void readLastAccess() {
		try {
			AccesosAplicativo accesosAplicativo = this.accesosAplicativoServicio.buscarPorCedulaAplicativo(Utilities.getRemoteCI(), AplicativoEnum.PQ_WEB.getCodigo());
			if (accesosAplicativo == null) {
				setLastSignin("Desconocida");
				accesosAplicativo = new AccesosAplicativo(null, Utilities.getRemoteCI(), new Date(), AplicativoEnum.PQ_WEB.getCodigo());
				this.guardarAccesoAplicativo(accesosAplicativo);
				return;
			}
			setLastSignin(Utilities.getFormatedDate(accesosAplicativo.getFechaAcceso(), "dd/MM/yyyy HH:mm:ss"));
			accesosAplicativo.setFechaAcceso(new Date());
			this.guardarAccesoAplicativo(accesosAplicativo);
		} catch (Exception e) {
			LOG.error("Se presento un error al consultar la fecha y hora de ultimo acceso", e);
			setLastSignin("Desconocida");
		}

	}

	/**
	 * Guarda informacion de acceso al aplicativo
	 *
	 * @param accesosAplicativo
	 */
	private void guardarAccesoAplicativo(AccesosAplicativo accesosAplicativo) {
		accesosAplicativoServicio.insertaOActualizaAccesosAplicativo(accesosAplicativo);
	}

	/**
	 * Fija en sesion bandera que indica si el proceso de planillas se esta ejecutando
	 */
	private void procesoPlanillasRunning() {
		CalculoCreditoHelperSingleton cchs = CalculoCreditoHelperSingleton.getInstancia();
		datos.setProcesoPlanillasRunning(cchs.procesoPlanillaRunning(parametroPQDao));
	}

	/**
	 * Fija en sesion bandera que indica si el reajuste de tasas de interes se esta ejecutando
	 */
	private void reajusteTasasRunning() {
		CalculoCreditoHelperSingleton cchs = CalculoCreditoHelperSingleton.getInstancia();
		datos.setReajusteTasaRunning(cchs.reajusteTasasRunning(parametroPQDao));
	}

	/**
	 * Obtiene informacion de que dias bloquear novacion
	 */
	private void obtenerDatosBloqueoNovacion() {
		ParametroPQ parametroPQEnNov = parametroPQDao.consultarPorCodigo("BLOQNOVPQ", 1);
		ParametroPQ parametroPQDic = parametroPQDao.consultarPorCodigo("BLONOVDIPQ", 1);

		datos.setDiaBloqEnNov(parametroPQEnNov.getValorCaracter());
		datos.setDiaBloqDic(parametroPQDic.getValorCaracter());
	}

	private void cargarParametrosGenerales() throws ConsultaParametroException {
		if(datos.getMapaParametrosGenerales() == null) {
			datos.setMapaParametrosGenerales(new HashMap<String, String>());
		}
		String correoConcesion = (String) this.consultaParametroServicio.consultarParametro(ParametrosGeneralesEnum.PQW_CORREO_CONCESION.getCodigo(),ParametrosGeneralesEnum.PQW_CORREO_CONCESION.getTipoDato());
		LOG.info("PARAMETRO CORREO AYUDA CONCESION OBTENIDO: "+correoConcesion);
		datos.getMapaParametrosGenerales().put(ParametrosGeneralesEnum.PQW_CORREO_CONCESION.getCodigo(), correoConcesion);

		String urlDatosIESS = (String) this.consultaParametroServicio.consultarParametro(ParametrosGeneralesEnum.PQW_FORM_DATOS_IESS.getCodigo(),ParametrosGeneralesEnum.PQW_FORM_DATOS_IESS.getTipoDato());
		LOG.info("PARAMETRO URL DATOS IESS OBTENIDO: "+urlDatosIESS);
		datos.getMapaParametrosGenerales().put(ParametrosGeneralesEnum.PQW_FORM_DATOS_IESS.getCodigo(), urlDatosIESS);

		String urTramitesIess = (String) this.consultaParametroServicio.consultarParametro(ParametrosGeneralesEnum.PQW_TRAMITE_IESS.getCodigo(),ParametrosGeneralesEnum.PQW_TRAMITE_IESS.getTipoDato());
		LOG.info("PARAMETRO URL TRAMITES IESS OBTENIDO: "+urTramitesIess);
		datos.getMapaParametrosGenerales().put(ParametrosGeneralesEnum.PQW_TRAMITE_IESS.getCodigo(), urTramitesIess);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getMensajeSueldoPromedio() {
		return mensajeSueldoPromedio;
	}

	public void setMensajeSueldoPromedio(String mensajeSueldoPromedio) {
		this.mensajeSueldoPromedio = mensajeSueldoPromedio;
	}

	public Integer getMostrarMensaje() {
		return mostrarMensaje;
	}

	public void setMostrarMensaje(Integer mostrarMensaje) {
		this.mostrarMensaje = mostrarMensaje;
	}

	public String getDiaDesdeGeneraComprobante() {
		return diaDesdeGeneraComprobante;
	}

	public void setDiaDesdeGeneraComprobante(String diaDesdeGeneraComprobante) {
		this.diaDesdeGeneraComprobante = diaDesdeGeneraComprobante;
	}
	
}