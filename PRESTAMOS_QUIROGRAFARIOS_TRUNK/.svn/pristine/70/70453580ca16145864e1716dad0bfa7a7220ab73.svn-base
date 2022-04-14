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

import ec.gov.iess.seguridades.ws.model.entity.BitacoraSeguridad;
import ec.gov.iess.seguridades.ws.model.excepcion.EntidadException;
import ec.gov.iess.seguridades.ws.model.excepcion.EntidadExisteException;

/**
 * Interface para la Bitacora
 * @author Chan - Cobiscorp
 *
 * @revision $Revision: 1.2 $
 */
@Local
public interface BitacoraServicioLocal {

    /**
     * Metodo para guardar la bitacora
     * @param bitacora
     * @throws EntidadExisteException
     * @throws EntidadException
     */
    void guardarBitacora(BitacoraSeguridad bitacora) throws EntidadExisteException, EntidadException;
}
