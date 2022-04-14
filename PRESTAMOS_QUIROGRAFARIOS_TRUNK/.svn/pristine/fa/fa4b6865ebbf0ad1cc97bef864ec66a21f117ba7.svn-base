/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * <b> Entidad que mantiene a la tabla CRE_DATOSAFILIADOS_TBL, para PQ y PH.
 * almacena las cargas Familiares de los Afiliados </b>
 * 
 * @author Ronald Barrera
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Ronald Barrera $, $Date: 12/07/2011 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_DATOSAFILIADOS_TBL")
@NamedQueries( { @NamedQuery(name = "datosAfiliados.obtenerCargaFamiliar", query = "select df from DatosAfiliado df "
		+ " where df.cedula = :pCedula ") })
public class DatosAfiliado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NUMAFI", nullable = false)
	private String cedula;

	@Column(name = "CARGA_FAMILIAR", nullable = false)
	private Integer cargaFamiliar;

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the cargaFamiliar
	 */
	public Integer getCargaFamiliar() {
		return cargaFamiliar;
	}

	/**
	 * @param cargaFamiliar
	 *            the cargaFamiliar to set
	 */
	public void setCargaFamiliar(Integer cargaFamiliar) {
		this.cargaFamiliar = cargaFamiliar;
	}

}
