/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.ParametroValorDao;
import ec.gov.iess.creditos.excepcion.ParametroNoEncontradoException;
import ec.gov.iess.creditos.modelo.persistencia.ParametroValor;
import ec.gov.iess.creditos.modelo.persistencia.pk.ParametroValorPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1.4.2 $  [Nov 1, 2007]
 * @author pablo
 * @author cvillarreal
 */
@Stateless
public class ParametroValorDaoImpl extends GenericEjbDao<ParametroValor, ParametroValorPk> implements ParametroValorDao {

	private LoggerBiess log = LoggerBiess.getLogger(ParametroValorDaoImpl.class);
	
	public ParametroValorDaoImpl() {
		super(ParametroValor.class);
	}

	/**
	 * @see ec.gov.iess.creditos.ph.dao.ParametroValorDao#obtenerParametroVigente(java.lang.String)
	 */
	public ParametroValor obtenerParametroVigente(String codConFin) throws ParametroNoEncontradoException {
		try {
			Query query = em.createNamedQuery("ParametroValor.parametroVigente");
			query.setParameter("codConFin", codConFin);
			return (ParametroValor) query.getSingleResult();
		} catch (NoResultException e) {
			StringBuffer msg = new StringBuffer();
			msg.append("Parametro no encontrado[");
			msg.append(codConFin);
			msg.append("]");
			log.error(msg);
			throw new ParametroNoEncontradoException(msg.toString());
		}catch(NonUniqueResultException e){
			StringBuffer msg = new StringBuffer();
			msg.append("Parametro duplicado [");
			msg.append(codConFin);
			msg.append("]");
			log.error(msg);
			throw new ParametroNoEncontradoException(msg.toString());
		}
		
	}
	
	//Cambios acantos - pq fraudes
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.dao.ParametroValorDao#obtenerFechabiess()
	 */
	public Date obtenerFechabiess()  throws ParametroNoEncontradoException{
		try{
		Query query = em.createNativeQuery("select VALFECPOL from KSCRETCREPOL where CODPOL =:param");
		query.setParameter("param", new  String("FECBIESS") );
		return (Date) query.getSingleResult();
		}catch (Exception e){
			StringBuffer msg = new StringBuffer();
			msg.append("error al consultar el parametro fecbiess en KSCRETCREPOL");
			log.error(msg);
			throw new  ParametroNoEncontradoException(msg.toString());
		}
	}
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.dao.ParametroValorDao#obtenermMontopqparavalidacionpda()
	 */
	public BigDecimal obtenermMontopqparavalidacionpda()  throws ParametroNoEncontradoException{
		try{
		Query query = em.createNativeQuery("select VALNUMPOL from KSCRETCREPOL where CODPOL =:param");
		query.setParameter("param", new  String("MONTOPQ") );
		BigDecimal l = (BigDecimal)query.getSingleResult();
		return l;
		}catch(Exception e){
			StringBuffer msg = new StringBuffer();
			msg.append("error al consultar el parametro montopq en KSCRETCREPOL");
			log.error(msg);
			throw new  ParametroNoEncontradoException(msg.toString());
		}
	}

}