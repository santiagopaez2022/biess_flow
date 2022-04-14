/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.sql.DataSource;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.excepcion.PrestamoPqCoreException;
import ec.gov.iess.creditos.modelo.dto.CuentaBancariaAnterior;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCredito;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Implementación de la cuota de préstamos hipotecario. </b>
 * 
 * @author cvillarreal,cbastidas,pjarrin
 * @version $Revision: 1.8 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/10/03 12:54:35 $]
 *          </p>
 */
@Stateless
public class PrestamoDaoImpl extends GenericEjbDao<Prestamo, PrestamoPk>
		implements PrestamoDao {

	LoggerBiess log = LoggerBiess.getLogger(PrestamoDaoImpl.class);
	
	@Resource(mappedName = "java:credito-pq-DS")
    private DataSource dataSource;

	public PrestamoDaoImpl() {
		super(Prestamo.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Prestamo> consultarPrestamosConLaMismaCuenta(
			final String rucInstitucionFinanciera, final TipoCuenta idTipoCuenta,
			final String numeroCuenta, final List<String> estadoCredito) {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros:");
			log.debug(" rucInstitucionFinanciera:" + rucInstitucionFinanciera);
			log.debug(" idTipoCuenta:" + idTipoCuenta);
			log.debug(" numeroCuenta:" + numeroCuenta);
			log.debug(" estadoCredito:" + estadoCredito);
		}

		final Query query = em
				.createNamedQuery("Prestamo.verificarCreditoCuentabancaria");
		query.setParameter("idInstitucionFinanciera", rucInstitucionFinanciera);
		query.setParameter("idTipoCuenta", idTipoCuenta);
		query.setParameter("numeroCuentaBancaria", numeroCuenta);
		query.setParameter("estadoCredito", estadoCredito);

		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> listaPrestamoCuotaDividendo(final String cedula) {

		final Query query = em
				.createNamedQuery("Prestamo.listaPrestamoCuotaDividendo");
		query.setParameter("cedula", cedula);

		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean existePrestamoConLaMismaCuenta(
			final String rucInstitucionFinanciera, final TipoCuenta idTipoCuenta,
			final String numeroCuenta, final List<String> estadoCredito) {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros:");
			log.debug(" rucInstitucionFinanciera:" + rucInstitucionFinanciera);
			log.debug(" idTipoCuenta:" + idTipoCuenta);
			log.debug(" numeroCuenta:" + numeroCuenta);
		}

		final List<Prestamo> resultado = this.consultarPrestamosConLaMismaCuenta(
				rucInstitucionFinanciera, idTipoCuenta, numeroCuenta,
				estadoCredito);
		log.debug("resultado existePrestamoConLaMismaCuenta: "
				+ (resultado != null ? resultado.size() : null));
		return !resultado.isEmpty();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean existePrestamoConLaMismaCuentaYCedula(
			final String rucInstitucionFinanciera, final TipoCuenta idTipoCuenta,
			final String numeroCuenta, final List<String> estadoCredito, final String cedula) {
		boolean resultadoValidacion = false;
		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros:");
			log.debug(" rucInstitucionFinanciera:" + rucInstitucionFinanciera);
			log.debug(" idTipoCuenta:" + idTipoCuenta);
			log.debug(" numeroCuenta:" + numeroCuenta);
			log.debug(" cedula:" + cedula);
		}

		final List<Prestamo> resultado = this.consultarPrestamosConLaMismaCuenta(
				rucInstitucionFinanciera, idTipoCuenta, numeroCuenta,
				estadoCredito);
		log.debug("resultado existePrestamoConLaMismaCuenta: "
				+ (resultado != null ? resultado.size() : null));
		for (final Prestamo p : resultado) {
			if (!p.getNumafi().equals(cedula)) {
				log.info("Se encontro un prestamo en la cuenta de un tercero con CI: "
						+ p.getNumafi());
				resultadoValidacion = true;
			} else {
				log.info("Se encontro un prestamo en la cuenta con la misma cedula"
						+ p.getNumafi() + "ced:" + cedula);
			}
		}
		return resultadoValidacion;
	}

	@Override
	public boolean tienePrestamoMoraHl(final String cedula) {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros:");
			log.debug(" cedula:" + cedula);
		}

		final List<DividendoPrestamo> resultado = this.listaPrestamoMoraHl(cedula);
		return !resultado.isEmpty();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DividendoPrestamo> listaPrestamoMoraHl(final String cedula) {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros:");
			log.debug(" cedula:" + cedula);
		}

		final Query query = em.createNamedQuery("Prestamo.mora");
		query.setParameter("cedula", cedula);
		return query.getResultList();

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> listaPrestamoVigentesHl(final String cedula,
			final List<String> estadoCredito) {

		final Query query = em.createNamedQuery("Prestamo.vigente");
		query.setParameter("cedula", cedula);
		query.setParameter("estadoCredito", estadoCredito);
		return query.getResultList();
	}

	@Override
	public boolean tienePrestamoVigentesHl(final String cedula,
			final List<String> estadoCredito) {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros:");
			log.debug(" cedula:" + cedula);
			log.debug(" estadoCredito:" + estadoCredito);
		}

		final List<Prestamo> resultado = this.listaPrestamoVigentesHl(cedula,
				estadoCredito);
		return !resultado.isEmpty();
	}

	@Override
	public boolean tienePrestamoVigentesHlPH(final String cedula,
			final List<String> estadoSolicitud) {

		final Query query = em
				.createNativeQuery("SELECT distinct p.numafi as NUMAFI "
						+ "FROM kscretsolicitudes o, "
						+ "cre_solicitante_tbl p "
						+ "where o.codtipsolser >= 35 "
						+ "and p.NUMSOLSER=o.NUMSOLSER "
						+ "and p.CODTIPSOLSER=o.CODTIPSOLSER "
						+ "and p.numafi=:cedula "
						+ "and o.CODESTSOLSER IN (:estadoSolicitud)");

		query.setParameter("cedula", cedula);
		query.setParameter("estadoSolicitud", estadoSolicitud);
		return !query.getResultList().isEmpty();
	}

	/**
	 * 
	 * <b> Retorna la cuota de préstamo hipotecario. </b>
	 * <p>
	 * [Author: cbastidas, Date: 11/12/2010]
	 * </p>
	 * 
	 * @param cedula
	 * @return
	 */
	@Override
	public BigDecimal cuotaPrestamoHipotecario(final String cedula) {

		final Query query = em
				.createNativeQuery("SELECT NVL(SUM(NVL(M.MONTO_ORG,0)),0) "
						+ "FROM   T20OPE01 P, T20TAM14 M "
						+ "WHERE  P.NRO_OPERACION = M.NRO_OPERACION "
						+ "AND    M.FEC_VCTO = TRUNC(LAST_DAY(ADD_MONTHS(SYSDATE, 1))) "
						+ "AND    M.NRO_DIVIDENDO >= 1 "
						+ "AND    TRIM(P.COD_PROD_SIS_ORG) <> '*DES' "
						+ "AND    P.ESTADO IN ('PV', 'ND') "
						+ "AND    P.IDENTIFICACION = :cedula  ");
		query.setParameter("cedula", cedula);
		return (BigDecimal) query.getSingleResult();
	}

	/**
	 * 
	 * <b> Retorna total de créditos hipotecarios que registre el asegurado
	 * vigentes. </b>
	 * <p>
	 * [Author: cbastidas, Date: 11/12/2010]
	 * </p>
	 * 
	 * @param cedula
	 * @return
	 */
	@Override
	public BigDecimal numeroPrestamoPHVIG(final String cedula) {

		final Query query = em.createNativeQuery("SELECT COUNT(DISTINCT(S.NUT))"
				+ " FROM   KSCRETSOLICITUDES S, CRE_SOLICITANTE_TBL SOLI"
				+ " WHERE  S.CODTIPSOLSER = SOLI.CODTIPSOLSER"
				+ " AND    S.NUMSOLSER    = SOLI.NUMSOLSER"
				+ " AND    S.CODTIPSOLSER > 34"
				+ " AND    S.CODESTSOLSER = 'VIG' "
				+ " AND    SOLI.NUMAFI    = :cedula  ");
		query.setParameter("cedula", cedula);
		return (BigDecimal) query.getSingleResult();
	}

	/**
	 * 
	 * <b> Retorna el valor(Monto financiado)de los créditos hipotecarios que
	 * registre el asegurado.. </b>
	 * <p>
	 * [Author: cbastidas, Date: 11/12/2010]
	 * </p>
	 * 
	 * @param cedula
	 * @return
	 */
	@Override
	public BigDecimal sumatoriaMntPHVIG(final String cedula) {

		final Query query = em
				.createNativeQuery("SELECT NVL(SUM(NVL(NT.SALDO_CAP_INICIAL,0)),0)"
						+ " FROM   T20NUT20 NT "
						+ " WHERE  TRIM(NT.COD_PROD_SIS_ORG) <> '*DES' "
						+ " AND    TRIM(NT.ESTADO) IN ('PV', 'ND')  "
						+ " AND    TRIM(NT.IDENTIFICACION) = :cedula  ");
		query.setParameter("cedula", cedula);
		return (BigDecimal) query.getSingleResult();
	}

	/**
	 * 
	 * <b> Retorna la cuota de Hipotecario cuando esta en trámite de solicitud.
	 * </b>
	 * <p>
	 * [Author: cbastidas, Date: 14/12/2010]
	 * </p>
	 * 
	 * @param cedula
	 * @return
	 */
	@Override
	public BigDecimal cuotaPrestamoSolicitudHipotecario(final String cedula) {
		final Query query = em
				.createNativeQuery("SELECT NVL(SUM(NVL(D.CUOPARORI,0)),0) "
						+ " FROM CRE_SOLICITANTE_TBL D ,KSCRETSOLICITUDES S "
						+ " WHERE D.CODTIPSOLSER = S.CODTIPSOLSER "
						+ " AND D.NUMSOLSER = S.NUMSOLSER "
						+ " AND S.CODTIPSOLSER >= 35 "
						+ " AND S.CODESTSOLSER IN ('SOL', 'APR') "
						+ " AND S.NUMAFI = :cedula ");
		query.setParameter("cedula", cedula);
		return (BigDecimal) query.getSingleResult();
	}

	@Override
	public BigDecimal montoMaximoCredito(final int tipoPrestamo, final int clasePrestamo) {

		try {
			final Query query = em.createNativeQuery("SELECT VALNUMCONFIN "
					+ " FROM KSCRETPREVARCONFIN "
					+ " WHERE CODUNIMEDCONFIN='DOL' " + " AND CODCONFIN = 23 "
					+ " AND CODPRETIP = :tipoPrestamo "
					+ " AND CODPRECLA = :clasePrestamo ");

			query.setParameter("tipoPrestamo", tipoPrestamo);
			query.setParameter("clasePrestamo", clasePrestamo);

			return (BigDecimal) query.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}
	}

	@Override
	public void refrescar(final Prestamo prestamo) {

		em.refresh(prestamo);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> getPrestamosPorCedula(final String cedula) {
		final Query query = em.createNamedQuery("Prestamo.consultarPorCedula");

		query.setParameter("cedula", cedula);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> consultarQuirografariosVigentes(final String cedula,
			final List<String> estadoCredito, final List<Long> tipoCredito) {
		final Query query = em
				.createNamedQuery("Prestamo.consultarQuirografariosVigentes");

		query.setParameter("cedula", cedula);
		query.setParameter("estadoCredito", estadoCredito);
		query.setParameter("tipoCredito", tipoCredito);

		return query.getResultList();

	}

	@Override
	public BigDecimal getTotalCreditoVigente(final String cedula,
			final List<String> estadoPrestamo) {

		final Query q = em
				.createNativeQuery("select sum(div.valcapamo)+sum(div.intsalcap)+sum(div.intpergra) as total "
						+ "from kscretcreditos cre, kscretdividendos div "
						+ "where cre.numafi = :cedula  "
						+ "and cre.codestpre IN (select codestpre from kscretpreest where obtestpre IN (:estadoPrestamo)) "
						+ "and cre.numpreafi = div.numpreafi "
						+ "and cre.codprecla = div.codprecla "
						+ "and cre.ordpreafi = div.ordpreafi "
						+ "and cre.codpretip = div.codpretip");

		q.setParameter("cedula", cedula);
		q.setParameter("estadoPrestamo", estadoPrestamo);

		return (BigDecimal) q.getSingleResult();
	}

	@Override
	public BigDecimal getTotalSaldoCreditoVigente(final String cedula,
			final List<String> estadoPrestamo, final List<String> estadoDividendo) {
		final Query q = em
				.createNativeQuery("select sum(div.valcapamo)+sum(div.intsalcap)+sum(div.intpergra) as total "
						+ "from kscretcreditos cre, kscretdividendos div "
						+ "where cre.numafi = :cedula  "
						+ "and cre.codestpre IN (select codestpre from kscretpreest where obtestpre IN (:estadoPrestamo)) "
						+ "and cre.numpreafi = div.numpreafi "
						+ "and cre.codprecla = div.codprecla "
						+ "and cre.ordpreafi = div.ordpreafi "
						+ "and cre.codpretip = div.codpretip "
						+ "and div.codestdiv not in ( select est.codestdiv "
						+ "from kscretdivpreest est where obtestcan IN (:estadoDividendo))");

		q.setParameter("cedula", cedula);
		q.setParameter("estadoPrestamo", estadoPrestamo);
		q.setParameter("estadoDividendo", estadoDividendo);

		return (BigDecimal) q.getSingleResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> consultarQuirografariosVigentesPH(final String cedula,
			final List<String> estadoCredito, final List<Long> tipoCredito) {
		final Query query = em
				.createNamedQuery("Prestamo.consultarQuirografariosVigentesPH");
		query.setParameter("cedula", cedula);
		query.setParameter("estadoCredito", estadoCredito);
		query.setParameter("tipoCredito", tipoCredito);
		return query.getResultList();
	}

	@Override
	public boolean tienePrestamoQuirografarioVigentesPH(final String cedula,
			final List<String> estadoCredito, final List<Long> tipoCredito) {
		final List<Prestamo> resultado = this.consultarQuirografariosVigentesPH(
				cedula, estadoCredito, tipoCredito);
		return !resultado.isEmpty();
	}

	@Override
	public BigDecimal consultarTienePqPorEstado(final String cedula,
			final List<String> estadoCredito) {
		final String sql = "SELECT COUNT(*) " + "FROM KSCRETCREDITOS A "
				+ "WHERE A.NUMAFI = :cedula "
				+ "AND A.CODESTPRE IN (:estadoCredito) ";

		final Query query = em.createNativeQuery(sql);
		query.setParameter("cedula", cedula);
		query.setParameter("estadoCredito", estadoCredito);
		return (BigDecimal) query.getSingleResult();

	}

	@Override
	public Prestamo buscarPorPk(final PrestamoPk prestamoPk) {
		final String buscarPorPK = "SELECT p FROM Prestamo p "
				+ " WHERE p.prestamoPk = :prestamoPk";
		final Query query = em.createQuery(buscarPorPK);
		query.setParameter("prestamoPk", prestamoPk);
		return (Prestamo) query.getSingleResult();
	}

	@Override
	public boolean actualizarFecCancelacionYEstado(final Prestamo prestamo) {
		final String sql = "update Prestamo p set p.estadoPrestamo.codestpre=:estadoPrestamo,p.feccanpre=:fechaCancelacion"
				+ " WHERE p.prestamoPk = :pk";
		em
				.createQuery(sql)
				.setParameter("estadoPrestamo",
						prestamo.getEstadoPrestamo().getCodestpre())
				.setParameter("fechaCancelacion", prestamo.getFeccanpre())
				.setParameter("pk", prestamo.getPrestamoPk()).executeUpdate();
		return true;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public DetalleCredito consultarDetalleCredito(final String cedula,
			final Long numeroPrestamo, final Long codPreTip, final Long ordPreAfi, final Long codPreCla) {
		DetalleCredito detalleCredito = null;
		try {
			final Query query = em.createNamedQuery("consultaPorDetalleCredito");
			query.setParameter("cedula", cedula);
			query.setParameter("numeroPrestamo", numeroPrestamo);
			query.setParameter("codPreTip", codPreTip);
			query.setParameter("ordPreAfi", ordPreAfi);
			query.setParameter("codPreCla", codPreCla);
			detalleCredito = (DetalleCredito) query.getSingleResult();
		} catch (final NoResultException e) {
			detalleCredito = null;
		}
		return detalleCredito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.PrestamoDao#consultarprestamospendientesaprobacion
	 * (java.util.Date, java.util.Date)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> consultarprestamospendientesaprobacion(
			final Date fecha_ant, final Date fecha_post) {
		final Query query = em
				.createNamedQuery("Prestamo.listaPrestamospendientesaprobacion");
		query.setParameter("fecha_ant", fecha_ant);
		query.setParameter("fecha_post", fecha_post);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> consultarPrestamoPDAPorCedula(final String cedula) {
		final Query query = em.createNamedQuery("Prestamo.listaPrestamosPDACedula");
		query.setParameter("cedula", cedula);
		return query.getResultList();
	}

	@Override
	public boolean existecuentaenlistablanca(final String numafi,
			final String rucfinanciera, final String tipocta, final String numerocta) {

		final Query query = em
				.createNativeQuery("select * from PQ_OWNER.cre_cuentasbancarias_tbl where "
						+ "CB_CEDULA =:cedafi and CB_RUC_INST_FINANCIERA =:rucinst and "
						+ "CB_TIPO_CUENTA =:tipocta and CB_NUMERO_CUENTA =:numcta and "
						+ "CB_ESTADO ='VALIDO'");
		query.setParameter("cedafi", numafi);
		query.setParameter("rucinst", rucfinanciera);
		query.setParameter("tipocta", tipocta);
		query.setParameter("numcta", numerocta);
		return !query.getResultList().isEmpty();

		// return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#consultaGenerica(String
	 * queryGenericoPQ)
	 */
	@Override
	public List<Prestamo> consultaGenerica(final String queryGenericoPQ) {

		final Query query = em.createNativeQuery(queryGenericoPQ, Prestamo.class);

		@SuppressWarnings("unchecked")
		final
		List<Prestamo> listaPrestamo = query.getResultList();

		return listaPrestamo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.PrestamoDao#poseeCreditosERC(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> poseeCreditosERC(final String cedula) {
		final StringBuilder sql = new StringBuilder();
		sql.append("select o from Prestamo o where (o.estadoPrestamo.codestpre = :estado or (o.estadoPrestamo.codestpre = :estadoVig and o.validacionRegistroCivil = :registroCivil)) ");
		sql.append(" and o.numafi = :cedula ");
		final Query query = em.createQuery(sql.toString());
		query.setParameter("estado", "ERC");
		query.setParameter("cedula", cedula);
		query.setParameter("estadoVig", "VIG");
		query.setParameter("registroCivil", "S");
		final List<Prestamo> result = query.getResultList();
		return result == null || result.isEmpty() ? null : result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.PrestamoDao#obtenerCreditoERCPorParametros(java
	 * .util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> obtenerCreditoPorParametros(final Date fechaDesde,
			final Date fechaHasta, final String cedula, final String estado, final String estadoVig,
			final String registroCivil) {
		final StringBuilder sql = new StringBuilder();
		sql.append("select o from Prestamo o ");
		sql.append("where (o.estadoPrestamo.codestpre = :estado or (o.estadoPrestamo.codestpre = :estadoVig and o.validacionRegistroCivil = :registroCivil)) ");

		if (cedula != null && !"".equals(cedula)) {
			sql.append("and o.numafi = :cedula ");
		} else {
			if (fechaDesde != null) {
				sql.append("and trunc(o.fecpreafi) >= :fechaDesde ");
			}
			if (fechaHasta != null) {
				sql.append("and trunc(o.fecpreafi) <= :fechaHasta ");
			}
		}
		sql.append("order by o.afiliado.apenomafi asc");
		final Query query = em.createQuery(sql.toString());
		query.setParameter("estado", estado);
		query.setParameter("estadoVig", estadoVig);
		query.setParameter("registroCivil", registroCivil);
		
		if (cedula != null && !"".equals(cedula)) {
			query.setParameter("cedula", cedula);
		} else {
		if (fechaDesde != null) {
			query.setParameter("fechaDesde", fechaDesde);
		}
		if (fechaHasta != null) {
			query.setParameter("fechaHasta", fechaHasta);
		}
		}
		final List<Prestamo> result = query.getResultList();
		return result == null || result.isEmpty() ? null : result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.PrestamoDao#sumaSaldosPrestamosVigentes(java
	 * .lang.String)
	 */
	@Override
	public BigDecimal sumaSaldosPrestamosVigentes(final String cedula) {
		try {
			final Query query = this.em
					.createNativeQuery("select sum(valsalcap) from kscretcreditos where numafi = :cedula and CODESTPRE = 'VIG'");
			query.setParameter("cedula", cedula);
			return (BigDecimal) query.getSingleResult();
		} catch (final Exception e) {
			return new BigDecimal(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.PrestamoDao#sumaSaldosPrestamosVigentes(java
	 * .lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public CuentaBancariaAnterior cuentaBancariaAnterior(final String cedula) {
		CuentaBancariaAnterior cba = null;
		List<Object[]> list = null;
		final Query query = this.em
				.createNativeQuery("select  DECODE(C.CODTIPCUE,'COR', 'CUENTA CORRIENTE','AHO', 'CUENTA DE AHORROS'  ) TIPO_CUENTA,"
						+ " C.NUMCUEBAN,"
						+ "(select nomemp from kspcotempleadores where rucemp = c.rucempinsfin )NOMEMP, B.fecreg "
						+ "from KSAFITDATBANAFI B, KSAFITDATBAN C "
						+ "where c.CODDATBAN = b.CODDATBAN "
						+ "AND b.numafi= :cedula "
						+ "AND b.CODFUN IS NOT NULL "
						+ "AND B.coddatbanest = 'ANU' and b.Fecaut is not null order by B.Fecaut");
		query.setParameter("cedula", cedula);
		list = query.getResultList();
		if (list != null) {
			for (final Object[] object : list) {
				cba = new CuentaBancariaAnterior();
				cba.setTipoCuentaAnterior(((String) object[0]));
				cba.setNumeroCuentaAnterior(((String) object[1]));
				cba.setEntidadFinancieraAnterior(((String) object[2]));
				cba.setFechaRegistroAnterior(((Date) object[3]));
				break;
			}
		}
		return cba;
	}
	
	/**
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#actualizarFechaCancelacionYEstado
	 *      (ec.gov.iess.creditos.modelo.persistencia.Prestamo,
	 *      java.lang.String, java.util.Date)
	 */
	@Override
	public boolean actualizarFechaCancelacionYEstado(final Prestamo prestamo,
			final String codigoEstadoPrestamo, final Date fechaCancelacion)
			throws Exception {

		prestamo.getEstadoPrestamo().setCodestpre(codigoEstadoPrestamo);
		prestamo.setFeccanpre(fechaCancelacion);

		this.update(prestamo);

		return true;
	}

	/** 
	 * @see
	 * ec.gov.iess.creditos.dao.PrestamoDao#consultarPorEstadoYPeriodo(java.
	 * util.List, java.util.Date, java.util.Date)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> consultarPorEstadoYPeriodo(
			final List<String> estadosPrestamo, final Date fechaAnt, final Date fechaPost) {
		// INC-2272 Pensiones Alimenticias
		final Query query = this.em
				.createQuery("select o from Prestamo o where  o.estadoPrestamo.codestpre in (:estadoPrestamo) "
						+ " and trunc(o.fecpreafi) >= :fecha_ant and trunc(o.fecpreafi) <= :fecha_post");
		query.setParameter("estadoPrestamo", estadosPrestamo);
		query.setParameter("fecha_ant", fechaAnt);
		query.setParameter("fecha_post", fechaPost);

		return query.getResultList();
	}

	/** 
	 * @see
	 * ec.gov.iess.creditos.dao.PrestamoDao#consultarPorEstadoPeriodoYCedula
	 * (java.util.List, java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> consultarPorEstadoPeriodoYCedula(
			final List<String> estadosPrestamo, final Date fechaAnt, final Date fechaPost,
			final String cedulaAfiliado) {
		// INC-2272 Pensiones Alimenticias
		final Query query = this.em
				.createQuery("select o from Prestamo o where  o.estadoPrestamo.codestpre in (:estadoPrestamo) "
						+ " and trunc(o.fecpreafi) >= :fecha_ant and trunc(o.fecpreafi) <= :fecha_post  "
						+ " and o.numafi = :cedulaAfiliado");

		query.setParameter("estadoPrestamo", estadosPrestamo);
		query.setParameter("fecha_ant", fechaAnt);
		query.setParameter("fecha_post", fechaPost);
		query.setParameter("cedulaAfiliado", cedulaAfiliado);

		return query.getResultList();
	}

	/**
	 * @see
	 * ec.gov.iess.creditos.dao.PrestamoDao#consultarPorEstadoPeriodoYTipoProducto
	 * (java.util.List, java.util.Date, java.util.Date, java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> consultarPorEstadoPeriodoYTipoProducto(
			final List<String> estadosPrestamo, final Date fechaAnt, final Date fechaPost,
			final Long tipoProducto) {
		// INC-2272 Pensiones Alimenticias
		final Query query = this.em
				.createQuery("select o from Prestamo o where  o.estadoPrestamo.codestpre in (:estadoPrestamo) "
						+ " and trunc(o.fecpreafi) >= :fecha_ant and trunc(o.fecpreafi) <= :fecha_post  "
						+ " and o.prestamoPk.codpretip = :tipoProducto");

		query.setParameter("estadoPrestamo", estadosPrestamo);
		query.setParameter("fecha_ant", fechaAnt);
		query.setParameter("fecha_post", fechaPost);
		query.setParameter("tipoProducto", tipoProducto);

		return query.getResultList();
	}

	/**
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#
	 *      consultarPorEstadoPeriodoCedulaYTipoProducto(java.util.List,
	 *      java.util.Date, java.util.Date, java.lang.String, java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> consultarPorEstadoPeriodoCedulaYTipoProducto(
			final List<String> estadosPrestamo, final Date fechaAnt, final Date fechaPost,
			final String cedulaAfiliado, final Long tipoProducto) {
		// INC-2272 Pensiones Alimenticias
		final Query query = this.em
				.createQuery("select o from Prestamo o where  o.estadoPrestamo.codestpre in (:estadoPrestamo) "
						+ " and trunc(o.fecpreafi) >= :fecha_ant and trunc(o.fecpreafi) <= :fecha_post  "
						+ " and o.numafi = :cedulaAfiliado"
						+ " and o.prestamoPk.codpretip = :tipoProducto");

		query.setParameter("estadoPrestamo", estadosPrestamo);
		query.setParameter("fecha_ant", fechaAnt);
		query.setParameter("fecha_post", fechaPost);
		query.setParameter("cedulaAfiliado", cedulaAfiliado);
		query.setParameter("tipoProducto", tipoProducto);

		return query.getResultList();
	}

	/**
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#consultarPrestamos(java.util.List, java.util.Date, java.util.Date,
	 *      java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> consultarPrestamos(final List<String> estadosPrestamo, final Date fechaAnt, final Date fechaPost,
			final String cedulaAfiliado, final Long tipoProducto, final String visaAfiliado) {
		// INC-2272 Pensiones Alimenticias
		final StringBuilder queryString = new StringBuilder(
				"select o from Prestamo o where  o.estadoPrestamo.codestpre in (:estadoPrestamo)");
		queryString.append(" and trunc(o.fecpreafi) >= :fecha_ant and trunc(o.fecpreafi) <= :fecha_post");

		if (cedulaAfiliado != null && cedulaAfiliado.trim().length() > 0) {
			queryString.append(" and o.numafi = :cedulaAfiliado");
		}

		if (tipoProducto != null && tipoProducto.doubleValue() > 0) {
			queryString.append(" and o.prestamoPk.codpretip = :tipoProducto");
		}

		if (visaAfiliado != null && visaAfiliado.trim().length() > 0) {
			queryString.append(" and o.numeroVisaPasaporteAfiliado = :visaAfiliado");
		}

		final Query query = this.em.createQuery(queryString.toString());
		query.setParameter("estadoPrestamo", estadosPrestamo);
		query.setParameter("fecha_ant", fechaAnt);
		query.setParameter("fecha_post", fechaPost);

		if (cedulaAfiliado != null && cedulaAfiliado.trim().length() > 0) {
			query.setParameter("cedulaAfiliado", cedulaAfiliado);
		}

		if (tipoProducto != null && tipoProducto.doubleValue() > 0) {
			query.setParameter("tipoProducto", tipoProducto);
		}

		if (visaAfiliado != null && visaAfiliado.trim().length() > 0) {
			query.setParameter("visaAfiliado", visaAfiliado);
		}

		return query.getResultList();
	}
	
	/**
	 * @param cedulasPrestamo
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> consultarPrestamosPorEstadosCedulas(final List<String> estadosPrestamo, final Date fechaAnt, final Date fechaPost,
			final Long idTipoProducto, final List<String> cedulasPrestamo) {
		
		final StringBuilder queryString = new StringBuilder(
				"select o from Prestamo o where o.numafi in (:cedulasAfiliados)");
		queryString.append(" and o.estadoPrestamo.codestpre in (:estadoPrestamo)");
		
		if (fechaAnt != null && fechaPost != null) {
			queryString.append(" and trunc(o.fecpreafi) >= :fecha_ant and trunc(o.fecpreafi) <= :fecha_post");
		}
		
		if (idTipoProducto != null && idTipoProducto.doubleValue() > 0) {
			queryString.append(" and o.prestamoPk.codpretip = :tipoProducto");
		}

		final Query query = this.em.createQuery(queryString.toString());
		query.setParameter("cedulasAfiliados", cedulasPrestamo);
		query.setParameter("estadoPrestamo", estadosPrestamo);
		
		if (fechaAnt != null && fechaPost != null) {
			query.setParameter("fecha_ant", fechaAnt);
			query.setParameter("fecha_post", fechaPost);
		}

		if (idTipoProducto != null && idTipoProducto.doubleValue() > 0) {
			query.setParameter("tipoProducto", idTipoProducto);
		}

		return query.getResultList();	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#contarPorEstadoAnio(java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public Long contarPorEstadoAnio(final String numafi, final String estadoPrestamo, final Long aniper) {
		final Query query = em.createNamedQuery("Prestamo.contarPorEstadoAnio");
		query.setParameter("numafi", numafi);
		query.setParameter("estado", estadoPrestamo);
		query.setParameter("anio", aniper);

		final Long resp = (Long) query.getSingleResult();

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#listaPorEstadoFechaPrecalifica(java.lang.String, java.lang.String,
	 * java.util.Date)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> listaPorEstadoFechaPrecalifica(final String numafi, final String codestpre, final Date fecpreafi) {
		final Query query = em.createNamedQuery("Prestamo.listaPorEstadoFechaPrecalifica");
		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		query.setParameter("numafi", numafi);
		query.setParameter("codestpre", codestpre);
		query.setParameter("fecpreafi", formatter.format(fecpreafi));
		List<Prestamo> listaResp = null;
		try {
			listaResp = query.getResultList();
		} catch (final NoResultException e) {
			listaResp = null;
		}

		return listaResp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#contarPorFechaCancelacion(java.util.Date, java.util.Date,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public BigDecimal contarPorFechaCancelacion(final Date fecrecpla, final Date feccanpla, final String codtippla, final String rucemp, final String codsuc) {
		BigDecimal resp = BigDecimal.ZERO;

		final String sql = "SELECT count(1) FROM ksrectplanillas p WHERE TRUNC(p.feccrepla) <= to_date(:feccrepla,'dd/mm/yyyy') AND ( TRUNC (p.FECCANPLA) > to_date(:feccanpla,'dd/mm/yyyy') or p.FECCANPLA is null and ESTTIPPLA <> 'ANU') and p.CODTIPPLA = :codtippla And p.RUCEMP= :rucemp and p.codsuc = :codsuc";
		final Query query = em.createNativeQuery(sql);
		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		query.setParameter("feccrepla", formatter.format(fecrecpla));
		query.setParameter("feccanpla", formatter.format(feccanpla));
		query.setParameter("codtippla", codtippla);
		query.setParameter("rucemp", rucemp);
		query.setParameter("codsuc", codsuc);

		try {
			resp = (BigDecimal) query.getSingleResult();
		} catch (final NoResultException e) {
			resp = BigDecimal.ZERO;
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#contarPlanillasMoraInvididualBiessEmergente(java.util.Date,
	 * java.lang.String)
	 */
	@Override
	public BigDecimal contarPlanillasMoraInvididualBiessEmergente(final Date fechaCreacionPlanilla, final String cedula) {
		BigDecimal resp = BigDecimal.ZERO;

		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(1) ");
		sql.append(" FROM ksrectplanillas p, ksafitserpre e, kscretplapredet d ");
		sql.append(" WHERE p.rucemp = e.rucemp ");
		sql.append(" AND p.codsuc = e.codsuc ");
		sql.append(" AND p.rucemp = d.rucemp ");
		sql.append(" AND p.codsuc = d.codsuc ");
		sql.append(" AND p.secpla = d.secpla ");
		sql.append(" AND p.mesper = d.mesper ");
		sql.append(" AND p.aniper = d.aniper ");
		sql.append(" AND e.numafi = d.numafi ");
		sql.append(" AND p.codtippla = d.codtippla ");
		sql.append(" AND TRUNC (p.feccrepla) <= TO_DATE (:feccrepla, 'dd/mm/yyyy') ");
		sql.append(" AND p.feccanpla IS NULL ");
		sql.append(" AND p.esttippla NOT IN  ('ANU','REG','GEV','TCO','CAN','CAC','CAT','CAG','CAA') ");
		sql.append(" AND p.codtippla = 'P' ");
		sql.append(" AND e.esthislab = '1' ");
		sql.append(" AND e.fecsalafi IS NULL ");
		sql.append(" AND e.numafi = :cedula ");
		final Query query = em.createNativeQuery(sql.toString());
		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		query.setParameter("feccrepla", formatter.format(fechaCreacionPlanilla));
		query.setParameter("cedula", cedula);

		try {
			resp = (BigDecimal) query.getSingleResult();
		} catch (final NoResultException e) {
			resp = BigDecimal.ZERO;
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#contarPlanillasMoraPatronalEmergente(java.util.Date, java.lang.String)
	 */
	@Override
	public BigDecimal contarPlanillasMoraPatronalEmergente(final Date fechaCreacionPlanilla, final String cedula) {
		BigDecimal resp = BigDecimal.ZERO;

		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(1) ");
		sql.append(" FROM ksrectplanillas p, ksafitserpre e ");
		sql.append(" WHERE p.rucemp = e.rucemp ");
		sql.append(" and p.codsuc = e.codsuc ");
		sql.append(" and TRUNC(p.feccrepla) <= to_date(:feccrepla, 'dd/mm/yyyy') ");
		sql.append(" AND p.FECCANPLA is null ");
		sql.append(" and CODTIPPLA in ('A','AA','RA','F','AF','P','PH','PHJ','PJ','PPH') ");
		sql.append(" AND p.esttippla NOT IN  ('ANU','REG','GEV','TCO','CAN','CAC','CAT','CAG','CAA') ");
		sql.append(" AND e.esthislab = '1' ");
		sql.append(" AND e.fecsalafi is null ");
		sql.append(" and e.numafi = :cedula ");
		final Query query = em.createNativeQuery(sql.toString());
		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		query.setParameter("feccrepla", formatter.format(fechaCreacionPlanilla));
		query.setParameter("cedula", cedula);

		try {
			resp = (BigDecimal) query.getSingleResult();
		} catch (final NoResultException e) {
			resp = BigDecimal.ZERO;
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#ejecutarCambioEstadoSP(java.util.Map, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void ejecutarCambioEstadoSP(final Map<String, Long> parametrosCredito, final String estadoCredito,
            final String estadoActualCredito) throws PrestamoPqCoreException {
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            final CallableStatement statement = connection
                    .prepareCall("{call kscrekpreesthis.actreghisestpretrg (?,?,?,?,?,?,SYSDATE,?,?,?)}");
            statement.setInt(1, parametrosCredito.get("numpreafi").intValue());
            statement.setInt(2, parametrosCredito.get("ordpreafi").intValue());
            statement.setInt(3, parametrosCredito.get("codpretip").intValue());
            statement.setInt(4, parametrosCredito.get("codprecla").intValue());
            statement.setString(5, estadoActualCredito);
            statement.setString(6, estadoCredito);
            statement.setString(7, "PQ anulado por el Afiliado/Jubilado");
            statement.registerOutParameter(8, Types.CHAR);
            statement.registerOutParameter(9, Types.VARCHAR);
            statement.execute();
            final String codError = statement.getString(8).trim();
            
            if ("0".equals(codError)) {
            	final String descError = statement.getString(9).trim();
                throw new PrestamoPqCoreException(descError);
            }
        } catch (final Exception e) {
            log.error("Error al kscrekpreesthis.actreghisestpretrg" + parametrosCredito.toString(), e);
            throw new PrestamoPqCoreException("Error al ejecutar proceso cambio estado", e);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (final SQLException e) {
                log.error("Error al kscrekpreesthis.actreghisestpretrg" + parametrosCredito.toString(), e);
                throw new PrestamoPqCoreException("Error al liberar recurso", e);
            }
        }
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#cambiarEstadoCredito(java.util.Map, java.lang.String)
	 */
	@Override
	public void cambiarEstadoCredito(final Map<String, Long> parametrosCredito, final String estadoCredito) throws PrestamoPqCoreException {
		try {
			final Query query = em.createNativeQuery(
					"UPDATE kscretcreditos SET codestpre = :estado WHERE numpreafi = :numpreafi  AND codprecla = :codprecla AND codpretip = :codpretip AND ordpreafi = :ordpreafi");
			query.setParameter("codprecla", parametrosCredito.get("codprecla"));
			query.setParameter("codpretip", parametrosCredito.get("codpretip"));
			query.setParameter("numpreafi", parametrosCredito.get("numpreafi"));
			query.setParameter("ordpreafi", parametrosCredito.get("ordpreafi"));
			query.setParameter("estado", estadoCredito);

			query.executeUpdate();
		} catch (final Exception e) {
			log.error("Error al cambiar el estado del credito", e);
			throw new PrestamoPqCoreException("Error al cambiar el estado del credito", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#contarPorEstadoTipoPrestamo(java.lang.String, java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public Long contarPorEstadoTipoPrestamo(final String numafi, final String estadoPrestamo, final Long codpretip) {
		final Query query = em.createNamedQuery("Prestamo.contarPorEstadoTipoPrestamo");
		query.setParameter("numafi", numafi);
		query.setParameter("estado", estadoPrestamo);
		query.setParameter("codpretip", codpretip);

		final Long resp = (Long) query.getSingleResult();

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PrestamoDao#contarPorEstadoFeccanpreAnio(java.lang.String, java.lang.String,
	 * java.lang.Long, java.util.Date)
	 */
	@Override
	public Long contarPorEstadoFeccanpreAnio(final String numafi, final String estadoPrestamo, final Long aniper, final Date feccanpre) {
		final Query query = em.createNamedQuery("Prestamo.contarPorEstadoFeccanpreAnio");
		query.setParameter("numafi", numafi);
		query.setParameter("estado", estadoPrestamo);
		query.setParameter("anio", aniper);
		
		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		query.setParameter("feccanpre", formatter.format(feccanpre));

		final Long resp = (Long) query.getSingleResult();

		return resp;
	}
	


	@Override
	public Prestamo buscarPorSolicitud(final Long codTipoSolicitud, final Long numSolicitud) {
		final Query query = em.createNamedQuery("Prestamo.consultarPorTipoSolicitud");
		query.setParameter("codSolicitud", codTipoSolicitud);
		query.setParameter("numSolicitud", numSolicitud);
		return (Prestamo) query.getSingleResult();		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Prestamo> listarPrestamosCancelados(final String cedula) {

		final Query query = em
				.createNamedQuery("Prestamo.cancelado");
		query.setParameter("cedula", cedula);

		return query.getResultList();
	}

	@Override
	public BigDecimal consultarTienePqSolCreTramite(final String cedula) {
		//Se agrega validacion para estado en tramite para Gaf
				//no tener 
				final String sqlCredito = "SELECT COUNT(*) FROM KSCRETCREDITOS A, Kscretsolicitudes B"
						+" WHERE A.NUMAFI  = :cedula and B.NUMSOLSER=A.NUMSOLSER  and B.NUMAFI  = :cedula"
						+" AND A.CODESTPRE IN ('VIG') and  B.CODESTSOLSER not IN ('VIG')";
				
			final Query query = em.createNativeQuery(sqlCredito);
				query.setParameter("cedula", cedula);
				
			return (BigDecimal) query.getSingleResult();
	}

	@Override
	public BigDecimal obtenerCreditoConciliacion(final String cedula) {
				final String sqlCredito = " select count(CR_ESTADOPESAC ) from kscretcreditos WHERE NUMAFI=:cedula AND CR_ESTADOPESAC='CON'";
				
			final Query query = em.createNativeQuery(sqlCredito);
				query.setParameter("cedula", cedula);
				
			return (BigDecimal) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Prestamo buscarPorNumOperacionSAC(final Long numOperacionSAC) {
		final Query query = em
				.createNamedQuery("Prestamo.consultarPorNumOpSAC");
		query.setParameter("numOpSAC", numOperacionSAC);
		
		final List<Prestamo> prestamos=query.getResultList();
		if(!prestamos.isEmpty()) {
			return prestamos.get(0);
		}else {
			return null;
		}
		
		
	}

	@Override
	public boolean tieneSolicitudNovacionTramite(final Long numCanceladoNovacion,final String identificacion) {
		final Query query = em
				.createNamedQuery("Prestamo.consultarPorNumCancelNov");
		query.setParameter("numprecannov", numCanceladoNovacion);
		query.setParameter("identificacion", identificacion);
		final List<Prestamo> prestamos=query.getResultList();
		if(!prestamos.isEmpty()) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
		
	
	}
	
}
