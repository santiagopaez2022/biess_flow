package ec.fin.biess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;

/**
 * Servicio local para almacenar informacion de auditoria
 * 
 * @author hugo.mora
 *
 */
@Remote
public interface ServicioAuditoriaPqConcesionEjbRemote {

	/**
	 * Guarda la informacion de auditoria del componente pq-concesion-ejb
	 * 
	 * @param operacion
	 * @param parametroEvento
	 * @param ipUsuario
	 * @param parametrosReferencia
	 */
	void guardarAuditoria(OperacionEnum operacion, ParametroEvento parametroEvento, String ipUsuario, String parametrosReferencia);
	
	/**
	 * Guarda la auditoria de procesos masivos
	 * 
	 * @param operacion
	 * @param parametrosAuditoria
	 * @param parametrosReferencia
	 * @param usuario
	 */
	public void guardarAuditoriaProcesosMasivos(OperacionEnum operacion, ParametroEvento parametroEvento, 
			String usuario, String ipUsuario, String hostRemoto);

}
