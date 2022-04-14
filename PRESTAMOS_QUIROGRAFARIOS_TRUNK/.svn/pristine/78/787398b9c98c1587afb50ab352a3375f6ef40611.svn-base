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
import javax.persistence.Embeddable;

/**
 * PK PrestamoBiess.
 * 
 * @author Omar Villanueva
 * @version 1.0
 *
 */
@Embeddable
public class PrestamoBiessPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CODPRECLA", nullable = false)
	private Long codprecla;

	@Column(name = "CODPRETIP", nullable = false)
	private Long codpretip;

	@Column(name = "NUMPREAFI", nullable = false)
	private Long numpreafi;

	@Column(name = "ORDPREAFI", nullable = false)
	private Long ordpreafi;

	public PrestamoBiessPK() {
	}

	public PrestamoBiessPK(Long codprecla, Long codpretip, Long numpreafi,
			Long ordpreafi) {
		this.codprecla = codprecla;
		this.codpretip = codpretip;
		this.numpreafi = numpreafi;
		this.ordpreafi = ordpreafi;
	}

	/**
	 * Comparador de objetos
	 */
	public boolean equals(Object other) {
		if (other instanceof PrestamoBiessPK) {
			final PrestamoBiessPK otherKscretcreditosPK = (PrestamoBiessPK) other;
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
