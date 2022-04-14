/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "KSCRETCUEINDTRATIP")
public class CuentaIndividualTrabajoTipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODTIPTRA")
    private String codtiptra;
    @Basic(optional = false)
    @Column(name = "DESTIPTRA")
    private String destiptra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codtiptra")
    private List<CuentaIndividual> cuentaIndividualList;

    public CuentaIndividualTrabajoTipo() {
    }

    public CuentaIndividualTrabajoTipo(String codtiptra) {
        this.codtiptra = codtiptra;
    }

    public CuentaIndividualTrabajoTipo(String codtiptra, String destiptra) {
        this.codtiptra = codtiptra;
        this.destiptra = destiptra;
    }

    public String getCodtiptra() {
        return codtiptra;
    }

    public void setCodtiptra(String codtiptra) {
        this.codtiptra = codtiptra;
    }

    public String getDestiptra() {
        return destiptra;
    }

    public void setDestiptra(String destiptra) {
        this.destiptra = destiptra;
    }   

    public List<CuentaIndividual> getCuentaIndividualList() {
		return cuentaIndividualList;
	}

	public void setCuentaIndividualList(List<CuentaIndividual> cuentaIndividualList) {
		this.cuentaIndividualList = cuentaIndividualList;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (codtiptra != null ? codtiptra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaIndividualTrabajoTipo)) {
            return false;
        }
        CuentaIndividualTrabajoTipo other = (CuentaIndividualTrabajoTipo) object;
        if ((this.codtiptra == null && other.codtiptra != null) || (this.codtiptra != null && !this.codtiptra.equals(other.codtiptra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gov.iess.creditos.modelo.persistencia.Kscretcueindtratip[codtiptra=" + codtiptra + "]";
    }

}
