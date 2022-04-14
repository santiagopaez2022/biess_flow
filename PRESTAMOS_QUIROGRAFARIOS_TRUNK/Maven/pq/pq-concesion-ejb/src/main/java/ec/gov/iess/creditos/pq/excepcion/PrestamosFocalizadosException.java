package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion para prestamos pq focalizados
 * 
 * @author hugo.mora
 *
 */
public class PrestamosFocalizadosException extends Exception {

	private static final long serialVersionUID = 1L;

	public PrestamosFocalizadosException() {
		super();
	}

	public PrestamosFocalizadosException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public PrestamosFocalizadosException(final String arg0) {
		super(arg0);
	}

	public PrestamosFocalizadosException(final Throwable arg0) {
		super(arg0);
	}

}
