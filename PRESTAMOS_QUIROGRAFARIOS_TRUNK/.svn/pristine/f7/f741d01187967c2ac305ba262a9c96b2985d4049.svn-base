package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.dinamico.dto.DataNotificaProvAprRequestDto;
import ec.gov.iess.creditos.dinamico.dto.DataProductoDinResponseDto;
import ec.gov.iess.creditos.dinamico.dto.DataProveedorRequestDto;
import ec.gov.iess.creditos.dinamico.dto.DataRespGenericaResponseDto;
import ec.gov.iess.creditos.pq.excepcion.ConecSrvPqDinamicoException;
import ec.gov.iess.creditos.pq.excepcion.ConsultaDataPqDinamicoException;
import ec.gov.iess.creditos.pq.excepcion.ExisteCodigoContratoException;
import ec.gov.iess.creditos.pq.excepcion.ParamCategoriaException;

/**
 * Servicio remoto para manejo de logica de negocio de prestamo dinamico
 * 
 * 
 * @author paul.sampedro
 *
 */
@Remote
public interface PrestamoDinamicoRemoteService {

	/**
	 * Metodo que consulta los datos de consulta de un proveedor
	 * 
	 * @param dataProveedor
	 * @return
	 * @throws ConsultaDataPqDinamicoException
	 * @throws ConecSrvPqDinamicoException
	 * @throws ExisteCodigoContratoException
	 * @throws ParamCategoriaException
	 */
	DataRespGenericaResponseDto consultarDataProveedor(DataProveedorRequestDto dataProveedor)
			throws ConsultaDataPqDinamicoException, ConecSrvPqDinamicoException, ExisteCodigoContratoException,
			ParamCategoriaException;

	/**
	 * Metodo que consulta los datos de un producto a partir de codigo especial
	 * 
	 * @param codigoEspecial
	 * @return
	 * @throws ConsultaDataPqDinamicoException
	 * @throws ConecSrvPqDinamicoException
	 * @throws ExisteCodigoContratoException
	 */
	DataProductoDinResponseDto consultarDataProducto(Long codigoEspecial)
			throws ConsultaDataPqDinamicoException, ConecSrvPqDinamicoException,ExisteCodigoContratoException;

	/**
	 * Metodo que realiza la notificacion al proveedor del credito generado y
	 * almacena datos
	 * 
	 * @param dataNotificaProv
	 * @throws ConsultaDataPqDinamicoException
	 * @throws ConecSrvPqDinamicoException
	 */
	void notificarAprobacionCredito(DataNotificaProvAprRequestDto dataNotificaProv)
			throws ConsultaDataPqDinamicoException, ConecSrvPqDinamicoException;

	/**
	 * Metodo que devuelve el documento en base 64
	 * 
	 * @param codigoContrato
	 * @return
	 * @throws ConsultaDataPqDinamicoException
	 * @throws ConecSrvPqDinamicoException
	 */
	String obtenerDocumentoContrato(String codigoContrato)
			throws ConsultaDataPqDinamicoException, ConecSrvPqDinamicoException;
}
