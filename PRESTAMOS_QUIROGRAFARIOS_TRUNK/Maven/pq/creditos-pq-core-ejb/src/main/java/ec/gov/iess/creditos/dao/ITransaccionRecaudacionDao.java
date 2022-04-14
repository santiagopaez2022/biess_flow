/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.TransaccionRecaudacion;
import ec.gov.iess.creditos.modelo.persistencia.pk.TransaccionRecaudacionPk;
import ec.gov.iess.dao.GenericDao;

/**
 * @author roberto.guizado
 *
 */
@Local
public interface ITransaccionRecaudacionDao extends GenericDao<TransaccionRecaudacion, TransaccionRecaudacionPk> {

	/**
	 * Metodo que permite guardar la transaccion
	 * 
	 * @param transaccionRecaudacion
	 */
	void guardarTransaccion(TransaccionRecaudacion transaccionRecaudacion);

	/**
	 * Permite obtener la secuencia de la transaccion
	 * 
	 * @param nombreSecuencia
	 * @return
	 */
	long obtenerValorSecuencial(String nombreSecuencia);

	/**
	 * Permite consultar una transaccion
	 * 
	 * @param nut
	 * @param cedula
	 * @return
	 */
	TransaccionRecaudacion obtenerTransaccion(BigDecimal nut, String cedula);

	/**
	 * Consulta la transaccion para obtener una planilla
	 * 
	 * @param nut
	 * @param cedula
	 * @param codigoSucursal
	 * @param rucEmpresa
	 * @param anio
	 * @param mes
	 * @param secpladet
	 * @param idTipoTransaccion
	 * @return
	 */
	TransaccionRecaudacion obtenerTransaccion(TransaccionRecaudacion transaccionRecaudacion);

	/**
	 * Actualizar el estaddo de la transaccion
	 * 
	 * @param estado
	 * @param fechaProceso
	 * @param secuencia
	 * @param idTipoTransaccion
	 * @param idTransaccion
	 */
	void actualizarEstadoAjustePlanilla(String estado, Date fechaProceso, BigDecimal secuencia, Long idTipoTransaccion,
			Long idTransaccion);

	/**
	 * Metodo que permite obtener las transacciones del cliente
	 * 
	 * @param cedula
	 * @return
	 */
	List<TransaccionRecaudacion> obtenerTransaccionesPorCliente(String cedula);

	/**
	 * Metodo para obtener el saldo de capital de las transacciones en estado EAM
	 * @param identificacion
	 * @return
	 */
	BigDecimal saldoCapitalTotal(String identificacion);

	/**
	 * Obtener un transaccion por mes y mperiod de un cliente
	 * @param nut
	 * @param cedula
	 * @param anioPerio
	 * @param mesPeriod
	 * @return
	 */
	TransaccionRecaudacion obtenerTransaccion(BigDecimal nut, String cedula,BigDecimal anioPerio,BigDecimal mesPeriod);
	
	
	/**
	 * Valida si tiene empleador en mora mientras salga calificar 3
	 * @param cedula
	 * @return
	 */
    boolean tieneMoraEmpleado( String cedula);
    
    /**
     * Valida si tiene transaccion en EPL
     * @param nut
     * @param cedula
     * @return
     */
    boolean obtenerTransaccionEpl(BigDecimal nut, String cedula);
}
