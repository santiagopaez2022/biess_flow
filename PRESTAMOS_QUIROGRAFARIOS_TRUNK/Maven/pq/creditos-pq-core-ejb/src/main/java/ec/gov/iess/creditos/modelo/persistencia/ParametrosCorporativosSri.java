package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * Mapeo de la tabla KSPCOTSRI
 * 
 * @author acantos
 * @author Andres Cantos 03/10/2011
 * @version 1.0
 * 
 */

@Entity
@Table(name="KSPCOTSRI")
public class ParametrosCorporativosSri implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8354231196681696268L;
	
	@Id
	@Column(name="NUMRUC",nullable=false)
	@Basic(optional=false)
    private String numruc;
	
	@Column(name="NOMCOM",nullable=true)
	private String nomcom;
	
	@Column(name="RAZSOC",nullable=false)
	private String razsoc;
	
	@Column(name="TIPPER",nullable=false)
	private String tipper;	
	
	@Column(name="CODTIPCON",nullable=false)
	private String codtipcon;	
	
	@Column(name="FECCON",nullable=true)
	private Date feccon;
	
	@Column(name="FECINIACT",nullable=true)
	private Date feciniact;	
	
	@Column(name="NUMSUC",nullable=false)
	private Long numsuc;	
	
	@Column(name="SITCON",nullable=false)
	private String sitcon;		
	
	@Column(name="FECREGSRI",nullable=false)
	private Date fecregsri;		
	
	@Column(name="CEDIDEREPLEG",nullable=false)
    private String cediderepleg;		
	
	@Column(name="NUMRUCPAD",nullable=true)
    private String numrucpad;	
	
	@Column(name="CODCII",nullable=false)
    private String codcii;	
	
	@Column(name="FECCAR",nullable=true)
	private Date feccar;		
	
	@Column(name="CODFUN",nullable=true)
	private String codfun;
	
	@OneToOne
	@JoinColumn(name = "CEDIDEREPLEG", referencedColumnName = "CEDIDEREPLEG", insertable = false, updatable = false)
	private SriRepresentanteLegal kspcotsrirepleg;
	
	
	public SriRepresentanteLegal getKspcotsrirepleg() {
		return kspcotsrirepleg;
	}

	public void setKspcotsrirepleg(SriRepresentanteLegal kspcotsrirepleg) {
		this.kspcotsrirepleg = kspcotsrirepleg;
	}

	public String getNumruc() {
		return numruc;
	}

	public void setNumruc(String numruc) {
		this.numruc = numruc;
	}

	public String getNomcom() {
		return nomcom;
	}

	public void setNomcom(String nomcom) {
		this.nomcom = nomcom;
	}

	public String getRazsoc() {
		return razsoc;
	}

	public void setRazsoc(String razsoc) {
		this.razsoc = razsoc;
	}

	public String getTipper() {
		return tipper;
	}

	public void setTipper(String tipper) {
		this.tipper = tipper;
	}

	public String getCodtipcon() {
		return codtipcon;
	}

	public void setCodtipcon(String codtipcon) {
		this.codtipcon = codtipcon;
	}

	public Date getFeccon() {
		return feccon;
	}

	public void setFeccon(Date feccon) {
		this.feccon = feccon;
	}

	public Date getFeciniact() {
		return feciniact;
	}

	public void setFeciniact(Date feciniact) {
		this.feciniact = feciniact;
	}

	public Long getNumsuc() {
		return numsuc;
	}

	public void setNumsuc(Long numsuc) {
		this.numsuc = numsuc;
	}

	public String getSitcon() {
		return sitcon;
	}

	public void setSitcon(String sitcon) {
		this.sitcon = sitcon;
	}

	public Date getFecregsri() {
		return fecregsri;
	}

	public void setFecregsri(Date fecregsri) {
		this.fecregsri = fecregsri;
	}

	public String getCediderepleg() {
		return cediderepleg;
	}

	public void setCediderepleg(String cediderepleg) {
		this.cediderepleg = cediderepleg;
	}

	public String getNumrucpad() {
		return numrucpad;
	}

	public void setNumrucpad(String numrucpad) {
		this.numrucpad = numrucpad;
	}

	public String getCodcii() {
		return codcii;
	}

	public void setCodcii(String codcii) {
		this.codcii = codcii;
	}

	public Date getFeccar() {
		return feccar;
	}

	public void setFeccar(Date feccar) {
		this.feccar = feccar;
	}

	public String getCodfun() {
		return codfun;
	}

	public void setCodfun(String codfun) {
		this.codfun = codfun;
	}
	
}
