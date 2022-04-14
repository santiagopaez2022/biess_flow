/**
 * 
 */
package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author cvillarreal
 *
 */
public class CondicionCalculoException extends Exception {

	private static final long serialVersionUID = -6082325220467558835L;

	/**
	 * 
	 */
	public CondicionCalculoException() {
	}

	/**
	 * @param arg0
	 */
	public CondicionCalculoException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public CondicionCalculoException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CondicionCalculoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}