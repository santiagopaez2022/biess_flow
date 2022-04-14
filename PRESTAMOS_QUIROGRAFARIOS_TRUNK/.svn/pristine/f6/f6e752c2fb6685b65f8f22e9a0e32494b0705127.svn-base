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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.VariablePrestamoPK;

/**
 * @author cvillarreal
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="Kscretprevar")
@NamedQueries({
	@NamedQuery(name = "VariablePrestamo.consultarTodos", 
		    query = "select o from VariablePrestamo o")
})
public class VariablePrestamo implements Serializable {

	@EmbeddedId
	private VariablePrestamoPK variablePrestamoPK;
	
    @Column(nullable = false)
    private String indhabvarpre;
    @Column(nullable = false)
    private Long secvarpre;

	
	/**
	 * 
	 */
	public VariablePrestamo() {
		
	}


	/**
	 * @return the variablePrestamoPK
	 */
	public VariablePrestamoPK getVariablePrestamoPK() {
		return variablePrestamoPK;
	}


	/**
	 * @param variablePrestamoPK the variablePrestamoPK to set
	 */
	public void setVariablePrestamoPK(VariablePrestamoPK variablePrestamoPK) {
		this.variablePrestamoPK = variablePrestamoPK;
	}


	/**
	 * @return the indhabvarpre
	 */
	public String getIndhabvarpre() {
		return indhabvarpre;
	}


	/**
	 * @param indhabvarpre the indhabvarpre to set
	 */
	public void setIndhabvarpre(String indhabvarpre) {
		this.indhabvarpre = indhabvarpre;
	}


	/**
	 * @return the secvarpre
	 */
	public Long getSecvarpre() {
		return secvarpre;
	}


	/**
	 * @param secvarpre the secvarpre to set
	 */
	public void setSecvarpre(Long secvarpre) {
		this.secvarpre = secvarpre;
	}

}
