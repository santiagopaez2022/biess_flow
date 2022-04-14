/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author cvillarreal
 *
 */
@Entity
@Table(name="CRE_AGENCIAS_TBL")
@NamedQueries({
	@NamedQuery(name="Agencia.buscarPorProvincia",query="SELECT o FROM Agencia o WHERE o.idProvincia=:idProvincia")
})
public class Agencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8354231196681696268L;
	
	@Id
	@Column(name="CODAGN",nullable=false)
	@Basic(optional=false)
	private String id;
	
	@Column(name="CODPRO",nullable=false)
	@Basic(optional=false)
	private String idProvincia;
	
	@Column(name="DESAGN",nullable=true)
	@Basic(optional=true)
	private String descripcion;
	
	public Agencia(){
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the idProvincia
	 */
	public String getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param idProvincia the idProvincia to set
	 */
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
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
