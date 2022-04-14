/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.CatalogoTipoBienDao;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoTipoBien;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementación de métodos para catálogos de tipo de bien
 * 
 * @author jsanchez
 * 
 */
@Stateless
public class CatalogoTipoBienDaoImpl extends
		GenericEjbDao<CatalogoTipoBien, Long> implements CatalogoTipoBienDao {

	/**
	 * Constructor
	 */
	public CatalogoTipoBienDaoImpl() {
		super(CatalogoTipoBien.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.CatalogoTipoBienDao#obtenerPorTipoSolSer(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<CatalogoTipoBien> obtenerPorTipoSolSer(Long tipoSolSer) {
		Query q = em.createNamedQuery("CatalogoTipoBien.obtenerPorTipSolSer");
		q.setParameter("codigoTipoSol", tipoSolSer);
		return (List<CatalogoTipoBien>) q.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.CatalogoTipoBienDao#obtenerPorTipoSolSerCodigoBien(java.lang.Long,
	 *      java.lang.String)
	 */
	public CatalogoTipoBien obtenerPorTipoSolSerCodigoBien(Long tipoSolSer,
			String codigoBien) {
		Query q = em
				.createNamedQuery("CatalogoTipoBien.obtenerPorTipSolSerCodigoBien");
		q.setParameter("codigoTipoSol", tipoSolSer);
		q.setParameter("codigoBien", codigoBien);
		return (CatalogoTipoBien) q.getResultList().get(0);
	}
}
