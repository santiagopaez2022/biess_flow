/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
@Embeddable
public class AniosPlazoCapitalEndeudamientoPk implements Serializable {

	@Column(nullable = false)
	private Long codprecla;
	@Column(nullable = false)
	private Long codpretip;
	@Column(nullable = false)
	private Long numanipla;

	public AniosPlazoCapitalEndeudamientoPk() {

	}

	public boolean equals(Object other) {
		if (other instanceof AniosPlazoCapitalEndeudamientoPk) {
			final AniosPlazoCapitalEndeudamientoPk otherKscretaniplacapendPK = (AniosPlazoCapitalEndeudamientoPk) other;
			final boolean areEqual = (otherKscretaniplacapendPK.codprecla
					.equals(codprecla)
					&& otherKscretaniplacapendPK.codpretip.equals(codpretip) && otherKscretaniplacapendPK.numanipla
					.equals(numanipla));
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
	 * @return the numanipla
	 */
	public Long getNumanipla() {
		return numanipla;
	}

	/**
	 * @param numanipla
	 *            the numanipla to set
	 */
	public void setNumanipla(Long numanipla) {
		this.numanipla = numanipla;
	}

}
