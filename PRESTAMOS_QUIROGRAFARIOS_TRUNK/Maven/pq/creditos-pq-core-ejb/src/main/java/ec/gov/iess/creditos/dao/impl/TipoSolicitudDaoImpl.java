/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */

package ec.gov.iess.creditos.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.sql.DataSource;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.TipoSolicitudDao;
import ec.gov.iess.creditos.excepcion.GenerarSecuenciaException;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitudCredito;
import ec.gov.iess.dao.ejb.GenericEjbDao;
import java.math.BigDecimal;
/** 
 * <b>
 * Permite la Obtención de los secuenciales para solicitud de Préstamos Quirografarios
 * </b>
 *  
 * @author cbastidas
 * @version $Revision: 1.4 $ <p>[$Author: smanosalvas $, $Date: 2011/03/01 16:01:16 $]</p>
*/ 
@Stateless
public class TipoSolicitudDaoImpl extends
		GenericEjbDao<TipoSolicitudCredito, Serializable> implements
		TipoSolicitudDao {

	LoggerBiess log = LoggerBiess.getLogger(TipoSolicitudDaoImpl.class);

	@Resource(mappedName = "java:credito-pq-DS")
	private DataSource dataSource;

	public TipoSolicitudDaoImpl() {
		super(TipoSolicitudCredito.class);
	}

	public TipoSolicitudCredito consultarTipoSolicitud(int idTipoCredito,
			int idVariedadCrdeito) {
		log.debug(" consultarTipoSolicitud");
		log.debug(" Parametros");
		log.debug(" idTipoCredito: " + idTipoCredito);
		log.debug(" idVariedadCrdeito: " + idVariedadCrdeito);

		Query query = em
				.createNamedQuery("TipoSolicitudCredito.consultarSerialSolicitud");
		query.setParameter("idTipoCredito", new Long(idTipoCredito));
		query.setParameter("idVariedadCredito", new Long(idVariedadCrdeito));

		try {
			return (TipoSolicitudCredito) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug(" No existen datos");
			return null;
		} catch (NonUniqueResultException e) {
			log.debug("Datos duplicados :", e);
			throw new RuntimeException("Datos duplicados de la solicitud");
		}
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public TipoSolicitudCredito getSecuencia(int idTipoCredito,
			int idVariedadCredito) throws GenerarSecuenciaException {

		String query = "select CODTIPSOLSER, NUMSECTIPSOL from Kscretsersoltip where CODPRECLA = ? and CODPRETIP = ? for update nowait";
		String update = "update Kscretsersoltip set NUMSECTIPSOL = ? where CODTIPSOLSER = ?";
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		TipoSolicitudCredito tipoSolicitudCredito = new TipoSolicitudCredito();

		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, idVariedadCredito);
			pstmt.setInt(2, idTipoCredito);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				Long id = rs.getLong(1);
				Long secuencia = rs.getLong(2);

				tipoSolicitudCredito.setCodtipsolser(id);

				secuencia = secuencia + 1;
				tipoSolicitudCredito.setNumsectipsol(secuencia);

				pstmt = connection.prepareStatement(update);
				pstmt.setLong(1, secuencia);
				pstmt.setLong(2, id);
				pstmt.execute();
				
			}
		} catch (SQLException e1) {
			log.error(e1);
			throw new GenerarSecuenciaException(
					"Error al generar la secuencia KSCRETSERSOLTIP");
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
		return tipoSolicitudCredito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.TipoSolicitudDao#consultarTipoSolicitud(java.lang.Long)
	 */
	public TipoSolicitudCredito consultarTipoSolicitud(Long idTipoSolicitud) {

		Query query = em
				.createNamedQuery("TipoSolicitudCredito.consultarTipoSolicitud");
		query.setParameter("idTipoSolicitud", idTipoSolicitud);

		try {
			return (TipoSolicitudCredito) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug(" No existen datos en el tipo de solicitud de credito");
			return null;
		} catch (NonUniqueResultException e) {
			log.error("Datos duplicados :", e);
			throw new RuntimeException("Datos duplicados de la solicitud");
		}
	}
        
        @Override
        public long obtenerSecuenciaSolicitud(String nombreSecuencia) {
            StringBuilder sql = new StringBuilder();
            sql.append("select ");
            sql.append(nombreSecuencia);
            sql.append(".nextval from dual");

            Query query = em.createNativeQuery(sql.toString());
            BigDecimal resultado = (BigDecimal) query.getSingleResult();
            return resultado.longValue();
        }
}
