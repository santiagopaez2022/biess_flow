package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clave primaria compuesta de la entidad CreditoValidacion
 * 
 * @author hugo.mora
 *
 */
@Embeddable
public class CreditoValidacionPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CV_CODPRECLA", nullable = false)
	private Long codprecla;

	@Column(name = "CV_CODPRETIP", nullable = false)
	private Long codpretip;

	@Column(name = "CV_NUMPREAFI", nullable = false)
	private Long numpreafi;

	@Column(name = "CV_ORDPREAFI", nullable = false)
	private Long ordpreafi;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codprecla == null) ? 0 : codprecla.hashCode());
		result = prime * result + ((codpretip == null) ? 0 : codpretip.hashCode());
		result = prime * result + ((numpreafi == null) ? 0 : numpreafi.hashCode());
		result = prime * result + ((ordpreafi == null) ? 0 : ordpreafi.hashCode());
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
		CreditoValidacionPk other = (CreditoValidacionPk) obj;
		if (codprecla == null) {
			if (other.codprecla != null)
				return false;
		} else if (!codprecla.equals(other.codprecla))
			return false;
		if (codpretip == null) {
			if (other.codpretip != null)
				return false;
		} else if (!codpretip.equals(other.codpretip))
			return false;
		if (numpreafi == null) {
			if (other.numpreafi != null)
				return false;
		} else if (!numpreafi.equals(other.numpreafi))
			return false;
		if (ordpreafi == null) {
			if (other.ordpreafi != null)
				return false;
		} else if (!ordpreafi.equals(other.ordpreafi))
			return false;
		return true;
	}

	// Getters and setters
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

}
