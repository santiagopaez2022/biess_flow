package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.persistencia.PrestamoEstadoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.ActualizarPrestamoEstadoHistoricoException;
/**
 * Implementacion Ejb para la Prestamo Estado Historico Servicio
 * 
 * @version 1.0
 * @author rtituana
 */
@Remote
public interface PrestamoEstadoHistoricoServicioRemote {
	/**
	*  buscar Historicos DePrestamo
	* @param numpreafi
	* @param ordpreafi
	* @param codpretip
	* @param codprecla
	* @return 
	* @author rtituana
	*/
	public List<PrestamoEstadoHistorico> buscarHistoricosDePrestamo(Long numpreafi,Long ordpreafi, Long codpretip, Long codprecla);
	
	/**
	* Actualiza el historico de un prestamo en Estado de PDA y crea uno en REC
	* @param numpreafi
	* @param ordpreafi
	* @param codpretip
	* @param codprecla
	* @return 
	* @author acantos
	*/
	public void actualizarprestamoPdaRec(Long numPreAfi,
			Long ordPreAfi, Long codPreTip, Long codPreCla, String codEstAnt, String obsTra)
			throws ActualizarPrestamoEstadoHistoricoException;
	
	public String getmotivorechazo(PrestamoPk pk);
}
