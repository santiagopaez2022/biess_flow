/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author cvillarreal
 * 
 */

@Entity
@Table(name = "CRE_COEFICIENTESEGUROS_TBL")
@NamedQueries( { @NamedQuery(name = "CoeficienteSeguro.findTipoSeguroTipoPrestacion", 
		query = "SELECT o FROM CoeficienteSeguro o " +
				"WHERE o.codtipsolser=:codtipsolser AND " +
				":fecha BETWEEN o.fechaInicio AND o.fechaFin " +
				"AND o.tipoSeguro= :tipoSeguro AND o.anio=:anio") })
@SequenceGenerator(name = "seqCoeficienteSeguro", allocationSize = 1, initialValue = 1, sequenceName = "CRE_COEFICIENTESEGUROS_SEQ")
public class CoeficienteSeguro implements Serializable {

	private static final long serialVersionUID = 3080470203256311161L;

	@Id
	@Column(name = "SECSEG", nullable = false)
	@Basic(optional = false)
	@GeneratedValue(generator = "seqCoeficienteSeguro", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "SEGTIP", nullable = true)
	@Basic(optional = true)
	private String tipoSeguro;

	@Column(name = "SEGFECINI", nullable = true)
	@Temporal(TemporalType.DATE)
	@Basic(optional = true)
	private Date fechaInicio;

	@Column(name = "SEGFECFIN", nullable = true)
	@Temporal(TemporalType.DATE)
	@Basic(optional = true)
	private Date fechaFin;

	@Column(name = "SEGANIO", nullable = true)
	@Basic(optional = true)
	private Integer anio;

	@Column(name = "SEGCOESEG", nullable = true)
	@Basic(optional = true)
	private BigDecimal coeficiente;

	@Column(name = "CODTIPSOLSER", nullable = false)
	@Basic(optional = false)
	private Long codtipsolser;

	/**
	 * 
	 */
	public CoeficienteSeguro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	
	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public BigDecimal getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(BigDecimal coeficiente) {
		this.coeficiente = coeficiente;
	}

	public Long getCodtipsolser() {
		return codtipsolser;
	}

	public void setCodtipsolser(Long codtipsolser) {
		this.codtipsolser = codtipsolser;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}
