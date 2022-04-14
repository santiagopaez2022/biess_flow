package ec.fin.biess.pq.simulacion.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Dto para el detalle de los egresos (prestamos vigentes) usado en el simulador PQ
 * 
 * @author hugo.mora
 * @date 2017/05/17
 *
 */
public class DetalleEgresosDto implements Serializable {

	private static final long serialVersionUID = -110378929992870915L;

	private String nombre;

	private BigDecimal valor;

	// Getters and setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
