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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase de modelo del funcionario
 * 
 * 
 * @version 1.0
 * @author cvillarreal
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="Kspcotfuncionarios")
@NamedQueries({
	@NamedQuery(name = "Funcionario.consultarTodos", 
		    query = "select o from Funcionario o")	
})
public class Funcionario implements Serializable {

    @Id
    @Column(nullable = false)
    private String codfun;

    private String apematfun;
    @Column(nullable = false)
    private String apenomfun;
    private String apepatfun;
    @Column(nullable = false)
    private String codcar;
    private String coddivpolcon;
    private String coddivpolpro;
    @Column(nullable = false)
    private String codofiies;
    private String dircorele;
    @Column(nullable = false)
    private String funabo;
    @Column(nullable = false)
    private String funjue;
    @Column(nullable = false)
    private String funssc;
    @Column(nullable = false)
    private String indhabfun;
    private String nomfun;
    private Long secrec;
    
    @ManyToOne
    @JoinColumn(name = "CODJEFFUN", referencedColumnName = "CODFUN")
    private Funcionario funcionario;
    
    @OneToMany(mappedBy = "funcionario",fetch=FetchType.LAZY)
    private List<Funcionario> funcionarioList;

	
	
	public Funcionario() {
	}



	/**
	 * @return the codfun
	 */
	public String getCodfun() {
		return codfun;
	}



	/**
	 * @param codfun the codfun to set
	 */
	public void setCodfun(String codfun) {
		this.codfun = codfun;
	}



	/**
	 * @return the apematfun
	 */
	public String getApematfun() {
		return apematfun;
	}



	/**
	 * @param apematfun the apematfun to set
	 */
	public void setApematfun(String apematfun) {
		this.apematfun = apematfun;
	}



	/**
	 * @return the apenomfun
	 */
	public String getApenomfun() {
		return apenomfun;
	}



	/**
	 * @param apenomfun the apenomfun to set
	 */
	public void setApenomfun(String apenomfun) {
		this.apenomfun = apenomfun;
	}



	/**
	 * @return the apepatfun
	 */
	public String getApepatfun() {
		return apepatfun;
	}



	/**
	 * @param apepatfun the apepatfun to set
	 */
	public void setApepatfun(String apepatfun) {
		this.apepatfun = apepatfun;
	}



	/**
	 * @return the codcar
	 */
	public String getCodcar() {
		return codcar;
	}



	/**
	 * @param codcar the codcar to set
	 */
	public void setCodcar(String codcar) {
		this.codcar = codcar;
	}



	/**
	 * @return the coddivpolcon
	 */
	public String getCoddivpolcon() {
		return coddivpolcon;
	}



	/**
	 * @param coddivpolcon the coddivpolcon to set
	 */
	public void setCoddivpolcon(String coddivpolcon) {
		this.coddivpolcon = coddivpolcon;
	}



	/**
	 * @return the coddivpolpro
	 */
	public String getCoddivpolpro() {
		return coddivpolpro;
	}



	/**
	 * @param coddivpolpro the coddivpolpro to set
	 */
	public void setCoddivpolpro(String coddivpolpro) {
		this.coddivpolpro = coddivpolpro;
	}



	/**
	 * @return the codofiies
	 */
	public String getCodofiies() {
		return codofiies;
	}



	/**
	 * @param codofiies the codofiies to set
	 */
	public void setCodofiies(String codofiies) {
		this.codofiies = codofiies;
	}



	/**
	 * @return the dircorele
	 */
	public String getDircorele() {
		return dircorele;
	}



	/**
	 * @param dircorele the dircorele to set
	 */
	public void setDircorele(String dircorele) {
		this.dircorele = dircorele;
	}



	/**
	 * @return the funabo
	 */
	public String getFunabo() {
		return funabo;
	}



	/**
	 * @param funabo the funabo to set
	 */
	public void setFunabo(String funabo) {
		this.funabo = funabo;
	}



	/**
	 * @return the funjue
	 */
	public String getFunjue() {
		return funjue;
	}



	/**
	 * @param funjue the funjue to set
	 */
	public void setFunjue(String funjue) {
		this.funjue = funjue;
	}



	/**
	 * @return the funssc
	 */
	public String getFunssc() {
		return funssc;
	}



	/**
	 * @param funssc the funssc to set
	 */
	public void setFunssc(String funssc) {
		this.funssc = funssc;
	}



	/**
	 * @return the indhabfun
	 */
	public String getIndhabfun() {
		return indhabfun;
	}



	/**
	 * @param indhabfun the indhabfun to set
	 */
	public void setIndhabfun(String indhabfun) {
		this.indhabfun = indhabfun;
	}



	/**
	 * @return the nomfun
	 */
	public String getNomfun() {
		return nomfun;
	}



	/**
	 * @param nomfun the nomfun to set
	 */
	public void setNomfun(String nomfun) {
		this.nomfun = nomfun;
	}



	/**
	 * @return the secrec
	 */
	public Long getSecrec() {
		return secrec;
	}



	/**
	 * @param secrec the secrec to set
	 */
	public void setSecrec(Long secrec) {
		this.secrec = secrec;
	}



	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}



	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}



	/**
	 * @return the funcionarioList
	 */
	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}



	/**
	 * @param funcionarioList the funcionarioList to set
	 */
	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

}
