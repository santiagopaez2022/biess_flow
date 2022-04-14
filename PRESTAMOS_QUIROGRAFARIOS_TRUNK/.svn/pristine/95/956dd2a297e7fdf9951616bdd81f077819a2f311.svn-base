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

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.TasaInteresExcepcion;
import ec.gov.iess.creditos.modelo.persistencia.TasaInteres;
import ec.gov.iess.creditos.modelo.persistencia.TasaInteresDetalle;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface TasaInteresDao extends GenericDao<TasaInteres, Serializable> {

	/**
	 * Consulta la tasa de interes con el detalle de la tasa de interes ordenao
	 * por fecha de registro
	 * 
	 * @param idtasaInteres
	 *            identificador de la tasa de interes
	 * @return la tasa de interes con detalle {@link TasaInteres} y
	 *         {@link TasaInteresDetalle}
	 * @author cvillarreal
	 */
	public TasaInteres consultaTasaInteresDetalle(String idtasaInteres) throws TasaInteresExcepcion;

}
