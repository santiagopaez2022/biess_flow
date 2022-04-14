/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import ec.gov.iess.creditos.modelo.persistencia.Proveedor;

/**
 * 
 * <b> Datos principales de la Orden Compra. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:28:12 $]
 *          </p>
 */
public class DatosOrdenCompra implements Serializable {

	private static final long serialVersionUID = -314682374878397542L;

	public DatosOrdenCompra() {
	}

	private String numeroOrden;
	private String codigoProducto;
	private String descripcionProducto;
	private Proveedor proveedor;
	private String mensageAutorizacion;
	private String observacionOrden;
	private BigDecimal montoOrden;

	/**
	 * 
	 * <b> Numero de orden de compra </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return Long : Número de Orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * 
	 * <b> Numero de orden de compra </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param numeroOrden
	 *            : Número de orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * 
	 * <b> Código del producto. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Código del producto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * 
	 * <b> Código del producto. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param codigoProducto
	 *            : Código del producto
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * 
	 * <b> Descripción del producto. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Descripción del producto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * 
	 * <b> Descripción del producto. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param descripcionProducto
	 *            : Descripción del producto
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * 
	 * <b> Objeto Proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return Proveedor Objeto Proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}

	/**
	 * 
	 * <b> Objeto Proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param proveedor
	 *            : Objeto Proveedor
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * 
	 * <b> Mensaje de autorización de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Mensaje de autorización
	 */
	public String getMensageAutorizacion() {
		return mensageAutorizacion;
	}

	/**
	 * 
	 * <b> Mensaje de autorización de compra </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param mensageAutorizacion
	 *            : Mensaje de autorización
	 */
	public void setMensageAutorizacion(String mensageAutorizacion) {
		this.mensageAutorizacion = mensageAutorizacion;
	}

	/**
	 * 
	 * <b> Observación de la orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Observación de la orden de compra
	 */
	public String getObservacionOrden() {
		return observacionOrden;
	}

	/**
	 * 
	 * <b> Observación de la orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param observacionOrden
	 *            : Observación de la orden de compra
	 */
	public void setObservacionOrden(String observacionOrden) {
		this.observacionOrden = observacionOrden;
	}

	/**
	 * 
	 * <b> Monto de la Orden. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return : BigDecimal Monto de la orden
	 */
	public BigDecimal getMontoOrden() {
		return montoOrden;
	}

	/**
	 * 
	 * <b> Monto de la Orden. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param montoOrden
	 *            : Monto de la orden
	 */
	public void setMontoOrden(BigDecimal montoOrden) {
		this.montoOrden = montoOrden;
	}

}
