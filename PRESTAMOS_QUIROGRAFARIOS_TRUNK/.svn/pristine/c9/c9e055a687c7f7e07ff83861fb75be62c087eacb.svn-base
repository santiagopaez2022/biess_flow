/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.servicio.TipoPrestamoServicio;
import ec.gov.iess.creditos.dao.TipoPrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.TipoPrestamo;

/**
 * @author Edwin Maza
 * @version 1.0
 * 
 */
@Stateless
public class TipoPrestamoServicioImpl implements TipoPrestamoServicio {

	@EJB
	private transient TipoPrestamoDao TipoPrestamoDao;

	/**
	 * @see ec.fin.biess.creditos.pq.servicio.TipoPrestamoServicio#getListaTipoPrestamo
	 *      (java.lang.String)
	 */
	public List<TipoPrestamo> getListaTipoPrestamo(String codigoModulo) {
		return TipoPrestamoDao.getListaTipoPrestamoProducto(codigoModulo);
	}

	/**
	 * @see ec.fin.biess.creditos.pq.servicio.TipoPrestamoServicio#load(java.lang
	 *      .Long)
	 */
	public TipoPrestamo load(Long codigo) {
		return TipoPrestamoDao.load(codigo);
	}

	/**
	 * @see ec.fin.biess.creditos.pq.servicio.TipoPrestamoServicio#
	 *      obtenerTipoPrestamoPorIds(java.util.List)
	 */
	public List<TipoPrestamo> obtenerTipoPrestamoPorIds(List<Long> idsTipos) {
		return TipoPrestamoDao.obtenerTipoPrestamoPorIds(idsTipos);
	}

}
