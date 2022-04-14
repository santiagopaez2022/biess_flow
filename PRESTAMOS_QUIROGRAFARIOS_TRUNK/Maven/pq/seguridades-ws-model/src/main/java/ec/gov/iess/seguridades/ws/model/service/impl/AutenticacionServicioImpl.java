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

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import ec.gov.iess.seguridades.ws.model.dto.RespuestaDTO;
import ec.gov.iess.seguridades.ws.model.entity.AccesoSeguridad;
import ec.gov.iess.seguridades.ws.model.entity.BitacoraSeguridad;
import ec.gov.iess.seguridades.ws.model.entity.IpSeguridad;
import ec.gov.iess.seguridades.ws.model.excepcion.EntidadException;
import ec.gov.iess.seguridades.ws.model.excepcion.EntidadExisteException;
import ec.gov.iess.seguridades.ws.model.excepcion.MetodoServicioDuplicadoException;
import ec.gov.iess.seguridades.ws.model.query.AdministracionSeguridadesQueryLocal;
import ec.gov.iess.seguridades.ws.model.service.AutenticacionServicioLocal;
import ec.gov.iess.seguridades.ws.model.service.AutenticacionServicioRemote;
import ec.gov.iess.seguridades.ws.model.service.BitacoraServicioLocal;
import ec.gov.iess.seguridades.ws.model.util.ParametroWrapper;

/**
 * Implementacion de procesos de seguridad de ws.
 * 
 * @version $Revision: 1.2 $
 * @author Chan - Cobiscorp
 */
@Stateless
public class AutenticacionServicioImpl implements AutenticacionServicioLocal, AutenticacionServicioRemote {
    @EJB
    private AdministracionSeguridadesQueryLocal administracionSeguridadesQueryLocal;

    @EJB
    private BitacoraServicioLocal bitacoraServicioLocal;

    private static final Logger log = Logger.getLogger(AutenticacionServicioImpl.class);

    /* (non-Javadoc)
     * @see ec.gov.iess.seguridades.ws.model.service.AutenticacionServicioLocal#tienePermisos(ec.gov.iess.seguridades.ws.model.util.ParametroWrapper)
     */
    public RespuestaDTO tienePermisos(ParametroWrapper parametros) {
	log.debug("Ip" + parametros.getIp());
	log.debug("codigo metodo" + parametros.getMetodoCodigo());
	log.debug("codigo WebService" + parametros.getWebserviceCodigo());

	BitacoraSeguridad bitacoraSeguridad = new BitacoraSeguridad();
	bitacoraSeguridad.setFechaAcceso(new Date());
	bitacoraSeguridad.setIpNumero(parametros.getIp());
	bitacoraSeguridad.setWebserviceCodigo(parametros.getWebserviceCodigo());
	bitacoraSeguridad.setMetodoCodigo(parametros.getMetodoCodigo());
	bitacoraSeguridad.setTrama(parametros.getTramaXML());

	RespuestaDTO respuestaDTO = new RespuestaDTO();
	respuestaDTO.setMensaje("");
	respuestaDTO.setTienePermiso(true);

	IpSeguridad ipSeguridad = administracionSeguridadesQueryLocal.findIpSeguridadByIp(parametros.getIp());

	if (ipSeguridad != null) {
	    bitacoraSeguridad.setAlias(ipSeguridad.getClienteSeguridad().getAlias());
	    bitacoraSeguridad.setRuc(ipSeguridad.getClienteSeguridad().getRuc());

	    try {
		AccesoSeguridad acceso = administracionSeguridadesQueryLocal.findAccesoSeguridadByIpMetodoServicio(
			parametros.getIp(), parametros.getMetodoCodigo(), parametros.getWebserviceCodigo());
		if (acceso != null) {
		    if (!acceso.getEstado().equals("ACT")) {
			respuestaDTO.setMensaje("206: El cliente de ip: " + parametros.getIp()
				+ " no posee los permisos de acceso al metodo con codigo: "
				+ parametros.getMetodoCodigo() + " del Servicio Web codigo: "
				+ parametros.getWebserviceCodigo());
			respuestaDTO.setTienePermiso(false);
		    }
		} else {
		    respuestaDTO.setMensaje("206: El cliente de ip: " + parametros.getIp()
			    + " no tiene registrado el permiso de acceso al metodo con codigo: "
			    + parametros.getMetodoCodigo() + " del servicio web codigo: "
			    + parametros.getWebserviceCodigo());
		    respuestaDTO.setTienePermiso(false);
		}
	    } catch (MetodoServicioDuplicadoException e) {
		respuestaDTO.setMensaje("206: El codigo metodo: " + parametros.getMetodoCodigo()
			+ " del codigo servicio web: " + parametros.getWebserviceCodigo()
			+ " esta duplicado, por favor contactese con el departamento de informatica del IESS");
		respuestaDTO.setTienePermiso(false);
	    }
	} else {
	    respuestaDTO.setMensaje("206: El cliente de ip: " + parametros.getIp()
		    + " no tiene registrado el permiso de acceso al codigo metodo: " + parametros.getMetodoCodigo()
		    + " del codigo servicio web: " + parametros.getWebserviceCodigo());
	    respuestaDTO.setTienePermiso(false);
	}

	if (respuestaDTO.isTienePermiso() == false) {
	    bitacoraSeguridad.setObservacion(respuestaDTO.getMensaje());
	}

	try {
	    bitacoraServicioLocal.guardarBitacora(bitacoraSeguridad);
	} catch (EntidadExisteException e) {
	    log.error("AutenticacionServicioImpl.tienePermisos(): no se registro la bitacora", e);
	} catch (EntidadException e) {
	    log.error("AutenticacionServicioImpl.tienePermisos(): no se registro la bitacora", e);
	}
	return respuestaDTO;
    }
}
