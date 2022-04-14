/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.planillaspq.servicio;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.OrdenCompra;


/** 
 * <b>
 * Interfaz del servicio para manejar la entidad OrdenCompra.
 * </b>
 *  
 * @author Gabriel Eguiguren
 * @version $Revision: 1.1.2.1 $ <p>[$Author: geguiguren $, $Date: 2011/05/25 15:24:28 $]</p>
*/ 
@Local
public interface OrdenCompraServicio {

	/**
	 * <b>
	 * Obtiene el detalle del listado de ordenes en un estado determinado.
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 16/05/2011]</p>
	 *
	 * @param catalogoOrden
	 * @param estadoOrden
	 * @return
	 */ 
	public List<OrdenCompra> consultarOrdenes(String catalogoOrden, String estadoOrden);
	
	
	/**
	 * <b>
	 * Obtiene una orden de compra en estado entregado segun el codigo proporcionado.
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 17/05/2011]</p>
	 *
	 * @param codigoOrden
	 * @return
	 */ 
	public OrdenCompra consultarOrdenPorCodigo(String codigoOrden);
	
	/**
	 * <b>
	 * Obtiene el detalle del listado de ordenes para una fecha determinada.
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 17/05/2011]</p>
	 *
	 * @param catalogoOrden
	 * @param estadoOrden
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */ 
	public List<OrdenCompra> consultarOrdenesPorFecha(String catalogoOrden, String estadoOrden, 
			Date fechaDesde, Date fechaHasta);

	/**
	 * <b>
	 * Actualiza el estado de una orden.
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 16/05/2011]</p>
	 *
	 * @param orden
	 * @param nuevoEstado
	 * @return
	 */ 
	public boolean actualizarOrdenEstado(OrdenCompra orden, String nuevoEstado);

}
