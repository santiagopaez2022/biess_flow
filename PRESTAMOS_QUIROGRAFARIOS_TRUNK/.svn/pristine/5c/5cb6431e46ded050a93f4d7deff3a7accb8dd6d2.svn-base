package ec.gov.iess.planillaspq.servicio.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EjecucionProcedimientoException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.commons.mail.MailUtil;
import ec.gov.iess.planillaspq.dao.ComprobantePagoPlaDao;
import ec.gov.iess.planillaspq.dao.ComprobantePagoHistoricoDao;
import ec.gov.iess.planillaspq.dao.DividendosDao;
import ec.gov.iess.planillaspq.dao.DividendosHistoricoDao;
import ec.gov.iess.planillaspq.dao.PagoParcialDao;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoComprobanteException;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoComprobanteGenericoException;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoDividendoException;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoHistorico;
import ec.gov.iess.planillaspq.modelo.persistencia.Dividendos;
import ec.gov.iess.planillaspq.modelo.persistencia.DividendosHistorico;
import ec.gov.iess.planillaspq.modelo.persistencia.PagoParcial;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.ComprobantePagoHistoricoPK;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.DividendosHistoricoPK;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.DividendosPK;
import ec.gov.iess.planillaspq.servicio.CambioEstadoServicioLocal;
import ec.gov.iess.planillaspq.servicio.CambioEstadoServicioRemote;
import ec.gov.iess.planillaspq.storeprocedure.CambioEstadoComprobanteParAnuSpLocal;
import ec.gov.iess.planillaspq.storeprocedure.CambioEstadoComprobanteSpLocal;
import ec.gov.iess.planillaspq.enumeraciones.EnvioMail;



@Stateless
public class CambioEstadoServicioImpl implements CambioEstadoServicioLocal,
CambioEstadoServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(CambioEstadoServicioImpl.class);
	
	@EJB
	private ComprobantePagoPlaDao comprobantePagoDao; 
	
	@EJB
	private DividendosDao dividendoDao; 
	
	@EJB
	private DividendosHistoricoDao dividendohistoricoDao; 
		
	@EJB
	private ComprobantePagoHistoricoDao comprobantehistoricoDao; 
		
	@EJB
	private PagoParcialDao pagpar; 
	
	@EJB
	private CambioEstadoComprobanteSpLocal camestcom; 	

	@EJB
	private CambioEstadoComprobanteParAnuSpLocal camestcomparanu; 	

	
	public void CambioEstadoComprobanteGenerico(ComprobantePagoPla compag, String Observacion,String TipoComp,String cedfun,String nueest)
	throws CambioEstadoComprobanteGenericoException{
		
		java.util.Date fechahoy=new java.util.Date();
		String error="";
		try{
					
				log.info("Entro al if del comprobante.");
				
					ComprobantePagoHistoricoPK compaghispk=new ComprobantePagoHistoricoPK(compag.getPk().getCodtipcompag(),compag.getPk().getCodcompag(),compag.getCodestcompag());
					error="SE ENCONTRARON INCONSISTENCIAS ENTRE LOS ESTADOS LA CABECERA DEL COMPROBANTE Y SU HISTORICO.  NO SE ENCONTRO EL HISTORICO CON ESTADO "+compag.getCodestcompag();
					log.info("codcompag: "+compaghispk.getCodcompag()+" codtipcompag: "+compaghispk.getCodtipcompag()+" estado: "+compaghispk.getCodestcompag());
					
					ComprobantePagoHistorico compaghis=comprobantehistoricoDao.buscarComprobanteHistoricoPorId(compaghispk);
										
					compaghis.setFecfin(fechahoy);
					compaghis.setObstra(Observacion);

					error="NO SE PUDO ACTUALIZAR EL HISTORICO DEL COMPROBANTE";
					comprobantehistoricoDao.update(compaghis);

					log.info("Actualizo el historico del comprobante");
					log.info("codcompag: "+compaghispk.getCodcompag()+" codtipcompag: "+compaghispk.getCodtipcompag()+" estado: "+compaghispk.getCodestcompag());
					
					ComprobantePagoHistoricoPK compaghispknew=new ComprobantePagoHistoricoPK(compag.getPk().getCodtipcompag(),compag.getPk().getCodcompag(),nueest,fechahoy);
					ComprobantePagoHistorico compaghisnew=new ComprobantePagoHistorico();
					compaghisnew.setPk(compaghispknew);
					compaghisnew.setCodusu(cedfun);

					error="NO SE PUDO INSERTAR EL NUEVO ESTADO DEL HISTORICO DEL COMPROBANTE";					
					comprobantehistoricoDao.insert(compaghisnew);
					log.info("Inserto el historico del comprobante");
					log.info("codcompag: "+compaghisnew.getPk().getCodcompag()+" codtipcompag: "+compaghisnew.getPk().getCodtipcompag()+" estado: "+compaghisnew.getPk().getCodestcompag());

					error="NO SE PUDO ACTUALIZAR LA CABECERA DEL COMPROBANTE CON EL NUEVO ESTADO";					
					compag.setCodestcompag(nueest);
					comprobantePagoDao.update(compag);
					log.info("Actualizo el comprobante");
										
					error="";
					
		}catch(EntidadNoExisteException e){
			throw new CambioEstadoComprobanteGenericoException(error,e);	
		}catch(DaoException e){
			throw new CambioEstadoComprobanteGenericoException(error,e);	
		}catch(Exception e){
			throw new CambioEstadoComprobanteGenericoException(error,e);	
		}		

	}				
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void CambioEstadoComprobante(Planillas planilla, String Observacion,String TipoComp,String cedfun)
	throws CambioEstadoComprobanteException{
		
		String error="SE ENCONTRARON INCONSISTENCIAS ENTRE EL ESTADO DE LA PLANILLA Y DEL COMPROBANTE";
		Map resultado;
		try{
					
			ComprobantePagoPla compag=comprobantePagoDao.buscarComprobantePorPlanilla(planilla,TipoComp);
			
			log.info("Econtro el comprobante");
			log.info("codcompag: "+compag.getPk().getCodcompag()+" codtipcompag: "+compag.getPk().getCodtipcompag()+" estado: "+ compag.getCodestcompag());
			MailUtil mail=new MailUtil();
						
			if (compag.getCodestcompag().equals("PAR")){
				
				error="NO SE ENCONTRO EL COMPROBANTE EN LA TABLA DE ABONOS";
				log.info("Entro a la condicion de estado PAR");
				
				List<PagoParcial> p=pagpar.buscarPorComprobante(compag.getPk().getCodtipcompag(), compag.getPk().getCodcompag());
				
				for (PagoParcial pagparcial:p){
					pagpar.delete(pagparcial);
				}
				
				log.info("Elimino de la tabla de Pago Parcial");

				resultado=camestcomparanu.ejecutarCambioEstadoComprobanteParAnu(compag,Observacion,cedfun,"ANU");
				if (resultado.get("AOCRESPRO").toString().trim().equals("0")){
					throw new CambioEstadoComprobanteGenericoException("EXISTE INCONSISTENCIA ENTRE LOS ESTADOS DE LA CABECERA Y EL HISTORICO DEL COMPROBANTE. MENSAJE DE LA BASE: "+resultado.get("AOCMENERR"));	
				}else{

					List<String> destinatarios=new ArrayList<String>();
					
					destinatarios.add(EnvioMail.FROM1.getValor());
					destinatarios.add(EnvioMail.FROM2.getValor());

					for (String from:destinatarios){
					
						mail.enviarMail(EnvioMail.ASUNTO.getValor(),
								EnvioMail.CUERPO.getValor()+ "Motivo: "+Observacion+" \n\n Código Comprobante: "+compag.getPk().getCodcompag()+" \n\n Tipo de comprobante: "+compag.getPk().getCodtipcompag()+" \n\n Estado: PAR \n\n "+ EnvioMail.PIEMAIL.getValor(),
								from, null);
					}		

				log.info("Se cambio el estado del comprobante a ANU existosamente: "+compag.getCodestcompag());
				}
			}else{
			
			resultado=camestcom.ejecutarCambioEstadoComprobante(compag,Observacion,cedfun,"ANU");
			if (resultado.get("AOCRESPRO").toString().trim().equals("0")){
				throw new CambioEstadoComprobanteGenericoException("EXISTE INCONSISTENCIA ENTRE LOS ESTADOS DE LA CABECERA Y EL HISTORICO DEL COMPROBANTE. MENSAJE DE LA BASE: "+resultado.get("AOCMENERR"));	
			}else{
				List<String> destinatarios=new ArrayList<String>();
				
				destinatarios.add(EnvioMail.FROM1.getValor());
				destinatarios.add(EnvioMail.FROM2.getValor());

				for (String from:destinatarios){
				
					mail.enviarMail(EnvioMail.ASUNTO.getValor(),
							EnvioMail.CUERPO.getValor()+ "Motivo: "+Observacion+" \n\n Código Comprobante: "+compag.getPk().getCodcompag()+" \n\n Tipo de comprobante: "+compag.getPk().getCodtipcompag()+" \n\n Estado: GEN \n\n "+ EnvioMail.PIEMAIL.getValor(),
							from, null);
				}
				log.info("Se cambio el estado del comprobante a ANU existosamente: "+compag.getCodestcompag());
			  }
			}
		} catch (EjecucionProcedimientoException e) {
			throw new CambioEstadoComprobanteException(e.getMessage(),e);			
		}catch(CambioEstadoComprobanteGenericoException e){
			throw new CambioEstadoComprobanteException(e.getMessage(),e);	
		}catch(Exception e){
			throw new CambioEstadoComprobanteException(error,e);	
		}		

	}				
			
		
		public void CambioEstadoDividendo(PlanillaPrestamosDetalle pladet, String Observacion)
		throws CambioEstadoDividendoException{

			String codestdiv=null;
			java.util.Date fechahoy=new java.util.Date();
			String error="EL DIVIDENDO: "+pladet.getPk().getNumdiv() +" DEL PRESTAMO: "+pladet.getPk().getNumpreafi()+ " Y CODPRECLA: " +
			pladet.getPk().getCodprecla()+" DEL AFILIADO: "+ pladet.getNumafi()+" NO SE ENCUENTRA EN ESTADO EPL";
			
			
			try{
				DividendosPK divpk=new DividendosPK(pladet.getPk().getNumdiv(),pladet.getPk().getCodpretip(),pladet.getPk().getOrdpreafi(),pladet.getPk().getNumpreafi(),pladet.getPk().getCodprecla());
				Dividendos div = dividendoDao.buscarPorId(divpk);
				
				Calendar hoy = new GregorianCalendar();
		        Calendar fecpag = new GregorianCalendar();
		        fecpag.setTime(div.getFecpagdiv());

		        Long mesactual = (long) (hoy.get(Calendar.MONTH)+1);
		        log.info("mes actual: "+mesactual);
				Long mespago = (long) (fecpag.get(Calendar.MONTH)+1);			
		        log.info("mes pago: "+mespago);
		        Long anioactual = (long) (hoy.get(Calendar.YEAR));
		        log.info("año actual: "+anioactual);
				
		        Long aniopago = (long) (fecpag.get(Calendar.YEAR));
		        log.info("anio pago: "+aniopago);
				
				if (mesactual.equals(mespago) && anioactual.equals(aniopago)){
					codestdiv="REG";
				}else{
					codestdiv="MOR";
				}
					log.info("Entró a la condición de si los dividendos están en EPL. "+div.getCodestdiv());

					error="SE ENCONTRARON INCONSISTENCIAS ENTRE LA CABECERA DEL DIVIDENDO (ESTADO: "+div.getCodestdiv()+ ") Y SU HISTORICO PARA EL DIVIDENDO " +
							+div.getPk().getNumdiv()+ " DEL PRESTAMO NRO: "+div.getPk().getNumpreafi()+" Y CODPRECLA: " +
							div.getPk().getCodprecla()+" DEL AFILIADO: "+ pladet.getNumafi();
										
					DividendosHistoricoPK divhispk=new DividendosHistoricoPK(div.getPk().getOrdpreafi(),div.getPk().getNumdiv(),div.getPk().getCodprecla(),div.getPk().getCodpretip(),div.getPk().getNumpreafi(),div.getCodestdiv()); 
					DividendosHistorico divhis=dividendohistoricoDao.buscarPorId(divhispk);
					log.info("Encontró al histórico del dividendo");
					divhis.setFecfin(fechahoy);
					divhis.setObstra(Observacion);
					error="NO SE PUDO ACTUALIZAR EL HISTORICO DEL DIVIDENDO NUMERO: " +
					+div.getPk().getNumdiv()+ " DEL PRESTAMO NRO: "+div.getPk().getNumpreafi()+" Y CODPRECLA: " +
					div.getPk().getCodprecla()+" DEL AFILIADO: "+ pladet.getNumafi();

					dividendohistoricoDao.update(divhis);
					log.info("Actualizó el histórico del dividendo");
				
					DividendosHistoricoPK divhispknew=new DividendosHistoricoPK(div.getPk().getOrdpreafi(),div.getPk().getNumdiv(),div.getPk().getCodprecla(),div.getPk().getCodpretip(),div.getPk().getNumpreafi(),fechahoy,codestdiv); 
					log.info("Buscó el histórico de dividendos Ok");
					DividendosHistorico divhisnew = new DividendosHistorico();
								
					divhisnew.setPk(divhispknew);			
					
					log.info("Creó un objeto tipo DividendosHistorico");
					error="NO SE PUDO INSERTAR EL HISTORICO DEL DIVIDENDO: " +
					+div.getPk().getNumdiv()+ " DEL PRESTAMO NRO: "+div.getPk().getNumpreafi()+" Y CODPRECLA: " +
					div.getPk().getCodprecla()+" DEL AFILIADO: "+ pladet.getNumafi();
					dividendohistoricoDao.insert(divhisnew);
					log.info("Insertó el nuevo estado del histórico del dividendo");

					error="NO SE PUDO ACTUALIZAR LA CABECERA DEL DIVIDENDO: " +
					+div.getPk().getNumdiv()+ " DEL PRESTAMO NRO: "+div.getPk().getNumpreafi()+" Y CODPRECLA: " +
					div.getPk().getCodprecla()+" DEL AFILIADO: "+ pladet.getNumafi();

					div.setCodestdiv(codestdiv);
					dividendoDao.update(div);

					log.info("Actualizó el estado de la cabecera del dividendo");
					error="";
			}catch(Exception e){
				throw new CambioEstadoDividendoException(error,e);			
				
			}		

		}
}
		

