/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.excepcion;


/**
 * Exceptio para la clase Parametro
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 *
 */
public class ParametroCreditoException extends Exception {

	private static final long serialVersionUID = -2611005433498470621L;

	public ParametroCreditoException() {
	}

	public ParametroCreditoException(String message) {
		super(message);
	}

	public ParametroCreditoException(Throwable cause) {
		super(cause);
	}

	public ParametroCreditoException(String message, Throwable cause) {
		super(message, cause);
	}

}
