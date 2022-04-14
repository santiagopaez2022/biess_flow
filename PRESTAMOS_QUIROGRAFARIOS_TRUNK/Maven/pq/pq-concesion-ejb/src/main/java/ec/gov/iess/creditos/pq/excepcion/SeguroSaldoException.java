/**
 * 
 */

package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author cvillarreal
 *
 */
public class SeguroSaldoException extends Exception {

	private static final long serialVersionUID = 3274141294027291381L;

	/**
	 * 
	 */
	public SeguroSaldoException() {
	}

	/**
	 * @param arg0
	 */
	public SeguroSaldoException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public SeguroSaldoException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SeguroSaldoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}