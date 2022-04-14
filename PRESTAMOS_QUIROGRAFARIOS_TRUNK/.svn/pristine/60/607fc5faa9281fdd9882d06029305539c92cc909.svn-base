/*
 * © Banco del Instituto Ecuatoriano de Seguridad Social 2020
 */
package ec.gov.iess.creditos.pq.excepcion;

/**
 * Excepcion usada para el manejo de errores en peticion rest del SAC.
 *
 * @author roberto.guizado
 * @date 2019/02/27
 */
public class CancelacionOperacionSacException extends Exception {

	/** codigo. */
	private final String codigo;

	/** mensaje. */
	private final String mensaje;

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5464616064323367L;

	/**
	 * Instantiates a new cancelacion operacion sac exception.
	 */
	public CancelacionOperacionSacException() {
		super();
		this.codigo = "";
		this.mensaje = "";
	}

	/**
	 * Instantiates a new cancelacion operacion sac exception.
	 *
	 * @param message the message
	 * @param codigo  the codigo
	 */
	public CancelacionOperacionSacException(final String message, final String codigo) {
		super(message);
		this.codigo = codigo;
		this.mensaje = message;
	}

	/**
	 * Instantiates a new cancelacion operacion sac exception.
	 *
	 * @param throwable the throwable
	 */
	public CancelacionOperacionSacException(final Throwable throwable) {
		super(throwable);
		this.codigo = "";
		this.mensaje = "";
	}

	/**
	 * Instantiates a new cancelacion operacion sac exception.
	 *
	 * @param message   the message
	 * @param throwable the throwable
	 */
	public CancelacionOperacionSacException(final String message, final Throwable throwable) {
		super(message, throwable);
		this.codigo = "";
		this.mensaje = "";
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

}
