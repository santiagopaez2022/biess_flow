package ec.gov.iess.creditos.pq.util;

import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;

/**
 * Utilitario para manejo de creditos especiales
 * 
 * @author hugo.mora
 * @date 17 de may. de 2016
 *
 */
public class CreditosEspecialesUtil {
	
	/**
	 * Clase con metodos estaticos no requiere new
	 */
	private CreditosEspecialesUtil(){
		
	}

	/**
	 * Dado el codigo devuelve el nombre de la enumeracion en String
	 * 
	 * @param codigo
	 * @return
	 */
	public static String obtenerNombrePorCodigo(Long codigo) {
		String resp = "";
		for (CreditoEspecialEnum especiales : CreditoEspecialEnum.values()) {
			if (especiales.getCodigoCredito().equals(codigo)) {
				resp = especiales.toString();
				break;
			}
		}
		return resp;
	}

}
