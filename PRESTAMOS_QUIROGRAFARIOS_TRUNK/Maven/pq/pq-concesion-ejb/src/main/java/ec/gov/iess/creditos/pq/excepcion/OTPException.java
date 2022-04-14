package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion para generacion y validacion de OTP
 * 
 * @author hugo.mora
 * @date 2016/11/17
 *
 */
public class OTPException extends Exception {

	private static final long serialVersionUID = 1L;

	public OTPException() {
		super();
	}

	public OTPException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public OTPException(final String arg0) {
		super(arg0);
	}

	public OTPException(final Throwable arg0) {
		super(arg0);
	}

}
