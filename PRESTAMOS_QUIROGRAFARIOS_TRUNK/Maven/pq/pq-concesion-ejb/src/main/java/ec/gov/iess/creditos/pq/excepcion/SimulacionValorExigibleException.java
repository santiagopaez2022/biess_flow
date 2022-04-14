package ec.gov.iess.creditos.pq.excepcion;

public class SimulacionValorExigibleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 15464897905664325L;

	private String codigo;
	private String mensaje;

	public SimulacionValorExigibleException() {
	}

	public SimulacionValorExigibleException(String message, String codigo) {
		super(message);
		this.mensaje = message;
		this.codigo = codigo;
	}

	public SimulacionValorExigibleException(Throwable cause) {
		super(cause);
	}

	public SimulacionValorExigibleException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
