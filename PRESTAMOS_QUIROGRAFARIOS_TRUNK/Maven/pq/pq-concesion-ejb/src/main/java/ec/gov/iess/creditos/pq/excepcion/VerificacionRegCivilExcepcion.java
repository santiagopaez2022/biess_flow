/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.excepcion;

/**
 * 
 * <b> Exception para datos del registro civil </b>
 * 
 * @author cbastidas
 * 
 */
public class VerificacionRegCivilExcepcion extends Exception {

	private static final long serialVersionUID = 6989986659339127027L;

	public VerificacionRegCivilExcepcion() {
		super();
	}

	public VerificacionRegCivilExcepcion(String message) {
		super(message);
	}

	public VerificacionRegCivilExcepcion(Throwable throwable) {
		super(throwable);
	}

	public VerificacionRegCivilExcepcion(String message, Throwable throwable) {
		super(message, throwable);
	}
}
