/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CatalogoTipoSeguro;
import ec.gov.iess.dao.GenericDao;

/**
 * <b> Interface para métodos de tipos de seguros de jubilados PH. </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 14/07/2011 $]
 *          </p>
 */
@Local
public interface CatalogoTipoSeguroDao extends GenericDao<CatalogoTipoSeguro, Long> {

	/**
	 * 
	 * <b> Método para obtener los tipos de seguros activos para jubilados. </b>
	 * <p>
	 * [Author: jsanchez, Date: 14/07/2011]
	 * </p>
	 * 
	 * @return
	 */
	public List<CatalogoTipoSeguro> obtenerTiposSeguroActivos();

}
