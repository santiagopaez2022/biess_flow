package ec.fin.biess.creditos.pq.enumeracion;

public enum ConfiguracionPQEnum {
	
	URL_MONTO_MAXIMO("PARAMETRIZACION_PQ", "URL_MONTO_MAXIMO"),
	URL_TABLAS_AMORTIZACION("PARAMETRIZACION_PQ", "URL_TABLAS_AMORTIZACION"),
	FECHA_CONTAR_NOVACION_JUB("PARAMETRIZACION_PQ", "FECHA_CONTAR_NOVACION_JUB"),
	EDAD_NOVACIONES_JUB("PARAMETRIZACION_PQ", "EDAD_NOVACIONES_JUB");

	/**
	 * Identificador del parametro
	 */
	private String idParametro;
	
	/**
	 * Nombre del parametro
	 */
	private String nombreParametro;

	/**
	 * @param idParametro
	 * @param nombreParametro
	 */
	private ConfiguracionPQEnum(String idParametro, String nombreParametro) {
		this.idParametro = idParametro;
		this.nombreParametro = nombreParametro;
	}

	// Getters and setters
	public String getIdParametro() {
		return idParametro;
	}

	public String getNombreParametro() {
		return nombreParametro;
	}

}
