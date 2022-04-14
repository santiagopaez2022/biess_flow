/*
 * PrestamoConsolidado.java
 *
 * Created on Aug 29, 2007
 *
 * Copyright @ ndeveloper. All Rights Reserved.
 *
 * NDEVELOPER cia ltda
 * Pradera N30-258 y Mariano Aguilera.
 * Edificio Santorini Piso 3
 * Quito-Ecuador
 * www.ndeveloper.com
 * www.ndeveloper.net
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author pmlopez
 * @version $Revision: 1.1 $
 * 
 */
@Entity
//@Table(name = "CRE_CONSOLIDACION_CREDITO_MVW")
public class CreditoConsolidado implements Serializable {

	private static final long serialVersionUID = -456793020899518076L;

	@Id
	private Long id;

	private Long cantidad;

	private BigDecimal monto;

	@Column(name = "fecha_actualizacion")
	private Timestamp fechaActualizacion;

	/**
	 * Returns the value of id.
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the value for id.
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the value of cantidad.
	 * @return cantidad
	 */
	public Long getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the value for cantidad.
	 * @param cantidad
	 */
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Returns the value of monto.
	 * @return monto
	 */
	public BigDecimal getMonto() {
		return monto;
	}

	/**
	 * Sets the value for monto.
	 * @param monto
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	/**
	 * Returns the value of fechaActualizacion.
	 * @return fechaActualizacion
	 */
	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * Sets the value for fechaActualizacion.
	 * @param fechaActualizacion
	 */
	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

}