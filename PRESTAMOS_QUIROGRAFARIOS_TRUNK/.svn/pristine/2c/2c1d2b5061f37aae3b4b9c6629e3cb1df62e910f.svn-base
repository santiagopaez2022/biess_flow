/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */

package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;

import javax.ejb.Local;

import ec.gov.iess.burocredito.exception.BuroCreditoException;

/** 
 * <b>
 * Interface del servicio para la consulta del Buro de Credito.
 * </b>
 *  
 * @author Gabriel Eguiguren
 * @version $Revision: 1.2 $ <p>[$Author: smanosalvas $, $Date: 2011/05/11 14:04:01 $]</p>
*/ 
@Local
public interface ConsultaBuroServicio {

	/**
	 * <b>
	 * Consulta la Cuota Mensual del Buro de Credito
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 12/04/2011]</p>
	 *
	 * @param cedula
	 * 			cedula consultada
	 * 
	 * @return el valor de la cuota estimada
	 * @throws BuroCreditoException 
	 */
	public BigDecimal getCuotaMensual(String cedula) throws Exception;
	
}