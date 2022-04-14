package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.SolicitanteCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.SolicitanteCredito;
import ec.gov.iess.dao.ejb.GenericEjbDao;

@Stateless
public class SolicitanteCreditoDaoImpl extends GenericEjbDao<SolicitanteCredito, Long> implements SolicitanteCreditoDao {

	private LoggerBiess log = LoggerBiess.getLogger(SolicitanteCreditoDaoImpl.class);

	public SolicitanteCreditoDaoImpl() {
		super(SolicitanteCredito.class);
	}

	public SolicitanteCredito getSolicitanteByCedulaTipoSolicitudestadoSolicitud(String cedula, Long idTipoSolcitud,
			List<String> estadosNoVigentesSolicitud) {

		log.debug(" Consulta solicitante [" + cedula + "] tipo solicitud [" + idTipoSolcitud + "]");

		Query q = em.createNamedQuery("SolicitanteCredito.findByCedulaTipoYEstadoSolicitud");
		q.setParameter("cedula", cedula);
		q.setParameter("idTipoSolicitud", idTipoSolcitud);
		q.setParameter("listaEstados", estadosNoVigentesSolicitud);

		try {
			return (SolicitanteCredito) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Long tieneRol(String rol, String cedula) {
		Query query = em.createNativeQuery("SELECT COUNT(*) FROM KSSEGTROLPORUSU RPU,KSSEGTROLES ROL "
				+ "WHERE ROL.TIPROL = :rol AND RPU.CEDIDEUSU = :cedula AND RPU.ESTROL = 'A' "
				+ "AND RPU.CODROL = ROL.CODROL");

		query.setParameter("rol", rol);
		query.setParameter("cedula", cedula);
		return (Long) query.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeec.gov.iess.creditos.dao.SolicitanteCreditoDao#
	 * obtenerPorCedulaTipoEstadoSolicitud(java.lang.String, java.util.List,
	 * java.util.List)
	 */

	@SuppressWarnings("unchecked")
	public SolicitanteCredito obtenerPorCedulaTipoEstadoSolicitud(String cedula, List<String> estadosSolicitud,
			List<Long> tiposSolicitud) {
		Query q = em.createNamedQuery("SolicitanteCredito.encontrarSolicitudesCancelar");
		q.setParameter("cedula", cedula);
		q.setParameter("listaEstados", estadosSolicitud);
		q.setParameter("tiposSolicitud", tiposSolicitud);
		List<SolicitanteCredito> lista = q.getResultList();
		if (lista == null || lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.SolicitanteCreditoDao#obtenerSolicitudesVigentes
	 * (java.lang.String, java.util.List, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitanteCredito> obtenerSolicitudesVigentes(String cedula, List<String> estadosSolicitud,
			List<Long> tiposSolicitud) {
		Query q = em.createNamedQuery("SolicitanteCredito.encontrarSolicitudesVigentes");
		q.setParameter("cedula", cedula);
		q.setParameter("listaEstados", estadosSolicitud);
		q.setParameter("tiposSolicitud", tiposSolicitud);
		return q.getResultList();

	}
}
