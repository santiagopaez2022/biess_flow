package ec.gov.iess.creditos.pq.excepcion;

public class PHEnTramiteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 42234411228899L;

	public PHEnTramiteException() {
		super();
	}
	
	public PHEnTramiteException(String message) {
		super(message);
	}

	public PHEnTramiteException(Throwable throwable) {
		super(throwable);
	}

	public PHEnTramiteException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
