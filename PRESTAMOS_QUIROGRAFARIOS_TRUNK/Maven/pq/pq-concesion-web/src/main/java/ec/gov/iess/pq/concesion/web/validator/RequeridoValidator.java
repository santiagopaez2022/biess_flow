package ec.gov.iess.pq.concesion.web.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class RequeridoValidator extends BaseValidator {

	// para usarlo en jsf
	public void validate(FacesContext context, UIComponent uIcomponent,
			Object value) throws ValidatorException {
		if (verificarRequerido(value.toString()) == false) {
			throwException(getErrorMessage(context, "requerido"));
		}
		// verificarRequerido(, context, "requerido");
	}

	// para usarlo en java
	public static void validate(String valor, FacesContext context,
			String errorKeyBundle) {
		if (verificarRequerido(valor) == false) {
			addGlobalMessage(getErrorMessage(context, errorKeyBundle), context);
		}
	}

	/**
	 * @return true si pasa la validacion
	 */
	private static boolean verificarRequerido(String valor) {
		if ("".equals(valor)) {
			return false;
		}
		return true;
	}

	private static String getErrorMessage(FacesContext context, String key) {
		String emailInvalido = context.getApplication().getResourceBundle(
				context, "errores").getString(key);
		return emailInvalido;
	}

}