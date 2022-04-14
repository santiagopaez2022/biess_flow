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
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * Clase de persistencia de modelo
 * 
 * @version 1.0
 * @author cvillarreal
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="Kscretdivpretip")
@NamedQueries({
	@NamedQuery(name = "Kscretdivpretip.consultarTodo", 
		    query = "select o from TipoDividendo o")
})
public class TipoDividendo implements Serializable {

    @Id
    @Column(nullable = false)
    private String codtipdiv;
    @Column(nullable = false)
    private String destipdiv;
	
	public TipoDividendo() {
	}

	/**
	 * @return the codtipdiv
	 */
	public String getCodtipdiv() {
		return codtipdiv;
	}

	/**
	 * @param codtipdiv the codtipdiv to set
	 */
	public void setCodtipdiv(String codtipdiv) {
		this.codtipdiv = codtipdiv;
	}

	/**
	 * @return the destipdiv
	 */
	public String getDestipdiv() {
		return destipdiv;
	}

	/**
	 * @param destipdiv the destipdiv to set
	 */
	public void setDestipdiv(String destipdiv) {
		this.destipdiv = destipdiv;
	}

}
