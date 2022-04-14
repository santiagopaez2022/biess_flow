/**
 * 
 */
package ec.gov.iess.planillaspq.exceptions;

/**
 * @author palvarez
 *
 */
public class PlanillaException extends Exception {
	private static final long serialVersionUID = 1L;

	public PlanillaException() {
		super();
		
	}

	public PlanillaException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PlanillaException(String message) {
		super(message);
		
	}

	public PlanillaException(Throwable cause) {
		super(cause);

	
	}

}
