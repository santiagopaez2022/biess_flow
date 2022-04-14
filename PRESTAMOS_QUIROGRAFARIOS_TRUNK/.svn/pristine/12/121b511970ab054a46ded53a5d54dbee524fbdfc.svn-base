/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Remote;

import ec.gov.iess.creditos.excepcion.ParametroNoEncontradoException;
import ec.gov.iess.creditos.modelo.persistencia.ParametroValor;

/**
 * <b> Servicio para interactuar con la tabla de parametros de credito </b>
 * 
 * @author cvillareal
 * @version $Revision: 1.1.4.2 $
 *          <p>
 *          [$Author: rtituana $, $Date: 2011/04/18 20:57:02 $]
 *          </p>
 */
@Remote
public interface ParametroServicioRemote {

	/**
	 * 
	 * <b> Método para obtener el plazo máximo de compra de inmuebles </b>
	 * <p>
	 * [Author: Jenny Sanchez, Date: 01/03/2011]
	 * </p>
	 * 
	 * @throws ParametroNoEncontradoException
	 *             en caso de no encontrar el parámetro
	 */
	public String obtenerParametro(String idParametro) throws ParametroNoEncontradoException;
	
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
	public BigDecimal obtenermMontopqparavalidacionpda() throws ParametroNoEncontradoException;
	
	/**
	 * Devuelve un ParametroValor dado el ID
	 * 
	 * @return
	 * @throws ParametroNoEncontradoException
	 */
	ParametroValor obtenerParametroPorId(String idParametro) throws ParametroNoEncontradoException;
	
}