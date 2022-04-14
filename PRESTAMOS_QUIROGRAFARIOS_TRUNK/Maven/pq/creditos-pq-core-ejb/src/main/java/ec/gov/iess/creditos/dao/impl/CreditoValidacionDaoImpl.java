package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateful;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.CreditoValidacionDao;
import ec.gov.iess.creditos.modelo.persistencia.CreditoValidacion;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Dao para acceso a datos de la entidad CreditoValidacionDto
 * 
 * @author hugo.mora
 *
 */
@Stateful
public class CreditoValidacionDaoImpl extends GenericEjbDao<CreditoValidacion, Long> implements CreditoValidacionDao {

	public CreditoValidacionDaoImpl() {
		super(CreditoValidacion.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.CreditoValidacionDao#consultarPorPrestamo(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long)
	 */
	@Override
	public CreditoValidacion consultarPorPrestamo(Long codprecla, Long codpretip, Long numpreafi, Long ordpreafi) {
		Query query = em.createNamedQuery("CreditoValidacion.consultarPorPrestamo");
		query.setParameter("codprecla", codprecla);
		query.setParameter("codpretip", codpretip);
		query.setParameter("numpreafi", numpreafi);
		query.setParameter("ordpreafi", ordpreafi);

		CreditoValidacion creditoValidacion = null;
		try {
			creditoValidacion = (CreditoValidacion) query.getSingleResult();
		} catch (NoResultException e) {
			creditoValidacion = null;
		}
		return creditoValidacion;
	}

}
