/*
 */

package ec.gov.iess.creditos.modelo.dto;

/**
 * @author pmlopez
 * @version $Revision: 1.1 $
 * 
 */
public class InfoPrecalificacion {

	private Integer numeroImposiciones;

	private Integer imposicionesConsecutivas;

	private Boolean tieneCreditosVigentes;

	private Boolean estaEnMora;

	private Boolean esEmpleador;

	private Boolean estaAlDiaObligaciones;

	private Boolean esPensionista;

	private Boolean tieneCuentaBancariaAut;

	private Boolean tieneCreditoVigenteMismaCuenta;

	private Boolean esVoluntario;

	private Boolean estaActivo;

	private String calificacionBuroCredito;

	private Boolean tieneSolicitudCesantia;
	
	private String codigoRelacionTrabajo;

	/**
	 * Returns the value of numeroImposiciones.
	 * @return numeroImposiciones
	 */
	public Integer getNumeroImposiciones() {
		return numeroImposiciones;
	}

	/**
	 * Sets the value for numeroImposiciones.
	 * @param numeroImposiciones
	 */
	public void setNumeroImposiciones(Integer numeroImposiciones) {
		this.numeroImposiciones = numeroImposiciones;
	}

	/**
	 * Returns the value of imposicionesConsecutivas.
	 * @return imposicionesConsecutivas
	 */
	public Integer getImposicionesConsecutivas() {
		return imposicionesConsecutivas;
	}

	/**
	 * Sets the value for imposicionesConsecutivas.
	 * @param imposicionesConsecutivas
	 */
	public void setImposicionesConsecutivas(Integer imposicionesConsecutivas) {
		this.imposicionesConsecutivas = imposicionesConsecutivas;
	}

	/**
	 * Returns the value of tieneCreditosVigentes.
	 * @return tieneCreditosVigentes
	 */
	public Boolean getTieneCreditosVigentes() {
		return tieneCreditosVigentes;
	}

	/**
	 * Sets the value for tieneCreditosVigentes.
	 * @param tieneCreditosVigentes
	 */
	public void setTieneCreditosVigentes(Boolean tieneCreditosVigentes) {
		this.tieneCreditosVigentes = tieneCreditosVigentes;
	}

	/**
	 * Returns the value of estaEnMora.
	 * @return estaEnMora
	 */
	public Boolean getEstaEnMora() {
		return estaEnMora;
	}

	/**
	 * Sets the value for estaEnMora.
	 * @param estaEnMora
	 */
	public void setEstaEnMora(Boolean estaEnMora) {
		this.estaEnMora = estaEnMora;
	}

	/**
	 * Returns the value of esEmpleador.
	 * @return esEmpleador
	 */
	public Boolean getEsEmpleador() {
		return esEmpleador;
	}

	/**
	 * Sets the value for esEmpleador.
	 * @param esEmpleador
	 */
	public void setEsEmpleador(Boolean esEmpleador) {
		this.esEmpleador = esEmpleador;
	}

	/**
	 * Returns the value of estaAlDiaObligaciones.
	 * @return estaAlDiaObligaciones
	 */
	public Boolean getEstaAlDiaObligaciones() {
		return estaAlDiaObligaciones;
	}

	/**
	 * Sets the value for estaAlDiaObligaciones.
	 * @param estaAlDiaObligaciones
	 */
	public void setEstaAlDiaObligaciones(Boolean estaAlDiaObligaciones) {
		this.estaAlDiaObligaciones = estaAlDiaObligaciones;
	}

	/**
	 * Returns the value of esPensionista.
	 * @return esPensionista
	 */
	public Boolean getEsPensionista() {
		return esPensionista;
	}

	/**
	 * Sets the value for esPensionista.
	 * @param esPensionista
	 */
	public void setEsPensionista(Boolean esPensionista) {
		this.esPensionista = esPensionista;
	}

	/**
	 * Returns the value of tieneCuentaBancariaAut.
	 * @return tieneCuentaBancariaAut
	 */
	public Boolean getTieneCuentaBancariaAut() {
		return tieneCuentaBancariaAut;
	}

	/**
	 * Sets the value for tieneCuentaBancariaAut.
	 * @param tieneCuentaBancariaAut
	 */
	public void setTieneCuentaBancariaAut(Boolean tieneCuentaBancariaAut) {
		this.tieneCuentaBancariaAut = tieneCuentaBancariaAut;
	}

	/**
	 * Returns the value of tieneCreditoVigenteMismaCuenta.
	 * @return tieneCreditoVigenteMismaCuenta
	 */
	public Boolean getTieneCreditoVigenteMismaCuenta() {
		return tieneCreditoVigenteMismaCuenta;
	}

	/**
	 * Sets the value for tieneCreditoVigenteMismaCuenta.
	 * @param tieneCreditoVigenteMismaCuenta
	 */
	public void setTieneCreditoVigenteMismaCuenta(Boolean tieneCreditoVigenteMismaCuenta) {
		this.tieneCreditoVigenteMismaCuenta = tieneCreditoVigenteMismaCuenta;
	}

	/**
	 * Returns the value of esVoluntario.
	 * @return esVoluntario
	 */
	public Boolean getEsVoluntario() {
		return esVoluntario;
	}

	/**
	 * Sets the value for esVoluntario.
	 * @param esVoluntario
	 */
	public void setEsVoluntario(Boolean esVoluntario) {
		this.esVoluntario = esVoluntario;
	}

	/**
	 * Returns the value of estaActivo.
	 * @return estaActivo
	 */
	public Boolean getEstaActivo() {
		return estaActivo;
	}

	/**
	 * Sets the value for estaActivo.
	 * @param estaActivo
	 */
	public void setEstaActivo(Boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

	/**
	 * Returns the value of calPositivaBuroCredito.
	 * @return calPositivaBuroCredito
	 */
	public String getCalificacionBuroCredito() {
		return calificacionBuroCredito;
	}

	/**
	 * Sets the value for calPositivaBuroCredito.
	 * @param calificacionBuroCredito
	 */
	public void setCalificacionBuroCredito(String calificacionBuroCredito) {
		this.calificacionBuroCredito = calificacionBuroCredito;
	}

	/**
	 * Returns the value of tieneSolicitudCesantia.
	 * @return tieneSolicitudCesantia
	 */
	public Boolean getTieneSolicitudCesantia() {
		return tieneSolicitudCesantia;
	}

	/**
	 * Sets the value for tieneSolicitudCesantia.
	 * @param tieneSolicitudCesantia
	 */
	public void setTieneSolicitudCesantia(Boolean tieneSolicitudCesantia) {
		this.tieneSolicitudCesantia = tieneSolicitudCesantia;
	}

	public String getCodigoRelacionTrabajo() {
		return codigoRelacionTrabajo;
	}

	public void setCodigoRelacionTrabajo(String codigoRelacionTrabajo) {
		this.codigoRelacionTrabajo = codigoRelacionTrabajo;
	}

}