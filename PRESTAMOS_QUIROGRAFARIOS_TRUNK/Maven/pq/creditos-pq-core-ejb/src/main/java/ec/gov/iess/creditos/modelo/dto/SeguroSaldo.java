/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * Clase modelo para los datos del seguro de saldo por fecha, edad, plazo
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
public class SeguroSaldo implements Serializable {

	private BigDecimal tasaInteres;
	private int plazo;
	private int edad;
	private BigDecimal valor;

	/**
	 * 
	 */
	public SeguroSaldo() {
	}

	/**
	 * @return the tasaInteres
	 */
	public BigDecimal getTasaInteres() {
		return tasaInteres;
	}

	/**
	 * @param tasaInteres
	 *            the tasaInteres to set
	 */
	public void setTasaInteres(BigDecimal tasaInteres) {
		this.tasaInteres = tasaInteres;
		this.tasaInteres = this.tasaInteres.setScale(6,
				BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @return the plazo
	 */
	public int getPlazo() {
		return plazo;
	}

	/**
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @param edad
	 *            the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
		this.valor = UtilAjusteCalculo.ajusteCalculo(this.valor);
	}

}
