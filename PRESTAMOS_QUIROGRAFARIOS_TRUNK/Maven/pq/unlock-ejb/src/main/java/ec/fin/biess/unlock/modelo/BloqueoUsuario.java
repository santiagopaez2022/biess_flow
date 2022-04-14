package ec.fin.biess.unlock.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tabla que represent
 * 
 */
@Entity
@Table(name = "CRE_BLOQUEOUSUARIO_TBL")
public class BloqueoUsuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2413509795231577368L;

	@Id
	@Column(name = "BU_NUMAFI", length = 10)
	private String cedula;

	@Column(name = "BU_TOKEN", length = 100)
	private String token;

	@Column(name = "BU_ESTADO", length = 1)
	private String indicadorCuentaBloqueda;

	@Column(name = "BU_INDICADOR", length = 1)
	private String indicadorActualizacionDatos;

	@Column(name = "BU_FECHAACTUALIZA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacionDatos;

	@Column(name = "BU_NUMBLOPAR")
	private Integer numeroBloqueosParciales;

	@Column(name = "BU_FECHABLOPAR")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaBloqueosParciales;

	@Column(name = "BU_NUMBLOTOT")
	private Integer numeroBloqueosTotales;

	@Column(name = "BU_OBSERVACION", length = 500)
	private String observacion;

	@Column(name = "BU_FECHADESBLOQUEO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaDesbloqueos;

	@Column(name = "BU_IP", length = 40)
	private String ip;

	@Column(name = "BU_CEDFUNCIONARIO", length = 10)
	private String cedulaFuncionario;

	public BloqueoUsuario() {
		super();
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIndicadorCuentaBloqueda() {
		return indicadorCuentaBloqueda;
	}

	public void setIndicadorCuentaBloqueda(String indicadorCuentaBloqueda) {
		this.indicadorCuentaBloqueda = indicadorCuentaBloqueda;
	}

	public String getIndicadorActualizacionDatos() {
		return indicadorActualizacionDatos;
	}

	public void setIndicadorActualizacionDatos(String indicadorActualizacionDatos) {
		this.indicadorActualizacionDatos = indicadorActualizacionDatos;
	}

	public Date getFechaActualizacionDatos() {
		return fechaActualizacionDatos;
	}

	public void setFechaActualizacionDatos(Date fechaActualizacionDatos) {
		this.fechaActualizacionDatos = fechaActualizacionDatos;
	}

	public void setNumeroBloqueosParciales(Integer numeroBloqueosParciales) {
		this.numeroBloqueosParciales = numeroBloqueosParciales;
	}

	public Integer getNumeroBloqueosParciales() {
		return numeroBloqueosParciales;
	}

	public Date getFechaBloqueosParciales() {
		return fechaBloqueosParciales;
	}

	public void setFechaBloqueosParciales(Date fechaBloqueosParciales) {
		this.fechaBloqueosParciales = fechaBloqueosParciales;
	}

	public void setNumeroBloqueosTotales(Integer numeroBloqueosTotales) {
		this.numeroBloqueosTotales = numeroBloqueosTotales;
	}

	public Integer getNumeroBloqueosTotales() {
		return numeroBloqueosTotales;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaDesbloqueos() {
		return fechaDesbloqueos;
	}

	public void setFechaDesbloqueos(Date fechaDesbloqueos) {
		this.fechaDesbloqueos = fechaDesbloqueos;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCedulaFuncionario() {
		return cedulaFuncionario;
	}

	public void setCedulaFuncionario(String cedulaFuncionario) {
		this.cedulaFuncionario = cedulaFuncionario;
	}

}
