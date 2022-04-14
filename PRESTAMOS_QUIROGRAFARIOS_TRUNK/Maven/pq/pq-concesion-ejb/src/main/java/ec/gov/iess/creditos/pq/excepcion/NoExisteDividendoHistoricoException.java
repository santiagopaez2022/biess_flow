package ec.gov.iess.creditos.pq.excepcion;

public class NoExisteDividendoHistoricoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoExisteDividendoHistoricoException() {
		super();
	}

	public NoExisteDividendoHistoricoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoExisteDividendoHistoricoException(String message) {
		super(message);
	}

	public NoExisteDividendoHistoricoException(Throwable cause) {
		super(cause);
	}

}
