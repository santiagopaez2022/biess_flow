/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author jvaca
 * 
 */
@Entity
@Table(name = "APORTES_PFR2")
@SequenceGenerator(name = "AportePfr2Sequence", sequenceName = "APORTES_PFR2_SEQ", allocationSize = 1)
@NamedQueries( {
		@NamedQuery(name = "AportesPfr2.obtenerAportesComprometidosPq", query = "select o from AportesPfr2 o where o.cedula = :cedula and valorComprometidoGarPqCap <> 0")
		
})
@NamedNativeQueries(
		@NamedNativeQuery(name = "AportesPfr2.obtenerAportesComprometidosPqN", query = "select"+
				"  A.CODIGOAPORTE,"+
				" A.CEDULA,"+
				" A.NOMBRE,"+
				" A.RUCPATRONAL,"+
				" A.NOMBREEMPRESA,"+
				" A.SUCURSALSECCION,"+
				" A.SECTOR,"+
				" A.NUMEROBOLETIN,"+
				" A.SECUENCIALPLANILLA,"+
				" A.FECHACOMPROBANTE,"+
				" A.PERIODODESDE,"+
				" A.PERIODOHASTA,"+
				" A.PERIODOANIO,"+
				" A.PERIODOMES,"+
				" A.TIPOPERIODO,"+
				" A.TIPOPLANILLA,"+
				" A.TIPOREGISTRO,"+
				" A.ESTADOAPORTE,"+
				" A.VALORCAPITAL,"+
				" A.FECHADEPAGO,"+
				" A.TIPODOCUMENTOORIGENDELPAGO,"+
				" A.NUMERODEDOCUMENTOORIGENPAGO,"+
				" A.VALORCAPITALPAGADO,"+
				" A.VALORINTERESPAGADO,"+
				" A.VALORMONTOPAGADO,"+
				" A.VALORGASTOPAGADO,"+
				" A.VALORLIQUIDOPAGADO,"+
				" A.VALORCOMPROMETIDOFRCAPITAL,"+
				" A.VALORCOMPROMETIDOFRINTERES,"+
				" A.VALORCOMPROMETIDOFRMONTO,"+
				" A.VALORCOMPROMETIDOFRGASTOADM,"+
				" A.VALORCOMPROMETIDOGARPQCAP,"+
				" A.VALORSALDOCAPITAL,"+
				" A.VALORCAPITALDISPONIBLE,"+
				" A.VALORESEXCESOS,"+
				" A.DETALLESEXCESOS,"+
				" A.BLOQUEOS,"+
				" A.DESCRIPCIONBLOQUEO,"+
				" A.INCONSISTENCIAS,"+
				" A.ESTADOBLOQUEO,"+
				" A.NUMEROIMPOSICIONESAPORTE,"+
				" A.CUMPLEIMPOSICIONES,"+
				" A.SECUENCIAL_PRORRATEO,"+
				" A.MARCADISPONIBLE,"+
				" A.FECHAORDEN,"+
				" A.PAGADOAPORTE,"+
				" A.COBRADOAPORTE,"+
				" A.CESANTEAPORTE,"+
				" A.CONDICIONAFILIADOALPAGO,"+
				" A.FECHAINICIORENDIMIENTOS,"+
				" A.FECHA_COMPROBANTE_INVALIDA,"+
				" A.FECHAPROCESOAPORTES,"+
				" A.DETALLEPROCESOAPORTE,"+
				" A.FECHARETIRO,"+
				" A.PROCESADOAPORTES,"+
				" A.FECHACARGA "+
				" FROM APORTES_PFR2 A,"+
				" FRSAFITCRURESCTAIND CI"+
				" WHERE  A.CEDULA = CI.NUMAFI "+
				" AND  A.CODIGOAPORTE = CI.CODSEC"+
				" AND NVL( A.VALORCOMPROMETIDOGARPQCAP,0) <> 0"+
				" AND  A.CEDULA =:CEDULA"+
				" AND CI.NUMPREAFI =:NUMPREAFI"+
				" AND CI.CODPRETIP =:CODPRETIP"+
				" AND CI.ORDPREAFI =:ORDPREAFI"+
				" AND CI.CODPRECLA =:CODPRECLA",
				resultClass=AportesPfr2.class)
)


public class AportesPfr2 implements Serializable {

	private static final long serialVersionUID = 1453446836393807289L;
	@Id
	@GeneratedValue(generator = "AportePfr2Sequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "CODIGOAPORTE", nullable = false)
	private BigInteger codigoAporte;

	@Column(name = "CEDULA", nullable = false)
	private String cedula;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "RUCPATRONAL", nullable = true)
	private String rucPatronal;

	@Column(name = "NOMBREEMPRESA", nullable = true)
	private String nombreEmpresa;

	@Column(name = "SUCURSALSECCION", nullable = true)
	private String sucursalSeccion;

	@Column(name = "SECTOR", nullable = true)
	private String sector;

	@Column(name = "NUMEROBOLETIN", nullable = true)
	private String numeroBoletin;

	@Column(name = "SECUENCIALPLANILLA", nullable = true)
	private BigInteger secuencialPlanilla;

	@Column(name = "FECHACOMPROBANTE", nullable = true)
	private String fechaComprobante;

	@Column(name = "PERIODODESDE", nullable = true)
	private String periodoDesde;

	@Column(name = "PERIODOHASTA", nullable = true)
	private String periodoHasta;

	@Column(name = "PERIODOANIO", nullable = true)
	private Integer periodoAnio;

	@Column(name = "PERIODOMES", nullable = true)
	private Integer periodoMes;

	@Column(name = "TIPOPERIODO", nullable = true)
	private String tipoPSeriodo;

	@Column(name = "TIPOPLANILLA", nullable = true)
	private String tipoPlanilla;

	@Column(name = "TIPOREGISTRO", nullable = true)
	private String tipoRegistro;

	@Column(name = "ESTADOAPORTE", nullable = true)
	private String estadoAporte;
	
	@Column(name = "VALORCAPITAL", nullable = true)
	private BigDecimal valorCapital;

	@Column(name = "FECHADEPAGO", nullable = true)
	private String fechaDePago;

	@Column(name = "TIPODOCUMENTOORIGENDELPAGO", nullable = true)
	private String tipoDocumentoOrigenDelPago;

	@Column(name = "NUMERODEDOCUMENTOORIGENPAGO", nullable = true)
	private String numeroDeDocumentoOrigenPago;

	@Column(name = "VALORCAPITALPAGADO", nullable = true)
	private BigDecimal valorCapitalPagado;

	@Column(name = "VALORINTERESPAGADO", nullable = true)
	private BigDecimal valorInteresPagado;

	@Column(name = "VALORMONTOPAGADO", nullable = true)
	private BigDecimal valorMontopagado;

	@Column(name = "VALORGASTOPAGADO", nullable = true)
	private BigDecimal valorGastoPagado;

	@Column(name = "VALORLIQUIDOPAGADO", nullable = true)
	private BigDecimal valorLiquidoPagado;

	@Column(name = "VALORCOMPROMETIDOFRCAPITAL", nullable = true)
	private BigDecimal valorComprometidoFrCapital;

	@Column(name = "VALORCOMPROMETIDOFRINTERES", nullable = true)
	private BigDecimal valorComprometidoFrInteres;

	@Column(name = "VALORCOMPROMETIDOFRMONTO", nullable = true)
	private BigDecimal valorComprometidoFrMonto;

	@Column(name = "VALORCOMPROMETIDOFRGASTOADM", nullable = true)
	private BigDecimal valorComprometidoFrGastoAdm;

	@Column(name = "VALORCOMPROMETIDOGARPQCAP", nullable = true)
	private BigDecimal valorComprometidoGarPqCap;

	@Column(name = "VALORSALDOCAPITAL", nullable = true)
	private BigDecimal valorSaldoCapital;

	@Column(name = "VALORCAPITALDISPONIBLE", nullable = true)
	private BigDecimal valorCapitalDisponible;

	@Column(name = "VALORESEXCESOS", nullable = true)
	private BigDecimal valoresExcesos;

	@Column(name = "DETALLESEXCESOS", nullable = true)
	private String detallesExcesos;

	@Column(name = "BLOQUEOS", nullable = true)
	private String bloqueos;

	@Column(name = "DESCRIPCIONBLOQUEO", nullable = true)
	private String descripcionBloqueo;

	@Column(name = "INCONSISTENCIAS", nullable = true)
	private String inconsistencias;

	@Column(name = "ESTADOBLOQUEO", nullable = true)
	private String estadoBloqueo;

	@Column(name = "NUMEROIMPOSICIONESAPORTE", nullable = true)
	private String numeroImposicionesAporte;

	@Column(name = "CUMPLEIMPOSICIONES", nullable = true)
	private String cumpleImposiciones;

	@Column(name = "SECUENCIAL_PRORRATEO", nullable = true)
	private String secuencialProrrateo;

	@Column(name = "MARCADISPONIBLE", nullable = true)
	private String marcaDisponible;

	@Column(name = "FECHAORDEN", nullable = true)
	private String fechaOrden;

	@Column(name = "PAGADOAPORTE", nullable = true)
	private String pagadoAporte;

	@Column(name = "COBRADOAPORTE", nullable = true)
	private String cobradoAporte;

	@Column(name = "CESANTEAPORTE", nullable = true)
	private String cesanteAporte;

	@Column(name = "CONDICIONAFILIADOALPAGO", nullable = true)
	private String condicionAfiliadoAlPago;

	@Column(name = "FECHAINICIORENDIMIENTOS", nullable = true)
	private String fechaInicioRendimientos;

	@Column(name = "FECHA_COMPROBANTE_INVALIDA", nullable = true)
	private String fechaComprobanteInvalida;

	@Column(name = "FECHAPROCESOAPORTES", nullable = true)
	private Timestamp fechaProcesoAportes;

	@Column(name = "DETALLEPROCESOAPORTE", nullable = true)
	private String detalleProcesoAporte;

	@Column(name = "FECHARETIRO", nullable = true)
	private Timestamp fechaRetiro;

	@Column(name = "PROCESADOAPORTES", nullable = true)
	private String procesadoAportes;

	@Column(name = "FECHACARGA", nullable = true)
	private Timestamp fechaCarga;
	
	
	
	@Transient
	private String codigoErrorProceso;
	
	@Transient
	private String mensajeErrorProceso;

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the codigoAporte
	 */
	public BigInteger getCodigoAporte() {
		return codigoAporte;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the rucPatronal
	 */
	public String getRucPatronal() {
		return rucPatronal;
	}

	/**
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @return the sucursalSeccion
	 */
	public String getSucursalSeccion() {
		return sucursalSeccion;
	}

	/**
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * @return the numeroBoletin
	 */
	public String getNumeroBoletin() {
		return numeroBoletin;
	}

	/**
	 * @return the secuencialPlanilla
	 */
	public BigInteger getSecuencialPlanilla() {
		return secuencialPlanilla;
	}

	/**
	 * @return the fechaComprobante
	 */
	public String getFechaComprobante() {
		return fechaComprobante;
	}

	/**
	 * @return the periodoDesde
	 */
	public String getPeriodoDesde() {
		return periodoDesde;
	}

	/**
	 * @return the periodoHasta
	 */
	public String getPeriodoHasta() {
		return periodoHasta;
	}

	/**
	 * @return the periodoAnio
	 */
	public Integer getPeriodoAnio() {
		return periodoAnio;
	}

	/**
	 * @return the periodoMes
	 */
	public Integer getPeriodoMes() {
		return periodoMes;
	}

	/**
	 * @return the tipoPSeriodo
	 */
	public String getTipoPSeriodo() {
		return tipoPSeriodo;
	}

	/**
	 * @return the tipoPlanilla
	 */
	public String getTipoPlanilla() {
		return tipoPlanilla;
	}

	/**
	 * @return the tipoRegistro
	 */
	public String getTipoRegistro() {
		return tipoRegistro;
	}

	/**
	 * @return the estadoAporte
	 */
	public String getEstadoAporte() {
		return estadoAporte;
	}

	/**
	 * @return the valorCapital
	 */
	public BigDecimal getValorCapital() {
		return valorCapital;
	}

	/**
	 * @return the fechaDePago
	 */
	public String getFechaDePago() {
		return fechaDePago;
	}

	/**
	 * @return the tipoDocumentoOrigenDelPago
	 */
	public String getTipoDocumentoOrigenDelPago() {
		return tipoDocumentoOrigenDelPago;
	}

	/**
	 * @return the numeroDeDocumentoOrigenPago
	 */
	public String getNumeroDeDocumentoOrigenPago() {
		return numeroDeDocumentoOrigenPago;
	}

	/**
	 * @return the valorCapitalPagado
	 */
	public BigDecimal getValorCapitalPagado() {
		return valorCapitalPagado;
	}

	/**
	 * @return the valorInteresPagado
	 */
	public BigDecimal getValorInteresPagado() {
		return valorInteresPagado;
	}

	/**
	 * @return the valorMontopagado
	 */
	public BigDecimal getValorMontopagado() {
		return valorMontopagado;
	}

	/**
	 * @return the valorGastoPagado
	 */
	public BigDecimal getValorGastoPagado() {
		return valorGastoPagado;
	}

	/**
	 * @return the valorLiquidoPagado
	 */
	public BigDecimal getValorLiquidoPagado() {
		return valorLiquidoPagado;
	}

	/**
	 * @return the valorComprometidoFrCapital
	 */
	public BigDecimal getValorComprometidoFrCapital() {
		return valorComprometidoFrCapital;
	}

	/**
	 * @return the valorComprometidoFrInteres
	 */
	public BigDecimal getValorComprometidoFrInteres() {
		return valorComprometidoFrInteres;
	}

	/**
	 * @return the valorComprometidoFrMonto
	 */
	public BigDecimal getValorComprometidoFrMonto() {
		return valorComprometidoFrMonto;
	}

	/**
	 * @return the valorComprometidoFrGastoAdm
	 */
	public BigDecimal getValorComprometidoFrGastoAdm() {
		return valorComprometidoFrGastoAdm;
	}

	/**
	 * @return the valorComprometidoGarPqCap
	 */
	public BigDecimal getValorComprometidoGarPqCap() {
		return valorComprometidoGarPqCap;
	}

	/**
	 * @return the valorSaldoCapital
	 */
	public BigDecimal getValorSaldoCapital() {
		return valorSaldoCapital;
	}

	/**
	 * @return the valorCapitalDisponible
	 */
	public BigDecimal getValorCapitalDisponible() {
		return valorCapitalDisponible;
	}

	/**
	 * @return the valoresExcesos
	 */
	public BigDecimal getValoresExcesos() {
		return valoresExcesos;
	}

	/**
	 * @return the detallesExcesos
	 */
	public String getDetallesExcesos() {
		return detallesExcesos;
	}

	/**
	 * @return the bloqueos
	 */
	public String getBloqueos() {
		return bloqueos;
	}

	/**
	 * @return the descripcionBloqueo
	 */
	public String getDescripcionBloqueo() {
		return descripcionBloqueo;
	}

	/**
	 * @return the inconsistencias
	 */
	public String getInconsistencias() {
		return inconsistencias;
	}

	/**
	 * @return the estadoBloqueo
	 */
	public String getEstadoBloqueo() {
		return estadoBloqueo;
	}

	/**
	 * @return the numeroImposicionesAporte
	 */
	public String getNumeroImposicionesAporte() {
		return numeroImposicionesAporte;
	}

	/**
	 * @return the cumpleImposiciones
	 */
	public String getCumpleImposiciones() {
		return cumpleImposiciones;
	}

	/**
	 * @return the secuencialProrrateo
	 */
	public String getSecuencialProrrateo() {
		return secuencialProrrateo;
	}

	/**
	 * @return the marcaDisponible
	 */
	public String getMarcaDisponible() {
		return marcaDisponible;
	}

	/**
	 * @return the fechaOrden
	 */
	public String getFechaOrden() {
		return fechaOrden;
	}

	/**
	 * @return the pagadoAporte
	 */
	public String getPagadoAporte() {
		return pagadoAporte;
	}

	/**
	 * @return the cobradoAporte
	 */
	public String getCobradoAporte() {
		return cobradoAporte;
	}

	/**
	 * @return the cesanteAporte
	 */
	public String getCesanteAporte() {
		return cesanteAporte;
	}

	/**
	 * @return the condicionAfiliadoAlPago
	 */
	public String getCondicionAfiliadoAlPago() {
		return condicionAfiliadoAlPago;
	}

	/**
	 * @return the fechaInicioRendimientos
	 */
	public String getFechaInicioRendimientos() {
		return fechaInicioRendimientos;
	}

	/**
	 * @return the fechaComprobanteInvalida
	 */
	public String getFechaComprobanteInvalida() {
		return fechaComprobanteInvalida;
	}

	/**
	 * @return the fechaProcesoAportes
	 */
	public Timestamp getFechaProcesoAportes() {
		return fechaProcesoAportes;
	}

	/**
	 * @return the detalleProcesoAporte
	 */
	public String getDetalleProcesoAporte() {
		return detalleProcesoAporte;
	}

	/**
	 * @return the fechaRetiro
	 */
	public Timestamp getFechaRetiro() {
		return fechaRetiro;
	}

	/**
	 * @return the procesadoAportes
	 */
	public String getProcesadoAportes() {
		return procesadoAportes;
	}

	/**
	 * @return the fechaCarga
	 */
	public Timestamp getFechaCarga() {
		return fechaCarga;
	}

	/**
	 * @param codigoAporte
	 *            the codigoAporte to set
	 */
	public void setCodigoAporte(BigInteger codigoAporte) {
		this.codigoAporte = codigoAporte;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @param rucPatronal
	 *            the rucPatronal to set
	 */
	public void setRucPatronal(String rucPatronal) {
		this.rucPatronal = rucPatronal;
	}

	/**
	 * @param nombreEmpresa
	 *            the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * @param sucursalSeccion
	 *            the sucursalSeccion to set
	 */
	public void setSucursalSeccion(String sucursalSeccion) {
		this.sucursalSeccion = sucursalSeccion;
	}

	/**
	 * @param sector
	 *            the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * @param numeroBoletin
	 *            the numeroBoletin to set
	 */
	public void setNumeroBoletin(String numeroBoletin) {
		this.numeroBoletin = numeroBoletin;
	}

	/**
	 * @param secuencialPlanilla
	 *            the secuencialPlanilla to set
	 */
	public void setSecuencialPlanilla(BigInteger secuencialPlanilla) {
		this.secuencialPlanilla = secuencialPlanilla;
	}

	/**
	 * @param fechaComprobante
	 *            the fechaComprobante to set
	 */
	public void setFechaComprobante(String fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}

	/**
	 * @param periodoDesde
	 *            the periodoDesde to set
	 */
	public void setPeriodoDesde(String periodoDesde) {
		this.periodoDesde = periodoDesde;
	}

	/**
	 * @param periodoHasta
	 *            the periodoHasta to set
	 */
	public void setPeriodoHasta(String periodoHasta) {
		this.periodoHasta = periodoHasta;
	}

	/**
	 * @param periodoAnio
	 *            the periodoAnio to set
	 */
	public void setPeriodoAnio(Integer periodoAnio) {
		this.periodoAnio = periodoAnio;
	}

	/**
	 * @param periodoMes
	 *            the periodoMes to set
	 */
	public void setPeriodoMes(Integer periodoMes) {
		this.periodoMes = periodoMes;
	}

	/**
	 * @param tipoPSeriodo
	 *            the tipoPSeriodo to set
	 */
	public void setTipoPSeriodo(String tipoPSeriodo) {
		this.tipoPSeriodo = tipoPSeriodo;
	}

	/**
	 * @param tipoPlanilla
	 *            the tipoPlanilla to set
	 */
	public void setTipoPlanilla(String tipoPlanilla) {
		this.tipoPlanilla = tipoPlanilla;
	}

	/**
	 * @param tipoRegistro
	 *            the tipoRegistro to set
	 */
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	/**
	 * @param estadoAporte
	 *            the estadoAporte to set
	 */
	public void setEstadoAporte(String estadoAporte) {
		this.estadoAporte = estadoAporte;
	}

	/**
	 * @param valorCapital
	 *            the valorCapital to set
	 */
	public void setValorCapital(BigDecimal valorCapital) {
		this.valorCapital = valorCapital;
	}

	/**
	 * @param fechaDePago
	 *            the fechaDePago to set
	 */
	public void setFechaDePago(String fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	/**
	 * @param tipoDocumentoOrigenDelPago
	 *            the tipoDocumentoOrigenDelPago to set
	 */
	public void setTipoDocumentoOrigenDelPago(String tipoDocumentoOrigenDelPago) {
		this.tipoDocumentoOrigenDelPago = tipoDocumentoOrigenDelPago;
	}

	/**
	 * @param numeroDeDocumentoOrigenPago
	 *            the numeroDeDocumentoOrigenPago to set
	 */
	public void setNumeroDeDocumentoOrigenPago(
			String numeroDeDocumentoOrigenPago) {
		this.numeroDeDocumentoOrigenPago = numeroDeDocumentoOrigenPago;
	}

	/**
	 * @param valorCapitalPagado
	 *            the valorCapitalPagado to set
	 */
	public void setValorCapitalPagado(BigDecimal valorCapitalPagado) {
		this.valorCapitalPagado = valorCapitalPagado;
	}

	/**
	 * @param valorInteresPagado
	 *            the valorInteresPagado to set
	 */
	public void setValorInteresPagado(BigDecimal valorInteresPagado) {
		this.valorInteresPagado = valorInteresPagado;
	}

	/**
	 * @param valorMontopagado
	 *            the valorMontopagado to set
	 */
	public void setValorMontopagado(BigDecimal valorMontopagado) {
		this.valorMontopagado = valorMontopagado;
	}

	/**
	 * @param valorGastoPagado
	 *            the valorGastoPagado to set
	 */
	public void setValorGastoPagado(BigDecimal valorGastoPagado) {
		this.valorGastoPagado = valorGastoPagado;
	}

	/**
	 * @param valorLiquidoPagado
	 *            the valorLiquidoPagado to set
	 */
	public void setValorLiquidoPagado(BigDecimal valorLiquidoPagado) {
		this.valorLiquidoPagado = valorLiquidoPagado;
	}

	/**
	 * @param valorComprometidoFrCapital
	 *            the valorComprometidoFrCapital to set
	 */
	public void setValorComprometidoFrCapital(
			BigDecimal valorComprometidoFrCapital) {
		this.valorComprometidoFrCapital = valorComprometidoFrCapital;
	}

	/**
	 * @param valorComprometidoFrInteres
	 *            the valorComprometidoFrInteres to set
	 */
	public void setValorComprometidoFrInteres(
			BigDecimal valorComprometidoFrInteres) {
		this.valorComprometidoFrInteres = valorComprometidoFrInteres;
	}

	/**
	 * @param valorComprometidoFrMonto
	 *            the valorComprometidoFrMonto to set
	 */
	public void setValorComprometidoFrMonto(BigDecimal valorComprometidoFrMonto) {
		this.valorComprometidoFrMonto = valorComprometidoFrMonto;
	}

	/**
	 * @param valorComprometidoFrGastoAdm
	 *            the valorComprometidoFrGastoAdm to set
	 */
	public void setValorComprometidoFrGastoAdm(
			BigDecimal valorComprometidoFrGastoAdm) {
		this.valorComprometidoFrGastoAdm = valorComprometidoFrGastoAdm;
	}

	/**
	 * @param valorComprometidoGarPqCap
	 *            the valorComprometidoGarPqCap to set
	 */
	public void setValorComprometidoGarPqCap(
			BigDecimal valorComprometidoGarPqCap) {
		this.valorComprometidoGarPqCap = valorComprometidoGarPqCap;
	}

	/**
	 * @param valorSaldoCapital
	 *            the valorSaldoCapital to set
	 */
	public void setValorSaldoCapital(BigDecimal valorSaldoCapital) {
		this.valorSaldoCapital = valorSaldoCapital;
	}

	/**
	 * @param valorCapitalDisponible
	 *            the valorCapitalDisponible to set
	 */
	public void setValorCapitalDisponible(BigDecimal valorCapitalDisponible) {
		this.valorCapitalDisponible = valorCapitalDisponible;
	}

	/**
	 * @param valoresExcesos
	 *            the valoresExcesos to set
	 */
	public void setValoresExcesos(BigDecimal valoresExcesos) {
		this.valoresExcesos = valoresExcesos;
	}

	/**
	 * @param detallesExcesos
	 *            the detallesExcesos to set
	 */
	public void setDetallesExcesos(String detallesExcesos) {
		this.detallesExcesos = detallesExcesos;
	}

	/**
	 * @param bloqueos
	 *            the bloqueos to set
	 */
	public void setBloqueos(String bloqueos) {
		this.bloqueos = bloqueos;
	}

	/**
	 * @param descripcionBloqueo
	 *            the descripcionBloqueo to set
	 */
	public void setDescripcionBloqueo(String descripcionBloqueo) {
		this.descripcionBloqueo = descripcionBloqueo;
	}

	/**
	 * @param inconsistencias
	 *            the inconsistencias to set
	 */
	public void setInconsistencias(String inconsistencias) {
		this.inconsistencias = inconsistencias;
	}

	/**
	 * @param estadoBloqueo
	 *            the estadoBloqueo to set
	 */
	public void setEstadoBloqueo(String estadoBloqueo) {
		this.estadoBloqueo = estadoBloqueo;
	}

	/**
	 * @param numeroImposicionesAporte
	 *            the numeroImposicionesAporte to set
	 */
	public void setNumeroImposicionesAporte(String numeroImposicionesAporte) {
		this.numeroImposicionesAporte = numeroImposicionesAporte;
	}

	/**
	 * @param cumpleImposiciones
	 *            the cumpleImposiciones to set
	 */
	public void setCumpleImposiciones(String cumpleImposiciones) {
		this.cumpleImposiciones = cumpleImposiciones;
	}

	/**
	 * @param secuencialProrrateo
	 *            the secuencialProrrateo to set
	 */
	public void setSecuencialProrrateo(String secuencialProrrateo) {
		this.secuencialProrrateo = secuencialProrrateo;
	}

	/**
	 * @param marcaDisponible
	 *            the marcaDisponible to set
	 */
	public void setMarcaDisponible(String marcaDisponible) {
		this.marcaDisponible = marcaDisponible;
	}

	/**
	 * @param fechaOrden
	 *            the fechaOrden to set
	 */
	public void setFechaOrden(String fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	/**
	 * @param pagadoAporte
	 *            the pagadoAporte to set
	 */
	public void setPagadoAporte(String pagadoAporte) {
		this.pagadoAporte = pagadoAporte;
	}

	/**
	 * @param cobradoAporte
	 *            the cobradoAporte to set
	 */
	public void setCobradoAporte(String cobradoAporte) {
		this.cobradoAporte = cobradoAporte;
	}

	/**
	 * @param cesanteAporte
	 *            the cesanteAporte to set
	 */
	public void setCesanteAporte(String cesanteAporte) {
		this.cesanteAporte = cesanteAporte;
	}

	/**
	 * @param condicionAfiliadoAlPago
	 *            the condicionAfiliadoAlPago to set
	 */
	public void setCondicionAfiliadoAlPago(String condicionAfiliadoAlPago) {
		this.condicionAfiliadoAlPago = condicionAfiliadoAlPago;
	}

	/**
	 * @param fechaInicioRendimientos
	 *            the fechaInicioRendimientos to set
	 */
	public void setFechaInicioRendimientos(String fechaInicioRendimientos) {
		this.fechaInicioRendimientos = fechaInicioRendimientos;
	}

	/**
	 * @param fechaComprobanteInvalida
	 *            the fechaComprobanteInvalida to set
	 */
	public void setFechaComprobanteInvalida(String fechaComprobanteInvalida) {
		this.fechaComprobanteInvalida = fechaComprobanteInvalida;
	}

	/**
	 * @param fechaProcesoAportes
	 *            the fechaProcesoAportes to set
	 */
	public void setFechaProcesoAportes(Timestamp fechaProcesoAportes) {
		this.fechaProcesoAportes = fechaProcesoAportes;
	}

	/**
	 * @param detalleProcesoAporte
	 *            the detalleProcesoAporte to set
	 */
	public void setDetalleProcesoAporte(String detalleProcesoAporte) {
		this.detalleProcesoAporte = detalleProcesoAporte;
	}

	/**
	 * @param fechaRetiro
	 *            the fechaRetiro to set
	 */
	public void setFechaRetiro(Timestamp fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	/**
	 * @param procesadoAportes
	 *            the procesadoAportes to set
	 */
	public void setProcesadoAportes(String procesadoAportes) {
		this.procesadoAportes = procesadoAportes;
	}

	/**
	 * @param fechaCarga
	 *            the fechaCarga to set
	 */
	public void setFechaCarga(Timestamp fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	/**
	 * @return the codigoErrorProceso
	 */
	public String getCodigoErrorProceso() {
		return codigoErrorProceso;
	}

	/**
	 * @param codigoErrorProceso the codigoErrorProceso to set
	 */
	public void setCodigoErrorProceso(String codigoErrorProceso) {
		this.codigoErrorProceso = codigoErrorProceso;
	}

	/**
	 * @return the mensajeErrorProceso
	 */
	public String getMensajeErrorProceso() {
		return mensajeErrorProceso;
	}

	/**
	 * @param mensajeErrorProceso the mensajeErrorProceso to set
	 */
	public void setMensajeErrorProceso(String mensajeErrorProceso) {
		this.mensajeErrorProceso = mensajeErrorProceso;
	}
}
