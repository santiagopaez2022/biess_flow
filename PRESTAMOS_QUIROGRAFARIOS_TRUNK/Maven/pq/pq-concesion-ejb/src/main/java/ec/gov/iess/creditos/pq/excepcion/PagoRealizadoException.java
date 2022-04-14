/**
 * 
 */
package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author caldaz
 *
 */
public class PagoRealizadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9181205402923048920L;

	/**
	 * 
	 */
	public PagoRealizadoException() {
		
	}

	/**
	 * @param message
	 */
	public PagoRealizadoException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public PagoRealizadoException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PagoRealizadoException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
