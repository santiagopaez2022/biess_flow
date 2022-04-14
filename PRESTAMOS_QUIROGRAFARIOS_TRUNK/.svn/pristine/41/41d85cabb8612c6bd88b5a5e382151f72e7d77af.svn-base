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
package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.PrestamoEstadoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoEstadoHistoricoPK;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * Interfaz Dao para los préstamos estado historicos
 * 
 * @version 1.0
 * @author rtituana 03/10/2011
 * 
 */
@Local
public interface PrestamoEstadoHistoricoDao  
extends GenericDao<PrestamoEstadoHistorico, PrestamoEstadoHistoricoPK>{
	/**
	 * Obtiene el numero de estados sin fecha inicial
	 * @param codestpre 
	 * @param numpreafi
	 * @param ordpreafi
	 * @param codpretip 
     * @param codprecla	 
	 * @return
     * @author rtituana
	 */
	public Long contarPorPkSinfecIni(String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla);
	/**
	 * Obtiene el estado del pretamos historico sin fecha inicial
	 * @param codestpre 
	 * @param numpreafi
	 * @param ordpreafi
	 * @param codpretip 
     * @param codprecla	 
	 * @return
     * @author rtituana
	 */
	public PrestamoEstadoHistorico obtenerPorPkSinfecIni(String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla);
	/**
	 * Obtiene lista de estados del pretamos historico
	 * @param numpreafi 
	 * @param ordpreafi
	 * @param codpretip
	 * @param codprecla  
	 * @return
     * @author rtituana
	 */
	public List<PrestamoEstadoHistorico> buscarHistoricosDePrestamo(Long numpreafi,Long ordpreafi, Long codpretip, Long codprecla);
	
	
	//nuevos metodos andres cantos
	/**
	 * @author acantos
	 * verifica si existe un historico de un prestamo
	 * @param codestpre
	 * @param numpreafi
	 * @param ordpreafi
	 * @param codpretip
	 * @param codprecla
	 * @return
	 */
	public BigDecimal verificaexistenciahist(String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla);
	/**
	 * @author acantos
	 * recupera el historico de un prestamo
	 * @param codestpre
	 * @param numpreafi
	 * @param ordpreafi
	 * @param codpretip
	 * @param codprecla
	 * @return
	 */
	public PrestamoEstadoHistorico obtenerhistorico(String codestpre, Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla);
	
}
