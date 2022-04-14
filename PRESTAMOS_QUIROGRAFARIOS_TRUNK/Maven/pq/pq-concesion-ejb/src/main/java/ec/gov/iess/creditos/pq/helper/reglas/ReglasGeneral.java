/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.helper.reglas;


import ec.fin.biess.creditos.pq.modelo.dto.ParamsReglasPrecalificacion;
import ec.fin.biess.listaobservados.enumeration.TipoListaEnum;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;

public class ReglasGeneral {
	
	private static LoggerBiess log = LoggerBiess.getLogger(ReglasGeneral.class);
	
	

	public static boolean cuentaValida(Precalificacion precalificacion, ResumenConsolidado resumenConsolidado) {
		Requisito rq = new Requisito();
		rq.setDescripcion("TENER UNA CUENTA BANCARIA REGISTRADA Y AUTORIZADA POR EL IESS");
		boolean tieneCuentaValida = resumenConsolidado.getTieneCuentaAutorizada().booleanValue();
		log.debug("tieneCuentaValida: " + resumenConsolidado.getTieneCuentaAutorizada());

		if (tieneCuentaValida) {
			// Si tiene una cuenta validad ademas se verifica que el tipo de
			// cuenta no sea virtual
			if (TipoCuenta.VIR.toString().equals(resumenConsolidado.getTipoCuenta())) {
				rq.setAprobado(false);
				rq
						.setObservacion("TIENE CUENTA VALIDADA POR UN FUNCIONARIO DEL IESS, SIN EMBARGO LA CUENTA ES VIRTUAL.  FAVOR "
								+ "REGISTRAR UNA CUENTA DE AHORROS O CORRIENTE Y ACERCASE AL IESS PARA SU VALIDACION");
				tieneCuentaValida = false;
			} else {
				rq.setAprobado(true);
				rq.setObservacion("TIENE CUENTA VALIDADA POR UN FUNCIONARIO DEL IESS");
			}
		} else {
			rq.setAprobado(false);
			rq
					.setObservacion("NO TIENE CUENTA VALIDADA.  FAVOR REGISTRAR CUENTA Y ACERCASE AL IESS PARA SU VALIDACION");
		}
		precalificacion.getRequisitos().add(rq);
		return tieneCuentaValida;
	}

	public static boolean tieneObligaciones(Precalificacion precalificacion, ResumenConsolidado resumenConsolidado) {
		Requisito rq = new Requisito();
		rq.setDescripcion("SI ES EMPLEADOR NO TENER OBLIGACIONES PENDIENTES CON EL IESS");

		boolean tieneObligaciones = "1".equals(resumenConsolidado.getMoraPatronal());
		log.debug("Tiene obligaciones: " + resumenConsolidado.getMoraPatronal());

		if (tieneObligaciones) {
			rq.setAprobado(false);
			rq.setObservacion("TIENE OBLIGACIONES PENDIENTES CON EL IESS");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("NO TIENE OBLIGACIONES PENDIENTES CON EL IESS");
		}
		precalificacion.getRequisitos().add(rq);
		return tieneObligaciones;
	}

	public static boolean tieneCalificacionPositivaBuro(Precalificacion precalificacion,
			ResumenConsolidado resumenConsolidado) {
		String calificacionPositiva = "1";
		Requisito rq = new Requisito();
		rq
				.setDescripcion("TENER CALIFICACION A O B EN EL BURO DE CREDITO CERTIFICADO POR LA SUPERINTENDENCIA DE BANCOS");

		boolean tieneCalificacionpositiva = calificacionPositiva
				.equals(resumenConsolidado.getCalificacionBuroCredito());
		log.debug("Calificaicion buro: " + resumenConsolidado.getCalificacionBuroCredito());

		if (tieneCalificacionpositiva) {
			rq.setAprobado(true);
			rq.setObservacion("SI CALIFICO BURO DE CREDITO");
			precalificacion.setCalificadoBuroCredito(true);
		} else {
			rq.setAprobado(false);
			if ("".equals(resumenConsolidado.getCalificacionBuroCredito())) {
				rq.setObservacion("NO SE HA ENCONTRADO INFORMACION DE CALIFICACION DEL BURO DE CREDITO");
			} else {
				rq.setObservacion("NO CALIFICO BURO DE CREDITO");
			}
			precalificacion.setCalificadoBuroCredito(false);
		}
		precalificacion.getRequisitos().add(rq);
		return tieneCalificacionpositiva;
	}

	public static boolean tieneCreditosVigentesConcesion(Precalificacion precalificacion, ResumenConsolidado resumenConsolidado) {
		String existeUnCredito = "1";
		Requisito rq = new Requisito();
		rq.setDescripcion("NO TENER CREDITO VIGENTE CON EL IESS - ANTERIOR SISTEMA HOST");
 
		//Eliminar validación si tiene credito pq vigente
		//CB 10/12/2010
		//Eliminar validación para multiples pqs
		//CB 09/06/2011
		boolean tieneCreditosVigentes = existeUnCredito.equals(resumenConsolidado.getCreditoVigenteHost())
				|| existeUnCredito.equals(resumenConsolidado.getCreditoEspecial());

		log.debug("resumenConsolidado.getCreditoVigenteHl(): " + resumenConsolidado.getCreditoVigenteHl());
		log.debug("resumenConsolidado.getCreditoVigenteHost(): " + resumenConsolidado.getCreditoVigenteHost());
		log.debug("resumenConsolidado.getCreditoEspecial(): " + resumenConsolidado.getCreditoEspecial());
		log.debug("resumenConsolidado.getTieneHipotecarioVigenteHl" + resumenConsolidado.getTieneHipotecarioVigenteHl());

		if (tieneCreditosVigentes) {
			rq.setAprobado(false);
			rq.setObservacion("TIENE ACTUALMENTE CREDITOS VIGENTES EN EL IESS");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("NO TIENE CREDITOS VIGENTES EN EL IESS");
		}
		precalificacion.getRequisitos().add(rq);
		return tieneCreditosVigentes;
	}

	/**
	 * Validacion si existen creditos subgarantizados
	 * @param precalificacion
	 * @param params
	 * @return
	 */
	public static boolean existenCreditosSubgarantizados(Precalificacion precalificacion, ParamsReglasPrecalificacion params) {
		Requisito rq = new Requisito();
		rq.setDescripcion("NO DISPONER DE CR\u00C9DITOS SUB GARANTIZADOS");
		boolean tieneCreditoSubGarantizado =params.getGarantiaCesantia().getTotalCesantia().signum()<0;

		if (tieneCreditoSubGarantizado) {
			rq.setAprobado(false);
			rq.setObservacion("DISPONE DE CR\u00C9DITOS SUB GARANTIZADOS");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("NO DISPONE DE CR\u00C9DITOS SUB GARANTIZADOS");
		}
		precalificacion.getRequisitos().add(rq);
		return tieneCreditoSubGarantizado;
	}

	
	
	public static boolean tieneCreditosEnMora(Precalificacion precalificacion, ResumenConsolidado resumenConsolidado, DatosBiessPublicos datosBiessPublicos, DatosBiessEmergente datosBiessEmergente) {
		String existeMora = "1";
		Requisito rq = new Requisito();
		rq.setDescripcion("NO TENER CREDITO EN MORA CON EL IESS O BIESS");
        
		//Se aumenta validación si tiene un hipotecario en mora
		//CB 02/02/2011
		//Creditos No emergentes
		boolean estaEnMora = existeMora.equals(resumenConsolidado.getCreditosHost())
				|| existeMora.equals(resumenConsolidado.getCreditosHistoriaLaboral())
				|| existeMora.equals(resumenConsolidado.getTieneCreditoHipotecarioMoraHl())
				|| existeMora.equals(resumenConsolidado.getTieneCreditoHipotecarioMoraHost());
		
		// Solo si es beneficiario sector publico
		if (datosBiessPublicos != null && datosBiessPublicos.isValidaPublicos()) {
			estaEnMora = existeMora.equals(resumenConsolidado.getCreditosHost())
					|| existeMora.equals(resumenConsolidado.getTieneCreditoHipotecarioMoraHost())
					|| datosBiessPublicos.isTieneMoraComprobantePublicos();
		}
		
		//sOLO SI ES EMERGENTE
		if (datosBiessEmergente != null && datosBiessEmergente.isValidaBiessEmergente()) {
			// Para creditos solo se valida PQ y PH Host y comprobantes en mora
			estaEnMora = existeMora.equals(resumenConsolidado.getCreditosHost())
					|| existeMora.equals(resumenConsolidado.getTieneCreditoHipotecarioMoraHost()) || datosBiessEmergente.isTieneMoraComprobante();
		}
		log.debug("Creditos en mora:");
		log.debug("resumenConsolidado.getCreditosHost(): " + resumenConsolidado.getCreditosHost());
		log
				.debug("resumenConsolidado.getCreditosHistoriaLaboral(): "
						+ resumenConsolidado.getCreditosHistoriaLaboral());

		if (estaEnMora) {
			rq.setAprobado(false);
			rq.setObservacion("EL AFILIADO TIENE CREDITOS EN MORA, FAVOR ACERCARSE A LAS OFICINAS DE CREDITO");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("EL AFILIADO NO TIENE CREDITOS EN MORA CON EL IESS O BIESS");
		}
		precalificacion.getRequisitos().add(rq);
		return estaEnMora;
	}

	public static boolean esFallecido(Precalificacion precalificacion, ResumenConsolidado resumenConsolidado) {
		Requisito rq = new Requisito();
		log.debug("FAllecido: " + resumenConsolidado.getFallecido());
		rq.setDescripcion("NO CONSTAR CON REGISTRO DE FECHA DE FALLECIMIENTO EN EL IESS");

		boolean esFallecido = "1".equals(resumenConsolidado.getFallecido());

		if (esFallecido) {
			rq.setAprobado(false);
			rq.setObservacion("LA PERSONA CONSTA CON REGISTRO");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("LA PERSONA NO CONSTA CON REGISTRO");
		}
		precalificacion.getRequisitos().add(rq);
		return esFallecido;
	}

	public static boolean esEnfermoTerminal(Precalificacion precalificacion, ResumenConsolidado resumenConsolidado) {
		Requisito rq = new Requisito();
		log.debug("enfermo terminal: " + resumenConsolidado.getTieneEnfermedadTerminal());
		rq.setDescripcion("VALIDACIONES BIESS");

		boolean esEnfemoTerminal = false;
		
		if (resumenConsolidado.getTieneEnfermedadTerminal().equals("1")  || resumenConsolidado.getTieneEnfermedadTerminal().equals("0"))
		{
			esEnfemoTerminal = true;
		}
		if (esEnfemoTerminal) {
			rq.setAprobado(true);
			/**
			 *  Ricardo Tituaña: INC-5780 se actualizo el tipo de correo de "iess.gov.ec" a "iess.gob.ec"
			 */
			rq.setObservacion("CUMPLE VALIDACIONES BIESS");
		} else {
			rq.setAprobado(false);
			rq.setObservacion("NO CUMPLE VALIDACIONES BIESS");
		}
		precalificacion.getRequisitos().add(rq);
		log.debug("rq.isAprobado(): " + rq.isAprobado());
		return esEnfemoTerminal;
	}

	public static boolean tienePrestamosActivosMismaCuenta(Precalificacion precalificacion,
			ResumenConsolidado resumenConsolidado) {
		Requisito rq = new Requisito();
		rq.setDescripcion("EXISTEN PRESTAMOS VIGENTES EN LA MISMA CUENTA?");

		boolean existenPrestamosMismaCuenta = resumenConsolidado.getTienePrestamosVigentesMismaCuenta();

		if (existenPrestamosMismaCuenta) {
			rq.setAprobado(false);
			rq.setObservacion(resumenConsolidado.getMensajePrestamosMismaCuenta());
		} else {
			rq.setAprobado(true);
			rq.setObservacion(resumenConsolidado.getMensajePrestamosMismaCuenta());
		}
		precalificacion.getRequisitos().add(rq);
		log.debug("rq.isAprobado(): " + rq.isAprobado());
		return existenPrestamosMismaCuenta;
	}
	
	public static boolean tieneGastoAdministrativoPendienteSolicitudPH(Precalificacion precalificacion,
			ResumenConsolidado resumenConsolidado, boolean tieneGastosPendientes) {
		Requisito rq = new Requisito();
		rq.setDescripcion("NO TENER GASTOS DE INSTRUMENTACION PENDIENTES EN SOLICITUD DE PRESTAMOS HIPOTECARIOS INICIADA Y NO CONCLUIDA");

		if (tieneGastosPendientes) {
			rq.setAprobado(false);
			rq.setObservacion("TIENE GASTOS DE INSTRUMENTACION PENDIENTES EN SOLICITUD DE PRESTAMOS HIPOTECARIOS");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("NO TIENE GASTOS DE INSTRUMENTACION PENDIENTES EN SOLICITUD DE PRESTAMOS HIPOTECARIOS");
		}
		precalificacion.getRequisitos().add(rq);
		log.debug("rq.isAprobado(): " + rq.isAprobado());
		return tieneGastosPendientes;
		
	}
	
	public static boolean tieneSolicitudPrestamoHipotecarioEnTramite(Precalificacion precalificacion,
			ResumenConsolidado resumenConsolidado, boolean tieneSolicitudPHEnTramite) {
		Requisito rq = new Requisito();
		rq.setDescripcion("NO TENER UN CREDITO HIPOTECARIO EN TRAMITE");
		
		if (tieneSolicitudPHEnTramite) {
			rq.setAprobado(false);
			rq.setObservacion("TIENE UN CREDITO HIPOTECARIO EN TRAMITE");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("NO TIENE UN CREDITO HIPOTECARIO EN TRAMITE");
		}
		precalificacion.getRequisitos().add(rq);
		log.debug("rq.isAprobado(): " + rq.isAprobado());
		return tieneSolicitudPHEnTramite;
		
	}
	
	/**
	 * Método que valida para la novacion que tenga cancelado un prestamo anterior
	 * un determinado porcentaje
	 * @param precalificacion
	 * @param resumenConsolidado
	 * @param porcentajeValidar
	 * @return
	 */
	public static boolean tieneCanceladoPorcentajePrestamoAnterior(Precalificacion precalificacion,
			ResumenConsolidado resumenConsolidado) {
		Requisito rq = new Requisito();
		float porcentajeValidar = 0f;
		porcentajeValidar = Float.parseFloat(resumenConsolidado.getCreditosHost());
		
		rq.setDescripcion("TENER CANCELADO AL MENOS EL "+porcentajeValidar +"% DEL PRESTAMO QUIROGRAFARIO");
		log.info("EN REGLA DE CANCELACI\u00D3N DE PORCENTAJE CUMPLE EL PLAZO DE CANCELACION: "+resumenConsolidado.isCumplePlazoCancelacionCredito());
		if (!(resumenConsolidado.isCumplePlazoCancelacionCredito())) {
			rq.setAprobado(false);
			rq.setObservacion("EL PRESTAMO QUIROGRAFARIO NO ESTA CANCELADO AL MENOS EL "+porcentajeValidar+"%");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("EL PRESTAMO QUIROGRAFARIO ESTA CANCELADO AL MENOS EL "+porcentajeValidar+"%");
		}
		precalificacion.getRequisitos().add(rq);
		log.debug("El req de porcentaje prestamo es: "+rq.isAprobado());	
		return rq.isAprobado();		
	}
	
	public static boolean tienePrestamoQuirografarioMora(Precalificacion precalificacion,
			ResumenConsolidado resumenConsolidado) {
		Requisito rq = new Requisito();
		rq.setDescripcion("QUE EL PRESTAMO QUIROGRAFARIO NO ESTE EN MORA");
	
		if (resumenConsolidado.isQuirografarioMora()) {
			rq.setAprobado(false);
			rq.setObservacion("EL PRESTAMO QUIROGRAFARIO VIGENTE TIENE DIVIDENDOS EN MORA");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("EL PRESTAMO QUIROGRAFARIO VIGENTE NO TIENE DIVIDENDOS EN MORA");
		}
		precalificacion.getRequisitos().add(rq);
		log.debug("El req de prestamo en mora: "+rq.isAprobado());	
		return resumenConsolidado.isQuirografarioMora();		
	}

	public static boolean tieneDividendoEnPlanilla(Precalificacion precalificacion,
			ResumenConsolidado resumenConsolidado) {
		Requisito rq = new Requisito();
		rq.setDescripcion("QUE LOS DIVIDENDOS NO SE ENCUENTREN EN ESTADO \"EN PLANILLA\"");
	
		if (resumenConsolidado.isDividendosPlanilla()) {
			rq.setAprobado(false);
			rq.setObservacion("LOS DIVIDENDOS SE ENCUENTRAN EN ESTADO \"EN PLANILLA\"");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("LOS DIVIDENDOS NO SE ENCUENTRAN EN ESTADO \"EN PLANILLA\"");
		}
		precalificacion.getRequisitos().add(rq);
		log.debug("El req de tieneDividendoEnPlanilla: "+rq.isAprobado());	
		return resumenConsolidado.isDividendosPlanilla();		
	}
	

	

	public static boolean tieneCreditosVigentesNovacion(Precalificacion precalificacion, ResumenConsolidado resumenConsolidado) {
		String existeUnCredito = "1";
		Requisito rq = new Requisito();
		rq.setDescripcion("NO TENER CREDITOS VIGENTES EN EL SISTEMA HOST DEL IESS");

		boolean tieneCreditosVigentes =  existeUnCredito.equals(resumenConsolidado.getCreditoVigenteHost())
				|| existeUnCredito.equals(resumenConsolidado.getCreditoEspecial());
				/*|| existeUnCredito.equals(resumenConsolidado.getTieneHipotecarioVigenteHl());*/

		log.debug("resumenConsolidado.getCreditoVigenteHl(): " + resumenConsolidado.getCreditoVigenteHl());
		log.debug("resumenConsolidado.getCreditoVigenteHost(): " + resumenConsolidado.getCreditoVigenteHost());
		log.debug("resumenConsolidado.getCreditoEspecial(): " + resumenConsolidado.getCreditoEspecial());
		log.debug("resumenConsolidado.getTieneHipotecarioVigenteHl" + resumenConsolidado.getTieneHipotecarioVigenteHl());

		if (tieneCreditosVigentes) {
			rq.setAprobado(false);
			rq.setObservacion("TIENE CREDITOS VIGENTES CON EL IESS O BIESS");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("EL AFILIADO NO TIENE CREDITOS VIGENTES CON EL IESS O BIESS");
		}
		precalificacion.getRequisitos().add(rq);
		return tieneCreditosVigentes;
	}
	/**
	 * 
	 * <b>
	 * Regla que verifica si tiene PQ en trámite.
	 * </b>
	 * <p>[Author: cbastidas, Date: 10/06/2011]</p>
	 *
	 * @param precalificacion Objeto precalificación
	 * @return boolean Cumple o no cumple el requisito
	 */
	public static boolean tienePQTramite (Precalificacion precalificacion)
	{
		Requisito rq = new Requisito();
		rq.setDescripcion("NO TENER CR\u00C9DITOS EN TR\u00C1MITE");
		
		boolean tieneCreditosTramite =  false;
		rq.setAprobado(true);
		rq.setObservacion("NO DISPONE DE CR\u00C9DITOS EN TR\u00C1MITE");
		
		if (precalificacion.getCumplePqTramite().floatValue() > 0)
		{
			tieneCreditosTramite = true;
			rq.setAprobado(false);
			rq.setObservacion("TIENE CR\u00C9DITOS EN TR\u00C1MITE");
		}
        precalificacion.getRequisitos().add(rq);
        return tieneCreditosTramite;
	}
	
	/**
	 * 
	 * <b>
	 * Regla que verifica si tiene un comprobante de pago individual pendiente.
	 * </b>
	 * <p>[Author: cbastidas, Date: 10/06/2011]</p>
	 *
	 * @param precalificacion Objeto precalificación
	 * @return boolean Cumple o no cumple el requisito
	 */
	public static boolean tieneComprobantePagoIndividual (Precalificacion precalificacion)
	{
		Requisito rq = new Requisito();
		rq.setDescripcion("TIENE UN COMPROBANTE PENDIENTE DE PAGO");
		
		boolean tieneCreditosTramite =  false;
		rq.setAprobado(true);
		rq.setObservacion("NO TIENE COMPROBANTES PENDIENTES DE PAGO");
		
		if (precalificacion.getCumpleComprobante().floatValue() > 0)
		{
			tieneCreditosTramite = true;
			rq.setAprobado(false);
			rq.setObservacion("TIENE COMPROBANTES PENDIENTES DE PAGO");
		}
        precalificacion.getRequisitos().add(rq);
        return tieneCreditosTramite;
	}
		
	
	/**
	 * Método que agrega requisito de precalificación que valida si el jubilado tiene créditos cancelados por fallecimiento.
	 * 
	 * @param precalificacion
	 * @param tieneCFA
	 * @return boolean
	 */
	public static boolean tieneCreditosCFA(Precalificacion precalificacion, boolean tieneCFA) {
		Requisito rq = new Requisito();
		rq.setDescripcion("TENER CR\u00C9DITOS CANCELADOS POR FALLECIMIENTO");
		if (!tieneCFA) {
			rq.setAprobado(true);
			rq.setObservacion("NO TIENE CR\u00C9DITOS CANCELADOS POR FALLECIMIENTO");
			precalificacion.getRequisitos().add(rq);
			
			return tieneCFA;
		} else {
			rq.setAprobado(false);
			rq.setObservacion("TIENE UNO O M\u00C1S CR\u00C9DITOS CANCELADOS POR FALLECIMIENTO");
			precalificacion.getRequisitos().add(rq);
			
			return tieneCFA;
		}				
	}
	
	/**
	 * Verifica si un Afiliado/Jubilado esta en alguna lista de observados.
	 * 
	 * @param precalificacion
	 *            - datos de la preclalificacion.
	 * @param listaObservados
	 *            - lista de observados.
	 * 
	 * @return true si consta en alguna lista, caso cotrario false.
	 */
	public static boolean constaListaObservados(Precalificacion precalificacion, ParamsReglasPrecalificacion parametros) {

		// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
		Requisito rq = new Requisito();

		rq.setDescripcion("NO CONSTAR DENTRO DE LAS LISTAS DE OBSERVADOS NACIONALES E INTERNACIONALES.");
		log.debug("Validando lista de observados");

		boolean estaEnLista = (parametros.getTipoListaControl() != null && (parametros.getTipoListaControl()
				.equalsIgnoreCase(TipoListaEnum.CONSEP.toString()) || parametros.getTipoListaControl()
				.equalsIgnoreCase(TipoListaEnum.OTRO.toString())));

		if (estaEnLista) {
			rq.setAprobado(false);
			rq.setObservacion("USTED CONSTA DENTRO DE LAS LISTAS DE OBSERVADOS NACIONALES E INTERNACIONALES");
		} else {
			rq.setAprobado(true);
			rq.setObservacion("USTED NO CONSTA DENTRO DE LAS LISTAS DE OBSERVADOS NACIONALES E INTERNACIONALES");
		}

		return estaEnLista;
	}
}
