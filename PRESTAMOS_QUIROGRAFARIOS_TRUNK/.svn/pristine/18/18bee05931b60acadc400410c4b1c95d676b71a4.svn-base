/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.	
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.gov.iess.creditos.dao.DiasFeriadoDao;
import ec.gov.iess.creditos.modelo.persistencia.DiasFeriado;
import ec.gov.iess.creditos.pq.servicio.DiasFeriadoServicio;

/** 
 * <b>
 * Clase que maneja los dias feriados
 * </b>
 *  
 * @author Ricardo Tituaña
 * 
*/
@Stateless
public class DiasFeriadoServicioImpl implements
DiasFeriadoServicio {

	@EJB
	private DiasFeriadoDao diasFeriadoDao;

	

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.DiasFeriadoServicio#obtenerPorAnioMesDiaMod(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public DiasFeriado obtenerPorAnioMesDiaMod(Long anio, Long mes, Long dia, String modulo) {
		return diasFeriadoDao.obtenerPorAnioMesDiaMod(anio, mes, dia, modulo);
	}

}
