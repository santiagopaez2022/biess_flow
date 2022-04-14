package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.servicio.fondoreserva.modelo.CuentaFondoReserva;

@Remote
public interface ConsultaFondosReservaServicioRemote {
	/**
	 * Se encarga de consultar el valor de fondos de reserva
	 * @return
	 */
	public CuentaFondoReserva consultarFondoReserva(DatosGarantia datGarantia);
	/**
	 * Determina si una cédula dada tienen cargos en fondos de reserva
	 * @param cedula
	 * @return
	 */
	public boolean tieneCargos(String cedula,List<String> tipos);
	
	/**
	 * Determina si tiene aportes en fondos de reserva
	 * @param cedula
	 * @return
	 */
	public boolean tieneAportesFondos(String cedula);
	
	/**
	 * Determina si una cédula tiene una solicitud de fondos de reserva en trámite
	 * Incluir aqui­ la descripcion del metodo.
	 * @param cedula
	 * @return
	 */
	public boolean tieneSolicitudFondosEnTramite(String cedula,List<String> estados);
	
	/**
	 * Determina si existen bloqueos en la cuenta de fondos de reserva
	 * @return
	 */
	public boolean tieneBloqueosEnCuenta(String cedula,String estado);

	
}
