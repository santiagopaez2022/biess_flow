/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.dao;

import java.util.List;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.modelo.persistencia.DatosPersonalesAfiliadoBiess;
import ec.gov.iess.dao.GenericDao;

/**
 * DAO para la clase DatosPersonalesAfiliadoBiess
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Local
public interface DatosPersonalesAfiliadoBiessDao extends GenericDao<DatosPersonalesAfiliadoBiess, String> {

	/**
	 * Metodo que consulta los datos personales de un afiliado.
	 * 
	 * @param cedula
	 * @return List<DatosPersonalesAfiliadoBiess>
	 */
	public List<DatosPersonalesAfiliadoBiess> consultarPorCedula(String cedula);
	
	/**
	 * Metodo que actualiza el campo discapacitado de los datos personales de un afiliado.
	 * 
	 * @param cedula
	 * @param discapacitado
	 * @return int Numero de registros actualizados
	 */
	public int actualizarDiscapacitado(String cedula, boolean discapacitado);

}
