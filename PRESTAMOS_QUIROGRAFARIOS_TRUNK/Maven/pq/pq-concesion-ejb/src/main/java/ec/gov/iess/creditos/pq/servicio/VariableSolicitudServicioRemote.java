/*
 * Copyright 2015 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitudCredito;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaSolicitudException;

@Remote
public interface VariableSolicitudServicioRemote {

	/**
	 * Obtiene e incrementa el secuencial de una solicitud de credito
	 * 
	 * @param idTipocredito
	 *            identificador del tipo de credito
	 * @param idVariedadCredito
	 *            identificador de la variedad de cerdito
	 * @return un objeto de modelo {@link TipoSolicitudCredito} caso contrario null
	 * @throws SecuenciaSolicitudException
	 */
	TipoSolicitudCredito obtenerSecuencial(int idTipocredito, int idVariedadCredito)
			throws SecuenciaSolicitudException;

}
