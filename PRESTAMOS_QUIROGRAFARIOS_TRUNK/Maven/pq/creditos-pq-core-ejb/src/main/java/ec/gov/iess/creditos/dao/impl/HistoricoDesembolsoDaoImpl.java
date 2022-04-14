/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.HistoricoDesembolsoDao;
import ec.gov.iess.creditos.modelo.persistencia.HistoricoDesembolso;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author ndeveloper
 * 
 */
@Stateless
public class HistoricoDesembolsoDaoImpl extends
		GenericEjbDao<HistoricoDesembolso, Long> implements
		HistoricoDesembolsoDao {

	LoggerBiess log = LoggerBiess.getLogger(HistoricoDesembolsoDaoImpl.class);

	/**
	 * @param type
	 */
	public HistoricoDesembolsoDaoImpl() {
		super(HistoricoDesembolso.class);
	}

	public HistoricoDesembolso consultarHistoricoDesembolsoPorNut(
			String dhNutdep) {
		Query q = em.createNamedQuery(
				"HistoricoDesembolso.consultarHistoricoDesembolsoPorNut")
				.setParameter("dhNutdep", dhNutdep);

		@SuppressWarnings("unchecked")
		List<HistoricoDesembolso> lista = q.getResultList();
		if (lista != null && !lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}
}
