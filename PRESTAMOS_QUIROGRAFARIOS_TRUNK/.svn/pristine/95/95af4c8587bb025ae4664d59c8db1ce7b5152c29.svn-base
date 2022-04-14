/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import ec.gov.iess.creditos.enumeracion.EstadoComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.pk.ComprobantePagoPk;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  03/10/2011
 * @author pablo
 */
@Entity
@Table(name = "kscretcompagafi")
public class ComprobantePago implements Serializable {

	private static final long serialVersionUID = -6608984449020969306L;

	@EmbeddedId
	private ComprobantePagoPk pk;

	@Column(name = "RUCEMPINSFIN")
	private String rucInstitucionFinanciera;

	@Column(name = "VALPAGBAN")
	private BigDecimal valorPagarBanco;

	@Column(name = "FECDEPBAN")
	private Timestamp fecDepBan;

	@Column(name = "NUMDETCOMPAGAFI", nullable = false)
	private BigDecimal numDetComPagAfi;

	@Column(name = "FECPAGBAN")
	private Timestamp fechaPagoBanco;

	@Column(name = "FECTERCOMPAGAFI", nullable = false)
	private Timestamp fechaFinComprobante;

	@Column(name = "FECGENCOMPAGAFI", nullable = false)
	private Timestamp fechageneracionComprobante;

	@Column(name = "VALTOTCOMPAGAFI", nullable = false)
	private BigDecimal valorTotalComprobante;

	@Column(name = "CODESTCOMPAGAFI", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoComprobantePago estadoComprobante;

	@Column(name = "NUMAFI", nullable = false)
	private String numeroAfiliado;

	@Column(name = "CODPRECLA")
	private BigDecimal codprecla;

	@Column(name = "CODCONAFI")
	private String codConAfi;

	@Column(name = "CODTIPCONAFI")
	private String codTipConAfi;

	@Column(name = "ORDPREAFI")
	private BigDecimal ordPreAfi;

	@Column(name = "NUMPREAFI")
	private BigDecimal numPreAfi;

	@Column(name = "CODPRETIP")
	private BigDecimal codPretip;

	@Column(name = "INFUSUBAN")
	private String infUsuBan;

	@Column(name = "VALCOMPAGAFI", nullable = false)
	private BigDecimal valComPagAfi;

	@Column(name = "VALINTMORCOMPAGAFI", nullable = false)
	private BigDecimal interesMora;

	@Column(name = "VALDEDPAG", nullable = false)
	private BigDecimal valDedPag;

	@Column(name = "CODTIPDOCJUSPAG")
	private String codTipDocJusPag;

	@Column(name = "NUMDOCPAG")
	private String numeroDocumentoPago;

	@Column(name = "FECDOCPAG")
	private Timestamp fechaDocumentoPago;

	@Column(name = "CODSUCINSFIN")
	private String codigoSucursalIfi;

	@Column(name = "CODDIVPOL")
	private String divisionPolitica;

	@Column(name = "CODOFIIES")
	private String codOfiIes;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "NUMPREAFI", referencedColumnName = "NUMPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "ORDPREAFI", referencedColumnName = "ORDPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP", insertable = false, updatable = false),
			@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA", insertable = false, updatable = false) })	
	private Prestamo prestamo;

	@ManyToOne
	@JoinColumn(name = "NUMLIQPRE", referencedColumnName = "NUMLIQPRE")
	private LiquidacionPrestamo liquidacion;

	@ManyToOne
	@JoinColumn(name = "CODTIPCOMPAG", referencedColumnName = "CODTIPCOMPAG", insertable = false, updatable = false)
	private TipoComprobantePago tipoComprobante;

	@OneToMany(mappedBy = "comprobantePago")
	private List<ComprobantePagoDetalle> detalle;

	/**
	 * Returns the value of pk.
	 * @return pk
	 */
	public ComprobantePagoPk getPk() {
		return pk;
	}

	/**
	 * Sets the value for pk.
	 * @param pk
	 */
	public void setPk(ComprobantePagoPk pk) {
		this.pk = pk;
	}

	/**
	 * Returns the value of rucInstitucionFinanciera.
	 * @return rucInstitucionFinanciera
	 */
	public String getRucInstitucionFinanciera() {
		return rucInstitucionFinanciera;
	}

	/**
	 * Sets the value for rucInstitucionFinanciera.
	 * @param rucInstitucionFinanciera
	 */
	public void setRucInstitucionFinanciera(String rucInstitucionFinanciera) {
		this.rucInstitucionFinanciera = rucInstitucionFinanciera;
	}

	/**
	 * Returns the value of valorPagarBanco.
	 * @return valorPagarBanco
	 */
	public BigDecimal getValorPagarBanco() {
		return valorPagarBanco;
	}

	/**
	 * Sets the value for valorPagarBanco.
	 * @param valorPagarBanco
	 */
	public void setValorPagarBanco(BigDecimal valorPagarBanco) {
		this.valorPagarBanco = valorPagarBanco;
	}

	/**
	 * Returns the value of fECDEPBAN.
	 * @return fECDEPBAN
	 */
	public Timestamp getFecDepBan() {
		return fecDepBan;
	}

	/**
	 * Sets the value for fECDEPBAN.
	 * @param fECDEPBAN
	 */
	public void setFecDepBan(Timestamp fecdepban) {
		fecDepBan = fecdepban;
	}

	/**
	 * Returns the value of nUMDETCOMPAGAFI.
	 * @return nUMDETCOMPAGAFI
	 */
	public BigDecimal getNumDetComPagAfi() {
		return numDetComPagAfi;
	}

	/**
	 * Sets the value for nUMDETCOMPAGAFI.
	 * @param nUMDETCOMPAGAFI
	 */
	public void setNumDetComPagAfi(BigDecimal numdetcompagafi) {
		numDetComPagAfi = numdetcompagafi;
	}

	/**
	 * Returns the value of fechaPagoBanco.
	 * @return fechaPagoBanco
	 */
	public Timestamp getFechaPagoBanco() {
		return fechaPagoBanco;
	}

	/**
	 * Sets the value for fechaPagoBanco.
	 * @param fechaPagoBanco
	 */
	public void setFechaPagoBanco(Timestamp fechaPagoBanco) {
		this.fechaPagoBanco = fechaPagoBanco;
	}

	/**
	 * Returns the value of fechaFinComprobante.
	 * @return fechaFinComprobante
	 */
	public Timestamp getFechaFinComprobante() {
		return fechaFinComprobante;
	}

	/**
	 * Sets the value for fechaFinComprobante.
	 * @param fechaFinComprobante
	 */
	public void setFechaFinComprobante(Timestamp fechaFinComprobante) {
		this.fechaFinComprobante = fechaFinComprobante;
	}

	/**
	 * Returns the value of fechageneracionComprobante.
	 * @return fechageneracionComprobante
	 */
	public Timestamp getFechageneracionComprobante() {
		return fechageneracionComprobante;
	}

	/**
	 * Sets the value for fechageneracionComprobante.
	 * @param fechageneracionComprobante
	 */
	public void setFechageneracionComprobante(Timestamp fechageneracionComprobante) {
		this.fechageneracionComprobante = fechageneracionComprobante;
	}

	/**
	 * Returns the value of valorTotalComprobante.
	 * @return valorTotalComprobante
	 */
	public BigDecimal getValorTotalComprobante() {
		return valorTotalComprobante;
	}

	/**
	 * Sets the value for valorTotalComprobante.
	 * @param valorTotalComprobante
	 */
	public void setValorTotalComprobante(BigDecimal valorTotalComprobante) {
		this.valorTotalComprobante = valorTotalComprobante;
	}

	/**
	 * Returns the value of estadoComprobante.
	 * @return estadoComprobante
	 */
	public EstadoComprobantePago getEstadoComprobante() {
		return estadoComprobante;
	}

	/**
	 * Sets the value for estadoComprobante.
	 * @param estadoComprobante
	 */
	public void setEstadoComprobante(EstadoComprobantePago estadoComprobante) {
		this.estadoComprobante = estadoComprobante;
	}

	/**
	 * Returns the value of numeroAfiliado.
	 * @return numeroAfiliado
	 */
	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}

	/**
	 * Sets the value for numeroAfiliado.
	 * @param numeroAfiliado
	 */
	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}

	/**
	 * Returns the value of codprecla.
	 * @return codprecla
	 */
	public BigDecimal getCodprecla() {
		return codprecla;
	}

	/**
	 * Sets the value for codprecla.
	 * @param codprecla
	 */
	public void setCodprecla(BigDecimal codprecla) {
		this.codprecla = codprecla;
	}

	/**
	 * Returns the value of codConAfi.
	 * @return codConAfi
	 */
	public String getCodConAfi() {
		return codConAfi;
	}

	/**
	 * Sets the value for codConAfi.
	 * @param codConAfi
	 */
	public void setCodConAfi(String codConAfi) {
		this.codConAfi = codConAfi;
	}

	/**
	 * Returns the value of codTipConAfi.
	 * @return codTipConAfi
	 */
	public String getCodTipConAfi() {
		return codTipConAfi;
	}

	/**
	 * Sets the value for codTipConAfi.
	 * @param codTipConAfi
	 */
	public void setCodTipConAfi(String codTipConAfi) {
		this.codTipConAfi = codTipConAfi;
	}

	/**
	 * Returns the value of ordPreAfi.
	 * @return ordPreAfi
	 */
	public BigDecimal getOrdPreAfi() {
		return ordPreAfi;
	}

	/**
	 * Sets the value for ordPreAfi.
	 * @param ordPreAfi
	 */
	public void setOrdPreAfi(BigDecimal ordPreAfi) {
		this.ordPreAfi = ordPreAfi;
	}

	/**
	 * Returns the value of numPreAfi.
	 * @return numPreAfi
	 */
	public BigDecimal getNumPreAfi() {
		return numPreAfi;
	}

	/**
	 * Sets the value for numPreAfi.
	 * @param numPreAfi
	 */
	public void setNumPreAfi(BigDecimal numPreAfi) {
		this.numPreAfi = numPreAfi;
	}

	/**
	 * Returns the value of codPretip.
	 * @return codPretip
	 */
	public BigDecimal getCodPretip() {
		return codPretip;
	}

	/**
	 * Sets the value for codPretip.
	 * @param codPretip
	 */
	public void setCodPretip(BigDecimal codPretip) {
		this.codPretip = codPretip;
	}

	/**
	 * Returns the value of infUsuBan.
	 * @return infUsuBan
	 */
	public String getInfUsuBan() {
		return infUsuBan;
	}

	/**
	 * Sets the value for infUsuBan.
	 * @param infUsuBan
	 */
	public void setInfUsuBan(String infUsuBan) {
		this.infUsuBan = infUsuBan;
	}

	/**
	 * Returns the value of valComPagAfi.
	 * @return valComPagAfi
	 */
	public BigDecimal getValComPagAfi() {
		return valComPagAfi;
	}

	/**
	 * Sets the value for valComPagAfi.
	 * @param valComPagAfi
	 */
	public void setValComPagAfi(BigDecimal valComPagAfi) {
		this.valComPagAfi = valComPagAfi;
	}

	/**
	 * Returns the value of interesMora.
	 * @return interesMora
	 */
	public BigDecimal getInteresMora() {
		return interesMora;
	}

	/**
	 * Sets the value for interesMora.
	 * @param interesMora
	 */
	public void setInteresMora(BigDecimal interesMora) {
		this.interesMora = interesMora;
	}

	/**
	 * Returns the value of valDedPag.
	 * @return valDedPag
	 */
	public BigDecimal getValDedPag() {
		return valDedPag;
	}

	/**
	 * Sets the value for valDedPag.
	 * @param valDedPag
	 */
	public void setValDedPag(BigDecimal valDedPag) {
		this.valDedPag = valDedPag;
	}

	/**
	 * Returns the value of liquidacionPrestamo.
	 * @return liquidacionPrestamo
	 */
	public LiquidacionPrestamo getLiquidacion() {
		return liquidacion;
	}

	/**
	 * Sets the value for liquidacionPrestamo.
	 * @param liquidacionPrestamo
	 */
	public void setLiquidacion(LiquidacionPrestamo liquidacionPrestamo) {
		this.liquidacion = liquidacionPrestamo;
	}

	/**
	 * Returns the value of codTipDocJusPag.
	 * @return codTipDocJusPag
	 */
	public String getCodTipDocJusPag() {
		return codTipDocJusPag;
	}

	/**
	 * Sets the value for codTipDocJusPag.
	 * @param codTipDocJusPag
	 */
	public void setCodTipDocJusPag(String codTipDocJusPag) {
		this.codTipDocJusPag = codTipDocJusPag;
	}

	/**
	 * Returns the value of numeroDocumentoPago.
	 * @return numeroDocumentoPago
	 */
	public String getNumeroDocumentoPago() {
		return numeroDocumentoPago;
	}

	/**
	 * Sets the value for numeroDocumentoPago.
	 * @param numeroDocumentoPago
	 */
	public void setNumeroDocumentoPago(String numeroDocumentoPago) {
		this.numeroDocumentoPago = numeroDocumentoPago;
	}

	/**
	 * Returns the value of fechaDocumentoPago.
	 * @return fechaDocumentoPago
	 */
	public Timestamp getFechaDocumentoPago() {
		return fechaDocumentoPago;
	}

	/**
	 * Sets the value for fechaDocumentoPago.
	 * @param fechaDocumentoPago
	 */
	public void setFechaDocumentoPago(Timestamp fechaDocumentoPago) {
		this.fechaDocumentoPago = fechaDocumentoPago;
	}

	/**
	 * Returns the value of codigoSucursalIfi.
	 * @return codigoSucursalIfi
	 */
	public String getCodigoSucursalIfi() {
		return codigoSucursalIfi;
	}

	/**
	 * Sets the value for codigoSucursalIfi.
	 * @param codigoSucursalIfi
	 */
	public void setCodigoSucursalIfi(String codigoSucursalIfi) {
		this.codigoSucursalIfi = codigoSucursalIfi;
	}

	/**
	 * Returns the value of divisionPolitica.
	 * @return divisionPolitica
	 */
	public String getDivisionPolitica() {
		return divisionPolitica;
	}

	/**
	 * Sets the value for divisionPolitica.
	 * @param divisionPolitica
	 */
	public void setDivisionPolitica(String divisionPolitica) {
		this.divisionPolitica = divisionPolitica;
	}

	/**
	 * Returns the value of codOfiIes.
	 * @return codOfiIes
	 */
	public String getCodOfiIes() {
		return codOfiIes;
	}

	/**
	 * Sets the value for codOfiIes.
	 * @param codOfiIes
	 */
	public void setCodOfiIes(String codOfiIes) {
		this.codOfiIes = codOfiIes;
	}

	/**
	 * Returns the value of prestamo.
	 * @return prestamo
	 */
	public Prestamo getPrestamo() {
		return prestamo;
	}

	/**
	 * Sets the value for prestamo.
	 * @param prestamo
	 */
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	/**
	 * Returns the value of detalle.
	 * @return detalle
	 */
	public List<ComprobantePagoDetalle> getDetalle() {
		return detalle;
	}

	/**
	 * Sets the value for detalle.
	 * @param detalle
	 */
	public void setDetalle(List<ComprobantePagoDetalle> detalle) {
		this.detalle = detalle;
	}

	/**
	 * Returns the value of tipoComprobante.
	 * @return tipoComprobante
	 */
	public TipoComprobantePago getTipoComprobante() {
		return tipoComprobante;
	}

	/**
	 * Sets the value for tipoComprobante.
	 * @param tipoComprobante
	 */
	public void setTipoComprobante(TipoComprobantePago tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

}