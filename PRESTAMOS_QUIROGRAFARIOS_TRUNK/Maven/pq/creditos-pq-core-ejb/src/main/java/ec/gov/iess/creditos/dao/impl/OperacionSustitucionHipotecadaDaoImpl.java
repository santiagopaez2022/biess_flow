/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.OperacionSustitucionHipotecaDao;
import ec.gov.iess.creditos.modelo.persistencia.OperacionSustitucionHipoteca;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 17/06/2011 $]
 *          </p>
 */
@Stateless
public class OperacionSustitucionHipotecadaDaoImpl extends GenericEjbDao<OperacionSustitucionHipoteca, Long> implements
		OperacionSustitucionHipotecaDao {

	public OperacionSustitucionHipotecadaDaoImpl() {
		super(OperacionSustitucionHipoteca.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.OperacionSustitucionHipotecaDao#obtenerPorSolicitud
	 * (java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<OperacionSustitucionHipoteca> obtenerPorSolicitud(Long numsolser, Long codtipsolser) {
		Query q = em.createNamedQuery("OperacionSustitucionHipoteca.obtenerPorSolicitud");
		q.setParameter("numsolser", numsolser);
		q.setParameter("codtipsolser", codtipsolser);
		return q.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.OperacionSustitucionHipotecaDao#
	 * obtenerViviendaEnTramite(java.util.List, java.lang.String,
	 * java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<OperacionSustitucionHipoteca> obtenerViviendaEnTramite(List<String> estadosNoTramite, String cedula,
			Long codigoIfi) {
		Query q = em.createNamedQuery("OperacionSustitucionHipoteca.obtenerViviendaEnTramite");
		q.setParameter("estados", estadosNoTramite);
		q.setParameter("cedula", cedula);
		q.setParameter("codigoIfi", codigoIfi);
		return q.getResultList();
	}
}
