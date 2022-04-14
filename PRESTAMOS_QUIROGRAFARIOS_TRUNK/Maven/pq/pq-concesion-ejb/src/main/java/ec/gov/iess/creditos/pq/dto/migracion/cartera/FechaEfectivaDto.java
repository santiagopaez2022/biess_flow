/**
 * 
 */
package ec.gov.iess.creditos.pq.dto.migracion.cartera;

import ec.gov.iess.creditos.pq.dto.MensajeSacResponseDto;

/**
 * @author PAUL
 *
 */
public class FechaEfectivaDto extends MensajeSacResponseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Fecha de respuesta
	 */
	private String fechaEfectiva;

	public String getFechaEfectiva() {
		return fechaEfectiva;
	}

	public void setFechaEfectiva(String fechaEfectiva) {
		this.fechaEfectiva = fechaEfectiva;
	}
	
}
