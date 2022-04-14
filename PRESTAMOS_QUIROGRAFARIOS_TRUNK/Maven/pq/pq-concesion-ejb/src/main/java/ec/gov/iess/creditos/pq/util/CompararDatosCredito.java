/**
 * 
 */
package ec.gov.iess.creditos.pq.util;

import java.util.List;

/**
 * @author cbastidas
 * 
 */
public class CompararDatosCredito {

	public CompararDatosCredito() {
	}

	/**
	 * Compara si el credito se encuentra en algun estado Vigente,
	 * 
	 * @param estadosComparar
	 * @param estadoCredito
	 * @return
	 */
	public static boolean CompararEstados(List<String> estadosComparar,
			String estadoCredito) {
		for (String unEstado : estadosComparar) {
			if (unEstado.equals(estadoCredito)) {
				return true;
			}
		}
		return false;
	}

	public static boolean CompararEstados(
			List<String> estadosComparar, List<String> estadosLiquidacion) {
		for (String unEstado : estadosComparar) {
			for (String unaLiquidacion : estadosLiquidacion) {
				if (unEstado.equals(unaLiquidacion)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean CompararTipos(List<Long> tiposComparar,
			Long tipoCredito) {
		for (Long unTipo : tiposComparar) {
			if (unTipo.equals(tipoCredito)) {
				return true;
			}
		}
		return false;
	}
	
}
