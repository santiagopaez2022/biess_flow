package ec.fin.biess.creditos.pq.enumeracion;

/**
 * Enumeracion para creditos especiales
 * 
 * @author hugo.mora
 * @date 17 de may. de 2016
 *
 */
public enum CreditoEspecialEnum {

	EMERGENTE(1L);

	/**
	 * Indica el codigo del credito especial
	 */
	private Long codigoCredito;

	/**
	 * @param codigoCredito
	 */
	private CreditoEspecialEnum(Long codigoCredito) {
		this.codigoCredito = codigoCredito;
	}

	/**
	 * @return
	 */
	public Long getCodigoCredito() {
		return codigoCredito;
	}

}
