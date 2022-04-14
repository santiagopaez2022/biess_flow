package ec.gov.iess.creditos.pq.excepcion;

/**
 * Manejo de excepciones para aportaciones consecutivas y acumuladas
 * 
 * @author hugo.mora
 * @date 2016/07/14
 *
 */
public class AportacionesException extends Exception {

	private static final long serialVersionUID = 1L;

	public AportacionesException() {
		super();
	}

	public AportacionesException(String message) {
		super(message);
	}

	public AportacionesException(Throwable throwable) {
		super(throwable);
	}

	public AportacionesException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
