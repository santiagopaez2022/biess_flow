/**
 * 
 */
package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author cvillarreal
 *
 */
public class NoexisteTasaSeguroDesgravamenException extends
		TasaSeguroDesgravamen {

	private static final long serialVersionUID = -8503277857498710215L;

	/**
	 * 
	 */
	public NoexisteTasaSeguroDesgravamenException() {
	}

	/**
	 * @param arg0
	 */
	public NoexisteTasaSeguroDesgravamenException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public NoexisteTasaSeguroDesgravamenException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public NoexisteTasaSeguroDesgravamenException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}