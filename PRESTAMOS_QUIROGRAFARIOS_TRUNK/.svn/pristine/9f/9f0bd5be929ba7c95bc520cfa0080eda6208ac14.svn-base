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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad para la tabla cre_detallefocalizado_t
 * 
 * @author hugo.mora
 *
 */
@Entity
@Table(name = "CRE_DETALLEFOCALIZADO_T")
@NamedQueries({ @NamedQuery(name = "DetalleFocalizado.buscarPorPrestamoYEstado", query = "SELECT d FROM DetalleFocalizado d "
		+ " WHERE d.codprecla = :codprecla " + " AND d.codpretip = :codpretip " + " AND d.numpreafi = :numpreafi " + " AND d.ordpreafi = :ordpreafi "
		+ " AND d.estado = :estado ") })
@SequenceGenerator(name = "CRE_DETALLEFOCALIZADO_SEQ", sequenceName = "CRE_DETALLEFOCALIZADO_SEQ", allocationSize = 1)
public class DetalleFocalizado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DF_CODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRE_DETALLEFOCALIZADO_SEQ")
	private Long codigo;

	@Column(name = "DF_CODPRECLA")
	private Long codprecla;

	@Column(name = "DF_CODPRETIP")
	private Long codpretip;

	@Column(name = "DF_NUMPREAFI")
	private Long numpreafi;

	@Column(name = "DF_ORDPREAFI")
	private Long ordpreafi;

	@Column(name = "DF_CODIGO_PRODUCTO_MEER")
	private Integer codigoProductoMeer;

	@Column(name = "DF_PRECIO")
	private BigDecimal precio;

	@Column(name = "DF_ESTADO")
	private String estado;
	
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "DF_CODPRECLA", referencedColumnName = "CV_CODPRECLA", insertable = false, updatable = false),
			@JoinColumn(name = "DF_CODPRETIP", referencedColumnName = "CV_CODPRETIP", insertable = false, updatable = false),
			@JoinColumn(name = "DF_NUMPREAFI", referencedColumnName = "CV_NUMPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "DF_ORDPREAFI", referencedColumnName = "CV_ORDPREAFI", insertable = false, updatable = false)})
	private CreditoValidacion creditoValidacion;

	public DetalleFocalizado() {
		super();
	}

	// Getters and setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodprecla() {
		return codprecla;
	}

	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	public Long getCodpretip() {
		return codpretip;
	}

	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	public Long getNumpreafi() {
		return numpreafi;
	}

	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	public Long getOrdpreafi() {
		return ordpreafi;
	}

	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

	public Integer getCodigoProductoMeer() {
		return codigoProductoMeer;
	}

	public void setCodigoProductoMeer(Integer codigoProductoMeer) {
		this.codigoProductoMeer = codigoProductoMeer;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public CreditoValidacion getCreditoValidacion() {
		return creditoValidacion;
	}

	public void setCreditoValidacion(CreditoValidacion creditoValidacion) {
		this.creditoValidacion = creditoValidacion;
	}

}
