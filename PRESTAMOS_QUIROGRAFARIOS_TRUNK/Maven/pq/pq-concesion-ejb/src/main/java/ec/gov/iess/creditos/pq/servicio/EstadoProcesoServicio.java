/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.EstadoProceso;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface EstadoProcesoServicio {

	/**
	 * Conslta el estado de un tipo de solicitud y orden
	 * 
	 * @param tipoSolicitud
	 * @param orden
	 * @return
	 */
	public EstadoProceso consultarTipoSolicitudOrden(Long tipoSolicitud,
			Long orden);

	/**
	 * Consulta los estados de un tipo de solicitud y entre el orden de las
	 * mismas
	 * 
	 * @param tipoSolicitud
	 * @param ordenInicial
	 * @param ordenFinal
	 * @return lista de estados
	 */
	public List<EstadoProceso> consultarEstadosTipoSolicitudEntreOrden(
			Long tipoSolicitud, Long ordenInicial, Long ordenFinal);
	
	/**
	 * Consulta el estado de un proceso por el ID
	 * @param id
	 * @return
	 */
	public EstadoProceso consultarEstadoProcesoByPk(Long id);

}
