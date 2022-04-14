package ec.gov.iess.evaluador.strings;

import ec.gov.iess.evaluador.Evaluador;

public class EvaluadorStringLleno implements Evaluador<String> {

	String cadena;

	public EvaluadorStringLleno(String o) {
		cadena = o;
	}

	public boolean cumpleRegla() {
		return this.cadena != null && !"".equals(this.cadena.trim());
	}

	public void setObjetoAEvaluarSiCumpleCondicion(String o) {
		this.cadena = o;
	}

}
