/**
 * 
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

import ec.gov.iess.creditos.modelo.persistencia.pk.MigracionPrestamoHostPK;

/**
 * @author cvillarreal
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "Ksmigtquiros")
@NamedQueries( { 
	@NamedQuery(name = "MigracionPrestamoHost.consultarTodos", 
		query = "select o from MigracionPrestamoHost o"),
		/*
		 * Creditos pendientes del HOST
		 */
	@NamedQuery(name="MigracionPrestamoHost.consultarEnMora",
			query="select o from MigracionPrestamoHost o " +
					"where o.migracionPrestamoHostPK.cedideusu=:cedula " +
					"and o.codmor=:estadoMigrado")
		})
public class MigracionPrestamoHost implements Serializable {

	@EmbeddedId
	private MigracionPrestamoHostPK migracionPrestamoHostPK;
	
    private String codest;
    @Column(nullable = false)
    private String codmor;
    private String codtipseg;
    private Long edad;
    private Date feccon;
    private Date fecpro;
    private String fecultpag;
    @Column(nullable = false)
    private String finpre;
    private String numpat;
    private Long numsec;
    private Long numtotimp;
    private String valpen;
    private String valsueapo;


	/**
	 * 
	 */
	public MigracionPrestamoHost() {

	}


	/**
	 * @return the migracionPrestamoHostPK
	 */
	public MigracionPrestamoHostPK getMigracionPrestamoHostPK() {
		return migracionPrestamoHostPK;
	}


	/**
	 * @param migracionPrestamoHostPK the migracionPrestamoHostPK to set
	 */
	public void setMigracionPrestamoHostPK(
			MigracionPrestamoHostPK migracionPrestamoHostPK) {
		this.migracionPrestamoHostPK = migracionPrestamoHostPK;
	}


	/**
	 * @return the codest
	 */
	public String getCodest() {
		return codest;
	}


	/**
	 * @param codest the codest to set
	 */
	public void setCodest(String codest) {
		this.codest = codest;
	}


	/**
	 * @return the codmor
	 */
	public String getCodmor() {
		return codmor;
	}


	/**
	 * @param codmor the codmor to set
	 */
	public void setCodmor(String codmor) {
		this.codmor = codmor;
	}


	/**
	 * @return the codtipseg
	 */
	public String getCodtipseg() {
		return codtipseg;
	}


	/**
	 * @param codtipseg the codtipseg to set
	 */
	public void setCodtipseg(String codtipseg) {
		this.codtipseg = codtipseg;
	}


	/**
	 * @return the edad
	 */
	public Long getEdad() {
		return edad;
	}


	/**
	 * @param edad the edad to set
	 */
	public void setEdad(Long edad) {
		this.edad = edad;
	}


	/**
	 * @return the feccon
	 */
	public Date getFeccon() {
		return feccon;
	}


	/**
	 * @param feccon the feccon to set
	 */
	public void setFeccon(Date feccon) {
		this.feccon = feccon;
	}


	/**
	 * @return the fecpro
	 */
	public Date getFecpro() {
		return fecpro;
	}


	/**
	 * @param fecpro the fecpro to set
	 */
	public void setFecpro(Date fecpro) {
		this.fecpro = fecpro;
	}


	/**
	 * @return the fecultpag
	 */
	public String getFecultpag() {
		return fecultpag;
	}


	/**
	 * @param fecultpag the fecultpag to set
	 */
	public void setFecultpag(String fecultpag) {
		this.fecultpag = fecultpag;
	}


	/**
	 * @return the finpre
	 */
	public String getFinpre() {
		return finpre;
	}


	/**
	 * @param finpre the finpre to set
	 */
	public void setFinpre(String finpre) {
		this.finpre = finpre;
	}


	/**
	 * @return the numpat
	 */
	public String getNumpat() {
		return numpat;
	}


	/**
	 * @param numpat the numpat to set
	 */
	public void setNumpat(String numpat) {
		this.numpat = numpat;
	}


	/**
	 * @return the numsec
	 */
	public Long getNumsec() {
		return numsec;
	}


	/**
	 * @param numsec the numsec to set
	 */
	public void setNumsec(Long numsec) {
		this.numsec = numsec;
	}


	/**
	 * @return the numtotimp
	 */
	public Long getNumtotimp() {
		return numtotimp;
	}


	/**
	 * @param numtotimp the numtotimp to set
	 */
	public void setNumtotimp(Long numtotimp) {
		this.numtotimp = numtotimp;
	}


	/**
	 * @return the valpen
	 */
	public String getValpen() {
		return valpen;
	}


	/**
	 * @param valpen the valpen to set
	 */
	public void setValpen(String valpen) {
		this.valpen = valpen;
	}


	/**
	 * @return the valsueapo
	 */
	public String getValsueapo() {
		return valsueapo;
	}


	/**
	 * @param valsueapo the valsueapo to set
	 */
	public void setValsueapo(String valsueapo) {
		this.valsueapo = valsueapo;
	}

}
