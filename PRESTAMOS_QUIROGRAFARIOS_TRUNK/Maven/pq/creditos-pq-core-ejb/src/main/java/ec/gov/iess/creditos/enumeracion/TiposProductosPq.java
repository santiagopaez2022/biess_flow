/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.enumeracion;

/**
 * 
 * <b> Enumeración para los tipos de productos en PQ. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:28:11 $]
 *          </p>
 */
public enum TiposProductosPq {
	// INC-2272 Pensiones Alimenticias
	NOR("Normal", 1L, CategoriaTipoProductoPq.CAT_NORMALES, true, 1),
	COM("Computadoras", 5L,	CategoriaTipoProductoPq.CAT_BIENES, false, 5), 
	TREN("Tren crucero", 6L, CategoriaTipoProductoPq.CAT_SERVICIOS, true, 6),
	PEN("Pensiones Alimenticias", 7L,  CategoriaTipoProductoPq.CAT_SERVICIOS, true, 7),
	TUR("Viaja Primero Ecuador", 8L,  CategoriaTipoProductoPq.CAT_SERVICIOS, true, 8),
	FOC("Cocinas de Inducci\u00F3n", 9L, CategoriaTipoProductoPq.CAT_FOCALIZADOS, true, 9),
	EMERG("Alivio Econ\u00F3mico", 1L, CategoriaTipoProductoPq.CAT_NORMALES, false, 10),
	ECU_TUR("Ecuador Tu Lugar en el Mundo", 4L, CategoriaTipoProductoPq.CAT_SERVICIOS, true, 4),
	DINAMICO("Prestamo quirografario dinamico", 4L, CategoriaTipoProductoPq.CAT_SERVICIOS, true, 4);

	private String descripcion;
	private Long codigoTipoProducto;
	private CategoriaTipoProductoPq categoriaTipoProducto;
	private boolean activo;
	private int identificadorProducto;

	/**
	 * 
	 * @param descripcion
	 */
	TiposProductosPq(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @param descripcion
	 * @param codigoTipoProducto
	 * @param categoriaTipoProducto
	 */
	TiposProductosPq(String descripcion, Long codigoTipoProducto,
			CategoriaTipoProductoPq categoriaTipoProducto, boolean activo, int identificadorProducto) {
		this.descripcion = descripcion;
		this.codigoTipoProducto = codigoTipoProducto;
		this.categoriaTipoProducto = categoriaTipoProducto;
		this.activo = activo;
		this.identificadorProducto = identificadorProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Long getCodigoTipoProducto() {
		return codigoTipoProducto;
	}

	public CategoriaTipoProductoPq getCategoriaTipoProducto() {
		return categoriaTipoProducto;
	}

	public void setCategoriaTipoProducto(
			CategoriaTipoProductoPq categoriaTipoProducto) {
		this.categoriaTipoProducto = categoriaTipoProducto;
	}

	public boolean isActivo() {
		return activo;
	}

	public int getIdentificadorProducto() {
		return identificadorProducto;
	}

	/**
	 * Obtiene la descripcion de un tipo de producto.
	 * 
	 * @param codigoTipoProducto
	 *            - codigo tipo de producto.
	 * 
	 * @return descripcion.
	 */
	public static TiposProductosPq getTiposProductosPq(Long codigoTipoProducto) {
		switch (codigoTipoProducto.intValue()) {
		case 1:
			return TiposProductosPq.NOR;
		case 4:
			return TiposProductosPq.ECU_TUR;
		case 5:
			return TiposProductosPq.COM;
		case 6:
			return TiposProductosPq.TREN;
		case 7:
			return TiposProductosPq.PEN;
		case 8:
			return TiposProductosPq.TUR;
		case 9:
			return TiposProductosPq.FOC;
		default:
			return null;
		}
	}
	
	/**
	 * Obtiene un TiposProductosPq dado el identificador del producto
	 * 
	 * @param identificadorProducto
	 * @return
	 */
	public static TiposProductosPq obtenerTiposProductosPorIdentificador(int identificadorProducto) {
		switch (identificadorProducto) {
		case 1:
			return TiposProductosPq.NOR;
		case 4:
			return TiposProductosPq.ECU_TUR;
		case 5:
			return TiposProductosPq.COM;
		case 6:
			return TiposProductosPq.TREN;
		case 7:
			return TiposProductosPq.PEN;
		case 8:
			return TiposProductosPq.TUR;
		case 9:
			return TiposProductosPq.FOC;
		case 10:
			return TiposProductosPq.EMERG;
		default:
			return null;
		}
	}

	/**
	 * Obtiene la cataegoria a la que pertenece un tipo de producto.
	 * 
	 * @param codigoTipoProducto
	 *            - codigo tipo de producto
	 * 
	 * @return categoria.
	 */
	public static CategoriaTipoProductoPq getCategoriaTipoProductoPq(
			Long codigoTipoProducto) {
		switch (codigoTipoProducto.intValue()) {
		case 1:
			return CategoriaTipoProductoPq.CAT_NORMALES;
		case 4:
			return CategoriaTipoProductoPq.CAT_SERVICIOS;
		case 5:
			return CategoriaTipoProductoPq.CAT_BIENES;
		case 6:
			return CategoriaTipoProductoPq.CAT_SERVICIOS;
		case 7:
			return CategoriaTipoProductoPq.CAT_SERVICIOS;
		case 8:
			return CategoriaTipoProductoPq.CAT_SERVICIOS;
		case 9: 
			return CategoriaTipoProductoPq.CAT_FOCALIZADOS;
		default:
			return null;
		}
	}
	
	/**
	 * Obtiene un CategoriaTipoProductoPq dado el identificador del producto
	 * 
	 * @param identificadorProducto
	 * @return
	 */
	public static CategoriaTipoProductoPq obtieneCategoriaProductoPorIdentificador(int identificadorProducto) {
		switch (identificadorProducto) {
		case 1:
			return CategoriaTipoProductoPq.CAT_NORMALES;
		case 4:
			return CategoriaTipoProductoPq.CAT_SERVICIOS;
		case 5:
			return CategoriaTipoProductoPq.CAT_BIENES;
		case 6:
			return CategoriaTipoProductoPq.CAT_SERVICIOS;
		case 7:
			return CategoriaTipoProductoPq.CAT_SERVICIOS;
		case 8:
			return CategoriaTipoProductoPq.CAT_SERVICIOS;
		case 9:
			return CategoriaTipoProductoPq.CAT_FOCALIZADOS;
		case 10:
			return CategoriaTipoProductoPq.CAT_NORMALES;
		default:
			return null;
		}
	}
	
}
