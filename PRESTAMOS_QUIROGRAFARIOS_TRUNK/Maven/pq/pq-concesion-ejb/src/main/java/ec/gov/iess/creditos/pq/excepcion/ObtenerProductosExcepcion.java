/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.excepcion;


/**
 * 
 * <b>
 * Exception para el manejo de productos
 * </b>
 *  
 * @author cbastidas
 * @version $Revision: 1.2 $ <p>[$Author: smanosalvas $, $Date: 2011/05/03 13:29:24 $]</p>
 */
public class ObtenerProductosExcepcion extends Exception {

	private static final long serialVersionUID = 1950571753244835843L;

	public ObtenerProductosExcepcion(){
		super();
	}

	public ObtenerProductosExcepcion(String message){
		super(message);
	}

	public ObtenerProductosExcepcion(Throwable throwable){
		super(throwable);
	}

	public ObtenerProductosExcepcion(String message, Throwable throwable){
		super(message, throwable);
	}
}
