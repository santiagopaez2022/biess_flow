/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */

package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DetalleCatalogos;
import ec.gov.iess.creditos.modelo.persistencia.pk.DetalleCatalogosPK;
import ec.gov.iess.dao.GenericDao;

/**
 * <b> Clase para métodos de detalle de catálogos de fondos. </b>
 * 
 * @author Jenny Sanchez,pjarrin
 * @version $Revision: 1.4 $
 *          <p>
 *          [$Author: etarambis $, $Date: 2011/01/03 21:30:43 $]
 *          </p>
 */
@Local
public interface DetalleCatalogosDao extends
		GenericDao<DetalleCatalogos, DetalleCatalogosPK> {

	/**
	 * Comprueba si el parentezco es o no de beneficiario
	 * 
	 * @param parentezco
	 * @return
	 */
	boolean compruebaParentezcoBeneficiario(String parentezco);

	/**
	 * Encuentra el detalle catalogo por el catalogo y el codigo de
	 * detalleCatalogo
	 * 
	 * @param codigoCatalogo
	 * @param codigoDetalleCatalogo
	 * @return
	 */
	DetalleCatalogos encontrarDetalleCatalogo(String codigoCatalogo,
			String codigoDetalleCatalogo);
}
