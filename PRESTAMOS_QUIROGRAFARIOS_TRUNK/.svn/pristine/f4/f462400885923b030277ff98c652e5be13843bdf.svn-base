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

import ec.fin.biess.creditos.pq.dao.PrestacionesBiessDao;
import ec.fin.biess.creditos.pq.servicio.PrestacionesBiessServicio;
import ec.gov.iess.servicio.pensiones.dao.PrestacionDao;

/**
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
@Stateless
public class PrestacionesBiessServicioImpl implements PrestacionesBiessServicio {

	@EJB
	PrestacionesBiessDao prestacionesBiessDao;

	@EJB
	PrestacionDao prestacionesPensionesDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.PrestacionesBiessServicio#getListaPrestaciones(java.lang.String,
	 * java.util.List)
	 */
	// @Deprecated
	// public List<Prestacion> getListaPrestaciones(String cedula, List<TipoPrestacionSeguro> listaTipoPrestacionSeguro)
	// {
	// List<Prestacion> listaPrestacionesHOST = new ArrayList<Prestacion>();
	// List<Prestacion> listaPrestacionesHL = new ArrayList<Prestacion>();
	// List<Prestacion> listaPrestaciones = new ArrayList<Prestacion>();
	// List<Prestacion> listaPrestacionesRetorno = new ArrayList<Prestacion>();
	// try {
	// listaPrestacionesHL = prestacionesBiessDao.consultarPrestacionesHL(cedula);
	// listaPrestacionesHOST = prestacionesPensionesDao.getPrestacionesHost(cedula);
	// listaPrestaciones.addAll(listaPrestacionesHL);
	// listaPrestaciones.addAll(listaPrestacionesHOST);
	// if (!listaPrestaciones.isEmpty() && !listaTipoPrestacionSeguro.isEmpty()) {
	// listaPrestacionesRetorno = new ArrayList<Prestacion>();
	// for (Prestacion prestacion : listaPrestaciones) {
	// for (TipoPrestacionSeguro tipoPrestacionSeguro : listaTipoPrestacionSeguro) {
	// if (prestacion.getTipoSeguro().equals(tipoPrestacionSeguro.getTipoSeguro())
	// && prestacion.getTipoPrestacion().equals(tipoPrestacionSeguro.getTipoPrestacion())) {
	// listaPrestacionesRetorno.add(prestacion);
	// }
	// }
	// }
	// }
	//
	// return (listaPrestacionesRetorno);
	// } catch (Exception e) {
	// return new ArrayList<Prestacion>();
	// }
	// }

	/**
	 * Metodo que obtiene la lista de prestaciones de un jubilado por origen.
	 * 
	 * @param cedula
	 * @param origen
	 * @return List<Prestacion>
	 */
	// @Deprecated
	// private List<Prestacion> getListaPrestacionPorOrigen(String cedula, TipoSistemaPrestacionesConstante origen) {
	// List<Prestacion> listaPrestaciones = new ArrayList<Prestacion>();
	// if (TipoSistemaPrestacionesConstante.SISTEMA_HISTORIA_LABORAL.equals(origen)) {
	// listaPrestaciones.addAll(prestacionesBiessDao.consultarPrestacionesHL(cedula));
	// } else {
	// listaPrestaciones.addAll(prestacionesPensionesDao.getPrestacionePorHostOrigen(cedula, origen.getValor()));
	// }
	//
	// return listaPrestaciones;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.PrestacionesBiessServicio#getListaPrestacionPorOrigen(java.lang.String,
	 * ec.gov.iess.pensiones.constantes.TipoSistemaPrestacionesConstante, java.util.List)
	 */
	// @Deprecated
	// public List<Prestacion> getListaPrestacionPorOrigen(String cedula, TipoSistemaPrestacionesConstante origen,
	// List<TipoPrestacionSeguro> listaTipoPrestacionSeguro) {
	// List<Prestacion> listaPrestacionesARetornar = new ArrayList<Prestacion>();
	// List<Prestacion> prestaciones = this.getListaPrestacionPorOrigen(cedula, origen);
	// // Se filtran las pretaciones segun el listado listaTipoPrestacionSeguro
	// for (Prestacion prestacion : prestaciones) {
	// for (TipoPrestacionSeguro tipoPrestacionSeguro : listaTipoPrestacionSeguro) {
	// if (prestacion.getTipoSeguro().equals(tipoPrestacionSeguro.getTipoSeguro())
	// && prestacion.getTipoPrestacion().equals(tipoPrestacionSeguro.getTipoPrestacion())) {
	// listaPrestacionesARetornar.add(prestacion);
	// }
	// }
	// }
	//
	// return listaPrestacionesARetornar;
	// }

}
