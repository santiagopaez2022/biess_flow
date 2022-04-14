/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.CatalogoTipoPrestacionDao;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoTipoPrestacion;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Clase para implementar métodos para obtener tipos de prestaciones de
 * jubilados Ph </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 14/07/2011 $]
 *          </p>
 */
@Stateless
public class CatalogoTipoPrestacionDaoImpl extends
		GenericEjbDao<CatalogoTipoPrestacion, Long> implements CatalogoTipoPrestacionDao {

	public CatalogoTipoPrestacionDaoImpl() {
		super(CatalogoTipoPrestacion.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.CatalogoTipoPrestacionDao#
	 * obtenerTiposPrestacionActivos()
	 */
	@SuppressWarnings("unchecked")
	public List<CatalogoTipoPrestacion> obtenerTiposPrestacionActivos() {
		Query q = em.createNamedQuery("CatalogoTipoPrestacion.obtenerActivos");
		return q.getResultList();
	}
}
