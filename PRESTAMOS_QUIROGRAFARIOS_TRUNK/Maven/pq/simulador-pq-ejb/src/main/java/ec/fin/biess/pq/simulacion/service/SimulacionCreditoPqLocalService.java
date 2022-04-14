package ec.fin.biess.pq.simulacion.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import ec.fin.biess.pq.simulacion.dto.DatosSimulacionCuotaMontoDto;
import ec.fin.biess.pq.simulacion.dto.DatosSimulacionDto;
import ec.fin.biess.pq.simulacion.dto.ParametrosCalculoCreditoResponseDto;
import ec.fin.biess.pq.simulacion.exception.SimuladorPqException;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;

/**
 * Servicio local para la simulacion de prestamos quirografarios
 * 
 * @author hugo.mora
 * @date 2017/05/16
 *
 */
@Local
public interface SimulacionCreditoPqLocalService {

	/**
	 * Obtiene los parametros necesarios para realizar la simulacion
	 * 
	 * @param datosSimulacionDto
	 *            DTO que tiene los parametros necesarios para realizar la simulacion
	 * @return Devuelve un objeto de tipo ParametrosCalculoCreditoDto con los resultados de la simulacion
	 * @throws SimuladorPqException
	 */
	ParametrosCalculoCreditoResponseDto obtenerParametrosSimulacion(DatosSimulacionDto datosSimulacionDto) throws SimuladorPqException;

	/**
	 * Obtiene un objeto de tipo Simulacion seteado el valor del monto maximo y el plazo maximo del credito
	 * 
	 * @param parametros
	 * @param opcionSimCuota
	 * @param esEmergente
	 * @param tipoPrestamista
	 * @return
	 * @throws SimuladorPqException
	 */
	Simulacion obtenerMontoMaximo(ParametrosCalculoCreditoResponseDto parametros, OpcionCredito opcionSimCuota, boolean esEmergente,
			TipoPrestamista tipoPrestamista,Solicitante solicitante,Precalificacion precalificacion) throws SimuladorPqException;

	/**
	 * Realiza la simulacion del credito por el monto ingresado
	 * 
	 * @param opcionSimCuota
	 * @param esEmergente
	 * @param tipoPrestamista
	 * @param parametros
	 * @param operacionSac 
	 * @return
	 * @throws SimuladorPqException
	 */
	Simulacion calcularSimulacionCuota(OpcionCredito opcionSimCuota, boolean esEmergente, TipoPrestamista tipoPrestamista,
			ParametrosCalculoCreditoResponseDto parametros, Long operacionSac) throws SimuladorPqException;

	/**
	 * Obtiene informacion de la tabla de amortizacion y seguro de saldos
	 * 
	 * @param parametros
	 * @return
	 * @throws SimuladorPqException
	 * @throws TablaAmortizacionSacException 
	 */
	PrestamoCalculo aceptarSimulacionCuota(DatosSimulacionCuotaMontoDto parametros) throws SimuladorPqException, TablaAmortizacionSacException;

	/**
	 * Realiza la simulacion del credito por la cuota ingresada
	 * 
	 * @param opcionSimMonto
	 * @param esEmergente
	 * @param tipoPrestamista
	 * @param parametros
	 * @return
	 * @throws SimuladorPqException
	 */
	Simulacion calcularSimulacionMonto(OpcionCredito opcionSimMonto, boolean esEmergente, TipoPrestamista tipoPrestamista,
			ParametrosCalculoCreditoResponseDto parametros) throws SimuladorPqException;

	/**
	 * Obtiene la informacion de tabla de amortizacion y seguro de saldos cuando se simula por cuota
	 * 
	 * @param parametros
	 * @return
	 * @throws SimuladorPqException
	 * @throws TablaAmortizacionSacException 
	 */
	PrestamoCalculo aceptarSimulacionMonto(DatosSimulacionCuotaMontoDto parametros) throws SimuladorPqException, TablaAmortizacionSacException;

	/**
	 * Permite obtener el valor de cuota maxima para la simulacion
	 * 
	 * @param opcionSimMonto
	 * @param tipoPrestamista
	 * @param edadAsegurado
	 * @param esEmergente
	 * @param plazoMaximo
	 * @param totalGarantia
	 * @return
	 * @throws SimuladorPqException
	 */
	Simulacion obtenerCuotaMaxima(OpcionCredito opcionSimMonto, TipoPrestamista tipoPrestamista, int edadAsegurado, boolean esEmergente,
			int plazoMaximo, BigDecimal totalGarantia) throws SimuladorPqException;

	
	/**
	 * Validacion monto minimo
	 * @param monto
	 * @param plazo
	 * @param tipoPrestamista
	 * @param edad
	 * @return
	 */
	List<BigDecimal>  validacionMontoMinimo(final BigDecimal monto, final BigDecimal plazo, final TipoPrestamista tipoPrestamista, final int edad)throws SimuladorPqException;
	
}
