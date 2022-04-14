package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion usada para validaciones de monto minimo
 * 
 * @author hugo.mora
 * @date 2017/07/15
 *
 */
public class MontoMinimoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Instancia un nuevo MontoMinimoException.
	 */
	public MontoMinimoException() {
		super();
	}

	/**
	 * Instancia un nuevo MontoMinimoException.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 */
	public MontoMinimoException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Instancia un nuevo MontoMinimoException.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public MontoMinimoException(final String arg0) {
		super(arg0);
	}

	/**
	 * Instancia un nuevo MontoMinimoException.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public MontoMinimoException(final Throwable arg0) {
		super(arg0);
	}

}
