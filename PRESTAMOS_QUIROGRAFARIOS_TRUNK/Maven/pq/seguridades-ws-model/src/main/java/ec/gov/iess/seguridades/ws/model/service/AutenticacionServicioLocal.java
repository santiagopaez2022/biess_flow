/* 
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.seguridades.ws.model.service;

import javax.ejb.Local;

import ec.gov.iess.seguridades.ws.model.dto.RespuestaDTO;
import ec.gov.iess.seguridades.ws.model.util.ParametroWrapper;

/**
 * Interface para el servicio de autenticacion
 * @author Chan - Cobiscorp
 *
 * @revision $Revision: 1.2 $
 */
@Local
public interface AutenticacionServicioLocal {

    /**
     * Metodo que indica si un ip tiene permisos para acceder al metodo de determinado webservice
     * @param parametros
     * @return
     */
    RespuestaDTO tienePermisos(ParametroWrapper parametros);
}
