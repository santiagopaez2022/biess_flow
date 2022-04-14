package ec.gov.iess.creditos.enumeracion;

/**
 * Enum para el tipo de tabla a seleccionar
 * 
 * @author diana.suasnavas
 *
 */
public enum TipoTablaEnum {

	ALEMANA, FRANCESA;

	public String value() {
		return this.name();
	}

	public TipoTablaEnum fromValue(String v) {
		return valueOf(v);
	}

}
