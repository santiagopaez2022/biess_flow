/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.gov.iess.creditos.dao.ParametroValorDao;
import ec.gov.iess.creditos.excepcion.ParametroNoEncontradoException;
import ec.gov.iess.creditos.modelo.persistencia.ParametroValor;
import ec.gov.iess.creditos.pq.servicio.ParametroServicio;
import ec.gov.iess.creditos.pq.servicio.ParametroServicioRemote;

/** 
 * <b>
 * Clase para el manejo de parametros
 * </b>
 *  
 * @author Ricardo Tituaña
 * 
*/
@Stateless
@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
public class ParametroServicioImpl implements ParametroServicio, ParametroServicioRemote {

	@EJB
	private ParametroValorDao parametroValorDao;

	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.ParametroServicio#obtenerParametro(java.lang.String)
	 */
	public String obtenerParametro(String idParametro) throws ParametroNoEncontradoException {
		ParametroValor parametroValor = parametroValorDao.obtenerParametroVigente(idParametro);
		return parametroValor.getValCharConFin();
	}
	
	//Cambios acantos - pq fraudes
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.ParametroServicio#obtenerFechabiess()
	 */
	public Date obtenerFechabiess() throws ParametroNoEncontradoException{
		return parametroValorDao.obtenerFechabiess();
	}
	
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.ParametroServicio#obtenermMontopqparavalidacionpda()
	 */
	public BigDecimal obtenermMontopqparavalidacionpda() throws ParametroNoEncontradoException{
		return parametroValorDao.obtenermMontopqparavalidacionpda();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.ParametroServicio#obtenerParametroPorId(java.lang.String)
	 */
	public ParametroValor obtenerParametroPorId(String idParametro) throws ParametroNoEncontradoException {
		ParametroValor parametroValor = parametroValorDao.obtenerParametroVigente(idParametro);
		return parametroValor;
	}

}