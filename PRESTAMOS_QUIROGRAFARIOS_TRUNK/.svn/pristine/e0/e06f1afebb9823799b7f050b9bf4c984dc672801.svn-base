
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cvillarreal
 *
 */
@Entity
@Table(name="CRE_TIPOACCION_TBL")
public class TipoAccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODTIPACC",nullable=false)
	private Long id;
	
	@Column(name="DESTIPACC",nullable=false)
	@Basic(optional=false)
	private String descripcion;
	
	@Column(name="ESTTIPACC",nullable=false)
	@Basic(optional=false)
	private String estadoAccion;
	
	public TipoAccion(Long id){
		this.id = id;
	}

	/**
	 * 
	 */
	public TipoAccion() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getEstadoAccion() {
		return estadoAccion;
	}


	public void setEstadoAccion(String estadoAccion) {
		this.estadoAccion = estadoAccion;
	}

}
