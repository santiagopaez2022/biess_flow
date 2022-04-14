/**
 * 
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * <b>
 * Cálculo de valores de Ingresos para la concesión del PQ.
 * </b>
 *  
 * @author cbastidas
 * @version $Revision: 1.0 $ <p>[$Author: cbastidas $, $Date: 13/06/2011 $]</p>
 */
public class DetalleCalculoIngresos implements Serializable {

	private static final long serialVersionUID = -5661554351510865940L;
	private String nombre;
	private BigDecimal valor;
	private String observacion;

	public DetalleCalculoIngresos() {

	}

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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
