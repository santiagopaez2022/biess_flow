/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/** 
 * <b>
 * Clave primaria de la clase SaldoFondos
 * </b>
 *  
 * @author Gabriel Eguiguren
 * @version $Revision: 1.3 $ <p>[$Author: etarambis $, $Date: 2010/12/20 16:55:55 $]</p>
*/ 
@Embeddable
public class SaldoFondosPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CF_CODIGO")
	private Long codigoCuentaFondo;

	@Column(name="SF_FEC_PROCESO")
	private Date fechaProceso;

    public SaldoFondosPK() {
    }

    public SaldoFondosPK(Long codigoCuentaFondo, Date fechaProceso ) {
    	this.codigoCuentaFondo = codigoCuentaFondo;
    	this.fechaProceso = fechaProceso;
    }


	/**
	 * @return the codigoCuentaFondo
	 */
	public Long getCodigoCuentaFondo() {
		return codigoCuentaFondo;
	}


	/**
	 * @param codigoCuentaFondo the codigoCuentaFondo to set
	 */
	public void setCodigoCuentaFondo(Long codigoCuentaFondo) {
		this.codigoCuentaFondo = codigoCuentaFondo;
	}

	/**
	 * @return the fechaProceso
	 */
	public Date getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso the fechaProceso to set
	 */
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
  
}