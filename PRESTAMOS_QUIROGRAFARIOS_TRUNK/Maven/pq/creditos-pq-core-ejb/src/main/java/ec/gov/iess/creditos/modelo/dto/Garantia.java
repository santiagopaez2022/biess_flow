/**
 * 
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import ec.gov.iess.creditos.modelo.persistencia.PrestacionConcesion;

/**
 * 
 * <b> Datos generales de la garantía. </b>
 * 
 * @author cvillarreal,cbastidas
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: cbastidas $, $Date: 03/10/2011 $]
 *          </p>
 */
public class Garantia implements Serializable {

	private static final long serialVersionUID = 8878504479246125641L;

	private BigDecimal garantiaFrSac;
	
	private BigDecimal garantiaCesSac;

	private BigDecimal sueloPromedio;

	private BigDecimal cupoMaximoCredito;

	private BigDecimal totalGarantia;

	private BigDecimal totalGarantiaAjustada;

	private BigDecimal sumaGarantias;

	private List<DetalleGarantia> detalleGarantiaList;

	// valor para cuota de Buro de Credito
	private BigDecimal cuotaMensualBuro;

	// Lista de Ingresos de la garantía
	private List<DetalleCalculoIngresos> detalleCalculoIngresos;

	// Lista de Egresos de la garantía
	private List<DetalleCalculoEgresos> detalleCalculoEgresos;

	// Capacidad de Pago
	private BigDecimal capacidadPago;

	// Capacidad de Endeudamiento
	private BigDecimal capacidadEndeudamiento;

	// Cuota Hipotecarios
	private BigDecimal cuotaHipotecarios;

	// Sueldo Promedio Original
	private BigDecimal sueldoPromedioOriginal;

	// Valor comprometido fondos de reserva
	private BigDecimal valorComprometidoFR;

	// Valor comprometido cesantias
	private BigDecimal valorComprometidoCesantia;

	// LIsta de Prestaciones para Concesión
	private List<PrestacionConcesion> listPrestamoConcesion;
	
	
	// cuota hipotacario monto 
	private BigDecimal cuotaHipotecario;
	
	// cuota hipotacario monto 
	private BigDecimal cuotaHipotecarioMnt;
	
	// numero total de pq vigentes
	private Integer numTotPQVig;
	
	// sumatoria total de div de pq vigentes
	private BigDecimal sumDivPq;
	
	// sumtoria total de monto de pq vigentes
	private BigDecimal sumMntPq;
	
	
	// numero total de ph vigentes
	private Integer numTotPHVig;
	
	// sumatoria total de div de ph vigentes
	private BigDecimal sumDivPh;
	
	// sumtoria total de monto de ph vigentes
	private BigDecimal sumMntPh;
	
	private Long numImposicionesCon;
	
	private Long numImposiciones;

	/**
	 * datos de garantia
	 */
	private DatosGarantia datGarantia;

	public Garantia() {
	}

	public Garantia(BigDecimal sueloPromedioNew,
			BigDecimal cupoMaximoCreditoNew,
			List<DetalleGarantia> detalleGarantiaListNew) {

		this.sueloPromedio = sueloPromedioNew;
		this.cupoMaximoCredito = cupoMaximoCreditoNew;
		this.detalleGarantiaList = detalleGarantiaListNew;

	}

	public BigDecimal getCupoMaximoCredito() {
		return cupoMaximoCredito;
	}

	public void setCupoMaximoCredito(BigDecimal cupoMaximoCredito) {
		this.cupoMaximoCredito = cupoMaximoCredito;
	}

	public List<DetalleGarantia> getDetalleGarantiaList() {
		return detalleGarantiaList;
	}

	public void setDetalleGarantiaList(List<DetalleGarantia> detalleGarantiaList) {
		this.detalleGarantiaList = detalleGarantiaList;
	}

	public BigDecimal getSueloPromedio() {
		return sueloPromedio;
	}

	public void setSueloPromedio(BigDecimal sueloPromedio) {
		this.sueloPromedio = sueloPromedio;
	}

	public BigDecimal getTotalGarantia() {
		return totalGarantia;
	}

	public void setTotalGarantia(BigDecimal totalGarantia) {
		this.totalGarantia = totalGarantia;
	}

	public BigDecimal getTotalGarantiaAjustada() {
		return totalGarantiaAjustada;
	}

	public void setTotalGarantiaAjustada(BigDecimal totalGarantiaAjustada) {
		this.totalGarantiaAjustada = totalGarantiaAjustada;
	}

	/**
	 * @return the cuotaMensualBuro
	 */
	public BigDecimal getCuotaMensualBuro() {
		return cuotaMensualBuro;
	}

	/**
	 * @param cuotaMensualBuro
	 *            the cuotaMensualBuro to set
	 */
	public void setCuotaMensualBuro(BigDecimal cuotaMensualBuro) {
		this.cuotaMensualBuro = cuotaMensualBuro;
	}

	/**
	 * @return the sumaGarantias
	 */
	public BigDecimal getSumaGarantias() {

		if (null != detalleGarantiaList) {
			Float valor = 0F;
			for (DetalleGarantia detalle : detalleGarantiaList) {
				valor += detalle.getValorGarantia().floatValue();
			}
			sumaGarantias = new BigDecimal(valor).setScale(2,
					RoundingMode.HALF_UP);

		}
		return sumaGarantias;
	}

	/**
	 * @param sumaGarantias
	 *            the sumaGarantias to set
	 */
	public void setSumaGarantias(BigDecimal sumaGarantias) {
		this.sumaGarantias = sumaGarantias;
	}

	public List<DetalleCalculoIngresos> getDetalleCalculoIngresos() {
		return detalleCalculoIngresos;
	}

	public void setDetalleCalculoIngresos(
			List<DetalleCalculoIngresos> detalleCalculoIngresos) {
		this.detalleCalculoIngresos = detalleCalculoIngresos;
	}

	public List<DetalleCalculoEgresos> getDetalleCalculoEgresos() {
		return detalleCalculoEgresos;
	}

	public void setDetalleCalculoEgresos(
			List<DetalleCalculoEgresos> detalleCalculoEgresos) {
		this.detalleCalculoEgresos = detalleCalculoEgresos;
	}

	public BigDecimal getCapacidadPago() {
		return capacidadPago;
	}

	public void setCapacidadPago(BigDecimal capacidadPago) {
		this.capacidadPago = capacidadPago;
	}

	public BigDecimal getCapacidadEndeudamiento() {
		return capacidadEndeudamiento;
	}

	public void setCapacidadEndeudamiento(BigDecimal capacidadEndeudamiento) {
		this.capacidadEndeudamiento = capacidadEndeudamiento;
	}

	public BigDecimal getCuotaHipotecarios() {
		return cuotaHipotecarios;
	}

	public void setCuotaHipotecarios(BigDecimal cuotaHipotecarios) {
		this.cuotaHipotecarios = cuotaHipotecarios;
	}

	public BigDecimal getSueldoPromedioOriginal() {
		return sueldoPromedioOriginal;
	}

	public void setSueldoPromedioOriginal(BigDecimal sueldoPromedioOriginal) {
		this.sueldoPromedioOriginal = sueldoPromedioOriginal;
	}

	public BigDecimal getValorComprometidoFR() {
		return valorComprometidoFR;
	}

	public void setValorComprometidoFR(BigDecimal valorComprometidoFR) {
		this.valorComprometidoFR = valorComprometidoFR;
	}

	public BigDecimal getValorComprometidoCesantia() {
		return valorComprometidoCesantia;
	}

	public void setValorComprometidoCesantia(
			BigDecimal valorComprometidoCesantia) {
		this.valorComprometidoCesantia = valorComprometidoCesantia;
	}

	public List<PrestacionConcesion> getListPrestamoConcesion() {
		return listPrestamoConcesion;
	}

	public void setListPrestamoConcesion(
			List<PrestacionConcesion> listPrestamoConcesion) {
		this.listPrestamoConcesion = listPrestamoConcesion;
	}

	

	public BigDecimal getCuotaHipotecario() {
		return cuotaHipotecario;
	}

	public void setCuotaHipotecario(BigDecimal cuotaHipotecario) {
		this.cuotaHipotecario = cuotaHipotecario;
	}

	public BigDecimal getCuotaHipotecarioMnt() {
		return cuotaHipotecarioMnt;
	}

	public void setCuotaHipotecarioMnt(BigDecimal cuotaHipotecarioMnt) {
		this.cuotaHipotecarioMnt = cuotaHipotecarioMnt;
	}

	public Integer getNumTotPQVig() {
		return numTotPQVig;
	}

	public void setNumTotPQVig(Integer numTotPQVig) {
		this.numTotPQVig = numTotPQVig;
	}

	public BigDecimal getSumDivPq() {
		return sumDivPq;
	}

	public void setSumDivPq(BigDecimal sumDivPq) {
		this.sumDivPq = sumDivPq;
	}

	public BigDecimal getSumMntPq() {
		return sumMntPq;
	}

	public void setSumMntPq(BigDecimal sumMntPq) {
		this.sumMntPq = sumMntPq;
	}

	public Integer getNumTotPHVig() {
		return numTotPHVig;
	}

	public void setNumTotPHVig(Integer numTotPHVig) {
		this.numTotPHVig = numTotPHVig;
	}

	public BigDecimal getSumDivPh() {
		return sumDivPh;
	}

	public void setSumDivPh(BigDecimal sumDivPh) {
		this.sumDivPh = sumDivPh;
	}

	public BigDecimal getSumMntPh() {
		return sumMntPh;
	}

	public void setSumMntPh(BigDecimal sumMntPh) {
		this.sumMntPh = sumMntPh;
	}

	public Long getNumImposicionesCon() {
		return numImposicionesCon;
	}

	public void setNumImposicionesCon(Long numImposicionesCon) {
		this.numImposicionesCon = numImposicionesCon;
	}

	public Long getNumImposiciones() {
		return numImposiciones;
	}

	public void setNumImposiciones(Long numImposiciones) {
		this.numImposiciones = numImposiciones;
	}
	
	/**
	 * @return the garantiaFrSac
	 */
	public BigDecimal getGarantiaFrSac() {
		return garantiaFrSac;
	}

	/**
	 * @param garantiaFrSac the garantiaFrSac to set
	 */
	public void setGarantiaFrSac(BigDecimal garantiaFrSac) {
		this.garantiaFrSac = garantiaFrSac;
	}

	/**
	 * @return the garantiaCesSac
	 */
	public BigDecimal getGarantiaCesSac() {
		return garantiaCesSac;
	}

	/**
	 * @param garantiaCesSac the garantiaCesSac to set
	 */
	public void setGarantiaCesSac(BigDecimal garantiaCesSac) {
		this.garantiaCesSac = garantiaCesSac;
	}
	
	public DatosGarantia getDatGarantia() {
		return datGarantia;
	}

	public void setDatGarantia(DatosGarantia datGarantia) {
		this.datGarantia = datGarantia;
	}
	
	

}
