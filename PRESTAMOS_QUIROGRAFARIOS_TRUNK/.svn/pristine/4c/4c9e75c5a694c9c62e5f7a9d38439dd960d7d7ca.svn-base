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
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.GarantiaCesantiaDao;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.dao.ejb.GenericEjbDao;



/**
 * Implementacion de CesantiaDao para administrar la entidad Cesantia 
 *  
 * @version $Revision: 1.2 $  [Jul 26, 2007]
 * @author pmlopez
 */
@Stateless
public class GarantiaCesantiaDaoImpl extends GenericEjbDao<GarantiaCesantia, String> implements GarantiaCesantiaDao {

   

	public GarantiaCesantiaDaoImpl() {
		super(GarantiaCesantia.class);
	}

		
	/**
	* RT: cambio solicitado por Pablo Albarez
	*
	*
	*/

	
	/*@TransactionAttribute(TransactionAttributeType.SUPPORTS)*/
	/*public void update(GarantiaCesantia o) {
		super.update(o);
		em.flush();
	} */
	
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.dao.GarantiaCesantiaDao#consultarValordisponibleconExtemporaneos(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal consultarValordisponibleconExtemporaneos(String cedula) {
		try{
			String sql = "SELECT NVL(D.CC_VALORDISPONIBLECONEXT,0) AS VALOR FROM CES_CUENTAINDIVIDUALCAB_TBL D " +
			"WHERE D.CC_CEDULAID = :PARAMCEDULA AND CC_VALORDISPONIBLECONEXT > 0 AND CC_ESTADO <>'INA' ";
			
			Query query = em.createNativeQuery(sql);
			query.setParameter("PARAMCEDULA", cedula);
			
			return (BigDecimal)query. getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Date consultarFechadecuentaindividual(String cedula){
		try{
			String sql = "SELECT CC_FECHACREACION AS VALOR FROM CES_CUENTAINDIVIDUALCAB_TBL D " +
			"WHERE D.CC_CEDULAID = :PARAMCEDULA ";
			
			Query query = em.createNativeQuery(sql);
			query.setParameter("PARAMCEDULA", cedula);
			
			return (Date)query. getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}

}