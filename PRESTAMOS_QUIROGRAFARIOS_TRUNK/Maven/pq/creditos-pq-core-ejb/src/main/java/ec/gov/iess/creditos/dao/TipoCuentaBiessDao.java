package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.TipoCuentaBiess;
import ec.gov.iess.dao.GenericDao;

/**
 * @author Ricardo Tituaña 03/10/2011
 * 
 */

@Local
public interface TipoCuentaBiessDao extends GenericDao<TipoCuentaBiess, Long> {

	/**
	 * Obtiene todos los tipos de cuentas.
	 * 
	 * @return List<TipoCuentaBiess>.
	 */
	public List<TipoCuentaBiess> obtenerTodos();

	/**
	 * Obtienen un tipo por codigo.
	 * 
	 * @param codigo
	 *            - codigo tipo cuenta.
	 * 
	 * @return TipoCuentaBiess.
	 */
	public TipoCuentaBiess obtenerPorCodigo(String codigo);

}
