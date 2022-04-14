/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entidad para realizar crud en la tabla KSCRETCUEBANTIP.
 * 
 * @author diego.iza
 * 
 */

@Entity
@Table(name = "KSCRETCUEBANTIP")
@NamedQueries({
		@NamedQuery(name = "TipoCuentaBiess.obtenerTodos", query = "SELECT c from TipoCuentaBiess c ORDER BY c.codigo"),
		@NamedQuery(name = "TipoCuentaBiess.obtenerPorCodigo", query = "SELECT c from TipoCuentaBiess c where c.codigo=:codigo") })
public class TipoCuentaBiess implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "CODTIPCUE")
	private String codigo;

	@Column(name = "DESTIPCUE")
	private String descripcion;

	/**
	 * Constructor.
	 */
	public TipoCuentaBiess() {
		super();
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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