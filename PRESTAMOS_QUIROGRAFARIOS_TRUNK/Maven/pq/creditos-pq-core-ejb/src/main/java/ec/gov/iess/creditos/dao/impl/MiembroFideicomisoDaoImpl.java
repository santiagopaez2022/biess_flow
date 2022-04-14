/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.MiembroFideicomisoDao;
import ec.gov.iess.creditos.excepcion.CrearDatoProyectoConstructorException;
import ec.gov.iess.creditos.modelo.persistencia.MiembroFideicomiso;
import ec.gov.iess.creditos.modelo.persistencia.pk.MiembroFideicomisoPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Clase para implementar los métodos de miembros de fideicomiso
 * 
 * @author jsanchez
 * 
 */
@Stateless
public class MiembroFideicomisoDaoImpl extends GenericEjbDao<MiembroFideicomiso, MiembroFideicomisoPk> implements
		MiembroFideicomisoDao {

	private static final LoggerBiess log = LoggerBiess.getLogger(MiembroFideicomisoDaoImpl.class);

	public MiembroFideicomisoDaoImpl() {
		super(MiembroFideicomiso.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.MiembroFideicomisoDao#obtenerNumeroEmpleadosPorRuc(java.lang.String,
	 *      java.lang.String)
	 */
	public Long obtenerNumeroEmpleadosPorRuc(String ruc, String cedulaEmpleador) {
		try {
			String select = "select count(numafi) from ksafitserpre "
					+ "where rucemp=:ruc and numafi<>:cedula and esthislab='1' ";
			Query q = em.createNativeQuery(select);
			q.setParameter("ruc", ruc);
			q.setParameter("cedula", cedulaEmpleador);
			BigDecimal dato = (BigDecimal) q.getSingleResult();
			return dato.longValue();
		} catch (Exception e) {
			log.error("Error al consultar empleados de RUC diferentes a representante legal.", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.MiembroFideicomisoDao#crearMiembroProyecto(ec.gov.iess.creditos.modelo.persistencia.MiembroFideicomiso)
	 */
	public MiembroFideicomiso crearMiembroProyecto(MiembroFideicomiso miembro)
			throws CrearDatoProyectoConstructorException {
		try {
			insert(miembro);
		} catch (Exception e) {
			log.error("Error al crear miembro del proyecto.", e);
			throw new CrearDatoProyectoConstructorException("ERROR AL CREAR MIEMBRO DEL PROYECTO: "
					+ miembro.getPk().getIdentificacion());
		}
		return miembro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.MiembroFideicomisoDao#obtenerPorCoditoProyecto(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<MiembroFideicomiso> obtenerPorCoditoProyecto(Long codigo) {
		Query q = em.createNamedQuery("MiembroFideicomiso.obtenerPorCodigoProyecto");
		q.setParameter("codigo", codigo);
		List<MiembroFideicomiso> lista = q.getResultList();
		if (lista == null) {
			log.error("Error al consultar miembros de proyecto.");
			return null;
		}
		return lista;
	}
}
