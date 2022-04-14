/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.dao.GenericDao;
import ec.gov.iess.creditos.modelo.persistencia.DatosAfiliado;

/**
 * <b> Clase de acceso a cargas Familiares, para PH y PQ </b>
 * 
 * @author Ronald Barrera
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Ronald Barrera $, $Date: 12/07/2011 $]
 *          </p>
 */
@Local
public interface DatosAfiliadoDao extends GenericDao<DatosAfiliado, String> {
	/**
	 * <b> Devuelve el numero de cargas familiares del afiliado. </b>
	 * <p>
	 * [Author: Ron@ld Barrer@ rbar.ec@gmail.com, Date: 13/07/2011]
	 * </p>
	 * 
	 * @param pCedula
	 * @return
	 * @throws DatosAfiliadoException
	 */
	public DatosAfiliado obtenerCargaFamiliar(String pCedula);

	/**
	 * Verifica si una persona es ama de casa dada la cédula
	 * 
	 * @param pCedula
	 * @return
	 */
	public boolean esTrabajadorHogar(String pCedula);

}
