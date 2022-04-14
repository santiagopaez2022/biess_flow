/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoEstadoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.ActualizarPrestamoEstadoHistoricoException;

/**
 * Implementacion Ejb para la Prestamo Estado Historico Servicio
 * 
 * @version 1.0
 * @author rtituana
 */

@Local
public interface PrestamoEstadoHistoricoServicio {
	/**
	*  buscar Historicos DePrestamo
	* @param numpreafi
	* @param ordpreafi
	* @param codpretip
	* @param codprecla
	* @return 
	* @author rtituana
	*/
	public List<PrestamoEstadoHistorico> buscarHistoricosDePrestamo(Long numpreafi,Long ordpreafi, Long codpretip, Long codprecla);
	
	/**
	* Actualiza el historico de un prestamo en Estado de PDA y crea uno en REC
	* @param numpreafi
	* @param ordpreafi
	* @param codpretip
	* @param codprecla
	* @return 
	* @author acantos
	*/
	public void actualizarprestamoPdaRec(Long numPreAfi,
			Long ordPreAfi, Long codPreTip, Long codPreCla, String codEstAnt,String obsTra)
			throws ActualizarPrestamoEstadoHistoricoException;
	
	public String getmotivorechazo(PrestamoPk pk);
	
	/**
	 * Actualiza el Historico de prestamos e inserta el nuevo estado historico
	 * del prestamo
	 * 
	 * @param prestamo
	 * @param codigoNuevoEstado
	 * @param observacion
	 * @throws ActualizarPrestamoEstadoHistoricoException
	 */
	void actualizarPrestamoHistorico(Prestamo prestamo,
			String codigoNuevoEstado, String observacion)
			throws ActualizarPrestamoEstadoHistoricoException;
}
