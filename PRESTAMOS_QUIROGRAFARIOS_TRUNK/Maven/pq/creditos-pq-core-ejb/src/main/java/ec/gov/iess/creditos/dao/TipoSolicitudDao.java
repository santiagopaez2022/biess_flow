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

import ec.gov.iess.creditos.excepcion.GenerarSecuenciaException;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitudCredito;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * Interfaz de los tipos de solicitud de credito
 * 
 * @author cvillarreal
 * 
 */
@Local
public interface TipoSolicitudDao extends
		GenericDao<TipoSolicitudCredito, Serializable> {

	/**
	 * Consulta el tipo de solictud por tipo de credito y variedad de credito
	 * 
	 * @param idTipoCredito
	 *            identificador del tipo de credito
	 * @param idVariedadCrdeito
	 *            identificador de la variedad de credito
	 * @return un objeto de modelo de persistencia {@link TipoSolicitudCredito}
	 *         caso contrario null
	 * @author cvillarreal
	 */
	public TipoSolicitudCredito consultarTipoSolicitud(int idTipoCredito,
			int idVariedadCrdeito);

	/**
	 * Consulta el tipo de solicitud por el id del tipo de la solicitud
	 * 
	 * @param idTipoSolicitud
	 *            id del tipo de solicitud
	 * @return uunobjeto persistente de {@link TipoSolicitudCredito}
	 * @author cvillarreal
	 */
	public TipoSolicitudCredito consultarTipoSolicitud(Long idTipoSolicitud);

	public TipoSolicitudCredito getSecuencia(int idTipoCredito,
			int idVariedadCredito) throws GenerarSecuenciaException;
        
        /**
         * Metodo que se encarga de retornar el valor de una secuencia dado el nombre de esta
         * @param nombreSecuencia
         * @return 
         */
        public long obtenerSecuenciaSolicitud(String nombreSecuencia);

}
