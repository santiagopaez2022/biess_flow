/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.sp;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.AnularComprobanteExcepcion;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;

@Local
public interface AnularComprobanteJdbcSp {

	/**
	 * Anula comprobante de pago
	 * @param comprobantePago
	 * @param estado
	 * @param fecha
	 * @param observacion
	 * @return
	 * @throws AnularComprobanteExcepcion
	 */
	public void anularComprobante(ComprobantePago comprobantePago, String estado, String fecha, String observacion) throws AnularComprobanteExcepcion;

}