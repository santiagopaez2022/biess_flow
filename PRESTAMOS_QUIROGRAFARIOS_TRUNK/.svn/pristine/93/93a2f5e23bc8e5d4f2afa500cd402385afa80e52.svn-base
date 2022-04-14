package ec.gov.iess.planillaspq.modelo.persistencia.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillasPK;



@Embeddable
public class PlanillasPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "RUCEMP", nullable = false)
	private String rucemp;

	@Column(name = "CODSUC", nullable = false)
	private String codsuc;

	@Column(name = "CODTIPPLA", nullable = false)
	private String codtippla;

	@Column(name = "TIPPER", nullable = false)
	private String tipper;

	@Column(name = "ANIPER", nullable = false)
	private Long aniper;

	@Column(name = "MESPER", nullable = false)
	private Long mesper;

	@Column(name = "SECPLA", nullable = false)
	private Long secpla;


	public PlanillasPK() {
		super();
	}

	public String getRucemp() {
		return this.rucemp;
	}

	public void setRucemp(String rucemp) {
		this.rucemp = rucemp;
	}

	public String getCodsuc() {
		return this.codsuc;
	}

	public void setCodsuc(String codsuc) {
		this.codsuc = codsuc;
	}

	public String getCodtippla() {
		return this.codtippla;
	}

	public void setCodtippla(String codtippla) {
		this.codtippla = codtippla;
	}

	public String getTipper() {
		return this.tipper;
	}

	public void setTipper(String tipper) {
		this.tipper = tipper;
	}

	public Long getAniper() {
		return this.aniper;
	}

	public void setAniper(Long aniper) {
		this.aniper = aniper;
	}

	public Long getMesper() {
		return this.mesper;
	}

	public void setMesper(Long mesper) {
		this.mesper = mesper;
	}

	public Long getSecpla() {
		return this.secpla;
	}

	public void setSecpla(Long secpla) {
		this.secpla = secpla;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if ( ! (o instanceof PlanillasPK)) {
			return false;
		}
		PlanillasPK other = (PlanillasPK) o;
		return this.rucemp.equals(other.rucemp)
			&& this.codsuc.equals(other.codsuc)
			&& this.codtippla.equals(other.codtippla)
			&& this.tipper.equals(other.tipper)
			&& (this.aniper == other.aniper)
			&& (this.mesper == other.mesper)
			&& (this.secpla == other.secpla);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rucemp.hashCode();
		hash = hash * prime + this.codsuc.hashCode();
		hash = hash * prime + this.codtippla.hashCode();
		hash = hash * prime + this.tipper.hashCode();
		hash = hash * prime + ((int) (this.aniper ^ (this.aniper >>> 32)));
		hash = hash * prime + ((int) (this.mesper ^ (this.mesper >>> 32)));
		hash = hash * prime + ((int) (this.secpla ^ (this.secpla >>> 32)));
		return hash;
	}

}
