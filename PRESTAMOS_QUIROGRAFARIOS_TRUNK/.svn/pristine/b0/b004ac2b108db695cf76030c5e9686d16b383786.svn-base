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

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * Clase singleton de apoyo para los procesos de calculo del credito
 * quirografario
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
public class CondicionCalculoHelperSingleton {

	private static CondicionCalculoHelperSingleton instanciaUnica = null;

	private CondicionCalculoHelperSingleton() {

	}

	public static CondicionCalculoHelperSingleton getInstancia() {

		if (instanciaUnica == null) {
			instanciaUnica = new CondicionCalculoHelperSingleton();
		}

		return instanciaUnica;

	}

	public BigDecimal calculoCouta() {
		return null;
	}

	public BigDecimal calculoMonto() {

		return null;
	}

	/**
	 * Determina la fecha restando un numero de dias especifico.
	 * 
	 * @param fecha
	 *            fecha inicial
	 * @param semanas
	 *            numero de semana a restar
	 * @return la fecha menos las semanas
	 * @author cvillarreal
	 */
	public Date determinarFechainicioTasaInteres(Date fecha, int semanas) {

		int diasSemana = 7 * semanas;

		Calendar cl = Calendar.getInstance();
		cl.setTime(fecha);
		cl.add(Calendar.DATE, diasSemana * -1);

		return cl.getTime();
	}

}
