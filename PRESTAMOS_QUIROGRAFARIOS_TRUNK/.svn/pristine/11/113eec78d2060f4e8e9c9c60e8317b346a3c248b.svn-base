/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */

package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.DetalleCatalogosDao;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCatalogos;
import ec.gov.iess.creditos.modelo.persistencia.pk.DetalleCatalogosPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author Jenny Sanchez,pjarrin
 * @version $Revision: 1.4 $
 *          <p>
 *          [$Author: etarambis $, $Date: 2011/01/03 21:40:46 $]
 *          </p>
 */
@Stateless
public class DetalleCatalogosDaoImpl extends
		GenericEjbDao<DetalleCatalogos, DetalleCatalogosPK> implements
		DetalleCatalogosDao {

	public DetalleCatalogosDaoImpl() {
		super(DetalleCatalogos.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.dao.GenericDao#load(java.io.Serializable)
	 */

	public DetalleCatalogos load(DetalleCatalogosPK pk) {
		return em.find(DetalleCatalogos.class, pk);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.DetalleCatalogosDao#compruebaParentezcoBeneficiario
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public boolean compruebaParentezcoBeneficiario(String parentezco) {
		StringBuilder sql = new StringBuilder();
		sql.append("select o from DetalleCatalogos o where o.dcEstado = :estado ");
		sql.append("and o.dcValor =:parentezco ");
		sql.append("and o.id.caCatalogo =:catalogo ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("estado", "A");
		query.setParameter("parentezco", parentezco.toUpperCase());
		query.setParameter("catalogo", "PAR_BEN");
		List<DetalleCatalogos> result = query.getResultList();
		if (result == null) {
			return false;
		}
		return result.isEmpty() ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.DetalleCatalogosDao#encontrarDetalleCatalogo
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public DetalleCatalogos encontrarDetalleCatalogo(String codigoCatalogo,
			String codigoDetalleCatalogo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select o from DetalleCatalogos o where o.dcEstado = :estado ");
		sql.append("and o.id.caCatalogo =:codigoCatalogo ");
		sql.append("and o.id.dcCodigo =:codigoDetalleCatalogo ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("estado", "A");
		query.setParameter("codigoCatalogo", codigoCatalogo);
		query.setParameter("codigoDetalleCatalogo", codigoDetalleCatalogo);
		List<DetalleCatalogos> result = query.getResultList();
		return result == null || result.isEmpty() ? null : result.get(0);
	}
}
