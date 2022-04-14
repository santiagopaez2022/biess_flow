/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.DatoProyectoConstructorDao;
import ec.gov.iess.creditos.excepcion.ListaVaciaException;
import ec.gov.iess.creditos.modelo.persistencia.DatoProyectoConstructor;
import ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author jsanchez
 * 
 */
@Stateless
public class DatoProyectoConstructorDaoImpl extends GenericEjbDao<DatoProyectoConstructor, Long> implements
		DatoProyectoConstructorDao {

	private static final LoggerBiess log = LoggerBiess.getLogger(DatoProyectoConstructorDaoImpl.class);

	public DatoProyectoConstructorDaoImpl() {
		super(DatoProyectoConstructor.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DatoProyectoConstructorDao#registrar(ec.gov.iess.creditos.modelo.persistencia.DatoProyectoConstructor)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public DatoProyectoConstructor registrar(DatoProyectoConstructor datoProyecto) throws ListaVaciaException {
		try {
			insert(datoProyecto);
		} catch (Exception e) {
			log.error("Error al crear datos del proyecto", e);
			throw new ListaVaciaException("ERROR AL REGISTRAR DATOS DEL PROYECTO.");
		}
		return datoProyecto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DatoProyectoConstructorDao#obtenerDatosPorSolicitud(ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK)
	 */
	@SuppressWarnings("unchecked")
	public List<DatoProyectoConstructor> obtenerDatosPorSolicitud(SolicitudCreditoPK pk) {
		Query q = em.createNamedQuery("DatoProyectoConstructor.obtenerPorSolicitud");
		q.setParameter("pk", pk);
		return q.getResultList();
	}
}
