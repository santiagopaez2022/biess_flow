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

import ec.fin.biess.creditos.pq.excepcion.RegistroCivilBiessException;
import ec.fin.biess.enumeraciones.AplicativoEnum;

/**
 * Interface que brinda funcionalidad del web service del registro civil.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
@Local
public interface RegistroCivilBiessServicio {

	/**
	 * Metodo que obtiene informacion de una persona desde el Registro Civil, dado su numero de cedula.
	 * 
	 * @param cedula
	 * @param cedulaFuncionario
	 * @param direccionIP
	 * @param aplicativo
	 * @return String Trama XML con información de la persona
	 * @throws RegistroCivilBiessException
	 */
	public String consultarWS(String cedula, String cedulaFuncionario, String direccionIP, AplicativoEnum aplicativo)
			throws RegistroCivilBiessException;

	/**
	 * Metodo que obtiene el valor de un campo especifico dada la trama xml retornada por el ws del Registro Civil.
	 * 
	 * @param trama
	 * @param campo
	 * @return String Valor del campo especifico
	 * @throws RegistroCivilBiessException
	 */
	public String obtenerCampo(String trama, String campo) throws RegistroCivilBiessException;

}
