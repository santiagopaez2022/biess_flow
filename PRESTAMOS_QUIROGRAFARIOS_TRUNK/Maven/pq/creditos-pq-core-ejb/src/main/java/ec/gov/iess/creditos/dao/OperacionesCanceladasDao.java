/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.OperacionesCanceladas;
import ec.gov.iess.dao.GenericDao;

/**
 * @author walter.meza
 * 
 */
@Local
public interface OperacionesCanceladasDao extends
		GenericDao<OperacionesCanceladas, Long> {

	public OperacionesCanceladas findByCoddetsol(Long coddetsol);

	public List<OperacionesCanceladas> consultarPorNutACancelar(
			Long nutCancelacion);

}
