/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.enumeracion;

/**
 * 
 * <b> Enumeración para los estados del proveedor. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:36:43 $]
 *          </p>
 */
public enum EstadoOrdenCompra {
	Generado("GEN"),
	Informado("INF"),
	Aprobado("APR"),
	Anulado("ANU"),
	EstadoOrdenCompra("ESTORD"),
	Entregado("ENT"),
	Regenerado("REG"),
	Rechazado("REC");

	private String descripcion;

	/**
	 * 
	 * @param descripcion
	 *            : Descipcion del estado del proveedor
	 */
	EstadoOrdenCompra(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * <b> Devuelve el valor del enum. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Descripcion del estado del proveedor
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
