/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.creditos.modelo.persistencia.pk.TransaccionRecaudacionPk;

/**
 * 
 * <b> Persistencia de tabla de transacciones para Recaudacion PQ </b>
 * 
 * @author Roberto Guizado
 * @version $Revision: 1.8 $
 *          <p>
 *          [$Author: roberto.guizado $, $Date: 2018/10/16$]
 *          </p>
 */
@Entity
@Table(name = "REC_TRANSACCION_TBL")
@NamedQueries({
		@NamedQuery(name = "TransaccionRecaudacion.obtenerTransaccionCedulaNut", query = "SELECT o FROM TransaccionRecaudacion o "
				+ "WHERE o.trNut=:nut AND o.cedula=:cedula"),
		@NamedQuery(name = "TransaccionRecaudacion.obtenerTransaccionCedulaNutEpl", query = "SELECT o FROM TransaccionRecaudacion o "
				+ "WHERE o.trNut=:nut AND o.cedula=:cedula and o.estado in ('EPL','ENV','EMI') and o.pk.trIdtipotransaccion in (14,15) "),
		@NamedQuery(name = "TransaccionRecaudacion.consultarTransPlanilla", query = "SELECT o FROM TransaccionRecaudacion o "
				+ " WHERE o.trNut = :nut " + " AND o.cedula = :cedula" + " AND o.codigoSucursal = :codigoSucursal" + " AND o.rucEmpresa = :rucEmpresa"
				+ " AND o.anioPeriodo = :anioPeriodo" + " AND o.mesPeriodo = :mesPeriodo"
				+ " AND o.secuencialDetallePlanilla = :secuencialDetallePlanilla"
				+ " AND o.tipoTransaccionRecaudacion.ttIdtipotransaccion = :ttIdtipotransaccion "),
		@NamedQuery(name = "TransaccionRecaudacion.actualizarEstadoAjustePlanilla", query = "UPDATE TransaccionRecaudacion o "
				+ " SET o.estado = :estado,  o.fechaProceso = :fechaProceso, o.secuencialDetallePlanilla = :secuencia"
				+ " WHERE o.tipoTransaccionRecaudacion.ttIdtipotransaccion = :tipoTransaccion" + " AND o.pk.idTransaccion = :idTransaccion "),
		@NamedQuery(name = "TransaccionRecaudacion.consultarTransAjustePlanilla", query = "SELECT o FROM TransaccionRecaudacion o "
				+ " WHERE trNut = :nut " + " AND cedula = :cedula" + " AND codigoSucursal = :codigoSucursal" + " AND rucEmpresa = :rucEmpresa"
				+ " AND anioPeriodo = :anioPeriodo" + " AND mesPeriodo = :mesPeriodo" + " AND secuencialDetallePlanilla = :secuencialDetallePlanilla"
				+ " AND tipoTransaccionRecaudacion.ttIdtipotransaccion = :ttIdtipotransaccion "),
		@NamedQuery(name = "TransaccionRecaudacion.obtenerTransaccionPorCedula", query = "SELECT o FROM TransaccionRecaudacion o  "
				+ "WHERE o.cedula=:cedula and o.pk.trIdtipotransaccion in (14,15)"),
		
		@NamedQuery(name = "TransaccionRecaudacion.consultarTransPeriodo", query = "SELECT o FROM TransaccionRecaudacion o "
				+ " WHERE trNut = :nut " + " AND cedula = :cedula"  
				+ " AND anioPeriodo = :anioPeriodo" + " AND mesPeriodo = :mesPeriodo and o.estado in ('EPL','ENV','EMI') and o.pk.trIdtipotransaccion in (14,15)" ),
		

})
public class TransaccionRecaudacion implements Serializable {
	@EmbeddedId
	private TransaccionRecaudacionPk pk;

	@Column(name = "TR_NUT")
	private BigDecimal trNut;

	@Column(name = "TR_CEDULA")
	private String cedula;

	@Column(name = "TR_CODSUC")
	private String codigoSucursal;

	@Column(name = "TR_RUCEMP")
	private String rucEmpresa;

	@Column(name = "TR_ANIPER")
	private BigDecimal anioPeriodo;

	@Column(name = "TR_MESPER")
	private BigDecimal mesPeriodo;

	@Column(name = "TR_MAXIMODEBITAR")
	private BigDecimal maximoDebitar;

	@Column(name = "TR_IDGAF")
	private BigDecimal trIdgaf;

	@Column(name = "TR_ANIODIVIDENDO")
	private BigDecimal anioDividendo;

	@Column(name = "TR_MESDIVIDENDO")
	private BigDecimal mesDividendo;

	@Column(name = "TR_VALORCOBRAR")
	private BigDecimal valorCobrar;

	@Column(name = "TR_VALORRECAUDADO")
	private BigDecimal valorRecaudado;

	@Column(name = "TR_INTERESESMORA")
	private BigDecimal interesesMora;

	@Column(name = "TR_ESTADO")
	private String estado;

	@Column(name = "TR_FECHAPROCESO")
	private Date fechaProceso;

	@Column(name = "TR_FECHAMAXIMAPAGO")
	private Date fechaMaximaPago;

	@Column(name = "TR_REFERENCIACANCELACION")
	private BigDecimal referenciaCancelacion;

	@Column(name = "TR_FECHACANCELACION")
	private Date fechaCancelacion;

	@Column(name = "TR_CEDULASOLIDARIA")
	private String cedulaSolidaria;

	@Column(name = "TR_OBSERVACIONES")
	private String observaciones;

	@Column(name = "TR_CODCOMPAG")
	private String codigoComprobantePago;

	@Column(name = "TR_SECPLADET")
	private BigDecimal secuencialDetallePlanilla;

	@Column(name = "TR_NUMSOLSER")
	private BigDecimal numeroSolicitudServida;

	@Column(name = "TR_CODTIPSOLSER")
	private BigDecimal codigoTipoSolicitudServida;

	@Column(name = "TR_SECUENCIAAVISO")
	private BigDecimal secuenciaAviso;

	@Column(name = "TR_VALORDIVIDENDO")
	private BigDecimal valorDividendo;

	@Column(name = "TR_IDGAFSOLIDARIO")
	private BigDecimal trIdGafSolidario;

	@Transient
	private String estadoDesc;

	@Transient
	private String observacion;

	@ManyToOne
	@JoinColumn(name = "TR_IDTIPOTRANSACCION", nullable = false, insertable = false, updatable = false)
	private TipoTransaccionRecaudacion tipoTransaccionRecaudacion;

	private static final long serialVersionUID = 1L;

	public TransaccionRecaudacion() {
		super();
	}

	public TransaccionRecaudacionPk getPk() {
		return this.pk;
	}

	public void setPk(TransaccionRecaudacionPk pk) {
		this.pk = pk;
	}

	public BigDecimal getTrNut() {
		return this.trNut;
	}

	public void setTrNut(BigDecimal trNut) {
		this.trNut = trNut;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String trCedula) {
		this.cedula = trCedula;
	}

	public String getCodigoSucursal() {
		return this.codigoSucursal;
	}

	public void setCodigoSucursal(String trCodsuc) {
		this.codigoSucursal = trCodsuc;
	}

	public String getRucEmpresa() {
		return this.rucEmpresa;
	}

	public void setRucEmpresa(String trRucemp) {
		this.rucEmpresa = trRucemp;
	}

	public BigDecimal getAnioPeriodo() {
		return this.anioPeriodo;
	}

	public void setAnioPeriodo(BigDecimal trAniper) {
		this.anioPeriodo = trAniper;
	}

	public BigDecimal getMesPeriodo() {
		return this.mesPeriodo;
	}

	public void setMesPeriodo(BigDecimal trMesper) {
		this.mesPeriodo = trMesper;
	}

	public BigDecimal getMaximoDebitar() {
		return this.maximoDebitar;
	}

	public void setMaximoDebitar(BigDecimal trMaximodebitar) {
		this.maximoDebitar = trMaximodebitar;
	}

	public BigDecimal getTrIdgaf() {
		return this.trIdgaf;
	}

	public void setTrIdgaf(BigDecimal trIdgaf) {
		this.trIdgaf = trIdgaf;
	}

	public BigDecimal getAnioDividendo() {
		return this.anioDividendo;
	}

	public void setAnioDividendo(BigDecimal trAniodividendo) {
		this.anioDividendo = trAniodividendo;
	}

	public BigDecimal getMesDividendo() {
		return this.mesDividendo;
	}

	public void setMesDividendo(BigDecimal trMesdividendo) {
		this.mesDividendo = trMesdividendo;
	}

	public BigDecimal getValorCobrar() {
		return this.valorCobrar;
	}

	public void setValorCobrar(BigDecimal trValorcobrar) {
		this.valorCobrar = trValorcobrar;
	}

	public BigDecimal getValorRecaudado() {
		return this.valorRecaudado;
	}

	public void setValorRecaudado(BigDecimal trValorrecaudado) {
		this.valorRecaudado = trValorrecaudado;
	}

	public BigDecimal getInteresesMora() {
		return this.interesesMora;
	}

	public void setInteresesMora(BigDecimal trInteresesmora) {
		this.interesesMora = trInteresesmora;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String trEstado) {
		this.estado = trEstado;
	}

	public Date getFechaProceso() {
		return this.fechaProceso;
	}

	public void setFechaProceso(Date trFechaproceso) {
		this.fechaProceso = trFechaproceso;
	}

	public Date getFechaMaximaPago() {
		return this.fechaMaximaPago;
	}

	public void setFechaMaximaPago(Date trFechamaximapago) {
		this.fechaMaximaPago = trFechamaximapago;
	}

	public BigDecimal getReferenciaCancelacion() {
		return this.referenciaCancelacion;
	}

	public void setReferenciaCancelacion(BigDecimal trReferenciacancelacion) {
		this.referenciaCancelacion = trReferenciacancelacion;
	}

	public Date getFechaCancelacion() {
		return this.fechaCancelacion;
	}

	public void setFechaCancelacion(Date trFechacancelacion) {
		this.fechaCancelacion = trFechacancelacion;
	}

	public String getCedulaSolidaria() {
		return this.cedulaSolidaria;
	}

	public void setCedulaSolidaria(String trCedulasolidaria) {
		this.cedulaSolidaria = trCedulasolidaria;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String trObservaciones) {
		this.observaciones = trObservaciones;
	}

	public String getCodigoComprobantePago() {
		return this.codigoComprobantePago;
	}

	public void setCodigoComprobantePago(String trCodcompag) {
		this.codigoComprobantePago = trCodcompag;
	}

	public BigDecimal getSecuencialDetallePlanilla() {
		return this.secuencialDetallePlanilla;
	}

	public void setSecuencialDetallePlanilla(BigDecimal trSecpladet) {
		this.secuencialDetallePlanilla = trSecpladet;
	}

	public BigDecimal getNumeroSolicitudServida() {
		return this.numeroSolicitudServida;
	}

	public void setNumeroSolicitudServida(BigDecimal trNumsolser) {
		this.numeroSolicitudServida = trNumsolser;
	}

	public BigDecimal getCodigoTipoSolicitudServida() {
		return this.codigoTipoSolicitudServida;
	}

	public void setCodigoTipoSolicitudServida(BigDecimal trCodtipsolser) {
		this.codigoTipoSolicitudServida = trCodtipsolser;
	}

	public BigDecimal getSecuenciaAviso() {
		return this.secuenciaAviso;
	}

	public void setSecuenciaAviso(BigDecimal trSecuenciaaviso) {
		this.secuenciaAviso = trSecuenciaaviso;
	}

	public BigDecimal getValorDividendo() {
		return this.valorDividendo;
	}

	public void setValorDividendo(BigDecimal trValordividendo) {
		this.valorDividendo = trValordividendo;
	}

	public BigDecimal getTrIdGafSolidario() {
		return this.trIdGafSolidario;
	}

	public void setTrIdGafSolidario(BigDecimal trIdgafsolidario) {
		this.trIdGafSolidario = trIdgafsolidario;
	}

	public TipoTransaccionRecaudacion getTipoTransaccionRecaudacion() {
		return tipoTransaccionRecaudacion;
	}

	public void setTipoTransaccionRecaudacion(TipoTransaccionRecaudacion tipoTransaccionRecaudacion) {
		this.tipoTransaccionRecaudacion = tipoTransaccionRecaudacion;
		if (tipoTransaccionRecaudacion != null) {
			this.pk.setTrIdtipotransaccion(tipoTransaccionRecaudacion.getTtIdtipotransaccion());
		}
	}

	/**
	 * @return the estadoDesc
	 */
	public String getEstadoDesc() {
		return estadoDesc;
	}

	/**
	 * @param estadoDesc
	 *            the estadoDesc to set
	 */
	public void setEstadoDesc(String estadoDesc) {
		this.estadoDesc = estadoDesc;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
