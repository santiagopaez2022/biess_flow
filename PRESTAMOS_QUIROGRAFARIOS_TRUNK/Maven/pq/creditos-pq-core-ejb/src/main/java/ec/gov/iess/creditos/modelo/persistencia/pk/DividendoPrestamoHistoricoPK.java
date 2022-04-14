/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author rtituana
 */
@Embeddable
public class DividendoPrestamoHistoricoPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    @Column(name = "FECINI",nullable=false)
    private Date fecini;    
    @Column(name = "NUMDIV",nullable=false)
    private Long numdiv;    
    @Column(name = "CODPRETIP",nullable=false)
    private Long codpretip;    
    @Column(name = "CODPRECLA",nullable=false)
    private Long codprecla;    
    @Column(name = "NUMPREAFI",nullable=false)
    private Long numpreafi;    
    @Column(name = "ORDPREAFI",nullable=false)
    private Long ordpreafi;    
    @Column(name = "CODESTDIV",nullable=false)
    private String codestdiv;

    public DividendoPrestamoHistoricoPK() {
    }

    public DividendoPrestamoHistoricoPK(Date fecini, Long numdiv, Long codpretip, Long codprecla, Long numpreafi, Long ordpreafi, String codestdiv) {
        this.fecini = fecini;
        this.numdiv = numdiv;
        this.codpretip = codpretip;
        this.codprecla = codprecla;
        this.numpreafi = numpreafi;
        this.ordpreafi = ordpreafi;
        this.codestdiv = codestdiv;
    }

    public Date getFecini() {
        return fecini;
    }

    public void setFecini(Date fecini) {
        this.fecini = fecini;
    }

    public Long getNumdiv() {
        return numdiv;
    }

    public void setNumdiv(Long numdiv) {
        this.numdiv = numdiv;
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

    public String getCodestdiv() {
        return codestdiv;
    }

    public void setCodestdiv(String codestdiv) {
        this.codestdiv = codestdiv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecini != null ? fecini.hashCode() : 0);
        hash += (Long) numdiv;
        hash += (Long) codpretip;
        hash += (Long) codprecla;
        hash += (numpreafi != null ? numpreafi.hashCode() : 0);
        hash += (Long) ordpreafi;
        hash += (codestdiv != null ? codestdiv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DividendoPrestamoHistoricoPK)) {
            return false;
        }
        DividendoPrestamoHistoricoPK other = (DividendoPrestamoHistoricoPK) object;
        if ((this.fecini == null && other.fecini != null) || (this.fecini != null && !this.fecini.equals(other.fecini))) {
            return false;
        }
        if (this.numdiv != other.numdiv) {
            return false;
        }
        if (this.codpretip != other.codpretip) {
            return false;
        }
        if (this.codprecla != other.codprecla) {
            return false;
        }
        if ((this.numpreafi == null && other.numpreafi != null) || (this.numpreafi != null && !this.numpreafi.equals(other.numpreafi))) {
            return false;
        }
        if (this.ordpreafi != other.ordpreafi) {
            return false;
        }
        if ((this.codestdiv == null && other.codestdiv != null) || (this.codestdiv != null && !this.codestdiv.equals(other.codestdiv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gov.iess.creditos.modelo.persistencia.KscretdivpreesthisPK[fecini=" + fecini + ", numdiv=" + numdiv + ", codpretip=" + codpretip + ", codprecla=" + codprecla + ", numpreafi=" + numpreafi + ", ordpreafi=" + ordpreafi + ", codestdiv=" + codestdiv + "]";
    }

}
