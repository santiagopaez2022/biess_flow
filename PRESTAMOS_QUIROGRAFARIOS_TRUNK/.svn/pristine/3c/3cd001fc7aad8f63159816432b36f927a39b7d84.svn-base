/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.	
 */
package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DiasFeriado;

/** 
 * <b>
 * Clase que maneja los dias feriados
 * </b>
 *  
 * @author Ricardo Tituaña
 * 
*/
@Local
public interface DiasFeriadoServicio {
	/**
	 * 
	 * <b>
	 * obtener dia feriado por anio, mes, dia y modulo
	 * </b>
	 * 
	 *
	 * @param anio, anio del dia feriado
	 * @param mes, mes del dia feriado
	 * @param dia, dia del dia feriado
	 * @return DiasFeriado Tasa de concesión de PQ.
	 * @throws TasaInteresExcepcion
	 */
	public DiasFeriado obtenerPorAnioMesDiaMod(Long anio, Long mes, Long c, String modulo);
}
