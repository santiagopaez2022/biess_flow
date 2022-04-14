/**
 * 
 */

package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author cvillarreal
 *
 */
public class TasaActurialDuplicadaException extends TasaActuarialException {

	private static final long serialVersionUID = 9036643396872971762L;

	/**
	 * 
	 */
	public TasaActurialDuplicadaException() {
	}

	/**
	 * @param arg0
	 */
	public TasaActurialDuplicadaException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TasaActurialDuplicadaException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TasaActurialDuplicadaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}