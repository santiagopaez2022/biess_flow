package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;

public class InstitucionFinanciera implements Serializable{

	private static final long serialVersionUID = -9098252943240870757L;
	
	private String institucionId;
	private String institucion;
	private String numeroCuenta;
	private String tipoCuenta;
	private String tipoCuentaId;

	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getInstitucionId() {
		return institucionId;
	}
	public void setInstitucionId(String institucionId) {
		this.institucionId = institucionId;
	}
	public String getTipoCuentaId() {
		return tipoCuentaId;
	}
	public void setTipoCuentaId(String tipoCuentaId) {
		this.tipoCuentaId = tipoCuentaId;
	}
}