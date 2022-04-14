package ec.gov.iess.planillaspq.modelo.persistencia.pk;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ComprobantePagoDetallePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CODTIPCOMPAG", nullable = false)
	private String codtipcompag;

	@Column(name = "CODCOMPAG", nullable = false)
	private String codcompag;

	@Column(name = "NUMDET", nullable = false)
	private BigDecimal numdet;


	public ComprobantePagoDetallePK(String codtipcompag, String codcompag,
			BigDecimal bigDecimal) {
		super();
		this.codtipcompag = codtipcompag;
		this.codcompag = codcompag;
		this.numdet = bigDecimal;
	}

	public ComprobantePagoDetallePK() {
		super();
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

	public BigDecimal getNumdet() {
		return this.numdet;
	}

	public void setNumdet(BigDecimal numdet) {
		this.numdet = numdet;
	}
/*
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if ( ! (o instanceof ComprobantePagoDetallePK)) {
			return false;
		}
		ComprobantePagoDetallePK other = (ComprobantePagoDetallePK) o;
		return this.codtipcompag.equals(other.codtipcompag)
			&& this.codcompag.equals(other.codcompag)
			&& (this.numdet == other.numdet);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codtipcompag.hashCode();
		hash = hash * prime + this.codcompag.hashCode();
		hash = hash * prime + ((int) (this.numdet ^ (this.numdet >>> 32)));
		return hash;
	}
*/
}
