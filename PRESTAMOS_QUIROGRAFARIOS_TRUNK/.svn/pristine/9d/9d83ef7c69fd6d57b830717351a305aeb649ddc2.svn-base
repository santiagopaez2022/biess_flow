/**
 * 
 */
package ec.gov.iess.pq.concesion.pqdinamico.util;

import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;

/**
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public final class DinamicoPqUtil {

	private DinamicoPqUtil() {
//Constructor privado para que no pueda ser instanciada esta Clase
	}

	/**
	 * Obtiene una institucion financiera tipo model de una instituciona financiera
	 * y un proveedor
	 * 
	 * @param instFinan
	 * @param proveedor
	 * @return
	 */
	public static ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera llenarInstitucionFinanciera(
			final InstitucionFinanciera instFinan, final Proveedor proveedor) {
		final ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera instFinaProv = new ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera();

		instFinaProv.setInstitucion(instFinan.getDescripcion());
		instFinaProv.setInstitucionId(proveedor.getPrRucInstfinanciera());
		instFinaProv.setNumeroCuenta(proveedor.getPrNumCuenta());
		instFinaProv.setTipoCuentaId(proveedor.getPrTipoCuenta());
		instFinaProv
				.setTipoCuenta("AHO".equals(proveedor.getPrTipoCuenta()) ? "CUENTA DE AHORROS" : "CUENTA CORRIENTE");
		return instFinaProv;
	}
}
