/*
 * TipoCuenta.java
 *
 * Created on Aug 22, 2007
 *
 * Copyright © ndeveloper. All Rights Reserved.
 *
 * NDEVELOPER cia ltda
 * Pradera N30-258 y Mariano Aguilera.
 * Edificio Santorini Piso 3
 * Quito-Ecuador
 * www.ndeveloper.com
 * www.ndeveloper.net
 */
package ec.gov.iess.creditos.enumeracion;


/**
 * @author pmlopez
 * @version $Revision: 1.1 $
 * 
 */
public enum TipoCuenta {
	
	AHO("Ahorros"), COR("Corriente"),VIR("Virtual");
	
	private String descripcion;
	
	TipoCuenta(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public String value() {
		return this.name();
	}

	public TipoCuenta fromValue(String v) {
		return valueOf(v);
	}

}