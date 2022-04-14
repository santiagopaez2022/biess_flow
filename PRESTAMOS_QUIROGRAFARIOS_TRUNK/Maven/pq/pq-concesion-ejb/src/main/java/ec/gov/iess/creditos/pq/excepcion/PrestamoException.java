/*
 * PrestamoException.java
 * 
 * Copyright (c) 2011 BIESS.
 * Todos los derechos reservados.
 */

package ec.gov.iess.creditos.pq.excepcion;

/**
 * Class PrestamoException.
 *
 * @author pjarrin
 * @revision $Revision: 1.1 $$
 */
public class PrestamoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Instancia un nuevo prestamo exception.
	 */
	public PrestamoException() {
		super();
	}

	/**
	 * Instancia un nuevo prestamo exception.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public PrestamoException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Instancia un nuevo prestamo exception.
	 *
	 * @param arg0 the arg0
	 */
	public PrestamoException(final String arg0) {
		super(arg0);
	}

	/**
	 * Instancia un nuevo prestamo exception.
	 *
	 * @param arg0 the arg0
	 */
	public PrestamoException(final Throwable arg0) {
		super(arg0);
	}
}