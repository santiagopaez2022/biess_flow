package ec.gov.iess.creditos.pq.helper.reglas;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.enumeracion.TipoPrecalificacionEnum;
import ec.gov.iess.creditos.enumeracion.TipoRequisitoSimulacionEnum;
import ec.gov.iess.creditos.modelo.dto.DetalleGarantia;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;

/**
 * Se incluye una regla para no superar los 80 SBU
 * 
 * @author pjarrin
 * 
 */
public class ReglasAfiliado {
	static LoggerBiess log = LoggerBiess.getLogger(ReglasAfiliado.class);
	
	/**
	 * Funcion para validar los 80 SBU
	 * 
	 * @param precalificacion
	 * @param sumaSaldos
	 * @return
	 */
	public static boolean validacionSBU(final Precalificacion precalificacion,
			final BigDecimal sumaSaldos, final BigDecimal sbu, final BigDecimal numsbu) {
		final Requisito rq = new Requisito(); 
		rq.setDescripcion("EL SALDO DE SUS CREDITOS VIGENTES EXCEDE DE " + numsbu.toString() +" SBU(SALARIO BASICO UNIFICADO)");
		if (sumaSaldos != null) {
			final BigDecimal totalSBU = sbu.multiply(numsbu).setScale(2, BigDecimal.ROUND_HALF_UP);
			if (new Integer(sumaSaldos.compareTo(totalSBU)).equals(-1)) {			
				rq.setAprobado(true);
				rq.setObservacion("NO EXCEDE DE LOS " + numsbu.toString() +" SBU");
				precalificacion.getRequisitos().add(rq);
				return true;
			} else {
				rq.setAprobado(false);
				rq.setObservacion("EXCEDE DE LOS " + numsbu.toString() + " SBU");
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
	 * Metodo para validar el requisito de imposiciones consecutivas
	 * 
	 * @param precalificacion
	 * @param resumenConsolidado
	 * @param discapacitado
	 * @param biessEmergente
	 * @param aportacionesAcumuladasRequeridas
	 * @return
	 */
	public static boolean numeroImposiciones(final Precalificacion precalificacion, final ResumenConsolidado resumenConsolidado, final boolean discapacitado,
			final DatosBiessEmergente datosBiessEmergente, final int aportacionesAcumuladasRequeridas) {
		Requisito rq = new Requisito();
		
		final int numero = resumenConsolidado.getNumeroImposiciones().intValue();
		rq = obtieneNumeroImposicionesAcumuladas(numero, discapacitado, datosBiessEmergente, precalificacion.getTipoPrecalificacionEnum(),
				aportacionesAcumuladasRequeridas);
		
		precalificacion.getRequisitos().add(rq);
		return rq.isAprobado();
	}
	
	/**
	 * Devuelve un requisito para validar el numero de aportaciones acumuladas
	 * 
	 * @param aportacionesAfiliado
	 * @param esDiscapacitado
	 * @param datosBiessEmergente
	 * @param tipoPrecalificacionEnum
	 * @param aportacionesAcumuladasRequeridas
	 * @return
	 */
	public static Requisito obtieneNumeroImposicionesAcumuladas(final int aportacionesAfiliado, final boolean esDiscapacitado,
			final DatosBiessEmergente datosBiessEmergente, final TipoPrecalificacionEnum tipoPrecalificacionEnum, final int aportacionesAcumuladasRequeridas) {
		final Requisito requisito = new Requisito();

		/* INC-1719 2013-11-13 Agregar preferencias para afiliados discapacitados */
		int aportaciones = aportacionesAcumuladasRequeridas;
		if (esDiscapacitado) {
			aportaciones = new BigDecimal(aportaciones).divide(new BigDecimal(2), 1, BigDecimal.ROUND_DOWN).intValue();
		}

		// Validacion imposiciones para daminificados del terremoto
		if (datosBiessEmergente != null && datosBiessEmergente.isValidaBiessEmergente()) {
			aportaciones = datosBiessEmergente.getAportacionesAcumuladas().intValue();
			if (esDiscapacitado) {
				aportaciones = aportaciones / 2;
			}
		}

		String observacionNoCumple = "TIENE " + String.valueOf(aportacionesAfiliado) + " APORTACIONES, POR FAVOR ACERCARSE A AFILIACION Y CONTROL";

		if (TipoPrecalificacionEnum.SIMULACION == tipoPrecalificacionEnum) {
			final int aportacionesFalta = aportaciones - aportacionesAfiliado;
			observacionNoCumple = "A LA FECHA USTED REGISTRA " + String.valueOf(aportacionesAfiliado) + " APORTACIONES ACUMULADAS, LE FALTA "
					+ String.valueOf(aportacionesFalta) + " APORTACIONES.";
		}

		requisito.setDescripcion("TENER MINIMO " + aportaciones + " APORTACIONES");

		final boolean valido = (aportacionesAfiliado >= aportaciones);
		if (valido) {
			requisito.setAprobado(true);
			requisito.setObservacion("TIENE " + String.valueOf(aportacionesAfiliado) + " APORTACIONES");
		} else {
			requisito.setAprobado(false);
			requisito.setObservacion(observacionNoCumple);
		}

		return requisito;
	}

	public static boolean numeroImposicionesConsecutivas(
			final Precalificacion precalificacion,
			final ResumenConsolidado resumenConsolidado, final boolean discapacitado, final DatosBiessEmergente datosBiessEmergente, final int aportacionesConsecutivasRequeridas) {
		log.debug("Imposiciones consecutivas: "
				+ resumenConsolidado.getNunmeroImpoConsecutivas());
		
		Requisito rq = new Requisito();
		final int numeroImpoConsecutivasAfiliado = resumenConsolidado.getNunmeroImpoConsecutivas().intValue();
		
		rq = obtieneRequisitoImposicionesConsecutivas(numeroImpoConsecutivasAfiliado, discapacitado, datosBiessEmergente,
				precalificacion.getTipoPrecalificacionEnum(), aportacionesConsecutivasRequeridas);
		if (TipoPrecalificacionEnum.CREDITO == precalificacion.getTipoPrecalificacionEnum()) {
			precalificacion.getRequisitos().add(rq);
		} else if (TipoPrecalificacionEnum.SIMULACION == precalificacion.getTipoPrecalificacionEnum() && !discapacitado) {
			precalificacion.getRequisitos().add(rq);
		}
		return rq.isAprobado();
	}
	
	/**
	 * Devuelve un Requisito para la validacion de imposiciones consecutivas
	 * 
	 * @param aportacionesAfiliado
	 * @param esDiscapacitado
	 * @param datosBiessEmergente
	 * @param tipoPrecalificacionEnum
	 * @param aportacionesConsecutivasRequeridas
	 * @return
	 */
	public static Requisito obtieneRequisitoImposicionesConsecutivas(final int aportacionesAfiliado, final boolean esDiscapacitado,
			final DatosBiessEmergente datosBiessEmergente, final TipoPrecalificacionEnum tipoPrecalificacionEnum, final int aportacionesConsecutivasRequeridas) {
		final Requisito requisito = new Requisito();
		requisito.setTipoRequisitoSimulacion(TipoRequisitoSimulacionEnum.BLOQUEANTE);
		int numeroImpoConsecutivas = aportacionesConsecutivasRequeridas;

		// Validacion imposiciones para daminificados del terremoto
		if (datosBiessEmergente != null && datosBiessEmergente.isValidaBiessEmergente()) {
			numeroImpoConsecutivas = datosBiessEmergente.getAportacionesConsecutivas().intValue();
		}

		requisito.setDescripcion("TENER LAS " + numeroImpoConsecutivas + " ULTIMAS APORTACIONES CONSECUTIVAS");

		/* INC-1719 2013-11-13 Agregar preferencias para afiliados discapacitados */
		if (esDiscapacitado) {
			requisito.setObservacion("NO APLICA PARA AFILIADO DISCAPACITADO");
			requisito.setAprobado(true);
		} else {
			final boolean cumpleCondicion = aportacionesAfiliado >= numeroImpoConsecutivas;
			String mensajeNoCumple = "NO TIENE LAS \u00DALTIMAS APORTACIONES CONSECUTIVAS, POR FAVOR ACERCARSE A AFILIACI\u00D3N Y CONTROL";
			if (TipoPrecalificacionEnum.SIMULACION == tipoPrecalificacionEnum) {
				final int aportacionesFalta = numeroImpoConsecutivas - aportacionesAfiliado;
				mensajeNoCumple = "A LA FECHA USTED REGISTRA " + String.valueOf(aportacionesAfiliado)
						+ " \u00DALTIMAS APORTACIONES CONSECUTIVAS, LE FALTA " + String.valueOf(aportacionesFalta) + " APORTACIONES.";
			}

			if (cumpleCondicion) {
				requisito.setObservacion("EL AFILIADO TIENE " + aportacionesAfiliado + " APORTACIONES CONSECUTIVAS");
				requisito.setAprobado(true);
			} else {
				requisito.setObservacion(mensajeNoCumple);
				requisito.setAprobado(false);
			}
		}

		return requisito;
	}

	public static boolean empleadorPerteneceSsc(
			final Precalificacion precalificacion,
			final ResumenConsolidado resumenConsolidado) {
		log.debug("Empleado SSC: " + resumenConsolidado.getRelacionTrabajo());
		final Requisito rq = new Requisito();
		rq.setDescripcion("SU EMPLEADOR ACTUAL NO DEBE PERTENECER AL SEGURO SOCIAL CAMPESINO");
		/**
		 * rituana: inc-5780 cambiado por mejores practicas
		 */
		final boolean perteneceAlSsc = resumenConsolidado.getRelacionTrabajo() == null ? false
				: "01".equals(resumenConsolidado.getRelacionTrabajo())
						|| "46".equals(resumenConsolidado.getRelacionTrabajo())
						|| "47".equals(resumenConsolidado.getRelacionTrabajo())
						|| "48".equals(resumenConsolidado.getRelacionTrabajo());

		if (perteneceAlSsc) {
			rq.setAprobado(false);
			rq.setObservacion("SU EMPLEADOR PERTENECE AL SEGURO SOCIAL CAMPESINO");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("SU EMPLEADOR NO PERTENECE AL SEGURO SOCIAL CAMPESINO");
		}
		precalificacion.getRequisitos().add(rq);
		return perteneceAlSsc;
	}

	public static boolean validarTieneGarantiasAfiliadoVoluntario(
			final Precalificacion precalificacion,
			final ResumenConsolidado resumenConsolidado) {
		log.debug("Voluntario: " + resumenConsolidado.getRelacionTrabajo());
		
		final boolean tieneGarantias = resumenConsolidado
				.getTieneGarantiasAfiliadoVoluntario();
		
		final Requisito rq = validaPoseerGarantiasReales(tieneGarantias);
		
		precalificacion.getRequisitos().add(rq);
		return tieneGarantias;
	}
	
	/**
	 * Indica la descripcion del requisito dependiendo si tiene garantias reales o no
	 * 
	 * @param tieneGarantias
	 * @return Objeto de tipo Requisito
	 */
	public static Requisito validaPoseerGarantiasReales(final boolean tieneGarantias) {
		final Requisito requisito = new Requisito();
		requisito.setDescripcion("POSEER GARANTIAS REALES (FONDOS DE RESERVA Y/O CESANTIAS) EN EL IESS");
		requisito.setTipoRequisitoSimulacion(TipoRequisitoSimulacionEnum.BLOQUEANTE);

		if (tieneGarantias) {
			requisito.setAprobado(true);
			log.debug("SI TIENE GARANT\u00CDAS");
			requisito.setObservacion("SI TIENE GARANT\u00CDAS");
		} else {
			requisito.setAprobado(false);
			log.debug("NO TIENE GARANTIAS");
			requisito.setObservacion("NO TIENE GARANTIAS");
		}

		return requisito;
	}

	public static boolean afiliadoEsActivo(final Precalificacion precalificacion,
			final ResumenConsolidado resumenConsolidado) {
		log.debug("Activo HL:" + resumenConsolidado.getEstadoActivoHl());
		final Requisito rq = validaAfiliadoActivo(resumenConsolidado);
		
		precalificacion.getRequisitos().add(rq);
		return rq.isAprobado();
	}
	
	/**
	 * Dado el resumen consolidado (FRAFITACTCES) verifica si es un afiliado activo
	 * 
	 * @param resumenConsolidado
	 * @return Objeto de tipo Requisito
	 */
	public static Requisito validaAfiliadoActivo(final ResumenConsolidado resumenConsolidado) {
		final Requisito requisito = new Requisito();
		requisito.setDescripcion("SER UN AFILIADO ACTIVO");
		requisito.setTipoRequisitoSimulacion(TipoRequisitoSimulacionEnum.BLOQUEANTE);
		
		final boolean esActivo = "ACT".equals(resumenConsolidado.getEstadoActivoHl());
		
		if (esActivo) {
			requisito.setAprobado(true);
			requisito.setObservacion("EL AFILIADO ESTA ACTIVO");
		} else {
			requisito.setAprobado(false);
			requisito.setObservacion("EL AFILIADO ES CESANTE");
		}
		
		return requisito;
	}

	public static boolean esMayo(final Precalificacion precalificacion,
			final ResumenConsolidado resumenConsolidado) {
		final Calendar hoy = new GregorianCalendar();
		final Requisito rq = new Requisito();
		rq.setDescripcion("LOS PRESTAMOS QUIROGRAFARIOS PARA LOS TRABAJADORES DE LA ZAFRA SOLAMENTE SE PUEDEN SOLICITAR EN MAYO DE CADA AÑO");

		final boolean esMayo = hoy.get(Calendar.MONTH) == Calendar.MAY;

		if (esMayo) {
			rq.setAprobado(true);
		} else {
			rq.setAprobado(false);
			rq.setObservacion("POR FAVOR SOLICITE SU PRESTAMO EN MAYO");
		}

		precalificacion.getRequisitos().add(rq);

		return esMayo;

	}

	public static boolean tieneSolicitudFondosReserva(
			final Precalificacion precalificacion,
			final ResumenConsolidado resumenConsolidado) {
		final Requisito rq = new Requisito();
		log.debug("Solicitud fondos: "
				+ resumenConsolidado.getSolicitudFondosReserva());
		rq.setDescripcion("NO TENER SOLICITUD DE FONDOS DE RESERVA EN TRAMITE");
		final boolean existeSolicitudFondos = "1".equals(resumenConsolidado
				.getSolicitudFondosReserva());

		if (existeSolicitudFondos) {
			rq.setAprobado(false);
			rq.setObservacion("EL AFILIADO TIENE UNA SOLICITUD DE FONDOS DE RESERVA EN TRAMITE. "
					+ "FAVOR ACERCARSE A FONDOS DE TERCEROS");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("EL AFILIADO NO TIENE UNA SOLICITUD DE FONDOS DE RESERVA EN TRAMITE");
		}
		precalificacion.getRequisitos().add(rq);
		log.debug("existeSolicitudFondos: " + existeSolicitudFondos + " rq: "
				+ rq.isAprobado());
		return existeSolicitudFondos;

	}

	public static boolean tieneSolicitudCesantia(
			final Precalificacion precalificacion,
			final ResumenConsolidado resumenConsolidado) {
		final Requisito rq = validaSolicitudCesantiaTramite(resumenConsolidado);
		
		precalificacion.getRequisitos().add(rq);
		log.debug("existeSolicitud: " + rq.isAprobado() + " rq: "
				+ rq.isAprobado());

		// Se niega porque si no tiene solicitudes en tramite debe devolver false para permitir la precalificacion
		return !rq.isAprobado();
	}
	
	/**
	 * Valida si el afiliado tiene una solicitud de cesantia en tramite.
	 * Esta quemado a FALSE debido a:
	 * 		INC-27741. Validacion eliminada debido a que hace referencia al aplicativo antiguo de cesantias.
	 * 		Actualmente el valor de cesantias se encera al momento que el asegurado solicita la misma.
	 * @param resumenConsolidado
	 * @return
	 */
	public static Requisito validaSolicitudCesantiaTramite(final ResumenConsolidado resumenConsolidado) {
		final Requisito requisito = new Requisito();
		requisito.setDescripcion("NO TENER SOLICITUD DE CESANTIA EN TRAMITE");
		requisito.setTipoRequisitoSimulacion(TipoRequisitoSimulacionEnum.BLOQUEANTE);
		
		log.debug("Solicitud Cesantia: "
				+ resumenConsolidado.getSolicitudCesantia());

		/* INC-27741. Validacion eliminada debido a que hace referencia al aplicativo antiguo de cesantias.
		 * Actualmente el valor de cesantias se encera al momento que el asegurado solicita la misma. */
		/* boolean existeSolicitud = "1".equals(resumenConsolidado.getSolicitudCesantia()); */
		final boolean existeSolicitud = false; 

		if (existeSolicitud) {
			requisito.setAprobado(false);
			requisito.setObservacion("EL AFILIADO TIENE UNA SOLICITUD DE CESANTIA EN TRAMITE. "
					+ "FAVOR ACERCARSE A FONDOS DE TERCEROS");
		} else {
			requisito.setAprobado(true);
			requisito.setObservacion("EL AFILIADO NO TIENE UNA SOLICITUD DE CESANTIA EN TRAMITE");
		}
		
		return requisito;
	}
	
	public static boolean suEmpleadorEstaEnMora(

			final Precalificacion precalificacion,final ResumenConsolidado resumenConsolidado, final DatosBiessEmergente datosBiessEmergente, boolean empleadoresEstanEnMora) {
		final Requisito rq = new Requisito();
		rq.setDescripcion("SU(S) EMPLEADOR(RES) NO DEBE(N) ESTAR EN MORA CON EL IESS");
		log.debug("Validando que los empleadores no esten en mora");

		// Valida si es biess emergente para determinar si el empleador esta en mora
		if (datosBiessEmergente != null && datosBiessEmergente.isValidaBiessEmergente()) {
			empleadoresEstanEnMora = datosBiessEmergente.isTieneMoraPlanillas();
		}

		if (empleadoresEstanEnMora) {
			rq.setAprobado(false);
			rq.setObservacion("SU(S) EMPLEADOR(ES) ESTA(N)EN MORA CON EL IESS");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("SU(S) EMPLEADOR(ES) NO ESTA(N)EN MORA");
		}
		precalificacion.getRequisitos().add(rq);
		return empleadoresEstanEnMora;
	}

	/**
	 * Valida las garantías previa la novacion Retorna true si la validación es
	 * satisfactoria
	 * 
	 * @param precalificacion
	 * @return
	 */
	public static boolean validarGarantiasNovacion(
			final Precalificacion precalificacion) throws PrecalificacionExcepcion {
		log.info("Validando las garantias de novacion");
		boolean resultadoValidacion = false;
		double saldoPrestamo = 0;
		double fondosReserva = 0;
		double cesantia = 0;
		if (precalificacion.getValidarRequisitosPrecalificacion()
				.getLiquidacionPrestamoVigente() == null) {
			throw new PrecalificacionExcepcion("VALOR DE LIQUIDACION NULA");
		}
		//tomo el saldo del credito de la respuesta del SAC seteado antes
		saldoPrestamo = precalificacion.getValidarRequisitosPrecalificacion()
				.getLiquidacionPrestamoVigente().getValorPorCancelar()
				.doubleValue();

		if (!precalificacion.getGarantia().getDetalleGarantiaList().isEmpty()) {
			for (final DetalleGarantia detalleGarantia : precalificacion
					.getGarantia().getDetalleGarantiaList()) {
				if (detalleGarantia.getNombreGarantia().equals(
						"FONDOS DE RESERVA")) {
					fondosReserva = detalleGarantia.getValorGarantia()
							.doubleValue();
				}
				if (detalleGarantia.getNombreGarantia().equals("CESANTIA")) {
					cesantia = detalleGarantia.getValorGarantia().doubleValue();
				}
			}
		}
		log.info("SALDO DEL PRESTAMO: " + saldoPrestamo);
		final Requisito rq = new Requisito();
		rq.setDescripcion("LAS GARANTIAS SON MAYORES O IGUALES AL SALDO VIGENTE");
		 if ( (fondosReserva + cesantia) >= saldoPrestamo) {
			rq.setAprobado(true);
			resultadoValidacion = true;
			rq.setObservacion("LAS GARANT\u00CDAS DISPONIBLES DEL ASEGURADO SON MAYORES O IGUALES AL SALDO VIGENTE");
		} else if ((fondosReserva + cesantia) < saldoPrestamo) {
			rq.setAprobado(false);
			rq.setObservacion("LAS GARANT\u00CDAS DISPONIBLES DEL ASEGURADO NO SON MAYORES O IGUALES AL SALDO VIGENTE");
		}
		precalificacion.getRequisitos().add(rq);
		return resultadoValidacion;
	}
}
