package ec.gov.iess.pq.concesion.web.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TipoTablaEnum;
import ec.gov.iess.creditos.modelo.dto.Simulacion;

/**
 * Utilitario para la pagina de simulacion, tanto del simulador como de la concesion
 * 
 * @author hugo.mora
 * @date 2017/05/23
 *
 */
public class SimulacionCreditoUtil {

	/**
	 * No muestra el boton de ver la tabla de amortización
	 * 
	 * @param simulacion
	 */
	public static void ocultarBotonTablaAmortizacion(Simulacion simulacion) {
		if (simulacion != null) {
			simulacion.setCalculoCredito(false);
			simulacion.setObservacion(null);
		}
	}

	/**
	 * Obtiene el tipo de tablas de amortizacion
	 * 
	 * @return
	 */
	public static List<SelectItem> obtenerTipoTablaItems(TipoPrestamista tipo) {
		List<SelectItem> listaItems = new ArrayList<SelectItem>();
		if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(tipo)) {
			// No agregar la amortizacion ALEMANA
		} else {
		listaItems.add(new SelectItem(TipoTablaEnum.ALEMANA));
		}
		listaItems.add(new SelectItem(TipoTablaEnum.FRANCESA));

		return listaItems;
	}

}
