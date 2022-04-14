package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

@Remote
public interface OrigenJubiladoServicioRemote {
	/**
	 * Se encarga de determinar cual es el origen del jubilado
	 * 
	 * @param cedula
	 * @return
	 */
	// TotalRentaPorNominaOrigen obtenerOrigenJubilado(String cedula);

	/**
	 * Se encarga de determiar una lista de totales de valores de renta de un jubilado clasificados por origen
	 * 
	 * @param cedula
	 * @return
	 */
	// List<TotalRentaPorNominaOrigen> obtenerTotalRentasPorOrigen(String cedula);
}
