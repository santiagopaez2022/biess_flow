package ec.gov.iess.creditos.pq.servicio.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.creditos.pq.enumeracion.RespuestaCatalogoFocalizadoEnum;
import ec.fin.biess.creditos.pq.servicio.ProveedorServicio;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.focalizados.dto.CatalogoFocalizadoResponseDto;
import ec.gov.iess.creditos.focalizados.dto.ProductoDto;
import ec.gov.iess.creditos.focalizados.dto.ProductoFocalizadoRequestDto;
import ec.gov.iess.creditos.focalizados.dto.ProveedorDto;
import ec.gov.iess.creditos.focalizados.dto.ProveedorFocalizadoRequestDto;
import ec.gov.iess.creditos.focalizados.dto.PuntoVentaDto;
import ec.gov.iess.creditos.focalizados.dto.PuntoVentaFocalizadoRequestDto;
import ec.gov.iess.creditos.focalizados.dto.PuntoVentaProductoDto;
import ec.gov.iess.creditos.focalizados.dto.PuntoVentaProductoFocalizadoRequestDto;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.pq.excepcion.PrestamosFocalizadosException;
import ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioRemote;

/**
 * Servicio para consulta de catalogos de prestamos focalizados
 * 
 * @author hugo.mora
 *
 */
@Stateless
public class CatalogoFocalizadosServicioImpl implements CatalogoFocalizadosServicioLocal, CatalogoFocalizadosServicioRemote {

	private static final LoggerBiess LOG = LoggerBiess.getLogger("CatalogoFocalizadosServicioImpl");

	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private ProveedorServicio proveedorServicio;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarProductoPorTipoActivos(java.lang.
	 * Integer)
	 */
	@Override
	public List<ProductoDto> consultarProductoPorTipoActivos(Integer tipoProducto) throws PrestamosFocalizadosException {
		List<ProductoDto> listaProductos = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PRODUCTO_POR_TIPO.getIdParametro(),
					ParametrosBiessEnum.PRODUCTO_POR_TIPO.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			ProductoFocalizadoRequestDto productoRequest = new ProductoFocalizadoRequestDto();
			productoRequest.setTipoProducto(tipoProducto);

			final String json = gson.toJson(productoRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();
			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<ProductoDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<ProductoDto> respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					listaProductos = respuesta.getListaCatalogos();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(
							RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion() + " para el tipo de producto ingresado.");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}

		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar productos de pq focalizado por tipo", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar productos de pq focalizado por tipo", e);
			throw new PrestamosFocalizadosException("2. Se present\u00F3 un problema al consultar productos. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar productos de pq focalizado por tipo", e);
			throw new PrestamosFocalizadosException("3. Se present\u00F3 un problema al consultar productos. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar productos de pq focalizado por tipo", e);
			throw new PrestamosFocalizadosException(e);
		}

		return listaProductos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarProductoDtoPorEstablecimientoYPV(java.
	 * lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ProductoDto> consultarProductoDtoPorEstablecimientoYPV(Integer tipoProducto, Integer codigoProveedorMeer,
			Integer codigoPuntoVentaMeer) throws PrestamosFocalizadosException {
		List<ProductoDto> listaProductos = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PRODUCTO_EST_PV.getIdParametro(),
					ParametrosBiessEnum.PRODUCTO_EST_PV.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			ProductoFocalizadoRequestDto productoRequest = new ProductoFocalizadoRequestDto();
			productoRequest.setTipoProducto(tipoProducto);
			productoRequest.setCodigoProveedorMeer(codigoProveedorMeer);
			productoRequest.setCodigoPuntoVentaMeer(codigoPuntoVentaMeer);

			final String json = gson.toJson(productoRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();
			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<ProductoDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<ProductoDto> respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					listaProductos = respuesta.getListaCatalogos();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion()
							+ " para el tipo de producto, establecimiento y punto de venta ingresado");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}

		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar productos de pq focalizado por tipo, proveedor y punto de venta", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar productos de pq focalizado por tipo, proveedor y punto de venta", e);
			throw new PrestamosFocalizadosException("2. Se present\u00F3 un problema al consultar productos por punto de venta. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar productos de pq focalizado por tipo, proveedor y punto de venta", e);
			throw new PrestamosFocalizadosException("3. Se present\u00F3 un problema al consultar productos por punto de venta. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar productos de pq focalizado por tipo, proveedor y punto de venta", e);
			throw new PrestamosFocalizadosException(e);
		}
		return listaProductos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarProductoPorCodigoMeer(java.lang.
	 * Integer)
	 */
	@Override
	public ProductoDto consultarProductoPorCodigoMeer(Integer codigoMeer) throws PrestamosFocalizadosException {
		ProductoDto producto = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PRODUCTO_POR_CODIGO_MEER.getIdParametro(),
					ParametrosBiessEnum.PRODUCTO_POR_CODIGO_MEER.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			ProductoFocalizadoRequestDto productoRequest = new ProductoFocalizadoRequestDto();
			productoRequest.setCodigoProductoMeer(codigoMeer);

			final String json = gson.toJson(productoRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();
			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<ProductoDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<ProductoDto> respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					producto = respuesta.getCatalogo();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(
							RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion() + " para el producto con codigo MEER ingresado");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar producto de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar producto de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException("2. Se present\u00F3 un problema al consultar productos por c\u00F3digo. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar producto de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException("3. Se present\u00F3 un problema al consultar productos por c\u00F3digo. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar producto de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException(e);
		}
		return producto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarProveedorPorCodigoInecActivos(java.
	 * lang.String)
	 */
	@Override
	public List<ProveedorDto> consultarProveedorPorCodigoInecActivos(String codigoUbicacionInec) throws PrestamosFocalizadosException {
		List<ProveedorDto> listaProveedores = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PROVEEDOR_CODIGO_INEC.getIdParametro(),
					ParametrosBiessEnum.PROVEEDOR_CODIGO_INEC.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			ProveedorFocalizadoRequestDto proveedorRequest = new ProveedorFocalizadoRequestDto();
			proveedorRequest.setCodigoUbicacionInec(codigoUbicacionInec);

			final String json = gson.toJson(proveedorRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();

			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<ProveedorDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<ProveedorDto> respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					listaProveedores = respuesta.getListaCatalogos();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(
							RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion() + " para la ubicaci\u00F3n seleccionada.");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar proveedores de pq focalizado por codigo de ubicacion inec", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar proveedores de pq focalizado por codigo de ubicacion inec", e);
			throw new PrestamosFocalizadosException("2. Se present\u00F3 un problema al consultar establecimientos por ubicaci\u00F3n. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar proveedores de pq focalizado por codigo de ubicacion inec", e);
			throw new PrestamosFocalizadosException("3. Se present\u00F3 un problema al consultar establecimientos por ubicaci\u00F3n. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar proveedores de pq focalizado por codigo de ubicacion inec", e);
			throw new PrestamosFocalizadosException(e);
		}
		return listaProveedores;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarProveedoresPorEstado(java.lang.String)
	 */
	@Override
	public List<ProveedorDto> consultarProveedoresPorEstado(String estado) throws PrestamosFocalizadosException {
		List<ProveedorDto> listaProveedores = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PROVEEDOR_ESTADO.getIdParametro(),
					ParametrosBiessEnum.PROVEEDOR_ESTADO.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			ProveedorFocalizadoRequestDto proveedorRequest = new ProveedorFocalizadoRequestDto();
			proveedorRequest.setEstado(estado);

			final String json = gson.toJson(proveedorRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();

			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<ProveedorDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<ProveedorDto> respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					listaProveedores = respuesta.getListaCatalogos();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(
							RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion() + " para el estado especificado.");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar proveedores de pq focalizado por estado", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar proveedores de pq focalizado por estado", e);
			throw new PrestamosFocalizadosException("2. Se present\u00F3 un problema al consultar establecimientos. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar proveedores de pq focalizado por estado", e);
			throw new PrestamosFocalizadosException("3. Se present\u00F3 un problema al consultar establecimientos. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar proveedores de pq focalizado por estado", e);
			throw new PrestamosFocalizadosException(e);
		}
		return listaProveedores;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarPuntoVentaPorCodigoInecActivos(java.
	 * lang.String)
	 */
	@Override
	public List<PuntoVentaDto> consultarPuntoVentaPorCodigoInecActivos(String codigoUbicacionInec) throws PrestamosFocalizadosException {
		List<PuntoVentaDto> listaPuntoVentas = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PUNTO_VENTA_CODIGO_INEC.getIdParametro(),
					ParametrosBiessEnum.PUNTO_VENTA_CODIGO_INEC.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			PuntoVentaFocalizadoRequestDto puntoVentaRequest = new PuntoVentaFocalizadoRequestDto();
			puntoVentaRequest.setCodigoUbicacionInec(codigoUbicacionInec);

			final String json = gson.toJson(puntoVentaRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();

			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<PuntoVentaDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<PuntoVentaDto> respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					listaPuntoVentas = respuesta.getListaCatalogos();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(
							RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion() + " para la ubicaci\u00F3n seleccionada.");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}

		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar puntos de venta de pq focalizado por codigo de ubicacion inec", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar puntos de venta de pq focalizado por codigo de ubicacion inec", e);
			throw new PrestamosFocalizadosException("2. Se present\u00F3 un problema al consultar puntos de venta por ubicaci\u00F3n. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar puntos de venta de pq focalizado por codigo de ubicacion inec", e);
			throw new PrestamosFocalizadosException("3. Se present\u00F3 un problema al consultar puntos de venta por ubicaci\u00F3n. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar puntos de venta de pq focalizado por codigo de ubicacion inec", e);
			throw new PrestamosFocalizadosException(e);
		}
		return listaPuntoVentas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#
	 * consultarPuntoVentaPorCodigoInecYProveedorActivos(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<PuntoVentaDto> consultarPuntoVentaPorCodigoInecYProveedorActivos(String codigoUbicacionInec, Integer codigoProveedorMeer)
			throws PrestamosFocalizadosException {
		List<PuntoVentaDto> listaPuntoVentas = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PUNTO_VENTA_PROVEEDOR_UBICACION.getIdParametro(),
					ParametrosBiessEnum.PUNTO_VENTA_PROVEEDOR_UBICACION.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			PuntoVentaFocalizadoRequestDto puntoVentaRequest = new PuntoVentaFocalizadoRequestDto();
			puntoVentaRequest.setCodigoUbicacionInec(codigoUbicacionInec);
			puntoVentaRequest.setCodigoProveedorMeer(codigoProveedorMeer);

			final String json = gson.toJson(puntoVentaRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();

			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<PuntoVentaDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<PuntoVentaDto> respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					listaPuntoVentas = respuesta.getListaCatalogos();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion()
							+ " para la ubicaci\u00F3n y proveedor seleccionado.");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}

		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar puntos de venta de pq focalizado por codigo de ubicacion inec y por codigo de establecimiento", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar puntos de venta de pq focalizado por codigo de ubicacion inec y por codigo de establecimiento", e);
			throw new PrestamosFocalizadosException("2.- Se present\u00F3 un problema al consultar puntos de venta por ubicaci\u00F3n. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar puntos de venta de pq focalizado por codigo de ubicacion inec y por codigo de establecimiento", e);
			throw new PrestamosFocalizadosException("3.- Se present\u00F3 un problema al consultar puntos de venta por ubicaci\u00F3n. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar puntos de venta de pq focalizado por codigo de ubicacion inec y por codigo de establecimiento", e);
			throw new PrestamosFocalizadosException(e);
		}
		return listaPuntoVentas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarProductoPorProveedorPV(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public List<PuntoVentaProductoDto> consultarProductoPorProveedorPV(Integer codigoProveedorMeer, Integer codigoPuntoVentaMeer)
			throws PrestamosFocalizadosException {
		List<PuntoVentaProductoDto> listaPuntoVentaProductoDto = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PROD_PROV_PV.getIdParametro(),
					ParametrosBiessEnum.PROD_PROV_PV.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			PuntoVentaProductoFocalizadoRequestDto request = new PuntoVentaProductoFocalizadoRequestDto();
			request.setCodigoProveedorMeer(codigoProveedorMeer);
			request.setCodigoPuntoVentaMeer(codigoPuntoVentaMeer);

			final String json = gson.toJson(request);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();

			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<PuntoVentaProductoDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<PuntoVentaProductoDto> respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					listaPuntoVentaProductoDto = respuesta.getListaCatalogos();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(
							RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion() + " para el proveedor y punto de venta ingresado.");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}

		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar productos por proveedor y puntos de venta de pq focalizado", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar productos por proveedor y puntos de venta de pq focalizado", e);
			throw new PrestamosFocalizadosException("2.- Se present\u00F3 un problema al consultar productos por punto de venta. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar productos por proveedor y puntos de venta de pq focalizado", e);
			throw new PrestamosFocalizadosException("3.- Se present\u00F3 un problema al consultar productos por punto de venta. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar productos por proveedor y puntos de venta de pq focalizado", e);
			throw new PrestamosFocalizadosException(e);
		}
		return listaPuntoVentaProductoDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarProveedorPorCodigoMeer(java.lang.
	 * Integer)
	 */
	@Override
	public ProveedorDto consultarProveedorPorCodigoMeer(Integer codigoMeer) throws PrestamosFocalizadosException {
		ProveedorDto proveedor = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PROVEEDOR_POR_CODIGO_MEER.getIdParametro(),
					ParametrosBiessEnum.PROVEEDOR_POR_CODIGO_MEER.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			ProveedorFocalizadoRequestDto proveedorRequest = new ProveedorFocalizadoRequestDto();
			proveedorRequest.setCodigoProveedorMeer(codigoMeer);

			final String json = gson.toJson(proveedorRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();
			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<ProveedorDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<ProveedorDto> respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					proveedor = respuesta.getCatalogo();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(
							RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion() + " para el proveedor con codigo MEER ingresado");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar proveedor de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar proveedor de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException("2. Se present\u00F3 un problema al consultar establecimiento por c\u00F3digo. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar proveedor de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException("3. Se present\u00F3 un problema al consultar establecimiento por c\u00F3digo. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar proveedor de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException(e);
		}
		return proveedor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarPuntoVentaPorCodigoMeer(java.lang.
	 * Integer)
	 */
	@Override
	public PuntoVentaDto consultarPuntoVentaPorCodigoMeer(Integer codigoMeer) throws PrestamosFocalizadosException {
		PuntoVentaDto puntoVenta = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PUNTO_VENTA_POR_CODIGO_MEER.getIdParametro(),
					ParametrosBiessEnum.PUNTO_VENTA_POR_CODIGO_MEER.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			PuntoVentaFocalizadoRequestDto puntoVentaRequest = new PuntoVentaFocalizadoRequestDto();
			puntoVentaRequest.setCodigoPuntoVentaMeer(codigoMeer);

			final String json = gson.toJson(puntoVentaRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();
			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<PuntoVentaDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<PuntoVentaDto> respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					puntoVenta = respuesta.getCatalogo();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion()
							+ " para el punto de venta con codigo MEER ingresado");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar punto de venta de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar punto de venta de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException("2. Se present\u00F3 un problema al consultar punto de venta por c\u00F3digo. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar punto de venta de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException("3. Se present\u00F3 un problema al consultar punto de venta por c\u00F3digo. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar punto de venta de pq focalizado por codigo MEER", e);
			throw new PrestamosFocalizadosException(e);
		}
		return puntoVenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarProveedoresConvenioPorEstado(java.lang
	 * .String)
	 */
	@Override
	public List<ProveedorDto> consultarProveedoresConvenioPorEstado(String estado) throws PrestamosFocalizadosException {
		List<ProveedorDto> listaProveedorCatalogo = this.consultarProveedoresPorEstado(estado);

		List<Proveedor> listaProveedorConvenio = this.proveedorServicio
				.consultarProveedorActivoCodpretip(TiposProductosPq.FOC.getCodigoTipoProducto());
		
		List<ProveedorDto> listaProveedores = new ArrayList<ProveedorDto>();
		for (ProveedorDto proveedorDto : listaProveedorCatalogo) {
			if (this.contieneLista(listaProveedorConvenio, proveedorDto)) {
				listaProveedores.add(proveedorDto);
			}
		}

		return listaProveedores;
	}
	
	/**
	 * Verifica si el proveedor catalogo esta en la lista de proveedor convenio
	 * 
	 * @param proveedorConvenio
	 * @param proveedorCatalogo
	 * @return
	 */
	private boolean contieneLista(List<Proveedor> proveedorConvenio, ProveedorDto proveedorCatalogo) {
		boolean respuesta = false;
		for (Proveedor proveedor : proveedorConvenio) {
			if (proveedorCatalogo.compareTo(proveedor) == 0) {
				respuesta = true;
			}
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#
	 * consultarProductoPorEstablecimientoTipoCodigoProducto(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ProductoDto consultarProductoPorEstablecimientoTipoCodigoProducto(Integer codigoProveedorMeer, Integer tipoProducto,
			Integer codigoProductoMeer) throws PrestamosFocalizadosException {
		ProductoDto producto = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PRODUCTO_EST_TIP_CODPROD.getIdParametro(),
					ParametrosBiessEnum.PRODUCTO_EST_TIP_CODPROD.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
			
			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			
			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			ProductoFocalizadoRequestDto productoRequest = new ProductoFocalizadoRequestDto();
			productoRequest.setCodigoProveedorMeer(codigoProveedorMeer);
			productoRequest.setTipoProducto(tipoProducto);
			productoRequest.setCodigoProductoMeer(codigoProductoMeer);
			
			final String json = gson.toJson(productoRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();
			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<ProductoDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<ProductoDto> respuesta = gson.fromJson(respuestaJson, tipo);
			
			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					producto = respuesta.getCatalogo();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(
							RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion() + " para el proveedor y producto ingresado");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}
			
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar producto por proveedor, tipo producto y codigo producto", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. 1. Error al consultar producto por proveedor, tipo producto y codigo producto", e);
			throw new PrestamosFocalizadosException("2. Se present\u00F3 un problema al consultar establecimiento por c\u00F3digo. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. 1. Error al consultar producto por proveedor, tipo producto y codigo producto", e);
			throw new PrestamosFocalizadosException("3. Se present\u00F3 un problema al consultar establecimiento por c\u00F3digo. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. 1. Error al consultar producto por proveedor, tipo producto y codigo producto", e);
			throw new PrestamosFocalizadosException(e);
		}
		return producto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal#consultarProductoPorEstablecimientoTipo(java.
	 * lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ProductoDto> consultarProductoPorEstablecimientoTipo(Integer codigoProveedorMeer, Integer tipoProducto)
			throws PrestamosFocalizadosException {
		List<ProductoDto> listaProductos = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.PRODUCTO_EST_TIP.getIdParametro(),
					ParametrosBiessEnum.PRODUCTO_EST_TIP.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			
			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			
			final Gson gson = new Gson();
			ProductoFocalizadoRequestDto productoRequest = new ProductoFocalizadoRequestDto();
			productoRequest.setCodigoProveedorMeer(codigoProveedorMeer);
			productoRequest.setTipoProducto(tipoProducto);
			
			final String json = gson.toJson(productoRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();
			final Type tipo = new TypeToken<CatalogoFocalizadoResponseDto<ProductoDto>>() {
			}.getType();
			final CatalogoFocalizadoResponseDto<ProductoDto> respuesta = gson.fromJson(respuestaJson, tipo);
			
			if (respuesta != null) {
				if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.EXITO.getCodigo())) {
					listaProductos = respuesta.getListaCatalogos();
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getCodigo())) {
					throw new PrestamosFocalizadosException(
							RespuestaCatalogoFocalizadoEnum.NO_EXISTEN_REGISTROS.getDescripcion() + " para el tipo de producto y establecimiento ingresado.");
				} else if (respuesta.getCodigo().equals(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getCodigo())) {
					throw new PrestamosFocalizadosException(RespuestaCatalogoFocalizadoEnum.ERROR_CONSULTA.getDescripcion());
				}
			}
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al consultar productos de pq focalizado por tipo y proveedor", e);
			throw new PrestamosFocalizadosException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar productos de pq focalizado por tipo y proveedor", e);
			throw new PrestamosFocalizadosException("2. Se present\u00F3 un problema al consultar productos. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar productos de pq focalizado por tipo y proveedor", e);
			throw new PrestamosFocalizadosException("3. Se present\u00F3 un problema al consultar productos. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al consultar productos de pq focalizado por tipo y proveedor", e);
			throw new PrestamosFocalizadosException(e);
		}
		
		return listaProductos;
	}

}
