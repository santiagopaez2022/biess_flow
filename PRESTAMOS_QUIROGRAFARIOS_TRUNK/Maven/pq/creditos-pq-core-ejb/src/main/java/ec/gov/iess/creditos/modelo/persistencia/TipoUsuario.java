/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <b> Persistencia de tabla de tipos de afiliados </b>
 * 
 * @author jsanchez 
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 03/10/2011 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_TIPOUSUARIO_TBL")
public class TipoUsuario implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 7198239851635844185L;

	@Id
	@Column(name = "TU_ID", nullable = false)
	private Long id;

	@Column(name = "TU_DESCRIPCION", nullable = false)
	private String descripcion;

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
}
