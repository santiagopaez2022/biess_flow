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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import ec.gov.iess.creditos.comprobante.dto.DatoComprobante;
import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoExcepcion;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoIndividualExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionAnticipadaExcepcion;
import ec.gov.iess.creditos.modelo.dto.CalculoLiquidacion;
import ec.gov.iess.creditos.modelo.persistencia.pk.ComprobantePagoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 17, 2007]
 * @author pablo
 */
@Local
public interface LiquidacionJdbc {

	/**
	 * Calcula los montos que tiene que pagarse por un prestamo en caso de realizar una liquidacion anticipada
	 * @param prestamoPk
	 * @param tipoLiquidacion
	 * @return
	 * @throws CalculoLiquidacionExcepcion
	 */
	CalculoLiquidacion calcularLiquidacion(PrestamoPk prestamoPk, TipoLiquidacion tipoLiquidacion)
			throws CalculoLiquidacionExcepcion;

	/**
	 * Genera la liquidacion anticipada
	 * 
	 * @param prestamoPk
	 * @param estadoPrestamo
	 * @param tipoLiquidacion
	 * @param tipoEmpleador
	 * @return
	 * @throws LiquidacionAnticipadaExcepcion
	 */
	BigDecimal generarLiquidacionAnticipada(PrestamoPk prestamoPk, String estadoPrestamo,
			TipoLiquidacion tipoLiquidacion, String tipoEmpleador) throws LiquidacionAnticipadaExcepcion;

	/**
	 * Genera un comprobante de pago
	 * 
	 * @param prestamoPk
	 * @param cedula
	 * @param numeroLiquidacion
	 * @param tipoEmpleador
	 * @return
	 * @throws GenerarComprobantePagoExcepcion
	 */
	ComprobantePagoPk generarComprobanteDePago(PrestamoPk prestamoPk, String cedula, BigDecimal numeroLiquidacion, String tipoEmpleador)
			throws GenerarComprobantePagoExcepcion;

	/**
	 * Genera un comprobante de pago individual
	 * @param prestamoPk
	 * @param cedula
	 * @param tipoPeriodo
	 * @param dividendos
	 * @param politicaCorporativa
	 * @param tipoEmpleador
	 * @return
	 * @throws GenerarComprobantePagoIndividualExcepcion
	 */
	ComprobantePagoPk generarComprobantePagoIndividual(PrestamoPk prestamoPk, String cedula, String tipoPeriodo,
			List<Long> dividendos, String politicaCorporativa, String tipoEmpleador) throws GenerarComprobantePagoIndividualExcepcion;
	
	/**
	 * Genera un comprobante de pago individual
	 * @param prestamoPk
	 * @param cedula
	 * @param tipoPeriodo
	 * @param dividendos
	 * @param politicaCorporativa
	 * @param tipoEmpleador
	 * @param estadoPrestamo
	 * @param tipoRecaudacion
	 * @param fechaVencimiento
	 * @return
	 * @throws GenerarComprobantePagoIndividualExcepcion
	 */
	public ComprobantePagoPk generarComprobanteConsolidado(PrestamoPk prestamoPk, String cedula, String tipoPeriodo, List<Long> dividendos,
			String politicaCorporativa, String tipoEmpleador, 
			String estadoPrestamo, String tipoRecaudacion, Date fechaVencimiento) throws GenerarComprobantePagoIndividualExcepcion;
	
	/**
	 * Genera la liquidacion anticipada
	 * 
	 * @param prestamoPk
	 * @param cedula
	 * @param estadoPrestamo
	 * @param tipoLiquidacion
	 * @param tipoEmpleador
	 * @param tipoRecaudacion
	 * @param fechaVencimiento
	 * @return
	 * @throws LiquidacionAnticipadaExcepcion
	 */
	public Map generarLiquidacionConsolidado(PrestamoPk prestamoPk, 
			String cedula, String estadoPrestamo,
			TipoLiquidacion tipoLiquidacion, String tipoEmpleador,
			String tipoRecaudacion, Date fechaVencimiento) throws LiquidacionAnticipadaExcepcion;


	/**
	 * Permite generar un comprobante individual mendiante un SP p
	 * 
	 * @param datoComprobante
	 * @return
	 * @throws GenerarComprobantePagoIndividualExcepcion
	 */
	ComprobantePagoPk generarComprobantePagoIndividualSAC(DatoComprobante datoComprobante) throws GenerarComprobantePagoIndividualExcepcion;


}