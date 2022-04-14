/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.excepcion;


/**
 * 
 * <b>
 * Excepcion para el majedo de la tienda virtual.
 * </b>
 *  
 * @author cbastidas
 * @version $Revision: 1.2 $ <p>[$Author: smanosalvas $, $Date: 2011/05/03 13:29:24 $]</p>
 */
public class TiendaVirtualExcepcion extends Exception {

	private static final long serialVersionUID = 8041609000911393778L;

	public TiendaVirtualExcepcion(){
		super();
	}

	public TiendaVirtualExcepcion(String message){
		super(message);
	}

	public TiendaVirtualExcepcion(Throwable throwable){
		super(throwable);
	}

	public TiendaVirtualExcepcion(String message, Throwable throwable){
		super(message, throwable);
	}
}
