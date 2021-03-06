package ec.gov.iess.creditos.enumeracion;

public enum PrestacionesValidasCredito {
	INVALIDEZ_FE_IN("FE", "IN", "INVALIDEZ DEL SEGURO DE FERROCARRILES"), VEJEZ_FE_JV(
			"FE", "JV", "JUBILACIÓN DE VEJEZ DEL SEGURO DE FERROCARRILES"), INVALIDEZ_GR_IN(
			"GR", "IN", "INVALIDEZ DEL SEGURO DE ARTES GRAFICAS"), VEJEZ_GR_JV(
			"GR", "JV", "JUBILACIÓN DE VEJEZ DEL SEURO DE ARTES GRAFICAS"), MEJORAS_GR_MJ(
			"GR", "MJ", "MEJORAS DE JUBILACIÓN DEL SEURO DE ARTES GRAFICAS"), ADICIONAL_MAGISTARIO_MB_AM(
			"MB", "AM", "ADICIONAL MAGISTERIO BIENESTAR SOCIAL"), ADICIONAL_MAGISTERIO_MF_AM(
			"MF", "AM", "ADICIONAL MAGISTERIO FISCAL"), INVALIDEZ_MF_IN("MF",
			"IN", "JUBILACION POR INVALIDEZ DEL MAGISTERIO FISCAL"), VEJEZ_MF_JV(
			"MF", "JV", "JUBILACIÓN DE VEJEZ"), MEJORA_MF_MJ("MF", "MJ",
			"MEJORA DEL MAGISTERIO FISCAL"), ACCIDENTE_TRABAJO_RT_AT("RT",
			"AT", "RENTA POR ACCCIDENTE DE TRABAJO"), ACCIDENTE_TRABAJO_RT_PT(
			"RT", "PT", "RENTA POR PERMANENTES TOTALES"), ACCIDENTE_TRABAJO_RT_PA(
			"RT", "PA", "RENTA POR PERMANENTES ABSOLUTOS"), INVALIDEZ_RT_IN(
			"RT", "IN", "INVALIDEZ DEL SEGURO DE RIESGOS DEL TRABAJO"), ESPECIAL_REDUCIDA_SG_ER(
			"SG", "ER", "ESPECIAL REDUCIDA"), ESPECIAL_REDUCIDA_FE_ER("FE",
			"ER", "ESPECIAL REDUCIDA SEGURO DE FERROCARRILES"), ESPECIAL_REDUCIDA_GR_ER(
			"GR", "ER", "ESPECIAL REDUCIDA SEGURO DE ARTES GRAFICAS"), ESPECIAL_REDUCIDA_TE_ER(
			"TE", "ER", "ESPECIAL REDUCIDA SEGURO DE TELECOMUNICACIONES"), ESPECIAL_REDUCIDA_MF_ER(
			"MF", "ER", "ESPECIAL REDUCIDA DEL MAGISTERIO FISCAL"), INVALIDEZ_SG_IN(
			"SG", "IN", "JUBILACIÓN POR INVALIDEZ DEL SEGURO GENERAL"), INVALIDEZ_TE_IN(
			"TE", "IN",
			"JUBILACIÓN POR INVALIDEZ DEL SEGURO DE TELECOMUNICACIONES"),
	// JUBILACION_PATRONAL_SG_JP("SG", "JP", "JUBILACION PATRONAL"),
	VEJEZ_SG_JV("SG", "JV", "JUBILACIÓN POR VEJEZ DEL SEGURO GENERAL"), MEJORA_SG_MJ(
			"SG", "MJ", "MEJORAS DE PENSIÓN DEL SEGURO GENERAL"), VEJEZ_TE_JV(
			"TE", "JV", "JUBILACIÓN DE VEJEZ DEL SEGURO DE TELECOMUNICACIONES"), MEJORA_TE_MJ(
			"TE", "MJ", "MEJORA TELECOMUNICACIONES"), VIUDEZ_FE_VO("FE", "VO",
			"VIUDEDAD DEL SEGURO DE FERROCARRILES"), VIUDEZ_GR_VO("GR", "VO",
			"VIUDEDAD DEL SEGURO DE SEGURO DE ARTES GRAFICAS"), VIUDEZ_MB_VO(
			"MB", "VO", "VIUDEDAD DEL MAGISTERIO BIENESTAR SOCIAL"), VIUDEZ_MF_VO(
			"MF", "VO", "VIUDEDAD DEL MAGISTERIO FISCAL"), VIUDEZ_RT_VO("RT",
			"VO", "VIUDEDAD DEL SEGURO DE RIESGOS DEL TRABAJO"), VIUDEZ_SG_VO(
			"SG", "VO", "VIUDEDAD DEL SEGURO GENERAL "), VIUDEZ_TE_VO("TE",
			"VO", "VIUDEDAD DEL SEGURO DE TELECOMUNICACIONES"), PERMANENTE_PARCIAL_RT_PP(
			"RT", "PP", "PERMANETE PARCIAL DE RIESGO DE TRABAJO");

	private String tipoSeguro;
	private String tipoPrestacion;
	private String nombre;

	PrestacionesValidasCredito(String tipoSeguro, String tipoPrestacion,
			String nombre) {
		this.tipoSeguro = tipoSeguro;
		this.tipoPrestacion = tipoPrestacion;
		this.nombre = nombre;
	}

	public String getTipoSeguro() {
		return tipoSeguro;
	}

	public String getTipoPrestacion() {
		return tipoPrestacion;
	}

	public String getNombre() {
		return nombre;
	}

	public static String obtenerNombre(String tipoSeguro, String tipoPrestacion) {
		String nombre = null;
		for (PrestacionesValidasCredito p : PrestacionesValidasCredito.values()) {
			if (p.getTipoSeguro().equals(tipoSeguro)
					&& p.getTipoPrestacion().equals(tipoPrestacion)) {
				nombre = p.getNombre();
			}
		}
		return nombre;
	}

}
