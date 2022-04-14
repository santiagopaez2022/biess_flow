/**
 * 
 */
package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author cvillarreal
 *
 */
public class TasaActuarialCeroException extends TasaActuarialException {

	private static final long serialVersionUID = -9104516506286450781L;

	/**
	 * 
	 */
	public TasaActuarialCeroException() {
	}

	/**
	 * @param arg0
	 */
	public TasaActuarialCeroException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TasaActuarialCeroException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TasaActuarialCeroException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}