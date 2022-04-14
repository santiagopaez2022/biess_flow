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

import java.util.Date;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.HistoricoAniosPlazoCapitalEndeudamiento;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface HistoricoAniosPlazoCapitalEndeudamientoDao extends
		GenericDao<HistoricoAniosPlazoCapitalEndeudamiento, Long> {

	/**
	 * Conslta el historico de endeudamiento
	 * 
	 * @param idCodigoCredito
	 * @param idTipoCredito
	 * @param fecha
	 * @param plazo
	 * @return
	 */
	public HistoricoAniosPlazoCapitalEndeudamiento consultarEndeudamientoindividual(
			int idCodigoCredito, int idTipoCredito, int plazo, Date fecha);

}
