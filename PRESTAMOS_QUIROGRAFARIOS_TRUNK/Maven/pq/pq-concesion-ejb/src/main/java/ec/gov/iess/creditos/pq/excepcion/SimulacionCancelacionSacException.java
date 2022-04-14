package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion usada para el manejo de errores en peticion rest del SAC
 * 
 * @author roberto.guizado
 * @date 2018/11/07
 *
 */
public class SimulacionCancelacionSacException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464616064323367L;

	private String codigo;
	private String mensaje;

	public SimulacionCancelacionSacException() {
		super();
	}

	public SimulacionCancelacionSacException(String message, String codigo) {
		super(message);
		this.codigo = codigo;
		this.mensaje = message;
	}

	public SimulacionCancelacionSacException(Throwable throwable) {
		super(throwable);
	}

	public SimulacionCancelacionSacException(String message, Throwable throwable) {
		super(message, throwable);
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
