/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.RegistroCivilExtranjero;
import ec.gov.iess.dao.GenericDao;

/**
 * Interfaz para la entidad RegistroCivilExtranjero.
 * 
 * @author diego.iza.
 * 
 * @version 1.0
 */
@Local
public interface RegistroCivilExtranjeroDao extends GenericDao<RegistroCivilExtranjero, String> {

	/**
	 * Obtienen datos de un refugiado/extranjero a partir de la cedula.
	 * 
	 * @param cedula
	 *            - cedula del refugiado/extranjero.
	 * @return List<RegistroCivilExtranjero>
	 */
	public List<RegistroCivilExtranjero> obtenerPorCedula(String cedula);

}
