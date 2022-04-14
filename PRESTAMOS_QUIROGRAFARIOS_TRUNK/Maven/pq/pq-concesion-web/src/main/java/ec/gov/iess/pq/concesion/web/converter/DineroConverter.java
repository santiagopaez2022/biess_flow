package ec.gov.iess.pq.concesion.web.converter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.gov.biess.util.log.LoggerBiess;

/**
 * @author Daniel Cardenas
 * 
 */
public class DineroConverter implements Converter {

	private transient static LoggerBiess log = LoggerBiess.getLogger(DineroConverter.class);

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valorString) {
		try {
			BigDecimal bigDecimal = new BigDecimal(valorString);
			return bigDecimal;
		} catch (Exception e) {
			log.debug("valor ingresado invalido");
		}
		return new BigDecimal(0);
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat("#######.##", simbolos);
		BigDecimal bigDecimal = (BigDecimal) obj;
		String res = decimalFormat.format(bigDecimal);
		return res;
	}
}
