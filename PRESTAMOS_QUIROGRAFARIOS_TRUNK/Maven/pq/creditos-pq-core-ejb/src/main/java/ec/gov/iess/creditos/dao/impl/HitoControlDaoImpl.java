/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.HitoControlDao;
import ec.gov.iess.creditos.excepcion.MasDeUnCostoPorHitoException;
import ec.gov.iess.creditos.modelo.persistencia.HitoControl;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * @modificado 03/05/2010
 * 
 */
@Stateless
public class HitoControlDaoImpl extends GenericEjbDao<HitoControl, Long>
		implements HitoControlDao {

	private LoggerBiess log = LoggerBiess.getLogger(HitoControlDaoImpl.class);

	/**
	 * 
	 */
	public HitoControlDaoImpl() {
		super(HitoControl.class);
	}

	@SuppressWarnings("unchecked")
	public List<HitoControl> findByTipoSolicitud(Long idTipoSolicitud) {

		log.debug(" Consulta Hitos por tipo solicitud : " + idTipoSolicitud);

		Query q = em.createNamedQuery("HitoControl.byTipoProducto");
		q.setParameter("idTipoSolicitud", idTipoSolicitud);

		List<HitoControl> lista = q.getResultList();

		for (HitoControl hitoControl : lista) {
			hitoControl.getHitoCostos().size();
			hitoControl.getSubprocesos().size();
		}

		return lista;
	}

	public HitoControl findByIdHitoCostoActual(Long idHito, Date fecha)
			throws MasDeUnCostoPorHitoException {

		Query q = em.createNamedQuery("HitoControl.fyndCostoActualById");
		q.setParameter("idHito", idHito);
		q.setParameter("fecha", fecha);

		try {
			return (HitoControl) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new MasDeUnCostoPorHitoException(
					"Existe mas de un costo para el hito ["
							+ idHito
							+ "] en la fecha : "
							+ (new SimpleDateFormat("dd/MM/yyyy"))
									.format(fecha));
		}

	}
	
	/**
	 * Obtiene el listado de Hitos segun un tipo de Solicitud (Producto)
	 * determinado
	 * @author geguiguren
	 * 
	 * @param idTipoSolicitud
	 *            Long que representa el tipo de producto a obtener
	 * @return List<HitoControl> representa el listado de Hitos 
	 *         correspondientes a ese tipo de solicitud
	 * */
	@SuppressWarnings("unchecked")
	public List<HitoControl> findByTipoProducto(Long idTipoSolicitud) {

		log.debug(" Consulta Hitos por tipo producto : " + idTipoSolicitud);

		Query q = em.createNamedQuery("HitoControl.byTipoProducto");
		q.setParameter("idTipoSolicitud", idTipoSolicitud);

		List<HitoControl> lista = q.getResultList();

		for (HitoControl hitoControl : lista) {
			hitoControl.getHitoDetalles().size();
		}

		return lista;
	}


}
