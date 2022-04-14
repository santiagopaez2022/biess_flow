/* 
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.pq.concesion.web.util;

import java.net.InetAddress;
import java.text.MessageFormat;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.fin.biess.auditoria.cliente.exception.RegistroAuditoriaException;
import ec.fin.biess.auditoria.cliente.servicio.RegistroAuditoriaJmsServicioLocal;
import ec.fin.biess.auditoria.dto.EventoAuditoriaDto;
import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.gov.biess.util.log.LoggerBiess;

/**
 * EstadoMenuBean.java
 * 
 * Backing Bean base
 * 
 * @version 1.0
 * @creacion 04/03/2010
 * @modificacion 04/03/2010
 * @author Daniel Cardenas
 * @author William Valencia
 */
public abstract class BaseBean {
	
	private static final LoggerBiess LOG = LoggerBiess.getLogger(BaseBean.class);

	protected String errorMessage;
	protected String fatalMessage;
	protected String infoMessage;
	protected String warningMessage;

	/**
	 * Devuelve la ultima excepcion
	 * 
	 * @param e
	 * @return
	 */
	protected Throwable devolverExcepcion(Throwable e) {
		Throwable res = e;
		if (e != null && e.getCause() != null) {
			res = devolverExcepcion(e.getCause());
		}
		return res;
	}

	/**
	 * Metodo que retorna la instancia actual de JSF
	 * 
	 * @return
	 */
	protected FacesContext context() {
		return (FacesContext.getCurrentInstance());
	}

	/**
	 * Retorna el contexto externo a JSF
	 * 
	 * @return
	 */
	protected ExternalContext getExternalContext() {
		return this.context().getExternalContext();
	}

	/**
	 * Obtiene el contexto de la aplicacion
	 * 
	 * @return
	 */
	protected String getContextPath() {
		return ((HttpServletRequest) context().getExternalContext()
				.getRequest()).getContextPath();
	}

	/**
	 * Retorna el request de la aplicacion
	 * 
	 * @return
	 */
	protected HttpServletRequest getHttpServletRequest() {
		return ((HttpServletRequest) context().getExternalContext()
				.getRequest());
	}

	/**
	 * Retorna el response de la aplicacion
	 * 
	 * @return
	 */
	protected HttpServletResponse getHttpServletResponse() {
		return ((HttpServletResponse) context().getExternalContext()
				.getResponse());
	}

	/**
	 * Retorna el request de la aplicacion
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ((HttpServletRequest) context().getExternalContext()
				.getRequest());
	}

	/**
	 * Retorna el response de la aplicacion
	 * 
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return ((HttpServletResponse) context().getExternalContext()
				.getResponse());
	}

	/**
	 * Retorna la sesion de la aplicacion
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		return ((HttpServletRequest) context().getExternalContext()
				.getRequest()).getSession();
	}

	/**
	 * Remueve el BackingBean del contexto
	 * 
	 * @param backingBeanName
	 */
	protected void removeBackingBean(String backingBeanName) {
		this.getExternalContext().getSessionMap().remove(backingBeanName);
	}

	/**
	 * Retorna el resource correspondiente al bundle y el key especificados Ej:
	 * getResource("errores","login.incorrecto");
	 * 
	 * @param bundle
	 * @param key
	 * @return
	 */
	protected String getResource(String bundle, String key) {
		return context().getApplication().getResourceBundle(context(), bundle)
				.getString(key);
	}

	/**
	 * retorna el classloader del proceso actual o de un objeto por defecto.
	 * 
	 * @param defaultObject
	 * @return
	 */
	protected ClassLoader getCurrentClassLoader(Object defaultObject) {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}

		return loader;
	}

	/**
	 * Retorna el mensaje corrspondiente a la clave ingresada reemplazando los
	 * parametros por sus valores respectivos
	 * 
	 * @param bundleName
	 * @param key
	 * @param params
	 * @return
	 */
	protected String getMessageResourceString(String bundleName, String key,
			Object params[]) {

		String text = null;

		text = getResource(bundleName, key);

		if (params != null) {
			MessageFormat mf = new MessageFormat(text);
			text = mf.format(params, new StringBuffer(), null).toString();
		}

		return text;
	}

	//
	// Global Messages
	//

	protected void addGlobalErrorMessage(String summary, String detail) {
		context().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
	}

	protected void addGlobalFatalMessage(String summary, String detail) {
		context().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail));
	}

	protected void addGlobalInfoMessage(String summary, String detail) {
		context().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}

	protected void addGlobalWarningMessage(String summary, String detail) {
		context().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
	}

	protected void removeMessages() {
		Iterator<FacesMessage> msgIterator =context().getMessages();
		while (msgIterator.hasNext())
		{
		    msgIterator.next();
		    msgIterator.remove();
		}
	}
	

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getFatalMessage() {
		return fatalMessage;
	}

	public void setFatalMessage(String fatalMessage) {
		this.fatalMessage = fatalMessage;
	}

	public String getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public void cleanMessages() {
		this.errorMessage = null;
		this.warningMessage = null;
		this.infoMessage = null;
		this.fatalMessage = null;
	}

	/**
	 * Retorna el remoteUser (usuario logueado)
	 * 
	 * @return
	 */
	protected String getRemoteUser() {
		return getHttpServletRequest().getRemoteUser();
	}
	
	/**
	 * Obtiene la IP del cliente
	 * 
	 * @return
	 */
	public String getIpUser() {
		String ip = getHttpServletRequest().getRemoteAddr();
		String ipAddress;
		if ((ipAddress = getHttpServletRequest().getHeader("X-FORWARDED-FOR")) != null) {
			ip = ipAddress;
			int idx = ip.indexOf(',');
			if (idx > -1) {
				ip = ip.substring(0, idx);
			}
		}
		return ip;
	}

	/**
	 * Obtiene el nombre del PC del cliente
	 * 
	 * @return
	 */
	public String obtenerNombrePCRemoto() {
		try {
			InetAddress inaHost = InetAddress.getByName(getHttpServletRequest().getRemoteAddr());
			String hostname = inaHost.getHostName();
			return hostname;
		} catch (Exception e) {
			return "error";
		}
	}
	
	/**
	 * M�todo que retorna la CI del usuario.
	 * 
	 * @return String
	 */
	public String getRemoteCI() {
		return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	}
	
	/**
	 * Guarda la auditoria de los diferentes procesos
	 * 
	 * @param operacion
	 * @param parametrosAuditoria
	 * @param parametrosReferencia
	 */
	@SuppressWarnings("unchecked")
	public void guardarAuditoria(OperacionEnum operacion, ParametroEvento parametroEvento, String parametrosReferencia) {
		try {
			RegistroAuditoriaJmsServicioLocal<EventoAuditoriaDto> logTransactionServicio = null;
			logTransactionServicio = (RegistroAuditoriaJmsServicioLocal<EventoAuditoriaDto>) new InitialContext()
					.lookup("RegistroAuditoriaPQJmsServicioImpl/local");

			EventoAuditoriaDto registro = new EventoAuditoriaDto();
			registro.setOperacionId(operacion.getCodigo());
			registro.setIpUsuario(this.getIpUser());
			registro.setParametrosReferencia(parametrosReferencia);

			registro.setParametro(parametroEvento);

			logTransactionServicio.registrarAuditoria(registro);
		} catch (NamingException e) {
			LOG.error("Se presento un error al instanciar servicio de auditoria", e);
		} catch (RegistroAuditoriaException e) {
			LOG.error("Error al guardar auditoria", e);
		}
	}
	
	public String devolverMensaje(String bundleName, String key, Object... parametros){
		String text = getResource(bundleName, key);
		if(parametros != null && parametros.length > 0){
			Object[] params = parametros;
			MessageFormat mf = new MessageFormat(text);
			return mf.format(params, new StringBuffer(), null).toString();
		}else{
			return text;
		}
	}
	
}