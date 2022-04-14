package ec.fin.biess.creditos.pq.servicio.impl;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ec.fin.biess.auditoria.cliente.exception.RegistroAuditoriaException;
import ec.fin.biess.auditoria.cliente.servicio.RegistroAuditoriaJmsServicioLocal;
import ec.fin.biess.auditoria.dto.EventoAuditoriaDto;
import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.servicio.ServicioAuditoriaPqConcesionEjbLocal;
import ec.fin.biess.creditos.pq.servicio.ServicioAuditoriaPqConcesionEjbRemote;
import ec.gov.biess.util.log.LoggerBiess;

/**
 * Servicio para almacenar informacion de auditoria
 * 
 * @author hugo.mora
 *
 */
@Stateless
public class ServicioAuditoriaPqConcesionEjbImpl implements ServicioAuditoriaPqConcesionEjbLocal, ServicioAuditoriaPqConcesionEjbRemote {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(ServicioAuditoriaPqConcesionEjbImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.ServicioAuditoriaPqConcesionEjbLocal#guardarAuditoria(ec.fin.biess.auditoria.
	 * enumeraciones.OperacionEnum, ec.fin.biess.auditoria.util.ParametroEvento, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void guardarAuditoria(OperacionEnum operacion, ParametroEvento parametroEvento, String ipUsuario,
			String parametrosReferencia) {
		try {
			RegistroAuditoriaJmsServicioLocal<EventoAuditoriaDto> logTransactionServicio = null;

			logTransactionServicio = (RegistroAuditoriaJmsServicioLocal<EventoAuditoriaDto>) new InitialContext()
					.lookup("RegistroAuditoriaPQJmsServicioImpl/local");

			EventoAuditoriaDto registro = new EventoAuditoriaDto();
			registro.setOperacionId(operacion.getCodigo());
			registro.setIpUsuario(ipUsuario);
			registro.setParametrosReferencia(parametrosReferencia);

			registro.setParametro(parametroEvento);

			logTransactionServicio.registrarAuditoria(registro);
		} catch (NamingException e) {
			LOG.error("Se presento un error al instanciar servicio de auditoria", e);
		} catch (RegistroAuditoriaException e) {
			LOG.error("Error al guardar auditoria", e);
		}
	}
	
	/**
	 * Guarda la auditoria de procesos masivos
	 * 
	 * @param operacion
	 * @param parametrosAuditoria
	 * @param parametrosReferencia
	 * @param usuario
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void guardarAuditoriaProcesosMasivos(OperacionEnum operacion, ParametroEvento parametroEvento, 
			String usuario, String ipUsuario, String hostRemoto) {
		try {
			RegistroAuditoriaJmsServicioLocal<EventoAuditoriaDto> logTransactionServicio = null;
			logTransactionServicio = (RegistroAuditoriaJmsServicioLocal<EventoAuditoriaDto>) new InitialContext()
					.lookup("RegistroAuditoriaPQJmsServicioImpl/local");

			EventoAuditoriaDto registro = new EventoAuditoriaDto();
			registro.setOperacionId(operacion.getCodigo());
			registro.setIpUsuario(ipUsuario);
			registro.setParametrosReferencia(null);
			
			parametroEvento.getParametros().put("usuario", usuario);
			parametroEvento.getParametros().put("hostremoto", hostRemoto);

			registro.setParametro(parametroEvento);
			logTransactionServicio.registrarAuditoria(registro);
			
		} catch (NamingException e) {
			LOG.error("Se presento un error al instanciar servicio de auditoria", e);
		} catch (RegistroAuditoriaException e) {
			LOG.error("Error al guardar auditoria", e);
		}
	}

}
