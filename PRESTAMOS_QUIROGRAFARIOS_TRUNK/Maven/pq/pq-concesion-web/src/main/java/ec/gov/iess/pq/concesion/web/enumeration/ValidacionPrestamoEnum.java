package ec.gov.iess.pq.concesion.web.enumeration;

/**
 * Enumeracion para la informacion que se almacenará en la tabla kscretcreditos en la columna CR_VALIDACION
 * 
 * @author hugo.mora
 * @date 2015/12/10
 *
 */
public enum ValidacionPrestamoEnum {
	
	TIPO_PDA(1L), TIPO_PDC(2L), TIPO_PDV(3L), TIPO_PAP(4L), TIPO_ERC(5L);
	
	private Long tipo;
	
	private ValidacionPrestamoEnum(Long tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @return
	 */
	public Long getTipo() {
		return this.tipo;
	}

}
