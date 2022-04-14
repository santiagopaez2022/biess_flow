package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;

@Remote
public interface ConsultaCreditoServicioRemote {
	/**
	 * Consulta los prestamos vigentes para la cédula pasada como parámetro
	 * @param cedula
	 * @return
	 */
	public List<Prestamo> getPrestamosVigentesPorCedula(String cedula,List<String> estadoCredito);
	
	/**
	 * Consulta los dividendos de un prestamo usando los datos de PK del prestamos al que pretenece
	 * @param codprecla
	 * @param codpretip
	 * @param numpreafi
	 * @param ordpreafi
	 * @return
	 */
	public List<DividendoPrestamo> getDividendosPrestamo(Long codprecla, Long codpretip, Long numpreafi,Long ordpreafi);
	
	/**
	 * Consulta todos los prestamos para una cédula dada.  No considera el estado del prestamo
	 * @param cedula
	 * @return
	 */
	public List<Prestamo> getAllPrestamosPorCedula(String cedula);	

}
