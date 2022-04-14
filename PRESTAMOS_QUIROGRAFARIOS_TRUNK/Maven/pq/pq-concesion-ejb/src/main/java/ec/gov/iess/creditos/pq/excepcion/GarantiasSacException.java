package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion usada para el manejo de errores en garantias del SAC
 * 
 * @author hugo.mora
 * @date 2018/08/31
 *
 */
public class GarantiasSacException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464616064403367L;
	
	private String mensaje;
	private String codigo;

	public GarantiasSacException() {
		super();
	}

	public GarantiasSacException(String message, String codigo) {
		super(message);
		this.mensaje = message;
		this.codigo = codigo;
	}

	public GarantiasSacException(Throwable throwable) {
		super(throwable);
	}

	public GarantiasSacException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
