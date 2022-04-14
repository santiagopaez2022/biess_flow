/* 
* Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
* Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
* file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
* by applicable law or agreed to in writing, software distributed under the License is 
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
* express or implied. See the License for the specific language governing permissions 
* and limitations under the License.
*/

package ec.gov.iess.seguridades.ws.model.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.log4j.Logger;

import ec.gov.iess.seguridades.ws.model.dto.RespuestaDTO;
import ec.gov.iess.seguridades.ws.model.service.AutenticacionServicioLocal;
import ec.gov.iess.seguridades.ws.model.service.impl.AutenticacionServicioImpl;
import ec.gov.iess.seguridades.ws.model.util.ParametroWrapper;
import ec.gov.iess.seguridades.ws.model.util.Util;

/**
 * Handler que controla cuando se accede a un WS
 * @author Chan - Cobiscorp
 *
 * @revision $Revision: 1.2 $
 */
public class SeguridadHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger log = Logger.getLogger(SeguridadHandler.class);

    AutenticacionServicioLocal autenticacionServicioLocal;

    /* (non-Javadoc)
     * @see javax.xml.ws.handler.soap.SOAPHandler#getHeaders()
     */
    public Set<QName> getHeaders() {
	return null;
    }

    /* (non-Javadoc)
     * @see javax.xml.ws.handler.Handler#close(javax.xml.ws.handler.MessageContext)
     */
    public void close(MessageContext arg0) {

    }

    /* (non-Javadoc)
     * @see javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext)
     */
    public boolean handleFault(SOAPMessageContext arg0) {
	return true;
    }

    /**
     * Metodo que se encarga de llamar a la logica de tiene permiso, y dependiendo de si tiene o no permiso
     * permite que se ejecute el webservice o no
     * @see javax.xml.ws.handler.Handler#handleMessage(javax.xml.ws.handler.MessageContext)
     */
    public boolean handleMessage(SOAPMessageContext smc) {
	Boolean outbound = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
	if (!outbound) {
	    try {

		SOAPEnvelope envelope;
		envelope = smc.getMessage().getSOAPPart().getEnvelope();
		SOAPBody body = envelope.getBody();
		SOAPFault fault;

		ParametroWrapper parametros = obtenerParametros(smc);

		RespuestaDTO respuestaDTO = getAutenticacionServicioLocal().tienePermisos(parametros);

		if (!respuestaDTO.isTienePermiso()) {
		    fault = body.addFault();
		    QName faultName = new QName(SOAPConstants.URI_NS_SOAP_ENVELOPE, "Cliente");
		    fault.setFaultCode(faultName);
		    fault.setFaultActor("IP: " + parametros.getIp());
		    fault.setFaultString(respuestaDTO.getMensaje());
		    throw new SOAPFaultException(fault);
		}
	    } catch (SOAPException e) {
		throw new SOAPFaultException(null);
	    }
	}
	return true;
    }

    /**
     * Metodo para obtener los parametros y llenar el wrapper
     * @param smc
     * @return
     */
    private ParametroWrapper obtenerParametros(SOAPMessageContext smc) {
	ParametroWrapper parametros = new ParametroWrapper();
	parametros.setIp(obtenerIP(smc));
	parametros.setMetodoCodigo(obtenerCodigoMetodo(smc));
	parametros.setWebserviceCodigo(obtenerCodigoServicio(smc));
	parametros.setTramaXML(obtenerTramaXml(smc));
	return parametros;
    }

    /**
     * Metodo para obtener la ip
     * @param smc
     * @return
     */
    private String obtenerIP(SOAPMessageContext smc) {
	HttpServletRequest rq = ((javax.servlet.http.HttpServletRequest) smc.get(MessageContext.SERVLET_REQUEST));
	return rq.getRemoteAddr();
    }

    /**
     * Metodo para obtener el codigo del Metodo
     * @param smc
     * @return
     */
    private String obtenerCodigoMetodo(SOAPMessageContext smc) {
	QName metodo = ((QName) smc.get(MessageContext.WSDL_OPERATION));
	return metodo.getLocalPart();
    }

    /**
     * Metodo para obtener el codigo del Servicio
     * @param smc
     * @return
     */
    private String obtenerCodigoServicio(SOAPMessageContext smc) {
	QName metodo = ((QName) smc.get(MessageContext.WSDL_SERVICE));
	return metodo.getLocalPart();
    }

    /**
     * Metodo para obtener la trama xml del web service
     * @param smc
     * @return
     */
    private String obtenerTramaXml(SOAPMessageContext smc) {
	SOAPMessage message = smc.getMessage();
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	try {
	    message.writeTo(out);
	} catch (SOAPException e) {
	    log.error("SeguridadHandler.obtenerTramaXml(): Error al parsear trama", e);
	} catch (IOException e) {
	    log.error("SeguridadHandler.obtenerTramaXml(): Error al parsear trama", e);
	}
	return new String(out.toByteArray());
    }

    /**
     * @return the autenticacionServicioLocal
     */
    public AutenticacionServicioLocal getAutenticacionServicioLocal() {
	if (autenticacionServicioLocal != null) {
	    return autenticacionServicioLocal;
	} else {
	    return Util.getServiceBean(AutenticacionServicioImpl.class.getSimpleName() + "/local");
	}
    }

}