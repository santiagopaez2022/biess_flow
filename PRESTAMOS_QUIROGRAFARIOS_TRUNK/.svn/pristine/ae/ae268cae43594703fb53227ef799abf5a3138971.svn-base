/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.sp.impl;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ec.gov.iess.creditos.excepcion.CalculoValorRealFondosExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionFondosReservaExcepcion;
import ec.gov.iess.creditos.modelo.cruccta.dto.DataCrucCtaGaf;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.sp.CalculoValorRealFondos;
import ec.gov.iess.creditos.sp.GenerarLiqFondosGaf;
import ec.gov.iess.creditos.sp.GenerarLiquidacionFondos;
import ec.gov.iess.creditos.sp.LiquidacionFondos;
import ec.gov.iess.creditos.sp.LiquidacionFondosJdbc;

/**
 * Incluir aquÃ­ la descripciÃ³n de la clase.
 *  
 * @version $Revision: 1.3 $  [Nov 24, 2009]
 * @author Pablo Alvarez
 */
@Stateless
public class LiquidacionFondosJdbcImpl implements LiquidacionFondosJdbc {

	@Resource(mappedName = "java:credito-pq-DS")
	DataSource dataSource;

	@SuppressWarnings("unchecked")
	public Map validarLiquidacionFondos(PrestamoPk prestamoPk,String cedula) throws LiquidacionFondosReservaExcepcion {

		LiquidacionFondos validarLiquidacionFondos = new LiquidacionFondos(dataSource);
		return validarLiquidacionFondos.execute(prestamoPk,cedula);
	}

	@SuppressWarnings("unchecked")
	public BigDecimal calculoValorRealFondos(PrestamoPk prestamoPk,String cedula,String cumpleImposiciones) throws CalculoValorRealFondosExcepcion {

		/**
		 *  Carlos Bastidas: INC-6047 se pasa como parámetro si cumple imposiciones en fondos de reserva"
		 */
		CalculoValorRealFondos valorFondos = new CalculoValorRealFondos(dataSource);
		Map results = valorFondos.execute(prestamoPk,cedula,cumpleImposiciones);
		String codigoError = (String) results.get("AOCRESPRO");
		String mensajeError = (String) results.get("AOCMENERR");
		BigDecimal valorRealFondos = (BigDecimal) results.get("AOVALORREALFR");
		if (!"1".equals(codigoError.trim())) {
			throw new CalculoValorRealFondosExcepcion(mensajeError);
		}
		
		return valorRealFondos;		
		
	}

	@SuppressWarnings("unchecked")
	public BigDecimal generarLiquidacionFondos(PrestamoPk prestamoPk, String cedula,
			String coddivpol,String cumpleImposiciones,String estadoAfiliado) throws LiquidacionFondosReservaExcepcion {
		/**
		 *  Carlos Bastidas: INC-6047 se pasa como parámetros el estado del afiliado en fondos y si cumple imposiciones en fondos de reserva"
		 */
		GenerarLiquidacionFondos procedureLiquidacionFondos = new GenerarLiquidacionFondos(dataSource);
		Map results = procedureLiquidacionFondos.execute(prestamoPk, cedula, coddivpol,cumpleImposiciones,estadoAfiliado);
		String codigoError = (String) results.get("AOCRESPRO");
		String mensajeError = (String) results.get("AOCMENERR");
		BigDecimal numeroLiquidacion = (BigDecimal) results.get("AONNUMLIQPRE");
		if (!"1".equals(codigoError.trim())) {
			throw new LiquidacionFondosReservaExcepcion(mensajeError);
		}
		return numeroLiquidacion;
	}
	
	@SuppressWarnings("unchecked")
	public void generarLiquidacionFondosGaf(DataCrucCtaGaf dataCruCtaGaf) throws LiquidacionFondosReservaExcepcion {
		/**
		 *  Carlos Bastidas: INC-6047 se pasa como parámetros el estado del afiliado en fondos y si cumple imposiciones en fondos de reserva"
		 */
		GenerarLiqFondosGaf procedureLiquidacionFondosGaf = new GenerarLiqFondosGaf(dataSource);
		Map results = procedureLiquidacionFondosGaf.execute(dataCruCtaGaf);
		String codigoError = (String) results.get("AOCRESPRO");
		String mensajeError = (String) results.get("AOCMENERR");
		if (!"1".equals(codigoError.trim())) {
			throw new LiquidacionFondosReservaExcepcion(mensajeError);
		}
	}


}