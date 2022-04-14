package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.jacc.PolicyContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import ec.fin.biess.unlock.excepciones.UnlockException;
import ec.fin.biess.unlock.servicio.DesbloqueoCuentaOnlineServicio;
import ec.gov.biess.util.log.LoggerBiess;

public class DesbloqueoOnlineBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private transient static final LoggerBiess log = LoggerBiess.getLogger(DesbloqueoOnlineBean.class);

	private DesbloqueoCuentaOnlineServicio desbloqueoCuentaOnlineServicio;

	private HttpSession session;

	/* Inicializar objetos */
	{
		try {
			desbloqueoCuentaOnlineServicio = (DesbloqueoCuentaOnlineServicio) new InitialContext().lookup("java:comp/env/ejb/DesbloqueoCuentaOnlineServicio");
		} catch (NamingException e) {
			log.error("Error al inyectar DesbloqueoCuentaOnlineServicio", e);
		}
	}

	/**
	 * Metodo que desbloquea cuenta de usuario.
	 *
	 * @throws UnlockException
	 */
	public void unlockAccount() throws UnlockException {
		String id;
		String unlockCode;
		/* Obtener parametros de session */
		try {
			if (getHttpServletRequest().isRequestedSessionIdFromURL()) {
				throw new UnlockException("Error al desbloquear cuenta.");
			}
			session = getHttpServletRequest().getSession();
			id = getHttpServletRequest().getParameter("code1");
			unlockCode = getHttpServletRequest().getParameter("code2");
		} catch (Exception e) {
			throw new UnlockException("Error al desbloquear cuenta.");
		}
		/* Generar URL de debloqueo e enviar e-mail al usuario */
		try {
			final String MSG = "Acceso al aplicativo web de pr\u00E9stamos desbloqueado exitosamente.";
			desbloqueoCuentaOnlineServicio.unlockAccount(id, unlockCode);
			resetLoginSessionVariables();
			session.setAttribute("egassemofninigol", MSG);
		} catch (UnlockException e) {
			resetLoginSessionVariables();
			session.setAttribute("egassemrorrenigol", e.getMensaje());
		}
	}

	/**
	 * Metodo que limpia las variables de session.
	 */
	private void resetLoginSessionVariables() {
		session.removeAttribute("dekcolblaitrap");
		session.removeAttribute("noitacifitnedi");
		session.removeAttribute("dinoisses");
		session.removeAttribute("detadpuatad");
		session.removeAttribute("egassemrorrenigol");
		session.removeAttribute("egassemofninigol");
	}

	/**
	 * Metodo que obtiene el objeto HttpServletRequest de la actual sesion.
	 *
	 * @return HttpServletRequest
	 * @throws UnlockException
	 */
	private HttpServletRequest getHttpServletRequest() throws UnlockException {
		try {
			return (HttpServletRequest) PolicyContext.getContext("javax.servlet.http.HttpServletRequest");
		} catch (Exception e) {
			log.error("Error al obtener HttpServletRequest desde unlock-ejb", e);
			throw new UnlockException("Error al obtener HttpServletRequest desde pq-concesion-web");
		}
	}

}
