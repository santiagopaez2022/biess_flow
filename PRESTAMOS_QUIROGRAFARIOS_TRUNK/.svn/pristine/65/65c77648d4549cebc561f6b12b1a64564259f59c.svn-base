
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.gov.iess.creditos.modelo.persistencia.pk.LiquidacionPrestamoHistoricoPK;

/**
 *
 * @author rtituana
 */
@Entity
@Table(name = "KSCRETPRELIQESTHIS")

public class LiquidacionPrestamoHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected LiquidacionPrestamoHistoricoPK liquidacionPrestamoHistoricoPk;
    
    @Column(name = "FECFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecfin;
    
    @Column(name = "OBSTRA")
    private String obstra;
    
    @Column(name = "CODTIPLIQ")
	private String codtipliq;
    
    @JoinColumn(name = "NUMLIQPRE", referencedColumnName = "NUMLIQPRE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LiquidacionPrestamo liquidacionPrestamo;
    
    @JoinColumn(name = "CODESTLIQPRE", referencedColumnName = "CODESTLIQPRE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LiquidacionPrestamoEstado liquidacionPrestamoEstado;
 
    
    public LiquidacionPrestamoHistorico() {
    }

    public LiquidacionPrestamoHistorico(LiquidacionPrestamoHistoricoPK liquidacionPrestamoHistoricoPK) {
        this.liquidacionPrestamoHistoricoPk = liquidacionPrestamoHistoricoPK;
    }

    public LiquidacionPrestamoHistorico(Date fecini, Long numliqpre, String codestliqpre) {
        this.liquidacionPrestamoHistoricoPk = new LiquidacionPrestamoHistoricoPK(fecini, numliqpre, codestliqpre);
    }

    

    public LiquidacionPrestamoHistoricoPK getLiquidacionPrestamoHistoricoPk() {
		return liquidacionPrestamoHistoricoPk;
	}

	public void setLiquidacionPrestamoHistoricoPk(
			LiquidacionPrestamoHistoricoPK liquidacionPrestamoHistoricoPK) {
		this.liquidacionPrestamoHistoricoPk = liquidacionPrestamoHistoricoPK;
	}

	public Date getFecfin() {
        return fecfin;
    }

    public void setFecfin(Date fecfin) {
        this.fecfin = fecfin;
    }

    public String getObstra() {
        return obstra;
    }

    public void setObstra(String obstra) {
        this.obstra = obstra;
    }

    public String getCodtipliq() {
        return codtipliq;
    }

    public void setCodtipliq(String codtipliq) {
        this.codtipliq = codtipliq;
    }

    public LiquidacionPrestamo getLiquidacionPrestamo() {
        return liquidacionPrestamo;
    }

    public void setLiquidacionPrestamo(LiquidacionPrestamo liquidacionPrestamo) {
        this.liquidacionPrestamo = liquidacionPrestamo;
    }

    public LiquidacionPrestamoEstado getLiquidacionPrestamoEstado() {
        return liquidacionPrestamoEstado;
    }

    public void setLiquidacionPrestamoEstado(LiquidacionPrestamoEstado liquidacionPrestamoEstado) {
        this.liquidacionPrestamoEstado = liquidacionPrestamoEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liquidacionPrestamoHistoricoPk != null ? liquidacionPrestamoHistoricoPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiquidacionPrestamoHistorico)) {
            return false;
        }
        LiquidacionPrestamoHistorico other = (LiquidacionPrestamoHistorico) object;
        if ((this.liquidacionPrestamoHistoricoPk == null && other.liquidacionPrestamoHistoricoPk != null) || (this.liquidacionPrestamoHistoricoPk != null && !this.liquidacionPrestamoHistoricoPk.equals(other.liquidacionPrestamoHistoricoPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gov.iess.creditos.modelo.persistencia.Kscretpreliqesthis[liquidacionPrestamoHistoricoPk=" + liquidacionPrestamoHistoricoPk + "]";
    }

}
