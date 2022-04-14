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

import java.math.BigDecimal;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;

/**
 * Interface que implementa los métodos para consultar parametros de la tabla KSCRETCREPOL
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 *
 */
@Local
public interface ParametroCreditoServicio {

	/**
	 * Método que obtiene el valor del parametro SBU
	 * 
	 * @return BigDecimal
	 * @throws ParametroCreditoException
	 */
	public BigDecimal obtenerValorSBU() throws ParametroCreditoException;

	/**
	 * Método que obtiene el valor del parametro NUMSBU
	 * 
	 * @return BigDecimal
	 * @throws ParametroCreditoException
	 */
	public BigDecimal obtenerValorNUMSBU() throws ParametroCreditoException;

	/**
	 * Devuelve el valor de parametros para parametrizacion de tasas de interes y montos maximos para concesion de
	 * credito
	 * 
	 * @param parametro
	 * @return
	 * @throws ParametroCreditoException
	 */
	BigDecimal obtenerParametroPQMontosMaximos(String parametro) throws ParametroCreditoException;

}
