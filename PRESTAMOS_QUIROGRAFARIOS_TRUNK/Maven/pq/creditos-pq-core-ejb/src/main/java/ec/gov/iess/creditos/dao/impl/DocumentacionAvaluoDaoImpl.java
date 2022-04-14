/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.DocumentacionAvaluoDao;
import ec.gov.iess.creditos.modelo.persistencia.DocumentacionAvaluo;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Clase para implementar los métodos de documentación para avaluo
 * 
 * @author jsanchez 03/10/2011
 * 
 */
@Stateless
public class DocumentacionAvaluoDaoImpl extends
		GenericEjbDao<DocumentacionAvaluo, Long> implements
		DocumentacionAvaluoDao {

	/**
	 * Constructor
	 */
	private DocumentacionAvaluoDaoImpl() {
		super(DocumentacionAvaluo.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.DocumentacionAvaluoDao#obtenerPorTipoSolSer(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentacionAvaluo> obtenerPorTipoSolSer(Long codTipoSolSer) {
		Query q = em
				.createNamedQuery("DocumentacionAvaluo.obtenerPorTipoSolSer");
		q.setParameter("codTipoSolSer", codTipoSolSer);
		return (List<DocumentacionAvaluo>) q.getResultList();
	}

	
	
	@SuppressWarnings("unchecked")
	public List<DocumentacionAvaluo> obtenerPorTipoSolSerTipoProd(
			Long codTipoSolSer, String codTipoProd) {
		List<DocumentacionAvaluo> lista = null;
		Query q = em.createNamedQuery("DocumentacionAvaluo.obtenerPorTipoSolserCodTipPro");
		q.setParameter("codTipoSolSer", codTipoSolSer);
		q.setParameter("codTipoProducto", codTipoProd);
		lista =q.getResultList();
		return lista;
	}
}
