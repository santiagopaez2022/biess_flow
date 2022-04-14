/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package ec.gov.iess.seguridades.ws.model.excepcion;

/**
 * @author Chan - Cobiscorp
 * @version $Revision: 1.2 $
 */
public class MetodoServicioDuplicadoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2687687852779582843L;
	
	/**
	 * 
	 */
	public MetodoServicioDuplicadoException() {
		super();
	}
	
	/**
	 * @param message
	 */
	public MetodoServicioDuplicadoException(String message) {
		super(message);
	}
	
	/**
	 * @param cause
	 */
	public MetodoServicioDuplicadoException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public MetodoServicioDuplicadoException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
