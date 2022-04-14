/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;

import javax.ejb.Local;

import ec.gov.iess.creditos.enumeracion.CodigoEstadoGarantiaFondos;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.excepcion.DescomprometerGarantiaException;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface GarantiaPrestamoServicio {

	/**
	 * Ingresa las garantias del credito concedido
	 * 
	 * @param cedula
	 * @param numeroPrestamo
	 * @param idTipocredito
	 * @param idVariedadCredito
	 * @param fechaSolicitud
	 * @param montocredito
	 * @author cvillarreal
	 */
	public void crearGarantiaPq(DatosGarantia garantia);

	public void descomprometerGarantias(Prestamo pre,
			CodigoEstadoGarantiaFondos estadoActualGarantia,
			CodigoEstadoGarantiaFondos estadoNuevoGarantia )
			throws DescomprometerGarantiaException;

	public boolean descomprometerGarantiasFondos(Prestamo pre,
			CodigoEstadoGarantiaFondos estadoActualGarantia,
			CodigoEstadoGarantiaFondos estadoNuevoGarantia)
			throws DescomprometerGarantiaException;

	public void descomprometerGarantiaCesantias(Prestamo pre) throws DescomprometerGarantiaException;

	/**
	 * Consulta las garantias de cesantia que tiene un cliente por identificacion
	 * KSAFITCESANTIAS
	 * 
	 * @param identificacion
	 * @return retonar entity GarantiaCesantia con datos de la tabla
	 */
	GarantiaCesantia consultarCesantiaCliente(String identificacion);
	
	
	BigDecimal consultarTotalFondosReserva(String identificacion);

}
