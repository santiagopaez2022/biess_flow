/**
 * 
 */

package ec.gov.iess.creditos.pq.excepcion;

import ec.gov.iess.creditos.excepcion.TasaInteresExcepcion;

/**
 * @author cvillarreal
 *
 */
public class TasaActuarialException extends TasaInteresExcepcion {

	private static final long serialVersionUID = 5336512979622742176L;

	/**
	 * 
	 */
	public TasaActuarialException() {
	}

	/**
	 * @param arg0
	 */
	public TasaActuarialException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TasaActuarialException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TasaActuarialException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}