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
 * @author pmlopez
 * @author Daniel Cardenas
 * @version $Revision: 1.1 03/10/2011 $
 * 
 */
public enum TipoPrestamista {

	AFILIADO("Afiliado", "afiliado-r.xml", "general-r.xml"), JUBILADO("Jubilado - Pensionista", "jubilado-r.xml",
			"general-r.xml"), AFILIADO_JUBILADO("Afiliado y jubilado", "afiliado-r.xml", "jubilado-r.xml",
			"general-r.xml"), ZAFRERO_TEMPORAL("Zafrero Temporal"), VOLUNTARIO("Voluntario"), VOLUNTARIO_EXT(
			"Voluntario Extranjero"), CESANTE("Cesante"), VOLUNTARIO_JUBILADO("Voluntario y jubilado"), 
			VOLUNTARIO_AFILIADO("Voluntario y afiliado"), UNIPERSONAL_JUBILADO("Unipersonal y jubilado"), 
			UNIPERSONAL_AFILIADO("Unipersonal y afiliado"), UNIPERSONAL("Unipersonal");

	private String[] archivos;
	private String etiqueta;

	/*
	 * private TipoPrestamista(String... archivos) { this.archivos = archivos; }
	 */

	private TipoPrestamista(String etiqueta, String... archivos) {
		this.etiqueta = etiqueta;
		this.archivos = archivos;
	}

	/**
	 * Returns the value of archivos.
	 * 
	 * @return archivos
	 */
	public String[] getArchivos() {
		return archivos;
	}

	/**
	 * Sets the value for archivos.
	 * 
	 * @param archivos
	 */
	public void setArchivos(String[] archivos) {
		this.archivos = archivos;
	}

	/**
	 * @return the etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * @param etiqueta
	 *            the etiqueta to set
	 */
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
}