package ec.gov.iess.creditos.pq.servicio.impl;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import ec.fin.biess.creditos.pq.excepcion.AprobacionCreditoSenderException;
import ec.fin.biess.creditos.pq.modelo.dto.AprobacionMasivaDto;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.servicio.AprobacionCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.AprobacionCreditoServicioRemote;

@Stateless
public class AprobacionCreditoServicioImpl implements AprobacionCreditoServicio, AprobacionCreditoServicioRemote {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(AprobacionCreditoServicioImpl.class);


	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "queue/aprobacionMasivaPQ")
	private Queue queue;
	
	public void encolarTramiteAprobacionMasiva(AprobacionMasivaDto dato) throws AprobacionCreditoSenderException {
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(queue);
			ObjectMessage objectMessage = session.createObjectMessage();
			objectMessage.setObject(dato);
			producer.send(objectMessage);
			session.close();
			connection.close();
		} catch (JMSException e) {
			LOG.error("Error al enviar a procesar la aprobacion masiva de creditos con JMS.", e);
			throw new AprobacionCreditoSenderException("Error al enviar a procesar la aprobacion masiva de creditos con JMS.");
		} 
	}
}
