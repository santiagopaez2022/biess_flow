package ec.gov.iess.pq.concesion.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.gov.iess.creditos.enumeracion.EstadoPrestamoEnum;

public class EstadoCreditoConverter implements Converter {

	public String getAsString(FacesContext arg0, UIComponent arg1, Object mora) {
		String estadoCredito;
		Long diasMora = (Long) mora;
		if (diasMora > 0) {
			estadoCredito = EstadoPrestamoEnum.VCD.getEstado();
		} else {
			estadoCredito = EstadoPrestamoEnum.VIG.getEstado();
		}
		return estadoCredito;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}

}
