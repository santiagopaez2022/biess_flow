/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * <b> Clase para guardar el detalle del depósito al momento de desembolso por
 * el banco central </b>
 * 
 * @author caldaz, Jenny Sanchez
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 15:43:59 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_DETALLEDEPOSITO_TBL")
@SequenceGenerator(name = "depositoSolicitudSeq", sequenceName = "CRE_DETALLEDEPOSITO_SEQ", initialValue = 1, allocationSize = 1)
public class DepositoSolicitud implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 530574941225775978L;

	@Id
	@Column(name = "CODDETDEP", nullable = false, insertable = true)
	@GeneratedValue(generator = "depositoSolicitudSeq", strategy = GenerationType.SEQUENCE)
	@Basic(optional = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER"),
			@JoinColumn(name = "NUMSOLSER", referencedColumnName = "NUMSOLSER") })
	private SolicitudCredito solicitudCredito;

	@Column(name = "CODDETSOL", nullable = false)
	@Basic(optional = false)
	private Long coddetsol;

	@Column(name = "NUMDEPDES", nullable = false)
	@Basic(optional = false)
	private Integer numeroPago;

	@Column(name = "DEPMON", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal montoDeposito;

	@Column(name = "DEPINT", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal interesDeposito;

	@Column(name = "DEPGTO", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal gastoAdministrativoDeposito;

	@Column(name = "DEPSEGDES", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal seguroDesgravamenDeposito;

	@Column(name = "DEPSEGINC", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal seguroIncendiosDeposito;

	@Column(name = "DEPDIV", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal dividendoDeposito;

	@Column(name = "DEPFECESC", nullable = true)
	@Basic(optional = true)
	private Date fechaFirmaEscritura;

	@Column(name = "DEPVEN_ID", nullable = true)
	@Basic(optional = true)
	private String idVendedorDeposito;

	@Column(name = "DEPVENNOM", nullable = true)
	@Basic(optional = true)
	private String nombreVendedorDeposito;

	@Column(name = "DEPVENCTA", nullable = true)
	@Basic(optional = true)
	private String numeroCuentaDeposito;

	@Column(name = "DEPVENTIPCTA", nullable = true)
	@Basic(optional = true)
	private String tipoCuentaDeposito;

	@Column(name = "DEPCODBAN", nullable = true)
	@Basic(optional = true)
	private String rucBancoDeposito;

	@Column(name = "DEPVENTIP_ID", nullable = true)
	@Basic(optional = true)
	private String idTipoIdentificacionVendedorDeposito;

	@Column(name = "FECSOLPAG", nullable = true)
	private Date fecsolpag;

	@Column(name = "DEPMONTOT", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal montoTotalDeposito;

	@Column(name = "NUTDEP", nullable = true)
	@Basic(optional = true)
	private String idTransaccionDeposito;

	@Column(name = "DEPSEGCON")
	private BigDecimal depositoSeguroConstruccion;

	@Column(name = "DEPSEGVID", precision = 2, nullable = true)
	private BigDecimal seguroVidaDeposito;

	@Column(name = "DEPFECAPR", nullable = true)
	private Date fechaAprobacionCrecio;

	@Column(name = "DEPGTOBIESS", precision = 2, nullable = true)
	private BigDecimal gastosOperativosBiess;

	@Column(name = "DEPMONAPR", precision = 2, nullable = true)
	private BigDecimal montoAprobado;

	@Column(name = "DEPMONFIN", precision = 2, nullable = true)
	private BigDecimal montoFinanciado;

	@Column(name = "DEPVALGARANTIA", precision = 2, nullable = true)
	private BigDecimal valorGarantia;

	@Column(name = "DD_AVALUOREALIZACION", precision = 2, nullable = true)
	private BigDecimal avaluoRealizacion;

	@Column(name = "DD_TIPODESEMBOLSO", nullable = true)
	private String tipoDesembolso;
	
	@Column(name = "DD_VALORPRESUPUESTO", precision = 2,nullable = true)
	private BigDecimal valorPresupuesto;
	
	//Desembolso VIVIENDA HIPOTECADA
	@Column(name = "DD_VALDESEMIFI", precision = 2,nullable = true)
	private BigDecimal valorDesembolsoIfi;
	
	@Column(name = "DD_NUMCTAIFI", nullable = true)
	private String numeroCuentaIfi;
	
	@Column(name = "DD_RUCIFI", nullable = true)
	private String rucIfi;
	
	@Column(name = "DD_TIPGARANTIAIFI", nullable = true)
	private String tipoGarantiaIfi;
	
	@Column(name = "DD_TIPCTAIFI", nullable = true)
	private String tipoCuentaIfi;
	
	@Column(name = "DD_NUTDEPSPL", nullable = true)
	private String nutDepSpl;
	//**
	
	/**
	 * 
	 */
	public DepositoSolicitud() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}

	public Integer getNumeroPago() {
		return numeroPago;
	}

	public void setNumeroPago(Integer numeroPago) {
		this.numeroPago = numeroPago;
	}

	public Long getCoddetsol() {
		return coddetsol;
	}

	public void setCoddetsol(Long coddetsol) {
		this.coddetsol = coddetsol;
	}

	public BigDecimal getMontoDeposito() {
		return montoDeposito;
	}

	public void setMontoDeposito(BigDecimal montoDeposito) {
		this.montoDeposito = montoDeposito;
	}

	public BigDecimal getInteresDeposito() {
		return interesDeposito;
	}

	public void setInteresDeposito(BigDecimal interesDeposito) {
		this.interesDeposito = interesDeposito;
	}

	public BigDecimal getGastoAdministrativoDeposito() {
		return gastoAdministrativoDeposito;
	}

	public void setGastoAdministrativoDeposito(BigDecimal gastoAdministrativoDeposito) {
		this.gastoAdministrativoDeposito = gastoAdministrativoDeposito;
	}

	public BigDecimal getSeguroDesgravamenDeposito() {
		return seguroDesgravamenDeposito;
	}

	public void setSeguroDesgravamenDeposito(BigDecimal seguroDesgravamenDeposito) {
		this.seguroDesgravamenDeposito = seguroDesgravamenDeposito;
	}

	public BigDecimal getSeguroIncendiosDeposito() {
		return seguroIncendiosDeposito;
	}

	public void setSeguroIncendiosDeposito(BigDecimal seguroIncendiosDeposito) {
		this.seguroIncendiosDeposito = seguroIncendiosDeposito;
	}

	public BigDecimal getDividendoDeposito() {
		return dividendoDeposito;
	}

	public void setDividendoDeposito(BigDecimal dividendoDeposito) {
		this.dividendoDeposito = dividendoDeposito;
	}

	public Date getFechaFirmaEscritura() {
		return fechaFirmaEscritura;
	}

	public void setFechaFirmaEscritura(Date fechaFirmaEscritura) {
		this.fechaFirmaEscritura = fechaFirmaEscritura;
	}

	public String getIdVendedorDeposito() {
		return idVendedorDeposito;
	}

	public void setIdVendedorDeposito(String idVendedorDeposito) {
		this.idVendedorDeposito = idVendedorDeposito;
	}

	public String getNombreVendedorDeposito() {
		return nombreVendedorDeposito;
	}

	public void setNombreVendedorDeposito(String nombreVendedorDeposito) {
		this.nombreVendedorDeposito = nombreVendedorDeposito;
	}

	public String getNumeroCuentaDeposito() {
		return numeroCuentaDeposito;
	}

	public void setNumeroCuentaDeposito(String numeroCuentaDeposito) {
		this.numeroCuentaDeposito = numeroCuentaDeposito;
	}

	public String getTipoCuentaDeposito() {
		return tipoCuentaDeposito;
	}

	public void setTipoCuentaDeposito(String tipoCuentaDeposito) {
		this.tipoCuentaDeposito = tipoCuentaDeposito;
	}

	public String getRucBancoDeposito() {
		return rucBancoDeposito;
	}

	public void setRucBancoDeposito(String rucBancoDeposito) {
		this.rucBancoDeposito = rucBancoDeposito;
	}

	public String getIdTipoIdentificacionVendedorDeposito() {
		return idTipoIdentificacionVendedorDeposito;
	}

	public void setIdTipoIdentificacionVendedorDeposito(String idTipoIdentificacionVendedorDeposito) {
		this.idTipoIdentificacionVendedorDeposito = idTipoIdentificacionVendedorDeposito;
	}

	public Date getFecsolpag() {
		return fecsolpag;
	}

	public void setFecsolpag(Date fecsolpag) {
		this.fecsolpag = fecsolpag;
	}

	public BigDecimal getMontoTotalDeposito() {
		return montoTotalDeposito;
	}

	public void setMontoTotalDeposito(BigDecimal montoTotalDeposito) {
		this.montoTotalDeposito = montoTotalDeposito;
	}

	public String getIdTransaccionDeposito() {
		return idTransaccionDeposito;
	}

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
	public void setDepositoSeguroConstruccion(BigDecimal depositoSeguroConstruccion) {
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
	 * @param valorDesembolsoIfi the valorDesembolsoIfi to set
	 */
	public void setValorDesembolsoIfi(BigDecimal valorDesembolsoIfi) {
		this.valorDesembolsoIfi = valorDesembolsoIfi;
	}

	/**
	 * @return the valorDesembolsoIfi
	 */
	public BigDecimal getValorDesembolsoIfi() {
		return valorDesembolsoIfi;
	}

	/**
	 * @param numeroCuentaIfi the numeroCuentaIfi to set
	 */
	public void setNumeroCuentaIfi(String numeroCuentaIfi) {
		this.numeroCuentaIfi = numeroCuentaIfi;
	}

	/**
	 * @return the numeroCuentaIfi
	 */
	public String getNumeroCuentaIfi() {
		return numeroCuentaIfi;
	}

	/**
	 * @param rucIfi the rucIfi to set
	 */
	public void setRucIfi(String rucIfi) {
		this.rucIfi = rucIfi;
	}

	/**
	 * @return the rucIfi
	 */
	public String getRucIfi() {
		return rucIfi;
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
	 * @param tipoCuentaIfi the tipoCuentaIfi to set
	 */
	public void setTipoCuentaIfi(String tipoCuentaIfi) {
		this.tipoCuentaIfi = tipoCuentaIfi;
	}

	/**
	 * @return the tipoCuentaIfi
	 */
	public String getTipoCuentaIfi() {
		return tipoCuentaIfi;
	}

	/**
	 * @param numeroSplIfi the numeroSplIfi to set
	 */
	public void setNutDepSpl(String numeroSplIfi) {
		this.nutDepSpl = numeroSplIfi;
	}

	/**
	 * @return the numeroSplIfi
	 */
	public String getNutDepSpl() {
		return nutDepSpl;
	}
	
	
	
}
