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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author cvillarreal
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "Kscretinttas")
@NamedQueries( { @NamedQuery(name = "TasaInteres.consultaPorTasainteres", 
		query = "select o from TasaInteres o "
		+ "where o.codtasint=:idtasaInteres ") })
public class TasaInteres implements Serializable {

	@Id
	@Column(nullable = false)
	private String codtasint;
	@Column(nullable = false)
	private String destasint;

	@OneToMany(mappedBy = "tasaInteres", fetch = FetchType.EAGER)
	//@OrderBy("fecinitasint")
	List<TasaInteresDetalle> tasaInteresDetalleList;

	/**
	 * 
	 */
	public TasaInteres() {

	}

	/**
	 * @return the codtasint
	 */
	public String getCodtasint() {
		return codtasint;
	}

	/**
	 * @param codtasint
	 *            the codtasint to set
	 */
	public void setCodtasint(String codtasint) {
		this.codtasint = codtasint;
	}

	/**
	 * @return the destasint
	 */
	public String getDestasint() {
		return destasint;
	}

	/**
	 * @param destasint
	 *            the destasint to set
	 */
	public void setDestasint(String destasint) {
		this.destasint = destasint;
	}

}
