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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 19, 2007]
 * @author pablo
 */
public class GenerarComprobantePagoIndividual extends StoredProcedure {

	private static final String SQL = "KSCREKCOMPAGAFI.GENCOMPAGINDDIV";

	public GenerarComprobantePagoIndividual(DataSource ds) {
		setDataSource(ds);
		setSql(SQL);

		// Parametros de entrada
		declareParameter(new SqlParameter("AICNUMAFI", Types.VARCHAR));
		declareParameter(new SqlParameter("AINNUMPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AINCODPRECLA", Types.NUMERIC));
		declareParameter(new SqlParameter("AINORDPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AINCODPRETIP", Types.NUMERIC));
		declareParameter(new SqlParameter("AICTIPPER", Types.VARCHAR));
		declareParameter(new SqlParameter("AINNUMCUOAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AICDIVIDENDOS", Types.VARCHAR));
		declareParameter(new SqlParameter("AICPOLCORCOM", Types.VARCHAR));

		// Parametros de salida
		declareParameter(new SqlOutParameter("AOCCODTIPCOMPAG", Types.VARCHAR));
		declareParameter(new SqlOutParameter("AOCCODCOMPAG", Types.VARCHAR));
		declareParameter(new SqlOutParameter("AOCRESPRO", Types.VARCHAR));
		declareParameter(new SqlOutParameter("AOCMENERR", Types.VARCHAR));
		
		declareParameter(new SqlParameter("AI_TIPOPLANILLA", Types.VARCHAR));
	}

	@SuppressWarnings("unchecked")
	public Map execute(PrestamoPk prestamoPk, String cedula, String tipoPeriodo, List<Long> dividendos,
			String politicaCorporativa, String tipoEmpleador) {

		String cadenaDividendos = cadenaDividendos(dividendos);

		Map input = new HashMap();

		input.put("AICNUMAFI", cedula);
		input.put("AINNUMPREAFI", prestamoPk.getNumpreafi());
		input.put("AINCODPRECLA", prestamoPk.getCodprecla());
		input.put("AINORDPREAFI", prestamoPk.getOrdpreafi());
		input.put("AINCODPRETIP", prestamoPk.getCodpretip());
		input.put("AICTIPPER", tipoPeriodo);
		input.put("AINNUMCUOAFI", dividendos.size());
		input.put("AICDIVIDENDOS", cadenaDividendos);
		input.put("AICPOLCORCOM", politicaCorporativa);
		input.put("AI_TIPOPLANILLA", tipoEmpleador);

		return execute(input);
	}

	private String cadenaDividendos(List<Long> dividendos) {
		StringBuffer cadenaDividendos = new StringBuffer();

		for (Iterator<Long> iterator = dividendos.iterator(); iterator.hasNext();) {
			Long dividendo = (Long) iterator.next();
			cadenaDividendos.append(String.valueOf(dividendo));
			cadenaDividendos.append("*");
		}

		return cadenaDividendos.toString();
	}

}