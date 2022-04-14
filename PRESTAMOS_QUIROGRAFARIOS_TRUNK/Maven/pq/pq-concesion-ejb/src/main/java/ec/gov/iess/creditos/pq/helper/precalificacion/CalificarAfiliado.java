package ec.gov.iess.creditos.pq.helper.precalificacion;


import ec.fin.biess.creditos.pq.modelo.dto.ParamsReglasPrecalificacion;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.helper.reglas.DatosBiessEmergente;
import ec.gov.iess.creditos.pq.helper.reglas.ReglasAfiliado;
import ec.gov.iess.creditos.pq.helper.reglas.ReglasGeneral;

/**
 * Calificar afiliado
 * 
 * @author pjarrin
 * 
 */
public class CalificarAfiliado {
	private static LoggerBiess log = LoggerBiess.getLogger(CalificarAfiliado.class);

	public static void calificar(Precalificacion precalificacion,
			ResumenConsolidado resumenConsolidado,
			TipoPrestamista tipoPrestamista, String prestacionesJubilado,
			ParamsReglasPrecalificacion params)
			throws PrecalificacionExcepcion {
		
		// Setear informacion de Biess Emergente
		DatosBiessEmergente datosBiessEmergente = null;
		if (params.isValidaBiessEmergente()) {
			datosBiessEmergente = new DatosBiessEmergente();
			datosBiessEmergente.setValidaBiessEmergente(true);
			datosBiessEmergente.setAportacionesAcumuladas(params.getNumeroImposicionesEmergente());
			datosBiessEmergente.setAportacionesConsecutivas(params.getImposicionesConsecutivasEmergente());
			datosBiessEmergente.setTieneMoraPlanillas(params.isParamMoraPlanillaEmergente());
		}
		
		boolean esNovacion = precalificacion
				.getValidarRequisitosPrecalificacion().isNovacion();

		if (!ReglasAfiliado.validacionSBU(precalificacion, params.getSumaSaldos(), params.getSbu(), params.getNumsbu())) {
			precalificacion.setCalificado(false);
		}

		if (!ReglasAfiliado.validarTieneGarantiasAfiliadoVoluntario(
				precalificacion, resumenConsolidado)) {
			log.debug("no pasó validarTieneGarantiasAfiliadoVoluntario");
			precalificacion.setCalificado(false);
		}

		// Valida si tiene el numero correcto de imposiciones
		if (!ReglasAfiliado.numeroImposiciones(precalificacion, resumenConsolidado, params.isDiscapacitado(), datosBiessEmergente,
				params.getNumeroAportacionesAcumuladasRequeridas())) {
			log.debug("No paso numeroImposiciones");
			precalificacion.setCalificado(false);
		}

		if (!ReglasAfiliado.numeroImposicionesConsecutivas(precalificacion,
				resumenConsolidado, params.isDiscapacitado(), datosBiessEmergente, params.getNumeroAportacionesConsecutivasRequeridas())) {
			log.debug("No paso numeroImposicionesConsecutivas");
			precalificacion.setCalificado(false);
		}
		// Valida si el afiliado es activo
		if (!ReglasAfiliado.afiliadoEsActivo(precalificacion,
				resumenConsolidado)) {
			log.debug("No paso afiliadoEsActivo");
			precalificacion.setCalificado(false);
		}

		// Valida si el empleador no pertenezca al seguro social campesino
		if (ReglasAfiliado.empleadorPerteneceSsc(precalificacion,
				resumenConsolidado)) {
			log.debug("No paso empleadorPerteneceSsc");
			precalificacion.setCalificado(false);
		}

		if (ReglasAfiliado.tieneSolicitudCesantia(precalificacion,
				resumenConsolidado)) {
			log.debug("No paso tieneSolicitudCesantia");
			precalificacion.setCalificado(false);
		}
		if (ReglasAfiliado.suEmpleadorEstaEnMora(precalificacion,resumenConsolidado, datosBiessEmergente, params.isTieneMoraPatronal())) {
			log.debug("No paso, sus empleadores estan en mora");
			precalificacion.setCalificado(false);
		}
		
		/**
		 * Se valida la existencia de creditos subgarantizados
		 */
		if (ReglasGeneral.existenCreditosSubgarantizados(precalificacion, params)) {
			precalificacion.setCalificado(false);
		}
		try {
			if (esNovacion) {
				if (!ReglasAfiliado.validarGarantiasNovacion(precalificacion)) {
					log.debug("No paso validacion garantias de novacion");
					precalificacion.setCalificado(false);
				}
			}
		} catch (PrecalificacionExcepcion e) {
			throw new PrecalificacionExcepcion(e);
		}
	}
}
