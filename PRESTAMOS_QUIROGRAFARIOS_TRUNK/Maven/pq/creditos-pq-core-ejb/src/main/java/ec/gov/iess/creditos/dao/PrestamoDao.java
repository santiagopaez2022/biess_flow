/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.excepcion.PrestamoPqCoreException;
import ec.gov.iess.creditos.modelo.dto.CuentaBancariaAnterior;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCredito;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * <b> Contiene metodos que permite validar requisitos para la pre-calificacion
 * del credito </b>
 * 
 * @author cbastidas,pjarrin
 * @version $Revision: 1.7 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/10/03 $]
 *          </p>
 */
@Local
public interface PrestamoDao extends GenericDao<Prestamo, PrestamoPk> {

	/**
	 * 
	 * <b> Consulta si tiene un dividendos en mora en HL. </b>
	 * <p>
	 * [Author: cvillarreal, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Cedula del solicitante
	 * @return boolean true en caso de que tenga dividendos y false en caso de
	 *         no encontrar dividendos en mora.
	 */
	public boolean tienePrestamoMoraHl(String cedula);

	/**
	 * 
	 * <b> Consulta los dividendos en mora de los prestamos quirografarios
	 * vigentes en HL. </b>
	 * <p>
	 * [Author: cvillarreal, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Cedula del solicitante
	 * @return List<DividendoPrestamo> una lista con los dividendos en mora si
	 *         no tiene dividentos retorna una lista vacia
	 */
	public List<DividendoPrestamo> listaPrestamoMoraHl(String cedula);

	/**
	 * 
	 * <b> Consulta si tiene prestamos vigentes de quirografarios en HL. </b>
	 * <p>
	 * [Author: cvillarreal, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Cedula del solicitante
	 * @param estadoCredito
	 *            :Estado del credito
	 * @return boolean True en caso de que tenga prestamos vigentes y false en
	 *         caso de que no
	 */
	public boolean tienePrestamoVigentesHl(String cedula,
			List<String> estadoCredito);

	/**
	 * 
	 * <b> Consulta la lista de prestamos vigentes HL. </b>
	 * <p>
	 * [Author: cvillarreal, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Cedula del solicitante
	 * @param estadoCredito
	 *            :Estado del credito
	 * @return List<Prestamo> Una lista de clases de modelo {@link Prestamo}
	 *         caso contrario una lista vacia
	 */

	public List<Prestamo> listaPrestamoVigentesHl(String cedula,
			List<String> estadoCredito);

	/**
	 * 
	 * <b> Consulta creditos quirografarios vigentes que no han sido cancelados
	 * ni rechazados </b>
	 * <p>
	 * [Author: cvillarreal, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Identificacion para la consulta de los prestamos
	 * @param estadoCredito
	 *            :Estado del credito
	 * @param tipoCredito
	 *            :Tipo de credito
	 * @return List<Prestamo> Una lista de clases de modelo Prestamo caso
	 *         contrario una lista vacia
	 */
	public List<Prestamo> consultarQuirografariosVigentes(String cedula,
			List<String> estadoCredito, List<Long> tipoCredito);

	/**
	 * 
	 * <b> Consulta creditos hipotecarios vigentes que no han sido cancelados ni
	 * rechazados y tambien verifica a los conyuges en caso de un credito
	 * solidario </b>
	 * <p>
	 * [Author: palvarez, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Cedula del solicitante
	 * @param estadoSolicitud
	 *            :Estado de la solicitud
	 * @return boolean Retorna verdadero o falso
	 */

	public boolean tienePrestamoVigentesHlPH(String cedula,
			List<String> estadoSolicitud);

	/**
	 * 
	 * <b> Verifica si existe un credito con la misma cuenta bancaria. </b>
	 * <p>
	 * [Author: cvillarreal, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param rucInstitucionFinanciera
	 *            :Ruc de la institucion financiera
	 * @param idTipoCuenta
	 *            :Codigo del tipo de cuenta
	 * @param numeroCuenta
	 *            :Numero de la ceunta bancaria
	 * @param estadoCredito
	 *            :Estado del credito quirografario
	 * @return boolean True en caso de que exista un credito vigente caso
	 *         contrario false
	 */
	public boolean existePrestamoConLaMismaCuenta(
			String rucInstitucionFinanciera, TipoCuenta idTipoCuenta,
			String numeroCuenta, List<String> estadoCredito);

	/**
	 * 
	 * <b> Consultar lista de cuotas de dividendo por cedula. </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/06/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            : numero de cedula
	 * @return List<Prestamo> Lista de prestamos
	 */
	public List<Prestamo> listaPrestamoCuotaDividendo(String cedula);

	/**
	 * 
	 * <b> Consulta la lista de prestamos que tengan la misma cuenta bancaria.
	 * </b>
	 * <p>
	 * [Author: cvillarreal, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param rucInstitucionFinanciera
	 *            :Ruc de la institucion financiera
	 * @param idTipoCuenta
	 *            :Codigo del tipo de cuenta
	 * @param numeroCuenta
	 *            :Numero de la ceunta bancaria
	 * @param estadoCredito
	 *            :Estado del credito
	 * @return List<Prestamo> Una lista de clases de modelo Prestamo caso
	 *         contrario una lista vacia
	 */
	public List<Prestamo> consultarPrestamosConLaMismaCuenta(
			String rucInstitucionFinanciera, TipoCuenta idTipoCuenta,
			String numeroCuenta, List<String> estadoCredito);

	/**
	 * 
	 * <b> Refrescar datos del credito </b>
	 * <p>
	 * [Author: cbastidas, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param prestamo
	 *            :Un objeto prestamo
	 */
	public void refrescar(Prestamo prestamo);

	/**
	 * 
	 * <b> Consulta todos los prestamos para una cedula dada sin importar su
	 * estado </b>
	 * <p>
	 * [Author: cbastidas, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Cedula del solicitante
	 * @return List<Prestamo> Retorna lista de prestamos
	 */
	public List<Prestamo> getPrestamosPorCedula(String cedula);

	/**
	 * 
	 * <b> Consulta total de un credito vigente. </b>
	 * <p>
	 * [Author: cbastidas, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Cedula del solicitante
	 * @param estadoPrestamo
	 *            :Estado del credito
	 * @return BigDecimal Total del credito vigente
	 */
	public BigDecimal getTotalCreditoVigente(String cedula,
			List<String> estadoPrestamo);

	/**
	 * 
	 * <b> Saldo del credito vigente </b>
	 * <p>
	 * [Author: cbastidas, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Cedula del solicitante
	 * @param estadoPrestamo
	 *            :Estadfo del credito
	 * @param estadoDividendo
	 *            :Estado del dividendo
	 * @return BigDecimal Saldo del credito vigente
	 */
	public BigDecimal getTotalSaldoCreditoVigente(String cedula,
			List<String> estadoPrestamo, List<String> estadoDividendo);

	/**
	 * 
	 * <b> Consulta creditos quirografarios vigentes bajo los parametros de
	 * validacion en precalificacion de PH </b>
	 * <p>
	 * [Author: cbastidas, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Cedula del solicitante
	 * @param estadoCredito
	 *            :Estado del credito
	 * @param tipoCredito
	 *            :Tipo de credito
	 * @return List<Prestamo> Retorna lista de credito vigentes
	 */

	List<Prestamo> consultarQuirografariosVigentesPH(String cedula,
			List<String> estadoCredito, List<Long> tipoCredito);

	/**
	 * 
	 * <b> Retorna la cuota de prestamo hipotecario. </b>
	 * <p>
	 * [Author: cbastidas, Date: 11/12/2010]
	 * </p>
	 * 
	 * @param cedula
	 * @return BigDecimal Monto de la cuota de credito PH
	 */
	public BigDecimal cuotaPrestamoHipotecario(String cedula);

	/**
	 * 
	 * <b> Retorna la cuota de Hipotecario cuando esta en tramite de solicitud.
	 * </b>
	 * <p>
	 * [Author: cbastidas, Date: 14/12/2010]
	 * </p>
	 * 
	 * @param cedula
	 *            :Numero de cedula del solicitante
	 * @return BigDecimal Monto del credito de solicitud de PH
	 */
	public BigDecimal cuotaPrestamoSolicitudHipotecario(String cedula);

	/**
	 * 
	 * <b> Calcula los montos maximos del credito con respecto al tipo y clase
	 * </b>
	 * <p>
	 * [Author: cbastidas, Date: 14/03/2011]
	 * </p>
	 * 
	 * @param tipoPrestamo
	 *            :Tipo de prestamo quirografario
	 * @param clasePrestamo
	 *            :Clase de prestamo afiliado,jubilado
	 * @return BigDecimal Retorna el monto maximo del credito
	 */
	public BigDecimal montoMaximoCredito(int tipoPrestamo, int clasePrestamo);

	/**
	 * 
	 * <b> Busca por clave primaria del credito. </b>
	 * <p>
	 * [Author: cbastidas, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param prestamoPk
	 * @return Prestamo Retorna el objeto prestamo
	 */
	public Prestamo buscarPorPk(PrestamoPk prestamoPk);

	/**
	 * 
	 * <b> Verifica si se tiene concedido otro credito quirografario </b>
	 * <p>
	 * [Author: cbastidas, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :Numero de cedula del solicitante
	 * @param estadoCredito
	 *            :Estado del credito
	 * @param tipoCredito
	 *            :Tipo de credito afiliado o jubilado
	 * @return boolean
	 */
	public boolean tienePrestamoQuirografarioVigentesPH(String cedula,
			List<String> estadoCredito, List<Long> tipoCredito);

	/**
	 * 
	 * <b> Consulta numero de Pqs por estados </b>
	 * <p>
	 * [Author: cbastidas, Date: 10/06/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            : Cedula del solicitante
	 * @param estadoCredito
	 *            : Lista de estados del credito
	 * @return BigDecimal Numero de Pqs por estado
	 */
	public BigDecimal consultarTienePqPorEstado(String cedula,
			List<String> estadoCredito);

	/**
	 * 
	 * <b> Verifica la existencia de prestamos que tengan la misma cuenta
	 * bancaria y cedula del afiliado </b>
	 * <p>
	 * [Author: cbastidas, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param rucInstitucionFinanciera
	 *            :Ruc de la institucion financiera en la que se deposita el
	 *            dinero
	 * @param idTipoCuenta
	 *            :Tipo de cuenta bancaria ahorros o corriente
	 * @param numeroCuenta
	 *            :Numero de cuenta bancaria
	 * @param estadoCredito
	 *            :Estado del credito
	 * @param cedula
	 *            :Cedula del solicitante
	 * @return boolean Retorna verdadero o falso
	 */
	public boolean existePrestamoConLaMismaCuentaYCedula(
			String rucInstitucionFinanciera, TipoCuenta idTipoCuenta,
			String numeroCuenta, List<String> estadoCredito, String cedula);

	/**
	 * 
	 * <b> Actualiza la fecha de cancelacion del prestamo y el estado ha
	 * cancelado. </b>
	 * <p>
	 * [Author: cbastidas, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param prestamo
	 *            :Objeto prestamo datos de la cabecera del credito
	 * @return boolean Retorna vwerdadero o falso
	 */
	public boolean actualizarFecCancelacionYEstado(Prestamo prestamo);

	/**
	 * 
	 * <b> Permite realizar la consulta del detalle del credito. </b>
	 * <p>
	 * [Author: rtituana, Date: 28/03/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            :numero de cedula del afiliado
	 * @param numeroPrestamo
	 *            :numero de prestamo
	 * @param codPreTip
	 *            :tipo de prestamo
	 * @param ordPreAfi
	 *            :secuencia prestamo
	 * @param codPreCla
	 *            :clase de prestamista
	 * @return DetalleCredito Objeto detalle crédito
	 */
	public DetalleCredito consultarDetalleCredito(String cedula,
			Long numeroPrestamo, Long codPreTip, Long ordPreAfi, Long codPreCla);

	/**
	 * <b> Consulta la lista de prestamos en estados pendiente de aprobacion.
	 * </b>
	 * <p>
	 * [Author: acantos, Date: 10/08/2011]
	 * </p>
	 * 
	 * @return List<prestamos> : Lista de prestamos que se encuentran en estado
	 *         pendiente de aprobacion
	 */
	public List<Prestamo> consultarprestamospendientesaprobacion(
			Date fecha_ant, Date fecha_post);

	/**
	 * Consulta la lista de prestamos PDA por cedula y fechas
	 * 
	 * @param fecha_ant
	 * @param fecha_post
	 * @param cedula
	 * @return
	 */
	List<Prestamo> consultarPrestamoPDAPorCedula(String cedula);

	/**
	 * <b> Verifica si existe una cuentabancaria valida en la lista blanca. </b>
	 * <p>
	 * [Author: acantos, Date: 10/08/2011]
	 * </p>
	 * 
	 * @return boolean : Indica si existe o no
	 */
	public boolean existecuentaenlistablanca(String numafi,
			String rucfinanciera, String tipocta, String numerocta);

	/**
	 * 
	 * <b> Retorna total de creditos hipotecarios que registre el asegurado
	 * vigentes. </b>
	 * <p>
	 * [Author: cbastidas, Date: 11/12/2010]
	 * </p>
	 * 
	 * @param cedula
	 * @return
	 */
	public BigDecimal numeroPrestamoPHVIG(String cedula);

	/**
	 * 
	 * <b> Retorna el valor(Monto financiado)de los creditos hipotecarios que
	 * registre el asegurado.. </b>
	 * <p>
	 * [Author: cbastidas, Date: 11/12/2010]
	 * </p>
	 * 
	 * @param cedula
	 * @return
	 */
	public BigDecimal sumatoriaMntPHVIG(String cedula);

	/**
	 * 
	 * <b> Retorna la lista de prestramos que han sido referenciados en este
	 * query generico
	 * <p>
	 * [Author: Ricardo T, Date: 11/12/2010]
	 * </p>
	 * 
	 * @param queryGenericoPQ
	 *            query generico que entra como parametro
	 * @return List<Prestamo> lista de prestamos
	 */
	public List<Prestamo> consultaGenerica(String queryGenericoPQ);

	/**
	 * Funcion que indica si tiene o no creditos en estado ERC
	 * 
	 * @param cedula
	 * @return
	 */
	List<Prestamo> poseeCreditosERC(String cedula);

	/**
	 * Consulta los prestamos en estado ERC por parametros
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param cedula
	 * @param estado
	 * @return
	 */
	List<Prestamo> obtenerCreditoPorParametros(Date fechaDesde,
			Date fechaHasta, String cedula, String estado, String estadoVig,
			String registroCivil);

	/**
	 * Consulta la suma de saldos de prestamos vigentes
	 * 
	 * @param cedula
	 * @return
	 */
	BigDecimal sumaSaldosPrestamosVigentes(String cedula);

	/**
	 * Datos de la cuenta bancaria anterior
	 * 
	 * @param cedula
	 * @return
	 */
	CuentaBancariaAnterior cuentaBancariaAnterior(String cedula);
	
	/**
	 * Metodo que actualiza el estado y fecha de un prestamo
	 * 
	 * @param prestamo
	 * @param codigoEstadoPrestamo
	 * @param fechaCancelacion
	 * @return
	 * @throws Exception
	 */
	boolean actualizarFechaCancelacionYEstado(Prestamo prestamo,
			String codigoEstadoPrestamo, Date fechaCancelacion) throws Exception;
	
	/**
	 * Obtiene prestamos por estado y periodo.
	 * 
	 * @param estadosPrestamo
	 *            - estos del prestamo.
	 * @param fechaAnt
	 *            - fecha anterior.
	 * @param fechaPost
	 *            - fecha porterior.
	 * 
	 * @return List<Prestamo>.
	 */
	public List<Prestamo> consultarPorEstadoYPeriodo(
			List<String> estadosPrestamo, Date fechaAnt, Date fechaPost);

	/**
	 * Obtiene prestamos por estado, periodo y cedula.
	 * 
	 * @param estadosPrestamo
	 *            - estos del prestamo.
	 * @param fechaAnt
	 *            - fecha anterior.
	 * @param fechaPost
	 *            - fecha porterior.
	 * @param cedulaAfiliado
	 *            - cedula afiliado.
	 * 
	 * @return List<Prestamo>.
	 */
	public List<Prestamo> consultarPorEstadoPeriodoYCedula(
			List<String> estadosPrestamo, Date fechaAnt, Date fechaPost,
			String cedulaAfiliado);

	/**
	 * Obtiene prestamos por estado, periodo, cedula y visa.
	 * 
	 * @param estadosPrestamo
	 *            - estos del prestamo.
	 * @param fechaAnt
	 *            - fecha anterior.
	 * @param fechaPost
	 *            - fecha porterior.
	 * @param tipoProducto
	 *            - tipo de producto.
	 * 
	 * @return List<Prestamo>.
	 */
	public List<Prestamo> consultarPorEstadoPeriodoYTipoProducto(
			List<String> estadosPrestamo, Date fechaAnt, Date fechaPost,
			Long tipoProducto);

	/**
	 * Obtiene prestamos por estado, periodo, cedula y visa.
	 * 
	 * @param estadosPrestamo
	 *            - estos del prestamo.
	 * @param fechaAnt
	 *            - fecha anterior.
	 * @param fechaPost
	 *            - fecha porterior.
	 * @param cedulaAfiliado
	 *            - cedula del afiliado.
	 * @param tipoProducto
	 *            - tipo de producto.
	 * 
	 * @return List<Prestamo>.
	 */
	public List<Prestamo> consultarPorEstadoPeriodoCedulaYTipoProducto(
			List<String> estadosPrestamo, Date fechaAnt, Date fechaPost,
			String cedulaAfiliado, Long tipoProducto);
	
	/**
	 * Obtiene prestamos por estado, periodo y visa.
	 * 
	 * @param estadosPrestamo
	 *            - estado del prestamo.
	 * @param fechaAnt
	 *            - fecha anterior.
	 * @param fechaPost
	 *            - fecha posterior
	 * @param cedulaAfiliado
	 *            - cedula afiliado.
	 * @param tipoProducto
	 *            - tipo de producto.
	 * @param visaAfiliado
	 *            - visa o pasaporte del afiliado
	 * 
	 * @return List<Prestamo>.
	 */
	public List<Prestamo> consultarPrestamos(List<String> estadosPrestamo, Date fechaAnt, Date fechaPost,
			String cedulaAfiliado, Long tipoProducto, String visaAfiliado);
	
	/**
	 * Devuelve el número de préstamo que tiene una persona en un año especificado y con un estado especificado
	 * 
	 * @param numafi
	 * @param estadoPrestamo
	 * @param aniper
	 * @return
	 */
	Long contarPorEstadoAnio(String numafi, String estadoPrestamo, Long aniper);
	
	/**
	 * Cuenta cuántos prestamos tiene el afiliado por cedula, estado y tipo de credito
	 * 
	 * @param numafi
	 * @param estadoPrestamo
	 * @param codpretip
	 * @return
	 */
	Long contarPorEstadoTipoPrestamo(String numafi, String estadoPrestamo, Long codpretip);

	/**
	 * Devuelve una lista de prestamos dado el numero de cedula del afiliado, el estado del prestamo y la fecha de
	 * precalificacion
	 * 
	 * @param numafi
	 * @param codestpre
	 * @param fecpreafi
	 * @return
	 */
	List<Prestamo> listaPorEstadoFechaPrecalifica(String numafi, String codestpre, Date fecpreafi);
	
	/**
	 * Permite conocer si el patrono cancelo sus planillas despues del feccanpla siendo estas planillas creadas
	 * anteriores al periodo feccrepla
	 * 
	 * @param fecrecpla
	 * @param feccanpla
	 * @param codtippla
	 * @param rucemp
	 * @param codsuc
	 * @return
	 */
	BigDecimal contarPorFechaCancelacion(Date fecrecpla, Date feccanpla, String codtippla, String rucemp, String codsuc);
	
	/**
	 * Cuenta las planillas en mora que tiene el empleador con el empleado cuya cedula es enviada. Se usa para validar
	 * el requisito NO TENER CREDITO EN MORA CON EL IESS O BIESS
	 * 
	 * @param fechaCreacionPlanilla
	 * @param cedula
	 * @param estadosCodTippla
	 * @return
	 */
	BigDecimal contarPlanillasMoraInvididualBiessEmergente(Date fechaCreacionPlanilla, String cedula);
	
	/**
	 * Cuenta las planillas en mora para validar el requisito SU(S) EMPLEADOR(RES) NO DEBE(N) ESTAR EN MORA CON EL IESS
	 * para prestamos emergentes
	 * 
	 * @param fechaCreacionPlanilla
	 * @param cedula
	 * @return
	 */
	BigDecimal contarPlanillasMoraPatronalEmergente(Date fechaCreacionPlanilla, String cedula);
	
	/**
	 * Realiza cambio de estad
	 * 
	 * @param parametrosCredito
	 * @param estadoCredito
	 * @param estadoActualCredito
	 * @throws PrestamoPqCoreException
	 */
	void ejecutarCambioEstadoSP(Map<String, Long> parametrosCredito, String estadoCredito, String estadoActualCredito)
			throws PrestamoPqCoreException;
	
	/**
	 * Actualiza el estado dek credito
	 * 
	 * @param parametrosCredito
	 * @param estadoCredito
	 * @throws PrestamoPqCoreException
	 */
	void cambiarEstadoCredito(Map<String, Long> parametrosCredito, String estadoCredito) throws PrestamoPqCoreException;
	
	/**
	 * Devuelve el número de prestamo que tiene una persona en un año especificado, con un estado especificado a partir
	 * de una fecha indicada
	 * 
	 * @param numafi
	 * @param estadoPrestamo
	 * @param aniper
	 * @param feccanpre
	 * @return
	 */
	Long contarPorEstadoFeccanpreAnio(String numafi, String estadoPrestamo, Long aniper, Date feccanpre);
	
	/**
	 * @param estadosPrestamo
	 * @param fechaAnt
	 * @param fechaPost
	 * @param idTipoProducto
	 * @param cedulasPrestamo
	 * @return
	 */
	public List<Prestamo> consultarPrestamosPorEstadosCedulas(List<String> estadosPrestamo, Date fechaAnt, Date fechaPost,
			Long idTipoProducto, List<String> cedulasPrestamo);



	/**
	 * Permite consultar el prestamo por numero de solicitud
	 * @param codTipoSolicitud
	 * @param numSolicitud
	 * @return
	 */
	public Prestamo buscarPorSolicitud(Long codTipoSolicitud, Long numSolicitud);
	
	/**
	 * Permite consultar todos los prestamos cancelados de la tabla de creditos obtestpre sea 1 que son los cancelados.
	 * @param cedula
	 * @return
	 */
	public List<Prestamo> listarPrestamosCancelados(String cedula);
	
	/**
	 * Consulta si tiene en tramite un credito cuando el credito esta en VIG y la solicitud no este en VIG
	 * @param cedula
	 * @return
	 */
	public BigDecimal consultarTienePqSolCreTramite(String cedula);
	
	/**
	 * Consultar un prestamo por un numero de operacion SAC
	 * @param numOperacionSAC
	 * @return
	 */
	public Prestamo buscarPorNumOperacionSAC(Long numOperacionSAC);
	
	/**
	 * Valida si existe una solicitud de credito en novacion con el numero cancelado de novacion
	 * @param numCanceladoNovacion =codpretip+ordpreafi+codprecla+numpreafi
	 * @param identificacion cedula o numero extranjero
	 * @return
	 */
	boolean tieneSolicitudNovacionTramite(Long numCanceladoNovacion,String identificacion);

	
	/**
	 * Obtiene el numero de creditos en conciliacion
	 * @param cedula
	 * @return
	 */
	BigDecimal obtenerCreditoConciliacion(final String cedula);

}
