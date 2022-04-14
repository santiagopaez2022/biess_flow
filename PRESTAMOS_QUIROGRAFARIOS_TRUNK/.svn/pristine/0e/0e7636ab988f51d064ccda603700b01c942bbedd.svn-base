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
import ec.gov.iess.planillaspq.dao.PlanillaDetalleDao;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaPrestamosDetallePK;



/**
 * Incluir aqui­ la descripcion de la clase.
 *  
 * @version $Revision: 1.1 $  [27/01/2009]
 * @author palvarez
 */
@Stateless											
public class PlanillaDetalleDaoImpl extends GenericEjbDao<PlanillaPrestamosDetalle, PlanillaPrestamosDetallePK> implements PlanillaDetalleDao{


	
	public PlanillaDetalleDaoImpl() {
		super(PlanillaPrestamosDetalle.class);
		// TODO Auto-generated constructor stub
	}
	
	public PlanillaPrestamosDetalle buscarPorId(PlanillaPrestamosDetallePK id){
		Query query=em.createNamedQuery("PlanillaDetalle.BuscarPorId");
		query.setParameter("Id", id);
		return (PlanillaPrestamosDetalle)query.getSingleResult();
		
	}

	@SuppressWarnings("unchecked")	
	public List<PlanillaPrestamosDetalle> buscarPorPlanilla(PlanillaPrestamosDetallePK id){
		Query query=em.createNamedQuery("PlanillaDetalle.BuscarPorPlanilla");
		query.setParameter("rucemp", id.getRucemp());
		query.setParameter("codsuc", id.getCodsuc());
		query.setParameter("aniper", id.getAniper());
		query.setParameter("mesper", id.getMesper());
		query.setParameter("codtippla", id.getCodtippla());
		query.setParameter("tipper", id.getTipper());
		return (List<PlanillaPrestamosDetalle>)query.getResultList();
	}

	public PlanillaPrestamosDetalle buscarPorPk(String ruc,String codsuc,long secpla,long aniper,long mesper,long numpreafi,String codtippla,String tipper){
		Query query=em.createNamedQuery("PlanillaDetalle.BuscarPorPk");
		query.setParameter("rucemp", ruc);
		query.setParameter("codsuc", codsuc);
		query.setParameter("aniper", aniper);
		query.setParameter("mesper", mesper);
		query.setParameter("codtippla", codtippla);
		query.setParameter("tipper", tipper);
		query.setParameter("secpla", secpla);
		query.setParameter("numpreafi", numpreafi);
		return (PlanillaPrestamosDetalle)query.getSingleResult();
		
	}
	
	public void eliminar(PlanillaPrestamosDetalle prd){
		em.remove(em.merge(prd));
	}
	
	
}
