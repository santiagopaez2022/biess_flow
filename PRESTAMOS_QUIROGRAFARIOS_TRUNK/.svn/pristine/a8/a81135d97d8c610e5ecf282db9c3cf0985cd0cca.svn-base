package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.excepcion.PHEnTramiteException;

@Local
public interface ValidarPHEnTramiteServicioLocal {
	
	/**
	 * 
	 * Consulta si tiene un PH en tramite mediante una consulta en logiflow
	 * servicio web ph core negocio
	 * @param cedula
	 * @return
	 * @throws PHEnTramiteException
	 */
	boolean tienePhEnTramite(String cedula) throws PHEnTramiteException;

}
