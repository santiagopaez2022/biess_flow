package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion para manejo de errores en el consumo de servicios de cor negocio de PQ Exigible
 * 
 * @author hugo.mora
 * @date 2018/09/03
 *
 */
public class CreacionOperacionSacException extends Exception {

	private static final long serialVersionUID = -244940007890646L;

	private String mensaje;
	private String codigo;
	
	public CreacionOperacionSacException() {
		super();
	}

	public CreacionOperacionSacException(String message, String codigo) {
		super(message);
		this.mensaje = message;
		this.codigo = codigo;
	}

	public CreacionOperacionSacException(Throwable throwable) {
		super(throwable);
	}

	public CreacionOperacionSacException(String message, Throwable throwable) {
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
