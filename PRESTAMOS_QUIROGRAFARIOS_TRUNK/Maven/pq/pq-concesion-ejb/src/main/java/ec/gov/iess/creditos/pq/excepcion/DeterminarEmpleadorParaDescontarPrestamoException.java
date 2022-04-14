/**
 * 
 */
package ec.gov.iess.creditos.pq.excepcion;

import javax.ejb.ApplicationException;

/**
 * @author jvaca
 *
 */
@ApplicationException(rollback = true)
public class DeterminarEmpleadorParaDescontarPrestamoException extends
		Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6060635607349073732L;

	/**
	 * 
	 */
	public DeterminarEmpleadorParaDescontarPrestamoException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DeterminarEmpleadorParaDescontarPrestamoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DeterminarEmpleadorParaDescontarPrestamoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DeterminarEmpleadorParaDescontarPrestamoException(String message,
			Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
