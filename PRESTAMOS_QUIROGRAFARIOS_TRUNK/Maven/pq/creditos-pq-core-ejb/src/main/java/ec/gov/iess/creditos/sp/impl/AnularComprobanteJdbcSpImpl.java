/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.sp.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ec.gov.iess.creditos.excepcion.AnularComprobanteExcepcion;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;
import ec.gov.iess.creditos.sp.AnularComprobanteJdbcSp;
import ec.gov.iess.creditos.sp.AnularComprobantePagoSp;

@Stateless
public class AnularComprobanteJdbcSpImpl implements AnularComprobanteJdbcSp {
	
	@Resource(mappedName = "java:credito-pq-DS")
	DataSource dataSource;

	@SuppressWarnings("unchecked")
	public void anularComprobante(ComprobantePago comprobantePago, String estado, String fecha, String observacion) throws AnularComprobanteExcepcion {
		AnularComprobantePagoSp anulacion = new AnularComprobantePagoSp(dataSource);
		Map results = anulacion.execute(comprobantePago, estado, fecha, observacion);
		
		String codigoError = (String) results.get("aocrespro");
		if (codigoError == null) {
			throw new AnularComprobanteExcepcion("Error inesperado al anular comprobante");
		} else {
			String mensajeError = (String) results.get("aocmenerr");
			if (!"1".equals(codigoError.trim())) {
				throw new AnularComprobanteExcepcion(mensajeError);
			}
		}
	}
}