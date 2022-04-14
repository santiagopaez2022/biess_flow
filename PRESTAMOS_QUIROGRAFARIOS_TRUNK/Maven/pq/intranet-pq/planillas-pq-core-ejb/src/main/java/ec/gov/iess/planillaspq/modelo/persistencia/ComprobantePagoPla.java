package ec.gov.iess.planillaspq.modelo.persistencia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import ec.gov.iess.planillaspq.modelo.persistencia.pk.ComprobantePagoPK;

@Entity
@Table(name="ksrectcompag")
@NamedQueries({
	@NamedQuery(name = "Comprobante.BuscarPorId", 
		    query = "select o from ComprobantePagoPla o where o.pk = :Id")
})
@NamedNativeQueries( {

		@NamedNativeQuery(name = "Comprobante.buscarComprobantePorPlanilla", 
				query = "select * "+
		 "from ksrectcompag where "+ 
                        "(codcompag,codtipcompag) in "+
                        "(select codcompag,codtipcompag from ksrectcompagdet where (rucemp,codsuc,tipper,mesper,aniper,secpla) in "+
                         "(select rucemp,codsuc,tipper,mesper,aniper,secpla "+
						 "from ksrectplanillas "+
                        "where rucemp = :rucemp "+ 
                        "and codsuc = :codsuc "+
                        "and mesper = :mesper "+
                        "and aniper = :aniper "+
						"and tipper = :tipper "+
                        "and codtippla = :codtippla "+
                        "and secpla = :secpla "+
                        "and ESTTIPPLA = :esttippla)) "+
                        "and codestcompag IN ('GEN','PAR') "+
                        "and codtipcompag = :tipcomp", resultSetMapping = "comprobantepagoId"),
		@NamedNativeQuery(name = "Comprobante.buscarComprobantePorPlanillaAjustePlanilla", 
		query = "select * "+
				"from ksrectcompag where "+ 
                "(codcompag,codtipcompag) in "+
                "(select codcompag,codtipcompag from ksrectcompagdet where (rucemp,codsuc,tipper,mesper,aniper,secpla) in "+
                 "(select rucemp,codsuc,tipper,mesper,aniper,secpla "+
				 "from ksrectplanillas "+
                "where rucemp = :rucemp "+ 
                "and codsuc = :codsuc "+
                "and mesper = :mesper "+
                "and aniper = :aniper "+
				"and tipper = :tipper "+
                "and codtippla = :codtippla "+
                "and secpla = :secpla "+
                "and ESTTIPPLA = :esttippla)) "+
                "and codestcompag IN ('GEN','PAR','DEP') ", resultSetMapping = "comprobantepagoId")

})
@SqlResultSetMappings( { @SqlResultSetMapping(name = "comprobantepagoId", entities = { @EntityResult(entityClass = ComprobantePagoPla.class) }) })

public class ComprobantePagoPla implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComprobantePagoPK pk;

	private String codsuc;

	private String rucemp;

	private String codestcompag;

	private String codtipglo;

	private BigDecimal valcompag;

	private BigDecimal valintmorcompag;

	private BigDecimal valtotcompag;

	private BigDecimal dedpag;

	private String codcon;

	private String codglo;

	private String codtit;

	private Date fecgencompag;

	private Date fectercompag;

	private BigDecimal numdetcompag;

	private Date fecdepban;

	private Date fecpagban;

	private BigDecimal valpagban;

	private Date fectesgennac;

	private Date fecpag;

	private BigDecimal valcar;

	private String sigcar;

	private String codtipcon;

	private String codtipdocjuspag;

	private String numdocpag;

	private String infusuban;

	private String codinsfin;

	private String sucinsfin;

	private String pagbancen;

	private BigDecimal diasug;

	private Date fecprodis;

	private BigDecimal valtotmul;

	private Date feccalintprotit;

	private BigDecimal gasadm;

	private BigDecimal honabo;

	private String coddivpol;

	private String codofiies;

	private String estpla;

	private String numafivol;

	private String prioridad;

	@OneToMany(mappedBy="comprobantepago")
	private List<Planillas> ksrectplanillasCollection;

	@OneToMany(mappedBy="comprobantepago")
	private List<ComprobantePagoDetalle> ksrectcompagdetCollection;


	public ComprobantePagoPla() {
		super();
	}

	public ComprobantePagoPK getPk() {
		return this.pk;
	}

	public void setPk(ComprobantePagoPK pk) {
		this.pk = pk;
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

	public String getCodestcompag() {
		return this.codestcompag;
	}

	public void setCodestcompag(String codestcompag) {
		this.codestcompag = codestcompag;
	}

	public String getCodtipglo() {
		return this.codtipglo;
	}

	public void setCodtipglo(String codtipglo) {
		this.codtipglo = codtipglo;
	}

	public BigDecimal getValcompag() {
		return this.valcompag;
	}

	public void setValcompag(BigDecimal valcompag) {
		this.valcompag = valcompag;
	}

	public BigDecimal getValintmorcompag() {
		return this.valintmorcompag;
	}

	public void setValintmorcompag(BigDecimal valintmorcompag) {
		this.valintmorcompag = valintmorcompag;
	}

	public BigDecimal getValtotcompag() {
		return this.valtotcompag;
	}

	public void setValtotcompag(BigDecimal valtotcompag) {
		this.valtotcompag = valtotcompag;
	}

	public BigDecimal getDedpag() {
		return this.dedpag;
	}

	public void setDedpag(BigDecimal dedpag) {
		this.dedpag = dedpag;
	}

	public String getCodcon() {
		return this.codcon;
	}

	public void setCodcon(String codcon) {
		this.codcon = codcon;
	}

	public String getCodglo() {
		return this.codglo;
	}

	public void setCodglo(String codglo) {
		this.codglo = codglo;
	}

	public String getCodtit() {
		return this.codtit;
	}

	public void setCodtit(String codtit) {
		this.codtit = codtit;
	}

	public Date getFecgencompag() {
		return this.fecgencompag;
	}

	public void setFecgencompag(Date fecgencompag) {
		this.fecgencompag = fecgencompag;
	}

	public Date getFectercompag() {
		return this.fectercompag;
	}

	public void setFectercompag(Date fectercompag) {
		this.fectercompag = fectercompag;
	}

	public BigDecimal getNumdetcompag() {
		return this.numdetcompag;
	}

	public void setNumdetcompag(BigDecimal numdetcompag) {
		this.numdetcompag = numdetcompag;
	}

	public Date getFecdepban() {
		return this.fecdepban;
	}

	public void setFecdepban(Date fecdepban) {
		this.fecdepban = fecdepban;
	}

	public Date getFecpagban() {
		return this.fecpagban;
	}

	public void setFecpagban(Date fecpagban) {
		this.fecpagban = fecpagban;
	}

	public BigDecimal getValpagban() {
		return this.valpagban;
	}

	public void setValpagban(BigDecimal valpagban) {
		this.valpagban = valpagban;
	}

	public Date getFectesgennac() {
		return this.fectesgennac;
	}

	public void setFectesgennac(Date fectesgennac) {
		this.fectesgennac = fectesgennac;
	}

	public Date getFecpag() {
		return this.fecpag;
	}

	public void setFecpag(Date fecpag) {
		this.fecpag = fecpag;
	}

	public BigDecimal getValcar() {
		return this.valcar;
	}

	public void setValcar(BigDecimal valcar) {
		this.valcar = valcar;
	}

	public String getSigcar() {
		return this.sigcar;
	}

	public void setSigcar(String sigcar) {
		this.sigcar = sigcar;
	}

	public String getCodtipcon() {
		return this.codtipcon;
	}

	public void setCodtipcon(String codtipcon) {
		this.codtipcon = codtipcon;
	}

	public String getCodtipdocjuspag() {
		return this.codtipdocjuspag;
	}

	public void setCodtipdocjuspag(String codtipdocjuspag) {
		this.codtipdocjuspag = codtipdocjuspag;
	}

	public String getNumdocpag() {
		return this.numdocpag;
	}

	public void setNumdocpag(String numdocpag) {
		this.numdocpag = numdocpag;
	}

	public String getInfusuban() {
		return this.infusuban;
	}

	public void setInfusuban(String infusuban) {
		this.infusuban = infusuban;
	}

	public String getCodinsfin() {
		return this.codinsfin;
	}

	public void setCodinsfin(String codinsfin) {
		this.codinsfin = codinsfin;
	}

	public String getSucinsfin() {
		return this.sucinsfin;
	}

	public void setSucinsfin(String sucinsfin) {
		this.sucinsfin = sucinsfin;
	}

	public String getPagbancen() {
		return this.pagbancen;
	}

	public void setPagbancen(String pagbancen) {
		this.pagbancen = pagbancen;
	}

	public BigDecimal getDiasug() {
		return this.diasug;
	}

	public void setDiasug(BigDecimal diasug) {
		this.diasug = diasug;
	}

	public Date getFecprodis() {
		return this.fecprodis;
	}

	public void setFecprodis(Date fecprodis) {
		this.fecprodis = fecprodis;
	}

	public BigDecimal getValtotmul() {
		return this.valtotmul;
	}

	public void setValtotmul(BigDecimal valtotmul) {
		this.valtotmul = valtotmul;
	}

	public Date getFeccalintprotit() {
		return this.feccalintprotit;
	}

	public void setFeccalintprotit(Date feccalintprotit) {
		this.feccalintprotit = feccalintprotit;
	}

	public BigDecimal getGasadm() {
		return this.gasadm;
	}

	public void setGasadm(BigDecimal gasadm) {
		this.gasadm = gasadm;
	}

	public BigDecimal getHonabo() {
		return this.honabo;
	}

	public void setHonabo(BigDecimal honabo) {
		this.honabo = honabo;
	}

	public String getCoddivpol() {
		return this.coddivpol;
	}

	public void setCoddivpol(String coddivpol) {
		this.coddivpol = coddivpol;
	}

	public String getCodofiies() {
		return this.codofiies;
	}

	public void setCodofiies(String codofiies) {
		this.codofiies = codofiies;
	}

	public String getEstpla() {
		return this.estpla;
	}

	public void setEstpla(String estpla) {
		this.estpla = estpla;
	}

	public String getNumafivol() {
		return this.numafivol;
	}

	public void setNumafivol(String numafivol) {
		this.numafivol = numafivol;
	}

	public String getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public List<Planillas> getKsrectplanillasCollection() {
		return this.ksrectplanillasCollection;
	}

	public void setKsrectplanillasCollection(List<Planillas> ksrectplanillasCollection) {
		this.ksrectplanillasCollection = ksrectplanillasCollection;
	}

	public List<ComprobantePagoDetalle> getKsrectcompagdetCollection() {
		return this.ksrectcompagdetCollection;
	}

	public void setKsrectcompagdetCollection(List<ComprobantePagoDetalle> ksrectcompagdetCollection) {
		this.ksrectcompagdetCollection = ksrectcompagdetCollection;
	}
}
