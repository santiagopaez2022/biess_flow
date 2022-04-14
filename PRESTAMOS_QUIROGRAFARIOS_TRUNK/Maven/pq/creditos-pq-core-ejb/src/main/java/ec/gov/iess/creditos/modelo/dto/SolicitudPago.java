package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SolicitudPago implements Serializable {

	private static final long serialVersionUID = 6404129281775424311L;

	private String cedula;
	private String solicitante;
	private String tipoSolicitud;
	private String provincia;
	private Date fechaEscritura;
	private BigDecimal monto;
	private Long plazo;
	private BigDecimal gastos;
	private BigDecimal dividendo;
	private Long nut;
	private String codigoAgenciaEmisora;
	private String vendedorDocumento;
	private Long vendedorNumeroCuenta;
	private String vendedorTipoCuenta;
	private String vendedorNombre;
	private String vendedorCodigoBancoCuenta;
	private String tipoDesembolso;
	private boolean seleccionado;
	//KSRECTINTBANCENDET
	private String fechaTransferencia;
	private BigDecimal valorReversadoVend;
	private Long codigoReverso;

	// Cambios SPI BIESS
	private BigDecimal valorFinanciado;
	private BigDecimal valorSeguroVida;
	private BigDecimal valorSeguroRiesgos;
	private BigDecimal valorAprobado;
	private String observacion;
	private String numeroCuenta;
	private String nombreFondo;

	// Cambios Unificacion
	private BigDecimal saldoCapital;
	private BigDecimal valorUnificado;
	private Date fechaUnificacion;
	private BigDecimal sumIvm;
	private BigDecimal sumCes;
	private BigDecimal sumSegSal;

	// Vivienda hipotecada
	// DI_VALDESEMIFI
	private BigDecimal desembolsoIfi;
	private String fondoConcesion;
	private String institucionFinanciera;
	//DI_RUCIFI
	private String rucIfiAcreedora;
	private String nombreIfiAcreedora;
	// DI_NUMCTAIFI
	private Long numeroCuentaIfiAcreedora;
	//DI_TIPCTAIFI
	private String tipoCuentaIfiAcreedora;
	private String tipoGarantiaIfi;
	private Long numeroOperaciones;
	
	// OC_VALOROPERACION
	private BigDecimal cancelacionOperacion;
	// OC_NUTOPERACION
	private Long nutOpCancelada;
	// OC_VALORCAPITAL
	private BigDecimal valorCapitalOpCancelada;
	// OC_VALORINTERES
	private BigDecimal valorInteresOpCancelada;
	// OC_VALORSEGINC
	private BigDecimal valorPrimasSegIngOpCancelada;
	// OC_VALORSEGDES
	private BigDecimal valorPrimasSegDesOpCancelada;
	// OC_VALORINTMORA
	private BigDecimal valorInteresMoraOpCancelada;
	// OC_IMPUESTOS
	private BigDecimal valorImpuestosOpCancelada;
	// OC_GASTOS
	private BigDecimal valorSegVidaOpCancelada;

	private String fondoConcesionOpCancelada;
	
	private String tipoSubproducto;

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

	/**
	 * @return the tipoSolicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * @param tipoSolicitud
	 *            the tipoSolicitud to set
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia
	 *            the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the fechaEscritura
	 */
	public Date getFechaEscritura() {
		return fechaEscritura;
	}

	/**
	 * @param fechaEscritura
	 *            the fechaEscritura to set
	 */
	public void setFechaEscritura(Date fechaEscritura) {
		this.fechaEscritura = fechaEscritura;
	}

	/**
	 * @return the monto
	 */
	public BigDecimal getMonto() {
		return monto;
	}

	/**
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	/**
	 * @return the gastos
	 */
	public BigDecimal getGastos() {
		return gastos;
	}

	/**
	 * @param gastos
	 *            the gastos to set
	 */
	public void setGastos(BigDecimal gastos) {
		this.gastos = gastos;
	}

	/**
	 * @return the dividendo
	 */
	public BigDecimal getDividendo() {
		return dividendo;
	}

	/**
	 * @param dividendo
	 *            the dividendo to set
	 */
	public void setDividendo(BigDecimal dividendo) {
		this.dividendo = dividendo;
	}

	/**
	 * @return the nut
	 */
	public Long getNut() {
		return nut;
	}

	/**
	 * @param nut
	 *            the nut to set
	 */
	public void setNut(Long nut) {
		this.nut = nut;
	}

	/**
	 * @return the seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}

	/**
	 * @param seleccionado
	 *            the seleccionado to set
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	/**
	 * @return the plazo
	 */
	public Long getPlazo() {
		return plazo;
	}

	/**
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}

	/**
	 * @return the codigoAgenciaEmisora
	 */
	public String getCodigoAgenciaEmisora() {
		return codigoAgenciaEmisora;
	}

	/**
	 * @param codigoAgenciaEmisora
	 *            the codigoAgenciaEmisora to set
	 */
	public void setCodigoAgenciaEmisora(String codigoAgenciaEmisora) {
		this.codigoAgenciaEmisora = codigoAgenciaEmisora;
	}

	/**
	 * @return the vendedorDocumento
	 */
	public String getVendedorDocumento() {
		return vendedorDocumento;
	}

	/**
	 * @param vendedorDocumento
	 *            the vendedorDocumento to set
	 */
	public void setVendedorDocumento(String vendedorDocumento) {
		this.vendedorDocumento = vendedorDocumento;
	}

	/**
	 * @return the vendedorNumeroCuenta
	 */
	public Long getVendedorNumeroCuenta() {
		return vendedorNumeroCuenta;
	}

	/**
	 * @param vendedorNumeroCuenta
	 *            the vendedorNumeroCuenta to set
	 */
	public void setVendedorNumeroCuenta(Long vendedorNumeroCuenta) {
		this.vendedorNumeroCuenta = vendedorNumeroCuenta;
	}

	/**
	 * @return the vendedorTipoCuenta
	 */
	public String getVendedorTipoCuenta() {
		return vendedorTipoCuenta;
	}

	/**
	 * @param vendedorTipoCuenta
	 *            the vendedorTipoCuenta to set
	 */
	public void setVendedorTipoCuenta(String vendedorTipoCuenta) {
		this.vendedorTipoCuenta = vendedorTipoCuenta;
	}

	/**
	 * @return the vendedorNombre
	 */
	public String getVendedorNombre() {
		return vendedorNombre;
	}

	/**
	 * @param vendedorNombre
	 *            the vendedorNombre to set
	 */
	public void setVendedorNombre(String vendedorNombre) {
		this.vendedorNombre = vendedorNombre;
	}

	/**
	 * @return the vendedorCodigoBancoCuenta
	 */
	public String getVendedorCodigoBancoCuenta() {
		return vendedorCodigoBancoCuenta;
	}

	/**
	 * @param vendedorCodigoBancoCuenta
	 *            the vendedorCodigoBancoCuenta to set
	 */
	public void setVendedorCodigoBancoCuenta(String vendedorCodigoBancoCuenta) {
		this.vendedorCodigoBancoCuenta = vendedorCodigoBancoCuenta;
	}

	/**
	 * @return the valorFinanciado
	 */

	public BigDecimal getValorFinanciado() {
		return valorFinanciado;
	}

	/**
	 * @param valorFinanciado
	 *            the valorFinanciado to set
	 */

	public void setValorFinanciado(BigDecimal valorFinanciado) {
		this.valorFinanciado = valorFinanciado;
	}

	/**
	 * @return the valorSeguroVida
	 */

	public BigDecimal getValorSeguroVida() {
		return valorSeguroVida;
	}

	/**
	 * @param valorSeguroVida
	 *            the valorSeguroVida to set
	 */

	public void setValorSeguroVida(BigDecimal valorSeguroVida) {
		this.valorSeguroVida = valorSeguroVida;
	}

	/**
	 * @return the valorSeguroRiesgos
	 */

	public BigDecimal getValorSeguroRiesgos() {
		return valorSeguroRiesgos;
	}

	/**
	 * @param valorSeguroRiesgos
	 *            the valorSeguroRiesgos to set
	 */

	public void setValorSeguroRiesgos(BigDecimal valorSeguroRiesgos) {
		this.valorSeguroRiesgos = valorSeguroRiesgos;
	}

	/**
	 * @return the valorAprobado
	 */

	public BigDecimal getValorAprobado() {
		return valorAprobado;
	}

	/**
	 * @param valorAprobado
	 *            the valorAprobado to set
	 */

	public void setValorAprobado(BigDecimal valorAprobado) {
		this.valorAprobado = valorAprobado;
	}

	/**
	 * @return the observacion
	 */

	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion
	 *            the observacion to set
	 */

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the numeroCuenta
	 */

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the solicitante
	 */

	public String getSolicitante() {
		return solicitante;
	}

	/**
	 * @param solicitante
	 *            the solicitante to set
	 */

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	/**
	 * @return the nombreFondo
	 */

	public String getNombreFondo() {
		return nombreFondo;
	}

	/**
	 * @param nombreFondo
	 *            the nombreFondo to set
	 */

	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

	/**
	 * @return the saldoCapital
	 */
	public BigDecimal getSaldoCapital() {
		return saldoCapital;
	}

	/**
	 * @param saldoCapital
	 *            the saldoCapital to set
	 */
	public void setSaldoCapital(BigDecimal saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	/**
	 * @return the valorUnificado
	 */
	public BigDecimal getValorUnificado() {
		return valorUnificado;
	}

	/**
	 * @param valorUnificado
	 *            the valorUnificado to set
	 */
	public void setValorUnificado(BigDecimal valorUnificado) {
		this.valorUnificado = valorUnificado;
	}

	/**
	 * @return the fechaUnificacion
	 */
	public Date getFechaUnificacion() {
		return fechaUnificacion;
	}

	/**
	 * @param fechaUnificacion
	 *            the fechaUnificacion to set
	 */
	public void setFechaUnificacion(Date fechaUnificacion) {
		this.fechaUnificacion = fechaUnificacion;
	}

	/**
	 * @return the sumIvm
	 */
	public BigDecimal getSumIvm() {
		return sumIvm;
	}

	/**
	 * @param sumIvm
	 *            the sumIvm to set
	 */
	public void setSumIvm(BigDecimal sumIvm) {
		this.sumIvm = sumIvm;
	}

	/**
	 * @return the sumCes
	 */
	public BigDecimal getSumCes() {
		return sumCes;
	}

	/**
	 * @param sumCes
	 *            the sumCes to set
	 */
	public void setSumCes(BigDecimal sumCes) {
		this.sumCes = sumCes;
	}

	/**
	 * @return the sumSegSal
	 */
	public BigDecimal getSumSegSal() {
		return sumSegSal;
	}

	/**
	 * @param sumSegSal
	 *            the sumSegSal to set
	 */
	public void setSumSegSal(BigDecimal sumSegSal) {
		this.sumSegSal = sumSegSal;
	}

	/**
	 * @return the desembolsoIfi
	 */
	public BigDecimal getDesembolsoIfi() {
		return desembolsoIfi;
	}

	/**
	 * @param desembolsoIfi
	 *            the desembolsoIfi to set
	 */
	public void setDesembolsoIfi(BigDecimal desembolsoIfi) {
		this.desembolsoIfi = desembolsoIfi;
	}

	/**
	 * @return the fondoConcesion
	 */
	public String getFondoConcesion() {
		return fondoConcesion;
	}

	/**
	 * @param fondoConcesion
	 *            the fondoConcesion to set
	 */
	public void setFondoConcesion(String fondoConcesion) {
		this.fondoConcesion = fondoConcesion;
	}

	/**
	 * @return the institucionFinanciera
	 */
	public String getInstitucionFinanciera() {
		return institucionFinanciera;
	}

	/**
	 * @param institucionFinanciera
	 *            the institucionFinanciera to set
	 */
	public void setInstitucionFinanciera(String institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}

	/**
	 * @return the nombreIfiAcreedora
	 */
	public String getNombreIfiAcreedora() {
		return nombreIfiAcreedora;
	}

	/**
	 * @param nombreIfiAcreedora
	 *            the nombreIfiAcreedora to set
	 */
	public void setNombreIfiAcreedora(String nombreIfiAcreedora) {
		this.nombreIfiAcreedora = nombreIfiAcreedora;
	}

	/**
	 * @return the numeroCuentaIfiAcreedora
	 */
	public Long getNumeroCuentaIfiAcreedora() {
		return numeroCuentaIfiAcreedora;
	}

	/**
	 * @param numeroCuentaIfiAcreedora
	 *            the numeroCuentaIfiAcreedora to set
	 */
	public void setNumeroCuentaIfiAcreedora(Long numeroCuentaIfiAcreedora) {
		this.numeroCuentaIfiAcreedora = numeroCuentaIfiAcreedora;
	}

	/**
	 * @return the tipoCuentaIfiAcreedora
	 */
	public String getTipoCuentaIfiAcreedora() {
		return tipoCuentaIfiAcreedora;
	}

	/**
	 * @param tipoCuentaIfiAcreedora
	 *            the tipoCuentaIfiAcreedora to set
	 */
	public void setTipoCuentaIfiAcreedora(String tipoCuentaIfiAcreedora) {
		this.tipoCuentaIfiAcreedora = tipoCuentaIfiAcreedora;
	}

	/**
	 * @return the numeroOperaciones
	 */
	public Long getNumeroOperaciones() {
		return numeroOperaciones;
	}

	/**
	 * @param numeroOperaciones
	 *            the numeroOperaciones to set
	 */
	public void setNumeroOperaciones(Long numeroOperaciones) {
		this.numeroOperaciones = numeroOperaciones;
	}

	/**
	 * @return the cancelacionOperacion
	 */
	public BigDecimal getCancelacionOperacion() {
		return cancelacionOperacion;
	}

	/**
	 * @param cancelacionOperacion
	 *            the cancelacionOperacion to set
	 */
	public void setCancelacionOperacion(BigDecimal cancelacionOperacion) {
		this.cancelacionOperacion = cancelacionOperacion;
	}

	/**
	 * @return the nutOpCancelada
	 */
	public Long getNutOpCancelada() {
		return nutOpCancelada;
	}

	/**
	 * @param nutOpCancelada
	 *            the nutOpCancelada to set
	 */
	public void setNutOpCancelada(Long nutOpCancelada) {
		this.nutOpCancelada = nutOpCancelada;
	}

	/**
	 * @return the valorCapitalOpCancelada
	 */
	public BigDecimal getValorCapitalOpCancelada() {
		return valorCapitalOpCancelada;
	}

	/**
	 * @param valorCapitalOpCancelada
	 *            the valorCapitalOpCancelada to set
	 */
	public void setValorCapitalOpCancelada(BigDecimal valorCapitalOpCancelada) {
		this.valorCapitalOpCancelada = valorCapitalOpCancelada;
	}

	/**
	 * @return the valorInteresOpCancelada
	 */
	public BigDecimal getValorInteresOpCancelada() {
		return valorInteresOpCancelada;
	}

	/**
	 * @param valorInteresOpCancelada
	 *            the valorInteresOpCancelada to set
	 */
	public void setValorInteresOpCancelada(BigDecimal valorInteresOpCancelada) {
		this.valorInteresOpCancelada = valorInteresOpCancelada;
	}

	/**
	 * @return the valorPrimasSegIngOpCancelada
	 */
	public BigDecimal getValorPrimasSegIngOpCancelada() {
		return valorPrimasSegIngOpCancelada;
	}

	/**
	 * @param valorPrimasSegIngOpCancelada
	 *            the valorPrimasSegIngOpCancelada to set
	 */
	public void setValorPrimasSegIngOpCancelada(
			BigDecimal valorPrimasSegIngOpCancelada) {
		this.valorPrimasSegIngOpCancelada = valorPrimasSegIngOpCancelada;
	}

	/**
	 * @return the valorPrimasSegDesOpCancelada
	 */
	public BigDecimal getValorPrimasSegDesOpCancelada() {
		return valorPrimasSegDesOpCancelada;
	}

	/**
	 * @param valorPrimasSegDesOpCancelada
	 *            the valorPrimasSegDesOpCancelada to set
	 */
	public void setValorPrimasSegDesOpCancelada(
			BigDecimal valorPrimasSegDesOpCancelada) {
		this.valorPrimasSegDesOpCancelada = valorPrimasSegDesOpCancelada;
	}

	/**
	 * @return the valorInteresMoraOpCancelada
	 */
	public BigDecimal getValorInteresMoraOpCancelada() {
		return valorInteresMoraOpCancelada;
	}

	/**
	 * @param valorInteresMoraOpCancelada
	 *            the valorInteresMoraOpCancelada to set
	 */
	public void setValorInteresMoraOpCancelada(
			BigDecimal valorInteresMoraOpCancelada) {
		this.valorInteresMoraOpCancelada = valorInteresMoraOpCancelada;
	}

	/**
	 * @return the fondoConcesionOpCancelada
	 */
	public String getFondoConcesionOpCancelada() {
		return fondoConcesionOpCancelada;
	}

	/**
	 * @param fondoConcesionOpCancelada
	 *            the fondoConcesionOpCancelada to set
	 */
	public void setFondoConcesionOpCancelada(String fondoConcesionOpCancelada) {
		this.fondoConcesionOpCancelada = fondoConcesionOpCancelada;
	}

	/**
	 * @param tipoGarantiaIfi the tipoGarantiaIfi to set
	 */
	public void setTipoGarantiaIfi(String tipoGarantiaIfi) {
		this.tipoGarantiaIfi = tipoGarantiaIfi;
	}

	/**
	 * @return the tipoGarantiaIfi
	 */
	public String getTipoGarantiaIfi() {
		return tipoGarantiaIfi;
	}

	/**
	 * @return the rucIfiAcreedora
	 */
	public String getRucIfiAcreedora() {
		return rucIfiAcreedora;
	}

	/**
	 * @param rucIfiAcreedora the rucIfiAcreedora to set
	 */
	public void setRucIfiAcreedora(String rucIfiAcreedora) {
		this.rucIfiAcreedora = rucIfiAcreedora;
	}

	/**
	 * @param tipoDesembolso the tipoDesembolso to set
	 */
	public void setTipoDesembolso(String tipoDesembolso) {
		this.tipoDesembolso = tipoDesembolso;
	}

	/**
	 * @return the tipoDesembolso
	 */
	public String getTipoDesembolso() {
		return tipoDesembolso;
	}

	/**
	 * @param fechaTransferencia the fechaTransferencia to set
	 */
	public void setFechaTransferencia(String fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}

	/**
	 * @return the fechaTransferencia
	 */
	public String getFechaTransferencia() {
		return fechaTransferencia;
	}

	/**
	 * @param valorReversadoVend the valorReversadoVend to set
	 */
	public void setValorReversadoVend(BigDecimal valorReversadoVend) {
		this.valorReversadoVend = valorReversadoVend;
	}

	/**
	 * @return the valorReversadoVend
	 */
	public BigDecimal getValorReversadoVend() {
		return valorReversadoVend;
	}

	/**
	 * @param codigoReverso the codigoReverso to set
	 */
	public void setCodigoReverso(Long codigoReverso) {
		this.codigoReverso = codigoReverso;
	}

	/**
	 * @return the codigoReverso
	 */
	public Long getCodigoReverso() {
		return codigoReverso;
	}
	
	/**
	 * @return the tipoSubporducto
	 */
	public String getTipoSubproducto() {
		return tipoSubproducto;
	}

	/**
	 * @param tipoSubporducto the tipoSubporducto to set
	 */
	public void setTipoSubproducto(String tipoSubporducto) {
		this.tipoSubproducto = tipoSubporducto;
	}

	/**
	 * @return the valorImpuestosOpCancelada
	 */
	public BigDecimal getValorImpuestosOpCancelada() {
		return valorImpuestosOpCancelada;
	}

	/**
	 * @param valorImpuestosOpCancelada the valorImpuestosOpCancelada to set
	 */
	public void setValorImpuestosOpCancelada(BigDecimal valorImpuestosOpCancelada) {
		this.valorImpuestosOpCancelada = valorImpuestosOpCancelada;
	}

	/**
	 * @return the valorGastosOpCancelada
	 */
	public BigDecimal getValorSegVidaOpCancelada() {
		return valorSegVidaOpCancelada;
	}

	/**
	 * @param valorGastosOpCancelada the valorGastosOpCancelada to set
	 */
	public void setValorSegVidaOpCancelada(BigDecimal valorGastosOpCancelada) {
		this.valorSegVidaOpCancelada = valorGastosOpCancelada;
	}


}