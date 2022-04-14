/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
@Embeddable
public class TasaInteresDetallePK implements Serializable {

	@Column(nullable = false)
	private String codtasint;

	@Column(nullable = false)
	private Date fecinitasint;

	/**
	 * 
	 */
	public TasaInteresDetallePK() {
	}

	public TasaInteresDetallePK(String codtasint, Date fecinitasint) {
		this.codtasint = codtasint;
		this.fecinitasint = fecinitasint;
	}

	public boolean equals(Object other) {
		if (other instanceof TasaInteresDetallePK) {
			final TasaInteresDetallePK otherKscretinttasdetPK = (TasaInteresDetallePK) other;
			final boolean areEqual = (otherKscretinttasdetPK.codtasint
					.equals(codtasint) && otherKscretinttasdetPK.fecinitasint
					.equals(fecinitasint));
			return areEqual;
		}
		return false;
	}

	/**
	 * @return the codtasint
	 */
	public String getCodtasint() {
		return codtasint;
	}

	/**
	 * @param codtasint
	 *            the codtasint to set
	 */
	public void setCodtasint(String codtasint) {
		this.codtasint = codtasint;
	}

	/**
	 * @return the fecinitasint
	 */
	public Date getFecinitasint() {
		return fecinitasint;
	}

	/**
	 * @param fecinitasint
	 *            the fecinitasint to set
	 */
	public void setFecinitasint(Date fecinitasint) {
		this.fecinitasint = fecinitasint;
	}

}
