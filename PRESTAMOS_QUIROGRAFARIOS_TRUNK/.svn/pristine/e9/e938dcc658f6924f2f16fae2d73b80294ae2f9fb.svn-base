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
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.EstadoArchivoEnvioEnum;
import ec.gov.iess.creditos.enumeracion.TipoProcesoArchivoEnvio;

/**
 * @author jvaca
 *
 */
@Entity
@Table(name ="CRE_ARCHIVOS_TBL ")
public class ArchivoEnvio implements Serializable{
	private static final long serialVersionUID = 1595163776946713592L;

	public ArchivoEnvio() {
	}
	/*  
		NUTTRAN                          No     NUMBER                   
		FECHA                            No     DATE                     
		TIPO                             No     VARCHAR2(3)              
		USUARIO                          No     VARCHAR2(50)             
		VERSION                          No     VARCHAR2(20)             
		ESTADO                           No     VARCHAR2(3)              
		CLAVE                            No     VARCHAR2(120)            
	 */
	
	@Column(name = "NUTTRAN", nullable = false)
	@Id
	private BigInteger numeroTransferencia;
	
	@Column(name = "FECHA", nullable = false)
	private Date fechaTransferencia;
	
	@Column(name = "TIPO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoProcesoArchivoEnvio tipo;
	
	@Column(name = "USUARIO", nullable = false)
	private String usuario;
	
	@Column(name = "VERSION", nullable = false)
	private String version;
	
	@Column(name = "ESTADO", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoArchivoEnvioEnum estado;
	
	@Column(name = "CLAVE", nullable = false)
	private String clave;
	
	@OneToMany(mappedBy = "archivoEnvio",cascade ={CascadeType.ALL})
	private List<ArchivoEnvioDetalleError> listaDetalleError;

	/**
	 * @return the listaDetalleError
	 */
	public List<ArchivoEnvioDetalleError> getListaDetalleError() {
		return listaDetalleError;
	}

	/**
	 * @param listaDetalleError the listaDetalleError to set
	 */
	public void setListaDetalleError(List<ArchivoEnvioDetalleError> listaDetalleError) {
		this.listaDetalleError = listaDetalleError;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the numeroTransferencia
	 */
	public BigInteger getNumeroTransferencia() {
		return numeroTransferencia;
	}

	/**
	 * @return the fechaTransferencia
	 */
	public Date getFechaTransferencia() {
		return fechaTransferencia;
	}

	/**
	 * @return the tipo
	 */
	public TipoProcesoArchivoEnvio getTipo() {
		return tipo;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return the estado
	 */
	public EstadoArchivoEnvioEnum getEstado() {
		return estado;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param numeroTransferencia the numeroTransferencia to set
	 */
	public void setNumeroTransferencia(BigInteger numeroTransferencia) {
		this.numeroTransferencia = numeroTransferencia;
	}

	/**
	 * @param fechaTransferencia the fechaTransferencia to set
	 */
	public void setFechaTransferencia(Date fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoProcesoArchivoEnvio tipo) {
		this.tipo = tipo;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoArchivoEnvioEnum estado) {
		this.estado = estado;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

}
