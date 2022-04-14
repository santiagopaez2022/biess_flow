/* 
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;

/**
 * Clase dto para guardar información necesario de cada 
 * miembro del proyecto
 * 
 * @author jsanchez
 * @version 1.0
 */
public class PrecalificacionCFN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5324047588084225719L;

	private Precalificacion precalificacion;

	private String identificacion;

	private String rucIdentificacion;

	private boolean calificadoProyecto;

	/**
	 * @return the precalificacion
	 */
	public Precalificacion getPrecalificacion() {
		return precalificacion;
	}

	/**
	 * @param precalificacion
	 *            the precalificacion to set
	 */
	public void setPrecalificacion(Precalificacion precalificacion) {
		this.precalificacion = precalificacion;
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

	/**
	 * @return the calificadoProyecto
	 */
	public boolean isCalificadoProyecto() {
		return calificadoProyecto;
	}

	/**
	 * @param calificadoProyecto
	 *            the calificadoProyecto to set
	 */
	public void setCalificadoProyecto(boolean calificadoProyecto) {
		this.calificadoProyecto = calificadoProyecto;
	}

	/**
	 * @return the rucIdentificacion
	 */
	public String getRucIdentificacion() {
		return rucIdentificacion;
	}

	/**
	 * @param rucIdentificacion
	 *            the rucIdentificacion to set
	 */
	public void setRucIdentificacion(String rucIdentificacion) {
		this.rucIdentificacion = rucIdentificacion;
	}

	public boolean getCalificarRequisitosMiembros() {
		boolean calificacion = true;
		for (Requisito requisito : precalificacion.getRequisitos()) {
			calificacion = calificacion && requisito.isAprobado();
		}
		return calificacion;
	}
}
