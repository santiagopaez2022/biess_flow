/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */

package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.DepositoSolicitudHistoricoDao;
import ec.gov.iess.creditos.modelo.persistencia.DepositoSolicitudHistorico;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Clase para implementación de métodos para histórico de solicitud de
 * deposito </b>
 * 
 * @author Jenny Sanchez
 * @version $Revision: 1.5 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 15:43:59 $]
 *          </p>
 */
@Stateless
public class DepositoSolicitudHistoricoDaoImpl extends
		GenericEjbDao<DepositoSolicitudHistorico, Long> implements
		DepositoSolicitudHistoricoDao {

	public DepositoSolicitudHistoricoDaoImpl() {
		super(DepositoSolicitudHistorico.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.DepositoSolicitudHistoricoDao#obtenerPorSPI(
	 * java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public DepositoSolicitudHistorico obtenerPorSPI(Long codDetalleSol,
			String spi) {
		Query q = em
				.createNamedQuery("DepositoSolicitudHistorico.obtenerPorSpi");
		q.setParameter("codDetalle", codDetalleSol);
		q.setParameter("spi", spi);
		List<DepositoSolicitudHistorico> lista = q.getResultList();
		if (lista == null || lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DepositoSolicitudHistoricoDao#
	 * obtenerSPIPorCodDetalleSol(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public DepositoSolicitudHistorico obtenerSPIPorCodDetalleSol(
			Long codDetalleSol) {
		Query q = em
				.createNamedQuery("DepositoSolicitudHistorico.obtenerSpiPorCodDetalle");
		q.setParameter("codDetalle", codDetalleSol);
		List<DepositoSolicitudHistorico> lista = q.getResultList();
		if (lista == null || lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DepositoSolicitudHistoricoDao#
	 * obtenerHistPorCodDetalleSol(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public DepositoSolicitudHistorico obtenerHistPorCodDetalleSol(
			Long codDetalleSol) {
		Query q = em
				.createNamedQuery("DepositoSolicitudHistorico.obtenerHistPorCodDetalle");
		q.setParameter("codDetalle", codDetalleSol);
		List<DepositoSolicitudHistorico> lista = q.getResultList();
		if (lista == null || lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}
}
