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
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.PrestamoEstadoHistoricoDao;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoEstadoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoEstadoHistoricoPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementacion Dao Ejb para el prestamo Estado Historico
 * 
 * @version 1.0
 * @author cvillarreal
 */
@Stateless
public class PrestamoEstadoHistoricoDaoImpl 
extends GenericEjbDao<PrestamoEstadoHistorico, PrestamoEstadoHistoricoPK>
implements PrestamoEstadoHistoricoDao{

	public PrestamoEstadoHistoricoDaoImpl() {
		super(PrestamoEstadoHistorico.class);
	}
	
	/**
	 * @see ec.gov.iess.creditos.dao.PrestamoEstadoHistoricoDao#contarPorPkSinfecIni(String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla)
	 *      
	 */
	public Long contarPorPkSinfecIni(String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla) {
		String consultaContarPorPK = 
			"SELECT COUNT(*) FROM PrestamoEstadoHistorico PEH" +
			" WHERE PEH.PrestamoEstadoHistoricoPK.Numpreafi = :Numpreafi" +
			" AND PEH.PrestamoEstadoHistoricoPK.Ordpreafi = :Ordpreafi"+
			" AND PEH.PrestamoEstadoHistoricoPK.Codpretip = :Codpretip"+
			" AND PEH.PrestamoEstadoHistoricoPK.Codprecla = :Codprecla"+
			" AND PEH.PrestamoEstadoHistoricoPK.Codestpre = :Codestpre"+
			" AND PEH.Fecfin IS NULL";
		Query query = em.createQuery(consultaContarPorPK);
		query.setParameter("Numpreafi", numpreafi);
		query.setParameter("Ordpreafi", ordpreafi);
		query.setParameter("Codpretip", codpretip);
		query.setParameter("Codprecla", codprecla);
		query.setParameter("Codestpre", codestpre);
		return (Long)query.getSingleResult();
	}
	
	/**
	 * @see ec.gov.iess.creditos.dao.PrestamoEstadoHistoricoDao#obtenerPorPkSinfecIni(String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla)
	 *      
	 */
	public PrestamoEstadoHistorico obtenerPorPkSinfecIni(String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla) {
		String consultaContarPorPK = 
			"SELECT PEH FROM PrestamoEstadoHistorico PEH" +
			" WHERE PEH.PrestamoEstadoHistoricoPK.Numpreafi = :Numpreafi" +
			" AND PEH.PrestamoEstadoHistoricoPK.Ordpreafi = :Ordpreafi"+
			" AND PEH.PrestamoEstadoHistoricoPK.Codpretip = :Codpretip"+
			" AND PEH.PrestamoEstadoHistoricoPK.Codprecla = :Codprecla"+
			" AND PEH.PrestamoEstadoHistoricoPK.Codestpre = :Codestpre"+
			" AND PEH.Fecfin IS NULL";
		Query query = em.createQuery(consultaContarPorPK);
		query.setParameter("Numpreafi", numpreafi);
		query.setParameter("Ordpreafi", ordpreafi);
		query.setParameter("Codpretip", codpretip);
		query.setParameter("Codprecla", codprecla);
		query.setParameter("Codestpre", codestpre);
		return (PrestamoEstadoHistorico)query.getSingleResult();
	}
	
	/**
	 * @see ec.gov.iess.creditos.dao.PrestamoEstadoHistoricoDao#buscarHistoricosDePrestamo(Long numpreafi,Long ordpreafi, Long codpretip, Long codprecla)
	 *      
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PrestamoEstadoHistorico> buscarHistoricosDePrestamo(Long numpreafi,Long ordpreafi, Long codpretip, Long codprecla){
		List<PrestamoEstadoHistorico> prestamoEstadoHistoricoList = null;
		String consultaBuscarHistoricosDePrestamo= 
			" SELECT PEH FROM PrestamoEstadoHistorico PEH" +
			" WHERE PEH.prestamoEstadoHistoricoPK.numpreafi = :Numpreafi" +
			" AND PEH.prestamoEstadoHistoricoPK.ordpreafi = :Ordpreafi"+
			" AND PEH.prestamoEstadoHistoricoPK.codpretip = :Codpretip"+
			" AND PEH.prestamoEstadoHistoricoPK.codprecla = :Codprecla"+
			" order by PEH.prestamoEstadoHistoricoPK.fecini desc";
		try {
			Query query = em.createQuery(consultaBuscarHistoricosDePrestamo);
			query.setParameter("Numpreafi", numpreafi);
			query.setParameter("Ordpreafi", ordpreafi);
			query.setParameter("Codpretip", codpretip);
			query.setParameter("Codprecla", codprecla);
			prestamoEstadoHistoricoList = (List<PrestamoEstadoHistorico>) query.getResultList(); 
		} catch (NoResultException e) {
			 prestamoEstadoHistoricoList = null;
		}		
		return prestamoEstadoHistoricoList;
	}
	
	
	
	//NUevos Metodos...!!! Andres Cantos
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.dao.PrestamoEstadoHistoricoDao#verificaexistenciahist(java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public BigDecimal verificaexistenciahist(String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla) {
		String consultaContarPorPK = 
			"SELECT COUNT(*) FROM KSCRETPREESTHIS " +
			" WHERE NUMPREAFI = :Numpreafi" +
			" AND ORDPREAFI = :Ordpreafi"+
			" AND CODPRETIP = :Codpretip"+
			" AND CODPRECLA = :Codprecla"+
			" AND CODESTPRE = :Codestpre"+
			" AND FECFIN IS NULL";
	
		Query query = em.createNativeQuery(consultaContarPorPK);
		query.setParameter("Numpreafi", numpreafi);
		query.setParameter("Ordpreafi", ordpreafi);
		query.setParameter("Codpretip", codpretip);
		query.setParameter("Codprecla", codprecla);
		query.setParameter("Codestpre", codestpre);
		return (BigDecimal)query.getSingleResult();
	}
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.dao.PrestamoEstadoHistoricoDao#obtenerhistorico(java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public PrestamoEstadoHistorico obtenerhistorico(String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla) {
		String consultaContarPorPK = 
			"SELECT * FROM KSCRETPREESTHIS " +
			" WHERE NUMPREAFI = :Numpreafi" +
			" AND ORDPREAFI = :Ordpreafi"+
			" AND CODPRETIP = :Codpretip"+
			" AND CODPRECLA = :Codprecla"+
			" AND CODESTPRE = :Codestpre"+
			" AND FECFIN IS NULL";
		Query query = em.createNativeQuery(consultaContarPorPK,PrestamoEstadoHistorico.class);
		query.setParameter("Numpreafi", numpreafi);
		query.setParameter("Ordpreafi", ordpreafi);
		query.setParameter("Codpretip", codpretip);
		query.setParameter("Codprecla", codprecla);
		query.setParameter("Codestpre", codestpre);
		return (PrestamoEstadoHistorico)query.getSingleResult();
	}
	
}
