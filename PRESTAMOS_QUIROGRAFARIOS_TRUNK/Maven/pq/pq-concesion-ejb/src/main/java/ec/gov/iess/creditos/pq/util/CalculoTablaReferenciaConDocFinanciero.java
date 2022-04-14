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
package ec.gov.iess.creditos.pq.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.persistencia.AniosPlazoCapitalEndeudamiento;

/**
 * 
 * Clase para el calculo de la tabla de referencia con documento financiero, que
 * determina el valor maximo de endeudamiento, valor del docuemnto financiero, y
 * la cuota maxima del mismo.
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
public class CalculoTablaReferenciaConDocFinanciero extends
		CalculoTablaReferencia {

	/**
	 * Constructor
	 */
	public CalculoTablaReferenciaConDocFinanciero() {
		super();
	}

	@Override
	public List<PlazoCredito> determinarTablaReferencia(
			List<AniosPlazoCapitalEndeudamiento> plazoEndeudamientoList,
			BigDecimal promedioSueldo, BigDecimal tasaInteres,
			BigDecimal tasaSeguroDesgravamen, BigDecimal totalGarantia,
			BigDecimal valormaximoPrestamo, Date fechaSolicitud,
			Date fechaNacimeinto,
			int idTipoCredito,
			int idVariedadCredito) {

		throw new RuntimeException(
				" Metodo no definido para la tabla de referencia de credito con documento financiero");
	}

}
