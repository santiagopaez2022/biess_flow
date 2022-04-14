/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.AfiliadoDiscapacitadoException;
import ec.fin.biess.creditos.pq.excepcion.RegistroCivilBiessException;
import ec.fin.biess.creditos.pq.modelo.persistencia.DatosPersonalesAfiliadoBiess;
import ec.fin.biess.enumeraciones.AplicativoEnum;

/**
 * Servicio para DatosPersonalesAfiliado.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
@Local
public interface DatosPersonalesAfiliadoBiessServicio {

	/**
	 * Metodo que consulta los datos personales de un afiliado.
	 * 
	 * @param cedula
	 * @return DatosPersonalesAfiliadoBiess
	 */
	public DatosPersonalesAfiliadoBiess consultarPorCedula(String cedula);

	/**
	 * Metodo que consulta el numero de cargas familiares de un afiliado.
	 * 
	 * @param cedula
	 * @return Integer
	 */
	public Integer consultarNumeroCargas(String cedula);
	
	/**
	 * Metodo que actualiza el campo discapacitado de los datos personales de un afiliado.
	 * 
	 * @param cedula
	 * @param discapacitadoOp
	 * @param discapacitadoDb
	 * @param direccionIP
	 * @param aplicativo
	 * @throws AfiliadoDiscapacitadoException
	 * @throws RegistroCivilBiessException
	 */
	void actualizarDiscapacitado(String cedula, Boolean discapacitadoOp, Boolean discapacitadoDb, String direccionIP, AplicativoEnum aplicativo)
			throws AfiliadoDiscapacitadoException, RegistroCivilBiessException;

	/**
	 * Metodo que valida si el afiliado realmente es discapacitado consultado el WS del Registro Civil.
	 * 
	 * @param cedula
	 * @param direccionIP
	 * @param aplicativo
	 * @return boolean
	 * @throws RegistroCivilBiessException
	 */
	public boolean validarDiscapacitado(String cedula, String direccionIP, AplicativoEnum aplicativo) throws RegistroCivilBiessException;
	
	/**
	 * Metodo que consulta si un afiliado es discapacitado.
	 * 
	 * @param cedula
	 * @return Boolean
	 */
	public Boolean consultarDiscapacitado(String cedula);
	
	/**
	 * Metodo que actualiza una entidad de tipo DatosPersonalesAfiliadoBiess
	 * 
	 * @param dpab
	 */
	public void actualizar(DatosPersonalesAfiliadoBiess dpab);
	
	/**
	 * Metodo que crea una entidad de tipo DatosPersonalesAfiliadoBiess
	 * 
	 * @param dpab
	 */
	public void nuevo(DatosPersonalesAfiliadoBiess dpab);

	/**
	 * Mantiene activa la sesion entre cliente/servidor.
	 */
	public void mantenerSesionActiva();
}
