/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.ActividadEconomicaDao;
import ec.gov.iess.creditos.modelo.persistencia.ActividadEconomica;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author Daniel Cardenas
 * 
 */
/**
 * @author daniel
 * 
 */
@Stateless
public class ActividadEconomicaDaoImpl extends
		GenericEjbDao<ActividadEconomica, String> implements
		ActividadEconomicaDao {

	private LoggerBiess log = LoggerBiess.getLogger(ActividadEconomicaDaoImpl.class);

	public ActividadEconomicaDaoImpl() {
		super(ActividadEconomica.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.ActividadEconomicaDao#getAllOrderByDescripcion()
	 */
	@SuppressWarnings("unchecked")
	public List<ActividadEconomica> getAllParentOrderByDescripcion() {
		log.debug(" Consulta de las actividades economicas...");

		Query q = em
				.createNamedQuery("ActividadEconomica.getAllOrderByDescripcionSoloPadres");

		return q.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.ActividadEconomicaDao#getAllOrderByDescripcion(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ActividadEconomica> getAllOrderByDescripcion(String codigoPadre) {
		log.debug(" Consulta de las actividades economicas de:" + codigoPadre);

		Query q = em
				.createNamedQuery("ActividadEconomica.getAllOrderByDescripcionHijos");
		q.setParameter("codigoPadre", codigoPadre);

		return q.getResultList();
	}
}
