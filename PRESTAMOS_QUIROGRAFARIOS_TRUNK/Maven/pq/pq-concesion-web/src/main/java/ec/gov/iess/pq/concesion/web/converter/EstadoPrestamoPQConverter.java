package ec.gov.iess.pq.concesion.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

public class EstadoPrestamoPQConverter implements Converter {

	private transient static LoggerBiess log = LoggerBiess.getLogger(EstadoPrestamoPQConverter.class);

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valorString) {
		log.info("******************************");
		log.info(valorString);
		log.info("******************************");
			return valorString;
	
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		return FuncionesUtilesSac.obtenerEstadoPrestamo((String) obj);

	}

}
