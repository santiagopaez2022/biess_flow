package ec.fin.biess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.TablaAmortizacionException;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DividendoCalculo;

/**
 * Interfaz local para tablas de amortizacion
 * 
 * @author diana.suasnavas
 *
 */
@Local
public interface TablaAmortizacionService {

	/**
	 * Metodo para obtener la tabla de amortizacion segun tipo
	 * 
	 * @param datosCredito
	 * @param esEmergente
	 * @return lista Dividendos calculos
	 * @throws TablaAmortizacionException
	 */
	List<DividendoCalculo> obtenerTablaAmortizacionPorTipo(DatosCredito datosCredito, boolean esEmergente) throws TablaAmortizacionException;
}
