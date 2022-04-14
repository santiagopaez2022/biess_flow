/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.PermisoProcesoDao;
import ec.gov.iess.creditos.enumeracion.RolSolicitante;
import ec.gov.iess.creditos.excepcion.MasDeUnPermisoMismoEstadoException;
import ec.gov.iess.creditos.modelo.persistencia.PermisoProceso;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class PermisoProcesoDaoImpl extends GenericEjbDao<PermisoProceso, Long>
		implements PermisoProcesoDao {

	private LoggerBiess log = LoggerBiess.getLogger(PermisoProcesoDaoImpl.class);

	/**
	 * 
	 */
	public PermisoProcesoDaoImpl() {
		super(PermisoProceso.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.PermisoProcesoDao#permisoAccion(java.lang.Long,
	 *      java.lang.Long, java.lang.String, java.lang.String)
	 */
	public PermisoProceso permisoAccion(Long tipoSolicitud, Long idEstado,
			RolSolicitante rol, Long idAccion)
			throws MasDeUnPermisoMismoEstadoException {

		Query q = em.createNamedQuery("PermisoProceso.findByPersmisoAccion");


		q.setParameter("tipoSolicitud", tipoSolicitud);
		q.setParameter("tipoSolicitud2", tipoSolicitud);		
		q.setParameter("idEstado", idEstado);
		q.setParameter("rol", rol);
		q.setParameter("idAccion", idAccion);

		try {

			return (PermisoProceso) q.getSingleResult();

		} catch (NonUniqueResultException e) {
			StringBuffer msg = new StringBuffer();
			msg.append("Existe mas de un permiso para : ");
			msg.append("Tipo Solicitud [" + tipoSolicitud + "], ");
			msg.append("Rol Solicitante [" + rol + "], ");
			msg.append("Estado [" + idEstado + "], ");
			msg.append("Accion [" + idAccion + "]");

			log.error(msg);
			throw new MasDeUnPermisoMismoEstadoException(msg.toString());
		}catch (NoResultException e){
			return null;
		}
	}

}
