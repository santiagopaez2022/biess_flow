package ec.gov.iess.pq.concesion.web.backing;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.helper.AuditoriaPqWebHelper;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.Utilities;

/**
 * 
 * @author wvalencia
 * 
 */
public class PrincipalBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6485077931600239707L;

	private static final LoggerBiess log = LoggerBiess.getLogger(RolesBean.class);

	private String errorMsg;

	private DatosBean datos;

	private CreditoAccionBean creditoAccion;
	
	
	//
	// Administration Methods
	//
	public PrincipalBean() {

	}

	//
	// Implementation Methods
	//
	public String goPrincipal() {
		return "principal";
	}
	
	public String logout(){
		// Inicio auditoria
		ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedula(Utilities.getRemoteCI());
		super.guardarAuditoria(OperacionEnum.LOGOUT_PQ_WEB, parametroEvento, Utilities.getRemoteCI());
		// Fin auditoria
		ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
		//HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
		HttpSession session = (HttpSession) ectx.getSession(false);
		session.invalidate();
		//Navegar a la pagina principal
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		app.getNavigationHandler().handleNavigation(ctx, "/pages/concesion/roles.jsf", "roles");
		return null;
	}
	
	/**
	 * Forzar el deslogueo de sesion por apertura de mas pestañas
	 * @return
	 */
	public String forzarLogout(){
		// Inicio auditoria
		ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedula(Utilities.getRemoteCI());
		super.guardarAuditoria(OperacionEnum.LOGOUT_PQ_WEB, parametroEvento, Utilities.getRemoteCI());
		// Fin auditoria
		ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
		//HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
		HttpSession session = (HttpSession) ectx.getSession(false);
		session.invalidate();

		try {
			HttpSession sessionLogin =((HttpServletRequest) PolicyContext.getContext("javax.servlet.http.HttpServletRequest")).getSession();			
			sessionLogin.setAttribute("interruptedSessionMsg", 
					"Estimado Cliente: <br />El sistema detect\u00f3 un nuevo inicio de sesi\u00f3n, "
					+ "por lo que procedi\u00f3 a cerrar la sesión anterior. "
					+ "Para continuar usando el sistema por favor ingrese nuevamente su usuario y clave del IESS.");
		} catch (PolicyContextException e) {
			log.error(e.getMessage());
		}
		
		//Navegar a la pagina principal
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		app.getNavigationHandler().handleNavigation(ctx, "/pages/concesion/roles.jsf", "roles");
		return null;
	}


	/**
	 * Hace que la invocacin de una opcin de men (tem) se redirija a la página
	 * respectiva.
	 */
	public void solicitarItem() throws IOException {
		String url = getRequest().getParameter("url");
		getResponse().sendRedirect(getRequest().getContextPath() + url);
	}

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

	public String getCedulaUsuario() {
		String ced = context().getExternalContext().getRemoteUser();
		log.debug("ced" + ced);
		datos.setSolicitante(null);
		return ced;
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

	public CreditoAccionBean getCreditoAccion() {
		return creditoAccion;
	}

	public void setCreditoAccion(CreditoAccionBean creditoAccion) {
		this.creditoAccion = creditoAccion;
	}
	
}