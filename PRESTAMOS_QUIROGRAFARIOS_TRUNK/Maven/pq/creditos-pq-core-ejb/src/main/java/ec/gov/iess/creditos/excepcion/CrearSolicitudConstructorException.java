/**
 * 
 */

package ec.gov.iess.creditos.excepcion;

import javax.ejb.ApplicationException;

/**
 * 
 * @author jsanchez
 *
 */
@ApplicationException(rollback = true)
public class CrearSolicitudConstructorException extends Exception {

	private static final long serialVersionUID = 4200691999445992083L;

	public CrearSolicitudConstructorException() {
		super();
	}

	public CrearSolicitudConstructorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CrearSolicitudConstructorException(String arg0) {
		super(arg0);
	}

	public CrearSolicitudConstructorException(Throwable arg0) {
		super(arg0);
	}

}