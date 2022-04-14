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

import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import ec.fin.biess.creditos.pq.dao.ListaBlancaDao;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementacion ListaBlancaDao
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ListaBlancaDaoEjb extends GenericEjbDao<Object, Long> implements ListaBlancaDao {

	public ListaBlancaDaoEjb() {
		super(Object.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.dao.ListaBlancaDao#consultarListaBlancaECC(java.lang.String)
	 */
	public Date consultarListaBlancaECC(String cedula) {
		try {
			final String SQL_QUERY = " select cb_fecha from cre_cuentasbancarias_tbl where "
						+ " cb_cedula = :cedula and cb_ruc_inst_financiera is null "
						+ " and cb_tipo_cuenta is null and cb_numero_cuenta is null "
						+ " and cb_estado = :estado ";
			Query query = em.createNativeQuery(SQL_QUERY);
			query.setParameter("cedula", cedula);
			query.setParameter("estado", "ECC");

			return (Date) query.getSingleResult();
		} catch (NonUniqueResultException e) {
			return null;
		} catch (NoResultException e) {
			return null;
		}
	}

}
