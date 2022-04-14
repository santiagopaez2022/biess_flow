package ec.gov.iess.creditos.pq.excepcion;

public class MasDeUnSolicitanteException extends Exception {
	
	private String cedula;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2977726996606244052L;

	public MasDeUnSolicitanteException() {
		// TODO Auto-generated constructor stub
	}

	public MasDeUnSolicitanteException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MasDeUnSolicitanteException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MasDeUnSolicitanteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

}
