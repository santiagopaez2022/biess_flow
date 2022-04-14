/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.enumeracion;

/**
 * Enumeracion que contiene la lista de discapacidades de una persona, las cuales son condideradas en PQ.
 * 
 * @author Omar Villanueva
 * @version 1.0
 *
 */
public enum DiscapacitadosEnum {

	DISCAPACIDAD_MENTAL_MAYOR_DE_EDAD("DISCAPACIDAD MENTAL MAYOR DE EDAD"),
	DISCAPACIDAD_FISICA_MAYOR_DE_EDAD("DISCAPACIDAD FISICA MAYOR DE EDAD"),
	DISCAPACITADO_FISICO_MENOR_EXTRANJERO("DISCAPACITADO FISICO MENOR EXTRANJERO"),
	DISCAPACITADO_FISICO_EXTRANJERO("DISCAPACITADO FISICO EXTRANJERO");
	
	private String id;
	
	private DiscapacitadosEnum(String id){
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
}
