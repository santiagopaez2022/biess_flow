package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.enumeracion.TipoCuenta;

/**
 * @author Andres Cantos
 * 
 */
@Remote
public interface CuentaBancariaAprobadaServicioRemote {
	
	public void actulizarctalistablanca(String cedula,String entidadfinanciera,String numerocuenta,
			String rucinstfin,TipoCuenta tipocta,String cedulafun,String estadoCredito);
}
