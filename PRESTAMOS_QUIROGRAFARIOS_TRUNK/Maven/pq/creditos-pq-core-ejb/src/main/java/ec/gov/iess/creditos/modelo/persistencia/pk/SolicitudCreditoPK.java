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

import javax.persistence.Embeddable;

/**
 * 
 * datos de la solicitud de cerdito
 * 
 * @version 1.0
 * @author cvillarreal
 *
 */
@SuppressWarnings("serial")
@Embeddable
public class SolicitudCreditoPK implements Serializable {

    private Long codtipsolser;
    private Long numsolser;
    
    public SolicitudCreditoPK(){
    	
    }
    
    public SolicitudCreditoPK(Long codtipsolser, Long numsolser) {
        this.codtipsolser = codtipsolser;
        this.numsolser = numsolser;
    }

    public boolean equals(Object other) {
        if (other instanceof SolicitudCreditoPK) {
            final SolicitudCreditoPK otherKscretsolicitudesPK = (SolicitudCreditoPK) other;
            final boolean areEqual = 
                (otherKscretsolicitudesPK.codtipsolser.equals(codtipsolser) && otherKscretsolicitudesPK.numsolser.equals(numsolser));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

	/**
	 * @return the codtipsolser
	 */
	public Long getCodtipsolser() {
		return codtipsolser;
	}

	/**
	 * @return the numsolser
	 */
	public Long getNumsolser() {
		return numsolser;
	}

	/**
	 * @param codtipsolser the codtipsolser to set
	 */
	public void setCodtipsolser(Long codtipsolser) {
		this.codtipsolser = codtipsolser;
	}

	/**
	 * @param numsolser the numsolser to set
	 */
	public void setNumsolser(Long numsolser) {
		this.numsolser = numsolser;
	}
	
}
