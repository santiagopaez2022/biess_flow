/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.DetalleSolicitudDao;
import ec.gov.iess.creditos.excepcion.CrearDetalleSolicitudException;
import ec.gov.iess.creditos.modelo.persistencia.DetalleSolicitud;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class DetalleSolicitudDaoImpl extends
		GenericEjbDao<DetalleSolicitud, Long> implements DetalleSolicitudDao {

	/**
	 * @param type
	 */
	public DetalleSolicitudDaoImpl() {
		super(DetalleSolicitud.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.DetalleSolicitudDao#crearDetalleSolicitud(java.util.List)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void crearDetalleSolicitud(
			List<DetalleSolicitud> detalleSolicitudList)
			throws CrearDetalleSolicitudException {

		if (detalleSolicitudList != null) {

			for (DetalleSolicitud detalleSolicitud : detalleSolicitudList) {
				try {

					insert(detalleSolicitud);
				} catch (RuntimeException e) {
					throw new CrearDetalleSolicitudException(e);
				}
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.DetalleSolicitudDao#consultarPorFechaSolicitudPago(java.util.Date,
	 *      java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<DetalleSolicitud> consultarPorFechaSolicitudPago(Date desde,
			Date hasta) {
		List<DetalleSolicitud> lista = new ArrayList<DetalleSolicitud>();
		if (desde == null && hasta == null) {
			lista = em.createNamedQuery(
					"DetalleSolicitud.consultarPorFechaSolicitudPagoNula")
					.getResultList();

		} else {
			if (desde == null) {
				Calendar inicio = Calendar.getInstance();
				inicio.set(Calendar.YEAR, 1900);
				desde = inicio.getTime();
			}
			if (hasta == null) {
				hasta = new Date();
			}

			Query q = em
					.createNamedQuery("DetalleSolicitud.consultarPorFechaSolicitudPagoNula");
			q.setParameter("desde", desde);
			q.setParameter("hasta", hasta);
			lista = q.getResultList();
		}

		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.DetalleSolicitudDao#obtenerPorCedulaNut(java.lang.String,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public DetalleSolicitud obtenerPorCedulaNut(String cedula, String nut,
			List<Long> tipoSolicitud, List<String> listaEstado) {
		boolean addNut = false;
		List<DetalleSolicitud> lista = new ArrayList<DetalleSolicitud>();
		StringBuilder select = new StringBuilder(
				"SELECT d FROM SolicitudCredito o JOIN o.detallesolicitudList d WHERE o.codestsolser IN (:estadosSolSer) "
						+ "AND o.solicitudCreditoPK.codtipsolser IN (:tiposSolSer) AND o.numafi=:cedula ");

		if (!(nut == null || "".equals(nut))) {
			select.append("AND o.nut=:nut ");
			addNut = true;
		}
		Query q = em.createQuery(select.toString());
		q.setParameter("estadosSolSer", listaEstado);
		q.setParameter("tiposSolSer", tipoSolicitud);
		q.setParameter("cedula", cedula);
		if (addNut) {
			q.setParameter("nut", Long.parseLong(nut));
		}
		lista = q.getResultList();
		if (lista.size() > 0) {
			return (DetalleSolicitud) lista.get(0);
		}
		return null;
	}
}
