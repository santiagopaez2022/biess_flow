/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;

import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.PrestacionConcesionDao;
import ec.gov.iess.creditos.modelo.persistencia.PrestacionConcesion;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Implementación de la cuota de préstamos hipotecario. </b>
 * 
 * @author cvillarreal,cbastidas
 * @version $Revision: 1.8 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/03/28 12:54:35 $]
 *          </p>
 */
@Stateless
public class PrestacionConcesionDaoImpl extends GenericEjbDao<PrestacionConcesion, BigDecimal>
		implements PrestacionConcesionDao {

	LoggerBiess log = LoggerBiess.getLogger(PrestacionConcesionDaoImpl.class);

	public PrestacionConcesionDaoImpl() {
		super(PrestacionConcesion.class);
	}
}
