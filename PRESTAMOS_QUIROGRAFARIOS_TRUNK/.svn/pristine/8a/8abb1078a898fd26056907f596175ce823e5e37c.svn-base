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
public class ComprobantePagoPk implements Serializable {

	private static final long serialVersionUID = 5418343601338304683L;

	public ComprobantePagoPk(){
		
	}
	
	@Column(name = "CODCOMPAGAFI", nullable=false)
	private String codComPagAfi;

	@Column(name = "CODTIPCOMPAG", nullable=false)
	private String codTipComPag;

	
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
	

}