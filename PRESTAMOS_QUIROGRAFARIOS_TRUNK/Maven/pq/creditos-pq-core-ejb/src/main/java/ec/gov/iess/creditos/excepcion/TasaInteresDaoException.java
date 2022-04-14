/**
 * 
 */

package ec.gov.iess.creditos.excepcion;

/**
 * @author cvillarreal
 *
 */
public class TasaInteresDaoException extends Exception {

	private static final long serialVersionUID = 1610054038721637522L;

	/**
	 * 
	 */
	public TasaInteresDaoException() {
	}

	/**
	 * @param arg0
	 */
	public TasaInteresDaoException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TasaInteresDaoException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TasaInteresDaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}