/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * <b> Persistencia del Objeto orden de compra. </b>
 * 
 * @author Ricardo Tituaña
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:36:43 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_ORDCOMPRADET_TBL")
@SequenceGenerator(name = "CRE_ORDCOMPRADET_SEQ", sequenceName = "CRE_ORDCOMPRADET_SEQ", allocationSize = 1)
public class OrdenCompraDetalle implements Serializable {
	@Id
	@Column(name = "OCD_ID_ORD_COMPRADET")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRE_ORDCOMPRADET_SEQ")
	private Long idOrdComDet;

	@Column(name = "OCD_VALOR")
	private BigDecimal valorParcial;
	
	// Asociación a Orden compra
	@ManyToOne
	@JoinColumns( { @JoinColumn(name = "OC_COD_ORD_COMPRA", referencedColumnName = "OC_COD_ORD_COMPRA") })
	private OrdenCompra ordenCompra;

	// Asociación a Proveedores
	@ManyToOne
	@JoinColumns( { @JoinColumn(name = "PR_ID_PROVEEDOR", referencedColumnName = "PR_ID_PROVEEDOR") })
	private Proveedor proveedor;
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public OrdenCompraDetalle() {
		super();
	}

	
	public OrdenCompraDetalle(Long idOrdComDet, BigDecimal valorParcial,
			OrdenCompra ordenCompra, Proveedor proveedor) {
		super();
		this.idOrdComDet = idOrdComDet;
		this.valorParcial = valorParcial;
		this.ordenCompra = ordenCompra;
		this.proveedor = proveedor;
	}

	
	/**
	 * <b>
	 * regresa id de la orden de compra
	 * </b>
	 * <p>[Author: cbastidas, Date: 17/05/2011]</p>
	 *
	 * @return idOrdComDet : id de la orden de compra
	 */ 
	public Long getIdOrdComDet() {
		return idOrdComDet;
	}

	
	/**
	 * <b>
	 * establece id de la orden de compra
	 * </b>
	 * <p>[Author: cbastidas, Date: 17/05/2011]</p>
	 *
	 * @param idOrdComDet : id de la orden de compra
	 */ 
	public void setIdOrdComDet(Long idOrdComDet) {
		this.idOrdComDet = idOrdComDet;
	}

	
	/**
	 * <b>
	 * regresa valor parcial 
	 * </b>
	 * <p>[Author: cbastidas, Date: 17/05/2011]</p>
	 *
	 * @return valorParcial > valor parcial 
	 */ 
	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	
	/**
	 * <b>
	 * establece valor parcial 
	 * </b>
	 * <p>[Author: cbastidas, Date: 17/05/2011]</p>
	 *
	 * @param valorParcial : valor parcial
	 */ 
	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	
	/**
	 * <b>
	 * regresa la orden de compra
	 * </b>
	 * <p>[Author: cbastidas, Date: 17/05/2011]</p>
	 *
	 * @return ordenCompra : la orden de compra
	 */ 
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	
	/**
	 * <b>
	 * establece la orden de compra
	 * </b>
	 * <p>[Author: cbastidas, Date: 17/05/2011]</p>
	 *
	 * @param ordenCompra : orden de compra
	 */ 
	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	
	/**
	 * <b>
	 * regresa proveedor
	 * </b>
	 * <p>[Author: cbastidas, Date: 17/05/2011]</p>
	 *
	 * @return proveedor : objeto proveedor
	 */ 
	public Proveedor getProveedor() {
		return proveedor;
	}

	
	/**
	 * <b>
	 * configura objeto proveedor
	 * </b>
	 * <p>[Author: cbastidas, Date: 17/05/2011]</p>
	 *
	 * @param proveedor : objeto proveedor
	 */ 
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
}
