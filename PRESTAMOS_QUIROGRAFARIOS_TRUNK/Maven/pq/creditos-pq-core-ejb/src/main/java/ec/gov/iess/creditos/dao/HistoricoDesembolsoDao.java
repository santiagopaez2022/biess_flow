/**
 * 
 */
package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.HistoricoDesembolso;
import ec.gov.iess.dao.GenericDao;

/**
 * @author ndeveloper
 * 
 */
@Local
public interface HistoricoDesembolsoDao extends
		GenericDao<HistoricoDesembolso, Long> {

	HistoricoDesembolso consultarHistoricoDesembolsoPorNut(String dhNutdep);

}