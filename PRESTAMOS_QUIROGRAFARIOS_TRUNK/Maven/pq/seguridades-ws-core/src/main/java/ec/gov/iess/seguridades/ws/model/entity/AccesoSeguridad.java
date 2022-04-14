/* 
* Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
* Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
* file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
* by applicable law or agreed to in writing, software distributed under the License is 
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
* express or implied. See the License for the specific language governing permissions 
* and limitations under the License.
*/

package ec.gov.iess.seguridades.ws.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import ec.gov.iess.seguridades.ws.model.entity.pk.AccesoSeguridadPK;

/**
 * 
 * Entity WSR_ACCESOS_TBL.
 *  
 * @version $Revision: 1.2 $
 * @author Chan - Cobiscorp
 */

@Entity
@Table(name = "WSR_ACCESOS_TBL")
@NamedQueries( { @NamedQuery(name = "AccesoSeguridad.findByIpMetodoWebService", query = "SELECT o FROM AccesoSeguridad o WHERE o.ipSeguridad.ipNumero =:ip and o.metodoSeguridad.codigo =:metodoCodigo and o.metodoSeguridad.webserviceSeguridad.codigo =:serviciowebCodigo", hints = { @QueryHint(name = "org.hibernate.cacheable", value = "true"),@QueryHint(name = "org.hibernate.cacheRegion", value = "query.AccesoSeguridadBusqueda")  }) })
public class AccesoSeguridad implements Serializable {

    private static final long serialVersionUID = 6624062556240697791L;

    @EmbeddedId
    private AccesoSeguridadPK accesoSeguridadPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IP_ID", insertable = false, updatable = false)
    private IpSeguridad ipSeguridad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MT_ID", insertable = false, updatable = false)
    private MetodoSeguridad metodoSeguridad;

    @Column(name = "AC_ESTADO")
    private String estado;

    public AccesoSeguridad() {
    }

    /**
     * @return the estado
     */
    public String getEstado() {
	return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
	this.estado = estado;
    }

    /**
     * @return the ipSeguridad
     */
    public IpSeguridad getIpSeguridad() {
	return ipSeguridad;
    }

    /**
     * @param ipSeguridad the ipSeguridad to set
     */
    public void setIpSeguridad(IpSeguridad ipSeguridad) {
	this.ipSeguridad = ipSeguridad;
    }

    /**
     * @return the metodoSeguridad
     */
    public MetodoSeguridad getMetodoSeguridad() {
	return metodoSeguridad;
    }

    /**
     * @param metodoSeguridad the metodoSeguridad to set
     */
    public void setMetodoSeguridad(MetodoSeguridad metodoSeguridad) {
	this.metodoSeguridad = metodoSeguridad;
    }

    /**
     * @return the accesoSeguridadPK
     */
    public AccesoSeguridadPK getAccesoSeguridadPK() {
	return accesoSeguridadPK;
    }

    /**
     * @param accesoSeguridadPK the accesoSeguridadPK to set
     */
    public void setAccesoSeguridadPK(AccesoSeguridadPK accesoSeguridadPK) {
	this.accesoSeguridadPK = accesoSeguridadPK;
    }

}
