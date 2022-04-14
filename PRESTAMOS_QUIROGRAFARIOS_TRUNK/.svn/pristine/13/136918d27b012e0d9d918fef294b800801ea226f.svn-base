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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

/**
 * 
 * Entity WSR_IP_TBL.
 *  
 * @version $Revision: 1.2 $
 * @author Chan - Cobiscorp
 */

@Entity
@Table(name = "WSR_IP_TBL")
@NamedQueries( { @NamedQuery(name = "IpSeguridad.findByIp", query = "SELECT o FROM IpSeguridad o WHERE o.ipNumero =:ip and o.estado ='ACT'", hints = {
	@QueryHint(name = "org.hibernate.cacheable", value = "true"),
	@QueryHint(name = "org.hibernate.cacheRegion", value = "query.IpSeguridadBusqueda") }) })
public class IpSeguridad implements Serializable {
    private static final long serialVersionUID = -45654373030880768L;

    @Id
    @Column(name = "IP_ID")
    private Long id;

    @Column(name = "IP_NUMERO", unique = true)
    private String ipNumero;

    @Column(name = "IP_DESCRIPCION")
    private String descripcion;

    @Column(name = "IP_ESTADO")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CL_ID")
    private ClienteSeguridad clienteSeguridad;

    public IpSeguridad() {
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the ipNumero
     */
    public String getIpNumero() {
	return ipNumero;
    }

    /**
     * @param ipNumero the ipNumero to set
     */
    public void setIpNumero(String ipNumero) {
	this.ipNumero = ipNumero;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
	return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
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
     * @return the clienteSeguridad
     */
    public ClienteSeguridad getClienteSeguridad() {
	return clienteSeguridad;
    }

    /**
     * @param clienteSeguridad the clienteSeguridad to set
     */
    public void setClienteSeguridad(ClienteSeguridad clienteSeguridad) {
	this.clienteSeguridad = clienteSeguridad;
    }

}
