package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.PeriodoComprobanteDao;
import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.modelo.persistencia.PeriodoComprobante;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * DAO para acceso a datos de la entidad PeriodoComprobante
 * 
 * @author hugo.mora
 * @date 2016/08/03
 *
 */
@Stateless
public class PeriodoComprobanteDaoImpl extends GenericEjbDao<PeriodoComprobante, Long> implements PeriodoComprobanteDao {

	public PeriodoComprobanteDaoImpl() {
		super(PeriodoComprobante.class);
	}

	private static final LoggerBiess LOG = LoggerBiess.getLogger(PeriodoComprobanteDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.PeriodoComprobanteDao#buscarPorPeriodoYEmpleador(java.lang.Long, java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public PeriodoComprobante buscarPorPeriodoYEmpleador(Integer aniPer, Integer mesPer, String tipoEmpleador) throws PeriodoComprobanteException {
		try {
			Query query = em.createNamedQuery("PeriodoComprobante.buscarPorPeriodoYEmpleador");
			query.setParameter("aniper", aniPer);
			query.setParameter("mesper", mesPer);
			query.setParameter("tipoEmpleador", tipoEmpleador);
			return (PeriodoComprobante) query.getSingleResult();
		} catch (NoResultException e) {
			StringBuilder msgErrorPeriodo = new StringBuilder();
			msgErrorPeriodo.append("No existe un periodo activo para el anio: " + aniPer);
			msgErrorPeriodo.append(" mes: " + mesPer);
			msgErrorPeriodo.append(" tipo empleador: " + tipoEmpleador);
			LOG.error("Error al buscar periodo para generacion de comprobante - " + msgErrorPeriodo.toString());
			throw new PeriodoComprobanteException("No existe un per\u00EDodo activo para el a\u00F1o, mes y tipo empleador enviado");
		} catch (NonUniqueResultException e) {
			StringBuilder msgErrorPeriodo = new StringBuilder();
			msgErrorPeriodo.append("Existe mas de un periodo activo para el anio: " + aniPer);
			msgErrorPeriodo.append(" mes: " + mesPer);
			msgErrorPeriodo.append(" tipo empleador: " + tipoEmpleador);
			LOG.error("Error al buscar periodo para generacion de comprobante - " + msgErrorPeriodo.toString());
			throw new PeriodoComprobanteException("Existe m\u00E1s de un per\u00EDodo activo para el a\u00F1o, mes y tipo empleador enviado");
		}
	}

}
