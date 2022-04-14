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
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.biess.creditos.pq.dao.ResiduosCesantiaDao;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Ejb para consultar residuos de cesantias de tipo RESIVM y RENIVM
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ResiduosCesantiaDaoEjb extends GenericEjbDao<Object, Long> implements ResiduosCesantiaDao {

	public ResiduosCesantiaDaoEjb() {
		super(Object.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.dao.ResiduosCesantiaDao#consultarResiduos(java.lang.String)
	 */
	public BigDecimal consultarResiduos(String cedula) {
		try {
			String sql = " select sum(cd_valortotconext - cd_valortotsinext) from ces_cuentaindividualdet_tbl where tr_id in ('RESIVM','RENIVM') and cc_cedulaid = :cedula ";
			Query query = em.createNativeQuery(sql);
			query.setParameter("cedula", cedula);

			return (BigDecimal) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
