/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CatalogoTipoPrestacion;
import ec.gov.iess.dao.GenericDao;

/**
 * <b> Interface para incluir métodos para catálogos de tipo de prestación de
 * jubilados. </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 14/07/2011 $]
 *          </p>
 */
@Local
public interface CatalogoTipoPrestacionDao extends GenericDao<CatalogoTipoPrestacion, Long> {

	/**
	 * 
	 * <b> Método para obtener los tipos de prestaciones activas para jubilados.
	 * </b>
	 * <p>
	 * [Author: jsanchez, Date: 14/07/2011]
	 * </p>
	 * 
	 * @return
	 */
	public List<CatalogoTipoPrestacion> obtenerTiposPrestacionActivos();

}
