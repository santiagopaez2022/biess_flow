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

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Utilitario para desplegar mensaje en pantalla.
 * 
 * @author diego.iza
 */
public class MensajesUtil {

	/**
	 * Obtiene la descripcion de un mensaje desde el bundle.
	 * 
	 * @param context
	 *            - contexto.
	 * @param key
	 *            - id del componente.
	 * 
	 * @return mensaje deerror.
	 */
	public static String getErrorMessage(FacesContext context, String key) {
		return context.getApplication().getResourceBundle(context, "errores").getString(key);
	}

	/**
	 * Añade el mensaje a un componente.
	 * 
	 * @param idComponent
	 *            - id del componente
	 * @param message
	 *            - Mensaje
	 * @param severiry
	 *            - Gravedad.
	 */
	public static void addMessageComponent(final String idComponent, final String message, final Severity severiry) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage mess = new FacesMessage();
		mess.setSeverity(severiry);
		mess.setSummary(message);
		mess.setDetail(message);

		if (idComponent == null) { // es mensaje global
			context.addMessage(null, mess);
		} else {
			UIComponent control = findComponentInRoot(idComponent);
			context.addMessage(control.getClientId(context), mess);
		}
	}

	/**
	 * Para encontrar el componente exacto dentro del xhtml.
	 * 
	 * @param id
	 *            identificador del componente
	 * @return uicomonent.
	 */
	private static UIComponent findComponentInRoot(final String id) {
		UIComponent component = null;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null) {
			UIComponent root = facesContext.getViewRoot();
			component = findComponent(root, id);
		}

		return component;
	}

	/**
	 * Busca el componente segun el root.
	 * 
	 * @param base
	 *            componente
	 * @param id
	 *            identificador del componente
	 * @return d.
	 */
	private static UIComponent findComponent(final UIComponent base, final String id) {
		if (id.equals(base.getId())) {
			return base;
		}

		UIComponent kid = null;
		UIComponent result = null;
		Iterator<UIComponent> kids = base.getFacetsAndChildren();
		while (kids.hasNext() && (result == null)) {
			kid = (UIComponent) kids.next();
			if (id.equals(kid.getId())) {
				result = kid;
				break;
			}
			result = findComponent(kid, id);
			if (result != null) {
				break;
			}
		}
		return result;
	}

}
