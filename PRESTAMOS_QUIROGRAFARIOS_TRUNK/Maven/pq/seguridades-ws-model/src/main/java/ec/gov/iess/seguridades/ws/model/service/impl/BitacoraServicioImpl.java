/* 
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.seguridades.ws.model.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.seguridades.ws.model.entity.BitacoraSeguridad;
import ec.gov.iess.seguridades.ws.model.excepcion.EntidadException;
import ec.gov.iess.seguridades.ws.model.excepcion.EntidadExisteException;
import ec.gov.iess.seguridades.ws.model.query.AdministracionSeguridadesQueryLocal;
import ec.gov.iess.seguridades.ws.model.service.BitacoraServicioLocal;
import ec.gov.iess.seguridades.ws.model.service.BitacoraServicioRemote;

/**
 * Implementacion de procesos de consulta de planillas.
 * 
 * @version $Revision: 1.2 $
 * @author Chan - Cobiscorp
 */
@Stateless
public class BitacoraServicioImpl implements BitacoraServicioLocal, BitacoraServicioRemote {
    @EJB
    private AdministracionSeguridadesQueryLocal administracionSeguridadesQueryLocal;

    /* (non-Javadoc)
     * @see ec.gov.iess.seguridades.ws.model.service.BitacoraServicioLocal#guardarBitacora(ec.gov.iess.seguridades.ws.model.entity.BitacoraSeguridad)
     */
    public void guardarBitacora(BitacoraSeguridad bitacora) throws EntidadExisteException, EntidadException {
	administracionSeguridadesQueryLocal.guardarBitacora(bitacora);

    }

}
