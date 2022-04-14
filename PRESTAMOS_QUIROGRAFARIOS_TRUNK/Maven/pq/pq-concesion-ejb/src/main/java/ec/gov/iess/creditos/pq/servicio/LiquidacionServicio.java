/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.enumeracion.EstadoLiquidacion;
import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.excepcion.CalculoValorRealFondosExcepcion;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionAnticipadaExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionFondosReservaExcepcion;
import ec.gov.iess.creditos.modelo.dto.CalculoLiquidacion;
import ec.gov.iess.creditos.modelo.dto.DatosSituacionPrestamo;
import ec.gov.iess.creditos.modelo.dto.RequisitosCruce;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosRecaudacion;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.GenerarComprobanteIndividualExcepcion;
import ec.gov.iess.creditos.pq.excepcion.NoTieneLiquidacionVigenteException;
import ec.gov.iess.creditos.excepcion.DebitoAutomaticoExcepcion;
/**
 * Incluir aquÃ­ la descripciÃ³n de la clase.
 *  
 * @version $Revision: 1.5 $  [Sep 17, 2007]
 * @author pablo
 */
@Local
public interface LiquidacionServicio {

	/**
	 * Calcula el valor real de la Liquidación con Fondos de Reserva
	 */
	public CalculoLiquidacion calcularLiquidacionFondos(ValidarRequisitosRecaudacion valReqCruces) throws LiquidacionFondosReservaExcepcion;	
	/**
	 * Calcula el valor real de Fondos de Reserva
	 */
	public BigDecimal calcularValorFondos(PrestamoPk prestamoPk,String cedula,String cumpleImposiciones) throws CalculoValorRealFondosExcepcion;

	/**
	 * Genera la liquidacion anticipada de un prestamo
	 */
	BigDecimal generarLiquidacionAnticipada(ValidarRequisitosRecaudacion valReqLiqAnt, TipoLiquidacion tipoLiquidacion)
			throws LiquidacionAnticipadaExcepcion, GenerarComprobantePagoExcepcion;

	/**
	 * Genera la liquidacion con fondes de reserva
	 */
	public BigDecimal generarLiquidacionFondos(ValidarRequisitosRecaudacion valReqCruces)
			throws LiquidacionFondosReservaExcepcion;	
	
	/**
	 * Calcula los valor a pagar para la liquidacion de un prestamo
	 * 
	 * @param prestamoPk
	 * @param tipoLiquidacion
	 * @return
	 */
	CalculoLiquidacion calcularLiquidacion(ValidarRequisitosRecaudacion valReqLiqAnt, TipoLiquidacion tipoLiquidacion)
			throws CalculoLiquidacionExcepcion;

	/**
	 * Verifica si un credito se puede liquidar
	 * Validaciones
	 *   1. El estado del prestamo debe ser VIG (Vigente).
	 *   2. El prestamo no debe tener mÃ¡s de dos dividendos en MOR,  si tiene tres no se permitirÃ¡ la liquidaciÃ³n.
	 *   3. El prestamo debe tener por lo menos dos dividendos en estado REG (registrado).
	 *   4. No deben existir dividendos en estado ECO o comprobantes de pago del tipo PAGIND en estado GEN o DEP.
	 * 
	 * @param pk
	 * @return
	 */
	Boolean esPosibleLiquidacion(ValidarRequisitosRecaudacion valReqLiqAnt) throws LiquidacionAnticipadaExcepcion;

	/**
	 * Verifica si un credito se puede liquidar con Fondos de Reserva
	 * Validaciones
	 *   1. El estado del prestamo debe ser VIG (Vigente).
	 *   2. El prestamo no debe tener mÃ¡s de dos dividendos en MOR,  si tiene tres no se permitirÃ¡ la liquidaciÃ³n.
	 *   3. El prestamo debe tener por lo menos dos dividendos en estado REG (registrado).
	 *   4. No deben existir dividendos en estado ECO o comprobantes de pago del tipo PAGIND en estado GEN o DEP.
	 * 
	 * @param pk
	 * @return
	 */
	Boolean esPosibleLiquidacionFondos(ValidarRequisitosRecaudacion valReqCruces) throws LiquidacionFondosReservaExcepcion;	

	/**
	 * Carga los prerequisitos para realizar el Cruce de Cuentas con Fondos de Reserva
	 * 
	 * @return
	 */
	public List<RequisitosCruce> cargarRequisitoCruceCuentas(ValidarRequisitosRecaudacion valReqCruces)
			throws LiquidacionFondosReservaExcepcion, GarantiasSacException;

	/**
	 * Verifica que se pueda generar el comprobante individual
	 * 
	 *  Caso si el afiliado esta activo, se debe validar lo siguiente:
	 *    1.El estado del prestamo debe ser: VIG (Vigente).
	 * 	Caso si el afiliado esta cesante, se debe validar lo siguiente:
	 *   1.El estado del prestamo puede  ser:  ELC (LIQUIDACION DEBITO CESANTIA) o ELF (LIQUIDACION DEBITO FR)
	 * @param pk
	 * @return
	 */
	Boolean esPosibleGenCompIndividual(DatosSituacionPrestamo datSituacionPrestamo) throws GenerarComprobanteIndividualExcepcion;

	/**
	 * Buscar si un prestamo tiene una liquidacion anticipada vigente
	 * 
	 * @param prestamoPk
	 * @return
	 * @throws NoTieneLiquidacionVigenteException
	 */
	Long obtenerLiquidacionVigente(PrestamoPk prestamoPk) throws NoTieneLiquidacionVigenteException;

	/**
	 * Carga una liquidacion por la clave primaria
	 * @param numeroLiquidacion
	 * @return
	 */
	LiquidacionPrestamo consultar(Long numeroLiquidacion);
	
	/**
	 * Permite buscar una liquidacion dado el numero de liquidacion, el tipo de liquidacion y el estado de la
	 * liquidacion
	 * 
	 * @param numeroLiquidacion
	 * @param tipoLiquidacion
	 * @param estadoLiquidacion
	 * @return Devuelve un objeto LiquidacionPrestamo si no tiene datos devuelve null
	 */
	LiquidacionPrestamo buscarLiquidacionPorNumeroTipoEstado(Long numeroLiquidacion, String tipoLiquidacion, EstadoLiquidacion estadoLiquidacion);
	
	public List<LiquidacionPrestamo> obtenerLiquidacionesVigente(String numeroAfiliado) throws NoTieneLiquidacionVigenteException;
	
	Boolean existeComprobanteEMI(Prestamo prestamo);
	
	/**
	 * Permite validar un comprobante de un prestamo por una lista de estados pasados
	 * @param prestamo
	 * @param estados
	 * @return
	 */
	Boolean existeComprobantesPorEstados(Prestamo prestamo,List<String> estados);
	
	/**
	 * Wrapper para pasar al bean Valida si existe una solicitud de credito en novacion con el numero cancelado de novacion
	 * @param numCanceladoNovacion =codpretip+ordpreafi+codprecla+numpreafi
	 * @param identificacion cedula o numero extranjero
	 * @return
	 */
	boolean tieneSolicitudNovacionTramite(Long numCanceladoNovacion,String identificacion);
	/**
	 * generacion liquidacion gaf
	 * @param valReqCruces
	 * @throws LiquidacionFondosReservaExcepcion
	 */
	 void generarLiquidacionFondosGaf(final ValidarRequisitosRecaudacion valReqCruces)	throws LiquidacionFondosReservaExcepcion;	
	 
		
		 /**
		  * REQ 444 N1.
		  * @param identificacion
		  * @param operacion
		  * @param nut
		  * @return
		  */
		 Boolean existeOperacionesEnDebitoAutomatico(String identificacion, final Long operacion, final Long nut);
		 /**
		  * REQ 444 N1.
		  * @param identificacion
		  * @param operacion
		  * @param nut
		  * @return
		  * @throws DebitoAutomaticoExcepcion
		  */
		 String validarDebitoAutomatico(String identificacion, Long operacion, Long nut) throws DebitoAutomaticoExcepcion;
	
}