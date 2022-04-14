/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ec.gov.iess.planillaspq.web.util;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
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
 * backing Bean base
 * 
 * @author Daniel Cardenas, William Valencia , pjarrin
 * 
 */
public abstract class BaseBean implements Serializable {
	
	private static final LoggerBiess LOG = LoggerBiess.getLogger(BaseBean.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String errorMessage;
	protected String fatalMessage;
	protected String infoMessage;
	protected String warningMessage;

	/**
	 * Mensaje de error
	 * 
	 * @param resumen
	 */
	protected void addErrorMessage(final String resumen) {
		FacesMessage message = new FacesMessage(resumen);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Adds the info message.
	 * 
	 * @param resumen
	 *            the resumen
	 */
	protected void addInfoMessage(final String resumen) {
		FacesMessage message = new FacesMessage(resumen);
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, message);
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
	 * Obtiene el servlet context.
	 * 
	 * @return el servlet context
	 */
	protected ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();

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

	public boolean isFacesContexConError() {
		Severity lSeverity = context().getMaximumSeverity();
		return FacesMessage.SEVERITY_ERROR.equals(lSeverity)
				|| FacesMessage.SEVERITY_FATAL.equals(lSeverity);
	}

	/**
	 * Método que retorna la CI del usuario.
	 * 
	 * @return String
	 */
	public String getRemoteCI() {
		 // INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
		return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	}
	
	/**
	 * Obtener la IP del cliente remoto
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public String obtenerIP() throws UnknownHostException {
		HttpServletRequest request = (HttpServletRequest) context().getExternalContext().getRequest();
		String remoteAddr = request.getRemoteAddr();
        String ipAddress;
        if ((ipAddress = request.getHeader("X-FORWARDED-FOR")) != null) {
            remoteAddr = ipAddress;
            int idx = remoteAddr.indexOf(',');
            if (idx > -1) {
                remoteAddr = remoteAddr.substring(0, idx);
            }
        }
        return remoteAddr;
	}
	
	/**
	 * Obtiene la IP del usuario
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
	 * Obtiene la ip del servidor
	 * 
	 * @return
	 */
	public String obtenerIpServer() {
		try {
			String ipServer = getHttpServletRequest().getServerName() + "";
			return ipServer;
		} catch (Exception e) {
			return "error";
		}
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

			parametroEvento.getParametros().put("usuario", this.getRemoteCI());
			parametroEvento.getParametros().put("hostremoto", this.obtenerNombrePCRemoto());
			registro.setParametro(parametroEvento);

			logTransactionServicio.registrarAuditoria(registro);
		} catch (NamingException e) {
			LOG.error("Se presento un error al instanciar servicio de auditoria", e);
		} catch (RegistroAuditoriaException e) {
			LOG.error("Error al guardar auditoria", e);
		}
	}
	
}