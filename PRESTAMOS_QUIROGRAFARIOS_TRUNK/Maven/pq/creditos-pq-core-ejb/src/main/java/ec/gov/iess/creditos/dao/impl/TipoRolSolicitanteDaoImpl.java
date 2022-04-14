/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.TipoRolSolicitanteDao;
import ec.gov.iess.creditos.modelo.persistencia.TipoRolSolicitante;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal 03/10/2011
 * 
 */
@Stateless
public class TipoRolSolicitanteDaoImpl extends
		GenericEjbDao<TipoRolSolicitante, Long> implements
		TipoRolSolicitanteDao {

	/**
	 * @param type
	 */
	public TipoRolSolicitanteDaoImpl() {
		super(TipoRolSolicitante.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.TipoRolSolicitanteDao#obtenerListaRolesSolicistante
	 * (java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<TipoRolSolicitante> obtenerListaRolesSolicistante(List<Long> listaProductos) {
		Query q = em.createNamedQuery("TipoRolSolicitante.obtenerPorTipoProducto");
		q.setParameter("listaProductos", listaProductos);
		return q.getResultList();
	}
}
