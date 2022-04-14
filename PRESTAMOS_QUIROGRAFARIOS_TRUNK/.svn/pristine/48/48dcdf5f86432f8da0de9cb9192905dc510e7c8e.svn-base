package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.CatalogoDao;
import ec.gov.iess.creditos.modelo.persistencia.Catalogo;
import ec.gov.iess.creditos.pq.servicio.CatalogoServicio;
import ec.gov.iess.creditos.pq.servicio.CatalogoServicioRemote;


@Stateless
public class CatalogoServicioImpl implements CatalogoServicio,CatalogoServicioRemote{
	@EJB
	CatalogoDao catalogoDao;
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.ActividadEconomicaServicio#obtenerActividadesPadres()
	 */
	public List<Catalogo> getAll() {

		return catalogoDao.getAll();
	}
	
	/** 
	 * @see ec.gov.iess.creditos.pq.servicio.CatalogoServicio#
	 * getListaCatalogoDestinoPorCodigoTipoProducto(java.lang.Long)
	 */
	public List<Catalogo> getListaCatalogoDestinoPorCodigoTipoProducto(
			Long codigoTipoProducto) {
		return catalogoDao
				.getListaCatalogoDestinoPorCodigoTipoProducto(codigoTipoProducto);
	}

}
