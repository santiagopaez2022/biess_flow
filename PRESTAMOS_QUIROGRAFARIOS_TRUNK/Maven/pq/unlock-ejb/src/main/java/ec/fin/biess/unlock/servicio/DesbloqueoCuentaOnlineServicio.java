package ec.fin.biess.unlock.servicio;

import javax.ejb.Local;

import ec.fin.biess.unlock.excepciones.UnlockException;

@Local
public interface DesbloqueoCuentaOnlineServicio {

	/**
	 * Metodo que desbloquea una cuenta.
	 * 
	 * @param id
	 * @param unlockCode
	 * @throws UnlockException  
	 */
	void unlockAccount(final String id, final String unlockCode) throws UnlockException;

}
