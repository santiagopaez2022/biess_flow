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

import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoHistoricoPK;

@Entity
@Table(name = "KSCRETDIVPREESTHIS")
public class DividendoPrestamoHistorico implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private DividendoPrestamoHistoricoPK dividendoPrestamoHistoricoPK;
    
    @Column(name = "FECFIN")
    private Date fecfin;
    
    @Column(name = "OBSTRA")
    private String obstra;
    
    @JoinColumns({
    	@JoinColumn(name = "NUMDIV", referencedColumnName = "NUMDIV", insertable = false, updatable = false), 
    	@JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP", insertable = false, updatable = false), 
    	@JoinColumn(name = "ORDPREAFI", referencedColumnName = "ORDPREAFI", insertable = false, updatable = false), 
    	@JoinColumn(name = "NUMPREAFI", referencedColumnName = "NUMPREAFI", insertable = false, updatable = false), 
    	@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA", insertable = false, updatable = false)
    })
    @ManyToOne
    private DividendoPrestamo dividendoPrestamo;
    
    
    @ManyToOne
    @JoinColumn(name = "CODESTDIV", referencedColumnName = "CODESTDIV", insertable = false, updatable = false)
    private EstadoDividendoPrestamo estadoDividendoPrestamo;
    
    

    public DividendoPrestamoHistorico() {
    }

    public DividendoPrestamoHistorico(DividendoPrestamoHistoricoPK kscretdivpreesthisPK) {
        this.dividendoPrestamoHistoricoPK = kscretdivpreesthisPK;
    }

    public DividendoPrestamoHistorico(Date fecini, Long numdiv, Long codpretip, Long codprecla, Long numpreafi, Long ordpreafi, String codestdiv) {
        this.dividendoPrestamoHistoricoPK = new DividendoPrestamoHistoricoPK(fecini, numdiv, codpretip, codprecla, numpreafi, ordpreafi, codestdiv);
    }

    public DividendoPrestamoHistoricoPK getDividendoPrestamoHistoricoPK() {
        return dividendoPrestamoHistoricoPK;
    }

    public void setDividendoPrestamoHistoricoPK(DividendoPrestamoHistoricoPK dividendoPrestamoHistoricoPK) {
        this.dividendoPrestamoHistoricoPK = dividendoPrestamoHistoricoPK;
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

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dividendoPrestamoHistoricoPK != null ? dividendoPrestamoHistoricoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DividendoPrestamoHistorico)) {
            return false;
        }
        DividendoPrestamoHistorico other = (DividendoPrestamoHistorico) object;
        if ((this.dividendoPrestamoHistoricoPK == null && other.dividendoPrestamoHistoricoPK != null) || (this.dividendoPrestamoHistoricoPK != null && !this.dividendoPrestamoHistoricoPK.equals(other.dividendoPrestamoHistoricoPK))) {
            return false;
        }
        return true;
    }

    

	public DividendoPrestamo getDividendoPrestamo() {
		return dividendoPrestamo;
	}

	public void setDividendoPrestamo(DividendoPrestamo dividendoPrestamo) {
		this.dividendoPrestamo = dividendoPrestamo;
	}

	public EstadoDividendoPrestamo getEstadoDividendoPrestamo() {
		return estadoDividendoPrestamo;
	}

	public void setEstadoDividendoPrestamo(
			EstadoDividendoPrestamo estadoDividendoPrestamo) {
		this.estadoDividendoPrestamo = estadoDividendoPrestamo;
	}

	@Override
    public String toString() {
        return "ec.gov.iess.creditos.modelo.persistencia.Kscretdivpreesthis[kscretdivpreesthisPK=" + dividendoPrestamoHistoricoPK + "]";
    }

}

