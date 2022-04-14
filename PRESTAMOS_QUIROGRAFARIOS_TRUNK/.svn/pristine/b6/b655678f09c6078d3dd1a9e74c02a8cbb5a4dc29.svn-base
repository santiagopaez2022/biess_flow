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

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.SaldoLiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * Interfaz que define todas las operaciones que se realizan con los saldos de
 * liquidacion de prestamos
 * 
 * @version $Revision: 1.2 $ [Sep 24, 2007]
 * @author pablo
 */
@Local
public interface SaldoLiquidacionPrestamoServicio {

	/**
	 * Busca todos los saldos por liquidacion cuyo dividendos estan en estado
	 * LCE o LFR
	 * 
	 * @return
	 */
	List<SaldoLiquidacionPrestamo> obtenerSaldosPorLiquidacion(PrestamoPk prestamoPk,List<String> estados);

}
