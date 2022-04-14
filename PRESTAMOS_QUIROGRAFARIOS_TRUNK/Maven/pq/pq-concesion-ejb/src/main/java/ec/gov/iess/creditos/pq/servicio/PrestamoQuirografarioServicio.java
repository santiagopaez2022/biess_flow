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

import java.math.BigDecimal;
import java.util.Calendar;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.BeneficiarioCreditoExcepcion;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.excepcion.CabeceraCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.CrearCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.RecalculoAmortizacionException;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaBloqueadaException;
/**
 * Implementacion Ejb para la Prestamo Quirografario Servicio
 * 
 * @version 1.0
 * @author palvarez, cbastidas
 */
@Local
public interface PrestamoQuirografarioServicio {

	/**
	 * 
	 * crea todo el credito quirografario ¡AL FIN!
	 * 
	 * @param credito
	 * @return
	 * @throws CrearCreditoQuirografarioException
	 * @throws SecuenciaBloqueadaException
	 * @throws BeneficiarioCreditoExcepcion
	 * @throws CabeceraCreditoQuirografarioException
	 */
	public Prestamo crearCreditoQuirografario(DatosCredito credito) throws CrearCreditoQuirografarioException, SecuenciaBloqueadaException,
			BeneficiarioCreditoExcepcion, CabeceraCreditoQuirografarioException;
	
	/**
	 * Recorre un listado de Préstamos en los cuales se van a reajustar la tabla de amortización
	 * @author dolaya
	 * */
	 
	 public void reajustarListadoPrestamos()throws RecalculoAmortizacionException;
	 
	/**
	 * Permite obtener la fecha habil
	 * 
	 * @param aumento dias aumentados a la fecha actual
	 * @return
	 */
	Calendar traerDiaHabil(int aumento);
	 
	/**
	 * Se consulta la tabla KSAFITCESANTIAS con la cedula del cliente,
	 * 
	 * @param identificacion
	 * @return retorna null si se produjo un error en la consulta
	 */
	GarantiaCesantia consultarCesantiaCliente(String identificacion);
	 
	/**
	 * Consulta el total de los fondos de reserva del cliente obtenida de la tabla
	 * APORTES_PFR2 la sumatoria del campo VALORSALDOCAPITAL
	 * 
	 * @param identificacion
	 * @return
	 */
	BigDecimal consultarFondosReservaTotales(String identificacion);
	
}