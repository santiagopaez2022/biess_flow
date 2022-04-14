/**
 * 
 */
package ec.gov.iess.creditos.util;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
public class UtilAjusteCalculo implements Serializable {

	/**
	 * 
	 */
	public UtilAjusteCalculo() {

	}

	public static BigDecimal ajusteCalculo(BigDecimal numero) {
		if (numero != null) {
			return numero.setScale(4, BigDecimal.ROUND_HALF_UP);
		}
		return new BigDecimal("0");
	}
	
	public static BigDecimal ajusteNumberBaseDatos(BigDecimal numero) {
		if (numero != null) {
			return numero.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return new BigDecimal("0");
	}

	public static BigDecimal getEsacalaDigitosCalculo(BigDecimal numeroRef) {
		return numeroRef.setScale(10, BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal ajusteNumber(BigDecimal numero, int escala) {
		if (numero != null) {
			return numero.setScale(escala, BigDecimal.ROUND_HALF_UP);
		}
		return new BigDecimal("0");
	}

	

}
