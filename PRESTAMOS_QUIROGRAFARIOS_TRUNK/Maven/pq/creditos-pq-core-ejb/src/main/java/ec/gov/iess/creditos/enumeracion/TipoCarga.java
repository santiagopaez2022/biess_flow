/**
 * 
 */
package ec.gov.iess.creditos.enumeracion;

/**
 * @author ndeveloper
 *
 */
public enum TipoCarga {

	MAS("MASIVA"), CDC("CARTA DE COMPROMISO");
	
	private String id;
	
	private TipoCarga(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	
}
