/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.dto.FechaVigencia;

/**
 * @author cvillarreal
 *
 */
@Entity
@Table(name="CRE_HITOSCOSTOS_TBL")
@SequenceGenerator(name="secHitoCosto",sequenceName="CRE_HITOSCOSTOS_SEQ",initialValue=1,allocationSize=1)
public class HitoCosto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5816653808743702668L;

	@Id
	@Column(name="ID_HITOCOSTO",nullable=false)
	@Basic(optional=false)
	@GeneratedValue(generator="secHitoCosto",strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_HITOSCONTROL")
	private HitoControl hitoControl;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="fechaDesde",column=@Column(name="FECHA_INI",nullable=false)),
		@AttributeOverride(name="fechaHasta",column=@Column(name="FECHA_FIN",nullable=false))
	})
	private FechaVigencia fechaVigencia;
	
	
	@Column(name="COSTO_ESTIMADO",nullable=false)
	@Basic(optional=false)
	private BigDecimal valor=new BigDecimal(0);
	
	
	/**
	 * 
	 */
	public HitoCosto() {
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the hitoControl
	 */
	public HitoControl getHitoControl() {
		return hitoControl;
	}



	/**
	 * @param hitoControl the hitoControl to set
	 */
	public void setHitoControl(HitoControl hitoControl) {
		this.hitoControl = hitoControl;
	}



	/**
	 * @return the fechaVigencia
	 */
	public FechaVigencia getFechaVigencia() {
		return fechaVigencia;
	}



	/**
	 * @param fechaVigencia the fechaVigencia to set
	 */
	public void setFechaVigencia(FechaVigencia fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}



	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}



	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
