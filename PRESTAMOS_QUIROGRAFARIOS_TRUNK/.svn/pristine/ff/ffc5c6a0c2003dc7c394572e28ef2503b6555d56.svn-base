/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.creditos.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.BeneficiarioCredito;
import ec.gov.iess.creditos.modelo.persistencia.pk.BeneficiarioCreditoPK;
import ec.gov.iess.dao.GenericDao;

/**
 * Interfaz para persistencia de beneficiarios de creditos quirografarios.
 * 
 * @author diego.iza.
 * 
 * @version 1.0
 */
@Local
public interface BeneficiarioCreditoDao extends
		GenericDao<BeneficiarioCredito, BeneficiarioCreditoPK> {

	/**
	 * Obtiene beneficiarios de creditos por fecha.
	 * 
	 * @author diego.iza.
	 * 
	 * @return List<BeneficiarioCredito>.
	 */
	public List<BeneficiarioCredito> obtenerPorPeriodo(Date fecha_ant,
			Date fecha_post);

	/**
	 * Obtienen un beneficiario por su pk.
	 * 
	 * @param codprecla
	 * @param codpretip
	 * @param numpreafi
	 * @param ordpreafi
	 * 
	 * @return BeneficiarioCredito.
	 */
	public BeneficiarioCredito obtenerPorPK(Long codprecla, Long codpretip,
			Long numpreafi, Long ordpreafi);

}
