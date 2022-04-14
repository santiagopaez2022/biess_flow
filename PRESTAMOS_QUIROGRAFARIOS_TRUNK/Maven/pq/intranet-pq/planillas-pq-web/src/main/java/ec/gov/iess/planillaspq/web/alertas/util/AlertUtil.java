/*
 * Copyright 2013 BIESS - ECUADOR
 * 
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.planillaspq.web.alertas.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.exception.EmailAlertUserException;
import ec.fin.biess.alerts.exception.SmsAlertUserException;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.alerts.modelo.InformacionAfiliado;
import ec.gov.iess.creditos.modelo.dto.DatosPersonales;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Clase que contiene funciones útiles para el envio de alertas.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
public class AlertUtil {

	private transient static Logger log = Logger.getLogger(AlertUtil.class);

	private static String subject = "Notificaci\u00F3n Banco del IESS";

	/**
	 * Metodo que envia mensajes de alerta al usuario.
	 * 
	 * @param alertUserHelper
	 * @param dp
	 * @param subject
	 * @param templatePath
	 * @param alertData
	 * @param smsMessage
	 * @param alertType
	 */
	public static void enviarAlerta(AlertUserHelper alertUserHelper, DatosPersonales dp, String templatePath, Map<String, Object> alertData, String smsMessage,
			AlertType alertType) {
		/* Desactivar log de objetos freemarker */
		try {
			freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_NONE);
		} catch (ClassNotFoundException e) {
			log.error("Error al desactivar log de freemarker.", e);
		}
		try {
			Configuration cfg = new Configuration();
			/* Cargar el template */
			cfg.setClassForTemplateLoading(AlertUtil.class, "/");
			Template template = cfg.getTemplate(templatePath);
			/* Enviar la alerta */
			InformacionAfiliado informacionAfiliado = new InformacionAfiliado();
			informacionAfiliado.setApellidosNombres(dp.getApellidosNombres());
			informacionAfiliado.setCedula(dp.getCedula());
			informacionAfiliado.setCelular(dp.getCelular());
			informacionAfiliado.setEmail(dp.getEmail());
			alertUserHelper.sendAlert(informacionAfiliado, subject, template, alertData, smsMessage, alertType);
		} catch (IOException e) {
			log.error("Error al obtener el template: " + templatePath, e);
		} catch (EmailAlertUserException e) {
			log.error(e.getMessage());
		} catch (SmsAlertUserException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Metodo que formatea una fecha.
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);
	}

	/**
	 * Metodo que enmascara una cadena, excepto los 2 primeros y 2 ultimos caracteres
	 * @param pstring
	 * @return
	 */
	public static String maskString(String pstring) {
		StringBuilder output = new StringBuilder("");
		for (int i = 0; i < pstring.length(); i++) {
			if (i < 2) {
				output.append(pstring.charAt(i));
				continue;
			}
			if (i + 2 >= pstring.length()) {
				output.append(pstring.charAt(i));
				continue;
			}
			output.append('X');
		}
		
		return output.toString();
	}

}
