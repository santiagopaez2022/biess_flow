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
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase de persistencia de modelo
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
@Embeddable
public class PeriodoPk implements Serializable {

	@Column(nullable=false)
    private Long aniper;
	@Column(nullable=false)
    private Long mesper;
	@Column(nullable=false)
    private String tipper;

	public PeriodoPk() {

	}
	
    public PeriodoPk(Long aniper, Long mesper, String tipper) {
        this.aniper = aniper;
        this.mesper = mesper;
        this.tipper = tipper;
    }

	
    public boolean equals(Object other) {
        if (other instanceof PeriodoPk) {
            final PeriodoPk otherKsrectperiodosPK = (PeriodoPk) other;
            final boolean areEqual = 
                (otherKsrectperiodosPK.aniper.equals(aniper) && otherKsrectperiodosPK.mesper.equals(mesper) && otherKsrectperiodosPK.tipper.equals(tipper));
            return areEqual;
        }
        return false;
    }

	/**
	 * @return the aniper
	 */
	public Long getAniper() {
		return aniper;
	}

	/**
	 * @param aniper the aniper to set
	 */
	public void setAniper(Long aniper) {
		this.aniper = aniper;
	}

	/**
	 * @return the mesper
	 */
	public Long getMesper() {
		return mesper;
	}

	/**
	 * @param mesper the mesper to set
	 */
	public void setMesper(Long mesper) {
		this.mesper = mesper;
	}

	/**
	 * @return the tipper
	 */
	public String getTipper() {
		return tipper;
	}

	/**
	 * @param tipper the tipper to set
	 */
	public void setTipper(String tipper) {
		this.tipper = tipper;
	}

	
}
