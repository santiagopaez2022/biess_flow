/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.DetalleBienesSolicitudDao;
import ec.gov.iess.creditos.modelo.persistencia.DetalleBienesSolicitud;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author Jenny Sanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Sanchez $, $Date: 07/06/2011 $]
 *          </p>
 */
@Stateless
public class DetalleBienesSolicitudDaoImpl extends GenericEjbDao<DetalleBienesSolicitud, Long> implements
		DetalleBienesSolicitudDao {

	public DetalleBienesSolicitudDaoImpl() {
		super(DetalleBienesSolicitud.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.DetalleBienesSolicitudDao#obtenerBienesPorSolicitud
	 * (java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<DetalleBienesSolicitud> obtenerBienesPorSolicitud(Long numsolser, Long codtipsolser) {
		Query q = em.createNamedQuery("DetalleBienesSolicitud.obtenerPorCodigoSolicitud");
		q.setParameter("numsolser", numsolser);
		q.setParameter("codtipsolser", codtipsolser);
		return q.getResultList();
	}
}
