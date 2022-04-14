package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.Catalogo;
import ec.gov.iess.dao.GenericDao;

/**
 * @author Ricardo Tituaña 03/10/2011
 * 
 */

@Local
public interface CatalogoDao extends GenericDao<Catalogo,Long>{
	
	public List<Catalogo> getAll();

	/**
	 * Metodo que obtiene la lista de codigoTipoProducto dado su codigo
	 * 
	 * @param codigoTipoProducto
	 *            Codigo del producto
	 * @return Lista de {@link CatalogoDestino}
	 */
	List<Catalogo> getListaCatalogoDestinoPorCodigoTipoProducto(Long codigoTipoProducto);
}
