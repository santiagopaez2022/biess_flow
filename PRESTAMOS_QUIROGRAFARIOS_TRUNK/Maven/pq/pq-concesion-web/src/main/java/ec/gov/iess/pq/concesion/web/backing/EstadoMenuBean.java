/* 
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;

import ec.gov.iess.pq.concesion.web.util.BaseBean;

/**
 * EstadoMenuBean.java 
 * 
 * Clase para manejo del estado del menu.
 *  
 * @version 1.0
 * @creacion 04/03/2010
 * @modificacion 04/03/2010
 * @autor caldaz
 */
public class EstadoMenuBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1168551102453238152L;

	private String margen = "sep";
	private boolean muestra = true;
	private String displayMenu = "";

	public String minimizar() {
		margen = "uni";
		muestra = false;
		displayMenu = "none";
		return null;
	}

	public String maximizar() {
		margen = "sep";
		muestra = true;
		displayMenu = "";
		return null;
	}

	/**
	 * El menu aparece expandido
	 * 
	 * @param tree
	 * @return
	 */
	public Boolean abrir(org.richfaces.component.UITree tree) {
		return java.lang.Boolean.TRUE;
	}

	/**
	 * @return the margen
	 */
	public String getMargen() {
		return margen;
	}

	/**
	 * @param margen
	 *            the margen to set
	 */
	public void setMargen(String margen) {
		this.margen = margen;
	}

	/**
	 * @return the muestra
	 */
	public boolean isMuestra() {
		return muestra;
	}

	/**
	 * @param muestra
	 *            the muestra to set
	 */
	public void setMuestra(boolean muestra) {
		this.muestra = muestra;
	}

	/**
	 * @return the displayMenu
	 */
	public String getDisplayMenu() {
		return displayMenu;
	}

	/**
	 * @param displayMenu
	 *            the displayMenu to set
	 */
	public void setDisplayMenu(String displayMenu) {
		this.displayMenu = displayMenu;
	}
}