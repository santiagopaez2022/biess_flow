/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cvillarreal
 *
 */
@Entity
@Table(name="CRE_ESTADOSPRESTAMOS_TBL")
public class EstadosPrestamoProceso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995555918050029399L;
	
	@Id
	@Column(name="CODPRE")
	private Long id;
	
	@Column(name="DESPRE")
	private String descripcion;
	
	@Column(name="ORDEN")
	private Integer orden;
	
	public EstadosPrestamoProceso(){
		
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

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

}
