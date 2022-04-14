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

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ec.gov.iess.creditos.enumeracion.DestinoComercialEnum;
import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.modelo.dto.CuentaBancariaAnterior;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.hl.modelo.Afiliado;
import ec.gov.iess.hl.modelo.Empleador;

/**
 * Clase de modelo de persistena de la cabecera del credito.
 * 
 * @version 1.0
 * @author cvillarreal 03/10/2011
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "Kscretcreditos")
@NamedQueries({ @NamedQuery(name = "Prestamo.consultarTodos", query = "select o from Prestamo o"),
		// consulta creditos vigentes
		@NamedQuery(name = "Prestamo.vigente", query = "select o from Prestamo o where "
				+ "o.numafi=:cedula "
				+ "and o.estadoPrestamo.obtestpre in (:estadoCredito) "),

		// consulta creditos cancelados
		@NamedQuery(name = "Prestamo.cancelado", 
		query = "select o from Prestamo o where o.numafi=:cedula and o.estadoPrestamo.obtestpre in (1) ORDER BY o.fecpreafi DESC "),

		// consulta creditos quirografarios vigentes que no han sido cancelados
		// ni rechazados
		@NamedQuery(name = "Prestamo.consultarQuirografariosVigentes", query = "select o from Prestamo o where "
				+ "o.numafi=:cedula and o.estadoPrestamo.obtestpre in (:estadoCredito) "
				+ "and o.prestamoPk.codprecla in (:tipoCredito) order by o.fecpreafi desc"),

		// consulta creditos quirografarios vigentes para validaciones de
		// hipotecarios
		@NamedQuery(name = "Prestamo.consultarQuirografariosVigentesPH", query = "select o from Prestamo o where "
				+ "o.numafi=:cedula and o.estadoPrestamo.obtestpre in (:estadoCredito) "
				+ "and o.prestamoPk.codprecla in (:tipoCredito)"),

		// credito con el mismo numero de cuenta
		@NamedQuery(name = "Prestamo.verificarCreditoCuentabancaria", query = "select o from Prestamo o where "
				+ "o.rucempinsfin=:idInstitucionFinanciera and o.tipoCuenta = :idTipoCuenta "
				+ "and o.numctaban = :numeroCuentaBancaria and o.estadoPrestamo.obtestpre not in "
				+ "(:estadoCredito)"),
		@NamedQuery(name = "Prestamo.consultarPorCedula", query = "select o from Prestamo o where o.numafi = :cedula"),
		// consultar creditos vigentes por cedula
		@NamedQuery(name = "Prestamo.listaPrestamoCuotaDividendo", query = "select o from Prestamo o where o.numafi = :cedula and o.estadoPrestamo.codestpre = 'VIG'"),

		// consultar creditos quirografarios - pendientes de aprobacion (PDA)
		// por fecha
		// 10/08/2011 - cambio andres cantos
		// and o.fecpreafi BETWEEN :fecha_ant AND :fecha_post
		@NamedQuery(name = "Prestamo.listaPrestamospendientesaprobacion", query = "select o from Prestamo o where o.estadoPrestamo.codestpre = 'PDA' and trunc(o.fecpreafi) >= :fecha_ant AND  trunc(o.fecpreafi) <= :fecha_post order by o.afiliado.apenomafi asc "),

		// Cambio para buscar prestamos en PDA por cedula tambien
		@NamedQuery(name = "Prestamo.listaPrestamosPDACedula", query = "select o from Prestamo o where o.estadoPrestamo.codestpre = 'PDA' AND o.numafi = :cedula order by o.afiliado.apenomafi asc "),
		// fin cambio
		
		@NamedQuery(name = "Prestamo.contarPorEstadoAnio", query="SELECT COUNT(*) FROM Prestamo p WHERE p.numafi = :numafi and p.estadoPrestamo.codestpre = :estado and p.aniper = :anio "),
		
		@NamedQuery(name = "Prestamo.contarPorEstadoFeccanpreAnio", query="SELECT COUNT(*) FROM Prestamo p WHERE p.numafi = :numafi and p.estadoPrestamo.codestpre = :estado and p.aniper = :anio and p.feccanpre >= TO_DATE(:feccanpre, 'DD/MM/YYYY') "),

		// Consulta para prestamos vigentes por fecha y estado
		@NamedQuery(name = "Prestamo.listaPorEstadoFechaPrecalifica", query = "SELECT p FROM Prestamo p WHERE p.numafi = :numafi AND p.estadoPrestamo.codestpre = :codestpre AND p.fecpreafi < TO_DATE(:fecpreafi,'DD/MM/YYYY')"),
		
		@NamedQuery(name = "Prestamo.contarPorEstadoTipoPrestamo", query = "SELECT COUNT(*) FROM Prestamo p WHERE p.numafi = :numafi and p.estadoPrestamo.codestpre = :estado and p.prestamoPk.codpretip = :codpretip "),

		@NamedQuery(name = "Prestamo.consultarPorTipoSolicitud", query = "select o from Prestamo o where o.codtipsolser = :codSolicitud AND o.numsolser = :numSolicitud"),
		
		@NamedQuery(name = "Prestamo.consultarPorNumOpSAC", query = "select o from Prestamo o where o.numOperacionSAC = :numOpSAC"),
		//Consultar por numero de novacion
		@NamedQuery(name = "Prestamo.consultarPorNumCancelNov", query = "select o from Prestamo o where o.numprecannov = :numprecannov AND o.numafi = :identificacion and o.estadoPrestamo.obtestpre in (2)")
       

		

})
public class Prestamo implements Serializable {

	@EmbeddedId
	private PrestamoPk prestamoPk;

	@Column(nullable = false)
	private Long aniper;
	private String coddivpol;
	@Column(nullable = false)
	private String codparpre;

	@Column(nullable = false)
	private Long codregadm;
	private String codsuc;

	@Column(nullable = false, name = "codtipcue")
	@Enumerated(EnumType.STRING)
	private TipoCuenta tipoCuenta;

	private Long codtipsolser;

	@Column(nullable = false)
	private Date fecfinpre;

	@Column(nullable = false)
	private Date fecinipre;

	@Column(nullable = false)
	private Date fecpreafi;

	@Column(name = "FORMA_PAGO")
	private Long formaPago;

	@Column(nullable = false)
	private Double intdiagrc;

	@Column(nullable = false)
	private Long mesper;

	@Column(nullable = false)
	private Double mntpre;

	@Column(nullable = false)
	private String numafi;

	@Column(nullable = false)
	private String numctaban;

	private Long numsolser;

	private String obsanupre;

	@Column(nullable = false)
	private String penoafi;

	@Column(nullable = false)
	private Long plzmes;

	@Column(nullable = false)
	private BigDecimal prisegsal;
	// private String rucemp;
	@Column(nullable = false)
	private Double tasint;

	@Column(nullable = false)
	private String tipper;

	@Column(nullable = false)
	private Double valsalcap;

	@Column(nullable = false)
	private Double valsegsal;

	@Column(nullable = false)
	private Double valtotdiv;

	@Column(name = "CR_REG_CIVIL")
	private String validacionRegistroCivil;

	private String codfun;
	private Date fectracre;
	private String claprerea;
	private Date feccanpre;
	private Date feccalintpergra;
	private Long numprecannov;
	private Double valliqnov;

	@Transient
	private Long nut;

	
	
	@Transient
	private Long diasMora;
	
	@Transient
	private boolean cumpleMontoPagado;
	
	@Transient
	private boolean cumpleCuotasPagadas;
	
	@Transient
	private String porcentajeComprometer;
	
	@Transient
	private boolean cumpleSaldoEmpleador;
	//REQ-617 9-23-21
	@Transient
	private boolean cumpleNumeroNovacion;
	//REQ-617	
	/**
	 * Carlos Bastidas: INC-6047 se agrega el numero de liquidacion en la
	 * cabecera del credito"
	 */
	private Long numliqprenov;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODESTPRE", referencedColumnName = "CODESTPRE")
	private EstadoPrestamo estadoPrestamo;

	@Column(nullable = false)
	private String rucempinsfin;
	private String rucemp;

	@OneToMany(mappedBy = "prestamo")
	@OrderBy("dividendoPrestamoPk.numdiv")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<DividendoPrestamo> dividendosPrestamo;

	@ManyToOne
	@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private ClasePrestamo clasePrestamo;

	// Incidente - 13346 datos para consulta PQ-fraudes
	// Cambio prestamo 15/08/2011 - andres cantos
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUMAFI", referencedColumnName = "NUMAFI", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private Afiliado afiliado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RUCEMPINSFIN", referencedColumnName = "RUCEMP", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private Empleador institucionfinanciera;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RUCEMP", referencedColumnName = "NUMRUC", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private ParametrosCorporativosSri kspcotsri;

	@Transient
	private String estiloCSS;

	@Transient
	private CuentaBancariaAnterior cuentaBancariaAnterior;

	// INC-2272 Pensiones Alimenticias
	@Transient
	private TipoPrestamo tipoPrestamo;
	
	// Indica si el prestamo focalizado puede ser anulado
	@Transient
	private boolean permiteAnular;

	// INC-2148 Refugiados
	@Column(name = "CR_NUM_PAS_AFILIADO", nullable = true)
	private String numeroVisaPasaporteAfiliado;

	@Column(name = "CR_TIPO_BENEFICIARIO", nullable = true)
	private String tipoBeneficiario;

	@Column(name = "CR_ID_PROVEEDOR", nullable = true)
	private Long idProveedor;
	
	@Column(name = "CR_SISTAMORTIZACION", nullable = true)
	private String tipoTablaAmortizacion;
	
	// Bandera para identificar los prestamos parametrizados con nuevas tasas de interes
	@Column(name = "CR_PARAMETRIZACION")
	private Long parametrizacion;
	
	// Validacion para estados de prestamos
	@Column(name = "CR_VALIDACION")
	private Long validacion;	

	// Bandera para prestamos emergentes
	@Column(name = "CR_CREDITO_ESPECIAL")
	private Long creditoEspecial;
	
	// Numero de reserva para prestamos turisticos
	@Column(name = "CR_NUMERO_RESERVA")
	private String numeroReserva;

	@Column(name = "CR_OPERACIONSAC")
	private Long numOperacionSAC;
	
	@Column(name = "CR_ESTADOPESAC")
	private String estadoSAC;
	
	

	public ParametrosCorporativosSri getKspcotsri() {
		return kspcotsri;
	}

	public void setKspcotsri(final ParametrosCorporativosSri kspcotsri) {
		this.kspcotsri = kspcotsri;
	}

	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(final Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public Empleador getInstitucionfinanciera() {
		return institucionfinanciera;
	}

	public void setInstitucionfinanciera(final Empleador institucionfinanciera) {
		this.institucionfinanciera = institucionfinanciera;
	}

	// fin cambio 15/08/2011
	public Prestamo() {
	}

	/**
	 * @return the creditoPk
	 */
	public PrestamoPk getCreditoPk() {
		return prestamoPk;
	}

	/**
	 * @param creditoPk
	 *            the creditoPk to set
	 */
	public void setCreditoPk(final PrestamoPk creditoPk) {
		this.prestamoPk = creditoPk;
	}

	/**
	 * @return the aniper
	 */
	public Long getAniper() {
		return aniper;
	}

	/**
	 * @param aniper
	 *            the aniper to set
	 */
	public void setAniper(final Long aniper) {
		this.aniper = aniper;
	}

	/**
	 * @return the coddivpol
	 */
	public String getCoddivpol() {
		return coddivpol;
	}

	/**
	 * @param coddivpol
	 *            the coddivpol to set
	 */
	public void setCoddivpol(final String coddivpol) {
		this.coddivpol = coddivpol;
	}

	/**
	 * @return the codparpre
	 */
	public String getCodparpre() {
		return codparpre;
	}

	/**
	 * @param codparpre
	 *            the codparpre to set
	 */
	public void setCodparpre(final String codparpre) {
		this.codparpre = codparpre;
	}

	/**
	 * @return the codregadm
	 */
	public Long getCodregadm() {
		return codregadm;
	}

	/**
	 * @param codregadm
	 *            the codregadm to set
	 */
	public void setCodregadm(final Long codregadm) {
		this.codregadm = codregadm;
	}

	/**
	 * @return the codsuc
	 */
	public String getCodsuc() {
		return codsuc;
	}

	/**
	 * @param codsuc
	 *            the codsuc to set
	 */
	public void setCodsuc(final String codsuc) {
		this.codsuc = codsuc;
	}

	/**
	 * @return the tipoCuenta
	 */
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(final TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * @return the codtipsolser
	 */
	public Long getCodtipsolser() {
		return codtipsolser;
	}

	/**
	 * @param codtipsolser
	 *            the codtipsolser to set
	 */
	public void setCodtipsolser(final Long codtipsolser) {
		this.codtipsolser = codtipsolser;
	}

	/**
	 * @return the fecfinpre
	 */
	public Date getFecfinpre() {
		return fecfinpre;
	}

	/**
	 * @param fecfinpre
	 *            the fecfinpre to set
	 */
	public void setFecfinpre(final Date fecfinpre) {
		this.fecfinpre = fecfinpre;
	}

	/**
	 * @return the fecinipre
	 */
	public Date getFecinipre() {
		return fecinipre;
	}

	/**
	 * @param fecinipre
	 *            the fecinipre to set
	 */
	public void setFecinipre(final Date fecinipre) {
		this.fecinipre = fecinipre;
	}

	/**
	 * @return the fecpreafi
	 */
	public Date getFecpreafi() {
		return fecpreafi;
	}

	/**
	 * @param fecpreafi
	 *            the fecpreafi to set
	 */
	public void setFecpreafi(final Date fecpreafi) {
		this.fecpreafi = fecpreafi;
	}

	/**
	 * @return the formaPago
	 */
	public Long getFormaPago() {
		return formaPago;
	}

	/**
	 * @param formaPago
	 *            the formaPago to set
	 */
	public void setFormaPago(final Long formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * @return the mesper
	 */
	public Long getMesper() {
		return mesper;
	}

	/**
	 * @param mesper
	 *            the mesper to set
	 */
	public void setMesper(final Long mesper) {
		this.mesper = mesper;
	}

	/**
	 * @return the numafi
	 */
	public String getNumafi() {
		return numafi;
	}

	/**
	 * @param numafi
	 *            the numafi to set
	 */
	public void setNumafi(final String numafi) {
		this.numafi = numafi;
	}

	/**
	 * @return the numctaban
	 */
	public String getNumctaban() {
		return numctaban;
	}

	/**
	 * @param numctaban
	 *            the numctaban to set
	 */
	public void setNumctaban(final String numctaban) {
		this.numctaban = numctaban;
	}

	/**
	 * @return the numsolser
	 */
	public Long getNumsolser() {
		return numsolser;
	}

	/**
	 * @param numsolser
	 *            the numsolser to set
	 */
	public void setNumsolser(final Long numsolser) {
		this.numsolser = numsolser;
	}

	/**
	 * @return the obsanupre
	 */
	public String getObsanupre() {
		return obsanupre;
	}

	/**
	 * @param obsanupre
	 *            the obsanupre to set
	 */
	public void setObsanupre(final String obsanupre) {
		this.obsanupre = obsanupre;
	}

	/**
	 * @return the penoafi
	 */
	public String getPenoafi() {
		return penoafi;
	}

	/**
	 * @param penoafi
	 *            the penoafi to set
	 */
	public void setPenoafi(final String penoafi) {
		this.penoafi = penoafi;
	}

	/**
	 * @return the plzmes
	 */
	public Long getPlzmes() {
		return plzmes;
	}

	/**
	 * @param plzmes
	 *            the plzmes to set
	 */
	public void setPlzmes(final Long plzmes) {
		this.plzmes = plzmes;
	}

	/**
	 * @return the prisegsal
	 */
	public BigDecimal getPrisegsal() {
		return prisegsal;
	}

	/**
	 * @param prisegsal
	 *            the prisegsal to set
	 */
	public void setPrisegsal(final BigDecimal prisegsal) {
		this.prisegsal = prisegsal;
	}

	/**
	 * @return the tipper
	 */
	public String getTipper() {
		return tipper;
	}

	/**
	 * @param tipper
	 *            the tipper to set
	 */
	public void setTipper(final String tipper) {
		this.tipper = tipper;
	}

	/**
	 * @return the valsalcap
	 */
	public Double getValsalcap() {
		return valsalcap;
	}

	/**
	 * @param valsalcap
	 *            the valsalcap to set
	 */
	public void setValsalcap(final Double valsalcap) {
		this.valsalcap = valsalcap;
	}

	/**
	 * @return the intdiagrc
	 */
	public Double getIntdiagrc() {
		return intdiagrc;
	}

	/**
	 * @return the mntpre
	 */
	public Double getMntpre() {
		return mntpre;
	}

	/**
	 * @return the tasint
	 */
	public Double getTasint() {
		return tasint;
	}

	/**
	 * @return the valsegsal
	 */
	public Double getValsegsal() {
		return valsegsal;
	}

	/**
	 * @param intdiagrc
	 *            the intdiagrc to set
	 */
	public void setIntdiagrc(final Double intdiagrc) {
		this.intdiagrc = intdiagrc;
	}

	/**
	 * @param mntpre
	 *            the mntpre to set
	 */
	public void setMntpre(final Double mntpre) {
		this.mntpre = mntpre;
	}

	/**
	 * @param tasint
	 *            the tasint to set
	 */
	public void setTasint(final Double tasint) {
		this.tasint = tasint;
	}

	/**
	 * @param valsegsal
	 *            the valsegsal to set
	 */
	public void setValsegsal(final Double valsegsal) {
		this.valsegsal = valsegsal;
	}

	/**
	 * @return the valtotdiv
	 */
	public Double getValtotdiv() {
		return valtotdiv;
	}

	/**
	 * @param valtotdiv
	 *            the valtotdiv to set
	 */
	public void setValtotdiv(final Double valtotdiv) {
		this.valtotdiv = valtotdiv;
	}

	/**
	 * @return the estadoPrestamo
	 */
	public EstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}

	/**
	 * @param estadoPrestamo
	 *            the estadoPrestamo to set
	 */
	public void setEstadoPrestamo(final EstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	/**
	 * @return the prestamoPk
	 */
	public PrestamoPk getPrestamoPk() {
		return prestamoPk;
	}

	/**
	 * @param prestamoPk
	 *            the prestamoPk to set
	 */
	public void setPrestamoPk(final PrestamoPk prestamoPk) {
		this.prestamoPk = prestamoPk;
	}

	/**
	 * @return the codfun
	 */
	public String getCodfun() {
		return codfun;
	}

	/**
	 * @return the fectracre
	 */
	public Date getFectracre() {
		return fectracre;
	}

	/**
	 * @return the claprerea
	 */
	public String getClaprerea() {
		return claprerea;
	}

	/**
	 * @return the feccanpre
	 */
	public Date getFeccanpre() {
		return feccanpre;
	}

	/**
	 * @return the feccalintpergra
	 */
	public Date getFeccalintpergra() {
		return feccalintpergra;
	}

	/**
	 * @param codfun
	 *            the codfun to set
	 */
	public void setCodfun(final String codfun) {
		this.codfun = codfun;
	}

	/**
	 * @param fectracre
	 *            the fectracre to set
	 */
	public void setFectracre(final Date fectracre) {
		this.fectracre = fectracre;
	}

	/**
	 * @param claprerea
	 *            the claprerea to set
	 */
	public void setClaprerea(final String claprerea) {
		this.claprerea = claprerea;
	}

	/**
	 * @param feccanpre
	 *            the feccanpre to set
	 */
	public void setFeccanpre(final Date feccanpre) {
		this.feccanpre = feccanpre;
	}

	/**
	 * @param feccalintpergra
	 *            the feccalintpergra to set
	 */
	public void setFeccalintpergra(final Date feccalintpergra) {
		this.feccalintpergra = feccalintpergra;
	}

	/**
	 * @return the rucempinsfin
	 */
	public String getRucempinsfin() {
		return rucempinsfin;
	}

	/**
	 * @return the rucemp
	 */
	public String getRucemp() {
		return rucemp;
	}

	/**
	 * @param rucempinsfin
	 *            the rucempinsfin to set
	 */
	public void setRucempinsfin(final String rucempinsfin) {
		this.rucempinsfin = rucempinsfin;
	}

	/**
	 * @param rucemp
	 *            the rucemp to set
	 */
	public void setRucemp(final String rucemp) {
		this.rucemp = rucemp;
	}

	public List<DividendoPrestamo> getDividendosPrestamo() {
		return dividendosPrestamo;
	}

	public void setDividendosPrestamo(final List<DividendoPrestamo> listaDividendos) {
		this.dividendosPrestamo = listaDividendos;
	}

	/**
	 * Returns the value of clasePrestamo.
	 * 
	 * @return clasePrestamo
	 */
	public ClasePrestamo getClasePrestamo() {
		return clasePrestamo;
	}

	/**
	 * Sets the value for clasePrestamo.
	 * 
	 * @param clasePrestamo
	 */
	public void setClasePrestamo(final ClasePrestamo clasePrestamo) {
		this.clasePrestamo = clasePrestamo;
	}

	public Double getValliqnov() {
		return valliqnov;
	}

	public void setValliqnov(final Double valliqnov) {
		this.valliqnov = valliqnov;
	}

	public Long getNumprecannov() {
		return numprecannov;
	}

	public void setNumprecannov(final Long numprecannov) {
		this.numprecannov = numprecannov;
	}

	public Long getNumliqprenov() {
		return numliqprenov;
	}

	public void setNumliqprenov(final Long numliqprenov) {
		this.numliqprenov = numliqprenov;
	}

	public void setValidacionRegistroCivil(final String validacionRegistroCivil) {
		this.validacionRegistroCivil = validacionRegistroCivil;
	}

	public String getValidacionRegistroCivil() {
		return validacionRegistroCivil;
	}

	public void setEstiloCSS(final String estiloCSS) {
		this.estiloCSS = estiloCSS;
	}

	public String getEstiloCSS() {

		if (validacionRegistroCivil != null && validacionRegistroCivil.equals("S")) {
			estiloCSS = "background-color: #f1f6f8;";
		}
		return estiloCSS;
	}

	public void setCuentaBancariaAnterior(final CuentaBancariaAnterior cuentaBancariaAnterior) {
		this.cuentaBancariaAnterior = cuentaBancariaAnterior;
	}

	public CuentaBancariaAnterior getCuentaBancariaAnterior() {
		return cuentaBancariaAnterior;
	}

	/**
	 * @return the tipoPrestamo
	 */
	public TipoPrestamo getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * @param tipoPrestamo
	 *            the tipoPrestamo to set
	 */
	public void setTipoPrestamo(final TipoPrestamo tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	/**
	 * @return the numeroVisaPasaporteAfiliado
	 */
	public String getNumeroVisaPasaporteAfiliado() {
		return numeroVisaPasaporteAfiliado;
	}

	/**
	 * @param numeroVisaPasaporteAfiliado
	 *            the numeroVisaPasaporteAfiliado to set
	 */
	public void setNumeroVisaPasaporteAfiliado(final String numeroVisaPasaporteAfiliado) {
		this.numeroVisaPasaporteAfiliado = numeroVisaPasaporteAfiliado;
	}

	/**
	 * @return the tipoBeneficiario
	 */
	public String getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	/**
	 * @param tipoBeneficiario
	 *            the tipoBeneficiario to set
	 */
	public void setTipoBeneficiario(final String tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}

	/**
	 * @return
	 */
	public Long getIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor
	 */
	public void setIdProveedor(final Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getTipoTablaAmortizacion() {
		return tipoTablaAmortizacion;
	}

	public void setTipoTablaAmortizacion(final String tipoTablaAmortizacion) {
		this.tipoTablaAmortizacion = tipoTablaAmortizacion;
	}

	public Long getParametrizacion() {
		return parametrizacion;
	}

	public void setParametrizacion(final Long parametrizacion) {
		this.parametrizacion = parametrizacion;
	}
	
	/**
	 * @return
	 */
	public Long getValidacion() {
		return validacion;
	}

	/**
	 * @param validacion
	 */
	public void setValidacion(final Long validacion) {
		this.validacion = validacion;
	}	

	public Long getCreditoEspecial() {
		return creditoEspecial;
	}

	public void setCreditoEspecial(final Long creditoEspecial) {
		this.creditoEspecial = creditoEspecial;
	}

	public boolean isPermiteAnular() {
		return permiteAnular;
	}

	public void setPermiteAnular(final boolean permiteAnular) {
		this.permiteAnular = permiteAnular;
	}

	public String getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(final String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public Long getNut() {
		return nut;
	}

	public void setNut(final Long nut) {
		this.nut = nut;
	}

	public Long getDiasMora() {
		return diasMora;
	}

	public void setDiasMora(final Long diasMora) {
		this.diasMora = diasMora;
	}

	public boolean getCumpleMontoPagado() {
		return cumpleMontoPagado;
	}

	public void setCumpleMontoPagado(final boolean cumpleMontoPagado) {
		this.cumpleMontoPagado = cumpleMontoPagado;
	}

	public boolean getCumpleCuotasPagadas() {
		return cumpleCuotasPagadas;
	}

	public void setCumpleCuotasPagadas(final boolean cumpleCuotasPagadas) {
		this.cumpleCuotasPagadas = cumpleCuotasPagadas;
	}

	public String getPorcentajeComprometer() {
		return porcentajeComprometer;
	}

	public void setPorcentajeComprometer(final String porcentajeComprometer) {
		this.porcentajeComprometer = porcentajeComprometer;
	}

	public Long getNumOperacionSAC() {
		return numOperacionSAC;
	}

	public void setNumOperacionSAC(final Long numOperacionSAC) {
		this.numOperacionSAC = numOperacionSAC;
	}

	public String getEstadoSAC() {
		return estadoSAC;
	}

	public void setEstadoSAC(final String estadoSAC) {
		this.estadoSAC = estadoSAC;
	}

	/**
	 * Obtuiene el numero De cancelacion del primarykey
	 * @return
	 */
	public Long getNumCancelacionArmado() {
		final StringBuilder numeroNovar = new StringBuilder();
		numeroNovar.append(prestamoPk.getCodpretip()).append(prestamoPk.getOrdpreafi())
				.append(prestamoPk.getCodprecla()).append(prestamoPk.getNumpreafi());
		return Long.valueOf(numeroNovar.toString());
	}

	/**
	 * 
	 * @return
	 */
    public DestinoComercialEnum getDestinoComercial() {
    	Long codigoProducto=null;
    	if((prestamoPk.getCodpretip()==4 &&  creditoEspecial!=null) || (prestamoPk.getCodpretip()==1 && creditoEspecial!=null) ) {
    		codigoProducto=Long.valueOf(prestamoPk.getCodpretip().toString().concat(creditoEspecial.toString()));
    	}else {
    		codigoProducto=prestamoPk.getCodpretip();
    	}
    	
    	return DestinoComercialEnum.getTiposProductosPq(codigoProducto);
    }
    
    /**
     * Obtener tipo de afiliado
     * @return
     */
	public String getTipoafiliadoSac() {

		switch (prestamoPk.getCodprecla().intValue()) {
		case 20:
			return "1";
		case 21:
			return "2";
		case 22:
			return "3";

		default:
			return "";
		}
	}

	public boolean isCumpleSaldoEmpleador() {
		return cumpleSaldoEmpleador;
	}

	public void setCumpleSaldoEmpleador(boolean cumpleSaldoEmpleador) {
		this.cumpleSaldoEmpleador = cumpleSaldoEmpleador;
	}

	public boolean isCumpleNumeroNovacion() {
		return cumpleNumeroNovacion;
	}
	//REQ617 9-23-21
	public void setCumpleNumeroNovacion(boolean cumpleNumeroNovacion) {
		this.cumpleNumeroNovacion = cumpleNumeroNovacion;
	}
	//
	
}
