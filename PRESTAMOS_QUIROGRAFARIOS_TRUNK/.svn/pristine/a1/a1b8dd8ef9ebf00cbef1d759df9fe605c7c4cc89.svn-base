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
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.iess.creditos.enumeracion.RolSolicitante;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.ErrorParametrosException;
import ec.gov.iess.creditos.excepcion.GenerarSecuenciaException;
import ec.gov.iess.creditos.excepcion.MasDeUnPermisoMismoEstadoException;
import ec.gov.iess.creditos.modelo.persistencia.Agencia;
import ec.gov.iess.creditos.modelo.persistencia.DepositoSolicitud;
import ec.gov.iess.creditos.modelo.persistencia.DocumentacionAvaluo;
import ec.gov.iess.creditos.modelo.persistencia.DocumentacionRequerida;
import ec.gov.iess.creditos.modelo.persistencia.PermisoProceso;
import ec.gov.iess.creditos.modelo.persistencia.ProcesoRealizado;
import ec.gov.iess.creditos.modelo.persistencia.SolicitanteCredito;
import ec.gov.iess.creditos.modelo.persistencia.SolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.TipoRolSolicitante;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitante;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK;
import ec.gov.iess.creditos.pq.excepcion.ConsultaAgenciaException;
import ec.gov.iess.creditos.pq.excepcion.CrearSolicitudCreditoException;
import ec.gov.iess.creditos.pq.excepcion.MasDeUnRegistroException;
import ec.gov.iess.creditos.pq.excepcion.MasDeUnSolicitanteException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteTipoRolSolicitanteException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteTipoSolicitanteException;
import ec.gov.iess.creditos.pq.excepcion.PagoAnteriorNoRealizadoException;
import ec.gov.iess.creditos.pq.excepcion.PagoEnProcesoException;
import ec.gov.iess.creditos.pq.excepcion.PagoRealizadoException;
import ec.gov.iess.creditos.pq.excepcion.SolicitanteExcepcion;
import ec.gov.iess.creditos.pq.excepcion.SolicitudException;
import ec.gov.iess.creditos.pq.excepcion.TransaccionErrorBDDException;

/**
 * @author cvillarreal
 * 
 */
@Remote
public interface SolicitudCreditoServicioRemote {

	/**
	 * Crea una solicitud para creditos quirografarios
	 * 
	 * @param secuencialSolicitud
	 *            secuencial de la solicitud
	 * @param idTipoSolicitud
	 *            identificador del tipo de solicitud
	 * @param fechaSolicitud
	 *            fecha de creacion de la solicitud
	 * @param cedulaAfiliado
	 *            identificacion del solicitante
	 * @param tipoSolicitante
	 *            tipo de solicitante A si es afiliado y P si es pensionista
	 * @return un objeto de modelo creado {@link SolicitudCredito}
	 * @author cvillarreal
	 */
	public SolicitudCredito crearSolicitudCreditoPq(long secuencialSolicitud, long idTipoSolicitud,
			Date fechaSolicitud, String cedulaAfiliado, String tipoSolicitante);

	/**
	 * Consulta las solicitudes que estan diferente de cancelado y vigentes por
	 * tipo de solicitud la consulata es por solicitante
	 * 
	 * @param cedula
	 * @param tipoSolicitud
	 * @return
	 */
	public SolicitudCredito getSolicitudCreditoSolicitante(String cedula, int tipoSolicitud);

	/**
	 * Consulta las solicitudes que estan diferente de cancelado y vigentes por
	 * tipo de solicitud la consulata es por conyugue
	 * 
	 * @param cedula
	 * @param tipoSolicitud
	 * @return
	 */
	public SolicitudCredito getSolicitudCreditoConyugue(String cedula, int tipoSolicitud);

	/**
	 * 
	 * Registra la solicitud de credito.
	 * 
	 * @param solicitudCredito
	 * @return
	 * @throws CrearSolicitudCreditoException
	 * @throws TransaccionErrorBDDException
	 *             en caso de error de transaccion
	 */
	public SolicitudCredito crearSolicitud(SolicitudCredito solicitudCredito) throws CrearSolicitudCreditoException,
			TransaccionErrorBDDException;

	/**
	 * Consulta el tipo de solicitante a partir del identificador SOLICITANTE,
	 * CONYUGUE
	 * 
	 * @param tipoSolicitanteId
	 * @return
	 * @throws NoExisteTipoRolSolicitanteException
	 */
	public TipoSolicitante getTipoSolicitante(Long tipoSolicitanteId) throws NoExisteTipoSolicitanteException;

	/**
	 * Consulta el tipo rol del solicitante, AFILIADO, JUBILADO, AFILIADO
	 * JUBILADO
	 * 
	 * @param tipoRolSolicitanteId
	 * @return
	 * @throws NoExisteTipoRolSolicitanteException
	 */
	public TipoRolSolicitante getTipoRolSolicitante(Long tipoRolSolicitanteId)
			throws NoExisteTipoRolSolicitanteException;

	/**
	 * Determina el secuencial e la solicitud de credito
	 * 
	 * 
	 * @param idTipoCredito
	 * @param idVariedadCredito
	 * @return
	 * @throws GenerarSecuenciaException
	 */
	public TipoSolicitudCredito getSecuenciaSolicitud(int idTipoCredito, int idVariedadCredito)
			throws GenerarSecuenciaException;

	/**
	 * Consulta la solicitud incluyendo sus relaciones
	 * 
	 * @param estados
	 * @param cedula
	 * @param tipoSolicitud
	 * @return un objeto solicitud caso contarrio null
	 */
	public SolicitudCredito consultarSolicitudCreditoEager(List<String> estados, String cedula, List<Long> tipoSolicitud);

	/**
	 * Consulta la solicitud con todas las relaciones, detalle solicitud,
	 * solicitantes, ingreso egreso solicitantes, precondiciones a partir de la
	 * clave principal
	 * 
	 * @param numeroSolictud
	 * @param tipoSolicitud
	 * @return un objeto de tipo solictud caso contrario null
	 */
	public SolicitudCredito consultarSolicitudCreditoPkEager(Long numeroSolictud, Long tipoSolicitud);

	/**
	 * Consulta las solicitudes de cerdito a partir de un estado, cedula,
	 * tipoSolicitud
	 * 
	 * @param estados
	 * @param cedula
	 * @param tipoSolicitud
	 * @return
	 */
	public List<SolicitudCredito> consultarSolicitudCredito(List<String> estados, String cedula,
			List<Long> tipoSolicitud);

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
	public List<SolicitudCredito> consultarCedulaSinEstadosYTipoSolicitud(List<Long> tipoSolicitudList,
			List<String> estadosList, String cedula) throws ErrorParametrosException;

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
	public List<SolicitudCredito> consultarCedulaConEstadosYTipoSolicitud(List<Long> tipoSolicitudList,
			List<String> estadosList, String cedula) throws ErrorParametrosException;

	/**
	 * Consulta los permisos para un estado
	 * 
	 * @param tipoSolicitud
	 *            tipo de solicitud
	 * @param idEstado
	 *            identificador del estado que se encuentra
	 * @param rol
	 *            id del rol del solicitante
	 * @param idAccion
	 *            id de la accion a realizar
	 * @return retorna un permiso
	 * @throws MasDeUnPermisoMismoEstadoException
	 *             en caso de encontrar mas de una accion para el mismo estado
	 */
	public PermisoProceso permisoAccion(Long tipoSolicitud, Long idEstado, RolSolicitante rol, Long idAccion)
			throws MasDeUnPermisoMismoEstadoException;

	/**
	 * Crea un nuevo proceso realizado
	 * 
	 * @param procesoRealizado
	 */
	public void insertarNuevoProcesoRealizado(ProcesoRealizado procesoRealizado);

	/**
	 * Consulta los procesos realizados a una solicitud
	 * 
	 * @param solicitudCreditoPK
	 *            clave primaria de la solicitud
	 * @return una lista de procesos realizados
	 */
	public List<ProcesoRealizado> getProcesoRealizadoSolicitud(SolicitudCreditoPK solicitudCreditoPK);

	/**
	 * Consulta la documentacion de una solciitud por provicia y tipo de
	 * solicitud
	 * 
	 * @param idProvincia
	 * @param idTipoSolciitud
	 * @param vendedorTipoPersona
	 * @param tipoBien
	 * @return
	 */
	public List<DocumentacionRequerida> getDocumentacion(String idProvincia, Long idTipoSolciitud,
			String vendedorTipoPersona, String tipoBien);

	/**
	 * Consulta el solicitante por un tipo de solicitud y estados que no sean
	 * los eastdos vigentes de la solicitud
	 * 
	 * @param cedula
	 *            identificacion a buscar
	 * @param tipoSolicitud
	 *            tipo de la solcitud
	 * @param estadosSolicitudNoVigente
	 *            lista de estados que nos los estados que determinan que una
	 *            solicitud sea vigente
	 * @return un objeto persistente {@link SolicitanteCredito} caso contrario
	 *         null
	 * @author cvillarreal
	 */
	public SolicitanteCredito getSolicitanteSolicitudVigente(String cedula, Long idTipoSolcitud,
			List<String> estadosSolicitudNoVigente) throws MasDeUnSolicitanteException, SolicitanteExcepcion;

	/**
	 * Se encarga de consultar solicitudes de crédito para la generación del XML
	 * de envio a Tata.
	 * 
	 * @param tipoSolicitudList
	 * @param estadosLis
	 * @return
	 * @throws ErrorParametrosException
	 * @throws SolicitudException
	 */
	public List<SolicitudCredito> consultarSolicitudesParaGenerarXmlEnvioTata(List<Long> tipoSolicitudList,
			List<String> estadosLis) throws ErrorParametrosException, SolicitudException;

	/**
	 * Se encarga de recuperar el valor secuencial para la identificación del
	 * envío del XML para TATA
	 * 
	 * @return
	 */
	public BigInteger obtenerSecuencialGeneracionArchivoXmlTata();

	/**
	 * Consulta una solicitud por nuttran
	 * 
	 * @param nut
	 * @return la solicitud encontrada
	 */
	public SolicitudCredito consultarSolicitudByNut(Long nut);

	/**
	 * Consulta las solictudes que tengan el nuttran igual al nuttran pasado
	 * como parámetro
	 * 
	 * @param nuttran
	 * @return La lista de solicitudes encontradas
	 */
	public List<SolicitudCredito> consultarSolicitudesByNuttran(Long nuttran);

	/**
	 * Actualiza los campos de la solicitud "actualizables" en intranet
	 * 
	 * @param solicitudCredito
	 */
	public void actualizarDesdeIntranet(SolicitudCredito solicitudCredito);

	/**
	 * Consultar agencia a partir de la provincia
	 * 
	 * @param idProvincia
	 *            identofocador de la provincia
	 * @return un objeto de modelo {@link Agencia} caso contrario null
	 * @throws MasDeUnRegistroException
	 *             en caso de que exista mas de una agencia para la misma
	 *             provincia
	 * @throws ConsultaAgenciaException
	 *             en caso de error runtime
	 */
	public Agencia consultarAgenciaPorProvincia(String idProvincia) throws MasDeUnRegistroException,
			ConsultaAgenciaException;

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
	public List<BigDecimal> consultarSolicitudesSinAgenciaPorTipoSolicitud(List<Long> tipoSolicitud)
			throws SolicitudException, IllegalArgumentException;

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
	public List<BigDecimal> consultarSolicitudesSinPorcentajeParticipacionPorTipoSolicitud(List<Long> tipoSolicitud)
			throws SolicitudException, IllegalArgumentException;

	/**
	 * Consulta todas las solicitudes que esten en los estados dados
	 * 
	 * @param estadosLis
	 * @return
	 * @throws ErrorParametrosException
	 */
	public List<SolicitudCredito> consultarSolicitudesPorEstadosConDetalle(List<String> estadosLis)
			throws ErrorParametrosException;

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
			List<Long> tipoSolicitud, List<String> listaEstado, List<Long> listaProcesoRealizado)
			throws IllegalArgumentException, SolicitudException;

	/**
	 * Consulta las cédulas que tienen mas de una solicitud que cumpla con la
	 * siguiente consulta
	 * <p>
	 * 
	 * select numafi from kscretsolicitudes where codtipsolser
	 * in(:tiposSolicitud) and codestsolser =:estado and nuttran is null group
	 * by numafi having count(*) >= :count
	 * 
	 * @param tiposSolicitud
	 *            es una lista del objetos tipo Long que representan los tipos
	 *            de solicitud que nos interesan
	 * @param estado
	 *            es el estado en el que tiene que estar la solicitud
	 * @param count
	 *            es el número mínimo de coincidencias que se deben encontrar
	 *            para cumplir la condición
	 * @return
	 */
	public List<String> consultarCedulasConSolicitudDuplicada(List<Long> tiposSolicitud, String estado, Integer count);

	/**
	 * 
	 * @param nut
	 * @param numeroPago
	 * @throws PagoRealizadoException
	 * @throws PagoEnProcesoException
	 */
	public void controlarPagosRealizadosNut(Long nut, Integer numeroPago, Integer numeroTotalDesembolso)
			throws PagoRealizadoException, PagoEnProcesoException, IllegalArgumentException,
			PagoAnteriorNoRealizadoException;

	/**
	 * 
	 * @param depositoSolicitud
	 */
	public void registrarDepositoSolicitud(DepositoSolicitud depositoSolicitud);

	/**
	 * Obtiene el listado de solicitudes para las que se pueden generar cargos
	 * por gastos administrativos
	 * 
	 * @return
	 */
	List<SolicitudCredito> consultarSolicitudesParaCargosHipotecarios(List<Long> tiposSolicitudes, List<String> estados);

	/**
	 * Método para obtener la documentación para avaluos de acuerdo al codigo de
	 * tipo solicitud
	 * 
	 * @param codigo
	 * @return Lista de documentacion
	 */
	public List<DocumentacionAvaluo> getDocumentacionAvaluo(Long codigoTipoSolSer);

	/**
	 * Método para obtener la documentación para avalúos por tipo de solicitud y
	 * de producto
	 * 
	 * @param código
	 *            del tipo de solicitud
	 * @param código
	 *            del tipo de producto
	 * @return lista de documentación
	 */
	public List<DocumentacionAvaluo> getDocumentacionAvaluoPorTipoProd(Long codigoTipoSolSer, String codigoTipoProd);

	/**
	 * Método para obtener la solicitud vigente
	 * 
	 * @param cedula
	 * @param tipoSolicitud
	 * @return
	 */
	public SolicitudCredito obtenerSolicitudVigente(String cedula, List<Long> tipoSolicitud);
	
	/**
	 * Método para obtener la solicitud vigente de un sol individual o
	 * solidario.
	 * 
	 * @param cedula
	 * @param tipoSolicitud
	 * @return
	 */
	public SolicitudCredito obtenerSolicitudVigenteIndSol(String cedula, List<Long> tipoSolicitud);
	
	/**
	 * Validacion para validar que un solicitante es damnificado
	 * 
	 * @param identificacion
	 * @return
	 * @throws ParametroBiessException
	 */
	boolean esDamnificado(String identificacion, TipoPrestamista tipoPrestamista) throws ParametroBiessException;
	
	/**
	 * Indica si tiene planillas en mora en el sector publico
	 * 
	 * @param cedula
	 * @return
	 */
	BigDecimal contarPlanillaMoraSectorPublico(String cedula);
	
	/**
	 * Verifica si la persona es beneficiaria de mora a ampresas publicas
	 * 
	 * @param cedula
	 * @return
	 */
	boolean esBeneficiarioEmpresaPublica(String cedula);
	
	/**
	 * Consulta el numero de imposiciones que tiene un solicitante desde un mes y anio hacia atras
	 * 
	 * @param identificacion
	 * @param hastaMes
	 * @param hastaAnio
	 * @return
	 */
	Long numeroImposicionesSolicitante(String identificacion, int hastaMes, int hastaAnio);
	
}