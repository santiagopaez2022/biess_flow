package ec.gov.iess.pq.concesion.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public abstract class BaseValidator implements Validator {

	public abstract void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException;

	protected static void throwException(String errMessage) {
		FacesMessage message = new FacesMessage();
		message.setDetail(errMessage);
		message.setSummary(errMessage);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(message);
	}

	protected static void addGlobalMessage(String errMessage,
			FacesContext context) {
		FacesMessage message = new FacesMessage();
		message.setDetail(errMessage);
		message.setSummary(errMessage);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		context.addMessage(null, message);
	}

	/*
	 * protected void throwException(String errMessage, String messageBundle) {
	 * String emailInvalido = facesContext.getApplication().getResourceBundle(
	 * facesContext, "errors").getString(messageBundle); FacesMessage message =
	 * new FacesMessage(); message.setDetail(errMessage);
	 * message.setSummary(errMessage);
	 * message.setSeverity(FacesMessage.SEVERITY_ERROR); throw new
	 * ValidatorException(message); }
	 */
}
