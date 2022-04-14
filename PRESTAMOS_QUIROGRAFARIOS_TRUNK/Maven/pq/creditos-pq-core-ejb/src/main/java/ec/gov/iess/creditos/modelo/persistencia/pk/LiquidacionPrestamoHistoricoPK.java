
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rtituana
 */
@Embeddable
public class LiquidacionPrestamoHistoricoPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @Column(name = "FECINI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecini;
    
	@Basic(optional = false)
    @Column(name = "NUMLIQPRE")
    private Long numliqpre;
    
	@Basic(optional = false)
    @Column(name = "CODESTLIQPRE")
    private String codestliqpre;

    public LiquidacionPrestamoHistoricoPK() {
    }

    public LiquidacionPrestamoHistoricoPK(Date fecini, Long numliqpre, String codestliqpre) {
        this.fecini = fecini;
        this.numliqpre = numliqpre;
        this.codestliqpre = codestliqpre;
    }

    public Date getFecini() {
        return fecini;
    }

    public void setFecini(Date fecini) {
        this.fecini = fecini;
    }

    public Long getNumliqpre() {
        return numliqpre;
    }

    public void setNumliqpre(Long numliqpre) {
        this.numliqpre = numliqpre;
    }

    public String getCodestliqpre() {
        return codestliqpre;
    }

    public void setCodestliqpre(String codestliqpre) {
        this.codestliqpre = codestliqpre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecini != null ? fecini.hashCode() : 0);
        hash += (numliqpre != null ? numliqpre.hashCode() : 0);
        hash += (codestliqpre != null ? codestliqpre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiquidacionPrestamoHistoricoPK)) {
            return false;
        }
        LiquidacionPrestamoHistoricoPK other = (LiquidacionPrestamoHistoricoPK) object;
        if ((this.fecini == null && other.fecini != null) || (this.fecini != null && !this.fecini.equals(other.fecini))) {
            return false;
        }
        if ((this.numliqpre == null && other.numliqpre != null) || (this.numliqpre != null && !this.numliqpre.equals(other.numliqpre))) {
            return false;
        }
        if ((this.codestliqpre == null && other.codestliqpre != null) || (this.codestliqpre != null && !this.codestliqpre.equals(other.codestliqpre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gov.iess.creditos.modelo.persistencia.KscretpreliqesthisPK[fecini=" + fecini + ", numliqpre=" + numliqpre + ", codestliqpre=" + codestliqpre + "]";
    }

}
