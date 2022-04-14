/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.OrdenCompraDao;
import ec.gov.iess.creditos.enumeracion.EstadoProveedor;
import ec.gov.iess.creditos.modelo.persistencia.OrdenCompra;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * 
 * <b> Implementación del objeto Dao para acceder a la Orden de Compra. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:36:43 $]
 *          </p>
 */
@Stateless
public class OrdenCompraDaoImpl extends GenericEjbDao<OrdenCompra, String> implements OrdenCompraDao {

	public OrdenCompraDaoImpl() {
		super(OrdenCompra.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.OrdenCompraDao#consultarOrdenCompra()
	 */
	@SuppressWarnings("unchecked")
	public List<OrdenCompra> consultarOrdenCompra() {
		List<OrdenCompra> lista = new ArrayList<OrdenCompra>();

		Query q = em.createNamedQuery("OrdenCompra.consultarOrden");
		q.setParameter("catalogoEstado", EstadoProveedor.EstadoProveedor.getDescripcion());
		q.setParameter("estado", EstadoProveedor.Activo.getDescripcion());
		lista = q.getResultList();
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.OrdenCompraDao#consultarOrdenPorCodigo(java.
	 * lang.String)
	 */
	public OrdenCompra consultarOrdenPorCodigo(String codigoOrden) {
/*
		String sql = "SELECT *  FROM cre_ordcompra_tbl WHERE oc_cod_ord_compra = :codigo "
				+ "AND dc_codigo_estado = 'ENT' ";
		Query q = em.createNativeQuery(sql);
*/
		Query q = em.createNamedQuery("OrdenCompra.consultarOrdenCodigo");
		q.setParameter("codigo", codigoOrden);

		return (OrdenCompra) q.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.OrdenCompraDao#consultarOrdenCompra(java.lang
	 * .String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<OrdenCompra> consultarOrdenCompra(String catalogoOrden, String estadoOrden) {
		List<OrdenCompra> lista = new ArrayList<OrdenCompra>();


		Query q = em.createNamedQuery("OrdenCompra.consultarOrden");
		q.setParameter("catalogoEstado", catalogoOrden);
		q.setParameter("estado", estadoOrden);
		lista = q.getResultList();
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.OrdenCompraDao#consultarOrdenFecha(java.lang
	 * .String, java.lang.String, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<OrdenCompra> consultarOrdenFecha(String catalogoOrden, String estadoOrden, Date fechaDesde,
			Date fechaHasta) {
		List<OrdenCompra> lista = new ArrayList<OrdenCompra>();
/*
		String sql = "SELECT * FROM cre_ordcompra_tbl WHERE dc_codigo_estado = :estado " +
				"AND oc_fec_generacion >= :fechaDesde AND oc_fec_generacion <= :fechaHasta ";
		Query q = em.createNativeQuery(sql);
*/		
		Query q = em.createNamedQuery("OrdenCompra.consultarOrdenFecha");
		q.setParameter("catalogoEstado", catalogoOrden);
		q.setParameter("estado", estadoOrden);
		q.setParameter("fechaDesde", fechaDesde);
		q.setParameter("fechaHasta", fechaHasta);
		lista = q.getResultList();
		return lista;
	}

}
