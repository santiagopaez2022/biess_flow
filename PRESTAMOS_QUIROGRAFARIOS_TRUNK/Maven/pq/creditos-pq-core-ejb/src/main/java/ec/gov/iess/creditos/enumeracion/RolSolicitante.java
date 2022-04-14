/**
 * 
 */
package ec.gov.iess.creditos.enumeracion;

/**
 * @author cvillarreal
 * 
 */
public enum RolSolicitante {

	AFI("AFILIADO"), PEN("PENSIONISTA"), IFI("INSTITUION FINANCIERA"),FUN("FUNCIONARIO");

	private String rol;

	private RolSolicitante(String rol) {
		this.rol = rol;
	}

	public String getRol() {
		return this.rol;
	}

}
