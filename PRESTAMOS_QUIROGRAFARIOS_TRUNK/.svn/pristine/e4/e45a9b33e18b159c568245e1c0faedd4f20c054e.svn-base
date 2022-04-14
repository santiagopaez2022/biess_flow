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
package ec.gov.iess.creditos.pq.helper.simulacion;

import ec.gov.iess.creditos.enumeracion.TipoSimulacionCredito;

/**
 * 
 * Clase factory singleton que determina la implementacion de la simulacion del
 * credito quirografario
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
public class SimulacionCreditoFactory {

	private static SimulacionCreditoFactory instanciaUnica = null;

	private SimulacionCreditoFactory() {

	}

	public static SimulacionCreditoFactory getInstancia() {

		if (instanciaUnica == null) {
			instanciaUnica = new SimulacionCreditoFactory();
		}

		return instanciaUnica;

	}

	public SimularCredito factory(int tipoSimulacion) {

		if (TipoSimulacionCredito.SIMULACION_MONTO.getValor() == tipoSimulacion) {
			return new SimularCreditoMonto();
		} else if (TipoSimulacionCredito.SIMULACION_CUOTA.getValor() == tipoSimulacion) {
			return new SimularCreditoCuota();
		} else if (TipoSimulacionCredito.SIMULACION_MEJOR_OPCION.getValor() == tipoSimulacion) {
			return new SimularCreditoCuota();
		}
		return null;

	}

}
