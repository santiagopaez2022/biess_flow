package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the FRSAFITCRURESCTAIND database table.
 * 
 */

@Entity
@Table(name="FRSAFITCRURESCTAIND")
@NamedQueries( {
		@NamedQuery(name = "CruceReservaAportes.obtenerAportesComprometidosPq", query = "select o from CruceFondosReservaAportes o where " +
				"o.numafi = :cedula and o.numpreafi = :numpreafi and o.ordpreafi = :ordpreafi and o.codpretip = :codpretip " +
				"and o.codprecla = :codprecla and o.codsec = :codigoaporte")
 })
public class CruceFondosReservaAportes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Long codigo;

	private Long aniper;

	@Column(nullable=false)
	private Double capitalcomprometido;

	private Double codcar;

	private Long codprecla;

	private Long codpretip;

	@Column(nullable=false)
	private BigInteger codsec;

	private Double codsolafi;

	private String codsuc;

	private String codtippla;

	private String codtipsolafi;

	private String estcar;

	private String estproctaind;

	private String estsolafi;

	private Date fecpagcar;

	private Date fecpagcompag;

	private Date fecpagsol;

	private Date fecpro;

	private Date fecregsol;

	private Double gasadm;

	@Column(nullable=false)
	private Double interes;

	private Long mesper;

	@Column(nullable=false)
	private String numafi;

	private Long numpreafi;

	private String observacion;

	private Long ordpreafi;

	private String rucemp;

	private Long secpla;

	@Column(nullable=false)
	private String tipo;

	private String tipper;

	@Column(nullable=false)
	private String total;

	@Column(nullable=false)
	private Double valdis;

	private Double valsol;

    public CruceFondosReservaAportes() {
    }

	public long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Long getAniper() {
		return this.aniper;
	}

	public void setAniper(Long aniper) {
		this.aniper = aniper;
	}

	public Double getCapitalcomprometido() {
		return this.capitalcomprometido;
	}

	public void setCapitalcomprometido(Double capitalcomprometido) {
		this.capitalcomprometido = capitalcomprometido;
	}

	public Double getCodcar() {
		return this.codcar;
	}

	public void setCodcar(Double codcar) {
		this.codcar = codcar;
	}

	public Long getCodprecla() {
		return this.codprecla;
	}

	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	public Long getCodpretip() {
		return this.codpretip;
	}

	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	public BigInteger getCodsec() {
		return this.codsec;
	}

	public void setCodsec(BigInteger codsec) {
		this.codsec = codsec;
	}

	public Double getCodsolafi() {
		return this.codsolafi;
	}

	public void setCodsolafi(Double codsolafi) {
		this.codsolafi = codsolafi;
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

	public String getCodtipsolafi() {
		return this.codtipsolafi;
	}

	public void setCodtipsolafi(String codtipsolafi) {
		this.codtipsolafi = codtipsolafi;
	}

	public String getEstcar() {
		return this.estcar;
	}

	public void setEstcar(String estcar) {
		this.estcar = estcar;
	}

	public String getEstproctaind() {
		return this.estproctaind;
	}

	public void setEstproctaind(String estproctaind) {
		this.estproctaind = estproctaind;
	}

	public String getEstsolafi() {
		return this.estsolafi;
	}

	public void setEstsolafi(String estsolafi) {
		this.estsolafi = estsolafi;
	}

	public Date getFecpagcar() {
		return this.fecpagcar;
	}

	public void setFecpagcar(Date fecpagcar) {
		this.fecpagcar = fecpagcar;
	}

	public Date getFecpagcompag() {
		return this.fecpagcompag;
	}

	public void setFecpagcompag(Date fecpagcompag) {
		this.fecpagcompag = fecpagcompag;
	}

	public Date getFecpagsol() {
		return this.fecpagsol;
	}

	public void setFecpagsol(Date fecpagsol) {
		this.fecpagsol = fecpagsol;
	}

	public Date getFecpro() {
		return this.fecpro;
	}

	public void setFecpro(Date fecpro) {
		this.fecpro = fecpro;
	}

	public Date getFecregsol() {
		return this.fecregsol;
	}

	public void setFecregsol(Date fecregsol) {
		this.fecregsol = fecregsol;
	}

	public Double getGasadm() {
		return this.gasadm;
	}

	public void setGasadm(Double gasadm) {
		this.gasadm = gasadm;
	}

	public Double getInteres() {
		return this.interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	public Long getMesper() {
		return this.mesper;
	}

	public void setMesper(Long mesper) {
		this.mesper = mesper;
	}

	public String getNumafi() {
		return this.numafi;
	}

	public void setNumafi(String numafi) {
		this.numafi = numafi;
	}

	public Long getNumpreafi() {
		return this.numpreafi;
	}

	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Long getOrdpreafi() {
		return this.ordpreafi;
	}

	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipper() {
		return this.tipper;
	}

	public void setTipper(String tipper) {
		this.tipper = tipper;
	}

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Double getValdis() {
		return this.valdis;
	}

	public void setValdis(Double valdis) {
		this.valdis = valdis;
	}

	public Double getValsol() {
		return this.valsol;
	}

	public void setValsol(Double valsol) {
		this.valsol = valsol;
	}

}