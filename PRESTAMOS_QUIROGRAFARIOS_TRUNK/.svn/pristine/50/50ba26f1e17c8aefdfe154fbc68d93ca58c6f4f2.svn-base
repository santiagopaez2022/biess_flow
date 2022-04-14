/**
 * 
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.util.List;

import ec.gov.iess.creditos.enumeracion.EstadoComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * @author cbastidas
 *
 */
public class ValidarRequisitosComprobante implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = -1991618496556599840L;
public ValidarRequisitosComprobante() {	}

private PrestamoPk prestamoPk;
private List<String> tiposComprobante;
private List<EstadoComprobantePago> estadosComprobante;
private List<String> estadosPrestamo;
private String numeroAfiliado;
/**
 * @param prestamoPk
 * @param tiposComprobante
 * @param estadosComprobante
 * @param estadosPrestamo
 */
public ValidarRequisitosComprobante(PrestamoPk prestamoPk,
		List<String> tiposComprobante,
		List<EstadoComprobantePago> estadosComprobante,
		List<String> estadosPrestamo) {
	this.prestamoPk = prestamoPk;
	this.tiposComprobante = tiposComprobante;
	this.estadosComprobante = estadosComprobante;
	this.estadosPrestamo = estadosPrestamo;
}

public PrestamoPk getPrestamoPk() {
	return prestamoPk;
}
public void setPrestamoPk(PrestamoPk prestamoPk) {
	this.prestamoPk = prestamoPk;
}
public List<String> getTiposComprobante() {
	return tiposComprobante;
}
public void setTiposComprobante(List<String> tiposComprobante) {
	this.tiposComprobante = tiposComprobante;
}
public List<EstadoComprobantePago> getEstadosComprobante() {
	return estadosComprobante;
}
public void setEstadosComprobante(List<EstadoComprobantePago> estadosComprobante) {
	this.estadosComprobante = estadosComprobante;
}
public List<String> getEstadosPrestamo() {
	return estadosPrestamo;
}
public void setEstadosPrestamo(List<String> estadosPrestamo) {
	this.estadosPrestamo = estadosPrestamo;
}

public String getNumeroAfiliado() {
	return numeroAfiliado;
}

public void setNumeroAfiliado(String numeroAfiliado) {
	this.numeroAfiliado = numeroAfiliado;
}




	
	
	
}
