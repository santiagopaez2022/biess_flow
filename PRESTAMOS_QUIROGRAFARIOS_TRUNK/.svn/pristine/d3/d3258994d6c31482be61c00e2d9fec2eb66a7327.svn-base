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
package ec.fin.biess.creditos.pq.longtime.rp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.annotation.ejb.PoolClass;
import org.jboss.ejb3.StrictMaxPool;

import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.exception.EmailAlertUserException;
import ec.fin.biess.alerts.exception.SmsAlertUserException;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.alerts.modelo.InformacionAfiliado;
import ec.fin.biess.creditos.pq.modelo.dto.AprobacionDto;
import ec.fin.biess.creditos.pq.modelo.dto.AprobacionMasivaDto;
import ec.fin.biess.dao.HistoricoAprobacionesMasivasDao;
import ec.fin.biess.modelo.persistencia.HistoricoAprobacionesMasivas;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.SolicitudException;
import ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio;
import ec.gov.iess.creditos.pq.servicio.impl.AdministracionOrdenCompraProveedorServicioImpl;
import ec.gov.iess.creditos.pq.util.Utilities;
import freemarker.template.Configuration;
import freemarker.template.Template;

@TransactionManagement(TransactionManagementType.BEAN)
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/aprobacionMasivaPQ"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "100") })
@PoolClass(value = StrictMaxPool.class, maxSize = 100)
public class AprobacionCreditoSenderRp implements MessageListener {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(AprobacionCreditoSenderRp.class);
	
	@EJB(name = "AdministracionOrdenCompraProveedorServicioImpl/local")
	private AdministracionOrdenCompraProveedorServicio administracionordencompraservicio;
	
	@EJB(name = "AlertUserHelperImpl/local")
	private AlertUserHelper alertUserHelper;
	
	@EJB
	private HistoricoAprobacionesMasivasDao historicoAprobacionesMasivasDao;
		
	//Aprobacion Masivos
	private static final String PATH_TEMPLATE = "ec/gov/iess/creditos/pq/alertas/templates/email/aprobacionMasiva.ftl";

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			try {
				AprobacionMasivaDto aprobacionMasivaDto = (AprobacionMasivaDto) (((ObjectMessage) message).getObject());
				ArrayList<String> solicitudesAprobadas = new ArrayList<String>();
				ArrayList<String> solicitudesNegadas = new ArrayList<String>();
				BigDecimal secuencialHistorico = aprobacionMasivaDto.getSecuencialHistorico();
				String destinatario = aprobacionMasivaDto.getEmailFuncionario();
				String nombreFuncionario = aprobacionMasivaDto.getNombreFuncionario();
				String ipUsuario = aprobacionMasivaDto.getIpUsuario();
				String usuario = aprobacionMasivaDto.getUsuario();
				String hostRemoto = aprobacionMasivaDto.getHostRemoto();
				
				int tamanio = aprobacionMasivaDto.getTamanio();
				LOG.info("Tamanio: " + tamanio);
				LOG.info("Tamanio Lista: " + aprobacionMasivaDto.getListaAprobacion().size());
				LOG.info("secuencialHistorico: " + secuencialHistorico);
				boolean cambiarHistorico = true;
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					LOG.error("Error al interrumpir cola de aprobacion masiva", e);
				}
				if (aprobacionMasivaDto.getListaAprobacion().size() > 0) {
					for (AprobacionDto dato: aprobacionMasivaDto.getListaAprobacion()) {
						cambiarHistorico = true;
						try {
							administracionordencompraservicio.procesarTablaAmortizacionParaPDA(dato, secuencialHistorico,
									ipUsuario, usuario, hostRemoto);
							cambiarHistorico = false;
						} catch (SolicitudException e) {
							LOG.error("Error al registrar la aprobacion masiva de creditos mediante MDB.", e);
						}
						
						if (cambiarHistorico) {
							administracionordencompraservicio.actualizarHistoricoMasivoParaPDA(dato, secuencialHistorico);
						}
					}
				}
				
				List<HistoricoAprobacionesMasivas> listaAprobaciones = 
						historicoAprobacionesMasivasDao.consultarPorCodigoProceso(secuencialHistorico);
				LOG.info("Tamanio Lista Aprobaciones: " + listaAprobaciones.size());
				if (listaAprobaciones.size() == tamanio) {
					for (HistoricoAprobacionesMasivas historico: listaAprobaciones) {
						if ("SI".equals(historico.getProcesada())) {
							solicitudesAprobadas.add(historico.getIdentificacionAfiliado() + " " + historico.getNombreAfiliado());
						} else {
							String mensajeNegada = historico.getIdentificacionAfiliado() + " " + historico.getNombreAfiliado();
							try {
								administracionordencompraservicio.validarCesanteAmortizacionparaPDA(
										historico.getPk().getNumpreafi().longValue(), 
										historico.getPk().getOrdpreafi(),
										historico.getPk().getCodpretip(), 
										historico.getPk().getCodprecla());
							} catch (NoServicioPrestadoSucursalException e) {
								mensajeNegada = mensajeNegada + ", " + e.getMessage();
							}
							
							solicitudesNegadas.add(mensajeNegada);
						}
					}
					enviarEmailAprobacionMasivos(solicitudesAprobadas, solicitudesNegadas, secuencialHistorico, destinatario, nombreFuncionario);
				}
			} catch (JMSException e) {
				LOG.error("Error al registrar la aprobacion masiva de creditos mediante MDB JMSException Mensaje.", e);
			} catch (SolicitudException se) {
				LOG.error("Error al registrar la aprobacion masiva de creditos mediante MDB SolicitudException Mensaje.", se);
			}
		}
	}

	
	/**
	 * Envia el correo electronico cuando se realiza el ajuste de planillas sin usar colas de mensajeria
	 * 
	 * @param reversoPlanilla
	 */
	private void enviarEmailAprobacionMasivos(ArrayList<String> solicitudesAprobadas, ArrayList<String> solicitudesNegadas, BigDecimal numeroProceso, String destinatario, String nombreFuncionario) {
		try {
			String mensajeAprobados = "Para el proceso " + numeroProceso + " se aprobaron las solicitudes de las siguientes personas:\n\n";
			String mensajeNegados = "Para el proceso " + numeroProceso + " no se aprobaron las solicitudes de las siguientes personas:\n\n";
			InformacionAfiliado dp = new InformacionAfiliado();			
			dp.setApellidosNombres(nombreFuncionario);
			dp.setEmail(destinatario);
			/* Parametros del mensaje */
	        Map<String, Object> alertData = new HashMap<String, Object>();
	        alertData.put("msg_usuario", dp.getApellidosNombres());	        
	        alertData.put("msg_fecha", Utilities.getCurrentDate("dd/MM/yyyy HH:mm"));
	        alertData.put("msg_aprobacion", mensajeAprobados);
	        alertData.put("msg_negadas", mensajeNegados);
	        alertData.put("aprobadosList", solicitudesAprobadas);
	        alertData.put("negadosList", solicitudesNegadas);
	        
	        freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_NONE);
	        
	        Configuration cfg = new Configuration();
			/* Cargar el template */
			cfg.setClassForTemplateLoading(AdministracionOrdenCompraProveedorServicioImpl.class, "/");
			Template template = cfg.getTemplate(PATH_TEMPLATE);
	        
	        alertUserHelper.sendAlert(dp, "Notificaci\u00F3n Banco del IESS", template, alertData, null, AlertType.EMAIL);
	        LOG.info("El correo electronico fue enviado correctamente");
		} catch (SmsAlertUserException e) {
			LOG.error("Error en el envio de notificaciones SMS", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Error en el envio de notificaciones", e);
		} catch (IOException e) {
			LOG.error("Error desconocido en el envio de notificaciones", e);
		} catch (EmailAlertUserException e) {
			LOG.error("Error en el envio de notificaciones Email", e);
		}
	}
	
}
