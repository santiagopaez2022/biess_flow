package ec.fin.biess.unlock.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.fin.biess.unlock.dao.BloqueoUsuarioDao;
import ec.fin.biess.unlock.modelo.BloqueoUsuario;

@Stateless
public class BloqueoUsuarioDaoImpl extends GenericDaoImpl<BloqueoUsuario> implements BloqueoUsuarioDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4594715347623609165L;

	@PersistenceContext(unitName = "unlock-PU")
	private EntityManager em;

	public BloqueoUsuarioDaoImpl() {
		super(BloqueoUsuario.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.unlock.dao.BloqueoUsuarioDao#getBloqueoUsuario(java.lang.String)
	 */
	@Override
	public BloqueoUsuario getBloqueoUsuario(final String cedula) {

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT bu FROM BloqueoUsuario bu WHERE bu.cedula=:cedula AND bu.indicadorCuentaBloqueda = 'S' ");
			Query query = em.createQuery(sql.toString());
			query.setParameter("cedula", cedula);
			return (BloqueoUsuario) query.getSingleResult();
		} catch (NoResultException e) {
			/**
			 * Si no existe registro de BloqueoUsuario retorna NULL.
			 */
			return null;
		}

	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.unlock.dao.BloqueoUsuarioDao#getBloqueoUsuarioPorId(java.lang.String)
	 */
	@Override
	public BloqueoUsuario getBloqueoUsuarioPorId(final String cedula) {
		return em.find(BloqueoUsuario.class, cedula);
	}

}
