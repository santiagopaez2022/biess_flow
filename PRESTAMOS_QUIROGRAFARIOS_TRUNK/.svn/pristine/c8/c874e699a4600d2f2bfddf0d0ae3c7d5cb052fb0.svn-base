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

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiess;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiessPK;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.dao.GenericDao;

/**
 * DAO para la clase PrestramoBiess.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
@Local
public interface PrestamoBiessDao extends GenericDao<PrestamoBiess, PrestamoBiessPK> {

	/**
	 * Metodo que obtiene una lista de prestamos dado un rango determinado por la primera y la utlima fila.
	 * 
	 * @param cedula
	 * @param estadosCredito
	 * @param tiposCredito
	 * @param firstRow
	 * @param endRow
	 * @return
	 */
	public List<PrestamoBiess> getItemsByRange(String cedula, List<String> estadosCredito, List<Long> tiposCredito, int firstRow, int endRow);

	public List<PrestamoBiess> obtenerPrestamosCedulaEstadosTipoCredito(String cedula, List<String> estadosCredito, List<Long> tiposCredito);
	
	/**
	 * Metodo que obtiene el numero de prestamos de un afiliado dados los estados y tipos.
	 * 
	 * @param cedula
	 * @param estadosCredito
	 * @param tiposCredito
	 * @return int
	 */
	public int getRowCount(String cedula, List<String> estadosCredito, List<Long> tiposCredito);
	
	/**
	 * Metodo que obtiene lista de prestamos en estado de bloqueo de un asegurado.
	 * 
	 * @param cedula
	 * @param estadoBloqNormal
	 * @param estadoBloqNovacion
	 * @return List<PrestamoBiess>
	 */
	public List<PrestamoBiess> getPrestamosEstadoBloqueo (String cedula, String estadoBloqNormal, String estadoBloqNovacion);

	/**
	 * Metodo que actualiza el estado y fecha de un prestamo
	 * 
	 * @param prestamo
	 * @param codigoEstadoPrestamo
	 * @param fechaCancelacion
	 * @return
	 */
	boolean actualizarFechaCancelacionYEstado(Prestamo prestamo,
			String codigoEstadoPrestamo, Date fechaCancelacion);
	
	
	/**
	 * Consultar por numero de operacion sac y por identificacion
	 * @param numOperacionSAC
	 * @param identificacion
	 * @return
	 */
	public PrestamoBiess buscarPorOpIdent(Long numOperacionSAC,String identificacion);
}
