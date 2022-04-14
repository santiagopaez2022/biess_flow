/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.helper.simulacion;

import java.math.BigDecimal;
import java.util.List;

import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * Implementacion de la simulacion del credito para la cuota maxima del credito
 * quirografario
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
public class SimularCreditoCuota extends SimularCredito {

	@Override
	public Simulacion simularCredito(List<PlazoCredito> plazoCreditoList,
			BigDecimal cuota, BigDecimal monto, BigDecimal tasaInteres,
			int plazoCredito) {

		// 0. setear valores
		Simulacion simulacion = this.setValores(cuota, monto, plazoCredito);

		simulacion.setTipoSimulacion("CUOTA");

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

		if (monto.floatValue() < 1) {
			simulacion
					.setObservacion("El monto ingresado no puede ser menor a 1 USD.");
			simulacion.setCalculoCredito(false);
			return simulacion;

		}

		PlazoCredito plazoCreditoRef = consultarFilaPlazoCreditoTablaReferencia(
				plazoCreditoList, plazoCredito);

		// 2. controla plazo maximo
		if (sobrepasaPlazoMaximo(plazoMaximoCredito, plazoCredito)) {
			if (plazoCreditoRef == null) {
				simulacion
						.setObservacion("No cumple monto, garantías, capacidad de pago o plazo para los parámetros ingresados.");
				simulacion.setCalculoCredito(false);
				return simulacion;
			} else {
				simulacion
						.setObservacion("El plazo ingresado es superior al plazo permitido.");
				simulacion.setCalculoCredito(false);
				return simulacion;
			}
		}

		// 3. si el monto ingresado es mayor al monto maximo
		BigDecimal montoMaximoCalculado = UtilAjusteCalculo.ajusteNumberBaseDatos(
			calculoCreditoHelper
				.determinarMontoMaximo(plazoCreditoRef
						.getValorMaximoComprometer(), tasaInteres,
						new BigDecimal(plazoCredito), plazoCreditoRef
								.getValorMaximoCredito()));

		simulacion.setMontoMaximoCredito(montoMaximoCalculado);
		if (monto.floatValue() > montoMaximoCalculado.floatValue()) {

			simulacion
					.setObservacion("El monto ingresado es mayor al monto máximo permitido en ese plazo.");
			simulacion.setCalculoCredito(false);
			return simulacion;

		}

		// 4. El monto mínimo no debe ser menor al monto de la orden
		if (plazoCreditoRef.getMontoMaximoOrden() != null) {
			if (monto.floatValue() < plazoCreditoRef.getValorMaximoCredito()
					.floatValue()) {
				simulacion
						.setObservacion("El monto ingresado es menor al monto mínimo permitido para la orden de compra:"
								+ plazoCreditoRef.getMontoMaximoOrden()
										.floatValue());
				simulacion.setCalculoCredito(false);
				return simulacion;
			}
		}

		simulacion.setCuotaCredito(this.calculoCreditoHelper
				.determinarCuotaMaxima(monto, tasaInteres, plazoCredito));
		simulacion.setCalculoCredito(true);

		simulacion.setObservacion("Simulación Exitosa");
		return simulacion;

	}
}
