/* 
* Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
* Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
* file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
* by applicable law or agreed to in writing, software distributed under the License is 
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
* express or implied. See the License for the specific language governing permissions 
* and limitations under the License.
*/
	
package ec.gov.iess.planillaspq.servicio;

import java.util.List;
import javax.ejb.Remote;

import ec.gov.iess.planillaspq.exceptions.AjustaPlanillaHlException;
import ec.gov.iess.planillaspq.exceptions.AjustaPlanillaHostException;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaDetalleHost;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;

/**
 * Incluir aquí la descripcion de la clase.
 *  
 * @version $Revision: 1.1 $  [28/01/2009]
 * @author palvarez
 */
@Remote
public interface AjustePlanillaServicioRemote {
	
	public void AjustarPlanilla(Planillas planilla, List<PlanillaPrestamosDetalle> pladetlist,String Observacion) throws AjustaPlanillaHlException;
	public void AjustarPlanillaHost(Planillas planilla, List<PlanillaDetalleHost> pladetlisteli,String Observacion) throws AjustaPlanillaHostException;
	
}
