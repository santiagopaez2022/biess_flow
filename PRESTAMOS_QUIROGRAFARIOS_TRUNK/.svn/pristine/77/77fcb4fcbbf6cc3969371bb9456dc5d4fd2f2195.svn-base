package ec.fin.biess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.servicio.TipoCuentaBiessServicio;
import ec.gov.iess.creditos.dao.TipoCuentaBiessDao;
import ec.gov.iess.creditos.modelo.persistencia.TipoCuentaBiess;

@Stateless
public class TipoCuentaBiessServicioImpl implements TipoCuentaBiessServicio {
	@EJB
	TipoCuentaBiessDao tipoCuentaBiessDao;

	/**
	 * @see ec.fin.biess.creditos.pq.servicio.TipoCuentaBiessServicio#obtenerTodos
	 *      ()
	 */
	public List<TipoCuentaBiess> obtenerTodos() {
		return tipoCuentaBiessDao.obtenerTodos();
	}

	/**
	 * @see ec.fin.biess.creditos.pq.servicio.TipoCuentaBiessServicio#
	 *      obtenerPorCodigo(java.lang.String)
	 */
	public TipoCuentaBiess obtenerPorCodigo(String codigo) {
		return tipoCuentaBiessDao.obtenerPorCodigo(codigo);
	}

}
