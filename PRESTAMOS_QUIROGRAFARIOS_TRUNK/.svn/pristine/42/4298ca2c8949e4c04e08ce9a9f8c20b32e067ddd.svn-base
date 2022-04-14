
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author rtituana
 */
@Entity
@Table(name = "KSCRETPRELIQEST")
public class LiquidacionPrestamoEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODESTLIQPRE")
    private String codestliqpre;
    
    @Basic(optional = false)
    @Column(name = "DESESTLIQPRE")
    private String desestliqpre;
    
    @Basic(optional = false)
    @Column(name = "INDHABESTLIQ")
    private char indhabestliq;
/*    
    @OneToMany(mappedBy = "codestliqpre")
    private List<LiquidacionPrestamo> liquidacionPrestamoList;
*/    
    @OneToMany(mappedBy = "liquidacionPrestamoEstado")
    private List<LiquidacionPrestamoHistorico> liquidacionPrestamoHistoricoList;

    public LiquidacionPrestamoEstado() {
    }

    public LiquidacionPrestamoEstado(String codestliqpre) {
        this.codestliqpre = codestliqpre;
    }

    public LiquidacionPrestamoEstado(String codestliqpre, String desestliqpre, char indhabestliq) {
        this.codestliqpre = codestliqpre;
        this.desestliqpre = desestliqpre;
        this.indhabestliq = indhabestliq;
    }

    public String getCodestliqpre() {
        return codestliqpre;
    }

    public void setCodestliqpre(String codestliqpre) {
        this.codestliqpre = codestliqpre;
    }

    public String getDesestliqpre() {
        return desestliqpre;
    }

    public void setDesestliqpre(String desestliqpre) {
        this.desestliqpre = desestliqpre;
    }

    public char getIndhabestliq() {
        return indhabestliq;
    }

    public void setIndhabestliq(char indhabestliq) {
        this.indhabestliq = indhabestliq;
    }
/*
    public List<LiquidacionPrestamo> getKscretpreliqList() {
        return liquidacionPrestamoList;
    }

    public void setKscretpreliqList(List<LiquidacionPrestamo> liquidacionPrestamoList) {
        this.liquidacionPrestamoList = liquidacionPrestamoList;
    }
*/
    public List<LiquidacionPrestamoHistorico> getLiquidacionPrestamoHistoricoList() {
        return liquidacionPrestamoHistoricoList;
    }

    public void setLiquidacionPrestamoHistoricoList(List<LiquidacionPrestamoHistorico> liquidacionPrestamoHistoricoList) {
        this.liquidacionPrestamoHistoricoList = liquidacionPrestamoHistoricoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codestliqpre != null ? codestliqpre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiquidacionPrestamoEstado)) {
            return false;
        }
        LiquidacionPrestamoEstado other = (LiquidacionPrestamoEstado) object;
        if ((this.codestliqpre == null && other.codestliqpre != null) || (this.codestliqpre != null && !this.codestliqpre.equals(other.codestliqpre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gov.iess.creditos.modelo.persistencia.Kscretpreliqest[codestliqpre=" + codestliqpre + "]";
    }

}
