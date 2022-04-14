/**
 * 
 */
package ec.gov.iess.planillaspq.exceptions;
import javax.ejb.ApplicationException;
/**
 * @author palvarez
 *
 */
//@ApplicationException(rollback=true)
public class CambioEstadoComprobanteException extends Exception {
	private static final long serialVersionUID = 1L;

	public CambioEstadoComprobanteException() {
		super();
		
	}

	public CambioEstadoComprobanteException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CambioEstadoComprobanteException(String message) {
		super(message);
		
	}

	public CambioEstadoComprobanteException(Throwable cause) {
		super(cause);

	
	}

}
