package ec.gov.iess.pq.concesion.web.enumeration;

public enum UnidadFechaEnum {

	SEGUNDO(1000), MINUTO(60000), HORA(3600000), DIA(86400000L), MES(2592000000L);// para el mes se lo aproxima con 30
	// dias

	private final long valor;

	UnidadFechaEnum(long valor) {
		this.valor = valor;
	}

	/**
	 * @return the valor
	 */
	public long getValor() {
		return valor;
	}

}
