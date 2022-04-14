/**
 * FormatoValidacion.java
 * 
 * Modulo Conozca a su Cliente.
 * 
 * Copyright 2014 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 *  
 * Todos los derechos reservados.
 */
package ec.gov.iess.pq.concesion.conozcacliente.util;

/**
 * Clase para realizar validaciones de los formatos de la informacion ingresada por pantalla.
 * 
 * @author diego.iza
 */
public enum FormatoValidacionUtil implements Comparable<FormatoValidacionUtil> {

	NUMERO_TELEFONO("9999", "0#", "0#", "NUMERO_TELEFONO", "left", "return aceptarNumerosNoNegativos(event);", "stripFormatDocument(this, false);",
			"formatPasteDocument(this,false);"),

	ENTERO_SIN_MILES("9999", "0", "0", "ENTERO_SIN_MILES", "right", "return aceptarNumerosNoNegativos(event);", "stripFormat(this, false);",
			"formatPastePositive(this,false);"),

	DECIMAL_SIN_MILES("9999.99", "0.00", ".00", "DECIMAL_SIN_MILES", "right", "return aceptarNumerosDecimalesPositivos(event);",
			"stripFormat(this, true);", "formatPaste(this,true);"),

	NUMERO_DOCUMENTO("9999", "0#", "0#", "NUMERO_DOCUMENTO", "right", "return aceptarNumerosNoNegativos(event);", "stripFormatDocument(this);",
			"formatPasteDocument(this);"),

	TEXTO_SIN_ESPACIOS("ABC", "A", "A", "TEXT_SIN_ESPACIOS", "left", "", "trimObject(this);", "trimObject(this);"),

	TEXTO_SOLO("ABC", "A", "A", "TEXTO_SOLO", "left", "return aceptarLetras(event);", "return aceptarLetras(event);", "return aceptarLetras(event);");
	/**
	 * Valores a devolver.
	 */
	private final String value;
	private final String pattern;
	private final String excelPattern;
	private final String nombre;
	private final String align;
	private final String onkeypress;
	private final String onfocus;
	private final String onblur;

	/**
	 * Constructor.
	 * 
	 * @param value
	 *            - valor.
	 * @param pattern
	 *            - formato.
	 * @param excelPattern
	 *            - formato excel.
	 * @param nombre
	 *            - nombre.
	 * @param align
	 *            - alineacion.
	 * @param onkeypress
	 *            - evento onkeypress.
	 * @param onfocus
	 *            - evento onfocus.
	 * @param onblur
	 *            - evento onblur.
	 */
	private FormatoValidacionUtil(final String value, final String pattern, final String excelPattern, final String nombre, final String align,
			final String onkeypress, final String onfocus, final String onblur) {
		this.value = value;
		this.pattern = pattern;
		this.excelPattern = excelPattern;
		this.nombre = nombre;
		this.align = align;
		this.onkeypress = onkeypress;
		this.onfocus = onfocus;
		this.onblur = onblur;
	}

	/**
	 * 
	 * @return el valor.
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return el pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @return el pattern
	 */
	public String getExcelPattern() {
		return excelPattern;
	}

	/**
	 * @return el align
	 */
	public final String getAlign() {
		return align;
	}

	/**
	 * @return el onkeypress
	 */
	public final String getOnkeypress() {
		return onkeypress;
	}

	/**
	 * @return el onfocus
	 */
	public final String getOnfocus() {
		return onfocus;
	}

	/**
	 * @return el onblur
	 */
	public final String getOnblur() {
		return onblur;
	}
}