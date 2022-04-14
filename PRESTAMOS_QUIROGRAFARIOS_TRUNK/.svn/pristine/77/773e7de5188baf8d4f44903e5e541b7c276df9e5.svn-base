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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * Entity WSR_BITACORA_ACCESOS_TBL.
 *  
 * @version $Revision: 1.2 $
 * @author Chan - Cobiscorp
 */

@Entity
@Table(name = "WSR_BITACORA_ACCESOS_TBL")
@SequenceGenerator(name = "seq_id", sequenceName = "WSR_BITACORA_SEQ")
public class BitacoraSeguridad implements Serializable {

    private static final long serialVersionUID = 5588825773668474628L;

    @Id
    @Column(name = "BA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    private Long id;

    @Column(name = "BA_RUC")
    private String ruc;

    @Column(name = "BA_IP_NUMERO")
    private String ipNumero;

    @Column(name = "BA_WS_CODIGO")
    private String webserviceCodigo;

    @Column(name = "BA_MT_CODIGO")
    private String metodoCodigo;

    @Column(name = "BA_FECHA")
    private Date fechaAcceso;

    @Column(name = "BA_OBSERVACION")
    private String observacion;

    @Column(name = "BA_TRAMA")
    private String trama;

    @Column(name = "BA_ALIAS")
    private String alias;

    public BitacoraSeguridad() {
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
     * @return the webserviceCodigo
     */
    public String getWebserviceCodigo() {
	return webserviceCodigo;
    }

    /**
     * @param webserviceCodigo the webserviceCodigo to set
     */
    public void setWebserviceCodigo(String webserviceCodigo) {
	this.webserviceCodigo = webserviceCodigo;
    }

    /**
     * @return the metodoCodigo
     */
    public String getMetodoCodigo() {
	return metodoCodigo;
    }

    /**
     * @param metodoCodigo the metodoCodigo to set
     */
    public void setMetodoCodigo(String metodoCodigo) {
	this.metodoCodigo = metodoCodigo;
    }

    /**
     * @return the fechaAcceso
     */
    public Date getFechaAcceso() {
	return fechaAcceso;
    }

    /**
     * @param fechaAcceso the fechaAcceso to set
     */
    public void setFechaAcceso(Date fechaAcceso) {
	this.fechaAcceso = fechaAcceso;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
	return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
	this.observacion = observacion;
    }

    /**
     * @return the trama
     */
    public String getTrama() {
	return trama;
    }

    /**
     * @param trama the trama to set
     */
    public void setTrama(String trama) {
	this.trama = trama;
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

}
