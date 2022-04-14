package ec.fin.biess.unlock.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.fin.biess.unlock.dao.BloqueoUsuarioHistoricoDao;
import ec.fin.biess.unlock.modelo.BloqueoUsuarioHistorico;

@Stateless
public class BloqueoUsuarioHistoricoDaoImpl extends GenericDaoImpl<BloqueoUsuarioHistorico> implements BloqueoUsuarioHistoricoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4594715347623609165L;

	@PersistenceContext(unitName = "unlock-PU")
	private EntityManager em;

	public BloqueoUsuarioHistoricoDaoImpl() {
		super(BloqueoUsuarioHistorico.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BloqueoUsuarioHistorico> getListaBloqueoUsuarioHistorico(String cedula) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT bu FROM BloqueoUsuarioHistorico bu WHERE bu.cedula=:cedula");
			Query query = em.createQuery(sql.toString());
			query.setParameter("cedula", cedula);
			return query.getResultList();
		} catch (NoResultException e) {
			/**
			 * Si no existe registro de bloqueo retorna NULL.
			 */
			return null;
		}
	}

}
