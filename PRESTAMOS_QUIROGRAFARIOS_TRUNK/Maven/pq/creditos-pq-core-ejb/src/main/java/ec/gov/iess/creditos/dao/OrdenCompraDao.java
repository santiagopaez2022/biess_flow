/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.OrdenCompra;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * <b> Interface para la orden de compra. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:36:43 $]
 *          </p>
 */
@Local
public interface OrdenCompraDao extends GenericDao<OrdenCompra, String> {
	/**
	 * 
	 * <b> Consulta las ordenes de compra por estado </b>
	 * <p>
	 * [Author: cbastidas, Date: 08/04/2011]
	 * </p>
	 * 
	 * @return List<OrdenCompra> Lista de objetos ordenes de compra
	 */
	public List<OrdenCompra> consultarOrdenCompra();
	
	/**
	 * <b>
	 * Obtiene una orden de compra en estado "entregada" segun el código proporcionado.
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 17/05/2011]</p>
	 *
	 * @param codigoOrden
	 * @return
	 */ 
	public OrdenCompra consultarOrdenPorCodigo(String codigoOrden);
	
	/**
	 * <b>
	 * Consulta las ordenes de compra por estado determinado.
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 16/05/2011]</p>
	 *
	 * @param catalogoOrden
	 * @param estadoOrden
	 * @return List<OrdenCompra> Lista de objetos ordenes de compra
	 */ 
	public List<OrdenCompra> consultarOrdenCompra(String catalogoOrden, String estadoOrden );
	
	/**
	 * <b>
	 * Consulta las ordenes de compra por estado y fecha determinada.
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 17/05/2011]</p>
	 *
	 * @param catalogoOrden
	 * @param estadoOrden
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */ 
	public List<OrdenCompra> consultarOrdenFecha(String catalogoOrden, String estadoOrden, Date fechaDesde,
			Date fechaHasta);
	
}