/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package ec.gov.iess.creditos.pq.servicio;
import java.util.Date;

import javax.ejb.Remote;

import ec.gov.iess.creditos.enumeracion.EstadoCredito;
import ec.gov.iess.creditos.enumeracion.EstadoDividendoPrestamo;
import ec.gov.iess.creditos.enumeracion.EstadoLiquidacion;
import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.excepcion.TasaInteresExcepcion;
import ec.gov.iess.creditos.modelo.dto.CalculoLiquidacion;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.excepcion.CalculoInteresMoraException;
import ec.gov.iess.creditos.pq.excepcion.GenerarLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarCabeceraLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarDetalleLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarHistoricoLiquidacionException;
/**
 * Implementacion Ejb para la Liquidacion GenericaServicio 
 * 
 * @version 1.0
 * @author palvarez, cbastidas
 */
@Remote
public interface LiquidacionGenericaServicioRemote {

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#calculoTasaInteres(String idtasaInteres, Date fecha) 
	 *      
	 */
    public Double calculoTasaInteres(String idtasaInteres, Date fecha) 
    throws TasaInteresExcepcion;

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#calculoLiquidacion(Prestamo pre, TipoLiquidacion tipoLiquidacion)
	 *      
	 */
    public CalculoLiquidacion calculoLiquidacion(Prestamo pre, TipoLiquidacion tipoLiquidacion)
	 throws CalculoLiquidacionExcepcion;
	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#calculoInteresMora(String idtasaInteres, Date fecha,Double valtotdiv) 
	 *      
	 */
	public Double calculoInteresMora(String idtasaInteres, Date fecha,Double valtotdiv) 
    throws CalculoInteresMoraException;
	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#generarLiquidacion(Prestamo pre,
	 *			TipoLiquidacion tipoLiquidacion,
	 *			EstadoLiquidacion estadoLiquidacion,
	 *			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo)
	 *      
	 */
	public LiquidacionPrestamo generarLiquidacion(Prestamo pre,
			TipoLiquidacion tipoLiquidacion,
			EstadoLiquidacion estadoLiquidacion,
			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo) throws GenerarLiquidacionException ;
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#insertarCabeceraLiquidacion(Prestamo pre, String tipoLiquidacion,EstadoLiquidacion estadoLiquidacion)
	 *      
	 */
	public Long insertarCabeceraLiquidacion(Prestamo pre, String tipoLiquidacion,EstadoLiquidacion estadoLiquidacion)
	throws InsertarCabeceraLiquidacionException;
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#insertarHistoricoLiquidacion(Long numeroLiquidacion, String estadoLiquidacion,String tipoLiquidacion)
	 *      
	 */
	public void insertarHistoricoLiquidacion(Long numeroLiquidacion, String estadoLiquidacion,String tipoLiquidacion)
	throws InsertarHistoricoLiquidacionException;	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#insertarDetalleLiquidacion(Prestamo prestamo,
	 * 			Long numeroLiquidacion, TipoLiquidacion tipoLiquidacion
	 * 		, EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo)
	 *      
	 */
	public LiquidacionPrestamo insertarDetalleLiquidacion(Prestamo prestamo,
			Long numeroLiquidacion, TipoLiquidacion tipoLiquidacion
		, EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo)
			throws InsertarDetalleLiquidacionException;

}
