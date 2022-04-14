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

import java.util.Date;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.AfiliadoExceptionBiess;
import ec.fin.biess.creditos.pq.excepcion.EmailException;
import ec.gov.iess.hl.modelo.Afiliado;

/**
 * Servicio para afiliados.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
@Local
public interface AfiliadoServicioBiess {

	/**
	 * Metodo que actualiza los datos del afiliado.
	 * 
	 * @param cedula
	 * @param divisionPolitica
	 * @param direccion
	 * @param telefonoDomicilio
	 * @param numeroCelular
	 * @param email
	 * @param poseeVivienda
	 * @param fechaActualizacionDatosDomicilio
	 * @param ipUser
	 * @throws AfiliadoExceptionBiess
	 */
	public void actualizarDatosAfiliado(String cedula, String divisionPolitica, String direccion, String telefonoDomicilio, String numeroCelular, String email,
			Boolean poseeVivienda, Date fechaActualizacionDatosDomicilio, String ipUser) throws AfiliadoExceptionBiess;

	/**
	 * Metodo para actualizar email y celular del afiliado.
	 * 
	 * @param afiliado
	 * @param ipUser
	 * @throws EmailException
	 */
	public void reActualizarDatosAfiliado(Afiliado afiliado, String ipUser) throws EmailException;
	
}
