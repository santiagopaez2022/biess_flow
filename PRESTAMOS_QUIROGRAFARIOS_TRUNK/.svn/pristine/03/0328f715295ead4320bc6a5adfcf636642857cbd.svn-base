package ec.fin.biess.creditos.pq.modelo.persistencia;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase para mapear la tabla ROL_PERIODO_DETALLE
 * 
 * @author christian.gomez
 * 
 */
@Entity
@Table(name = "ROL_PERIODO_DETALLE")
@NamedQueries({
// Se filtra en base a la identificacion del beneficiario, anio, mes y rubro,
// este query se creo con la necesidad
// de averiguar las retenciones judiciales de los pensionados para el INC2135
@NamedQuery(name = "RolPeriodoDetalle.sumValorByIdentificacionAnioMesRubro", query = " select sum (o.valor) from RolPeriodoDetalle o where "
		+ " o.pk.identiBeneficiario = :identificacion and o.pk.anio = :anio  and o.pk.mes = :mes  and o.pk.rubro = :rubro ") })
public class RolPeriodoDetalle {

	@EmbeddedId
	private RolPeriodoDetallePk pk;

	@Column(name = "VALOR")
	private BigDecimal valor;

	/**
	 * Constructor.
	 * 
	 * @param pk
	 * @param valor
	 */
	public RolPeriodoDetalle(RolPeriodoDetallePk pk, BigDecimal valor) {
		this.pk = pk;
		this.valor = valor;
	}

	/**
	 * @return the pk
	 */
	public RolPeriodoDetallePk getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(RolPeriodoDetallePk pk) {
		this.pk = pk;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
