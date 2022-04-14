package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.enumeracion.TipoCuenta;

/**
 * @author Andres Cantos
 * 
 */
@Local
public interface CuentaBancariaAprobadaServicioLocal {

	public void actulizarctalistablanca(String cedula,String entidadfinanciera,String numerocuenta,
			String rucinstfin,TipoCuenta tipocta,String cedulafun,String estadoCredito);
}
