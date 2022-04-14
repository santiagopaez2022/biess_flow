/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;

import javax.ejb.Remote;

import ec.fin.biess.creditos.pq.excepcion.ConsultaParametroException;

/**
 * Servicio remoto para consultar parametros por servicio rest
 * @author cristian.yaselga
 * 
 */
@Remote
public interface ConsultaParametroServicioRemote {
	/**
	 * Consulta el parametro devolviendo un tipo entero
	 * @param codigoParametro
	 * @return
	 */
	Integer consultarParametroEntero(String codigoParametro) throws ConsultaParametroException;
	
	/**
	 * Consulta el parametro devolviendo un Decimal entero
	 * @param codigoParametro
	 * @return
	 */
	BigDecimal consultarParametroDecimal(String codigoParametro) throws ConsultaParametroException;
	
	/**
	 * Consulta el parametro devolviendo un String entero
	 * @param codigoParametro
	 * @return
	 */
	String consultarParametroString(String codigoParametro) throws ConsultaParametroException;
	
	/**
	 * Consulta el parametro devolviendo un Object
	 * @param codigoParametro
	 * @param tipoDato
	 * @return
	 */
	Object consultarParametro(String codigoParametro, String tipoDato) throws ConsultaParametroException;

}
