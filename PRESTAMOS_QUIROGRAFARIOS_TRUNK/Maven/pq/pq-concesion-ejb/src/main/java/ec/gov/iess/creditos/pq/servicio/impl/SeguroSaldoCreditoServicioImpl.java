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
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.SeguroSaldoCreditoDao;
import ec.gov.iess.creditos.modelo.dto.SeguroSaldo;
import ec.gov.iess.creditos.modelo.persistencia.SeguroSaldoCredito;
import ec.gov.iess.creditos.pq.excepcion.SeguroSaldoException;
import ec.gov.iess.creditos.pq.servicio.SeguroSaldoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.SeguroSaldoCreditoServicioRemoto;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * Implementacion de la consulta y calculo del seguro de saldo en base a edad,
 * plazo, vigencia
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@Stateless
public class SeguroSaldoCreditoServicioImpl implements
		SeguroSaldoCreditoServicio, SeguroSaldoCreditoServicioRemoto {

	LoggerBiess log = LoggerBiess.getLogger(SeguroSaldoCreditoServicioImpl.class);

	@EJB
	SeguroSaldoCreditoDao seguroSaldoCreditoDao;

	CalculoCreditoHelperSingleton calculoCreditoHelper;

	public SeguroSaldoCreditoServicioImpl() {
	}

	@PostConstruct
	public void init() {
		this.calculoCreditoHelper = CalculoCreditoHelperSingleton
				.getInstancia();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SeguroSaldoCreditoServicio#calculoSeguroSaldo(int,
	 *      int, int, java.util.Date, int, java.math.BigDecimal)
	 */
	public SeguroSaldo calculoSeguroSaldo(int idTipoCredito,
			int idVariedadCredito, Date fechaNacimiento, Date fechaSolicitud,
			int plazoMeses, BigDecimal montoCredito)
			throws SeguroSaldoException {

		log.debug(" Consulta del seguro de saldo resumen **** ");
		log.debug("idTipoCredito : " + idTipoCredito);
		log.debug("idVariedadCredito : " + idVariedadCredito);
		log.debug("fechaNacimiento : " + fechaNacimiento);
		log.debug("fechaSolicitud : " + fechaSolicitud);
		log.debug("plazoMeses : " + plazoMeses);
		log.debug("montoCredito : " + montoCredito);

		int edad = calculoCreditoHelper.determinarEdad(fechaNacimiento,
				fechaSolicitud).intValue();

		int nuevoPlazo = recalcularPlazo(plazoMeses);
		log.debug(" Plazo  recalculo : " + nuevoPlazo);

		SeguroSaldoCredito seguroSaldoCredito = consultarSeguroSaldo(
				idTipoCredito, idVariedadCredito, edad, fechaSolicitud,
				nuevoPlazo);
		log.info(" idTipoCredito : " + idTipoCredito);
		log.info(" idVariedadCredito : " + idVariedadCredito);
		log.info(" edad : " + edad);
		log.info(" fechaSolicitud : " + fechaSolicitud);
		log.info(" nuevoPlazo : " + nuevoPlazo);
		SeguroSaldo seguroSaldo = new SeguroSaldo();
		seguroSaldo.setEdad(edad);
		seguroSaldo.setPlazo(plazoMeses);
		seguroSaldo.setTasaInteres(new BigDecimal(seguroSaldoCredito
				.getTasint()));
		seguroSaldo.setValor(calculoSeguroSaldoEnBaseMontoCredito(
				idTipoCredito, idVariedadCredito, fechaNacimiento,
				fechaSolicitud, plazoMeses, montoCredito));

		return seguroSaldo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SeguroSaldoCreditoServicio#calculoSeguroSaldoEnBaseMontoCredito(int,
	 *      int, java.util.Date, java.util.Date, int, java.math.BigDecimal)
	 */
	public BigDecimal calculoSeguroSaldoEnBaseMontoCredito(int idTipoCredito,
			int idVariedadCredito, Date fechaNacimiento, Date fechaSolicitud,
			int plazoMeses, BigDecimal montoCredito)
			throws SeguroSaldoException {

		log.debug(" calcula valor seguro de saldo *******");
		log.debug(" idTipoCredito " + idTipoCredito);
		log.debug(" idVariedadCredito " + idVariedadCredito);
		log.debug(" fechaNacimiento " + fechaNacimiento);
		log.debug(" fechaSolicitud " + fechaSolicitud);
		log.debug(" montoCredito " + montoCredito);
		log.debug(" plazoMeses " + plazoMeses);

		SeguroSaldoCredito seguroSaldoCredito = null;

		int edad = this.calculoCreditoHelper.determinarEdad(fechaNacimiento,
				fechaSolicitud).intValue();
		log.debug(" Edad : " + edad);

		int nuevoPlazo = recalcularPlazo(plazoMeses);

		log.debug(" Plazo  recalculo : " + nuevoPlazo);

		try {

			seguroSaldoCredito = consultarSeguroSaldo(idTipoCredito,
					idVariedadCredito, edad, fechaSolicitud, nuevoPlazo);

			if (seguroSaldoCredito == null) {
				log.error(" No existe seguro de saldo");
				throw new SeguroSaldoException("No existe parametrización del seguro de saldos para el plazo seleccionado");
			}
		} catch (NullPointerException e) {
			log.error(" No existe seguro de saldo", e);
			throw new RuntimeException(" EJB seguro saldo null", e);
		}

		BigDecimal valorSeguroSaldo = montoCredito.multiply(new BigDecimal(
				seguroSaldoCredito.getTasint()));

		valorSeguroSaldo = UtilAjusteCalculo
				.getEsacalaDigitosCalculo(valorSeguroSaldo);

		log.debug(" valorSeguroSaldo: " + valorSeguroSaldo);

		return valorSeguroSaldo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SeguroSaldoCreditoServicio#consultarSeguroSaldo(int,
	 *      int, int, java.util.Date, int)
	 */
	public SeguroSaldoCredito consultarSeguroSaldo(int idTipoCredito,
			int idVariedadCredito, int edadAniosCompletos, Date fechaSolicitud,
			int plazoMeses) throws SeguroSaldoException {

		log.debug(" Consulta del seguro de saldo **** ");
		log.debug("idTipoCredito : " + idTipoCredito);
		log.debug("idVariedadCredito : " + idVariedadCredito);
		log.debug("edadAniosCompletos : " + edadAniosCompletos);
		log.debug("fechaSolicitud : " + fechaSolicitud);
		log.debug("plazoMeses : " + plazoMeses);
		log.debug("***********************************");

		return seguroSaldoCreditoDao.consultarRangoEdadFechaVigencia(
				idTipoCredito, idVariedadCredito, fechaSolicitud,
				edadAniosCompletos,plazoMeses);
	}

	private int recalcularPlazo(int plazoMeses) {
		/*
		 * Se recalculo elplazo que no es multipo de doce se redondea al
		 * superior y por doce para el rango superior
		 */
		BigDecimal nuevoPlazo = new BigDecimal(plazoMeses);
		nuevoPlazo = nuevoPlazo.divide(new BigDecimal(12), 0,
				BigDecimal.ROUND_UP);
		nuevoPlazo = nuevoPlazo.multiply(new BigDecimal(12));

		return nuevoPlazo.intValue();
	}
}
