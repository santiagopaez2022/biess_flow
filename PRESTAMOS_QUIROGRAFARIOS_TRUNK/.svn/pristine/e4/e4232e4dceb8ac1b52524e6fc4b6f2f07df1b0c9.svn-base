/*
 * Copyright 2015 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.PoolClass;
import org.jboss.ejb3.StrictMaxPool;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.TipoSolicitudDao;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitudCredito;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaSolicitudException;
import ec.gov.iess.creditos.pq.servicio.VariableSolicitudServicio;
import ec.gov.iess.creditos.pq.servicio.VariableSolicitudServicioRemote;

@Stateless
@PoolClass(value = StrictMaxPool.class, maxSize = 1, timeout = 5000)
public class VariableSolicitudServicioImpl implements VariableSolicitudServicio, VariableSolicitudServicioRemote {

	private static LoggerBiess log = LoggerBiess.getLogger(TipoSeguroServicioImpl.class);

	@EJB
	TipoSolicitudDao tipoSolicitudDao;

	public VariableSolicitudServicioImpl() {
	}

	/*
	 * @see ec.gov.iess.creditos.pq.servicio.VariableSolicitudServicio#obtenerSecuencial(int, int)
	 */
        @Override
	public TipoSolicitudCredito obtenerSecuencial(int idTipocredito, int idVariedadCredito) throws SecuenciaSolicitudException {
		String nombreSecuencia = null;
		try {
			TipoSolicitudCredito solicitudCredito = tipoSolicitudDao.consultarTipoSolicitud(idTipocredito, idVariedadCredito);
			nombreSecuencia = "CRE_KSCRETSOLICITUDES" + solicitudCredito.getCodtipsolser() + "_SEQ";
			Long secuenciaObtenida = tipoSolicitudDao.obtenerSecuenciaSolicitud(nombreSecuencia);
			log.info("El nombre de la secuencia a consultar es: " + nombreSecuencia);
			log.info("secuenciaObtenida>>>" + secuenciaObtenida);
			solicitudCredito.setNumsectipsol(secuenciaObtenida);
			tipoSolicitudDao.update(solicitudCredito);

			return solicitudCredito;
		} catch (Exception e) {
			log.error("Error al obtener secuencial solicitud. Codpretip: " + idTipocredito + ". Codprecla: " + idVariedadCredito + " nombreSecuencia="
					+ nombreSecuencia, e);
			throw new SecuenciaSolicitudException("Error al obtener secuencial solicitud", e);
		}
	}

}
