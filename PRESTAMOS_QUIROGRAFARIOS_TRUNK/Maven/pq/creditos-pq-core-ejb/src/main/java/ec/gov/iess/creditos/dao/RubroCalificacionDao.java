/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.RubroCalificacion;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 *
 */
@Local
public interface RubroCalificacionDao extends GenericDao<RubroCalificacion,Long> {
	
	/**
	 * lista de rubros activos
	 * 
	 * @return
	 */
	public List<RubroCalificacion> listaRubrosActivos();
	
}
