package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.focalizados.dto.ProductoDto;
import ec.gov.iess.creditos.focalizados.dto.ProveedorDto;
import ec.gov.iess.creditos.focalizados.dto.PuntoVentaDto;
import ec.gov.iess.creditos.focalizados.dto.PuntoVentaProductoDto;
import ec.gov.iess.creditos.pq.excepcion.PrestamosFocalizadosException;

/**
 * Servicio remoto para consulta de catalogos de prestamos focalizados
 * 
 * @author hugo.mora
 *
 */
@Remote
public interface CatalogoFocalizadosServicioRemote {

	/**
	 * Consulta los productos de pq focalizados dado el tipo de producto
	 * 
	 * @param tipoProducto
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	List<ProductoDto> consultarProductoPorTipoActivos(Integer tipoProducto) throws PrestamosFocalizadosException;

	/**
	 * Consulta los productos de pq focalizados dado el tipo de producto, el codigo MEER del proveedor y el codigo MEER
	 * del punto de venta
	 * 
	 * @param tipoProducto
	 * @param codigoProveedorMeer
	 * @param codigoPuntoVentaMeer
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	List<ProductoDto> consultarProductoDtoPorEstablecimientoYPV(Integer tipoProducto, Integer codigoProveedorMeer, Integer codigoPuntoVentaMeer)
			throws PrestamosFocalizadosException;

	/**
	 * Consulta productos dado el codigo MEER
	 * 
	 * @param codigoMeer
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	ProductoDto consultarProductoPorCodigoMeer(Integer codigoMeer) throws PrestamosFocalizadosException;

	/**
	 * Consulta los proveedores que se encuentren en la ubicacion segun el codigo INEC
	 * 
	 * @param codigoUbicacionInec
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	List<ProveedorDto> consultarProveedorPorCodigoInecActivos(String codigoUbicacionInec) throws PrestamosFocalizadosException;

	/**
	 * Consulta los proveedores por estado
	 * 
	 * @param estado
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	List<ProveedorDto> consultarProveedoresPorEstado(String estado) throws PrestamosFocalizadosException;

	/**
	 * Consulta los proveedores que tengan convenio por estado
	 * 
	 * @param estado
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	List<ProveedorDto> consultarProveedoresConvenioPorEstado(String estado) throws PrestamosFocalizadosException;

	/**
	 * Consulta los puntos de venta que se encuentran en la ubicacion segun el codigo INEC
	 * 
	 * @param codigoUbicacionInec
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	List<PuntoVentaDto> consultarPuntoVentaPorCodigoInecActivos(String codigoUbicacionInec) throws PrestamosFocalizadosException;

	/**
	 * Consulta los puntos de venta que se encuentran en la ubicacion segun el codigo INEC y pertenezcan al proveedor
	 * dado y que se encuentren en estado activo
	 * 
	 * @param codigoUbicacionInec
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	List<PuntoVentaDto> consultarPuntoVentaPorCodigoInecYProveedorActivos(String codigoUbicacionInec, Integer codigoProveedorMeer)
			throws PrestamosFocalizadosException;

	/**
	 * Consulta el producto por proveedor y pro punto de venta
	 * 
	 * @param codigoProveedorMeer
	 * @param codigoPuntoVentaMeer
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	List<PuntoVentaProductoDto> consultarProductoPorProveedorPV(Integer codigoProveedorMeer, Integer codigoPuntoVentaMeer)
			throws PrestamosFocalizadosException;

	/**
	 * Consulta proveedores dado el codigo MEER
	 * 
	 * @param codigoMeer
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	ProveedorDto consultarProveedorPorCodigoMeer(Integer codigoMeer) throws PrestamosFocalizadosException;

	/**
	 * Consulta puntos de venta dado el codigo meer
	 * 
	 * @param codigoMeer
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	PuntoVentaDto consultarPuntoVentaPorCodigoMeer(Integer codigoMeer) throws PrestamosFocalizadosException;

	/**
	 * Consulta el producto por proveedor, por tipo de producto, por codigo de producto y que se encuentren en estado
	 * activo
	 * 
	 * @param codigoProveedorMeer
	 * @param tipoProducto
	 * @param codigoProductoMeer
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	ProductoDto consultarProductoPorEstablecimientoTipoCodigoProducto(Integer codigoProveedorMeer, Integer tipoProducto, Integer codigoProductoMeer)
			throws PrestamosFocalizadosException;

	/**
	 * Consulta el producto por proveedor, por punto de venta y por tipo de producto y que se encuentren en estado
	 * activo
	 * 
	 * @param codigoProveedorMeer
	 * @param tipoProducto
	 * @return
	 * @throws PrestamosFocalizadosException
	 */
	List<ProductoDto> consultarProductoPorEstablecimientoTipo(Integer codigoProveedorMeer, Integer tipoProducto) throws PrestamosFocalizadosException;

}
