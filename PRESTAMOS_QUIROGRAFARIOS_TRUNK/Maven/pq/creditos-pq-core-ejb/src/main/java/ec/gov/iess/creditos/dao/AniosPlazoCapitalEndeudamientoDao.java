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
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.AniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface AniosPlazoCapitalEndeudamientoDao extends
		GenericDao<AniosPlazoCapitalEndeudamiento, Serializable> {

	/**
	 * consulta los periodos de endeudamiento por tipo de cerdito y variedad de
	 * credito
	 * 
	 * @param idCodigoCredito
	 *            identificador de la variedad de credito
	 * @param idTipoCredito
	 *            identificador del tipo de credito
	 * @return una lista de anios de endeudamiento
	 * @author cvillarreal
	 */
	public List<AniosPlazoCapitalEndeudamiento> consultarEndeudamientoTipoCreditovariedadCredito(
			int idCodigoCredito, int idTipoCredito);
	
	/**
	 * consulta los periodos de endeudamiento por tipo de cerdito y variedad de
	 * credito
	 * 
	 * @param idCodigoCredito
	 *            identificador de la variedad de credito
	 * @param idTipoCredito
	 *            identificador del tipo de credito
	 * @return una lista de anios de endeudamiento
	 * @author cvillarreal
	 */
	public List<AniosPlazoCapitalEndeudamiento> consultarEndeudamientoPlazoMaximo(
			int idVariedadCredito, int idTipoCredito,int plazoMaximoMeses);
	/**
	 * consulta del coeficieficiente de seguro de saldos para el credito
	 * 
	 * @param PrestamoPk
	 *            clave primaria del credito
	 * @return el objeto AniosPlazoCapitalEndeudamiento
	 */
	public AniosPlazoCapitalEndeudamiento consultarCoeficienteSeguroSaldos(
			Prestamo pre);

}
