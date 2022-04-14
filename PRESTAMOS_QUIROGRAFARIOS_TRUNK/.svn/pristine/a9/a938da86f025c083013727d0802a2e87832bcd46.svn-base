/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.CrearDatoProyectoConstructorException;
import ec.gov.iess.creditos.modelo.persistencia.MiembroFideicomiso;
import ec.gov.iess.creditos.modelo.persistencia.pk.MiembroFideicomisoPk;
import ec.gov.iess.dao.GenericDao;

/**
 * @author jsanchez
 * 
 */
@Local
public interface MiembroFideicomisoDao extends GenericDao<MiembroFideicomiso, MiembroFideicomisoPk> {

	/**
	 * Método que obtiene el número de empleados de un ruc diferente al
	 * representante legal (empleador)
	 * 
	 * @param ruc
	 * @param cedulaEmpleador
	 * @return
	 */
	public Long obtenerNumeroEmpleadosPorRuc(String ruc, String cedulaEmpleador);

	/**
	 * Método para crear miembros del proyecto
	 * 
	 * @param miembro
	 * @return
	 * @throws CrearDatoProyectoConstructorException
	 */
	public MiembroFideicomiso crearMiembroProyecto(MiembroFideicomiso miembro)
			throws CrearDatoProyectoConstructorException;

	/**
	 * Método para obtener todos los miembros de un proyecto
	 * 
	 * @param codigo
	 * @return
	 */
	public List<MiembroFideicomiso> obtenerPorCoditoProyecto(Long codigo);
}
