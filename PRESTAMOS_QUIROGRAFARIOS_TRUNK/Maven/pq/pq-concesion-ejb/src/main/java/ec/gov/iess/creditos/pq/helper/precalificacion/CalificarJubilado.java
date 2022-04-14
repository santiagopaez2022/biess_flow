package ec.gov.iess.creditos.pq.helper.precalificacion;


import ec.fin.biess.creditos.pq.modelo.dto.ParamsReglasPrecalificacion;
import ec.fin.biess.creditos.pq.modelo.dto.PrestacionPensionado;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.helper.reglas.ReglasAfiliado;
import ec.gov.iess.creditos.pq.helper.reglas.ReglasJubilado;

/**
 * Calificar jubilado
 * 
 * @author pjarrin
 * 
 */
public class CalificarJubilado {
	static LoggerBiess log = LoggerBiess.getLogger(ReglasAfiliado.class);

	public static void calificar(final Precalificacion precalificacion,
			final ResumenConsolidado resumenConsolidado,
			final TipoPrestamista tipoPrestamista, final String prestacionesJubilado,
			//BigDecimal sumaSaldos, BigDecimal sbu, BigDecimal numsbu) throws PrecalificacionExcepcion {
			final ParamsReglasPrecalificacion params) throws PrecalificacionExcepcion {


		if (!ReglasJubilado.validacionSBU(precalificacion, params.getSumaSaldos(), params.getSbu(), params.getNumsbu())) {
			precalificacion.setCalificado(false);
		}

		// INC-2135 Pensiones Jubilados/Pensionistas: se valida si el jubilado es pensionista verificando si existen prestaciones
		if (!ReglasJubilado.esPensionista(params.getInformacionPrestacionPensionado(),precalificacion, prestacionesJubilado)) {
			precalificacion.setCalificado(false);
		}

		// solamente si es pensionista de invalidez se aplica esta regla
		if (null != params.getInformacionPrestacionPensionado()
				&& null != params.getInformacionPrestacionPensionado().getListaPrestaciones()
				&& !params.getInformacionPrestacionPensionado().getListaPrestaciones().isEmpty()
				&& params.getInformacionPrestacionPensionado().getListaPrestaciones().size() == 1) {
			final PrestacionPensionado p = params.getInformacionPrestacionPensionado().getListaPrestaciones().get(0);
			if (p.getTipoprestacion().equals("IN")) {
				if (!ReglasJubilado.tieneIncapacidadPermanete(precalificacion, prestacionesJubilado, p)) {
					precalificacion.setCalificado(false);
				}
			}
		}
		
		// INC-2135 Pensiones Jubilados/Pensionistas
		if (!ReglasJubilado.validarPrestaciones(precalificacion, params.getInformacionPrestacionPensionado(),
				params.getValorRetencionesJudiciales())) {
			precalificacion.setCalificado(false);
			log.debug("No pasa la regla de validar prestaciones");
		}


	}
}
