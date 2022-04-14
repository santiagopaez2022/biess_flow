/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase de modelo que representa la clave primaria.
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */

@SuppressWarnings("serial")
@Embeddable
public class MigracionPrestamoHostPK implements Serializable {

	@Column(nullable = false)
	private String cedideusu;

	@Column(nullable = false)
	private String clapre;

	@Column(nullable = false)
	private String inipre;

	@Column(nullable = false)
	private String oridat;

	public MigracionPrestamoHostPK() {

	}

	public MigracionPrestamoHostPK(String cedideusu, String clapre,
			String inipre, String oridat) {
		this.cedideusu = cedideusu;
		this.clapre = clapre;
		this.inipre = inipre;
		this.oridat = oridat;
	}

	public boolean equals(Object other) {
		if (other instanceof MigracionPrestamoHostPK) {
			final MigracionPrestamoHostPK otherKsmigtquirosPK = (MigracionPrestamoHostPK) other;
			final boolean areEqual = (otherKsmigtquirosPK.cedideusu
					.equals(cedideusu)
					&& otherKsmigtquirosPK.clapre.equals(clapre)
					&& otherKsmigtquirosPK.inipre.equals(inipre) && otherKsmigtquirosPK.oridat
					.equals(oridat));
			return areEqual;
		}
		return false;
	}

	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * @return the cedideusu
	 */
	public String getCedideusu() {
		return cedideusu;
	}

	/**
	 * @param cedideusu the cedideusu to set
	 */
	public void setCedideusu(String cedideusu) {
		this.cedideusu = cedideusu;
	}

	/**
	 * @return the clapre
	 */
	public String getClapre() {
		return clapre;
	}

	/**
	 * @param clapre the clapre to set
	 */
	public void setClapre(String clapre) {
		this.clapre = clapre;
	}

	/**
	 * @return the inipre
	 */
	public String getInipre() {
		return inipre;
	}

	/**
	 * @param inipre the inipre to set
	 */
	public void setInipre(String inipre) {
		this.inipre = inipre;
	}

	/**
	 * @return the oridat
	 */
	public String getOridat() {
		return oridat;
	}

	/**
	 * @param oridat the oridat to set
	 */
	public void setOridat(String oridat) {
		this.oridat = oridat;
	}

}
