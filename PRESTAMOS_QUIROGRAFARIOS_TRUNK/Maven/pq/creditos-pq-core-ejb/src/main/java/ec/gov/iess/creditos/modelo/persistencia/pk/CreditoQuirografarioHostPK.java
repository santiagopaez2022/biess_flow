package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class CreditoQuirografarioHostPK implements Serializable {

	private static final long serialVersionUID = -7348858659380135817L;

	@Column(nullable = false)
	private String bolcon;

	@Column(name = "CEDULA")
	private String cedula;

	@Column(nullable = false)
	private String clapre;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date inipre;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date finpre;

	public CreditoQuirografarioHostPK() {

	}

	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(Object other) {
		if (other instanceof CreditoQuirografarioHostPK) {
			final CreditoQuirografarioHostPK creditoQuirografarioHostPK = (CreditoQuirografarioHostPK) other;
			final boolean areEqual = (creditoQuirografarioHostPK.bolcon
					.equals(bolcon)
					&& creditoQuirografarioHostPK.cedula.equals(cedula)
					&& creditoQuirografarioHostPK.clapre.equals(clapre)
					&& creditoQuirografarioHostPK.inipre.equals(inipre) && creditoQuirografarioHostPK.finpre
					.equals(finpre));
			return areEqual;
		}
		return false;
	}

	/**
	 * @return the bolcon
	 */
	public String getBolcon() {
		return bolcon;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @return the clapre
	 */
	public String getClapre() {
		return clapre;
	}

	/**
	 * @return the inipre
	 */
	public Date getInipre() {
		return inipre;
	}

	/**
	 * @return the finpre
	 */
	public Date getFinpre() {
		return finpre;
	}

	/**
	 * @param bolcon
	 *            the bolcon to set
	 */
	public void setBolcon(String bolcon) {
		this.bolcon = bolcon;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @param clapre
	 *            the clapre to set
	 */
	public void setClapre(String clapre) {
		this.clapre = clapre;
	}

	/**
	 * @param inipre
	 *            the inipre to set
	 */
	public void setInipre(Date inipre) {
		this.inipre = inipre;
	}

	/**
	 * @param finpre
	 *            the finpre to set
	 */
	public void setFinpre(Date finpre) {
		this.finpre = finpre;
	}

}
