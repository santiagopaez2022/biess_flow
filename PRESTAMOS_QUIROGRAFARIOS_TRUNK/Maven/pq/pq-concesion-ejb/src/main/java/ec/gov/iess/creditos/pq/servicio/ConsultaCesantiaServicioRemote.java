package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Remote;

import ec.fin.biess.creditos.pq.modelo.dto.InformacionCesantia;
import ec.gov.iess.creditos.excepcion.CesantiaExcepcion;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.servicio.cesantia.excepcion.ActualizacionCesantiaExcetion;

@Remote
public interface ConsultaCesantiaServicioRemote {
	/**
	 * Consulta el valor de cesantia para un determinado afiliado tomando las consideraciones
	 * respectivas de si se trata de un proceso de consesion normal o novacion.
	 * Esto influye en los valores comprometidos
	 * @param valReqFondos
	 * @return
	 */
	public BigDecimal consultarCesantia(DatosGarantia datGarantia)
	throws CesantiaExcepcion;

	/**
	 * Consulta la cuenta de cesantia por la cédula
	 * @param cedula
	 * @return
	 * @throws CesantiaExcepcion
	 */
	public GarantiaCesantia consultarCesantia(String cedula)
	throws CesantiaExcepcion;
	
	/**
	 * consulta el valor disponible considerando aportes extemporaneos
	 * @author acantos
	 * @param cedula 
	 * @return valor disponible
	 */
	public BigDecimal consultarValordisponibleconExtemporaneos(String cedula);
	
	/**
	 * consulta la fecha que tiene en la cuentaindividual
	 * @author acantos
	 * @param cedula 
	 * @return Timestamp fecha
	 */
	public Date consultarFechadecuentaindividual(String cedula);
	
	/**
	 * consulta el valor disponible considerando aportes extemporaneos
	 * @author acantos
	 * @param cedula 
	 * @return valor disponible
	 * @throws CesantiaExcepcion
	 */
	public void actualizarvalorcesantia(String cedula, BigDecimal valor)throws ActualizacionCesantiaExcetion;
	
	/**
	 * Consulta el valor disponible de cesantia de un asegurado
	 * 
	 * @param identificacion
	 * @return
	 */
	InformacionCesantia consultarValorDisponible(String identificacion) throws CesantiaExcepcion;
	
}
