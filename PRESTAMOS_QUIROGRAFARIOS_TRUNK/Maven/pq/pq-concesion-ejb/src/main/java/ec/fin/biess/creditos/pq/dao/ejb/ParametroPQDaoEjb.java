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

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import ec.fin.biess.creditos.pq.dao.ParametroPQDao;
import ec.fin.biess.creditos.pq.modelo.persistencia.ParametroPQ;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Ejb para la clase ParametroPQ
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ParametroPQDaoEjb extends GenericEjbDao<ParametroPQ, Integer> implements ParametroPQDao {

	public ParametroPQDaoEjb() {
		super(ParametroPQ.class);
	}

	/*
	 * Referencia
	 * 
	 * @see ec.fin.biess.creditos.pq.dao.ParametroPQDao#consultarPorCodigo(java.lang.String, java.lang.Integer)
	 */
	public ParametroPQ consultarPorCodigo(String codigo, Integer idCatalogo) {
		try {
			StringBuilder SQL_QUERY = new StringBuilder();
			SQL_QUERY.append(" select o from ParametroPQ o where o.idCatalogo = :idCatalogo ");
			SQL_QUERY.append(" and o.estado = :estado and o.codigo = :codigo ");
			Query query = em.createQuery(SQL_QUERY.toString());
			query.setParameter("idCatalogo", idCatalogo);
			query.setParameter("estado", "A");
			query.setParameter("codigo", codigo);

			return (ParametroPQ) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}
	}

}
