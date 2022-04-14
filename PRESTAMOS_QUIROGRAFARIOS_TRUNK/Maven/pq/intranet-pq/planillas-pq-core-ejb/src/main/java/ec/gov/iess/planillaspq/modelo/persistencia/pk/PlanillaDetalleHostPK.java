package ec.gov.iess.planillaspq.modelo.persistencia.pk;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class PlanillaDetalleHostPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numafi;

	private long aniper;

	private long secpla;

	private long mesper;

	private String codtippla;

	private String codsuc;

	private String tipper;

	private String rucemp;

	public PlanillaDetalleHostPK() {
		super();
	}

	public String getNumafi() {
		return this.numafi;
	}

	public void setNumafi(String numafi) {
		this.numafi = numafi;
	}

	public long getAniper() {
		return this.aniper;
	}

	public void setAniper(long aniper) {
		this.aniper = aniper;
	}

	public long getSecpla() {
		return this.secpla;
	}

	public void setSecpla(long secpla) {
		this.secpla = secpla;
	}

	public long getMesper() {
		return this.mesper;
	}

	public void setMesper(long mesper) {
		this.mesper = mesper;
	}

	public String getCodtippla() {
		return this.codtippla;
	}

	public void setCodtippla(String codtippla) {
		this.codtippla = codtippla;
	}

	public String getCodsuc() {
		return this.codsuc;
	}

	public void setCodsuc(String codsuc) {
		this.codsuc = codsuc;
	}

	public String getTipper() {
		return this.tipper;
	}

	public void setTipper(String tipper) {
		this.tipper = tipper;
	}

	public String getRucemp() {
		return this.rucemp;
	}

	public void setRucemp(String rucemp) {
		this.rucemp = rucemp;
	}
/*
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof PlanillaDetalleHostPK)) {
			return false;
		}
		PlanillaDetalleHostPK other = (PlanillaDetalleHostPK) o;
		return this.numafi.equals(other.numafi)
				&& (this.aniper == other.aniper)
				&& (this.secpla == other.secpla)
				&& (this.mesper == other.mesper)
				&& this.codtippla.equals(other.codtippla)
				&& this.codsuc.equals(other.codsuc)
				&& this.tipper.equals(other.tipper)
				&& this.rucemp.equals(other.rucemp);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numafi.hashCode();
		hash = hash * prime + ((int) (this.aniper ^ (this.aniper >>> 32)));
		hash = hash * prime + ((int) (this.secpla ^ (this.secpla >>> 32)));
		hash = hash * prime + ((int) (this.mesper ^ (this.mesper >>> 32)));
		hash = hash * prime + this.codtippla.hashCode();
		hash = hash * prime + this.codsuc.hashCode();
		hash = hash * prime + this.tipper.hashCode();
		hash = hash * prime + this.rucemp.hashCode();
		return hash;
	}
*/
}
