/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CatalogoTipoBien;

/**
 * Interface del servicio para catálogo de tipo de bien
 * 
 * @author jsanchez
 * 
 */
@Local
public interface CatalogoTipoBienServicio {

	/**
	 * Método para obtener la lista de catálogos de acuerdo al tipo de solicitud
	 * 
	 * @param id
	 *            de tipo de solicitud
	 * @return lista de catálogos
	 */
	public List<CatalogoTipoBien> obtenerPorTipoSolicitudSer(Long idTipoSol);

	/**
	 * Método para obtener el catálogo por el id de tipo de solicitud
	 * y el código del bien
	 * 
	 * @param id tipo solicitud
	 * @param codigo del bien
	 * @return catálogo
	 */
	public CatalogoTipoBien obtenerPorTipoSolicitudCodigoBien(Long tipoSolSer,
			String codigoBien);
}