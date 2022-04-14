package ec.gov.iess.pq.concesion.web.util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilitario para encriptacion de codigo
 * 
 * @author hugo.mora
 *
 */
public class EncriptacionUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final char[] HEXADECIMALES = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * Genera encriptacion MD5
	 * 
	 * @param cadena
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generarMD5(String cadena) throws NoSuchAlgorithmException {
		String cadenaEncriptada = "";
		if (cadena != null) {
			MessageDigest msgdgt = MessageDigest.getInstance("MD5");
			byte[] bytes = msgdgt.digest(cadena.getBytes());
			StringBuilder strCryptMD5 = new StringBuilder(2 * bytes.length);
			for (int i = 0; i < bytes.length; i++) {
				int low = (int) (bytes[i] & 0x0f);
				int high = (int) ((bytes[i] & 0xf0) >> 4);
				strCryptMD5.append(HEXADECIMALES[high]);
				strCryptMD5.append(HEXADECIMALES[low]);
			}
			cadenaEncriptada = strCryptMD5.toString();
		}
		return cadenaEncriptada;
	}

}
