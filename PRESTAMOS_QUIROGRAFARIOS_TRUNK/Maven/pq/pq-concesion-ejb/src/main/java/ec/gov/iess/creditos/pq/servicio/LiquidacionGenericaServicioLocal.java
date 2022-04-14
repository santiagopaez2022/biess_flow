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

import javax.ejb.Local;

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

@Local
public interface LiquidacionGenericaServicioLocal {
	/**
	 * Calculo Tasa Interes
	 * 
	 * @param idtasaInteres
	 * @param fecha
	 * @return 	
	 * @author palvarez, cbastidas
	 */
    public Double calculoTasaInteres(String idtasaInteres, Date fecha) 
    throws TasaInteresExcepcion;


	/**
	 * calculo Liquidacion
	 * 
	 * @param pre
	 * @param tipoLiquidacion
	 * @return 	
	 * @author palvarez, cbastidas
	 */
    public CalculoLiquidacion calculoLiquidacion(Prestamo pre, TipoLiquidacion tipoLiquidacion)
	 throws CalculoLiquidacionExcepcion;

	/**
	 * Calculo Interes Mora
	 * 
	 * @param idtasaInteres
	 * @param fecha 
	 * @param valtotdiv 
	 * @return 	
	 * @author palvarez, cbastidas
	 */
	public Double calculoInteresMora(String idtasaInteres, Date fecha,Double valtotdiv) 
    throws CalculoInteresMoraException;

	/**
	 * Generar Liquidacion
	 * 
	 * @param pre
	 * @param tipoLiquidacion 
	 * @param estadoLiquidacion 
	 * @param estadoCredito 
	 * @param nuevoEstadoDividendo 
	 * @return 	
	 * @author palvarez, cbastidas
	 */
	public LiquidacionPrestamo generarLiquidacion(Prestamo pre,
			TipoLiquidacion tipoLiquidacion,
			EstadoLiquidacion estadoLiquidacion,
			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo) throws GenerarLiquidacionException ;
	
	/**
	 * Insertar Cabecera Liquidacion
	 * 
	 * @param pre
	 * @param tipoLiquidacion 
	 * @param estadoLiquidacion 
	 * @return 	
	 * @author palvarez, cbastidas
	 */
	public Long insertarCabeceraLiquidacion(Prestamo pre, String tipoLiquidacion,EstadoLiquidacion estadoLiquidacion)
	throws InsertarCabeceraLiquidacionException;
	
	/**
	 * Insertar Historico Liquidacion
	 * 
	 * @param numeroLiquidacion
	 * @param estadoLiquidacion 
	 * @param tipoLiquidacion 
	 * @return 	
	 * @author palvarez, cbastidas
	 */
	public void insertarHistoricoLiquidacion(Long numeroLiquidacion, String estadoLiquidacion,String tipoLiquidacion)
	throws InsertarHistoricoLiquidacionException;	
	
	/**
	 * Insertar Historico Liquidacion
	 * 
	 * @param prestamo
	 * @param numeroLiquidacion 
	 * @param tipoLiquidacion 
	 * @param estadoCredito 
	 * @param nuevoEstadoDividendo 
	 * @return 	
	 * @author palvarez, cbastidas
	 */
	public LiquidacionPrestamo insertarDetalleLiquidacion(Prestamo prestamo,
			Long numeroLiquidacion, TipoLiquidacion tipoLiquidacion,
			 EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo)
			throws InsertarDetalleLiquidacionException;
}
