/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.persistencia.DatosAfiliado;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Clase de acceso a cargas Familiares, para PH y PQ </b>
 * 
 * @author Ronald Barrera
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Ronald Barrera $, $Date: 12/07/2011 $]
 *          </p>
 */
@Stateless
public class DatosAfiliadoDaoImpl extends GenericEjbDao<DatosAfiliado, String> implements ec.gov.iess.creditos.dao.DatosAfiliadoDao {

	private static final LoggerBiess log = LoggerBiess.getLogger(DividendoPrestamoDaoImpl.class);

	public DatosAfiliadoDaoImpl() {
		super(DatosAfiliado.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DatosAfiliadoDao#obtenerCargaFamiliar(java.lang.String)
	 */
	public DatosAfiliado obtenerCargaFamiliar(String pCedula) {
		Query q = em.createNamedQuery("datosAfiliados.obtenerCargaFamiliar");
		q.setParameter("pCedula", pCedula);
		try {
			return (DatosAfiliado) q.getSingleResult();
		} catch (Exception e) {
			log.debug("Error al obtener la carga Familiar ");
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DatosAfiliadoDao#esTrabajadorHogar(java.lang.String)
	 */
	public boolean esTrabajadorHogar(String pCedula) {
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT c.codsec FROM frafitactces a ");
		consulta.append(" INNER JOIN kspcotempleadores b ON (a.rucempmejor = b.rucemp ");
		consulta.append(" AND a.cedula = ?) INNER JOIN kspcotemptip c ON (b.codtipemp = c.codtipemp)");
		
		Query query = em.createNativeQuery(consulta.toString());

		query.setParameter(1, pCedula);
		boolean resp = false;
		try {
			String ret = (String) query.getSingleResult();
			log.info("M".equals(ret) ? "ES AMA DE CASA " + pCedula : "NO ES AMA DE CASA " + pCedula);
			resp = "M".equals(ret);

		} catch (Exception e) {
			log.error("Error al verificar si afiliado " + pCedula + " es ama de casa ", e);
			resp = false;
		}
		return resp;
	}

}
