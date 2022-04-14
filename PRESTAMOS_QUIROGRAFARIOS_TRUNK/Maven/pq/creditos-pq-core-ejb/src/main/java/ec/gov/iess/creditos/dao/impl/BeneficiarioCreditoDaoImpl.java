/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.BeneficiarioCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.BeneficiarioCredito;
import ec.gov.iess.creditos.modelo.persistencia.pk.BeneficiarioCreditoPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementacion para persistencia de beneficiarios de creditos quirografarios.
 * 
 * @author diego.iza.
 * 
 * @version 1.0
 */
@Stateless
public class BeneficiarioCreditoDaoImpl extends
		GenericEjbDao<BeneficiarioCredito, BeneficiarioCreditoPK> implements
		BeneficiarioCreditoDao {

	LoggerBiess log = LoggerBiess.getLogger(PrestamoDaoImpl.class);

	/**
	 * Constructor.
	 */
	public BeneficiarioCreditoDaoImpl() {
		super(BeneficiarioCredito.class);
	}

	/**
	 * @see ec.gov.iess.creditos.dao.BeneficiarioCreditoDao#obtenerPorPeriodo(java.util.Date,
	 *      java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<BeneficiarioCredito> obtenerPorPeriodo(Date fecha_ant,
			Date fecha_post) {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros:");
			log.debug(" fecha_ant:" + fecha_ant);
			log.debug(" fecha_post:" + fecha_post);
		}

		Query query = em
				.createNamedQuery("BeneficiarioCredito.buscarPorPeriodo");
		query.setParameter("fecha_ant", fecha_ant);
		query.setParameter("fecha_post", fecha_post);

		return query.getResultList();
	}

	/**
	 * @see ec.gov.iess.creditos.dao.BeneficiarioCreditoDao#obtenerPorPK(java.lang
	 *      .Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public BeneficiarioCredito obtenerPorPK(Long codprecla, Long codpretip,
			Long numpreafi, Long ordpreafi) {

		Query query = em.createNamedQuery("BeneficiarioCredito.buscarPorPK");

		query.setParameter("codprecla", codprecla);
		query.setParameter("codpretip", codpretip);
		query.setParameter("numpreafi", numpreafi);
		query.setParameter("ordpreafi", ordpreafi);

		return (BeneficiarioCredito) query.getSingleResult();
	}

}
