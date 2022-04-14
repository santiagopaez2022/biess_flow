package ec.gov.iess.planillaspq.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class DividendosPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long numdiv;

	private long codpretip;

	private long ordpreafi;

	private long numpreafi;

	private long codprecla;

	public DividendosPK() {
		super();
	}

	public DividendosPK(long numdiv, long codpretip, long ordpreafi,
			long numpreafi, long codprecla) {
		super();
		this.numdiv = numdiv;
		this.codpretip = codpretip;
		this.ordpreafi = ordpreafi;
		this.numpreafi = numpreafi;
		this.codprecla = codprecla;
	}

	public long getNumdiv() {
		return this.numdiv;
	}

	public void setNumdiv(long numdiv) {
		this.numdiv = numdiv;
	}

	public long getCodpretip() {
		return this.codpretip;
	}

	public void setCodpretip(long codpretip) {
		this.codpretip = codpretip;
	}

	public long getOrdpreafi() {
		return this.ordpreafi;
	}

	public void setOrdpreafi(long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

	public long getNumpreafi() {
		return this.numpreafi;
	}

	public void setNumpreafi(long numpreafi) {
		this.numpreafi = numpreafi;
	}

	public long getCodprecla() {
		return this.codprecla;
	}

	public void setCodprecla(long codprecla) {
		this.codprecla = codprecla;
	}
/*
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof DividendosPK)) {
			return false;
		}
		DividendosPK other = (DividendosPK) o;
		return (this.numdiv == other.numdiv)
				&& (this.codpretip == other.codpretip)
				&& (this.ordpreafi == other.ordpreafi)
				&& (this.numpreafi == other.numpreafi)
				&& (this.codprecla == other.codprecla);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numdiv ^ (this.numdiv >>> 32)));
		hash = hash * prime
				+ ((int) (this.codpretip ^ (this.codpretip >>> 32)));
		hash = hash * prime
				+ ((int) (this.ordpreafi ^ (this.ordpreafi >>> 32)));
		hash = hash * prime
				+ ((int) (this.numpreafi ^ (this.numpreafi >>> 32)));
		hash = hash * prime
				+ ((int) (this.codprecla ^ (this.codprecla >>> 32)));
		return hash;
	}
*/
}
