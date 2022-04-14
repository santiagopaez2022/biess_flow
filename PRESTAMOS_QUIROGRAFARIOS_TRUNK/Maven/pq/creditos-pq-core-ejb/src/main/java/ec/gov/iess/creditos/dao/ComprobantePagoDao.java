/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.enumeracion.EstadoComprobantePago;
import ec.gov.iess.creditos.excepcion.DebitoAutomaticoExcepcion;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.pk.ComprobantePagoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.GenericDao;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 19, 2007]
 * @author pablo
 */
@Local
public interface ComprobantePagoDao extends GenericDao<ComprobantePago, ComprobantePagoPk> {

	public Long contarPorPrestamoTipoYEstado(PrestamoPk prestamoPk, String tipo,
			List<EstadoComprobantePago> listaEstados);

	public List<ComprobantePago> obtenerPorLiquidacionYEstados(Long numeroLiquidacion,
			List<EstadoComprobantePago> estadosComprobante);

	/**
	 * Cuenta el numero de comprobantes de un prestamo con un tipo y estado especificos
	 * @param prestamoPk clave primaria del prestamo
	 * @param estadosPrestamo lista de estados del prestamo
	 * @param tiposComprobante lista de tipos de comprobante
	 * @param estadosComprobante lista de estados del comprobante
	 * @return
	 */
	public Long contarPorPrestamoEstadoPTipoEstado(PrestamoPk prestamoPk, List<String> estadosPrestamo,
			List<String> tiposComprobante, List<EstadoComprobantePago> estadosComprobante);
	
	public Long contarPorAfiliadoEstadoPTipoEstado(String numeroAfiliado, List<String> estadosPrestamo,
			List<String> tiposComprobante, List<EstadoComprobantePago> estadosComprobante);

	/**
	 * Devuelve los comprobantes de un prestamo con un tipo y estado especificos
	 * @param prestamoPk clave primaria del prestamo
	 * @param estadosPrestamo lista de estados del prestamo
	 * @param tiposComprobante lista de tipos de comprobante
	 * @param estadosComprobante lista de estados del comprobante
	 * @return
	 */
	public List<ComprobantePago> obtenerPorPrestamoEstadoPTipoEstado(PrestamoPk prestamoPk, List<String> estadosPrestamo,
			List<String> tiposComprobante, List<EstadoComprobantePago> estadosComprobante);
	
	public List<ComprobantePago> obtenerPorAfiliadoEstadoPTipoEstado(String numeroAfiliado,
			List<String> estadosPrestamo, List<String> tiposComprobante, List<EstadoComprobantePago> estadosComprobante);

/**
	 * Permite obtener el comprobante de pago por nut, cedula
	 * @param nut numero unico de la transaccion
	 * @param cedula identificacion del afiliado
	 * @return
	 */
	boolean validarComprobantePorEstadoEMI(Long nut, String cedula);
	
	/**
	 * Permite validar un comprobante de pago por nut,cedula y estados.
	 * @param nut
	 * @param cedula
	 * @param estadosComprobante
	 * @return
	 */
	boolean validarComprobantePorEstados(Long nut, String cedula,List<String> estadosComprobante);
	
	
	/**
	 * Obtener comprobante de pago por identificacion y lista de estados.
	 * @param identificacion
	 * @param estadosComprobante
	 * @return
	 */
	public List<ComprobantePago> obtenerComprobantePendPago(String identificacion,
			List<EstadoComprobantePago> estadosComprobante) ;
	
	/**
	 */
		Boolean consultarDebitoAutomaticoPeriodo(String identificacion, Long operacion, Long nut);
		/**
		 * REQ 444 N1
		 * @param idTransaccion
		 * @param nut
		 * @param cedula
		 * @param operacionSac
		 * @return
		 */
		public Boolean confirmarAfectacionTransaccion(final Long idTransaccion,final Long nut, final String cedula,final Long operacionSac);
		
		public  String consultarDebitoAutomatico (String identificacion, Long operacion, Long nut) throws DebitoAutomaticoExcepcion;
		
		public Boolean estadoBanderaDebito() throws DebitoAutomaticoExcepcion;
		
		public  Boolean consultaDebitoENV (String identificacion, Long operacion, Long nut) throws DebitoAutomaticoExcepcion;


	}
	
	

