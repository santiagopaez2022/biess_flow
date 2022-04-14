package ec.gov.iess.pq.concesion.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * @author Daniel Cardenas
 * 
 */
public class EmailValidator extends BaseValidator {

	// para usarlo en jsf
	public void validate(FacesContext context, UIComponent uIcomponent,
			Object value) throws ValidatorException {
		if (verificarEmail(value.toString()) == false) {
			throwException(getErrorMessage(context, "email.invalido"));
		}
	}

	// para usarlo en java
	public static void validate(String valor, FacesContext context,
			String errorKeyBundle) {
		if (verificarEmail(valor) == false) {
			addGlobalMessage(getErrorMessage(context, errorKeyBundle), context);
		}
	}
	
	// para usarlo en java
	public static boolean validar(String valor) {
		return verificarEmail(valor);
	}

	/**
	 * @param valor
	 * @return true si pasa la validacion, false si falla
	 */
	private static boolean verificarEmail(String valor) {

		valor = valor.replace(" ", "").trim();

		// Get the component's contents and cast it to a String
		String enteredEmail = valor;

		// Set the email pattern string
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(EMAIL_PATTERN);

		// Match the given string with the pattern
		Matcher m = p.matcher(enteredEmail);

		// Check whether match is found
		boolean matchFound = m.matches();

		if (!matchFound) {
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