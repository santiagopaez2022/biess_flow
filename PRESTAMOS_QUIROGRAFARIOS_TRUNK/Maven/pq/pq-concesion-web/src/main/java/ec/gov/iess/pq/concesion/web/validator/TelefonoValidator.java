package ec.gov.iess.pq.concesion.web.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import ec.gov.iess.pq.concesion.web.util.Constantes;

public class TelefonoValidator extends BaseValidator {

	// jsf
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String strValue = (String) value;
		String claveMensaje = "telefono.invalido";
		boolean celular = false;
		String idControl = component.getId().toString().toLowerCase();
		if (idControl.contains("celular")) {
			celular = true;
			claveMensaje = "celular.invalido";
		} else if (idControl.contains("trabajocsc")) {
			claveMensaje = "trabajo.invalido";
			celular = false;
		}
		if (!verificarNumero(strValue, celular)) {
			throwException(getErrorMessage(context, claveMensaje));
		}
	}

	// java
	public static void validate(String valor, FacesContext context, String errorKeyBundle, boolean celular) {
		if (verificarNumero(valor, celular) == false) {
			addGlobalMessage(getErrorMessage(context, errorKeyBundle), context);
		}
	}
	
	// java
	public static boolean validarTelefono(String valor) {
		return verificarNumero(valor, false);
	}

	/**
	 * @return true si pasa la validacion
	 */
	private static boolean verificarNumero(String valor, boolean celular) {
		if (celular) {
			return valor.matches(Constantes.REGEX_EVALUA_TELEFONOS);
		} else {
			return valor.matches(Constantes.REGEX_EVALUA_CONVENCIONAL);
		}
	}

	private static String getErrorMessage(FacesContext context, String key) {
		String telefonoInvalido = context.getApplication().getResourceBundle(context, "errores").getString(key);// "telefono.invalido"
		return telefonoInvalido;
	}

}