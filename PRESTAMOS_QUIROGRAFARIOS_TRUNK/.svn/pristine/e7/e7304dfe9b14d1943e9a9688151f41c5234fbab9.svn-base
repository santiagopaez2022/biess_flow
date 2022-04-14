/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CatalogoTipoBien;
import ec.gov.iess.dao.GenericDao;

/**
 * Interface para métodos de catálogos de tipo de bien
 * 
 * @author jsanchez
 * 
 */
@Local
public interface CatalogoTipoBienDao extends GenericDao<CatalogoTipoBien, Long> {

	/**
	 * Método para obtener el catálogo de acuerdo al tipo de solicitud
	 * 
	 * @param tipo
	 *            solicitud
	 * @return lista de catalogos
	 */
	public List<CatalogoTipoBien> obtenerPorTipoSolSer(Long tipoSolSer);

	/**
	 * Método para obtener el catálogo por id del tipo de
	 * solicitud y por el código del bien
	 * 
	 * @param id
	 * @param codigo del bien
	 * @return catalogo
	 */
	public CatalogoTipoBien obtenerPorTipoSolSerCodigoBien(Long tipoSolSer,
			String codigoBien);
}
