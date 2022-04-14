/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package ec.gov.iess.creditos.excepcion;

import javax.ejb.ApplicationException;


/**
 * 
 *  
 * @version $Revision: 1.1 $  [Sep 17, 2007]
 * @author acantos
 */
@ApplicationException(rollback=true)
public class ReversaPrestamoExcepcion extends Exception {

	private static final long serialVersionUID = 7365294253242032519L;

	public ReversaPrestamoExcepcion() {
	}

	public ReversaPrestamoExcepcion(String message) {
		super(message);
	}

	public ReversaPrestamoExcepcion(Throwable cause) {
		super(cause);
	}

	public ReversaPrestamoExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
}