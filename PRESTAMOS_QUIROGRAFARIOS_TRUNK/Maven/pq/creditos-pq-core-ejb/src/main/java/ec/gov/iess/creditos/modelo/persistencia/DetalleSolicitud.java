/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;
import ec.gov.iess.hl.modelo.DivisionPolitica;

/**
 * 
 * <b> Se añadieron nuevas columnas necesarias para incluir información desde
 * TATA. </b>
 * 
 * @author cvillarreal, Jenny Sanchez
 * @version $Revision: 1.7.6.1 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 2011/05/24 21:50:31 $] [$Modificado:
 *          rbarrera $, $Date: 2011/10/28 13:28:35 $]
 *          </p>
 * @version 1.0
 */
@Entity
@Table(name = "CRE_DETALLESOLICITUD_TBL")
@NamedQueries({
		@NamedQuery(name = "DetalleSolicitud.findAll", query = "SELECT o FROM DetalleSolicitud o"),
		@NamedQuery(name = "DetalleSolicitud.porID", query = "SELECT o FROM DetalleSolicitud o WHERE o.coddetsol in (:listaId)")

})
@SequenceGenerator(name = "CRE_DETALLESOLICITUD_SEQ", sequenceName = "CRE_DETALLESOLICITUD_SEQ", allocationSize = 1)
public class DetalleSolicitud implements Serializable {

	private static final long serialVersionUID = -4824137200157482672L;

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRE_DETALLESOLICITUD_SEQ")
	private Long coddetsol;

	@Column(nullable = false)
	private BigDecimal avaviv;

	@Column(nullable = false)
	private BigDecimal cuoapr;

	@Column(nullable = false)
	private BigDecimal intapr;

	@Column(nullable = false)
	private BigDecimal monapr;

	@Column(nullable = false)
	private Long plaapr;

	@Column(name = "AVADIR")
	private String avaluoDireccion;

	@Column(name = "CODIVPOLAVA")
	private String avaluoDivisionPolitica;

	@Column(name = "AVAESTBIEN")
	private String avaluoEstadoBien;

	@Column(name = "AVATIPBIEN")
	private String avaluoTipoBien;

	@Column(name = "AVASECTOR")
	private String avaluoSector;

	@Column(name = "VENNOM")
	private String vendedorNombre;

	@Column(name = "VENTIPDOC")
	private String vendedorTipoDocumento;// Cedula, pasaporte

	@Column(name = "VENDOC")
	private String vendedorDocumento;// el valor de la cedula o pasaporte

	@Column(name = "VENTIPPER")
	private String vendedorTipoPersona;// si es NAT (natural) o JUR (juridica)

	@Column(name = "VENTEL")
	private String vendedorTelf;

	@Column(name = "VENTELTRA")
	private String vendedorTelfTrabajo;

	@Column(name = "VENCEL")
	private String vendedorCelular;

	@Column(name = "VENDIR")
	private String vendedorDireccion;

	@Column(name = "VENNOMBEN")
	private String vendedorBeneficiarioCuenta;

	@Column(name = "VENCODIFI")
	private String vendedorCodigoBancoCuenta;

	@Column(name = "VENTIPCTA")
	private String vendedorTipoCuenta;

	@Column(name = "VENNUMCTA")
	private String vendedorNumeroCuenta;

	@Column(name = "CODDIVPOLVEN")
	private String vendedorDivisionPolitica;

	/*
	 * campo debe permitir null para hipotecarios
	 */
	@Column(nullable = true)
	private Date fecsolpag;

	@Transient
	private DivisionPolitica divisionPoliticaAvaluo;

	@Transient
	private DivisionPolitica divisionPoliticaVendedor;

	@Transient
	private InstitucionFinanciera vendedorInstitucionFinanciera;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER"),
			@JoinColumn(name = "NUMSOLSER", referencedColumnName = "NUMSOLSER") })
	private SolicitudCredito solicitudCredito;

	@Column(name = "DEPMONTOT", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal montoTotalDeposito;

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

	/*
	 * @Column(name="DEPTIPPAG",nullable=true) @Basic(optional=true) private
	 * String tipoPagoDeposito;
	 */

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

	@Column(name = "DEPVENTIP_ID", nullable = true)
	@Basic(optional = true)
	private String idTipoIdentificacionVendedorDeposito;

	@Column(name = "NUTDEP", nullable = true)
	@Basic(optional = true)
	private String idTransaccionDeposito;

	@Column(name = "DEPCODBAN", nullable = true)
	@Basic(optional = true)
	private String rucBancoDeposito;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODAGNREC")
	private Agencia agenciaReceptora;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODAGN")
	private Agencia agenciaEmisora;

	@Column(name = "CUOCOEDES", nullable = true)
	@Basic(optional = true)
	private BigDecimal coeficienteSeguroDesgravamen;

	@Column(name = "CUOSEGDES", nullable = true)
	@Basic(optional = true)
	private BigDecimal cuotaSeguroDesgravamen;

	@Column(name = "CUOCOEINC", nullable = true)
	@Basic(optional = true)
	private BigDecimal coeficienteSeguroIncendios;

	@Column(name = "CUOSEGINC", nullable = true)
	@Basic(optional = true)
	private BigDecimal cuotaSeguroIncendios;

	@SuppressWarnings("unused")
	@Transient
	private BigDecimal cuotaTotal;

	@Column(name = "MAXMON", nullable = true)
	@Basic(optional = true)
	private BigDecimal maxMonto;

	@Column(name = "MAXPLA", nullable = true)
	@Basic(optional = true)
	private Integer maxPlazo;

	@Column(name = "MAXINT", nullable = true)
	@Basic(optional = true)
	private BigDecimal maxInteres;

	@Column(name = "MAXCUO", nullable = true)
	@Basic(optional = true)
	private BigDecimal maxCuota;

	@Column(name = "MAXCOEDES", nullable = true)
	@Basic(optional = true)
	private BigDecimal maxCoeficienteSeguroDesgravamen;

	@Column(name = "MAXCOEINC", nullable = true)
	@Basic(optional = true)
	private BigDecimal maxCoeficienteSeguroIncendios;

	@Column(name = "MAXSEGDES", nullable = true)
	@Basic(optional = true)
	private BigDecimal maxSeguroDesgravamen;

	@Column(name = "MAXSEGINC", nullable = true)
	@Basic(optional = true)
	private BigDecimal maxSeguroIncendios;

	@Column(name = "CUOSEGCON")
	private BigDecimal cuotaSeguroConstruccion;

	@Column(name = "COESEGCON")
	private BigDecimal coeficienteSeguroConstruccion;

	@Column(name = "MAXSEGCON")
	private BigDecimal maxSeguroConstruccion;

	@Column(name = "MAXCOESEGCON")
	private BigDecimal maxCoeficienteSeguroConstruccion;

	@Column(name = "DEPSEGCON")
	private BigDecimal depositoSeguroConstruccion;

	// TODO implementar
	@Column(name = "NUMDEPDES")
	private Integer numeroDeposito;

	private String traLegSol;

	@Column(name = "NUMTOTDES")
	private Integer numeroTotalDesembolso;

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

	@Column(name = "DS_CALLEPRINCIPAL", nullable = true)
	private String callePrincipalTerreno;

	@Column(name = "DS_CALLESECUNDARIA", nullable = true)
	private String calleSecundariaTerreno;

	@Column(name = "DS_NOMBREURBANIZACION", nullable = true)
	private String urbanizacionTerreno;

	@Column(name = "DS_LOTE", nullable = true)
	private String nroLoteTerreno;

	@Column(name = "DS_TIPCTAPETICIONARIO", nullable = true)
	private String tipoCtaPeticionario;

	@Column(name = "DS_NUMCTAPETICIONARIO", nullable = true)
	private String numeroCtaPeticionario;

	@Column(name = "DS_NOMIFIPETICIONARIO", nullable = true)
	private String codigoIfiPeticionario;

	@Column(name = "DS_NOMBENPETICIONARIO", nullable = true)
	private String nombreBeneficiarioPeticionario;

	@Column(name = "DS_VALORTERRENO", precision = 2, nullable = true)
	private BigDecimal valorEstimadoTerreno;

	@Column(name = "DS_VALORCONSTRUCCION", precision = 2, nullable = true)
	private BigDecimal valorEstimadoConstruccion;

	@Column(name = "DS_EXPERIENCIANEGOCIO", nullable = true)
	private String experienciaNegocio;

	@Column(name = "DS_AVALUOREALIZACION", precision = 2, nullable = true)
	private BigDecimal avaluoRealizacion;

	@Column(name = "DS_BUROINMUEBLE", nullable = true)
	private String buroInmueble;

	@OneToMany(mappedBy = "detalleSolicitudCredito", cascade = CascadeType.ALL)
	private List<DatosPersonalesUsuario> listaDetalleAdicional;

	@Column(name = "DS_TIPODESEMBOLSO", nullable = true)
	private String tipoDesembolso;

	@Column(name = "DS_SALDOCAPITAL", nullable = true)
	private BigDecimal saldoCapital;

	@Column(name = "DS_VALORUNIFICADO", nullable = true)
	private BigDecimal valorUnificado;
	// **
	@Column(name = "DS_AREAEDIF", nullable = true)
	private BigDecimal areaEdificacion;

	@Column(name = "DS_AREATERRENO", nullable = true)
	private BigDecimal areaTerreno;

	@Column(name = "DS_NUMHAB", nullable = true)
	private Integer numeroHabitaciones;

	@Column(name = "DS_VIVPRO", nullable = true)
	private String viviendaPropia;

	@Column(name = "DS_VALORPRESUPUESTO", precision = 2, nullable = true)
	private BigDecimal valorPresupuesto;

	@Transient
	private InstitucionFinanciera institucionFinancieraPeticionario;

	// Desembolso VIVIENDA HIPOTECADA
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private DetalleSolicitudIfi detalleSolicitudIfi;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private OperacionesCanceladas operacionCancelada;

	/**
	 * 
	 */
	public DetalleSolicitud() {
	}

	/**
	 * @return the coddetsol
	 */
	public Long getCoddetsol() {
		return coddetsol;
	}

	/**
	 * @return the avaviv
	 */
	public BigDecimal getAvaviv() {
		return avaviv;
	}

	/**
	 * @return the cuoapr
	 */
	public BigDecimal getCuoapr() {
		return cuoapr;
	}

	/**
	 * @return the intapr
	 */
	public BigDecimal getIntapr() {
		return intapr;
	}

	/**
	 * @return the monapr
	 */
	public BigDecimal getMonapr() {
		return monapr;
	}

	/**
	 * @return the plaapr
	 */
	public Long getPlaapr() {
		return plaapr;
	}

	/**
	 * @return the solicitudCredito
	 */
	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	/**
	 * @param coddetsol
	 *            the coddetsol to set
	 */
	public void setCoddetsol(Long coddetsol) {
		this.coddetsol = coddetsol;
	}

	/**
	 * @param avaviv
	 *            the avaviv to set
	 */
	public void setAvaviv(BigDecimal avaviv) {
		this.avaviv = avaviv;
	}

	/**
	 * @param cuoapr
	 *            the cuoapr to set
	 */
	public void setCuoapr(BigDecimal cuoapr) {
		this.cuoapr = cuoapr;
	}

	/**
	 * @param intapr
	 *            the intapr to set
	 */
	public void setIntapr(BigDecimal intapr) {
		this.intapr = intapr;
	}

	/**
	 * @param monapr
	 *            the monapr to set
	 */
	public void setMonapr(BigDecimal monapr) {
		this.monapr = monapr;
	}

	/**
	 * @param plaapr
	 *            the plaapr to set
	 */
	public void setPlaapr(Long plaapr) {
		this.plaapr = plaapr;
	}

	/**
	 * @param solicitudCredito
	 *            the solicitudCredito to set
	 */
	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}

	/**
	 * @return the avaluoDireccion
	 */
	public String getAvaluoDireccion() {
		return avaluoDireccion;
	}

	/**
	 * @param avaluoDireccion
	 *            the avaluoDireccion to set
	 */
	public void setAvaluoDireccion(String avaluoDireccion) {
		this.avaluoDireccion = avaluoDireccion;
	}

	/**
	 * @return the avaluoDivisionPolitica
	 */
	public String getAvaluoDivisionPolitica() {
		return avaluoDivisionPolitica;
	}

	/**
	 * @param avaluoDivisionPolitica
	 *            the avaluoDivisionPolitica to set
	 */
	public void setAvaluoDivisionPolitica(String avaluoDivisionPolitica) {
		this.avaluoDivisionPolitica = avaluoDivisionPolitica;
	}

	/**
	 * @return the divisionPoliticaAvaluo
	 */
	public DivisionPolitica getDivisionPoliticaAvaluo() {
		return divisionPoliticaAvaluo;
	}

	/**
	 * @param divisionPoliticaAvaluo
	 *            the divisionPoliticaAvaluo to set
	 */
	public void setDivisionPoliticaAvaluo(
			DivisionPolitica divisionPoliticaAvaluo) {
		this.divisionPoliticaAvaluo = divisionPoliticaAvaluo;
	}

	/**
	 * @return the avaluoEstadoBien
	 */
	public String getAvaluoEstadoBien() {
		return avaluoEstadoBien;
	}

	/**
	 * @param avaluoEstadoBien
	 *            the avaluoEstadoBien to set
	 */
	public void setAvaluoEstadoBien(String avaluoEstadoBien) {
		this.avaluoEstadoBien = avaluoEstadoBien;
	}

	/**
	 * @return the avaluoTipoBien
	 */
	public String getAvaluoTipoBien() {
		return avaluoTipoBien;
	}

	/**
	 * @param avaluoTipoBien
	 *            the avaluoTipoBien to set
	 */
	public void setAvaluoTipoBien(String avaluoTipoBien) {
		this.avaluoTipoBien = avaluoTipoBien;
	}

	/**
	 * @return the avaluoSector
	 */
	public String getAvaluoSector() {
		return avaluoSector;
	}

	/**
	 * @param avaluoSector
	 *            the avaluoSector to set
	 */
	public void setAvaluoSector(String avaluoSector) {
		this.avaluoSector = avaluoSector;
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
	 * @return the vendedorTelf
	 */
	public String getVendedorTelf() {
		return vendedorTelf;
	}

	/**
	 * @param vendedorTelf
	 *            the vendedorTelf to set
	 */
	public void setVendedorTelf(String vendedorTelf) {
		this.vendedorTelf = vendedorTelf;
	}

	/**
	 * @return the vendedorCelular
	 */
	public String getVendedorCelular() {
		return vendedorCelular;
	}

	/**
	 * @param vendedorCelular
	 *            the vendedorCelular to set
	 */
	public void setVendedorCelular(String vendedorCelular) {
		this.vendedorCelular = vendedorCelular;
	}

	/**
	 * @return the vendedorDireccion
	 */
	public String getVendedorDireccion() {
		return vendedorDireccion;
	}

	/**
	 * @param vendedorDireccion
	 *            the vendedorDireccion to set
	 */
	public void setVendedorDireccion(String vendedorDireccion) {
		this.vendedorDireccion = vendedorDireccion;
	}

	/**
	 * @return the vendedorBeneficiarioCuenta
	 */
	public String getVendedorBeneficiarioCuenta() {
		return vendedorBeneficiarioCuenta;
	}

	/**
	 * @param vendedorBeneficiarioCuenta
	 *            the vendedorBeneficiarioCuenta to set
	 */
	public void setVendedorBeneficiarioCuenta(String vendedorBeneficiarioCuenta) {
		this.vendedorBeneficiarioCuenta = vendedorBeneficiarioCuenta;
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
	 * @return the vendedorNumeroCuenta
	 */
	public String getVendedorNumeroCuenta() {
		return vendedorNumeroCuenta;
	}

	/**
	 * @param vendedorNumeroCuenta
	 *            the vendedorNumeroCuenta to set
	 */
	public void setVendedorNumeroCuenta(String vendedorNumeroCuenta) {
		this.vendedorNumeroCuenta = vendedorNumeroCuenta;
	}

	/**
	 * @return the vendedorDivisionPolitica
	 */
	public String getVendedorDivisionPolitica() {
		return vendedorDivisionPolitica;
	}

	/**
	 * @param vendedorDivisionPolitica
	 *            the vendedorDivisionPolitica to set
	 */
	public void setVendedorDivisionPolitica(String vendedorDivisionPolitica) {
		this.vendedorDivisionPolitica = vendedorDivisionPolitica;
	}

	/**
	 * @return the vendedorTipoDocumento
	 */
	public String getVendedorTipoDocumento() {
		return vendedorTipoDocumento;
	}

	/**
	 * @param vendedorTipoDocumento
	 *            the vendedorTipoDocumento to set
	 */
	public void setVendedorTipoDocumento(String vendedorTipoDocumento) {
		this.vendedorTipoDocumento = vendedorTipoDocumento;
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
	 * @return the divisionPoliticaVendedor
	 */
	public DivisionPolitica getDivisionPoliticaVendedor() {
		return divisionPoliticaVendedor;
	}

	/**
	 * @param divisionPoliticaVendedor
	 *            the divisionPoliticaVendedor to set
	 */
	public void setDivisionPoliticaVendedor(
			DivisionPolitica divisionPoliticaVendedor) {
		this.divisionPoliticaVendedor = divisionPoliticaVendedor;
	}

	/**
	 * @return the vendedorInstitucionFinanciera
	 */
	public InstitucionFinanciera getVendedorInstitucionFinanciera() {
		return vendedorInstitucionFinanciera;
	}

	/**
	 * @param vendedorInstitucionFinanciera
	 *            the vendedorInstitucionFinanciera to set
	 */
	public void setVendedorInstitucionFinanciera(
			InstitucionFinanciera vendedorInstitucionFinanciera) {
		this.vendedorInstitucionFinanciera = vendedorInstitucionFinanciera;
	}

	/**
	 * @return the vendedorTelfTrabajo
	 */
	public String getVendedorTelfTrabajo() {
		return vendedorTelfTrabajo;
	}

	/**
	 * @param vendedorTelfTrabajo
	 *            the vendedorTelfTrabajo to set
	 */
	public void setVendedorTelfTrabajo(String vendedorTelfTrabajo) {
		this.vendedorTelfTrabajo = vendedorTelfTrabajo;
	}

	/**
	 * @return the vendedorTipoPersona
	 */
	public String getVendedorTipoPersona() {
		return vendedorTipoPersona;
	}

	/**
	 * @param vendedorTipoPersona
	 *            the vendedorTipoPersona to set
	 */
	public void setVendedorTipoPersona(String vendedorTipoPersona) {
		this.vendedorTipoPersona = vendedorTipoPersona;
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
	 * @return the agenciaReceptora
	 */
	public Agencia getAgenciaReceptora() {
		return agenciaReceptora;
	}

	/**
	 * @param agenciaReceptora
	 *            the agenciaReceptora to set
	 */
	public void setAgenciaReceptora(Agencia agenciaReceptora) {
		this.agenciaReceptora = agenciaReceptora;
	}

	/**
	 * @return the agenciaEmisora
	 */
	public Agencia getAgenciaEmisora() {
		return agenciaEmisora;
	}

	/**
	 * @param agenciaEmisora
	 *            the agenciaEmisora to set
	 */
	public void setAgenciaEmisora(Agencia agenciaEmisora) {
		this.agenciaEmisora = agenciaEmisora;
	}

	/**
	 * @return the coeficienteSeguroDesgravamen
	 */
	public BigDecimal getCoeficienteSeguroDesgravamen() {
		return coeficienteSeguroDesgravamen;
	}

	/**
	 * @param coeficienteSeguroDesgravamen
	 *            the coeficienteSeguroDesgravamen to set
	 */
	public void setCoeficienteSeguroDesgravamen(
			BigDecimal coeficienteSeguroDesgravamen) {
		this.coeficienteSeguroDesgravamen = coeficienteSeguroDesgravamen;
	}

	/**
	 * @return the cuotaSeguroDesgravamen
	 */
	public BigDecimal getCuotaSeguroDesgravamen() {
		return cuotaSeguroDesgravamen;
	}

	/**
	 * @param cuotaSeguroDesgravamen
	 *            the cuotaSeguroDesgravamen to set
	 */
	public void setCuotaSeguroDesgravamen(BigDecimal cuotaSeguroDesgravamen) {
		this.cuotaSeguroDesgravamen = cuotaSeguroDesgravamen;
	}

	/**
	 * @return the coeficienteSeguroIncendios
	 */
	public BigDecimal getCoeficienteSeguroIncendios() {
		return coeficienteSeguroIncendios;
	}

	/**
	 * @param coeficienteSeguroIncendios
	 *            the coeficienteSeguroIncendios to set
	 */
	public void setCoeficienteSeguroIncendios(
			BigDecimal coeficienteSeguroIncendios) {
		this.coeficienteSeguroIncendios = coeficienteSeguroIncendios;
	}

	/**
	 * @return the cuotaSeguroIncendios
	 */
	public BigDecimal getCuotaSeguroIncendios() {
		return cuotaSeguroIncendios;
	}

	/**
	 * @param cuotaSeguroIncendios
	 *            the cuotaSeguroIncendios to set
	 */
	public void setCuotaSeguroIncendios(BigDecimal cuotaSeguroIncendios) {
		this.cuotaSeguroIncendios = cuotaSeguroIncendios;
	}

	/**
	 * @return the maxMonto
	 */
	public BigDecimal getMaxMonto() {
		return maxMonto;
	}

	/**
	 * @param maxMonto
	 *            the maxMonto to set
	 */
	public void setMaxMonto(BigDecimal maxMonto) {
		this.maxMonto = maxMonto;
	}

	/**
	 * @return the maxPlazo
	 */
	public Integer getMaxPlazo() {
		return maxPlazo;
	}

	/**
	 * @param maxPlazo
	 *            the maxPlazo to set
	 */
	public void setMaxPlazo(Integer maxPlazo) {
		this.maxPlazo = maxPlazo;
	}

	/**
	 * @return the maxInteres
	 */
	public BigDecimal getMaxInteres() {
		return maxInteres;
	}

	/**
	 * @param maxInteres
	 *            the maxInteres to set
	 */
	public void setMaxInteres(BigDecimal maxInteres) {
		this.maxInteres = maxInteres;
	}

	/**
	 * @return the maxCuota
	 */
	public BigDecimal getMaxCuota() {
		return maxCuota;
	}

	/**
	 * @param maxCuota
	 *            the maxCuota to set
	 */
	public void setMaxCuota(BigDecimal maxCuota) {
		this.maxCuota = maxCuota;
	}

	/**
	 * @return the maxCoeficienteSeguroDesgravamen
	 */
	public BigDecimal getMaxCoeficienteSeguroDesgravamen() {
		return maxCoeficienteSeguroDesgravamen;
	}

	/**
	 * @param maxCoeficienteSeguroDesgravamen
	 *            the maxCoeficienteSeguroDesgravamen to set
	 */
	public void setMaxCoeficienteSeguroDesgravamen(
			BigDecimal maxCoeficienteSeguroDesgravamen) {
		this.maxCoeficienteSeguroDesgravamen = maxCoeficienteSeguroDesgravamen;
	}

	/**
	 * @return the maxCoeficienteSeguroIncendios
	 */
	public BigDecimal getMaxCoeficienteSeguroIncendios() {
		return maxCoeficienteSeguroIncendios;
	}

	/**
	 * @param maxCoeficienteSeguroIncendios
	 *            the maxCoeficienteSeguroIncendios to set
	 */
	public void setMaxCoeficienteSeguroIncendios(
			BigDecimal maxCoeficienteSeguroIncendios) {
		this.maxCoeficienteSeguroIncendios = maxCoeficienteSeguroIncendios;
	}

	/**
	 * @return the maxSeguroDesgravamen
	 */
	public BigDecimal getMaxSeguroDesgravamen() {
		return maxSeguroDesgravamen;
	}

	/**
	 * @param maxSeguroDesgravamen
	 *            the maxSeguroDesgravamen to set
	 */
	public void setMaxSeguroDesgravamen(BigDecimal maxSeguroDesgravamen) {
		this.maxSeguroDesgravamen = maxSeguroDesgravamen;
	}

	/**
	 * @return the maxSeguroIncendios
	 */
	public BigDecimal getMaxSeguroIncendios() {
		return maxSeguroIncendios;
	}

	/**
	 * @param maxSeguroIncendios
	 *            the maxSeguroIncendios to set
	 */
	public void setMaxSeguroIncendios(BigDecimal maxSeguroIncendios) {
		this.maxSeguroIncendios = maxSeguroIncendios;
	}

	/**
	 * @return the cuotaTotal
	 */
	public BigDecimal getCuotaTotal() {

		try {
			return (getCuoapr().add(cuotaSeguroDesgravamen)
					.add(cuotaSeguroIncendios)).setScale(2,
					BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			return BigDecimal.valueOf(0);
		}

	}

	/**
	 * @param cuotaTotal
	 *            the cuotaTotal to set
	 */
	public void setCuotaTotal(BigDecimal cuotaTotal) {
		this.cuotaTotal = cuotaTotal;
	}

	/**
	 * @return the cuotaSeguroConstruccion
	 */
	public BigDecimal getCuotaSeguroConstruccion() {
		return cuotaSeguroConstruccion;
	}

	/**
	 * @param cuotaSeguroConstruccion
	 *            the cuotaSeguroConstruccion to set
	 */
	public void setCuotaSeguroConstruccion(BigDecimal cuotaSeguroConstruccion) {
		this.cuotaSeguroConstruccion = cuotaSeguroConstruccion;
	}

	/**
	 * @return the coeficienteSeguroConstruccion
	 */
	public BigDecimal getCoeficienteSeguroConstruccion() {
		return coeficienteSeguroConstruccion;
	}

	/**
	 * @param coeficienteSeguroConstruccion
	 *            the coeficienteSeguroConstruccion to set
	 */
	public void setCoeficienteSeguroConstruccion(
			BigDecimal coeficienteSeguroConstruccion) {
		this.coeficienteSeguroConstruccion = coeficienteSeguroConstruccion;
	}

	/**
	 * @return the maxSeguroConstruccion
	 */
	public BigDecimal getMaxSeguroConstruccion() {
		return maxSeguroConstruccion;
	}

	/**
	 * @param maxSeguroConstruccion
	 *            the maxSeguroConstruccion to set
	 */
	public void setMaxSeguroConstruccion(BigDecimal maxSeguroConstruccion) {
		this.maxSeguroConstruccion = maxSeguroConstruccion;
	}

	/**
	 * @return the maxCoeficienteSeguroConstruccion
	 */
	public BigDecimal getMaxCoeficienteSeguroConstruccion() {
		return maxCoeficienteSeguroConstruccion;
	}

	/**
	 * @param maxCoeficienteSeguroConstruccion
	 *            the maxCoeficienteSeguroConstruccion to set
	 */
	public void setMaxCoeficienteSeguroConstruccion(
			BigDecimal maxCoeficienteSeguroConstruccion) {
		this.maxCoeficienteSeguroConstruccion = maxCoeficienteSeguroConstruccion;
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
	 * @return the numeroDeposito
	 */
	public Integer getNumeroDeposito() {
		return numeroDeposito;
	}

	/**
	 * @param numeroDeposito
	 *            the numeroDeposito to set
	 */
	public void setNumeroDeposito(Integer numeroDeposito) {
		this.numeroDeposito = numeroDeposito;
	}

	/**
	 * @return the traLegSol
	 */
	public String getTraLegSol() {
		return traLegSol;
	}

	/**
	 * @param traLegSol
	 *            the traLegSol to set
	 */
	public void setTraLegSol(String traLegSol) {
		this.traLegSol = traLegSol;
	}

	/**
	 * @return the numeroTotalDesembolso
	 */
	public Integer getNumeroTotalDesembolso() {
		return numeroTotalDesembolso;
	}

	/**
	 * @param numeroTotalDesembolso
	 *            the numeroTotalDesembolso to set
	 */
	public void setNumeroTotalDesembolso(Integer numeroTotalDesembolso) {
		this.numeroTotalDesembolso = numeroTotalDesembolso;
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
	 * @return the callePrincipalTerreno
	 */

	public String getCallePrincipalTerreno() {
		return callePrincipalTerreno;
	}

	/**
	 * @param callePrincipalTerreno
	 *            the callePrincipalTerreno to set
	 */

	public void setCallePrincipalTerreno(String callePrincipalTerreno) {
		this.callePrincipalTerreno = callePrincipalTerreno;
	}

	/**
	 * @return the calleSecundariaTerreno
	 */

	public String getCalleSecundariaTerreno() {
		return calleSecundariaTerreno;
	}

	/**
	 * @param calleSecundariaTerreno
	 *            the calleSecundariaTerreno to set
	 */

	public void setCalleSecundariaTerreno(String calleSecundariaTerreno) {
		this.calleSecundariaTerreno = calleSecundariaTerreno;
	}

	/**
	 * @return the urbanizacionTerreno
	 */

	public String getUrbanizacionTerreno() {
		return urbanizacionTerreno;
	}

	/**
	 * @param urbanizacionTerreno
	 *            the urbanizacionTerreno to set
	 */

	public void setUrbanizacionTerreno(String urbanizacionTerreno) {
		this.urbanizacionTerreno = urbanizacionTerreno;
	}

	/**
	 * @return the nroLoteTerreno
	 */

	public String getNroLoteTerreno() {
		return nroLoteTerreno;
	}

	/**
	 * @param nroLoteTerreno
	 *            the nroLoteTerreno to set
	 */

	public void setNroLoteTerreno(String nroLoteTerreno) {
		this.nroLoteTerreno = nroLoteTerreno;
	}

	/**
	 * @return the tipoCtaPeticionario
	 */

	public String getTipoCtaPeticionario() {
		return tipoCtaPeticionario;
	}

	/**
	 * @param tipoCtaPeticionario
	 *            the tipoCtaPeticionario to set
	 */

	public void setTipoCtaPeticionario(String tipoCtaPeticionario) {
		this.tipoCtaPeticionario = tipoCtaPeticionario;
	}

	/**
	 * @return the numeroCtaPeticionario
	 */

	public String getNumeroCtaPeticionario() {
		return numeroCtaPeticionario;
	}

	/**
	 * @param numeroCtaPeticionario
	 *            the numeroCtaPeticionario to set
	 */

	public void setNumeroCtaPeticionario(String numeroCtaPeticionario) {
		this.numeroCtaPeticionario = numeroCtaPeticionario;
	}

	/**
	 * @return the codigoIfiPeticionario
	 */

	public String getCodigoIfiPeticionario() {
		return codigoIfiPeticionario;
	}

	/**
	 * @param codigoIfiPeticionario
	 *            the codigoIfiPeticionario to set
	 */

	public void setCodigoIfiPeticionario(String codigoIfiPeticionario) {
		this.codigoIfiPeticionario = codigoIfiPeticionario;
	}

	/**
	 * @return the nombreBeneficiarioPeticionario
	 */

	public String getNombreBeneficiarioPeticionario() {
		return nombreBeneficiarioPeticionario;
	}

	/**
	 * @param nombreBeneficiarioPeticionario
	 *            the nombreBeneficiarioPeticionario to set
	 */

	public void setNombreBeneficiarioPeticionario(
			String nombreBeneficiarioPeticionario) {
		this.nombreBeneficiarioPeticionario = nombreBeneficiarioPeticionario;
	}

	/**
	 * @return the institucionFinancieraPeticionario
	 */

	public InstitucionFinanciera getInstitucionFinancieraPeticionario() {
		return institucionFinancieraPeticionario;
	}

	/**
	 * @param institucionFinancieraPeticionario
	 *            the institucionFinancieraPeticionario to set
	 */

	public void setInstitucionFinancieraPeticionario(
			InstitucionFinanciera institucionFinancieraPeticionario) {
		this.institucionFinancieraPeticionario = institucionFinancieraPeticionario;
	}

	/**
	 * @return the valorEstimadoTerreno
	 */

	public BigDecimal getValorEstimadoTerreno() {
		return valorEstimadoTerreno;
	}

	/**
	 * @param valorEstimadoTerreno
	 *            the valorEstimadoTerreno to set
	 */

	public void setValorEstimadoTerreno(BigDecimal valorEstimadoTerreno) {
		this.valorEstimadoTerreno = valorEstimadoTerreno;
	}

	/**
	 * @return the valorEstimadoConstruccion
	 */

	public BigDecimal getValorEstimadoConstruccion() {
		return valorEstimadoConstruccion;
	}

	/**
	 * @param valorEstimadoConstruccion
	 *            the valorEstimadoConstruccion to set
	 */

	public void setValorEstimadoConstruccion(
			BigDecimal valorEstimadoConstruccion) {
		this.valorEstimadoConstruccion = valorEstimadoConstruccion;
	}

	/**
	 * @return the experienciaNegocio
	 */
	public String getExperienciaNegocio() {
		return experienciaNegocio;
	}

	/**
	 * @param experienciaNegocio
	 *            the experienciaNegocio to set
	 */
	public void setExperienciaNegocio(String experienciaNegocio) {
		this.experienciaNegocio = experienciaNegocio;
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
	 * @return the buroInmueble
	 */

	public String getBuroInmueble() {
		return buroInmueble;
	}

	/**
	 * @param buroInmueble
	 *            the buroInmueble to set
	 */

	public void setBuroInmueble(String buroInmueble) {
		this.buroInmueble = buroInmueble;
	}

	/**
	 * @return the listaDetalleAdicional
	 */
	public List<DatosPersonalesUsuario> getListaDetalleAdicional() {
		return listaDetalleAdicional;
	}

	/**
	 * @param listaDetalleAdicional
	 *            the listaDetalleAdicional to set
	 */
	public void setListaDetalleAdicional(
			List<DatosPersonalesUsuario> listaDetalleAdicional) {
		this.listaDetalleAdicional = listaDetalleAdicional;
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
	 * @param areaEdificacion
	 *            the areaEdificacion to set
	 */
	public void setAreaEdificacion(BigDecimal areaEdificacion) {
		this.areaEdificacion = areaEdificacion;
	}

	/**
	 * @return the areaEdificacion
	 */
	public BigDecimal getAreaEdificacion() {
		return areaEdificacion;
	}

	/**
	 * @param areaTerreno
	 *            the areaTerreno to set
	 */
	public void setAreaTerreno(BigDecimal areaTerreno) {
		this.areaTerreno = areaTerreno;
	}

	/**
	 * @return the areaTerreno
	 */
	public BigDecimal getAreaTerreno() {
		return areaTerreno;
	}

	/**
	 * @param numeroHabitaciones
	 *            the numeroHabitaciones to set
	 */
	public void setNumeroHabitaciones(Integer numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	/**
	 * @return the numeroHabitaciones
	 */
	public Integer getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	/**
	 * @param viviendaPropia
	 *            the viviendaPropia to set
	 */
	public void setViviendaPropia(String viviendaPropia) {
		this.viviendaPropia = viviendaPropia;
	}

	/**
	 * @return the viviendaPropia
	 */
	public String getViviendaPropia() {
		return viviendaPropia;
	}

	public BigDecimal getValorPresupuesto() {
		return valorPresupuesto;
	}

	public void setValorPresupuesto(BigDecimal valorPresupuesto) {
		this.valorPresupuesto = valorPresupuesto;
	}

	/**
	 * @param detalleSolicitudIfi
	 *            the detalleSolicitudIfi to set
	 */
	public void setDetalleSolicitudIfi(DetalleSolicitudIfi detalleSolicitudIfi) {
		this.detalleSolicitudIfi = detalleSolicitudIfi;
	}

	/**
	 * @return the detalleSolicitudIfi
	 */
	public DetalleSolicitudIfi getDetalleSolicitudIfi() {
		return detalleSolicitudIfi;
	}

	/**
	 * @return the operacionesCanceladas
	 */
	public OperacionesCanceladas getOperacionCancelada() {
		return operacionCancelada;
	}

	/**
	 * @param operacionesCanceladas
	 *            the operacionesCanceladas to set
	 */
	public void setOperacionCancelada(
			OperacionesCanceladas operacionesCanceladas) {
		this.operacionCancelada = operacionesCanceladas;
	}

}