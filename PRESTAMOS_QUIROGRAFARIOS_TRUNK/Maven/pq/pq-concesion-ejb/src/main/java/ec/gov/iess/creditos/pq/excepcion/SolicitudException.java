/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package ec.gov.iess.creditos.pq.excepcion;

/**
 * @author jvaca
 *
 */
public class SolicitudException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4295940741277477856L;

	/**
	 * 
	 */
	public SolicitudException() {
	}

	/**
	 * @param message
	 */
	public SolicitudException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SolicitudException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SolicitudException(String message, Throwable cause) {
		super(message, cause);
	}

}