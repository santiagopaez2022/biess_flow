package ec.fin.biess.creditos.pq.servicio;

import ec.fin.biess.creditos.pq.excepcion.RegistroCivilBiessException;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionRegistroCivil;
import ec.fin.biess.enumeraciones.AplicativoEnum;

import javax.ejb.Local;

/**
 * Servicio para consumir el servicio web del registro civil
 * 
 * @author hugo.mora
 *
 */
@Local
public abstract interface RegistroCivilBiessServicioT {

	/**
	 * Consulta al servicio web del registro civil
	 * 
	 * @param cedula
	 * @param cedulaFuncionario
	 * @param direccionIP
	 * @param aplicativo
	 * @return
	 * @throws RegistroCivilBiessException
	 */
	abstract InformacionRegistroCivil consultarWS(String cedula, String cedulaFuncionario, String direccionIP, AplicativoEnum aplicativo)
			throws RegistroCivilBiessException;

}