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
import java.util.Date;

import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * Clase de modelo con los datos del periodo de gracias del credito
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
public class PeriodoGracia implements Serializable {

	private Date fechaInicio;
	private Date fechaFin;
	private BigDecimal tasaInteres;
	private int numeroDias;
	private BigDecimal valor;
	
	/**
	 * 
	 */
	public PeriodoGracia() {
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the tasaInteres
	 */
	public BigDecimal getTasaInteres() {
		return tasaInteres;
	}

	/**
	 * @param tasaInteres the tasaInteres to set
	 */
	public void setTasaInteres(BigDecimal tasaInteres) {
		this.tasaInteres = tasaInteres;
		this.tasaInteres = UtilAjusteCalculo
				.ajusteCalculo(this.tasaInteres);		
		
	}

	/**
	 * @return the numeroDias
	 */
	public int getNumeroDias() {
		return numeroDias;
	}

	/**
	 * @param numeroDias the numeroDias to set
	 */
	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
		this.valor = UtilAjusteCalculo
				.ajusteCalculo(this.valor);		

	}

}
