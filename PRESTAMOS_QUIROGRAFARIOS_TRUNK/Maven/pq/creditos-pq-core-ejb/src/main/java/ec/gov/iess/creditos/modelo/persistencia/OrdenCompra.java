/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * <b> Persistencia del Objeto orden de compra. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:36:43 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_ORDCOMPRA_TBL")
@NamedQueries( {
		@NamedQuery(name = "OrdenCompra.consultarOrden", query = "SELECT c FROM OrdenCompra c WHERE "
				+ "c.detalleCatalogo.id.caCatalogo=:catalogoEstado AND c.detalleCatalogo.id.dcCodigo = :estado " 
				+ "ORDER BY c.ocFecGeneracion )"),
		@NamedQuery(name = "OrdenCompra.consultarOrdenCodigo", query = "SELECT o FROM OrdenCompra o " 
				+ "WHERE o.ocCodOrdCompra = :codigo AND o.detalleCatalogo.id.caCatalogo = 'ESTORD' " 
				+ "AND o.detalleCatalogo.id.dcCodigo = 'ENT' )"),
		@NamedQuery(name = "OrdenCompra.consultarOrdenFecha", query = "SELECT o FROM OrdenCompra o "
				+ " WHERE o.detalleCatalogo.id.caCatalogo=:catalogoEstado "
				+ " AND o.detalleCatalogo.id.dcCodigo = :estado AND o.ocFecGeneracion >= :fechaDesde "
				+ " AND o.ocFecGeneracion <= :fechaHasta ORDER BY o.ocFecGeneracion ") })
public class OrdenCompra implements Serializable {
	private static final long serialVersionUID = -5165011531358318357L;

	@Id
	@Column(name = "OC_COD_ORD_COMPRA")
	private String ocCodOrdCompra;

	@Column(name = "OC_FEC_GENERACION")
	private Date ocFecGeneracion;

	@Column(name = "OC_VALOR")
	private Double ocValor;

	@Column(name = "OC_VALOR_MULTA")
	private Double valorMulta;

	@Column(name = "OC_OBSERVACION")
	private String ocObservacion;

	@Column(name = "OC_NUM_FACTURA")
	private String numFactura;

	@Column(name = "OC_FEC_CONFIRMACION")
	private Date fecConfirmacion;

	@Column(name = "OC_FEC_PLAZO")
	private Date fecPlazoPago;

	// Asociacion a DetalleCatalogos
	@ManyToOne
	@JoinColumns( { @JoinColumn(name = "CA_CATALOGO_ESTADO", referencedColumnName = "CA_CATALOGO"),
			@JoinColumn(name = "DC_CODIGO_ESTADO", referencedColumnName = "DC_CODIGO") })
	private DetalleCatalogos detalleCatalogo;

	// Asociación a Préstamos
	@ManyToOne
	@JoinColumns( { @JoinColumn(name = "NUMPREAFI", referencedColumnName = "NUMPREAFI"),
			@JoinColumn(name = "ORDPREAFI", referencedColumnName = "ORDPREAFI"),
			@JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP"),
			@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA") })
	private Prestamo prestamo;

	@OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrdenCompraDetalle> ordenDetalle;
	
	@Transient
	private String nombres;

	@Transient
	private String observacionRechazo;
	
	@Transient
	private String rucProveedor;
	
	@Transient
	private String nombreProveedor;

	public OrdenCompra() {
		super();
	}

	/**
	 * 
	 * <b> Código de la orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return Long Código de la orden de compra
	 */
	public String getOcCodOrdCompra() {
		return this.ocCodOrdCompra;
	}

	/**
	 * 
	 * <b> Código de la orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param ocCodOrdCompra
	 *            : Código de la orden de compra
	 */
	public void setOcCodOrdCompra(String ocCodOrdCompra) {
		this.ocCodOrdCompra = ocCodOrdCompra;
	}

	/**
	 * 
	 * <b> Fecha de generación de la orden. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return Date Fecha de generación de la orden
	 */
	public Date getOcFecGeneracion() {
		return this.ocFecGeneracion;
	}

	/**
	 * 
	 * <b> Fecha de generación de la orden. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param ocFecGeneracion
	 *            : Fecha de generación de la orden
	 */
	public void setOcFecGeneracion(Date ocFecGeneracion) {
		this.ocFecGeneracion = ocFecGeneracion;
	}

	/**
	 * 
	 * <b> Valor total de orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return Double Valor total de orden de compra
	 */
	public Double getOcValor() {
		return this.ocValor;
	}

	/**
	 * 
	 * <b> Valor total de orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param ocValor
	 *            : Valor total de orden de compra
	 */
	public void setOcValor(Double ocValor) {
		this.ocValor = ocValor;
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
	public String getOcObservacion() {
		return this.ocObservacion;
	}

	/**
	 * 
	 * <b> Observación de la orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param ocObservacion
	 *            : Observación de la orden de compra
	 */
	public void setOcObservacion(String ocObservacion) {
		this.ocObservacion = ocObservacion;
	}

	/**
	 * 
	 * <b> Catálogo de estado de la orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return DetalleCatalogos Catálogo de estado de la orden de compra
	 */
	public DetalleCatalogos getDetalleCatalogo() {
		return detalleCatalogo;
	}

	/**
	 * 
	 * <b> Catálogo de estado de la orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param detalleCatalogo
	 *            : Catálogo de estado de la orden de compra
	 */
	public void setDetalleCatalogo(DetalleCatalogos detalleCatalogo) {
		this.detalleCatalogo = detalleCatalogo;
	}

	/**
	 * 
	 * <b> Objeto préstamo. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return Prestamo : Objeto Préstamo
	 */
	public Prestamo getPrestamo() {
		return prestamo;
	}

	/**
	 * 
	 * <b> Objeto préstamo. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prestamo
	 *            : Objeto préstamo.
	 */
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	/**
	 * @return
	 */
	public String getNumFactura() {
		return numFactura;
	}

	/**
	 * @param numFactura
	 */
	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	/**
	 * @return
	 */
	public Date getFecConfirmacion() {
		return fecConfirmacion;
	}

	/**
	 * @param fecConfirmacion
	 */
	public void setFecConfirmacion(Date fecConfirmacion) {
		this.fecConfirmacion = fecConfirmacion;
	}

	/**
	 * @return
	 */
	public Double getValorMulta() {
		return valorMulta;
	}

	/**
	 * @param valorMulta
	 */
	public void setValorMulta(Double valorMulta) {
		this.valorMulta = valorMulta;
	}

	/**
	 * @return
	 */
	public Date getFecPlazoPago() {
		return fecPlazoPago;
	}

	/**
	 * @param fecPlazoPago
	 */
	public void setFecPlazoPago(Date fecPlazoPago) {
		this.fecPlazoPago = fecPlazoPago;
	}

	/**
	 * @return the ordenDetalle
	 */
	public List<OrdenCompraDetalle> getOrdenDetalle() {
		return ordenDetalle;
	}

	/**
	 * @param ordenDetalle the ordenDetalle to set
	 */
	public void setOrdenDetalle(List<OrdenCompraDetalle> ordenDetalle) {
		this.ordenDetalle = ordenDetalle;
	}

	/**
	 * @return the observacionRechazo
	 */
	public String getObservacionRechazo() {
		return observacionRechazo;
	}

	/**
	 * @param observacionRechazo the observacionRechazo to set
	 */
	public void setObservacionRechazo(String observacionRechazo) {
		this.observacionRechazo = observacionRechazo;
	}

	/**
	 * @return the rucProveedor
	 */
	public String getRucProveedor() {
		rucProveedor = ordenDetalle.get(0).getProveedor().getPrRuc();
		return rucProveedor;
	}

	/**
	 * @param rucProveedor the rucProveedor to set
	 */
	public void setRucProveedor(String rucProveedor) {
		this.rucProveedor = rucProveedor;
	}

	/**
	 * @return the nombreProveedor
	 */
	public String getNombreProveedor() {
		nombreProveedor = ordenDetalle.get(0).getProveedor().getPrNombre();
		return nombreProveedor;
	}

	/**
	 * @param nombreProveedor the nombreProveedor to set
	 */
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
}
