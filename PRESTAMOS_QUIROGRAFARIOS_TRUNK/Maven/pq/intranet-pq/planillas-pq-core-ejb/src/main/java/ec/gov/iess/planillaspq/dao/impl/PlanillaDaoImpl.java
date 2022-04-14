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

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import ec.gov.iess.dao.ejb.GenericEjbDao;
import ec.gov.iess.planillaspq.dao.PlanillaDao;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillasPK;

/**
 * Incluir aqui­ la descripcion de la clase.
 *  
 * @version $Revision: 1.1 $  [27/01/2009]
 * @author palvarez
 */
@Stateless											
public class PlanillaDaoImpl extends GenericEjbDao<Planillas, PlanillasPK> implements PlanillaDao{
	
	public PlanillaDaoImpl() {
		super(Planillas.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Planillas> buscarPorRuc(PlanillasPK id){
		List<String> listaestcan=new ArrayList<String>();
		listaestcan.add("CAA");
		listaestcan.add("CAC");
		listaestcan.add("CAG");
		listaestcan.add("CAN");
		listaestcan.add("CAT");
		listaestcan.add("ANU");
		Query query=em.createNamedQuery("Planillas.BuscarPorRuc");
		query.setParameter("rucemp", id.getRucemp());
		query.setParameter("codsuc", id.getCodsuc());
		query.setParameter("aniper", id.getAniper());
		query.setParameter("mesper", id.getMesper());
		query.setParameter("codtippla", id.getCodtippla());
		query.setParameter("tipper", id.getTipper());
		query.setParameter("estcan", listaestcan);
		return (List<Planillas>)query.getResultList();
		
	}
	
	public Planillas buscarPorId(PlanillasPK id){
		Query query=em.createNamedQuery("Planillas.BuscarPorId");
		query.setParameter("Id", id);
		return (Planillas)query.getSingleResult();
	}
	
}
