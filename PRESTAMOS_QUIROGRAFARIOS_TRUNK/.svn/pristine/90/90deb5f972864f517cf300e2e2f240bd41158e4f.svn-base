/**
 * 
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author cbastidas
 * 
 */
public class ValidarRequisitosFondos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -909351126680634828L;

	public ValidarRequisitosFondos() {
	}

	/**
	 * @param cedula
	 * @param tiposCargos
	 * @param estadoBloqueado
	 * @param estadosSolicitud
	 */
	public ValidarRequisitosFondos(String cedula, List<String> tiposCargos,
			String estadoBloqueado, List<String> estadosSolicitud) {
		this.cedula = cedula;
		this.tiposCargos = tiposCargos;
		this.estadoBloqueado = estadoBloqueado;
		this.estadosSolicitud = estadosSolicitud;
	}

	private String cedula;
	private List<String> tiposCargos;
	private String estadoBloqueado;
	private List<String> estadosSolicitud;
	private boolean novacion;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public List<String> getTiposCargos() {
		return tiposCargos;
	}

	public void setTiposCargos(List<String> tiposCargos) {
		this.tiposCargos = tiposCargos;
	}

	public String getEstadoBloqueado() {
		return estadoBloqueado;
	}

	public void setEstadoBloqueado(String estadoBloqueado) {
		this.estadoBloqueado = estadoBloqueado;
	}

	public List<String> getEstadosSolicitud() {
		return estadosSolicitud;
	}

	public void setEstadosSolicitud(List<String> estadosSolicitud) {
		this.estadosSolicitud = estadosSolicitud;
	}

	public boolean isNovacion() {
		return novacion;
	}

	public void setNovacion(boolean novacion) {
		this.novacion = novacion;
	}

}
