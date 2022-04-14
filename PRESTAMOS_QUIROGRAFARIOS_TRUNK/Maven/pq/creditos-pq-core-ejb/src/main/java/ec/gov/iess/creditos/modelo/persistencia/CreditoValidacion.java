package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.gov.iess.creditos.modelo.persistencia.pk.CreditoValidacionPk;

/**
 * Entidad para datos de la tabla CRE_CREDITOVALIDACION_T
 * 
 * @author hugo.mora
 *
 */
@Entity
@Table(name = "CRE_CREDITOVALIDACION_T")
@NamedQueries({ @NamedQuery(name = "CreditoValidacion.consultarPorPrestamo", query = " SELECT cv FROM CreditoValidacion cv "
		+ " WHERE cv.creditoValidacionPk.codprecla = :codprecla " + " AND cv.creditoValidacionPk.codpretip = :codpretip "
		+ " AND cv.creditoValidacionPk.numpreafi = :numpreafi " + " AND cv.creditoValidacionPk.ordpreafi = :ordpreafi ") })
public class CreditoValidacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CreditoValidacionPk creditoValidacionPk;

	@Column(name = "CV_ID_PROVEEDOR")
	private Long idProveedor;

	@Column(name = "CV_CODIGO_ACTIVACION")
	private String codigoActivacion;

	@Column(name = "CV_CODIGO_DACTILAR")
	private String codigoDactilar;

	@Column(name = "CV_FECHA_EXPEDICION_CEDULA")
	private String fechaExpedicion;

	@Column(name = "CV_NUMAFI")
	private String numafi;

	@Column(name = "CV_FECHA_GENERACION")
	private Date fechaGeneracion;

	@Column(name = "CV_FECHA_VALIDACION", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaValidacion;

	@Column(name = "CV_FECHA_ACTIVACION")
	private Date fechaActivacion;

	@Column(name = "CV_ESTADO")
	private String estado;

	@Column(name = "CV_ID_PUNTO_VENTA")
	private Integer idPuntoVenta;

	@Column(name = "CV_FECHA_CADUCIDAD")
	private Date fechaCaducidad;

	@Column(name = "CV_NUMERO_INTENTO")
	private Integer numeroIntento;

	@Column(name = "CV_ID_PROVEEDOR_MEER")
	private Long idProveedorMeer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "creditoValidacion")
	private List<DetalleFocalizado> listaDetalleFocalizado;

	public CreditoValidacion() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditoValidacionPk == null) ? 0 : creditoValidacionPk.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditoValidacion other = (CreditoValidacion) obj;
		if (creditoValidacionPk == null) {
			if (other.creditoValidacionPk != null)
				return false;
		} else if (!creditoValidacionPk.equals(other.creditoValidacionPk))
			return false;
		return true;
	}

	// Getters and setters
	public CreditoValidacionPk getCreditoValidacionPk() {
		return creditoValidacionPk;
	}

	public void setCreditoValidacionPk(CreditoValidacionPk creditoValidacionPk) {
		this.creditoValidacionPk = creditoValidacionPk;
	}

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getCodigoActivacion() {
		return codigoActivacion;
	}

	public void setCodigoActivacion(String codigoActivacion) {
		this.codigoActivacion = codigoActivacion;
	}

	public String getCodigoDactilar() {
		return codigoDactilar;
	}

	public void setCodigoDactilar(String codigoDactilar) {
		this.codigoDactilar = codigoDactilar;
	}

	public String getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public String getNumafi() {
		return numafi;
	}

	public void setNumafi(String numafi) {
		this.numafi = numafi;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Date getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(Date fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	public Date getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(Date fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<DetalleFocalizado> getListaDetalleFocalizado() {
		return listaDetalleFocalizado;
	}

	public void setListaDetalleFocalizado(List<DetalleFocalizado> listaDetalleFocalizado) {
		this.listaDetalleFocalizado = listaDetalleFocalizado;
	}

	public Integer getIdPuntoVenta() {
		return idPuntoVenta;
	}

	public void setIdPuntoVenta(Integer idPuntoVenta) {
		this.idPuntoVenta = idPuntoVenta;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Integer getNumeroIntento() {
		return numeroIntento;
	}

	public void setNumeroIntento(Integer numeroIntento) {
		this.numeroIntento = numeroIntento;
	}

	public Long getIdProveedorMeer() {
		return idProveedorMeer;
	}

	public void setIdProveedorMeer(Long idProveedorMeer) {
		this.idProveedorMeer = idProveedorMeer;
	}

}
