package ec.gov.iess.planillaspq.modelo.persistencia.pk;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class DividendosHistoricoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(insertable=false, updatable=false)
	private long ordpreafi;

	@Column(insertable=false, updatable=false)
	private long numdiv;

	@Column(insertable=false, updatable=false)
	private long codprecla;

	@Column(insertable=false, updatable=false)
	private long codpretip;

	@Column(insertable=false, updatable=false)
	private long numpreafi;

	private Date fecini;

	private String codestdiv;

	public DividendosHistoricoPK(long ordpreafi, long numdiv, long codprecla,
			long codpretip, long numpreafi, String codestdiv) {
		super();
		this.ordpreafi = ordpreafi;
		this.numdiv = numdiv;
		this.codprecla = codprecla;
		this.codpretip = codpretip;
		this.numpreafi = numpreafi;
		this.codestdiv = codestdiv;
	}

	public DividendosHistoricoPK(long ordpreafi, long numdiv, long codprecla,
			long codpretip, long numpreafi, Date fechahoy, String codestdiv) {
		super();
		this.ordpreafi = ordpreafi;
		this.numdiv = numdiv;
		this.codprecla = codprecla;
		this.codpretip = codpretip;
		this.numpreafi = numpreafi;
		this.fecini = fechahoy;
		this.codestdiv = codestdiv;
	}

	public DividendosHistoricoPK() {
		super();
	}

	public long getOrdpreafi() {
		return this.ordpreafi;
	}

	public void setOrdpreafi(long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

	public long getNumdiv() {
		return this.numdiv;
	}

	public void setNumdiv(long numdiv) {
		this.numdiv = numdiv;
	}

	public long getCodprecla() {
		return this.codprecla;
	}

	public void setCodprecla(long codprecla) {
		this.codprecla = codprecla;
	}

	public long getCodpretip() {
		return this.codpretip;
	}

	public void setCodpretip(long codpretip) {
		this.codpretip = codpretip;
	}

	public long getNumpreafi() {
		return this.numpreafi;
	}

	public void setNumpreafi(long numpreafi) {
		this.numpreafi = numpreafi;
	}

	public Date getFecini() {
		return this.fecini;
	}

	public void setFecini(Date fecini) {
		this.fecini = fecini;
	}

	public String getCodestdiv() {
		return this.codestdiv;
	}

	public void setCodestdiv(String codestdiv) {
		this.codestdiv = codestdiv;
	}
/*
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if ( ! (o instanceof DividendosHistoricoPK)) {
			return false;
		}
		DividendosHistoricoPK other = (DividendosHistoricoPK) o;
		return (this.ordpreafi == other.ordpreafi)
			&& (this.numdiv == other.numdiv)
			&& (this.codprecla == other.codprecla)
			&& (this.codpretip == other.codpretip)
			&& (this.numpreafi == other.numpreafi)
			&& this.fecini.equals(other.fecini)
			&& this.codestdiv.equals(other.codestdiv);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.ordpreafi ^ (this.ordpreafi >>> 32)));
		hash = hash * prime + ((int) (this.numdiv ^ (this.numdiv >>> 32)));
		hash = hash * prime + ((int) (this.codprecla ^ (this.codprecla >>> 32)));
		hash = hash * prime + ((int) (this.codpretip ^ (this.codpretip >>> 32)));
		hash = hash * prime + ((int) (this.numpreafi ^ (this.numpreafi >>> 32)));
		hash = hash * prime + this.fecini.hashCode();
		hash = hash * prime + this.codestdiv.hashCode();
		return hash;
	}
*/
}
