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
public class AnulaPlanillaHlException extends Exception {
	private static final long serialVersionUID = 1L;

	public AnulaPlanillaHlException() {
		super();
		
	}

	public AnulaPlanillaHlException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public AnulaPlanillaHlException(String message) {
		super(message);
		
	}

	public AnulaPlanillaHlException(Throwable cause) {
		super(cause);

	
	}

}
