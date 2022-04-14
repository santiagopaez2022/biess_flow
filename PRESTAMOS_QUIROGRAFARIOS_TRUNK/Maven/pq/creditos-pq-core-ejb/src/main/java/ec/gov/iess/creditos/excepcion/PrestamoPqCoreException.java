package ec.gov.iess.creditos.excepcion;

/**
 * Excepcion para errores con la entidad Prestamo
 * 
 * @author hugo.mora
 *
 */
public class PrestamoPqCoreException extends Exception {

	private static final long serialVersionUID = 1L;

	public PrestamoPqCoreException() {
	}

	public PrestamoPqCoreException(String message) {
		super(message);
	}

	public PrestamoPqCoreException(Throwable cause) {
		super(cause);
	}

	public PrestamoPqCoreException(String message, Throwable cause) {
		super(message, cause);
	}

}
