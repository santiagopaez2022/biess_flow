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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
 * @version $Revision: 1.2 $ 03/10/2011
 * @author Pablo Alvarez
 */
public class CalculoValorRealFondos extends StoredProcedure {

	/*
	 * ( AICNUMAFI IN KSCRETCREDITOS.NUMAFI%TYPE, AINIMPOSICIONES IN VARCHAR2
	 * --CB 13/09/2010 , AI_NNUMPREAFI IN KSCRETCREDITOS.NUMPREAFI%TYPE,
	 * AI_NORDPREAFI IN KSCRETCREDITOS.ORDPREAFI%TYPE, AI_NCODPRETIP IN
	 * KSCRETCREDITOS.CODPRETIP%TYPE, AI_NCODPRECLA IN
	 * KSCRETCREDITOS.CODPRECLA%TYPE,
	 * 
	 * AOVALORREALFR OUT NUMBER, AOCRESPRO OUT CHAR, AOCMENERR OUT VARCHAR2)
	 */

	private static final String SQL = "CRE_CRUCECUENTASPQFR_PKG.CRE_VALORREALFR_PRC";

	private DataSource dataSourceF;

	private Connection connection;

	public CalculoValorRealFondos(DataSource dataSource) {
		setDataSource(dataSource);
		dataSourceF = dataSource;
		setSql(SQL);

		// Paraqmetros de entrada
		declareParameter(new SqlParameter("AICNUMAFI", Types.VARCHAR));
		declareParameter(new SqlParameter("AI_NNUMPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AI_NORDPREAFI", Types.NUMERIC));
		declareParameter(new SqlParameter("AI_NCODPRETIP", Types.NUMERIC));
		declareParameter(new SqlParameter("AI_NCODPRECLA", Types.NUMERIC));

		/**
		 * Carlos Bastidas: INC-6047 se pasa como parámetro si cumple
		 * imposiciones en fondos de reserva"
		 */
		declareParameter(new SqlParameter("AINIMPOSICIONES", Types.VARCHAR));

		// Parametros de salida
		declareParameter(new SqlOutParameter("AOVALORREALFR", Types.DECIMAL));
		declareParameter(new SqlOutParameter("AOCRESPRO", Types.VARCHAR));
		declareParameter(new SqlOutParameter("AOCMENERR", Types.VARCHAR));
	}

	@SuppressWarnings("unchecked")
	public Map execute(PrestamoPk prestamoPk, String cedula,
			String cumpleImposiciones) {
		/**
		 * Carlos Bastidas: INC-6047 se pasa como parámetro si cumple
		 * imposiciones en fondos de reserva"
		 */

		Map input = new HashMap();
		String sql = "{call CRE_CRUCECUENTASPQFR_PKG.CRE_VALORREALFR_PRC(?,?,?,?,?,?,?,?,?)}";
		BigDecimal valorRealFR = null;
		CallableStatement cs = null;
		String codErr = null;
		String menErr = null;
		try {
			connection = (Connection) dataSourceF.getConnection();
			cs = connection.prepareCall(sql);
			cs.setString(1, cedula);
			cs.setString(2, cumpleImposiciones);
			cs.setLong(3, prestamoPk.getNumpreafi());
			cs.setLong(4, prestamoPk.getOrdpreafi());
			cs.setLong(5, prestamoPk.getCodpretip());
			cs.setLong(6, prestamoPk.getCodprecla());
			cs.registerOutParameter(7, Types.DECIMAL);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.registerOutParameter(9, Types.VARCHAR);
			cs.execute();
			valorRealFR = cs.getBigDecimal(7);
			codErr = cs.getString(8);
			menErr = cs.getString(9);

		} catch (Exception e) {
			codErr = "0";
			menErr = "Existio problema al ejecutar CRE_CRUCECUENTASPQFR_PKG.CRE_VALORREALFR_PRC: "
					+ e.getMessage();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
			}
			try {
				connection.close();
			} catch (Exception e2) {
			}
		}

		/*
		 * input.put("AI_NNUMPREAFI", prestamoPk.getNumpreafi());
		 * input.put("AI_NORDPREAFI", prestamoPk.getOrdpreafi());
		 * input.put("AI_NCODPRETIP", prestamoPk.getCodpretip());
		 * input.put("AI_NCODPRECLA", prestamoPk.getCodprecla());
		 * input.put("AICNUMAFI", cedula); input.put("AINIMPOSICIONES",
		 * cumpleImposiciones);
		 */

		input.put("AOVALORREALFR", valorRealFR);
		input.put("AOCRESPRO", codErr);
		input.put("AOCMENERR", menErr);
		return input;

		// return execute(input);
	}
}
