package ec.gov.iess.planillaspq.web.bean;

import java.io.Serializable;

public class CedulasMasivasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String cedula;
	private String mensaje;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
