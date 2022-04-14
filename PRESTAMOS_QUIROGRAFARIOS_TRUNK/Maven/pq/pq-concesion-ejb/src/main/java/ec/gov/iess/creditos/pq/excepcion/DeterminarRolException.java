package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion para manejo de errores al determinar el rol
 * 
 * @author hugo.mora
 * 
 */
public class DeterminarRolException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param arg0
	 */
	public DeterminarRolException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public DeterminarRolException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DeterminarRolException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
