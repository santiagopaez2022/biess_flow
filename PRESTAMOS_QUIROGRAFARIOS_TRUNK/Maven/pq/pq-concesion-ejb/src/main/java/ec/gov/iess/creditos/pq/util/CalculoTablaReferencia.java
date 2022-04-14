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


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.persistencia.AniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.pq.excepcion.SeguroSaldoException;
import ec.gov.iess.creditos.pq.servicio.SeguroSaldoCreditoServicioRemoto;

/**
 * 
 * Clase abstracta para el calculo de la tabla de referncia de credito
 * 
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
public abstract class CalculoTablaReferencia {

	private LoggerBiess log1 = LoggerBiess.getLogger(CalculoTablaReferencia.class);

	protected CalculoCreditoHelperSingleton calculoCreditoHelper;
	protected CalculoPeriodoGracia calculoPeriodoGracia;

	// @EJB(name = "java:comp/env/ejb/SeguroSaldoCreditoServicio")
	SeguroSaldoCreditoServicioRemoto seguroSaldoCreditoServicio;

	public CalculoTablaReferencia() {
		this.calculoCreditoHelper = CalculoCreditoHelperSingleton
				.getInstancia();
		this.calculoPeriodoGracia = new CalculoPeriodoGracia();
	}

	public abstract List<PlazoCredito> determinarTablaReferencia(
			List<AniosPlazoCapitalEndeudamiento> plazoEndeudamientoList,
			BigDecimal promedioSueldo, BigDecimal tasaInteres,
			BigDecimal tasaSeguroDesgravamen, BigDecimal totalGarantia,
			BigDecimal valormaximoPrestamo, Date fechaSolicitud,
			Date fechaNacimeinto, int idTipoCredito, int idVariedadCredito);

	protected BigDecimal calculoValorSeguroSaldo(int idTipoCredito,
			int idVariedadCredito, Date fechaNacimiento, Date fechaSolicitud,
			BigDecimal montoTotalCredito, int plazoMeses)
			throws SeguroSaldoException {

		log1.debug(" calcula valor seguro de saldo");
		log1.debug(" idTipoCredito " + idTipoCredito);
		log1.debug(" idVariedadCredito " + idVariedadCredito);
		log1.debug(" fechaNacimiento " + fechaNacimiento);
		log1.debug(" fechaSolicitud " + fechaSolicitud);
		log1.debug(" montoTotalCredito " + montoTotalCredito);
		log1.debug(" plazoMeses " + plazoMeses);

		try {

			this.seguroSaldoCreditoServicio = (SeguroSaldoCreditoServicioRemoto) Util
					.getServiceBean("SeguroSaldoCreditoServicioImpl/remote");

			BigDecimal valorSegurosaldo = seguroSaldoCreditoServicio
					.calculoSeguroSaldoEnBaseMontoCredito(idTipoCredito,
							idVariedadCredito, fechaNacimiento, fechaSolicitud,
							plazoMeses, montoTotalCredito);

			log1.debug(" valorSegurosaldo Calculado: " + valorSegurosaldo);

			return valorSegurosaldo;

		} catch (Exception e) {
			log1.error(" Error llamada servicio seguroSaldoCreditoServicio : ",
					e);
			throw new RuntimeException(e);
		}

	}

}
