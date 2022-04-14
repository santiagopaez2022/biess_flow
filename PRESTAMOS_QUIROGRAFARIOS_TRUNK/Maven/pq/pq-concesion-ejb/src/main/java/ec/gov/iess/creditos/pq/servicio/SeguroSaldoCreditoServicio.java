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
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.dto.SeguroSaldo;
import ec.gov.iess.creditos.modelo.persistencia.SeguroSaldoCredito;
import ec.gov.iess.creditos.pq.excepcion.SeguroSaldoException;

/**
 * 
 * Servicio para la consulta y detrminacion del seguro de saldo
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@Local
public interface SeguroSaldoCreditoServicio {

	/**
	 * Consulta el seguro de saldo en base de edad y plazo de endeudaiento
	 * 
	 * @param idTipoCredito
	 *            tipo de credito
	 * @param idVariedadCredito
	 *            variedad de credito
	 * @param edadAniosCompletos
	 *            edad en anios completos
	 * @param fechaVigencia
	 *            fecha de consulta
	 * @param plazoMeses
	 *            plazo en meses
	 * @return un objeto con los datos del credito consultado caso contrario
	 *         null
	 * @author cvillarreal
	 */
	public SeguroSaldoCredito consultarSeguroSaldo(int idTipoCredito,
			int idVariedadCredito, int edadAniosCompletos, Date fechaSolicitud,
			int plazoMeses)
			throws SeguroSaldoException;

	/**
	 * 
	 * Calcula el valor del seguro de saldo a un monto de credito ya calculado
	 * 
	 * @param idTipoCredito
	 *            identificador del tipo de credito
	 * @param idVariedadCredito
	 *            identificador de la variedad de credito
	 * @param fechaNacimiento
	 *            fecha de nacimeinto del solicitante
	 * @param fechaVigencia
	 *            fecha de vigencia del credito
	 * @param plazoMeses
	 *            plazo en meses del credito
	 * @param montoCredito
	 *            valor total del credito
	 * @return el valor del seguro de saldo
	 * @throws SeguroSaldoException
	 * @author cvillarreal
	 */
	public BigDecimal calculoSeguroSaldoEnBaseMontoCredito(int idTipoCredito,
			int idVariedadCredito, Date fechaNacimiento, Date fechaSolicitud,
			int plazoMeses, BigDecimal montoCredito)
			throws SeguroSaldoException;

	/**
	 * calcula el seguro de saldo con el detalle del calculo, el interes, edad,
	 * plazo, y valor
	 * 
	 * @param idTipoCredito
	 *            identificador del tipo de credito
	 * @param idVariedadCredito
	 *            identificador de la variedad de cerdito
	 * @param edad
	 *            edad en anios cunplidos
	 * @param fechaVigencia
	 *            fecahde vigencia de la consulta
	 * @param plazoMeses
	 *            plazo del credito
	 * @param montoCredito
	 *            monto total del credito
	 * @return un objeto de modelo con el resumen del seguro de saldo
	 *         {@link SeguroSaldo}
	 * @throws SeguroSaldoException
	 * @author cvillarreal
	 */
	public SeguroSaldo calculoSeguroSaldo(int idTipoCredito,
			int idVariedadCredito, Date fechaNacimiento, Date fechaSolicitud,
			int plazoMeses, BigDecimal montoCredito)
			throws SeguroSaldoException;

}
