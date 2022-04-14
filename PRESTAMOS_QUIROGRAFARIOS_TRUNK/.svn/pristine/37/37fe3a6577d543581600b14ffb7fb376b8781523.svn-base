/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.CatalogoTipoSeguroDao;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoTipoSeguro;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Clase para implementar métodos de tipos de seguros. </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 14/07/2011 $]
 *          </p>
 */
@Stateless
public class CatalogoTipoSeguroDaoImpl extends
		GenericEjbDao<CatalogoTipoSeguro, Long> implements CatalogoTipoSeguroDao {

	public CatalogoTipoSeguroDaoImpl() {
		super(CatalogoTipoSeguro.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.CatalogoTipoSeguroDao#obtenerTiposSeguroActivos
	 * ()
	 */
	@SuppressWarnings("unchecked")
	public List<CatalogoTipoSeguro> obtenerTiposSeguroActivos() {
		Query q = em.createNamedQuery("CatalogoTipoSeguro.obtenerActivos");
		return q.getResultList();
	}
}
