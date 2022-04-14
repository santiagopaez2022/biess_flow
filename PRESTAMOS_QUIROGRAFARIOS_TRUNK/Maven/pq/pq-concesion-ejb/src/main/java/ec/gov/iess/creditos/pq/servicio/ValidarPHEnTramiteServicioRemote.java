package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.pq.excepcion.PHEnTramiteException;

@Remote
public interface ValidarPHEnTramiteServicioRemote {

	boolean tienePhEnTramite(String cedula) throws PHEnTramiteException;
}
