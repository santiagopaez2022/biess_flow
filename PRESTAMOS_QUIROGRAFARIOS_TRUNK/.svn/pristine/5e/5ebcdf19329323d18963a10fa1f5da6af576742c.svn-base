/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NonUniqueResultException;
import javax.sql.DataSource;

import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.modelo.persistencia.RucMora;
import ec.fin.biess.service.RucMoraServicioLocal;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.AgenciaDao;
import ec.gov.iess.creditos.dao.DepositoSolicitudDao;
import ec.gov.iess.creditos.dao.DocumentacionAvaluoDao;
import ec.gov.iess.creditos.dao.DocumentacionRequeridaDetalleDao;
import ec.gov.iess.creditos.dao.PermisoProcesoDao;
import ec.gov.iess.creditos.dao.ProcesoRealizadoDao;
import ec.gov.iess.creditos.dao.SolicitanteCreditoDao;
import ec.gov.iess.creditos.dao.SolicitudCreditoDao;
import ec.gov.iess.creditos.dao.TipoRolSolicitanteDao;
import ec.gov.iess.creditos.dao.TipoSolicitanteDao;
import ec.gov.iess.creditos.dao.TipoSolicitudDao;
import ec.gov.iess.creditos.enumeracion.RolSolicitante;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.ErrorParametrosException;
import ec.gov.iess.creditos.excepcion.GenerarSecuenciaException;
import ec.gov.iess.creditos.excepcion.MasDeUnPermisoMismoEstadoException;
import ec.gov.iess.creditos.modelo.persistencia.Agencia;
import ec.gov.iess.creditos.modelo.persistencia.DepositoSolicitud;
import ec.gov.iess.creditos.modelo.persistencia.DocumentacionAvaluo;
import ec.gov.iess.creditos.modelo.persistencia.DocumentacionRequerida;
import ec.gov.iess.creditos.modelo.persistencia.DocumentacionRequeridaDetalle;
import ec.gov.iess.creditos.modelo.persistencia.PermisoProceso;
import ec.gov.iess.creditos.modelo.persistencia.ProcesoRealizado;
import ec.gov.iess.creditos.modelo.persistencia.SolicitanteCredito;
import ec.gov.iess.creditos.modelo.persistencia.SolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.TipoRolSolicitante;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitante;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK;
import ec.gov.iess.creditos.pq.excepcion.ConsultaAgenciaException;
import ec.gov.iess.creditos.pq.excepcion.CrearSolicitudCreditoException;
import ec.gov.iess.creditos.pq.excepcion.MasDeUnRegistroException;
import ec.gov.iess.creditos.pq.excepcion.MasDeUnSolicitanteException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteTipoRolSolicitanteException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteTipoSolicitanteException;
import ec.gov.iess.creditos.pq.excepcion.PagoAnteriorNoRealizadoException;
import ec.gov.iess.creditos.pq.excepcion.PagoEnProcesoException;
import ec.gov.iess.creditos.pq.excepcion.PagoRealizadoException;
import ec.gov.iess.creditos.pq.excepcion.SolicitanteExcepcion;
import ec.gov.iess.creditos.pq.excepcion.SolicitudException;
import ec.gov.iess.creditos.pq.excepcion.TransaccionErrorBDDException;
import ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicioRemote;
import ec.gov.iess.creditos.sp.SolicitudCreditoJdbc;
import ec.gov.iess.creditos.sp.impl.SolicitudCreditoJdbcImpl;
import ec.gov.iess.hl.modelo.ServicioPrestado;
import ec.gov.iess.hl.servicio.ServicioPrestadoServicio;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class SolicitudCreditoServicioImpl implements SolicitudCreditoServicio,
		SolicitudCreditoServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(SolicitudCreditoServicioImpl.class);

	@EJB
	private SolicitudCreditoDao solicitudCreditoDao;

	@EJB
	private SolicitanteCreditoDao solicitanteCreditoDao;

	@EJB
	private TipoSolicitanteDao tipoSolicitanteDao;
	@EJB
	private TipoRolSolicitanteDao tipoRolSolicitanteDao;

	@EJB
	private TipoSolicitudDao tipoSolicitudDao;

	@EJB
	private PermisoProcesoDao permisoProcesoDao;

	@EJB
	private ProcesoRealizadoDao procesoRealizadoDao;

	@EJB
	private DocumentacionRequeridaDetalleDao documentacionRequeridaDetalleDao;

	@EJB
	private AgenciaDao agenciaDao;

	@EJB
	private DepositoSolicitudDao depositoSolicitudDao;

	@EJB
	private DocumentacionAvaluoDao documentacionAvaluoDao;
	
	/**
	 * Servicio para consultar parametros
	 */
	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private ServicioPrestadoServicio servicioPrestado;
	
	@EJB
	private RucMoraServicioLocal rucMoraServicio;

	@Resource(mappedName = "java:credito-pq-DS")
	DataSource dataSource;

	/**
	 * 
	 */
	public SolicitudCreditoServicioImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * crearSolicitudCreditoPq(long, long, java.util.Date, java.lang.String,
	 * java.lang.String)
	 */
	public SolicitudCredito crearSolicitudCreditoPq(long secuencialSolicitud,
			long idTipoSolicitud, Date fechaSolicitud, String cedulaAfiliado,
			String tipoSolicitante) {
		log.debug(" crearSolicitudCreditoPq");
		log.debug(" Parametros");
		log.debug(" secuencialSolicitud : " + secuencialSolicitud);
		log.debug(" idTipoSolicitud : " + idTipoSolicitud);
		log.debug(" fechaSolicitud : " + fechaSolicitud);
		log.debug(" cedulaAfiliado : " + cedulaAfiliado);
		log.debug(" tipoSolicitante : " + tipoSolicitante);

		SolicitudCredito solicitudCreditoNew = new SolicitudCredito();
		SolicitudCreditoPK solicitudCreditoPKNew = new SolicitudCreditoPK();
		// TODO REVISAR PROBLEMA ESTADO GEN
		solicitudCreditoPKNew.setNumsolser(secuencialSolicitud);
		solicitudCreditoPKNew.setCodtipsolser(idTipoSolicitud);

		solicitudCreditoNew.setSolicitudCreditoPK(solicitudCreditoPKNew);
		final Long nutSolicitud = tipoSolicitudDao.obtenerSecuenciaSolicitud("CRE_SOLICITUDES_SEQ");
		solicitudCreditoNew.setNumsecinmhip(null);
		solicitudCreditoNew.setSueafisol(null);
		solicitudCreditoNew.setFecsolser(fechaSolicitud);
		solicitudCreditoNew.setObssolser("No existe observacion");
		solicitudCreditoNew.setCodofiies(null);
		solicitudCreditoNew.setCodestsolser("APR");
		solicitudCreditoNew.setCodfunapr(null);
		solicitudCreditoNew.setCodfun(null);
		solicitudCreditoNew.setNumafi(cedulaAfiliado);
		solicitudCreditoNew.setPenoafi(tipoSolicitante);
		solicitudCreditoNew.setFectersolser(fechaSolicitud);
		solicitudCreditoNew.setNut(nutSolicitud);
		try {
			solicitudCreditoDao.insert(solicitudCreditoNew);

			return solicitudCreditoNew;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(" Error al crear la solicitud", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * getSolicitudCreditoConyugue(java.lang.String, int)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SolicitudCredito getSolicitudCreditoConyugue(String cedula,
			int tipoSolicitud) {
		return solicitudCreditoDao.solicitudCreditoVigenteSolicitante(cedula,
				tipoSolicitud);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * getSolicitudCreditoSolicitante(java.lang.String, int)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SolicitudCredito getSolicitudCreditoSolicitante(String cedula,
			int tipoSolicitud) {
		return solicitudCreditoDao.solicitudCreditoVigenteConyugue(cedula,
				tipoSolicitud);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#crearSolicitud
	 * (ec.gov.iess.creditos.pq.modelo.persistencia.SolicitudCredito)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public SolicitudCredito crearSolicitud(SolicitudCredito solicitudCredito)
			throws CrearSolicitudCreditoException, TransaccionErrorBDDException {
		try {

			solicitudCreditoDao.insert(solicitudCredito);

		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new CrearSolicitudCreditoException(e.getMessage());
		}
		return solicitudCredito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * getTipoRolSolicitante(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TipoRolSolicitante getTipoRolSolicitante(Long tipoRolSolicitanteId)
			throws NoExisteTipoRolSolicitanteException {

		TipoRolSolicitante tipoRolSolicitante = tipoRolSolicitanteDao
				.load(tipoRolSolicitanteId);

		if (tipoRolSolicitante == null) {
			throw new NoExisteTipoRolSolicitanteException(
					"No existe tipo de rol solicitante ["
							+ tipoRolSolicitanteId + "]");
		}

		return tipoRolSolicitante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#getTipoSolicitante
	 * (java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TipoSolicitante getTipoSolicitante(Long tipoSolicitanteId)
			throws NoExisteTipoSolicitanteException {

		TipoSolicitante tipoSolicitante = tipoSolicitanteDao
				.load(tipoSolicitanteId);

		if (tipoSolicitante == null) {
			throw new NoExisteTipoSolicitanteException(
					"No existe tipo solicitante [" + tipoSolicitanteId + "]");
		}

		return tipoSolicitante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * getSecuenciaSolicitud(int, int)
	 */
	public TipoSolicitudCredito getSecuenciaSolicitud(int idTipoCredito,
			int idVariedadCredito) throws GenerarSecuenciaException {

		return tipoSolicitudDao.getSecuencia(idTipoCredito, idVariedadCredito);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarSolicitudCreditoEager(java.util.List, java.lang.String,
	 * java.util.List)
	 */
	public SolicitudCredito consultarSolicitudCreditoEager(
			List<String> estados, String cedula, List<Long> tipoSolicitud) {

		return solicitudCreditoDao.consultarSolicitudCreditoEager(estados,
				cedula, tipoSolicitud);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarSolicitudCredito(java.util.List, java.lang.String,
	 * java.util.List)
	 */
	public List<SolicitudCredito> consultarSolicitudCredito(
			List<String> estados, String cedula, List<Long> tipoSolicitud) {

		return solicitudCreditoDao.consultarSolicitudCredito(estados, cedula,
				tipoSolicitud);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarSolicitudCreditoPkEager(java.lang.Long, java.lang.Long)
	 */
	public SolicitudCredito consultarSolicitudCreditoPkEager(
			Long numeroSolictud, Long tipoSolicitud) {
		return solicitudCreditoDao.consultarSolicitudCreditoPkEager(
				numeroSolictud, tipoSolicitud);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarCedulaConEstadosYTipoSolicitud(java.util.List, java.util.List,
	 * java.lang.String)
	 */
	public List<SolicitudCredito> consultarCedulaConEstadosYTipoSolicitud(
			List<Long> tipoSolicitudList, List<String> estadosList,
			String cedula) throws ErrorParametrosException {

		return solicitudCreditoDao.consultarCedulaConEstadosYTipoSolicitud(
				tipoSolicitudList, estadosList, cedula);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarCedulaSinEstadosYTipoSolicitud(java.util.List, java.util.List,
	 * java.lang.String)
	 */
	public List<SolicitudCredito> consultarCedulaSinEstadosYTipoSolicitud(
			List<Long> tipoSolicitudList, List<String> estadosList,
			String cedula) throws ErrorParametrosException {
		return solicitudCreditoDao.consultarCedulaSinEstadosYTipoSolicitud(
				tipoSolicitudList, estadosList, cedula);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#permisoAccion
	 * (java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public PermisoProceso permisoAccion(Long tipoSolicitud, Long idEstado,
			RolSolicitante rol, Long idAccion)
			throws MasDeUnPermisoMismoEstadoException {

		log.debug("Consulta permiso");
		return permisoProcesoDao.permisoAccion(tipoSolicitud, idEstado, rol,
				idAccion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * insertarNuevoProcesoRealizado
	 * (ec.gov.iess.creditos.pq.modelo.persistencia.ProcesoRealizado)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void insertarNuevoProcesoRealizado(ProcesoRealizado procesoRealizado) {

		procesoRealizadoDao.insertarNuevoProcesoRealizado(procesoRealizado);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * getProcesoRealizadoSolicitud
	 * (ec.gov.iess.creditos.pq.modelo.persistencia.SolicitudCreditoPK)
	 */
	public List<ProcesoRealizado> getProcesoRealizadoSolicitud(
			SolicitudCreditoPK solicitudCreditoPK) {

		return procesoRealizadoDao
				.getProcesoRealizadoSolicitud(solicitudCreditoPK);
	}

	public List<DocumentacionRequerida> getDocumentacion(String idProvincia,
			Long idTipoSolciitud, String vendedorTipoPersona, String tipoBien) {
		List<DocumentacionRequeridaDetalle> lista = documentacionRequeridaDetalleDao
				.getDocumentacion(idProvincia, idTipoSolciitud,
						vendedorTipoPersona, tipoBien);

		List<DocumentacionRequerida> listaDoc = new ArrayList<DocumentacionRequerida>();
		for (DocumentacionRequeridaDetalle det : lista) {
			listaDoc.add(det.getDocumentacionRequerida());
		}

		return listaDoc;
	}

	public SolicitanteCredito getSolicitanteSolicitudVigente(String cedula,
			Long idTipoSolcitud, List<String> estadosSolicitudNoVigente)
			throws MasDeUnSolicitanteException, SolicitanteExcepcion {

		SolicitanteCredito solicitanteCredito = null;

		try {
			solicitanteCredito = solicitanteCreditoDao
					.getSolicitanteByCedulaTipoSolicitudestadoSolicitud(cedula,
							idTipoSolcitud, estadosSolicitudNoVigente);

		} catch (NonUniqueResultException e) {
			StringBuffer msg = new StringBuffer();
			msg.append("Existe mas de una solcicitud en para la cedula : "
					+ cedula + " tipo solicitud : " + idTipoSolcitud);
			log.error(msg);
			MasDeUnSolicitanteException error = new MasDeUnSolicitanteException(
					msg.toString());
			error.setCedula(cedula);
			throw error;
		} catch (NullPointerException e) {
			log.error(e);
			throw new SolicitanteExcepcion(e);
		}

		return solicitanteCredito;
	}

	/*
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarSolicitudesParaGenerarXmlEnvioTata(java.util.List,
	 * java.util.List)
	 */
	public List<SolicitudCredito> consultarSolicitudesParaGenerarXmlEnvioTata(
			List<Long> tipoSolicitudList, List<String> estadosList)
			throws ErrorParametrosException, SolicitudException {

		List<SolicitudCredito> resultado = null;
		try {
			resultado = solicitudCreditoDao
					.consultarSolicitudeParaGenerarXmlEnvioTata(
							tipoSolicitudList, estadosList);

			if (!(resultado != null && resultado.size() > 0)) {
				SolicitudException se = new SolicitudException(
						"NO HAY SOLICITUDES PARA GENERAR XML TATA");
				log.error(se);
				throw se;
			}

			return resultado;
		} catch (EJBException e) {
			SolicitudException se = new SolicitudException(
					"ERROR AL CONSULTAR LAS SOLICITUDES PARA GENERAR XML TATA: "
							+ e.getMessage(), e);
			log.error(se);
			throw se;
		}
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public BigInteger obtenerSecuencialGeneracionArchivoXmlTata() {
		return solicitudCreditoDao.obtenerSecuencialGeneracionArchivoXmlTata();
	}

	// TODO verificar este cambio a not supperted
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SolicitudCredito consultarSolicitudByNut(Long nut) {
		return solicitudCreditoDao.findByNut(nut);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<SolicitudCredito> consultarSolicitudesByNuttran(Long nuttran) {
		return solicitudCreditoDao.findByNuttran(nuttran);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * actualizarDesdeIntranet
	 * (ec.gov.iess.creditos.pq.modelo.persistencia.SolicitudCredito)
	 */
	public void actualizarDesdeIntranet(SolicitudCredito solicitudCredito) {
		// SolicitudCredito actualizado = solicitudCreditoDao
		// .load(solicitudCredito.getSolicitudCreditoPK());

		// TODO probar esto
		solicitudCreditoDao.update(solicitudCredito);

		// actualiza datos domicilio

		// actualiza datos referencia

		// actualiza datos vivienda a adquirir

		// actualiza datos de vendedor

		// actualizado.getDetallesolicitudList().get(0).setVendedorTelf(
		// solicitudCredito.getDetallesolicitudList().get(0)
		// .getVendedorTelf());
		// actualizado.getDetallesolicitudList().get(0).setVendedorTelfTrabajo(
		// solicitudCredito.getDetallesolicitudList().get(0)
		// .getVendedorTelfTrabajo());
		// actualizado.getDetallesolicitudList().get(0).setVendedorTelfTrabajo(
		// solicitudCredito.getDetallesolicitudList().get(0)
		// .getVendedorTelfTrabajo());
		//
		// actualizado.getDetallesolicitudList().get(0)
		// .setVendedorDivisionPolitica(
		// solicitudCredito.getDetallesolicitudList().get(0)
		// .getVendedorDivisionPolitica());
		// actualizado.getDetallesolicitudList().get(0).setVendedorDireccion(
		// solicitudCredito.getDetallesolicitudList().get(0)
		// .getVendedorDireccion());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarAgenciaPorProvincia(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Agencia consultarAgenciaPorProvincia(String idProvincia)
			throws MasDeUnRegistroException, ConsultaAgenciaException {

		try {

			return agenciaDao.consultarAgenciaPorProvincia(idProvincia);

		} catch (NonUniqueResultException e) {
			log.error(e);
			throw new MasDeUnRegistroException(
					"Mas de una agencia para la provincia : " + idProvincia
							+ " ", e);
		} catch (Exception e) {
			log.error(e);
			throw new ConsultaAgenciaException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarSolicitudesSinAgenciaPorTipoSolicitud(java.util.List)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<BigDecimal> consultarSolicitudesSinAgenciaPorTipoSolicitud(
			List<Long> tipoSolicitud) throws SolicitudException,
			IllegalArgumentException {
		if (tipoSolicitud == null || tipoSolicitud.size() == 0) {
			throw new IllegalArgumentException(
					"La lista de tipos de solicitud no puede ser null o vacia.");
		}
		try {
			return solicitudCreditoDao
					.consultarSolicitudesSinAgenciaPorTipoSolicitud(tipoSolicitud);
		} catch (Exception e) {
			log.error(e);
			throw new SolicitudException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarSolicitudesSinPorcentajeParticipacionPorTipoSolicitud
	 * (java.util.List)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<BigDecimal> consultarSolicitudesSinPorcentajeParticipacionPorTipoSolicitud(
			List<Long> tipoSolicitud) throws SolicitudException,
			IllegalArgumentException {
		if (tipoSolicitud == null || tipoSolicitud.size() == 0) {
			throw new IllegalArgumentException(
					"La lista de tipos de solicitud no puede ser null o vacia.");
		}
		try {
			return solicitudCreditoDao
					.consultarSolicitudesSinPorcentajeParticipacionPorTipoSolicitud(tipoSolicitud);
		} catch (Exception e) {
			log.error(e);
			throw new SolicitudException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarSolicitudesPorEstadosConDetalle(java.util.List)
	 */
	public List<SolicitudCredito> consultarSolicitudesPorEstadosConDetalle(
			List<String> estadosLis) throws ErrorParametrosException {

		return solicitudCreditoDao
				.consultarSolicitudesPorEstadosConDetalle(estadosLis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarSolicitudesPorTipoSolicitudPorEstadoPorProcesoRealizdo
	 * (java.util.List, java.util.List, java.util.List)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<SolicitudCredito> consultarSolicitudesPorTipoSolicitudPorEstadoPorProcesoRealizdo(
			List<Long> tipoSolicitud, List<String> listaEstado,
			List<Long> listaProcesoRealizado) throws IllegalArgumentException,
			SolicitudException {

		if (tipoSolicitud == null || tipoSolicitud.size() == 0) {
			throw new IllegalArgumentException(
					"La lista de solicitudes no puede ser null o vacia.");
		}

		if (listaEstado == null || listaEstado.size() == 0) {
			throw new IllegalArgumentException(
					"La lista de estados no puede ser null o vacia.");
		}

		if (listaProcesoRealizado == null || listaProcesoRealizado.size() == 0) {
			throw new IllegalArgumentException(
					"La lista de proceoss realizados no puede ser null o vacia.");
		}

		List<SolicitudCredito> solicitudList = solicitudCreditoDao
				.consultarSolicitudesPorTipoSolicitudPorEstadoPorUltimoProcesoRealizdo(
						tipoSolicitud, listaEstado, listaProcesoRealizado);

		List<SolicitudCredito> solicitudListNew = new ArrayList<SolicitudCredito>();

		for (SolicitudCredito solicitudCredito : solicitudList) {

			if (listaProcesoRealizado.contains(solicitudCredito
					.getUltimoEstado().getEstadoProceso().getId())) {

				solicitudListNew.add(solicitudCredito);

			}

		}

		return solicitudListNew;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarCedulasConSolicitudDuplicada(java.util.List, java.lang.String,
	 * java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<String> consultarCedulasConSolicitudDuplicada(
			List<Long> tiposSolicitud, String estado, Integer count) {
		SolicitudCreditoJdbc solicitudCreditoJdbc = new SolicitudCreditoJdbcImpl(
				dataSource);
		return solicitudCreditoJdbc.consultarCedulasConSolicitudDuplicada(
				tiposSolicitud, estado, count);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * controlarPagosRealizadosNut(java.lang.Long, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void controlarPagosRealizadosNut(Long nut, Integer numeroPago,
			Integer numeroTotalDesembolso) throws PagoRealizadoException,
			PagoEnProcesoException, IllegalArgumentException,
			PagoAnteriorNoRealizadoException {

		log.debug("SER: controlarPagosRealizadosNut");

		// TODO
		if (nut == null) {
			throw new IllegalArgumentException("Nut no puede ser NULL");
		}
		log.debug("SER: consultarSolicitudPagoTramite");
		if (solicitudCreditoDao.consultarSolicitudPagoTramite(nut, numeroPago) != null) {
			log.info("SER: controlarPagosRealizadosNut PagoEnProcesoException");
			throw new PagoEnProcesoException("El pago esta en tramite");
		}
		log.info("SER: consultarSolicitudPagoTramiteDetallePagos");
		if (solicitudCreditoDao.consultarSolicitudPagoTramiteDetallePagos(nut,
				numeroPago) != null) {
			log.info("SER: controlarPagosRealizadosNut PagoRealizadoException");
			throw new PagoRealizadoException("El pago se  ha realizado");
		}
		log.info("SER: consultarMaximoDesembolso ************** " + numeroPago);

		List<SolicitudCredito> listSol = solicitudCreditoDao
				.consultarMaximoDesembolso(nut, numeroPago - 1);

		if (listSol != null && listSol.size() > 0) {
			log.info("SER: controlarPagosRealizadosNut PagoAnteriorNoRealizadoException");
			throw new PagoAnteriorNoRealizadoException(
					"El pago anterior no se  ha realizado");
		}

		// VALIDACIÓN PARA NÚMERO TOTAL DE DESEMBOLSO

		if (numeroPago > numeroTotalDesembolso) {
			log.info("SER: controlarPagosRealizadosNut PagoRealizadoException");
			throw new PagoRealizadoException(
					"El pago no está dentro del límite del número total de desembolsos.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * registrarDepositoSolicitud
	 * (ec.gov.iess.creditos.pq.modelo.persistencia.DepositoSolicitud)
	 */
	public void registrarDepositoSolicitud(DepositoSolicitud depositoSolicitud) {
		depositoSolicitudDao.insert(depositoSolicitud);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean consultarSolicitudEnTramitePorCedulaEstadoTipoSolicitud(
			String cedula, List<Long> codTipSolSerList,
			List<String> codEstSolSerList) throws IllegalArgumentException {

		log.error("SRV: Tiene solicitud tramite.");

		if (cedula == null) {
			throw new IllegalArgumentException(
					"Parametro cedula no puede ser null");
		}
		if (codTipSolSerList == null) {
			throw new IllegalArgumentException(
					"Lista de tipo de solicitud no puede ser null");
		}
		if (codEstSolSerList == null) {
			throw new IllegalArgumentException(
					"Lista de estados de solicitud no puede ser null");
		}

		String solicitudCredito = solicitudCreditoDao
				.consultarSolicitudTramite(cedula, codTipSolSerList,
						codEstSolSerList);

		if (solicitudCredito == null) {
			return false;
		}
		return true;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean consultarSolicitudGastosAdministrativosPorCedulaTipoSolicitud(
			String cedula, List<Long> codTipSolSerList)
			throws IllegalArgumentException {

		log.info("SRV: Verifica si tiene solicitud en tramite.");
		if (cedula == null) {
			throw new IllegalArgumentException(
					"Parametro cedula no puede ser null");
		}
		if (codTipSolSerList == null) {
			throw new IllegalArgumentException(
					"Lista de tipo de solicitud no puede ser null");
		}

		String solicitudCredito = solicitudCreditoDao
				.consultarSolicitudGastosAdministrativos(cedula,
						codTipSolSerList);

		if (solicitudCredito != null) {
			return true;
		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * consultarSolicitudesParaCargosHipotecarios()
	 */
	public List<SolicitudCredito> consultarSolicitudesParaCargosHipotecarios(
			List<Long> tiposSolicitudes, List<String> estados) {
		// Consultar solicitudes
		return solicitudCreditoDao.consultarSolicitudesParaCargos(
				tiposSolicitudes, estados);
	}

	public List<DocumentacionAvaluo> getDocumentacionAvaluo(
			Long codigoTipoSolSer) {
		return documentacionAvaluoDao.obtenerPorTipoSolSer(codigoTipoSolSer);
	}

	public List<DocumentacionAvaluo> getDocumentacionAvaluoPorTipoProd(
			Long codigoTipoSolSer, String codigoTipoProd) {
		return documentacionAvaluoDao.obtenerPorTipoSolSerTipoProd(
				codigoTipoSolSer, codigoTipoProd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * obtenerSolicitudVigente(java.lang.String, java.util.List)
	 */
	public SolicitudCredito obtenerSolicitudVigente(String cedula,
			List<Long> tipoSolicitud) {
		return solicitudCreditoDao.obtenerSolicitudVigente(cedula,
				tipoSolicitud);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * obtenerSolicitudVigenteIndSol(java.lang.String, java.util.List)
	 */
	public SolicitudCredito obtenerSolicitudVigenteIndSol(String cedula,
			List<Long> tipoSolicitud) {
		return solicitudCreditoDao.obtenerSolicitudVigenteIndSol(cedula,
				tipoSolicitud);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#esDamnificado(java.lang.String)
	 */
	public boolean esDamnificado(String identificacion, TipoPrestamista tipoPrestamista) throws ParametroBiessException {
		boolean damnificado = false;
		String codigosDivPolitica = this.parametroBiessDao
				.consultarPorIdNombreParametro(BiessEmergenteEnum.CODIGOS_DIVISIONES_POLITICAS.getIdParametro(),
						BiessEmergenteEnum.CODIGOS_DIVISIONES_POLITICAS.getNombreParametro())
				.getValorCaracter();

		List<String> listaCodDivAfectadas = new ArrayList<String>(Arrays.asList(codigosDivPolitica.split(";")));
		//Solo si el prestamista es jubilado
		if(TipoPrestamista.JUBILADO.equals(tipoPrestamista)){
			Long numeroSucursalesAfecJub = solicitudCreditoDao.numeroSucursalesAfectadasJubilado(identificacion, listaCodDivAfectadas);
			String codigoProvincia=identificacion.substring(0,2);
			//Cuando perteneces a una de las provicncias afectadas
			if (this.esProvinciaAfectada(codigoProvincia, listaCodDivAfectadas)
					|| numeroSucursalesAfecJub > 0) {
				damnificado = true;
			}
		}else{
			//Cuando tiene un rol diferente a jubilado
			Long numeroSucursalesAfec = solicitudCreditoDao.numeroSucursalesAfectadasAfiliado(identificacion, listaCodDivAfectadas);
			//Si su sucursal donde trabaja esta registrada como afectada
			if (numeroSucursalesAfec > 0) {
				damnificado = true;
			}
		}
		return damnificado;
	}
	
	/**
	 * Determina si la provicnia es considerada como afectada a partir de los
	 * digitos de division politica
	 * 
	 * @param idProvincia
	 * @param listaAfectadas
	 * @return
	 */
	private boolean esProvinciaAfectada(String idProvincia, List<String> listaAfectadas) {
		boolean esAfectada = false;
		for (String idAfectada : listaAfectadas) {
			String codigoAfectada = idAfectada.substring(0, 2);
			if (idProvincia.equals(codigoAfectada)) {
				esAfectada = true;
				break;
			}
		}	
		return esAfectada;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#
	 * contarPlanillaMoraSectorPublico(java.lang.String)
	 */
	@Override
	public BigDecimal contarPlanillaMoraSectorPublico(String cedula) {
		return solicitudCreditoDao.contarPlanillaMoraSectorPublico(cedula);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#esBeneficiarioEmpresaPublica(java.lang.String)
	 */
	@Override
	public boolean esBeneficiarioEmpresaPublica(String cedula) {
		String fechaVigenciaBeneficiario = null;
		Date fechaVigencia = null;
		try {
			fechaVigenciaBeneficiario = this.parametroBiessDao
					.consultarPorIdNombreParametro(ParametrosBiessEnum.FECHA_VIG_BENEFICIARIO.getIdParametro(),
							ParametrosBiessEnum.FECHA_VIG_BENEFICIARIO.getNombreParametro())
					.getValorCaracter();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			fechaVigencia = dateFormat.parse(fechaVigenciaBeneficiario);
		} catch (ParametroBiessException e) {
			log.info("Error al consultar el parametro de vigenecia de beneficiario", e);
			return false;
		} catch (ParseException e) {
			log.error("Error al convertir fecha de String a date", e);
			return false;
		}

		boolean esBeneficiario = false;

		Calendar calFechaVigencia = Calendar.getInstance();
		calFechaVigencia.setTime(fechaVigencia);
		calFechaVigencia.set(Calendar.HOUR, 0);
		calFechaVigencia.set(Calendar.MINUTE, 0);
		calFechaVigencia.set(Calendar.SECOND, 0);
		calFechaVigencia.set(Calendar.MILLISECOND, 0);

		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(Calendar.HOUR, 0);
		fechaActual.set(Calendar.MINUTE, 0);
		fechaActual.set(Calendar.SECOND, 0);
		fechaActual.set(Calendar.MILLISECOND, 0);

		if (fechaActual.equals(calFechaVigencia) || fechaActual.before(calFechaVigencia)) {

			List<ServicioPrestado> listaEmpresasActivo = servicioPrestado.consultarActivoPorCedula(cedula);
			List<String> listaRucs = new ArrayList<String>();

			for (ServicioPrestado servicioPrestado : listaEmpresasActivo) {
				if ("E".equals(servicioPrestado.getOripag())) {
					listaRucs.add(servicioPrestado.getServicioPrestadoPk().getRucEmpleador());
				}
			}

			List<RucMora> listaRucMora = null;
			if (!listaRucs.isEmpty()) {
				listaRucMora = rucMoraServicio.obtenerListaMoraPorRucs(listaRucs);
			}

			esBeneficiario = listaRucMora != null && !listaRucMora.isEmpty();
		}

		return esBeneficiario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio#numeroImposicionesSolicitante(java.lang.String,
	 * int, int)
	 */
	@Override
	public Long numeroImposicionesSolicitante(String identificacion, int hastaMes, int hastaAnio) {
		return solicitudCreditoDao.numeroImposicionesSolicitante(identificacion, hastaMes, hastaAnio);
	}

}