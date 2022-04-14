/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */

package ec.gov.iess.creditos.enumeracion;

/**
 * Enumeracion para tipos de beneficiarios de creditos quirografiarios
 * 
 * @author diego.iza
 */
public enum TipoBeneficiarioCredito {

	/**
	 * Discapacitado
	 */
	DISCAPACITADO("DIS", "Discapacitado"),
	/**
	 * Refugiado/Extranjero
	 */
	REFUGIADO("REF", "Refugiado/Extranjero"),
	/**
	 * Normal
	 */
	NORMAL("NOR", "Normal"),
	/**
	 * Ferrocarril
	 */
	FERROCARRIL("FER", "Ferrocarril"),
	/**
	 * Pencion Alimenticia
	 */
	PENSION_ALIMENTICIA("PEN", "Pension Alimenticia");

	private String id;
	private String descripcion;

	private TipoBeneficiarioCredito(String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public String getId() {
		return id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Obtiene la descripcion de un tipo de beneficiario.
	 * 
	 * @param id
	 *            - identificador.
	 * @return {@link String}
	 */
	public static String obtenerDescripcion(String id) {
		if (id == null) {
			return TipoBeneficiarioCredito.NORMAL.getDescripcion();
		}

		if (id.equalsIgnoreCase(TipoBeneficiarioCredito.DISCAPACITADO.getId())) {
			return TipoBeneficiarioCredito.DISCAPACITADO.getDescripcion();
		}

		if (id.equalsIgnoreCase(TipoBeneficiarioCredito.REFUGIADO.getId())) {
			return TipoBeneficiarioCredito.REFUGIADO.getDescripcion();
		}

		if (id.equalsIgnoreCase(TipoBeneficiarioCredito.FERROCARRIL.getId())) {
			return TipoBeneficiarioCredito.FERROCARRIL.getDescripcion();
		}

		if (id.equalsIgnoreCase(TipoBeneficiarioCredito.PENSION_ALIMENTICIA.getId())) {
			return TipoBeneficiarioCredito.PENSION_ALIMENTICIA.getDescripcion();
		}

		return TipoBeneficiarioCredito.NORMAL.getDescripcion();
	}
}