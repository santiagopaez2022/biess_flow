/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.CatalogoPrestacionSeguroDao;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoPrestacionSeguro;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Implementación de métodos para prestaciones por seguro de jubilados ph
 * </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 15/07/2011 $]
 *          </p>
 */
@Stateless
public class CatalogoPrestacionSeguroDaoImpl extends
		GenericEjbDao<CatalogoPrestacionSeguro, Long> implements CatalogoPrestacionSeguroDao {

	public CatalogoPrestacionSeguroDaoImpl() {
		super(CatalogoPrestacionSeguro.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.CatalogoPrestacionSeguroDao#
	 * obtenerPorPrestacionSeguros(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CatalogoPrestacionSeguro> obtenerPorPrestacionSeguros(String prestacion, String seguro, String seguroAlt) {
		Query q = em.createNamedQuery("CatalogoPrestacionSeguro.obtenerPorPrestacionSeguros");
		q.setParameter("tipoPrestacion", prestacion);
		q.setParameter("tipoSeguro", seguro);
		q.setParameter("tipoSeguroAlt", seguroAlt);
		return q.getResultList();
	}
}
