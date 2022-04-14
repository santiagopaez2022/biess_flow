/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * <b> Representacion ws entrada del Objeto orden detalle de compra. </b>
 * 
 * @author Ricardo Tituaña
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:36:43 $]
 *          </p>
 */
public class DetalleOrdenEntradaWs implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ruc;
	private BigDecimal valorParcial;
	
	public DetalleOrdenEntradaWs(){}
	
	public DetalleOrdenEntradaWs(String ruc, BigDecimal valorParcial) {
		super();
		this.ruc = ruc;
		this.valorParcial = valorParcial;
	}

	/**
	 * 
	 * <b> ruc de la empresa proveedora. </b>
	 * <p>
	 * [Author: Ricardo Tituaña, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String ruc de la empresa proveedora
	 */
	public String getRuc() {
		return ruc;
	}

	/**
	 * 
	 * <b> ruc de la empresa proveedora. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param ruc
	 *            : ruc de la empresa proveedora
	 */
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	
	/**
	 * 
	 * <b> valor parcial. </b>
	 * <p>
	 * [Author: Ricardo Tituaña, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return BigDecimal: valor parcial
	 */
	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	/**
	 * 
	 * <b> valor parcial. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param ruc
	 *            :  valor parcial
	 */
	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}
	
	
	
}
