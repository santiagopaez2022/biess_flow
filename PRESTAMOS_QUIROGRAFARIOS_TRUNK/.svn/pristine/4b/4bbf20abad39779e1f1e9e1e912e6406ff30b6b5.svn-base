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

import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * Implementacion de la simulacion del credito para el monto maximo del credito
 * quirografario
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
public class SimularCreditoMonto extends SimularCredito {

	@Override
	public Simulacion simularCredito(List<PlazoCredito> plazoCreditoList,
			BigDecimal cuota, BigDecimal monto, BigDecimal tasaInteres,
			int plazoCredito) {

		// 1. setear valores
		Simulacion simulacion = this.setValores(cuota, monto, plazoCredito);
		simulacion.setTipoSimulacion("MONTO");

		int plazoMaximoCredito = determinarPlazoMaximoTablaReferencia(plazoCreditoList);
		simulacion.setPlazoMaximoCredito(plazoMaximoCredito);

		// 1. Controla que el plazo sea mayor a cero

		if (plazoCredito <= 0) {
			simulacion
					.setObservacion("El plazo ingresado no puede ser menor o igual cero.");
			simulacion.setCalculoCredito(false);
			return simulacion;

		}

		// 1. Controla que el monto sea mayor a 10

		if (cuota.floatValue() <= 0) {
			simulacion
					.setObservacion("La cuota ingresado no puede ser menor o igual 0 USD.");
			simulacion.setCalculoCredito(false);
			return simulacion;

		}

		// 2. controla plazo maximo
		if (sobrepasaPlazoMaximo(plazoMaximoCredito, plazoCredito)) {
			simulacion
					.setObservacion("El plazo ingresado es superio al plazo permitido");
			simulacion.setCalculoCredito(false);
			return simulacion;
		}

		PlazoCredito plazoCreditoRef = consultarFilaPlazoCreditoTablaReferencia(
				plazoCreditoList, plazoCredito);

		BigDecimal cuotaMaximaCalculada = plazoCreditoRef
				.getValorMaximoComprometer();

		simulacion.setCuotaMaximaComprometer(cuotaMaximaCalculada);
		if (cuota.floatValue() > cuotaMaximaCalculada.floatValue()) {

			simulacion
					.setObservacion("La cuota ingresada es mayor a la cuota estimada a comprometer en ese plazo.");
			simulacion.setCalculoCredito(false);
			return simulacion;

		}

		BigDecimal montoCalculado = this.calculoCreditoHelper
				.determinarMontoMaximoSinAjuste(cuota, tasaInteres,
						new BigDecimal(plazoCredito));
		montoCalculado = montoCalculado.setScale(2, BigDecimal.ROUND_HALF_UP);

		if (montoCalculado.floatValue() >   UtilAjusteCalculo.ajusteNumberBaseDatos(plazoCreditoRef
				.getValorMaximoCredito()).floatValue()) {

			simulacion
					.setObservacion("Con la cuota ingresada al plazo ingresado, el monto calculado ("
							+ montoCalculado
							+ ") sobrepasa el limite maximo de credito ("
							+ plazoCreditoRef.getValorMaximoCredito() + ")");
			simulacion.setCalculoCredito(false);
			return simulacion;

		}

		// 4. Calculo monto
		simulacion
				.setMontoCredito(this.calculoCreditoHelper
						.determinarMontoMaximo(cuota, tasaInteres,
								new BigDecimal(plazoCredito), plazoCreditoRef
										.getValorMaximoCredito()));

		simulacion.setCalculoCredito(true);

		simulacion.setObservacion("Simulación Exitosa");
		return simulacion;
	}
}
