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
import javax.persistence.Query;

import ec.fin.biess.creditos.pq.dao.ParametroCreditoDao;
import ec.fin.biess.creditos.pq.modelo.persistencia.ParametroCredito;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Ejb para la clase Parametro
 * 
 * @author Omar Villanueva 
 * @version 1.0.0
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ParametroCreditoDaoEjb extends GenericEjbDao<ParametroCredito, String> implements ParametroCreditoDao {
	
	public ParametroCreditoDaoEjb() {
		super(ParametroCredito.class);
	}
	
	/* (non-Javadoc)
	 * @see ec.gob.biess.creditos.pq.dao.ParametroDao#consultarPorCodigo(java.lang.String)
	 */
	public ParametroCredito consultarPorCodigo(String codpol) {
        try {
			Query query = em.createNamedQuery("ParametroCredito.consultarPorCodigo");
	        query.setParameter("codpol", codpol);
	
	        return (ParametroCredito)query.getSingleResult();
        } catch (NoResultException e) {
        	return null;
        }
	}

}
