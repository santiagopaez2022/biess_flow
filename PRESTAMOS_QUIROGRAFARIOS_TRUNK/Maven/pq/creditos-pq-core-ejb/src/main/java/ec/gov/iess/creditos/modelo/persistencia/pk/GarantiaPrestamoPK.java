/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
@Embeddable
public class GarantiaPrestamoPK implements Serializable {

	private Long codprecla;
	private Long codpretip;
	private String numafi;
	private Long numpreafi;
	private Long ordpreafi;

	/**
	 * 
	 */
	public GarantiaPrestamoPK() {
	}

	public GarantiaPrestamoPK(Long codprecla, Long codpretip, String numafi,
			Long numpreafi, Long ordpreafi) {
		this.codprecla = codprecla;
		this.codpretip = codpretip;
		this.numafi = numafi;
		this.numpreafi = numpreafi;
		this.ordpreafi = ordpreafi;
	}

	public boolean equals(Object other) {
		if (other instanceof GarantiaPrestamoPK) {
			final GarantiaPrestamoPK otherThlcrtGarantiasPK = (GarantiaPrestamoPK) other;
			final boolean areEqual = (otherThlcrtGarantiasPK.codprecla
					.equals(codprecla)
					&& otherThlcrtGarantiasPK.codpretip.equals(codpretip)
					&& otherThlcrtGarantiasPK.numafi.equals(numafi)
					&& otherThlcrtGarantiasPK.numpreafi.equals(numpreafi) && otherThlcrtGarantiasPK.ordpreafi
					.equals(ordpreafi));
			return areEqual;
		}
		return false;
	}

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
	 * @return the codpretip
	 */
	public Long getCodpretip() {
		return codpretip;
	}

	/**
	 * @return the numafi
	 */
	public String getNumafi() {
		return numafi;
	}

	/**
	 * @return the numpreafi
	 */
	public Long getNumpreafi() {
		return numpreafi;
	}

	/**
	 * @return the ordpreafi
	 */
	public Long getOrdpreafi() {
		return ordpreafi;
	}

	/**
	 * @param codprecla
	 *            the codprecla to set
	 */
	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	/**
	 * @param codpretip
	 *            the codpretip to set
	 */
	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	/**
	 * @param numafi
	 *            the numafi to set
	 */
	public void setNumafi(String numafi) {
		this.numafi = numafi;
	}

	/**
	 * @param numpreafi
	 *            the numpreafi to set
	 */
	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	/**
	 * @param ordpreafi
	 *            the ordpreafi to set
	 */
	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

}
