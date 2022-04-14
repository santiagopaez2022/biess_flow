/*
 * Copyright 2013 BIESS - ECUADOR
 * 
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.AprobacionCreditoSenderException;
import ec.fin.biess.creditos.pq.modelo.dto.AprobacionMasivaDto;

/**
 * Servicio para registrar los log de las transacciones realizadas.
 * 
 * @author Omar Villanueva
 * @version 1.0
 */
@Local
public interface AprobacionCreditoServicio {

	/**
	 * Servicio para registrar logs de transacciones.
	 * 
	 * @param transaction
	 * @throws LogTransactionException
	 */
	public void encolarTramiteAprobacionMasiva(AprobacionMasivaDto dato) throws AprobacionCreditoSenderException;
}
