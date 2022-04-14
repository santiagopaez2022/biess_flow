package ec.fin.biess.pq.simulacion.util;

import java.net.UnknownHostException;

public class Utiltario {

	/**
	 * Obtiene la IP del servidor
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public static String obtenerIPServer() throws UnknownHostException {
		String ipServer = java.net.InetAddress.getLocalHost().getHostAddress();

		return ipServer;
	}

}
