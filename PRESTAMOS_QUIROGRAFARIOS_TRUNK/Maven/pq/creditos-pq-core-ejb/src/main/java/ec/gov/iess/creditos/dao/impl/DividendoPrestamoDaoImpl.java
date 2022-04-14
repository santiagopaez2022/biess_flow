/**
 * 
 */

package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.DividendoPrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal 03/10/2011
 * 
 */
@Stateless
public class DividendoPrestamoDaoImpl extends
		GenericEjbDao<DividendoPrestamo, DividendoPrestamoPk> implements
		DividendoPrestamoDao {

	LoggerBiess log = LoggerBiess.getLogger(DividendoPrestamoDaoImpl.class);

	/**
	 * @param type
	 */
	public DividendoPrestamoDaoImpl() {
		super(DividendoPrestamo.class);
	}

	@SuppressWarnings("unchecked")
	public List<DividendoPrestamo> listaPrestamoMoraHl(String cedula,
			List<String> estadoDividendo) {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros");
			log.debug(" cedula : " + cedula);
			log.debug(" estadoDividendo : " + estadoDividendo);
		}

		Query query = em.createNamedQuery("DividendoPrestamo.mora");
		query.setParameter("cedula", cedula);
		query.setParameter("estados", estadoDividendo);

		return query.getResultList();
	}

	public boolean tienePrestamoMoraHl(String cedula,
			List<String> estadoDividendo) {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros");
			log.debug(" cdula : " + cedula);
			log.debug(" estados : " + estadoDividendo);
		}

		List<DividendoPrestamo> resultado = this.listaPrestamoMoraHl(cedula,
				estadoDividendo);
		return !resultado.isEmpty();

	}

	@SuppressWarnings("unchecked")
	public List<DividendoPrestamo> getDividendosByIdPrestamo(Long codprecla,
			Long codpretip, Long numpreafi, Long ordpreafi) {
		if (log.isDebugEnabled()) {
			log.debug("Consulta: DividendoPrestamo.consultarPorIdPrestamo");
			log.debug("Lista de parametros");
			log.debug("codprecla: " + codprecla);
			log.debug("codpretip: " + codpretip);
			log.debug("numpreafi: " + numpreafi);
			log.debug("ordpreafi: " + ordpreafi);
		}

		Query query = em
				.createNamedQuery("DividendoPrestamo.consultarPorIdPrestamo");
		query.setParameter("codprecla", codprecla);
		query.setParameter("codpretip", codpretip);
		query.setParameter("numpreafi", numpreafi);
		query.setParameter("ordpreafi", ordpreafi);

		return query.getResultList();
	}

	public BigDecimal contarDividendosenEPL(PrestamoPk prestamoPk,
			List<String> estadoDividendo, List<String> estadoComprobante) {
		String sql = "select count(*) from kscretdividendos div "
				+ "where div.numpreafi = :numpreafi and div.codprecla = :codprecla "
				+ "and div.codpretip = :codpretip and div.ordpreafi = :ordpreafi "
				+ "and div.codestdiv IN (:codestdiv) "
				+ "and (numpreafi, codprecla,codpretip,ordpreafi,NUMDIV) in("
				+ "select d.numpreafi,d.codprecla,d.codpretip,d.ordpreafi,d.NUMDIV "
				+ "from kscretplapredet d,ksrectplanillas p "
				+ "where d.rucemp=p.rucemp " + "and d.codsuc=p.codsuc "
				+ "and d.tipper=p.tipper " + "and d.mesper=p.mesper "
				+ "and d.aniper=p.aniper " + "and d.secpla=p.secpla "
				+ "and d.codtippla=p.codtippla "
				+ "and div.numpreafi=d.numpreafi "
				+ "and div.codprecla=d.codprecla "
				+ "and p.esttippla in(:estadoComprobante) "
				+ "and p.codtippla = 'P' "
				+ "and trunc(p.fecpagpla)<=trunc(sysdate))";

		Query query = em.createNativeQuery(sql);
		query.setParameter("numpreafi", prestamoPk.getNumpreafi());
		query.setParameter("codprecla", prestamoPk.getCodprecla());
		query.setParameter("codpretip", prestamoPk.getCodpretip());
		query.setParameter("ordpreafi", prestamoPk.getOrdpreafi());
		query.setParameter("codestdiv", estadoDividendo);
		query.setParameter("estadoComprobante", estadoComprobante);

		return (BigDecimal) query.getSingleResult();

	}

	public Long contarDividendosPorPrestamoYEstado(PrestamoPk prestamoPk,
			List<String> estadoDividendo) {
		String sql = "select count(*) from DividendoPrestamo d "
				+ "where d.prestamo.prestamoPk = :prestamoPk and d.estadoDividendoPrestamo.codestdiv IN (:codestdiv)";

		Query query = em.createQuery(sql);
		query.setParameter("prestamoPk", prestamoPk);
		query.setParameter("codestdiv", estadoDividendo);

		return (Long) query.getSingleResult();
	}

	public BigDecimal contarDividendosPorPrestamoEnMora(PrestamoPk prestamoPk,
			List<String> estadoDividendo) {
		String sql = "select count(*) from kscretdividendos div "
				+ "where div.numpreafi = :numpreafi and div.codprecla = :codprecla "
				+ "and div.codpretip = :codpretip and div.ordpreafi = :ordpreafi "
				+ "and div.codestdiv in (:estadoDividendo) "
				+ "or (div.numpreafi = :numpreafi and div.codprecla = :codprecla "
				+ "and div.codpretip = :codpretip and div.ordpreafi = :ordpreafi "
				+ "and to_number(to_char(aniper||lpad(mesper,2,'0')))<to_number(to_char(sysdate,'yyyymm')) and codestdiv='REG')";

		Query query = em.createNativeQuery(sql);
		query.setParameter("numpreafi", prestamoPk.getNumpreafi());
		query.setParameter("codprecla", prestamoPk.getCodprecla());
		query.setParameter("codpretip", prestamoPk.getCodpretip());
		query.setParameter("ordpreafi", prestamoPk.getOrdpreafi());
		query.setParameter("estadoDividendo", estadoDividendo);

		return (BigDecimal) query.getSingleResult();
	}

	public BigDecimal comprobarAportesFecha(String cedula,
			List<String> tipoAporte, String cumpleImposiciones) {
		String sql = "";
		String sqlSelect = "";
		String sqlWhere = "";
		sqlSelect = "SELECT count(1) " + "FROM APORTES_PFR2 A ";

		sqlWhere = "WHERE A.CEDULA = :cedula "
				+ "AND A.TIPOREGISTRO NOT IN (:tipoAporte) "
				+ "AND A.ESTADOBLOQUEO = 'N' "
				+ "AND A.MARCADISPONIBLE = 'NP' "
				+ "AND A.FECHAINICIORENDIMIENTOS IS NULL";

		if (cumpleImposiciones.equals("0")) {
			sqlWhere = sqlWhere + " AND A.CUMPLEIMPOSICIONES = 'S' ";
		}
		sql = sqlSelect + sqlWhere;

		Query query = em.createNativeQuery(sql);
		query.setParameter("cedula", cedula);
		query.setParameter("tipoAporte", tipoAporte);

		return (BigDecimal) query.getSingleResult();
	}

	public BigDecimal comprobarSolicitudFondos(String cedula,
			List<String> tipoSolicitud, List<String> estadoSolicitud) {
		String sql = "SELECT COUNT(*) FROM FRSAFITSOLAFI "
				+ "WHERE CODTIPSOLAFI NOT IN (:tipoSolicitud) "
				+ "AND CODESTSOLAFI IN (:estadoSolicitud) "
				+ "AND NUMAFI = :cedula ";

		Query query = em.createNativeQuery(sql);
		query.setParameter("cedula", cedula);
		query.setParameter("tipoSolicitud", tipoSolicitud);
		query.setParameter("estadoSolicitud", estadoSolicitud);

		return (BigDecimal) query.getSingleResult();
	}

	/**
	 * Carlos Bastidas: INC-6047 se agrega validación de solicitud en trámite de
	 * fondos de reserva"
	 */
	public BigDecimal comprobarSolicitudFondosTramite(String cedula,
			List<String> estadoSolicitud) {
		String sql = "SELECT COUNT(*) FROM FRSAFITTRAMITE "
				+ "WHERE CODESTTRAAFI NOT IN (:estadoSolicitud) "
				+ "AND CEDAFICAU = :cedula ";
		Query query = em.createNativeQuery(sql);
		query.setParameter("cedula", cedula);
		query.setParameter("estadoSolicitud", estadoSolicitud);
		return (BigDecimal) query.getSingleResult();
	}

	public BigDecimal comprobarCargos(String cedula,
			List<String> estadoCargoReg, List<String> estadoCargoPro) {
		String sql = "select count(*) from frsafitcargos "
				+ "where numafi = :cedula AND (ESTCAR IN (:estadoCargoReg) OR (ESTCAR IN (:estadoCargoPro) AND PORAPL > 0))";

		Query query = em.createNativeQuery(sql);
		query.setParameter("cedula", cedula);
		query.setParameter("estadoCargoReg", estadoCargoReg);
		query.setParameter("estadoCargoPro", estadoCargoPro);

		return (BigDecimal) query.getSingleResult();
	}
	
	public BigDecimal comprobarBloqueos(String cedula,
			List<String> estadoBloqueado) {
		String sql = "select count(*) from ksafitbloqueos "
				+ "where numafi = :cedula AND estblo IN (:estadoBloqueado) ";

		Query query = em.createNativeQuery(sql);
		query.setParameter("cedula", cedula);
		query.setParameter("estadoBloqueado", estadoBloqueado);

		return (BigDecimal) query.getSingleResult();
	}
	
	public BigDecimal comprobarComprobantePagoIndividual(String cedula,
			List<String> estadoDividendo) {
		String sql = "SELECT COUNT(*) "+
                     "FROM KSCRETCREDITOS A , KSCRETDIVIDENDOS B "+
                     "WHERE A.NUMPREAFI = B.NUMPREAFI "+
                     "AND A.CODPRECLA = B.CODPRECLA "+
                     "AND A.CODPRETIP = B.CODPRETIP "+
                     "AND A.ORDPREAFI = B.ORDPREAFI "+
                     "AND B.CODESTDIV IN (:estadoDividendo) "+
                     "AND A.NUMAFI =  :cedula ";

		Query query = em.createNativeQuery(sql);
		query.setParameter("cedula", cedula);
		query.setParameter("estadoDividendo", estadoDividendo);

		return (BigDecimal) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<BigDecimal> saldoCapitalPQ(String cedula)
	{
		String sql = "SELECT NVL(CRE.MNTPRE,0) "+
                     " - ( "+
                            "SELECT NVL(SUM(NVL(DIV.VALCAPAMO,0)),0) "+
                            "FROM KSCRETDIVIDENDOS DIV, KSCRETDIVPREEST EST "+
                            "WHERE DIV.CODESTDIV = EST.CODESTDIV "+
                            "AND EST.OBTESTCAN = 1 "+
                            "AND CRE.NUMPREAFI = DIV.NUMPREAFI "+
                            "AND CRE.CODPRECLA = DIV.CODPRECLA "+
                            "AND CRE.ORDPREAFI = DIV.ORDPREAFI "+
                            "AND CRE.CODPRETIP = DIV.CODPRETIP "+
         
                       " )  "+ 
                    "FROM KSCRETCREDITOS CRE "+
                    "WHERE CRE.NUMAFI = :cedula "+
                    "AND CRE.CODESTPRE = 'VIG' ";

        Query query = em.createNativeQuery(sql);
        query.setParameter("cedula", cedula);

        return query.getResultList();
	}
	
	public BigDecimal diasMoraPQ(PrestamoPk prestamoPk)
	{
		String sql = "SELECT TRUNC(SYSDATE) - LAST_DAY(TRUNC(FECHAPAGODIV)) "+
                     "FROM  ( "+
                     "SELECT MIN(FECPAGDIV) AS FECHAPAGODIV "+
                     "FROM KSCRETDIVIDENDOS "+
                     "WHERE NUMPREAFI = :numpreafi "+
                     "AND CODPRECLA = :codprecla "+
                     "AND CODPRETIP = :codpretip "+
                     "AND ORDPREAFI = :ordpreafi "+
                     "AND CODESTDIV = 'MOR')";

	Query query = em.createNativeQuery(sql);
	query.setParameter("numpreafi", prestamoPk.getNumpreafi());
	query.setParameter("codprecla", prestamoPk.getCodprecla());
	query.setParameter("codpretip", prestamoPk.getCodpretip());
	query.setParameter("ordpreafi", prestamoPk.getOrdpreafi());

	return (BigDecimal) query.getSingleResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DividendoPrestamo> listaCuotaPrestamos(String cedula) {

		Query query = em.createNamedQuery("DividendoPrestamo.listaCuota");
		query.setParameter("cedula", cedula);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DividendoPrestamo> obtenerDividendosPorPrestamoYEstado(
			PrestamoPk prestamoPk, List<String> estados) {
		String sql = "select d from DividendoPrestamo d where "
				+ "d.prestamo.prestamoPk = :prestamoPk and d.estadoDividendoPrestamo.codestdiv in (:estados)"
				+ "order by d.aniper,d.mesper";

		Query query = em.createQuery(sql);
		query.setParameter("prestamoPk", prestamoPk);
		query.setParameter("estados", estados);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<DividendoPrestamo> obtenerDividendosPorClaseEstado(
			PrestamoPk prestamoPk, List<String> claseEstados) {
		StringBuilder sql = new StringBuilder();
		sql
				.append("select d from DividendoPrestamo d JOIN d.estadoDividendoPrestamo e where "
						+ "d.prestamo.prestamoPk = :prestamoPk ");
		if (claseEstados.contains("cancelado")) {
			sql.append("and e.estadoCancelado='1' ");
		}
		if (claseEstados.contains("debitado")) {
			sql.append("and  e.estadoDebito='1' ");
		}
		if (claseEstados.contains("liquidado")) {
			sql.append("and e.estadoLiquidacion='1' ");
		}
		sql.append("order by d.aniper,d.mesper,d.dividendoPrestamoPk.numdiv");

		Query query = em.createQuery(sql.toString());
		query.setParameter("prestamoPk", prestamoPk);

		return query.getResultList();

	}

	public Long contarPorPK(DividendoPrestamoPk dividendoPrestamoPk) {
		String consultaContarPorPK = "SELECT COUNT(*) FROM DividendoPrestamo DP "
				+ "WHERE  DP.dividendoPrestamoPk = :dividendoPrestamoPk ";
		/*
		 * String consultaContarPorPK2 =
		 * "SELECT COUNT(*) FROM KSCRETDIVIDENDOS WHERE " +
		 * "WHERE  numdiv = :ainnumdiv " + "AND numpreafi = :ainnumpreafi " +
		 * "AND ordpreafi = :ainordpreafi " + "AND codpretip = :aincodpretip " +
		 * "AND codprecla = :aincodprecla";
		 */
		Query query = (Query) em.createQuery(consultaContarPorPK);
		query.setParameter("dividendoPrestamoPk", dividendoPrestamoPk);
		/*
		 * query.setParameter("ainnumdiv", dividendoPrestamoPk.getNumdiv());
		 * query.setParameter("ainnumpreafi",
		 * dividendoPrestamoPk.getNumpreafi());
		 * query.setParameter("ainordpreafi",
		 * dividendoPrestamoPk.getOrdpreafi());
		 * query.setParameter("aincodpretip",
		 * dividendoPrestamoPk.getCodpretip());
		 * query.setParameter("aincodprecla",
		 * dividendoPrestamoPk.getCodprecla());
		 */
		return (Long) query.getSingleResult();
	}

	public DividendoPrestamo obtenerPorPK(
			DividendoPrestamoPk dividendoPrestamoPk) {
		/*
		 * String consultaContarPorPK = "SELECT * FROM KSCRETDIVIDENDOS WHERE "
		 * + "WHERE  numdiv = :ainnumdiv " + "AND numpreafi = :ainnumpreafi " +
		 * "AND ordpreafi = :ainordpreafi " + "AND codpretip = :aincodpretip " +
		 * "AND codprecla = :aincodprecla";
		 */
		String consultaObtenerPorPK = "SELECT DP FROM DividendoPrestamo DP "
				+ "WHERE  DP.dividendoPrestamoPk = :dividendoPrestamoPk ";
		Query query = (Query) em.createQuery(consultaObtenerPorPK);
		query.setParameter("dividendoPrestamoPk", dividendoPrestamoPk);
		/*
		 * Query query=(Query) em.createQuery(consultaContarPorPK);
		 * query.setParameter("ainnumdiv", dividendoPrestamoPk.getNumdiv());
		 * query.setParameter("ainnumpreafi",
		 * dividendoPrestamoPk.getNumpreafi());
		 * query.setParameter("ainordpreafi",
		 * dividendoPrestamoPk.getOrdpreafi());
		 * query.setParameter("aincodpretip",
		 * dividendoPrestamoPk.getCodpretip());
		 * query.setParameter("aincodprecla",
		 * dividendoPrestamoPk.getCodprecla());
		 */
		return (DividendoPrestamo) query.getSingleResult();
	}

	public List<DividendoPrestamo> obtenerListaPorPKSinNumDiv(
			DividendoPrestamoPk dividendoPrestamoPk) {
		List<DividendoPrestamo> dividendoPrestamoList = new ArrayList<DividendoPrestamo>();
		String consultaObtenerPorPKSinNumDiv = "SELECT DP FROM DividendoPrestamo DP "
				+ "WHERE DP.dividendoPrestamoPk.numpreafi = :ainnumpreafi "
				+ "AND DP.dividendoPrestamoPk.ordpreafi = :ainordpreafi "
				+ "AND DP.dividendoPrestamoPk.codpretip = :aincodpretip "
				+ "AND DP.dividendoPrestamoPk.codprecla = :aincodprecla";
		Query query = (Query) em.createQuery(consultaObtenerPorPKSinNumDiv);
		query.setParameter("ainnumpreafi", dividendoPrestamoPk.getNumpreafi());
		query.setParameter("ainordpreafi", dividendoPrestamoPk.getOrdpreafi());
		query.setParameter("aincodpretip", dividendoPrestamoPk.getCodpretip());
		query.setParameter("aincodprecla", dividendoPrestamoPk.getCodprecla());
		dividendoPrestamoList = (List) query.getResultList();
		return dividendoPrestamoList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DividendoPrestamoDao#
	 * contarDividendosMoraBiessPorFecha(java.lang.String)
	 */
	@Override
	public BigDecimal contarDividendosMoraBiessPorFecha(String cedula) {
		BigDecimal resp = BigDecimal.ZERO;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(1) ");
		sql.append(" FROM kscretdividendos d, kscretcreditos k ");
		sql.append(" WHERE d.numpreafi = k.numpreafi ");
		sql.append(" AND d.ordpreafi = k.ordpreafi ");
		sql.append(" AND d.codpretip = k.codpretip ");
		sql.append(" AND d.codprecla = k.codprecla ");
		sql.append(" AND trunc(d.fecpagdiv) < trunc(sysdate,'mm') ");
		sql.append(" AND k.numafi = :cedula ");
		sql.append(" AND k.codestpre = 'VIG' ");
		sql.append(" AND d.codestdiv in ('MOR','ECO') ");
		sql.append(" AND trunc(k.FECPREAFI) < trunc(sysdate,'mm') ");
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("cedula", cedula);

		try {
			resp = (BigDecimal) query.getSingleResult();
		} catch (NoResultException e) {
			resp = BigDecimal.ZERO;
		}

		return resp;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DividendoPrestamoDao#contarDividendosPorFecha(java.lang.String, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.util.Date, java.util.Date, java.util.List)
	 */
	public BigDecimal contarDividendosPorFecha(Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla, Date fecpagdiv, Date feccandiv,
			List<String> forcandiv) {
		BigDecimal resp = BigDecimal.ZERO;
		String sql = "SELECT COUNT(1) FROM kscretdividendos WHERE numpreafi = :numpreafi AND ordpreafi = :ordpreafi AND codpretip = :codpretip AND codprecla = :codprecla AND trunc(fecpagdiv) < to_date(:fecpagdiv,'dd/mm/yyyy') and (trunc(FECCANDIV) > to_date(:feccandiv,'dd/mm/yyyy') OR FECCANDIV IS NULL) and FORCANDIV not in (:forcandiv)";
		Query query = em.createNativeQuery(sql);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		query.setParameter("numpreafi", numpreafi);
		query.setParameter("ordpreafi", ordpreafi);
		query.setParameter("codpretip", codpretip);
		query.setParameter("codprecla", codprecla);
		query.setParameter("fecpagdiv", formatter.format(fecpagdiv));
		query.setParameter("feccandiv", formatter.format(feccandiv));
		query.setParameter("forcandiv", forcandiv);
		
		try {
			resp = (BigDecimal) query.getSingleResult();
		} catch(NoResultException e) {
			resp = BigDecimal.ZERO;
		}
		
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DividendoPrestamoDao#
	 * contarDividendosMoraBiessEmergente(java.util.Date, java.lang.String)
	 */
	public BigDecimal contarDividendosMoraBiessEmergente(Date fechaPagoDividendos, String cedula) {
		BigDecimal resp = BigDecimal.ZERO;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(1) ");
		sql.append(" FROM kscretdividendos d, kscretcreditos k ");
		sql.append(" WHERE d.numpreafi = k.numpreafi ");
		sql.append(" AND d.ordpreafi = k.ordpreafi ");
		sql.append(" AND d.codpretip = k.codpretip ");
		sql.append(" AND d.codprecla = k.codprecla ");
		sql.append(" AND trunc(d.fecpagdiv) < to_date(:fecpagdiv, 'DD/MM/YYYY')  ");
		sql.append(" and d.codestdiv IN ");
		sql.append(" (SELECT e.codestdiv ");
		sql.append(" FROM kscretdivpreest e ");
		sql.append(" WHERE (e.obtestdeb = '1' OR e.obtestliq = '1') ");
		sql.append(" 	AND e.codestdiv NOT IN ('REG', 'EPL', 'ETJ', 'CJB') ");
		sql.append("  ) ");
		sql.append(" and k.numafi = :cedula ");
		sql.append(" AND k.codestpre = 'VIG' ");
		sql.append(" AND trunc(k.FECPREAFI) < TO_DATE(sysdate, 'DD/MM/YYYY') ");
		Query query = em.createNativeQuery(sql.toString());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		query.setParameter("fecpagdiv", formatter.format(fechaPagoDividendos));
		query.setParameter("cedula", cedula);

		try {
			resp = (BigDecimal) query.getSingleResult();
		} catch (NoResultException e) {
			resp = BigDecimal.ZERO;
		}

		return resp;
	}

}