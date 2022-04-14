/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.fin.biess.creditos.pq.excepcion.RegistroCivilExtranjeroException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.RegistroCivilExtranjeroDao;
import ec.gov.iess.creditos.modelo.persistencia.RegistroCivilExtranjero;
import ec.gov.iess.creditos.pq.servicio.RegistroCivilExtranjeroServicio;

/**
 * Interfaz para persistencia de beneficiarios de creditos quirografarios.
 * 
 * @author diego.iza.
 * 
 * @version 1.0
 */
@Stateless
public class RegistroCivilExtranjeroServicioImpl implements RegistroCivilExtranjeroServicio {

	private LoggerBiess log = LoggerBiess.getLogger(RegistroCivilExtranjeroServicioImpl.class);

	@EJB
	private RegistroCivilExtranjeroDao registroCivilExtranjeroDao;

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.RegistroCivilExtranjeroServicio#obtenerPorCedula(java.lang.String)
	 */
	public List<RegistroCivilExtranjero> obtenerPorCedula(String cedula) throws RegistroCivilExtranjeroException {
		try {
		return this.registroCivilExtranjeroDao.obtenerPorCedula(cedula);
		} catch (Exception e) {
			log.error(" No se pudo obtener informacion del documento de identificacion del refugiado/extranjero.", e);
			throw new RegistroCivilExtranjeroException(
					" No se pudo obtener informacion del documento de identificacion del refugiado/extranjero.");
		}
	}

}
