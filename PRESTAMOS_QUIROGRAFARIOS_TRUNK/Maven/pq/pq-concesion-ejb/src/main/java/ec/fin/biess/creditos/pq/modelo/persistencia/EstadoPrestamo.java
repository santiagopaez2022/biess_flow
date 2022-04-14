/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean para la tabla Kscretpreest.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
@Entity
@Table(name = "Kscretpreest")
public class EstadoPrestamo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	private String codestpre;

	@Column(nullable = false)
	private String desestpre;

	@Column(nullable = false)
	private String indhabestpre;

	@Column(nullable = false)
	private String obtestpre;

	public EstadoPrestamo() {

	}

	/**
	 * @return the codestpre
	 */
	public String getCodestpre() {
		return codestpre;
	}

	/**
	 * @param codestpre
	 *            the codestpre to set
	 */
	public void setCodestpre(String codestpre) {
		this.codestpre = codestpre;
	}

	/**
	 * @return the desestpre
	 */
	public String getDesestpre() {
		return desestpre;
	}

	/**
	 * @param desestpre
	 *            the desestpre to set
	 */
	public void setDesestpre(String desestpre) {
		this.desestpre = desestpre;
	}

	/**
	 * @return the indhabestpre
	 */
	public String getIndhabestpre() {
		return indhabestpre;
	}

	/**
	 * @param indhabestpre
	 *            the indhabestpre to set
	 */
	public void setIndhabestpre(String indhabestpre) {
		this.indhabestpre = indhabestpre;
	}

	/**
	 * @param obtestpre
	 *            the obtestpre to set
	 */
	public void setObtestpre(String obtestpre) {
		this.obtestpre = obtestpre;
	}

	/**
	 * @return the obtestpre
	 */
	public String getObtestpre() {
		return obtestpre;
	}

}
