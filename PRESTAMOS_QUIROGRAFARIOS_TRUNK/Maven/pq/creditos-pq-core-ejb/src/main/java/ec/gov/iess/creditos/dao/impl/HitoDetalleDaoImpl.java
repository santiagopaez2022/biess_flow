/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.HitoDetalleDao;
import ec.gov.iess.creditos.modelo.persistencia.HitoDetalle;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * HitoDetalleDaoImpl.java  DAO de acceso a tabla CRE_CC_HITOSCONTROLDETALLE_TBL
 * 
 * @author geguiguren
 * 
 */
@Stateless
public class HitoDetalleDaoImpl extends GenericEjbDao<HitoDetalle, Long>
		implements HitoDetalleDao {

	private LoggerBiess log = LoggerBiess.getLogger(HitoDetalleDaoImpl.class);

	public HitoDetalleDaoImpl() {
		super(HitoDetalle.class);
	}

	/**
	 * Obtiene el listado de Hitos segun un tipo de Solicitud (Producto)
	 * determinado
	 * 
	 * @param idTipoSolicitud
	 *            Long que representa el tipo de producto a obtener
	 * @return List<HitoDetalle> representa el listado de Sub-Itos o tareas
	 *         correspondientes a ese tipo de solicitud
	 * */
	@SuppressWarnings("unchecked")
	public List<HitoDetalle> detalleHitosPorTipoSolicitud(Long idTipoSolicitud) {
		log.debug(" Consulta Detalle de Hitos por tipo solicitud : "
				+ idTipoSolicitud);

		Query q = em.createNamedQuery("HitoDetalle.byTipoSolicitud");
		q.setParameter("idTipoSolicitud", idTipoSolicitud);
		List<HitoDetalle> lista = q.getResultList();
		lista.size();
		return lista;
	}
}
