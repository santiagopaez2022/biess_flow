package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.CatalogoDao;
import ec.gov.iess.creditos.modelo.persistencia.Catalogo;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author Ricardo Tituaña 03/10/2011
 * 
 */

@Stateless
public class CatalogoDaoImpl
extends
GenericEjbDao<Catalogo, Long>implements CatalogoDao {
	
	private LoggerBiess log = LoggerBiess.getLogger(CatalogoDaoImpl.class);

	
	public CatalogoDaoImpl() {
		super(Catalogo.class);
		// TODO Auto-generated constructor stub
	}


	@SuppressWarnings("unchecked")
	public List<Catalogo> getAll() {
		log.debug(" Consulta de los catalogos...");

		Query q = em
				.createNamedQuery("Catalogo.getAll");

		return q.getResultList();
	}
	
	/**
	 * @see ec.gov.iess.creditos.dao.CatalogoDao#
	 *      getListaCatalogoDestinoPorCodigoTipoProducto(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<Catalogo> getListaCatalogoDestinoPorCodigoTipoProducto(
			Long codigoTipoProducto) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT c FROM Catalogo c ");
			sql.append(" WHERE c.codigoTipoProducto=:codigoTipoProducto");
			sql.append(" AND c.indicadorActivo=:indicadorActivo");
			Query query = em.createQuery(sql.toString());
			query.setParameter("codigoTipoProducto", codigoTipoProducto);
			query.setParameter("indicadorActivo", "1");
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
