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
public class AnulaPlanillaHostException extends Exception {
	private static final long serialVersionUID = 1L;

	public AnulaPlanillaHostException() {
		super();
		
	}

	public AnulaPlanillaHostException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public AnulaPlanillaHostException(String message) {
		super(message);
		
	}

	public AnulaPlanillaHostException(Throwable cause) {
		super(cause);

	
	}

}
