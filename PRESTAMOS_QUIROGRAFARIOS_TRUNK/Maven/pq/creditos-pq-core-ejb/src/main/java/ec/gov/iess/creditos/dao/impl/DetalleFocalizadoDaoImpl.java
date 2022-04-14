package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.DetalleFocalizadoDao;
import ec.gov.iess.creditos.modelo.persistencia.DetalleFocalizado;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementacion de dao para acceso a datos de la entidad DetalleFocalizado
 * 
 * @author hugo.mora
 *
 */
@Stateless
public class DetalleFocalizadoDaoImpl extends GenericEjbDao<DetalleFocalizado, Long> implements DetalleFocalizadoDao {

	public DetalleFocalizadoDaoImpl() {
		super(DetalleFocalizado.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DetalleFocalizadoDao#buscarPorPrestamoYEstado(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleFocalizado> buscarPorPrestamoYEstado(Long codprecla, Long codpretip, Long numpreafi, Long ordpreaf, String estado) {
		List<DetalleFocalizado> listaDetalle = null;
		Query query = em.createNamedQuery("DetalleFocalizado.buscarPorPrestamoYEstado");
		query.setParameter("codprecla", codprecla);
		query.setParameter("codpretip", codpretip);
		query.setParameter("numpreafi", numpreafi);
		query.setParameter("ordpreaf", ordpreaf);
		query.setParameter("estado", estado);

		try {
			listaDetalle = query.getResultList();
		} catch (NoResultException e) {
			listaDetalle = null;
		}

		return listaDetalle;
	}

}
