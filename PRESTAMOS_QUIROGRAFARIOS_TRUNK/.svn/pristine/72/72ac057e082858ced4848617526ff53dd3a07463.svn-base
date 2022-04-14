package ec.fin.biess.unlock.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import ec.gov.biess.util.log.LoggerBiess;

/**
 * Clase que encripta una cadena en MD5
 * 
 * @author edwin.maza
 * 
 */
public class MD5Helper {

	private static final LoggerBiess log = LoggerBiess.getLogger(MD5Helper.class);

	public static String hash(String cadena) {

		if (cadena == null) {
			return null;
		}

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(cadena.getBytes());
			return toHex(md.digest());

		} catch (NoSuchAlgorithmException e) {
			log.error("Fallo al cargar el algoritmo MD5 de MessageDigest.", e);
		}
		return null;
	}

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
