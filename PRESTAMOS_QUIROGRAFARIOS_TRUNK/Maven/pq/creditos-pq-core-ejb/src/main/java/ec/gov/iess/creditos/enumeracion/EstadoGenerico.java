/**
 * 
 */
package ec.gov.iess.creditos.enumeracion;

/**
 * @author cvillarreal
 *
 */
public enum EstadoGenerico {

	ACT("ACTIVO"),INA("INACTIVO");
	
	private String descripcion;
	
	private EstadoGenerico(String desc){
		this.descripcion = desc;
	}
	
	public String getDescripcion(){
		return this.descripcion;
	}
	
}
