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
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * Interface para los dividendos del prestamo quirografrio
 * 
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
/**
 * @author cbastidas
 *
 */
/**
 * @author cbastidas 03/10/2011
 *
 */
@Local
public interface DividendoPrestamoDao 
extends GenericDao<DividendoPrestamo, DividendoPrestamoPk> {

	/**
	 * Consulta si tiene un dividendos en mora en HL.
	 * 
	 * @param cedula
	 *            identificcaion de la persona
	 * @return true en caso de que tenga dividendos y false en caso de no
	 *         encontrar dividendos en mora.
	 * @author cvillarreal
	 */
	public boolean tienePrestamoMoraHl(String cedula,List<String> estadoDividendo);

	/**
	 * Consulta los dividendos en mora de los préstamos quirografarios vigentes
	 * en HL.
	 * 
	 * @param cedula
	 *            identificacion de la persona.
	 * @return una lista con los dividendos en mora si no tiene dividentos
	 *         retorn una lista vacia
	 * @author cvillarreal
	 */
	public List<DividendoPrestamo> listaPrestamoMoraHl(String cedula,List<String> estadoDividendo);

	/**
	 * Consulta los dividendos de un prestamo usando los datos del PK de un credito
	 * @param codprecla
	 * @param codpretip
	 * @param numpreafi
	 * @param ordpreafi
	 * @return
	 */
	public List<DividendoPrestamo> getDividendosByIdPrestamo(Long codprecla, Long codpretip, Long numpreafi,
			Long ordpreafi);
	
	/**
	 * Cuenta el numero de dividendos en EPL y su fecha de pago es mayor al sysdate 
	 * @param prestamoPk
	 * @param estadoDividendo
	 * @param estadoComprobante
	 * @return
	 */
	public BigDecimal contarDividendosenEPL(PrestamoPk prestamoPk, List<String> estadoDividendo,List<String> estadoComprobante);

	/**
	 * Cuenta el numero de dividendos que tiene un prestamo por estado
	 * @param prestamoPk
	 * @param estadoDividendo
	 * @return
	 */
	public Long contarDividendosPorPrestamoYEstado(PrestamoPk prestamoPk, List<String> estadoDividendo);
	
	/**
	 * Cuenta el numero de dividendos que tiene un prestamo en mora
	 * @param prestamoPk
	 * @param estadoDividendo
	 * @return
	 */
	public BigDecimal contarDividendosPorPrestamoEnMora(PrestamoPk prestamoPk,List<String> estadoDividendo);
	
	/**
	 * Verifica si todos los aportes tienen fecha de inicio de rendimiento
	 * @param cedula
	 * @param tipoAporte
	 * @param cumpleImposiciones
	 * @return
	 */
	public BigDecimal comprobarAportesFecha(String cedula,List<String> tipoAporte,String cumpleImposiciones);

	/**
	 * Comprueba si tiene realizada una solicitud de fondos de reserva
	 * @param cedula
	 * @param tipoSolicitud
	 * @param estadoSolicitud
	 * @return
	 */
	public BigDecimal comprobarSolicitudFondos(String cedula,List<String> tipoSolicitud,List<String> estadoSolicitud);

	/**
	 * Coprueba si tiene una solicitud de fondos de reserva en tramite
	 * @param cedula
	 * @param estadoSolicitud
	 * @return
	 */
	public BigDecimal comprobarSolicitudFondosTramite(String cedula,List<String> estadoSolicitud);
	
	/**
	 * Comprueba si el afiliado tiene cargos.
	 * @param cedula
	 * @param estadoCargoReg
	 * @param estadoCargoPro
	 * @return
	 */
	public BigDecimal comprobarCargos(String cedula,List<String> estadoCargoReg,List<String> estadoCargoPro);
	
	/**
	 * Comprueba si el afiliado tiene bloqueos.
	 * @param cedula
	 * @param estadoBloqueado
	 * @return
	 */
	public BigDecimal comprobarBloqueos(String cedula,List<String> estadoBloqueado);
	
	/**
	 * 
	 * <b>
	 * Comprueba si existe un dividendo en comprobante de pago individual.
	 * </b>
	 * <p>[Author: cbastidas, Date: 09/06/2011]</p>
	 *
	 * @param cedula : Número de cédula
	 * @param estadoDividendo : Lista de estados de dividendo
	 * @return BigDecimal Número de dividendos en comprobante de pago
	 */
	public BigDecimal comprobarComprobantePagoIndividual(String cedula,
			List<String> estadoDividendo);
	/**
	 * 
	 * <b>
	 * Retorna el saldo de capital del prestamo.
	 * </b>
	 * <p>[Author: cbastidas, Date: 10/06/2011]</p>
	 *
	 * @param cedula Numero de cedula
	 * @return BigDecimal Saldo de capital
	 */
	public List<BigDecimal> saldoCapitalPQ(String cedula);
	
	
	/**
	 * 
	 * <b>
	 * Cuenta el número de días de mora.
	 * </b>
	 * <p>[Author: cbastidas, Date: 11/06/2011]</p>
	 *
	 * @param prestamoPk : Clave primaria del crédito
	 * @return BigDecimal Número de días de mora 
	 */
	public BigDecimal diasMoraPQ(PrestamoPk prestamoPk);
	
	/**
	 * 
	 * <b>
	 * Lista los dividendos de creditos vigentes por cedula.
	 * </b>
	 * <p>[Author: cbastidas, Date: 11/06/2011]</p>
	 *
	 * @param cedula : Nýmero de cédula del solicitante
	 * @return List<DividendoPrestamo> Lista de dividendos
	 */
	public List<DividendoPrestamo> listaCuotaPrestamos(String cedula);
	
	/**
	 * Obtiene los dividendos por prestamo que estan dentro de una lista de estados
	 * @param prestamoPk
	 * @param estados
	 * @return
	 */
	public List<DividendoPrestamo> obtenerDividendosPorPrestamoYEstado(PrestamoPk prestamoPk, List<String> estados);
	
	/**
	 * Obtiene los dividendos de préstamo por la clase de estado y no solo de un estado en particular.
	 * Las clases de estados pueden ser: cancelado, debitado, liquidado
	 * @param prestamoPk
	 * @param claseEstados
	 * @return
	 */
	public List<DividendoPrestamo> obtenerDividendosPorClaseEstado(PrestamoPk prestamoPk, List<String> claseEstados);
	
	/**
	 * Obtiene el numero de dividendos buscados por su clave primaria
	 * @param dividendoPrestamoPk
	 * @return
	 */
	public Long contarPorPK(DividendoPrestamoPk dividendoPrestamoPk);
	
	
	/**
	 * @param dividendoPrestamoPk
	 * @return
	 */
	public DividendoPrestamo obtenerPorPK(DividendoPrestamoPk dividendoPrestamoPk);
	
	/**
	 * @param dividendoPrestamoPk
	 * @return
	 */
	public List<DividendoPrestamo> obtenerListaPorPKSinNumDiv(DividendoPrestamoPk dividendoPrestamoPk);
	
	/**
	 * Cuenta los dividendos en mora desde una fecha hacia atras
	 * 
	 * @param cedula
	 * @return
	 */
	BigDecimal contarDividendosMoraBiessPorFecha(String cedula);
	
	/**
	 * Cuenta los dividendos dada la informacion del prestamo y fechas de cancelaciÃ³n
	 * 
	 * @param numpreafi
	 * @param ordpreafi
	 * @param codpretip
	 * @param codprecla
	 * @param fecpagdiv
	 * @param feccandiv
	 * @param forcandiv
	 * @return
	 */
	BigDecimal contarDividendosPorFecha(Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla, Date fecpagdiv, Date feccandiv,
			List<String> forcandiv);
	
	/**
	 * Cuenta los dividendos en mora para prestamos Emergentes
	 * 
	 * @param fechaPagoDividendos
	 * @param cedula
	 * @return
	 */
	BigDecimal contarDividendosMoraBiessEmergente(Date fechaPagoDividendos, String cedula);
	
}