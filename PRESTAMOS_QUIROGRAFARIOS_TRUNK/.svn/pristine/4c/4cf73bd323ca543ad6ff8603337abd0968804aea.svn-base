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
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * Historico Desembolso - Solo consulta
 * 
 * @version 1.0
 * @author ndeveloper
 * 
 */
@Entity
@Table(name = "cre_desembolsoshist_tbl")
@NamedQueries({ @NamedQuery(name = "HistoricoDesembolso.consultarHistoricoDesembolsoPorNut", query = "FROM HistoricoDesembolso o where o.dhNutdep = :dhNutdep ") })
public class HistoricoDesembolso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DH_ID")
	private Long dhId;

	@Column(name = "dh_depmonfin")
	private BigDecimal dhDepmonfin;

	@Column(name = "DH_NUTDEP")
	private String dhNutdep;

	public Long getDhId() {
		return dhId;
	}

	public void setDhId(Long dhId) {
		this.dhId = dhId;
	}

	public BigDecimal getDhDepmonfin() {
		return dhDepmonfin;
	}

	public void setDhDepmonfin(BigDecimal dhDepmonfin) {
		this.dhDepmonfin = dhDepmonfin;
	}

	public String getDhNutdep() {
		return dhNutdep;
	}

	public void setDhNutdep(String dhNutdep) {
		this.dhNutdep = dhNutdep;
	}

}