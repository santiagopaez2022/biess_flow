package ec.gov.iess.evaluador.strings;

import ec.gov.iess.evaluador.Evaluador;

public class EvaluadorOtroStringLleno extends EvaluadorStrings {
	public EvaluadorOtroStringLleno(Evaluador<String> evaluador, String cadena){
		super(evaluador,cadena);
	}

	public boolean cumpleRegla() {
		return super.cumpleRegla(cadena);
	}
}
