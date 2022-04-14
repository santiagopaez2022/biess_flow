package ec.fin.biess.unlock.servicio;

import javax.ejb.Local;

import ec.fin.biess.unlock.excepciones.UnlockException;

@Local
public interface GenerarEmailDesbloqueoServicio {

	/**
	 * Metodo que envia el e-mail de desbloqueo al asegurado dado su cedula.
	 * 
	 * @param cedula
	 * @throws UnlockException 
	 */
	void sentUnlockEmail(final String cedula) throws UnlockException;

}
