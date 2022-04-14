/**
 * 
 */
package ec.gov.iess.planillaspq.exceptions;

import javax.ejb.ApplicationException;
/**
 * @author palvarez
 *
 */
@ApplicationException(rollback=true)
public class CambioEstadoDividendoException extends Exception {
	private static final long serialVersionUID = 1L;

	public CambioEstadoDividendoException() {
		super();
		
	}

	public CambioEstadoDividendoException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CambioEstadoDividendoException(String message) {
		super(message);
		
	}

	public CambioEstadoDividendoException(Throwable cause) {
		super(cause);

	
	}

}
