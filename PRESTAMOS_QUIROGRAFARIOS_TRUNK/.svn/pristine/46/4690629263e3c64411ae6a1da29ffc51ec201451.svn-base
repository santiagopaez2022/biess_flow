/**
 * 
 */
package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.enumeracion.RolSolicitante;
import ec.gov.iess.creditos.excepcion.MasDeUnPermisoMismoEstadoException;
import ec.gov.iess.creditos.modelo.persistencia.PermisoProceso;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface PermisoProcesoDao extends GenericDao<PermisoProceso, Long> {

	/**
	 * Consulta los permisos para un estado
	 * 
	 * @param tipoSolicitud
	 *            tipo de solicitud
	 * @param idEstado
	 *            identificador del estado que se encuentra
	 * @param rol
	 *            id del rol del solicitante
	 * @param idAccion
	 *            id de la accion a realizar
	 * @return retorna un permiso
	 * @throws MasDeUnPermisoMismoEstadoException
	 *             en caso de encontrar mas de una accion para el mismo estado
	 */
	public PermisoProceso permisoAccion(Long tipoSolicitud, Long idEstado,
			RolSolicitante rol, Long idAccion)
			throws MasDeUnPermisoMismoEstadoException;
}
