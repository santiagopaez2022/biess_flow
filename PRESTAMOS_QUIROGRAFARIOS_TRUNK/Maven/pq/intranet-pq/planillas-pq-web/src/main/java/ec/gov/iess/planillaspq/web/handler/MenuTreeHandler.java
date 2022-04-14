package ec.gov.iess.planillaspq.web.handler;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.security.auth.spi.IessPrincipal;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNodeImpl;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.seguridades.exception.GeneracionMenuException;
import ec.gov.iess.seguridades.modelo.Menu;
import ec.gov.iess.seguridades.modelo.OpcionMenu;
import ec.gov.iess.seguridades.servicio.MenuServicio;

/**
 * Maneja los recursos del árbol de menú
 * 
 * @author wvalencia
 * 
 */
public class MenuTreeHandler extends TreeNodeImpl implements Serializable{

	private MenuServicio menuServicio;
	private static final long serialVersionUID = 6341264035068839950L;
	private static LoggerBiess log = LoggerBiess.getLogger(MenuTreeHandler.class);
	private ec.gov.iess.planillaspq.web.util.ContainerBean containerBean;

	/**
	 * Constructor
	 */
	public MenuTreeHandler() throws IOException, ServletException {
		log.debug("Constructor");
		Menu menu = this.generateMenu();
		
		if(menu == null)
			throw new ServletException("Error en la generaci\u00F3n del men\u00FA");
		this.setData("ROOT");
		this.poblarNodos(this, menu.getListaOpcionesMenu());
	}

	/**
	 * Remueve los Backing Beans del ámbito de sesión de JSF.
	 */
	private void reset() {
		this.removeBackingBean("depuracionCompletaBean");
		this.removeBackingBean("depuracionNombreBean");
		this.removeBackingBean("unificacionParcial");
	}

	/**
	 * Atrapa el evento de cambio de nodo en el árbol menú y restablece el
	 * estado inicial de los Backing Beans.
	 * 
	 * @param event
	 */
	public void reset(NodeSelectedEvent event) {
		this.reset();
	}

	private TreeNodeImpl poblarNodos(TreeNodeImpl padre,
			List<OpcionMenu> opcionesHijos) {
		log.debug("padre:" + padre.getData());

		for (OpcionMenu opcionMenu : opcionesHijos) {

			if (!"MEN".equals(opcionMenu.getTipoOpcion())) {
				continue;
			}

			TreeNodeImpl nodo = new TreeNodeImpl();
			nodo.setData(opcionMenu);
			padre.addChild(opcionMenu.getCodigoOpcionMenu(), nodo);
			// si no tiene hijos
			if (opcionMenu.getOpcionMenuList() != null) {
				nodo = poblarNodos(nodo, opcionMenu.getOpcionMenuList());
			}
		}
		log.debug("fin padre:" + padre.getData());
		return padre;
	}

	/**
	 * Metodo que retorna la instancia actual de JSF
	 * 
	 * @return
	 */
	protected FacesContext getContext() {
		return (FacesContext.getCurrentInstance());
	}

	/**
	 * Retorna el contexto externo a JSF
	 * 
	 * @return
	 */
	protected ExternalContext getExternalContext() {
		return this.getContext().getExternalContext();
	}

	/**
	 * Retorna el objeto Request
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) this.getExternalContext().getRequest();
	}

	/**
	 * Retorna el objeto Response
	 * 
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) this.getExternalContext().getResponse();
	}

	/**
	 * Retorna la sesion de la aplicacion
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		return ((HttpServletRequest) getContext().getExternalContext()
				.getRequest()).getSession();
	}

	private void removeBackingBean(String backingBeanName) {
		this.getExternalContext().getSessionMap().remove(backingBeanName);
	}

	//
	// Menu Methods
	//

	private Menu generateMenu() throws IOException {
		log.info("Generando men\u00FA desde MenuTreeHandler...");
		int badRequest = HttpServletResponse.SC_BAD_REQUEST;
		try {
			Context ctx = new InitialContext();
			menuServicio = (MenuServicio) ctx
					.lookup("java:comp/env/ejb/MenuServicio");
			String cedula = getRequest().getRemoteUser(); // si no existe menu
			String appCode = getExternalContext().getInitParameter("codigoAplicacion");
			Menu menu = (Menu) getSession().getAttribute("menu");
			if (menu == null) {
				IessPrincipal principal = (IessPrincipal) this.getRequest()
						.getUserPrincipal();
				String[] tiposRol = this.getRoleTypes(principal);
				String[] roles = this.getRoles(principal);
				menu = menuServicio.obtenerMenuPorApp(cedula, tiposRol, roles,
						appCode);
				this.getSession().setAttribute("menu", menu);
			}
			return menu;
		} catch (NamingException e1) {
			String err = "No cargo MenuServicio:" + e1;
			log.error(err);
			this.getSession().invalidate();
			this.getResponse().sendError(badRequest, err);
			return null;
		} catch (GeneracionMenuException e) {
			String err = "Ocurrio un error al generar el men\u00FA:" + e;
			this.getSession().invalidate();
			this.getResponse().sendError(badRequest, err);
			return null;
		}
		
	}

	private String[] getRoles(IessPrincipal iessPrincipal) {
		String[] roles = new String[iessPrincipal.getRoles().size()];

		int i = 0;
		for (Object rol : iessPrincipal.getRoles()) {
			roles[i] = (String) rol;
			i++;
		}
		return roles;
	}

	private String[] getRoleTypes(IessPrincipal iessPrincipal) {
		String[] tipoRoles = new String[iessPrincipal.getRoleTypes().size()];

		int i = 0;
		for (Object rol : iessPrincipal.getRoleTypes()) {
			tipoRoles[i] = (String) rol;
			i++;
		}
		return tipoRoles;
	}

	public ec.gov.iess.planillaspq.web.util.ContainerBean getContainerBean() {
		return containerBean;
	}

	public void setContainerBean(
			ec.gov.iess.planillaspq.web.util.ContainerBean containerBean) {
		this.containerBean = containerBean;
	}

}