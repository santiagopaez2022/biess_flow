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
 * 
 * <b>
 * Liquidacion cruce de cuentas.
 * </b>
 *  
 * @author cbastidas
 * @version $Revision: 1.0 $ <p>[$Author: cbastidas $, $Date: 22/06/2011 $]</p>
 */
public class LiquidacionFondos extends StoredProcedure {
	
	private static final String SQL = "CRE_CRUCECUENTASPQFR_PKG.CRE_VALIDASALDOCAPITALFR_PRC";

	public LiquidacionFondos(DataSource dataSource) {
		setDataSource(dataSource);
		setSql(SQL);

		// Paraqmetros de entrada
		declareParameter(new SqlParameter("AICNUMAFI", Types.VARCHAR));
		declareParameter(new SqlParameter("AI_NNUMPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AI_NORDPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AI_NCODPRETIP", Types.NUMERIC));
		declareParameter(new SqlParameter("AI_NCODPRECLA", Types.NUMERIC));

		// Parametros de salida
		declareParameter(new SqlOutParameter("AOCRESPRO", Types.VARCHAR));
		declareParameter(new SqlOutParameter("AOCMENERR", Types.VARCHAR));
	}

	@SuppressWarnings("unchecked")
	public Map execute(PrestamoPk prestamoPk,String cedula) {
		Map input = new HashMap();
		input.put("AICNUMAFI", cedula);
		input.put("AI_NNUMPREAFI", prestamoPk.getNumpreafi());
		input.put("AI_NORDPREAFI", prestamoPk.getOrdpreafi());
		input.put("AI_NCODPRETIP", prestamoPk.getCodpretip());
		input.put("AI_NCODPRECLA", prestamoPk.getCodprecla());
		return execute(input);
	}
}
