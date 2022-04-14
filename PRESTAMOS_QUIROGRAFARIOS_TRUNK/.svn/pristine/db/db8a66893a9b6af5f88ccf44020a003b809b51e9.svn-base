/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.ParametrosCredito;
import ec.gov.iess.dao.GenericDao;

/** 
 * <b>
 * Clase para acceder a la tabla de parametros.
 * </b>
 *  
 * @author Gabriel Eguiguren
 * @version $Revision: 1.2 $ <p>[$Author: smanosalvas $, $Date: 2011/05/11 14:03:38 $]</p>
*/ 
@Local
public interface ParametrosCreditoDao extends GenericDao<ParametrosCredito, String> {

	/**
	 * <b>
	 * Obtiene la ip o URL para acceder al buró de credito de la tabla de parametros.
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 12/04/2011]</p>
	 *
	 * @return ip o URL para acceder al buró de credito
	 */ 
	public String getURLWebServiceCrediReport();
	
}
