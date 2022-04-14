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

import ec.gov.iess.creditos.comprobante.dto.DatoComprobante;

public class ComprobantePagoIndividualSacSp extends StoredProcedure {

	private static final String SQL = "IESS_OWNER.REC_COMPROBANTEPAGO_PH_PKG.rec_gencompgind_ext_pq_prc";
	
	public ComprobantePagoIndividualSacSp(DataSource ds) {
		setDataSource(ds);
		setSql(SQL);

		// Parametros de entrada
		declareParameter(new SqlParameter("ain_tt_idtipotransaccion", Types.NUMERIC));
		declareParameter(new SqlParameter("ain_nut", Types.NUMERIC));
		declareParameter(new SqlParameter("aic_cedula", Types.VARCHAR));
		declareParameter(new SqlParameter("ain_valor_cobr", Types.NUMERIC));
		declareParameter(new SqlParameter("ain_int_mora", Types.NUMERIC));
		declareParameter(new SqlParameter("ain_idgaf", Types.NUMERIC));
		declareParameter(new SqlParameter("aid_fecha_vencimiento", Types.DATE));
        //Parametro de salida
		declareParameter(new SqlOutParameter("aocodtipcompag", Types.VARCHAR));
		declareParameter(new SqlOutParameter("aocodcompag", Types.VARCHAR));
		declareParameter(new SqlOutParameter("aocrespro", Types.VARCHAR));
		declareParameter(new SqlOutParameter("aocmenerr", Types.VARCHAR));
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> execute(DatoComprobante datoComprobante) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("ain_tt_idtipotransaccion", datoComprobante.getTipoTransaccion());
		input.put("ain_nut", datoComprobante.getNut());
		input.put("aic_cedula", datoComprobante.getCedula());
		input.put("ain_valor_cobr", datoComprobante.getValorCobro());
		input.put("ain_int_mora", datoComprobante.getIntMora());
		input.put("ain_idgaf", datoComprobante.getIdGaf());
		input.put("aid_fecha_vencimiento", datoComprobante.getFechaVencimiento());
		return execute(input);
	}
	

}