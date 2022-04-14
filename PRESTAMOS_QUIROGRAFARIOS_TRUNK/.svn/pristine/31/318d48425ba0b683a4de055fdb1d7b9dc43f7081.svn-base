/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitudCredito;

/**
 * @author cvillarreal
 * 
 */
@Remote
public interface TipoSolicitudServicioRemote {

	/**
	 * Consulta el tipo de solictud por tipo de credito y variedad de credito
	 * 
	 * @param idTipoCredito
	 *            identificador del tipo de credito
	 * @param idVariedadCrdeito
	 *            identificador de la variedad de credito
	 * @return un objeto de modelo de persistencia {@link TipoSolicitudCredito}
	 *         caso contrario null
	 * @author cvillarreal
	 */
	public TipoSolicitudCredito consultarTipoSolicitud(int idTipoCredito,
			int idVariedadCrdeito);

	/**
	 * Actualiza el nuemro de solicitud a un objeto persistente
	 * 
	 * @param tipoSolicitudCredito
	 *            objetio de modelo a incrementar
	 * @return un entero con el numero de registros actualizados
	 */
	public TipoSolicitudCredito actualizarNumeroSolicitudCredito(
			TipoSolicitudCredito tipoSolicitudCredito);
	
	
	/**
	 * Consulta el tipo de solicitud por el id del tipo de la solicitud
	 * 
	 * @param idTipoSolicitud
	 *            id del tipo de solicitud
	 * @return uunobjeto persistente de {@link TipoSolicitudCredito}
	 * @author cvillarreal
	 */
	public TipoSolicitudCredito consultarTipoSolicitud(Long idTipoSolicitud);

}
