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
public class BloquearCreditoExcepcion extends Exception {

	private static final long serialVersionUID = 4702200882776678858L;

	public BloquearCreditoExcepcion() {
		super();
	}

	public BloquearCreditoExcepcion(String message) {
		super(message);
	}

	public BloquearCreditoExcepcion(Throwable throwable) {
		super(throwable);
	}

	public BloquearCreditoExcepcion(String message, Throwable throwable) {
		super(message, throwable);
	}
}
