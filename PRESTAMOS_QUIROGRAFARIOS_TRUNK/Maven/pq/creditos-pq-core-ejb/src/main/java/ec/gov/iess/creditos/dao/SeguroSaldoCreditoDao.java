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
package ec.gov.iess.creditos.dao;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.SeguroSaldoCredito;
import ec.gov.iess.dao.GenericDao;

/**
 * @version 1.0 @author cvillarreal
 * 
 */
@Local
public interface SeguroSaldoCreditoDao extends
		GenericDao<SeguroSaldoCredito, Serializable> {

	/**
	 * Consulta el seguro de saldo en un rango de edad y en rango de fecha de
	 * vigencia por tipo de credito y variedad de credito
	 * 
	 * @param idTipoCredito
	 *            identificador del tipo de credito
	 * @param idvariedadCredito
	 *            identificador de la variedad de credito
	 * @param fechaSolicitud
	 *            fecha de solicitud para la consulta de la vigencia
	 * @param edad
	 *            edad actual en anios completos
	 * @return un objeto de modelo con el seguro de saldo caso contrario null
	 * @author cvillarreal
	 */
	public SeguroSaldoCredito consultarRangoEdadFechaVigencia(
			int idTipoCredito, int idvariedadCredito, Date fechaSolicitud,
			int edad,int plazoMeses);

}
