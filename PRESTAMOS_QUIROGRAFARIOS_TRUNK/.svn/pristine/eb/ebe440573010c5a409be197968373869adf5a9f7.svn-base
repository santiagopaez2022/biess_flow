/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.TipoRolSolicitante;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal 03/10/2011
 * 
 */
@Local
public interface TipoRolSolicitanteDao extends GenericDao<TipoRolSolicitante, Long> {

	/**
	 * 
	 * <b> Método para obtener lista de roles de solicitante de PH. </b>
	 * <p>
	 * [Author: jsanchez, Date: 02/08/2011]
	 * </p>
	 * 
	 * @param listaProductos
	 *            lista de tipos de productos
	 * @return lista de roles del solicitante
	 */
	public List<TipoRolSolicitante> obtenerListaRolesSolicistante(List<Long> listaProductos);
}
