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

import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 19, 2007]
 * @author pablo
 */
public class LiquidacionAnticipada extends StoredProcedure {

	private static final String SQL = "KSCREKPRELIQ.GENLIQANTPRE";

	public LiquidacionAnticipada(DataSource dataSource) {
		setDataSource(dataSource);
		setSql(SQL);

		// Paraqmetros de entrada
		declareParameter(new SqlParameter("NUMPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("ORDPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("CODPRETIP", Types.NUMERIC));
		declareParameter(new SqlParameter("CODPRECLA", Types.NUMERIC));
		declareParameter(new SqlParameter("CODESTPRE", Types.VARCHAR));
		declareParameter(new SqlParameter("CODTIPLIQ", Types.VARCHAR));

		// Parametros de salida
		declareParameter(new SqlOutParameter("AOCRESPRO", Types.VARCHAR));
		declareParameter(new SqlOutParameter("AONNUMLIQPRE", Types.NUMERIC));
		declareParameter(new SqlOutParameter("AOCMENERR", Types.VARCHAR));
		
		declareParameter(new SqlParameter("AI_TIPOPLANILLA", Types.VARCHAR));
	}

	@SuppressWarnings("unchecked")
	public Map execute(PrestamoPk prestamoPk, String estadoPrestamo, TipoLiquidacion tipoLiquidacion, String tipoEmpleador) {
		Map input = new HashMap();
		input.put("NUMPREAFI", prestamoPk.getNumpreafi());
		input.put("ORDPREAFI", prestamoPk.getOrdpreafi());
		input.put("CODPRETIP", prestamoPk.getCodpretip());
		input.put("CODPRECLA", prestamoPk.getCodprecla());
		input.put("CODESTPRE", estadoPrestamo);
		input.put("CODTIPLIQ", tipoLiquidacion);
		input.put("AI_TIPOPLANILLA", tipoEmpleador);
		return execute(input);
	}
}
