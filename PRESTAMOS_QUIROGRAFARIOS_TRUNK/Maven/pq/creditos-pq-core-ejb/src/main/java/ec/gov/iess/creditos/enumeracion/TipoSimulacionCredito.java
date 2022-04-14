/**
 * 
 */
package ec.gov.iess.creditos.enumeracion;

/**
 * @author cvillarreal
 *
 */
public enum TipoSimulacionCredito {

	SIMULACION_MONTO(1),SIMULACION_CUOTA(2),SIMULACION_MEJOR_OPCION(3) ;
	
	private int simulacion;
	
	TipoSimulacionCredito(int simulacion){
		this.simulacion = simulacion;
	}
	
	public int getValor(){
		return this.simulacion;
	}
	
	public String getEstado() {
		switch (this.simulacion) {
		case 1:
			return "SIMULACION_MONTO";
		case 2:
			return "SIMULACION_CUOTA";
		case 3:
			return "SIMULACION_MEJOR_OPCION";
			
		default:
			return null;
		}
	}

	
}
