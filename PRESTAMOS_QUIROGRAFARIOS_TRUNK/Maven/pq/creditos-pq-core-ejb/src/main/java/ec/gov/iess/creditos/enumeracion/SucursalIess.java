package ec.gov.iess.creditos.enumeracion;

public enum SucursalIess {
	HOUIO("9016"),HL("9017"), HOGYE("9020");
	
	private String codigo;
	
	SucursalIess(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
	
}
