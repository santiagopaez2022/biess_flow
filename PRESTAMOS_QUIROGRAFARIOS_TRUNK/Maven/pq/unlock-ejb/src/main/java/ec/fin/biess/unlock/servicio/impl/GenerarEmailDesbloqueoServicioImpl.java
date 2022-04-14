package ec.fin.biess.unlock.servicio.impl;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.security.jacc.PolicyContext;
import javax.servlet.http.HttpServletRequest;


import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.alerts.modelo.InformacionAfiliado;
import ec.fin.biess.unlock.dao.BloqueoUsuarioDao;
import ec.fin.biess.unlock.dao.DatosPersonalesIessDao;
import ec.fin.biess.unlock.excepciones.UnlockException;
import ec.fin.biess.unlock.helper.AlertsHelper;
import ec.fin.biess.unlock.helper.PropertiesHelper;
import ec.fin.biess.unlock.modelo.BloqueoUsuario;
import ec.fin.biess.unlock.servicio.GenerarEmailDesbloqueoServicio;
import ec.fin.biess.unlock.util.Utilities;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.dto.DatosPersonales;
import ec.gov.iess.suite.seguridad.Encriptacion;
import ec.gov.iess.suite.seguridad.EncriptacionFactory;

@Stateless
public class GenerarEmailDesbloqueoServicioImpl implements GenerarEmailDesbloqueoServicio {

	private transient static final LoggerBiess log = LoggerBiess.getLogger(GenerarEmailDesbloqueoServicioImpl.class);

	@EJB
	private transient AlertUserHelper alertUserHelper;

	@EJB
	private transient DatosPersonalesIessDao datosPersonalesIessDao;

	@EJB
	private transient BloqueoUsuarioDao bloqueoUsuarioDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.unlock.servicio.EnviarCodigoDesbloqueoServicio#enviarCodigoDebloqueo(java.lang.String)
	 */
	@Override
	public void sentUnlockEmail(final String cedula) throws UnlockException {
		
		DatosPersonales datosPersonales = datosPersonalesIessDao.getDatosPersonales(cedula);
		//INC-2414 - en pruebas encontradas se sugerio que si no existe un mail asociado al afiliado se presente un mensaje.
		if(datosPersonales.getEmail().equalsIgnoreCase("null") || datosPersonales.getEmail() == null || datosPersonales.getEmail().trim().isEmpty()) {
			throw new UnlockException("No se puede realizar el env\u00EDo del enlace de desbloqueo debido a que no posee una cuenta de correo registrada. Por favor actualizar su informaci\u00F3n");
		}
		InformacionAfiliado informacionAfiliado = new InformacionAfiliado();
		informacionAfiliado.setApellidosNombres(datosPersonales.getApellidosNombres());
		informacionAfiliado.setCedula(datosPersonales.getCedula());
		informacionAfiliado.setCelular(datosPersonales.getCelular());
		informacionAfiliado.setEmail(datosPersonales.getEmail());
		
		try {
			/* Actualizar tabla de bloqueos con token */
			String token = Utilities.getToken();
			updateTokenUserAccount(cedula, token);
			/* Enviar email con URL de desbloqueo */
			String templatePath = "ec/fin/biess/unlock/templates/email/unlockaccount.ftl";
			Map<String, Object> alertData = new HashMap<String, Object>();
			alertData.put("msg_urlunlock", getUnlockURL(cedula, token));
			AlertsHelper.enviarAlerta(alertUserHelper, informacionAfiliado, templatePath, alertData, null, AlertType.EMAIL);
		} catch (UnlockException e) {
			throw new UnlockException("Error al enviar enlace de desbloqueo.");
		} catch (Exception e) {
			log.error("Error al enviar enlace de desbloqueo al usuario: " + cedula);
			log.error(e.getMessage(), e);
			throw new UnlockException("Error al enviar enlace de desbloqueo.");
		}
	}

	/**
	 * Metodo que actualiza informacion de desbloqueo.
	 * 
	 * @param cedula
	 * @param token
	 * @throws UnlockException
	 */
	private void updateTokenUserAccount(String cedula, String token) throws UnlockException {
		try {
			final String OBS = "Se envi\u00F3 e-mail con URL de desbloqueo";
			BloqueoUsuario blkusr = bloqueoUsuarioDao.getBloqueoUsuarioPorId(cedula);
			verifyUserAccountStatus(blkusr);
			/* Actualizar contador bloqueo total */
			int numblotot = null == blkusr.getNumeroBloqueosTotales() ? 1 : blkusr.getNumeroBloqueosTotales() + 1;
			blkusr.setNumeroBloqueosTotales(new Integer(numblotot));			
			/* Guardar token */
			blkusr.setToken(getTokenUserAccount(cedula, token));
			blkusr.setIp(getHttpServletRequest().getRemoteAddr());
			blkusr.setObservacion(OBS);
			bloqueoUsuarioDao.actualizar(blkusr);
		} catch (Exception e) {
			log.error("Error al actualizar token al usuario: " + cedula, e);
			throw new UnlockException("Error al actualizar token al usuario");
		}
	}

	/**
	 * Metodo que verifica el estado del usuario antes de enviar el enlace de desbloqueo.
	 * 
	 * @param blkusr
	 * @throws UnlockException
	 */
	private void verifyUserAccountStatus(BloqueoUsuario blkusr) throws UnlockException {
		if (blkusr.getNumeroBloqueosTotales().compareTo(new Integer(PropertiesHelper.MAXONLINEUNLOCKS)) >= 0) {
			log.error("No se puede enviar enlace de desbloqueo. El usuario " + blkusr.getCedula() + " esta en estado de bloqueo total: ");
			throw new UnlockException("No se puede enviar enlace de desbloqueo. El usuario esta en estado de bloqueo total");			
		}
	}
	
	/**
	 * Metodo genera el token de desbloqueo.
	 * 
	 * @param cedula
	 * @param token
	 * @return String
	 * @throws UnlockException
	 */
	private String getTokenUserAccount(String cedula, String token) throws UnlockException {
		try {
			StringBuffer newtoken = new StringBuffer();
			newtoken.append(cedula);
			newtoken.append(token);
			newtoken.append(PropertiesHelper.SALT);
			return Utilities.hash(newtoken.toString());
		} catch (NoSuchAlgorithmException e) {
			log.error("Error al encriptar token de desbloqueo", e);
			throw new UnlockException("Error al encriptar token de desbloqueo");
		}
	}

	/**
	 * Metodo que forma el URL de desbloqueo.
	 * 
	 * @param cedula
	 * @return String
	 * @throws UnlockException
	 */
	private String getUnlockURL(String cedula, String token) throws UnlockException {
		StringBuffer urlunlock = new StringBuffer();
		urlunlock.append(getURLApp());
		urlunlock.append("/unlock/onlineUnlock.jsp?code1=");
		urlunlock.append(getIdEncoded(cedula));
		urlunlock.append("&code2=");
		urlunlock.append(token);

		return urlunlock.toString();
	}

	/**
	 * Obtiene el URL de la aplicacion
	 * 
	 * @return String
	 * @throws UnlockException
	 */
	private String getURLApp() throws UnlockException {
		try {
			URL requrl = new URL(getHttpServletRequest().getRequestURL().toString());
			StringBuffer urlapp = new StringBuffer();
			/* urlapp.append(requrl.getProtocol()); */
			urlapp.append("https://");
			urlapp.append(requrl.getAuthority());
			urlapp.append(getHttpServletRequest().getContextPath());
			return urlapp.toString();
		} catch (Exception e) {
			log.error("Error al formar URL de desbloqueo", e);
			throw new UnlockException("Error al formar URL de desbloqueo");
		}
	}

	/**
	 * Metodo que encripta la CI del usuario juntamente con la fecha actual para realizar luego el control de expiracion del URL.
	 * 
	 * @param id
	 * @return String
	 * @throws UnlockException
	 */
	private String getIdEncoded(String id) throws UnlockException {
		Encriptacion encrypt;
		try {
			encrypt = EncriptacionFactory.getInstance();
			return encrypt.encodeWithDate(id);
		} catch (Exception e) {
			log.error("Error al encriptar el id del usuario", e);
			throw new UnlockException("Error al encriptar el id del usuario");
		}
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
			throw new UnlockException("Error al obtener HttpServletRequest desde unlock-ejb");
		}
	}

}
