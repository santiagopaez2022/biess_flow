/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.helper.precalificacion;

import java.util.ArrayList;
import java.util.List;


import ec.fin.biess.creditos.pq.modelo.dto.ParamsReglasPrecalificacion;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.commons.util.date.DateUtil;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.pq.helper.reglas.DatosBiessEmergente;
import ec.gov.iess.creditos.pq.helper.reglas.DatosBiessPublicos;
import ec.gov.iess.creditos.pq.helper.reglas.ReglasGeneral;

public class CalificacionGeneral {
	private static LoggerBiess log = LoggerBiess.getLogger(CalificacionGeneral.class);

	public static void calificar(Precalificacion precalificacion,
			//ResumenConsolidado resumenConsolidado, boolean tieneCFA) {
			ResumenConsolidado resumenConsolidado, ParamsReglasPrecalificacion params) {
		boolean esNovacion = precalificacion
				.getValidarRequisitosPrecalificacion().isNovacion();
				
		// Setear informacion de Biess Emergente
		DatosBiessEmergente datosBiessEmergente = null;
		if (params.isValidaBiessEmergente()) {
			datosBiessEmergente = new DatosBiessEmergente();
			datosBiessEmergente.setValidaBiessEmergente(true);
			datosBiessEmergente.setTieneMoraComprobante(params.isParamMoraComprobanteEmergente());
		}
		
		// Setear informacion para beneficiarios sector publico
		DatosBiessPublicos datosBiessPublicos = null;
		if (params.isValidaPublicos()) {
			datosBiessPublicos = new DatosBiessPublicos();
			datosBiessPublicos.setValidaPublicos(true);
			datosBiessPublicos.setTieneMoraComprobantePublicos(params.isParamMoraComprobantePublicos());
		}

		if (!ReglasGeneral.cuentaValida(precalificacion, resumenConsolidado)) {
			log.debug("No paso cuentaValida");
			precalificacion.setCalificado(false);
		}

	   //Se elimino el prerequisto existen prestamos vigentes en la misma cuenta
		
		if (ReglasGeneral.esFallecido(precalificacion, resumenConsolidado)) {
			log.debug("No paso esFallecido");
			precalificacion.setCalificado(false);
		}
		if (ReglasGeneral.tieneCreditosEnMora(precalificacion,
				resumenConsolidado, datosBiessPublicos, datosBiessEmergente)) {
			log.debug("No paso tieneCreditosEnMora");
			precalificacion.setCalificado(false);
		}

		if (ReglasGeneral
				.tieneObligaciones(precalificacion, resumenConsolidado)) {
			log.debug("No paso tieneObligaciones");
			precalificacion.setCalificado(false);
		}
		//Se cambia control de enfermo terminal, insolventes e interdictos
		if (!ReglasGeneral
				.esEnfermoTerminal(precalificacion, resumenConsolidado)) {
			log.debug("No paso esEnfermoTerminal");
			precalificacion.setCalificado(false);
		}

		// Se aumentan validaciones de solicitud y gastos de hipotecarios
		// CB 09/06/2011
		if (ReglasGeneral.tieneSolicitudPrestamoHipotecarioEnTramite(
				precalificacion, resumenConsolidado, resumenConsolidado
						.isTieneSolicitudPHEnTramite())) {
			log
					.debug("No paso tiene Solicitud de Prestamo Hipotecario en tramite");
			precalificacion.setCalificado(false);
		}
		if (ReglasGeneral.tieneGastoAdministrativoPendienteSolicitudPH(
				precalificacion, resumenConsolidado, resumenConsolidado
						.isTieneGastosPendientes())) {
			log
					.debug("No paso tiene Gastos Administrativos pendientes en Solicitud PH");
			precalificacion.setCalificado(false);
		}
		// CB 10/06/2011
		//Se aumenta validación de generacion de comprobante de pago individual
		if (ReglasGeneral.tieneComprobantePagoIndividual(precalificacion)) {
			log
					.debug("No paso tiene comprobante de pago individual generado");
			precalificacion.setCalificado(false);
		}
		//Se aumenta validación de PQ en trámite
		if (ReglasGeneral.tienePQTramite(precalificacion)) {
			log
					.debug("No paso tiene prestamo quirografario en tramite");
			precalificacion.setCalificado(false);
		}

		/* Validar si el afiliado tiene créditos cancelados por fallecimiento */
		if (ReglasGeneral.tieneCreditosCFA(precalificacion, params.isTieneCFA())) {
			precalificacion.setCalificado(false);
		}
		
		if (esNovacion) {
			if (ReglasGeneral.tieneCreditosVigentesNovacion(precalificacion,
					resumenConsolidado)) {
				log.debug("No paso tieneCreditosVigentes Novacion");
				precalificacion.setCalificado(false);
			}
			
			if (ReglasGeneral.tienePrestamoQuirografarioMora(precalificacion,
					resumenConsolidado)) {
				log.debug("Tien quirografario en mora");
				precalificacion.setCalificado(false);
			}
			if (ReglasGeneral.tieneDividendoEnPlanilla(precalificacion,
					resumenConsolidado)) {
				log.debug("Tiene dividento en planilla");
				precalificacion.setCalificado(false);
			}

		} else {
			if (ReglasGeneral.tieneCreditosVigentesConcesion(precalificacion,
					resumenConsolidado)) {
				log.debug("No paso tieneCreditosVigentes concesion");
				precalificacion.setCalificado(false);
			}
		}
	}

	public static List<Integer> calificarDividendos(
			List<DividendoPrestamo> listdiv) {

		List<Integer> resultado = new ArrayList<Integer>();
		int mora = 0;
		int epl = 0;
		int eco = 0;

		for (DividendoPrestamo div : listdiv) {

			if (div.getEstadoDividendoPrestamo().getCodestdiv().equals("MOR")
					|| (div.getEstadoDividendoPrestamo().getCodestdiv().equals(
							"REG") && DateUtil.compararPeriodos2(div
							.getAniper(), div.getMesper()))) {

				mora = 1;// Mora

			}

			if (div.getEstadoDividendoPrestamo().getCodestdiv().equals("EPL")) {

				epl = 1;// En planilla

			}

			if (div.getEstadoDividendoPrestamo().getCodestdiv().equals("ECO")) {

				eco = 1;// En comprobante

			}

		}
		resultado.add(0, mora);// Mora
		resultado.add(1, epl);// En Planilla
		resultado.add(2, eco);// En Comprobante

		return resultado;
	}

	/**
	 * Verifica si un Afiliado/Jubilado consta en alguna lista de observados.
	 * 
	 * @param precalificacion
	 *            - datos de la precalificacion.
	 * 
	 * @param parametros
	 *            - parametros.
	 */
	public static void calificarListaObservados(Precalificacion precalificacion, ParamsReglasPrecalificacion parametros) {
		// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
		if (ReglasGeneral.constaListaObservados(precalificacion, parametros)) {
			log.debug("El Afiliado/Jubilado consta en Listas de Observados");
			precalificacion.setCalificado(false);
		}
	}
}
