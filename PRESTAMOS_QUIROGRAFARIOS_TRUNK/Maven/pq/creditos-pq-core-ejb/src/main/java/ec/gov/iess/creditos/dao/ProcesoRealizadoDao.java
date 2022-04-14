/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.ProcesoRealizado;
import ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface ProcesoRealizadoDao extends GenericDao<ProcesoRealizado, Long> {

	/**
	 * Crea un nuevo proceso realizado
	 * 
	 * @param procesoRealizado
	 */
	public void insertarNuevoProcesoRealizado(ProcesoRealizado procesoRealizado);

	/**
	 * Consulta los procesos realizados a una solicitud
	 * 
	 * @param solicitudCreditoPK
	 *            clave primaria de la solicitud
	 * @return una lista de procesos realizados
	 */
	public List<ProcesoRealizado> getProcesoRealizadoSolicitud(
			SolicitudCreditoPK solicitudCreditoPK);

}
