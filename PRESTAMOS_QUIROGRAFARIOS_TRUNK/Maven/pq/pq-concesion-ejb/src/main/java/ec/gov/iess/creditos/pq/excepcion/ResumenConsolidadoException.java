/**
 * 
 */
package ec.gov.iess.creditos.pq.excepcion;

import javax.ejb.ApplicationException;

/**
 * @author jvaca
 *
 */
@ApplicationException(rollback = true)
public class ResumenConsolidadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2502438588907156061L;

	/**
	 * 
	 */
	public ResumenConsolidadoException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ResumenConsolidadoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ResumenConsolidadoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ResumenConsolidadoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
