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
import java.util.Map;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.CalculoValorRealFondosExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionFondosReservaExcepcion;
import ec.gov.iess.creditos.modelo.cruccta.dto.DataCrucCtaGaf;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * LiquidacionFondosJdbc
 *  
* @version $Revision: 1.2 $  [Nov 24, 2009]
 * @author Pablo Alvarez
 */
@Local
public interface LiquidacionFondosJdbc {

	
	/**
	 * Valida capital disponible  
	 * @param cedula
	 * @return
	 * @throws LiquidacionFondosReservaExcepcion
	 */
	@SuppressWarnings("unchecked")
	public Map validarLiquidacionFondos(PrestamoPk prestamoPk,String cedula) throws LiquidacionFondosReservaExcepcion;
	
	/** Calcula el valor real de fondos de reserva .
	 *
	 * @param cedula
	 * @param cumpleImposiciones
	 * @return
	 * @throws CalculoValorRealFondosExcepcion
	*/
		
	public BigDecimal calculoValorRealFondos(PrestamoPk prestamoPk,String cedula,String cumpleImposiciones) throws CalculoValorRealFondosExcepcion;	
	
	/**
	 * Genera la liquidación por Fondos de Reserva
	 * @param prestamoPk
	 * @param cedula
	 * @param coddivpol
	 * @param cumpleImposiciones
	 * @param estadoAfiliado
	 * @return
	 * @throws LiquidacionFondosReservaExcepcion
	 */
	public BigDecimal generarLiquidacionFondos(PrestamoPk prestamoPk, String cedula,
			String coddivpol,String cumpleImposiciones,String estadoAfiliado) throws LiquidacionFondosReservaExcepcion;
	
	/**
	 * Generar liquidacion cruce cuentas Gaf
	 * @param dataCruCtaGaf
	 * @throws LiquidacionFondosReservaExcepcion
	 */
	void generarLiquidacionFondosGaf(DataCrucCtaGaf dataCruCtaGaf) throws LiquidacionFondosReservaExcepcion;
	
}
