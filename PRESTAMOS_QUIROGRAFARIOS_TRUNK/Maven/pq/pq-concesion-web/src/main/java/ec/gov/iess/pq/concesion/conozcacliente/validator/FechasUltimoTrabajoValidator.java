package ec.gov.iess.pq.concesion.conozcacliente.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import ec.gov.iess.jsf.validator.BaseValidator;

/**
 * @author edison.cayambe
 *
 */
public class FechasUltimoTrabajoValidator extends BaseValidator {

	/*
	 * Valida las fechas ingresadas (non-Javadoc)
	 * 
	 * @see ec.gov.iess.jsf.validator.BaseValidator#validate(javax.faces.context.FacesContext,
	 * javax.faces.component.UIComponent, java.lang.Object)
	 */
	public void validate(FacesContext context, UIComponent uIcomponent, Object value) throws ValidatorException {
		String valorTiempo = value.toString();
		if (valorTiempo.equals("0")) {
			throwException(getErrorMessage(context, "conozcacliente_lbl_tiempo_ultimo_trabajo_fechas"));
		}
	}
}
