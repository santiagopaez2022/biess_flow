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
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.QueryGenericoDao;
import ec.gov.iess.creditos.excepcion.QueryGenericoException;
/**
 * Implementacion Dao Ejb para el Query Generico Dao
 * 
 * @version 1.0
 * @author rtituana
 */
@Stateless
public class QueryGenericoDaoImpl implements QueryGenericoDao{
	
	LoggerBiess log = LoggerBiess.getLogger(QueryGenericoDaoImpl.class);
	
	@Resource(mappedName = "java:credito-pq-DS")
	private DataSource dataSource;
	
	
	/**
	 * @see ec.gov.iess.creditos.dao.QueryGenericoDao#ejecutarQueryGenerico(String query)
	 *      java.lang.String, java.lang.String)
	 */
	public void ejecutarQueryGenerico(String query) throws QueryGenericoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.executeQuery();
		} catch (SQLException e1) {
			log.error(e1);
			throw new QueryGenericoException("Error de bloqueos ejecutando query: "+query+" - "+e1.getMessage(),e1);
		} finally {
			
			try {
				pstmt.close();
			} catch (Exception e) {
				log.error(e);
				throw new QueryGenericoException("Error al ejecutar query generico: "+e.getMessage(),e);
			}
			try {
				connection.close();
			} catch (Exception e) {
				log.error(e);
				throw new QueryGenericoException("Error al ejecutar query generico: "+e.getMessage(),e);
			}
		}
	}

}
