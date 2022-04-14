package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CuentasBancariasAprobadasPk implements Serializable {
	
	private static final long serialVersionUID = -7348858659380135817L;
	
	@Column(name = "CB_FECHA")
	private Date fecha;
	
	@Column(name = "CB_CEDULA")
	private String cedula;
	
	public CuentasBancariasAprobadasPk(){
		
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public CuentasBancariasAprobadasPk(Date fecha, String cedula){
		this.fecha = fecha;
		this.cedula = cedula;
		
	}
	
}
