package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;

import ec.gov.iess.planillaspq.web.util.BaseBean;




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