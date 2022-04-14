/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.ActividadEconomicaDao;
import ec.gov.iess.creditos.modelo.persistencia.ActividadEconomica;
import ec.gov.iess.creditos.pq.servicio.ActividadEconomicaServicio;
import ec.gov.iess.creditos.pq.servicio.ActividadEconomicaServicioRemote;

/**
 * @author daniel
 * 
 */
@Stateless
public class ActividadEconomicaServicioImpl implements
		ActividadEconomicaServicio, ActividadEconomicaServicioRemote {

	@EJB
	ActividadEconomicaDao actividadEconomicaDao;

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.ActividadEconomicaServicio#obtenerActividadesPadres()
	 */
	public List<ActividadEconomica> obtenerActividadesPadres() {

		return actividadEconomicaDao.getAllParentOrderByDescripcion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.ActividadEconomicaServicio#obtenerActividades(java.lang.String)
	 */
	public List<ActividadEconomica> obtenerActividades(String codigoPadre) {
		return actividadEconomicaDao.getAllOrderByDescripcion(codigoPadre);
	}
}