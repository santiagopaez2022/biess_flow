/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CuentaFondos;
import ec.gov.iess.dao.GenericDao;

/**
 * <b> Objeto Dao para acceder a la capa de persistencia. </b>
 * 
 * @author Gabriel Eguiguren
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: etarambis $, $Date: 2010/12/20 16:55:41 $]
 *          </p>
 */
@Local
public interface CuentaFondosDao extends GenericDao<CuentaFondos, Long> {

	/**
	 * 
	 * <b> Método para obtener listado de cuentas </b>
	 * <p>
	 * [Author: geguiguren $, $Date: 2010/12/17 22:02:18]
	 * </p>
	 * 
	 * @return
	 */
	public List<CuentaFondos> consultarListado();

	/**
	 * <b> Método para obtener las cuentas que le pertenecen a PH para
	 * desembolsos </b>
	 * <p>
	 * [Author: Jenny Sanchez, Date: 20/12/2010]
	 * </p>
	 * 
	 * @param estado
	 * @param codCatalogo
	 * @param codCuentaPH
	 * @return
	 */
	public List<CuentaFondos> obtenerCuentasPH(String estado, String codCatalogo, List<String> codCuentaPH);
	
	/**
	 * 
	 * @param cuenta
	 * @return
	 */
	public CuentaFondos obtenerPorCuentaBanco(String cuenta);
}