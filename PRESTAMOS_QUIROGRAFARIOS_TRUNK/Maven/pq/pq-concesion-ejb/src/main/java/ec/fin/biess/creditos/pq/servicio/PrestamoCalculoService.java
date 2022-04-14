package ec.fin.biess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.TablaAmortizacionException;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;

/**
 * Servicio para calculos de prestamos
 * @author diana.suasnavas
 *
 */
@Local
public interface PrestamoCalculoService {	
	
	/**
	 * Llena todos los datos del prestamo y los dividendos de la tabla de
	 * amortizacion
	 * @param datosCredito
	 * @return PrestamosCalculo
	 * @throws TablaAmortizacionException 
	 */
	PrestamoCalculo poblarPrestamoCalculoNew(DatosCredito datosCredito) throws TablaAmortizacionException;

}
