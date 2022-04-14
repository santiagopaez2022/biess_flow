package ec.fin.biess.creditos.pq.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Calse dto para almacenar informacion de cesantias de un asegurado
 * @author edison.cayambe
 *
 */
public class AprobacionMasivaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String cedulaFuncionario;
	String nombreFuncionario;
	String emailFuncionario;
	BigDecimal secuencialHistorico;
	Date fechaAprobacion;
	List<AprobacionDto> listaAprobacion;
	int tamanio;
	
	String ipUsuario;
	String usuario;
	String hostRemoto;
	
	public String getCedulaFuncionario() {
		return cedulaFuncionario;
	}
	
	public void setCedulaFuncionario(String cedulaFuncionario) {
		this.cedulaFuncionario = cedulaFuncionario;
	}
	
	public String getNombreFuncionario() {
		return nombreFuncionario;
	}
	
	public void setNombreFuncionario(String nombreFuncionario) {
		this.nombreFuncionario = nombreFuncionario;
	}
	
	public String getEmailFuncionario() {
		return emailFuncionario;
	}
	
	public void setEmailFuncionario(String emailFuncionario) {
		this.emailFuncionario = emailFuncionario;
	}
	
	public BigDecimal getSecuencialHistorico() {
		return secuencialHistorico;
	}
	
	public void setSecuencialHistorico(BigDecimal secuencialHistorico) {
		this.secuencialHistorico = secuencialHistorico;
	}
	
	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}
	
	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	
	public List<AprobacionDto> getListaAprobacion() {
		return listaAprobacion;
	}
	
	public void setListaAprobacion(List<AprobacionDto> listaAprobacion) {
		this.listaAprobacion = listaAprobacion;
	}
	
	public int getTamanio() {
		return tamanio;
	}
	
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public String getIpUsuario() {
		return ipUsuario;
	}

	public void setIpUsuario(String ipUsuario) {
		this.ipUsuario = ipUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getHostRemoto() {
		return hostRemoto;
	}

	public void setHostRemoto(String hostRemoto) {
		this.hostRemoto = hostRemoto;
	}
}
