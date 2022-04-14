/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.GarantiaPrestamoPK;

/**
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "THLCRT_GARANTIAS")
public class GarantiaPrestamo implements Serializable {

	@EmbeddedId
	private GarantiaPrestamoPK garantiaPrestamoPK;

	private String blogarces;
	private String blogarfonres;
	private Date feccanpre;
	private Date fecobtces;
	private Date fecobtfonres;
	@Column(nullable = false)
	private Date fecpreafi;
	private Double valcesusadeb;
	private Double valcomceshl;
	private Double valcomfonres;
	private Double valdisceshl;
	private Double valdisceshos;
	private Double valdisfonres;
	private Double valfonresusadeb;
	private String estmig;
	private Date fecmig;
	private Double salporaplfonres;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODESTGARFON", referencedColumnName = "CODESTGARFON")
	private EstadoGarantia estadoGarantia;

	public EstadoGarantia getEstadoGarantia() {
		return estadoGarantia;
	}

	public void setEstadoGarantia(EstadoGarantia estadoGarantia) {
		this.estadoGarantia = estadoGarantia;
	}

	/**
	 * @return the valfonresusadeb
	 */
	public Double getValfonresusadeb() {
		return valfonresusadeb;
	}

	/**
	 * @param valfonresusadeb the valfonresusadeb to set
	 */
	public void setValfonresusadeb(Double valfonresusadeb) {
		this.valfonresusadeb = valfonresusadeb;
	}

	/**
	 * 
	 */
	public GarantiaPrestamo() {
	}

	/**
	 * @return the garantiaPrestamoPK
	 */
	public GarantiaPrestamoPK getGarantiaPrestamoPK() {
		return garantiaPrestamoPK;
	}

	/**
	 * @return the blogarces
	 */
	public String getBlogarces() {
		return blogarces;
	}

	/**
	 * @return the blogarfonres
	 */
	public String getBlogarfonres() {
		return blogarfonres;
	}

	/**
	 * @return the feccanpre
	 */
	public Date getFeccanpre() {
		return feccanpre;
	}

	/**
	 * @return the fecobtces
	 */
	public Date getFecobtces() {
		return fecobtces;
	}

	/**
	 * @return the fecobtfonres
	 */
	public Date getFecobtfonres() {
		return fecobtfonres;
	}

	/**
	 * @return the fecpreafi
	 */
	public Date getFecpreafi() {
		return fecpreafi;
	}

	/**
	 * @return the valcesusadeb
	 */
	public Double getValcesusadeb() {
		return valcesusadeb;
	}

	/**
	 * @return the valcomceshl
	 */
	public Double getValcomceshl() {
		return valcomceshl;
	}

	/**
	 * @return the valcomfonres
	 */
	public Double getValcomfonres() {
		return valcomfonres;
	}

	/**
	 * @return the valdisceshl
	 */
	public Double getValdisceshl() {
		return valdisceshl;
	}

	/**
	 * @return the valdisceshos
	 */
	public Double getValdisceshos() {
		return valdisceshos;
	}

	/**
	 * @return the valdisfonres
	 */
	public Double getValdisfonres() {
		return valdisfonres;
	}

	/**
	 * @param garantiaPrestamoPK the garantiaPrestamoPK to set
	 */
	public void setGarantiaPrestamoPK(GarantiaPrestamoPK garantiaPrestamoPK) {
		this.garantiaPrestamoPK = garantiaPrestamoPK;
	}

	/**
	 * @param blogarces the blogarces to set
	 */
	public void setBlogarces(String blogarces) {
		this.blogarces = blogarces;
	}

	/**
	 * @param blogarfonres the blogarfonres to set
	 */
	public void setBlogarfonres(String blogarfonres) {
		this.blogarfonres = blogarfonres;
	}

	/**
	 * @param feccanpre the feccanpre to set
	 */
	public void setFeccanpre(Date feccanpre) {
		this.feccanpre = feccanpre;
	}

	/**
	 * @param fecobtces the fecobtces to set
	 */
	public void setFecobtces(Date fecobtces) {
		this.fecobtces = fecobtces;
	}

	/**
	 * @param fecobtfonres the fecobtfonres to set
	 */
	public void setFecobtfonres(Date fecobtfonres) {
		this.fecobtfonres = fecobtfonres;
	}

	/**
	 * @param fecpreafi the fecpreafi to set
	 */
	public void setFecpreafi(Date fecpreafi) {
		this.fecpreafi = fecpreafi;
	}

	/**
	 * @param valcesusadeb the valcesusadeb to set
	 */
	public void setValcesusadeb(Double valcesusadeb) {
		this.valcesusadeb = valcesusadeb;
	}

	/**
	 * @param valcomceshl the valcomceshl to set
	 */
	public void setValcomceshl(Double valcomceshl) {
		this.valcomceshl = valcomceshl;
	}

	/**
	 * @param valcomfonres the valcomfonres to set
	 */
	public void setValcomfonres(Double valcomfonres) {
		this.valcomfonres = valcomfonres;
	}

	/**
	 * @param valdisceshl the valdisceshl to set
	 */
	public void setValdisceshl(Double valdisceshl) {
		this.valdisceshl = valdisceshl;
	}

	/**
	 * @param valdisceshos the valdisceshos to set
	 */
	public void setValdisceshos(Double valdisceshos) {
		this.valdisceshos = valdisceshos;
	}

	/**
	 * @param valdisfonres the valdisfonres to set
	 */
	public void setValdisfonres(Double valdisfonres) {
		this.valdisfonres = valdisfonres;
	}

	/**
	 * @return the estmig
	 */
	public String getEstmig() {
		return estmig;
	}

	/**
	 * @return the fecmig
	 */
	public Date getFecmig() {
		return fecmig;
	}

	/**
	 * @param estmig the estmig to set
	 */
	public void setEstmig(String estmig) {
		this.estmig = estmig;
	}

	/**
	 * @param fecmig the fecmig to set
	 */
	public void setFecmig(Date fecmig) {
		this.fecmig = fecmig;
	}


	public Double getSalporaplfonres() {
		return salporaplfonres;
	}

	public void setSalporaplfonres(Double salporaplfonres) {
		this.salporaplfonres = salporaplfonres;
	}


}
