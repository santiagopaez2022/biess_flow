package ec.gov.iess.creditos.excepcion;

public class DebitoAutomaticoExcepcion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DebitoAutomaticoExcepcion() {
	}

	public DebitoAutomaticoExcepcion(String message) {
		super(message);
	}

	public DebitoAutomaticoExcepcion(Throwable cause) {
		super(cause);
	}

	public DebitoAutomaticoExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

}
