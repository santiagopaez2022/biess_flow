/**
 * 
 */
package ec.gov.iess.creditos.enumeracion;

/**
 * @author cvillarreal
 *
 */
public enum TipoTablaReferencia {

SIN_DOCUMENTO_FIDUCIARIO(1),CON_DOCUMENTO_FIDUCIARIO(2);
	
	private int tablaReferencia;
	
	TipoTablaReferencia(int tablaReferencia){
		this.tablaReferencia = tablaReferencia;
	}
	
	public int getValor(){
		return this.tablaReferencia;
	}
	
	public String getEstado() {
		switch (this.tablaReferencia) {
		case 1:
			return "SIN_DOCUMENTO_FIDUCIARIO";
		case 2:
			return "CON_DOCUMENTO_FIDUCIARIO";
		default:
			return null;
		}
	}
	
}
