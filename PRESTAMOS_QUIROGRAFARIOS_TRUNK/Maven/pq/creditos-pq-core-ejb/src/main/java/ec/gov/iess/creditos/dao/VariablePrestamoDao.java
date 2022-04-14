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

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.GenerarSecuenciaException;
import ec.gov.iess.creditos.modelo.persistencia.VariablePrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.VariablePrestamoPK;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * variable de prestamos
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@Local
public interface VariablePrestamoDao extends
		GenericDao<VariablePrestamo, VariablePrestamoPK> {

	VariablePrestamo getSecuencia(int idTipocredito, int idVariedadCredito) throws GenerarSecuenciaException;

}
