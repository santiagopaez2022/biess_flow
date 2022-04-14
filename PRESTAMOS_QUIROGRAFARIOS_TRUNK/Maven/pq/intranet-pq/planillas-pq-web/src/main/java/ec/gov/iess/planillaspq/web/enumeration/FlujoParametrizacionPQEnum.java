package ec.gov.iess.planillaspq.web.enumeration;

/**
 * Enumeracion de los flujos de opciones de comprobante y liquidación de PQ
 * 
 * @author roberth.obando
 *
 */
public enum FlujoParametrizacionPQEnum {
	
	GCPI("Generacion Comprobante Pago Individual"),
	GLA("Generacion Liquidacion Anticipada"),
	CLA("Consulta Liquidacion Anticipada"),
	CCP("Consulta Comprobantes Pago"),
	ACPI("Anular Comprobante Pago Individual"),
	ALA("Anular Liquidacion Anticipada"),
	CCI("Consultar Cuenta Individual");
	
	private String opcion;
	
	private FlujoParametrizacionPQEnum(String opcion) {
		this.opcion = opcion;
	}

	public String getOpcion() {
		return opcion;
	}
}
