package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PrestamoVistaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CODPRECLA", nullable = false)
	private Long codprecla;

	@Column(name = "CODPRETIP", nullable = false)
	private Long codpretip;

	@Column(name = "NUMPREAFI", nullable = false)
	private Long numpreafi;

	@Column(name = "ORDPREAFI", nullable = false)
	private Long ordpreafi;

	public PrestamoVistaPK() {
	}

	public PrestamoVistaPK(Long codprecla, Long codpretip, Long numpreafi,
			Long ordpreafi) {
		this.codprecla = codprecla;
		this.codpretip = codpretip;
		this.numpreafi = numpreafi;
		this.ordpreafi = ordpreafi;
	}

	/**
	 * Comparador de objetos
	 */
	public boolean equals(Object other) {
		if (other instanceof PrestamoPk) {
			final PrestamoPk otherKscretcreditosPK = (PrestamoPk) other;
			final boolean areEqual = (
					otherKscretcreditosPK.getCodprecla().equals(codprecla)
					&& otherKscretcreditosPK.getCodpretip().equals(codpretip)
					&& otherKscretcreditosPK.getNumpreafi().equals(numpreafi) 
					&& otherKscretcreditosPK.getOrdpreafi().equals(ordpreafi));
			return areEqual;
		}
		return false;
	}

	/**
	 * hascode
	 */
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * @return the codprecla
	 */
	public Long getCodprecla() {
		return codprecla;
	}

	/**
	 * @param codprecla
	 *            the codprecla to set
	 */
	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	/**
	 * @return the codpretip
	 */
	public Long getCodpretip() {
		return codpretip;
	}

	/**
	 * @param codpretip
	 *            the codpretip to set
	 */
	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	/**
	 * @return the numpreafi
	 */
	public Long getNumpreafi() {
		return numpreafi;
	}

	/**
	 * @param numpreafi
	 *            the numpreafi to set
	 */
	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	/**
	 * @return the ordpreafi
	 */
	public Long getOrdpreafi() {
		return ordpreafi;
	}

	/**
	 * @param ordpreafi
	 *            the ordpreafi to set
	 */
	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

}
