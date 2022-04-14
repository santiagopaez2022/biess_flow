/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.EstadoGenerico;

/**
 * HitoDetalle.java
 * 
 * Definicion de la tabla CRE_CC_HITOSCONTROLDETALLE_TBL y corresponde al
 * detalle de la tabla CRE_HITOSCONTROL_TBL
 * 
 * @author geguiguren
 * 
 */
@Entity
@Table(name = "CRE_CC_HITOSCONTROLDETALLE_TBL")
@SequenceGenerator(name = "secHitoCostoDetalle", sequenceName = "CRE_HITOSCONTROLDET_SEQ", initialValue = 1, allocationSize = 1)
@NamedQuery(name = "HitoDetalle.byTipoSolicitud", query = "SELECT DISTINCT o FROM HitoDetalle o  "
		+ "WHERE o.hitoControl.tipoServicio =:idTipoSolicitud and o.estado ='ACT' ORDER BY o.id")
public class HitoDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5816653808743702668L;

	@Id
	@Column(name = "HD_ID_HITOSCONTROLDET", nullable = false)
	@Basic(optional = false)
	@GeneratedValue(generator = "secHitoCostoDetalle", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "HD_HITOSCONTROL")
	private HitoControl hitoControl;

	@Column(name = "HD_DESCRIPCION", nullable = false)
	@Basic(optional = false)
	private String descripcion;

	@Column(name = "HD_ESTADO_HITO", nullable = false)
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private EstadoGenerico estado;

	@Column(name = "HD_VALOR", nullable = false)
	@Basic(optional = false)
	private BigDecimal valor = new BigDecimal(0);

	/*
	 * @Embedded
	 * 
	 * @AttributeOverrides({
	 * 
	 * 
	 * @AttributeOverride(name="fechaDesde",column=@Column(name="FECHA_INI",nullable
	 * =false)),
	 * 
	 * 
	 * @AttributeOverride(name="fechaHasta",column=@Column(name="FECHA_FIN",nullable
	 * =false)) }) private FechaVigencia fechaVigencia;
	 */

	/**
	 * 
	 */
	public HitoDetalle() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the hitoControl
	 */
	public HitoControl getHitoControl() {
		return hitoControl;
	}

	/**
	 * @param hitoControl
	 *            the hitoControl to set
	 */
	public void setHitoControl(HitoControl hitoControl) {
		this.hitoControl = hitoControl;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the estado
	 */
	public EstadoGenerico getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(EstadoGenerico estado) {
		this.estado = estado;
	}
}
