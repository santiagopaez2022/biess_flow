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
 * @version $Revision: 1.1 $  [Sep 19, 2007]
 * @author pablo
 */
@Embeddable
public class LiquidacionPrestamoDetallePk implements Serializable {

	private static final long serialVersionUID = 1987183320570534076L;

	@Column(name = "NUMLIN", nullable = false)
	private Long numLin;

	@Column(name = "NUMLIQPRE", nullable = false)
	private Long numLiqPre;

	/**
	 * Returns the value of numLin.
	 * @return numLin
	 */
	public Long getNumLin() {
		return numLin;
	}

	/**
	 * Sets the value for numLin.
	 * @param numLin
	 */
	public void setNumLin(Long numLin) {
		this.numLin = numLin;
	}

	/**
	 * Returns the value of numLiqPre.
	 * @return numLiqPre
	 */
	public Long getNumLiqPre() {
		return numLiqPre;
	}

	/**
	 * Sets the value for numLiqPre.
	 * @param numLiqPre
	 */
	public void setNumLiqPre(Long numLiqPre) {
		this.numLiqPre = numLiqPre;
	}

}