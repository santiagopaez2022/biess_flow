/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.EstadoGenerico;

/**
 * @author cvillarreal
 *
 */
@Entity
@Table(name="CRE_RUBROSCALIF_TBL")
@SequenceGenerator(name="secRubroCalificacion",sequenceName="CRE_RUBROSCALIF_SEC",initialValue=1,allocationSize=1)
@NamedQueries({
	@NamedQuery(name="RubroCalificacion.allActivos",
			query="SELECT o FROM RubroCalificacion o WHERE o.estado='ACT' ORDER BY o.descripcion")
})
public class RubroCalificacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8344994725634540674L;

	@Id
	@Column(name="SECCOSHP",nullable=false)
	@Basic(optional=false)
	@GeneratedValue(generator="secRubroCalificacion",strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="DESCOSHP",nullable=false)
	@Basic(optional=false)
	private String descripcion;
	
	@Column(name="ESTCOSHP",nullable=false)
	@Basic(optional=false)
	@Enumerated
	private EstadoGenerico estado;

	
	/**
	 * @return the estado
	 */
	public EstadoGenerico getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoGenerico estado) {
		this.estado = estado;
	}

	public RubroCalificacion(){
		
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
