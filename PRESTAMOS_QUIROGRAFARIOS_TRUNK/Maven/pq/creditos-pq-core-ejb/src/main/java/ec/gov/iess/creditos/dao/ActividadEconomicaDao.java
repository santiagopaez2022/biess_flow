package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.ActividadEconomica;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * @author Daniel Cardenas
 * 
 */
@Local
public interface ActividadEconomicaDao extends
		GenericDao<ActividadEconomica, String> {

	/**
	 * Consulta la lista (de padres) de las actividades economicas disponibles,
	 * ordenadas alfabeticamente por su descripcion
	 * 
	 * @return una lista de {@link ActividadEconomica} ordenadas alfabeticamente
	 */
	public List<ActividadEconomica> getAllParentOrderByDescripcion();

	/**
	 * Consulta la lista de las actividades economicas disponibles dado un
	 * padre, ordenadas alfabeticamente por su descripcion
	 * 
	 * @param codigoPadre
	 * @return
	 */
	public List<ActividadEconomica> getAllOrderByDescripcion(String codigoPadre);

}
