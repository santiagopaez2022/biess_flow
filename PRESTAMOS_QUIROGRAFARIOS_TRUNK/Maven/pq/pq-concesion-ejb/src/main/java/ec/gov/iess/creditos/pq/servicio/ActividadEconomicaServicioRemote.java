/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.persistencia.ActividadEconomica;

/**
 * @author Daniel Cardenas
 * 
 */
@Remote
public interface ActividadEconomicaServicioRemote {

	/**
	 * Obtiene el listado de las actividades economicas padres
	 * 
	 * @return
	 */
	List<ActividadEconomica> obtenerActividadesPadres();

	/**
	 * Obtiene el listado de las actividades economicas de un padre especifico
	 * 
	 * @param codigoPadre
	 * @return
	 */
	List<ActividadEconomica> obtenerActividades(String codigoPadre);
}
