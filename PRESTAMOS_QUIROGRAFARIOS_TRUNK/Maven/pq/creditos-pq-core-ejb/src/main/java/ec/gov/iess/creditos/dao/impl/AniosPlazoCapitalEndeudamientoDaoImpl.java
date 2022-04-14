/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.AniosPlazoCapitalEndeudamientoDao;
import ec.gov.iess.creditos.modelo.persistencia.AniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class AniosPlazoCapitalEndeudamientoDaoImpl extends
		GenericEjbDao<AniosPlazoCapitalEndeudamiento, Serializable> implements
		AniosPlazoCapitalEndeudamientoDao {

	LoggerBiess log = LoggerBiess.getLogger(AniosPlazoCapitalEndeudamientoDaoImpl.class);

	/**
	 * @param type
	 */
	public AniosPlazoCapitalEndeudamientoDaoImpl() {
		super(AniosPlazoCapitalEndeudamiento.class);
	}

	@SuppressWarnings("unchecked")
	public List<AniosPlazoCapitalEndeudamiento> consultarEndeudamientoTipoCreditovariedadCredito(
			int idCodigoCredito, int idTipoCredito) {

		if (log.isDebugEnabled()) {

			log.debug(" Parametros :");
			log.debug("  codigo credito : " + idCodigoCredito);
			log.debug("  tipo credito : " + idTipoCredito);
		}

		Query query = em
				.createNamedQuery("AniosPlazoCapitalEndeudamiento.consultaTipoCreditoVariedadCredito");
		query.setParameter("variedadCredito", new Long(idTipoCredito));
		query.setParameter("tipoCredito", new Long(idCodigoCredito));

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AniosPlazoCapitalEndeudamiento> consultarEndeudamientoPlazoMaximo(
			int idVariedadCredito, int idTipoCredito, int plazoMaximoMeses) {
		if (log.isDebugEnabled()) {

			log.debug(" Parametros :");
			log.debug("  plazo meses : " + plazoMaximoMeses);
			log.debug("  codigo credito : " + idVariedadCredito);
			log.debug("  tipo credito : " + idTipoCredito);
		}

		Query query = em
				.createNamedQuery("AniosPlazoCapitalEndeudamiento.consultaPlazoEndeudamiento");
		query.setParameter("plazoEndeudamiento", new Long(plazoMaximoMeses));
		query.setParameter("variedadCredito", new Long(idVariedadCredito));
		query.setParameter("tipoCredito", new Long(idTipoCredito));

		return query.getResultList();
	}
	public AniosPlazoCapitalEndeudamiento consultarCoeficienteSeguroSaldos(
			Prestamo pre) {

		Query query = em
				.createNamedQuery("AniosPlazoCapitalEndeudamiento.buscarCoeSegSal");
		query.setParameter("numpreafi", pre.getPrestamoPk().getNumpreafi());
		query.setParameter("ordpreafi", pre.getPrestamoPk().getOrdpreafi());
		query.setParameter("codpretip", pre.getPrestamoPk().getCodpretip());
		query.setParameter("codprecla", pre.getPrestamoPk().getCodprecla());

		return (AniosPlazoCapitalEndeudamiento)query.getSingleResult();
	}

}
