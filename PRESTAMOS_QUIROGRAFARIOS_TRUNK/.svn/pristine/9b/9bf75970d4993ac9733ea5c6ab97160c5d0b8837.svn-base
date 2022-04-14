/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.CrearDetalleSolicitudException;
import ec.gov.iess.creditos.modelo.persistencia.DetalleSolicitud;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface DetalleSolicitudDao extends GenericDao<DetalleSolicitud, Long> {

	/**
	 * Crea el detalle de la solicitud
	 * 
	 * @param detalleSolicitudList
	 * @throws CrearDetalleSolicitudException
	 */
	public void crearDetalleSolicitud(
			List<DetalleSolicitud> detalleSolicitudList)
			throws CrearDetalleSolicitudException;

	/**
	 * Consulta por la fecha de solicitud de pago
	 * 
	 * @param desde
	 * @param hasta
	 * @return
	 */
	public List<DetalleSolicitud> consultarPorFechaSolicitudPago(Date desde,
			Date hasta);

	/**
	 * Método para obtener el detalle de la solicitud por Cédula y/o Nut
	 * 
	 * @param cedula
	 * @param nut
	 * @return detalle solicitud
	 */
	public DetalleSolicitud obtenerPorCedulaNut(String cedula, String nut,
			List<Long> tipoSolicitud, List<String> listaEstado);
}