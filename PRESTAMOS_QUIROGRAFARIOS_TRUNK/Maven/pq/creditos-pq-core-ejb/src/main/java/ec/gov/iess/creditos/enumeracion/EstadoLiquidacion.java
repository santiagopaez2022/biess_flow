/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.enumeracion;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 20, 2007]
 * @author pablo
 */
public enum EstadoLiquidacion {

	ANU("LIQUIDACION ANULADA"), CAN("LIQUIDACION CANCELADA"), ECO("LIQUIDACION EN COMPROBANTE"), GEN(
			"LIQUIDACION GENERADO"), VEN("LIQUIDACION VENCIDA");
	
	private String descripcion;
	
	private EstadoLiquidacion(String descripcion){
		this.descripcion = descripcion;
	}

	
	/**
	 * Returns the value of descripcion.
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	
	/**
	 * Sets the value for descripcion.
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}