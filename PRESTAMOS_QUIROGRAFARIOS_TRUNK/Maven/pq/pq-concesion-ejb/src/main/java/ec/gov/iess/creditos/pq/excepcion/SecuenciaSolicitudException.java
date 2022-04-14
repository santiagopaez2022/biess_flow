/*
 * Copyright 2015 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.excepcion;

public class SecuenciaSolicitudException extends Exception {

	private static final long serialVersionUID = 1L;

	public SecuenciaSolicitudException() {
		super();
	}

	public SecuenciaSolicitudException(String message) {
		super(message);
	}

	public SecuenciaSolicitudException(Throwable throwable) {
		super(throwable);
	}

	public SecuenciaSolicitudException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
