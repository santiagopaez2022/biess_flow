package ec.fin.biess.creditos.pq.servicio;

import java.math.BigDecimal;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.MontosMaximosException;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.pq.dto.migracion.cartera.DataPersonalizacionDto;
import ec.gov.iess.creditos.pq.excepcion.MontoMinimoException;

/**
 * Interfaz local para servicios de personalizar creditos
 * @author diana.suasnavas
 *
 */
@Local
public interface PersonalizarCreditoService {	
	
	
	/**
	 * Obtiene el monto maximo de credito segun tipo de amortizacion deseada
	 * 
	 * @param cuotaMaximaEndeudamiento
	 * @param tasaInteres
	 * @param numeroMeses
	 * @param garantiaTotal
	 * @param tipo
	 *            que puede ser ALEMANA o FRANCESA
	 * @param esEmergente
	 * @param tipoPrestamista
	 * @param edad
	 * @return
	 * @throws MontosMaximosException
	 */
	BigDecimal obtenerMontoMaximoPorTipoAmortizacion(BigDecimal cuotaMaximaEndeudamiento, BigDecimal tasaInteres, BigDecimal numeroMeses,
			BigDecimal garantiaTotal, String tipo, boolean esEmergente, TipoPrestamista tipoPrestamista, int edad) throws MontosMaximosException;

	
	
	
	/**
	 * Obtiene el porcentaje maximmo para comprometer de la garantia
	 * @param esEmergente
	 * @param tipoPrestamista
	 * @param edad
	 * @param plazo
	 * @return
	 * @throws MontosMaximosException
	 */
	BigDecimal obtenerPorcentajeGarantia(boolean esEmergente, TipoPrestamista tipoPrestamista, int edad, BigDecimal plazo)
			throws MontosMaximosException ;
	
	
	/**
	 * Obtiene el monto maximo por amortizacion frances
	 * @param dataPerDto
	 * @return
	 * @throws MontosMaximosException
	 */
	BigDecimal obtenerMontoMaximoPorTipoAmortizacionFran(DataPersonalizacionDto dataPerDto) throws MontosMaximosException,MontoMinimoException;

	
	/**
	 * Obtiene el monto maximo por amortizacion alemana
	 * @param dataPerDto
	 * @return
	 * @throws MontosMaximosException
	 */
	BigDecimal obtenerMontoMaximoPorTipoAmortizacionAle(DataPersonalizacionDto dataPerDto) throws MontosMaximosException,MontoMinimoException;

}
