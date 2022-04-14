package ec.fin.biess.unlock.servicio.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.security.jacc.PolicyContext;
import javax.servlet.http.HttpServletRequest;


import ec.fin.biess.unlock.dao.BloqueoUsuarioDao;
import ec.fin.biess.unlock.excepciones.UnlockException;
import ec.fin.biess.unlock.helper.PropertiesHelper;
import ec.fin.biess.unlock.modelo.BloqueoUsuario;
import ec.fin.biess.unlock.servicio.DesbloqueoCuentaOnlineServicio;
import ec.fin.biess.unlock.util.Utilities;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.suite.seguridad.Encriptacion;
import ec.gov.iess.suite.seguridad.EncriptacionException;
import ec.gov.iess.suite.seguridad.EncriptacionFactory;
import ec.gov.iess.suite.seguridad.EncriptacionFactoryException;
import ec.gov.iess.suite.seguridad.ExpiracionKeyException;

@Stateless
public class DesbloqueoCuentaOnlineServicioImpl implements DesbloqueoCuentaOnlineServicio {

	private transient static final LoggerBiess log = LoggerBiess.getLogger(DesbloqueoCuentaOnlineServicioImpl.class);

	@EJB
	private transient BloqueoUsuarioDao bloqueoUsuarioDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.unlock.servicio.EnviarCodigoDesbloqueoServicio#enviarCodigoDebloqueo(java.lang.String)
	 */
	@Override
	public void unlockAccount(final String id, final String unlockCode) throws UnlockException {
		try {
			/* Validar parametros URL */
			if (null == id || id.isEmpty() || null == unlockCode || id.isEmpty()) {
				throw new UnlockException("Parametros id y/o unlockCode nulos o vacios");
			}
			/* Validar expiracion URL */
			String cedula = validateURLExpiration(id);
			BloqueoUsuario blkusr = bloqueoUsuarioDao.getBloqueoUsuarioPorId(cedula);
			/* Validar usuario en la tabla de bloqueos */
			if (null == blkusr) {
				throw new UnlockException("Usuario no existe en la tabla de bloqueos");
			}
			/* Validar que la cuenta no este con bloqueo total */
			if (blkusr.getNumeroBloqueosTotales() > PropertiesHelper.MAXONLINEUNLOCKS) {
				throw new UnlockException("No se puede desbloquear cuenta. La cuenta tiene bloqueo total.");
			}
			/* Validar token */
			if (!isTokenValid(cedula, unlockCode, blkusr)) {
				/* Se bloque la cuenta */
				blockUserAccount(cedula);
				throw new UnlockException("Token invalido");
			}
			/* Desbloquear cuenta */
			unlockUserAccount(cedula);
		} catch (ExpiracionKeyException e) {
			throw new UnlockException("No se puede desbloquear cuenta. El enlace ha expirado.");
		} catch (UnlockException e) {
			throw new UnlockException("Error al desbloquear cuenta.");
		} catch (Exception e) {
			log.error("Error al desbloquear usuario");
			log.error("id: " + id);
			log.error("unlockCode: " + unlockCode);
			log.error(e.getMessage(), e);
			throw new UnlockException("Error al desbloquear cuenta.");
		}
	}

	/**
	 * Metodo que valida expiracion del URL enviado.
	 * 
	 * @param id
	 * @return String
	 * @throws ExpiracionKeyException
	 * @throws UnlockException
	 */
	private String validateURLExpiration(String id) throws ExpiracionKeyException, UnlockException {
		Encriptacion encrypt;
		try {
			encrypt = EncriptacionFactory.getInstance();
			return encrypt.decodeWithDate(id);
		} catch (EncriptacionException e) {
			log.error("Error al encriptar token de desbloqueo", e);
			throw new UnlockException("Error al desencriptar id de desbloqueo");
		} catch (EncriptacionFactoryException e) {
			log.error("Error al encriptar token de desbloqueo", e);
			throw new UnlockException("Error al desencriptar id de desbloqueo");
		}
	}

	/**
	 * Metodo que compara token enviado con token almacenado.
	 * 
	 * @param cedula
	 * @param unlockCode
	 * @param blkusr
	 * @return boolean
	 * @throws UnlockException
	 */
	private boolean isTokenValid(String cedula, String unlockCode, BloqueoUsuario blkusr) throws UnlockException {
		try {
			if (null == blkusr.getToken()) {
				throw new UnlockException("Error al validar codido de desbloqueo");
			}
			StringBuffer senttoken = new StringBuffer();
			senttoken.append(cedula);
			senttoken.append(unlockCode);
			senttoken.append(PropertiesHelper.SALT);
			return Utilities.hash(senttoken.toString()).compareTo(blkusr.getToken()) == 0 ? true : false;
		} catch (Exception e) {
			log.error("Error al validar codido de desbloqueo", e);
			throw new UnlockException("Error al validar codido de desbloqueo");
		}
	}

	/**
	 * Metodo que desbloquea cuenta de usuario.
	 * 
	 * @param cedula
	 * @throws UnlockException
	 */
	private void unlockUserAccount(String cedula) throws UnlockException {
		try {
			final String OBS = "Se desbloque\u00F3 cuenta via online";
			BloqueoUsuario blkusr = bloqueoUsuarioDao.getBloqueoUsuarioPorId(cedula);
			/* Reiniciar contador bloqueo parcial */
			blkusr.setNumeroBloqueosParciales(new Integer(0));
			blkusr.setFechaBloqueosParciales(null);
			blkusr.setIndicadorCuentaBloqueda("N");
			blkusr.setFechaDesbloqueos(new Date());
			blkusr.setToken(null);
			blkusr.setIp(getHttpServletRequest().getRemoteAddr());
			blkusr.setObservacion(OBS);
			bloqueoUsuarioDao.actualizar(blkusr);
		} catch (Exception e) {
			log.error("Error al actualizar registro de bloqueo del usuario: " + cedula, e);
			throw new UnlockException("Error al actualizar registro de bloqueo del usuario");
		}
	}

	/**
	 * Metodo que bloquea cuenta de usuario por intento de desbloqueo con token invalido.
	 * 
	 * @param cedula
	 * @throws UnlockException
	 */
	private void blockUserAccount(String cedula) throws UnlockException {
		try {
			final String OBS = "Se bloque\u00F3 cuenta por intento de desbloqueo via online con token invalido ";
			BloqueoUsuario blkusr = bloqueoUsuarioDao.getBloqueoUsuarioPorId(cedula);
			blkusr.setIndicadorCuentaBloqueda("S");
			blkusr.setNumeroBloqueosTotales(PropertiesHelper.MAXONLINEUNLOCKS + 1);
			blkusr.setIp(getHttpServletRequest().getRemoteAddr());
			blkusr.setObservacion(OBS);
			bloqueoUsuarioDao.actualizar(blkusr);
		} catch (Exception e) {
			log.error("Error al actualizar registro de bloqueo del usuario: " + cedula, e);
			throw new UnlockException("Error al actualizar registro de bloqueo del usuario");
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
