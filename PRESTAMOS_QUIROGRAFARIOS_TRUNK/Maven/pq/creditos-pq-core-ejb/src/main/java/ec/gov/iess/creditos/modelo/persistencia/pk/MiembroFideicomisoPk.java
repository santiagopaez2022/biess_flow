/* 
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase para la clave compuesta de MiembroFideicomiso
 * 
 * @author jsanchez
 * @version 1.0
 */
@Embeddable
public class MiembroFideicomisoPk implements Serializable {

	private static final long serialVersionUID = 7897827711654749884L;

	@Column(name = "DP_CODIGO_PROYECTO", nullable = false)
	private Long codigoProyecto;

	@Column(name = "MP_IDENTIFICACION", nullable = false)
	private String identificacion;

	public MiembroFideicomisoPk(){
		
	}
	
	public MiembroFideicomisoPk(Long codigoProyecto, String identificacion) {
		this.codigoProyecto = codigoProyecto;
		this.identificacion = identificacion;
	}

	public boolean equals(Object other) {
		if (other instanceof MiembroFideicomisoPk) {
			final MiembroFideicomisoPk otherMiembroFideicomisoPk = (MiembroFideicomisoPk) other;
			final boolean areEqual = (otherMiembroFideicomisoPk.codigoProyecto.equals(codigoProyecto) && otherMiembroFideicomisoPk.identificacion
					.equals(identificacion));
			return areEqual;
		}
		return false;
	}

	/**
	 * @return the codigoProyecto
	 */
	public Long getCodigoProyecto() {
		return codigoProyecto;
	}

	/**
	 * @param codigoProyecto
	 *            the codigoProyecto to set
	 */
	public void setCodigoProyecto(Long codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion
	 *            the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
}
