/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiess;
import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.enumeracion.SituacionPrestamo;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.ListaVaciaException;
import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.excepcion.PrestamoPqCoreException;
import ec.gov.iess.creditos.modelo.dto.CuentaBancariaAnterior;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DatosSituacionPrestamo;
import ec.gov.iess.creditos.modelo.dto.SolicitudPda;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosPrecalificacion;
import ec.gov.iess.creditos.modelo.persistencia.CreditoConsolidado;
import ec.gov.iess.creditos.modelo.persistencia.CreditoQuirografarioHost;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCredito;
import ec.gov.iess.creditos.modelo.persistencia.HistoricoAniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoInformacion;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoInformacionDetalle;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.dto.EvaluaReglaMontoMinimoDto;
import ec.gov.iess.creditos.pq.excepcion.ActualizarPrestamoEstadoHistoricoException;
import ec.gov.iess.creditos.pq.excepcion.BloqueoFinSemanaException;
import ec.gov.iess.creditos.pq.excepcion.CabeceraCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.CrearCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.DiasNoPermitidosNovacionException;
import ec.gov.iess.creditos.pq.excepcion.MontoMinimoException;
import ec.gov.iess.creditos.pq.excepcion.NovarCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.PrestamoException;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaBloqueadaException;
import ec.gov.iess.creditos.pq.excepcion.SituacionPrestamoNoExisteException;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancariaAfiliado;
import ec.gov.iess.hl.exception.DivisionPoliticaException;
import ec.gov.iess.hl.exception.SucursalException;
import ec.gov.iess.hl.modelo.DivisionPolitica;
import ec.gov.iess.hl.modelo.Sucursal;

/**
 * Implementacion Ejb para la Prestamo Quirografario Servicio
 * 
 * @version 1.0
 * @author palvarez, cbastidas,pjarrin
 */
@Local
public interface PrestamoServicio {

	/**
	 * 
	 * crea todo el credito quirografario AL FIN!
	 * 
	 * @param credito
	 * @throws CrearCreditoQuirografarioException
	 * @throws SecuenciaBloqueadaException
	 * @throws MontoMinimoException
	 * @throws CabeceraCreditoQuirografarioException
	 * @throws BloqueoFinSemanaException
	 */
	public void crearCreditoQuirografario(DatosCredito credito) throws CrearCreditoQuirografarioException, SecuenciaBloqueadaException,
			MontoMinimoException, CabeceraCreditoQuirografarioException, BloqueoFinSemanaException;

	/**
	 * Consulta los prestamos vigentes para la c??dula pasada como par??metro.
	 * 
	 * @param cedula
	 * @param estado
	 * @return
	 * @author palvarez, cbastidas
	 */
	public List<Prestamo> getPrestamosVigentesPorCedula(String cedula,
			List<String> estadoCredito);

	/**
	 * Consulta todos los prestamos para una c??dula dada. No considera el
	 * estado del prestamo
	 * 
	 * @param cedula
	 * @return
	 * @author palvarez, cbastidas
	 */
	public List<Prestamo> getPrestamosPorCedula(String cedula);

	/**
	 * Devuelve un prestamo por el nut de la solicitud
	 * 
	 * @param nut
	 * @return
	 * @author roberto
	 */
	public Prestamo getPrestamoByNut(Long nut);

	/**
	 * Devuelve un prestamo por la clave primaria
	 * 
	 * @param pk
	 * @return
	 * @author palvarez, cbastidas
	 */
	public Prestamo getPrestamoByPk(PrestamoPk pk);

	/**
	 * Busca el resumen total de prestamos realizados y el monto
	 * 
	 * @return
	 * @author palvarez, cbastidas
	 */
	public CreditoConsolidado getResumenConsolidadoTotal();

	/**
	 * Busca el resumen en el dia actual de prestamos realizados y el monto
	 * 
	 * @return
	 * @author palvarez, cbastidas
	 */
	public CreditoConsolidado getResumenConsolidadoDiario();

	/**
	 * Consulta creditos quirografarios vigentes que no han sido cancelados ni
	 * rechazados
	 * 
	 * @param cedula
	 * @return
	 * @author palvarez, cbastidas
	 */
	public List<Prestamo> consultarQuirografariosVigentes(String cedula,
			List<String> estadoCredito, List<Long> tipoCredito);

	/**
	 * Obtiene la situacion en que se encuentra el prestamo con respecto al
	 * estado del afiliado y estado del prestamo
	 * 
	 * @param prestamoPk
	 * @return
	 * @throws SituacionPrestamoNoExisteException
	 * @author palvarez, cbastidas
	 */
	public SituacionPrestamo obtenerSituacionPrestamo(
			DatosSituacionPrestamo datSituacionPrestamo)
			throws SituacionPrestamoNoExisteException;

	/**
	 * Consulta si tiene un quirografario en mora
	 * 
	 * @param cedula
	 * @return
	 * @author palvarez, cbastidas
	 */
	public boolean tienePrestamoQuirografarioMora(String cedula,
			List<String> estadoDividendo);

	/**
	 * Consulta si tiene un quirografario vigente
	 * 
	 * @param cedula
	 * @return
	 * @author palvarez, cbastidas
	 */
	public boolean tienePrestamoQuirografarioVigente(String cedula,
			List<String> estadoCredito);

	/**
	 * Consulta el total de un credito vigente
	 * 
	 * @param cedula
	 * @return
	 * @author palvarez, cbastidas
	 */
	public BigDecimal getTotalCreditoVigente(String cedula,
			List<String> estadoPrestamo);

	/**
	 * Consulta el total del saldo del credito vigente
	 * 
	 * @param cedula
	 * @return
	 * @author palvarez, cbastidas
	 */
	public BigDecimal getTotalSaldoCreditoVigente(String cedula,
			List<String> estadoPrestamo, List<String> estadoDividendo);

	/**
	 * Consulta el plazo de endeudamiento
	 * 
	 * @param tipoCredito
	 * @param claseCredito
	 * @param fecha
	 * @param plazo
	 * @return
	 * @author palvarez, cbastidas
	 */
	public HistoricoAniosPlazoCapitalEndeudamiento getAniosPlazoCapitalEndeudamiento(
			int tipoCredito, int idCodigoCredito, int plazo, Date fecha);

	/**
	 * Consulta los creditos quirografarios del host en base a los codigos de
	 * los quiros del HOST
	 * 
	 * @param cedula
	 * @param listaCreditos
	 * @return
	 * @throws ListaVaciaException
	 * @author palvarez, cbastidas en caso de que la lista de codigos de
	 *         creditos esta vacia o null
	 * 
	 */
	public List<CreditoQuirografarioHost> getCreditosQuirografariosHost(
			String cedula, List<String> listaCreditos)
			throws ListaVaciaException;

	/**
	 * Consulta creditos quirografarios vigentes que no han sido cancelados ni
	 * rechazado, para validaciones en precalificacion de hipotecarios
	 * 
	 * @param cedula
	 * @author palvarez, cbastidas
	 */
	public List<Prestamo> consultarQuirografariosVigentesPH(String cedula,
			List<String> estadoCredito, List<Long> tipoCredito);

	/**
	 * Consulta si tiene un quirografario vigente bajo criterios validaciones
	 * precalificacion PH
	 * 
	 * @param cedula
	 * @return
	 * @author palvarez, cbastidas
	 */
	public boolean tienePrestamoQuirografarioVigentePH(String cedula,
			List<String> estadoCredito, List<Long> tipoCredito);

	/**
	 * Servicio que realiza toda la l??gica de novaci??n de un cr??dito vigente
	 * Generaci??n de liquidaci??n, Cancelaci??n de la liquidaci??n,
	 * descomprometimiento de las garant??as y creaci??n de un nuevo prestamo
	 * quirografario.
	 * 
	 * @param credito
	 * @throws NovarCreditoQuirografarioException
	 * @throws DiasNoPermitidosNovacionException
	 * @throws BloqueoFinSemanaException
	 * @author palvarez, cbastidas
	 */
	public void novarCreditoQuirografario(DatosCredito credito)
			throws NovarCreditoQuirografarioException, DiasNoPermitidosNovacionException, BloqueoFinSemanaException;

	/**
	 * Buscar prestamo por clave primaria
	 * 
	 * @param credito
	 * @throws CrearCreditoQuirografarioException
	 * @author palvarez, cbastidas
	 */
	public Prestamo bucarPrestamoPk(PrestamoPk pk);

	/**
	 * Metodo que obtiene el detalle del prestamo
	 * 
	 * @param cedula
	 * @param numeroPrestamo
	 * @param codPreTip
	 * @param ordPreAfi
	 * @param codPreCla
	 * @retunr DetalleCredito
	 * @author rtituana
	 */
	public DetalleCredito consultarDetalleCredito(String cedula,
			Long numeroPrestamo, Long codPreTip, Long ordPreAfi, Long codPreCla);

	/**
	 * acantos
	 * 
	 * @param fecha_ant
	 * @param fecha_post
	 * @return
	 */
	public List<Prestamo> consultarprestamospendientesaprobacion(
			Date fecha_ant, Date fecha_post);

	/**
	 * acantos
	 * 
	 * @param p
	 * @param observacion
	 * @param idmotivoprestamo
	 */
	public void rechazarPrestamoPda(Prestamo p, String observacion,
			Long idmotivorechazo, String cedulafuncionario, String estadoActualPrestamo)
			throws ActualizarPrestamoEstadoHistoricoException;

	/**
	 * Para control de fraudes
	 * 
	 * @param numafi
	 * @param rucfinanciera
	 * @param tipocta
	 * @param numerocta
	 * @return
	 */
	public boolean existecuentaenlistablanca(String numafi,
			String rucfinanciera, String tipocta, String numerocta);

	/**
	 * @param pk
	 * @author acantos
	 */
	public void actualizarcabeceraprestamoPDA(PrestamoPk pk,
			boolean verificacionRegistro);
	
	/**
	 * Metodo que actualiza el estado de un prestamo a ECC.
	 * 
	 * @param pk
	 */
	public void actualizarEstadoPrestamoECC(PrestamoPk pk);
	

	/**
	 * Funcion que indica si tiene o no creditos en estado ERC
	 * 
	 * @param cedula
	 * @return
	 */
	List<Prestamo> poseeCreditosERC(String cedula);

	/**
	 * Metodo que obtiene lista de prestamos en estado de bloqueo ECC.
	 * 
	 * @param cedula
	 * @return List<PrestamoBiess>
	 */
	List<PrestamoBiess> poseeCreditosECC(String cedula);
	
	/**
	 * Consulta los prestamos en estado de bloqueo por parametros
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param cedula
	 * @param flagBloqueo
	 * @return
	 * @throws PrestamoException
	 */
	List<Prestamo> obtenerCreditoPorParametros(Date fechaDesde,
			Date fechaHasta, String cedula, String estado, String flagBloqueo)
			throws PrestamoException;

	/**
	 * Consulta los prestamos en PDA
	 * 
	 * @param fecha_ant
	 * @param fecha_post
	 * @return
	 */
	List<SolicitudPda> consultarprestamosPda(Date fecha_ant, Date fecha_post,
			String cedulaBus, boolean conCedula);

	/**
	 * Consulta la lista de prestamos PDA por cedula y fechas
	 * 
	 * @param fecha_ant
	 * @param fecha_post
	 * @param cedula
	 * @return
	 */
	List<SolicitudPda> consultarPrestamoPDAPorCedula(String cedula);

	/**
	 * @param pk
	 * @author acantos
	 */
	void actualizarPrestamoRegCivil(Prestamo prestamo);
	
	/**
	 * Metodo que agrega una bandera de bloqueo por error ECC al prestamo que se esta intentado novar
	 *  
	 * @param prestamo
	 */
	void actualizarPrestamoCambioInuCtaBco(Prestamo prestamo);

	/**
	 * Desbloquea los prestamos que se encuentran en novacion
	 * 
	 * @param prestamo
	 * @param cedulaFuncionario
	 */
	void desbloquearPrestamoNovacion(Prestamo prestamo, String cedulaFuncionario);

	/**
	 * Consulta la suma de los saldos vigentes
	 * 
	 * @param cedula
	 * @return
	 */
	BigDecimal sumaSaldosPrestamosVigentes(String cedula);
	
	/**
	 * Valida si existen movimientos inusuales de cuentas bancarias
	 * 
	 * @param cedula
	 * @return boolean
	 */
	boolean cambioInusualCtaBco(String cedula);

	/**
	 * Valida si el monto del prestamo es mayor o igual al monto m??nimo.
	 * 
	 * @param montoPrestamo Monto del pr??stamo
	 * @return boolean Indica si el monto del prestamo es mayor o igual al monto m??nimo.
	 * @throws PrestamoException
	 */
	boolean cumpleMontoMinimo(BigDecimal montoPrestamo) throws PrestamoException;

	/**
	 * Servicio para anular un prestamo
	 * 
	 * @param p
	 * @param codigoNuevoEstado
	 * @param observacion
	 * @throws ActualizarPrestamoEstadoHistoricoException
	 */
	void anularPrestamo(Prestamo p, String observacion) throws ActualizarPrestamoEstadoHistoricoException;
	/**
	 * Metodo que actualiza el estado de un prestamo.
	 * 
	 * @param pk
	 */
	public void actualizarEstadoPrestamo(PrestamoPk pk,String estado);
	
	/**
	 * Actualizar a estado PDP un prestamo.
	 * 
	 * @param pk - primary key de un prestamo.
	 */
	public void actualizarcabeceraprestamoPDC(PrestamoPk pk);
	
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
	 *            - cedula afiliado.
	 * @param idTipoProducto
	 *            -tipo de producto.
	 * @param visaPasaporte - visa o pasaporte del afiliado.
	 * 
	 * @return List<Prestamo>.
	 */
	public List<Prestamo> consultarPrestamos(List<String> estadosPrestamo,
			Date fechaAnt, Date fechaPost, String cedulaAfiliado,
			Long idTipoProducto, String visaPasaporte);
	
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
	 * Obtiene una division politica.
	 * 
	 * @param sucursalfila
	 *            - datos de la sucursal.
	 * 
	 * @return <DivisionPolitica>
	 * 
	 * @throws DivisionPoliticaException
	 *             - excepcion.
	 */
	public DivisionPolitica consultaDivisionPoliticaPorId(Sucursal sucursalfila)
			throws DivisionPoliticaException;

	/**
	 * Obtienen una sucursal.
	 * 
	 * @param rucemp
	 * @param codsucu
	 * 
	 * @return <Sucursal>
	 * 
	 * @throws SucursalException
	 */
	public Sucursal obtenerSucursal(String rucemp, String codsucu)
			throws SucursalException;

	/**
	 * Obtiene una cuenta valida de un afiliado.
	 * 
	 * @param cedula
	 * 
	 * @return <CuentaBancariaAfiliado>
	 */
	public CuentaBancariaAfiliado obtenerCuentaValidaAfiliado(String cedula);

	/**
	 * Obtiene una cuenta anterior de un afiliado.
	 * 
	 * @param cedula
	 * 
	 * @return <CuentaBancariaAfiliado>
	 */
	public CuentaBancariaAnterior obtenerCuentabancariaAnterior(String cedula);
	
	/**
	 * Obtiene informacion adicional de prestamos
	 * 
	 * @param prestamoPk
	 * @return {@link PrestamoInformacion}
	 */
	PrestamoInformacion obtenerPrestamoInformacion(PrestamoPk prestamoPk);
	
	/**
	 * Actualizar a estado PDV un prestamo.
	 * 
	 * @param pk
	 *            - primary key de un prestamo.
	 */
	public void actualizarcabeceraprestamoPDV(PrestamoPk pk);
	
	/**
	 * Obtiene la lista de detalles dado el PK del credito
	 * 
	 * @param prestamoPk
	 * @return List<PrestamoInformacionDetalle>
	 */
	List<PrestamoInformacionDetalle> obtenerPrestamoInformacionDetalle(PrestamoPk prestamoPk);
	
	/**
	 * Devuelve el n??mero de pr??stamo que tiene una persona en un a??o especificado y con un estado especificado
	 * 
	 * @param numafi
	 * @param estadoPrestamo
	 * @param aniper
	 * @return
	 */
	Long contarPorEstadoAnio(String numafi, String estadoPrestamo, Long aniper);
	
	/**
	 * Cuenta cu??ntos prestamos tiene el afiliado por cedula, estado y tipo de credito
	 * 
	 * @param numafi
	 * @param estadoPrestamo
	 * @param codpretip
	 * @return
	 */
	Long contarPorEstadoTipoPrestamo(String numafi, String estadoPrestamo, Long codpretip);
	
	/**
	 * Metodo para verificar si una persona esta solicitando un prestamo mayor
	 * 
	 * @param monto
	 * @param sumaSaldos
	 * @param sbu
	 * @param numsbu
	 * @return
	 */
	boolean verificarMontoMaximoSBU(BigDecimal monto, BigDecimal sumaSaldos, BigDecimal sbu, BigDecimal numsbu);
	
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
	 * Realiza la anulacion de un credito llamando a un procedimiento almacenado
	 * 
	 * @param parametrosCredito
	 * @param estadoActualCredito
	 * @throws PrestamoPqCoreException
	 */
	void anularCreditoPQConProcedimiento(Map<String, Long> parametrosCredito, String estadoActualCredito) throws PrestamoPqCoreException;
	
	/**
	 * Realiza la anulacion de un credito llamando a un procedimiento almacenado
	 * 
	 * @param parametrosCredito
	 * @param estadoActualCredito
	 * @param estadoAnulacion
	 *            Indica el estado de anulacion, como REC o ANU
	 * @throws PrestamoPqCoreException
	 */
	void anularCreditoPQConProcedimiento(Map<String, Long> parametrosCredito, String estadoActualCredito, String estadoAnulacion)
			throws PrestamoPqCoreException;
	
	/**
	 * Devuelve el numero de prestamo que tiene una persona en un anio especificado, con un estado especificado a partir
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
	 * Devuelve una lista de Prestamos vigentes para novar dada la cedula
	 * 
	 * @param cedula
	 * @param tipoPrestamista
	 * @return
	 */
	List<Prestamo> obtenerPrestamosVigentesNovacion(String cedula, TipoPrestamista tipoPrestamista);
	
	/**
	 * Devuelve un listado de Prestamos que se pueden novar para el simulador
	 * 
	 * @param cedula
	 * @param tipoPrestamista
	 * @return
	 */
	List<Prestamo> obtienePrestamosVigentesNovacionSimulador(String cedula, TipoPrestamista tipoPrestamista);
	
	/**
	 * Determina si un prestamo tiene cancelado al menos un porcentaje (se usa en novaciones)
	 * 
	 * @param prestamoVigente
	 * @param resumenConsolidado
	 * @param requisitosPrec
	 */
	void determinarPorcentajeCancelacionPqVigente(Prestamo prestamoVigente, ResumenConsolidado resumenConsolidado,
			ValidarRequisitosPrecalificacion requisitosPrec);
			
	/**
	 * Metodo para validar el monto minimo del credito
	 * 
	 * @param monto
	 * @param plazo
	 * @param tipoPrestamista
	 * @param edadAsegurado
	 * @return Devuelve un objeto de tipo EvaluaReglaMontoMinimoDto, donde se indica si cumplio la validacion de monto
	 *         minimo (true), el valor del monto minimo (dividido para 100) y el valor del monto minimo
	 * @throws MontoMinimoException
	 */
	EvaluaReglaMontoMinimoDto validarMontoMinimoPrestamo(BigDecimal monto, BigDecimal plazo, TipoPrestamista tipoPrestamista, int edadAsegurado)
			throws MontoMinimoException;
	
	/**
	 * Metodo que indica si se pueden realizar novaciones en base a la fecha actual
	 * 
	 * @return Devuelve true si se pueden novaciones y false en caso contrario
	 */
	boolean permiteRealizarNovaciones();
	
	public List<Prestamo> listarPrestamosCancelados(String cedula);

	public Long traerNut(Long tiposolicitud, Long numeroSolicitud);

	/**
	 * Permite crear la operacion en el SAC
	 * 
	 * @param datosCredito
	 */
	void crearOperacionSAC(DatosCredito datosCredito, Prestamo prestamo);

	/**
	 * Valida Si tiene creditos subgarantizados consultando en la tabla
	 * KSAFITCESANTIAS SALTOT es negativo
	 * 
	 * @param identificacion
	 * @return 
	 */
	boolean tieneCreditosSubgarantizados(String identificacion);
	
	
	/**
	 * Total fondos resrva y fondos de cesantia
	 * @param identificacion
	 * @return el total de los fondos de reserva y cesantia
	 */
	BigDecimal totalGarantiaCliente(String identificacion);
	
	
	
	
	/**
	 * Obtiene el total de creditos de conciliacion por usuario
	 * @param cedula
	 * @return 
	 */
	BigDecimal obtenerTotalCreditosConciliacion(final String cedula);
	/**
	 * 
	 * @param tipoPrestamista
	 * @param rangoJub
	 * @param rangoAfi
	 * @return
	 * @throws PeriodoComprobanteException
	 */
	public boolean validarHabilitaNovacionPlanillaje(TipoPrestamista tipoPrestamista,String rangoJub,String rangoAfi, String rangoAfiJub) throws PeriodoComprobanteException;


	 /**
	  * Valida si existe un credito castigado en PH
	  * @param cedula
	  * @return
	  */
	boolean existeUnCreditoCastigadoPh(String cedula);
	//req-617
	Prestamo buscarCreditoAnterior(Prestamo prestamoActual);
	
}