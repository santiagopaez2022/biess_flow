package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionIessServicioDto;
import ec.fin.biess.exception.ParametrizacionPQException;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.dto.Garantia;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigible;
import ec.gov.iess.creditos.pq.excepcion.GarantiaException;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;

@Local
public interface GarantiaCreditoServicio {

	/**
	 * @param datGarantia
	 * @param rolPrestamista
	 * @param informacionPrestacionPensionado
	 * @param sueldoPromedio
	 * @return
	 * @throws GarantiaException
	 * @throws PQExigibleException 
	 */
	public Garantia getGarantia(DatosGarantia datGarantia, TipoPrestamista rolPrestamista,
			InformacionIessServicioDto informacionIessServicioDto, BigDecimal sueldoPromedio,InformacionGarantias infoGarantiaComp) throws GarantiaException, GarantiasSacException, PQExigibleException;
	
	/**
	 * Obtiene el porcentaje de comprometimiento de PQ
	 * 
	 * @param creditoEmergente
	 * @param tipoPrestamista
	 * @param edad
	 * @param plazo
	 * @return
	 * @throws ParametrizacionPQException
	 * @throws ParametroBiessException
	 */
	BigDecimal obtenerPorcentajeComprometimientoPQ(boolean creditoEmergente, TipoPrestamista tipoPrestamista, int edad, BigDecimal plazo)
			throws ParametrizacionPQException, ParametroBiessException;
	
	/**
	 * Obtiene el valor de monto maximo para el credito
	 * 
	 * @param plazo
	 * @param tipoPrestamista
	 * @param edad
	 * @return
	 * @throws ParametrizacionPQException
	 * @throws ParametroCreditoException
	 */
	BigDecimal obtenerMontoMaximo(BigDecimal plazo, TipoPrestamista tipoPrestamista, int edad)
			throws ParametrizacionPQException, ParametroCreditoException;
	
	/**
	 * Obtiene el valor monto maximo para el credito se pasa la lista de exigibles del SAC
	 * @param datGarantia
	 * @param rolPrestamista
	 * @param informacionIessServicioDto
	 * @param sueldoPromedio
	 * @param infoPqExigile
	 * @return
	 */
	 Garantia getGarantia(DatosGarantia datGarantia, TipoPrestamista rolPrestamista,
			InformacionIessServicioDto informacionIessServicioDto, BigDecimal sueldoPromedio,InformacionPQExigible infoPqExigile,InformacionGarantias infoGarantiaComp)throws GarantiaException;
	
	
}