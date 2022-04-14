/*
 * Copyright 2010 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * Clase de entidad PrestamoInformacion
 * 
 * @author edwin.maza
 *
 */
@Entity
@Table(name = "CRE_INFOCREDITOS_TBL",schema="PQ_OWNER")
public class PrestamoInformacion implements Serializable {

	private static final long serialVersionUID = 1975753207793702607L;

	@EmbeddedId
	private PrestamoPk prestamoPk;

	@Column(name = "IC_URLDOC", length = 128)
	private String urlDocumetosProveedor;

	public PrestamoInformacion() {

	}

	/**
	 * @return {@link PrestamoPk}
	 */
	public PrestamoPk getPrestamoPk() {
		return prestamoPk;
	}

	/**
	 * @param prestamoPk
	 */
	public void setPrestamoPk(PrestamoPk prestamoPk) {
		this.prestamoPk = prestamoPk;
	}

	/**
	 * @return {@link String}
	 */
	public String getUrlDocumetosProveedor() {
		return urlDocumetosProveedor;
	}

	/**
	 * @param urlDocumetosProveedor
	 */
	public void setUrlDocumetosProveedor(String urlDocumetosProveedor) {
		this.urlDocumetosProveedor = urlDocumetosProveedor;
	}

}
