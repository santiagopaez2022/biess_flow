package ec.gov.iess.evaluador.strings;

import ec.gov.iess.evaluador.Evaluador;

public abstract class EvaluadorStrings implements Evaluador<String> {
	
	protected Evaluador<String> otroEvaluador;
	protected String cadena;

	public Evaluador<String> getOtroEvaluador() {
		return otroEvaluador;
	}
	public EvaluadorStrings(Evaluador<String> o, String o1){
		otroEvaluador = o;
		cadena = o1;
	}
	public boolean cumpleRegla(String o) {
		return otroEvaluador.cumpleRegla() && this.cadena != null && !"".equals(this.cadena.trim());
	}

	public void setObjetoAEvaluarSiCumpleCondicion(String o) {
		cadena = o;
	}
	
	public String setObjetoAEvaluarSiCumpleCondicion(){
		return cadena;
	}

}
