/* 
* Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
* Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
* file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
* by applicable law or agreed to in writing, software distributed under the License is 
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
* express or implied. See the License for the specific language governing permissions 
* and limitations under the License.
*/
	
package ec.gov.iess.planillaspq.dao.impl;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.dao.ejb.GenericEjbDao;
import ec.gov.iess.planillaspq.dao.PlanillaDetalleHostDao;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaDetalleHost;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaDetalleHostPK;


/**
 * Incluir aqui­ la descripcion de la clase.
 *  
 * @version $Revision: 1.1 $  [01/07/2009]
 * @author palvarez
 */
@Stateless											
public class PlanillaDetalleHostDaoImpl extends GenericEjbDao<PlanillaDetalleHost, PlanillaDetalleHostPK> implements PlanillaDetalleHostDao{
	
	
	public PlanillaDetalleHostDaoImpl() {
		super(PlanillaDetalleHost.class);
		// TODO Auto-generated constructor stub
	}

	public PlanillaDetalleHost buscarPorId(PlanillaDetalleHostPK id){
		
		return null;
	}

	@SuppressWarnings("unchecked")	
	public List<PlanillaDetalleHost> buscarPorPlanilla(PlanillaDetalleHostPK id){
		Query query=em.createNamedQuery("PlanillaDetalleHost.BuscarPorPlanilla");
		query.setParameter("rucemp", id.getRucemp());
		query.setParameter("codsuc", id.getCodsuc());
		query.setParameter("aniper", id.getAniper());
		query.setParameter("mesper", id.getMesper());
		query.setParameter("codtippla", id.getCodtippla());
		query.setParameter("tipper", id.getTipper());
		query.setParameter("secpla", id.getSecpla());
		return (List<PlanillaDetalleHost>)query.getResultList();
	}
	public void eliminar(PlanillaDetalleHost prd){
		em.remove(em.merge(prd));
	}
	
}
