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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoEstadoHistoricoPK;

/**
 *
 * @author rtituana
 */
@Entity
@Table(name = "KSCRETPREESTHIS")
public class PrestamoEstadoHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrestamoEstadoHistoricoPK prestamoEstadoHistoricoPK;
    @Column(name = "FECFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecfin;
    @Column(name = "OBSTRA")
    private String obstra;
    @JoinColumns({@JoinColumn(name = "NUMPREAFI", referencedColumnName = "NUMPREAFI", insertable = false, updatable = false), @JoinColumn(name = "ORDPREAFI", referencedColumnName = "ORDPREAFI", insertable = false, updatable = false), @JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP", insertable = false, updatable = false), @JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Prestamo prestamo;
    @JoinColumn(name = "CODESTPRE", referencedColumnName = "CODESTPRE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EstadoPrestamo estadoPrestamo;

    public PrestamoEstadoHistorico() {
    }

    public PrestamoEstadoHistorico(PrestamoEstadoHistoricoPK prestamoEstadoHistoricoPK) {
        this.prestamoEstadoHistoricoPK = prestamoEstadoHistoricoPK;
    }

    public PrestamoEstadoHistorico(Date fecini, String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla) {
        this.prestamoEstadoHistoricoPK = new PrestamoEstadoHistoricoPK(fecini, codestpre, numpreafi, ordpreafi, codpretip, codprecla);
    }

    public PrestamoEstadoHistoricoPK getPrestamoEstadoHistoricoPK() {
        return prestamoEstadoHistoricoPK;
    }

    public void setPrestamoEstadoHistoricoPK(PrestamoEstadoHistoricoPK prestamoEstadoHistoricoPK) {
        this.prestamoEstadoHistoricoPK = prestamoEstadoHistoricoPK;
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

    public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public EstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}

	public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (prestamoEstadoHistoricoPK != null ? prestamoEstadoHistoricoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoEstadoHistorico)) {
            return false;
        }
        PrestamoEstadoHistorico other = (PrestamoEstadoHistorico) object;
        if ((this.prestamoEstadoHistoricoPK == null && other.prestamoEstadoHistoricoPK != null) || (this.prestamoEstadoHistoricoPK != null && !this.prestamoEstadoHistoricoPK.equals(other.prestamoEstadoHistoricoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gov.iess.creditos.modelo.persistencia.Kscretpreesthis[kscretpreesthisPK=" + prestamoEstadoHistoricoPK + "]";
    }

}
