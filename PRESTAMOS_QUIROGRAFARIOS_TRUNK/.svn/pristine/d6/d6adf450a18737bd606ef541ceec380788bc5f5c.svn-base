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

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.enumeracion.DocumentoHabilitacionEnum;
import ec.gov.iess.creditos.comprobante.dto.DatoComprobante;
import ec.gov.iess.creditos.enumeracion.EstadoComprobantePago;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoIndividualExcepcion;
import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.modelo.dto.DatosSituacionPrestamo;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosComprobante;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.PeriodoComprobante;
import ec.gov.iess.creditos.modelo.persistencia.pk.ComprobantePagoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.EntidadNoEncontradaException;
import ec.gov.iess.creditos.pq.excepcion.NoTieneComprobanteVigenteException;
import ec.gov.iess.creditos.excepcion.AnularComprobanteExcepcion;

/**
 * Incluir aquí la descripción de la clase.
 * 
 * @version $Revision: 1.2 $ [Sep 20, 2007]
 * @author pablo
 */
@Local
public interface ComprobantePagoServicio {

	/**
	 * Obtiene el comprobante de pago vigente de una liquidacion
	 * 
	 * @param numeroLiquidacion
	 * @return
	 */
	ComprobantePago obtenerComprobantePagoVigente(Long numeroLiquidacion,
			List<EstadoComprobantePago> estados)
			throws NoTieneComprobanteVigenteException;

	/**
	 * 
	 * @param prestamoPk
	 * @param cedula
	 * @param tipoPeriodo
	 * @param dividendos
	 * @param politicaCorporativa
	 * @return
	 * @throws GenerarComprobantePagoIndividualExcepcion
	 */
	ComprobantePagoPk generarComprobantePagoIndividual(List<Long> dividendos,
			DatosSituacionPrestamo datSituacionPrestamo)
			throws GenerarComprobantePagoIndividualExcepcion;

	/**
	 * Busca si un prestamo tiene generado algun comprobante de pago individual
	 * y esta vigente
	 * 
	 * @param prestamoPk
	 * @return
	 */
	public Boolean existeComprobanteIndividualVigente(
			ValidarRequisitosComprobante valReqComprobante);
	
	public Boolean existeComprobantesIndividualesVigentes(ValidarRequisitosComprobante valReqComprobante);

	/**
	 * Devuelve la lista de comprbantes de pago individual vigentes para un
	 * prestamo
	 * 
	 * @param prestamoPk
	 * @return
	 */
	public List<ComprobantePago> obtenerComprobanteIndividualVigente(
			ValidarRequisitosComprobante valReqComprobante);
	
	public List<ComprobantePago> obtenerComprobantesIndividualesVigentes(ValidarRequisitosComprobante valReqComprobante);

	/**
	 * Pbtiene un comprobante de pago por su clave primaria y carga sus detalles
	 * 
	 * @param prestamoPk
	 * @return
	 */
	public ComprobantePago obtenerPorPk(ComprobantePagoPk comprobantePagoPk)
			throws EntidadNoEncontradaException;
	
	
	/**
	 * Metodo que indica si se debe habilitar o no la opcion de generacion de comprobante y liquidacion de credito
	 * 
	 * @param fechaValidacion
	 * @param tipoEmpleador
	 * @param documentoHabilita
	 * @param periodoComprobante
	 * @return
	 * @throws PeriodoComprobanteException
	 */
	boolean habilitaComprobanteLiquidacion(Calendar fechaValidacion, String tipoEmpleador, 
			DocumentoHabilitacionEnum documentoHabilita, PeriodoComprobante periodoComprobante)
			throws PeriodoComprobanteException;
	
	/**
	 * Metodo que determina el empleador para la generacion de comprobantes y liquidacion anticipada del credito
	 * 
	 * @param prestamoPk
	 * @return
	 */
	String determinaEmpleador(PrestamoPk prestamoPk);
	
	public void anularComprobantePago(ComprobantePago comprobantePago) 
			throws AnularComprobanteExcepcion;
	

	/**
	 * Permite generar el comprobante individual
	 * 
	 * @param datoComprobante
	 * @return
	 * @throws GenerarComprobantePagoIndividualExcepcion
	 */
	ComprobantePagoPk generarComprobantePagoIndividualSAC(DatoComprobante datoComprobante) throws GenerarComprobantePagoIndividualExcepcion;
	
	/**
	 * Permite obtener una lista de comprobante individuales
	 * @param identificacion
	 * @param estadosComprobante
	 * @return
	 */
	public List<ComprobantePago> obtenerComprobantePendPago(String identificacion,
			List<EstadoComprobantePago> estadosComprobante);
	
	/**
	 * Permite validar si se habilita o no un comprobante 
	 * @param fechaValidacion
	 * @param rangos
	 * @return
	 */
	 boolean habilitaComprobantePago(final Calendar fechaValidacion, final String rangos) ;
}