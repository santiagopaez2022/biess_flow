package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;

/**
 * @author Pablo Alvarez
 * 
 */
public class RequisitosCruce implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -374843916063221634L;
	private String descripcion;
	private boolean aprobado;
	private String observacion;
	private int id;
	
	
	
	public RequisitosCruce() {
	}

	public RequisitosCruce(int id,String descripcion, boolean aprobado, String observacion) {
		super();
		this.descripcion = descripcion;
		this.aprobado = aprobado;
		this.observacion = observacion;
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String nombre) {
		this.descripcion = nombre;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isAprobado() {
		return aprobado;
	}

	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuffer msg = new StringBuffer();

		msg.append("RES : " + this.isAprobado() + " REQ : " + this.descripcion
				+ " OBS : " + this.observacion);

		return msg.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}