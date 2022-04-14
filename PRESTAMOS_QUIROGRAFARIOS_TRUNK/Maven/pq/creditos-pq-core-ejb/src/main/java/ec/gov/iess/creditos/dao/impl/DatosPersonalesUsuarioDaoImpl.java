/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.DatosPersonalesUsuarioDao;
import ec.gov.iess.creditos.modelo.persistencia.DatosPersonalesUsuario;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 03/10/2011 $]
 *          </p>
 */
@Stateless
public class DatosPersonalesUsuarioDaoImpl extends GenericEjbDao<DatosPersonalesUsuario, Long>
		implements DatosPersonalesUsuarioDao {
	
	private static final LoggerBiess log = LoggerBiess.getLogger(DatosPersonalesUsuarioDaoImpl.class);

	public DatosPersonalesUsuarioDaoImpl() {
		super(DatosPersonalesUsuario.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.DatosPersonalesUsuarioDao#
	 * obtenerPorDetalleSolicitud(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<DatosPersonalesUsuario> obtenerPorDetalleSolicitud(Long codigoDetalle) {
		Query q = em.createNamedQuery("DatosPersonalesUsuario.obtenerPorDetalleSolicitud");
		q.setParameter("codigoDetalle", codigoDetalle);
		return q.getResultList();
	}

	
	/* (non-Javadoc)
	* @see ec.gov.iess.creditos.dao.DatosPersonalesUsuarioDao#listaGarantiasActivasdeSolicitante(java.lang.String, java.util.List)
	*/ 
	@SuppressWarnings("unchecked")
	public List<DatosPersonalesUsuario> listaGarantiasActivasdeSolicitante(String cedula, List<String> notEstados)
	throws Exception{
		List<DatosPersonalesUsuario> lista = null;
		Query q = em.createNamedQuery("DatosPersonalesUsuario.Obtener_cedula");
		q.setParameter("cedula", cedula);
		try{
			lista = q.getResultList();
		}catch(Exception e){
			log.error("No se puede Obtener Objetos relacionados a las Garantias");
			//retorno esto debido a que retornar null solo se da cuando NO HAY  garantías del Solicitante.
			//por lo tanto, por error se debe retornar algo diferente de null. 
			throw new Exception(e.toString());
		}
		if(lista != null && !lista.isEmpty()){
			for(DatosPersonalesUsuario das: lista){
				String estado = das.getDetalleSolicitudCredito().getSolicitudCredito().getCodestsolser();
				Integer acumulador=0;
				for(String estadoNegado: notEstados){
					//cuando es un ESTADO valido(APR,VIG, ...)  el acumulador se mantendra en CERO.
					if(estado.equalsIgnoreCase(estadoNegado)){
						acumulador++;
					}
				}
				if(acumulador == 0){
					return lista;
				}
			}
		}
		return null;
	}
}
