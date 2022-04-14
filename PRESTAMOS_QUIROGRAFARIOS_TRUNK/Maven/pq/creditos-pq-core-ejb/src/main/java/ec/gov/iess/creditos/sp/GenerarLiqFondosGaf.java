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

import ec.gov.iess.creditos.modelo.cruccta.dto.DataCrucCtaGaf;

/**
 * Cruce de cuentas GAF
 * 
 * @author PAUL
 *
 */
public class GenerarLiqFondosGaf extends StoredProcedure {

	private static final String SQL = "IESS_OWNER.CRE_CRUCECUENTASPQFR_PKG.CRE_CRUCECUENTASGAF_PRC";

	public GenerarLiqFondosGaf(DataSource dataSource) {
		setDataSource(dataSource);
		setSql(SQL);
		// Paraqmetros de entrada
		declareParameter(new SqlParameter("AINNUMPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AINORDPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AINCODPRETIP", Types.NUMERIC));
		declareParameter(new SqlParameter("AINCODPRECLA", Types.NUMERIC));
		declareParameter(new SqlParameter("AICNUMAFI", Types.VARCHAR));
		declareParameter(new SqlParameter("AINCODDIVPOL", Types.VARCHAR));
		declareParameter(new SqlParameter("AINIMPOSICIONES", Types.VARCHAR));
		declareParameter(new SqlParameter("AINESTADOAFILIADO", Types.VARCHAR));
		declareParameter(new SqlParameter("AINVALORCRUCEPQ", Types.VARCHAR));
		// Parametros de salida
		declareParameter(new SqlOutParameter("AOCRESPRO", Types.VARCHAR));
		declareParameter(new SqlOutParameter("AOCMENERR", Types.VARCHAR));
	}

	@SuppressWarnings("unchecked")
	public Map execute(DataCrucCtaGaf dataCruCtaGaf) {
		Map input = new HashMap();
		input.put("AINNUMPREAFI", dataCruCtaGaf.getPrestamoPk().getNumpreafi());
		input.put("AINORDPREAFI", dataCruCtaGaf.getPrestamoPk().getOrdpreafi());
		input.put("AINCODPRETIP", dataCruCtaGaf.getPrestamoPk().getCodpretip());
		input.put("AINCODPRECLA", dataCruCtaGaf.getPrestamoPk().getCodprecla());
		input.put("AICNUMAFI", dataCruCtaGaf.getCedula());
		input.put("AINCODDIVPOL", dataCruCtaGaf.getCoddivpol());
		input.put("AINIMPOSICIONES", dataCruCtaGaf.getCumpleImposiciones());
		input.put("AINESTADOAFILIADO", dataCruCtaGaf.getEstadoAfiliado());
		input.put("AINVALORCRUCEPQ", dataCruCtaGaf.getValorCruceCta());
		return execute(input);
	}
}
