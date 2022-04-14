package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.gov.iess.creditos.modelo.persistencia.pk.CreditoQuirografarioHostPK;

@Entity
@Table(name = "KSMIGTQRM11")
@NamedQueries( { @NamedQuery(name = "CreditoQuirografarioHost.findCreditosCedulaTiposCredito", query = "SELECT o FROM CreditoQuirografarioHost o "
		+ "WHERE o.creditoQuirografarioHostPK.cedula = :cedula "
		+ "AND o.creditoQuirografarioHostPK.clapre in (:listaCredistos)"
		+ "AND o.bandera = 1") })
public class CreditoQuirografarioHost implements Serializable,
		Comparable<CreditoQuirografarioHost> {

	@Id
	private CreditoQuirografarioHostPK creditoQuirografarioHostPK;

	private String bandera;
	private Long bolcan;
	private BigDecimal capital;

	@Column(nullable = false)
	private String codestpre;
	@Column(nullable = false)
	private Double divpre;
	private Double divpri;
	private String estmig;
	private String estpre;

	@Temporal(TemporalType.DATE)
	private Date fecan;

	@Temporal(TemporalType.DATE)
	private Date fecconc;

	@Temporal(TemporalType.DATE)
	private Date fecprocar;

	@Temporal(TemporalType.DATE)
	private Date findes;

	@Temporal(TemporalType.DATE)
	private Date inides;

	private String nombre;
	private String numpat;
	@Column(nullable = false)
	private String ordbol;
	private String origen;
	private BigDecimal porint;
	private BigDecimal porseg;
	private String region;
	private String secpat;
	private String tabamo;

	@Column(nullable = false)
	private BigDecimal valdiv;

	/**
	 * 
	 */
	private static final long serialVersionUID = -7348858659380135817L;

	public CreditoQuirografarioHost() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the creditoQuirografarioHostPK
	 */
	public CreditoQuirografarioHostPK getCreditoQuirografarioHostPK() {
		return creditoQuirografarioHostPK;
	}

	/**
	 * @return the bandera
	 */
	public String getBandera() {
		return bandera;
	}

	/**
	 * @return the bolcan
	 */
	public Long getBolcan() {
		return bolcan;
	}

	/**
	 * @return the capital
	 */
	public BigDecimal getCapital() {
		return capital;
	}

	/**
	 * @return the codestpre
	 */
	public String getCodestpre() {
		return codestpre;
	}

	/**
	 * @return the divpre
	 */
	public Double getDivpre() {
		return divpre;
	}

	/**
	 * @return the divpri
	 */
	public Double getDivpri() {
		return divpri;
	}

	/**
	 * @return the estmig
	 */
	public String getEstmig() {
		return estmig;
	}

	/**
	 * @return the estpre
	 */
	public String getEstpre() {
		return estpre;
	}

	/**
	 * @return the fecan
	 */
	public Date getFecan() {
		return fecan;
	}

	/**
	 * @return the fecconc
	 */
	public Date getFecconc() {
		return fecconc;
	}

	/**
	 * @return the fecprocar
	 */
	public Date getFecprocar() {
		return fecprocar;
	}

	/**
	 * @return the findes
	 */
	public Date getFindes() {
		return findes;
	}

	/**
	 * @return the inides
	 */
	public Date getInides() {
		return inides;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the numpat
	 */
	public String getNumpat() {
		return numpat;
	}

	/**
	 * @return the ordbol
	 */
	public String getOrdbol() {
		return ordbol;
	}

	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @return the porint
	 */
	public BigDecimal getPorint() {
		return porint;
	}

	/**
	 * @return the porseg
	 */
	public BigDecimal getPorseg() {
		return porseg;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @return the secpat
	 */
	public String getSecpat() {
		return secpat;
	}

	/**
	 * @return the tabamo
	 */
	public String getTabamo() {
		return tabamo;
	}

	/**
	 * @return the valdiv
	 */
	public BigDecimal getValdiv() {
		return valdiv;
	}

	/**
	 * @param creditoQuirografarioHostPK
	 *            the creditoQuirografarioHostPK to set
	 */
	public void setCreditoQuirografarioHostPK(
			CreditoQuirografarioHostPK creditoQuirografarioHostPK) {
		this.creditoQuirografarioHostPK = creditoQuirografarioHostPK;
	}

	/**
	 * @param bandera
	 *            the bandera to set
	 */
	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

	/**
	 * @param bolcan
	 *            the bolcan to set
	 */
	public void setBolcan(Long bolcan) {
		this.bolcan = bolcan;
	}

	/**
	 * @param capital
	 *            the capital to set
	 */
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	/**
	 * @param codestpre
	 *            the codestpre to set
	 */
	public void setCodestpre(String codestpre) {
		this.codestpre = codestpre;
	}

	/**
	 * @param divpre
	 *            the divpre to set
	 */
	public void setDivpre(Double divpre) {
		this.divpre = divpre;
	}

	/**
	 * @param divpri
	 *            the divpri to set
	 */
	public void setDivpri(Double divpri) {
		this.divpri = divpri;
	}

	/**
	 * @param estmig
	 *            the estmig to set
	 */
	public void setEstmig(String estmig) {
		this.estmig = estmig;
	}

	/**
	 * @param estpre
	 *            the estpre to set
	 */
	public void setEstpre(String estpre) {
		this.estpre = estpre;
	}

	/**
	 * @param fecan
	 *            the fecan to set
	 */
	public void setFecan(Date fecan) {
		this.fecan = fecan;
	}

	/**
	 * @param fecconc
	 *            the fecconc to set
	 */
	public void setFecconc(Date fecconc) {
		this.fecconc = fecconc;
	}

	/**
	 * @param fecprocar
	 *            the fecprocar to set
	 */
	public void setFecprocar(Date fecprocar) {
		this.fecprocar = fecprocar;
	}

	/**
	 * @param findes
	 *            the findes to set
	 */
	public void setFindes(Date findes) {
		this.findes = findes;
	}

	/**
	 * @param inides
	 *            the inides to set
	 */
	public void setInides(Date inides) {
		this.inides = inides;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @param numpat
	 *            the numpat to set
	 */
	public void setNumpat(String numpat) {
		this.numpat = numpat;
	}

	/**
	 * @param ordbol
	 *            the ordbol to set
	 */
	public void setOrdbol(String ordbol) {
		this.ordbol = ordbol;
	}

	/**
	 * @param origen
	 *            the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * @param porint
	 *            the porint to set
	 */
	public void setPorint(BigDecimal porint) {
		this.porint = porint;
	}

	/**
	 * @param porseg
	 *            the porseg to set
	 */
	public void setPorseg(BigDecimal porseg) {
		this.porseg = porseg;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @param secpat
	 *            the secpat to set
	 */
	public void setSecpat(String secpat) {
		this.secpat = secpat;
	}

	/**
	 * @param tabamo
	 *            the tabamo to set
	 */
	public void setTabamo(String tabamo) {
		this.tabamo = tabamo;
	}

	/**
	 * @param valdiv
	 *            the valdiv to set
	 */
	public void setValdiv(BigDecimal valdiv) {
		this.valdiv = valdiv;
	}

	public int compareTo(CreditoQuirografarioHost o) {
		this.valdiv.compareTo(o.getValdiv());
		return 0;
	}

}
