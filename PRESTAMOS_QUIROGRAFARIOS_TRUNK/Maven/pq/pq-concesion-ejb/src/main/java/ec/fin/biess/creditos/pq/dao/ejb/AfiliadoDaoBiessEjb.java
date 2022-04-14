/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.dao.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ec.fin.biess.creditos.pq.dao.AfiliadoDaoBiess;
import ec.fin.biess.creditos.pq.excepcion.AfiliadoExceptionBiess;
import ec.gov.iess.dao.ejb.GenericEjbDao;
import ec.gov.iess.hl.modelo.Afiliado;

/**
 * Implementacion AfiliadoDaoBiess
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Stateless
public class AfiliadoDaoBiessEjb extends GenericEjbDao<Afiliado, String> implements AfiliadoDaoBiess {

	@Resource(mappedName = "java:historiaLaboralEjbDS")
	private DataSource dataSource;

	public AfiliadoDaoBiessEjb() {
		super(Afiliado.class);
	}

	/* (non-Javadoc)
	 * @see ec.fin.biess.creditos.pq.dao.AfiliadoDaoBiess#actualizarDatosAfiliado(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String)
	 */
	public void actualizarDatosAfiliado(String numafi, String coddivpol, String dirafi, String telafi, String celafi,
			String maiafi, String posviv, Date fecactafi, String dpcodigo) throws AfiliadoExceptionBiess {
		final String SQL_UPDATE = "update kspcotafiliados " +
				" set coddivpol = ?, telafi = ?, dirafi = ?, maiafi = ?, celafi = ?, posviv = ?, fecactafi = ?, dp_codigo = ? " +
				" where numafi = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(SQL_UPDATE);
			statement.setString(1, coddivpol);
			statement.setString(2, telafi);
			statement.setString(3, dirafi);
			statement.setString(4, maiafi);
			statement.setString(5, celafi);
			statement.setString(6, posviv);
			statement.setDate(7, convertDate(fecactafi));
			statement.setString(8, dpcodigo);
			statement.setString(9, numafi);
			statement.execute();
		} catch (SQLException e) {
			throw new AfiliadoExceptionBiess("Error al actualizar datos del afiliado. CI: " + numafi, e);
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
	}

	/**
	 * Metodo que convierte un java.util.Date a java.sql.Date.
	 * 
	 * @param fecha
	 * @return java.sql.Date
	 */
	private static java.sql.Date convertDate(java.util.Date fecha) {
		return new java.sql.Date(fecha.getTime());
	}
	
}
