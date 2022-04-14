package ec.gov.iess.creditos.pq.excepcion;

import javax.ejb.ApplicationException;

/**
 * Clase exception para Secuencia Bloqueada
 * 24/08/2010: rituana inc-5780 agregando @ApplicationException
 * @author Palvarez
 * 
 */
@ApplicationException(rollback = true)
public class SecuenciaBloqueadaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecuenciaBloqueadaException() {
	}

	public SecuenciaBloqueadaException(String message) {
		super(message);
	}

	public SecuenciaBloqueadaException(Throwable cause) {
		super(cause);
	}

	public SecuenciaBloqueadaException(String message,
			Throwable cause) {
		super(message, cause);
	}

}
