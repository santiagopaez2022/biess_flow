package ec.gov.iess.creditos.pq.helper.reglas;

import java.util.Calendar;
import java.util.GregorianCalendar;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.enumeracion.TipoPrecalificacionEnum;
import ec.gov.iess.creditos.enumeracion.TipoRequisitoSimulacionEnum;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;

public class ReglasZafrero {
	static LoggerBiess log = LoggerBiess.getLogger(ReglasZafrero.class);

	public static boolean numeroImposicionesConsecutivasZafrero(
			Precalificacion precalificacion,
			ResumenConsolidado resumenConsolidado, boolean discapacitado) {
		Requisito rq = new Requisito();
		/* Validacion para afiliados normales */
		int numeroImpoConsecutivasAfiliado = resumenConsolidado.getNunmeroImpoConsecutivas().intValue();
		rq = obtieneRequisitoImpoConsecutivasZafrero(discapacitado, numeroImpoConsecutivasAfiliado, precalificacion.getTipoPrecalificacionEnum());
		precalificacion.getRequisitos().add(rq);
		return rq.isAprobado();
	}
	
	/**
	 * Devuelve un Requisito con la validacion de aportaciones consecutivas para zafrero
	 * 
	 * @param esDiscapacitado
	 * @param numeroImpoConsecutivasAfiliado
	 * @return
	 */
	public static Requisito obtieneRequisitoImpoConsecutivasZafrero(boolean esDiscapacitado, int numeroImpoConsecutivasAfiliado,
			TipoPrecalificacionEnum tipoPrecalificacion) {
		Requisito requisito = new Requisito();
		requisito.setTipoRequisitoSimulacion(TipoRequisitoSimulacionEnum.BLOQUEANTE);
		int numeroImpoConsecutivas = 6;

		requisito.setDescripcion("TENER LAS " + numeroImpoConsecutivas
				+ " \u00DALTIMAS APORTACIONES CONSECUTIVAS CORRESPONDIENTES AL PERIODO DE ZAFRA (SEGUNDO SEMESTRE DEL A\u00D1O ANTERIOR)");

		/* INC-1719 2013-11-13 Agregar preferencias para afiliados discapacitados */
		if (esDiscapacitado) {
			requisito.setObservacion("NO APLICA PARA AFILIADO DISCAPACITADO");
			requisito.setAprobado(true);
		} else {
			boolean cumpleCondicion = numeroImpoConsecutivasAfiliado >= numeroImpoConsecutivas;
			if (cumpleCondicion) {
				requisito.setObservacion("EL AFILIADO TIENE " + numeroImpoConsecutivas + " APORTACIONES CONSECUTIVAS");
				requisito.setAprobado(true);
			} else {
				if (TipoPrecalificacionEnum.SIMULACION == tipoPrecalificacion) {
					int imposicionesFalta = numeroImpoConsecutivas - numeroImpoConsecutivasAfiliado;
					requisito.setObservacion("A LA FECHA USTED REGISTRA " + String.valueOf(numeroImpoConsecutivasAfiliado)
							+ " \u00DALTIMAS APORTACIONES CONSECUTIVAS, LE FALTA " + String.valueOf(imposicionesFalta) + " APORTACIONES.");
				} else {
					requisito.setObservacion("NO TIENE LAS \u00DALTIMAS APORTACIONES CONSECUTIVAS, POR FAVOR ACERCARSE A AFILIACI\u00D3N Y CONTROL");
				}
				requisito.setAprobado(false);
			}
		}

		return requisito;
	}

	public static boolean esposibleSolicitarCredito(Precalificacion precalificacion,
			ResumenConsolidado resumenConsolidado){
		Calendar hoy = new GregorianCalendar();
		Requisito rq = new Requisito();
		rq
				.setDescripcion("SU CONDICIÓN DE AFILIADO ES ZAFRERO TEMPORAL");
		int mesPermitido = Integer.valueOf(Messages.getString("mes.concesion.zafreros")).intValue() - 1;
		boolean esJunio = hoy.get(Calendar.MONTH) == mesPermitido;
		//boolean esJunio = true;
		
		if(esJunio){
			rq.setAprobado(true);
			rq.setObservacion("NOS ENCONTRAMOS EN EL MES DE JUNIO, USTED PUEDE SOLICITAR SU PRESTAMO QUIROGRAFARIO");
		}else{
			rq.setAprobado(false);
			rq.setObservacion("USTED PUEDE SOLICITAR SU PRÉSTAMO QUIROGRAFARIO UNICAMENTE EN  EL MES DE JUNIO");
		}
		
		precalificacion.getRequisitos().add(rq);
		
		return esJunio;
	}

}
