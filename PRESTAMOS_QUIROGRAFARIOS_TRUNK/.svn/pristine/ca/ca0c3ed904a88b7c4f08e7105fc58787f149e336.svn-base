package ec.gov.iess.planillaspq.modelo.persistencia.pk;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PagoParcialPK implements Serializable {
	@Column(name="PP_ID")
	private long ppId;

	private String codtipcompag;

	private String codcompag;

	private static final long serialVersionUID = 1L;

	public PagoParcialPK() {
		super();
	}

	public long getPpId() {
		return this.ppId;
	}

	public void setPpId(long ppId) {
		this.ppId = ppId;
	}

	public String getCodtipcompag() {
		return this.codtipcompag;
	}

	public void setCodtipcompag(String codtipcompag) {
		this.codtipcompag = codtipcompag;
	}

	public String getCodcompag() {
		return this.codcompag;
	}

	public void setCodcompag(String codcompag) {
		this.codcompag = codcompag;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if ( ! (o instanceof PagoParcialPK)) {
			return false;
		}
		PagoParcialPK other = (PagoParcialPK) o;
		return (this.ppId == other.ppId)
			&& this.codtipcompag.equals(other.codtipcompag)
			&& this.codcompag.equals(other.codcompag);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.ppId ^ (this.ppId >>> 32)));
		hash = hash * prime + this.codtipcompag.hashCode();
		hash = hash * prime + this.codcompag.hashCode();
		return hash;
	}

}
