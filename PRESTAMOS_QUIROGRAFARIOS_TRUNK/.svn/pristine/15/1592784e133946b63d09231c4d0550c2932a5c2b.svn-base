package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CreditoValidacion;
import ec.gov.iess.dao.GenericDao;

/**
 * Dao para acceso a datos de la entidad CreditoValidacion
 * 
 * @author hugo.mora
 *
 */
@Local
public interface CreditoValidacionDao extends GenericDao<CreditoValidacion, Long> {

	/**
	 * Consulta un CreditoValidacion dado los datos del prestamo (codprecla, codpretip, numpreafi, ordpreafi)
	 * 
	 * @param codprecla
	 * @param codpretip
	 * @param numpreafi
	 * @param ordpreafi
	 * @return
	 */
	CreditoValidacion consultarPorPrestamo(Long codprecla, Long codpretip, Long numpreafi, Long ordpreafi);

}
