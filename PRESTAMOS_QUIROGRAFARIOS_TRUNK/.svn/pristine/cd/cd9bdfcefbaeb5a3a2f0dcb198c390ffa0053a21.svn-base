package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ec.fin.biess.creditos.pq.modelo.dto.InformacionIessServicioDto;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado;
import ec.fin.biess.creditos.pq.modelo.dto.ValidarRequisitosPrecalificacionBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.enumeracion.TipoPrecalificacionEnum;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosPrecalificacion;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.dto.InformacionAportacionResponseDto;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigible;
import ec.gov.iess.creditos.pq.excepcion.GarantiaException;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.hl.exception.ImposicionException;

/**
 * @author Daniel Cardenas
 * 
 */
@Remote
public interface PrecalificacionServicioRemote {

	/**
	 * Metodo para obtener la precalificacion incluyendo si es un biess emergente
	 * 
	 * @param valReqPrecalificacion
	 * @param saldoValorNovacion
	 * @param rolPrestamista
	 * @param informacionPrestacionPensionado
	 * @param esPagoPensionesAlimenticias
	 * @param biessEmergenteDamnificado
	 *            Indica si la persona puede acceder a prestamo emergente
	 * @param productoBiessEmergente
	 *            Indica que la persona escogio el producto de prestamo emergente
	 * @return
	 * @throws PrecalificacionExcepcion
	 */
	Precalificacion obtenerPrecalificacion(ValidarRequisitosPrecalificacionBiess valReqPrecalificacion, BigDecimal saldoValorNovacion,
			TipoPrestamista rolPrestamista, InformacionIessServicioDto informacionIessServicioDto, boolean esPagoPensionesAlimenticias,
			boolean productoBiessEmergente,InformacionPQExigible infoPqExigile,InformacionGarantias infoGarantiaComp) throws PrecalificacionExcepcion, GarantiasSacException, PQExigibleException;
	
	/**
	 * Obtiene el valor disponible del fondo de reserva
	 * 
	 * @param cedula
	 * @return
	 * @throws PrecalificacionExcepcion
	 */
	BigDecimal obtenerValorFondoReserva(String cedula) throws PrecalificacionExcepcion;
	
	/**
	 * Obtiene informacion de las pensiones del jubilado/pensionista
	 * 
	 * @param cedula
	 * @return
	 * @throws PrecalificacionExcepcion
	 */
	InformacionPrestacionPensionado obtieneInformacionPensiones(String cedula) throws PrecalificacionExcepcion;
	
	/**
	 * Devuelve un objeto de tipo Precalificacion con informacion de requisitos, es usado para simulacion
	 * 
	 * @param esEmergente
	 * @param esNovacion
	 * @param esPagoPensionesAlimenticias
	 * @param cedula
	 * @param tipoPrestamista
	 * @param solicitante
	 * @param tipoPrecalificacionEnum
	 * @param tiposProductosPq
	 * @param fechaNacimiento
	 * @param informacionPrestacionPensionado
	 * @param esDiscapacitado
	 * @param prestamoNovar
	 * @return
	 * @throws PrecalificacionExcepcion
	 */
	Precalificacion obtenerRequisitos(boolean esEmergente, boolean esNovacion, boolean esPagoPensionesAlimenticias, String cedula,
			TipoPrestamista tipoPrestamista, Solicitante solicitante, TipoPrecalificacionEnum tipoPrecalificacionEnum,
			TiposProductosPq tiposProductosPq, Date fechaNacimiento, InformacionIessServicioDto informacionIessServicioDto,
			boolean esDiscapacitado, Prestamo prestamoNovar,InformacionPQExigible infoPqExigile,InformacionGarantias infoGarantiaComp) throws PrecalificacionExcepcion, GarantiasSacException, PQExigibleException;
	
	/**
	 * Devuelve informacion al consumir el servicio web de aportaciones del IESS (aportaciones acumuladas, consecutivas,
	 * sueldo promedio)
	 * 
	 * @param cedula
	 * @param esEmergente
	 * @return Devuelve un objeto de tipo InformacionAportacionResponseDto
	 * @throws PrecalificacionExcepcion
	 */
	InformacionAportacionResponseDto obtenerInformacionAportaciones(String cedula, boolean esEmergente) throws PrecalificacionExcepcion;
	
	/**
	 * Setea en ResumenConsolidado el numero de imposiciones consecutivas y acumuladas
	 * 
	 * @param resumenConsolidado
	 * @throws ImposicionException
	 */
	void consultarDatosImposicionesZafreros(ResumenConsolidado resumenConsolidado) throws ImposicionException;
	
	/**
	 * Obtiene el monto maximo de la garantia
	 * 
	 * @param valReqPrecalificacion
	 * @throws GarantiaException
	 */
	void consultarMontoMaximo(ValidarRequisitosPrecalificacion valReqPrecalificacion) throws GarantiaException;
	
	/**
	 * Dada una lista de requisitos devuelve otra lista unicamente con los requisitos bloqueantes que no cumple
	 * 
	 * @param requisitos
	 * @param fechaNacimiento
	 * @param tipoPrestamista
	 * @param esNovacion
	 * @return
	 */
	List<Requisito> obtieneRequisitosBloqueantesSimulador(List<Requisito> requisitos, Date fechaNacimiento, TipoPrestamista tipoPrestamista,
			boolean esNovacion);
	
}