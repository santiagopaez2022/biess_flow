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

import java.math.BigDecimal;
import java.util.List;

import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.modelo.dto.TablaReferenciaCredito;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;

/**
 * Clase abstracta para la simulacion de los creditos quirografarios.
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
public abstract class SimularCredito {

	protected CalculoCreditoHelperSingleton calculoCreditoHelper;

	public SimularCredito() {
		this.calculoCreditoHelper = CalculoCreditoHelperSingleton
				.getInstancia();
	}

	/**
	 * Logica de simulacion del credito.
	 * 
	 * @return un objeto de modelo {@link Simulacion} con el resultado de la
	 *         simulacion
	 */
	public abstract Simulacion simularCredito(
			List<PlazoCredito> plazoCreditoList, BigDecimal cuota,
			BigDecimal monto,
			BigDecimal tasaInteres, int plazoCredito);

	/**
	 * Determina la fila de la lista de tabla de referencia del credito a partir
	 * del plazo, para determinar los montos y cuotas maximas que se pueden
	 * comprometer.
	 * 
	 * @param plazo
	 *            numero de meses a consultar ne la tabla de referencia
	 * @return un objeto de modelo {@link TablaReferenciaCredito} con los datos
	 *         del credito a un plazo determiando
	 * @author cvillarreal
	 */
	protected PlazoCredito consultarFilaPlazoCreditoTablaReferencia(
			List<PlazoCredito> plazoCreditoList, int plazo) {

		for (PlazoCredito plazoCredito : plazoCreditoList) {
			if (plazo <= plazoCredito.getOpcionCredito().getMeses()) {
				return plazoCredito;
			}
		}

		return null;
	}

	protected boolean sobrepasaPlazoMaximo(int plazoMaximoCredito,
			int plazocredito) {

		if (plazocredito > plazoMaximoCredito) {
			return true;
		}

		return false;
	}

	protected Simulacion setValores(BigDecimal cuota, BigDecimal monto,
			 int plazoCredito) {

		Simulacion simulacion = new Simulacion();

		simulacion.setCalculoCredito(false);
		simulacion.setCuotaCredito(cuota);
		simulacion.setMontoCredito(monto);		
		simulacion.setPlazoCredito(plazoCredito);

		return simulacion;

	}

	protected int determinarPlazoMaximoTablaReferencia(
			List<PlazoCredito> plazoCreditoList) {

		if (null != plazoCreditoList && plazoCreditoList.size() > 0) {
			return plazoCreditoList.get(plazoCreditoList.size() - 1)
					.getOpcionCredito().getMeses();
		} else {
			throw new RuntimeException(" Tabla de referencia vacia");
		}

	}

}
