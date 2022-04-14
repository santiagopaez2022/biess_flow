package ec.fin.biess.creditos.pq.modelo.dto;

import java.io.Serializable;

import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoResumenHistorico;

/**
 * Calse dto para almacenar informacion de cesantias de un asegurado
 * @author edison.cayambe
 *
 */
public class AprobacionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DatosCredito datosCredito;
	PrestamoResumenHistorico prestamoResumenHistorico;
	Prestamo prestamoLocal;
	
	public DatosCredito getDatosCredito() {
		return datosCredito;
	}
	
	public void setDatosCredito(DatosCredito datosCredito) {
		this.datosCredito = datosCredito;
	}
	
	public PrestamoResumenHistorico getPrestamoResumenHistorico() {
		return prestamoResumenHistorico;
	}
	
	public void setPrestamoResumenHistorico(
			PrestamoResumenHistorico prestamoResumenHistorico) {
		this.prestamoResumenHistorico = prestamoResumenHistorico;
	}
	
	public Prestamo getPrestamoLocal() {
		return prestamoLocal;
	}
	
	public void setPrestamoLocal(Prestamo prestamoLocal) {
		this.prestamoLocal = prestamoLocal;
	}
}
