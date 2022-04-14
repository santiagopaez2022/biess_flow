package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion para manejo de errores para prestamos turisticos
 * 
 * @author hugo.mora
 *
 */
public class PrestamosTuristicosException extends Exception {

	private static final long serialVersionUID = 1L;

	public PrestamosTuristicosException() {
		super();
	}

	public PrestamosTuristicosException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public PrestamosTuristicosException(final String arg0) {
		super(arg0);
	}

	public PrestamosTuristicosException(final Throwable arg0) {
		super(arg0);
	}

}
