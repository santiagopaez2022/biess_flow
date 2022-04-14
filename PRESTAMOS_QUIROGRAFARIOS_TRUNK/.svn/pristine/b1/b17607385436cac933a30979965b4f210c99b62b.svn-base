package ec.gov.iess.creditos.pq.util;

import java.util.ArrayList;

import ec.fin.biess.creditos.pq.modelo.dto.ParamsReglasPrecalificacion;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.helper.precalificacion.CalificacionGeneral;
import ec.gov.iess.creditos.pq.helper.precalificacion.CalificarAfiliado;
import ec.gov.iess.creditos.pq.helper.precalificacion.CalificarJubilado;
import ec.gov.iess.creditos.pq.helper.precalificacion.CalificarZafrero;

/**
 * @author pmlopez
 * 
 */
public class ValidadorPrecalificacion {

	static LoggerBiess logger = LoggerBiess.getLogger(ValidadorPrecalificacion.class);

	public static void ejecutarReglasJava(Precalificacion precalificacion,
			//ResumenConsolidado resumenConsolidado, BigDecimal sumaSaldos, BigDecimal sbu, BigDecimal numsbu, boolean tieneCFA, boolean discapacitado)
			ResumenConsolidado resumenConsolidado, ParamsReglasPrecalificacion params)
			throws PrecalificacionExcepcion {

		// Asumimos de entrada que esta calificado para el credito
		precalificacion.setCalificado(true);

		// Inicializamos la lista de requisitos
		precalificacion.setRequisitos(new ArrayList<Requisito>());

		TipoPrestamista tipoPrestamista = precalificacion
				.getValidarRequisitosPrecalificacion().getTipoPrestamista();
		// INC-2135 Pensiones Jubilados/Pensionistas
		String prestacionesJubilado="";
		if (TipoPrestamista.JUBILADO.equals(tipoPrestamista)
				&& params.getInformacionPrestacionPensionado()!=null){
			prestacionesJubilado = GeneradorListaPrestaciones.concatenarDescripcionPrestaciones(params
					.getInformacionPrestacionPensionado().getListaPrestaciones());
		}else{
			prestacionesJubilado = resumenConsolidado.getNombresPrestaciones();
		}
		
		try {
			if (TipoPrestamista.AFILIADO.equals(tipoPrestamista)) {
				CalificarAfiliado.calificar(precalificacion,
						resumenConsolidado, tipoPrestamista,
						prestacionesJubilado,
						params);
			} else if (TipoPrestamista.JUBILADO.equals(tipoPrestamista)) {
				CalificarJubilado.calificar(precalificacion,
						resumenConsolidado, tipoPrestamista,
						prestacionesJubilado, params);
			} else if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(tipoPrestamista)) {
				CalificarZafrero.calificar(precalificacion, resumenConsolidado,
						tipoPrestamista, prestacionesJubilado,
						params);
			} else {
				throw new PrecalificacionExcepcion(
						"NO EXISTEN REGLAS PARA APLICAR AL TIPO DE SOLICITANTE: "
								+ tipoPrestamista);
			}
			// Requisitos generales
			CalificacionGeneral.calificar(precalificacion, resumenConsolidado, params);
		} catch (PrecalificacionExcepcion e) {
			throw new PrecalificacionExcepcion(e);
		}

	}
	
	/**
	 * Ejecuta las reglas para validar si un Afiliado/Jubilado consta en alguna lista de observados.
	 * 
	 * @param precalificacion
	 *            - datos de la precalificacion.
	 * @param parametros
	 *            - parametros.
	 * 
	 * @throws PrecalificacionExcepcion
	 *             - excepcion.
	 */
	public static void ejecutarReglasJavaListaObservados(Precalificacion precalificacion,
			ParamsReglasPrecalificacion parametros) throws PrecalificacionExcepcion {
		// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
		
		// Inicializamos la lista de requisitos
		if (precalificacion.getRequisitos() == null) {
			precalificacion.setRequisitos(new ArrayList<Requisito>());
		}

		// Requisitos generales
		CalificacionGeneral.calificarListaObservados(precalificacion, parametros);
	}
	
}