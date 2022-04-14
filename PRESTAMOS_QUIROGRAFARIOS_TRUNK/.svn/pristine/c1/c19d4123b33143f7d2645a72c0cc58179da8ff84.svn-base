/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.LiquidacionPrestamoHistoricoPK;
import ec.gov.iess.dao.GenericDao;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 19, 2007]
 * @author pablo
 */
@Local
public interface LiquidacionPrestamoHistoricoDao extends GenericDao<LiquidacionPrestamoHistorico, LiquidacionPrestamoHistoricoPK> {

	public Long contarPorNumYCodEst(Long numeroLiquidacion, String codigoEstado);
	
	public List<LiquidacionPrestamoHistorico> buscarPorNumYCodEst(Long numeroLiquidacion);


}