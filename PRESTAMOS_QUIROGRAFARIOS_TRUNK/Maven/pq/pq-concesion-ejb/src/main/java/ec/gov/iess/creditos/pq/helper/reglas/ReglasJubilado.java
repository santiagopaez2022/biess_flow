package ec.gov.iess.creditos.pq.helper.reglas;

import java.math.BigDecimal;

import ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado;
import ec.fin.biess.creditos.pq.modelo.dto.PrestacionPensionado;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.TipoRequisitoSimulacionEnum;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.util.GeneradorListaPrestaciones;

/**
 * Se incluye una regla para no superar los 80 SBU
 * 
 * @author pjarrin
 * 
 */
public class ReglasJubilado {

	static LoggerBiess log = LoggerBiess.getLogger(ReglasAfiliado.class);

	/**
	 * Funcion para validar los 80 SBU
	 * 
	 * @param precalificacion
	 * @param sumaSaldos
	 * @return
	 */
	public static boolean validacionSBU(Precalificacion precalificacion,
			BigDecimal sumaSaldos, BigDecimal sbu, BigDecimal numsbu) {
		Requisito rq = new Requisito();
		rq.setDescripcion("EL SALDO DE SUS CREDITOS VIGENTES EXCEDE DE 80 SBU(SALARIO BASICO UNIFICADO)");
		if (sumaSaldos != null) {
			BigDecimal totalSBU = sbu.multiply(numsbu).setScale(2, BigDecimal.ROUND_HALF_UP);
			if (new Integer(sumaSaldos.compareTo(totalSBU)).equals(-1)) {
				rq.setAprobado(true);
				rq.setObservacion("NO EXCEDE DE LOS 80 SBU");
				precalificacion.getRequisitos().add(rq);
				return true;
			} else {
				rq.setAprobado(false);
				rq.setObservacion("EXCEDE DE LOS 80 SBU");
				precalificacion.getRequisitos().add(rq);
				return false;
			}
		} else {
			rq.setAprobado(true);
			rq.setObservacion("NO EXCEDE DE LOS 80 SBU");
			precalificacion.getRequisitos().add(rq);
			return true;
		}
	}

	/**
	 * Valida si un usuario es o no pensionista.
	 * 
	 * @param precalificacion
	 * @param esPensionista
	 * @param prestacionesJubilado
	 * 
	 * @return
	 */
	public static boolean esPensionista(InformacionPrestacionPensionado informacionPrestacionPensionado, 
			Precalificacion precalificacion, String prestacionesJubilado) {
		// INC-2135 Pensiones Jubilados/Pensionistas: se valida la existencia de prestaciones de pensionista a traves
		// del ESB con el web service del iess
		Requisito rq = validaSerPensionista(informacionPrestacionPensionado);

		precalificacion.getRequisitos().add(rq);

		return rq.isAprobado();
	}
	
	/**
	 * Valida si el jubilado/pensionista tiene lista de pensiones
	 * 
	 * @param informacionPrestacionPensionado
	 * @return
	 */
	public static Requisito validaSerPensionista(InformacionPrestacionPensionado informacionPrestacionPensionado) {
		String prestacionesJubilado = "";

		Requisito requisito = new Requisito();
		requisito.setTipoRequisitoSimulacion(TipoRequisitoSimulacionEnum.BLOQUEANTE);
		requisito.setDescripcion("SER PENSIONISTA DEL IESS");

		boolean esPensionista = false;

		if (null != informacionPrestacionPensionado && null != informacionPrestacionPensionado.getListaPrestaciones()
				&& !informacionPrestacionPensionado.getListaPrestaciones().isEmpty()) {
			esPensionista = true;
			prestacionesJubilado = GeneradorListaPrestaciones
					.concatenarDescripcionPrestaciones(informacionPrestacionPensionado.getListaPrestaciones());
		}

		if (esPensionista) {
			requisito.setAprobado(esPensionista);
			requisito.setObservacion("ES PENSIONISTA DE: " + prestacionesJubilado);
		} else {
			requisito.setAprobado(esPensionista);
			requisito.setObservacion("NO ES PENSIONISTA DEL IESS");
		}

		return requisito;
	}
	
	/**
	 * Requisito bloqueante para la novacion en simulador
	 * 
	 * @param edadJubilado
	 * @param edadRequerida
	 * @return
	 */
	public static Requisito edadMinimaNovacionSimulacion(int edadJubilado, int edadRequerida) {
		Requisito requisito = new Requisito();
		requisito.setTipoRequisitoSimulacion(TipoRequisitoSimulacionEnum.BLOQUEANTE);
		requisito.setDescripcion("EL JUBILADO/PENSIONISTA DEBE TENER HASTA " + edadRequerida + " A\u00D1OS PARA REALIZAR UNA NOVACI\u00D3N");

		boolean cumpleEdadMinima = edadJubilado <= edadRequerida;
		if (cumpleEdadMinima) {
			requisito.setAprobado(true);
			requisito.setObservacion("CUMPLE REQUISITO DE EDAD M\u00CDNIMA PARA REALIZAR UNA NOVACI\u00D3N");
		} else {
			requisito.setAprobado(false);
			requisito.setObservacion("USTED TIENE " + edadJubilado + " A\u00D1OS");
		}

		return requisito;
	}

	public static boolean tieneIncapacidadPermanete(
			Precalificacion precalificacion,
			String prestacionesJubilado, PrestacionPensionado p) {
		
		boolean cumpleCondicion = true;
		
		Requisito rq = new Requisito();
		rq.setDescripcion("EN CASO DE TENER UNA PRESTACION DE INVALIDEZ, ESTA TIENE QUE SER PERMANENTE");

		if (cumpleCondicion) {
			rq.setAprobado(cumpleCondicion);
			rq.setObservacion("SU PRESTACION " + prestacionesJubilado
					+ " ES PERMANENTE");
		} else {
			rq.setAprobado(cumpleCondicion);
			rq.setObservacion("SU PRESTACION " + prestacionesJubilado
					+ " NO ES PERMANENTE");
		}

		precalificacion.getRequisitos().add(rq);

		return cumpleCondicion;
	}

	/**
	 * Regla que valida el jubilado tenga prestaciones, para el caso de que no
	 * las disponga emite el mensaje respectivo
	 * 
	 * @param precalificacion
	 * @return
	 */
	public static boolean validarPrestaciones(Precalificacion precalificacion, InformacionPrestacionPensionado informacionPrestacionPensionado,
			BigDecimal valorRetencionesJudiciales) {
		// INC-2135 Pensiones Jubilados/Pensionistas
		Requisito rq = validaTenerValorDisponiblePension(informacionPrestacionPensionado, valorRetencionesJudiciales);
		log.info("Validando las prestaciones de un jubilado");

		precalificacion.getRequisitos().add(rq);
		return rq.isAprobado();
	}
	
	/**
	 * Valida si el jubilado/pensionista tiene un valor disponible de pension
	 * 
	 * @param informacionPrestacionPensionado
	 * @param valorRetencionesJudiciales
	 * @return
	 */
	public static Requisito validaTenerValorDisponiblePension(InformacionPrestacionPensionado informacionPrestacionPensionado,
			BigDecimal valorRetencionesJudiciales) {
		Requisito requisito = new Requisito();
		requisito.setTipoRequisitoSimulacion(TipoRequisitoSimulacionEnum.BLOQUEANTE);

		if (null == informacionPrestacionPensionado || null == informacionPrestacionPensionado.getListaPrestaciones()
				|| informacionPrestacionPensionado.getListaPrestaciones().isEmpty()) {
			requisito.setObservacion("USTED NO TIENE VALOR DISPONIBLE DE SU PENSI\u00D3N");
			requisito.setAprobado(false);
		} else {
			if (informacionPrestacionPensionado.getTotalIngresos().compareTo(informacionPrestacionPensionado.getTotalEgresos()) <= 0) {
				requisito.setObservacion("USTED NO TIENE VALOR DISPONIBLE DE SU PENSI\u00D3N");
				requisito.setAprobado(false);
				log.info("valorRetencionesJudiciales: " + valorRetencionesJudiciales);
				if (null != valorRetencionesJudiciales && valorRetencionesJudiciales.compareTo(BigDecimal.ZERO) > 0) {
					requisito.setObservacion(requisito.getObservacion() + ", MANTIENE UNA RETENCI\u00D3N JUDICIAL DE $" + valorRetencionesJudiciales
							+ " QUE SUPERA EL VALOR DE SUS GARANT\u00CDAS");
				}
			} else {
				requisito.setObservacion("DISPONE DE UN VALOR EN PENSIONES QUE LE PERMITE LA CONCESI\u00D3N DE UN PRESTAMO QUIROGRAFARIO");
				requisito.setAprobado(true);
			}
		}

		requisito.setDescripcion("TENER VALOR DISPONIBLE DE SU PENSION QUE PERMITA LA CONCESION DE PRESTAMO QUIROGRAFARIO");
		
		return requisito;
	}

	/**
	 * Valida las garantías previa la novacion Retorna true si la validación es
	 * satisfactoria
	 * 
	 * @param precalificacion
	 * @return
	 */
	public static boolean validarGarantiasNovacionJubilado(
			Precalificacion precalificacion) throws PrecalificacionExcepcion {
		log.info("Validando las garantias de novacion");
		boolean resultadoValidacion = false;
		double saldoPrestamo = 0;
		// double prestaciones = 0;

		/*
		 * if(precalificacion.getValidarRequisitosPrecalificacion().
		 * getLiquidacionPrestamoVigente() == null) { throw new
		 * PrecalificacionExcepcion( "VALOR DE LIQUIDACION NULA"); }
		 */
		saldoPrestamo = precalificacion.getValidarRequisitosPrecalificacion()
				.getLiquidacionPrestamoVigente().getValorPorCancelar()
				.doubleValue();

		// prestaciones =
		// precalificacion.getGarantia().getSueloPromedio().doubleValue();
		log.info("SALDO DEL PRESTAMO: " + saldoPrestamo);
		Requisito rq = new Requisito();
		rq.setDescripcion("EL SALDO DEL PRESTAMO QUIROGRAFARIO VIGENTE ES MAYOR A CERO ");
		if (saldoPrestamo <= 0) {
			rq.setAprobado(false);
			resultadoValidacion = false;
			rq.setObservacion("NO EXISTE SALDO DE PR\u00C9STAMO QUIROGRAFARIO PARA HACER LA NOVACI\u00D3N");
		} else {
			rq.setAprobado(true);
			resultadoValidacion = true;
			rq.setObservacion("EL SALDO DE PR\u00C9STAMO QUIROGRAFARIO ES MAYOR A CERO  ");
		}
		precalificacion.getRequisitos().add(rq);
		return resultadoValidacion;
	}
}
