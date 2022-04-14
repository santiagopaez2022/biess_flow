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
public class PrestamoEstadoHistoricoPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Column(name = "FECINI",nullable=false)
    private Date fecini;
    @Column(name = "CODESTPRE",nullable=false)
    private String codestpre;
    @Column(name = "NUMPREAFI",nullable=false)
    private Long numpreafi;
    @Column(name = "ORDPREAFI",nullable=false)
    private Long ordpreafi;
    @Column(name = "CODPRETIP",nullable=false)
    private Long codpretip;
    @Column(name = "CODPRECLA",nullable=false)
    private Long codprecla;

    public PrestamoEstadoHistoricoPK() {
    }

    public PrestamoEstadoHistoricoPK(Date fecini, String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla) {
        this.fecini = fecini;
        this.codestpre = codestpre;
        this.numpreafi = numpreafi;
        this.ordpreafi = ordpreafi;
        this.codpretip = codpretip;
        this.codprecla = codprecla;
    }

    public Date getFecini() {
        return fecini;
    }

    public void setFecini(Date fecini) {
        this.fecini = fecini;
    }

    public String getCodestpre() {
        return codestpre;
    }

    public void setCodestpre(String codestpre) {
        this.codestpre = codestpre;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecini != null ? fecini.hashCode() : 0);
        hash += (codestpre != null ? codestpre.hashCode() : 0);
        hash += (numpreafi != null ? numpreafi.hashCode() : 0);
        hash += ordpreafi;
        hash += codpretip;
        hash += codprecla;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoEstadoHistoricoPK)) {
            return false;
        }
        PrestamoEstadoHistoricoPK other = (PrestamoEstadoHistoricoPK) object;
        if ((this.fecini == null && other.fecini != null) || (this.fecini != null && !this.fecini.equals(other.fecini))) {
            return false;
        }
        if ((this.codestpre == null && other.codestpre != null) || (this.codestpre != null && !this.codestpre.equals(other.codestpre))) {
            return false;
        }
        if ((this.numpreafi == null && other.numpreafi != null) || (this.numpreafi != null && !this.numpreafi.equals(other.numpreafi))) {
            return false;
        }
        if (this.ordpreafi != other.ordpreafi) {
            return false;
        }
        if (this.codpretip != other.codpretip) {
            return false;
        }
        if (this.codprecla != other.codprecla) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gov.iess.creditos.modelo.persistencia.KscretpreesthisPK[fecini=" + fecini + ", codestpre=" + codestpre + ", numpreafi=" + numpreafi + ", ordpreafi=" + ordpreafi + ", codpretip=" + codpretip + ", codprecla=" + codprecla + "]";
    }

}
