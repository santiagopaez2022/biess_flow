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
package ec.fin.biess.unlock.helper;

import java.io.IOException;
import java.util.Map;



import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.exception.EmailAlertUserException;
import ec.fin.biess.alerts.exception.SmsAlertUserException;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.alerts.modelo.InformacionAfiliado;
import ec.gov.biess.util.log.LoggerBiess;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Clase que contiene funciones útiles para el envio de alertas.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
public class AlertsHelper {

	private transient static LoggerBiess log = LoggerBiess.getLogger(AlertsHelper.class);

	private static String subject = "Banco del IESS";

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
	public static void enviarAlerta(AlertUserHelper alertUserHelper, InformacionAfiliado dp, String templatePath, Map<String, Object> alertData, String smsMessage,
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
			cfg.setClassForTemplateLoading(AlertsHelper.class, "/");
			Template template = cfg.getTemplate(templatePath);
			/* Enviar la alerta */
			alertUserHelper.sendAlert(dp, subject, template, alertData, smsMessage, alertType);
		} catch (IOException e) {
			log.error("Error al obtener el template credito.ftl.", e);
		} catch (EmailAlertUserException e) {
			log.error(e.getMessage(), e);
		} catch (SmsAlertUserException e) {
			log.error(e.getMessage(), e);
		}
	}

}
