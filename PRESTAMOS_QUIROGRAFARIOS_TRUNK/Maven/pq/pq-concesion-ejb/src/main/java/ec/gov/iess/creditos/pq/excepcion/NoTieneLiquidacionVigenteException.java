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
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 20, 2007]
 * @author pablo
 */
public class NoTieneLiquidacionVigenteException extends Exception {

	private static final long serialVersionUID = -2517377315368579261L;

	public NoTieneLiquidacionVigenteException() {
	}

	public NoTieneLiquidacionVigenteException(String arg0) {
		super(arg0);
	}

	public NoTieneLiquidacionVigenteException(Throwable arg0) {
		super(arg0);
	}

	public NoTieneLiquidacionVigenteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
