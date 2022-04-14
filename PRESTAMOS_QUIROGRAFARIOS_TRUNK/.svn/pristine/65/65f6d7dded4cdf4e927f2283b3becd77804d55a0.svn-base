/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.SolicitudCreditoDao;
import ec.gov.iess.creditos.excepcion.ErrorParametrosException;
import ec.gov.iess.creditos.modelo.persistencia.SolicitanteCredito;
import ec.gov.iess.creditos.modelo.persistencia.SolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal 03/10/2011
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class SolictudCreditoDaoImpl extends
		GenericEjbDao<SolicitudCredito, SolicitudCreditoPK> implements
		SolicitudCreditoDao {

	private static LoggerBiess log = LoggerBiess.getLogger(SolictudCreditoDaoImpl.class);

	private static final String ES_VOLUNTARIO = "V";

	@EJB
	private SolicitudCreditoDao solicitudCreditoDao;

	/**
	 * @param type
	 */
	public SolictudCreditoDaoImpl() {
		super(SolicitudCredito.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * solicitudCreditoVigenteConyugue(java.lang.String, int)
	 */
	public SolicitudCredito solicitudCreditoVigenteConyugue(String cedula,
			int tipoSolicitud) {

		Query q = em
				.createNamedQuery("SolicitudCredito.solicitudVigenteSolicitante");
		q.setParameter("cedula", cedula);
		q.setParameter("tipoSolicitud", new Long(tipoSolicitud));

		try {
			return (SolicitudCredito) q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return (SolicitudCredito) q.getResultList().get(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * solicitudCreditoVigenteSolicitante(java.lang.String, int)
	 */
	public SolicitudCredito solicitudCreditoVigenteSolicitante(String cedula,
			int tipoSolicitud) {

		Query q = em
				.createNamedQuery("SolicitudCredito.solicitudVigenteConyugue");
		q.setParameter("cedula", cedula);
		q.setParameter("tipoSolicitud", new Long(tipoSolicitud));

		try {
			return (SolicitudCredito) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return (SolicitudCredito) q.getResultList().get(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * consultarSolicitudCreditoEager(java.util.List, java.lang.String,
	 * java.util.List)
	 */
	public SolicitudCredito consultarSolicitudCreditoEager(
			List<String> estados, String cedula, List<Long> tipoSolicitud) {

		Query q = em
				.createNamedQuery("SolicitudCredito.solicitudByEsatdoAndCedula");
		q.setParameter("estados", estados);
		q.setParameter("cedula", cedula);
		q.setParameter("tiposSolicitud", tipoSolicitud);
		try {
			SolicitudCredito solicitudCredito = (SolicitudCredito) q
					.getSingleResult();

			if (solicitudCredito != null) {
				log.debug("DET : "
						+ solicitudCredito.getDetallesolicitudList().size());
				log.debug("SOLI : "
						+ solicitudCredito.getSolicitanteList().size());
				for (SolicitanteCredito solicitanteCredito : solicitudCredito
						.getSolicitanteList()) {
					log.debug(" ING-EGR : "
							+ solicitanteCredito
									.getIngresoEgresoSolicitanteList().size());
				}
				log.debug("REQ : "
						+ solicitudCredito.getPrecondicionesList().size());
			}

			return solicitudCredito;
		} catch (NoResultException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * consultarSolicitudCreditoPkEager(java.lang.Long, java.lang.Long)
	 */
	public SolicitudCredito consultarSolicitudCreditoPkEager(
			Long numeroSolictud, Long tipoSolicitud) {

		Query q = em
				.createNamedQuery("SolicitudCredito.solicitudByEsatdoAndCedula");
		q.setParameter("tipoSolicitud", tipoSolicitud);
		q.setParameter("numeroSolictud", numeroSolictud);
		try {
			SolicitudCredito solicitudCredito = (SolicitudCredito) q
					.getSingleResult();

			if (solicitudCredito != null) {
				log.debug("DET : "
						+ solicitudCredito.getDetallesolicitudList().size());
				log.debug("SOLI : "
						+ solicitudCredito.getSolicitanteList().size());
				for (SolicitanteCredito solicitanteCredito : solicitudCredito
						.getSolicitanteList()) {
					log.debug(" ING-EGR : "
							+ solicitanteCredito
									.getIngresoEgresoSolicitanteList().size());
				}
				log.debug("REQ : "
						+ solicitudCredito.getPrecondicionesList().size());
			}

			return solicitudCredito;
		} catch (NoResultException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#consultarSolicitudCredito
	 * (java.util.List, java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitudCredito> consultarSolicitudCredito(
			List<String> estados, String cedula, List<Long> tipoSolicitud) {

		Query q = em
				.createNamedQuery("SolicitudCredito.solicitudByEsatdoAndCedula");

		q.setParameter("estados", estados);
		q.setParameter("cedula", cedula);
		q.setParameter("tiposSolicitud", tipoSolicitud);

		return q.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * consultarCedulaConEstadosYTipoSolicitud(java.util.List, java.util.List,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitudCredito> consultarCedulaConEstadosYTipoSolicitud(
			List<Long> tipoSolicitudList, List<String> estadosList,
			String cedula) throws ErrorParametrosException {

		if (null == tipoSolicitudList || tipoSolicitudList.size() == 0) {
			StringBuffer msg = new StringBuffer(
					"No existe lista de tipos de solicitud a consultar");
			log.error(msg);
			throw new ErrorParametrosException(msg.toString());
		}

		if (null == estadosList || estadosList.size() == 0) {
			StringBuffer msg = new StringBuffer(
					"No existe lista de estados de solicitud a consultar");
			log.error(msg);
			throw new ErrorParametrosException(msg.toString());
		}

		Query q = em.createNamedQuery("SolicitudCredito.findByCedulaConEstado");
		q.setParameter("cedula", cedula);
		q.setParameter("tipoSolicitud", tipoSolicitudList);
		q.setParameter("listaEstados", estadosList);

		return cargarDatosSolicitud(q.getResultList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * consultarCedulaSinEstadosYTipoSolicitud(java.util.List, java.util.List,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitudCredito> consultarCedulaSinEstadosYTipoSolicitud(
			List<Long> tipoSolicitudList, List<String> estadosList,
			String cedula) throws ErrorParametrosException {
		if (null == tipoSolicitudList || tipoSolicitudList.size() == 0) {
			StringBuffer msg = new StringBuffer(
					"No existe lista de tipos de solicitud a consultar");
			log.error(msg);
			throw new ErrorParametrosException(msg.toString());
		}

		if (null == estadosList || estadosList.size() == 0) {
			StringBuffer msg = new StringBuffer(
					"No existe lista de estados de solicitud a consultar");
			log.error(msg);
			throw new ErrorParametrosException(msg.toString());
		}

		Query q = em.createNamedQuery("SolicitudCredito.findByCedulaSinEstado");
		q.setParameter("cedula", cedula);
		q.setParameter("tipoSolicitud", tipoSolicitudList);
		q.setParameter("listaEstados", estadosList);

		return cargarDatosSolicitud(q.getResultList());
	}

	/**
	 * Se encarga de que los hijos de una solicitud sean cargados para que
	 * puedan ser usados en otra capa de negocios
	 * 
	 * @param solicitudList
	 *            Una lista de solicitudes
	 * @return La lista de solicitudes con sus hijos llenos
	 */
	private List<SolicitudCredito> cargarDatosSolicitud(
			List<SolicitudCredito> solicitudList) {

		for (SolicitudCredito solicitudCredito : solicitudList) {

			// precondiciones
			solicitudCredito.getPrecondicionesList().size();
			// proceso actual
			solicitudCredito.getProcesoRealizadoList().size();
			// detalle solicitud
			solicitudCredito.getDetallesolicitudList().size();
			// detalle de pago
			solicitudCredito.getDepositoSolicitudList().size();
			// solicitantes
			for (SolicitanteCredito solicitanteCredito : solicitudCredito
					.getSolicitanteList()) {
				solicitanteCredito.getIngresoEgresoSolicitanteList().size();
			}
			solicitudCredito.getAnulaciones().size();

			solicitudCredito.getReferenciaList().size();

		}
		return solicitudList;

	}

	private SolicitudCredito cargarDatosSolicitud(
			SolicitudCredito solicitudCredito) {
		if (solicitudCredito == null) {
			return null;
		}

		// precondiciones
		solicitudCredito.getPrecondicionesList().size();
		// proceso actual
		solicitudCredito.getProcesoRealizadoList().size();
		// detalle solicitud
		solicitudCredito.getDetallesolicitudList().size();
		// detalle pago
		solicitudCredito.getDepositoSolicitudList().size();
		// solicitantes
		for (SolicitanteCredito solicitanteCredito : solicitudCredito
				.getSolicitanteList()) {
			solicitanteCredito.getIngresoEgresoSolicitanteList().size();
		}
		solicitudCredito.getAnulaciones().size();

		solicitudCredito.getReferenciaList().size();

		solicitudCredito.getLiquidacionGastoList().size();
		return solicitudCredito;

	}

	/**
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#consultarCedulaConEstadosYTipoSolicitudEager(java.util.List,
	 *      java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<SolicitudCredito> consultarSolicitudeParaGenerarXmlEnvioTata(
			List<Long> tipoSolicitudList, List<String> estadosList)
			throws ErrorParametrosException {

		// Debido a Voluntarios este metodo debe modificarse para que no traiga
		// las sol. voluntarios
		List<String> listaNoCargados = new ArrayList<String>();
		listaNoCargados.add(ES_VOLUNTARIO);

		if (null == tipoSolicitudList || tipoSolicitudList.size() == 0) {
			StringBuffer msg = new StringBuffer(
					"No existe lista de tipos de solicitud a consultar");
			log.error(msg);
			throw new ErrorParametrosException(msg.toString());
		}

		if (null == estadosList || estadosList.size() == 0) {
			StringBuffer msg = new StringBuffer(
					"No existe lista de estados de solicitud a consultar");
			log.error(msg);
			throw new ErrorParametrosException(msg.toString());
		}

		Query q = em
				.createNamedQuery("SolicitudCredito.findSolicitudParaGenerarXml");
		q.setParameter("listaTipoSolicitud", tipoSolicitudList);
		q.setParameter("listaEstados", estadosList);
		q.setParameter("listaNoCargados", listaNoCargados);

		return cargarDatosSolicitud(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<SolicitudCredito> consultarSolicitudeParaGenerarXmlEnvioTataPorPenoAfi(
			List<Long> tipoSolicitudList, List<String> estadosList,
			List<String> listaPenoAfi) throws ErrorParametrosException {
		if (null == tipoSolicitudList || tipoSolicitudList.size() == 0) {
			StringBuffer msg = new StringBuffer(
					"No existe lista de tipos de solicitud a consultar");
			log.error(msg);
			throw new ErrorParametrosException(msg.toString());
		}

		if (null == estadosList || estadosList.size() == 0) {
			StringBuffer msg = new StringBuffer(
					"No existe lista de estados de solicitud a consultar");
			log.error(msg);
			throw new ErrorParametrosException(msg.toString());
		}

		if (null == listaPenoAfi || listaPenoAfi.size() == 0) {
			StringBuffer msg = new StringBuffer(
					"No existe lista de Tipo de Afiliado");
			log.error(msg);
			throw new ErrorParametrosException(msg.toString());
		}

		Query q = em
				.createNamedQuery("SolicitudCredito.findSolicitudParaGenerarXmlPorPenoAfi");
		q.setParameter("listaTipoSolicitud", tipoSolicitudList);
		q.setParameter("listaEstados", estadosList);
		q.setParameter("listaPenoAfi", listaPenoAfi);

		return cargarDatosSolicitud(q.getResultList());
	}

	/*
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * obtenerSecuencialGeneracionArchivoXmlTata()
	 */
	public BigInteger obtenerSecuencialGeneracionArchivoXmlTata() {
		StringBuffer sql = new StringBuffer();
		sql.append("select CRE_ENVIOS_SEQ.nextval from dual");

		Query query = em.createNativeQuery(sql.toString());
		BigDecimal resultado = (BigDecimal) query.getSingleResult();
		return BigInteger.valueOf(resultado.longValue());

	}

	public SolicitudCredito findByNut(Long nut) throws IllegalArgumentException {

		if (nut == null)
			throw new IllegalArgumentException(
					"El nut pasado como parametro es null");

		Query query = em.createNamedQuery("SolicitudCredito.findByNut");

		query.setParameter("nut", nut);

		try {
			SolicitudCredito solicitudCredito = (SolicitudCredito) query
					.getSingleResult();
			return this.cargarDatosSolicitud(solicitudCredito);
		} catch (NoResultException e) {
			log.warn("No se ha encontrado solicitud con nut: " + nut);
			log.warn("Retornando null");
			return null;
		} catch (NonUniqueResultException e) {
			throw new RuntimeException("NUT duplicado[" + nut + "]");
		}

	}

	@SuppressWarnings({ "unchecked", "null" })
	public List<SolicitudCredito> findByNuts(List<BigInteger> nuts)
			throws IllegalArgumentException {
		if (nuts != null) {
			throw new IllegalArgumentException("La lista de nuts es nula");
		}

		if (nuts.size() == 0) {
			throw new IllegalArgumentException(
					"La lista de nuts no tiene elementos");
		}

		Query query = em.createNamedQuery("SolicitudCredito.findByNuts");
		query.setParameter("nuts", nuts);

		return this.cargarDatosSolicitud(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public List<SolicitudCredito> findByNuttran(Long nuttran) {
		Query query = em.createNamedQuery("SolicitudCredito.findByNuttran");

		query.setParameter("nuttran", nuttran);

		return this.cargarDatosSolicitud(query.getResultList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * consultarSolicitudesSinAgenciaPorTipoSolicitud(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<BigDecimal> consultarSolicitudesSinAgenciaPorTipoSolicitud(
			List<Long> tipoSolicitud) throws IllegalArgumentException {

		Query q = em
				.createNativeQuery("SELECT kscretsolicitudes.nut as NUT "
						+ "FROM kscretsolicitudes,  "
						+ "cre_detallesolicitud_tbl "
						+ "WHERE cre_detallesolicitud_tbl.codtipsolser = kscretsolicitudes.codtipsolser "
						+ "AND cre_detallesolicitud_tbl.numsolser = kscretsolicitudes.numsolser  "
						+ "AND cre_detallesolicitud_tbl.CODAGN is null "
						+ "AND kscretsolicitudes.codtipsolser IN (:lista1) ");

		q.setParameter("lista1", tipoSolicitud);

		return q.getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * consultarSolicitudesSinPorcentajeParticipacionPorTipoSolicitud
	 * (java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<BigDecimal> consultarSolicitudesSinPorcentajeParticipacionPorTipoSolicitud(
			List<Long> tipoSolicitud) throws IllegalArgumentException {

		Query q = em
				.createNativeQuery("SELECT kscretsolicitudes.nut as NUT "
						+ "FROM kscretsolicitudes,  "
						+ "cre_solicitante_tbl  "
						+ "WHERE cre_solicitante_tbl.numsolser = kscretsolicitudes.numsolser "
						+ "AND cre_solicitante_tbl.codtipsolser = kscretsolicitudes.codtipsolser "
						+ "AND cre_solicitante_tbl.PORPAR IS NULL  "
						+ "AND kscretsolicitudes.codtipsolser IN (:lista1) ");

		q.setParameter("lista1", tipoSolicitud);

		return q.getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * consultarSolicitudesPorEstadosConDetalle(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitudCredito> consultarSolicitudesPorEstadosConDetalle(
			List<String> estadosList) throws ErrorParametrosException {
		if (null == estadosList || estadosList.size() == 0) {
			StringBuffer msg = new StringBuffer(
					"No existe lista de estados de solicitud a consultar");
			log.error(msg);
			throw new ErrorParametrosException(msg.toString());
		}

		Query q = em
				.createNamedQuery("SolicitudCredito.findSolicitudPorEstados");
		q.setParameter("listaEstados", estadosList);

		List<SolicitudCredito> solicitudes = cargarDatosSolicitud(q
				.getResultList());
		for (SolicitudCredito solicitudCredito : solicitudes) {
			log.debug("cargara detalle?");
			int size = solicitudCredito.getDetallesolicitudList().size();
			log.debug("size:" + size);
		}

		return solicitudes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.SolicitudCreditoDao#
	 * consultarSolicitudesPorTipoSolicitudPorEstadoPorProcesoRealizdo
	 * (java.util.List, java.util.List, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<SolicitudCredito> consultarSolicitudesPorTipoSolicitudPorEstadoPorProcesoRealizdo(
			List<Long> tipoSolicitud, List<String> listaEstado,
			List<Long> listaProcesoRealizado) {

		Query q = em
				.createNamedQuery("SolicitudCredito.consultarTipoSolicitudProcesoRealizado");
		q.setParameter("tipoSolicitud", tipoSolicitud);
		q.setParameter("listaEstados", listaEstado);
		q.setParameter("listaProceosRealizados", listaProcesoRealizado);

		return this.cargarDatosSolicitud(q.getResultList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#consultarSolicitudPagoTramite
	 * (java.lang.Long, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public SolicitudCredito consultarSolicitudPagoTramite(Long nut,
			Integer numeroPago) {

		log.debug("DAO consultarSolicitudPagoTramite : nut " + nut);
		log.debug("DAO consultarSolicitudPagoTramite : numeroDeposito "
				+ numeroPago);

		Query q = em
				.createNamedQuery("SolicitudCredito.consultarDepositoSolicitadoActual");
		q.setParameter("nut", nut);
		q.setParameter("numeroDeposito", numeroPago);
		try {

			return cargarDatosSolicitud((SolicitudCredito) q.getSingleResult());

		} catch (NoResultException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * consultarSolicitudPagoTramiteDetallePagos(java.lang.Long,
	 * java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public SolicitudCredito consultarSolicitudPagoTramiteDetallePagos(Long nut,
			Integer numeroPago) {
		log.debug("DAO consultarSolicitudPagoTramiteDetallePagos : nut " + nut);
		log.debug("DAO consultarSolicitudPagoTramiteDetallePagos : numeroPago "
				+ numeroPago);

		Query q = em
				.createNamedQuery("SolicitudCredito.consultarDepositoSolicitadoActualDetalle");
		q.setParameter("nut", nut);
		q.setParameter("numeroPago", numeroPago);
		try {

			return cargarDatosSolicitud((SolicitudCredito) q.getSingleResult());

		} catch (NoResultException e) {
			return null;
		}
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String consultarSolicitudGastosAdministrativos(String cedula,
			List<Long> codTipSolSerList) {

		Query q = em
				.createNativeQuery("SELECT distinct p.numafi as NUMAFI "
						+ "FROM kscretsolicitudes o, "
						+ "cre_solicitante_tbl p "
						+ "where o.codtipsolser >= 35 "
						+ "and p.NUMSOLSER=o.NUMSOLSER "
						+ "and p.CODTIPSOLSER=o.CODTIPSOLSER "
						+ "and p.numafi=:cedula "
						+ "and o.CODESTSOLSER IN ('GAP','REC', 'GRA', 'RAV', 'GVA', 'VAV')");
		q.setParameter("cedula", cedula);

		try {
			return q.getSingleResult().toString();
		} catch (NoResultException e) {
			return null;
		}
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public String consultarSolicitudTramite(String cedula,
			List<Long> codTipSolSerList, List<String> codEstSolSerList) {

		Query q = em.createNativeQuery("SELECT distinct p.numafi as NUMAFI "
				+ "FROM kscretsolicitudes o, " + "cre_solicitante_tbl p "
				+ "where o.codtipsolser >= 35 "
				+ "and p.NUMSOLSER=o.NUMSOLSER "
				+ "and p.CODTIPSOLSER=o.CODTIPSOLSER "
				+ "and p.numafi=:cedula "
				+ "and o.CODESTSOLSER IN ('APR','SOL')");
		q.setParameter("cedula", cedula);

		try {
			return q.getSingleResult().toString();
		} catch (NoResultException e) {
			log.error(e);
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * consultarSolicitudesPorTipoSolicitudPorUltimoEstadoPorProcesoRealizdo
	 * (java.util.List, java.util.List, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<SolicitudCredito> consultarSolicitudesPorTipoSolicitudPorEstadoPorUltimoProcesoRealizdo(
			List<Long> tipoSolicitud, List<String> listaEstado,
			List<Long> listaProcesoRealizado) {

		Query q = em
				.createNamedQuery("SolicitudCredito.consultarTipoSolicitudUltimoProcesoRealizado");
		q.setParameter("tipoSolicitud", tipoSolicitud);
		q.setParameter("listaEstados", listaEstado);
		q.setParameter("listaProceosRealizados", listaProcesoRealizado);

		return this.cargarDatosSolicitud(q.getResultList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#
	 * consultarSolicitudesParaCargos(java.util.List, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<SolicitudCredito> consultarSolicitudesParaCargos(
			List<Long> tiposSolicitud, List<String> estadosSolicitud) {
		Query q = em.createNamedQuery("SolicitudCredito.solicitudesSinCargos");
		q.setParameter("estadosList", estadosSolicitud);
		q.setParameter("tipoSolicitud", tiposSolicitud);
		return this.cargarDatosSolicitud(q.getResultList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#consultarMaximoDesembolso
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<SolicitudCredito> consultarMaximoDesembolso(Long nut,
			Integer numeroPago) {
		Query q = em
				.createNamedQuery("SolicitudCredito.consultarMaximoDesembolso");
		q.setParameter("nut", nut);
		q.setParameter("numeroPago", numeroPago);
		return (List<SolicitudCredito>) (q.getResultList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#obtenerSolicitudVigente
	 * (java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public SolicitudCredito obtenerSolicitudVigente(String cedula,
			List<Long> tipoSolicitud) {
		Query q = em
				.createNamedQuery("SolicitudCredito.obtenerSolicitudVigente");
		q.setParameter("cedula", cedula);
		q.setParameter("tipoSolicitud", tipoSolicitud);
		List<SolicitudCredito> lista = q.getResultList();
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.dao.SolicitudCreditoDao#obtenerSolicitudVigenteIndSol
	 * (java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public SolicitudCredito obtenerSolicitudVigenteIndSol(String cedula,
			List<Long> tipoSolicitud) {
		Query q = em
				.createNamedQuery("SolicitudCredito.obtenerPrestamoVigente");
		q.setParameter("cedula", cedula);
		q.setParameter("tipoSolicitud", tipoSolicitud);
		List<SolicitudCredito> lista = q.getResultList();
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	/**
	 * Metodo que obtiene la lista de Solicitudes de Credito que posee el
	 * Solicitante
	 * 
	 * @author geguiguren
	 * 
	 * @param cedula
	 * @param idTipoProducto
	 * @return List<SolicitudCredito> retorna el listado de Solicitudes de
	 *         Credito
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitudCredito> encontrarPorCedulaPorTipoProducto(
			String cedula, Long idTipoProducto) {
		Query q = em
				.createNamedQuery("SolicitudCredito.obtenerSolicitudAprobada");
		q.setParameter("cedula", cedula);
		q.setParameter("idTipoProducto", idTipoProducto);

		List<SolicitudCredito> lista = q.getResultList();
		if (lista.size() > 0) {
			return lista;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<SolicitudCredito> buscarPorLiquidacionAprobada(List<Long> tipoSolicitud) {
		Query q = em
				.createNamedQuery("SolicitudCredito.obtenerSolicitudLiquidacionAprobada");
		q.setParameter("tipoSolicitud", tipoSolicitud);

		List<SolicitudCredito> lista = q.getResultList();
		if (lista.size() > 0) {
			return lista;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.dao.SolicitudCreditoDao#numeroSucursalesAfectadasAfiliado(java.lang.String, java.util.List)
	 */
	public Long numeroSucursalesAfectadasAfiliado(String identificacion, List<String> listaCodDivPolitica) {
		StringBuffer select= new StringBuffer();
		select.append("SELECT count(1)");
		select.append(" FROM ksafitserpre e, kspcotsucursales s");
		select.append(" WHERE e.rucemp = s.rucemp AND e.codsuc = s.codsuc");
		select.append(" AND e.numafi = :identificacion AND e.esthislab = '1'");
		select.append(" AND s.estsuc <> '0' AND s.codestemp <> 'INA'");
		select.append(" AND e.fecsalafi is null" + " AND (");

		int indice = 0;
		for (String codDivPol : listaCodDivPolitica) {
			if (indice == 0) {
				select.append(" s.coddivpol LIKE '" + codDivPol + "%'");
			} else {
				select.append(" OR s.coddivpol LIKE '" + codDivPol + "%'");
			}
			indice++;
		}
		select.append(")");

		Query query = em.createNativeQuery(select.toString());
		query.setParameter("identificacion", identificacion);

		BigDecimal numeroSucursalesAfectadas = (BigDecimal) query.getSingleResult();
		return numeroSucursalesAfectadas.longValue();
	}
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.dao.SolicitudCreditoDao#numeroSucursalesAfectadasJubilado(java.lang.String, java.util.List)
	 */
	public Long numeroSucursalesAfectadasJubilado(String identificacion, List<String> listaCodDivPolitica) {
		
		StringBuffer select= new StringBuffer();
		select.append("SELECT count(1)");
		select.append(" FROM ksafitserpre e, kspcotsucursales s");
		select.append(" WHERE e.rucemp = s.rucemp AND e.codsuc = s.codsuc");
		select.append(" AND e.numafi = :identificacion AND e.esthislab = '0'");
		select.append(" AND e.fecsalafi is not null" + " AND (");

		int indice = 0;
		for (String codDivPol : listaCodDivPolitica) {
			if (indice == 0) {
				select.append(" s.coddivpol LIKE '" + codDivPol + "%'");
			} else {
				select.append(" OR s.coddivpol LIKE '" + codDivPol + "%'");
			}
			indice++;
		}
		select.append(")");
		select.append(" AND fecsalafi IN(SELECT MAX(k.fecsalafi)");
		select.append(" FROM ksafitserpre k WHERE k.numafi = :identificacion)");

		Query query = em.createNativeQuery(select.toString());
		query.setParameter("identificacion", identificacion);

		BigDecimal numeroSucursalesAfectadas = (BigDecimal) query.getSingleResult();
		return numeroSucursalesAfectadas.longValue();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.SolicitudCreditoDao#
	 * contarPlanillaMoraSectorPublico(java.lang.String)
	 */
	public BigDecimal contarPlanillaMoraSectorPublico(String cedula) {
		StringBuilder sql = new StringBuilder();
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
		sql.append(" AND (p.esttippla NOT IN ");
		sql.append(" ('ANU', ");
		sql.append(" 'REG', ");
		sql.append(" 'GEV', ");
		sql.append(" 'TCO', ");
		sql.append(" 'CAN', ");
		sql.append(" 'CAC', ");
		sql.append(" 'CAT', ");
		sql.append(" 'CAG', ");
		sql.append(" 'CAA') ");
		sql.append(" ) ");
		sql.append(" AND (p.codtipcompag IS NULL OR p.codcompag IS NULL) ");
		sql.append(" AND TRUNC(p.feccrepla) <= TRUNC(p.fecpagpla) ");
		sql.append(" AND (to_number(to_char(p.FECCREPLA,'yyyy'))*100 + to_number(to_char(p.FECCREPLA,'mm'))) < ");
		sql.append(" to_number(to_char(ADD_MONTHS(TRUNC(SYSDATE, 'mm'), -2), 'yyyymm')) ");
		sql.append(" AND trunc(p.fecpagpla) < trunc(sysdate) ");
		sql.append(" AND e.esthislab = '1' ");
		sql.append(" AND e.fecsalafi is null ");
		sql.append(" and p.CODTIPPLA in ('P','PJ') ");
		sql.append(" AND e.numafi = :cedula ");

		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("cedula", cedula);

		return (BigDecimal) query.getSingleResult();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.SolicitudCreditoDao#numeroImposicionesSolicitante(java.lang.String, int, int)
	 */
	public Long numeroImposicionesSolicitante(String identificacion, int hastaMes, int hastaAnio) {
		StringBuffer select = new StringBuffer();

		select.append("SELECT COUNT(1) FROM kscretimpos i  WHERE i.numafi = :identificacion");
		select.append(" AND NOT (aniper >= :hastaAnio AND mesper > :hastaMes)");
		select.append(" ORDER BY i.aniper, i.mesper");

		Query query = em.createNativeQuery(select.toString());
		query.setParameter("identificacion", identificacion);
		query.setParameter("hastaMes", hastaMes);
		query.setParameter("hastaAnio", hastaAnio);

		BigDecimal numeroPlanillasMora = (BigDecimal) query.getSingleResult();
		return numeroPlanillasMora.longValue();
	}

	@SuppressWarnings("unchecked")
	public SolicitudCredito traerSolicitudPorTipoPorNumero(Long tipoSolicitud, Long numeroSolicitud) {

		Query q = em.createNamedQuery("SolicitudCredito.solicitudPk");

		q.setParameter("tipoSolicitud", tipoSolicitud);

		q.setParameter("numeroSolictud", numeroSolicitud);

		List<SolicitudCredito> lista = q.getResultList();

		if (!lista.isEmpty()) {

			return lista.get(0);
		}
		return null;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarEstadoSolicitudCredito(SolicitudCreditoPK pk, String estado) {
		SolicitudCredito solicitud = solicitudCreditoDao.load(pk);
		solicitud.setCodestsolser(estado);
		solicitudCreditoDao.update(solicitud);		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarNutSolicitudCredito(SolicitudCreditoPK pk, Long nut) {
		SolicitudCredito solicitud = solicitudCreditoDao.load(pk);
		solicitud.setNut(nut);
		solicitudCreditoDao.update(solicitud);		
	}

}
