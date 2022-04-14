package ec.gov.iess.planillaspq.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillasPK;

@Entity
@Table(name = "Ksrectplanillas")
@NamedQueries({
	@NamedQuery(name = "Planillas.BuscarPorId", 
		    query = "select o from Planillas o where o.pk = :Id"),
	@NamedQuery(name = "Planillas.BuscarPorRuc", 
			query = "select o from Planillas o where o.pk.rucemp= :rucemp " +
				    		"and o.pk.codsuc= :codsuc " +
				    		"and o.pk.aniper= :aniper " +
				    		"and o.pk.mesper= :mesper " +
				    		"and o.pk.codtippla= :codtippla " +
				    		"and o.pk.tipper= :tipper " +
				    		"and o.esttippla not in (:estcan)")
})
public class Planillas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlanillasPK pk;

	private Integer numdetpla;

	private String esttippla;

	private Date feccrepla;

	private Date fecpagpla;

	private BigDecimal valnorpla;

	private BigDecimal valadipla;

	private BigDecimal valcesadipla;

	private BigDecimal valiecpla;

	private BigDecimal valsecpla;

	private Date feccanpla;

	private String pagbancen;

	private Date fecfirconpre;

	private Date fectrabancen;

	private Date fectratesemp;

	private Date fecdispla;

	private String idetipjus;

	private String calint;

	private String pleesp;

	private BigDecimal valmulgasadm;

	private BigDecimal valmulnovext;

	private String banderaimpos;

	private BigDecimal marca;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CODTIPCOMPAG", referencedColumnName="CODTIPCOMPAG", insertable = false, updatable = false),
		@JoinColumn(name="CODCOMPAG", referencedColumnName="CODCOMPAG", insertable = false, updatable = false)
	})
	private ComprobantePagoPla comprobantepago;

	@OneToMany(mappedBy="planillas")
	private List<PlanillaPrestamosDetalle> kscretplapredetCollection;

	@OneToMany(mappedBy="planillas")
	private List<ComprobantePagoDetalle> ksrectcompagdetCollection;

	@Transient
	private String nombreEmpleador;

	public Planillas() {
		super();
	}

	public PlanillasPK getPk() {
		return this.pk;
	}

	public void setPk(PlanillasPK pk) {
		this.pk = pk;
	}

	public Integer getNumdetpla() {
		return this.numdetpla;
	}

	public void setNumdetpla(Integer numdetpla) {
		this.numdetpla = numdetpla;
	}

	public String getEsttippla() {
		return this.esttippla;
	}

	public void setEsttippla(String esttippla) {
		this.esttippla = esttippla;
	}

	public Date getFeccrepla() {
		return this.feccrepla;
	}

	public void setFeccrepla(Date feccrepla) {
		this.feccrepla = feccrepla;
	}

	public Date getFecpagpla() {
		return this.fecpagpla;
	}

	public void setFecpagpla(Date fecpagpla) {
		this.fecpagpla = fecpagpla;
	}

	public BigDecimal getValnorpla() {
		return this.valnorpla;
	}

	public void setValnorpla(BigDecimal valnorpla) {
		this.valnorpla = valnorpla;
	}

	public BigDecimal getValadipla() {
		return this.valadipla;
	}

	public void setValadipla(BigDecimal valadipla) {
		this.valadipla = valadipla;
	}

	public BigDecimal getValcesadipla() {
		return this.valcesadipla;
	}

	public void setValcesadipla(BigDecimal valcesadipla) {
		this.valcesadipla = valcesadipla;
	}

	public BigDecimal getValiecpla() {
		return this.valiecpla;
	}

	public void setValiecpla(BigDecimal valiecpla) {
		this.valiecpla = valiecpla;
	}

	public BigDecimal getValsecpla() {
		return this.valsecpla;
	}

	public void setValsecpla(BigDecimal valsecpla) {
		this.valsecpla = valsecpla;
	}

	public Date getFeccanpla() {
		return this.feccanpla;
	}

	public void setFeccanpla(Date feccanpla) {
		this.feccanpla = feccanpla;
	}

	public String getPagbancen() {
		return this.pagbancen;
	}

	public void setPagbancen(String pagbancen) {
		this.pagbancen = pagbancen;
	}

	public Date getFecfirconpre() {
		return this.fecfirconpre;
	}

	public void setFecfirconpre(Date fecfirconpre) {
		this.fecfirconpre = fecfirconpre;
	}

	public Date getFectrabancen() {
		return this.fectrabancen;
	}

	public void setFectrabancen(Date fectrabancen) {
		this.fectrabancen = fectrabancen;
	}

	public Date getFectratesemp() {
		return this.fectratesemp;
	}

	public void setFectratesemp(Date fectratesemp) {
		this.fectratesemp = fectratesemp;
	}

	public Date getFecdispla() {
		return this.fecdispla;
	}

	public void setFecdispla(Date fecdispla) {
		this.fecdispla = fecdispla;
	}

	public String getIdetipjus() {
		return this.idetipjus;
	}

	public void setIdetipjus(String idetipjus) {
		this.idetipjus = idetipjus;
	}

	public String getCalint() {
		return this.calint;
	}

	public void setCalint(String calint) {
		this.calint = calint;
	}

	public String getPleesp() {
		return this.pleesp;
	}

	public void setPleesp(String pleesp) {
		this.pleesp = pleesp;
	}

	public BigDecimal getValmulgasadm() {
		return this.valmulgasadm;
	}

	public void setValmulgasadm(BigDecimal valmulgasadm) {
		this.valmulgasadm = valmulgasadm;
	}

	public BigDecimal getValmulnovext() {
		return this.valmulnovext;
	}

	public void setValmulnovext(BigDecimal valmulnovext) {
		this.valmulnovext = valmulnovext;
	}

	public String getBanderaimpos() {
		return this.banderaimpos;
	}

	public void setBanderaimpos(String banderaimpos) {
		this.banderaimpos = banderaimpos;
	}

	public BigDecimal getMarca() {
		return this.marca;
	}

	public void setMarca(BigDecimal marca) {
		this.marca = marca;
	}

	public ComprobantePagoPla getComprobantepago() {
		return this.comprobantepago;
	}

	public void setComprobantepago(ComprobantePagoPla comprobantepago) {
		this.comprobantepago = comprobantepago;
	}

	public List<PlanillaPrestamosDetalle> getKscretplapredetCollection() {
		return this.kscretplapredetCollection;
	}

	public void setKscretplapredetCollection(List<PlanillaPrestamosDetalle> kscretplapredetCollection) {
		this.kscretplapredetCollection = kscretplapredetCollection;
	}

	public List<ComprobantePagoDetalle> getKsrectcompagdetCollection() {
		return this.ksrectcompagdetCollection;
	}

	public void setKsrectcompagdetCollection(List<ComprobantePagoDetalle> ksrectcompagdetCollection) {
		this.ksrectcompagdetCollection = ksrectcompagdetCollection;
	}

	public String getNombreEmpleador() {
		return nombreEmpleador;
	}

	public void setNombreEmpleador(String nombreEmpleador) {
		this.nombreEmpleador = nombreEmpleador;
	}

}
