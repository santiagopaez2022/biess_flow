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
import ec.gov.iess.planillaspq.dao.PagoParcialDao;
import ec.gov.iess.planillaspq.modelo.persistencia.PagoParcial;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PagoParcialPK;



/**
 * Incluir aqui­ la descripcion de la clase.
 *  
 * @version $Revision: 1.1 $  [27/06/2009]
 * @author palvarez
 */
@Stateless											
public class PagoParcialDaoImpl extends GenericEjbDao<PagoParcial, PagoParcialPK> implements PagoParcialDao{


	
	public PagoParcialDaoImpl() {
		super(PagoParcial.class);
		// TODO Auto-generated constructor stub
	}
	

	@SuppressWarnings("unchecked")
	public List<PagoParcial> buscarPorComprobante(String Codtipcompag,String Codcompag){
		Query query=em.createNamedQuery("PagoParcial.BuscarPorComprobante");
		query.setParameter("codtipcompag", Codtipcompag);
		query.setParameter("codcompag", Codcompag);
		return (List<PagoParcial>)query.getResultList();
	}

	public void eliminar(PagoParcial prd){
		em.remove(em.merge(prd));
	}
	
}
