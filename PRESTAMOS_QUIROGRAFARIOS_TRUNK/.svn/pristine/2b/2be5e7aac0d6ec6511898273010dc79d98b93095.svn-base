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

import javax.ejb.Stateless;
import javax.persistence.Query;
import ec.gov.iess.dao.ejb.GenericEjbDao;
import ec.gov.iess.planillaspq.dao.ComprobantePagoPlaDao;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.ComprobantePagoPK;




/**
 * Incluir aqui­ la descripcion de la clase.
 *  
 * @version $Revision: 1.1 $  [27/06/2009]
 * @author palvarez
 */
@Stateless											
public class ComprobantePagoDaoPlaImpl extends GenericEjbDao<ComprobantePagoPla, ComprobantePagoPK> 
			implements ComprobantePagoPlaDao{
	
	public ComprobantePagoDaoPlaImpl() {
		super(ComprobantePagoPla.class);
	}

	public ComprobantePagoPla buscarComprobantePorId(ComprobantePagoPK id){
		Query query=em.createNamedQuery("Comprobante.BuscarPorId");
		query.setParameter("Id", id);
		return (ComprobantePagoPla)query.getResultList();
	}

	public ComprobantePagoPla buscarComprobantePorPlanilla(Planillas plani,String TipoComp)
	 {
		Query query=em.createNamedQuery("Comprobante.buscarComprobantePorPlanilla");
		query.setParameter("rucemp", plani.getPk().getRucemp());
		query.setParameter("codsuc", plani.getPk().getCodsuc());
		query.setParameter("aniper", plani.getPk().getAniper());
		query.setParameter("mesper", plani.getPk().getMesper());
		query.setParameter("codtippla", plani.getPk().getCodtippla());
		query.setParameter("tipper", plani.getPk().getTipper());
		query.setParameter("esttippla", plani.getEsttippla());
		query.setParameter("tipcomp", TipoComp);
		query.setParameter("secpla", plani.getPk().getSecpla());
		return (ComprobantePagoPla)query.getSingleResult();
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.planillaspq.dao.ComprobantePagoPlaDao#buscarComprobantePorPlanillaAjustePlanilla(ec.gov.iess.
	 * planillaspq.modelo.persistencia.Planillas)
	 */
	@Override
	public ComprobantePagoPla buscarComprobantePorPlanillaAjustePlanilla(Planillas planilla) {
		Query query = em.createNamedQuery("Comprobante.buscarComprobantePorPlanillaAjustePlanilla");
		query.setParameter("rucemp", planilla.getPk().getRucemp());
		query.setParameter("codsuc", planilla.getPk().getCodsuc());
		query.setParameter("aniper", planilla.getPk().getAniper());
		query.setParameter("mesper", planilla.getPk().getMesper());
		query.setParameter("codtippla", planilla.getPk().getCodtippla());
		query.setParameter("tipper", planilla.getPk().getTipper());
		query.setParameter("esttippla", planilla.getEsttippla());
		query.setParameter("secpla", planilla.getPk().getSecpla());
		return (ComprobantePagoPla) query.getSingleResult();
	}

}
