/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.sp.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.dto.SolicitudPago;
import ec.gov.iess.creditos.sp.SolicitudPagoJdbc;
import ec.gov.iess.creditos.util.desembolso.SolicitudPagoBiessBiessRM;
import ec.gov.iess.creditos.util.desembolso.SolicitudPagoBiessIfiRM;
import ec.gov.iess.creditos.util.desembolso.SolicitudPagoRM;
import ec.gov.iess.creditos.util.desembolso.SolicitudPagoShRM;
import ec.gov.iess.creditos.util.desembolso.reverso.SolicitudPagoBiessBiessReversoRM;
import ec.gov.iess.creditos.util.desembolso.reverso.SolicitudPagoBiessIfiReversoRM;

/**
 * <b> Clase para métodos de solicitudes de desembolso de PH </b>
 * 
 * @author Daniel Cardenas, Jenny Sanchez
 * 
 * @version $Revision: 1.6.6.1 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 2011/05/24 21:50:52 $]
 *          </p>
 */
@Stateless
public class SolicitudPagoJdbcImpl extends JdbcDaoSupport implements
		SolicitudPagoJdbc {

	private LoggerBiess log = LoggerBiess.getLogger(SolicitudPagoJdbcImpl.class);

	private static final String OBSERVACION_VALOR_FINANCIADO = "El Valor Financiado NO es igual al Monto Aprobado sumado los Gastos. ";
	private static final String OBSERVACION_VALOR_APROBADO = "El Monto Aprobado NO es igual al Valor Desembolsado sumado el Seguro de Vida y sumado el Seguro de Riesgos. ";
	private static final String OBSERVACION_VALOR_APROBADO_IFIS = "El Monto Aprobado NO es igual al Valor Desembolsado sumado el Seguro de Vida y sumado el Desembolso a la IFI. ";
	private static final String OBSERVACION_VALOR_APROBADO_BIESS = "El Monto Aprobado NO es igual al Valor Desembolsado sumado el Seguro de Vida y sumado la Cancelaci\u00F3n de operaciones. ";
	private static final String OBSERVACION_VALOR_FINANCIADO_SH = "El Valor Financiado NO es igual al Valor Desembolsado sumado los Gastos. ";
	private static final String OBSERVACION_VALOR_APROBADO_SH = "El Monto Aprobado NO es igual al Valor Desembolsado. ";
	private static final String OBSERVACION_VALOR_CANCELACION_OP_ANTERIOR = "La cancelaci\u00F3n de operaci\u00F3n anterior NO es igual a la suma de rubros. ";

	public SolicitudPagoJdbcImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * Wrapper para completar cada fila en el objeto SolicitudPago
	 */
	private RowMapper solicitudPagoMapper = new SolicitudPagoRM();

	/**
	 * Wrapper para completar cada fila en el objeto SolicitudPago
	 */
	private RowMapper solicitudUnificadaMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SolicitudPago solicitudPago = new SolicitudPago();
			solicitudPago.setCedula(rs.getString(1));
			solicitudPago.setTipoSolicitud(rs.getString(2));
			solicitudPago.setGastos(rs.getBigDecimal(3));
			solicitudPago.setNut(rs.getLong(4));
			solicitudPago.setSaldoCapital(rs.getBigDecimal(5));
			solicitudPago.setValorUnificado(rs.getBigDecimal(6));
			solicitudPago.setFechaUnificacion(rs.getDate(7));

			solicitudPago.setSeleccionado(true);
			return solicitudPago;
		}

	};

	private RowMapper solicitudPagoBiessIfiMapper = new SolicitudPagoBiessIfiRM();

	private RowMapper solicitudPagoBiessBiessMapper = new SolicitudPagoBiessBiessRM();

	// REVERSOS
	private RowMapper solicitudPagoBiessIfiReversoMapper = new SolicitudPagoBiessIfiReversoRM();

	private RowMapper solicitudPagoBiessBiessReversoMapper = new SolicitudPagoBiessBiessReversoRM();

	// SH
	private RowMapper solicitudPagoShMapper = new SolicitudPagoShRM();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.jdbc.SolicitudPagoJdbc#obtenerPorFechaSolicitudPago
	 * (java.util.Date, java.util.Date, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitudPago> obtenerPorFechaSolicitudPago(Date desde,
			Date hasta, List<Long> estados, List<Long> tiposPrestamos) {

		String cadenaEstados = fromListaToString(estados);
		String cadenaTiposPrestamos = fromListaToString(tiposPrestamos);

		String sql = "select ks.NUMAFI cedula, decode(ks.CODTIPSOLSER,35, 'INDIVIDUAL', 36, 'SOLIDARIA',"
				+ "37, 'INDIVIDUAL', 38, 'SOLIDARIA',39, 'INDIVIDUAL', 40, 'SOLIDARIA', 41, 'INDIVIDUAL', "
				+ "42,'SOLIDARIA',43,'INDIVIDUAL',44,'SOLIDARIA',46,'INDIVIDUAL',47,'SOLIDARIA', "
				+ "50,'INDIVIDUAL',51,'SOLIDARIA') tipo_sol,"
				+ "Kspcokdivpol.OBTNOMDIVPOL (ds.CODIVPOLAVA), DS.DEPFECESC fec_escritura, DS.DEPMON monto_total, "
				+ "DS.PLAAPR plazo, DS.DEPGTO gastos, DS.DEPDIV dividendo, KS.NUT nut, ds.CODAGN, ds.DEPVEN_ID, "
				+ "ds.DEPVENCTA,  ds.DEPVENTIPCTA, ds.DEPVENNOM, ds.DEPCODBAN, ds.DEPSEGCON, ds.DEPMONAPR, "
				+ "ds.DEPMONFIN, ds.DEPSEGVID "// ds.CODIVPOLAVA,
				+ "from kscretsolicitudes ks, cre_detallesolicitud_tbl ds, cre_procesosrealizados_tbl ps "
				+ "where ks.NUMSOLSER = ds.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ds.CODTIPSOLSER "
				+ "AND  ks.NUMSOLSER = ps.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ps.CODTIPSOLSER "
				+ "AND ks.codtipsolser in "
				+ cadenaTiposPrestamos
				+ "AND ks.codestsolser = 'SOL'  "
				+ "AND ps.CODESTPRO in "
				+ cadenaEstados
				+ " AND trunc(DS.FECSOLPAG) BETWEEN ? AND ?"
				+ " AND ps.secpro=( select MAX(secpro) FROM CRE_PROCESOSREALIZADOS_TBL ps "
				+ "WHERE ps.numsolser = ks.numsolser "
				+ "AND ps.codtipsolser = ks.codtipsolser )";

		log.debug("ejecuta consulta solicitud pago");

		List<SolicitudPago> lista = getJdbcTemplate().query(sql,
				new Object[] { desde, hasta }, solicitudPagoMapper);

		// VALIDAR CADA OPERACION:
		if (lista != null && !lista.isEmpty()) {
			if (tiposPrestamos.contains(41L) || tiposPrestamos.contains(42L)
					|| tiposPrestamos.contains(43L)
					|| tiposPrestamos.contains(44L)) {
				validarMontosPagoSh(lista);
			} else {
				validarMontosPago(lista);
			}
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.jdbc.SolicitudPagoJdbc#obtenerPorFechaSolicitudPago
	 * (java.util.Date, java.util.Date, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitudPago> obtenerSolicitudPagoTerrenoConstruccion(
			Date desde, Date hasta, List<Long> estados,
			List<Long> tiposPrestamos, List<String> tiposDesembolsos) {

		String cadenaEstados = fromListaToString(estados);
		String cadenaTiposPrestamos = fromListaToString(tiposPrestamos);
		String cadenaTiposDesembolsos = cambiarListaACadeba(tiposDesembolsos);

		String sql = "select ks.NUMAFI cedula, decode(ks.CODTIPSOLSER,48, 'INDIVIDUAL', 49, 'SOLIDARIA') tipo_sol,"
				+ "Kspcokdivpol.OBTNOMDIVPOL (ds.CODIVPOLAVA), DS.DEPFECESC fec_escritura, DS.DEPMON monto_total, "
				+ "DS.PLAAPR plazo, DS.DEPGTO gastos, DS.DEPDIV dividendo, KS.NUT nut, ds.CODAGN, ds.DEPVEN_ID, "
				+ "ds.DEPVENCTA,  ds.DEPVENTIPCTA, ds.DEPVENNOM, ds.DEPCODBAN, ds.DEPSEGCON, ds.DEPMONAPR, "
				+ "ds.DEPMONFIN, ds.DEPSEGVID "
				+ "from kscretsolicitudes ks, cre_detallesolicitud_tbl ds, cre_procesosrealizados_tbl ps "
				+ "where ks.NUMSOLSER = ds.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ds.CODTIPSOLSER "
				+ "AND  ks.NUMSOLSER = ps.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ps.CODTIPSOLSER "
				+ "AND ks.codtipsolser in "
				+ cadenaTiposPrestamos
				+ "AND ks.codestsolser in ('SOL','VIG')  "
				+ "AND ds.ds_tipodesembolso in "
				+ cadenaTiposDesembolsos
				+ " AND ps.CODESTPRO in "
				+ cadenaEstados
				+ " AND ps.secpro=( select MAX(secpro) FROM CRE_PROCESOSREALIZADOS_TBL ps "
				+ "WHERE ps.numsolser = ks.numsolser "
				+ "AND ps.codtipsolser = ks.codtipsolser )";

		log.debug("ejecuta consulta solicitud pago");

		List<SolicitudPago> lista = getJdbcTemplate().query(sql,
				solicitudPagoMapper);

		// VALIDAR CADA OPERACION:
		if (lista != null && !lista.isEmpty()) {
			validarMontosPago(lista);
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.jdbc.SolicitudPagoJdbc#obtenerPorFechaSolicitudPago
	 * (java.util.Date, java.util.Date, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitudPago> obtenerSolicitudPagoTerrenoConstruccionUnificado(
			Date desde, Date hasta, List<Long> estados,
			List<Long> tiposPrestamos, List<String> tiposDesembolsos) {

		String cadenaEstados = fromListaToString(estados);
		String cadenaTiposPrestamos = fromListaToString(tiposPrestamos);
		String cadenaTiposDesembolsos = cambiarListaACadeba(tiposDesembolsos);

		String sql = "select ks.NUMAFI cedula, decode(ks.CODTIPSOLSER,48, 'INDIVIDUAL', 49, 'SOLIDARIA') tipo_sol,"
				+ "DS.DEPGTO gastos, KS.NUT nut, ds.DS_SALDOCAPITAL SALDO_CAP_UNI,   ds.DS_VALORUNIFICADO VALOR_UNI, "
				+ "ds.fecsolpag FECHA_UNI "
				+ "from kscretsolicitudes ks, cre_detallesolicitud_tbl ds, cre_procesosrealizados_tbl ps  "
				+ "where ks.NUMSOLSER = ds.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ds.CODTIPSOLSER "
				+ "AND  ks.NUMSOLSER = ps.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ps.CODTIPSOLSER "
				+ "AND ks.codtipsolser in "
				+ cadenaTiposPrestamos
				+ "AND ks.codestsolser in ('VIG')  "
				+ "AND ds.ds_tipodesembolso in "
				+ cadenaTiposDesembolsos
				+ " AND ps.CODESTPRO in "
				+ cadenaEstados
				+ " AND ps.secpro=( select MAX(secpro) FROM CRE_PROCESOSREALIZADOS_TBL ps "
				+ "WHERE ps.numsolser = ks.numsolser "
				+ "AND ps.codtipsolser = ks.codtipsolser )";

		log.debug("ejecuta consulta solicitud pago");

		List<SolicitudPago> lista = getJdbcTemplate().query(sql,
				solicitudUnificadaMapper);

		return lista;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.sp.SolicitudPagoJdbc#obtenerSolicitudPagoBiessIfi(java.util.Date,
	 *      java.util.Date, java.util.List, java.util.List)
	 */
	public List<SolicitudPago> obtenerSolicitudPagoBiessIfi(Date desde,
			Date hasta, List<Long> estados, List<Long> tiposPrestamos,
			boolean isReverso) {
		// String estado = isReverso ? "VIG" : "SOL";
		if (isReverso) {
			return obtenerSolicitudPagoReversoVH(desde, hasta, estados,
					tiposPrestamos, true, "VIG");
		} else {
			return obtenerSolicitudPagoVH(desde, hasta, estados,
					tiposPrestamos, true, "SOL");
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.sp.SolicitudPagoJdbc#obtenerSolicitudPagoBiessBiess(java.util.Date,
	 *      java.util.Date, java.util.List, java.util.List)
	 */
	public List<SolicitudPago> obtenerSolicitudPagoBiessBiess(Date desde,
			Date hasta, List<Long> estados, List<Long> tiposPrestamos,
			boolean isReverso) {
		if (isReverso) {
			return obtenerSolicitudPagoReversoVH(desde, hasta, estados,
					tiposPrestamos, false, "VIG");
		} else {
			return obtenerSolicitudPagoVH(desde, hasta, estados,
					tiposPrestamos, false, "SOL");
		}

	}

	@SuppressWarnings("unchecked")
	private List<SolicitudPago> obtenerSolicitudPagoVH(Date desde, Date hasta,
			List<Long> estados, List<Long> tiposPrestamos, boolean esIfi,
			String estado) {

		RowMapper rowMapper = solicitudPagoBiessIfiMapper;

		String cadenaEstados = fromListaToString(estados);
		String cadenaTiposPrestamos = fromListaToString(tiposPrestamos);

		String select = "select ks.NUMAFI cedula, decode(ks.CODTIPSOLSER,35, 'INDIVIDUAL', 36, 'SOLIDARIA',"
				+ "37, 'INDIVIDUAL', 38, 'SOLIDARIA',39, 'INDIVIDUAL', 40, 'SOLIDARIA', 41, 'INDIVIDUAL', "
				+ "42,'SOLIDARIA',43,'INDIVIDUAL',44,'SOLIDARIA',46,'INDIVIDUAL',47,'SOLIDARIA', "
				+ "50,'INDIVIDUAL',51,'SOLIDARIA',57,'INDIVIDUAL',58,'SOLIDARIA') tipo_sol,"
				+ "Kspcokdivpol.OBTNOMDIVPOL (ds.CODIVPOLAVA), DS.DEPFECESC fec_escritura, DS.DEPMON monto_total, "
				+ "DS.PLAAPR plazo, DS.DEPGTO gastos, DS.DEPDIV dividendo, KS.NUT nut, ds.CODAGN, ds.DEPVEN_ID, "
				+ "ds.DEPVENCTA,  ds.DEPVENTIPCTA, ds.DEPVENNOM, ds.DEPCODBAN, ds.DEPSEGCON, ds.DEPMONAPR, "
				+ "ds.DEPMONFIN, ds.DEPSEGVID ";
		String from = " from kscretsolicitudes ks, cre_detallesolicitud_tbl ds, cre_procesosrealizados_tbl ps";
		String where = " where ks.NUMSOLSER = ds.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ds.CODTIPSOLSER "
				+ "AND  ks.NUMSOLSER = ps.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ps.CODTIPSOLSER "
				+ "AND ks.codtipsolser in "
				+ cadenaTiposPrestamos
				+ "AND ks.codestsolser = '"
				+ estado
				+ "'  "
				+ "AND ps.CODESTPRO in "
				+ cadenaEstados
				+ " AND trunc(DS.FECSOLPAG) BETWEEN ? AND ?"
				+ " AND ps.secpro=( select MAX(secpro) FROM CRE_PROCESOSREALIZADOS_TBL ps "
				+ "WHERE ps.numsolser = ks.numsolser "
				+ "AND ps.codtipsolser = ks.codtipsolser )";

		if (esIfi) {
			select += ", dsi.DI_VALDESEMIFI, dsi.DI_NUMCTAIFI, dsi.DI_TIPCTAIFI, dsi.DI_RUCIFI, dsi.DI_TIPGARANTIAIFI";
			from += ", CRE_DETALLESOLICITUDIFI_TBL dsi";
			where += " and dsi.DI_CODDETSOL = ds.CODDETSOL";
			rowMapper = solicitudPagoBiessIfiMapper;
		} else {
			select += ", oc.OC_NUTOPERACION, oc.OC_VALORCAPITAL, oc.OC_VALORINTERES, oc.OC_VALORSEGINC, oc.OC_VALORSEGDES, oc.OC_VALORINTMORA";
			select += ", oc.OC_VALOROPERACION, OC_VALORIMPUESTOS, OC_VALORSEGVIDA ";
			from += ", CRE_OPERACIONESCAN_TBL oc";
			where += " and oc.OC_CODDETSOL = ds.CODDETSOL";
			rowMapper = solicitudPagoBiessBiessMapper;
		}

		String sql = select + from + where;
		log.debug("ejecuta consulta solicitud pago");
		log.info(sql);

		List<SolicitudPago> lista = getJdbcTemplate().query(sql,
				new Object[] { desde, hasta }, rowMapper);

		// VALIDAR CADA OPERACION:
		if (lista != null && !lista.isEmpty()) {
			if (tiposPrestamos.contains(57L) || tiposPrestamos.contains(58L)) {
				validarMontosPagoVh(lista, esIfi);
			} else {
				validarMontosPago(lista);
			}
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private List<SolicitudPago> obtenerSolicitudPagoReversoVH(Date desde,
			Date hasta, List<Long> estados, List<Long> tiposPrestamos,
			boolean esIfi, String estado) {

		RowMapper rowMapper = solicitudPagoBiessIfiReversoMapper;

		String cadenaEstados = fromListaToString(estados);
		String cadenaTiposPrestamos = fromListaToString(tiposPrestamos);

		String select = "select ks.NUMAFI cedula, decode(ks.CODTIPSOLSER,35, 'INDIVIDUAL', 36, 'SOLIDARIA',"
				+ "37, 'INDIVIDUAL', 38, 'SOLIDARIA',39, 'INDIVIDUAL', 40, 'SOLIDARIA', 41, 'INDIVIDUAL', "
				+ "42,'SOLIDARIA',43,'INDIVIDUAL',44,'SOLIDARIA',46,'INDIVIDUAL',47,'SOLIDARIA', "
				+ "50,'INDIVIDUAL',51,'SOLIDARIA',57,'INDIVIDUAL',58,'SOLIDARIA') tipo_sol,"
				+ "Kspcokdivpol.OBTNOMDIVPOL (ds.CODIVPOLAVA), DS.DEPFECESC fec_escritura, DS.DEPMON monto_total, "
				+ "DS.PLAAPR plazo, DS.DEPGTO gastos, DS.DEPDIV dividendo, KS.NUT nut, ds.CODAGN, ds.DEPVEN_ID, "
				+ "ds.DEPVENCTA,  ds.DEPVENTIPCTA, ds.DEPVENNOM, ds.DEPCODBAN, ds.DEPSEGCON, ds.DEPMONAPR, "
				+ "ds.DEPMONFIN, ds.DEPSEGVID, "
				+ "ds.DS_TIPODESEMBOLSO, bd.G01, bd.G05, bd.R06 ";
		String from = " from kscretsolicitudes ks, cre_detallesolicitud_tbl ds, cre_procesosrealizados_tbl ps"
				+ ", KSRECTINTBANCENDET bd ";
		String where = " where ks.NUMSOLSER = ds.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ds.CODTIPSOLSER "
				+ "AND  ks.NUMSOLSER = ps.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ps.CODTIPSOLSER "
				+ "AND ks.codtipsolser in "
				+ cadenaTiposPrestamos
				+ "AND ks.codestsolser = '"
				+ estado
				+ "'  "
				+ "AND ps.CODESTPRO in "
				+ cadenaEstados
				+ " AND trunc(DS.FECSOLPAG) BETWEEN ? AND ?"
				+ " AND ps.secpro=( select MAX(secpro) FROM CRE_PROCESOSREALIZADOS_TBL ps "
				+ "WHERE ps.numsolser = ks.numsolser "
				+ "AND ps.codtipsolser = ks.codtipsolser )"
				+ " AND bd.g16 = ds.NUTDEP " + " AND bd.TIPINT = 'P' "
				+ " AND bd.CODTIPCOMPAG = 'DESPH' "
				+ " AND bd.CONINT in ('PAGCREHIP','PAGREVHIP')";

		if (esIfi) {
			select += ", dsi.DI_VALDESEMIFI, dsi.DI_NUMCTAIFI, dsi.DI_TIPCTAIFI, dsi.DI_RUCIFI, dsi.DI_TIPGARANTIAIFI";
			from += ", CRE_DETALLESOLICITUDIFI_TBL dsi";
			where += " and dsi.DI_CODDETSOL = ds.CODDETSOL";
			rowMapper = solicitudPagoBiessIfiReversoMapper;
		} else {
			select += ", oc.OC_NUTOPERACION, oc.OC_VALORCAPITAL, oc.OC_VALORINTERES, oc.OC_VALORSEGINC, oc.OC_VALORSEGDES, oc.OC_VALORINTMORA";
			select += ", oc.OC_VALOROPERACION, OC_VALORIMPUESTOS, OC_VALORSEGVIDA ";
			from += ", CRE_OPERACIONESCAN_TBL oc";
			where += " and oc.OC_CODDETSOL = ds.CODDETSOL";
			rowMapper = solicitudPagoBiessBiessReversoMapper;
		}
		// PAGCREHIP, PAGREVHIP

		String sql = select + from + where;
		log.debug("ejecuta consulta solicitud pago");
		log.info(sql);

		List<SolicitudPago> lista = getJdbcTemplate().query(sql,
				new Object[] { desde, hasta }, rowMapper);

		// VALIDAR CADA OPERACION:
		if (lista != null && !lista.isEmpty()) {
			if (tiposPrestamos.contains(57L) || tiposPrestamos.contains(58L)) {
				validarMontosPagoVh(lista, esIfi);
			} else {
				validarMontosPago(lista);
			}
		}
		return lista;
	}

	@SuppressWarnings("rawtypes")
	private String fromListaToString(List<Long> estados) {
		String cadenaEstados = "(";
		for (Iterator iterator = estados.iterator(); iterator.hasNext();) {
			Long estado = (Long) iterator.next();
			cadenaEstados += "'" + estado + "'";
			if (iterator.hasNext()) {
				cadenaEstados += ",";
			}
		}
		cadenaEstados += ")";
		return cadenaEstados;
	}

	/**
	 * 
	 * <b> Método para obtener una cadena String de la lista de objetos enviada
	 * </b>
	 * <p>
	 * [Author: jsanchez, Date: 22/09/2011]
	 * </p>
	 * 
	 * @param tipos
	 *            c&#243;digos de tipos de desembolsos
	 * @return cadena
	 */
	@SuppressWarnings("rawtypes")
	private String cambiarListaACadeba(List<String> tipos) {
		String cadenaTipos = "(";
		for (Iterator iterator = tipos.iterator(); iterator.hasNext();) {
			String estado = (String) iterator.next();
			cadenaTipos += "'" + estado + "'";
			if (iterator.hasNext()) {
				cadenaTipos += ",";
			}
		}
		cadenaTipos += ")";
		return cadenaTipos;
	}

	/**
	 * 
	 * <b> Método para validar los montos a desembolsos </b>
	 * <p>
	 * [Author: Jenny Sanchez, Date: 20/12/2010]
	 * </p>
	 * 
	 * @param lista
	 */
	private void validarMontosPago(List<SolicitudPago> lista) {
		for (SolicitudPago pago : lista) {
			StringBuilder observacionPago = new StringBuilder();
			// ValorFinanciado = MontoAprobado + GastosAdministrativos
			validarValorFinanciado(pago, observacionPago);
			// MontoAprobado = ValorDesembolsado + SeguroVida +
			// SeguroTotalRiesgos
			validarMontoAprobado(pago, observacionPago);

			verificarObservaciones(pago, observacionPago);
		}
	}

	/**
	 * 
	 * <b> Método para validar los montos a desembolsos para Sustituci&#243;n
	 * Hipoteca</b>
	 * <p>
	 * [Author: Jenny Sanchez, Date: 20/12/2010]
	 * </p>
	 * 
	 * @param lista
	 */
	private void validarMontosPagoSh(List<SolicitudPago> lista) {
		for (SolicitudPago pago : lista) {
			StringBuilder observacionPago = new StringBuilder();
			// ValorFinanciado = MontoAprobado + GastosAdministrativos
			validarValorFinanciado(pago, observacionPago);
			// ValorFinanciado = ValorDesembolsado + GastosAdministrativos
			if (pago.getValorFinanciado() == null || pago.getMonto() == null
					|| pago.getGastos() == null) {
				observacionPago.append(OBSERVACION_VALOR_FINANCIADO_SH);
			} else {
				BigDecimal sumaMonto = pago.getMonto().add(pago.getGastos());
				if (pago.getValorFinanciado().compareTo(sumaMonto) != 0) {
					observacionPago.append(OBSERVACION_VALOR_FINANCIADO_SH);
				}
			}
			// ValorAprobado=ValorDesembolsado
			if (pago.getValorAprobado() == null || pago.getMonto() == null) {
				observacionPago.append(OBSERVACION_VALOR_APROBADO_SH);
			} else {
				if (pago.getValorAprobado().compareTo(pago.getMonto()) != 0) {
					observacionPago.append(OBSERVACION_VALOR_APROBADO_SH);
				}
			}

			verificarObservaciones(pago, observacionPago);
		}
	}

	/**
	 * 
	 * <b> Método para validar los montos a desembolsos para Vivienda
	 * Hipotecada</b>
	 * <p>
	 * [Author: rsambrano, Date: 05/07/2012]
	 * </p>
	 * 
	 * @param lista
	 */
	private void validarMontosPagoVh(List<SolicitudPago> lista, boolean esIfi) {
		for (SolicitudPago pago : lista) {
			StringBuilder observacionPago = new StringBuilder();
			if (esIfi) {
				validarMontoAprobadoBiessIfis(pago, observacionPago);
			} else {
				validarMontoAprobadoBiessBiess(pago, observacionPago);
			}

			validarValorFinanciado(pago, observacionPago);

			verificarObservaciones(pago, observacionPago);
		}
	}

	/**
	 * Valida que ValorFinanciado = MontoAprobado + GastosAdministrativos
	 */
	private void validarValorFinanciado(SolicitudPago pago,
			StringBuilder observacionPago) {
		if (pago.getValorFinanciado() == null
				|| pago.getValorAprobado() == null || pago.getGastos() == null) {
			observacionPago.append(OBSERVACION_VALOR_FINANCIADO);
		} else if (pago.getValorFinanciado().compareTo(
				pago.getValorAprobado().add(pago.getGastos())) != 0) {
			observacionPago.append(OBSERVACION_VALOR_FINANCIADO);
		}
	}

	/**
	 * Valida que MontoAprobado = ValorDesembolsado + SeguroVida +
	 * SeguroTotalRiesgos
	 */
	private void validarMontoAprobado(SolicitudPago pago,
			StringBuilder observacionPago) {
		if (pago.getValorAprobado() == null || pago.getMonto() == null
				|| pago.getValorSeguroVida() == null
				|| pago.getValorSeguroRiesgos() == null) {
			observacionPago.append(OBSERVACION_VALOR_APROBADO);
		} else {
			BigDecimal sumaMonto = pago.getMonto()
					.add(pago.getValorSeguroVida().add(
							pago.getValorSeguroRiesgos()));
			if (pago.getValorAprobado().compareTo(sumaMonto) != 0) {
				observacionPago.append(OBSERVACION_VALOR_APROBADO);
			}
		}
	}

	private void verificarObservaciones(SolicitudPago pago,
			StringBuilder observacionPago) {
		if (observacionPago == null || "".equals(observacionPago.toString())) {
			pago.setObservacion(null);
			pago.setSeleccionado(true);
		} else {
			pago.setObservacion(observacionPago.toString());
			pago.setSeleccionado(false);
		}
	}

	/**
	 * Valida que MontoAprobado = ValorDesembolsado + SeguroVida +
	 * SeguroTotalRiesgos
	 */
	private void validarMontoAprobadoBiessIfis(SolicitudPago pago,
			StringBuilder observacionPago) {
		if (pago.getValorAprobado() == null || pago.getMonto() == null
				|| pago.getValorSeguroVida() == null
				|| pago.getDesembolsoIfi() == null) {
			observacionPago.append(OBSERVACION_VALOR_APROBADO_IFIS);
		} else {
			BigDecimal sumaMonto = pago.getMonto().add(
					pago.getDesembolsoIfi().add(pago.getValorSeguroVida()));
			if (pago.getValorAprobado().compareTo(sumaMonto) != 0) {
				observacionPago.append(OBSERVACION_VALOR_APROBADO_IFIS);
			}
		}
	}

	private void validarMontoAprobadoBiessBiess(SolicitudPago pago,
			StringBuilder observacionPago) {
		if (pago.getValorAprobado() == null || pago.getMonto() == null
				|| pago.getValorSeguroVida() == null
				|| pago.getValorSeguroRiesgos() == null) {
			observacionPago.append(OBSERVACION_VALOR_APROBADO_BIESS);
		} else {
			BigDecimal sumaMonto = pago.getMonto().add(
					pago.getValorSeguroVida().add(
							pago.getCancelacionOperacion()));
			if (pago.getValorAprobado().compareTo(sumaMonto) != 0) {
				observacionPago.append(OBSERVACION_VALOR_APROBADO_BIESS);
			}
		}

		validarCancelacionOpAnterior(pago, observacionPago);
	}

	private void validarCancelacionOpAnterior(SolicitudPago pago,
			StringBuilder observacionPago) {
		if (pago.getValorFinanciado() == null
				|| pago.getValorAprobado() == null || pago.getGastos() == null) {
			observacionPago.append(OBSERVACION_VALOR_CANCELACION_OP_ANTERIOR);
		} else {
			BigDecimal sumaCancelacionOpAnterior = pago
					.getValorCapitalOpCancelada()
					.add(pago
							.getValorInteresOpCancelada()
							.add(pago
									.getValorPrimasSegIngOpCancelada()
									.add(pago
											.getValorPrimasSegDesOpCancelada()
											.add(pago
													.getValorInteresMoraOpCancelada()
													.add(pago
															.getValorImpuestosOpCancelada()
															.add(pago
																	.getValorSegVidaOpCancelada()))))));
			if (pago.getCancelacionOperacion().compareTo(
					sumaCancelacionOpAnterior) != 0) {
				observacionPago
						.append(OBSERVACION_VALOR_CANCELACION_OP_ANTERIOR);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<SolicitudPago> obtenerPorFechaSolicitudPagoSH(Date desde,
			Date hasta, List<Long> estados, List<Long> tiposPrestamos) {

		String cadenaEstados = fromListaToString(estados);
		String cadenaTiposPrestamos = fromListaToString(tiposPrestamos);

		String sql = "select ks.NUMAFI cedula, decode(ks.CODTIPSOLSER,35, 'INDIVIDUAL', 36, 'SOLIDARIA',"
				+ "37, 'INDIVIDUAL', 38, 'SOLIDARIA',39, 'INDIVIDUAL', 40, 'SOLIDARIA', 41, 'INDIVIDUAL', "
				+ "42,'SOLIDARIA',43,'INDIVIDUAL',44,'SOLIDARIA',46,'INDIVIDUAL',47,'SOLIDARIA', "
				+ "50,'INDIVIDUAL',51,'SOLIDARIA') tipo_sol,"
				+ "Kspcokdivpol.OBTNOMDIVPOL (ds.CODIVPOLAVA), DS.DEPFECESC fec_escritura, DS.DEPMON monto_total, "
				+ "DS.PLAAPR plazo, DS.DEPGTO gastos, DS.DEPDIV dividendo, KS.NUT nut, ds.CODAGN, ds.DEPVEN_ID, "
				+ "ds.DEPVENCTA,  ds.DEPVENTIPCTA, ds.DEPVENNOM, ds.DEPCODBAN, ds.DEPSEGCON, ds.DEPMONAPR, "
				+ "ds.DEPMONFIN, ds.DEPSEGVID, ds.DS_TIPODESEMBOLSO "// ds.CODIVPOLAVA,
				+ "from kscretsolicitudes ks, cre_detallesolicitud_tbl ds, cre_procesosrealizados_tbl ps "
				+ "where ks.NUMSOLSER = ds.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ds.CODTIPSOLSER "
				+ "AND  ks.NUMSOLSER = ps.NUMSOLSER "
				+ "AND ks.CODTIPSOLSER = ps.CODTIPSOLSER "
				+ "AND ks.codtipsolser in "
				+ cadenaTiposPrestamos
				+ "AND ks.codestsolser = 'SOL'  "
				+ "AND ps.CODESTPRO in "
				+ cadenaEstados
				+ " AND trunc(DS.FECSOLPAG) BETWEEN ? AND ?"
				+ " AND ps.secpro=( select MAX(secpro) FROM CRE_PROCESOSREALIZADOS_TBL ps "
				+ "WHERE ps.numsolser = ks.numsolser "
				+ "AND ps.codtipsolser = ks.codtipsolser )";

		log.debug("ejecuta consulta solicitud pago");

		List<SolicitudPago> lista = getJdbcTemplate().query(sql,
				new Object[] { desde, hasta }, solicitudPagoShMapper);

		// VALIDAR CADA OPERACION:
		if (lista != null && !lista.isEmpty()) {
			if (tiposPrestamos.contains(41L) || tiposPrestamos.contains(42L)
					|| tiposPrestamos.contains(43L)
					|| tiposPrestamos.contains(44L)) {
				validarMontosPagoSh(lista);
			} else {
				validarMontosPago(lista);
			}
		}
		return lista;
	}
}