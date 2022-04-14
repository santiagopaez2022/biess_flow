/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.planillaspq.servicio.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.OrdenCompraDao; 
import ec.gov.iess.creditos.modelo.persistencia.OrdenCompra;
import ec.gov.iess.planillaspq.servicio.OrdenCompraServicio;
import ec.gov.iess.planillaspq.servicio.OrdenCompraServicioRemote;

/**
 * <b> Implementación del Servicio para manipulación de Ordenes de Compra del
 * Core de Creditos. </b>
 * 
 * @author Gabriel Eguiguren
 * @version $Revision: 1.1.2.1 $
 *          <p>
 *          [$Author: geguiguren $, $Date: 2011/05/25 15:24:28 $]
 *          </p>
 */
@Stateless
public class OrdenCompraServicioImpl implements OrdenCompraServicio, OrdenCompraServicioRemote {

	@EJB
	OrdenCompraDao ordenCompraDao;

	private static final LoggerBiess log = LoggerBiess.getLogger(OrdenCompraServicioImpl.class);

	/* (non-Javadoc)
	* @see ec.gov.iess.planillaspq.servicio.OrdenCompraServicio#consultarOrdenes(java.lang.String, java.lang.String)
	*/ 
	public List<OrdenCompra> consultarOrdenes(String catalogoOrden, String estadoOrden) {

		List<OrdenCompra> lista = null;

		try {
			lista = ordenCompraDao.consultarOrdenCompra(catalogoOrden, estadoOrden);
		} catch (Exception e) {
			log.error("ERROR al obtener listado de ordenes:" + e);
		}

		return lista;
	}

	/* (non-Javadoc)
	* @see ec.gov.iess.planillaspq.servicio.OrdenCompraServicio#consultarOrdenPorCodigo(java.lang.String)
	*/ 
	public OrdenCompra consultarOrdenPorCodigo(String codigoOrden) {

		OrdenCompra orden = null;

		try {
			orden = ordenCompraDao.consultarOrdenPorCodigo(codigoOrden);
		} catch (Exception e) {
			log.error("ERROR al obtener listado de ordenes:" + e);
		}

		return orden;
	}

	/* (non-Javadoc)
	* @see ec.gov.iess.planillaspq.servicio.OrdenCompraServicio#consultarOrdenesPorFecha(java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	*/ 
	public List<OrdenCompra> consultarOrdenesPorFecha(String catalogoOrden, String estadoOrden, Date fechaDesde,
			Date fechaHasta) {

		List<OrdenCompra> lista = null;

		try {
			lista = ordenCompraDao.consultarOrdenFecha(catalogoOrden, estadoOrden, fechaDesde, fechaHasta);
		} catch (Exception e) {
			log.error("ERROR al obtener listado de ordenes:" + e);
		}

		return lista;
	}

	/* (non-Javadoc)
	* @see ec.gov.iess.planillaspq.servicio.OrdenCompraServicio#actualizarOrdenEstado(ec.gov.iess.creditos.modelo.persistencia.OrdenCompra, java.lang.String)
	*/ 
	public boolean actualizarOrdenEstado(OrdenCompra orden, String nuevoEstado) {

		try {
			orden.getDetalleCatalogo().getId().setDcCodigo(nuevoEstado);

			log.info("Nuevo estado orden: " + orden.getDetalleCatalogo().getId().getDcCodigo());

			ordenCompraDao.update(orden);

		} catch (Exception e) {
			log.error(e);
			return false;
		}
		return true;
	}
}
