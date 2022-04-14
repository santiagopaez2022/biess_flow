package ec.gov.iess.creditos.pq.excepcion;

public class NoExisteDividendoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoExisteDividendoException() {
	}

	public NoExisteDividendoException(String message) {
		super(message);
	}

	public NoExisteDividendoException(Throwable cause) {
		super(cause);
	}

	public NoExisteDividendoException(String message, Throwable cause) {
		super(message, cause);
	}

}
