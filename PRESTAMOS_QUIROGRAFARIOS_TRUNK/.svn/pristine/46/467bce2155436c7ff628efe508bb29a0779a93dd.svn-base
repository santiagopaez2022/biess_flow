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

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.servicio.EstadoPrestamoServicio;
import ec.gov.iess.creditos.dao.EstadoPrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.EstadoPrestamo;

/**
 * Implementacion para persistencia de estados de prestamos.
 * 
 * @author diego.iza.
 * 
 * @version 1.0
 */
@Stateless
public class EstadoPrestamoServicioImpl implements EstadoPrestamoServicio {

	@EJB
	private EstadoPrestamoDao estadoPrestamoDao;

	/**
	 * @see ec.fin.biess.creditos.pq.servicio.EstadoPrestamoServicio#obtenerPorCodigo
	 *      (java.lang.String)
	 */
	public EstadoPrestamo obtenerPorCodigo(String codigoEstado) {
		return estadoPrestamoDao.load(codigoEstado);
	}

}