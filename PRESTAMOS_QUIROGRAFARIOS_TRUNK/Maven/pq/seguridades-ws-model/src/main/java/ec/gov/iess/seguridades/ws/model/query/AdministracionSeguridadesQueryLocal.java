/* 
* Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
* Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
* file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
* by applicable law or agreed to in writing, software distributed under the License is 
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
* express or implied. See the License for the specific language governing permissions 
* and limitations under the License.
*/

package ec.gov.iess.seguridades.ws.model.query;

import javax.ejb.Local;

import ec.gov.iess.seguridades.ws.model.entity.AccesoSeguridad;
import ec.gov.iess.seguridades.ws.model.entity.BitacoraSeguridad;
import ec.gov.iess.seguridades.ws.model.entity.IpSeguridad;
import ec.gov.iess.seguridades.ws.model.excepcion.EntidadException;
import ec.gov.iess.seguridades.ws.model.excepcion.EntidadExisteException;
import ec.gov.iess.seguridades.ws.model.excepcion.MetodoServicioDuplicadoException;

/**
 * Acceso a datos de la logica de seguridades WS.
 *  
 * @version $Revision: 1.2 $
 * @author Chan - Cobiscorp
 */
@Local
public interface AdministracionSeguridadesQueryLocal {

    /**
     * Metodo que devuelve la entidad accesoseguridad
     * @param ip
     * @param metodoCodigo
     * @param serviciowebCodigo
     * @return
     * @throws MetodoServicioDuplicadoException
     */
    AccesoSeguridad findAccesoSeguridadByIpMetodoServicio(String ip, String metodoCodigo, String serviciowebCodigo) throws MetodoServicioDuplicadoException;

    /**
     * Metodo para guardar la bitacora
     * @param bitacoraSeguridad
     * @throws EntidadExisteException
     * @throws EntidadException
     */
    void guardarBitacora(BitacoraSeguridad bitacoraSeguridad) throws EntidadExisteException, EntidadException;

    /**
     * Metodo que devuelve la entidad ipseguridad
     * @param ip
     * @return
     */
    IpSeguridad findIpSeguridadByIp(String ip);

}
