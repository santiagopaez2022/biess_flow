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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase de modelo de persistencia 
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "Kscretdivpreest")
@NamedQueries( { @NamedQuery(name = "EstadoDividendoPrestamo.consultarTodo", query = "select o from EstadoDividendoPrestamo o") })
public class EstadoDividendoPrestamo implements Serializable {

    @Id
    @Column(nullable = false)
    private String codestdiv;
    @Column(nullable = false)
    private String desestdiv;
    @Column(nullable = false)
    private String indhabestdiv;

    @Column(name="OBTESTCAN")
    private String estadoCancelado;
    
    @Column(name="OBTESTDEB")
    private String estadoDebito;
    
    @Column(name="OBTESTLIQ")
    private String estadoLiquidacion;
    
	
	public EstadoDividendoPrestamo() {
	}


	/**
	 * @return the codestdiv
	 */
	public String getCodestdiv() {
		return codestdiv;
	}


	/**
	 * @param codestdiv the codestdiv to set
	 */
	public void setCodestdiv(String codestdiv) {
		this.codestdiv = codestdiv;
	}


	/**
	 * @return the desestdiv
	 */
	public String getDesestdiv() {
		return desestdiv;
	}


	/**
	 * @param desestdiv the desestdiv to set
	 */
	public void setDesestdiv(String desestdiv) {
		this.desestdiv = desestdiv;
	}


	/**
	 * @return the indhabestdiv
	 */
	public String getIndhabestdiv() {
		return indhabestdiv;
	}


	/**
	 * @param indhabestdiv the indhabestdiv to set
	 */
	public void setIndhabestdiv(String indhabestdiv) {
		this.indhabestdiv = indhabestdiv;
	}


	public String getEstadoCancelado() {
		return estadoCancelado;
	}


	public void setEstadoCancelado(String estadoCancelado) {
		this.estadoCancelado = estadoCancelado;
	}


	public String getEstadoDebito() {
		return estadoDebito;
	}


	public void setEstadoDebito(String estadoDebito) {
		this.estadoDebito = estadoDebito;
	}


	public String getEstadoLiquidacion() {
		return estadoLiquidacion;
	}


	public void setEstadoLiquidacion(String estadoLiquidacion) {
		this.estadoLiquidacion = estadoLiquidacion;
	}

	
}
