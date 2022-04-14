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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import ec.gov.iess.creditos.enumeracion.TipoRecaudacionEnum;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * Clase que contiene el paquete que permite generar comprobante y liquidación.
 *  
 * @author roberth.obando
 */
public class GenerarComprobanteLiquidacionSp extends StoredProcedure {

	private static final String SQL = "KSCREKCOMPAGAFI.CRE_GENERACOMPLIQPQ_PRC";

	public GenerarComprobanteLiquidacionSp(DataSource ds) {
		setDataSource(ds);
		setSql(SQL);

		// Parametros de entrada
		declareParameter(new SqlParameter("ai_cnumafi", Types.VARCHAR));
		declareParameter(new SqlParameter("ai_nnumpreafi", Types.NUMERIC));
		declareParameter(new SqlParameter("ai_nordpreafi", Types.NUMERIC));
		declareParameter(new SqlParameter("ai_ncodpretip", Types.NUMERIC));
		declareParameter(new SqlParameter("ai_ncodprecla", Types.NUMERIC));
		declareParameter(new SqlParameter("ai_ccodestpre", Types.VARCHAR));
		declareParameter(new SqlParameter("ai_ccodtipliq", Types.VARCHAR));
		declareParameter(new SqlParameter("ai_ctipper", Types.VARCHAR));
		declareParameter(new SqlParameter("ai_nnumcuoafi", Types.NUMERIC));
		declareParameter(new SqlParameter("ai_cdividendos", Types.VARCHAR));
		declareParameter(new SqlParameter("ai_cpolcorcom", Types.VARCHAR));
		declareParameter(new SqlParameter("ai_tiporec", Types.VARCHAR));
		declareParameter(new SqlParameter("ai_tipoplanilla", Types.VARCHAR));
		declareParameter(new SqlParameter("ai_fechavence", Types.DATE));
		// Parametros de salida
		declareParameter(new SqlOutParameter("ao_nnumliqpre", Types.VARCHAR));
		declareParameter(new SqlOutParameter("ao_ccodtipcompag", Types.VARCHAR));
		declareParameter(new SqlOutParameter("ao_ccodcompag", Types.VARCHAR));
		declareParameter(new SqlOutParameter("ao_crespro", Types.VARCHAR));
		declareParameter(new SqlOutParameter("ao_cmenerr", Types.VARCHAR));		
	}

	@SuppressWarnings("unchecked")
	public Map execute(PrestamoPk prestamoPk, String cedula, String tipoPeriodo, List<Long> dividendos,
			String politicaCorporativa, String tipoEmpleador, 
			String estadoPrestamo, String tipoRecaudacion, Date fechaVencimiento, String tipoLiquidacion) {

		Map input = new HashMap();

		input.put("ai_cnumafi", cedula);
		input.put("ai_nnumpreafi", prestamoPk.getNumpreafi());
		input.put("ai_nordpreafi", prestamoPk.getOrdpreafi());
		input.put("ai_ncodpretip", prestamoPk.getCodpretip());
		input.put("ai_ncodprecla", prestamoPk.getCodprecla());
		input.put("ai_ccodestpre", estadoPrestamo);
		if (TipoRecaudacionEnum.LIQPRE.name().equals(tipoRecaudacion)) {
			input.put("ai_ccodtipliq", tipoLiquidacion);
			input.put("ai_ctipper", null);
			input.put("ai_nnumcuoafi", null);
			input.put("ai_cdividendos", null);
			input.put("ai_cpolcorcom", null);
		} else if (TipoRecaudacionEnum.COMPROB.name().equals(tipoRecaudacion)) {
			String cadenaDividendos = cadenaDividendos(dividendos);
			input.put("ai_ccodtipliq", null);
			input.put("ai_ctipper", tipoPeriodo);
			input.put("ai_nnumcuoafi", dividendos.size());
			input.put("ai_cdividendos", cadenaDividendos);
			input.put("ai_cpolcorcom", politicaCorporativa);
		}
		
		input.put("ai_tiporec", tipoRecaudacion);
		input.put("ai_tipoplanilla", tipoEmpleador);
		input.put("ai_fechavence", fechaVencimiento);
		
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