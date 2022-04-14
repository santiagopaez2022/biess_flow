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
import java.math.BigInteger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.ArchivoDetalleErrorPk;

/**
 * @author jvaca
 *
 */
@Entity
@Table(name = "CRE_ARCHIVOSDETALLEERROR_TBL")
public class ArchivoEnvioDetalleError implements Serializable{

	private static final long serialVersionUID = -9053577622612498308L;

	public ArchivoEnvioDetalleError() {
	}
	
	/*
		NUT                              No     NUMBER                   
		NUMSOLSER                        No     NUMBER(20)               
		CODTIPSOLSER                     No     NUMBER(2)                
		NUTTRAN                          No     NUMBER                   
		DETALLE                          No     VARCHAR2(2000)      	 
	*/
	
	
	
	@EmbeddedId
	private ArchivoDetalleErrorPk pk;
	
	private Long codtipsolser;
	
	private BigInteger nuttran;
	
	private String detalle;
	
	private Long resultado;
	
	@ManyToOne
	@JoinColumn(name = "NUTTRAN", referencedColumnName = "NUTTRAN", insertable = false, updatable = false)
	private ArchivoEnvio archivoEnvio;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "NUMSOLSER", referencedColumnName = "NUMSOLSER", insertable = false, updatable = false),
		@JoinColumn(name = "CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER", insertable = false, updatable = false)
	})
	private SolicitudCredito solicitudCredito;

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the codtipsolser
	 */
	public Long getCodtipsolser() {
		return codtipsolser;
	}

	/**
	 * @return the nuttran
	 */
	public BigInteger getNuttran() {
		return nuttran;
	}

	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * @return the archivoEnvio
	 */
	public ArchivoEnvio getArchivoEnvio() {
		return archivoEnvio;
	}

	/**
	 * @return the solicitudCredito
	 */
	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	/**
	 * @param codtipsolser the codtipsolser to set
	 */
	public void setCodtipsolser(Long codtipsolser) {
		this.codtipsolser = codtipsolser;
	}

	/**
	 * @param nuttran the nuttran to set
	 */
	public void setNuttran(BigInteger nuttran) {
		this.nuttran = nuttran;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	/**
	 * @param archivoEnvio the archivoEnvio to set
	 */
	public void setArchivoEnvio(ArchivoEnvio archivoEnvio) {
		this.archivoEnvio = archivoEnvio;
	}

	/**
	 * @param solicitudCredito the solicitudCredito to set
	 */
	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}

	/**
	 * @return the pk
	 */
	public ArchivoDetalleErrorPk getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(ArchivoDetalleErrorPk pk) {
		this.pk = pk;
	}

	/**
	 * @return the resultado
	 */
	public Long getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(Long resultado) {
		this.resultado = resultado;
	}
	
	

}
