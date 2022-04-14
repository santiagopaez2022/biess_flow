/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.ParametroNoEncontradoException;
import ec.gov.iess.creditos.modelo.persistencia.ParametroValor;
import ec.gov.iess.creditos.modelo.persistencia.pk.ParametroValorPk;
import ec.gov.iess.dao.GenericDao;

/** 
 * <b>
 * Interface que controla los datos de los parametros PQ
 * </b>
 *  
 * @author Ricardo Tituaña
 * 
*/
@Local
public interface ParametroValorDao extends GenericDao<ParametroValor, ParametroValorPk> {

	/**
	 * 
	 * <b>
	 * Trae parametro de acuerdo a identificador del parametro
	 * </b>
	 * <p>[Author: Ricardo Tituaña, Date: 04/04/2011]</p>
	 *
	 * @param codConFin identificador del parametro
	 * @return ParametroValor Parametro de acuerdo a su identificador
	 * @throws ParametroNoEncontradoException
	 */
	ParametroValor obtenerParametroVigente(String codConFin) throws ParametroNoEncontradoException;
	
	//Cambios acantos - pq fraudes
	/**
	 * acantos
	 * retorna el parametro fecbiess de kscretcrepol`
	 * utilizado para validacion de pq-fraudes, <= a esta fecha es un credito posiblemente
	 * considerado para estado pendiente de aprobacion
	 * @return
	 */
	public Date obtenerFechabiess() throws ParametroNoEncontradoException;
	
	/**
	 * acantos
	 * retorna el parametro montopq de kscretcrepol
	 * este parametro es utilizado para validacion de pq-fraudes, mayor a esa cantidad es un credito posiblemente
	 * considerado para estado pendiente de aprobacion
	 * @return
	 */
	public BigDecimal obtenermMontopqparavalidacionpda()  throws ParametroNoEncontradoException;
	
}