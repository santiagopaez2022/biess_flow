package ec.gov.iess.creditos.pq.util;

import java.util.ArrayList;
import java.util.List;

import ec.gov.iess.commons.mail.MailUtil;
import ec.gov.iess.creditos.enumeracion.EnvioMail;

/**
 * Clase con una implementación personalizada para el envío de  mails
 * @author jmolina
 *
 */
public class MailSend {

	public MailSend(){		
	}
	
	public static void enviarMailInconsistenciaNovacionPQ(String mensaje){
		List<String> destinatarios = new ArrayList<String>();
		destinatarios.add(EnvioMail.FROM1.getValor());
		destinatarios.add(EnvioMail.FROM4.getValor());
		destinatarios.add(EnvioMail.FROM5.getValor());
		//destinatarios.add("jemolina@iess.gov.ec");

		for (String from : destinatarios) {
			MailUtil.enviarMail(EnvioMail.ASUNTOINCONSISTENCIANOVACION.getValor(),mensaje+ ".\n\n"+EnvioMail.PIEMAIL.getValor(), from, null);
		}
	}
	public static void enviarMailErrorNovacionPQ(String mensaje){
		List<String> destinatarios = new ArrayList<String>();
		destinatarios.add(EnvioMail.FROM1.getValor());
		destinatarios.add(EnvioMail.FROM4.getValor());
		destinatarios.add(EnvioMail.FROM5.getValor());
		//destinatarios.add("jemolina@iess.gov.ec");

		for (String from : destinatarios) {
			MailUtil.enviarMail(EnvioMail.ASUNTOERRORNOVACION.getValor(),mensaje+ ".\n\n"+EnvioMail.PIEMAIL.getValor(), from, null);
		}
	}
	
	public static void enviarMailFuncionario(String mensaje, String destinatario) {
		String asunto = "LISTADO PRESTAMOS APROBADOS EN PROCESO MASIVO";
			MailUtil.enviarMail(asunto,mensaje+ ".\n\n"+EnvioMail.PIEMAIL.getValor(), destinatario, null);
	}
}
