package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.PeriodoComprobanteDao;
import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.modelo.persistencia.PeriodoComprobante;
import ec.gov.iess.creditos.pq.servicio.PeriodoComprobanteServicio;

/**
 * Servicio para logica de negocio de periodos de comprobantes
 * 
 * @author hugo.mora
 * @date 2016/08/03
 *
 */
@Stateless
public class PeriodoComprobanteServicioImpl implements PeriodoComprobanteServicio {

	@EJB
	private PeriodoComprobanteDao periodoComprobanteDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PeriodoComprobanteServicio#buscarPorPeriodoYEmpleador(java.lang.Long,
	 * java.lang.Long, java.lang.String)
	 */
	@Override
	public PeriodoComprobante buscarPorPeriodoYEmpleador(Integer aniPer, Integer mesPer, String tipoEmpleador) throws PeriodoComprobanteException {
		return periodoComprobanteDao.buscarPorPeriodoYEmpleador(aniPer, mesPer, tipoEmpleador);
	}

}
