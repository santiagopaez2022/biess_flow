package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;


import ec.gov.iess.creditos.dao.CatalogoDao;
import ec.gov.iess.creditos.dao.TipoCuentaBiessDao;
import ec.gov.iess.creditos.modelo.persistencia.Catalogo;
import ec.gov.iess.creditos.modelo.persistencia.TipoCuentaBiess;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitudCredito;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author Ricardo Tituaña 03/10/2011
 * 
 */

@Stateless
public class TipoCuentaBiessDaoImpl extends
		GenericEjbDao<TipoCuentaBiess, Long> implements TipoCuentaBiessDao {

	public TipoCuentaBiessDaoImpl() {
		super(TipoCuentaBiess.class);
	}

	/**
	 * @see ec.gov.iess.creditos.dao.TipoCuentaBiessDao#obtenerTodos()
	 */
	@SuppressWarnings("unchecked")
	public List<TipoCuentaBiess> obtenerTodos() {
		Query q = em.createNamedQuery("TipoCuentaBiess.obtenerTodos");
		return q.getResultList();
	}

	/**
	 * @see ec.gov.iess.creditos.dao.TipoCuentaBiessDao#obtenerPorCodigo(java.lang
	 *      .String)
	 */
	public TipoCuentaBiess obtenerPorCodigo(String codigo) {
		Query query = em.createNamedQuery("TipoCuentaBiess.obtenerPorCodigo");
		query.setParameter("codigo", codigo);
		try {
			return (TipoCuentaBiess) query.getSingleResult();
		} catch (NoResultException e) {
			return new TipoCuentaBiess();
		} catch (NonUniqueResultException e) {
			throw new RuntimeException("Datos duplicados del tipo de cuenta.");
		}
	}
}
