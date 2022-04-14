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
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.hibernate.QueryParameterException;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.SeguroSaldoCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.SeguroSaldoCredito;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * 
 * Implementacion EJb DAo para los datos del seguro de saldo
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@Stateless
public class SeguroSaldoCreditoDaoImpl extends
		GenericEjbDao<SeguroSaldoCredito, Serializable> implements
		SeguroSaldoCreditoDao {

	LoggerBiess log = LoggerBiess.getLogger(SeguroSaldoCreditoDaoImpl.class);

	/**
	 * @param type
	 */
	public SeguroSaldoCreditoDaoImpl() {
		super(SeguroSaldoCredito.class);
	}

	public SeguroSaldoCredito consultarRangoEdadFechaVigencia(
			int idTipoCredito, int idvariedadCredito, Date fechaSolicitud,
			int edad, int plazoMeses) {

		log.debug(" Parametros consultarRangoEdadFechaVigencia:");
		log.debug(" idTipoCredito :" + idTipoCredito);
		log.debug(" idvariedadCredito :" + idvariedadCredito);
		log.debug(" fechaSolicitud :" + fechaSolicitud);
		log.debug(" edad :" + edad);
		log.debug(" plazoMeses :" + plazoMeses);

		Query qm = em
				.createNamedQuery("SeguroSaldoCredito.consultarRangoEdadPlazo");
		qm.setParameter("fechaSolicitud", fechaSolicitud);
		qm.setParameter("edadActual", new Long(edad));
		qm.setParameter("tipoCredito", new Long(idTipoCredito));
		qm.setParameter("varidadCredito", new Long(idvariedadCredito));
		qm.setParameter("plazoMeses", new Long(plazoMeses));

		try {
			log.debug(" Ejecuta consulta");
			return (SeguroSaldoCredito) qm.getSingleResult();
		} catch (QueryParameterException e) {
			log.error(" Error de parametros consulta", e);
			throw new RuntimeException(e);
		} catch (NoResultException e) {
			log.warn(" No existen datos seguro de saldo");
			return null;
		} catch (NonUniqueResultException e) {
			log.error(" Mas de un resultado datos seguro de saldo.", e);
			throw new RuntimeException(
					"Mas de un resultado en el seguro de saldo", e);
		}
	}

}
