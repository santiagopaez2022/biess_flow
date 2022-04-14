/**
 * 
 */
package ec.gov.iess.creditos.pq.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cristian.yaselga
 * 
 */
public class ParametroSingleton {

	private static ParametroSingleton instancia = null;
	private Map<String, Object> parametrosMap;

	/**
	 * 
	 */
	private ParametroSingleton() {

	}

	/**
	 * determina una instancia unica
	 * 
	 * @return
	 */
	public static ParametroSingleton getInstancia() {
		if (instancia == null) {
			instancia = new ParametroSingleton();
		}
		return instancia;
	}

	public Map<String, Object> getParametrosMap() {
		if(parametrosMap == null){
			parametrosMap = new ConcurrentHashMap<String, Object>();
		}
		return parametrosMap;
	}

	public void setParametrosMap(Map<String, Object> parametrosMap) {
		this.parametrosMap = parametrosMap;
	}

}
