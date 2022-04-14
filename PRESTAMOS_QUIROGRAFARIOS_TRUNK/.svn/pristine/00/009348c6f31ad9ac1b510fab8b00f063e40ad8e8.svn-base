/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.ListaVaciaException;
import ec.gov.iess.creditos.modelo.persistencia.DatoProyectoConstructor;
import ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK;
import ec.gov.iess.dao.GenericDao;

/**
 * @author jsanchez
 * 
 */
@Local
public interface DatoProyectoConstructorDao extends GenericDao<DatoProyectoConstructor, Long> {

	/**
	 * Método que crea el registro de Datos del proyecto y devuelve el objeto
	 * nuevo
	 * 
	 * @param datoProyecto
	 * @return
	 * @throws ListaVaciaException
	 */
	public DatoProyectoConstructor registrar(DatoProyectoConstructor datoProyecto) throws ListaVaciaException;

	/**
	 * Método para obtener los datos del proyecto relacionados con la solicitud
	 * 
	 * @param pk
	 * @return
	 */
	public List<DatoProyectoConstructor> obtenerDatosPorSolicitud(SolicitudCreditoPK pk);
}
