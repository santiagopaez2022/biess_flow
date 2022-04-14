/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <b> Clave primaria de la clase DetalleCatalogos. </b>
 * 
 * @author Gabriel Eguiguren
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: etarambis $, $Date: 2010/12/20 16:55:55 $]
 *          </p>
 */
@Embeddable
public class DetalleCatalogosPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "CA_CATALOGO")
	private String caCatalogo;

	@Column(name = "DC_CODIGO")
	private String dcCodigo;

	public DetalleCatalogosPK() {
	}

	public DetalleCatalogosPK(String caCatalogo, String dcCodigo) {
		this.caCatalogo = caCatalogo;
		this.dcCodigo = dcCodigo;
	}

	public String getCaCatalogo() {
		return this.caCatalogo;
	}

	public void setCaCatalogo(String caCatalogo) {
		this.caCatalogo = caCatalogo;
	}

	public String getDcCodigo() {
		return this.dcCodigo;
	}

	public void setDcCodigo(String dcCodigo) {
		this.dcCodigo = dcCodigo;
	}
}