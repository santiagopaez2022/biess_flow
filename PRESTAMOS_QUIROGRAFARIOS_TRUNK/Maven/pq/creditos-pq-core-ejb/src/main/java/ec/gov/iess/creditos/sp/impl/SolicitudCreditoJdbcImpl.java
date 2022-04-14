/**
 * 
 */
package ec.gov.iess.creditos.sp.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.sp.SolicitudCreditoJdbc;

/**
 * @author jvaca
 * 
 */
public class SolicitudCreditoJdbcImpl extends JdbcDaoSupport implements
		SolicitudCreditoJdbc {
	
	 private LoggerBiess log = LoggerBiess.getLogger(SolicitudCreditoJdbcImpl.class);
	public SolicitudCreditoJdbcImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@SuppressWarnings("unchecked")
	public List<String> consultarCedulasConSolicitudDuplicada(
			List<Long> tiposSolicitud, String estado, Integer count) {
		
		log.debug("tiposSolicitud: " + tiposSolicitud);
		log.debug("estado: " + estado);
		log.debug("count: " + count);
		StringBuffer cadenaTipos = new StringBuffer();
		cadenaTipos.append('(');
		for (Iterator<Long> iterator = tiposSolicitud.iterator(); iterator
				.hasNext();) {
			Long tipo = iterator.next();
			cadenaTipos.append(tipo);
			if (iterator.hasNext()) {
				cadenaTipos.append(',');
			}
		}
		cadenaTipos.append(')');

		StringBuffer sql = new StringBuffer("select numafi\n").append(
				"from kscretsolicitudes\n").append("where codtipsolser in")
				.append(cadenaTipos).append("\n").append(
						"and codestsolser =?\n")
				.append("group by numafi\n")
				.append("having count(*) >= ?");
		
		log.debug(sql);
		List<String> resultado = getJdbcTemplate().query(sql.toString(),
				new Object[] { estado, count }, new RowMapper() {

					public Object mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						String cedula = arg0.getString("numafi");
						return cedula;
					}

				});
		return resultado;
	}

}
