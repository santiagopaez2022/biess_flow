/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.PrestacionConcesion;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * <b>
 * Interfaz Prestación.
 * </b>
 *  
 * @author cbastidas
 * @version $Revision: 1.0 $ <p>[$Author: cbastidas $, $Date: 16/06/2011 $]</p>
 */
@Local
public interface PrestacionConcesionDao extends GenericDao<PrestacionConcesion, BigDecimal> {
	
}
