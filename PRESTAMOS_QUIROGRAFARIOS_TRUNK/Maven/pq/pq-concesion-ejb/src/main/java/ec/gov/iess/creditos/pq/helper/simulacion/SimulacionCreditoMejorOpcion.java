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

package ec.gov.iess.creditos.pq.helper.simulacion;

import java.math.BigDecimal;
import java.util.List;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

public class SimulacionCreditoMejorOpcion extends SimularCredito {

	LoggerBiess log = LoggerBiess.getLogger(SimulacionCreditoMejorOpcion.class);

	@Override
	public Simulacion simularCredito(List<PlazoCredito> plazoCreditoList,
			BigDecimal cuota, BigDecimal monto, BigDecimal tasaInteres,
			int plazoCredito) {

		// 0. setear valores
		Simulacion simulacion = this.setValores(cuota, monto, plazoCredito);

		simulacion.setTipoSimulacion("MEJOR_OPCION");
		log.debug("MEJOR OPCION");

		// 1: no cumple monto minimo, no hace simulacion
		if (monto.floatValue() < 1) {
			log.debug("Error:  Monto inferior a 1 -->" + monto.floatValue());
			simulacion
					.setObservacion("El monto ingresado no puede ser menor a 1 USD.");
			simulacion.setCalculoCredito(false);
			return simulacion;
		}

		// 2: no cumple monto minimo, no hace simulacion
		if (cuota.floatValue() <= 0) {
			log.debug("Error:  Cuota ingresada <= 0 -->" + cuota.floatValue());
			simulacion.setObservacion("La cuota ingresada no puede ser cero");
			simulacion.setCalculoCredito(false);
			return simulacion;

		}
		// 3: calcula plazo de la mejor opcion
		BigDecimal interesMensual = tasaInteres.divide(new BigDecimal(12),
				BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100),
				BigDecimal.ROUND_HALF_UP);
		interesMensual = UtilAjusteCalculo
				.getEsacalaDigitosCalculo(interesMensual);
		log.debug("Calcula interes: " + interesMensual.floatValue());

		// 4: Calcula plazo simulado
		BigDecimal plazoEstimado = new BigDecimal(
				(-java.lang.Math.log(1 - (interesMensual.floatValue()
						* monto.floatValue() / cuota.floatValue())) / (java.lang.Math
						.log(1 + interesMensual.floatValue()))));
		int plazoCalculado = plazoEstimado.intValue();
		log.debug("Plazo Calculado: " + plazoCalculado);

		// 5. controla plazo maximo en base al plazo simulado
		if (sobrepasaPlazoMaximo(plazoCalculado, plazoCredito)) {
			log.debug("Error: plazo ingresado superior a plazo máximo ");

			simulacion
					.setObservacion("El plazo ingresado es superior al plazo permitido");
			simulacion.setCalculoCredito(false);
			return simulacion;
		}

		// 6: en base al nuevo plazo consulto la fila que corresponde
		PlazoCredito plazoCreditoRef = consultarFilaPlazoCreditoTablaReferencia(
				plazoCreditoList, plazoCalculado);
		log.debug("Consulta fila de plazo de credito");

		// 7: recalcula la cuota máxima en base a plazo calculado y monto máximo
		BigDecimal cuotaCalculada = calculoCreditoHelper.determinarCuotaMaxima(
				plazoCreditoRef.getOpcionCredito().getValorTotalCredito(),
				tasaInteres, plazoCalculado);
		log.debug("Recalcula cuota máxima con plazo calculado y monto máximo: "
				+ cuotaCalculada.floatValue());

		// 8:comparó si se pasa de la nueva cuota máxima
		if (cuotaCalculada.floatValue() > plazoCreditoRef
				.getValorMaximoComprometer().floatValue()) {
			cuotaCalculada = plazoCreditoRef.getValorMaximoComprometer();
			log.debug("Cuota ajustada a maximo a comprometer: "
					+ cuotaCalculada.floatValue());
		}

		// 9: recalculo monto en base a la nueva cuota y al nuevo plazo
		BigDecimal montoCalculado = calculoCreditoHelper.determinarMontoMaximo(
				cuotaCalculada, tasaInteres, new BigDecimal(plazoCalculado),
				plazoCreditoRef.getValorMaximoCredito());
		log.debug("Monto ajustado en base a plazo calc y cuota max : "
				+ montoCalculado.floatValue());

		log.debug("Actualiza valores ");
		simulacion.setCuotaCredito(cuotaCalculada);
		simulacion.setCuotaMaximaComprometer(plazoCreditoRef
				.getValorMaximoComprometer());
		simulacion.setMontoCredito(montoCalculado);
		simulacion.setMontoMaximoCredito(plazoCreditoRef
				.getValorMaximoCredito());
		simulacion.setPlazoCredito(plazoCalculado);
		simulacion.setCalculoCredito(true);
		simulacion.setObservacion("Simulación Exitosa");
		log.debug("Setea valores ");		
		return simulacion;

	}
}
