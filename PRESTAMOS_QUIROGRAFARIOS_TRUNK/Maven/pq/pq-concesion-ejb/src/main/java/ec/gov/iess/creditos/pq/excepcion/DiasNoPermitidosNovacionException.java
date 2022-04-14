package ec.gov.iess.creditos.pq.excepcion;

/**
 * Exception para controlar los dias no permitidos para realizar novaciones
 * 
 * @author hugo.mora
 * @date 2017/07/06
 *
 */
public class DiasNoPermitidosNovacionException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Instancia un nuevo DiasNoPermitidosNovacionException.
	 */
	public DiasNoPermitidosNovacionException() {
		super();
	}

	/**
	 * Instancia un nuevo DiasNoPermitidosNovacionException.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 */
	public DiasNoPermitidosNovacionException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Instancia un nuevo DiasNoPermitidosNovacionException.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public DiasNoPermitidosNovacionException(final String arg0) {
		super(arg0);
	}

	/**
	 * Instancia un nuevo DiasNoPermitidosNovacionException.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public DiasNoPermitidosNovacionException(final Throwable arg0) {
		super(arg0);
	}

}
