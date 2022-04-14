package ec.gov.iess.creditos.pq.servicio.impl;

import static ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum.CONSULTA_CONTRATO_PROV;
import static ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum.CONSULTA_DATA_PRODUCTO;
import static ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum.CONSULTA_DOC_CONTRATO;
import static ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum.NOTIFICAR_PROVEEDOR;

import java.lang.reflect.Type;
import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dinamico.dto.DataNotificaProvAprRequestDto;
import ec.gov.iess.creditos.dinamico.dto.DataProductoDinResponseDto;
import ec.gov.iess.creditos.dinamico.dto.DataProveedorRequestDto;
import ec.gov.iess.creditos.dinamico.dto.DataRespGenericaResponseDto;
import ec.gov.iess.creditos.dinamico.dto.PrestamoContratoDto;
import ec.gov.iess.creditos.pq.excepcion.ConecSrvPqDinamicoException;
import ec.gov.iess.creditos.pq.excepcion.ConsultaDataPqDinamicoException;
import ec.gov.iess.creditos.pq.excepcion.ExisteCodigoContratoException;
import ec.gov.iess.creditos.pq.excepcion.ParamCategoriaException;
import ec.gov.iess.creditos.pq.helper.dinamico.PqDinamicoHelper;
import ec.gov.iess.creditos.pq.servicio.PrestamoDinamicoLocalService;
import ec.gov.iess.creditos.pq.servicio.PrestamoDinamicoRemoteService;

/**
 * Servicio para manejo de logica de negocio de un prestamo con proveedor
 * 
 * @author paul.sampedro
 *
 */
@Stateless
public class PrestamoDinamicoImpl implements PrestamoDinamicoLocalService, PrestamoDinamicoRemoteService {

	private static final String SIN_DATA_RESPUESTA = "No se obtiene datos de respuesta";

	/**
	 * Constante que contiene el codigo exitoso devuelto
	 */
	private static final String COD_EXITOSO = "0";

	/**
	 * Existe el codigo de contrato
	 */
	private static final String COD_EXISTE_CONTRATO = "1";

	/**
	 * Codigo de error tecnico
	 */
	private static final String COD_ERROR_TECNICO = "7";

	/**
	 * Codigo error
	 */
	private static final String COD_ERR_PARAM_CATEG = "11";

	/**
	 * Servicio para obtener los parametros de la base de datos
	 */
	@EJB
	private transient ParametroBiessDao parametroBiessDao;

	/**
	 * URL de servicio rest que se obtiene para realizar la consulta de los
	 * parametros
	 */
	private transient String urlContrtProv;
	/**
	 * URL del servicio rest que se obtiene la consulta de los datos del producto
	 */
	private transient String urlDataProd;
	/**
	 * URL rest que envia la notificacion de aprobacion al proveedor
	 */
	private transient String urlNotifiProv;

	private transient String urlConsultaDoc;

	/**
	 * LOG para la clase
	 */
	private static final LoggerBiess LOG = LoggerBiess.getLogger(PrestamoDinamicoImpl.class);

	/**
	 * Metodo que inicializa los datos
	 */
	@PostConstruct
	public void init() {
		try {
			urlContrtProv = obtenerParametro(CONSULTA_CONTRATO_PROV);
			urlDataProd = obtenerParametro(CONSULTA_DATA_PRODUCTO);
			urlNotifiProv = obtenerParametro(NOTIFICAR_PROVEEDOR);
			urlConsultaDoc = obtenerParametro(CONSULTA_DOC_CONTRATO);

		} catch (ParametroBiessException e) {
			LOG.info("1.Error al obtener paramateros de servicios de de consulta de datos para pqdinamico", e);
		}

	}

	private String obtenerParametro(final ParametrosBiessEnum paramBiessEnum) throws ParametroBiessException {
		return parametroBiessDao
				.consultarPorIdNombreParametro(paramBiessEnum.getIdParametro(), paramBiessEnum.getNombreParametro())
				.getValorCaracter();
	}

	/**
	 * @throws ParamCategoriaException
	 * 
	 */
	@Override
	public DataRespGenericaResponseDto consultarDataProveedor(final DataProveedorRequestDto dataProveedor)
			throws ConsultaDataPqDinamicoException, ConecSrvPqDinamicoException, ExisteCodigoContratoException,
			ParamCategoriaException {

		final String gsonRespuesta = PqDinamicoHelper.obtenerTramaPeticionPOST(this.urlContrtProv,
				new Gson().toJson(dataProveedor));
		final Type tipo = new TypeToken<DataRespGenericaResponseDto>() {
		}.getType();
		final DataRespGenericaResponseDto respuesta = new Gson().fromJson(gsonRespuesta, tipo);

		if (respuesta == null) {
			throw new ConecSrvPqDinamicoException(SIN_DATA_RESPUESTA);
		} else {
			if (COD_EXITOSO.equals(respuesta.getCodigoErr())) {
				return respuesta;

			} else if (COD_EXISTE_CONTRATO.equals(respuesta.getCodigoErr())) {
				throw new ExisteCodigoContratoException(respuesta.getDescripcionErr());
			} else if (COD_ERROR_TECNICO.equals(respuesta.getCodigoErr())) {
				throw new ConecSrvPqDinamicoException(respuesta.getDescripcionErr());
			} else if (COD_ERR_PARAM_CATEG.equals(respuesta.getCodigoErr())) {
				throw new ParamCategoriaException(respuesta.getDescripcionErr());
			} else {
				throw new ConsultaDataPqDinamicoException(respuesta.getDescripcionErr());
			}

		}
	}

	@Override
	public DataProductoDinResponseDto consultarDataProducto(Long codigoEspecial)
			throws ConsultaDataPqDinamicoException, ConecSrvPqDinamicoException {
		final DataProveedorRequestDto paqueteRequest = new DataProveedorRequestDto();
		paqueteRequest.setCodigoEspecial(codigoEspecial);
		final String gsonRespuesta = PqDinamicoHelper.obtenerTramaPeticionPOST(urlDataProd,
				new Gson().toJson(paqueteRequest));
		final Type tipo = new TypeToken<DataProductoDinResponseDto>() {
		}.getType();
		DataProductoDinResponseDto respuesta = null;
		try {
			respuesta = new Gson().fromJson(gsonRespuesta, tipo);
		} catch (IllegalStateException e) {
			throw new ConecSrvPqDinamicoException(SIN_DATA_RESPUESTA, e);
		}
		if (respuesta == null) {
			throw new ConecSrvPqDinamicoException(SIN_DATA_RESPUESTA);
		} else {

			if (COD_EXITOSO.equals(respuesta.getCodigoErr())) {
				return respuesta;

			} else {
				throw new ConsultaDataPqDinamicoException(respuesta.getDescripcionErr());
			}
		}

	}

	@Override
	public void notificarAprobacionCredito(final DataNotificaProvAprRequestDto dataNotificaProv)
			throws ConsultaDataPqDinamicoException, ConecSrvPqDinamicoException {

		final String gsonRespuesta = PqDinamicoHelper.obtenerTramaPeticionPOST(urlNotifiProv,
				new Gson().toJson(dataNotificaProv));
		final Type tipo = new TypeToken<DataProductoDinResponseDto>() {
		}.getType();
		DataProductoDinResponseDto respuesta = null;
		try {
			respuesta = new Gson().fromJson(gsonRespuesta, tipo);
		} catch (IllegalStateException e) {
			throw new ConecSrvPqDinamicoException("Los datos de respuesta no tienen el formato adecuado", e);
		}
		if (respuesta == null) {
			throw new ConecSrvPqDinamicoException("La respuesta recibida esta vacia");
		} else {

			if (COD_EXITOSO.equals(respuesta.getCodigoErr())) {
				LOG.info("Se ha notificado a la empresa de ruc".concat(dataNotificaProv.getRucEmpresa()));
			} else {
				throw new ConsultaDataPqDinamicoException(
						respuesta.getCodigoErr().concat("-").concat(respuesta.getDescripcionErr()));
			}
		}
	}

	@Override
	public String obtenerDocumentoContrato(String codigoContrato)
			throws ConsultaDataPqDinamicoException, ConecSrvPqDinamicoException {
		final DataProveedorRequestDto paqueteRequest = new DataProveedorRequestDto();
		paqueteRequest.setCodigoContrato(codigoContrato);
		final String gsonRespuesta = PqDinamicoHelper.obtenerTramaPeticionPOST(urlConsultaDoc,
				new Gson().toJson(paqueteRequest));
		final Type tipo = new TypeToken<PrestamoContratoDto>() {
		}.getType();

		PrestamoContratoDto respuesta = null;
		try {
			respuesta = new Gson().fromJson(gsonRespuesta, tipo);
		} catch (IllegalStateException e) {
			throw new ConecSrvPqDinamicoException("Los datos de respuesta no tienen el formato adecuado", e);
		}
		if (respuesta == null) {
			throw new ConecSrvPqDinamicoException("La respuesta recibida esta vacia");
		} else {

			if (COD_EXITOSO.equals(respuesta.getCodigoErr())) {
				byte[] decodeResult = DatatypeConverter.parseBase64Binary(respuesta.getDocContrato());
				return new String(decodeResult, Charset.forName("UTF-8"));
			} else {
				throw new ConsultaDataPqDinamicoException(
						respuesta.getCodigoErr().concat("-").concat(respuesta.getDescripcionErr()));
			}
		}

	}

}
