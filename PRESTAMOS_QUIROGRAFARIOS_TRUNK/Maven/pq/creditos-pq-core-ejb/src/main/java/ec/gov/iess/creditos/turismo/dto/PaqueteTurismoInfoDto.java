package ec.gov.iess.creditos.turismo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Clase para devolver informacion del paquete turistico
 * 
 * @author hugo.mora
 *
 */
public class PaqueteTurismoInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal montoPaquete;

	private String descripcionPaquete;

	// Getters and setters
	public BigDecimal getMontoPaquete() {
		return montoPaquete;
	}

	public void setMontoPaquete(BigDecimal montoPaquete) {
		this.montoPaquete = montoPaquete;
	}

	public String getDescripcionPaquete() {
		return descripcionPaquete;
	}

	public void setDescripcionPaquete(String descripcionPaquete) {
		this.descripcionPaquete = descripcionPaquete;
	}

}
