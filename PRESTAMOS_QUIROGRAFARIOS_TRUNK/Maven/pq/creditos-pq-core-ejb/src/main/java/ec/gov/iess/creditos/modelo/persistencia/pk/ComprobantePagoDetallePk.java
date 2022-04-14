/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
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
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 24, 2007]
 * @author pablo
 */
@Embeddable
public class ComprobantePagoDetallePk implements Serializable {

	private static final long serialVersionUID = -4582813578110239478L;

	@Column(name = "NUMDET", nullable = false)
	private Long numDet;

	@Column(name = "CODTIPCOMPAG", nullable = false)
	private String codTipComPag;

	@Column(name = "CODCOMPAGAFI", nullable = false)
	private String codComPagAfi;

	/**
	 * Returns the value of numDet.
	 * @return numDet
	 */
	public Long getNumDet() {
		return numDet;
	}

	/**
	 * Sets the value for numDet.
	 * @param numDet
	 */
	public void setNumDet(Long numDet) {
		this.numDet = numDet;
	}

	/**
	 * Returns the value of codTipComPag.
	 * @return codTipComPag
	 */
	public String getCodTipComPag() {
		return codTipComPag;
	}

	/**
	 * Sets the value for codTipComPag.
	 * @param codTipComPag
	 */
	public void setCodTipComPag(String codTipComPag) {
		this.codTipComPag = codTipComPag;
	}

	/**
	 * Returns the value of codComPagAfi.
	 * @return codComPagAfi
	 */
	public String getCodComPagAfi() {
		return codComPagAfi;
	}

	/**
	 * Sets the value for codComPagAfi.
	 * @param codComPagAfi
	 */
	public void setCodComPagAfi(String codComPagAfi) {
		this.codComPagAfi = codComPagAfi;
	}
}