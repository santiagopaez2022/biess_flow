
package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.excepcion.CabeceraCreditoQuirografarioException;


@Local
public interface PrestamocabeceraServicio {

	/**
	 * 
	 * Creacion de la cabecera del credito quirografario
	 * 
	 * @param prestamo
	 * @return
	 * @throws CabeceraCreditoQuirografarioException
	 */
	Prestamo crearPrestamoPq(DatosCredito prestamo) throws CabeceraCreditoQuirografarioException;

}
