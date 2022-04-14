/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.creditos.enumeracion.TipoIngresoEgreso;
import ec.gov.iess.hl.modelo.DivisionPolitica;

/**
 * datos de los solicitantes del prestamos
 * 
 * @author cvillarreal 03/10/2011
 * @author Daniel Cardenas
 * @version 1.0
 * 
 */
@Entity
@Table(name = "CRE_SOLICITANTE_TBL")
@NamedQueries({
		@NamedQuery(name = "SolicitanteCredito.findAll", query = "SELECT o FROM SolicitanteCredito o"),
		@NamedQuery(name = "SolicitanteCredito.findByCedulaTipoYEstadoSolicitud", query = "SELECT o FROM SolicitanteCredito o "
				+ "WHERE o.numafi = :cedula AND "
				+ "o.solicitudCredito.codestsolser NOT IN (:listaEstados) "
				+ "AND o.solicitudCredito.solicitudCreditoPK.codtipsolser = :idTipoSolicitud"),
		@NamedQuery(name = "SolicitanteCredito.encontrarSolicitudesCancelar", query = "SELECT o FROM SolicitanteCredito o "
				+ "WHERE o.numafi = :cedula AND o.solicitudCredito.codestsolser IN (:listaEstados) "
				+ "AND o.solicitudCredito.solicitudCreditoPK.codtipsolser  in (:tiposSolicitud)"),
		@NamedQuery(name = "SolicitanteCredito.encontrarSolicitudesVigentes", query = "SELECT o FROM SolicitanteCredito o "
				+ "WHERE o.numafi = :cedula AND o.solicitudCredito.codestsolser IN (:listaEstados) "
				+ "AND o.solicitudCredito.solicitudCreditoPK.codtipsolser  in (:tiposSolicitud)") })
@SequenceGenerator(name = "CRE_SOLICITANTE_SEQ", sequenceName = "CRE_SOLICITANTE_SEQ", allocationSize = 1)
public class SolicitanteCredito implements Serializable {

	/**
	 * serializacion
	 */
	private static final long serialVersionUID = 387169825286924769L;

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRE_SOLICITANTE_SEQ")
	private Long codsol;

	private String numafi;
	@Column(nullable = false)
	private BigDecimal porend;
	@Column(nullable = false)
	private BigDecimal toddes;
	@Column(nullable = false)
	private BigDecimal toting;

	@Column(name = "CODIVPOL", nullable = false)
	private String coddivpol;

	@Column(name = "MAIAFI")
	private String email;

	@Column(name = "DIRAFI", nullable = false)
	private String direccion;

	@Column(name = "TELAFI")
	private String telefono;

	@Column(name = "CELAFI")
	private String celular;

	@Column(name = "TOTNET")
	private BigDecimal ingnet;

	@Column(name = "VALEND")
	private BigDecimal valcomp;

	@Column(name = "NOMAFI")
	private String nombreApellido;

	@Column(name = "CONFMAIL")
	private String confirmacionEmail;

	@Column(name = "UNILIB")
	private String unionLibre;

	@Column(name = "SEPBIE")
	private String separacionBienes;

	@Column(name = "TELTRA")
	private String telefonoTrabajo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODTIPSOL", referencedColumnName = "CODTIPSOL")
	private TipoSolicitante tipoSolicitante;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER"),
			@JoinColumn(name = "NUMSOLSER", referencedColumnName = "NUMSOLSER") })
	private SolicitudCredito solicitudCredito;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODTIPROLSOL", referencedColumnName = "CODTIPROLSOL")
	private TipoRolSolicitante tipoRolSolicitante;

	@ManyToOne
	@JoinColumn(name = "CODACTECO")
	private ActividadEconomica actividadEconomica;

	@OneToMany(mappedBy = "solicitanteCredito", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SolicitanteIngresoEgreso> ingresoEgresoSolicitanteList;

	@Column(name = "PORPAR", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal porcentajeParticipacion;

	@Column(name = "MONPARORI", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal montoParticipacion;

	@Column(name = "MONPARDEP", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal montoParticipacionDeposito;

	@Column(name = "CUOPARORI", precision = 2, nullable = true)
	@Basic(optional = true)
	private BigDecimal cuotaParticipacion;

	@Transient
	private BigDecimal totalIngresos;

	@Transient
	private BigDecimal totalEgresos;

	@Transient
	private BigDecimal cuotaComprometer;

	@Transient
	private DivisionPolitica divisionPoliticaDomicilio;

	@Column(name = "VALSEGCON")
	private BigDecimal valorSeguroConstruccion;

	@Column(name = "VALSEGDES")
	private BigDecimal valorSeguroDesgravamen;

	@Column(name = "VALSEGINC")
	private BigDecimal valorSeguroIncendios;

	private String tipProD;

	@Column(name = "NUMOPEBANAFI")
	private String numOpBanAfi;

	@Column(name = "CODINSFIN")
	private Long numOpBanIfi;

	private BigDecimal saldoHipotecario;

	@Column(name = "CODESTSOLSER")
	private String estadoCredito;

	@Column(name = "SO_TIPO_INSTITUCION")
	private String soTipoInstitucion;

	@Column(name = "SO_PRESTACION_SEGURO")
	private Long prestacionSeguroJub;

	@Column(name = "SO_PAIS")
	private String pais;

	@Column(name = "SO_ESTADO")
	private String estadoExtranjero;

	@Column(name = "SO_CIUDAD")
	private String ciudad;

	/**
	 * @return the ingresoEgresoSolicitanteList
	 */
	public List<SolicitanteIngresoEgreso> getIngresoEgresoSolicitanteList() {
		return ingresoEgresoSolicitanteList;
	}

	/**
	 * @param ingresoEgresoSolicitanteList
	 *            the ingresoEgresoSolicitanteList to set
	 */
	public void setIngresoEgresoSolicitanteList(List<SolicitanteIngresoEgreso> ingresoEgresoSolicitanteList) {
		this.ingresoEgresoSolicitanteList = ingresoEgresoSolicitanteList;
	}

	public SolicitanteCredito() {

	}

	/**
	 * @return the codsol
	 */
	public Long getCodsol() {
		return codsol;
	}

	/**
	 * @return the numafi
	 */
	public String getNumafi() {
		return numafi;
	}

	/**
	 * @return the porend
	 */
	public BigDecimal getPorend() {
		return porend;
	}

	/**
	 * @return the toddes
	 */
	public BigDecimal getToddes() {
		return toddes;
	}

	/**
	 * @return the toting
	 */
	public BigDecimal getToting() {
		return toting;
	}

	/**
	 * @return the tipoSolicitante
	 */
	public TipoSolicitante getTipoSolicitante() {
		return tipoSolicitante;
	}

	/**
	 * @return the tipoRolSolicitante
	 */
	public TipoRolSolicitante getTipoRolSolicitante() {
		return tipoRolSolicitante;
	}

	/**
	 * @param codsol
	 *            the codsol to set
	 */
	public void setCodsol(Long codsol) {
		this.codsol = codsol;
	}

	/**
	 * @param numafi
	 *            the numafi to set
	 */
	public void setNumafi(String numafi) {
		this.numafi = numafi;
	}

	/**
	 * @param porend
	 *            the porend to set
	 */
	public void setPorend(BigDecimal porend) {
		this.porend = porend;
	}

	/**
	 * @param toddes
	 *            the toddes to set
	 */
	public void setToddes(BigDecimal toddes) {
		this.toddes = toddes;
	}

	/**
	 * @param toting
	 *            the toting to set
	 */
	public void setToting(BigDecimal toting) {
		this.toting = toting;
	}

	/**
	 * @param tipoSolicitante
	 *            the tipoSolicitante to set
	 */
	public void setTipoSolicitante(TipoSolicitante tipoSolicitante) {
		this.tipoSolicitante = tipoSolicitante;
	}

	/**
	 * @param tipoRolSolicitante
	 *            the tipoRolSolicitante to set
	 */
	public void setTipoRolSolicitante(TipoRolSolicitante tipoRolSolicitante) {
		this.tipoRolSolicitante = tipoRolSolicitante;
	}

	/**
	 * @return the coddivpol
	 */
	public String getCoddivpol() {
		return coddivpol;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param coddivpol
	 *            the coddivpol to set
	 */
	public void setCoddivpol(String coddivpol) {
		this.coddivpol = coddivpol;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @param celular
	 *            the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return the solicitudCredito
	 */
	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	/**
	 * @param solicitudCredito
	 *            the solicitudCredito to set
	 */
	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}

	/**
	 * @return the ingnet
	 */
	public BigDecimal getIngnet() {
		return ingnet;
	}

	/**
	 * @return the valcomp
	 */
	public BigDecimal getValcomp() {
		return valcomp;
	}

	/**
	 * @param ingnet
	 *            the ingnet to set
	 */
	public void setIngnet(BigDecimal ingnet) {
		this.ingnet = ingnet;
	}

	/**
	 * @param valcomp
	 *            the valcomp to set
	 */
	public void setValcomp(BigDecimal valcomp) {
		this.valcomp = valcomp;
	}

	/**
	 * @return the totalIngresos
	 */
	public BigDecimal getTotalIngresos() {
		if (totalIngresos == null) {
			totalIngresos = new BigDecimal(0.0);
			for (SolicitanteIngresoEgreso ingreso : this.ingresoEgresoSolicitanteList) {
				if (ingreso.getTipoIngresoEgreso().equals(TipoIngresoEgreso.ING)) {
					totalIngresos = totalIngresos.add(ingreso.getValor());
				}
			}
		}

		return totalIngresos;
	}

	/**
	 * @return the totalEgresos
	 */
	public BigDecimal getTotalEgresos() {
		if (totalEgresos == null) {
			totalEgresos = new BigDecimal(0.0);
			for (SolicitanteIngresoEgreso egreso : this.ingresoEgresoSolicitanteList) {
				if (egreso.getTipoIngresoEgreso().equals(TipoIngresoEgreso.EGR)) {
					totalEgresos = totalEgresos.add(egreso.getValor());
				}
			}
		}

		return totalEgresos;
	}

	/**
	 * @return the cuotaComprometer
	 */
	public BigDecimal getCuotaComprometer() {
		BigDecimal totalNeto = totalIngresos.subtract(getTotalEgresos());
		cuotaComprometer = totalNeto.multiply(this.porend.multiply(BigDecimal.valueOf(0.01)));
		return cuotaComprometer;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getConfirmacionEmail() {
		return confirmacionEmail;
	}

	public void setConfirmacionEmail(String confirmacionEmail) {
		this.confirmacionEmail = confirmacionEmail;
	}

	/**
	 * @return the unionLibre
	 */
	public String getUnionLibre() {
		return unionLibre;
	}

	/**
	 * @param unionLibre
	 *            the unionLibre to set
	 */
	public void setUnionLibre(String unionLibre) {
		this.unionLibre = unionLibre;
	}

	/**
	 * @return the separacionBienes
	 */
	public String getSeparacionBienes() {
		return separacionBienes;
	}

	/**
	 * @param separacionBienes
	 *            the separacionBienes to set
	 */
	public void setSeparacionBienes(String separacionBienes) {
		this.separacionBienes = separacionBienes;
	}

	/**
	 * @return the actividadEconomica
	 */
	public ActividadEconomica getActividadEconomica() {
		return actividadEconomica;
	}

	/**
	 * @param actividadEconomica
	 *            the actividadEconomica to set
	 */
	public void setActividadEconomica(ActividadEconomica actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	/**
	 * @return the divisionPoliticaDomicilio
	 */
	public DivisionPolitica getDivisionPoliticaDomicilio() {
		return divisionPoliticaDomicilio;
	}

	/**
	 * @param divisionPoliticaDomicilio
	 *            the divisionPoliticaDomicilio to set
	 */
	public void setDivisionPoliticaDomicilio(DivisionPolitica divisionPoliticaDomicilio) {
		this.divisionPoliticaDomicilio = divisionPoliticaDomicilio;
	}

	/**
	 * @return the telefonoTrabajo
	 */
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}

	/**
	 * @param telefonoTrabajo
	 *            the telefonoTrabajo to set
	 */
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	/**
	 * @return the porcentajeParticipacion
	 */
	public BigDecimal getPorcentajeParticipacion() {
		return porcentajeParticipacion;
	}

	/**
	 * @param porcentajeParticipacion
	 *            the porcentajeParticipacion to set
	 */
	public void setPorcentajeParticipacion(BigDecimal porcentajeParticipacion) {
		this.porcentajeParticipacion = porcentajeParticipacion;
	}

	/**
	 * @return the montoParticipacion
	 */
	public BigDecimal getMontoParticipacion() {
		return montoParticipacion;
	}

	/**
	 * @param montoParticipacion
	 *            the montoParticipacion to set
	 */
	public void setMontoParticipacion(BigDecimal montoParticipacion) {
		this.montoParticipacion = montoParticipacion;
	}

	/**
	 * @return the montoParticipacionDeposito
	 */
	public BigDecimal getMontoParticipacionDeposito() {
		return montoParticipacionDeposito;
	}

	/**
	 * @param montoParticipacionDeposito
	 *            the montoParticipacionDeposito to set
	 */
	public void setMontoParticipacionDeposito(BigDecimal montoParticipacionDeposito) {
		this.montoParticipacionDeposito = montoParticipacionDeposito;
	}

	/**
	 * @return the cuotaParticipacion
	 */
	public BigDecimal getCuotaParticipacion() {
		return cuotaParticipacion;
	}

	/**
	 * @param cuotaParticipacion
	 *            the cuotaParticipacion to set
	 */
	public void setCuotaParticipacion(BigDecimal cuotaParticipacion) {
		this.cuotaParticipacion = cuotaParticipacion;
	}

	public BigDecimal getValorSeguroConstruccion() {
		return valorSeguroConstruccion;
	}

	public void setValorSeguroConstruccion(BigDecimal valorSeguroConstruccion) {
		this.valorSeguroConstruccion = valorSeguroConstruccion;
	}

	public BigDecimal getValorSeguroDesgravamen() {
		return valorSeguroDesgravamen;
	}

	public void setValorSeguroDesgravamen(BigDecimal valorSeguroDesgravamen) {
		this.valorSeguroDesgravamen = valorSeguroDesgravamen;
	}

	public BigDecimal getValorSeguroIncendios() {
		return valorSeguroIncendios;
	}

	public void setValorSeguroIncendios(BigDecimal valorSeguroIncendios) {
		this.valorSeguroIncendios = valorSeguroIncendios;
	}

	public String getTipProD() {
		return tipProD;
	}

	public void setTipProD(String tipProD) {
		this.tipProD = tipProD;
	}

	public String getNumOpBanAfi() {
		return numOpBanAfi;
	}

	public void setNumOpBanAfi(String numOpBanAfi) {
		this.numOpBanAfi = numOpBanAfi;
	}

	public Long getNumOpBanIfi() {
		return numOpBanIfi;
	}

	public void setNumOpBanIfi(Long numOpBanIfi) {
		this.numOpBanIfi = numOpBanIfi;
	}

	public BigDecimal getSaldoHipotecario() {
		return saldoHipotecario;
	}

	public void setSaldoHipotecario(BigDecimal saldoHipotecario) {
		this.saldoHipotecario = saldoHipotecario;
	}

	/**
	 * @return the estadoCredito
	 */

	public String getEstadoCredito() {
		return estadoCredito;
	}

	/**
	 * @param estadoCredito
	 *            the estadoCredito to set
	 */

	public void setEstadoCredito(String estadoCredito) {
		this.estadoCredito = estadoCredito;
	}

	/**
	 * @return the soTipoInstitucion
	 */
	public String getSoTipoInstitucion() {
		return soTipoInstitucion;
	}

	/**
	 * @param soTipoInstitucion
	 *            the soTipoInstitucion to set
	 */
	public void setSoTipoInstitucion(String soTipoInstitucion) {
		this.soTipoInstitucion = soTipoInstitucion;
	}

	/**
	 * @return the prestacionSeguroJub
	 */
	public Long getPrestacionSeguroJub() {
		return prestacionSeguroJub;
	}

	/**
	 * @param prestacionSeguroJub
	 *            the prestacionSeguroJub to set
	 */
	public void setPrestacionSeguroJub(Long prestacionSeguroJub) {
		this.prestacionSeguroJub = prestacionSeguroJub;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the estadoExtranjero
	 */
	public String getEstadoExtranjero() {
		return estadoExtranjero;
	}

	/**
	 * @param estadoExtranjero
	 *            the estadoExtranjero to set
	 */
	public void setEstadoExtranjero(String estadoExtranjero) {
		this.estadoExtranjero = estadoExtranjero;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad
	 *            the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
}