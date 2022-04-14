/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.HitoControlDao;
import ec.gov.iess.creditos.dao.RubroCalificacionDao;
import ec.gov.iess.creditos.excepcion.MasDeUnCostoPorHitoException;
import ec.gov.iess.creditos.modelo.persistencia.HitoControl;
import ec.gov.iess.creditos.modelo.persistencia.RubroCalificacion;
import ec.gov.iess.creditos.pq.excepcion.HitoControlException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteCostoOperativoException;
import ec.gov.iess.creditos.pq.servicio.EstadosCalificacionServicio;
import ec.gov.iess.creditos.pq.servicio.EstadosCalificacionServicioRemote;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class EstadosCalificacionServicioImpl implements
		EstadosCalificacionServicio, EstadosCalificacionServicioRemote {

	private LoggerBiess log = LoggerBiess
			.getLogger(EstadosCalificacionServicioImpl.class);

	@EJB
	private HitoControlDao hitoControlDao;

	@EJB
	private RubroCalificacionDao rubroCalificacionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.EstadosCalificacionServicio#consultarHitosPorTipoSolicitud(java.lang.Long)
	 */
	public List<HitoControl> consultarHitosPorTipoSolicitud(Long idTipoSolicitud)
			throws HitoControlException {
		try {

			return hitoControlDao.findByTipoSolicitud(idTipoSolicitud);
		} catch (Exception e) {
			log.error(e);
			throw new HitoControlException(e);
		}
	}

	public BigDecimal costoOperativoHitoActual(Long idHito)
			throws NoExisteCostoOperativoException,
			MasDeUnCostoPorHitoException {

		HitoControl hitoControl = hitoControlDao.findByIdHitoCostoActual(
				idHito, new Date());

		if (hitoControl == null) {
			throw new NoExisteCostoOperativoException(
					"No existe valor de costo operativo.");
		}

		return hitoControl.getHitoCostos().get(0).getValor();
	}

	public List<RubroCalificacion> consultarRubros() {
		return rubroCalificacionDao.listaRubrosActivos();
	}
}
