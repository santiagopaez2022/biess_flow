/**
 * 
 */

package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author cvillarreal
 *
 */
public class EsperanzaVidaException extends Exception {

	private static final long serialVersionUID = -1354926661648931404L;

	/**
	 * 
	 */
	public EsperanzaVidaException() {
	}

	/**
	 * @param arg0
	 */
	public EsperanzaVidaException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public EsperanzaVidaException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public EsperanzaVidaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
