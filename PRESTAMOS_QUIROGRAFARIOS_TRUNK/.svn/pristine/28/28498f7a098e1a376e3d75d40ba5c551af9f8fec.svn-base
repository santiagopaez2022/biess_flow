/**
 * 
 */
package ec.gov.iess.creditos.enumeracion;


/**
 * Enumeracion para destinos comerciales sacados desde base de datos.
 *
 * @author paul.sampedro
 */
public enum DestinoComercialEnum {
	
	/**  cin. */
	CIN("Cocinas de inducción",9l,"FOC"),
	
	/**  cpt. */
	CPT("Computadores",5l,"COM"),
	
	/**  ebi. */
	EBI("Edúcate con BIESS",43l,"EBI"),
	
	/**  emu. */
	EMU("Ecuador tu lugar",4l,"ECU_TUR"),
	
	/**  pal. */
	PAL("Pens.alimenticias",7l,"PEN"),
	
	/**  qbi. */
	QBI("Quirografario BIESS",1l,"NOR"),
	
	/**  tcr. */
	TCR("Tren crucero",6l,"TREN"),
	
	/**  vec. */
	VEC("Vive Ecuador",42l,"VEC"),
	
	/**  vpe. */
	VPE("Viaja primero Ecuador",8l,"TUR"),
	
	/**  qem. */
	QEM("Quirografario Emergente",11l,"EMERG"),
	
	/**  q144. */
	Q144("MIG Resolución 144",0l,"Q144");

	
	/**  descripcion. */
	private String descripcion; 
	
	/**  codigo tipo producto. */
	private Long codigoTipoProducto;
	
	/**  cod prod pq. */
	private String codProdPq;
	
	/**
	 * Instantiates a new destino comercial enum.
	 *
	 * @param descripcion the descripcion
	 * @param codigoTipoProducto the codigo tipo producto
	 * @param codProdPq the cod prod pq
	 */
	private DestinoComercialEnum(final String descripcion,final Long codigoTipoProducto,final String codProdPq) {
		this.descripcion=descripcion;
		this.codProdPq=codProdPq;
		this.codigoTipoProducto=codigoTipoProducto;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	
	/**
	 * Obtiene la descripcion de un tipo de producto.
	 * 
	 * @param codigoTipoProducto
	 *            - codigo tipo de producto.
	 * 
	 * @return descripcion.
	 */
	public static DestinoComercialEnum getTiposProductosPq(final Long codigoTipoProducto) {
		switch (codigoTipoProducto.intValue()) {
		case 1:
			return DestinoComercialEnum.QBI;
		case 4:
			return DestinoComercialEnum.EMU;
		case 5:
			return DestinoComercialEnum.CPT;
		case 6:
			return DestinoComercialEnum.TCR;
		case 7:
			return DestinoComercialEnum.PAL;
		case 8:
			return DestinoComercialEnum.VPE;
		case 9:
			return DestinoComercialEnum.CIN;
		case 11:	
			return DestinoComercialEnum.QEM;
		case 42:
			return DestinoComercialEnum.VEC;
		case 43:
			return DestinoComercialEnum.EBI;
	
			
		default:
			return DestinoComercialEnum.QBI;
		}
	}

	/**
	 * Gets the codigo tipo producto.
	 *
	 * @return the codigo tipo producto
	 */
	public Long getCodigoTipoProducto() {
		return codigoTipoProducto;
	}


	/**
	 * Gets the cod prod pq.
	 *
	 * @return the cod prod pq
	 */
	public String getCodProdPq() {
		return codProdPq;
	}

}
