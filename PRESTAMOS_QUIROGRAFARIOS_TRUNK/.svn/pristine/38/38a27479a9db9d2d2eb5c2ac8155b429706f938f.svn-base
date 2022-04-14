package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DetalleFocalizado;
import ec.gov.iess.dao.GenericDao;

/**
 * Dao para acceso a datos de la entidad DetalleFocalizado
 * 
 * @author hugo.mora
 *
 */
@Local
public interface DetalleFocalizadoDao extends GenericDao<DetalleFocalizado, Long> {

	/**
	 * Consulta un DetalleFocalizado dado los datos del prestamo (codprecla, codpretip, numpreafi, ordpreafi) y por
	 * estado
	 * 
	 * @param codprecla
	 * @param codpretip
	 * @param numpreafi
	 * @param ordpreaf
	 * @param estado
	 * @return
	 */
	List<DetalleFocalizado> buscarPorPrestamoYEstado(Long codprecla, Long codpretip, Long numpreafi, Long ordpreaf, String estado);

}
