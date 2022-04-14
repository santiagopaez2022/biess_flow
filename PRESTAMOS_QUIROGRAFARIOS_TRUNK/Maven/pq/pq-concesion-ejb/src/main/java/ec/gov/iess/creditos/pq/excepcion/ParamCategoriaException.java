package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion producida cuando no esta parametrizado una categoria enviada por
 * proveedor
 * 
 * @author paul.sampedro
 *
 */
public class ParamCategoriaException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParamCategoriaException() {
		super();

	}

	public ParamCategoriaException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public ParamCategoriaException(final String arg0) {
		super(arg0);
	}

	public ParamCategoriaException(final Throwable arg0) {
		super(arg0);
	}

}
