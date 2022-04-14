/**
 * 
 */
package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author cvillarreal
 *
 */
public class TasaDesgravamenCeroException extends TasaSeguroDesgravamen {

	private static final long serialVersionUID = -9098858067249054698L;

	/**
	 * 
	 */
	public TasaDesgravamenCeroException() {
	}

	/**
	 * @param arg0
	 */
	public TasaDesgravamenCeroException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TasaDesgravamenCeroException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TasaDesgravamenCeroException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}