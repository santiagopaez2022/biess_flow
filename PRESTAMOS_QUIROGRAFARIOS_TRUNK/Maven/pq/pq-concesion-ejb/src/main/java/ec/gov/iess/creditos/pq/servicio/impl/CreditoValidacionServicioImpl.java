package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.CreditoValidacionDao;
import ec.gov.iess.creditos.modelo.persistencia.CreditoValidacion;
import ec.gov.iess.creditos.pq.servicio.CreditoValidacionServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoValidacionServicioRemote;

/**
 * Servicio para logica de negocios para credito validacion
 * 
 * @author hugo.mora
 *
 */
@Stateless
public class CreditoValidacionServicioImpl implements CreditoValidacionServicioLocal, CreditoValidacionServicioRemote {

	@EJB
	private CreditoValidacionDao creditoValidacionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CreditoValidacionServicio#consultarPorPrestamoYCedula(java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@Override
	public CreditoValidacion consultarPorPrestamo(Long codprecla, Long codpretip, Long numpreafi, Long ordpreafi) {
		return this.creditoValidacionDao.consultarPorPrestamo(codprecla, codpretip, numpreafi, ordpreafi);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CreditoValidacionServicio#insertarCreditoValidacion(ec.gov.iess.creditos.modelo.
	 * persistencia.CreditoValidacion)
	 */
	@Override
	public void insertarCreditoValidacion(CreditoValidacion creditoValidacion) {
		this.creditoValidacionDao.insert(creditoValidacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CreditoValidacionServicioLocal#consultarPorPrestamoConDetalle(java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public CreditoValidacion consultarPorPrestamoConDetalle(Long codprecla, Long codpretip, Long numpreafi, Long ordpreafi) {
		CreditoValidacion creditoValidacion = null;
		creditoValidacion = this.creditoValidacionDao.consultarPorPrestamo(codprecla, codpretip, numpreafi, ordpreafi);

		if (creditoValidacion.getListaDetalleFocalizado() != null && !creditoValidacion.getListaDetalleFocalizado().isEmpty()) {
			creditoValidacion.getListaDetalleFocalizado().size();
		}

		return creditoValidacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CreditoValidacionServicioLocal#actualizarCreditoValidacion(ec.gov.iess.creditos.
	 * modelo.persistencia.CreditoValidacion)
	 */
	@Override
	public void actualizarCreditoValidacion(CreditoValidacion creditoValidacion) {
		this.creditoValidacionDao.update(creditoValidacion);
	}

}
