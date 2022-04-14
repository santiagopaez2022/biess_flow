package ec.gov.iess.pq.concesion.conozcacliente.constant;

/**
 * @author edison.cayambe
 *
 */
public class Validador {
	// para validar telefono
	public static final String REGEX_EVALUA_TELEFONOS = "(0\\d{8})?";

	// para validar celular
	public static final String REGEX_EVALUA_CELULAR = "0\\d{9}|^$";

	// para validar email
	public static final String REGEX_EVALUA_EMAILS = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$|^$";

	// para validar alfa (solo letras), permite espacios entre letras
	public static final String REGEX_EVALUA_ALFA = "^$|[a-zA-Z ]+";

	// para validar alfanumericos (solo letras y numeros), permite espacios
	public static final String REGEX_EVALUA_ALFA_NUMERICOS = "[0-9a-zA-Z ]+";

	// para validar numeros de cuenta
	public static final String REGEX_EVALUA_NUMERO_CUENTA = "[0-9]+";
}
