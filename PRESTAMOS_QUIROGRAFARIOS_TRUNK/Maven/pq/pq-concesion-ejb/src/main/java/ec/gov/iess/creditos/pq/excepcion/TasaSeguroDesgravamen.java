/**
 * 
 */

package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author cvillarreal
 *
 */
public class TasaSeguroDesgravamen extends Exception {

	private static final long serialVersionUID = 8876515198387186738L;

	/**
	 * 
	 */
	public TasaSeguroDesgravamen() {
	}

	/**
	 * @param arg0
	 */
	public TasaSeguroDesgravamen(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TasaSeguroDesgravamen(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TasaSeguroDesgravamen(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}