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
import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.dto.CondicionCalculo;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.Garantia;
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.persistencia.AniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.pq.excepcion.TablaReferenciaException;

/**
 * 
 * Interface con los metodos para determinar la tabla de referncia del credito
 * quirografario en base al plazo maximo de endeudamiento
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@Remote
public interface TablaReferenciaCreditoServicioRemoto {

	/**
	 * Determina la mejor opcion de cerdito para el IESS, en el menor plazo
	 * posible.
	 * 
	 * @return un objeto de modelo {@link OpcionCredito} con los datos de la
	 *         mejor opcion
	 * @author cvillarreal
	 */
	public OpcionCredito determinarMejorOpcioncredito();

	/**
	 * Determina la lista de plazo posible de endeudamiento.
	 * 
	 * @param plazoMaximoMeses
	 *            nuemero de meses maximo de endeudamiento
	 * @return una lista de plazos de endeudamientos
	 *         {@link AniosPlazoCapitalEndeudamiento}
	 */
	public List<AniosPlazoCapitalEndeudamiento> consultarPlazoEndeudamiento(
			int plazoMaximoPrestamo, int plazoMaximoMeses,
			int idVariedadCredito, int idTipoCredito);

	/**
	 * Determina la tabla de referencia de endeudamiento ya sea con o sin
	 * documento fiduciario.
	 * 
	 * @param tipoTablaReferencia
	 *            tipo de tabla con o sin documento fiduciario
	 * @param condicionCalculo
	 *            objeto de modelo con los datos de condicion de calculo
	 *            {@link CondicionCalculo}
	 * @param garantia
	 *            objeto de modelo con los datos de la garantia {@link Garantia}
	 * @return una lista con el plazo de endeudamiento de tipo
	 *         {@link PlazoCredito}
	 * @author cvillarreal
	 */
	public List<PlazoCredito> determinarTablaReferencia(DatosCredito credito,
			BigDecimal saldoDisponibleVigente) throws TablaReferenciaException;

}
