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
@Table(name = "CRE_DOCUMENTACION_TBL")
public class DocumentacionRequerida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6749025903826382423L;

	@Id
	@Column(name = "CODDOC")
	private Long id;

	@Column(name = "DESDOC")
	private String descripcion;

	@Column(name = "OBSDOC")
	private String observacion;

	/**
	 * 
	 */
	public DocumentacionRequerida() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}