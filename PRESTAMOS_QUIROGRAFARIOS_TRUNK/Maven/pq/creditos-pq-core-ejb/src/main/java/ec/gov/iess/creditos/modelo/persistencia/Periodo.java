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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.PeriodoPk;

/**
 * Clase de modelo de persistencia que incluye un PK {@link PeriodoPk}
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name="Ksrectperiodos")
@NamedQueries({
	@NamedQuery(name = "Ksrectperiodos.findAll", 
		    query = "select o from Periodo o")
})
public class Periodo implements Serializable {

	@EmbeddedId
	private PeriodoPk periodoPk;
	
    private String desper;
    @Column(nullable = false)
    private String estaju;
    @Column(nullable = false)
    private Date fecfinper;
    @Column(nullable = false)
    private Date feciniper;
    private Date feciniperreapri;
    private Date feciniperreapub;
    @Column(nullable = false)
    private Date fecmaxpag;
    @Column(nullable = false)
    private Date pridialab;

	
	public Periodo() {
	
	}


	/**
	 * @return the periodoPk
	 */
	public PeriodoPk getPeriodoPk() {
		return periodoPk;
	}


	/**
	 * @param periodoPk the periodoPk to set
	 */
	public void setPeriodoPk(PeriodoPk periodoPk) {
		this.periodoPk = periodoPk;
	}


	/**
	 * @return the desper
	 */
	public String getDesper() {
		return desper;
	}


	/**
	 * @param desper the desper to set
	 */
	public void setDesper(String desper) {
		this.desper = desper;
	}


	/**
	 * @return the estaju
	 */
	public String getEstaju() {
		return estaju;
	}


	/**
	 * @param estaju the estaju to set
	 */
	public void setEstaju(String estaju) {
		this.estaju = estaju;
	}


	/**
	 * @return the fecfinper
	 */
	public Date getFecfinper() {
		return fecfinper;
	}


	/**
	 * @param fecfinper the fecfinper to set
	 */
	public void setFecfinper(Date fecfinper) {
		this.fecfinper = fecfinper;
	}


	/**
	 * @return the feciniper
	 */
	public Date getFeciniper() {
		return feciniper;
	}


	/**
	 * @param feciniper the feciniper to set
	 */
	public void setFeciniper(Date feciniper) {
		this.feciniper = feciniper;
	}


	/**
	 * @return the feciniperreapri
	 */
	public Date getFeciniperreapri() {
		return feciniperreapri;
	}


	/**
	 * @param feciniperreapri the feciniperreapri to set
	 */
	public void setFeciniperreapri(Date feciniperreapri) {
		this.feciniperreapri = feciniperreapri;
	}


	/**
	 * @return the feciniperreapub
	 */
	public Date getFeciniperreapub() {
		return feciniperreapub;
	}


	/**
	 * @param feciniperreapub the feciniperreapub to set
	 */
	public void setFeciniperreapub(Date feciniperreapub) {
		this.feciniperreapub = feciniperreapub;
	}


	/**
	 * @return the fecmaxpag
	 */
	public Date getFecmaxpag() {
		return fecmaxpag;
	}


	/**
	 * @param fecmaxpag the fecmaxpag to set
	 */
	public void setFecmaxpag(Date fecmaxpag) {
		this.fecmaxpag = fecmaxpag;
	}


	/**
	 * @return the pridialab
	 */
	public Date getPridialab() {
		return pridialab;
	}


	/**
	 * @param pridialab the pridialab to set
	 */
	public void setPridialab(Date pridialab) {
		this.pridialab = pridialab;
	}

}
