/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * Datos de la liquidacion de gastos
 * 
 * @version 1.0
 * @author rsambrano
 * 
 */
@Entity
@Table(name = "CRE_LIQUIDACIONGTO_TBL")
@SequenceGenerator(name = "liquidacionGastoSeq", sequenceName = "CRE_LIQUIDACIONGTO_SEQ", initialValue = 1, allocationSize = 1)
@NamedQueries({
		@NamedQuery(name = "LiquidacionGasto.consultarSolicitudLiquidacion", query = "FROM LiquidacionGasto o where o.estado in ('SOL') "),
		@NamedQuery(name = "LiquidacionGasto.consultarSolicitudLiquidacionExistente", query = "FROM LiquidacionGasto o where o.nut = :nut and cedula = :cedula"),
		@NamedQuery(name = "LiquidacionGasto.consultarSolicitudLiquidacionGenerada", query = "select o FROM LiquidacionGasto o  "
				+ " join o.solicitudCredito s"
				+ " where o.estado in ('GEN') "
				+ " and s.solicitudCreditoPK.codtipsolser in (:codigosSolicitud)") })
public class LiquidacionGasto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD_LIQGTO")
	@GeneratedValue(generator = "liquidacionGastoSeq", strategy = GenerationType.SEQUENCE)
	private Long codliqgto;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER"),
			@JoinColumn(name = "NUMSOLSER", referencedColumnName = "NUMSOLSER") })
	private SolicitudCredito solicitudCredito;

	private Long nut;

	@Column(name = "TIPO_LIQUIDACION")
	private String tipoLiquidacion;

	@Column(name = "VAL_PORCENTAJEPAR")
	private BigDecimal porcentajeParticipacion;

	@Column(name = "VAL_GASTOSFIN")
	private BigDecimal montoFinanciado;

	@Column(name = "VAL_GASTOSPRO")
	private BigDecimal gastosProyectados;

	@Column(name = "VAL_GASTOSREA")
	private BigDecimal gastosFinales;

	@Column(name = "VAL_DIFERENCIA")
	private BigDecimal diferenciaGastos;

	@Column(name = "COD_FONDO")
	private String codigoFondo;

	@Column(name = "TIPO_SOLICITANTE")
	private String tipoSolicitante;

	@Column(name = "CEDULA")
	private String cedula;

	@Column(name = "ESTADO")
	private String estado;

	@Transient
	private boolean seleccionado;

	@Transient
	private String nombreCompleto;

	@Transient
	private String fondoPH;

	@Transient
	private String fondoGastos;

	@Transient
	private String cedulaSolidario;

	@Transient
	private BigDecimal saldoCapital;

	@Transient
	private BigDecimal cuotasRemanentes;

	@Transient
	private BigDecimal valorOriginal;

	@Transient
	private BigDecimal nuevoMontoFinanciado;

	/**
	 * @return the codliqgto
	 */
	public Long getCodliqgto() {
		return codliqgto;
	}

	/**
	 * @param codliqgto
	 *            the codliqgto to set
	 */
	public void setCodliqgto(Long codliqgto) {
		this.codliqgto = codliqgto;
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
	 * @return the nut
	 */
	public Long getNut() {
		return nut;
	}

	/**
	 * @param nut
	 *            the nut to set
	 */
	public void setNut(Long nut) {
		this.nut = nut;
	}

	/**
	 * @return the porcentajeParticipacion
	 */
	public BigDecimal getPorcentajeParticipacion() {
		return porcentajeParticipacion;
	}

	/**
	 * @param porcentajeParticipacion
	 *            the porcentajeParticipacion to set
	 */
	public void setPorcentajeParticipacion(BigDecimal porcentajeParticipacion) {
		this.porcentajeParticipacion = porcentajeParticipacion;
	}

	/**
	 * @return the gastosFinancieros
	 */
	public BigDecimal getMontoFinanciado() {
		return montoFinanciado;
	}

	/**
	 * @param gastosFinancieros
	 *            the gastosFinancieros to set
	 */
	public void setMontoFinanciado(BigDecimal gastosFinancieros) {
		this.montoFinanciado = gastosFinancieros;
	}

	/**
	 * @return the gastosProyectados
	 */
	public BigDecimal getGastosProyectados() {
		return gastosProyectados;
	}

	/**
	 * @param gastosProyectados
	 *            the gastosProyectados to set
	 */
	public void setGastosProyectados(BigDecimal gastosProyectados) {
		this.gastosProyectados = gastosProyectados;
	}

	/**
	 * @return the gastosReales
	 */
	public BigDecimal getGastosFinales() {
		return gastosFinales;
	}

	/**
	 * @param gastosReales
	 *            the gastosReales to set
	 */
	public void setGastosFinales(BigDecimal gastosReales) {
		this.gastosFinales = gastosReales;
	}

	/**
	 * @return the diferenciaGastos
	 */
	public BigDecimal getDiferenciaGastos() {
		return diferenciaGastos;
	}

	/**
	 * @param diferenciaGastos
	 *            the diferenciaGastos to set
	 */
	public void setDiferenciaGastos(BigDecimal diferenciaGastos) {
		this.diferenciaGastos = diferenciaGastos;
	}

	/**
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * @param codigoFondo
	 *            the codigoFondo to set
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * @return the tipoSolicitante
	 */
	public String getTipoSolicitante() {
		return tipoSolicitante;
	}

	/**
	 * @param tipoSolicitante
	 *            the tipoSolicitante to set
	 */
	public void setTipoSolicitante(String tipoSolicitante) {
		this.tipoSolicitante = tipoSolicitante;
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
	 * @return the seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}

	/**
	 * @param seleccionado
	 *            the seleccionado to set
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto
	 *            the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the fondo
	 */
	public String getFondoPH() {
		return fondoPH;
	}

	/**
	 * @param fondo
	 *            the fondo to set
	 */
	public void setFondoPH(String fondo) {
		this.fondoPH = fondo;
	}

	/**
	 * @return the fondoGastos
	 */
	public String getFondoGastos() {
		return fondoGastos;
	}

	/**
	 * @param fondoGastos
	 *            the fondoGastos to set
	 */
	public void setFondoGastos(String fondoGastos) {
		this.fondoGastos = fondoGastos;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoLiquidacion() {
		return tipoLiquidacion;
	}

	public void setTipoLiquidacion(String tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}

	public String getCedulaSolidario() {
		return cedulaSolidario;
	}

	public void setCedulaSolidario(String cedulaSolidario) {
		this.cedulaSolidario = cedulaSolidario;
	}

	public BigDecimal getSaldoCapital() {
		return saldoCapital;
	}

	public void setSaldoCapital(BigDecimal saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	public BigDecimal getCuotasRemanentes() {
		return cuotasRemanentes;
	}

	public void setCuotasRemanentes(BigDecimal cuotasRemanentes) {
		this.cuotasRemanentes = cuotasRemanentes;
	}

	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(BigDecimal valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public BigDecimal getNuevoMontoFinanciado() {
		return nuevoMontoFinanciado;
	}

	public void setNuevoMontoFinanciado(BigDecimal nuevoMontoFinanciado) {
		this.nuevoMontoFinanciado = nuevoMontoFinanciado;
	}

}