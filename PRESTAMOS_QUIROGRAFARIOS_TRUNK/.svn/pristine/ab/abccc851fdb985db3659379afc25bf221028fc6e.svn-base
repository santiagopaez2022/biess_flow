package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Mapeo de la tabla CRE_MOTIVORECHAZO_TBL.- tabla que contiene los motivos de rechazo de la solicitud 
 * de credito de PQ
 * @author acantos
 * @version 1.0
 */
@Entity
@Table(name="CRE_MOTIVORECHAZO_TBL", schema="PQ_OWNER")
@NamedQueries({
	@NamedQuery(name = "catalogorechazo.BuscarPorId", 
		    query = "select o from CatalogoRechazoCredito o where o.cre_motivorechazo_pk = :Id"),
    @NamedQuery(name = "catalogorechazo.todos", 
				    query = "select o from CatalogoRechazoCredito o")
			
})
public class CatalogoRechazoCredito implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CRE_MOTIVORECHAZO_PK")
	private BigInteger cre_motivorechazo_pk;
	
	@Column(name = "MO_DESCRIPCION")
	private String mo_descripcion;
		
	public BigInteger getCre_motivorechazo_pk() {
		return cre_motivorechazo_pk;
	}
	public void setCre_motivorechazo_pk(BigInteger cre_motivorechazo_pk) {
		this.cre_motivorechazo_pk = cre_motivorechazo_pk;
	}
	public String getMo_descripcion() {
		return mo_descripcion;
	}
	public void setMo_descripcion(String mo_descripcion) {
		this.mo_descripcion = mo_descripcion;
	}

	
}
