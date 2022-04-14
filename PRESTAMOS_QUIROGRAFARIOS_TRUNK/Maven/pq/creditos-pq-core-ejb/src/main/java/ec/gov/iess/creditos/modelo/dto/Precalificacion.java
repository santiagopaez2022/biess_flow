package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ec.fin.biess.listaobservados.modelo.persistence.BloqueoListaControl;
import ec.gov.iess.creditos.enumeracion.TipoPrecalificacionEnum;

/**
 * 
 * <b> Propiedades de la calificación general. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: cbastidas $, $Date: 03/10/2011 $]
 *          </p>
 */

public class Precalificacion implements Serializable {

	private static final long serialVersionUID = -7100079765744250423L;
	protected List<Requisito> requisitos;
	protected boolean calificado;
	protected boolean calificadoBuroCredito;
	protected List<DetalleBuroCredito> detallesBuro;
	private ValidarRequisitosPrecalificacion validarRequisitosPrecalificacion;
	private Garantia garantia;
	private BigDecimal cumpleComprobante;
	private BigDecimal cumplePqTramite;
	private BigDecimal cumpleLiquidacionDebito;
	private Long numImposicionesCon;
	private Long numImposiciones;

	// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
	private String tipoListaControl;

	private boolean enListaObservados;

	private boolean enListaObservadosCONSEP;

	private boolean enListaObservadosOTROS;
	
	private boolean enListaObservadosPEP;
	
	private Date fecharegistroListaObservados;
	
	private BloqueoListaControl bloqueoListaControl;

	private String ipUsuarioLogueado;
	
	private String idUsuarioLogueado;
	
	private TipoPrecalificacionEnum tipoPrecalificacionEnum = TipoPrecalificacionEnum.CREDITO;
	
	private BigDecimal sueldoPromedio;
	
	//Migracion Cartera GAF
	/**
	 * Saldos de creditos Exigibles del SAC
	 */
	private BigDecimal sumaSaldosCred;
	
	/**
	 * Validacion si empleador esta en mora EPL hasta que salga califica 3
	 */
	private boolean empleadorEstaMoraEpl;
	
	/**
	 * Bandera para habilitar novacion de afiliado
	 */
	private boolean habilitaNovacionAfi;
	
	/**
	 * Bandera para habilitar novacion jubilado
	 */
	private boolean habilitaNovacionJubi;
	
	
	
	public List<Requisito> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<Requisito> requisitos) {
		this.requisitos = requisitos;
	}

	public boolean isCalificado() {
		return calificado;
	}

	public void setCalificado(boolean calificado) {
		this.calificado = calificado;
	}

	public boolean isCalificadoBuroCredito() {
		return calificadoBuroCredito;
	}

	public void setCalificadoBuroCredito(boolean calificadoBuroCredito) {
		this.calificadoBuroCredito = calificadoBuroCredito;
	}

	public List<DetalleBuroCredito> getDetallesBuro() {
		return detallesBuro;
	}

	public void setDetallesBuro(List<DetalleBuroCredito> detallesBuro) {
		this.detallesBuro = detallesBuro;
	}

	public ValidarRequisitosPrecalificacion getValidarRequisitosPrecalificacion() {
		return validarRequisitosPrecalificacion;
	}

	public void setValidarRequisitosPrecalificacion(ValidarRequisitosPrecalificacion validarRequisitosPrecalificacion) {
		this.validarRequisitosPrecalificacion = validarRequisitosPrecalificacion;
	}

	public Garantia getGarantia() {
		return garantia;
	}

	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}

	public BigDecimal getCumpleComprobante() {
		return cumpleComprobante;
	}

	public void setCumpleComprobante(BigDecimal cumpleComprobante) {
		this.cumpleComprobante = cumpleComprobante;
	}

	public BigDecimal getCumplePqTramite() {
		return cumplePqTramite;
	}

	public void setCumplePqTramite(BigDecimal cumplePqTramite) {
		this.cumplePqTramite = cumplePqTramite;
	}

	public BigDecimal getCumpleLiquidacionDebito() {
		return cumpleLiquidacionDebito;
	}

	public void setCumpleLiquidacionDebito(BigDecimal cumpleLiquidacionDebito) {
		this.cumpleLiquidacionDebito = cumpleLiquidacionDebito;
	}

	public Long getNumImposicionesCon() {
		return numImposicionesCon;
	}

	public void setNumImposicionesCon(Long numImposicionesCon) {
		this.numImposicionesCon = numImposicionesCon;
	}

	public Long getNumImposiciones() {
		return numImposiciones;
	}

	public void setNumImposiciones(Long numImposiciones) {
		this.numImposiciones = numImposiciones;
	}

	/**
	 * @return the tipoListaControl
	 */
	public String getTipoListaControl() {
		return tipoListaControl;
	}

	/**
	 * @param tipoListaControl
	 *            the tipoListaControl to set
	 */
	public void setTipoListaControl(String tipoListaControl) {
		this.tipoListaControl = tipoListaControl;
	}

	/**
	 * @return the fecharegistroListaObservados
	 */
	public Date getFecharegistroListaObservados() {
		return fecharegistroListaObservados;
	}

	/**
	 * @param fecharegistroListaObservados the fecharegistroListaObservados to set
	 */
	public void setFecharegistroListaObservados(Date fecharegistroListaObservados) {
		this.fecharegistroListaObservados = fecharegistroListaObservados;
	}

	/**
	 * @return the enListaObservados
	 */
	public boolean isEnListaObservados() {
		return enListaObservados;
	}

	/**
	 * @param enListaObservados the enListaObservados to set
	 */
	public void setEnListaObservados(boolean enListaObservados) {
		this.enListaObservados = enListaObservados;
	}

	/**
	 * @return the enListaObservadosCONSEP
	 */
	public boolean isEnListaObservadosCONSEP() {
		return enListaObservadosCONSEP;
	}

	/**
	 * @param enListaObservadosCONSEP the enListaObservadosCONSEP to set
	 */
	public void setEnListaObservadosCONSEP(boolean enListaObservadosCONSEP) {
		this.enListaObservadosCONSEP = enListaObservadosCONSEP;
	}

	/**
	 * @return the enListaObservadosOTROS
	 */
	public boolean isEnListaObservadosOTROS() {
		return enListaObservadosOTROS;
	}

	/**
	 * @param enListaObservadosOTROS the enListaObservadosOTROS to set
	 */
	public void setEnListaObservadosOTROS(boolean enListaObservadosOTROS) {
		this.enListaObservadosOTROS = enListaObservadosOTROS;
	}

	/**
	 * @return the enListaObservadosPEP
	 */
	public boolean isEnListaObservadosPEP() {
		return enListaObservadosPEP;
	}

	/**
	 * @param enListaObservadosPEP the enListaObservadosPEP to set
	 */
	public void setEnListaObservadosPEP(boolean enListaObservadosPEP) {
		this.enListaObservadosPEP = enListaObservadosPEP;
	}

	/**
	 * @return the bloqueoListaControl
	 */
	public BloqueoListaControl getBloqueoListaControl() {
		return bloqueoListaControl;
	}

	/**
	 * @param bloqueoListaControl the bloqueoListaControl to set
	 */
	public void setBloqueoListaControl(BloqueoListaControl bloqueoListaControl) {
		this.bloqueoListaControl = bloqueoListaControl;
	}

	/**
	 * @return the ipUsuarioLogueado
	 */
	public String getIpUsuarioLogueado() {
		return ipUsuarioLogueado;
	}

	/**
	 * @param ipUsuarioLogueado the ipUsuarioLogueado to set
	 */
	public void setIpUsuarioLogueado(String ipUsuarioLogueado) {
		this.ipUsuarioLogueado = ipUsuarioLogueado;
	}

	/**
	 * @return the idUsuarioLogueado
	 */
	public String getIdUsuarioLogueado() {
		return idUsuarioLogueado;
	}

	/**
	 * @param idUsuarioLogueado the idUsuarioLogueado to set
	 */
	public void setIdUsuarioLogueado(String idUsuarioLogueado) {
		this.idUsuarioLogueado = idUsuarioLogueado;
	}

	public TipoPrecalificacionEnum getTipoPrecalificacionEnum() {
		return tipoPrecalificacionEnum;
	}

	public void setTipoPrecalificacionEnum(TipoPrecalificacionEnum tipoPrecalificacionEnum) {
		this.tipoPrecalificacionEnum = tipoPrecalificacionEnum;
	}

	public BigDecimal getSueldoPromedio() {
		return sueldoPromedio;
	}

	public void setSueldoPromedio(BigDecimal sueldoPromedio) {
		this.sueldoPromedio = sueldoPromedio;
	}

	public BigDecimal getSumaSaldosCred() {
		return sumaSaldosCred;
	}

	public void setSumaSaldosCred(BigDecimal sumaSaldosCred) {
		this.sumaSaldosCred = sumaSaldosCred;
	}

	public boolean isEmpleadorEstaMoraEpl() {
		return empleadorEstaMoraEpl;
	}

	public void setEmpleadorEstaMoraEpl(boolean empleadorEstaMoraEpl) {
		this.empleadorEstaMoraEpl = empleadorEstaMoraEpl;
	}

	public boolean isHabilitaNovacionAfi() {
		return habilitaNovacionAfi;
	}

	public void setHabilitaNovacionAfi(boolean habilitaNovacionAfi) {
		this.habilitaNovacionAfi = habilitaNovacionAfi;
	}

	public boolean isHabilitaNovacionJubi() {
		return habilitaNovacionJubi;
	}

	public void setHabilitaNovacionJubi(boolean habilitaNovacionJubi) {
		this.habilitaNovacionJubi = habilitaNovacionJubi;
	}

}
