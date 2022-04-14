package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.Catalogo;

@Local
public interface CatalogoServicio {
	
	/**
	 * Obtiene el listado de las de todos los posibles opciones de credito
	 * 
	 * @return
	 */
	public List<Catalogo> getAll();

	/**
	 * Metodo que obtiene la lista de CatalogoDestino dado su codigo
	 * 
	 * @param codigoTipoProducto
	 *            Codigo del TipoProducto
	 * @return Lista de {@link CatalogoDestino}
	 */
	List<Catalogo> getListaCatalogoDestinoPorCodigoTipoProducto(
			Long codigoTipoProducto);
}
