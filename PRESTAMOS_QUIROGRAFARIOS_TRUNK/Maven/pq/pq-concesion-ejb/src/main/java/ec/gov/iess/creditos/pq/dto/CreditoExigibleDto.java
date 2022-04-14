package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

/**
 * DTO que obtiene informacion de los creditos exigibles desde el SAC
 * 
 * @author hugo.mora
 * @date 2018/08/31
 *
 */
public class CreditoExigibleDto implements Serializable, Comparable<CreditoExigibleDto> {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(CreditoExigibleDto.class);

	private static final long serialVersionUID = 99898001545789345L;

	private Long idProducto;

	private String destinoComercial;

	private Long operacionSAC;

	private Long nut;

	private BigDecimal saldoOperacion;
	
	private BigDecimal saldoOperacionEmp;
	
	private BigDecimal montoConcedido;

	private Long diasMora;

	private BigDecimal cuota;

	private String descProducto;

	private String estado;

	private BigDecimal plazo;

	private String fechaConcesion;

	private String fechaVencimiento;

	private BigDecimal totalCuotas;

	private BigDecimal cuotasFaltantes;

	private BigDecimal montoMora;

	private BigDecimal montoPrecancelar;

	private String numeroTicket;

	private List<DatoDeudaDto> datosDeuda;

	private String fechaPagoLiquid;

	private BigDecimal montoCapital;

	private BigDecimal valorSeguroSaldos;

	private List<DatoReferencia> datosTotalesDeuda;

	private BigDecimal numeroDividendo;

	private BigDecimal interesGracia;
	
	private BigDecimal interesVencimientoAnticipado;
	
	private BigDecimal montoInteresVencimiento;

        private Double tasaSegSaldos;
	

	// Getters and setters
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(final Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getDestinoComercial() {
		return destinoComercial;
	}

	public void setDestinoComercial(final String destinoComercial) {
		this.destinoComercial = destinoComercial;
	}

	public Long getOperacionSAC() {
		return operacionSAC;
	}

	public void setOperacionSAC(final Long operacionSAC) {
		this.operacionSAC = operacionSAC;
	}

	public Long getNut() {
		return nut;
	}

	public void setNut(final Long nut) {
		this.nut = nut;
	}

	public BigDecimal getSaldoOperacion() {
		return saldoOperacion;
	}

	public void setSaldoOperacion(final BigDecimal saldoOperacion) {
		this.saldoOperacion = saldoOperacion;
	}

	public BigDecimal getMontoConcedido() {
		return montoConcedido;
	}

	public void setMontoConcedido(final BigDecimal montoConcedido) {
		this.montoConcedido = montoConcedido;
	}

	public Long getDiasMora() {
		return diasMora;
	}

	public void setDiasMora(final Long diasMora) {
		this.diasMora = diasMora;
	}

	public BigDecimal getCuota() {
		return cuota;
	}

	public void setCuota(final BigDecimal cuota) {
		this.cuota = cuota;
	}

	public String getDescProducto() {
		return descProducto;
	}

	public void setDescProducto(final String descProducto) {
		this.descProducto = descProducto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(final String estado) {
		this.estado = estado;
	}

	public BigDecimal getPlazo() {
		return plazo;
	}

	public void setPlazo(final BigDecimal plazo) {
		this.plazo = plazo;
	}

	public String getFechaConcesion() {
		return fechaConcesion;
	}

	public void setFechaConcesion(final String fechaConcesion) {
		this.fechaConcesion = fechaConcesion;
	}

	public BigDecimal getTotalCuotas() {
		return totalCuotas;
	}

	public void setTotalCuotas(final BigDecimal totalCuotas) {
		this.totalCuotas = totalCuotas;
	}

	public BigDecimal getCuotasFaltantes() {
		return cuotasFaltantes;
	}

	public void setCuotasFaltantes(final BigDecimal cuotasFaltantes) {
		this.cuotasFaltantes = cuotasFaltantes;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(final String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public BigDecimal getMontoMora() {
		return montoMora;
	}

	public void setMontoMora(final BigDecimal montoMora) {
		this.montoMora = montoMora;
	}

	public BigDecimal getMontoPrecancelar() {
		return montoPrecancelar;
	}

	public void setMontoPrecancelar(final BigDecimal montoPrecancelar) {
		this.montoPrecancelar = montoPrecancelar;
	}

	public String getNumeroTicket() {
		return numeroTicket;
	}

	public void setNumeroTicket(final String numeroTicket) {
		this.numeroTicket = numeroTicket;
	}

	public List<DatoDeudaDto> getDatosDeuda() {
		return datosDeuda;
	}

	public void setDatosDeuda(final List<DatoDeudaDto> datosDeuda) {
		this.datosDeuda = datosDeuda;
	}

	public String getFechaPagoLiquid() {
		return fechaPagoLiquid;
	}

	public void setFechaPagoLiquid(final String fechaPagoLiquid) {
		this.fechaPagoLiquid = fechaPagoLiquid;
	}

	public BigDecimal getValorSeguroSaldos() {
		return valorSeguroSaldos;
	}

	public void setValorSeguroSaldos(final BigDecimal valorSeguroSaldos) {
		this.valorSeguroSaldos = valorSeguroSaldos;
	}

	public BigDecimal getMontoCapital() {
		return montoCapital;
	}

	public void setMontoCapital(final BigDecimal montoCapital) {
		this.montoCapital = montoCapital;
	}

	public List<DatoReferencia> getDatosTotalesDeuda() {
		return datosTotalesDeuda;
	}

	public void setDatosTotalesDeuda(List<DatoReferencia> datosTotalesDeuda) {
		this.datosTotalesDeuda = datosTotalesDeuda;
	}

	public BigDecimal getNumeroDividendo() {
		return numeroDividendo;
	}

	public void setNumeroDividendo(final BigDecimal numeroDividendo) {
		this.numeroDividendo = numeroDividendo;
	}

	public BigDecimal getInteresGracia() {
		return interesGracia;
	}

	public void setInteresGracia(final BigDecimal interesGracia) {
		this.interesGracia = interesGracia;
	}

	public BigDecimal getInteresVencimientoAnticipado() {
		return interesVencimientoAnticipado;
	}

	public void setInteresVencimientoAnticipado(BigDecimal interesVencimientoAnticipado) {
		this.interesVencimientoAnticipado = interesVencimientoAnticipado;
	}

	public BigDecimal getMontoInteresVencimiento() {
		return montoInteresVencimiento;
	}

	public void setMontoInteresVencimiento(BigDecimal montoInteresVencimiento) {
		this.montoInteresVencimiento = montoInteresVencimiento;
	}

	public BigDecimal getSaldoOperacionEmp() {
		return saldoOperacionEmp;
	}

	public void setSaldoOperacionEmp(BigDecimal saldoOperacionEmp) {
		this.saldoOperacionEmp = saldoOperacionEmp;
	}

	@Override
	public int compareTo(CreditoExigibleDto o) {
		Date fechaA = null;
		Date fechaB = null;
		try {
			fechaA = FuncionesUtilesSac.obtenerFechaSac(o.getFechaConcesion());

			fechaB = FuncionesUtilesSac.obtenerFechaSac(this.getFechaConcesion());
		} catch (ParseException e) {
			LOG.error("Error al parsear fecha en comparacion ordenar", e);
		}
		return fechaA.compareTo(fechaB);
	}
	public Double getTasaSegSaldos() {
		return tasaSegSaldos;
	}

	public void setTasaSegSaldos(Double tasaSegSaldos) {
		this.tasaSegSaldos = tasaSegSaldos;
	}
}
