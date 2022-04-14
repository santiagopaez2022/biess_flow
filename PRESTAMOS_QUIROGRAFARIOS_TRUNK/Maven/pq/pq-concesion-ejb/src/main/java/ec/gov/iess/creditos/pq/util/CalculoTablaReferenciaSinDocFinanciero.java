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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.dto.PlazoCreditoSinDocumentoFiduciario;
import ec.gov.iess.creditos.modelo.persistencia.AniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.pq.excepcion.SeguroSaldoException;

/**
 * 
 * Calculo de la tabla de referencia sin documento fiduciario, determina los
 * montos maximos de endeudamiento en base a la capacidad de endeudamiento.
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
public class CalculoTablaReferenciaSinDocFinanciero extends
		CalculoTablaReferencia {

	LoggerBiess log = LoggerBiess.getLogger(CalculoTablaReferenciaSinDocFinanciero.class);

	

	/**
	 * 
	 */
	public CalculoTablaReferenciaSinDocFinanciero() {
		super();
	}

	

	@Override
	public List<PlazoCredito> determinarTablaReferencia(
			List<AniosPlazoCapitalEndeudamiento> plazoEndeudamientoList,
			BigDecimal promedioSueldo, BigDecimal tasaInteres,
			BigDecimal tasaSeguroDesgravamen, BigDecimal totalGarantia,
			BigDecimal valormaximoPrestamo, Date fechaSolicitud,
			Date fechaNacimeinto, int idTipoCredito, int idVariedadCredito) {

		log.debug(" Determina tabla de referencia sin docuemnto fiduciario");
		List<PlazoCredito> plazoCreditoList = new ArrayList<PlazoCredito>();

		BigDecimal valorTopecredito = null;

		log.debug(" determina el valor maximo de credito");

		if (totalGarantia.floatValue() > valormaximoPrestamo.floatValue()) {
			valorTopecredito = valormaximoPrestamo;
			log.debug(" garantia mayor al tope : " + valorTopecredito);
		} else {
			valorTopecredito = totalGarantia;
			log.debug(" garantia menor al tope : " + valorTopecredito);
		}

		log.debug(" Itera los anios de endeudamiento determinados");
		for (AniosPlazoCapitalEndeudamiento aniosPlazoCapitalEndeudamiento : plazoEndeudamientoList) {

			log.debug(" iteracion nueva--------");
			PlazoCredito plazoNew = new PlazoCreditoSinDocumentoFiduciario();

			OpcionCredito opcionCreditoNew = new OpcionCredito();

			opcionCreditoNew.setMeses(aniosPlazoCapitalEndeudamiento.getPk()
					.getNumanipla().intValue());
			log.debug(" Meses : " + opcionCreditoNew.getMeses());

			plazoNew.setPorcentajeComprometido(new BigDecimal(
					aniosPlazoCapitalEndeudamiento.getPorcapend()));
			log.debug(" porcentaje maximo comprometer : "
					+ plazoNew.getPorcentajeComprometido());

			BigDecimal porcentajeComprometido = plazoNew
					.getPorcentajeComprometido().divide(new BigDecimal(100), 4,
							BigDecimal.ROUND_HALF_UP);
			plazoNew.setValorMaximoComprometer(porcentajeComprometido
					.multiply(promedioSueldo));
			log.debug(" valor maximo comprometido : "
					+ plazoNew.getValorMaximoComprometer());

			plazoNew.setValorMaximoCredito(this.calculoCreditoHelper
					.determinarMontoMaximo(
							plazoNew.getValorMaximoComprometer(), tasaInteres,
							new BigDecimal(opcionCreditoNew.getMeses()),
							valorTopecredito));
			log.debug(" valor maximo del credito estimado : "
					+ plazoNew.getValorMaximoCredito());

			opcionCreditoNew.setCuotaMensual(this.calculoCreditoHelper
					.determinarCuotaMaxima(plazoNew.getValorMaximoCredito(),
							tasaInteres, opcionCreditoNew.getMeses()));
			log.debug(" valaor de la cuota estimada : "
					+ opcionCreditoNew.getCuotaMensual());

			try {
				plazoNew.setValorTotalSeguroSaldo(calculoValorSeguroSaldo(
						idTipoCredito, idVariedadCredito, fechaNacimeinto,
						fechaSolicitud, plazoNew.getValorMaximoCredito(),
						opcionCreditoNew.getMeses()));
				log.debug(" valor Seguro saldos Total : "
						+ plazoNew.getValorTotalSeguroSaldo());

			} catch (SeguroSaldoException e) {
				throw new RuntimeException(e);
			}

			plazoNew.setValorTotalPeriodoGracia(this.calculoPeriodoGracia
					.determinaPeriodoGracia(fechaSolicitud, tasaInteres,
							plazoNew.getValorMaximoCredito()).getValor());

			// plazoNew.setValorTotalPeriodoGracia(new BigDecimal(0));
			log.debug(" valor Periodo de gracia Total : "
					+ plazoNew.getValorTotalPeriodoGracia());

			plazoNew.setValorLiquidoPagar(plazoNew.getValorMaximoCredito().add(
					plazoNew.getValorTotalSeguroSaldo().negate()));

			log.debug(" valor Liquido pagar Total : "
					+ plazoNew.getValorLiquidoPagar());

			plazoNew.setOpcionCredito(opcionCreditoNew);

			plazoCreditoList.add(plazoNew);

		}

		return plazoCreditoList;

	}
}
