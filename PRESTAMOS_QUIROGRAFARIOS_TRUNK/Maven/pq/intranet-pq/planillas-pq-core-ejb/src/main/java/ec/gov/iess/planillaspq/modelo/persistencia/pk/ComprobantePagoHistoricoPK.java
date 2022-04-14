package ec.gov.iess.planillaspq.modelo.persistencia.pk;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;

@Embeddable
public class ComprobantePagoHistoricoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "CODTIPCOMPAG", nullable = false)
	private String codtipcompag;

	@Column(name = "CODCOMPAG", nullable = false)
	private String codcompag;

	@Column(name = "CODESTCOMPAG", nullable = false)
	private String codestcompag;

	@Column(name = "FECINI", nullable = false)
	private Date fecini;


	public ComprobantePagoHistoricoPK() {
		super();
	}

	public ComprobantePagoHistoricoPK(String codtipcompag, String codcompag,
			String codestcompag) {
		this.codtipcompag = codtipcompag;
		this.codcompag = codcompag;
		this.codestcompag = codestcompag;
	}

	public ComprobantePagoHistoricoPK(String codtipcompag, String codcompag,
			String codestcompag, Date fechahoy) {
		this.codtipcompag = codtipcompag;
		this.codcompag = codcompag;
		this.codestcompag = codestcompag;
		this.fecini = fechahoy;
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

	public String getCodestcompag() {
		return this.codestcompag;
	}

	public void setCodestcompag(String codestcompag) {
		this.codestcompag = codestcompag;
	}

	public Date getFecini() {
		return this.fecini;
	}

	public void setFecini(Date fecini) {
		this.fecini = fecini;
	}

	
	/*
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if ( ! (o instanceof ComprobantePagoHistoricoPK)) {
			return false;
		}
		ComprobantePagoHistoricoPK other = (ComprobantePagoHistoricoPK) o;
		return this.codtipcompag.equals(other.codtipcompag)
			&& this.codcompag.equals(other.codcompag)
			&& this.codestcompag.equals(other.codestcompag)
			&& this.fecini.equals(other.fecini);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codtipcompag.hashCode();
		hash = hash * prime + this.codcompag.hashCode();
		hash = hash * prime + this.codestcompag.hashCode();
		hash = hash * prime + this.fecini.hashCode();
		return hash;
	}
*/
}
