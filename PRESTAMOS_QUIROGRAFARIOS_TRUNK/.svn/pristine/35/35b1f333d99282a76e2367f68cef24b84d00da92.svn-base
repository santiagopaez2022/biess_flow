/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.ParametrosCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.ParametrosCredito;
import ec.gov.iess.creditos.pq.servicio.ParametrosCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.ParametrosCreditoServicioRemote;

/** 
 * <b>
 * Implementacion de servicios para acceder a la tabla de parametros.
 * </b>
 *  
 * @author NON, Gabriel Eguiguren
 * @version $Revision: 1.2 $ <p>[$Author: smanosalvas $, $Date: 2011/05/11 14:04:00 $]</p>
*/ 
@Stateless
public class ParametrosCreditoServicioImpl implements ParametrosCreditoServicio,ParametrosCreditoServicioRemote{
	@EJB
	ParametrosCreditoDao dao;
	
	public BigDecimal getMontoMaximoCredito() {
		ParametrosCredito par = null;
		par = dao.load("1");
		return par.getMontoMaximoCredito();
	}
	
	/* (non-Javadoc)
	* @see ec.gov.iess.creditos.pq.servicio.ParametrosCreditoServicio#getURLWebServiceCrediReport()
	*/ 
	public String getURLWebServiceCrediReport(){
		
		return dao.getURLWebServiceCrediReport();
		
	}
	
	
	

}
