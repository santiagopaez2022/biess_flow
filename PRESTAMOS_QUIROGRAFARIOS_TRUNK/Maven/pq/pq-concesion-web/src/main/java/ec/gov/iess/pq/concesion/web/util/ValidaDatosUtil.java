/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.pq.concesion.web.util;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Utilitario para validar datos de una pantalla.
 * 
 * @author diego.iza
 */
public class ValidaDatosUtil {

	/**
	 * Valida el valor de un campo numerico.
	 * 
	 * @param valor
	 *            - valor ingresado.
	 * @param idComponente
	 *            - identificador del componente.
	 * @param valorMinimo
	 *            - valor minimo a ser ingresado.
	 * 
	 * @return true valor correcto, caso contrario false.
	 */
	public static boolean validarValorMinimo(BigDecimal valor, String idComponente, BigDecimal valorMinimo) {

		if (valor == null || valor.doubleValue() <= 0) {
			MensajesUtil.addMessageComponent(idComponente,
					MensajesUtil.getErrorMessage(FacesContext.getCurrentInstance(), "validacion.datos.requerido"),
					FacesMessage.SEVERITY_ERROR);
			return false;
		}

		if (valor.doubleValue() < valorMinimo.doubleValue()) {
			System.out.println("El valor del Patrimonio es demaciado bajo.");
			MensajesUtil
					.addMessageComponent(
							idComponente,
							MensajesUtil.getErrorMessage(FacesContext.getCurrentInstance(),
									"validacion.datos.valor.incorrecto") + valorMinimo, FacesMessage.SEVERITY_ERROR);
			return false;
		}

		return true;
	}

}
