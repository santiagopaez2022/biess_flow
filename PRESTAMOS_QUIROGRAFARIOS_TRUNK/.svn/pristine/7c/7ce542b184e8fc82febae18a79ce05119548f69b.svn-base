/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.CatalogoTipoBienDao;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoTipoBien;
import ec.gov.iess.creditos.pq.servicio.CatalogoTipoBienServicio;

/**
 * Implementación de métodos del servicio de catalogo de tipo bien
 * 
 * @author jsanchez
 * 
 */
@Stateless
public class CatalogoTipoBienServicioImpl implements CatalogoTipoBienServicio {

	@EJB
	private CatalogoTipoBienDao catalogoTipoBienDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CatalogoTipoBienServicio#obtenerPorTipoSolicitudSer(java.lang.Long)
	 */
	public List<CatalogoTipoBien> obtenerPorTipoSolicitudSer(Long idTipoSol) {
		return catalogoTipoBienDao.obtenerPorTipoSolSer(idTipoSol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CatalogoTipoBienServicio#obtenerPorId(java.lang.Long)
	 */
	public CatalogoTipoBien obtenerPorTipoSolicitudCodigoBien(Long tipoSolSer,
			String codigoBien) {
		return catalogoTipoBienDao.obtenerPorTipoSolSerCodigoBien(tipoSolSer,
				codigoBien);
	}

}
