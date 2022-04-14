/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DocumentacionAvaluo;
import ec.gov.iess.dao.GenericDao;

/**
 * Clase DAO para los métodos de DocumentacionAvaluo
 * 
 * @author jsanchez
 * 
 */
@Local
public interface DocumentacionAvaluoDao extends
		GenericDao<DocumentacionAvaluo, Long> {

	/**
	 * Método para obtener la documentación por código tipo solicitud
	 * 
	 * @param codigo
	 * @return lista de documentación para avaluo
	 */
	public List<DocumentacionAvaluo> obtenerPorTipoSolSer(Long codTipoSolSer);

	/**
	 * Método para obtener la documentación de acuerdo al tipo
	 * de solicitud y también al tipo de producto
	 */
	public List<DocumentacionAvaluo> obtenerPorTipoSolSerTipoProd(Long codTipoSolSer, String codTipoProd);
}
