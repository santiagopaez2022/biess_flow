package ec.gov.iess.pq.concesion.simulador.util;

import java.util.ArrayList;
import java.util.List;

import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;

/**
 * Contiene metodos utilitarios para el simulador
 * 
 * @author hugo.mora
 *
 */
public class SimuladorUtil {

	/**
	 * Devuelve una lista de requisitos no bloqueantes
	 * 
	 * @param precalificacion
	 * @return
	 */
	public static List<Requisito> obtenerRequisitosNoBloqueantes(Precalificacion precalificacion) {
		List<Requisito> requisitos = new ArrayList<Requisito>();
		if (precalificacion != null && !precalificacion.getRequisitos().isEmpty()) {
			for (Requisito requisito : precalificacion.getRequisitos()) {
				if (!requisito.isAprobado()) {
					requisitos.add(requisito);
				}
			}
		}

		return requisitos;
	}

}
