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
import java.util.List;

import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * Clase modelo que contiene los datos del calculo del prestamo, que son:
 * 
 * <ul>
 * <li>Periodo de gracia</li>
 * <li>Seguro de saldo</li>
 * <li>Datos prestamo</li>
 * <li>Cuotas</li>
 * </ul>
 * 
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
public class PrestamoCalculo implements Serializable {

	private SeguroSaldo seguroSaldo;
	private PeriodoGracia periodoGracia;
	private PeriodoGracia periodoGraciaInterZafra;
	private BigDecimal periodoGraciaPorrateadoInterZafra; 
	private List<DividendoCalculo> dividendoCalculoList;

	/*
	 * datos de la cabecera
	 */

	private BigDecimal valorCredito;
	private int anio;
	private int mes;
	private BigDecimal valorTotalDividendoMensual;
	private int plazoMeses;
	private BigDecimal liquidoPagar;

	// TODO llenar
	private Date fechaInicioPrestamo;
	private Date fechaFinPrestamo;
	private String cedula;
	private BigDecimal montoPrestamo;
	private Date fechaTrasferencia;

	/**
	 * Se llena en la capa web al momento de aceptar la creación de un préstamo
	 * Nos ayudará en la capa de nogocio para saber si hay que guardar garantías
	 */
	private Boolean guardarGarantias;

	/**
	 * Para tener valor liquidado del prestamo anterior por novacion
	 */
	private BigDecimal montoCanceladoNovacion;
	
	private BigDecimal tasaInteres;
	
	private BigDecimal totalInteres;

	/**
	 * @return the fechaTrasferencia
	 */
	public Date getFechaTrasferencia() {
		return fechaTrasferencia;
	}

	/**
	 * @param fechaTrasferencia
	 *            the fechaTrasferencia to set
	 */
	public void setFechaTrasferencia(Date fechaTrasferencia) {
		this.fechaTrasferencia = fechaTrasferencia;
	}

	/**
	 * @return the montoPrestamo
	 */
	public BigDecimal getMontoPrestamo() {
		return montoPrestamo;
	}

	/**
	 * @param montoPrestamo
	 *            the montoPrestamo to set
	 */
	public void setMontoPrestamo(BigDecimal montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public PrestamoCalculo() {
	}

	/**
	 * @return the periodoGracia
	 */
	public PeriodoGracia getPeriodoGracia() {
		return periodoGracia;
	}

	/**
	 * @param periodoGracia
	 *            the periodoGracia to set
	 */
	public void setPeriodoGracia(PeriodoGracia periodoGracia) {
		this.periodoGracia = periodoGracia;
	}

	public PeriodoGracia getPeriodoGraciaInterZafra() {
		return periodoGraciaInterZafra;
	}

	public void setPeriodoGraciaInterZafra(PeriodoGracia periodoGraciaInterZafra) {
		this.periodoGraciaInterZafra = periodoGraciaInterZafra;
	}
	
	public BigDecimal getPeriodoGraciaPorrateadoInterZafra() {
		return periodoGraciaPorrateadoInterZafra;
	}

	public void setPeriodoGraciaPorrateadoInterZafra(
			BigDecimal periodoGraciaPorrateadoInterZafra) {
		this.periodoGraciaPorrateadoInterZafra = periodoGraciaPorrateadoInterZafra;
	}

	/**
	 * @return the dividendoCalculoList
	 */
	public List<DividendoCalculo> getDividendoCalculoList() {
		return dividendoCalculoList;
	}

	/**
	 * @param dividendoCalculoList
	 *            the dividendoCalculoList to set
	 */
	public void setDividendoCalculoList(
			List<DividendoCalculo> dividendoCalculoList) {
		this.dividendoCalculoList = dividendoCalculoList;
	}

	/**
	 * @return the valorCredito
	 */
	public BigDecimal getValorCredito() {
		return valorCredito;
	}

	/**
	 * @param valorCredito
	 *            the valorCredito to set
	 */
	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
		this.valorCredito = UtilAjusteCalculo.ajusteCalculo(this.valorCredito);
	}

	/**
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * @return the mes
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * @return the valorTotalDividendoMensual
	 */
	public BigDecimal getValorTotalDividendoMensual() {
		return valorTotalDividendoMensual;
	}

	/**
	 * @param valorTotalDividendoMensual
	 *            the valorTotalDividendoMensual to set
	 */
	public void setValorTotalDividendoMensual(
			BigDecimal valorTotalDividendoMensual) {
		this.valorTotalDividendoMensual = valorTotalDividendoMensual;
		this.valorTotalDividendoMensual = UtilAjusteCalculo
				.ajusteCalculo(this.valorTotalDividendoMensual);
	}

	/**
	 * @return the plazoMeses
	 */
	public int getPlazoMeses() {
		return plazoMeses;
	}

	/**
	 * @param plazoMeses
	 *            the plazoMeses to set
	 */
	public void setPlazoMeses(int plazoMeses) {
		this.plazoMeses = plazoMeses;
	}

	/**
	 * @return the liquidoPagar
	 */
	public BigDecimal getLiquidoPagar() {
		return liquidoPagar;
	}

	/**
	 * @param liquidoPagar
	 *            the liquidoPagar to set
	 */
	public void setLiquidoPagar(BigDecimal liquidoPagar) {
		this.liquidoPagar = liquidoPagar;
		this.liquidoPagar = UtilAjusteCalculo.ajusteCalculo(this.liquidoPagar);
	}

	/**
	 * @return the seguroSaldo
	 */
	public SeguroSaldo getSeguroSaldo() {
		return seguroSaldo;
	}

	/**
	 * @param seguroSaldo
	 *            the seguroSaldo to set
	 */
	public void setSeguroSaldo(SeguroSaldo seguroSaldo) {
		this.seguroSaldo = seguroSaldo;
	}

	/**
	 * @return the fechaInicioPrestamo
	 */
	public Date getFechaInicioPrestamo() {
		return fechaInicioPrestamo;
	}

	/**
	 * @return the fechaFinPrestamo
	 */
	public Date getFechaFinPrestamo() {
		return fechaFinPrestamo;
	}

	/**
	 * @param fechaInicioPrestamo
	 *            the fechaInicioPrestamo to set
	 */
	public void setFechaInicioPrestamo(Date fechaInicioPrestamo) {
		this.fechaInicioPrestamo = fechaInicioPrestamo;
	}

	/**
	 * @param fechaFinPrestamo
	 *            the fechaFinPrestamo to set
	 */
	public void setFechaFinPrestamo(Date fechaFinPrestamo) {
		this.fechaFinPrestamo = fechaFinPrestamo;
	}

	public Boolean getGuardarGarantias() {
		return guardarGarantias;
	}

	public void setGuardarGarantias(Boolean guardarGarantias) {
		this.guardarGarantias = guardarGarantias;
	}

	public BigDecimal getMontoCanceladoNovacion() {
		return montoCanceladoNovacion;
	}

	public void setMontoCanceladoNovacion(BigDecimal montoCanceladoNovacion) {
		this.montoCanceladoNovacion = montoCanceladoNovacion;
	}

	public BigDecimal getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(BigDecimal tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public BigDecimal getTotalInteres() {
		return totalInteres;
	}

	public void setTotalInteres(BigDecimal totalInteres) {
		this.totalInteres = totalInteres;
	}

}
