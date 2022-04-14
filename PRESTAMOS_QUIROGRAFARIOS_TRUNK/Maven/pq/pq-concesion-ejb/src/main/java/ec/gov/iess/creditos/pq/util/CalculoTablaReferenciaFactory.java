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

import ec.gov.iess.creditos.enumeracion.TipoTablaReferencia;

/**
 * 
 * Clase singleton que determina el tipo de tabla de referencia ya sea la
 * imolementacion sin o con documento de referncia.
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
public class CalculoTablaReferenciaFactory {

	private static CalculoTablaReferenciaFactory instanciaUnica = null;

	private CalculoTablaReferenciaFactory() {
	}

	/**
	 * Devuelve la instancia de la clase.
	 * 
	 * @return {@link CalculoTablaReferenciaFactory}
	 */
	public static CalculoTablaReferenciaFactory getInstancia() {

		if (instanciaUnica == null) {
			instanciaUnica = new CalculoTablaReferenciaFactory();
		}

		return instanciaUnica;
	}

	/**
	 * Determina la implementacion del tipo de calculo de la tabla de referencia
	 * 
	 * @param tipoTablaReferencia
	 *            opcion para escoger la implementacio
	 * 
	 * @return la implemnetacion de {@link CalculoTablaReferencia} en caso de no
	 *         encontrar la opcion retorna null
	 * @author cvillarreal
	 */
	public CalculoTablaReferencia getTipoTablaReferencia(int tipoTablaReferencia) {

		if (TipoTablaReferencia.SIN_DOCUMENTO_FIDUCIARIO.getValor() == tipoTablaReferencia) {
			return new CalculoTablaReferenciaSinDocFinanciero();
		} else if (TipoTablaReferencia.CON_DOCUMENTO_FIDUCIARIO.getValor() == tipoTablaReferencia) {
			return new CalculoTablaReferenciaConDocFinanciero();
		}

		return null;
	}

}
