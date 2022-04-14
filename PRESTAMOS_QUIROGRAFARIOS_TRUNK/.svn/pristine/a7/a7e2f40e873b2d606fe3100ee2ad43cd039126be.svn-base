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

import javax.ejb.Remote;

import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.creditos.enumeracion.SituacionPrestamo;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.ListaVaciaException;
import ec.gov.iess.creditos.excepcion.PrestamoPqCoreException;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DatosSituacionPrestamo;
import ec.gov.iess.creditos.modelo.dto.SolicitudPda;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosPrecalificacion;
import ec.gov.iess.creditos.modelo.persistencia.CreditoConsolidado;
import ec.gov.iess.creditos.modelo.persistencia.CreditoQuirografarioHost;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCredito;
import ec.gov.iess.creditos.modelo.persistencia.HistoricoAniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.dto.EvaluaReglaMontoMinimoDto;
import ec.gov.iess.creditos.pq.excepcion.ActualizarPrestamoEstadoHistoricoException;
import ec.gov.iess.creditos.pq.excepcion.BloqueoFinSemanaException;
import ec.gov.iess.creditos.pq.excepcion.CabeceraCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.CrearCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.DiasNoPermitidosNovacionException;
import ec.gov.iess.creditos.pq.excepcion.MontoMinimoException;
import ec.gov.iess.creditos.pq.excepcion.NovarCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaBloqueadaException;
import ec.gov.iess.creditos.pq.excepcion.SituacionPrestamoNoExisteException;

/**
 * Implementacion Ejb para la Prestamo Quirografario Servicio
 * 
 * @version 1.0
 * @author palvarez, cbastidas,pjarrin
 */
@Remote
public interface PrestamoServicioRemote {

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
	 * Consulta los prestamos para la cédula y el estado pasados como
	 * parámetro.
	 * 
	 * @param cedula
	 * @param estado
	 * @return
	 * @author palvarez, cbastidas
	 */
	public List<Prestamo> getPrestamosVigentesPorCedula(String cedula,
			List<String> estadoCredito);

	/**
	 * Consulta todos los prestamos para una cédula dada. No considera el
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
	 * @return
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
	 * Servicio que realiza toda la lógica de novación de un crédito vigente
	 * Generación de liquidación, Cancelación de la liquidación,
	 * descomprometimiento de las garantías y creación de un nuevo prestamo
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
	public List<SolicitudPda> consultarprestamosPda(Date fecha_ant,
			Date fecha_post, String cedulaBus, boolean conCedula);

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
	 * Consulta la lista de prestamos PDA por cedula y fechas
	 * 
	 * @param fecha_ant
	 * @param fecha_post
	 * @param cedula
	 * @return
	 */
	List<SolicitudPda> consultarPrestamoPDAPorCedula(String cedula);
	
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
	
	/**
	 * @param estadosPrestamo
	 * @param fechaAnt
	 * @param fechaPost
	 * @param idTipoProducto
	 * @param cedulasPrestamo
	 * @return
	 */
	public List<Prestamo> consultarPrestamosPorEstadosCedulas(List<String> estadosPrestamo, Date fechaAnt,
			Date fechaPost, Long idTipoProducto, List<String> cedulasPrestamo);
	
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
	 * 
	 * @param identificacion
	 * @return el total de los fondos de reserva y cesantia
	 */
	BigDecimal totalGarantiaCliente(String identificacion);

	/**
	 * valida si tiene un credito castigado en PH
	 * @param cedula
	 * @return
	 */
	boolean existeUnCreditoCastigadoPh(String cedula);
}