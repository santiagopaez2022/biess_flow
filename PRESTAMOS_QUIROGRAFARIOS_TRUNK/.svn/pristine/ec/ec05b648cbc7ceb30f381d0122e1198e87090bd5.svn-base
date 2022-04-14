/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * <b> Representacion ws entrada del Objeto orden de compra. </b>
 * 
 * @author Ricardo Tituaña
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:36:43 $]
 *          </p>
 */
public class OrdenCompraEntradaWS implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroOrden;
	private String numeroFactura;
	private String estadoOrden;
	private Date fecConfOrden;
	private Date fecPlazoPago;
	private BigDecimal valorOrden;
	private BigDecimal valorMulta;
	private List<DetalleOrdenEntradaWs> listaDetalleOrdenEntradaWs;
	
	public OrdenCompraEntradaWS(){}
	
	public OrdenCompraEntradaWS(String numeroOrden, String numeroFactura,
			String estadoOrden, Date fecConfOrden, Date fecPlazoPago,
			BigDecimal valorOrden, BigDecimal valorMulta,
			List<DetalleOrdenEntradaWs> listaDetalleOrdenEntradaWs) {
		super();
		this.numeroOrden = numeroOrden;
		this.numeroFactura = numeroFactura;
		this.estadoOrden = estadoOrden;
		this.fecConfOrden = fecConfOrden;
		this.fecPlazoPago = fecPlazoPago;
		this.valorOrden = valorOrden;
		this.valorMulta = valorMulta;
		this.listaDetalleOrdenEntradaWs = listaDetalleOrdenEntradaWs;
	}

	/**
	 * 
	 * <b> numero de la orden. </b>
	 * <p>
	 * [Author: Ricardo Tituaña, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String numero de la orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * 
	 * <b> numero de la orden. </b>
	 * <p>
	 * [Author: Ricardo Tituana, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param numeroOrden
	 *            : numero de la orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * 
	 * <b> numero de la factura. </b>
	 * <p>
	 * [Author: Ricardo Tituaña, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String numero de la factura
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * 
	 * <b> numero de la factura. </b>
	 * <p>
	 * [Author: Ricardo Tituana, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param numeroFactura
	 *            : numero de la factura
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	/**
	 * 
	 * <b> Estado de la orden. </b>
	 * <p>
	 * [Author: Ricardo Tituaña, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Estado de la orden
	 */
	public String getEstadoOrden() {
		return estadoOrden;
	}

	/**
	 * 
	 * <b> estado de la orden. </b>
	 * <p>
	 * [Author: Ricardo Tituana, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param estadoOrden
	 *            : estado de la orden
	 */
	public void setEstadoOrden(String estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	/**
	 * 
	 * <b> Fecha de confirmacion de la orden. </b>
	 * <p>
	 * [Author: Ricardo Tituaña, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return Date Fecha de confirmacion de la orden
	 */
	public Date getFecConfOrden() {
		return fecConfOrden;
	}

	/**
	 * 
	 * <b> Fecha de confirmacion de la orden. </b>
	 * <p>
	 * [Author: Ricardo Tituana, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param fecConfOrden
	 *            : Fecha de confirmacion de la orden
	 */
	public void setFecConfOrden(Date fecConfOrden) {
		this.fecConfOrden = fecConfOrden;
	}

	/**
	 * 
	 * <b> Fecha plazo de pago. </b>
	 * <p>
	 * [Author: Ricardo Tituaña, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return Date Fecha plazo de pago
	 */
	public Date getFecPlazoPago() {
		return fecPlazoPago;
	}

	/**
	 * 
	 * <b> Fecha de plaza pago. </b>
	 * <p>
	 * [Author: Ricardo Tituana, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param fecPlazoPago
	 *            : Fecha de plaza pago
	 */
	public void setFecPlazoPago(Date fecPlazoPago) {
		this.fecPlazoPago = fecPlazoPago;
	}

	/**
	 * 
	 * <b> Valor de la orden. </b>
	 * <p>
	 * [Author: Ricardo Tituaña, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return BigDecimal Valor de la orden
	 */
	public BigDecimal getValorOrden() {
		return valorOrden;
	}

	/**
	 * 
	 * <b> valor orden. </b>
	 * <p>
	 * [Author: Ricardo Tituana, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param fecPlazoPago
	 *            : valorOrden
	 */
	public void setValorOrden(BigDecimal valorOrden) {
		this.valorOrden = valorOrden;
	}

	/**
	 * 
	 * <b> Valor de la multa. </b>
	 * <p>
	 * [Author: Ricardo Tituaña, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return BigDecimal Valor de la multa
	 */
	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	/**
	 * 
	 * <b> valor multa. </b>
	 * <p>
	 * [Author: Ricardo Tituana, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param fecPlazoPago
	 *            : valorMulta
	 */
	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	
	/**
	 * 
	 * <b> lista detalle orden compra. </b>
	 * <p>
	 * [Author: Ricardo Tituaña, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return List<DetalleOrdenEntradaWs> lista detalle orden compra
	 */
	public List<DetalleOrdenEntradaWs> getListaDetalleOrdenEntradaWs() {
		return listaDetalleOrdenEntradaWs;
	}

	/**
	 * 
	 * <b> lista detalle orden compra. </b>
	 * <p>
	 * [Author: Ricardo Tituana, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param listaDetalleOrdenEntradaWs
	 *            : lista detalle orden compra
	 */
	public void setListaDetalleOrdenEntradaWs(
			List<DetalleOrdenEntradaWs> listaDetalleOrdenEntradaWs) {
		this.listaDetalleOrdenEntradaWs = listaDetalleOrdenEntradaWs;
	}
	
}
