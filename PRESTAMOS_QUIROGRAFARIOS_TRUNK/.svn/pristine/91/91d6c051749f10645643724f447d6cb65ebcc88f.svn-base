/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.2 $  [Sep 25, 2007]
 * @author pablo
 */
@Entity
@Table(name = "KSCRETPRECLA")
public class ClasePrestamo implements Serializable {

	@GeneratedValue
	private static final long serialVersionUID = 8305722556658588422L;

	@Column(name = "CODPRECLA", nullable = false)
	@Id
	private Integer codPreCla;

	@Column(name = "DESPRECLA", nullable = false)
	private String descripcion;

	@Column(name = "INDHABPRECLA", nullable = false)
	private String indHabPreCla;

	/**
	 * Returns the value of codPreCla.
	 * @return codPreCla
	 */
	public Integer getCodPreCla() {
		return codPreCla;
	}

	/**
	 * Sets the value for codPreCla.
	 * @param codPreCla
	 */
	public void setCodPreCla(Integer codPreCla) {
		this.codPreCla = codPreCla;
	}

	/**
	 * Returns the value of descripcion.
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the value for descripcion.
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Returns the value of indHabPreCla.
	 * @return indHabPreCla
	 */
	public String getIndHabPreCla() {
		return indHabPreCla;
	}

	/**
	 * Sets the value for indHabPreCla.
	 * @param indHabPreCla
	 */
	public void setIndHabPreCla(String indHabPreCla) {
		this.indHabPreCla = indHabPreCla;
	}

}