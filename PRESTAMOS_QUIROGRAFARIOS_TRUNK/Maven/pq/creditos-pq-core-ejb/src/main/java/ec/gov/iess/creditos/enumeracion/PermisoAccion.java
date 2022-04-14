/**
 * 
 */
package ec.gov.iess.creditos.enumeracion;

/**
 * @author cvillarreal
 * 
 */
public enum PermisoAccion {

	S("PERMITIDO"), N("NO PERMITIDO");

	private String accion;

	private PermisoAccion(String accion) {
		this.accion = accion;
	}

	public String getAccion() {
		return this.accion;
	}

}
