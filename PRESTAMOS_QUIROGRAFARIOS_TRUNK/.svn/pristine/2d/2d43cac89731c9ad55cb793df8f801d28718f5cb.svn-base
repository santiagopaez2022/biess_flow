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

import ec.gov.iess.creditos.enumeracion.EstadoLiquidacion;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.GenericDao;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 19, 2007]
 * @author pablo
 */
@Local
public interface LiquidacionPrestamoDao extends GenericDao<LiquidacionPrestamo, Long> {

	public List<LiquidacionPrestamo> obtenerLiquidacionPorPrestamoYEstado(PrestamoPk prestamoPk,
			EstadoLiquidacion estadoLiquidacion);
	
	public Long contarPorNumLiq(Long numeroLiquidacion);
	
	public LiquidacionPrestamo buscarPorNumLiq(Long numeroLiquidacion);
	
	/**
	 * Permite buscar una liquidacion dado el numero de liquidacion, el tipo de liquidacion y el estado de la
	 * liquidacion
	 * 
	 * @param numeroLiquidacion
	 * @param tipoLiquidacion
	 * @param estadoLiquidacion
	 * @return Devuelve un objeto LiquidacionPrestamo si no tiene datos devuelve null
	 */
	LiquidacionPrestamo buscarLiquidacionPorNumeroTipoEstado(Long numeroLiquidacion, String tipoLiquidacion, EstadoLiquidacion estadoLiquidacion);
	
	public List<LiquidacionPrestamo> obtenerLiquidacionPorAfiliadoYEstado(String numeroAfiliado,
			EstadoLiquidacion estadoLiquidacion);
	

}