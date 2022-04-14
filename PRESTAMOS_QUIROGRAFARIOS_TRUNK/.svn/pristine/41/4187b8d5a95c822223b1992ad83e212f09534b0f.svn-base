/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.creditos.modelo.persistencia.pk.SaldoFondosPK;

/**
 * <b> Clase para persistencia de la tabla: PCO_DETALLE_CATALOGOS_TBL </b>
 * 
 * @author Gabriel Eguiguren
 * @version $Revision: 1.5 $
 *          <p>
 *          [$Author: etarambis $, $Date: 2011/03/14 14:52:13 $]
 *          </p>
 * 
 */
@Entity
@Table(name = "CRE_SALDOSFONDOS_TBL")
@NamedQueries( {
		@NamedQuery(name = "SaldoFondos.consultarFondosPorFecha", query = "SELECT s FROM SaldoFondos s WHERE "
				+ "s.clave.fechaProceso = :fecha " + "order by s.cuentaFondos.cuentaBanco "),
		@NamedQuery(name = "SaldoFondos.obtenerFondoPH", query = "SELECT o FROM SaldoFondos o WHERE o.clave.codigoCuentaFondo in (:codFondo) "
				+ "AND o.clave.fechaProceso=TO_DATE(:fechaAct,'dd/MM/yyyy') AND o.estado in (:estados) AND o.saldoFinal>:valor ORDER BY "
				+ "o.cuentaFondos.ordenProceso") })
public class SaldoFondos implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SaldoFondosPK clave;

	@Column(name = "SF_ESTADO")
	private String estado;

	@Column(name = "SF_SALDO_FINAL")
	private BigDecimal saldoFinal;

	@Column(name = "SF_SALDO_INICIAL")
	private BigDecimal saldoInicial;

	// asociacion a CuentasFondos
	@ManyToOne
	@JoinColumn(name = "CF_CODIGO", insertable = false, updatable = false)
	private CuentaFondos cuentaFondos;

	@Transient
	private BigDecimal saldoReversar;

	public SaldoFondos() {
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
	 * @return the saldoFinal
	 */
	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}

	/**
	 * @param saldoFinal
	 *            the saldoFinal to set
	 */
	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
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
	 * @return the cuentaFondos
	 */
	public CuentaFondos getCuentaFondos() {
		return cuentaFondos;
	}

	/**
	 * @param cuentaFondos
	 *            the cuentaFondos to set
	 */
	public void setCuentaFondos(CuentaFondos cuentaFondos) {
		this.cuentaFondos = cuentaFondos;
	}

	/**
	 * @return the clave
	 */
	public SaldoFondosPK getClave() {
		return clave;
	}

	/**
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(SaldoFondosPK clave) {
		this.clave = clave;
	}

	/**
	 * @return the saldoReversar
	 */

	public BigDecimal getSaldoReversar() {
		return saldoReversar;
	}

	/**
	 * @param saldoReversar
	 *            the saldoReversar to set
	 */

	public void setSaldoReversar(BigDecimal saldoReversar) {
		this.saldoReversar = saldoReversar;
	}
}