/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <b> Clase para persistencia de la tabla: CRE_CUENTASFONDOS_TBL. </b>
 * 
 * @author Gabriel Eguiguren
 * @version $Revision: 1.4 $
 *          <p>
 *          [$Author: etarambis $, $Date: 2011/03/14 14:52:08 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_CUENTASFONDOS_TBL")
@NamedQueries({
		@NamedQuery(name = "CuentaFondos.consultarListado", query = "SELECT c FROM CuentaFondos c WHERE "
				+ "c.estado = 'A' order by c.cuentaBanco "),
		@NamedQuery(name = "CuentaFondos.obtenerCuentasPH", query = "SELECT o FROM CuentaFondos o WHERE o.estado=:estado AND "
				+ "o.detalleCatalogo.id.caCatalogo=:catalogo AND o.detalleCatalogo.id.dcCodigo in (:codCuenta) ORDER BY o.ordenProceso"),
		@NamedQuery(name = "CuentaFondos.consultarPorCuenta", query = "SELECT c FROM CuentaFondos c WHERE "
				+ "c.estado = 'A' and c.cuentaBanco = :cuentaBanco") })
public class CuentaFondos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CF_CODIGO")
	private Long codigoCuentaFondo;

	@Column(name = "CF_CUENTA_BANCO")
	private String cuentaBanco;

	@Column(name = "CF_ESTADO")
	private String estado;

	@Column(name = "CF_NOMBRE")
	private String nombre;

	@Column(name = "CF_OBSERVACION")
	private String observacion;

	@Column(name = "CF_ORDEN")
	private Long ordenProceso;

	@Column(name = "CF_PLAZO")
	private String plazo;

	@Transient
	private Date fechaProceso;

	@Transient
	private BigDecimal saldoInicial;

	@Transient
	private String estadoFondo;

	// asociacion a DetalleCatalogos
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CA_CATALOGO", referencedColumnName = "CA_CATALOGO"),
			@JoinColumn(name = "DC_CODIGO", referencedColumnName = "DC_CODIGO") })
	private DetalleCatalogos detalleCatalogo;

	public CuentaFondos() {
	}

	/**
	 * @return the cuentaBanco
	 */
	public String getCuentaBanco() {
		return cuentaBanco;
	}

	/**
	 * @param cuentaBanco
	 *            the cuentaBanco to set
	 */
	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
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

	/**
	 * @return the ordenProceso
	 */

	public Long getOrdenProceso() {
		return ordenProceso;
	}

	/**
	 * @param ordenProceso
	 *            the ordenProceso to set
	 */

	public void setOrdenProceso(Long ordenProceso) {
		this.ordenProceso = ordenProceso;
	}

	/**
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * @return the codigoCuentaFondo
	 */
	public Long getCodigoCuentaFondo() {
		return codigoCuentaFondo;
	}

	/**
	 * @param codigoCuentaFondo
	 *            the codigoCuentaFondo to set
	 */
	public void setCodigoCuentaFondo(Long codigoCuentaFondo) {
		this.codigoCuentaFondo = codigoCuentaFondo;
	}

	/**
	 * @return the fechaProceso
	 */
	public java.util.Date getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso
	 *            the fechaProceso to set
	 */
	public void setFechaProceso(java.util.Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return the saldoInicial
	 */
	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	/**
	 * @param saldoInicial
	 *            the saldoInicial to set
	 */
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	/**
	 * @return the estadoFondo
	 */
	public String getEstadoFondo() {
		return estadoFondo;
	}

	/**
	 * @param estadoFondo
	 *            the estadoFondo to set
	 */
	public void setEstadoFondo(String estadoFondo) {
		this.estadoFondo = estadoFondo;
	}

	/**
	 * @return the detalleCatalogo
	 */
	public DetalleCatalogos getDetalleCatalogo() {
		return detalleCatalogo;
	}

	/**
	 * @param detalleCatalogo
	 *            the detalleCatalogo to set
	 */
	public void setDetalleCatalogo(DetalleCatalogos detalleCatalogo) {
		this.detalleCatalogo = detalleCatalogo;
	}

}