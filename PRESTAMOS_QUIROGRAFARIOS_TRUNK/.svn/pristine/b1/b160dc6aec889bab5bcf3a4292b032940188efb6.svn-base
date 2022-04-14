/* 
* Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
* Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
* file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
* by applicable law or agreed to in writing, software distributed under the License is 
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
* express or implied. See the License for the specific language governing permissions 
* and limitations under the License.
*/

package ec.gov.iess.seguridades.ws.model.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * Entity PK de KSRECTRECBANCENDET.
 *  
 * @version $Revision: 1.2 $
 * @author Chan - Cobiscorp
 */

@Embeddable
public class AccesoSeguridadPK implements Serializable {
    private static final long serialVersionUID = -5794747679401759828L;

    @Column(name="IP_ID", nullable = false)
    private Long ipId;

    @Column(name="MT_ID", nullable = false)
    private Long metodoId;

    public AccesoSeguridadPK() {
    }

    public boolean equals(Object other) {
	if (other instanceof AccesoSeguridadPK) {
	    final AccesoSeguridadPK accesoSeguridadPK = (AccesoSeguridadPK) other;
	    final boolean areEqual = (accesoSeguridadPK.ipId.equals(ipId)
		    && accesoSeguridadPK.metodoId.equals(metodoId));
	    return areEqual;
	}
	return false;
    }

    public int hashCode() {
	return super.hashCode();
    }

    /**
     * @return the ipId
     */
    public Long getIpId() {
        return ipId;
    }

    /**
     * @param ipId the ipId to set
     */
    public void setIpId(Long ipId) {
        this.ipId = ipId;
    }

    /**
     * @return the metodoId
     */
    public Long getMetodoId() {
        return metodoId;
    }

    /**
     * @param metodoId the metodoId to set
     */
    public void setMetodoId(Long metodoId) {
        this.metodoId = metodoId;
    }

}
