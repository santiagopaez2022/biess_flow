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

import ec.gov.iess.creditos.modelo.persistencia.pk.DestinoCreditoPk;


/**
 * @author Angel Sarmiento 03/10/2011
 * 
 */
@Entity
@Table(name = "CRE_DESTINOPQ_TBL")
public class DestinoCredito implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DestinoCreditoPk pk;
	
	@Column(name = "DPQ_OBSERVACION")
	private String observacion;

	@Column(name = "DPQ_FEC_REGISTRO")
	private Date fechaRegistro;

	@ManyToOne
	@JoinColumns({
        @JoinColumn(name="NUMPREAFI", referencedColumnName="NUMPREAFI", insertable = false, updatable = false),
        @JoinColumn(name="ORDPREAFI", referencedColumnName="ORDPREAFI", insertable = false, updatable = false),
        @JoinColumn(name="CODPRETIP", referencedColumnName="CODPRETIP", insertable = false, updatable = false),
        @JoinColumn(name="CODPRECLA", referencedColumnName="CODPRECLA", insertable = false, updatable = false)
    })    
	private Prestamo prestamo;
	
	@ManyToOne
	@JoinColumn(name = "COPQ_ID", referencedColumnName = "COPQ_ID", insertable = false, updatable = false)
	private Catalogo catalogo;
	
	public DestinoCreditoPk getPk() {
		return pk;
	}

	public void setPk(DestinoCreditoPk pk) {
		this.pk = pk;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public DestinoCredito()
	{
		
	}
	
	
		
	

}
