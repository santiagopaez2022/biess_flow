/* 
* Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
* Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
* file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
* by applicable law or agreed to in writing, software distributed under the License is 
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
* express or implied. See the License for the specific language governing permissions 
* and limitations under the License.
*/
	
package ec.gov.iess.planillaspq.dao;

import javax.ejb.Local;

import ec.gov.iess.dao.GenericDao;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.ComprobantePagoPK;


/**
 * Incluir aqui­ la descripcion de la clase.
 *  
 * @version $Revision: 1.1 $  [27/06/2009]
 * @author palvarez
 */
@Local											
public interface ComprobantePagoPlaDao extends GenericDao<ComprobantePagoPla, ComprobantePagoPK>{
	
	public ComprobantePagoPla buscarComprobantePorId(ComprobantePagoPK id);
	public ComprobantePagoPla buscarComprobantePorPlanilla(Planillas plani,String TipoComp);
	
	/**
	 * Busca informacion del comprobante dada la planilla
	 * 
	 * @param planilla
	 * @return
	 */
	ComprobantePagoPla buscarComprobantePorPlanillaAjustePlanilla(Planillas planilla);
	
}
