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
import javax.persistence.Table;

/**
 * 
 * Entity WSR_METODOS_TBL.
 *  
 * @version $Revision: 1.2 $
 * @author Chan - Cobiscorp
 */

@Entity
@Table(name = "WSR_METODOS_TBL")
public class MetodoSeguridad implements Serializable {

    private static final long serialVersionUID = -8550563786885835009L;

    @Id
    @Column(name = "MT_ID")
    private Long id;

    @Column(name = "MT_CODIGO")
    private String codigo;

    @Column(name = "MT_NOMBRE")
    private String nombre;

    @Column(name = "MT_DESCRIPCION")
    private String descripcion;

    @Column(name = "MT_ESTADO")
    private String estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WS_ID")
    private WebServiceSeguridad webserviceSeguridad;

    public MetodoSeguridad() {
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
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the webserviceSeguridad
     */
    public WebServiceSeguridad getWebserviceSeguridad() {
        return webserviceSeguridad;
    }

    /**
     * @param webserviceSeguridad the webserviceSeguridad to set
     */
    public void setWebserviceSeguridad(WebServiceSeguridad webserviceSeguridad) {
        this.webserviceSeguridad = webserviceSeguridad;
    }
    
}
