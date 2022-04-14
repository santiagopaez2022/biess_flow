/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.servicio.impl;

import java.io.StringReader;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import ec.biess.fin.ws.Args;
import ec.biess.fin.ws.RunJobReturn;
import ec.biess.fin.ws.WSBIESSBCEDULAINTDBPRORUN;
import ec.biess.fin.ws.WSBIESSBCEDULAINTDBPRORUNPortType;
import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.excepcion.RegistroCivilBiessException;
import ec.fin.biess.creditos.pq.helper.AuditoriaHelperPqConcesion;
import ec.fin.biess.creditos.pq.servicio.RegistroCivilBiessServicio;
import ec.fin.biess.creditos.pq.servicio.ServicioAuditoriaPqConcesionEjbLocal;
import ec.fin.biess.enumeraciones.AplicativoEnum;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCatalogos;
import ec.gov.iess.creditos.pq.servicio.DetalleCatalogoServicio;

/**
 * Implementacion interface que brinda funcionalidad del web service del registro civil.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
@Stateless
public class RegistroCivilBiessServicioImpl implements RegistroCivilBiessServicio {

	private transient LoggerBiess log = LoggerBiess.getLogger(RegistroCivilBiessServicioImpl.class);

	@EJB
	private DetalleCatalogoServicio detalleCatalogoServicio;
	
	@EJB
    private ServicioAuditoriaPqConcesionEjbLocal servicioAuditoria;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.RegistroCivilBiessServicio#consultarWS(java.lang.String, java.lang.String,
	 * java.lang.String, ec.fin.biess.enumeraciones.AplicativoEnum)
	 */
	public String consultarWS(String cedula, String cedulaFuncionario, String direccionIP, AplicativoEnum aplicativo) throws RegistroCivilBiessException {
		RunJobReturn runJobReturn = null;
		String xmlmj = null;
		String respuesta = "OK";
		String estado = "0";
		String observacion = "";
		try {
			Args argumentos = getAgumentos(cedula);
			runJobReturn = runJob(argumentos);
			if (runJobReturn == null) {
				/* Fijar info log de consulta al WS del registro civil */
				respuesta = "ERR";
				estado = "1";
				observacion = "Error al consultar informaci\u00F3n del registro civil. El web service no responde.";
				throw new RegistroCivilBiessException(observacion);
			}
			/* Obtener la trama del WS con la que se va a trabajar */
			xmlmj = itemToString(runJobReturn.getItem().get(0).getItem());
			if (null == xmlmj || xmlmj.isEmpty()) {
				/* Fijar info log de consulta al WS del registro civil */
				respuesta = "ERR";
				estado = "1";
				observacion = "Error al consultar informaci\u00F3n del registro civil. La trama que retorn\u00F3 el WS es nula o vac\u00EDa";
				throw new RegistroCivilBiessException("Error al consultar informaci\u00f3n del registro civil.");
			}
			/* Verificar si el WS retornó algún código de error */
			Integer error = null;
			String errorStr = obtenerCampo(xmlmj, "CODIGO_ERROR");
			if (null != errorStr && "" != errorStr) {
				error = Integer.parseInt(errorStr);
			}
			if (error != null) {
				String mensajeErrorRegCivil = getMensajeErrorRegCivil(error, xmlmj);
				/* Fijar info log de consulta al WS del registro civil */
				respuesta = "ERM";
				estado = "0";
				observacion = mensajeErrorRegCivil;
				throw new RegistroCivilBiessException(mensajeErrorRegCivil);
			}
		} catch (RegistroCivilBiessException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			log.error("Trama registro civil para CI: " + cedula);
			log.error("--- Incio trama ---");
			log.error(xmlmj);
			log.error("--- Fin trama ---");
			throw new RegistroCivilBiessException(e.getMessage(), e);
		} catch (Exception e) {
			log.error("Error no manejado al consultar informaci\u00F3n del registro civil. CI: " + cedula);
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			log.error("Trama registro civil para CI: " + cedula);
			log.error("--- Incio trama ---");
			log.error(xmlmj);
			log.error("--- Fin trama ---");
			/* Fijar info log de consulta al WS del registro civil */
			respuesta = "ERR";
			estado = "1";
			observacion = "Error no manejado al consultar informaci\u00F3n del registro civil.";
			throw new RegistroCivilBiessException(observacion, e);
		} finally {
			/* Guardar log de consulta al WS del registro civil */
			ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosConsumeServicioWeb(cedula, respuesta,
					estado + " " + observacion);
			if (AplicativoEnum.PQ_WEB == aplicativo) {
				this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_REGISTRO_CIVIL_PQ_WEB, parametroEvento, direccionIP, cedula);
			} else {
				this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_REGISTRO_CIVIL_PQ_INTRANET, parametroEvento, direccionIP, cedula);
			}
		}

		return xmlmj;
	}

	/**
	 * Metodo que obtiene la descripcion de un error enviado por el ws RC.
	 * 
	 * @param error
	 * @param trama
	 * @return String
	 * @throws RegistroCivilBiessException
	 */
	private String getMensajeErrorRegCivil(Integer error, String trama) throws RegistroCivilBiessException {
		String mensajeErrorRegCivil;
		switch (error) {
		case 1:
			mensajeErrorRegCivil = "C\u00E9dula no encontrada";
			break;
		case 2:
			mensajeErrorRegCivil = "Menor a 16 años";
			break;
		case 3:
			mensajeErrorRegCivil = "Usuario o Contrase\u00F1a incorrecta";
			break;
		case 4:
			mensajeErrorRegCivil = "Caracteres no admitidos";
			break;
		case 5:
			mensajeErrorRegCivil = "Cuota mensual de consulta excedida";
			break;
		case 6:
			mensajeErrorRegCivil = "C\u00E9dula debe tener 10 digitos";
			break;
		case 7:
			mensajeErrorRegCivil = "Ingrese n\u00FAmero de c\u00E9dula";
			break;
		case 8:
			mensajeErrorRegCivil = "Ingrese usuario y contrase\u00F1a";
			break;
		case 9:
			mensajeErrorRegCivil = "Fallecido";
			break;
		default:
			mensajeErrorRegCivil = obtenerCampo(trama, "DESCRIPCION");
			break;
		}

		return mensajeErrorRegCivil;
	}

	/**
	 * Fija parametros de entrada para consultar WS Registro Civil.
	 * 
	 * @param cedula
	 * @return Args
	 */
	private Args getAgumentos(String cedula) {
		Args argumentos = new Args();
		DetalleCatalogos dcUsuario = detalleCatalogoServicio.encontrarDetalleCatalogo("REC_WS", "RC_USU");
		DetalleCatalogos dcClave = detalleCatalogoServicio.encontrarDetalleCatalogo("REC_WS", "RC_CLA");
		argumentos.getItem().add("--context_param p_macedu=" + cedula);
		argumentos.getItem().add("--context_param p_usuario=" + dcUsuario.getDcValor());
		argumentos.getItem().add("--context_param p_contrasenia=" + dcClave.getDcValor());

		return argumentos;
	}

	/**
	 * Metodo que invoca al web service de registro civil
	 * 
	 * @param args
	 * @return
	 */
	private RunJobReturn runJob(Args args) {
		WSBIESSBCEDULAINTDBPRORUN service = new WSBIESSBCEDULAINTDBPRORUN();
		WSBIESSBCEDULAINTDBPRORUNPortType port = service.getWSBIESSBCEDULAINTDBPRORUNHttpSoap11Endpoint();

		return port.runJob(args);
	}

	/**
	 * Método que toma una lista de strings y la convierte en un string
	 * 
	 * @param list
	 * @return String
	 */
	private String itemToString(List<String> list) {
		if (null == list || list.isEmpty()) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String l : list) {
			sb.append(l);
		}

		return sb.toString();
	}

	/**
	 * Método que retorna el valor del campo dado un XML
	 * 
	 * @param trama
	 * @param campo
	 * @return String
	 * @throws RegistroCivilBiessException
	 */
	@SuppressWarnings("rawtypes")
	public String obtenerCampo(String trama, String campo) throws RegistroCivilBiessException {
		String result = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			StringReader sr = new StringReader(trama);
			Document document = (Document) builder.build(sr);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("ROW");
			if (null == list || list.isEmpty()) {
				return result;
			}
			/* Se utiliza la primera trama del XML devuelto por el WS en caso de que exista mas de una */
			Element node = (Element) list.get(0);
			result = node.getChildText(campo);
		} catch (JDOMException jdomex) {
			log.error("Error al obtener el campo: " + campo);
			log.error(jdomex.getMessage());
			throw new RegistroCivilBiessException("Error al obtener el campo: " + campo + ". ");
		} catch (Exception ex) {
			log.error("Error al obtener el campo: " + campo);
			log.error(ex.getMessage());
			throw new RegistroCivilBiessException("Error al obtener el campo: " + campo + ". ");
		}

		return result;
	}

}
