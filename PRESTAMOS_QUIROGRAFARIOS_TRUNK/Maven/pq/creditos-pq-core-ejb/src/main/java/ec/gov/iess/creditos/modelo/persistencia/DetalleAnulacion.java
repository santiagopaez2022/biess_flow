/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author cvillarreal
 *
 */
@Entity
@Table(name="CRE_DETALLEANULACION_TBL")
@SequenceGenerator(name="secDetalleAnulacion",sequenceName="CRE_DETALLEANULACION_SEQ",initialValue=1,allocationSize=1)
public class DetalleAnulacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7056690829573033376L;

	@Id
	@Column(name="SECDETCOS",nullable=false)
	@Basic(optional=false)
	@GeneratedValue(generator="secDetalleAnulacion",strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="SECANU")
	private NovedadAnulacion novedadAnulacion;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="SECCOSHP")
	private RubroCalificacion rubroCalificacion;
	
	@Column(name="VALCOS",nullable=false)
	@Basic(optional=false)
	private BigDecimal valor = BigDecimal.valueOf(0);
	
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
	 * @return the novedadAnulacion
	 */
	public NovedadAnulacion getNovedadAnulacion() {
		return novedadAnulacion;
	}

	/**
	 * @param novedadAnulacion the novedadAnulacion to set
	 */
	public void setNovedadAnulacion(NovedadAnulacion novedadAnulacion) {
		this.novedadAnulacion = novedadAnulacion;
	}

	/**
	 * @return the rubroCalificacion
	 */
	public RubroCalificacion getRubroCalificacion() {
		return rubroCalificacion;
	}

	/**
	 * @param rubroCalificacion the rubroCalificacion to set
	 */
	public void setRubroCalificacion(RubroCalificacion rubroCalificacion) {
		this.rubroCalificacion = rubroCalificacion;
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

	/**
	 * 
	 */
	public DetalleAnulacion() {
	}

}
