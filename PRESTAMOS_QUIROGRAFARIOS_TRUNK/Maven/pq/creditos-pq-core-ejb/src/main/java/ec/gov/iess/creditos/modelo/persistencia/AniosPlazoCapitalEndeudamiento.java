/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.AniosPlazoCapitalEndeudamientoPk;

/**
 * @author cvillarreal
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="kscretaniplacapend")
@NamedQueries({
	@NamedQuery(name="AniosPlazoCapitalEndeudamiento.consultaTipoCreditoVariedadCredito",
			query="select o from AniosPlazoCapitalEndeudamiento o " +
					"where o.pk.codprecla = :variedadCredito " +
					" and o.pk.codpretip = :tipoCredito " +
					" order by o.pk.numanipla"),
	@NamedQuery(name="AniosPlazoCapitalEndeudamiento.consultaPlazoEndeudamiento",
			query="select o from AniosPlazoCapitalEndeudamiento o " +
					"where o.pk.codprecla = :variedadCredito " +
					" and o.pk.codpretip = :tipoCredito " +
					" and o.pk.numanipla <= :plazoEndeudamiento " +
					" order by o.pk.numanipla")
})
@NamedNativeQueries( {

		@NamedNativeQuery(name = "AniosPlazoCapitalEndeudamiento.buscarCoeSegSal", 
				query = "select * "+
						"FROM kscretaniplacapend, (SELECT plzmes "+
                        "FROM kscretcreditos "+
                        "WHERE numpreafi = :numpreafi AND "+
                        "ordpreafi = :ordpreafi AND "+
                        "codpretip = :codpretip AND "+
                        "codprecla = :codprecla) S1 "+
                        "WHERE "+
                        "codprecla = :codprecla AND "+
                        "codpretip = :codpretip AND "+
                        "numanipla = S1.plzmes", resultSetMapping = "AniosPlazoCapitalEndeudamientoId")
})
@SqlResultSetMappings( { @SqlResultSetMapping(name = "AniosPlazoCapitalEndeudamientoId", entities = { @EntityResult(entityClass = AniosPlazoCapitalEndeudamiento.class) }) })


public class AniosPlazoCapitalEndeudamiento implements Serializable {

    
	@EmbeddedId
	private AniosPlazoCapitalEndeudamientoPk pk;
	
    @Column(nullable = false)
    private Double porcapend;
    @Column(nullable = false)
    private Double porcoesegsal;
	
	public AniosPlazoCapitalEndeudamiento() {
		
	}

	/**
	 * @return the pk
	 */
	public AniosPlazoCapitalEndeudamientoPk getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(AniosPlazoCapitalEndeudamientoPk pk) {
		this.pk = pk;
	}

	/**
	 * @return the porcapend
	 */
	public Double getPorcapend() {
		return porcapend;
	}

	/**
	 * @param porcapend the porcapend to set
	 */
	public void setPorcapend(Double porcapend) {
		this.porcapend = porcapend;
	}

	/**
	 * @return the porcoesegsal
	 */
	public Double getPorcoesegsal() {
		return porcoesegsal;
	}

	/**
	 * @param porcoesegsal the porcoesegsal to set
	 */
	public void setPorcoesegsal(Double porcoesegsal) {
		this.porcoesegsal = porcoesegsal;
	}

	}
