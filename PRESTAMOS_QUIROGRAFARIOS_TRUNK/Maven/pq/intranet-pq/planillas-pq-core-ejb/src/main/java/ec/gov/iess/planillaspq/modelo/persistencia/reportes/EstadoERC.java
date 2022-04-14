package ec.gov.iess.planillaspq.modelo.persistencia.reportes;

import java.util.Date;

/**
 * @author Paul
 * 
 *         Clase que representa la reporte de estado bloqueado
 */
public class EstadoERC {

	// Datos Patrono
	private String nombreEmpresa;
	private String provinciaPatrono;
	private String cuidadPatrono;
	private String numeroTelefonicoPatrono;

	// Datos Asegurado
	private String numeroCedula;
	private String nombreCompleto;
	private Double montoCredito;
	private String provinciaAsegurado;
	private String cuidadAsegurado;
	private String numeroTelefonicoAsegurado;

	// Datos Institucion
	private String nombreInstitucion;
	private String numeroCuenta;
	private String actualizacion;
	private Date fechaRegistro;
	private String registro;

	// Datos Historicos
	private String institucionAnterior;
	private String cuentaAnterior;
	private String tipoAnterior;
	private Date fechaRegistroAnterior;
	
	// Datos del credito
	private String estadoCredito;

	// Getters and Setters

	/**
	 * Obtiene nombreEmpresa
	 * 
	 * @return
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Setea nombreEmpresa
	 * 
	 * @param nombreEmpresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * Obtiene provinciaPatrono
	 * 
	 * @return
	 */
	public String getProvinciaPatrono() {
		return provinciaPatrono;
	}

	/**
	 * Setea provinciaPatrono
	 * 
	 * @param provinciaPatrono
	 */
	public void setProvinciaPatrono(String provinciaPatrono) {
		this.provinciaPatrono = provinciaPatrono;
	}

	/**
	 * Obtiene cuidadPatrono
	 * 
	 * @return
	 */
	public String getCuidadPatrono() {
		return cuidadPatrono;
	}

	/**
	 * Setea cuidadPatrono
	 * 
	 * @param cuidadPatrono
	 */
	public void setCuidadPatrono(String cuidadPatrono) {
		this.cuidadPatrono = cuidadPatrono;
	}

	/**
	 * Obtiene numeroTelefonicoPatrono
	 * 
	 * @return
	 */
	public String getNumeroTelefonicoPatrono() {
		return numeroTelefonicoPatrono;
	}

	/**
	 * Setea numeroTelefonicoPatrono
	 * 
	 * @param numeroTelefonicoPatrono
	 */
	public void setNumeroTelefonicoPatrono(String numeroTelefonicoPatrono) {
		this.numeroTelefonicoPatrono = numeroTelefonicoPatrono;
	}

	/**
	 * Obtiene numeroCedula
	 * 
	 * @return
	 */
	public String getNumeroCedula() {
		return numeroCedula;
	}

	/**
	 * Setea numeroCedula
	 * 
	 * @param numeroCedula
	 */
	public void setNumeroCedula(String numeroCedula) {
		this.numeroCedula = numeroCedula;
	}

	/**
	 * Obtiene nombreCompleto
	 * 
	 * @return
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Setea nombreCompleto
	 * 
	 * @param nombreCompleto
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * Obtiene montoCredito
	 * 
	 * @return
	 */
	public Double getMontoCredito() {
		return montoCredito;
	}

	/**
	 * Setea montoCredito
	 * 
	 * @param montoCredito
	 */
	public void setMontoCredito(Double montoCredito) {
		this.montoCredito = montoCredito;
	}

	/**
	 * Obtiene provinciaAsegurado
	 * 
	 * @return
	 */
	public String getProvinciaAsegurado() {
		return provinciaAsegurado;
	}

	/**
	 * Setea provinciaAsegurado
	 * 
	 * @param provinciaAsegurado
	 */
	public void setProvinciaAsegurado(String provinciaAsegurado) {
		this.provinciaAsegurado = provinciaAsegurado;
	}

	/**
	 * Obtiene cuidadAsegurado
	 * 
	 * @return
	 */
	public String getCuidadAsegurado() {
		return cuidadAsegurado;
	}

	/**
	 * Setea cuidadAsegurado
	 * 
	 * @param cuidadAsegurado
	 */
	public void setCuidadAsegurado(String cuidadAsegurado) {
		this.cuidadAsegurado = cuidadAsegurado;
	}

	/**
	 * Obtiene numeroTelefonicoAsegurado
	 * 
	 * @return
	 */
	public String getNumeroTelefonicoAsegurado() {
		return numeroTelefonicoAsegurado;
	}

	/**
	 * Setea numeroTelefonicoAsegurado
	 * 
	 * @param numeroTelefonicoAsegurado
	 */
	public void setNumeroTelefonicoAsegurado(String numeroTelefonicoAsegurado) {
		this.numeroTelefonicoAsegurado = numeroTelefonicoAsegurado;
	}

	/**
	 * Obtiene nombreInstitucion
	 * 
	 * @return
	 */
	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	/**
	 * Setea nombreInstitucion
	 * 
	 * @param nombreInstitucion
	 */
	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	/**
	 * Obtiene numeroCuenta
	 * 
	 * @return
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Setea numeroCuenta
	 * 
	 * @param numeroCuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Obtiene actualizacion
	 * 
	 * @return
	 */
	public String getActualizacion() {
		return actualizacion;
	}

	/**
	 * Setea actualizacion
	 * 
	 * @param actualizacion
	 */
	public void setActualizacion(String actualizacion) {
		this.actualizacion = actualizacion;
	}

	/**
	 * Obtiene fechaRegistro
	 * 
	 * @return
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Setea fechaRegistro
	 * 
	 * @param fechaRegistro
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Obtiene registro
	 * 
	 * @return
	 */
	public String getRegistro() {
		return registro;
	}

	/**
	 * Setea registro
	 * 
	 * @param registro
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getInstitucionAnterior() {
		return institucionAnterior;
	}

	public void setInstitucionAnterior(String institucionAnterior) {
		this.institucionAnterior = institucionAnterior;
	}

	public String getCuentaAnterior() {
		return cuentaAnterior;
	}

	public void setCuentaAnterior(String cuentaAnterior) {
		this.cuentaAnterior = cuentaAnterior;
	}

	public String getTipoAnterior() {
		return tipoAnterior;
	}

	public void setTipoAnterior(String tipoAnterior) {
		this.tipoAnterior = tipoAnterior;
	}

	public Date getFechaRegistroAnterior() {
		return fechaRegistroAnterior;
	}

	public void setFechaRegistroAnterior(Date fechaRegistroAnterior) {
		this.fechaRegistroAnterior = fechaRegistroAnterior;
	}

	/**
	 * @return the estadoCredito
	 */
	public String getEstadoCredito() {
		return estadoCredito;
	}

	/**
	 * @param estadoCredito the estadoCredito to set
	 */
	public void setEstadoCredito(String estadoCredito) {
		this.estadoCredito = estadoCredito;
	}

}
