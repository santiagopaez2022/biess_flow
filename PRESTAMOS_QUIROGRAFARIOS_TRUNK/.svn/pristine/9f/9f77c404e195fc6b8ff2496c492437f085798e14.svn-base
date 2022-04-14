/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.dao.ejb;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import ec.fin.biess.creditos.pq.dao.CambioClaveDao;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Ejb para consultar si el asegurado ha realizado un cambio de clave
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CambioClaveDaoEjb extends GenericEjbDao<Object, Long> implements CambioClaveDao {

	public CambioClaveDaoEjb() {
		super(Object.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.dao.ResiduosCesantiaDao#consultarResiduos(java.lang.String)
	 */
	public boolean afiliadoCambioClave(String cedula) {
		try {
			final String SQL_QUERY = " select count(1) from acc_solicitudclave_tbl s, acc_auditorias_tbl a where trunc(s.sc_fecha) >= to_date('27/12/2013','dd/mm/yyyy') "
					+ " and to_char(s.sc_codigo) = a.au_claveprimaria_1 and a.au_valor_anterior = 'REG' and a.au_valor_nuevo = 'APR' "
					+ " and us_cedula = :cedula ";
			Query query = em.createNativeQuery(SQL_QUERY);
			query.setParameter("cedula", cedula);
			if (((BigDecimal) query.getSingleResult()).compareTo(BigDecimal.ZERO) == 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

		return true;
	}

}
