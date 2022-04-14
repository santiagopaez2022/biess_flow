/* 
* Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
* Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
* file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
* by applicable law or agreed to in writing, software distributed under the License is 
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
* express or implied. See the License for the specific language governing permissions 
* and limitations under the License.
*/

package ec.gov.iess.seguridades.ws.model.dto;

/**
 * 
 * @version $Revision: 1.2 $
 * @author Chan - Cobiscorp
 */

public class RespuestaDTO {

    private boolean tienePermiso;

    private String mensaje;

    /**
     * @return the tienePermiso
     */
    public boolean isTienePermiso() {
	return tienePermiso;
    }

    /**
     * @param tienePermiso the tienePermiso to set
     */
    public void setTienePermiso(boolean tienePermiso) {
	this.tienePermiso = tienePermiso;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
	return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
    }

}
