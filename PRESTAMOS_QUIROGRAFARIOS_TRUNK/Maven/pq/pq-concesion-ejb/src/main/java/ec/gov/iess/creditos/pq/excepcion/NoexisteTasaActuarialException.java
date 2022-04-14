/**
 * 
 */

package ec.gov.iess.creditos.pq.excepcion;


/**
 * @author cvillarreal
 *
 */
public class NoexisteTasaActuarialException extends TasaActuarialException {

	private static final long serialVersionUID = 8544811912025399435L;

	/**
	 * 
	 */
	public NoexisteTasaActuarialException() {
	}

	/**
	 * @param arg0
	 */
	public NoexisteTasaActuarialException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public NoexisteTasaActuarialException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public NoexisteTasaActuarialException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}