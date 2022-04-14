/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.CuentaFondosDao;
import ec.gov.iess.creditos.modelo.persistencia.CuentaFondos;
import ec.gov.iess.dao.ejb.GenericEjbDao;


/** 
 * <b>
 * Implementacion del Objeto Dao para acceder a la capa de persistencia CuentaFondos.
 * </b>
 *  
 * @author Gabriel Eguiguren
 * @version $Revision: 1.3 $ <p>[$Author: etarambis $, $Date: 2010/12/20 19:40:12 $]</p>
*/ 
@Stateless
public class CuentaFondosDaoImpl extends GenericEjbDao<CuentaFondos, Long> implements CuentaFondosDao {

	public CuentaFondosDaoImpl() {
		super(CuentaFondos.class);
	}

	@SuppressWarnings("unchecked")
	public List<CuentaFondos> consultarListado() {
		List<CuentaFondos> lista = new ArrayList<CuentaFondos>();

		Query q = em.createNamedQuery("CuentaFondos.consultarListado");
		lista = q.getResultList();
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.CuentaFondosDao#obtenerCuentasPH(java.lang.String
	 * , java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CuentaFondos> obtenerCuentasPH(String estado, String codCatalogo, List<String> codCuentaPH) {
		Query q = em.createNamedQuery("CuentaFondos.obtenerCuentasPH");
		q.setParameter("estado", estado);
		q.setParameter("catalogo", codCatalogo);
		q.setParameter("codCuenta", codCuentaPH);
		List<CuentaFondos> lista = q.getResultList();
		if (lista == null || lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * @see ec.gov.iess.creditos.dao.CuentaFondosDao#obtenerPoruentaBanco(java.lang.String)
	 */
	public CuentaFondos obtenerPorCuentaBanco(String cuenta) {
		Query q = em.createNamedQuery("CuentaFondos.consultarPorCuenta");
		q.setParameter("cuentaBanco", cuenta);
		return (CuentaFondos) q.getSingleResult();
	}

}
