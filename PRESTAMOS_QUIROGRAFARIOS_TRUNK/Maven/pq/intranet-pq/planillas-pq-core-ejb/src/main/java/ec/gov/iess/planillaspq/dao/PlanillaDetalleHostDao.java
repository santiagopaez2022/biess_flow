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

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.dao.GenericDao;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaDetalleHost;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaDetalleHostPK;

/**
 * Incluir aqui­ la descripcion de la clase.
 *  
 * @version $Revision: 1.1 $  [27/01/2009]
 * @author palvarez
 */
@Local											
public interface PlanillaDetalleHostDao extends GenericDao<PlanillaDetalleHost, PlanillaDetalleHostPK>{
	
	public PlanillaDetalleHost buscarPorId(PlanillaDetalleHostPK id);
	public List<PlanillaDetalleHost> buscarPorPlanilla(PlanillaDetalleHostPK id);	
	public void eliminar(PlanillaDetalleHost prd);
	
}
