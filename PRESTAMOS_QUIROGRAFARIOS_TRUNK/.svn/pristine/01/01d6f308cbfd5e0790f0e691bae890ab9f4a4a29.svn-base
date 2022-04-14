/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DiasFeriado;
import ec.gov.iess.dao.GenericDao;


/** 
 * <b>
 * Interface que controla los dias de feriado
 * </b>
 *  
 * @author Ricardo Tituaña
 * 
*/
@Local
public interface DiasFeriadoDao  extends GenericDao<DiasFeriado, Long> {
	public DiasFeriado obtenerPorAnioMesDiaMod(Long anio, Long mes, Long dia, String modulo);

}
