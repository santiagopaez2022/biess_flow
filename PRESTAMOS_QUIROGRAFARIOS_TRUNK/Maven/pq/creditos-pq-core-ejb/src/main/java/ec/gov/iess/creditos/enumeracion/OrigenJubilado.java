package ec.gov.iess.creditos.enumeracion;

/**
 * @author Daniel Cardenas
 * 
 */
public enum OrigenJubilado {
	HL(3), HOUIO(2), HOGYE(1);
	OrigenJubilado(int valorEntero){
		this.valorEntero = valorEntero; 
	}
	private int valorEntero;
	
	public int getValorEntero() {
		return valorEntero;
	}
}