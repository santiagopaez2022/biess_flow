/* 
* Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
* Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
* file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
* by applicable law or agreed to in writing, software distributed under the License is 
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
* express or implied. See the License for the specific language governing permissions 
* and limitations under the License.
*/

package ec.gov.iess.seguridades.ws.model.query.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gov.iess.seguridades.ws.model.entity.AccesoSeguridad;
import ec.gov.iess.seguridades.ws.model.entity.BitacoraSeguridad;
import ec.gov.iess.seguridades.ws.model.entity.IpSeguridad;
import ec.gov.iess.seguridades.ws.model.excepcion.EntidadExisteException;
import ec.gov.iess.seguridades.ws.model.excepcion.MetodoServicioDuplicadoException;
import ec.gov.iess.seguridades.ws.model.query.AdministracionSeguridadesQueryLocal;

/**
 * Acceso a datos de la administracion de seguridades.
 *  
 * @version $Revision: 1.2 $
 * @author Chan - Cobiscorp
 */
@Stateless
public class AdministracionSeguridadesQueryImpl implements AdministracionSeguridadesQueryLocal {

    @PersistenceContext(unitName = "seguridades-ws-core")
    protected EntityManager entityManager;

    /* (non-Javadoc)
     * @see ec.gov.iess.seguridades.ws.model.query.AdministracionSeguridadesQueryLocal#findAccesoSeguridadByIpMetodoServicio(java.lang.String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public AccesoSeguridad findAccesoSeguridadByIpMetodoServicio(String ip, String metodoCodigo,
	    String serviciowebCodigo) throws MetodoServicioDuplicadoException {
	List<AccesoSeguridad> result = new ArrayList<AccesoSeguridad>();
	Query q = entityManager.createNamedQuery("AccesoSeguridad.findByIpMetodoWebService");
	q.setParameter("ip", ip);
	q.setParameter("metodoCodigo", metodoCodigo);
	q.setParameter("serviciowebCodigo", serviciowebCodigo);
	result.addAll(q.getResultList());

	if (!result.isEmpty()) {
	    if (result.size() == 1) {
		return result.get(0);
	    } else if (result.size() > 1) {
		throw new MetodoServicioDuplicadoException("Metodo duplicado para un servicio");
	    }
	}
	return null;
    }

    /* (non-Javadoc)
     * @see ec.gov.iess.seguridades.ws.model.query.AdministracionSeguridadesQueryLocal#findIpSeguridadByIp(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public IpSeguridad findIpSeguridadByIp(String ip) {
	List<IpSeguridad> result = new ArrayList<IpSeguridad>();
	Query q = entityManager.createNamedQuery("IpSeguridad.findByIp");
	q.setParameter("ip", ip);
	result.addAll(q.getResultList());

	if (!result.isEmpty()) {
	    return result.get(0);
	}
	return null;
    }

    /* (non-Javadoc)
     * @see ec.gov.iess.seguridades.ws.model.query.AdministracionSeguridadesQueryLocal#guardarBitacora(ec.gov.iess.seguridades.ws.model.entity.BitacoraSeguridad)
     */
    public void guardarBitacora(BitacoraSeguridad bitacoraSeguridad) throws EntidadExisteException {
	entityManager.persist(bitacoraSeguridad);
    }

}