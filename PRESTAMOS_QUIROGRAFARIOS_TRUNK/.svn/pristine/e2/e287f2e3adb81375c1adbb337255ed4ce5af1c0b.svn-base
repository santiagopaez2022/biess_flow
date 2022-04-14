/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clave primaria para persistencia de beneficiarios de creditos quirografarios.
 * 
 * @author diego.iza.
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
@Embeddable
public class BeneficiarioCreditoPK implements Serializable {

	@Column(name = "BC_CODPRECLA", nullable = false)
	private Long codprecla;

	@Column(name = "BC_CODPRETIP", nullable = false)
	private Long codpretip;

	@Column(name = "BC_NUMPREAFI", nullable = false)
	private Long numpreafi;

	@Column(name = "BC_ORDPREAFI", nullable = false)
	private Long ordpreafi;

	/**
	 * Constructor.
	 */
	public BeneficiarioCreditoPK() {

	}

	/**
	 * Constructor.
	 * 
	 * @param codprecla
	 * @param codpretip
	 * @param numpreafi
	 * @param ordpreafi
	 */
	public BeneficiarioCreditoPK(Long codprecla, Long codpretip,
			Long numpreafi, Long ordpreafi) {
		this.codprecla = codprecla;
		this.codpretip = codpretip;
		this.numpreafi = numpreafi;
		this.ordpreafi = ordpreafi;
	}

	/**
	 * Comparador de objetos
	 */
	public boolean equals(Object other) {
		if (other instanceof BeneficiarioCreditoPK) {
			final BeneficiarioCreditoPK otherKscretcreditosPK = (BeneficiarioCreditoPK) other;
			final boolean areEqual = (otherKscretcreditosPK.codprecla
					.equals(codprecla)
					&& otherKscretcreditosPK.codpretip.equals(codpretip)
					&& otherKscretcreditosPK.numpreafi.equals(numpreafi) && otherKscretcreditosPK.ordpreafi
					.equals(ordpreafi));
			return areEqual;
		}
		return false;
	}

	/**
	 * hascode
	 */
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * @return the codprecla
	 */
	public Long getCodprecla() {
		return codprecla;
	}

	/**
	 * @param codprecla
	 *            the codprecla to set
	 */
	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	/**
	 * @return the codpretip
	 */
	public Long getCodpretip() {
		return codpretip;
	}

	/**
	 * @param codpretip
	 *            the codpretip to set
	 */
	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	/**
	 * @return the numpreafi
	 */
	public Long getNumpreafi() {
		return numpreafi;
	}

	/**
	 * @param numpreafi
	 *            the numpreafi to set
	 */
	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	/**
	 * @return the ordpreafi
	 */
	public Long getOrdpreafi() {
		return ordpreafi;
	}

	/**
	 * @param ordpreafi
	 *            the ordpreafi to set
	 */
	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

}
