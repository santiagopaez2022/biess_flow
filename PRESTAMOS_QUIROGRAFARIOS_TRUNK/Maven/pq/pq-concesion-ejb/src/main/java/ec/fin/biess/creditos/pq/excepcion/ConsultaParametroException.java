/**
 * 
 */
package ec.fin.biess.creditos.pq.excepcion;

/**
 * Excepcion para manejo de errores al consultar parametros
 * 
 * @author cvillarreal
 *
 */
public class ConsultaParametroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7796655492941751014L;

	/**
	 * 
	 */
	public ConsultaParametroException() {
		super();
	}

	/**
	 * @param message
	 */
	public ConsultaParametroException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ConsultaParametroException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConsultaParametroException(String message, Throwable cause) {
		super(message, cause);
	}

}
