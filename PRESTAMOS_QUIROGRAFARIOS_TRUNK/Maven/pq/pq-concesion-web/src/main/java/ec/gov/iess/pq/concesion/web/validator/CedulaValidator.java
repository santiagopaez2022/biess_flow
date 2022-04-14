/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.pq.concesion.web.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * Utilitario para validar la cedula.
 * 
 * @author diego.iza
 * 
 */
public class CedulaValidator extends BaseValidator {

	/**
	 * @see ec.gov.iess.pq.concesion.web.validator.BaseValidator#validate(javax.faces
	 *      .context.FacesContext, javax.faces.component.UIComponent,
	 *      java.lang.Object)
	 */
	public void validate(FacesContext context, UIComponent uIcomponent,
			Object value) throws ValidatorException {

		int respuesta = verificarCedula(value.toString());

		switch (respuesta) {
		case 0:
			break;

		case 1:
			throwException(getErrorMessage(context,
					"validacion.cedula.longitud"));
			break;

		case 2:
			throwException(getErrorMessage(context, "validacion.cedula.numeros"));
			break;

		default:
			throwException(getErrorMessage(context,
					"validacion.cedula.invalida"));
			break;
		}
	}

	/**
	 * 
	 * @param valor
	 * @param context
	 * @param errorKeyBundle
	 */
	public static void validate(String valor, FacesContext context,
			String errorKeyBundle) {

		int respuesta = verificarCedula(valor);

		switch (respuesta) {
		case 0:
			break;

		case 1:
			addGlobalMessage(getErrorMessage(context, errorKeyBundle), context);
			break;

		case 2:
			addGlobalMessage(getErrorMessage(context, errorKeyBundle), context);
			break;

		default:
			addGlobalMessage(getErrorMessage(context, errorKeyBundle), context);
			break;
		}
	}

	/**
	 * 
	 * @param cedula
	 * @return
	 */
	private static int verificarCedula(final String cedula) {

		int numeroProvincias = 24;

		/**
		 * verifica que el parametro cedula no se encuentre vacio y que tenga 10
		 * digitos
		 */
		if (cedula == null || cedula.trim().length() <= 0
				|| !(cedula.trim().length() == 10)) {
			return 1;
		}

		/**
		 * verifica que contenga solo valores numericos
		 */
		if (!(cedula.matches("^[0-9]{10}$"))) {
			return 2;
		}

		/**
		 * verifica que los dos primeros digitos correspondan a un valor entre 1
		 * y NUMERO_DE_PROVINCIAS
		 */
		int prov = Integer.parseInt(cedula.substring(0, 2));

		if (!((prov > 0) && (prov <= numeroProvincias))) {
			return 3;
		}

		/**
		 * verifica que el ultimo digito de la cedula sea valido
		 */
		int[] d = new int[10];

		/**
		 * Asignamos el string a un array
		 */
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(cedula.charAt(i) + "");
		}

		int imp = 0;
		int par = 0;

		/**
		 * sumamos los duplos de posicion impar
		 */
		for (int i = 0; i < d.length; i += 2) {
			d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
			imp += d[i];
		}

		/**
		 * sumamos los digitos de posicion par
		 */
		for (int i = 1; i < (d.length - 1); i += 2) {
			par += d[i];
		}

		/**
		 * Sumamos los dos resultados
		 */
		int suma = imp + par;

		/**
		 * Restamos de la decena superior
		 */
		int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1)
				+ "0")
				- suma;

		/**
		 * Si es diez el decimo digito es cero
		 */
		d10 = (d10 == 10) ? 0 : d10;

		/**
		 * si el decimo digito calculado es igual al digitado la cedula es
		 * correcta
		 */
		if (d10 == d[9]) {
			return 0;
		} else {
			return 4;
		}
	}

	/**
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	private static String getErrorMessage(FacesContext context, String key) {
		String emailInvalido = context.getApplication()
				.getResourceBundle(context, "errores").getString(key);
		return emailInvalido;
	}
}