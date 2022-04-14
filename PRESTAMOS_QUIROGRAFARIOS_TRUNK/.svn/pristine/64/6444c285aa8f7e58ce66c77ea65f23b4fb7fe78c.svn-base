package ec.gov.iess.planillaspq.modelo.persistencia;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.planillaspq.modelo.persistencia.pk.ComprobantePagoDetallePK;

@Entity
@Table(name="ksrectcompagdet")
@NamedQueries({
	@NamedQuery(name = "ComprobanteDetalle.BuscarPorId", 
		    query = "select o from ComprobantePagoDetalle o where o.pk = :Id")
})
public class ComprobantePagoDetalle implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComprobantePagoDetallePK pk;

	private String codtipglo;

	private String codcon;

	private BigDecimal numcuo;

	private String codtit;

	private BigDecimal numdettit;

	private String codglo;

	private BigDecimal numdetglo;

	private String codtipcon;

	private String codtiprubcompag;

	private String tiptra;

	private BigDecimal valcandet;

	private BigDecimal valintdet;

	private String obsdet;

	private String codgar;

	private String codtipgar;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CODTIPCOMPAG", referencedColumnName="CODTIPCOMPAG", insertable = false, updatable = false),
		@JoinColumn(name="CODCOMPAG", referencedColumnName="CODCOMPAG", insertable = false, updatable = false)
	})
	private ComprobantePagoPla comprobantepago;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="RUCEMP", referencedColumnName="RUCEMP", insertable = false, updatable = false),
		@JoinColumn(name="CODSUC", referencedColumnName="CODSUC", insertable = false, updatable = false),
		@JoinColumn(name="CODTIPPLA", referencedColumnName="CODTIPPLA", insertable = false, updatable = false),
		@JoinColumn(name="TIPPER", referencedColumnName="TIPPER", insertable = false, updatable = false),
		@JoinColumn(name="ANIPER", referencedColumnName="ANIPER", insertable = false, updatable = false),
		@JoinColumn(name="MESPER", referencedColumnName="MESPER", insertable = false, updatable = false),
		@JoinColumn(name="SECPLA", referencedColumnName="SECPLA", insertable = false, updatable = false)
	})
	private Planillas planillas;


	public ComprobantePagoDetalle() {
		super();
	}

	public ComprobantePagoDetallePK getPk() {
		return this.pk;
	}

	public void setPk(ComprobantePagoDetallePK pk) {
		this.pk = pk;
	}

	public String getCodtipglo() {
		return this.codtipglo;
	}

	public void setCodtipglo(String codtipglo) {
		this.codtipglo = codtipglo;
	}

	public String getCodcon() {
		return this.codcon;
	}

	public void setCodcon(String codcon) {
		this.codcon = codcon;
	}

	public BigDecimal getNumcuo() {
		return this.numcuo;
	}

	public void setNumcuo(BigDecimal numcuo) {
		this.numcuo = numcuo;
	}

	public String getCodtit() {
		return this.codtit;
	}

	public void setCodtit(String codtit) {
		this.codtit = codtit;
	}

	public BigDecimal getNumdettit() {
		return this.numdettit;
	}

	public void setNumdettit(BigDecimal numdettit) {
		this.numdettit = numdettit;
	}

	public String getCodglo() {
		return this.codglo;
	}

	public void setCodglo(String codglo) {
		this.codglo = codglo;
	}

	public BigDecimal getNumdetglo() {
		return this.numdetglo;
	}

	public void setNumdetglo(BigDecimal numdetglo) {
		this.numdetglo = numdetglo;
	}

	public String getCodtipcon() {
		return this.codtipcon;
	}

	public void setCodtipcon(String codtipcon) {
		this.codtipcon = codtipcon;
	}

	public String getCodtiprubcompag() {
		return this.codtiprubcompag;
	}

	public void setCodtiprubcompag(String codtiprubcompag) {
		this.codtiprubcompag = codtiprubcompag;
	}

	public String getTiptra() {
		return this.tiptra;
	}

	public void setTiptra(String tiptra) {
		this.tiptra = tiptra;
	}

	public BigDecimal getValcandet() {
		return this.valcandet;
	}

	public void setValcandet(BigDecimal valcandet) {
		this.valcandet = valcandet;
	}

	public BigDecimal getValintdet() {
		return this.valintdet;
	}

	public void setValintdet(BigDecimal valintdet) {
		this.valintdet = valintdet;
	}

	public String getObsdet() {
		return this.obsdet;
	}

	public void setObsdet(String obsdet) {
		this.obsdet = obsdet;
	}

	public String getCodgar() {
		return this.codgar;
	}

	public void setCodgar(String codgar) {
		this.codgar = codgar;
	}

	public String getCodtipgar() {
		return this.codtipgar;
	}

	public void setCodtipgar(String codtipgar) {
		this.codtipgar = codtipgar;
	}

	public ComprobantePagoPla getComprobantepago() {
		return this.comprobantepago;
	}

	public void setComprobantepago(ComprobantePagoPla comprobantepago) {
		this.comprobantepago = comprobantepago;
	}

	public Planillas getPlanillas() {
		return this.planillas;
	}

	public void setPlanillas(Planillas planillas) {
		this.planillas = planillas;
	}

}
