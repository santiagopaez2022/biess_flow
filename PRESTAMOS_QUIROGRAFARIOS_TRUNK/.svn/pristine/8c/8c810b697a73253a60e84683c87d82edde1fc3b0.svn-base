/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.DetalleCatalogosPK;



/** 
 * <b>
 * Clase para persistencia de la tabla: PCO_DETALLE_CATALOGOS_TBL.
 * </b>
 *  
 * @author Gabriel Eguiguren
 * @version $Revision: 1.3 $ <p>[$Author: etarambis $, $Date: 2010/12/20 16:55:55 $]</p>
*/ 
@Entity
@Table(name="PCO_DETALLE_CATALOGOS_TBL")
public class DetalleCatalogos implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleCatalogosPK id;

	@Column(name="DC_ESTADO")
	private String dcEstado;

	@Column(name="DC_VALOR")
	private String dcValor;


    public DetalleCatalogos() {
    }

	public DetalleCatalogosPK getId() {
		return this.id;
	}

	public void setId(DetalleCatalogosPK id) {
		this.id = id;
	}
	
	public String getDcEstado() {
		return this.dcEstado;
	}

	public void setDcEstado(String dcEstado) {
		this.dcEstado = dcEstado;
	}

	public String getDcValor() {
		return this.dcValor;
	}

	public void setDcValor(String dcValor) {
		this.dcValor = dcValor;
	}
}