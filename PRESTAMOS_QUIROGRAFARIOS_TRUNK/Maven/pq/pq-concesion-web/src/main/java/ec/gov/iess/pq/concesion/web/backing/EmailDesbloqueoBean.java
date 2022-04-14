package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.jacc.PolicyContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import ec.fin.biess.unlock.excepciones.UnlockException;
import ec.fin.biess.unlock.servicio.GenerarEmailDesbloqueoServicio;
import ec.gov.biess.util.log.LoggerBiess;

public class EmailDesbloqueoBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private transient static final LoggerBiess log = LoggerBiess.getLogger(EmailDesbloqueoBean.class);

	private GenerarEmailDesbloqueoServicio generarEmailDesbloqueoServicio;

	private HttpSession session;

	/* Inicializar objetos */
	{
		try {
			generarEmailDesbloqueoServicio = (GenerarEmailDesbloqueoServicio) new InitialContext().lookup("java:comp/env/ejb/GenerarEmailDesbloqueoServicio");
		} catch (NamingException e) {
			log.error("Error al inyectar GenerarEmailDesbloqueoServicio", e);
		}
	}

	/**
	 * Metodo que genera URL de debloqueo y envia e-mail al usuario.
	 *
	 * @throws UnlockException
	 */
	public void sentUnlockEmail() throws UnlockException {
		String id;
		/* Obtener parametros de session */
		try {
			if(!getHttpServletRequest().isRequestedSessionIdValid() || getHttpServletRequest().isRequestedSessionIdFromURL()) {
				throw new UnlockException("Error al enviar enlace de desbloqueo.");
			}
			session = getHttpServletRequest().getSession();
			id = (String) session.getAttribute("noitacifitnedi");
		} catch (Exception e) {
			throw new UnlockException("Error al enviar enlace de desbloqueo.");
		}
		/* Generar URL de debloqueo e enviar e-mail al usuario */
		try {
			final String MSG = "Se ha enviado un enlace para el desbloqueo del aplicativo web de pr\u00E9stamos a la direcci\u00F3n de correo electr\u00F3nico registrado en la p\u00E1gina de Historia Laboral del IESS.";
			generarEmailDesbloqueoServicio.sentUnlockEmail(id);
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
