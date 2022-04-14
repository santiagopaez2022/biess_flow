/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.ParametrosCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.ParametrosCredito;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Implementacion para acceder a la tabla de parametros. </b>
 * 
 * @author Gabriel Eguiguren
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/11 14:03:38 $]
 *          </p>
 */
@Stateless
public class ParametrosCreditoDaoImpl extends GenericEjbDao<ParametrosCredito, String> implements ParametrosCreditoDao {
	public ParametrosCreditoDaoImpl() {
		super(ParametrosCredito.class);
	}


	/* (non-Javadoc)
	* @see ec.gov.iess.creditos.dao.ParametrosCreditoDao#getURLWebServiceCrediReport()
	*/
	public String getURLWebServiceCrediReport() {

		try {
			Query query = em.createNativeQuery("SELECT valcharconfin FROM kscretprevarconfin " +
					"WHERE codpretip = 1 AND codprecla = 24 AND codconfin = '153' ");

			return (String) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}

}
