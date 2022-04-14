package ec.gov.iess.planillaspq.modelo.persistencia.pk;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class PlanillaPrestamosDetallePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="TIPPER",nullable = false)
	private String tipper;

	@Column(name="ANIPER",nullable = false)
	private Long aniper;

	@Column(name="CODSUC",nullable = false)
	private String codsuc;

	@Column(name="RUCEMP",nullable = false)
	private String rucemp;

	@Column(name="SECPLA",nullable = false)
	private Long secpla;

	@Column(name="MESPER",nullable = false)
	private Long mesper;

	@Column(name="CODTIPPLA",nullable = false)
	private String codtippla;

	@Column(name="ORDPREAFI",nullable = false)
	private Long ordpreafi;

	@Column(name="CODPRETIP",nullable = false)
	private Long codpretip;

	@Column(name="CODPRECLA",nullable = false)
	private Long codprecla;

	@Column(name="NUMPREAFI",nullable = false)
	private Long numpreafi;

	@Column(name="NUMDIV",nullable = false)
	private Long numdiv;

	public PlanillaPrestamosDetallePK() {
		super();
	}

	public PlanillaPrestamosDetallePK(String tipper, Long aniper,
			String codsuc, String rucemp, Long secpla, Long mesper,
			String codtippla, Long ordpreafi, Long codpretip, Long codprecla,
			Long numpreafi, Long numdiv) {
		super();
		this.tipper = tipper;
		this.aniper = aniper;
		this.codsuc = codsuc;
		this.rucemp = rucemp;
		this.secpla = secpla;
		this.mesper = mesper;
		this.codtippla = codtippla;
		this.ordpreafi = ordpreafi;
		this.codpretip = codpretip;
		this.codprecla = codprecla;
		this.numpreafi = numpreafi;
		this.numdiv = numdiv;
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

	public String getCodsuc() {
		return this.codsuc;
	}

	public void setCodsuc(String codsuc) {
		this.codsuc = codsuc;
	}

	public String getRucemp() {
		return this.rucemp;
	}

	public void setRucemp(String rucemp) {
		this.rucemp = rucemp;
	}

	public Long getSecpla() {
		return this.secpla;
	}

	public void setSecpla(Long secpla) {
		this.secpla = secpla;
	}

	public Long getMesper() {
		return this.mesper;
	}

	public void setMesper(Long mesper) {
		this.mesper = mesper;
	}

	public String getCodtippla() {
		return this.codtippla;
	}

	public void setCodtippla(String codtippla) {
		this.codtippla = codtippla;
	}

	public Long getOrdpreafi() {
		return this.ordpreafi;
	}

	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

	public Long getCodpretip() {
		return this.codpretip;
	}

	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	public Long getCodprecla() {
		return this.codprecla;
	}

	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	public Long getNumpreafi() {
		return this.numpreafi;
	}

	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	public Long getNumdiv() {
		return this.numdiv;
	}

	public void setNumdiv(Long numdiv) {
		this.numdiv = numdiv;
	}
/*
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if ( ! (o instanceof PlanillaPrestamosDetallePK)) {
			return false;
		}
		PlanillaPrestamosDetallePK other = (PlanillaPrestamosDetallePK) o;
		return this.tipper.equals(other.tipper)
			&& (this.aniper == other.aniper)
			&& this.codsuc.equals(other.codsuc)
			&& this.rucemp.equals(other.rucemp)
			&& (this.secpla == other.secpla)
			&& (this.mesper == other.mesper)
			&& this.codtippla.equals(other.codtippla)
			&& (this.ordpreafi == other.ordpreafi)
			&& (this.codpretip == other.codpretip)
			&& (this.codprecla == other.codprecla)
			&& (this.numpreafi == other.numpreafi)
			&& (this.numdiv == other.numdiv);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tipper.hashCode();
		hash = hash * prime + ((int) (this.aniper ^ (this.aniper >>> 32)));
		hash = hash * prime + this.codsuc.hashCode();
		hash = hash * prime + this.rucemp.hashCode();
		hash = hash * prime + ((int) (this.secpla ^ (this.secpla >>> 32)));
		hash = hash * prime + ((int) (this.mesper ^ (this.mesper >>> 32)));
		hash = hash * prime + this.codtippla.hashCode();
		hash = hash * prime + ((int) (this.ordpreafi ^ (this.ordpreafi >>> 32)));
		hash = hash * prime + ((int) (this.codpretip ^ (this.codpretip >>> 32)));
		hash = hash * prime + ((int) (this.codprecla ^ (this.codprecla >>> 32)));
		hash = hash * prime + ((int) (this.numpreafi ^ (this.numpreafi >>> 32)));
		hash = hash * prime + ((int) (this.numdiv ^ (this.numdiv >>> 32)));
		return hash;
	}
*/
}
