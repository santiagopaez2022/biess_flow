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
 * 
 * Clase de persistencia de modelo PK de dividendo prestamo
 * 
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@Embeddable
public class DividendoPrestamoPk implements Serializable {

	private static final long serialVersionUID = 1626682501045956153L;

	@Column(name="NUMDIV",nullable = false)
	private Long numdiv;
	
	@Column(name="CODPRETIP",nullable = false)	
	private Long codpretip;
	
	@Column(name="ORDPREAFI",nullable = false)
	private Long ordpreafi;
	
	@Column(name="NUMPREAFI",nullable = false)
	private Long numpreafi;
	
	@Column(name="CODPRECLA",nullable = false)
	private Long codprecla;
	
	public DividendoPrestamoPk() {
	}

	public DividendoPrestamoPk(Long codprecla, Long codpretip, Long numdiv,
			Long numpreafi, Long ordpreafi) {
		this.codprecla = codprecla;
		this.codpretip = codpretip;
		this.numdiv = numdiv;
		this.numpreafi = numpreafi;
		this.ordpreafi = ordpreafi;
	}

	public boolean equals(Object other) {
		if (other instanceof DividendoPrestamoPk) {
			final DividendoPrestamoPk otherKscretdividendosPK = (DividendoPrestamoPk) other;
			final boolean areEqual = (otherKscretdividendosPK.codprecla
					.equals(codprecla)
					&& otherKscretdividendosPK.codpretip.equals(codpretip)
					&& otherKscretdividendosPK.numdiv.equals(numdiv)
					&& otherKscretdividendosPK.numpreafi.equals(numpreafi) && otherKscretdividendosPK.ordpreafi
					.equals(ordpreafi));
			return areEqual;
		}
		return false;
	}

	public int hashCode() {
		return super.hashCode();
	}

	
	/**
	 * Returns the value of numdiv.
	 * @return numdiv
	 */
	public Long getNumdiv() {
		return numdiv;
	}

	
	/**
	 * Sets the value for numdiv.
	 * @param numdiv
	 */
	public void setNumdiv(Long numdiv) {
		this.numdiv = numdiv;
	}

	
	/**
	 * Returns the value of codpretip.
	 * @return codpretip
	 */
	public Long getCodpretip() {
		return codpretip;
	}

	
	/**
	 * Sets the value for codpretip.
	 * @param codpretip
	 */
	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	
	/**
	 * Returns the value of ordpreafi.
	 * @return ordpreafi
	 */
	public Long getOrdpreafi() {
		return ordpreafi;
	}

	
	/**
	 * Sets the value for ordpreafi.
	 * @param ordpreafi
	 */
	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

	
	/**
	 * Returns the value of numpreafi.
	 * @return numpreafi
	 */
	public Long getNumpreafi() {
		return numpreafi;
	}

	
	/**
	 * Sets the value for numpreafi.
	 * @param numpreafi
	 */
	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	
	/**
	 * Returns the value of codprecla.
	 * @return codprecla
	 */
	public Long getCodprecla() {
		return codprecla;
	}

	
	/**
	 * Sets the value for codprecla.
	 * @param codprecla
	 */
	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

}
