package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the CRE_PRESTACIONCONCESION_TBL database table.
 * 
 */
@Entity
@Table(name="CRE_PRESTACIONCONCESION_TBL")
@SequenceGenerator(name = "CRE_PRESTACIONCONCESION_SEQ", sequenceName = "CRE_PRESTACIONCONCESION_SEQ", allocationSize = 1)
public class PrestacionConcesion  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="PP_ID_PRESTCONCESION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRE_PRESTACIONCONCESION_SEQ")
	private Long ppIdPorcentajeprest;
	
	@Column(name="PP_CODIGO_PRESTACION")
	private Long ppCodigoPrestacion;
	
	@Column(name="PP_VALOR_PRESTACIONPQ")
	private BigDecimal ppValorPrestacionpq;
	
	@Column(name="PP_PORCENTAJE_PARTICIPACION")
	private BigDecimal ppPorcentajeParticipacion;
	
	@Column(name="PP_PORCENTAJE_ENDEUDAMIENTO")
	private BigDecimal ppPorcentajeCapendeudamiento;

    @Temporal( TemporalType.DATE)
	@Column(name="PP_FECHA_CREACION")
	private Date ppFechaCreacion;

	



	

	



	//bi-directional many-to-one association to Kscretcredito
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="CODPRECLA", referencedColumnName="CODPRECLA"),
		@JoinColumn(name="CODPRETIP", referencedColumnName="CODPRETIP"),
		@JoinColumn(name="NUMPREAFI", referencedColumnName="NUMPREAFI"),
		@JoinColumn(name="ORDPREAFI", referencedColumnName="ORDPREAFI")
		})
	private Prestamo pretamo;

   

	public PrestacionConcesion() {
		super();
	}

	public Long getPpCodigoPrestacion() {
		return this.ppCodigoPrestacion;
	}

	public void setPpCodigoPrestacion(Long ppCodigoPrestacion) {
		this.ppCodigoPrestacion = ppCodigoPrestacion;
	}

	public Date getPpFechaCreacion() {
		return this.ppFechaCreacion;
	}

	public void setPpFechaCreacion(Date ppFechaCreacion) {
		this.ppFechaCreacion = ppFechaCreacion;
	}

	public Long getPpIdPorcentajeprest() {
		return this.ppIdPorcentajeprest;
	}

	public void setPpIdPorcentajeprest(Long ppIdPorcentajeprest) {
		this.ppIdPorcentajeprest = ppIdPorcentajeprest;
	}

	public BigDecimal getPpPorcentajeCapendeudamiento() {
		return this.ppPorcentajeCapendeudamiento;
	}

	public void setPpPorcentajeCapendeudamiento(BigDecimal ppPorcentajeCapendeudamiento) {
		this.ppPorcentajeCapendeudamiento = ppPorcentajeCapendeudamiento;
	}

	public BigDecimal getPpPorcentajeParticipacion() {
		return this.ppPorcentajeParticipacion;
	}

	public void setPpPorcentajeParticipacion(BigDecimal ppPorcentajeParticipacion) {
		this.ppPorcentajeParticipacion = ppPorcentajeParticipacion;
	}

	public BigDecimal getPpValorPrestacionpq() {
		return this.ppValorPrestacionpq;
	}

	public void setPpValorPrestacionpq(BigDecimal ppValorPrestacionpq) {
		this.ppValorPrestacionpq = ppValorPrestacionpq;
	}

	public Prestamo getPretamo() {
		return pretamo;
	}

	public void setPretamo(Prestamo pretamo) {
		this.pretamo = pretamo;
	}

	
}