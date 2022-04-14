/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.MontosMaximosException;
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.helper.simulacion.SimularCredito;

/**
 * 
 * Interface del servicio de simulacion del credito quirografario.
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@Local
public interface SimularCreditoServicio {

	/**
	 * Simula el credito en base a plazo, montos, cuota dependiento del tipo de
	 * simulacion.
	 * 
	 * @param tipoSimulacion
	 *            opcion de la simulacion ya sea por monto, por cuota, etc.
	 *            {@link SimularCredito}
	 * @param plazo
	 *            plazo en meses a simular
	 * @param monto
	 *            monto maximo del credito a simular
	 * @param cuota
	 *            cuota maxima del credito a simular
	 * @param plazoCreditoList
	 *            lista de la tabla de referncia de los montos y cuotas maximas
	 *            a comprometer
	 * @return un objeto de modelo {@link Simulacion} con los datos de la
	 *         simulacion
	 */
	public Simulacion simularCredito(int tipoSimulacion, int plazo,
			BigDecimal monto, BigDecimal cuota,
			List<PlazoCredito> plazoCreditoList,BigDecimal tasaInteres);
	
		
	/**
	 * Metodo que simula un credito segun el monto de credito que se ingreso
	 * 
	 * @param opcionCredito valores necesarios para realizar los calculos para la cuota
	 * @return Simulacion
	 * @author diana.suasnavas
	 * @throws MontosMaximosException 
	 */
	public Simulacion simularCreditoSegunMontoIngresado(OpcionCredito opcionCredito) throws MontosMaximosException;
	
	/**
	 * Metodo que simula un credito segun la cuota que se ingresa para obtener el monto
	 *  
	 * @param opcionCredito valores necesarios para realizar los calculos para el monto
	 * @return Simulacion
	 * @author diana.suasnavas
	 * @throws MontosMaximosException 
	 */
	public Simulacion simularCreditoSegunCuotaIngresada(OpcionCredito opcionCredito) throws MontosMaximosException;
	
	
	/**
	 * Metodo que simula cuota maxima segun el tpo de tabla seleccionado
	 * @param montoCredito
	 * @param tasaInteres
	 * @param numeroMeses
	 * @param tipoTabla
	 * @return cuota maxima
	 */
	public BigDecimal obtenerCuotaMaximaPorTipoTabla(BigDecimal montoCredito, BigDecimal tasaInteres, int numeroMeses, String tipoTabla);

	/**
	 * 
	 * @param opcionCredito
	 * @param calculoCreditoServicio
	 * @param cliente
	 * @param operacionSac
	 * @autor luis.avila
	 * @return
	 * @throws MontosMaximosException
	 */
	public Simulacion simularCreditoSegunMontoIngresadoSac(OpcionCredito opcionCredito,CalculoCreditoServicio calculoCreditoServicio,ClienteRequestDto cliente, Long operacionSac ) throws MontosMaximosException;

}
