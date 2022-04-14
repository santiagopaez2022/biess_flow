/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rtituana 03/10/2011
 */
@Entity
@Table(name = "CRE_RESUMENCREDITOPQ_TBL")
@NamedQueries({
    @NamedQuery(name = "ResumenCreditoPQ.findAll", query = "SELECT r FROM ResumenCreditoPQ r")})
@SequenceGenerator(name = "CRE_RESUMENCREDITOPQ_SEQ", sequenceName = "CRE_RESUMENCREDITOPQ_SEQ", allocationSize = 1)
public class ResumenCreditoPQ implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id    
    @Column(name = "RPQ_ID")
    @GeneratedValue(generator = "CRE_RESUMENCREDITOPQ_SEQ", strategy = GenerationType.SEQUENCE)
    private Long rpqId;
    @Column(name = "RPQ_RUCINSFINANCIERA")
    private String rpqRucinsfinanciera;
    @Column(name = "RPQ_NUMCUENTA")
    private String rpqNumcuenta;
    @Column(name = "RPQ_RUCEMPLEADOR")
    private String rpqRucempleador;
    @Column(name = "RPQ_EDAD")
    private Long rpqEdad;
   
    
    @Column(name = "RPQ_TIPOAFILIADO")
    private String rpqTipoafiliado;
    
    
    @Column(name = "RPQ_NUMAPORTACIONESTOT")
    private Long rpqNumaportaciones;
    
    
    @Column(name = "RPQ_NUMAPORTACIONESCON")
    private Long rpqNumaportacionescon;
    
    
    @Column(name = "RPQ_PLZMAXIMO")
    private Long rpqPlzmaximo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RPQ_TASAINT")
    private BigDecimal rpqTasaint;
    @Column(name = "RPQ_SUELDOPROM")
    private BigDecimal rpqSueldoprom;
    @Column(name = "RPQ_CAPPAGO")
    private BigDecimal rpqCappago;
    
    @Column(name = "RPQ_CAPENDEUDAMIENTO")
    private BigDecimal rpqCapendeudamiento;
    @Column(name = "RPQ_CUOOTROSPRES")
    private BigDecimal rpqCuootrospres;
    @Column(name = "RPQ_TOTCESANTIA")
    private BigDecimal rpqTotcesantia;
    @Column(name = "RPQ_TOTFR")
    private BigDecimal rpqTotfr;
    @Column(name = "RPQ_VALDISCES")
    private BigDecimal rpqValdisces;
    @Column(name = "RPQ_VALDISFR")
    private BigDecimal rpqValdisfr;
    @Column(name = "RPQ_VALCOMCES")
    private BigDecimal rpqValcomces;
    @Column(name = "RPQ_VALCOMFR")
    private BigDecimal rpqValcomfr;
    @Column(name = "RPQ_TOTDISGAR")
    private BigDecimal rpqTotdisgar;
    @Column(name = "RPQ_NUMTOTPH")
    private Long rpqNumtotph;
    @Column(name = "RPQ_NUMTOTPQ")
    private Long rpqNumtotpq;
    @Column(name = "RPQ_MONTOTDIVPQ")
    private BigDecimal rpqMontotdivpq;
    @Column(name = "RPQ_MONTOTDIVPH")
    private BigDecimal rpqMontotdivph;
    @Column(name = "RPQ_TOTMONCONPQ")
    private BigDecimal rpqTotmonconpq;
    @Column(name = "RPQ_TOTMONCONPH")
    private BigDecimal rpqTotmonconph;
    @Column(name = "RPQ_MONTOCREDITO")
    private BigDecimal rpqMontocredito;
    @Column(name = "RPQ_SEGDESGRA")
    private BigDecimal rpqSegdesgra;
    @Column(name = "RPQ_MONTOLIQUIDO")
    private BigDecimal rpqMontoliquido;
    @Column(name = "RPQ_PLAZO")
    private Long rpqPlazo;
    @Column(name = "RPQ_INTERES")
    private BigDecimal rpqInteres;
    @Column(name = "RPQ_TOTCAPITAL")
    private BigDecimal rpqTotcapital;
    @Column(name = "RPQ_TOTINTERES")
    private BigDecimal rpqTotinteres;
    @Column(name = "RPQ_AFIJUB")
    private String rpqAfijub;
    @Column(name = "RPQ_FEC_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rpqFecRegistro;
    
    /*@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "NUMPREAFI", referencedColumnName = "NUMPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "ORDPREAFI", referencedColumnName = "ORDPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP", insertable = false, updatable = false),
			@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA", insertable = false, updatable = false) })
	private Prestamo prestamo;*/
    
    
    @Column(name = "NUMPREAFI")
    private Long numpreafi;
    
    @Column(name = "ORDPREAFI")
    private Long ordpreafi;
    
    @Column(name = "CODPRETIP")
    private Long codpretip;
    
    @Column(name = "CODPRECLA")
    private Long codprecla;

    public ResumenCreditoPQ() {
    }

    public ResumenCreditoPQ(Long rpqId) {
        this.rpqId = rpqId;
    }

    public Long getRpqId() {
        return rpqId;
    }

    public void setRpqId(Long rpqId) {
        this.rpqId = rpqId;
    }

    public String getRpqRucinsfinanciera() {
        return rpqRucinsfinanciera;
    }

    public void setRpqRucinsfinanciera(String rpqRucinsfinanciera) {
        this.rpqRucinsfinanciera = rpqRucinsfinanciera;
    }

    public String getRpqNumcuenta() {
        return rpqNumcuenta;
    }

    public void setRpqNumcuenta(String rpqNumcuenta) {
        this.rpqNumcuenta = rpqNumcuenta;
    }

    public String getRpqRucempleador() {
        return rpqRucempleador;
    }

    public void setRpqRucempleador(String rpqRucempleador) {
        this.rpqRucempleador = rpqRucempleador;
    }

    public Long getRpqEdad() {
        return rpqEdad;
    }

    public void setRpqEdad(Long rpqEdad) {
        this.rpqEdad = rpqEdad;
    }

    public Long getRpqPlzmaximo() {
        return rpqPlzmaximo;
    }

    public void setRpqPlzmaximo(Long rpqPlzmaximo) {
        this.rpqPlzmaximo = rpqPlzmaximo;
    }

    public BigDecimal getRpqTasaint() {
        return rpqTasaint;
    }

    public void setRpqTasaint(BigDecimal rpqTasaint) {
        this.rpqTasaint = rpqTasaint;
    }

    public BigDecimal getRpqSueldoprom() {
        return rpqSueldoprom;
    }

    public void setRpqSueldoprom(BigDecimal rpqSueldoprom) {
        this.rpqSueldoprom = rpqSueldoprom;
    }

    public BigDecimal getRpqCuootrospres() {
        return rpqCuootrospres;
    }

    public void setRpqCuootrospres(BigDecimal rpqCuootrospres) {
        this.rpqCuootrospres = rpqCuootrospres;
    }

    public BigDecimal getRpqTotcesantia() {
        return rpqTotcesantia;
    }

    public void setRpqTotcesantia(BigDecimal rpqTotcesantia) {
        this.rpqTotcesantia = rpqTotcesantia;
    }

    public BigDecimal getRpqTotfr() {
        return rpqTotfr;
    }

    public void setRpqTotfr(BigDecimal rpqTotfr) {
        this.rpqTotfr = rpqTotfr;
    }

    public BigDecimal getRpqValdisces() {
        return rpqValdisces;
    }

    public void setRpqValdisces(BigDecimal rpqValdisces) {
        this.rpqValdisces = rpqValdisces;
    }

    public BigDecimal getRpqValdisfr() {
        return rpqValdisfr;
    }

    public void setRpqValdisfr(BigDecimal rpqValdisfr) {
        this.rpqValdisfr = rpqValdisfr;
    }

    public BigDecimal getRpqValcomces() {
        return rpqValcomces;
    }

    public void setRpqValcomces(BigDecimal rpqValcomces) {
        this.rpqValcomces = rpqValcomces;
    }

    public BigDecimal getRpqValcomfr() {
        return rpqValcomfr;
    }

    public void setRpqValcomfr(BigDecimal rpqValcomfr) {
        this.rpqValcomfr = rpqValcomfr;
    }

    public BigDecimal getRpqTotdisgar() {
        return rpqTotdisgar;
    }

    public void setRpqTotdisgar(BigDecimal rpqTotdisgar) {
        this.rpqTotdisgar = rpqTotdisgar;
    }

    public Long getRpqNumtotph() {
        return rpqNumtotph;
    }

    public void setRpqNumtotph(Long rpqNumtotph) {
        this.rpqNumtotph = rpqNumtotph;
    }

    public Long getRpqNumtotpq() {
        return rpqNumtotpq;
    }

    public void setRpqNumtotpq(Long rpqNumtotpq) {
        this.rpqNumtotpq = rpqNumtotpq;
    }

    public BigDecimal getRpqMontotdivpq() {
        return rpqMontotdivpq;
    }

    public void setRpqMontotdivpq(BigDecimal rpqMontotdivpq) {
        this.rpqMontotdivpq = rpqMontotdivpq;
    }

    public BigDecimal getRpqMontotdivph() {
        return rpqMontotdivph;
    }

    public void setRpqMontotdivph(BigDecimal rpqMontotdivph) {
        this.rpqMontotdivph = rpqMontotdivph;
    }

    public BigDecimal getRpqTotmonconpq() {
        return rpqTotmonconpq;
    }

    public void setRpqTotmonconpq(BigDecimal rpqTotmonconpq) {
        this.rpqTotmonconpq = rpqTotmonconpq;
    }

    public BigDecimal getRpqTotmonconph() {
        return rpqTotmonconph;
    }

    public void setRpqTotmonconph(BigDecimal rpqTotmonconph) {
        this.rpqTotmonconph = rpqTotmonconph;
    }

    public BigDecimal getRpqMontocredito() {
        return rpqMontocredito;
    }

    public void setRpqMontocredito(BigDecimal rpqMontocredito) {
        this.rpqMontocredito = rpqMontocredito;
    }

    public BigDecimal getRpqSegdesgra() {
        return rpqSegdesgra;
    }

    public void setRpqSegdesgra(BigDecimal rpqSegdesgra) {
        this.rpqSegdesgra = rpqSegdesgra;
    }

    public BigDecimal getRpqMontoliquido() {
        return rpqMontoliquido;
    }

    public void setRpqMontoliquido(BigDecimal rpqMontoliquido) {
        this.rpqMontoliquido = rpqMontoliquido;
    }

    public Long getRpqPlazo() {
        return rpqPlazo;
    }

    public void setRpqPlazo(Long rpqPlazo) {
        this.rpqPlazo = rpqPlazo;
    }

    public BigDecimal getRpqInteres() {
        return rpqInteres;
    }

    public void setRpqInteres(BigDecimal rpqInteres) {
        this.rpqInteres = rpqInteres;
    }

    public BigDecimal getRpqTotcapital() {
        return rpqTotcapital;
    }

    public void setRpqTotcapital(BigDecimal rpqTotcapital) {
        this.rpqTotcapital = rpqTotcapital;
    }

    public BigDecimal getRpqTotinteres() {
        return rpqTotinteres;
    }

    public void setRpqTotinteres(BigDecimal rpqTotinteres) {
        this.rpqTotinteres = rpqTotinteres;
    }

    public String getRpqAfijub() {
        return rpqAfijub;
    }

    public void setRpqAfijub(String rpqAfijub) {
        this.rpqAfijub = rpqAfijub;
    }

    public Date getRpqFecRegistro() {
        return rpqFecRegistro;
    }

    public void setRpqFecRegistro(Date rpqFecRegistro) {
        this.rpqFecRegistro = rpqFecRegistro;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rpqId != null ? rpqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResumenCreditoPQ)) {
            return false;
        }
        ResumenCreditoPQ other = (ResumenCreditoPQ) object;
        if ((this.rpqId == null && other.rpqId != null) || (this.rpqId != null && !this.rpqId.equals(other.rpqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gov.iess.creditos.modelo.persistencia.ResumenCreditoPQ[ rpqId=" + rpqId + " ]";
    }

	

	public BigDecimal getRpqCappago() {
		return rpqCappago;
	}

	public void setRpqCappago(BigDecimal rpqCappago) {
		this.rpqCappago = rpqCappago;
	}

	public BigDecimal getRpqCapendeudamiento() {
		return rpqCapendeudamiento;
	}

	public void setRpqCapendeudamiento(BigDecimal rpqCapendeudamiento) {
		this.rpqCapendeudamiento = rpqCapendeudamiento;
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

	public String getRpqTipoafiliado() {
		return rpqTipoafiliado;
	}

	public void setRpqTipoafiliado(String rpqTipoafiliado) {
		this.rpqTipoafiliado = rpqTipoafiliado;
	}

	public Long getRpqNumaportaciones() {
		return rpqNumaportaciones;
	}

	public void setRpqNumaportaciones(Long rpqNumaportaciones) {
		this.rpqNumaportaciones = rpqNumaportaciones;
	}

	public Long getRpqNumaportacionescon() {
		return rpqNumaportacionescon;
	}

	public void setRpqNumaportacionescon(Long rpqNumaportacionescon) {
		this.rpqNumaportacionescon = rpqNumaportacionescon;
	}
	
	
    
}
