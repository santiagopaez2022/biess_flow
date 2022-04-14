/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CatalogoPrestacionSeguro;
import ec.gov.iess.dao.GenericDao;

/**
 * <b> Interface para métodos de prestaciones seguros. </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 15/07/2011 $]
 *          </p>
 */
@Local
public interface CatalogoPrestacionSeguroDao extends GenericDao<CatalogoPrestacionSeguro, Long> {

	/**
	 * 
	 * <b> Método para obtener los códigos de prestaciones por seguro. </b>
	 * <p>
	 * [Author: jsanchez, Date: 15/07/2011]
	 * </p>
	 * 
	 * @param prestacion
	 *            tipo de prestación
	 * @param seguro
	 *            tipo de seguro
	 * @param seguroAlt
	 *            tipo de seguro alternativo
	 * @return lista de catálogos por prestación y seguros.
	 */
	public List<CatalogoPrestacionSeguro> obtenerPorPrestacionSeguros(String prestacion, String seguro, String seguroAlt);

}
