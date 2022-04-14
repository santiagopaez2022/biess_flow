package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;

/**
 * DTO de respuesta para obtener informacion de tabla de amortizacion de core negocio
 * 
 * @author hugo.mora
 * @date 2018/09/05
 *
 */
public class InformacionTablaAmortizacionResponseDto extends MensajeSacResponseDto implements Serializable {

	private static final long serialVersionUID = 1878008996666687L;

	private TablaAmortizacionSac tablaAmortizacionSac;

	// Getters and setters
	public TablaAmortizacionSac getTablaAmortizacionSac() {
		return tablaAmortizacionSac;
	}

	public void setTablaAmortizacionSac(TablaAmortizacionSac tablaAmortizacionSac) {
		this.tablaAmortizacionSac = tablaAmortizacionSac;
	}
}
