package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;

/**
 * @author Ricardo Tituaña 03/10/2011
 * 
 */

public class DatosCatalogo implements Serializable{
	private static final long serialVersionUID = 3754714494475326577L;
	private Long idcatalogo;
	private Boolean seleccion;
	private String descripcion;
	private String observacion;
	
	


	public Long getIdcatalogo() {
		return idcatalogo;
	}


	public void setIdcatalogo(Long idcatalogo) {
		this.idcatalogo = idcatalogo;
	}


	public Boolean getSeleccion() {
		return seleccion;
	}


	public void setSeleccion(Boolean seleccion) {
		this.seleccion = seleccion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public DatosCatalogo() {
	}
	
	public void cambiarSeleccion()
	{
		if(this.seleccion)
			this.seleccion=false;
		else
			this.seleccion=true;
		
	}

}
