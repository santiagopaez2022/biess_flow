/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity bean para la tabla KSCRETCREPOL
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 *
 */
@Entity
@Table(name="KSCRETCREPOL")
@NamedQueries({
	@NamedQuery(name="ParametroCredito.consultarPorCodigo", query="select o from ParametroCredito o where o.codPol = :codpol")
})
public class ParametroCredito implements Serializable {

	private static final long serialVersionUID = 5264675024304739578L;

	@Id
	@Column(name="CODPOL", nullable=false, unique=true)
	private String codPol;
	
	@Column(name="DESPOL", nullable=false)
	private String desPol;
	
	@Column(name="TIPVALPOL", nullable=false)
	private String tipValPol;
	
	@Column(name="VALNUMPOL")
	private BigDecimal valNumPol;
	
	@Column(name="VALCARPOL")
	private String valCarPol;
	
	@Column(name="VALFECPOL")
	private Timestamp valFecPol;
	
	
	public ParametroCredito() {
		
	}


	/**
	 * @return the codPol
	 */
	public String getCodPol() {
		return codPol;
	}


	/**
	 * @param codPol the codPol to set
	 */
	public void setCodPol(String codPol) {
		this.codPol = codPol;
	}


	/**
	 * @return the desPol
	 */
	public String getDesPol() {
		return desPol;
	}


	/**
	 * @param desPol the desPol to set
	 */
	public void setDesPol(String desPol) {
		this.desPol = desPol;
	}


	/**
	 * @return the tipValPol
	 */
	public String getTipValPol() {
		return tipValPol;
	}


	/**
	 * @param tipValPol the tipValPol to set
	 */
	public void setTipValPol(String tipValPol) {
		this.tipValPol = tipValPol;
	}


	/**
	 * @return the valNumPol
	 */
	public BigDecimal getValNumPol() {
		return valNumPol;
	}


	/**
	 * @param valNumPol the valNumPol to set
	 */
	public void setValNumPol(BigDecimal valNumPol) {
		this.valNumPol = valNumPol;
	}


	/**
	 * @return the valCarPol
	 */
	public String getValCarPol() {
		return valCarPol;
	}


	/**
	 * @param valCarPol the valCarPol to set
	 */
	public void setValCarPol(String valCarPol) {
		this.valCarPol = valCarPol;
	}


	/**
	 * @return the valFecPol
	 */
	public Timestamp getValFecPol() {
		return valFecPol;
	}


	/**
	 * @param valFecPol the valFecPol to set
	 */
	public void setValFecPol(Timestamp valFecPol) {
		this.valFecPol = valFecPol;
	}
	
	
}
