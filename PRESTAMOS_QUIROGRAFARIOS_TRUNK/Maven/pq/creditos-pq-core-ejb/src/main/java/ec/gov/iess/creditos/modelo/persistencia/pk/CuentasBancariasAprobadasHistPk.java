package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CuentasBancariasAprobadasHistPk implements Serializable {
	
	private static final long serialVersionUID = -7348858659380135817L;
	
	@Column(name = "CB_FECHAHIST")
	private Date fechaact;
	
	@Column(name = "CB_CEDULA")
	private String cedula;
	
	public CuentasBancariasAprobadasHistPk(){
		
	}
	
	public Date getFechaact() {
		return fechaact;
	}

	public void setFechaact(Date fechaact) {
		this.fechaact = fechaact;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public CuentasBancariasAprobadasHistPk(Date fecha, String cedula){
		this.fechaact = fecha;
		this.cedula = cedula;
		
	}
	
}
