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
import ec.gov.iess.creditos.dao.VariablePrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.VariablePrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.VariablePrestamoPK;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaCreditoException;
import ec.gov.iess.creditos.pq.servicio.VariablePrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.VariablePrestamoServicioRemote;

@Stateless
@PoolClass(value = StrictMaxPool.class, maxSize = 1, timeout = 5000)
public class VariablePrestamoServicioImpl implements VariablePrestamoServicio, VariablePrestamoServicioRemote {

	private static LoggerBiess log = LoggerBiess.getLogger(TipoSeguroServicioImpl.class);

	@EJB
	VariablePrestamoDao variablePrestamoDao;
        
        @EJB
	TipoSolicitudDao tipoSolicitudDao;

	public VariablePrestamoServicioImpl() {
	}

	public VariablePrestamo obtenerSecuencial(int idTipocredito, int idVariedadCredito)
			throws SecuenciaCreditoException {
		try {
			VariablePrestamoPK variablePrestamoPK = new VariablePrestamoPK();
			variablePrestamoPK.setCodprecla(new Long(idVariedadCredito));
			variablePrestamoPK.setCodpretip(new Long(idTipocredito));
			VariablePrestamo variablePrestamo = variablePrestamoDao.load(variablePrestamoPK);
			String nombreSecuencia = "CRE_KSCRETCREDITOS" + idTipocredito + "_" + idVariedadCredito + "_SEQ";
			log.info("El nombre de la secuencia a consultar es: " + nombreSecuencia);
			Long secuenciaObtenida = tipoSolicitudDao.obtenerSecuenciaSolicitud(nombreSecuencia);
			log.info("cre secuenciaObtenida>>>" + secuenciaObtenida);
			variablePrestamo.setSecvarpre(secuenciaObtenida);
			variablePrestamoDao.update(variablePrestamo);
			return variablePrestamo;
		} catch (Exception e) {
			log.error("Error al obtener secuencial credito. Codpretip: " + idTipocredito + ". Codprecla: "
					+ idVariedadCredito, e);
			throw new SecuenciaCreditoException("Error al obtener secuencial credito", e);
		}
	}

}
