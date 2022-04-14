/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase para persistencia de la tabla que contiene los datos del prouecto
 * 
 * @author jsanchez
 * 
 */
@Entity
@Table(name = "CRE_CC_DATOSPROYECTO_TBL")
@SequenceGenerator(name = "seqDatoProyectoConstructor", sequenceName = "CRE_CC_DATOSPROYECTO_SEQ", allocationSize = 1, initialValue = 1)
@NamedQuery(name = "DatoProyectoConstructor.obtenerPorSolicitud", query = "Select o from DatoProyectoConstructor o "
		+ "where o.solicitudCredito.solicitudCreditoPK=:pk order by o.codigoProyecto")
public class DatoProyectoConstructor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3884353318175533330L;

	@Id
	@Column(name = "DP_CODIGO_PROYECTO", nullable = false)
	@GeneratedValue(generator = "seqDatoProyectoConstructor", strategy = GenerationType.SEQUENCE)
	private Long codigoProyecto;

	@Column(name = "DP_CEDULA_REPRESENTANTE_LEGAL", nullable = false)
	private String cedRepresentanteLegal;

	@Column(name = "DP_RUC_EMPRESA_REPRES_LEGAL", nullable = false)
	private String rucRepresentanteLegal;

	@Column(name = "DP_RUC_EMPRESA_CONSTRUCTOR", nullable = false)
	private String rucConstructor;

	@Column(name = "DP_NOMBRE_PROYECTO", nullable = false)
	private String nombreProyecto;

	@Column(name = "DP_DIVISION_POLITICA", nullable = false)
	private String divisionPolitica;

	@Column(name = "DP_DIRECCION_PROYECTO", nullable = false)
	private String direccionProyecto;

	@Column(name = "DP_AREA_CONSTRUCCION_VENDIBLE", nullable = false)
	private BigDecimal areaConstruccion;

	@Column(name = "DP_AREA_CONSTRUCCION_IRM", nullable = false)
	private BigDecimal areaConstruccionIrm;

	@Column(name = "DP_COSTO_TOTAL", nullable = false)
	private BigDecimal costoTotalProyecto;

	@Column(name = "DP_COSTO_TERRENO", nullable = false)
	private BigDecimal costoTerreno;

	@Column(name = "DP_COSTO_DIRECTO", nullable = false)
	private BigDecimal costoDirecto;

	@Column(name = "DP_COSTO_INDIRECTO", nullable = false)
	private BigDecimal costoIndirecto;

	@Column(name = "DP_PLAZO", nullable = false)
	private Integer plazo;

	@Column(name = "DP_PLAZO_CREDITO", nullable = false)
	private Integer plazoCredito;

	@Column(name = "DP_MONTO_REQUERIDO", nullable = false)
	private BigDecimal montoRequerido;

	@Column(name = "DP_COSTO_TRAMITE", nullable = false)
	private BigDecimal costoTramite;

	// @Column(name = "DP_SCORE", nullable = false)
	// private Long score;
	//
	// @Column(name = "DP_CALBUR", nullable = false)
	// private String calificacionBuro;

	@Column(name = "DP_NOMBRE_FIDEICOMISO")
	private String nombreFideicomiso;

	@Column(name = "DP_TIPPROD", nullable = false)
	private String tipProD;

	@Column(name = "DP_NUMCTABAN", nullable = false)
	private String numeroCtaBancaria;

	@Column(name = "DP_TIPCTABAN", nullable = false)
	private String tipoCtaBancaria; // AHO, COR

	@Column(name = "DP_RUCINSFIN", nullable = false)
	private String rucInsFin;

	@Column(name = "DP_FECHA_INICIO_PROYECTO", nullable = false)
	private Date fechaInicioProyecto;

	@Column(name = "DP_TIPO_PROYECTO", nullable = false)
	private String tipoProyecto;

	@Column(name = "DP_CEDULA_LP", nullable = false)
	private String cedulaLider;

	@Column(name = "DP_CORREO_LP")
	private String mailLider;

	@Column(name = "DP_TELEFONO_LP")
	private String telefonoLider;

	@Column(name = "DP_CELULAR_LP")
	private String celularLider;

	@Column(name = "DP_CEDULA_TITULARCTA", nullable = false)
	private String cedulaTitularCuenta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( { @JoinColumn(name = "DP_CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER"),
			@JoinColumn(name = "DP_NUMSOLSER", referencedColumnName = "NUMSOLSER") })
	private SolicitudCredito solicitudCredito;

	/**
	 * @return the codigoProyecto
	 */
	public Long getCodigoProyecto() {
		return codigoProyecto;
	}

	/**
	 * @param codigoProyecto
	 *            the codigoProyecto to set
	 */
	public void setCodigoProyecto(Long codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	/**
	 * @return the cedRepresentanteLegal
	 */
	public String getCedRepresentanteLegal() {
		return cedRepresentanteLegal;
	}

	/**
	 * @param cedRepresentanteLegal
	 *            the cedRepresentanteLegal to set
	 */
	public void setCedRepresentanteLegal(String cedRepresentanteLegal) {
		this.cedRepresentanteLegal = cedRepresentanteLegal;
	}

	/**
	 * @return the rucRepresentanteLegal
	 */
	public String getRucRepresentanteLegal() {
		return rucRepresentanteLegal;
	}

	/**
	 * @param rucRepresentanteLegal
	 *            the rucRepresentanteLegal to set
	 */
	public void setRucRepresentanteLegal(String rucRepresentanteLegal) {
		this.rucRepresentanteLegal = rucRepresentanteLegal;
	}

	/**
	 * @return the nombreProyecto
	 */
	public String getNombreProyecto() {
		return nombreProyecto;
	}

	/**
	 * @param nombreProyecto
	 *            the nombreProyecto to set
	 */
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	/**
	 * @return the divisionPolitica
	 */
	public String getDivisionPolitica() {
		return divisionPolitica;
	}

	/**
	 * @param divisionPolitica
	 *            the divisionPolitica to set
	 */
	public void setDivisionPolitica(String divisionPolitica) {
		this.divisionPolitica = divisionPolitica;
	}

	/**
	 * @return the direccionProyecto
	 */
	public String getDireccionProyecto() {
		return direccionProyecto;
	}

	/**
	 * @param direccionProyecto
	 *            the direccionProyecto to set
	 */
	public void setDireccionProyecto(String direccionProyecto) {
		this.direccionProyecto = direccionProyecto;
	}

	/**
	 * @return the areaConstruccion
	 */
	public BigDecimal getAreaConstruccion() {
		return areaConstruccion;
	}

	/**
	 * @param areaConstruccion
	 *            the areaConstruccion to set
	 */
	public void setAreaConstruccion(BigDecimal areaConstruccion) {
		this.areaConstruccion = areaConstruccion;
	}

	/**
	 * @return the plazo
	 */
	public Integer getPlazo() {
		return plazo;
	}

	/**
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	/**
	 * @return the montoRequerido
	 */
	public BigDecimal getMontoRequerido() {
		return montoRequerido;
	}

	/**
	 * @param montoRequerido
	 *            the montoRequerido to set
	 */
	public void setMontoRequerido(BigDecimal montoRequerido) {
		this.montoRequerido = montoRequerido;
	}

	/**
	 * @return the costoTramite
	 */
	public BigDecimal getCostoTramite() {
		return costoTramite;
	}

	/**
	 * @param costoTramite
	 *            the costoTramite to set
	 */
	public void setCostoTramite(BigDecimal costoTramite) {
		this.costoTramite = costoTramite;
	}

	// /**
	// * @return the score
	// */
	// public Long getScore() {
	// return score;
	// }
	//
	// /**
	// * @param score
	// * the score to set
	// */
	// public void setScore(Long score) {
	// this.score = score;
	// }
	//
	// /**
	// * @return the calificacionBuro
	// */
	// public String getCalificacionBuro() {
	// return calificacionBuro;
	// }
	//
	// /**
	// * @param calificacionBuro
	// * the calificacionBuro to set
	// */
	// public void setCalificacionBuro(String calificacionBuro) {
	// this.calificacionBuro = calificacionBuro;
	// }

	/**
	 * @return the nombreFideicomiso
	 */
	public String getNombreFideicomiso() {
		return nombreFideicomiso;
	}

	/**
	 * @param nombreFideicomiso
	 *            the nombreFideicomiso to set
	 */
	public void setNombreFideicomiso(String nombreFideicomiso) {
		this.nombreFideicomiso = nombreFideicomiso;
	}

	/**
	 * @return the tipProD
	 */
	public String getTipProD() {
		return tipProD;
	}

	/**
	 * @param tipProD
	 *            the tipProD to set
	 */
	public void setTipProD(String tipProD) {
		this.tipProD = tipProD;
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
	 * @return the rucConstructor
	 */
	public String getRucConstructor() {
		return rucConstructor;
	}

	/**
	 * @param rucConstructor
	 *            the rucConstructor to set
	 */
	public void setRucConstructor(String rucConstructor) {
		this.rucConstructor = rucConstructor;
	}

	/**
	 * @return the numeroCtaBancaria
	 */
	public String getNumeroCtaBancaria() {
		return numeroCtaBancaria;
	}

	/**
	 * @param numeroCtaBancaria
	 *            the numeroCtaBancaria to set
	 */
	public void setNumeroCtaBancaria(String numeroCtaBancaria) {
		this.numeroCtaBancaria = numeroCtaBancaria;
	}

	/**
	 * @return the tipoCtaBancaria
	 */
	public String getTipoCtaBancaria() {
		return tipoCtaBancaria;
	}

	/**
	 * @param tipoCtaBancaria
	 *            the tipoCtaBancaria to set
	 */
	public void setTipoCtaBancaria(String tipoCtaBancaria) {
		this.tipoCtaBancaria = tipoCtaBancaria;
	}

	/**
	 * @return the rucInsFin
	 */
	public String getRucInsFin() {
		return rucInsFin;
	}

	/**
	 * @param rucInsFin
	 *            the rucInsFin to set
	 */
	public void setRucInsFin(String rucInsFin) {
		this.rucInsFin = rucInsFin;
	}

	/**
	 * @return the areaConstruccionIrm
	 */
	public BigDecimal getAreaConstruccionIrm() {
		return areaConstruccionIrm;
	}

	/**
	 * @param areaConstruccionIrm
	 *            the areaConstruccionIrm to set
	 */
	public void setAreaConstruccionIrm(BigDecimal areaConstruccionIrm) {
		this.areaConstruccionIrm = areaConstruccionIrm;
	}

	/**
	 * @return the costoTotalProyecto
	 */
	public BigDecimal getCostoTotalProyecto() {
		return costoTotalProyecto;
	}

	/**
	 * @param costoTotalProyecto
	 *            the costoTotalProyecto to set
	 */
	public void setCostoTotalProyecto(BigDecimal costoTotalProyecto) {
		this.costoTotalProyecto = costoTotalProyecto;
	}

	/**
	 * @return the costoTerreno
	 */
	public BigDecimal getCostoTerreno() {
		return costoTerreno;
	}

	/**
	 * @param costoTerreno
	 *            the costoTerreno to set
	 */
	public void setCostoTerreno(BigDecimal costoTerreno) {
		this.costoTerreno = costoTerreno;
	}

	/**
	 * @return the costoDirecto
	 */
	public BigDecimal getCostoDirecto() {
		return costoDirecto;
	}

	/**
	 * @param costoDirecto
	 *            the costoDirecto to set
	 */
	public void setCostoDirecto(BigDecimal costoDirecto) {
		this.costoDirecto = costoDirecto;
	}

	/**
	 * @return the costoIndirecto
	 */
	public BigDecimal getCostoIndirecto() {
		return costoIndirecto;
	}

	/**
	 * @param costoIndirecto
	 *            the costoIndirecto to set
	 */
	public void setCostoIndirecto(BigDecimal costoIndirecto) {
		this.costoIndirecto = costoIndirecto;
	}

	/**
	 * @return the plazoCredito
	 */
	public Integer getPlazoCredito() {
		return plazoCredito;
	}

	/**
	 * @param plazoCredito
	 *            the plazoCredito to set
	 */
	public void setPlazoCredito(Integer plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	/**
	 * @return the fechaInicioProyecto
	 */
	public Date getFechaInicioProyecto() {
		return fechaInicioProyecto;
	}

	/**
	 * @param fechaInicioProyecto
	 *            the fechaInicioProyecto to set
	 */
	public void setFechaInicioProyecto(Date fechaInicioProyecto) {
		this.fechaInicioProyecto = fechaInicioProyecto;
	}

	/**
	 * @return the tipoProyecto
	 */
	public String getTipoProyecto() {
		return tipoProyecto;
	}

	/**
	 * @param tipoProyecto
	 *            the tipoProyecto to set
	 */
	public void setTipoProyecto(String tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}

	/**
	 * @return the cedulaLider
	 */
	public String getCedulaLider() {
		return cedulaLider;
	}

	/**
	 * @param cedulaLider
	 *            the cedulaLider to set
	 */
	public void setCedulaLider(String cedulaLider) {
		this.cedulaLider = cedulaLider;
	}

	/**
	 * @return the mailLider
	 */
	public String getMailLider() {
		return mailLider;
	}

	/**
	 * @param mailLider
	 *            the mailLider to set
	 */
	public void setMailLider(String mailLider) {
		this.mailLider = mailLider;
	}

	/**
	 * @return the telefonoLider
	 */
	public String getTelefonoLider() {
		return telefonoLider;
	}

	/**
	 * @param telefonoLider
	 *            the telefonoLider to set
	 */
	public void setTelefonoLider(String telefonoLider) {
		this.telefonoLider = telefonoLider;
	}

	/**
	 * @return the celularLider
	 */
	public String getCelularLider() {
		return celularLider;
	}

	/**
	 * @param celularLider
	 *            the celularLider to set
	 */
	public void setCelularLider(String celularLider) {
		this.celularLider = celularLider;
	}

	/**
	 * @return the cedulaTitularCuenta
	 */
	public String getCedulaTitularCuenta() {
		return cedulaTitularCuenta;
	}

	/**
	 * @param cedulaTitularCuenta
	 *            the cedulaTitularCuenta to set
	 */
	public void setCedulaTitularCuenta(String cedulaTitularCuenta) {
		this.cedulaTitularCuenta = cedulaTitularCuenta;
	}
}
