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
 * @version $Revision: 1.1.2.1 $  [Nov 1, 2007]
 * @author pablo
 */
@Embeddable
public class ParametroValorPk implements Serializable {

	private static final long serialVersionUID = -2999403235149923481L;

	@Column(name = "CODCONFIN", nullable = false)
	private String codConFin;

	@Column(name = "CODPRECLA", nullable = false)
	private Integer codPreCla;

	@Column(name = "CODPRETIP", nullable = false)
	private Integer codPreTip;

	@Column(name = "CODUNIMEDCONFIN", nullable = false)
	private String codUniMedConFin;

	
	/**
	 * Returns the value of codConFin.
	 * @return codConFin
	 */
	public String getCodConFin() {
		return codConFin;
	}

	
	/**
	 * Sets the value for codConFin.
	 * @param codConFin
	 */
	public void setCodConFin(String codConFin) {
		this.codConFin = codConFin;
	}

	
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
	 * Returns the value of codPreTip.
	 * @return codPreTip
	 */
	public Integer getCodPreTip() {
		return codPreTip;
	}

	
	/**
	 * Sets the value for codPreTip.
	 * @param codPreTip
	 */
	public void setCodPreTip(Integer codPreTip) {
		this.codPreTip = codPreTip;
	}

	
	/**
	 * Returns the value of codUniMedConFin.
	 * @return codUniMedConFin
	 */
	public String getCodUniMedConFin() {
		return codUniMedConFin;
	}

	
	/**
	 * Sets the value for codUniMedConFin.
	 * @param codUniMedConFin
	 */
	public void setCodUniMedConFin(String codUniMedConFin) {
		this.codUniMedConFin = codUniMedConFin;
	}

}