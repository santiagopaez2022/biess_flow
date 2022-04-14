
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

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.LiquidacionPrestamoHistoricoDao;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.LiquidacionPrestamoHistoricoPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 19, 2007]
 * @author pablo
 */
@Stateless
public class LiquidacionPrestamoHistoricoDaoImpl 
extends GenericEjbDao<LiquidacionPrestamoHistorico, LiquidacionPrestamoHistoricoPK> 
implements LiquidacionPrestamoHistoricoDao {

	private static final String consultaContarPorNumLiquidacionYestado = 
		"SELECT COUNT (*) FROM LiquidacionPrestamoHistorico LPH" +
		" WHERE  LPH.liquidacionPrestamoHistoricoPk.numliqpre = :ainnumliqpre "+
		" AND LPH.liquidacionPrestamoHistoricoPk.codestliqpre = :aiccodestantliq"+
		" AND LPH.fecfin IS NULL";
	
	private static final String consultaBuscarPorNumLiquidacionYestado = 
		"SELECT LPH FROM LiquidacionPrestamoHistorico LPH" +
		" WHERE  LPH.liquidacionPrestamoHistoricoPk.numliqpre = :ainnumliqpre "+
		//" AND LPH.liquidacionPrestamoHistoricoPk.codestliqpre = :aiccodestantliq"+
		" AND LPH.fecfin IS NULL";
	
	public LiquidacionPrestamoHistoricoDaoImpl() {
		super(LiquidacionPrestamoHistorico.class);
	}
	public Long contarPorNumYCodEst(Long numeroLiquidacion, String codigoEstado){
		Query query = em.createQuery(consultaContarPorNumLiquidacionYestado);
		query.setParameter("ainnumliqpre", numeroLiquidacion);
		query.setParameter("aiccodestantliq", codigoEstado);
		return (Long)query.getSingleResult();
	}
	public List<LiquidacionPrestamoHistorico> buscarPorNumYCodEst(Long numeroLiquidacion){
		List<LiquidacionPrestamoHistorico> historicosLiquidacion=new ArrayList<LiquidacionPrestamoHistorico>();
		Query query = em.createQuery(consultaBuscarPorNumLiquidacionYestado);
		query.setParameter("ainnumliqpre", numeroLiquidacion);
		//query.setParameter("aiccodestantliq", codigoEstado);
		historicosLiquidacion=query.getResultList();
		return historicosLiquidacion;
	}


}