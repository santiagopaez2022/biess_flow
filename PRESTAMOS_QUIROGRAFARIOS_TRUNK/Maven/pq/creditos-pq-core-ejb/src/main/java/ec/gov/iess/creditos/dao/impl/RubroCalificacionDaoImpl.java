/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.RubroCalificacionDao;
import ec.gov.iess.creditos.modelo.persistencia.RubroCalificacion;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 *
 */
@Stateless
public class RubroCalificacionDaoImpl extends GenericEjbDao<RubroCalificacion,Long> implements
		RubroCalificacionDao {
	
	private LoggerBiess log = LoggerBiess.getLogger(RubroCalificacionDaoImpl.class);

	/**
	 * @param type
	 */
	public RubroCalificacionDaoImpl() {
		super(RubroCalificacion.class);
	}

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.dao.RubroCalificacionDao#listaRubrosActivos()
	 */
	@SuppressWarnings("unchecked")
	public List<RubroCalificacion> listaRubrosActivos() {
		log.debug("Lista de rubros");
		Query q = em.createNamedQuery("RubroCalificacion.allActivos");
		
		return q.getResultList();
	}

	
}
