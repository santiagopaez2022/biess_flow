/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.ResumenFinPrestamoPK;

/**
 * 
 * Tabla de resumen de finalizacion de la concesion
 * 
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "KSCRETPRECONFIN")
public class ResumenFinPrestamo implements Serializable {

	@EmbeddedId
	private ResumenFinPrestamoPK resumenFinPrestamoPK;
	
    @Column(nullable = false)
    private String tipvalconfinpre;
    private String valcarconfinpre;
    private Date valfecconfinpre;
    private Double valnumconfinpre;
	
	/**
	 * 
	 */
	public ResumenFinPrestamo() {
	}

	/**
	 * @return the resumenFinPrestamoPK
	 */
	public ResumenFinPrestamoPK getResumenFinPrestamoPK() {
		return resumenFinPrestamoPK;
	}

	/**
	 * @return the tipvalconfinpre
	 */
	public String getTipvalconfinpre() {
		return tipvalconfinpre;
	}

	/**
	 * @return the valcarconfinpre
	 */
	public String getValcarconfinpre() {
		return valcarconfinpre;
	}

	/**
	 * @return the valfecconfinpre
	 */
	public Date getValfecconfinpre() {
		return valfecconfinpre;
	}

	/**
	 * @return the valnumconfinpre
	 */
	public Double getValnumconfinpre() {
		return valnumconfinpre;
	}

	/**
	 * @param resumenFinPrestamoPK the resumenFinPrestamoPK to set
	 */
	public void setResumenFinPrestamoPK(ResumenFinPrestamoPK resumenFinPrestamoPK) {
		this.resumenFinPrestamoPK = resumenFinPrestamoPK;
	}

	/**
	 * @param tipvalconfinpre the tipvalconfinpre to set
	 */
	public void setTipvalconfinpre(String tipvalconfinpre) {
		this.tipvalconfinpre = tipvalconfinpre;
	}

	/**
	 * @param valcarconfinpre the valcarconfinpre to set
	 */
	public void setValcarconfinpre(String valcarconfinpre) {
		this.valcarconfinpre = valcarconfinpre;
	}

	/**
	 * @param valfecconfinpre the valfecconfinpre to set
	 */
	public void setValfecconfinpre(Date valfecconfinpre) {
		this.valfecconfinpre = valfecconfinpre;
	}

	/**
	 * @param valnumconfinpre the valnumconfinpre to set
	 */
	public void setValnumconfinpre(Double valnumconfinpre) {
		this.valnumconfinpre = valnumconfinpre;
	}

}
