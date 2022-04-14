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
 * reversa de prestamos novados
 * </b>
 *  
 * @author acantos
 * @version $Revision: 1.0 
 */
public class ReversaPrestamo extends StoredProcedure {
	
	private static final String SQL = "CRE_REVERSANOVACIONPQ_PKG.CRE_EJECUTAREVERSA_PRC";
	
	public ReversaPrestamo(DataSource dataSource) {
		setDataSource(dataSource);
		setSql(SQL);
		
		// Paraqmetros de entrada
		declareParameter(new SqlParameter("VNNUMPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("VNORDPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("VNCODPRETIP", Types.NUMERIC));
		declareParameter(new SqlParameter("VNCODPRECLA", Types.NUMERIC));

		// Parametros de salida
		declareParameter(new SqlOutParameter("AOCRESPRO", Types.VARCHAR));
		declareParameter(new SqlOutParameter("AOCMENERR", Types.VARCHAR));
	}

	@SuppressWarnings("unchecked")
	public Map execute(PrestamoPk prestamoPk) {
		Map input = new HashMap();
		input.put("VNNUMPREAFI", prestamoPk.getNumpreafi());
		input.put("VNORDPREAFI", prestamoPk.getOrdpreafi());
		input.put("VNCODPRETIP", prestamoPk.getCodpretip());
		input.put("VNCODPRECLA", prestamoPk.getCodprecla());
		return execute(input);
	}
}
