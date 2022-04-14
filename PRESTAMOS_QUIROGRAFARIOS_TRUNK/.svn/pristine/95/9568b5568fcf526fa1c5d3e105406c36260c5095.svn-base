/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoVistaPK;

@Entity
@Table(name = "KSCRETPRESTAMOS")
public class PrestamoVista implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private PrestamoVistaPK prestamoVistaPK;
    /*@Id
    @Basic(optional = false)
    @Column(name = "NUMPREAFI")
    private Long numpreafi;
    @Id
    @Basic(optional = false)
    @Column(name = "ORDPREAFI")
    private Long ordpreafi;
    @Id
    @Basic(optional = false)
    @Column(name = "CODPRETIP")
    private Long codpretip;
    @Id
    @Basic(optional = false)
    @Column(name = "CODPRECLA")
    private Long codprecla;*/
    @Basic(optional = false)
    @Column(name = "FECPREAFI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecpreafi;
    @Basic(optional = false)
    @Column(name = "VALSALCAP")
    private BigDecimal valsalcap;
    @Column(name = "OBSANUPRE")
    private String obsanupre;
    @Basic(optional = false)
    @Column(name = "TIPPER")
    private String tipper;
    @Basic(optional = false)
    @Column(name = "CODPARPRE")
    private String codparpre;
    @Basic(optional = false)
    @Column(name = "ANIPER")
    private Long aniper;
    @Basic(optional = false)
    @Column(name = "MESPER")
    private Long mesper;
    @Basic(optional = false)
    @Column(name = "CODREGADM")
    private Long codregadm;
    @Basic(optional = false)
    @Column(name = "CODTIPCUE")
    private String codtipcue;
    @Column(name = "NUMSOLSER")
    private Long numsolser;
    @Column(name = "CODTIPSOLSER")
    private Long codtipsolser;
    @Basic(optional = false)
    @Column(name = "FECINIPRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecinipre;
    @Basic(optional = false)
    @Column(name = "FECFINPRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecfinpre;
    @Column(name = "CODPEN")
    private String codpen;
    @Basic(optional = false)
    @Column(name = "NUMAFI")
    private String numafi;
    @Basic(optional = false)
    @Column(name = "NUMCTABAN")
    private String numctaban;
    @Column(name = "RUCEMP")
    private String rucemp;
    @Column(name = "CODSUC")
    private String codsuc;
    @Basic(optional = false)
    @Column(name = "VALTOTDIV")
    private BigDecimal valtotdiv;
    @Basic(optional = false)
    @Column(name = "MNTPRE")
    private Long mntpre;
    @Basic(optional = false)
    @Column(name = "PLZMES")
    private Long plzmes;
    @Basic(optional = false)
    @Column(name = "TASINT")
    private Long tasint;
    @Basic(optional = false)
    @Column(name = "INTDIAGRC")
    private Long intdiagrc;
    @Basic(optional = false)
    @Column(name = "VALSEGSAL")
    private Long valsegsal;
    @Column(name = "CODDIVPOL")
    private String coddivpol;
    @JoinColumn(name = "CODESTPRE", referencedColumnName = "CODESTPRE")
    @ManyToOne(optional = false)
    private EstadoPrestamo estadoPrestamo;
    @JoinColumns({@JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP", insertable = false, updatable = false), @JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private VariablePrestamo variablePrestamo;
    /*@JoinColumn(name = "RUCEMPINSFIN", referencedColumnName = "RUCEMP")
    @ManyToOne(optional = false)
    private Kspcotempleadores rucempinsfin;
    @JoinColumn(name = "CODFUN", referencedColumnName = "CODFUN")
    @ManyToOne
    private Kspcotfuncionarios codfun;*/

    public PrestamoVista() {
    }
/*
    public PrestamoVista(Long codprecla) {
        this.codprecla = codprecla;
    }
*/
    
/*    
    public PrestamoVista(Long numpreafi, Long ordpreafi, Long codpretip,
			Long codprecla, Date fecpreafi, BigDecimal valsalcap,
			String obsanupre, String tipper, String codparpre, Long aniper,
			Long mesper, Long codregadm, String codtipcue, Long numsolser,
			Long codtipsolser, Date fecinipre, Date fecfinpre, String codpen,
			String numafi, String numctaban, String rucemp, String codsuc,
			BigDecimal valtotdiv, Long mntpre, Long plzmes, Long tasint,
			Long intdiagrc, Long valsegsal, String coddivpol,
			EstadoPrestamo estadoPrestamo, VariablePrestamo variablePrestamo) {
		super();
		this.numpreafi = numpreafi;
		this.ordpreafi = ordpreafi;
		this.codpretip = codpretip;
		this.codprecla = codprecla;
		this.fecpreafi = fecpreafi;
		this.valsalcap = valsalcap;
		this.obsanupre = obsanupre;
		this.tipper = tipper;
		this.codparpre = codparpre;
		this.aniper = aniper;
		this.mesper = mesper;
		this.codregadm = codregadm;
		this.codtipcue = codtipcue;
		this.numsolser = numsolser;
		this.codtipsolser = codtipsolser;
		this.fecinipre = fecinipre;
		this.fecfinpre = fecfinpre;
		this.codpen = codpen;
		this.numafi = numafi;
		this.numctaban = numctaban;
		this.rucemp = rucemp;
		this.codsuc = codsuc;
		this.valtotdiv = valtotdiv;
		this.mntpre = mntpre;
		this.plzmes = plzmes;
		this.tasint = tasint;
		this.intdiagrc = intdiagrc;
		this.valsegsal = valsegsal;
		this.coddivpol = coddivpol;
		this.estadoPrestamo = estadoPrestamo;
		this.variablePrestamo = variablePrestamo;
	}
*/
    
    
	public Date getFecpreafi() {
        return fecpreafi;
    }

    public void setFecpreafi(Date fecpreafi) {
        this.fecpreafi = fecpreafi;
    }

    public BigDecimal getValsalcap() {
        return valsalcap;
    }

    public void setValsalcap(BigDecimal valsalcap) {
        this.valsalcap = valsalcap;
    }

    public String getObsanupre() {
        return obsanupre;
    }

    public void setObsanupre(String obsanupre) {
        this.obsanupre = obsanupre;
    }

    public String getTipper() {
        return tipper;
    }

    public void setTipper(String tipper) {
        this.tipper = tipper;
    }

    public String getCodparpre() {
        return codparpre;
    }

    public void setCodparpre(String codparpre) {
        this.codparpre = codparpre;
    }

   

    public String getCodtipcue() {
        return codtipcue;
    }

    public void setCodtipcue(String codtipcue) {
        this.codtipcue = codtipcue;
    }

    
    public Date getFecinipre() {
        return fecinipre;
    }

    public void setFecinipre(Date fecinipre) {
        this.fecinipre = fecinipre;
    }

    public Date getFecfinpre() {
        return fecfinpre;
    }

    public void setFecfinpre(Date fecfinpre) {
        this.fecfinpre = fecfinpre;
    }

    public String getCodpen() {
        return codpen;
    }

    public void setCodpen(String codpen) {
        this.codpen = codpen;
    }

    public String getNumafi() {
        return numafi;
    }

    public void setNumafi(String numafi) {
        this.numafi = numafi;
    }

    public String getNumctaban() {
        return numctaban;
    }

    public void setNumctaban(String numctaban) {
        this.numctaban = numctaban;
    }

    public String getRucemp() {
        return rucemp;
    }

    public void setRucemp(String rucemp) {
        this.rucemp = rucemp;
    }

    public String getCodsuc() {
        return codsuc;
    }

    public void setCodsuc(String codsuc) {
        this.codsuc = codsuc;
    }

    public BigDecimal getValtotdiv() {
        return valtotdiv;
    }

    public void setValtotdiv(BigDecimal valtotdiv) {
        this.valtotdiv = valtotdiv;
    }
    
    public String getCoddivpol() {
        return coddivpol;
    }

    public void setCoddivpol(String coddivpol) {
        this.coddivpol = coddivpol;
    }
/*
    public Long getNumpreafi() {
		return numpreafi;
	}

	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	public Long getOrdpreafi() {
		return ordpreafi;
	}

	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

	public Long getCodpretip() {
		return codpretip;
	}

	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	public Long getCodprecla() {
		return codprecla;
	}

	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}
*/
	public Long getAniper() {
		return aniper;
	}

	public void setAniper(Long aniper) {
		this.aniper = aniper;
	}

	public Long getMesper() {
		return mesper;
	}

	public void setMesper(Long mesper) {
		this.mesper = mesper;
	}

	public Long getCodregadm() {
		return codregadm;
	}

	public void setCodregadm(Long codregadm) {
		this.codregadm = codregadm;
	}

	public Long getNumsolser() {
		return numsolser;
	}

	public void setNumsolser(Long numsolser) {
		this.numsolser = numsolser;
	}

	public Long getCodtipsolser() {
		return codtipsolser;
	}

	public void setCodtipsolser(Long codtipsolser) {
		this.codtipsolser = codtipsolser;
	}

	public Long getMntpre() {
		return mntpre;
	}

	public void setMntpre(Long mntpre) {
		this.mntpre = mntpre;
	}

	public Long getPlzmes() {
		return plzmes;
	}

	public void setPlzmes(Long plzmes) {
		this.plzmes = plzmes;
	}

	public Long getTasint() {
		return tasint;
	}

	public void setTasint(Long tasint) {
		this.tasint = tasint;
	}

	public Long getIntdiagrc() {
		return intdiagrc;
	}

	public void setIntdiagrc(Long intdiagrc) {
		this.intdiagrc = intdiagrc;
	}

	public Long getValsegsal() {
		return valsegsal;
	}

	public void setValsegsal(Long valsegsal) {
		this.valsegsal = valsegsal;
	}

	public EstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}

	public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	public VariablePrestamo getVariablePrestamo() {
		return variablePrestamo;
	}

	public void setVariablePrestamo(VariablePrestamo variablePrestamo) {
		this.variablePrestamo = variablePrestamo;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += prestamoVistaPK.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoVista)) {
            return false;
        }
        PrestamoVista other = (PrestamoVista) object;
        if (this.prestamoVistaPK != other.prestamoVistaPK) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gov.iess.creditos.modelo.persistencia.Kscretprestamos[codprecla=" + prestamoVistaPK + "]";
    }

}
