/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ec.gov.iess.creditos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.VariablePrestamoDao;
import ec.gov.iess.creditos.excepcion.GenerarSecuenciaException;
import ec.gov.iess.creditos.modelo.persistencia.VariablePrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.VariablePrestamoPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class VariablePrestamoDaoImpl extends GenericEjbDao<VariablePrestamo, VariablePrestamoPK> implements
		VariablePrestamoDao {

	LoggerBiess log = LoggerBiess.getLogger(VariablePrestamoDaoImpl.class);

	@Resource(mappedName = "java:credito-pq-DS")
	private DataSource dataSource;

	/**
	 * @param type
	 */
	public VariablePrestamoDaoImpl() {
		super(VariablePrestamo.class);
	}

	public VariablePrestamo getSecuencia(int idTipocredito, int idVariedadCredito) throws GenerarSecuenciaException {

		String query = "select CODPRETIP, CODPRECLA, INDHABVARPRE, SECVARPRE from KSCRETPREVAR where CODPRECLA = ? and CODPRETIP = ? for update nowait";
		String update = "update KSCRETPREVAR set SECVARPRE = ? where CODPRECLA = ? and CODPRETIP = ?";
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		VariablePrestamo variablePrestamo = new VariablePrestamo();

		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, idVariedadCredito);
			pstmt.setInt(2, idTipocredito);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				Long secuencia = rs.getLong(4);

				VariablePrestamoPK variablePrestamoPK = new VariablePrestamoPK();
				variablePrestamoPK.setCodpretip(rs.getLong(1));
				variablePrestamoPK.setCodprecla(rs.getLong(2));

				variablePrestamo.setVariablePrestamoPK(variablePrestamoPK);
				variablePrestamo.setIndhabvarpre(rs.getString(3));
				variablePrestamo.setSecvarpre(rs.getLong(4));
//				variablePrestamo.setSecvarpre(new Long(277327));

				secuencia = secuencia + 1;

				pstmt = connection.prepareStatement(update);
				pstmt.setLong(1, secuencia);
				pstmt.setLong(2, idVariedadCredito);
				pstmt.setLong(3, idTipocredito);
				pstmt.execute();

			}
		} catch (SQLException e1) {
			log.error(e1);
			throw new GenerarSecuenciaException("Error al generar la secuencia KSCRETPREVAR");
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				log.error(e);
				throw new GenerarSecuenciaException("Error al generar la secuencia KSCRETPREVAR");
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				log.error(e);
				throw new GenerarSecuenciaException("Error al generar la secuencia KSCRETPREVAR");
			}
			try {
				connection.close();
			} catch (Exception e) {
				log.error(e);
				throw new GenerarSecuenciaException("Error al generar la secuencia KSCRETPREVAR");
			}
		}
		return variablePrestamo;
	}

}