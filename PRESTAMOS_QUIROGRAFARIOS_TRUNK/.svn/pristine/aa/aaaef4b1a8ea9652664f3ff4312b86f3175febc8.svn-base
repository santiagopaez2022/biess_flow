/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b> Clase para persistir el histórico de las solicitudes de desembolso. </b>
 * 
 * @author Jenny Sanchez
 * @version $Revision: 1.5 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 15:43:59 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_DESEMBOLSOSHIST_TBL")
@SequenceGenerator(name = "depositoSolicitudHistSeq", sequenceName = "CRE_DESEMBOLSOSHIST_SEQ", initialValue = 1, allocationSize = 1)
@javax.persistence.NamedQueries({
		@NamedQuery(name = "DepositoSolicitudHistorico.obtenerPorSpi", query = "SELECT o FROM DepositoSolicitudHistorico o "
				+ "WHERE o.detalleSolicitudCredito.coddetsol=:codDetalle AND o.idTransaccionDeposito=:spi ORDER BY o.id "),
		@NamedQuery(name = "DepositoSolicitudHistorico.obtenerSpiPorCodDetalle", query = "SELECT o FROM DepositoSolicitudHistorico o "
				+ "WHERE o.detalleSolicitudCredito.coddetsol=:codDetalle AND substr(o.idTransaccionDeposito,1,3)='SPI' ORDER BY o.id "),
		@NamedQuery(name = "DepositoSolicitudHistorico.obtenerHistPorCodDetalle", query = "SELECT o FROM DepositoSolicitudHistorico o "
				+ "WHERE o.detalleSolicitudCredito.coddetsol=:codDetalle ORDER BY o.id ")})
public class DepositoSolicitudHistorico implements Serializable {

	private static final long serialVersionUID = 6087122053876485220L;

	@Id
	@Column(name = "DH_ID", nullable = false, insertable = true)
	@GeneratedValue(generator = "depositoSolicitudHistSeq", strategy = GenerationType.SEQUENCE)
	@Basic(optional = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DH_CODDETSOL", referencedColumnName = "CODDETSOL")
	private DetalleSolicitud detalleSolicitudCredito;

	@Column(name = "DH_NUMDEPDES", nullable = false)
	@Basic(optional = false)
	private Integer numeroPago;

	@Column(name = "DH_DEPMON", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal montoDeposito;

	@Column(name = "DH_DEPINT", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal interesDeposito;

	@Column(name = "DH_DEPGTO", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal gastoAdministrativoDeposito;

	@Column(name = "DH_DEPSEGDES", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal seguroDesgravamenDeposito;

	@Column(name = "DH_DEPSEGINC", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal seguroIncendiosDeposito;

	@Column(name = "DH_DEPDIV", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal dividendoDeposito;

	@Column(name = "DH_DEPFECESC", nullable = true)
	@Basic(optional = true)
	private Date fechaFirmaEscritura;

	@Column(name = "DH_DEPVENID", nullable = true)
	@Basic(optional = true)
	private String idVendedorDeposito;

	@Column(name = "DH_DEPVENNOM", nullable = true)
	@Basic(optional = true)
	private String nombreVendedorDeposito;

	@Column(name = "DH_DEPVENCTA", nullable = true)
	@Basic(optional = true)
	private String numeroCuentaDeposito;

	@Column(name = "DH_DEPVENTIPCTA", nullable = true)
	@Basic(optional = true)
	private String tipoCuentaDeposito;

	@Column(name = "DH_DEPCODBAN", nullable = true)
	@Basic(optional = true)
	private String rucBancoDeposito;

	@Column(name = "DH_DEPVENTIPID", nullable = true)
	@Basic(optional = true)
	private String idTipoIdentificacionVendedorDeposito;

	@Column(name = "DH_FECSOLPAG", nullable = true)
	private Date fecsolpag;

	@Column(name = "DH_DEPMONTOT", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal montoTotalDeposito;

	@Column(name = "DH_NUTDEP", nullable = true)
	@Basic(optional = true)
	private String idTransaccionDeposito;

	@Column(name = "DH_DEPSEGCON")
	private BigDecimal depositoSeguroConstruccion;

	@Column(name = "DH_DEPSEGVID", precision = 2, nullable = true)
	private BigDecimal seguroVidaDeposito;

	@Column(name = "DH_DEPFECAPR", nullable = true)
	private Date fechaAprobacionCrecio;

	@Column(name = "DH_DEPGTOBIESS", precision = 2, nullable = true)
	private BigDecimal gastosOperativosBiess;

	@Column(name = "DH_DEPMONAPR", precision = 2, nullable = true)
	private BigDecimal montoAprobado;

	@Column(name = "DH_DEPMONFIN", precision = 2, nullable = true)
	private BigDecimal montoFinanciado;

	@Column(name = "DH_DEPVALGARANTIA", precision = 2, nullable = true)
	private BigDecimal valorGarantia;

	@Column(name = "DH_AVALUOREALIZACION", precision = 2, nullable = true)
	private BigDecimal avaluoRealizacion;

	@Column(name = "DH_TIPODESEMBOLSO", nullable = true)
	private String tipoDesembolso;

	@Column(name = "DS_VALORPRESUPUESTO", precision = 2, nullable = true)
	private BigDecimal valorPresupuesto;

	// Desembolso VIVIENDA HIPOTECADA
	@Column(name = "DH_VALDESEMIFI", precision = 2, nullable = true)
	private BigDecimal valorDesembolsoIfi;

	@Column(name = "DH_NUMCTAIFI", nullable = true)
	private String numeroCuentaIfi;

	@Column(name = "DH_RUCIFI", nullable = true)
	private String rucIfi;

	@Column(name = "DH_TIPGARANTIAIFI", nullable = true)
	private String tipoGarantiaIfi;

	@Column(name = "DH_TIPCTAIFI", nullable = true)
	private String tipoCuentaIfi;

	@Column(name = "DH_NUTDEPSPL", nullable = true)
	private String numeroSplIfi;
	
	//OP CANELADAS
	@Column(name = "DH_NUTOPERACION", nullable = true)
	private Long nutOperacionCanc;
	
	@Column(name = "DH_VALOROPERACION", precision = 2, nullable = true)
	private BigDecimal valorOperacionCanc;
	
	@Column(name = "DH_VALORCAPITAL", precision = 2, nullable = true)
	private BigDecimal valorCapitalCanc;
	
	@Column(name = "DH_VALORINTERES", precision = 2, nullable = true)
	private BigDecimal valorInteresCanc;

	@Column(name = "DH_VALORSEGINC", nullable = true)
	private BigDecimal valorSeguroIncCanc;
	
	@Column(name = "DH_VALORSEGDES", nullable = true)
	private BigDecimal valorSeguroDesCanc;
	
	@Column(name = "DH_VALORINTMORA", nullable = true)
	private BigDecimal valorInteresMora;
	
	@Column(name = "DH_FECHACAN", nullable = true)
	private Date fechaCancelacion;
	
	@Column(name = "DH_VALORIMPUESTOS", nullable = true)
	private BigDecimal valorImpuestosCanc;
	
	@Column(name = "DH_VALORSEGVIDA", nullable = true)
	private BigDecimal valorSegVidaCanc;

	// **

	/**
	 * 
	 */
	public DepositoSolicitudHistorico() {
		// TODO:
	}

	/**
	 * @return the id
	 */

	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the detalleSolicitudCredito
	 */

	public DetalleSolicitud getDetalleSolicitudCredito() {
		return detalleSolicitudCredito;
	}

	/**
	 * @param detalleSolicitudCredito
	 *            the detalleSolicitudCredito to set
	 */

	public void setDetalleSolicitudCredito(
			DetalleSolicitud detalleSolicitudCredito) {
		this.detalleSolicitudCredito = detalleSolicitudCredito;
	}

	/**
	 * @return the numeroPago
	 */

	public Integer getNumeroPago() {
		return numeroPago;
	}

	/**
	 * @param numeroPago
	 *            the numeroPago to set
	 */

	public void setNumeroPago(Integer numeroPago) {
		this.numeroPago = numeroPago;
	}

	/**
	 * @return the montoDeposito
	 */

	public BigDecimal getMontoDeposito() {
		return montoDeposito;
	}

	/**
	 * @param montoDeposito
	 *            the montoDeposito to set
	 */

	public void setMontoDeposito(BigDecimal montoDeposito) {
		this.montoDeposito = montoDeposito;
	}

	/**
	 * @return the interesDeposito
	 */

	public BigDecimal getInteresDeposito() {
		return interesDeposito;
	}

	/**
	 * @param interesDeposito
	 *            the interesDeposito to set
	 */

	public void setInteresDeposito(BigDecimal interesDeposito) {
		this.interesDeposito = interesDeposito;
	}

	/**
	 * @return the gastoAdministrativoDeposito
	 */

	public BigDecimal getGastoAdministrativoDeposito() {
		return gastoAdministrativoDeposito;
	}

	/**
	 * @param gastoAdministrativoDeposito
	 *            the gastoAdministrativoDeposito to set
	 */

	public void setGastoAdministrativoDeposito(
			BigDecimal gastoAdministrativoDeposito) {
		this.gastoAdministrativoDeposito = gastoAdministrativoDeposito;
	}

	/**
	 * @return the seguroDesgravamenDeposito
	 */

	public BigDecimal getSeguroDesgravamenDeposito() {
		return seguroDesgravamenDeposito;
	}

	/**
	 * @param seguroDesgravamenDeposito
	 *            the seguroDesgravamenDeposito to set
	 */

	public void setSeguroDesgravamenDeposito(
			BigDecimal seguroDesgravamenDeposito) {
		this.seguroDesgravamenDeposito = seguroDesgravamenDeposito;
	}

	/**
	 * @return the seguroIncendiosDeposito
	 */

	public BigDecimal getSeguroIncendiosDeposito() {
		return seguroIncendiosDeposito;
	}

	/**
	 * @param seguroIncendiosDeposito
	 *            the seguroIncendiosDeposito to set
	 */

	public void setSeguroIncendiosDeposito(BigDecimal seguroIncendiosDeposito) {
		this.seguroIncendiosDeposito = seguroIncendiosDeposito;
	}

	/**
	 * @return the dividendoDeposito
	 */

	public BigDecimal getDividendoDeposito() {
		return dividendoDeposito;
	}

	/**
	 * @param dividendoDeposito
	 *            the dividendoDeposito to set
	 */

	public void setDividendoDeposito(BigDecimal dividendoDeposito) {
		this.dividendoDeposito = dividendoDeposito;
	}

	/**
	 * @return the fechaFirmaEscritura
	 */

	public Date getFechaFirmaEscritura() {
		return fechaFirmaEscritura;
	}

	/**
	 * @param fechaFirmaEscritura
	 *            the fechaFirmaEscritura to set
	 */

	public void setFechaFirmaEscritura(Date fechaFirmaEscritura) {
		this.fechaFirmaEscritura = fechaFirmaEscritura;
	}

	/**
	 * @return the idVendedorDeposito
	 */

	public String getIdVendedorDeposito() {
		return idVendedorDeposito;
	}

	/**
	 * @param idVendedorDeposito
	 *            the idVendedorDeposito to set
	 */

	public void setIdVendedorDeposito(String idVendedorDeposito) {
		this.idVendedorDeposito = idVendedorDeposito;
	}

	/**
	 * @return the nombreVendedorDeposito
	 */

	public String getNombreVendedorDeposito() {
		return nombreVendedorDeposito;
	}

	/**
	 * @param nombreVendedorDeposito
	 *            the nombreVendedorDeposito to set
	 */

	public void setNombreVendedorDeposito(String nombreVendedorDeposito) {
		this.nombreVendedorDeposito = nombreVendedorDeposito;
	}

	/**
	 * @return the numeroCuentaDeposito
	 */

	public String getNumeroCuentaDeposito() {
		return numeroCuentaDeposito;
	}

	/**
	 * @param numeroCuentaDeposito
	 *            the numeroCuentaDeposito to set
	 */

	public void setNumeroCuentaDeposito(String numeroCuentaDeposito) {
		this.numeroCuentaDeposito = numeroCuentaDeposito;
	}

	/**
	 * @return the tipoCuentaDeposito
	 */

	public String getTipoCuentaDeposito() {
		return tipoCuentaDeposito;
	}

	/**
	 * @param tipoCuentaDeposito
	 *            the tipoCuentaDeposito to set
	 */

	public void setTipoCuentaDeposito(String tipoCuentaDeposito) {
		this.tipoCuentaDeposito = tipoCuentaDeposito;
	}

	/**
	 * @return the rucBancoDeposito
	 */

	public String getRucBancoDeposito() {
		return rucBancoDeposito;
	}

	/**
	 * @param rucBancoDeposito
	 *            the rucBancoDeposito to set
	 */

	public void setRucBancoDeposito(String rucBancoDeposito) {
		this.rucBancoDeposito = rucBancoDeposito;
	}

	/**
	 * @return the idTipoIdentificacionVendedorDeposito
	 */

	public String getIdTipoIdentificacionVendedorDeposito() {
		return idTipoIdentificacionVendedorDeposito;
	}

	/**
	 * @param idTipoIdentificacionVendedorDeposito
	 *            the idTipoIdentificacionVendedorDeposito to set
	 */

	public void setIdTipoIdentificacionVendedorDeposito(
			String idTipoIdentificacionVendedorDeposito) {
		this.idTipoIdentificacionVendedorDeposito = idTipoIdentificacionVendedorDeposito;
	}

	/**
	 * @return the fecsolpag
	 */

	public Date getFecsolpag() {
		return fecsolpag;
	}

	/**
	 * @param fecsolpag
	 *            the fecsolpag to set
	 */

	public void setFecsolpag(Date fecsolpag) {
		this.fecsolpag = fecsolpag;
	}

	/**
	 * @return the montoTotalDeposito
	 */

	public BigDecimal getMontoTotalDeposito() {
		return montoTotalDeposito;
	}

	/**
	 * @param montoTotalDeposito
	 *            the montoTotalDeposito to set
	 */

	public void setMontoTotalDeposito(BigDecimal montoTotalDeposito) {
		this.montoTotalDeposito = montoTotalDeposito;
	}

	/**
	 * @return the idTransaccionDeposito
	 */

	public String getIdTransaccionDeposito() {
		return idTransaccionDeposito;
	}

	/**
	 * @param idTransaccionDeposito
	 *            the idTransaccionDeposito to set
	 */

	public void setIdTransaccionDeposito(String idTransaccionDeposito) {
		this.idTransaccionDeposito = idTransaccionDeposito;
	}

	/**
	 * @return the depositoSeguroConstruccion
	 */

	public BigDecimal getDepositoSeguroConstruccion() {
		return depositoSeguroConstruccion;
	}

	/**
	 * @param depositoSeguroConstruccion
	 *            the depositoSeguroConstruccion to set
	 */

	public void setDepositoSeguroConstruccion(
			BigDecimal depositoSeguroConstruccion) {
		this.depositoSeguroConstruccion = depositoSeguroConstruccion;
	}

	/**
	 * @return the seguroVidaDeposito
	 */

	public BigDecimal getSeguroVidaDeposito() {
		return seguroVidaDeposito;
	}

	/**
	 * @param seguroVidaDeposito
	 *            the seguroVidaDeposito to set
	 */

	public void setSeguroVidaDeposito(BigDecimal seguroVidaDeposito) {
		this.seguroVidaDeposito = seguroVidaDeposito;
	}

	/**
	 * @return the fechaAprobacionCrecio
	 */

	public Date getFechaAprobacionCrecio() {
		return fechaAprobacionCrecio;
	}

	/**
	 * @param fechaAprobacionCrecio
	 *            the fechaAprobacionCrecio to set
	 */

	public void setFechaAprobacionCrecio(Date fechaAprobacionCrecio) {
		this.fechaAprobacionCrecio = fechaAprobacionCrecio;
	}

	/**
	 * @return the gastosOperativosBiess
	 */

	public BigDecimal getGastosOperativosBiess() {
		return gastosOperativosBiess;
	}

	/**
	 * @param gastosOperativosBiess
	 *            the gastosOperativosBiess to set
	 */

	public void setGastosOperativosBiess(BigDecimal gastosOperativosBiess) {
		this.gastosOperativosBiess = gastosOperativosBiess;
	}

	/**
	 * @return the montoAprobado
	 */

	public BigDecimal getMontoAprobado() {
		return montoAprobado;
	}

	/**
	 * @param montoAprobado
	 *            the montoAprobado to set
	 */

	public void setMontoAprobado(BigDecimal montoAprobado) {
		this.montoAprobado = montoAprobado;
	}

	/**
	 * @return the montoFinanciado
	 */

	public BigDecimal getMontoFinanciado() {
		return montoFinanciado;
	}

	/**
	 * @param montoFinanciado
	 *            the montoFinanciado to set
	 */

	public void setMontoFinanciado(BigDecimal montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}

	/**
	 * @return the valorGarantia
	 */

	public BigDecimal getValorGarantia() {
		return valorGarantia;
	}

	/**
	 * @param valorGarantia
	 *            the valorGarantia to set
	 */

	public void setValorGarantia(BigDecimal valorGarantia) {
		this.valorGarantia = valorGarantia;
	}

	/**
	 * @return the avaluoRealizacion
	 */
	public BigDecimal getAvaluoRealizacion() {
		return avaluoRealizacion;
	}

	/**
	 * @param avaluoRealizacion
	 *            the avaluoRealizacion to set
	 */
	public void setAvaluoRealizacion(BigDecimal avaluoRealizacion) {
		this.avaluoRealizacion = avaluoRealizacion;
	}

	/**
	 * @return the tipoDesembolso
	 */
	public String getTipoDesembolso() {
		return tipoDesembolso;
	}

	/**
	 * @param tipoDesembolso
	 *            the tipoDesembolso to set
	 */
	public void setTipoDesembolso(String tipoDesembolso) {
		this.tipoDesembolso = tipoDesembolso;
	}

	public BigDecimal getValorPresupuesto() {
		return valorPresupuesto;
	}

	public void setValorPresupuesto(BigDecimal valorPresupuesto) {
		this.valorPresupuesto = valorPresupuesto;
	}

	/**
	 * @return the valorDesembolsoIfi
	 */
	public BigDecimal getValorDesembolsoIfi() {
		return valorDesembolsoIfi;
	}

	/**
	 * @param valorDesembolsoIfi
	 *            the valorDesembolsoIfi to set
	 */
	public void setValorDesembolsoIfi(BigDecimal valorDesembolsoIfi) {
		this.valorDesembolsoIfi = valorDesembolsoIfi;
	}

	/**
	 * @return the numeroCuentaIfi
	 */
	public String getNumeroCuentaIfi() {
		return numeroCuentaIfi;
	}

	/**
	 * @param numeroCuentaIfi
	 *            the numCuentaIfi to set
	 */
	public void setNumeroCuentaIfi(String numeroCuentaIfi) {
		this.numeroCuentaIfi = numeroCuentaIfi;
	}

	/**
	 * @return the rucIfi
	 */
	public String getRucIfi() {
		return rucIfi;
	}

	/**
	 * @param rucIfi
	 *            the rucIfi to set
	 */
	public void setRucIfi(String rucIfi) {
		this.rucIfi = rucIfi;
	}

	/**
	 * @return the tipoGarantiaIfi
	 */
	public String getTipoGarantiaIfi() {
		return tipoGarantiaIfi;
	}

	/**
	 * @param tipoGarantiaIfi
	 *            the tipoGarantiaIfi to set
	 */
	public void setTipoGarantiaIfi(String tipoGarantiaIfi) {
		this.tipoGarantiaIfi = tipoGarantiaIfi;
	}

	/**
	 * @return the tipoCuentaIfi
	 */
	public String getTipoCuentaIfi() {
		return tipoCuentaIfi;
	}

	/**
	 * @param tipoCuentaIfi
	 *            the tipoCuentaIfi to set
	 */
	public void setTipoCuentaIfi(String tipoCuentaIfi) {
		this.tipoCuentaIfi = tipoCuentaIfi;
	}

	/**
	 * @param numeroSplIfi
	 *            the numeroSplIfi to set
	 */
	public void setNumeroSplIfi(String numeroSplIfi) {
		this.numeroSplIfi = numeroSplIfi;
	}

	/**
	 * @return the numeroSplIfi
	 */
	public String getNumeroSplIfi() {
		return numeroSplIfi;
	}

	/**
	 * @return the nutOperacionCanc
	 */
	public Long getNutOperacionCanc() {
		return nutOperacionCanc;
	}

	/**
	 * @param nutOperacionCanc the nutOperacionCanc to set
	 */
	public void setNutOperacionCanc(Long nutOperacionCanc) {
		this.nutOperacionCanc = nutOperacionCanc;
	}

	/**
	 * @return the valorOperacionCanc
	 */
	public BigDecimal getValorOperacionCanc() {
		return valorOperacionCanc;
	}

	/**
	 * @param valorOperacionCanc the valorOperacionCanc to set
	 */
	public void setValorOperacionCanc(BigDecimal valorOperacionCanc) {
		this.valorOperacionCanc = valorOperacionCanc;
	}

	/**
	 * @return the valorCapitalCanc
	 */
	public BigDecimal getValorCapitalCanc() {
		return valorCapitalCanc;
	}

	/**
	 * @param valorCapitalCanc the valorCapitalCanc to set
	 */
	public void setValorCapitalCanc(BigDecimal valorCapitalCanc) {
		this.valorCapitalCanc = valorCapitalCanc;
	}

	/**
	 * @return the valorInteresCanc
	 */
	public BigDecimal getValorInteresCanc() {
		return valorInteresCanc;
	}

	/**
	 * @param valorInteresCanc the valorInteresCanc to set
	 */
	public void setValorInteresCanc(BigDecimal valorInteresCanc) {
		this.valorInteresCanc = valorInteresCanc;
	}

	/**
	 * @return the valorSeguroIncCanc
	 */
	public BigDecimal getValorSeguroIncCanc() {
		return valorSeguroIncCanc;
	}

	/**
	 * @param valorSeguroIncCanc the valorSeguroIncCanc to set
	 */
	public void setValorSeguroIncCanc(BigDecimal valorSeguroIncCanc) {
		this.valorSeguroIncCanc = valorSeguroIncCanc;
	}

	/**
	 * @return the valorSeguroDesCanc
	 */
	public BigDecimal getValorSeguroDesCanc() {
		return valorSeguroDesCanc;
	}

	/**
	 * @param valorSeguroDesCanc the valorSeguroDesCanc to set
	 */
	public void setValorSeguroDesCanc(BigDecimal valorSeguroDesCanc) {
		this.valorSeguroDesCanc = valorSeguroDesCanc;
	}

	/**
	 * @return the valorInteresMora
	 */
	public BigDecimal getValorInteresMora() {
		return valorInteresMora;
	}

	/**
	 * @param valorInteresMora the valorInteresMora to set
	 */
	public void setValorInteresMora(BigDecimal valorInteresMora) {
		this.valorInteresMora = valorInteresMora;
	}

	/**
	 * @return the fechaCancelacion
	 */
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	/**
	 * @param fechaCancelacion the fechaCancelacion to set
	 */
	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	/**
	 * @return the valorImpuestosCanc
	 */
	public BigDecimal getValorImpuestosCanc() {
		return valorImpuestosCanc;
	}

	/**
	 * @param valorImpuestosCanc the valorImpuestosCanc to set
	 */
	public void setValorImpuestosCanc(BigDecimal valorImpuestosCanc) {
		this.valorImpuestosCanc = valorImpuestosCanc;
	}

	/**
	 * @return the valorGastosCanc
	 */
	public BigDecimal getValorSegVidaCanc() {
		return valorSegVidaCanc;
	}

	/**
	 * @param valorGastosCanc the valorGastosCanc to set
	 */
	public void setValorSegVidaCanc(BigDecimal valorGastosCanc) {
		this.valorSegVidaCanc = valorGastosCanc;
	}

}
