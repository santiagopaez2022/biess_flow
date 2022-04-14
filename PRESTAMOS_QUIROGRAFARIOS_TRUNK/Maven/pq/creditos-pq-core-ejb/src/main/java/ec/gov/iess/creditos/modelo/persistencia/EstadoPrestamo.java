/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author cvillarreal
 *
 */
@Entity
@Table(name="Kscretpreest")
@NamedQueries({
	@NamedQuery(name = "EstadoPrestamo.consultarTodos", 
		    query = "select o from EstadoPrestamo o")
})
public class EstadoPrestamo implements Serializable {

	@GeneratedValue
	private static final long serialVersionUID = -6770550448478589765L;
	
	@Id
    @Column(nullable = false)
    private String codestpre;
    @Column(nullable = false)
    private String desestpre;
    @Column(nullable = false)
    private String indhabestpre;

    private String obtestpre;

	
	/**
	 * 
	 */
	public EstadoPrestamo() {
		
	}


	/**
	 * @return the codestpre
	 */
	public String getCodestpre() {
		return codestpre;
	}


	/**
	 * @param codestpre the codestpre to set
	 */
	public void setCodestpre(String codestpre) {
		this.codestpre = codestpre;
	}


	/**
	 * @return the desestpre
	 */
	public String getDesestpre() {
		return desestpre;
	}


	/**
	 * @param desestpre the desestpre to set
	 */
	public void setDesestpre(String desestpre) {
		this.desestpre = desestpre;
	}


	/**
	 * @return the indhabestpre
	 */
	public String getIndhabestpre() {
		return indhabestpre;
	}


	/**
	 * @param indhabestpre the indhabestpre to set
	 */
	public void setIndhabestpre(String indhabestpre) {
		this.indhabestpre = indhabestpre;
	}


	public String getObsestpre() {
		return obtestpre;
	}


	public void setObsestpre(String obsestpre) {
		this.obtestpre = obsestpre;
	}

}
