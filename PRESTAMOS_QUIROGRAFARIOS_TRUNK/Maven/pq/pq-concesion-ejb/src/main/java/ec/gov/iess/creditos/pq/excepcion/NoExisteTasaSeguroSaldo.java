/**
 * 
 */

package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author cvillarreal
 *
 */
public class NoExisteTasaSeguroSaldo extends SeguroSaldoException {

	private static final long serialVersionUID = 9089928241703232424L;

	/**
	 * 
	 */
	public NoExisteTasaSeguroSaldo() {
	}

	/**
	 * @param arg0
	 */
	public NoExisteTasaSeguroSaldo(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public NoExisteTasaSeguroSaldo(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public NoExisteTasaSeguroSaldo(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}