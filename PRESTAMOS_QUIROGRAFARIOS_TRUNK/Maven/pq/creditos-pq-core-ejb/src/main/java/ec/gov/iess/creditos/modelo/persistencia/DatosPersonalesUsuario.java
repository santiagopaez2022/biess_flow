/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.hl.modelo.DivisionPolitica;

/**
 * <b> Clase para persistencia de datos adicionales de una solicitud PH </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.1 $
 *          <p>
 *          [$Author: ricardo T $, $Date: 03/10/2011 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_DATOSPERSONALESUSUARIO_TBL")
@SequenceGenerator(name = "creDetalleAdicionalSolSeq", sequenceName = "CRE_DATOSPERSONALESUSUARIO_SEQ")

		
@NamedQueries( {
	@NamedQuery(name = "DatosPersonalesUsuario.obtenerPorDetalleSolicitud", query = "SELECT o FROM DatosPersonalesUsuario o "
		+ " WHERE o.detalleSolicitudCredito.coddetsol=:codigoDetalle ORDER BY o.id"),
		@NamedQuery(name = "DatosPersonalesUsuario.Obtener_cedula",query="SELECT o FROM DatosPersonalesUsuario o " +
				"Where o.cedula = :cedula ")
})

		
public class DatosPersonalesUsuario implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 7161216621009488497L;

	@Id
	@Column(name = "DP_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creDetalleAdicionalSolSeq")
	private Long id;

	@Column(name = "DP_TIPO_USUARIO")
	private Long tipoUsuario;

	@Column(name = "DP_CEDULA", nullable = false)
	private String cedula;

	@Column(name = "DP_NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "DP_CODDIVPOL")
	private String codDivPol;

	@Column(name = "DP_DIRECCION")
	private String direccion;

	@Column(name = "DP_TELEFONO")
	private String telefono;

	@Column(name = "DP_RELACION_REF")
	private String relacionReferencia;

	@Column(name = "DP_NOMBRE_REF")
	private String nombreReferencia;

	@Column(name = "DP_FONO_DOMICILIO_REF")
	private String fonoReferencia;

	@Column(name = "DP_FONO_TRABAJO_REF")
	private String fonoTrabajoRefencia;

	@Column(name = "DP_CELULAR_REF")
	private String celularRefencia;

	@Column(name = "DP_ESTADO_BIEN")
	private String estadoBien;

	@Column(name = "DP_TIPO_BIEN")
	private String tipoBien;

	@Column(name = "DP_CODDIVPOL_BIEN")
	private String codDivPolBien;

	@Column(name = "DP_SECTOR_BIEN")
	private String sectorBien;

	@Column(name = "DP_DIRECCION_BIEN")
	private String direccionBien;

	@Column(name = "DP_CALLE_PRINCIPAL_BIEN")
	private String callePrincipalBien;

	@Column(name = "DP_CALLE_SECUNDARIA_BIEN")
	private String calleSecundariaBien;

	@Column(name = "DP_NOMBRE_EDIFICIO_BIEN")
	private String nombreEdificioCasa;

	@Column(name = "DP_NUMERO_DEP_CASA_BIEN")
	private String numeroDepartamentoCasa;

	@Column(name = "DP_CUOTA_APROBADA")
	private BigDecimal cuotaAprobada;

	@Column(name = "DP_TOTAL_INGRESOS")
	private BigDecimal totalIngresos;

	@Column(name = "DP_TOTAL_DESCUENTOS")
	private BigDecimal totalDescuentos;

	@Column(name = "DP_PORCENTAJE_END")
	private BigDecimal porcentajeEndeudamiento;

	@Column(name = "DP_TOTAL_NETO")
	private BigDecimal totalNeto;

	@ManyToOne
	@JoinColumn(name = "DP_CODDETSOL", referencedColumnName = "CODDETSOL")
	private DetalleSolicitud detalleSolicitudCredito;

	@Transient
	private DivisionPolitica divisionPoliticaFiador;

	@Transient
	private DivisionPolitica divisionPoliticaBien;

	@Transient
	private String provinciaFiador;

	@Transient
	private String cantonFiador;

	@Transient
	private String parroquiaFiador;

	@Transient
	private String provinciaBien;

	@Transient
	private String cantonBien;

	@Transient
	private String parroquiaBien;

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
	 * @return the relacionReferencia
	 */
	public String getRelacionReferencia() {
		return relacionReferencia;
	}

	/**
	 * @param relacionReferencia
	 *            the relacionReferencia to set
	 */
	public void setRelacionReferencia(String relacionReferencia) {
		this.relacionReferencia = relacionReferencia;
	}

	/**
	 * @return the nombreReferencia
	 */
	public String getNombreReferencia() {
		return nombreReferencia;
	}

	/**
	 * @param nombreReferencia
	 *            the nombreReferencia to set
	 */
	public void setNombreReferencia(String nombreReferencia) {
		this.nombreReferencia = nombreReferencia;
	}

	/**
	 * @return the fonoReferencia
	 */
	public String getFonoReferencia() {
		return fonoReferencia;
	}

	/**
	 * @param fonoReferencia
	 *            the fonoReferencia to set
	 */
	public void setFonoReferencia(String fonoReferencia) {
		this.fonoReferencia = fonoReferencia;
	}

	/**
	 * @return the fonoTrabajoRefencia
	 */
	public String getFonoTrabajoRefencia() {
		return fonoTrabajoRefencia;
	}

	/**
	 * @param fonoTrabajoRefencia
	 *            the fonoTrabajoRefencia to set
	 */
	public void setFonoTrabajoRefencia(String fonoTrabajoRefencia) {
		this.fonoTrabajoRefencia = fonoTrabajoRefencia;
	}

	/**
	 * @return the celularRefencia
	 */
	public String getCelularRefencia() {
		return celularRefencia;
	}

	/**
	 * @param celularRefencia
	 *            the celularRefencia to set
	 */
	public void setCelularRefencia(String celularRefencia) {
		this.celularRefencia = celularRefencia;
	}

	/**
	 * @return the estadoBien
	 */
	public String getEstadoBien() {
		return estadoBien;
	}

	/**
	 * @param estadoBien
	 *            the estadoBien to set
	 */
	public void setEstadoBien(String estadoBien) {
		this.estadoBien = estadoBien;
	}

	/**
	 * @return the tipoBien
	 */
	public String getTipoBien() {
		return tipoBien;
	}

	/**
	 * @param tipoBien
	 *            the tipoBien to set
	 */
	public void setTipoBien(String tipoBien) {
		this.tipoBien = tipoBien;
	}

	/**
	 * @return the codDivPolBien
	 */
	public String getCodDivPolBien() {
		return codDivPolBien;
	}

	/**
	 * @param codDivPolBien
	 *            the codDivPolBien to set
	 */
	public void setCodDivPolBien(String codDivPolBien) {
		this.codDivPolBien = codDivPolBien;
	}

	/**
	 * @return the sectorBien
	 */
	public String getSectorBien() {
		return sectorBien;
	}

	/**
	 * @param sectorBien
	 *            the sectorBien to set
	 */
	public void setSectorBien(String sectorBien) {
		this.sectorBien = sectorBien;
	}

	/**
	 * @return the direccionBien
	 */
	public String getDireccionBien() {
		return direccionBien;
	}

	/**
	 * @param direccionBien
	 *            the direccionBien to set
	 */
	public void setDireccionBien(String direccionBien) {
		this.direccionBien = direccionBien;
	}

	/**
	 * @return the callePrincipalBien
	 */
	public String getCallePrincipalBien() {
		return callePrincipalBien;
	}

	/**
	 * @param callePrincipalBien
	 *            the callePrincipalBien to set
	 */
	public void setCallePrincipalBien(String callePrincipalBien) {
		this.callePrincipalBien = callePrincipalBien;
	}

	/**
	 * @return the calleSecundariaBien
	 */
	public String getCalleSecundariaBien() {
		return calleSecundariaBien;
	}

	/**
	 * @param calleSecundariaBien
	 *            the calleSecundariaBien to set
	 */
	public void setCalleSecundariaBien(String calleSecundariaBien) {
		this.calleSecundariaBien = calleSecundariaBien;
	}

	/**
	 * @return the nombreEdificioCasa
	 */
	public String getNombreEdificioCasa() {
		return nombreEdificioCasa;
	}

	/**
	 * @param nombreEdificioCasa
	 *            the nombreEdificioCasa to set
	 */
	public void setNombreEdificioCasa(String nombreEdificioCasa) {
		this.nombreEdificioCasa = nombreEdificioCasa;
	}

	/**
	 * @return the numeroDepartamentoCasa
	 */
	public String getNumeroDepartamentoCasa() {
		return numeroDepartamentoCasa;
	}

	/**
	 * @param numeroDepartamentoCasa
	 *            the numeroDepartamentoCasa to set
	 */
	public void setNumeroDepartamentoCasa(String numeroDepartamentoCasa) {
		this.numeroDepartamentoCasa = numeroDepartamentoCasa;
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
	public void setDetalleSolicitudCredito(DetalleSolicitud detalleSolicitudCredito) {
		this.detalleSolicitudCredito = detalleSolicitudCredito;
	}

	/**
	 * @return the divisionPoliticaFiador
	 */
	public DivisionPolitica getDivisionPoliticaFiador() {
		return divisionPoliticaFiador;
	}

	/**
	 * @param divisionPoliticaFiador
	 *            the divisionPoliticaFiador to set
	 */
	public void setDivisionPoliticaFiador(DivisionPolitica divisionPoliticaFiador) {
		this.divisionPoliticaFiador = divisionPoliticaFiador;
	}

	/**
	 * @return the divisionPoliticaBien
	 */
	public DivisionPolitica getDivisionPoliticaBien() {
		return divisionPoliticaBien;
	}

	/**
	 * @param divisionPoliticaBien
	 *            the divisionPoliticaBien to set
	 */
	public void setDivisionPoliticaBien(DivisionPolitica divisionPoliticaBien) {
		this.divisionPoliticaBien = divisionPoliticaBien;
	}

	/**
	 * @return the provinciaFiador
	 */
	public String getProvinciaFiador() {
		return provinciaFiador;
	}

	/**
	 * @param provinciaFiador
	 *            the provinciaFiador to set
	 */
	public void setProvinciaFiador(String provinciaFiador) {
		this.provinciaFiador = provinciaFiador;
	}

	/**
	 * @return the cantonFiador
	 */
	public String getCantonFiador() {
		return cantonFiador;
	}

	/**
	 * @param cantonFiador
	 *            the cantonFiador to set
	 */
	public void setCantonFiador(String cantonFiador) {
		this.cantonFiador = cantonFiador;
	}

	/**
	 * @return the parroquiaFiador
	 */
	public String getParroquiaFiador() {
		return parroquiaFiador;
	}

	/**
	 * @param parroquiaFiador
	 *            the parroquiaFiador to set
	 */
	public void setParroquiaFiador(String parroquiaFiador) {
		this.parroquiaFiador = parroquiaFiador;
	}

	/**
	 * @return the provinciaBien
	 */
	public String getProvinciaBien() {
		return provinciaBien;
	}

	/**
	 * @param provinciaBien
	 *            the provinciaBien to set
	 */
	public void setProvinciaBien(String provinciaBien) {
		this.provinciaBien = provinciaBien;
	}

	/**
	 * @return the cantonBien
	 */
	public String getCantonBien() {
		return cantonBien;
	}

	/**
	 * @param cantonBien
	 *            the cantonBien to set
	 */
	public void setCantonBien(String cantonBien) {
		this.cantonBien = cantonBien;
	}

	/**
	 * @return the parroquiaBien
	 */
	public String getParroquiaBien() {
		return parroquiaBien;
	}

	/**
	 * @param parroquiaBien
	 *            the parroquiaBien to set
	 */
	public void setParroquiaBien(String parroquiaBien) {
		this.parroquiaBien = parroquiaBien;
	}

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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the codDivPol
	 */
	public String getCodDivPol() {
		return codDivPol;
	}

	/**
	 * @param codDivPol
	 *            the codDivPol to set
	 */
	public void setCodDivPol(String codDivPol) {
		this.codDivPol = codDivPol;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the tipoUsuario
	 */
	public Long getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario
	 *            the tipoUsuario to set
	 */
	public void setTipoUsuario(Long tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * @return the cuotaAprobada
	 */
	public BigDecimal getCuotaAprobada() {
		return cuotaAprobada;
	}

	/**
	 * @param cuotaAprobada
	 *            the cuotaAprobada to set
	 */
	public void setCuotaAprobada(BigDecimal cuotaAprobada) {
		this.cuotaAprobada = cuotaAprobada;
	}

	/**
	 * @return the totalIngresos
	 */
	public BigDecimal getTotalIngresos() {
		return totalIngresos;
	}

	/**
	 * @param totalIngresos
	 *            the totalIngresos to set
	 */
	public void setTotalIngresos(BigDecimal totalIngresos) {
		this.totalIngresos = totalIngresos;
	}

	/**
	 * @return the totalDescuentos
	 */
	public BigDecimal getTotalDescuentos() {
		return totalDescuentos;
	}

	/**
	 * @param totalDescuentos
	 *            the totalDescuentos to set
	 */
	public void setTotalDescuentos(BigDecimal totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	/**
	 * @return the porcentajeEndeudamiento
	 */
	public BigDecimal getPorcentajeEndeudamiento() {
		return porcentajeEndeudamiento;
	}

	/**
	 * @param porcentajeEndeudamiento
	 *            the porcentajeEndeudamiento to set
	 */
	public void setPorcentajeEndeudamiento(BigDecimal porcentajeEndeudamiento) {
		this.porcentajeEndeudamiento = porcentajeEndeudamiento;
	}

	/**
	 * @return the totalNeto
	 */
	public BigDecimal getTotalNeto() {
		return totalNeto;
	}

	/**
	 * @param totalNeto
	 *            the totalNeto to set
	 */
	public void setTotalNeto(BigDecimal totalNeto) {
		this.totalNeto = totalNeto;
	}
}
