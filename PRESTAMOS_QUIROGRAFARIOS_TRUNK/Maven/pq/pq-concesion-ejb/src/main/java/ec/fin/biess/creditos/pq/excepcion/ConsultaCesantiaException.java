package ec.fin.biess.creditos.pq.excepcion;

public class ConsultaCesantiaException  extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultaCesantiaException() {
		super();
	}

	/**
	 * @param message
	 */
	public ConsultaCesantiaException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ConsultaCesantiaException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConsultaCesantiaException(String message, Throwable cause) {
		super(message, cause);
	}
}
