package ec.fin.biess.unlock.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Clase que brinda funciones utiles.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
public class Utilities {

	/**
	 * Obtiene un token alfanumerico randomico y unico
	 * 
	 * @return String
	 */
	public static final String getToken() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Metodo que formatea una fecha.
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static final String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);
	}

	/**
	 * Metodo que encripta una cadena en MD5.
	 * 
	 * @param cadena
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
	public static String hash(String cadena) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(cadena.getBytes());

		return toHex(md.digest());
	}

	/**
	 * Metodo que transforma un byte[] a hexadecimal.
	 * 
	 * @param bytes
	 * @return String
	 */
	private static final String toHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xFF) < 16) {
				buf.append("0");
			}
			buf.append(Long.toString(bytes[i] & 0xFF, 16));
		}

		return buf.toString();
	}

}
