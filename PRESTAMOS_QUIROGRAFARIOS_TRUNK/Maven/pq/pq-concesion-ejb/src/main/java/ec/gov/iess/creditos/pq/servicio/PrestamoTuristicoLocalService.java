package ec.gov.iess.creditos.pq.servicio;

import java.util.Map;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.PrestamoPqCoreException;
import ec.gov.iess.creditos.pq.excepcion.CodigoReservaPaqueteTuristicoException;
import ec.gov.iess.creditos.pq.excepcion.PrestamosTuristicosException;
import ec.gov.iess.creditos.turismo.dto.PaqueteTurismoInfoDto;

/**
 * Servicio local para manejo de logica de negocio de prestamos turisticos
 * 
 * @author hugo.mora
 *
 */
@Local
public interface PrestamoTuristicoLocalService {

	/**
	 * Devueve informacion del paquete turistico (descripcion y monto) dada el numero de cedula y el codigo de reserva
	 * 
	 * @param cedula
	 * @param codigoReserva
	 * @return Devuelve un objeto de tipo PaqueteTurismoInfoDto con informacion de la descripcion y monto del paquete
	 *         turistico, en caso de error lanza una excepcion
	 * @throws PrestamosTuristicosException
	 * @throws CodigoReservaPaqueteTuristicoException
	 */
	PaqueteTurismoInfoDto consultarInformacionPaqueteTuristico(String cedula, String codigoReserva)
			throws PrestamosTuristicosException, CodigoReservaPaqueteTuristicoException;

	/**
	 * Indica si se confirmo la anulacion del credito por parte del proveedor de servicios turisticos
	 * 
	 * @param cedula
	 * @param codigoReserva
	 * @return Devuelve true si se confirmo la anulacion o una excepcion con la descripcion del problema
	 * @throws PrestamosTuristicosException
	 */
	boolean rechazaCreditoTuristico(String cedula, String codigoReserva) throws PrestamosTuristicosException;

	/**
	 * Envia la notificacion de anulacion al proveedor externo de creditos turisticos Ecuador tu lugar en el mundo, si
	 * se recibe una confirmacion exitosa del proveedor se procede a anular el credito del lado del BIESS
	 * 
	 * @param parametrosCredito
	 * @param estadoActualCredito
	 * @param cedula
	 * @param codigoReserva
	 * @throws PrestamosTuristicosException
	 * @throws PrestamoPqCoreException
	 */
	void rechazarCreditoTuristicoConProcedimiento(Map<String, Long> parametrosCredito, String estadoActualCredito, String cedula,
			String codigoReserva) throws PrestamosTuristicosException, PrestamoPqCoreException;

}
