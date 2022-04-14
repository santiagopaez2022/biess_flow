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
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Entity WSR_CLIENTES_TBL.
 *  
 * @version $Revision: 1.2 $
 * @author chan
 */

@Entity
@Table(name = "WSR_CLIENTES_TBL")
public class ClienteSeguridad implements Serializable {

    private static final long serialVersionUID = 1406308661016189170L;

    @Id
    @Column(name = "CL_ID")
    private Long id;

    @Column(name = "CL_RUC", unique = true)
    private String ruc;

    @Column(name = "CL_ALIAS")
    private String alias;

    @Column(name = "CL_DIRECCION")
    private String direccion;

    @Column(name = "CL_TELEFONO")
    private String telefono;

    @Column(name = "CL_CORREO_ELECTRONICO")
    private String correoElectronico;

    @Column(name = "CL_ESTADO")
    private String estado;

    public ClienteSeguridad() {
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
     * @return the ruc
     */
    public String getRuc() {
	return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
	this.ruc = ruc;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
	return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
	this.alias = alias;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
	return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
	this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
	return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
	return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
	this.correoElectronico = correoElectronico;
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

}
