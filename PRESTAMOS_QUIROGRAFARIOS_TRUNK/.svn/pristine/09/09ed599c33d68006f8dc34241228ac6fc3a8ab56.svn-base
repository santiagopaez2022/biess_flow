/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.sql.DataSource;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.LiquidacionGastoDao;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionGasto;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author rsambrano
 * 
 */
@Stateless
public class LiquidacionGastoDaoImpl extends
		GenericEjbDao<LiquidacionGasto, Long> implements LiquidacionGastoDao {

	@Resource(mappedName = "java:replica-tcs-DS")
	DataSource dataSource;

	LoggerBiess log = LoggerBiess.getLogger(LiquidacionGastoDaoImpl.class);

	/**
	 * @param type
	 */
	public LiquidacionGastoDaoImpl() {
		super(LiquidacionGasto.class);
	}

	@SuppressWarnings("unchecked")
	public LiquidacionGasto consultarSolicitudLiquidacionExistente(Long nut,
			String cedula) {
		Query q = em
				.createNamedQuery(
						"LiquidacionGasto.consultarSolicitudLiquidacionExistente")
				.setParameter("nut", nut).setParameter("cedula", cedula);

		List<LiquidacionGasto> lista = q.getResultList();
		if (lista != null && !lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<LiquidacionGasto> consultarSolicitudLiquidacion() {
		Query q = em
				.createNamedQuery("LiquidacionGasto.consultarSolicitudLiquidacion");

		List<LiquidacionGasto> lista = q.getResultList();
		if (lista.size() > 0) {
			return lista;
		}
		return null;
	}

	public BigDecimal obtenerCuotasRemanentes(Long nut) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(DISTINCT NRO_DIVIDENDO) ");
		sql.append("FROM T20TAM14 ");
		sql.append("WHERE ESTADO = 'PV' ");
		sql.append("AND NRO_OPERACION IN (SELECT NRO_OPERACION FROM ");
		sql.append("T20NUT20 WHERE COD_NUT = :nut)");

		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("nut", nut);
		return (BigDecimal) query.getSingleResult();
	}

	public BigDecimal obtenerValorOriginal(Long nut) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MONTO_ORG ");
		sql.append("FROM T20OPE01 ");
		sql.append("WHERE NRO_OPERACION IN (SELECT NRO_OPERACION FROM ");
		sql.append("T20NUT20 WHERE COD_NUT = :nut)  AND ROWNUM = 1 ");

		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("nut", nut);
		BigDecimal resultado = (BigDecimal) query.getSingleResult();
		return resultado != null ? resultado : BigDecimal.ZERO;
	}

	public List<LiquidacionGasto> consultarSolicitudLiquidacionCC() {
		Connection cnn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<LiquidacionGasto> lista = new ArrayList<LiquidacionGasto>();

		StringBuffer sql = new StringBuffer("");
		sql.append("EXEC dbo.SP_GENERA_INFO_LIQ_GASTOS ");
		log.info("DAO: sql : " + sql);

		int i =0;
		try {
			cnn = dataSource.getConnection();
			ps = cnn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				i++;
				LiquidacionGasto item = new LiquidacionGasto();
				String tipoLiquidacion = null;

				if (rs.getObject("TIPO_PORTAB") != null
						&& rs.getObject("TIPO_PORTAB").toString().trim()
								.toUpperCase().equals("CARTA COMPROMISO")) {
					tipoLiquidacion = "CC";
				}

				if (rs.getObject("NUT") != null && tipoLiquidacion != null) {
					item.setNut(Long.valueOf(rs.getObject("NUT").toString()));

					item.setPorcentajeParticipacion(rs.getObject("PORCENT_IND") != null ? new BigDecimal(
							rs.getObject("PORCENT_IND").toString()
									.replaceAll(",", "")) : BigDecimal.ZERO);
					item.setGastosProyectados(rs.getObject("GASTOS_PRY") != null ? new BigDecimal(
							rs.getObject("GASTOS_PRY").toString()
									.replaceAll(",", "")) : BigDecimal.ZERO);
					item.setGastosFinales(rs.getObject("GASTOS_REAL_IND") != null ? new BigDecimal(
							rs.getObject("GASTOS_REAL_IND").toString()
									.replaceAll(",", "")) : BigDecimal.ZERO);
					item.setDiferenciaGastos(item.getGastosProyectados()
							.subtract(item.getGastosFinales()));
					item.setTipoSolicitante(rs.getObject("TIPO_CREDITO") != null ? rs
							.getObject("TIPO_CREDITO").toString() : null);
					item.setCedula(rs.getObject("CED_IND") != null ? rs
							.getObject("CED_IND").toString() : null);
					item.setTipoLiquidacion(tipoLiquidacion);

					if (rs.getObject("CED_SOL") != null
							&& !rs.getObject("CED_SOL").toString().equals("0")) {
						item.setCedulaSolidario(rs.getObject("CED_SOL")
								.toString());
						item.setTipoSolicitante(rs.getObject("TIPO_CREDITO") != null ? rs
								.getObject("TIPO_CREDITO").toString()
								+ "/PRINCIPAL" : null);

						LiquidacionGasto itemSol = new LiquidacionGasto();
						itemSol.setNut(new Long(((Integer) rs.getObject("NUT"))
								.intValue()));
						itemSol.setPorcentajeParticipacion(rs
								.getObject("PORCENT_SOL") != null ? new BigDecimal(
								rs.getObject("PORCENT_SOL").toString()
										.replaceAll(",", "")) : BigDecimal.ZERO);
						itemSol.setGastosProyectados(rs.getObject("GASTOS_PRY") != null ? new BigDecimal(
								rs.getObject("GASTOS_PRY").toString()
										.replaceAll(",", "")) : BigDecimal.ZERO);
						itemSol.setGastosFinales(rs
								.getObject("GASTOS_REAL_SOL") != null ? new BigDecimal(
								rs.getObject("GASTOS_REAL_SOL").toString()
										.replaceAll(",", "")) : BigDecimal.ZERO);
						itemSol.setDiferenciaGastos(itemSol
								.getGastosProyectados().subtract(
										itemSol.getGastosFinales()));
						itemSol.setTipoSolicitante(rs.getObject("TIPO_CREDITO") != null ? rs
								.getObject("TIPO_CREDITO").toString() : null);
						itemSol.setCedula(rs.getObject("CED_SOL").toString());
						itemSol.setTipoLiquidacion(tipoLiquidacion);
						itemSol.setCedulaSolidario(rs.getObject("CED_IND") != null ? rs
								.getObject("CED_IND").toString() : null);

						lista.add(itemSol);
					}

					lista.add(item);
				}

			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (cnn != null) {
					cnn.close();
				}
			} catch (Exception e) {
				log.error("Error ", e);
			}
		}
		log.info(i+" registrados recuperados");
		return (lista != null && !lista.isEmpty()) ? lista
				: new ArrayList<LiquidacionGasto>();
	}

	public List<LiquidacionGasto> consultarSolicitudLiquidacionCM() {
		Connection cnn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<LiquidacionGasto> lista = new ArrayList<LiquidacionGasto>();

		StringBuffer sql = new StringBuffer("");
		sql.append("EXEC dbo.SP_GENERA_INFO_LIQ_GASTOS ");
		log.info("DAO: sql : " + sql);

		try {
			cnn = dataSource.getConnection();
			ps = cnn.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				LiquidacionGasto item = new LiquidacionGasto();
				String tipoLiquidacion = null;
				if (rs.getObject("TIPO_LIQ") != null
						&& rs.getObject("TIPO_LIQ").toString().trim()
								.toUpperCase().equals("CM")) {
					tipoLiquidacion = "CM";
				}

				if (rs.getObject("NUT") != null && tipoLiquidacion != null) {
					item.setNut(Long.valueOf(rs.getObject("NUT").toString()));

					item.setPorcentajeParticipacion(rs.getObject("PORCENT_IND") != null ? new BigDecimal(
							rs.getObject("PORCENT_IND").toString()
									.replaceAll(",", "")) : BigDecimal.ZERO);
					item.setGastosProyectados(rs.getObject("GASTOS_PRY") != null ? new BigDecimal(
							rs.getObject("GASTOS_PRY").toString()
									.replaceAll(",", "")) : BigDecimal.ZERO);
					item.setGastosFinales(rs.getObject("GASTOS_REAL_IND") != null ? new BigDecimal(
							rs.getObject("GASTOS_REAL_IND").toString()
									.replaceAll(",", "")) : BigDecimal.ZERO);
					item.setDiferenciaGastos(item.getGastosProyectados()
							.subtract(item.getGastosFinales()));
					item.setTipoSolicitante(rs.getObject("TIPO_CREDITO") != null ? rs
							.getObject("TIPO_CREDITO").toString() : null);
					item.setCedula(rs.getObject("CED_IND") != null ? rs
							.getObject("CED_IND").toString() : null);
					item.setTipoLiquidacion(tipoLiquidacion);

					if (rs.getObject("CED_SOL") != null
							&& !rs.getObject("CED_SOL").toString().equals("0")) {
						item.setCedulaSolidario(rs.getObject("CED_SOL")
								.toString());
						item.setTipoSolicitante(rs.getObject("TIPO_CREDITO") != null ? rs
								.getObject("TIPO_CREDITO").toString()
								+ "/PRINCIPAL" : null);

						LiquidacionGasto itemSol = new LiquidacionGasto();
						itemSol.setNut(new Long(((Integer) rs.getObject("NUT"))
								.intValue()));
						itemSol.setPorcentajeParticipacion(rs
								.getObject("PORCENT_SOL") != null ? new BigDecimal(
								rs.getObject("PORCENT_SOL").toString()
										.replaceAll(",", "")) : BigDecimal.ZERO);
						itemSol.setGastosProyectados(rs.getObject("GASTOS_PRY") != null ? new BigDecimal(
								rs.getObject("GASTOS_PRY").toString()
										.replaceAll(",", "")) : BigDecimal.ZERO);
						itemSol.setGastosFinales(rs
								.getObject("GASTOS_REAL_SOL") != null ? new BigDecimal(
								rs.getObject("GASTOS_REAL_SOL").toString()
										.replaceAll(",", "")) : BigDecimal.ZERO);
						itemSol.setDiferenciaGastos(itemSol
								.getGastosProyectados().subtract(
										itemSol.getGastosFinales()));
						itemSol.setTipoSolicitante(rs.getObject("TIPO_CREDITO") != null ? rs
								.getObject("TIPO_CREDITO").toString() : null);
						itemSol.setCedula(rs.getObject("CED_SOL").toString());
						itemSol.setTipoLiquidacion(tipoLiquidacion);
						itemSol.setCedulaSolidario(rs.getObject("CED_IND") != null ? rs
								.getObject("CED_IND").toString() : null);

						lista.add(itemSol);
					}

					lista.add(item);
				}

			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (cnn != null) {
					cnn.close();
				}
			} catch (Exception e) {
				log.error("Error ", e);
			}
		}

		return (lista != null && !lista.isEmpty()) ? lista
				: new ArrayList<LiquidacionGasto>();
	}

	@SuppressWarnings("unchecked")
	public List<LiquidacionGasto> consultarSolicitudLiquidacionGenerada(
			List<Long> listaTiposSolicitud) {
		Query q = em
				.createNamedQuery("LiquidacionGasto.consultarSolicitudLiquidacionGenerada");
		q.setParameter("codigosSolicitud", listaTiposSolicitud);
		List<LiquidacionGasto> lista = q.getResultList();
		if (lista.size() > 0) {
			return lista;
		}
		return null;
	}
}
