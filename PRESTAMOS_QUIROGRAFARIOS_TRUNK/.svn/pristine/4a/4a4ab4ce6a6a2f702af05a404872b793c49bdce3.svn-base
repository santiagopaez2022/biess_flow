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
import ec.gov.iess.planillaspq.dao.DividendosHistoricoDao;
import ec.gov.iess.planillaspq.modelo.persistencia.DividendosHistorico;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.DividendosHistoricoPK;


/**
 * Incluir aqui­ la descripcion de la clase.
 *  
 * @version $Revision: 1.1 $  [27/01/2009]
 * @author palvarez
 */
@Stateless											
public class DividendosHistoricoDaoImpl extends GenericEjbDao<DividendosHistorico, DividendosHistoricoPK> implements DividendosHistoricoDao{
	
	public DividendosHistoricoDaoImpl() {
		super(DividendosHistorico.class);
		// TODO Auto-generated constructor stub
	}

	public DividendosHistorico buscarPorId(DividendosHistoricoPK divhis){
		
		Query query=em.createNamedQuery("DividendosHistorico.BuscarDividendoActual");
		query.setParameter("ordpreafi", divhis.getOrdpreafi());
		query.setParameter("numdiv", divhis.getNumdiv());
		query.setParameter("codprecla", divhis.getCodprecla());
		query.setParameter("codpretip", divhis.getCodpretip());
		query.setParameter("numpreafi", divhis.getNumpreafi());
		query.setParameter("codestdiv", divhis.getCodestdiv());
		return (DividendosHistorico)query.getSingleResult();
	}
	
}
