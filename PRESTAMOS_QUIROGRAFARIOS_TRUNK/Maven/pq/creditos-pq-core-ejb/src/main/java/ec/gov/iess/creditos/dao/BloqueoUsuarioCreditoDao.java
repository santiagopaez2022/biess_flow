package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.BloqueoUsuario;
import ec.gov.iess.dao.GenericDao;

/**
 * Dao para acceso a datos de la entidad BloqueoUsuario
 * 
 * @author hugo.mora
 *
 */
@Local
public interface BloqueoUsuarioCreditoDao extends GenericDao<BloqueoUsuario, String> {

	/**
	 * Guarda o actualiza un BloqueoUsuario
	 * 
	 * @param bloqueoUsuario
	 */
	void guardarOActualizar(BloqueoUsuario bloqueoUsuario);

}
