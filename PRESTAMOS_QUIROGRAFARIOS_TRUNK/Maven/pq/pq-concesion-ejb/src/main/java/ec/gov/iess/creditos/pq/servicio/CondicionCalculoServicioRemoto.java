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

import javax.ejb.Remote;

import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.TasaInteresExcepcion;
import ec.gov.iess.creditos.modelo.dto.CondicionCalculo;
import ec.gov.iess.creditos.pq.excepcion.CondicionCalculoException;
import ec.gov.iess.creditos.pq.excepcion.ConsultarTasaInteresException;
import ec.gov.iess.creditos.pq.excepcion.EsperanzaVidaException;
import ec.gov.iess.creditos.pq.excepcion.PlazoMaximoEndeudamientoException;
import ec.gov.iess.creditos.pq.excepcion.TasaActuarialException;
import ec.gov.iess.creditos.pq.excepcion.TasaInteresBancoCentralException;
import ec.gov.iess.creditos.pq.excepcion.TasaSeguroDesgravamen;

/**
 * 
 * Interface para la determinacion de las condiciones del calculo del credito
 * 
 * <ul>
 * <li> Esperanza de vida </li>
 * <li> Edad Actual </li>
 * <li> Plazo maximo del credito </li>
 * <li> Tasa de inters </li>
 * <li> Seguro de desgravamen </li>
 * </ul>
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@Remote
public interface CondicionCalculoServicioRemoto {

	/**
	 * Determina la tasa de interes a aplicar tanto en las tablas de referncia
	 * como en la simulacion
	 * 
	 * @return la tasa de interes a 4 decimales
	 * @author cvillarreal
	 * @throws ConsultarTasaInteresException
	 */
	public BigDecimal consultarTasaInteres(String idtasaBC, String idtasaACT,
			Date fechaSolicitud, int semanas) throws TasaInteresExcepcion;

	/**
	 * Determina la esperanza de vida del solicitante.
	 * 
	 * @param genero
	 *            F,M
	 * @param fechaNacimeinto
	 *            fecha de nacimiento del solicitante
	 * @return el numero de anios.
	 * @author cvillarreal
	 */
	public BigDecimal consultarEsperanzaVida(String genero,
			Date fechaNacimeinto, Date fechaSolicitud)
			throws EsperanzaVidaException;

	/**
	 * Determina el numero de anios maximo de endeudamiento.
	 * 
	 * @param edadMesasAnos
	 *            edad actual en anios y meses del solicitante.
	 * @param esperanzaVida
	 *            numero de anos de esperanza de vida
	 * @param tipoPrestamista
	 *            Es el rol del prestamista
	 * @return el plazo maximo de endeudamiento en base al plazo maximo del credito, si pasa la edad maxima el plazo se
	 *         ajusta a un anio
	 * @author cvillarreal
	 */
	public BigDecimal determinarPlazoMaximoEndeudamiento(Date fechaNacimient, BigDecimal esperanzaVida, TipoPrestamista tipoPrestamista)
			throws PlazoMaximoEndeudamientoException;

	/**
	 * Determina el porcentaje del seguro de desgravamen con modalidad seguro de
	 * saldos.
	 * 
	 * @return el valor del porcentajde del seguro de saldos
	 */
	public BigDecimal determinarSeguroDesgravamen(String idTipoSeguro,
			Date fechaSolicitud) throws TasaSeguroDesgravamen;

	/**
	 * 
	 * Consulta el proedio de la tasa de interes del banco central
	 * 
	 * @param idTipoTasa
	 *            tipo de tasa de interez
	 * @param fechaDesde
	 *            fecha inicial de consulta
	 * @param fechaHasta
	 *            fecha final de consulta
	 * @param semanas
	 *            nmero de semanas para la consulta
	 * @return el promedio de la tasa de interes
	 * @throws TasaInteresBancoCentralException
	 *             SemanaTasaInteresBancoCentralException en caso de que los
	 *             registros sean menores a la semana
	 *             TasaBancoCentralCeroException en caso de que la tasa sea cero
	 */
	public BigDecimal consultarTasaBancoCentral(String idTipoTasa,
			Date fechaDesde, Date fechaHasta, int semanas)
			throws TasaInteresBancoCentralException;

	/**
	 * Consulta la tasa de actual vigente a una fecha
	 * 
	 * @param idTipoTasa
	 *            id de la tasa de actuarial
	 * @param fecha
	 *            fecha vigencia de cosnulta
	 * @return el valor de la tasa actuarial
	 * @throws TasaActuarialException
	 *             NoexisteTasaActuarialException cuando no existe la tasa
	 *             actuarial TasaActurialDuplicadaException cuando existe mas de
	 *             una tasa en un mismo periodo TasaActuarialCeroException
	 *             cuando el valor es cero
	 */
	public BigDecimal consultarTasaActuarial(String idTipoTasa, Date fecha)
			throws TasaActuarialException;

	/**
	 * Setea informacion para el credito
	 * 
	 * @param genero
	 * @param fechaNacimiento
	 * @param fechaSolicitud
	 * @param semanas
	 * @param tipoPrestamista
	 * @return
	 * @throws CondicionCalculoException
	 */
	public CondicionCalculo poblarCondicionCalculo(String genero,
			Date fechaNacimiento, 
			Date fechaSolicitud, int semanas, TipoPrestamista tipoPrestamista) throws CondicionCalculoException;
	
	/**
	 * Devuelve la tasa de interes de acuerdo al plazo solicitado y edad
	 * 
	 * @param plazo
	 * @param tipoProducto
	 * @param edad
	 * @return
	 * @throws CondicionCalculoException
	 */
	BigDecimal obtenerTasaInteresPorPlazo(BigDecimal plazoM, String tipoProducto, int edad) throws CondicionCalculoException;

}
