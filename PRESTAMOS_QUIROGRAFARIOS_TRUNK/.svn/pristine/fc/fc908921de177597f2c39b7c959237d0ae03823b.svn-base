package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion para manejo de errores en consulta de tabla de amortizacion sac
 * 
 * @author hugo.mora
 * @date 2018/09/05
 *
 */
public class TablaAmortizacionSacException extends Exception {

	private static final long serialVersionUID = 15464897905664985L;

	private String codigo;
	private String mensaje;

	public TablaAmortizacionSacException() {
		super();
	}

	public TablaAmortizacionSacException(String message, String codigo) {
		super(message);
		this.mensaje = message;
		this.codigo = codigo;
	}

	public TablaAmortizacionSacException(Throwable throwable) {
		super(throwable);
	}

	public TablaAmortizacionSacException(String message, Throwable throwable) {
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
