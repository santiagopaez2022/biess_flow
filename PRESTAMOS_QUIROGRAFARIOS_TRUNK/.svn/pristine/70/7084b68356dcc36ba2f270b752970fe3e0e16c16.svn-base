package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "THLCRT_GARANTIAS_EST")
public class EstadoGarantia implements Serializable {
	private static final long serialVersionUID = -2970054976075507678L;
	@Id
	@Column(name = "codestgarfon")
	private String codigoEstadoGarantia;
	
	@Column(name = "desestgarfon")
	private String descripcionEstadoGarantia;
	
	@Column(name = "indhabestgarfon")
	private String indicadorHabiliatadoEstado;

	public String getCodigoEstadoGarantia() {
		return codigoEstadoGarantia;
	}

	public void setCodigoEstadoGarantia(String codigoEstadoGarantia) {
		this.codigoEstadoGarantia = codigoEstadoGarantia;
	}

	public String getDescripcionEstadoGarantia() {
		return descripcionEstadoGarantia;
	}

	public void setDescripcionEstadoGarantia(String descripcionEstadoGarantia) {
		this.descripcionEstadoGarantia = descripcionEstadoGarantia;
	}

	public String getIndicadorHabiliatadoEstado() {
		return indicadorHabiliatadoEstado;
	}

	public void setIndicadorHabiliatadoEstado(String indicadorHabiliatadoEstado) {
		this.indicadorHabiliatadoEstado = indicadorHabiliatadoEstado;
	}

}
