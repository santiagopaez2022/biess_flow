/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.ErrorParametrosException;
import ec.gov.iess.creditos.modelo.persistencia.SolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal 03/10/2011
 * 
 */
@Local
public interface SolicitudCreditoDao extends
		GenericDao<SolicitudCredito, SolicitudCreditoPK> {

	public SolicitudCredito solicitudCreditoVigenteSolicitante(String cedula,
			int tipoSolicitud);

	public SolicitudCredito solicitudCreditoVigenteConyugue(String cedula,
			int tipoSolicitud);

	/**
	 * Consulta la solicitud con todas las relaciones, detalle solicitud,
	 * solicitantes, ingreso egreso solicitantes, precondiciones,
	 * 
	 * @param estados
	 *            lista de estados a consultar
	 * @param cedula
	 *            cedula a consultar
	 * @param tipoSolicitud
	 *            tipos de solicitud
	 * @return un objeto de negocio con los datos del solicitante, caso
	 *         contrario null
	 */
	public SolicitudCredito consultarSolicitudCreditoEager(
			List<String> estados, String cedula, List<Long> tipoSolicitud);

	/**
	 * Consulta la solicitud con todas las relaciones, detalle solicitud,
	 * solicitantes, ingreso egreso solicitantes, precondiciones a partir de la
	 * clave principal
	 * 
	 * @param numeroSolictud
	 * @param tipoSolicitud
	 * @return un objeto de tipo solictud caso contrario null
	 */
	public SolicitudCredito consultarSolicitudCreditoPkEager(
			Long numeroSolictud, Long tipoSolicitud);

	/**
	 * Consulta las solicitudes de cerdito a partir de un estado, cedula,
	 * tipoSolicitud
	 * 
	 * @param estados
	 * @param cedula
	 * @param tipoSolicitud
	 * @return
	 */
	public List<SolicitudCredito> consultarSolicitudCredito(
			List<String> estados, String cedula, List<Long> tipoSolicitud);

	/**
	 * Consulta la solicitud de prestamos, en base a un tipo de solicitud, que
	 * no se encuentre en los estados, de una cedula especifica
	 * 
	 * @param tipoSolicitudList
	 *            lista de tipos de solicitud
	 * @param estadosList
	 *            lista de estados
	 * @param cedula
	 *            identificador del solicitante
	 * @return una lista de solicitudes {@link SolicitudCredito}
	 * @throws ErrorParametrosException
	 *             encaso de que la lista de estados, y lista de tipos de
	 *             solicitud sean nulas
	 */
	public List<SolicitudCredito> consultarCedulaSinEstadosYTipoSolicitud(
			List<Long> tipoSolicitudList, List<String> estadosList,
			String cedula) throws ErrorParametrosException;

	/**
	 * Consulta la solicitud de prestamos, en base a un tipo de solicitud, que
	 * se encuentre en los estados, de una cedula especifica
	 * 
	 * @param tipoSolicitudList
	 *            lista de tipos de solicitud
	 * @param estadosList
	 *            lista de estados
	 * @param cedula
	 *            identificador del solicitante
	 * @return una lista de solicitudes {@link SolicitudCredito}
	 * @throws ErrorParametrosException
	 *             encaso de que la lista de estados, y lista de tipos de
	 *             solicitud sean nulas
	 */
	public List<SolicitudCredito> consultarCedulaConEstadosYTipoSolicitud(
			List<Long> tipoSolicitudList, List<String> estadosList,
			String cedula) throws ErrorParametrosException;

	/**
	 * Se encarga de consultar solicitudes de préstamos que cumplan con el tipo
	 * de solicitud y los estados pasados como parámetro y además que el campo
	 * nuttran sea null
	 * 
	 * Retornará un objeto solicitud llenando todos sus hijos
	 * 
	 * @param tipoSolicitudList
	 * @param estadosList
	 * @return
	 * @throws ErrorParametrosException
	 *             en caso de que los parámetros enviado sean null
	 */
	public List<SolicitudCredito> consultarSolicitudeParaGenerarXmlEnvioTata(
			List<Long> tipoSolicitudList, List<String> estadosList)
			throws ErrorParametrosException;

	
	/**
	 * Se encarga de consultar solicitudes de préstamos de afiliados que cumplan con el tipo
	 * de solicitud, los estados pasados y tipo de Afiliado (P, V, J) como parámetro y además que el campo
	 * nuttran sea null
	 * 
	 * Retornará un objeto solicitud llenando todos sus hijos
	 * 
	 * @param tipoSolicitudList
	 * @param estadosList
	 * @param listaPenoAfi   
	 * 				lista de tipo de Afiliado a consultar
	 * @return
	 * @throws ErrorParametrosException
	 *             en caso de que los parámetros enviado sean null
	 */
	public List<SolicitudCredito> consultarSolicitudeParaGenerarXmlEnvioTataPorPenoAfi(
			List<Long> tipoSolicitudList, List<String> estadosList, List<String> listaPenoAfi)
			throws ErrorParametrosException;
	
	/**
	 * Se encarga de recuperar el valor secuencial para la identificación del
	 * envío del XML para TATA
	 * 
	 * @return
	 */
	public BigInteger obtenerSecuencialGeneracionArchivoXmlTata();

	/**
	 * Consulta una solicitud por el nut
	 * 
	 * @param nut
	 * @return La solicitud que tenga ese nut
	 * @throws IllegalArgumentException
	 *             cuando el parámtro es null
	 */
	public SolicitudCredito findByNut(Long nut) throws IllegalArgumentException;

	/**
	 * Consulta las solicitudes dado una lista de nuts
	 * 
	 * @param nuts
	 * @return Una lista de solicitudes
	 * @throws IllegalArgumentException
	 *             cuando la lista es null o no tiene elementos
	 */
	public List<SolicitudCredito> findByNuts(List<BigInteger> nuts)
			throws IllegalArgumentException;

	/**
	 * Consulta las solicitudes que tiene nuttran igual al especificado como
	 * parámetro
	 * 
	 * @param nuttran
	 *            La lista de solicitudes encontradas, puede retornar una lista
	 *            vacia
	 * @return
	 */
	public List<SolicitudCredito> findByNuttran(Long nuttran);

	/**
	 * Consulta las solicitudes que no tienen asignado una agencia.
	 * 
	 * @param tipoSolicitud
	 *            lista de los codigos de tipos de solicitud a consultar
	 * @return lista de Object con NUT, CODTIPSOLSER, NUMSOLSER caso contrario
	 *         una lista vacia3
	 * @throws IllegalArgumentException
	 *             en caso de que los argumentos sean null o vacios
	 */
	public List<BigDecimal> consultarSolicitudesSinAgenciaPorTipoSolicitud(
			List<Long> tipoSolicitud) throws IllegalArgumentException;

	/**
	 * Consulta las solicitudes cuyos solicitantes no tengan asignado el
	 * porcentaje de participacion
	 * 
	 * @param tipoSolicitud
	 *            lista de id de tipos de solicitud a consultar
	 * @return lista de Objetos con NUT, CODTIPSOLSER, NUMSOLSER caso
	 *         contrariouna lista vacia
	 * @throws IllegalArgumentException
	 *             en caso de que los parametros sean null
	 */
	public List<BigDecimal> consultarSolicitudesSinPorcentajeParticipacionPorTipoSolicitud(
			List<Long> tipoSolicitud) throws IllegalArgumentException;

	/**
	 * Consulta todas las solicitudes que esten en los estados dados
	 * 
	 * @param estadosList
	 * @return
	 * @throws ErrorParametrosException
	 */
	public List<SolicitudCredito> consultarSolicitudesPorEstadosConDetalle(
			List<String> estadosList) throws ErrorParametrosException;

	/**
	 * Consulta las solicitudes por un tipo de solicitud, en una lista de
	 * estados especificos, y una lista de procesos especificos
	 * 
	 * @param tipoSolicitud
	 *            lista de tipos de solicitud
	 * @param listaEstado
	 *            lista de estados de la solicitud
	 * @param listaProcesoRealizado
	 *            lista de proesos realizados
	 * @return lista de solicitudes EAGER, caso contrario una lista vacia
	 */
	public List<SolicitudCredito> consultarSolicitudesPorTipoSolicitudPorEstadoPorProcesoRealizdo(
			List<Long> tipoSolicitud, List<String> listaEstado,
			List<Long> listaProcesoRealizado);

	/**
	 * Consulta si la solicitud se encuentra en proceso de pago en un numero de
	 * pago especifico
	 * 
	 * @param nut
	 * @param numeroPago
	 * @return el objeto de modelo {@link SolicitudCredito} caso contrario null
	 */
	public SolicitudCredito consultarSolicitudPagoTramite(Long nut,
			Integer numeroPago);

	/**
	 * Consulta un pago ya fue realizado
	 * 
	 * @param nut
	 * @param numeroPago
	 * @return el objeto de modelo {@link SolicitudCredito} caso contrario null
	 */
	public SolicitudCredito consultarSolicitudPagoTramiteDetallePagos(Long nut,
			Integer numeroPago);

	/**
	 * 
	 * @param cedula
	 * @param codTipSolSerList
	 * @return
	 */
	public String consultarSolicitudGastosAdministrativos(
			String cedula, List<Long> codTipSolSerList);

	/**
	 * 
	 * @param cedula
	 * @param codTipSolSerList
	 * @return
	 */
	public String consultarSolicitudTramite(String cedula,
			List<Long> codTipSolSerList, List<String> codEstSolSerList);

	/**
	 * Obtiene solicitudes basado en su último estado, tipo de solicitud, estado
	 * y proceso realizado
	 * 
	 * @param tipoSolicitud
	 * @param listaEstado
	 * @param listaProcesoRealizado
	 * @return
	 */
	public List<SolicitudCredito> consultarSolicitudesPorTipoSolicitudPorEstadoPorUltimoProcesoRealizdo(
			List<Long> tipoSolicitud, List<String> listaEstado,
			List<Long> listaProcesoRealizado);
			
	/**
	 * Obtiene el listado de solicitudes de hipotecarios de las que se debe
	 * cobrar gastos administrativos
	 * 
	 * @param tiposSolicitud
	 * @param estadosSolicitud
	 * @return
	 */
	List<SolicitudCredito> consultarSolicitudesParaCargos(
			List<Long> tiposSolicitud, List<String> estadosSolicitud);
	
	/**
	 * Consulta el máximo desembolso efectivo
	 * @param nut
	 * @param numeroPago
	 * @return
	 */
	public List<SolicitudCredito> consultarMaximoDesembolso(Long nut, Integer numeroPago);
	
	/**
	 * Método para consultar la solicitud vigente de un PH
	 * 
	 * @param cedula
	 * @param tipoSolicitud
	 * @return
	 */
	public SolicitudCredito obtenerSolicitudVigente(String cedula, List<Long> tipoSolicitud);
	
	/**
	 * Método que obtiene el préstamo vigente de un solicitante individual o
	 * solidario
	 * 
	 * @param cedula
	 * @param tipoSolicitud
	 * @return
	 */
	public SolicitudCredito obtenerSolicitudVigenteIndSol(String cedula, List<Long> tipoSolicitud);
	
	/**
	 * Metodo que obtiene la lista de Solicitudes de Credito que posee el
	 * Solicitante
	 * 
	 * @author geguiguren
	 * 
	 * @param cedula
	 * @param idTipoProducto
	 * @return List<SolicitudCredito> retorna el listado de Solicitudes de Credito
	 */
	public List<SolicitudCredito> encontrarPorCedulaPorTipoProducto(String cedula, Long idTipoProducto);
	
	/**
	 * Metodo que obtiene las solicitudes que tienen liquidaciones aprobadas
	 * @param tipoSolicitud
	 * @return
	 */
	public List<SolicitudCredito> buscarPorLiquidacionAprobada(List<Long> tipoSolicitud);
	
	/**
	 * Consulta el numero de sucursales del afiliado que son contempladas como afectadas
	 * Si esta registrado en una de ellas, para determinar si es damnificado
	 * 
	 * @param identificacion
	 * @param listaCodDivPolitica
	 * @return
	 */
	Long numeroSucursalesAfectadasAfiliado(String identificacion, List<String> listaCodDivPolitica);
	
	/**
	 * Consulta el numero de sucursales del jubilado que son contempladas como afectadas
	 * considerando solo el ultimo trabajo
	 * Si esta registrado en una de ellas, para determinar si es damnificado
	 * @param identificacion
	 * @param listaCodDivPolitica
	 * @return
	 */
	Long numeroSucursalesAfectadasJubilado(String identificacion, List<String> listaCodDivPolitica);
	
	/**
	 * Indica si tiene planillas en mora en el sector publico
	 * 
	 * @param cedula
	 * @return
	 */
	BigDecimal contarPlanillaMoraSectorPublico(String cedula);
	
	/**
	 * Consulta el numero de imposiciones que tiene un solicitante desde un mes y anio hacia atras
	 * 
	 * @param identificacion
	 * @param hastaMes
	 * @param hastaAnio
	 * @return
	 */
	Long numeroImposicionesSolicitante(String identificacion, int hastaMes, int hastaAnio);
	
	public SolicitudCredito traerSolicitudPorTipoPorNumero(Long tipoSolicitud, Long numeroSolicitud);
	
	/**
	 * Permite actualizar el estado de una solicitud credito
	 * @param pk
	 * @param estado
	 */
	void actualizarEstadoSolicitudCredito(SolicitudCreditoPK pk, String estado);
	
	/**
	 * Permite actualizar una solicitud nut y estado
	 * @param pk
	 * @param solicitudCredito
	 */
	void actualizarNutSolicitudCredito(SolicitudCreditoPK pk, Long nut);

}
