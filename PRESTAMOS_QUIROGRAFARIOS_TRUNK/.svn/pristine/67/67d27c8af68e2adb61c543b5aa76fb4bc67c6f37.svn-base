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

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.HistoricoAniosPlazoCapitalEndeudamientoDao;
import ec.gov.iess.creditos.modelo.persistencia.HistoricoAniosPlazoCapitalEndeudamiento;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class HistoricoAniosPlazoCapitalEndeudamientoDaoImpl extends
		GenericEjbDao<HistoricoAniosPlazoCapitalEndeudamiento, Long> implements
		HistoricoAniosPlazoCapitalEndeudamientoDao {

	LoggerBiess log = LoggerBiess
			.getLogger(HistoricoAniosPlazoCapitalEndeudamientoDaoImpl.class);

	/**
	 * @param type
	 */
	public HistoricoAniosPlazoCapitalEndeudamientoDaoImpl() {
		super(HistoricoAniosPlazoCapitalEndeudamiento.class);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public HistoricoAniosPlazoCapitalEndeudamiento consultarEndeudamientoindividual(
			int idVariedadCredito, int idTipoCredito, int plazo, Date fecha) {
		Query query = em
				.createNamedQuery("HistoricoAniosPlazoCapitalEndeudamiento.consultaPlazoEndeudamiento");
		query.setParameter("plazoEndeudamiento", new Integer(plazo));
		query.setParameter("variedadCredito", new Integer(idVariedadCredito));
		query.setParameter("tipoCredito", new Integer(idTipoCredito));
		query.setParameter("fechaConsulta", fecha);

		try {

			return (HistoricoAniosPlazoCapitalEndeudamiento) query
					.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException(
					"Mas de un resultado para la consulta de historico plazo de endeudamiento : TipoCredito : "
							+ idTipoCredito
							+ " Variedad Credito : "
							+ idVariedadCredito
							+ " plazo : "
							+ plazo
							+ " Fecha Consulta : "
							+ (new SimpleDateFormat("dd/MM/yyyy"))
									.format(fecha));
		}
	}

}
