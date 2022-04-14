/**
 * 
 */

package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author cvillarreal
 *
 */
public class NoExistePlazoEndeudamiento extends TablaReferenciaException {

	private static final long serialVersionUID = 3657970748931339579L;

	/**
	 * 
	 */
	public NoExistePlazoEndeudamiento() {
	}

	/**
	 * @param arg0
	 */
	public NoExistePlazoEndeudamiento(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public NoExistePlazoEndeudamiento(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public NoExistePlazoEndeudamiento(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}