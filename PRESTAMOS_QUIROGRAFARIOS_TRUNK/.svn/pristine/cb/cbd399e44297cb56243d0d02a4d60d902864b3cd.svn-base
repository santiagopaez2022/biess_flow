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

import javax.persistence.Embeddable;

/**
 * 
 * Tabla de resumen de finalizacion de la concesion
 * 
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
@Embeddable
public class ResumenFinPrestamoPK implements Serializable {

	private String codconfin;
	private Long codprecla;
	private Long codpretip;
	private String codunimedconfin;
	private Long numpreafi;
	private Long ordpreafi;

	/**
	 * 
	 */
	public ResumenFinPrestamoPK() {
	}

	public ResumenFinPrestamoPK(String codconfin, Long codprecla,
			Long codpretip, String codunimedconfin, Long numpreafi,
			Long ordpreafi) {
		this.codconfin = codconfin;
		this.codprecla = codprecla;
		this.codpretip = codpretip;
		this.codunimedconfin = codunimedconfin;
		this.numpreafi = numpreafi;
		this.ordpreafi = ordpreafi;
	}

	public boolean equals(Object other) {
		if (other instanceof ResumenFinPrestamoPK) {
			final ResumenFinPrestamoPK otherKscretpreconfinPK = (ResumenFinPrestamoPK) other;
			final boolean areEqual = (otherKscretpreconfinPK.codconfin
					.equals(codconfin)
					&& otherKscretpreconfinPK.codprecla.equals(codprecla)
					&& otherKscretpreconfinPK.codpretip.equals(codpretip)
					&& otherKscretpreconfinPK.codunimedconfin
							.equals(codunimedconfin)
					&& otherKscretpreconfinPK.numpreafi.equals(numpreafi) && otherKscretpreconfinPK.ordpreafi
					.equals(ordpreafi));
			return areEqual;
		}
		return false;
	}

	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * @return the codconfin
	 */
	public String getCodconfin() {
		return codconfin;
	}

	/**
	 * @return the codprecla
	 */
	public Long getCodprecla() {
		return codprecla;
	}

	/**
	 * @return the codpretip
	 */
	public Long getCodpretip() {
		return codpretip;
	}

	/**
	 * @return the codunimedconfin
	 */
	public String getCodunimedconfin() {
		return codunimedconfin;
	}

	/**
	 * @return the numpreafi
	 */
	public Long getNumpreafi() {
		return numpreafi;
	}

	/**
	 * @return the ordpreafi
	 */
	public Long getOrdpreafi() {
		return ordpreafi;
	}

	/**
	 * @param codconfin
	 *            the codconfin to set
	 */
	public void setCodconfin(String codconfin) {
		this.codconfin = codconfin;
	}

	/**
	 * @param codprecla
	 *            the codprecla to set
	 */
	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	/**
	 * @param codpretip
	 *            the codpretip to set
	 */
	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	/**
	 * @param codunimedconfin
	 *            the codunimedconfin to set
	 */
	public void setCodunimedconfin(String codunimedconfin) {
		this.codunimedconfin = codunimedconfin;
	}

	/**
	 * @param numpreafi
	 *            the numpreafi to set
	 */
	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	/**
	 * @param ordpreafi
	 *            the ordpreafi to set
	 */
	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

}
