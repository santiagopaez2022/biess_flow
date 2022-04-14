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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.SaldoFondosDetallePK;

/**
 * <b> Clase para persistencia de la tabla: CRE_SALDOSFONDOSDET_TBL </b>
 * 
 * @author Gabriel Eguiguren
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: etarambis $, $Date: 2010/12/20 16:55:55 $]
 *          </p>
 */

@Entity
@Table(name = "CRE_SALDOSFONDOSDET_TBL")
@NamedQueries( { 
	@NamedQuery(name = "SaldoFondosDetalle.detalleSPI", 
	query = "select d from SaldoFondosDetalle d where "
	      + "d.clave.codigoCuentaFondo = :codigo "
	      + "and d.clave.fechaProceso = :fecha and d.estado = 'DIS' " 
	      +	"order by d.saldoFondos.cuentaFondos.cuentaBanco ") })
@NamedNativeQueries( { 
	@NamedNativeQuery(name = "SaldoFondosDetalle.consultarFondosPorFecha", 
	query = "SELECT * FROM cre_saldosfondosdet_tbl d " +
	"WHERE TRUNC (d.sf_fec_proceso) = TO_DATE (:fecha, 'DD/MM/YYYY') " +
	"AND d.sf_fec_registro = (SELECT MAX (t.sf_fec_registro) " +
			"FROM cre_saldosfondosdet_tbl t " +
			"WHERE d.sf_fec_proceso = t.sf_fec_proceso AND d.cf_codigo = t.cf_codigo) " +
	"ORDER BY d.CF_CODIGO", 
	resultClass = SaldoFondosDetalle.class) })
public class SaldoFondosDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SaldoFondosDetallePK clave;

	@Column(name = "SF_CEDULA_USUARIO")
	private String cedulaUsuario;

	@Column(name = "SF_ESTADO")
	private String estado;

	@Column(name = "SF_MONTO")
	private BigDecimal montoUtilizado;

	@Column(name = "SF_OBSERVACION")
	private String observacion;

	@Column(name = "SF_SALDO")
	private BigDecimal saldoDisponible;

	@Column(name = "SF_ARCHIVO_SPI")
	private String archivo;

	// bi-directional many-to-one association to SaldosFondos
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "CF_CODIGO", referencedColumnName = "CF_CODIGO", insertable = false, updatable = false),
			@JoinColumn(name = "SF_FEC_PROCESO", referencedColumnName = "SF_FEC_PROCESO", insertable = false, updatable = false) })
	private SaldoFondos saldoFondos;

	// bi-directional many-to-one association to DetalleCatalogos
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "CA_CATALOGO", referencedColumnName = "CA_CATALOGO"),
			@JoinColumn(name = "DC_CODIGO", referencedColumnName = "DC_CODIGO") })
	private DetalleCatalogos detalleCatalogos;

	public SaldoFondosDetalle() {
	}

	/**
	 * @return the cedulaUsuario
	 */
	public String getCedulaUsuario() {
		return cedulaUsuario;
	}

	/**
	 * @param cedulaUsuario
	 *            the cedulaUsuario to set
	 */
	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
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
	 * @return the montoUtilizado
	 */
	public BigDecimal getMontoUtilizado() {
		return montoUtilizado;
	}

	/**
	 * @param montoUtilizado
	 *            the montoUtilizado to set
	 */
	public void setMontoUtilizado(BigDecimal montoUtilizado) {
		this.montoUtilizado = montoUtilizado;
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
	 * @return the saldoDisponible
	 */
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}

	/**
	 * @param saldoDisponible
	 *            the saldoDisponible to set
	 */
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	/**
	 * @return the saldoFondos
	 */
	public SaldoFondos getSaldoFondos() {
		return saldoFondos;
	}

	/**
	 * @param saldoFondos
	 *            the saldoFondos to set
	 */
	public void setSaldoFondos(SaldoFondos saldoFondos) {
		this.saldoFondos = saldoFondos;
	}

	/**
	 * @return the detalleCatalogos
	 */
	public DetalleCatalogos getDetalleCatalogos() {
		return detalleCatalogos;
	}

	/**
	 * @param detalleCatalogos
	 *            the detalleCatalogos to set
	 */
	public void setDetalleCatalogos(DetalleCatalogos detalleCatalogos) {
		this.detalleCatalogos = detalleCatalogos;
	}

	/**
	 * @return the clave
	 */
	public SaldoFondosDetallePK getClave() {
		return clave;
	}

	/**
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(SaldoFondosDetallePK clave) {
		this.clave = clave;
	}

	/**
	 * @return the archivo
	 */
	public String getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo
	 *            the archivo to set
	 */
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

}