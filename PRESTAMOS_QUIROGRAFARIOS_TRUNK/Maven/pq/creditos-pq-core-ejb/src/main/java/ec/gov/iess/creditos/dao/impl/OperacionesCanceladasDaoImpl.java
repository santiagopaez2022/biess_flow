/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.OperacionesCanceladasDao;
import ec.gov.iess.creditos.modelo.persistencia.OperacionesCanceladas;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author walter.meza
 *
 */
@Stateless
public class OperacionesCanceladasDaoImpl 
extends GenericEjbDao<OperacionesCanceladas, Long> implements OperacionesCanceladasDao {
	
	/**
	 * 
	 */
	public OperacionesCanceladasDaoImpl() {
		super(OperacionesCanceladas.class);
	}

	public OperacionesCanceladas findByCoddetsol(Long coddetsol) {
		StringBuffer sql = new StringBuffer();
		sql.append("from OperacionesCanceladas o");
		sql.append(" where o.detalleSolicitudCredito.coddetsol = :coddetsol");
		sql.append(" and o.codOpeCan = (select max(oc.codOpeCan) from OperacionesCanceladas oc");
		sql.append(" where oc.detalleSolicitudCredito.coddetsol = :coddetsol)");
		Query query = em.createQuery(sql.toString());
		query.setParameter("coddetsol", coddetsol);
		return (OperacionesCanceladas) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<OperacionesCanceladas> consultarPorNutACancelar(
			Long nutCancelacion) {
		Query q = em
				.createNamedQuery("OperacionesCanceladas.buscarPorNutCancelacion");
		q.setParameter("nutOperacionCanc", nutCancelacion);

		return q.getResultList();
	}

}
