/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.burocredito.constantes.TipoDocumentoBuro;
import ec.gov.iess.burocredito.exception.BuroCreditoException;
import ec.gov.iess.burocredito.modelo.BuroCreditoAfiliado;
import ec.gov.iess.burocredito.servicio.BuroCreditoAfiliadoConsultaServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaBuroServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaBuroServicioRemote;
import ec.gov.iess.creditos.pq.servicio.ParametrosCreditoServicio;

/** 
 * <b>
 * Clase que implementa los servicios de consulta la Cuota Mensual de endeudamiento en el Buro de Credito 
 * </b>
 *  
 * @author Gabriel Eguiguren
 * @version $Revision: 1.2 $ <p>[$Author: smanosalvas $, $Date: 2011/05/11 14:04:01 $]</p>
*/ 
@Stateless
public class ConsultaBuroServicioImpl implements ConsultaBuroServicio, ConsultaBuroServicioRemote {

	protected LoggerBiess log = LoggerBiess.getLogger(ConsultaBuroServicioImpl.class);
	
	@EJB
	private ParametrosCreditoServicio  parametroServicio;
	
	@EJB
	private BuroCreditoAfiliadoConsultaServicio buroCreditoAfiliadoConsultaServicio;
	
	private final String ID_APLICACION = "PH-INTERNET";
	
	//como por el momento este metodo solo va a ser utilizado por el producto jubilados se utiliza como constante 
	//el listado completo de estos codigos se encuentra en la tabla KSCRETSERSOLTIP
	private Long codtipsolser = 26L;  
	
	
	/* (non-Javadoc)
	* @see ec.gov.iess.creditos.pq.servicio.ConsultaBuroServicio#getCuotaMensual(java.lang.String)
	*/ 
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public BigDecimal getCuotaMensual(String cedula) throws Exception  {
	
		BigDecimal cuotaMensual = BigDecimal.ZERO;
		BuroCreditoAfiliado buroCreditoSolicitante = null;
		
		String urlWebService = parametroServicio.getURLWebServiceCrediReport();
		
		try {
			// llama al WS de Vivienda Terminada
			buroCreditoSolicitante = buroCreditoAfiliadoConsultaServicio.consultaBuroCreditoVigentePorCedula(
					cedula, new Date(), ID_APLICACION, urlWebService, TipoDocumentoBuro.CEDULA, codtipsolser);

		} catch (BuroCreditoException e) {
			log.error("Problemas con la conexión al obtener los datos de buró de crédito. Por favor intente más tarde");
			throw new Exception("Problemas con la conexión al obtener los datos de buró de crédito. Por favor intente más tarde");
		}

		if (null != buroCreditoSolicitante) {
			cuotaMensual = buroCreditoSolicitante.getCuotaMensualEstimada();
			log.info("valor cuota del Buro de VT: " + cuotaMensual);
		}
		
		return cuotaMensual;
	}
	
}