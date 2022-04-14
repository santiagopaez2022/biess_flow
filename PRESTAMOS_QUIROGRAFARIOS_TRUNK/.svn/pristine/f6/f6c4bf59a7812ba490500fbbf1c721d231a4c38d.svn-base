package ec.gov.iess.planillaspq.modelo.persistencia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ec.gov.iess.planillaspq.modelo.persistencia.pk.DividendosPK;

@Entity
@Table(name="Kscretdividendos")
@NamedQueries({
	@NamedQuery(name = "Dividendos.BuscarPorId", 
		    query = "select o from Dividendos o where o.pk = :Id " +
		    		"and o.codestdiv = :estado")
})
public class Dividendos implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DividendosPK pk;

	private Date fecpagdiv;

	private BigDecimal valcapamo;

	private BigDecimal intsalcap;

	private BigDecimal intpergra;

	private BigDecimal valsegsal;

	private BigDecimal valcarafi;

	private String sigcarafi;

	private String tipper;

	private BigDecimal aniper;

	private BigDecimal mesper;

	private String codtipdiv;

	private String codestdiv;

	private String numdoccan;

	private String obscarafi;

	private Date feccandiv;

	private String forcandiv;

	private BigDecimal tasintrea;

	@OneToMany(mappedBy="dividendos")
	private List<PlanillaPrestamosDetalle> kscretplapredetCollection;

	@OneToMany(mappedBy="dividendos")
	private List<DividendosHistorico> kscretdivpreesthisCollection;


	public Dividendos() {
		super();
	}

	public DividendosPK getPk() {
		return this.pk;
	}

	public void setPk(DividendosPK pk) {
		this.pk = pk;
	}

	public Date getFecpagdiv() {
		return this.fecpagdiv;
	}

	public void setFecpagdiv(Date fecpagdiv) {
		this.fecpagdiv = fecpagdiv;
	}

	public BigDecimal getValcapamo() {
		return this.valcapamo;
	}

	public void setValcapamo(BigDecimal valcapamo) {
		this.valcapamo = valcapamo;
	}

	public BigDecimal getIntsalcap() {
		return this.intsalcap;
	}

	public void setIntsalcap(BigDecimal intsalcap) {
		this.intsalcap = intsalcap;
	}

	public BigDecimal getIntpergra() {
		return this.intpergra;
	}

	public void setIntpergra(BigDecimal intpergra) {
		this.intpergra = intpergra;
	}

	public BigDecimal getValsegsal() {
		return this.valsegsal;
	}

	public void setValsegsal(BigDecimal valsegsal) {
		this.valsegsal = valsegsal;
	}

	public BigDecimal getValcarafi() {
		return this.valcarafi;
	}

	public void setValcarafi(BigDecimal valcarafi) {
		this.valcarafi = valcarafi;
	}

	public String getSigcarafi() {
		return this.sigcarafi;
	}

	public void setSigcarafi(String sigcarafi) {
		this.sigcarafi = sigcarafi;
	}

	public String getTipper() {
		return this.tipper;
	}

	public void setTipper(String tipper) {
		this.tipper = tipper;
	}

	public BigDecimal getAniper() {
		return this.aniper;
	}

	public void setAniper(BigDecimal aniper) {
		this.aniper = aniper;
	}

	public BigDecimal getMesper() {
		return this.mesper;
	}

	public void setMesper(BigDecimal mesper) {
		this.mesper = mesper;
	}

	public String getCodtipdiv() {
		return this.codtipdiv;
	}

	public void setCodtipdiv(String codtipdiv) {
		this.codtipdiv = codtipdiv;
	}

	public String getCodestdiv() {
		return this.codestdiv;
	}

	public void setCodestdiv(String codestdiv) {
		this.codestdiv = codestdiv;
	}

	public String getNumdoccan() {
		return this.numdoccan;
	}

	public void setNumdoccan(String numdoccan) {
		this.numdoccan = numdoccan;
	}

	public String getObscarafi() {
		return this.obscarafi;
	}

	public void setObscarafi(String obscarafi) {
		this.obscarafi = obscarafi;
	}

	public Date getFeccandiv() {
		return this.feccandiv;
	}

	public void setFeccandiv(Date feccandiv) {
		this.feccandiv = feccandiv;
	}

	public String getForcandiv() {
		return this.forcandiv;
	}

	public void setForcandiv(String forcandiv) {
		this.forcandiv = forcandiv;
	}

	public BigDecimal getTasintrea() {
		return this.tasintrea;
	}

	public void setTasintrea(BigDecimal tasintrea) {
		this.tasintrea = tasintrea;
	}

	public List<PlanillaPrestamosDetalle> getKscretplapredetCollection() {
		return this.kscretplapredetCollection;
	}

	public void setKscretplapredetCollection(List<PlanillaPrestamosDetalle> kscretplapredetCollection) {
		this.kscretplapredetCollection = kscretplapredetCollection;
	}

	public List<DividendosHistorico> getKscretdivpreesthisCollection() {
		return this.kscretdivpreesthisCollection;
	}

	public void setKscretdivpreesthisCollection(List<DividendosHistorico> kscretdivpreesthisCollection) {
		this.kscretdivpreesthisCollection = kscretdivpreesthisCollection;
	}

}
