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
 * @author cvillarreal
 *
 */
@SuppressWarnings("serial")
@Embeddable
public class VariablePrestamoPK implements Serializable {

	@Column(nullable=false)
    private Long codprecla;
	
	@Column(nullable=false)
    private Long codpretip;

	
	/**
	 * 
	 */
	public VariablePrestamoPK() {
		
	}

    public boolean equals(Object other) {
        if (other instanceof VariablePrestamoPK) {
            final VariablePrestamoPK otherKscretprevarPK = (VariablePrestamoPK) other;
            final boolean areEqual = 
                (otherKscretprevarPK.codprecla.equals(codprecla) && otherKscretprevarPK.codpretip.equals(codpretip));
            return areEqual;
        }
        return false;
    }

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
	 * @param codprecla the codprecla to set
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
	 * @param codpretip the codpretip to set
	 */
	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

}
