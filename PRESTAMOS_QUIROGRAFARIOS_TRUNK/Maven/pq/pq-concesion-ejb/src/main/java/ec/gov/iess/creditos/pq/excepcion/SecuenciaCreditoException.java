/*
 * Copyright 2015 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.excepcion;

public class SecuenciaCreditoException extends Exception {

	private static final long serialVersionUID = 1L;

	public SecuenciaCreditoException() {
		super();
	}

	public SecuenciaCreditoException(String message) {
		super(message);
	}

	public SecuenciaCreditoException(Throwable throwable) {
		super(throwable);
	}

	public SecuenciaCreditoException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
