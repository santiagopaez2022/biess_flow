package ec.fin.biess.creditos.pq.dao.ejb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ec.fin.biess.creditos.pq.dao.CesantiasPQDao;
import ec.fin.biess.creditos.pq.excepcion.ConsultaCesantiaException;
import ec.gov.biess.util.log.LoggerBiess;
/***
 * 
 * @author luis.avila
 * REQ 560 IMPLEMENTACION DE LA LLAMADA AL PROCEDIMIENTO
 */
@Stateless	
public class CesantiasPQDaoEjb implements CesantiasPQDao{
	
	CallableStatement statement = null;
	Connection con = null;
	private static final String MENSAJE_ERROR = "Estimado cliente, realice la solicitud m\u00e1s tarde, estamos trabajando para servirle mejor.";
	private final LoggerBiess log = LoggerBiess
			.getLogger(CesantiasPQDaoEjb.class);
	
	@Resource(mappedName = "java:credito-pq-DS")
	DataSource dataSource;
	public String actualizarCesantias(String cedula) throws ConsultaCesantiaException {
		
		log.info("INICIA SP ACTUALIZACION");
		String sql = "{call PQ_OWNER.cre_procesosdebautomatico_pkg.cre_actualizarcesantias_prc(?,?,?,?)}";
		String retstatus = "";
		String mensajeError = "";
		try {
			
			con = dataSource.getConnection();
			statement = con.prepareCall(sql);
			if(cedula != null) {
				statement.setString(1, cedula);
				statement.registerOutParameter(2, Types.VARCHAR);
				statement.registerOutParameter(3, Types.VARCHAR);
				statement.setString(4, "Consulta");
				statement.execute();
				retstatus = statement.getString(2);
			}else {
				throw new ConsultaCesantiaException(MENSAJE_ERROR);
			}
			
			if("0".equals(retstatus)) {
				mensajeError = statement.getString(3);
				throw new ConsultaCesantiaException(MENSAJE_ERROR);
			}else {
				
				mensajeError = statement.getString(3);
				
			}
			log.info("FIN SP ACTUALIZACION"+mensajeError);
			con.close();
			statement.close();
			
		} catch (SQLException e) {
			log.info("ERROR AL LLAMADO DEL PROCEDIMIENTO");
			throw new ConsultaCesantiaException(MENSAJE_ERROR);
		}finally {
			try {
				if(statement!=null){
					statement.close();
				}
				if(con!=null){
					con.close();
				}
				
			} catch (SQLException e) {
				log.error("Error al cerrar conexion", e);
				
			}
		}
		return mensajeError;
	}

}
