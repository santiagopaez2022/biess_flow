package ec.gov.iess.planillaspq.web.backing;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.planillaspq.web.helper.AuditoriaHelper;
import ec.gov.iess.planillaspq.web.util.BaseBean;

/**
 * 
 * @author wvalencia
 * 
 */
public class PrincipalBean extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final LoggerBiess LOG = LoggerBiess.getLogger(PrincipalBean.class);

	private String ulrPrincipal = null;
	//
	// Administration Methods
	//
	public PrincipalBean() {
		
	}
	
	@PostConstruct
	public void init () {
		ulrPrincipal = devolverUrlPrincipal();
	}

	//
	// Implementation Methods
	//
	public String goPrincipal() {
		return "principal";
	}

	/**
	 * Hace que la invocación de una opción de menú (ítem) se redirija a la
	 * página respectiva.
	 */
	public void solicitarItem() throws IOException {
		String url = getRequest().getParameter("url");
		AnulaPlanillaHlBacking anulaPlanillaHlBacking = (AnulaPlanillaHlBacking)getRequest().getSession().getAttribute("anuplahl");
		if (anulaPlanillaHlBacking!=null) {
			anulaPlanillaHlBacking.init();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("anuplahl",anulaPlanillaHlBacking);
		}
		AjustePlanillaHlBacking ajustePlanillaHlBacking = (AjustePlanillaHlBacking)getRequest().getSession().getAttribute("planillahl");
		if (ajustePlanillaHlBacking!=null) {
			ajustePlanillaHlBacking.init();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("planillahl",ajustePlanillaHlBacking);
		}
		AjustePlanillaHostBacking ajustePlanillaHostBacking = (AjustePlanillaHostBacking)getRequest().getSession().getAttribute("planillahost");
		if (ajustePlanillaHostBacking!=null) {
			ajustePlanillaHostBacking.init();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("planillahost",ajustePlanillaHostBacking);
		}
		
		AnulaPlanillaHostBacking anulaPlanillaHostBacking = (AnulaPlanillaHostBacking)getRequest().getSession().getAttribute("anuplahost");
		if (anulaPlanillaHostBacking!=null) {
			anulaPlanillaHostBacking.init();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("anuplahost",anulaPlanillaHostBacking);
		}

		getResponse().sendRedirect(getRequest().getContextPath() + url);

	}
	
	/**
	 * Realiza el cierre de sesion y guarda auditoria
	 * 
	 * @return
	 */
	public String logout() {
		this.guardaAuditoria(OperacionEnum.LOGOUT_PQ_INTRANET);
		this.cerrarSesion();
		return "";
	}
	
	/**
	 * Invalida esta sesión y luego desvincula cualquier objeto enlazado a ella.
	 * 
	 */
	private void cerrarSesion() {
		ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) ectx.getSession(false);
		session.invalidate();
		//Navegar a la pagina principal
		try {
			getResponse().sendRedirect(getRequest().getContextPath() + "/pages/salir.jsf");
		} catch (IOException e) {
			LOG.error("Error al realizar redireccion a pagina principal", e);
		}
	}
	
	/**
	 * Devolver path principal de la aplicación.
	 * 
	 */
	private String devolverUrlPrincipal() {
		return getRequest().getContextPath();
	}
	
	/**
	 * Guarda la auditoria dada la operacion
	 * 
	 * @param operacion
	 */
	private void guardaAuditoria(OperacionEnum operacion) {
		ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosVacio();
		super.guardarAuditoria(operacion, parametroEvento, super.getRemoteCI());
	}

	public String getUlrPrincipal() {
		return ulrPrincipal;
	}

	public void setUlrPrincipal(String ulrPrincipal) {
		this.ulrPrincipal = ulrPrincipal;
	}
	
}
