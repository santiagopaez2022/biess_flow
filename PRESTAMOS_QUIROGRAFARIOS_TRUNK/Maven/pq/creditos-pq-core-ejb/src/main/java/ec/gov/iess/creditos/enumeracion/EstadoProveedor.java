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
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:28:11 $]
 *          </p>
 */
public enum EstadoProveedor {
	Activo("ACT"), Inactivo("INA"), EstadoProveedor("ESTPROV");

	private String descripcion;

	/**
	 * 
	 * @param descripcion
	 *            : Descipcion del estado del proveedor
	 */
	EstadoProveedor(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * <b> Descripción de los estados del proveedor. </b>
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
