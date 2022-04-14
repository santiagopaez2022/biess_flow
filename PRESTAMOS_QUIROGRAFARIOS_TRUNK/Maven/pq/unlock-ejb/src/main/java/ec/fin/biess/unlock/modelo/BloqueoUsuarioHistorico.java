package ec.fin.biess.unlock.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CRE_BLOQUEOUSUARIOHIS_TBL")
public class BloqueoUsuarioHistorico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3867473249468233228L;

	@Id
	@Column(name = "BU_ID")
	private int idBloqueoUsuarioHistorico;

	@Column(name = "BU_NUMAFI", length = 10)
	private String cedula;

	@Column(name = "BU_ESTADOBLOQUEO", length = 1)
	private String indicadorCuentaBloqueda;

	@Column(name = "BU_FECHAREGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;

	@Column(name = "BU_TIPOBLOQUEO", length = 1)
	private String tipoBloqueo;

	@Column(name = "BU_TOKEN", length = 100)
	private String token;

	@Column(name = "BU_IP", length = 40)
	private String ip;

	@Column(name = "BU_CEDFUNCIONARIO", length = 10)
	private String cedulaFuncionario;

	@Column(name = "BU_OBSERVACION", length = 500)
	private String observacion;

	public BloqueoUsuarioHistorico() {
		super();
	}

	public int getIdBloqueoUsuarioHistorico() {
		return idBloqueoUsuarioHistorico;
	}

	public void setIdBloqueoUsuarioHistorico(int idBloqueoUsuarioHistorico) {
		this.idBloqueoUsuarioHistorico = idBloqueoUsuarioHistorico;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getIndicadorCuentaBloqueda() {
		return indicadorCuentaBloqueda;
	}

	public void setIndicadorCuentaBloqueda(String indicadorCuentaBloqueda) {
		this.indicadorCuentaBloqueda = indicadorCuentaBloqueda;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getTipoBloqueo() {
		return tipoBloqueo;
	}

	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
