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
public class AjustaPlanillaHostException extends Exception {
	private static final long serialVersionUID = 1L;

	public AjustaPlanillaHostException() {
		super();
		
	}

	public AjustaPlanillaHostException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public AjustaPlanillaHostException(String message) {
		super(message);
		
	}

	public AjustaPlanillaHostException(Throwable cause) {
		super(cause);

	
	}

}
