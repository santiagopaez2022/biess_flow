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
 * @author pmlopez
 * @version $Revision: 1.1 $
 * 
 */
public class ObtenerRolesExcepcion extends Exception {

	private static final long serialVersionUID = 1950571753244835843L;

	public ObtenerRolesExcepcion(){
		super();
	}

	public ObtenerRolesExcepcion(String message){
		super(message);
	}

	public ObtenerRolesExcepcion(Throwable throwable){
		super(throwable);
	}

	public ObtenerRolesExcepcion(String message, Throwable throwable){
		super(message, throwable);
	}
}
