package ec.fin.biess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.PrestacionPensionadosException;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado;

/**
 * Interfaz local para consultar las prestaciones de los pensionados en el cliente de web service del iess
 * 
 * @author christian.gomez
 * 
 */
@Local
public interface ConsultaPrestacionPensionadosServicioLocal {

	/**
	 * Consulta las prestaciones de un jubilado/pensionado
	 * 
	 * @param identificacion
	 *            del jubilado/pensionado
	 * @param direccionIP
	 *            Direccion IP del cliente
	 * @return @link InformacionPrestacionPensionado
	 * @throws PrestacionPensionadosException
	 */
	public InformacionPrestacionPensionado consultarPrestaciones(String identificacion, String direccionIP) throws PrestacionPensionadosException;

}
