package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.excepcion.CancelaLiquidacionException;

@Remote
public interface CancelarLiquidacionServicioRemote {

	public void cancelarCredito(Prestamo prestamo);
	public void cancelaLiquidacionPrestamo(LiquidacionPrestamo liquidacionPrestamo,String cedula)throws CancelaLiquidacionException;
	public void generarCuentaIndividual(DividendoPrestamo dividendo,String mensajeObservacion,String cedula,int i);		
	public void actualizarLiquidacionPrestamo(LiquidacionPrestamo liquidacionPrestamo,String tipo,String numeroDocumento)throws CancelaLiquidacionException;

}
