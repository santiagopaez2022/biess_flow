/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.EstadoProceso;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface EstadoProcesoDao extends GenericDao<EstadoProceso, Long> {

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
	 * 
	 * @param id
	 * @return
	 */
	public EstadoProceso consultarByPk(Long id);

}
