package ec.gov.iess.creditos.pq.helper.precalificacion;


import ec.fin.biess.creditos.pq.modelo.dto.ParamsReglasPrecalificacion;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.pq.helper.reglas.DatosBiessEmergente;
import ec.gov.iess.creditos.pq.helper.reglas.ReglasAfiliado;
import ec.gov.iess.creditos.pq.helper.reglas.ReglasGeneral;
import ec.gov.iess.creditos.pq.helper.reglas.ReglasZafrero;

public class CalificarZafrero {
	private static LoggerBiess log = LoggerBiess.getLogger(CalificarZafrero.class);

	public static void calificar(Precalificacion precalificacion, ResumenConsolidado resumenConsolidado,
			//TipoPrestamista tipoPrestamista, String prestacionesJubilado, Solicitante solicitante, boolean discapacitado) {
			TipoPrestamista tipoPrestamista, String prestacionesJubilado, ParamsReglasPrecalificacion params) {		
		// Setear informacion de Biess Emergente
		DatosBiessEmergente datosBiessEmergente = null;
		if (params.isValidaBiessEmergente()) {
			datosBiessEmergente = new DatosBiessEmergente();
			datosBiessEmergente.setValidaBiessEmergente(true);
			datosBiessEmergente.setAportacionesAcumuladas(params.getNumeroImposicionesEmergente());
			datosBiessEmergente.setAportacionesConsecutivas(params.getImposicionesConsecutivasEmergente());
		}
		if (!ReglasZafrero.esposibleSolicitarCredito(precalificacion, resumenConsolidado)) {
			log.debug("No pasó validación fecha de consecion zafreros");
			precalificacion.setCalificado(false);
		}
		
		if (!ReglasAfiliado.validarTieneGarantiasAfiliadoVoluntario(precalificacion, resumenConsolidado)) {
			log.debug("no pasó validarTieneGarantiasAfiliadoVoluntario");
			precalificacion.setCalificado(false);
		}

		// Valida si tiene el numero correcto de imposiciones
		if (!ReglasAfiliado.numeroImposiciones(precalificacion, resumenConsolidado, params.isDiscapacitado(), datosBiessEmergente,
				params.getNumeroAportacionesAcumuladasRequeridas())) {
			log.debug("No paso numeroImposiciones");
			precalificacion.setCalificado(false);
		}

		if (!ReglasZafrero.numeroImposicionesConsecutivasZafrero(precalificacion, resumenConsolidado, params.isDiscapacitado())) {
			log.debug("No pasó validación Imposiciones consecutivas");
			precalificacion.setCalificado(false);
		}

		// Valida si el afiliado es activo
		if (!ReglasAfiliado.afiliadoEsActivo(precalificacion, resumenConsolidado)) {
			log.debug("No paso afiliadoEsActivo");
			precalificacion.setCalificado(false);
		}

		// Valida si el empleador no pertenezca al seguro social campesino
		if (ReglasAfiliado.empleadorPerteneceSsc(precalificacion, resumenConsolidado)) {
			log.debug("No paso empleadorPerteneceSsc");
			precalificacion.setCalificado(false);
		}

		if (ReglasAfiliado.tieneSolicitudCesantia(precalificacion, resumenConsolidado)) {
			log.debug("No paso tieneSolicitudCesantia");
			precalificacion.setCalificado(false);
		}

		if (ReglasAfiliado.suEmpleadorEstaEnMora(precalificacion, resumenConsolidado,datosBiessEmergente, params.isTieneMoraPatronal())) {
			log.debug("No paso, sus empleadores estan en mora");
			precalificacion.setCalificado(false);
		}
		
		/**
		 * Se valida la existencia de creditos subgarantizados
		 */
		if (ReglasGeneral.existenCreditosSubgarantizados(precalificacion, params)) {
			precalificacion.setCalificado(false);
		}
	}

}
