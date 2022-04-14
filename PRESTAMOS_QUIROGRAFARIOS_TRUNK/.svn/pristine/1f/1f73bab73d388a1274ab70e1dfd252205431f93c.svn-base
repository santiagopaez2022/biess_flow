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

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * Incluir aquÃ­ la descripciÃ³n de la clase.
 *  
 * @version $Revision: 1.2 $  [Nov 27, 2009]
 * @author Pablo Alvarez
 */
public class GenerarLiquidacionFondos extends StoredProcedure {

	private static final String SQL = "CRE_CRUCECUENTASPQFR_PKG.CRE_CRUCECUENTAS_PRC";

	public GenerarLiquidacionFondos(DataSource dataSource) {
		setDataSource(dataSource);
		setSql(SQL);
		/**
		 *  Carlos Bastidas: INC-6047 se pasa como parámetro al paquete que realiza el cruce de cuentas el estado del afiliado y si cumple imposiciones en fondos de reserva"
		 */

		// Paraqmetros de entrada
		declareParameter(new SqlParameter("AINNUMPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AINORDPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AINCODPRETIP", Types.NUMERIC));
		declareParameter(new SqlParameter("AINCODPRECLA", Types.NUMERIC));
		declareParameter(new SqlParameter("AICNUMAFI", Types.VARCHAR));
		declareParameter(new SqlParameter("AINCODDIVPOL", Types.VARCHAR));
		declareParameter(new SqlParameter("AINIMPOSICIONES", Types.VARCHAR));
		declareParameter(new SqlParameter("AINESTADOAFILIADO", Types.VARCHAR));

		// Parametros de salida
		declareParameter(new SqlOutParameter("AONNUMLIQPRE", Types.NUMERIC));
		declareParameter(new SqlOutParameter("AOCRESPRO", Types.VARCHAR));
		declareParameter(new SqlOutParameter("AOCMENERR", Types.VARCHAR));
	}

	@SuppressWarnings("unchecked")
	public Map execute(PrestamoPk prestamoPk, String cedula, String coddivpol,String cumpleImposiciones,String estadoAfiliado) {
		/**
		 *  Carlos Bastidas: INC-6047 se pasa como parámetro al paquete que realiza el cruce de cuentas el estado del afiliado y si cumple imposiciones en fondos de reserva"
		 */
		Map input = new HashMap();
		input.put("AINNUMPREAFI", prestamoPk.getNumpreafi());
		input.put("AINORDPREAFI", prestamoPk.getOrdpreafi());
		input.put("AINCODPRETIP", prestamoPk.getCodpretip());
		input.put("AINCODPRECLA", prestamoPk.getCodprecla());
		input.put("AICNUMAFI", cedula);
		input.put("AINCODDIVPOL", coddivpol);
		input.put("AINIMPOSICIONES", cumpleImposiciones);
		input.put("AINESTADOAFILIADO", estadoAfiliado);
		return execute(input);
	}
}
