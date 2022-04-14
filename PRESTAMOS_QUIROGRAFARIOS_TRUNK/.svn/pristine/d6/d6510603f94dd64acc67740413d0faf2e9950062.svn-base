package ec.fin.biess.creditos.pq.servicio;

import java.math.BigDecimal;

import javax.ejb.Local;

/**
 * Interfaz local para el servicio de RolPeriodoDetalle
 * 
 * @author christian.gomez
 * 
 */
@Local
public interface RolPeriodoDetalleServicioLocal {

	/**
	 * Devuelve la suma de los valores de los detalles encontrados en base al
	 * filtro creado por los parámetros
	 * 
	 * @param identificacion
	 *            Identificacion del jubilado/pensionista
	 * @param anio
	 *            Anio a buscar en los registros
	 * @param mes
	 *            Mes a buscar en los registros
	 * @param rubro
	 *            Tipo de rubro a buscar en los registros
	 * @return {@link BigDecimal}
	 */
	public BigDecimal sumarValorPorIdentificacionAnioMesRubro(
			String identificacion, Long anio, Long mes, Long rubro);
}
